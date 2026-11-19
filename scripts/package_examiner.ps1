# GCU MHI326841 CW1 examiner package helper.
# This is a packaging helper, NOT an APK build or a secret audit.
# Usage (PowerShell 5+):
# .\scripts\package_examiner.ps1 -Apk "C:\safe\app-debug.apk" -Video "C:\safe\Bezmen_Kiril_s2338746_Video.mp4" -ProjectZip "C:\safe\AndroidStudioProjectClean.zip" -ConfirmCredentialFreeApk
[CmdletBinding()]
param(
    [Parameter(Mandatory=$true)][string]$Apk,
    [Parameter(Mandatory=$true)][string]$Video,
    [Parameter(Mandatory=$true)][string]$ProjectZip,
    [string]$OutputDirectory = ".\submission-output",
    [switch]$ConfirmCredentialFreeApk
)

$ErrorActionPreference = 'Stop'
$student = "Bezmen_Kiril_s2338746"
if (-not $ConfirmCredentialFreeApk) {
    throw "STOP: A real Traffic Scotland Client ID/Key compiled into BuildConfig can be extracted from the APK. Confirm the supplied APK is credential-free before packaging."
}
foreach ($path in @($Apk, $Video, $ProjectZip)) {
    if (-not (Test-Path -LiteralPath $path -PathType Leaf)) {
        throw "Missing input file: $path"
    }
}
if ([IO.Path]::GetExtension($Apk).ToLowerInvariant() -ne ".apk") { throw "Expected an .apk input." }
if ([IO.Path]::GetExtension($Video).ToLowerInvariant() -ne ".mp4") { throw "Expected an .mp4 input." }
if ([IO.Path]::GetExtension($ProjectZip).ToLowerInvariant() -ne ".zip") { throw "Expected a clean .zip source project." }

Add-Type -AssemblyName System.IO.Compression
$sourceZip = [IO.Compression.ZipFile]::OpenRead((Resolve-Path -LiteralPath $ProjectZip).Path)
try {
    $bad = @($sourceZip.Entries | Where-Object {
        $_.FullName -match '(^|/|\\)(local\.properties|key\.properties|\.env|[^/\\]*\.jks|[^/\\]*\.keystore)$'
    })
    if ($bad.Count -gt 0) {
        throw ("Project ZIP contains potentially sensitive files: " + (($bad | ForEach-Object FullName) -join ", "))
    }
} finally { $sourceZip.Dispose() }

New-Item -ItemType Directory -Path $OutputDirectory -Force | Out-Null
$root = (Resolve-Path -LiteralPath $OutputDirectory).Path
$staging = Join-Path $root $student
$archive = Join-Path $root ($student + ".zip")

if (Test-Path -LiteralPath $staging) { throw "Staging folder exists; move/remove it before retrying: $staging" }
if (Test-Path -LiteralPath $archive) { throw "ZIP already exists; move/remove it before retrying: $archive" }
New-Item -ItemType Directory -Path $staging | Out-Null
Copy-Item -LiteralPath $Apk -Destination (Join-Path $staging ($student + ".apk"))
Copy-Item -LiteralPath $Video -Destination (Join-Path $staging ($student + "_Video.mp4"))
Copy-Item -LiteralPath $ProjectZip -Destination (Join-Path $staging ($student + "_AndroidStudioSourceClean.zip"))

Compress-Archive -Path $staging -DestinationPath $archive -CompressionLevel Optimal
Write-Host "Created: $archive"
Write-Host "Reminder: manually verify APK contains no credentials, test source ZIP on clean Android Studio, inspect final ZIP, upload in GCULearn."
