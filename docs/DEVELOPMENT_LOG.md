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

## 11 October 2026 — CW1 Project Board blueprint and full acceptance organisation
**User:** Requested creation of visual GitHub Project and complete project logic to maximise rubric outcomes.
**Connector limitation:** GitHub connection supports repository files/Issues but does not expose **GitHub Projects creation or item assignment**. No board created or claimed. Added a ready-to-create board spec for user's single manual setup.
**GitHub tasks:** Created new P0 child Issues #11 (clean Gradle build), #12 (official API approval/auth), #13 (full lecturer XML emulator test) and retained seven broad milestone Issues plus three earlier closed checkpoints.
**Documentation:** Added PROJECT_BOARD.md, PRODUCT_REQUIREMENTS.md, RUBRIC_100_CHECKLIST.md and QA_EXECUTION_MATRIX.md; README linked all. Clarified double-counting risk in summary subcriteria, documented the still-unresolved icon mark discrepancy and deadline conflict.
**Testing:** No new Android tests performed in this management-only commit. Previous synthetic 4/4 passes remain the only device parser result currently supplied.
**AI assistance:** Entry 010.

## 11 October 2026 — Issues housekeeping and board simplification
**User issue:** A screenshot of the empty Projects tab and 10 open Issues led to confusion between unresolved bugs and planning issues. None of the extra issue count was an observed Java compile error.
**Action:** Consolidated #11 and #12 into active M0/#1; #13 into active M1/#2, including their pending acceptance checks. Closed #11/#12/#13 as GitHub **duplicate**, explicitly not completed. Renamed and rewrote main #1–#7 to single concise milestone issues with proven status, remaining tests and evidence.
**Documentation:** Simplified PROJECT_BOARD.md and README to describe **7 Open / 6 Closed** (3 narrow verified + 3 administrative duplicate); no visual Project has been created because GitHub connector lacks a Projects creation action.
**Testing:** Management-only work. No new tests executed, no app build claimed.
**GenAI:** See Entry 011.

## 11 October 2026 — UI polish requested from Pixel 9 Pro screenshots
**Observation:** Student supplied screenshot of 4/4 original parser tests green, and displayed 4 Incidents in the historic XML sample mode on an Android emulator. Another screenshot revealed the duplicate 'Explore traffic' buttons on Home.
**Task:** Student explicitly asked to rewrite/fix the affected code, document everything in GitHub and let them update their local Android Studio project. Their requested changes: remove duplicate Home navigation, prohibit whole-page scrolling but retain the cards list scroll, replace text Refresh with circular-arrow icon.
**Code change:** Rewrote HomeFragment and Home portrait layout; added compact dedicated Home landscape XML. Rewrote TrafficListFragment to use fixed non-scrolling controls with ListView-only scrolling, dynamic Nearby radius, 48dp ImageButton with accessible label; added distinct list landscape layout and original vector ic_refresh.xml. Kept tested parser, models, repository, ViewModel and ListView adapter without a gratuitous migration. Added two NavigationChromeTest instrumentation UI smoke cases (not run).
**Documentation:** [UI refinement design and test criteria](UI_REFINEMENT_2026-10-11.md), README update and QA matrix entries. GenAI prompts in Entry 012.
**Verification at commit time:** Pre-refactor user screenshot shows 4/4 parser smoke test PASS and 4 incidents rendered; **new UI has not yet been exercised on emulator**. GitHub Actions status tracked separately; failure on early partial Home commit for a missing refresh string was corrected in a later commit. Do not claim the incomplete intermediate build passed.


## 11 October 2026 — Full official lecturer XML files tested on Android
Student screenshot of Android Studio test window demonstrates `OfficialXmlFixturesTest` ran with **2/2 passed**, both `originalIncidentsContainFourSituations` and `originalRoadworksContain405Situations` green, on Pixel 9 Pro AVD. `:app:connectedDebugAndroidTest` BUILD SUCCESSFUL in 8 seconds. Historical lecturer assets **4 incidents / 405 roadworks** correctly parsed according to test assertions. The screenshot is in conversation, not in GitHub; source SHA on student device not visible. This is **not** live API or field-to-screen verification. M1 remains open for that audit. More detail: [test log](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md).


## 11 October 2026 — M2 visual Details smoke test, actual XML comparison
**Student evidence:** Two Pixel 9 Pro emulator screenshots show the new Home screen (4 incidents / 405 roadworks, historical-source warning and branded road/cone/warning artwork) and the first A74(M) incident Details after navigation.
**Grounded verification:** Compared visible record id I_20260810_248, subtype roadClosed, type, generalPublicComment, validity status, start, last updated, southBound direction, operational lanes 0, to-location J19 Eaglesfield and coordinates 55.065432/-3.273135 against the original `sample_unplanned.xml`; all visible fields matched. An end time is absent in XML and appropriately not displayed.
**Still required:** Scroll down to the From J18 Ecclefechan endpoint, independently verify one complete roadworks Details screen, UI tests/rotation and real LIVE feeds. Evidence/screenshots only in ChatGPT conversation, not GitHub yet. [Comparison matrix](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md).


## 11 October 2026 — Second source-to-screen audit: A74(M) both endpoints + M8 roadworks D_159301
**Student screenshots:** Full bottom of A74(M) Details showing From J18 Ecclefechan and coordinates 55.103477/-3.349595; Roadworks Details for M8 D_159301 including both endpoints, validity Start/End, longDelays, summary and technical type. Compared with raw lecturer XML using GitHub file and Git blob contents.
**Observed A74:** From location / coords exactly match `sample_unplanned.xml`; the full displayed record field comparison is now PASS.
**Observed M8:** `D_159301`, MaintenanceWorks/roadworks, generalPublicComment, validity window 2023-07-22 → 2027-12-31, last updated 2026-06-25, westBound, longDelays, To (55.870380/-4.268265), From (55.869923/-4.261365) all correspond to `sample_roadworks.xml`.
**Evidence:** [A74 audit](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md) and [M8 audit](evidence/test-logs/ROADWORKS_M8_DETAILS_2026-10-11.md); original screenshots remain in student conversation, still need permanent upload into GitHub.
**Limits:** Does not establish 405-item source-to-UI field fidelity, short/long **colour** coding, rotation state, API access, live refresh or online map. M1 and M2 remain open pending more acceptance tests.
