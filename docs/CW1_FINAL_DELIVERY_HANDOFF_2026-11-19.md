# CW1 final delivery handoff — 19 November 2026

Module: **MHI326841 Mobile Platform Development**. Assessed coursework: **Traffic Scotland Explorer CW1**. Detailed official brief deadline: **Monday 23 November 2026, 17:00 UK**. Target upload: **22 November**.

This is a **submission checklist and audit**, not a declaration that the work has been submitted. Current authoritative implementation: `main` at `fad4c7a40c8bb71b7b4331660053e4a3a950876a` (checked 19 November); evidence must include the **exact local APK commit** when student tests again.

## Completed / independently confirmed
- `main` GitHub Actions run **44332929422**, 18 Nov 2026: **SUCCESS**. JDK 17, trusted Gradle 8.9 wrapper, `:app:assembleDebug`, `:app:assembleDebugAndroidTest` and `:app:testDebugUnitTest` ran successfully. This verifies compilation and host-JVM tests **not execution of Android instrumentation tests**.
- Successful CI artifact `TrafficScotlandExplorer-debug`, ID **14440102079**, created 18 Nov and scheduled to expire 25 Nov. **Credential-free DEBUG artifact only**: with no local DATEX credentials it displays historic lecturer XML samples, clearly labelled, and does **not** independently retrieve live traffic.
- Static source review: single `MainActivity`, multiple Fragments, `TrafficViewModel`, `TrafficRepository`; the app uses native Java/XML and `XmlPullParser` over a `StringReader` of downloaded XML; `ExecutorService` for network and `LiveData` for UI state; two distinct `res/layout*/activity_main.xml` layouts.
- Student manual observations dated 11 Oct and 18 Nov documented in `QA_EXECUTION_MATRIX.md`: 4/4 representative parser tests, 2/2 original fixture tests, M8/M80 filtering, Nearby 5/25/200 km, map selected 11 M80 situations, distinct landscape rail, live counts 7/421 and 423 after refresh, reported last-good-data cache.

## Outstanding before hand-in — do not mark complete
1. Run all **current** Android instrumentation tests on emulator. Suggested Windows command with running AVD: `./gradlew.bat :app:connectedDebugAndroidTest`. Capture resulting test counts, Android API, Git SHA and screenshot. Old 11 Oct passes do not prove 19 Nov-added tests.
2. Verify **hourly automatic refresh** (or clearly label as implemented/not time-observed); no-cache + offline **ERROR**; offline `CACHED` banner with actual network-off screenshot; controlled same-marker → Details → Back; and six distinct icon situations, if data permits.
3. Preserve or replace screenshots: the current Word **Figure 5 left edge is cropped** and does not show the claimed radius. Do not infer test results from a cropped capture.
4. **Distribution blocker:** `BuildConfig.TRAFFIC_CLIENT_ID` and `TRAFFIC_CLIENT_KEY` are recoverable from any APK compiled with real credentials. Do not upload or embed a LIVE-secret-bearing APK in the examiner package. Ask tutor for an acceptable credential-provisioning approach; CI credential-free sample APK is a fallback/demo artifact, not proof of public-live access.
5. Record narrated MP4 in English from the actual tested build. See `CW1_VIDEO_SCRIPT_2026-11-19.md`.
6. Review and complete Word declaration/signature, dates, actual safe APK URL, OneDrive MP4 URL, and final GitHub source URL; **do not fabricate missing evidence or claims**.
7. Verify clean Windows checkout/Gradle Sync/launch, reviewer access to GitHub (private repo access) and OneDrive permissions, and GCULearn actual upload confirmation.

## Official submission naming and contents
- **Word:** `Bezmen_Kiril_s2338746.docx`. Must contain exact originality declaration, detailed GenAI involvement **and prompt appendix**, links to accessible OneDrive MP4/GitHub project/GitHub APK, documented testing with Expected/Actual/Result.
- **Narrated video:** `Bezmen_Kiril_s2338746_Video.mp4`.
- **External examiner folder:** `Bezmen_Kiril_s2338746/` with credential-safe `.apk`, narrated `.mp4`, and **cleaned Android Studio source project ZIP**. Compress outer folder to a **.zip**, not .rar/.7z or password protected.
- **GCULearn:** Submit all required artefacts to their designated slots **before 23 Nov 17:00**, allowing headroom for uploads and permission verification.

## GenAI transparency
Category **G — Guided**. The official detailed brief says the Word document must include an appendix **with the prompting used**, in addition to an honest description of AI-assisted code, troubleshooting, tests, screenshots and report drafting. English translations of **actual Russian prompts** are supplied in `CW1_GENAI_PROMPT_TRANSLATIONS_2026-11-19.md`, explicitly identified as translations. Do not present fictional examples as actual prompts. Full chronological source: `GENAI_USAGE_LOG.md`.

## Rubric-sensitive findings
- Features 42 (includes map multi-selected; route + Nearby; type/comment/details; delay colour; icons). Rubric icon line has inconsistent 6 vs 8 maximum: do not invent resolution.
- Layout 8 (different portrait/landscape arrangements **and preserved state**).
- Parser/collections 10; MVVM 10; worker + startup/hourly 10.
- Documented tests 15 (source-to-UI 5, navigation 4, functionality 4, offline 2).
- Voiceover video 5.

Report notes and test matrix are historical, not independent runtime reproduction. Preserve verified student observations and explicitly identify remaining checks.