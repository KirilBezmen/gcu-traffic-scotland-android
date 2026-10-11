# Development log

## 10 October 2026 — Repository foundation
**Goal:** version-controlled coursework structure and honest status.

**Completed:** Created private GitHub repository (by student), confirmed assistant write access, prepared Android Gradle + Java/XML startup structure, docs, evidence folders, templates and tracking files.

**Not completed:** Full marking rubric review, app build/emulator run, Gradle wrapper generation, Traffic Scotland networking/parsing, actual Android functionality.

**Testing performed:** Reviewed package names and XML layout structure before GitHub upload. No Android compilation or emulator run yet.

**Next actions:** obtain rubric/DOs/DON'Ts/starter code; add wrapper; build and test baseline; implement required feed parser using coursework-approved methods.

**AI disclosure:** see GENAI_USAGE_LOG.md. All implementation/testing claims must reference an actual run, not an assumption.

## Later entries template
Date / commit:
Goal:
Changes:
Testing evidence (pass/fail/not run):
Problems:
Next steps:
GenAI assistance logged:

## 10 October 2026 — Official rubric and starter/code samples audit
**Inputs:** Detailed CW1 assignment, 4-page rubric, lecturer starter ZIP, 2 DATEX II XML fixtures and XML structure note.

**Analysis completed:** Mapped 100-mark rubric to implementation plan, discovered mandatory one-Activity+Fragments+MVVM for full architecture points and hard feed/PullParser restrictions; inspected starter and counted 4 incidents / 405 roadworks. Prepared documented pitfalls and test targets.

**Changes:** Documentation only, not the app's runtime logic. Existing GitHub scaffold has not yet been built/tested.

**Next:** Register official Traffic Scotland restricted API with GCU email; test Android Studio build; then implement and test PullParser before UI features.

**GenAI disclosure:** see GENAI_USAGE_LOG.md entry 003.

## 11 October 2026 — First architecture-level code commit
**Goal:** Stabilise `MainActivity` to avoid rewriting network/UI code in it during feature development.

**Code commit:** `07734399ad8c24715f29066f548d4c57944a150f`. Added 5 Java files, main layout variants, 2 fragment layouts, strings, dependencies and Internet manifest permission. UI is a navigable, **unverified scaffold**; map/list/detail content are explicit placeholders.

**Local status:** Student demonstrated a successful Android Studio Hello World emulator run in a **separate local generated project**, not this GitHub snapshot. These must be reconciled before reporting a successful run.

**Next action:** Clone the GitHub repo and supply a compatible wrapper from local generated project, sync/build/test; then implement full XML parsing and repository functionality in focused classes.

**AI assistance:** GENAI_USAGE_LOG.md Entry 004.

## 11 October 2026 — DATEX II XmlPullParser implementation (API approval pending)
**Prerequisite:** Student requested Traffic Scotland Public Restricted API access; portal shows under administrator review, request submitted 11 Oct 2026 at 15:45.

**Implementation:** New `TrafficXmlParser`, `TrafficSituation`, `TrafficRecord`, `TrafficLocation` under data/parser and data/model. Uses `XmlPullParserFactory`, downloaded XML String -> `StringReader`, hierarchical event parsing for generalPublicComment, validity, delays/lanes, road name descriptors, locations (from/to), direction and coordinates, handles missing optional tags and unknown subtrees.

**Local preliminary validation:** Java source compiled with JDK using a throwaway lightweight xmlpull interface adapter backed by StAX for OFFLINE TEST ONLY; ran the same Java parsing logic on both supplied full XML samples and extracted 4 unplanned and 405 roadworks situations with expected sample details. This compatibility test is NOT a native Android PullParser/emulator pass and the adapter is NOT included in submitted code.

**GitHub code commit:** `d723f2ef836952d4f09997845387a6bf36e18462`. Android instrumentation test source added separately.

**Not implemented / verified:** Real authenticated network retrieval, cache, actual device/parser tests, view binding, UI data lists, map or filters. No real API credentials were accessed or committed.

**Next:** Student uses Git Pull in Android Studio; run `TrafficXmlParserTest` instrumentation test. Then arrange official feeds in background repository/VM, connect parsed list, and test live source after approval.

## 11 October 2026 — First successful on-emulator parser instrumentation run
**Evidence source:** Student screenshot of Android Studio Run / Test Results, provided in project chat. The screenshot shows `4 passed`, `4 tests`, `BUILD SUCCESSFUL in 18s`, `Pixel_9_Pro(AVD) - 16` / API 36 and zero failed tests.

