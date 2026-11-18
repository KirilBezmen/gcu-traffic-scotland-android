# Traffic Scotland Explorer

**Glasgow Caledonian University · BSc (Hons) Software Development · Mobile Platform Development CW1 · 2026/27**

[![Android CI](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions/workflows/android-build.yml/badge.svg)](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions/workflows/android-build.yml)
![Java](https://img.shields.io/badge/Language-Java-2563eb)
![Android](https://img.shields.io/badge/Android-API%2026%2B-0e7490)
![Status](https://img.shields.io/badge/Status-submission%20QA-f59e0b)

> **Educational prototype — not affiliated with Traffic Scotland.** The app can show real official Traffic Scotland DATEX II feeds **only with approved credentials**. Historical lecturer XML fixtures are clearly marked **SAMPLE**, never LIVE. This private coursework repository is **not yet a signed-off final submission**.

## At a glance

Traffic Scotland Explorer is a native **Java + Android XML Views** app for exploring road incidents and current roadworks across Scotland. It supports source-grounded search, nearby filtering, details and Google Maps without inventing traffic conditions.

| Capability | Implementation / validation |
| --- | --- |
| Incidents and current roadworks | Both authorised **DATEX II** Content.xml streams; LIVE student emulator smoke test **18 Nov 2026**: **7 incidents / 421 roadworks** |
| Manual live refresh | Student-observed **421 → 423 roadworks**, 18 Nov |
| XML parsing | Downloaded XML **String → XmlPullParser → typed Java collections**; 6 Android parser tests observed passed on 11 Oct |
| Search and nearby | Road ID search **M8 vs M80** and **5–200 km** from a labelled fixed Glasgow reference; student-tested on fixture data |
| Details | Type, general-public comment, impacts, date validity, direction, roads and coordinates where available |
| Map | Google Maps SDK for Android, marker previews, selected result subsets and Details navigation; student-tested |
| Portrait / landscape | Distinct bottom-navigation and side-rail layouts; selected record and Nearby state tested |
| Background and offline | `ExecutorService`, `LiveData`, startup/hourly in-app refresh, local last-good live XML; offline-cache **student-reported PASS**, screenshot outstanding |

**Validation matters:** CI builds Java/XML, a debug APK and Android test APK and executes JVM tests. **CI does not run the emulator instrumentation tests.** Details, dates, evidence limitations and remaining checks: [QA execution matrix](docs/QA_EXECUTION_MATRIX.md).

## Explore the repository

| Where | What you'll find |
| --- | --- |
| [`app/src/main/java/`](app/src/main/java/com/kirilbezmen/trafficscotland/) | Single `MainActivity`, Fragments, shared `TrafficViewModel`, repository, DATEX II XML parser and models |
| [`app/src/main/res/`](app/src/main/res/) | Native layouts, original vector artwork and traffic icons, separate landscape layouts |
| [`app/src/main/assets/`](app/src/main/assets/) | **Historic lecturer samples**, 4 incidents and 405 roadworks, for clearly labelled development/testing |
| [`app/src/test/`](app/src/test/) | JVM distance and exact-road search regression tests |
| [`app/src/androidTest/`](app/src/androidTest/) | Android XML parser, full-fixture and UI navigation tests (on-device execution recorded separately) |
| [Docs index](docs/INDEX.md) | Architecture, setup, UX, rubric, QA evidence, AI assistance and final submission |
| [Issues](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues) | Seven coursework milestones M0–M6, including unresolved acceptance criteria |
| [PR #14](https://github.com/KirilBezmen/gcu-traffic-scotland-android/pull/14) | Native UX/live DATEX integration history and review |

## Architecture

```text
Traffic Scotland DATEX II (UnplannedEvents + CurrentRoadworks / Content.xml)
      ↓  HTTPS Basic auth • worker thread • hourly while app is running
TrafficRepository → XML String → TrafficXmlParser (XmlPullParser)
      ↓                   ↓
Live/cached snapshot    TrafficSituation → TrafficRecord → TrafficLocation
      ↓
TrafficViewModel (shared search, radius, section and selected IDs)
      ↓
Single MainActivity + Home / Lists / Details / Map Fragments
      ↓
Portrait XML layouts or distinct landscape rail
```

Google Maps uses a **separate, Android-restricted Maps SDK API key** and plots coordinates found in the official/XML sample records. It is a display layer, not a traffic-data source.

## Run locally

1. Open the repository in **Android Studio**, with Android SDK **35**, JDK **17** and an Android **API 26+** emulator/device (device used for assessment checks: **Pixel 9 Pro, API 36**).
2. Open root `local.properties` (ignored by Git), keeping the generated `sdk.dir=...` line. For authorised LIVE testing, add:
   ```properties
   TRAFFIC_CLIENT_ID=YOUR_APPROVED_CLIENT_ID
   TRAFFIC_CLIENT_KEY=YOUR_APPROVED_CLIENT_KEY
   MAPS_API_KEY=YOUR_ANDROID_RESTRICTED_GOOGLE_MAPS_KEY
   ```
3. Run Gradle Sync, select `app`, then **Run** on the emulator. With no Traffic Scotland credentials, the **debug** app deliberately uses historical lecturer XML with a prominent SAMPLE label; release does not silently substitute samples. With valid credentials, expect **Official Traffic Scotland data**.
4. See [credential and safe-release instructions](docs/ANDROID_CREDENTIALS_SETUP.md) before publishing or sharing APKs.

**Reproducible build tools:** a trusted **Gradle 8.9 Wrapper** (`gradlew`, `gradlew.bat`, wrapper JAR and pinned distribution checksum) is now committed. [Source and checksums](docs/GRADLE_WRAPPER_PROVENANCE.md). CI validates builds with the wrapper; a separate **fresh Windows checkout** still needs a student-run test before being called PASS.

## Build and test

- **Android Studio:** run `TrafficXmlParserTest`, `OfficialXmlFixturesTest` and `NavigationChromeTest` on a configured emulator; record the actual run and screenshots.
- **Command line (Windows PowerShell):** `.\\gradlew.bat :app:assembleDebug :app:assembleDebugAndroidTest :app:testDebugUnitTest`. On macOS/Linux use `./gradlew`. CI runs these tasks with the committed wrapper.
- **Manual rubric tests:** M8/M80 search, radius 5/25/200 km, Roadworks delays, map selected-results, rotation, Back navigation, online refresh, offline cache and empty cache. See [QA Matrix](docs/QA_EXECUTION_MATRIX.md) and [test strategy](docs/TEST_STRATEGY.md).

**Never commit keys, copy credentials into screenshots, or upload the locally authenticated APK:** `BuildConfig` values can be extracted from compiled APKs. A credential-free build runs with labelled samples in debug mode but is **not a substitute for demonstrating authorised LIVE functionality in a handed-in APK**. This distribution issue is an open release blocker and must be resolved with the module team.

## Project ownership and academic transparency

This is a **student-directed, AI-assisted** application. The student selected the product concept and priorities, reviewed UI and XML outputs, obtained DATEX II access with lecturer assistance, configured credentials, ran emulator tests and assessed observed behaviour. AI assistance included producing Java/XML source, UX assets, regression tests, debugging guidance and documentation drafts. **Git history is not rewritten to invent authorship.**

- [Student-led decisions and independently observed tests](docs/STUDENT_WORK_AND_EVIDENCE.md)
- [Full AI usage log and prompts](docs/GENAI_USAGE_LOG.md)
- [Requirements and 100-mark rubric](docs/RUBRIC_100_CHECKLIST.md)

## Submission status

**CW1 due Monday 23 November 2026.** Source documents contain conflicting **12:00/17:00** times; use **12:00 as the safe target** unless confirmed otherwise. Deliverables still being prepared: Word report with originality/AI disclosure and actual test evidence, narrated MP4, secure functional APK/source link and external-examiner backup ZIP. This is **not a PowerPoint requirement**. [Final hand-in checklist](docs/SUBMISSION_CHECKLIST.md).

**Data and map attribution:** Traffic Scotland official DATEX II feeds; map tiles © Google (shown automatically by Maps SDK). UI vector art was generated for this coursework and is not an official Traffic Scotland brand identity.
