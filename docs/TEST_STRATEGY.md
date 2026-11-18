# Written test strategy — working document

**Current source of truth:** [QA Execution Matrix](QA_EXECUTION_MATRIX.md) and [CW1 Final Sprint](CW1_FINAL_SPRINT_2026-11-17.md). The older early-October planning entries below are retained as a historical baseline and should NOT be copied into the November submission as current "not implemented" statuses. Actual observed results require date, Android API, device and commit SHA.

## Objectives
Demonstrate correctness of Traffic Scotland data handling, meaningful UI, resilience to invalid data and usability across mobile conditions. Full required test types depend on official rubric.

## Test levels
- **Unit:** XML parsing, item conversion, missing/optional fields, business rules when present.
- **Integration:** parsed data reaches collection and Android list; refresh behaviour; error paths.
- **System/UI:** launch, navigation, selection, details, loading/empty/error states, portrait/landscape and accessibility.
- **Regression:** rerun representative tests after each change.

## Early-October plan (historical baseline — superseded by QA Execution Matrix)
| Test ID | Scenario | Expected result | Actual result | Status |
| --- | --- | --- | --- | --- |
| ENV-01 | Android clean clone, Gradle sync and install | Successful build/run | Not attempted | Not run |
| UI-01 | Tap Check setup on starter screen | Text changes, Toast appears | Not attempted | Not run |
| XML-01 | Valid incident XML | Correct number and fields | Not implemented | Not run |
| XML-02 | Valid roadworks XML | Correct number and fields | Not implemented | Not run |
| XML-03 | Invalid / truncated XML | Clear error, no crash | Not implemented | Not run |
| XML-04 | Empty items list | Useful empty UI | Not implemented | Not run |
| UI-02 | Choose an item | Matching detail view | Not implemented | Not run |
| UI-03 | Screen rotation | Usable layout/state | Not implemented | Not run |
| NET-01 | Network unavailable | No UI hang, useful error | Not implemented | Not run |

## Evidence policy
Record test date, device/emulator, Android/API level, commit SHA, steps, expected/actual result, PASS/FAIL, screenshots or Logcat paths. Planned tests are not evidence of a successful test.

## Submission
Summarise execution and any open defects in the Word test strategy. Never claim an unexecuted test has passed.

## Parser regression instrumentation tests (added 11 Oct 2026)
`app/src/androidTest/java/com/kirilbezmen/trafficscotland/data/parser/TrafficXmlParserTest.java` includes four tests: sample-like incident with two endpoints and missing end date; roadworks duration/delay; empty server response; unknown tags and optional fields. **Historical note superseded:** all four representative Android tests PASSED on Pixel 9 Pro, 11 October 2026.

Offline analysis of the supplied original examples found **4 unplanned + 405 roadworks** records. To count these as coursework testing evidence, run a device/instrumented test of the real full files and document observed values/screenshots. Independent local JDK compatibility checks are preliminary, not a substitute.

Record actual Android run details (commit, emulator, API, date, outcome) only after executing tests.

## Executed Android instrumented parser tests — 11 October 2026

| Test | Expected | Observed from Android Studio screenshot | Result |
| --- | --- | --- | --- |
| `incidentCommentImpactAndTwoPointsAreExtracted` | Extract summary, dates, impact, 2 points and road ID | Test green | **PASS** |
| `roadworksDurationAndDelayCanBeParsed` | Extract subtype, end date, delay, and road ID | Test green | **PASS** |
| `emptyDownloadIsNotSilentlyAccepted` | Empty XML raises expected parse exception | Test green | **PASS** |
| `unknownFieldsAndMissingOptionalElementsDoNotCrash` | Unknown nested XML and absent optional fields handled safely | Test green | **PASS** |

**Environment:** Android Studio / Pixel 9 Pro Android emulator, API 36, 11 Oct 2026. **Test summary:** 4 passed, 0 failed; BUILD SUCCESSFUL in 18s. Screenshot is present in ChatGPT conversation, but **not yet archived into GitHub evidence folder**.

**Scope limitation:** These tests use small representative constructed XML fragments and **not** the complete official supplied XML fixtures. Full-file parsing count, actual live XML feed, map/UI presentation and offline tests remain outstanding. Do not conflate these four tests with full coursework verification.

## Full lecturer samples passed / pending UI field checks — 11 October 2026
| Scenario | Expected | Observed | Status |
| --- | --- | --- | --- |
| Full unplanned XML (lecturer fixture) | **4** situations, nested fields | Pixel 9 Pro AVD; complete fixture instrumentation test passed 11 Oct | **PASS** |
| Full current roadworks XML | **405** situations; nested fields/coordinate coverage | Pixel 9 Pro AVD; complete fixture instrumentation test passed 11 Oct | **PASS** |
| New Home, lists and detail screen | Clearly labelled sample source, valid counts, select a row and fields equal XML | Not yet run | NOT RUN |
| Search `M8` versus `M80` | Exact road route match, not substring | Not yet run in new UI | NOT RUN |
| Orientation | Portrait tabs vs landscape rail, state + loaded data stay | Not yet run | NOT RUN |
| Live API fetch and offline cached mode | Authenticated official XML; cache on no internet | API approval pending | BLOCKED |
| Map + nearby distance | Geolocated selected markers and radius filter | Not implemented | TODO |

All other unexecuted tests remain NOT RUN; complete XML lecturer fixtures now have observed Android evidence. Add Android Studio Screenshots to `docs/evidence/screenshots/` and record date/device/API/commit and actual results.

## New acceptance tests — Google Maps and radius
| Scenario | Expected | Actual | Status |
| --- | --- | --- | --- |
| Nearby reference point | 5–200 km radius centred on fixed Glasgow coordinates, selection retained when rotating | JVM distance tests in green CI; UI not yet run | PARTIAL |
| Long line near Glasgow | Event appears when road segment crosses radius even if endpoints are far | JUnit source included; CI unit tests pass | PASS (algorithm only) |
| Road search > map | Search M8, click **Show filtered results on map**, multiple matching pins, tap details | No Maps key available to this session | BLOCKED |
| Map with no key | Useful setup message, lists remain usable | Not yet run on device | NOT RUN |
| Seven vector icons | Icon semantics match subtype and label, attribution document | Sources committed but screenshot proof missing | NOT RUN |


## Executed full sample tests — 11 October 2026
Two full lecturer sample Android tests `OfficialXmlFixturesTest` **PASSED 2/2** on Pixel 9 Pro emulator API36. First test asserted four incident situations and incident record/summary/start/coordinates; second asserted 405 roadworks, required validity/summary and >390 entries with coordinates. `BUILD SUCCESSFUL in 8s`. Screenshot submitted by student in conversation, pending archival to GitHub. [Full test record](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md). No LIVE API functionality is inferred.


### Source-to-screen specimen audits (11 October 2026)
- **A74(M) Incident, I_20260810_248:** exact screenshot compare including all displayed fields and *both* From/To coordinates: **PASS**. [Evidence record](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md).
- **M8 Roadworks, D_159301:** road closure summary, MaintenanceWorks, roadworks, validity dates, `longDelays`, westBound and both From/To coordinates match: **PASS**. [Evidence record](evidence/test-logs/ROADWORKS_M8_DETAILS_2026-10-11.md).
- These are two specific historical samples, not an exhaustive 409-record UI audit or live API testing. Pending UI smoke suite, orientation, errors and colouring verification.
