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
- A build **without credentials** can show clearly labelled historical lecturer samples in DEBUG mode. It **does not demonstrate LIVE data** and must not be presented as the full authenticated assessed application.
- For coursework delivery, keep this GitHub repository as **clean source only**, without APK files or releases. The separately required External Examiner ZIP is submitted through GCULearn, under applicable permissions and Traffic Scotland sharing terms. A production solution would use a secured credential-provisioning mechanism. Do not silently switch to an unauthorised traffic source.

## Reproducible build

Trusted Gradle **8.9** wrapper scripts/JAR are now committed, copied from the official Gradle 8.9 source tag with exact Git blob identity verification. The stable Gradle distribution SHA-256 is pinned in `gradle-wrapper.properties`. See [Wrapper provenance](GRADLE_WRAPPER_PROVENANCE.md). Use `.\\gradlew.bat :app:assembleDebug` on Windows or `./gradlew :app:assembleDebug` on Unix. **A clean Windows clone and Android Studio import still need student execution/evidence.**

## Coursework and disclosure

The submitted Word report must accurately disclose the use of GenAI on code, tests, debugging and documentation, including exact actual prompts. Full chronology: [GenAI usage log](GENAI_USAGE_LOG.md). Follow the [submission checklist](SUBMISSION_CHECKLIST.md).
