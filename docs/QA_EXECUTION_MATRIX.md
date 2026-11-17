# CW1 QA execution matrix — expected vs actual

**Rules:** \`NOT RUN\` and \`BLOCKED\` are not PASS. After running each scenario, replace placeholders with date/time, emulator/Android version, commit SHA, actual observed result and evidence path. On 11 October student screenshots confirmed 4/4 small XML tests and 2/2 tests on complete lecturer XML files. Neither verifies live API or exact UI fields.

| ID | Scenario / Steps | Expected | Actual | State | Evidence |
| --- | --- | --- | --- | --- | --- |
| BUILD-01 | Clean Git clone, Gradle Sync / assembleDebug / install | Importable/reproducible app | Await wrapper + fresh clone | NOT RUN | — |
| XML-01 | Run four representative \`TrafficXmlParserTest\` | 4 passes | 4/4 green, Pixel 9 Pro API 36, 11 Oct | **PASS** | Screenshot in ChatGPT, not yet GitHub |
| XML-02 | Run `OfficialXmlFixturesTest`, sample_unplanned | 4 incidents, nested dates/locations | 4 incidents parsed; originalIncidentsContainFourSituations green on Pixel 9 Pro API36, 11 Oct | **PASS** | [Student test screenshot noted](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md) |
| XML-03 | Run `OfficialXmlFixturesTest`, sample_roadworks | 405 roadworks; valid records and location coverage | 405 roadworks parsed; originalRoadworksContain405Situations green on Pixel 9 Pro API36, 11 Oct | **PASS** | [Student test screenshot noted](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md) |
| XML-04 | Malformed XML, missing/unknown nested fields | Safe error / sensible optional defaults | — | NOT RUN | — |
| UI-01 | New Home on Pixel 9 Pro | Road/cones/warning illustration, counters, source state | 11 Oct 2026 screenshot: branded illustrated Home, **4 incidents · 405 roadworks**, historic sample warning, primary navigation visible | **PASS (visible requirements)** | Student screenshot in ChatGPT; [source-to-screen log](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md) |
| UI-02 | Incidents list, tap 1st item | Type + exact comment, correct details ID | A74(M) card opens record **I_20260810_248** with matching roadClosed subtype and full comment; 11 Oct screenshot | **PASS (first incident)** | Student screenshot in ChatGPT; [source-to-screen log](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md) |
| UI-03 | Roadworks list, tap long-delay item | Correct type/description, impact/validity shown | First roadworks M8 / D_159301 opens: MaintenanceWorks, roadworks subtype, correct dates and longDelays with both endpoints (11 Oct screenshot) | **PASS (first roadwork)** | [M8 XML-to-UI audit](evidence/test-logs/ROADWORKS_M8_DETAILS_2026-10-11.md) |
| UI-04 | Compare sample XML and UI rows/details | Exact values from XML for selected records; no fabricated fields | **A74(M) and M8 D_159301:** all visible Detail fields including **both from/to coordinates** match respective original XML (11 Oct screenshots) | **PASS (two selected records); broader feed coverage still pending** | [A74 audit](evidence/test-logs/INCIDENT_A74_DETAILS_2026-10-11.md), [M8 audit](evidence/test-logs/ROADWORKS_M8_DETAILS_2026-10-11.md) |
| UI-05 | Search "M8"; then "M80" | No false substring cross-match | — | NOT RUN | — |
| UI-06 | Nearby toggle at 5,25,200 km | Fixed Glasgow reference, distance-appropriate matches | — | NOT RUN | — |
| UI-07 | Roadwork delay colour + text legend | Short/long clearly distinguished, missing clearly handled | — | NOT RUN | — |
| UI-08 | Six+ icon data scenarios | Each correct type, original attribution, not generic repeats | — | NOT RUN | — |
| MAP-01 | Local restricted Maps API key + launch | Tiles/markers with authentic source coords | — | BLOCKED key | — |
| MAP-02 | Search road, show multiple selected points | >1 selected situation plotted when XML supplies coords | — | NOT RUN | — |
| MAP-03 | Tap marker info window | Correct situation details | — | NOT RUN | — |
| ROT-01 | Rotate incidents list with search | Landscape left rail, same list/query/scroll | — | NOT RUN | — |
| ROT-02 | Rotate while detail open | Correct detail maintained; Back navigation works | — | NOT RUN | — |
| NET-01 | Launch after LIVE API approval | Both authorised XML feeds fetched in background | — | BLOCKED approval | — |
| NET-02 | Wait/shorten demo interval for second fetch | Periodic refresh, no UI stall, updated timestamp | — | BLOCKED approval | — |
| NET-03 | Switch off internet after successful LIVE | Cached LIVE data shown with stale indicator | — | BLOCKED approval | — |
| NET-04 | Fresh install offline, no cache | Useful unavailable/error state, no crash or fake LIVE | — | NOT RUN | — |
| SEC-01 | Inspect repo/APK/config and key policies | No secret commits, safe release approach documented | — | NOT RUN | — |
| ACC-01 | Small screen, large text, touch targets | Readable labels, no clipping, colour+text semantics | — | NOT RUN | — |
| VIDEO-01 | Narrated full app portrait + landscape | Voiceover covers all features and source status | — | NOT RUN | — |
| HANDIN-01 | Word, video, code/APK links, examiner ZIP | Names/formats correct, links accessible, no secrets | — | NOT RUN | — |

## Evidence filing convention
Store non-sensitive evidence in \`docs/evidence/screenshots/<test-id>_<YYYYMMDD>_<short-commit>.png\` or \`docs/evidence/test-logs/\`. Do **not** include Traffic Scotland Client ID/Key, unrestricted Maps key, private tokens or other account details.

If test fails: record actual failure; create/change GitHub Issue; fix, rerun; log **FAILED → PASS** with both timestamps. A screenshot of GitHub Actions **BUILD SUCCESS** is valid only as compilation/JVM evidence, **not** proof of Android UI, full XML instrumentation or live map.

## UI revision QA — 11 October 2026 (all pending on new commit)
| Test | Steps | Expected | Actual observed | Status |
| --- | --- | --- | --- | --- |
| UX-01 | Launch Home portrait | Illustration and counts; ONE primary navigation only; no Explore traffic block; no whole-screen scroll | — | NOT RUN |
| UX-02 | Open Incidents, scroll past first 4 cards | Title, source, search, Nearby and refresh remain fixed; **only card list** moves | — | NOT RUN |
| UX-03 | Open Roadworks, scroll historical 405 | Same fixed header, 405 scrollable cards and correct data label | — | NOT RUN |
| UX-04 | Toggle Nearby off/on | Radius slider appears only when checked; filter results remain correct | — | NOT RUN |
| UX-05 | Tap circular refresh icon on Home/list | Real repository refresh triggered, control uses accessibility description | — | NOT RUN |
| UX-06 | Rotate Home, Incidents, Roadworks | Distinct landscape panels, fixed filters and usable scroll area; data preserved | — | NOT RUN |
| UX-07 | Run NavigationChromeTest on Pixel 9 Pro | 2/2 Android instrumentation smoke tests pass | — | NOT RUN |

**Screenshot already observed before this UI refactor:** 4/4 original `TrafficXmlParserTest` passing on Pixel 9 Pro AVD, and Incidents screen displaying **4 situations** from historical lecturer XML. These are baseline observations, not evidence for UX-01 through UX-07 after refactor.

## Parser regression expansion — added 26 October 2026

| ID | Test | Expected | Actual on Android | Status |
| --- | --- | --- | --- | --- |
| XML-05 | `truncatedXmlIsRejected` | Truncated DATEX XML throws XmlPullParserException | Not executed on Pixel 9 Pro | **NOT RUN** |
| XML-06 | `multipleSituationRecordsAreRetainedWithSeparateValues` | Two records under one situation, distinct IDs/delays, M8 and M80 both discoverable | Not executed on Pixel 9 Pro | **NOT RUN** |
| XML-07 | `missingOptionalValuesRemainAbsent` | Missing validity, impact and coordinates remain empty/null; A9 != A90 | Not executed on Pixel 9 Pro | **NOT RUN** |

Source: `app/src/androidTest/java/com/kirilbezmen/trafficscotland/data/parser/TrafficXmlParserTest.java`. Three tests added in Git commit `4591b68`. They must NOT be counted as passed unless Android Studio execution is observed.

## New native UX review branch — 26 October 2026

`feat/premium-native-ux-2026-10-26` / draft PR #14 implements the approved graphic UX direction. **Do not treat the AI-generated mockup as evidence of tested functionality.** All scenarios below remain NOT RUN on Pixel 9 Pro until student observation.

| ID | Scenario | Expected | Observed | Status |
| --- | --- | --- | --- | --- |
| DESIGN-01 | Open Home (portrait) on debug fixtures | Original hero graphic, 4/405 from provided XML, **SAMPLE** warning visible | — | NOT RUN |
| DESIGN-02 | Check tab nav and cards | Native four tabs, selected state, accurate incident/roadworks cards | — | NOT RUN |
| DESIGN-03 | Tap incident, roadworks; Back | Styled details; data remains identical to XML, back returns correctly | — | NOT RUN |
| DESIGN-04 | Rotate Home/list/details | Landscape rail, selected details and search state retained | — | NOT RUN |
| DESIGN-05 | Press Refresh | Brief animation; actual refresh invoked, source badge stays truthful | — | NOT RUN |
| DESIGN-06 | Open Map with restricted key | Real tiles; select multiple markers; tap marker to show parsed summary and Details | — | BLOCKED: local Maps key |
| DESIGN-07 | Open Map without key | Helpful setup message, no crash | — | NOT RUN |
| DESIGN-08 | Open launcher from Android | Original adaptive app icon displayed on Pixel 9 Pro | — | NOT RUN |

See [design-to-implementation traceability](UI_UX_IMPLEMENTATION_2026-10-26.md); save device, date, commit and redacted screenshots when performed.

## Background refresh and visible loading — 17 November 2026 (PR #14)

Changes: `TrafficRepository.refresh()` publishes a loading snapshot before network/sample processing; both portrait/landscape Home and lists expose a native indeterminate ProgressBar; lists retain previously displayed data during a refresh, preserving the original provenance label. This is a **code implementation**, not yet evidence of visual/emulator behaviour.

| ID | Procedure | Expected result | Actual on Pixel 9 Pro | Status |
| --- | --- | --- | --- | --- |
| LOAD-01 | Cold start without local DATEX II credentials (DEBUG) | Brief progress indicator, then 4 incidents/405 roadworks from the original labelled historic lecturer XML samples | Not run | NOT RUN |
| LOAD-02 | Tap Refresh on Home and list | Progress indicator is visible while refreshing; prior XML records remain accessible; completed data remains correctly marked SAMPLE/LIVE/CACHED | Not run | NOT RUN |
| LOAD-03 | Rotate while loading / after load | No crash; landscape has distinct rail; information source and filters remain correct | Not run | NOT RUN |
| LOAD-04 | Authorized live fetch succeeds | Both official DATEX II streams displayed as LIVE, only after both parse | Pending access/credentials | BLOCKED |
| LOAD-05 | Live fetch fails after successful fetch | CACHED label explicitly shown; last valid records preserved | Pending access/credentials | BLOCKED |
| LOAD-06 | Live fetch fails without cache | ERROR state; no false live information | Pending access/credentials | BLOCKED |

Do not count CI resource compilation as emulator execution. Screenshots are required for actual/expected results, no secrets in images.

## Road-search JVM regressions — 17 November 2026

Added `TrafficSituationRoadSearchTest.java` for route matching independent of DATEX API credentials.

| ID | Requirement | Expected | CI execution | Emulator/UI |
| --- | --- | --- | --- | --- |
| SEARCH-JVM-01 | M8 vs M80 | Exact motorway match, no substring collision | Pending latest JVM test run | NOT RUN |
| SEARCH-JVM-02 | Case and whitespace | M8 matches lower-case/space-trimmed user query | Pending latest JVM test run | NOT RUN |
| SEARCH-JVM-03 | A74(M) suffix | Suffixed Scottish road distinct from A74 | Pending latest JVM test run | NOT RUN |
| SEARCH-JVM-04 | Multiple records/comment fallback | Index routes in all records, exclude unrelated routes | Pending latest JVM test run | NOT RUN |

These tests do not prove the search EditText, search results, navigation, keyboard or rotation UX. User must verify these separately.

## Navigation state restoration Android instrumented tests — added 17 November 2026

`NavigationChromeTest` now has four tests: previous two for Home chrome and list/filter visibility plus two newly added checks below. Existing test suite **MUST be run by the student on Pixel 9 Pro**, as CI only compiles it.

| ID | Expected | Actual | Status |
| --- | --- | --- | --- |
| NAV-03 | Roadworks tab remains checked; Roadworks title visible after Activity recreation | Not run on emulator | NOT RUN |
| NAV-04 | M8 query and Incidents tab remain selected after Activity recreation | Not run on emulator | NOT RUN |

Activity recreation tests are NOT proof that the distinct landscape composition works; separately rotate and capture UI state.
