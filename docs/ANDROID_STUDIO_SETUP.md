# Android Studio — reproducible project setup

**Updated 18 November 2026.** This is the current setup guide. The old October placeholder app is no longer the project's state.

## Prerequisites
- Android Studio with JDK **17** and Android **SDK Platform 35**
- Pixel 9 Pro emulator, Android **API 36** for the recorded coursework tests (any Android API 26+ can run the app)
- Internet access for first Gradle dependency download and Google Maps tiles
- Official, approved Traffic Scotland DATEX II Client ID/Key **only if testing LIVE feeds**
- Android-restricted Google Maps SDK for Android key **only if testing map tiles**

## Clone and sync (Windows)

1. Open Android Studio → **Get from VCS** → clone `https://github.com/KirilBezmen/gcu-traffic-scotland-android.git`. The repository is private; authenticated access is required.
2. Open the **project root**, not its `app` subdirectory. The Gradle project is named `Bezmen_Kiril_s2338746` in `settings.gradle.kts`.
3. Check that `gradlew.bat`, `gradlew` and `gradle/wrapper/gradle-wrapper.jar` exist; these are **committed official Gradle 8.9** files ([provenance](GRADLE_WRAPPER_PROVENANCE.md)).
4. Let Android Studio create a **local `local.properties`** with `sdk.dir` (never commit this file).
5. For a **credential-free DEBUG smoke test**, omit Traffic Scotland credentials; historic lecturer files (4 incidents / 405 roadworks) must be clearly labelled `Historic lecturer XML samples — NOT current traffic conditions`.
6. To run **authorised LIVE**, add approved `TRAFFIC_CLIENT_ID` and `TRAFFIC_CLIENT_KEY` in ignored `local.properties`; do not share screenshots of their values. Map needs separate `MAPS_API_KEY`. See [credential setup](ANDROID_CREDENTIALS_SETUP.md).
7. Perform **File → Sync Project with Gradle Files**; select **app**, Pixel 9 Pro and **Run**.

## Build and tests from Terminal

Open the terminal in repository root:

```powershell
.\gradlew.bat :app:assembleDebug :app:assembleDebugAndroidTest :app:testDebugUnitTest
```

A clean wrapper build uses Gradle **8.9** and checks the official distribution hash. On macOS/Linux use `./gradlew`.

Run Android instrumentation tests from Android Studio:
- `TrafficXmlParserTest` (representative and edge cases)
- `OfficialXmlFixturesTest` (whole historical lecturer feeds)
- `NavigationChromeTest` (UI state/recreation)

**CI compiles Android tests, but does not execute them on an emulator.** Record actual test output and Android API level. See [QA execution matrix](QA_EXECUTION_MATRIX.md).

## Important security and submission notes

The local authenticated APK compiles DATEX credentials into `BuildConfig`, so **do not upload that APK or commit the API keys**. The CI debug APK uses historical fixtures and is not a completed authorised-LIVE coursework release. An approved examiner delivery solution is still required.

**Not yet evidenced:** clean Windows clone tested by the student, full current instrumentation suite on the final APK, and a safe published APK. The repository's green CI confirms the automated build only; do not misreport these missing manual steps as PASS.
