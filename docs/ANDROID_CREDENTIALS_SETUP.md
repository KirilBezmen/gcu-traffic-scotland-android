# API setup and APK credential security

**Current project status (18 Nov 2026):** approved Traffic Scotland DATEX II access has been tested locally on Pixel 9 Pro: 7 incidents / 421 roadworks, followed by a 423-roadwork snapshot after refresh. Google Maps was separately configured and map tiles/markers observed on emulator. No secrets are stored in the GitHub repository.

## Configure the two independent APIs

1. **Official Traffic Scotland DATEX II** is the road-data source. Use **approved** Client ID and Client Key in ignored root `local.properties`:
   ```properties
   TRAFFIC_CLIENT_ID=YOUR_APPROVED_CLIENT_ID
   TRAFFIC_CLIENT_KEY=YOUR_APPROVED_CLIENT_KEY
   ```
   The two official, source-verified paths are built into `TrafficRepository.java`:
   - `https://datex2.trafficscotland.org/rest/2.3/publications/UnplannedEvents/Content.xml`
   - `https://datex2.trafficscotland.org/rest/2.3/publications/CurrentRoadworks/Content.xml`

   The official developer guide specifies **HTTP Basic** with `clientId:clientKey` Base64-encoded. The application now sends `Authorization: Basic ...`. No `TRAFFIC_ROADWORKS_URL` setting is needed.

2. **Google Maps SDK for Android** is used **only to display maps**. Enable that exact API in a Google Cloud project with billing, create a separate key restricted to Android package `com.kirilbezmen.trafficscotland` and the local debug signing certificate SHA-1, and add:
   ```properties
   MAPS_API_KEY=YOUR_ANDROID_RESTRICTED_MAPS_KEY
   ```
   A different release signing certificate requires its own Android key restriction.

3. Sync Gradle and run on the emulator. Source label **Official Traffic Scotland data** appears only after both official XML streams have downloaded and parsed. If valid credentials are absent from a *debug* build, only the clearly marked historic lecturer samples are used. Offline CACHED is only for previously downloaded live content.

## Critical: the current authenticated build is NOT safe to distribute

`app/build.gradle.kts` currently embeds DATEX credentials via `BuildConfig.TRAFFIC_CLIENT_ID` / `TRAFFIC_CLIENT_KEY`, and passes Maps key to the manifest. **Any APK compiled locally with those DATEX values can reveal the secret even though `local.properties` itself is gitignored.**

- **DO NOT** upload authenticated `app-debug.apk`, AAB or release APK to GitHub, OneDrive or the external examiner ZIP while the real DATEX key is embedded.
- **DO NOT** share screenshots of `local.properties`, Android `BuildConfig`, HTTP `Authorization` headers or Google Cloud Credentials pages that expose keys.
- A CI-built APK **without repository secrets** is safe from leaked DATEX credentials, but its debug SAMPLE mode **does not demonstrate the required LIVE assessment functionality**. Do not call it a complete final deliverable.
- A legitimate production solution requires either a **tutor-approved** secure server-side proxy / credential provisioning workflow or another **explicitly authorised** assessment delivery mechanism. Do not bypass Traffic Scotland's access controls or silently change to a different feed; verify constraints with Mario Mata before final APK submission.

## Reproducibility blocker

The project does not yet include `gradlew`, `gradlew.bat` or `gradle/wrapper/gradle-wrapper.jar`. GitHub CI uses Gradle 8.9 installed by workflow. Android Studio can build locally, but a *fresh clone via standard wrapper command* has not been proven. Add a genuine trusted wrapper (matching Gradle 8.9) using Android Studio / the Gradle wrapper task, commit the generated wrapper files and then test a clean checkout. Do not add an unverified wrapper JAR from an arbitrary source.

## Coursework and disclosure

The submitted Word report must accurately disclose the use of GenAI on code, tests, debugging and documentation, including exact actual prompts. Full chronology: [GenAI usage log](GENAI_USAGE_LOG.md). Follow the [submission checklist](SUBMISSION_CHECKLIST.md).
