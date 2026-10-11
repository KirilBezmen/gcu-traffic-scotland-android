# Traffic Scotland Explorer

**GCU · Mobile Platform Development (MHI326841) · CW1 2026/27**

![Java](https://img.shields.io/badge/Java-Android-007396)
![Platform](https://img.shields.io/badge/Platform-Android%208%2B-3DDC84)
![Assessment](https://img.shields.io/badge/CW1-70%25%20of%20module-485569)
![Phase](https://img.shields.io/badge/Phase-native%20UI%20integration-E5A449)
![Tests](https://img.shields.io/badge/Android%20parser%20tests-4%20passed-238636)

> **Independent student coursework; not an official Traffic Scotland app.** The GitHub code is an evolving implementation, **not a completed or fully tested submission**. The approved DATEX II feeds and the coursework's DOs/DON'Ts govern every feature.

## One clean CW1 workflow

We have **seven active Issues** ([M0–M6](docs/PROJECT_BOARD.md)), one per coursework milestone. [#8–#10](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues?q=is%3Aissue%20is%3Aclosed) are three verified narrow completed checkpoints; **#11–#13 are archived duplicates, not completed work**, merged back into M0/M1. As of 11 Oct: **7 Open / 6 Closed (3 completed + 3 duplicate)**.

- [**Simple board setup and seven task statuses**](docs/PROJECT_BOARD.md) — the visual GitHub Project **has not been created**; one manual click-through in Projects is still needed.
- [**Full application/product logic**](docs/PRODUCT_REQUIREMENTS.md) — data, UI, state, maps, background refresh and offline.
- [**All 100 rubric marks, possible vs verified**](docs/RUBRIC_100_CHECKLIST.md) — evidence and clarification requirements.
- [**26 test scenarios: actual vs expected**](docs/QA_EXECUTION_MATRIX.md) — small XML 4/4 previously observed, new UI/full XML unverified.

## Development dashboard

**Last verified:** 11 October 2026 · **Target:** high First Class (85%+ for CW1, not guaranteed) · **Official CW1 date:** 23 November 2026, 17:00 (earlier generic brief says noon; aim to submit by noon).

| Workstream | Evidence / what is complete | Next action | Status |
| :-- | :-- | :-- | :-- |
| Android setup | GitHub app launched on **Pixel 9 Pro emulator**; Java and XML navigation shell | Verify fresh clone can build with committed Gradle wrapper | ✅ Local run / ⚠️ wrapper |
| Official API access | Applied using GCU email on **11 Oct**, Public Restricted API | Wait for administrator approval; never commit client credentials | 🟡 Pending |
| XmlPullParser + models | Parser source; **4/4** representative tests passed; **4 + 405 original sample XML files now included** | Run newly added full-file instrumentation tests on Android | 🟡 New tests not yet run |
| MVVM and Fragments | Shared AndroidViewModel, background repository and list/detail/home code committed | Build and verify transitions, rotation and selection | 🟡 Code committed, new UI unverified |
| Portrait / landscape | Separate `activity_main.xml` arrangements committed | Test rotation and persistence of selected data, filters and scroll state | 🟡 Not verified |
| Incident / roadworks lists + details | Native ListView card adapter and detail fields implemented in source | Pull and run on emulator; compare rows to XML values | 🟡 Not yet emulator-tested |
| Road / nearby search | Exact road-ID and 5–200 km radius filter from fixed Glasgow coordinate source compiled | Test sample location data, filtering, rotation | 🟡 Device testing pending |
| Delays, licensed icons, map | Delay colours + seven original icons; Google Maps multi-result/segment code compiled | Configure Maps API key locally; validate marker tapping, legends and adequate icons | 🟡 Source only, map key pending |
| Auto-update + offline state | Java Executor background worker, startup/hourly refresh, live-cache fallback **source committed** | Test after API approval (no live responses yet) | 🟡 Unverified live |
| Assessment test evidence | Four instrumented parser tests passed (synthetic examples) | Full-fixture, UI, navigation, offline and feed-vs-display tests | 🟡 In progress |
| Final submission | No APK/video/Word/backup ZIP submitted | Prepare final artefacts after working functionality | ⚪ To do |

**Legend:** ✅ verified · 🟡 partially done / pending · ⚪ not started. This table tracks *implementation evidence*, **not marks already awarded**.

### Where we are in the workflow

```text
[✔] Android Studio + emulator launched
[✔] GitHub / Java+XML / single Activity and Fragment placeholders
[✔] Representative XmlPullParser tests (4 pass)
[~] Approval for official Traffic Scotland API
[>] NEXT: Git Pull → compile / run new Home + lists → full sample XML tests
[~] Search by road + details source written; distance remains
[~] Seven original icons / delay badges / Maps code; Maps key and tests remain
[ ] Distinct landscape state / usability validation
[~] Background/refresh/cache code written; real conditions not verified
[ ] Full testing evidence, demo video, APK and Word summary
```

### Completed, verified checkpoints

Three narrowly scoped tasks are **closed as completed**, without prematurely closing the broader assessment milestones:

| Closed issue | Verified result |
| :-- | :-- |
| [#8 — Repository foundation](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/8) | Dedicated private repository, coursework documentation and issue tracker created |
| [#9 — Emulator launch](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/9) | Android GitHub project ran on Pixel 9 Pro; navigation placeholders displayed |
| [#10 — Parser smoke tests](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/10) | **4/4 Android instrumentation tests passed** on representative XML, API 36 |

**Open:** #1 API approval/clean wrapper, #2 full XML samples, #3 real lists, #4 rotation/MVVM validation, #5 search/maps/icons, #6 background/offline, #7 final testing/deliverables.

## How we manage tasks in GitHub

| Issue | Milestone | Status |
| :-- | :-- | :-- |
| [#1](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/1) | Android Studio setup and authorised API access | In progress (approval pending) |
| [#2](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/2) | Complete XML parsing and real fixture tests | In progress |
| [#3](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/3) | Selectable incidents / roadworks and detail UI | Source committed; emulator verification pending |
| [#4](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/4) | Portrait, landscape and MVVM saved state | Partly implemented |
| [#5](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/5) | Search, nearby, map, icons and delay colours | Source written; device/Maps key verification pending |
| [#6](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/6) | Background loading, auto refresh, offline handling | Implementation drafted; live verification pending |
| [#7](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/7) | Testing, video, APK, Word and backup ZIP | Test evidence started |

**Rules:** Each issue has a definition of done; close it only when the implementation AND its tests are verified. Each change should have a meaningful Git commit. No guessed completion percentages or unexecuted PASS results.

## Proposed UI direction (to be implemented)

- **Visual language:** clear, modern traffic-information UI: deep navy (#142B49), blue (#2675C9), neutral off-white (#F3F6FA) and semantic amber/red for warnings.
- **Navigation:** Incidents, Roadworks, Map, with search and detail screens in a **single Activity + Fragments + MVVM** architecture, as required for maximum architecture marks.
- **Lists:** road/type at a glance, a concise `generalPublicComment`, severity/delay indication and useful empty/loading/error states.
- **Details:** validity dates, impact, lane restrictions and from/to locations with safe handling of missing values.
- **Landscape:** materially different arrangement, not portrait stretched; preserve current selection and filters on rotation.
- **Accessibility:** readable sizes, tap targets, contrast, text labels paired with colours, licensed and attributed icons.
- **Implementation constraint:** Java + XML Android Views; no HTML WebView or unrelated third-party UI frameworks. Maps via an explicitly allowed service.

This is an **aesthetic proposal**, not a claim of implemented screens. A polished UI matters only where it supports the marking rubric, performance and usability.

## Coursework architecture

```text
Official Traffic Scotland DATEX II UnplannedEvents + CurrentRoadworks XML
    ↓ (approved credentials; startup + periodic background fetch)
XML text String → XmlPullParser (mandatory) → Java TrafficSituation/Record/Location objects
    ↓
TrafficRepository (data + most recent good feed / failures)
    ↓
TrafficViewModel (observable data, current search, selection)
    ↓
MainActivity + ListFragment / DetailsFragment / MapFragment
    ↓
Portrait and landscape XML layouts
```

Current source: `app/src/main/java/com/kirilbezmen/trafficscotland/`; documentation is in `docs/`. The project was successfully launched in Android Studio on Pixel 9 Pro; the **repository does not yet include the full standard Gradle wrapper JAR and scripts**, so a completely fresh standalone wrapper build has **not** been verified.

## Assessment and documentation

| Rubric area | Max marks |
| --- | ---: |
| Requested app functionality | **42** |
| Portrait / landscape layouts | **8** |
| XmlPullParser and data structures | **10** |
| Architecture | **10** |
| Threading and automatic updating | **10** |
| Documented testing | **15** |
| Narrated video | **5** |
| **Total** | **100** |

The rubric contains an **icons line inconsistency (6 versus 8)** — clarification pending. Full descriptions and DOs/DON'Ts: [Assessment tracker](docs/ASSESSMENT_TRACKER.md) and [Coursework constraints](docs/COURSEWORK_CONSTRAINTS.md).

**Source of truth for work:** GitHub commits and Issues. **Working documentation:**
- [Development log](docs/DEVELOPMENT_LOG.md): meaningful changes and actual verification.
- [Testing strategy](docs/TEST_STRATEGY.md): expected vs actual, evidence, device/API, status.
- [GenAI usage log](docs/GENAI_USAGE_LOG.md): disclose actual AI assistance and prompts (G — Guided).
- [Architecture](docs/ARCHITECTURE.md): implementation decisions.
- [Submission checklist](docs/SUBMISSION_CHECKLIST.md): final delivery and naming.

Other audit/reference documents in `docs/` are optional working notes; **they are not separate GCU hand-ins**.

### Final submission (separate from internal Markdown docs)

The official coursework specification requires a **Word document** `Surname_GivenName_StudentId.docx` with originality declaration, complete GenAI disclosure and prompt appendix, link to OneDrive narrated video, links to GitHub source and APK, and a written **documented test strategy**. Also prepare narrated portrait/landscape video and an External Examiner backup **ZIP** (APK + video + ZIP of cleaned Android project). See [Submission checklist](docs/SUBMISSION_CHECKLIST.md). Don't manufacture test results.

**Internal targets:** core functionality by early November, testing/polish by mid-November, export/record/review final deliverables around 18–20 November. Honours Project remains the highest time priority.

## Active development — 11 October 2026

- [Native UI design and functional spec](docs/DESIGN_AND_FUNCTIONAL_SPEC.md): illustrated road/cones/warning sign home, lists, full details and selected states.
- [Official sample XMLs](app/src/main/assets/): **4 unplanned events and 405 roadworks**, bundled for local **debug** testing only. UI explicitly says **historic samples, NOT live conditions**.
- [Traffic Scotland credential setup](docs/ANDROID_CREDENTIALS_SETUP.md): uses ignored local properties after portal approval; do not publish credentials-bearing APK.
- [GitHub Actions](.github/workflows/android-build.yml): debug + instrumentation test compilation only, not device execution. CI verification pending; user should check [Actions](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions).
- [Full XML Android tests](app/src/androidTest/java/com/kirilbezmen/trafficscotland/data/parser/OfficialXmlFixturesTest.java): **written but NOT yet executed on Android**.

**Next verification:** Git Pull → Gradle Sync → run `app` on Pixel 9 Pro (Home, lists, type/description, search M8/M80, details, landscape), then right-click `OfficialXmlFixturesTest` → Run and save the actual results. **None of these new UI/tests has been confirmed by an Android run yet.**

### Google Maps prerequisite
The [Google Maps SDK](https://developers.google.com/maps/documentation/android-sdk/config) is the *one explicitly permitted mapping SDK* used. Set a restricted `MAPS_API_KEY` **locally** before trying to display real map tiles; see [credential setup](docs/ANDROID_CREDENTIALS_SETUP.md). Without it the Map tab explains the missing setup, **not a crash**. Never paste maps keys into a public issue or ChatGPT.

### CI verification
- GitHub Actions debug app + Android test APK compilation and JVM unit tests have passed on recent 11 Oct commits; view the [current CI history](https://github.com/KirilBezmen/gcu-traffic-scotland-android/actions/workflows/android-build.yml).
- A **credential-free debug APK** is uploaded as an Actions artifact for 7 days on green builds. It contains labelled lecturer examples, not verified live traffic.
- Android instrumentation tests for 4/405 full lecturer XML **must still be run on a Pixel 9 Pro emulator** and documented. CI only compiled the Android test APK, it did not execute emulator tests.