**Executed tests:** `incidentCommentImpactAndTwoPointsAreExtracted`, `unknownFieldsAndMissingOptionalElementsDoNotCrash`, `roadworksDurationAndDelayCanBeParsed`, `emptyDownloadIsNotSilentlyAccepted`.

**Result:** PASS — all 4 instrumented synthetic/minimal XML cases, no failures on emulator. Validates these cases on Android's actual PullParser implementation. **Does not yet verify full fixture parsing (4 incident/405 roadworks), live network updates, UI data accuracy, filters, maps or offline behaviour.**

**Next:** Add full supplied XML sample-based instrumented tests (count + spot checks), then integrate parsed collections into repository/ViewModel and real selectable lists. API application approval still pending.

## 11 October 2026 — Repository progress/dashboard housekeeping
**Purpose:** Avoid confusion between scaffolding, verified tests and remaining assessment work.
**Changes:** README now records verified Pixel 9 Pro app run and 4/4 actual instrumented parser tests, separated from untested full XML fixtures and pending API access. Proposed UI direction and seven milestone links added. Issue #1 being reopened because official API approval and clean wrapper setup are still outstanding.
**Testing:** No application code or new tests executed in this documentation-only change.
**GenAI assistance:** Entry 006.

## 11 October 2026 — GitHub checkpoint close-out
**User request:** Asked whether Projects, Pull Requests and Issues need maintenance, and requested that already completed work be closed.

**Actions:** Created GitHub issues [#8](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/8), [#9](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/9), and [#10](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/10) for small independently verified outcomes and marked them `closed/completed`. All seven full-coursework milestone issues remain open (pending API access, complete fixtures, real UI, mapping, offline, documented full test/video). Updated README with closed checklist and open tasks.

**Testing:** No new code or Android tests executed in this housekeeping change.

**GenAI assistance:** See log Entry 007.

## 11 October 2026 — Native functional prototype Sprint 1
**Action requested:** User delegated commencement of comprehensive coursework implementation, GitHub commits and continuous documentation.

**Development:** Added exact lecturer XML fixtures to private repo (4 unplanned, 405 roadworks); implemented background `TrafficRepository` with official feed restriction, API key values from ignored local properties, debug-only labelled fixture fallback, scheduled hourly refresh and last-successful LIVE response cache; upgraded `TrafficViewModel` to AndroidViewModel. Added full-fixture Android instrumentation tests; created list adapter, functional list and detail Fragment source, HomeFragment, four-tab navigation, original vector road/cones/warning hero, separate landscape rail, cards and branded colour scheme. **These new features are not yet tested in emulator.**

**Commits:** `f0e3a91` (fixtures), `afc40d7` (repository + tests), `bc3b9fe` (UI logic), `3bc3ee4` (native layouts/illustration), `1dac057` (CI/design specs). CI setup initially failed before Java compile because modern hosted runner rejected obsolete Android SDK tools; workflow is being repaired.

**Academic scope:** Original official feeds ONLY; no WebView; Java/XML PullParser; sample mode clearly labelled. **Pending:** validate samples 4/405 on Android, test UI, official live API access, map and nearby filter, complete icon set, full test evidence.

**Development note:** Original traffic sample XML may be copyrighted/licensed. It resides in a private coursework repository for educational testing. Review Traffic Scotland data terms before any public distribution.

## 11 October 2026 — Nearby filter, Google Maps, and icon expansions
**Source work:** Added Haversine and point-to-line radius filter from fixed Glasgow centre; editable 5–200 km nearby search; JUnit unit tests, including a segment crossing the reference point. Added **Google Maps SDK 20.0.0**, BuildConfig/Manifest local `MAPS_API_KEY`, multi-result selection from list search, selected map markers and linear roadwork polylines; map displays key-required explanation if not configured. Authored five additional original SVG-style Android vector icons (total seven) and documented their authoring in `ICON_SOURCES.md`.

**Verification:** GitHub Actions successfully compiled base UI, repository, radius feature and Maps-integrated project; JVM tests passed in green Actions builds. **No live Google Maps key, tile rendering or marker click has been observed on the student emulator yet.** User still needs to run full supplied XML instrumentation tests and UI tests manually. API approval pending.

**CI artifact:** credential-free debug APK available through green GitHub Actions workflow run (expires after 7 days).
