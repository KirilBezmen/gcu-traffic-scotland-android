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
