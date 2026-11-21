# Traffic Scotland Explorer

**Glasgow Caledonian University | BSc (Hons) Software Development | Mobile Platform Development CW1 | 2026/27**

[![Android build](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions/workflows/android-build.yml/badge.svg)](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions/workflows/android-build.yml)

**Source code and assessment repository:** https://github.com/KirilBezmen/gcu-traffic-scotland-android

Traffic Scotland Explorer is a native Android application for viewing official Scottish road incidents and current roadworks. It was designed as a student-led, AI-assisted coursework project. The application is written in **Java with XML Views**, uses an **XmlPullParser** to process Traffic Scotland DATEX II publications, and displays results in accessible lists, detailed records and Google Maps.

> **Examiner note:** This repository contains the **Android Studio source project**, not the submitted APK or narrated video. The assessment requires the source to be importable into Android Studio. The APK and MP4 are supplied separately through the university's restricted GCULearn submission. API credentials are not committed. This educational project is not affiliated with Traffic Scotland.

## Features

| Feature | Implementation |
| --- | --- |
| Official feeds | Approved DATEX II UnplannedEvents and CurrentRoadworks Content.xml |
| Browsing | Separate Incidents and Roadworks lists, with event type and public description |
| Details | Available road references, validity, impact, delay, direction and location fields |
| Search | Exact road identifier matching, including distinguishing M8 from M80 |
| Nearby | Adjustable radius around a clearly stated fixed Glasgow reference point |
| Map | Google Maps SDK, source coordinates, markers and multiple selected results |
| Responsive design | Distinct portrait bottom navigation and landscape side rail |
| Data management | Background worker, startup/manual/hourly in-app refresh and last-good XML cache |
| Data transparency | LIVE, SAMPLE and CACHED states are labelled explicitly |

The historic XML samples included under `app/src/main/assets/` are for **development and reproducible testing**, not current traffic conditions.

## Import into Android Studio

**Requirements:** Android Studio, JDK 17, Android SDK Platform 35 and an emulator/device running Android 8.0 (API 26) or newer. The documented manual test device is Pixel 9 Pro, Android API 36.

1. Clone this repository or download **Code > Download ZIP**:
   ```bash
   git clone https://github.com/KirilBezmen/gcu-traffic-scotland-android.git
   ```
2. Open the project root in Android Studio. The root Gradle project is named `Bezmen_Kiril_s2338746`.
3. Allow Android Studio to create a local `local.properties` containing `sdk.dir`. This file must **not** be committed.
4. For **authorised LIVE data**, add the individually approved credentials to that local file:
   ```properties
   TRAFFIC_CLIENT_ID=YOUR_APPROVED_CLIENT_ID
   TRAFFIC_CLIENT_KEY=YOUR_APPROVED_CLIENT_KEY
   MAPS_API_KEY=YOUR_ANDROID_RESTRICTED_MAPS_KEY
   ```
   The Maps key is separate from DATEX access and must be restricted to the Android app package and signing certificate.
5. Sync Gradle. Select the `app` configuration and launch an API 26+ device or emulator.

**Without credentials:** the DEBUG build deliberately shows labelled *historic lecturer XML samples*. It does not pretend these are LIVE. Google Maps requires a separately configured key. The assessment video shows student-operated testing with authorised LIVE data.

## Build and tests

The committed **Gradle 8.9 Wrapper** allows a reproducible build. Run from the repository root:

```powershell
.\gradlew.bat :app:assembleDebug :app:assembleDebugAndroidTest :app:testDebugUnitTest
```

On Linux/macOS use `./gradlew`. To **execute** Android instrumentation tests with a running emulator, use:

```powershell
.\gradlew.bat :app:connectedDebugAndroidTest
```

**Evidence:** GitHub Actions successfully compiled the main application and Android test APK and executed JVM tests on 18 November 2026. CI does **not** execute Android instrumentation tests on an emulator. The student separately observed four initial parser tests and two full lecturer fixture tests passing on Pixel 9 Pro on 11 October. Manual tests on 18 November covered official LIVE data, manual refresh, road search, Nearby, orientation and map marker selection. See [QA execution matrix](docs/QA_EXECUTION_MATRIX.md) for exact dates, expected and actual results, and remaining limits.

Some additional scenarios, including the latest complete Android instrumentation suite, a timed full-hour refresh, no-cache offline error and a six-icon coverage check, have not been independently verified. They are **not** labelled PASS.

## Architecture

```text
Official Traffic Scotland DATEX II publications
       |
       v
TrafficRepository (HTTPS + worker thread + cached XML)
       |
       v
Downloaded XML String -> TrafficXmlParser (XmlPullParser)
       |
       v
TrafficSituation / TrafficRecord / TrafficLocation
       |
       v
TrafficViewModel (shared state, filters, selected map items)
       |
       v
Single MainActivity + Home / Lists / Details / Map Fragments
       |
       v
Portrait and landscape Android XML layouts
```

Only the two lecturer-approved DATEX II traffic publications are used as live sources. Google Maps is a visualisation layer, not a replacement traffic-data feed.

## Key project locations

| Path | Contents |
| --- | --- |
| [Java application](app/src/main/java/com/kirilbezmen/trafficscotland/) | Activity, Fragments, ViewModel, Repository, parser and models |
| [XML layouts and assets](app/src/main/res/) | Portrait and landscape UI, cards, icons and styles |
| [Android instrumentation tests](app/src/androidTest/) | XmlPullParser, full XML fixtures and navigation tests |
| [JVM tests](app/src/test/) | Distance calculations and road matching |
| [QA execution matrix](docs/QA_EXECUTION_MATRIX.md) | Observed test results versus outstanding scenarios |
| [Student contribution and evidence](docs/STUDENT_WORK_AND_EVIDENCE.md) | Development decisions and emulator observations |
| [Architecture notes](docs/ARCHITECTURE.md) | MVVM and network/data flow |
| [Documentation index](docs/INDEX.md) | Additional project and assessment documentation |
| [AI assistance record](docs/GENAI_USAGE_LOG.md) | Disclosure of AI-assisted implementation and prompts |

## Credential security and distribution

`local.properties`, signing keys, `build/` outputs and other local developer files are ignored by Git. The Android Gradle configuration reads DATEX Client ID and Client Key from the local file, which allows authorised data requests **but embeds those values into an authenticated APK**. APK-embedded credentials can be extracted, even if they are obfuscated. Therefore **do not commit credentials or publish a locally authenticated APK on GitHub**.

The rubric assessment includes testing, including LIVE data and offline behaviour, which are documented in the submitted Word report and narrated video. APK distribution for the External Examiner is a separate GCULearn hand-in. [Full configuration and security guidance](docs/ANDROID_CREDENTIALS_SETUP.md).

## Academic integrity and attribution

The student led the concept, requirements, app design choices, architecture planning, local API integration, debugging observations and Android Studio testing. ChatGPT provided substantial support implementing Java/XML features, XML processing, UI changes, test code, debugging, GitHub workflows and report drafting. This contribution is described in the [student evidence notes](docs/STUDENT_WORK_AND_EVIDENCE.md) and [GenAI usage log](docs/GENAI_USAGE_LOG.md).

**Assessment deadline:** Monday, **23 November 2026 at 17:00 GMT**. Source, narrated video and examiner ZIP are handed in separately according to GCULearn.

**Attribution:** Official traffic data: Traffic Scotland / Transport Scotland. Map tiles: Google Maps SDK. The original UI vector artwork is coursework design, not official Traffic Scotland branding.
