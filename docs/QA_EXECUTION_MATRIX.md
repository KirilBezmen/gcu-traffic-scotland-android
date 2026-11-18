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

## UX-DETAIL-01 — A74(M) Details on redesigned interface (18 November 2026)

**Evidence:** Student-provided Pixel 9 Pro emulator screenshot in ChatGPT at ~12:37 UK time, API 36 visible earlier, newly checked-out `feat/premium-native-ux-2026-10-26` branch. **The exact installed APK commit SHA remains unconfirmed** and the screenshot PNG has **not** been archived into GitHub. No screenshot of this test has been misrepresented as a committed file.

| XML field / behaviour | Observed on current screenshot | Result |
| --- | --- | --- |
| Road | `A74(M)` | MATCH to lecturer historic sample |
| Record type | `RoadOrCarriagewayOrLaneManagement` | MATCH |
| Comment | A74M(S) J19 Ecclefechan off slip closed for essential roadworks | MATCH |
| Record ID | `I_20260810_248` | MATCH |
| Subtype / validity | `roadClosed` / `definedByValidityTimeSpec` | MATCH |
| Start | `2026-08-10T11:37:42+01:00` | MATCH |
| Last updated | `2026-08-24T18:25:33+01:00` | MATCH |
| Direction / operational lanes | `southBound` / `0` | MATCH |
| To location | `J19; A74(M) J19 Eaglesfield` | MATCH |
| From and To coordinates, from endpoint | Below screenshot viewport; **not visible** | NOT VERIFIED on redesigned build |
| Back navigation, landscape retention | Not shown | NOT RUN |

**Outcome:** **PARTIAL PASS — visible fields of first incident** on 18 Nov premium UX, not a full details or navigation PASS. Prior full-field A74 audit on 11 Oct belongs to the previous app build and must not be transferred silently to the redesign. **Next:** scroll down, verify J19/J18 coordinates and return via Back; save original screenshot and installed commit SHA.

### UX-DETAIL-01 follow-up — student confirmation (18 November 2026)
Student explicitly confirmed that all remaining A74(M) Details fields pass, including the expected From/To endpoint coordinates and values. **Outcome: PASS as student-reported for the full first incident record.** Original screenshot of the lower viewport has not yet been supplied to GitHub, so independent visual evidence for that viewport remains pending. This does not establish landscape/back navigation or all incident records.

## UX-DETAIL-02 — M8 Woodside roadworks on redesigned UI (18 November 2026)

**Evidence:** Screenshot directly supplied by student in ChatGPT, Pixel 9 Pro emulator, Android API 36 (as visible in prior Android Studio screenshots), 18 November 2026 ~12:41 UK local. Current UI branch `feat/premium-native-ux-2026-10-26`; **exact installed APK SHA not confirmed**. Binary screenshot not yet archived in GitHub.

| Field | Expected from lecturer XML | Observed in redesigned screen | Result |
| --- | --- | --- | --- |
| Record ID | `D_159301` | `D_159301` | PASS |
| Event subtype | `roadworks` | `roadworks` | PASS |
| Validity status | `definedByValidityTimeSpec` | same | PASS |
| Start | `2023-07-22T00:00:00+01:00` | same | PASS |
| End | `2027-12-31T23:59:00+00:00` | same | PASS |
| Last updated | `2026-06-25T17:43:07+01:00` | same | PASS |
| Direction | `westBound` | same | PASS |
| Delay type | `longDelays` | same | PASS |
| To coords | `55.870380, -4.268265` | same | PASS |
| From coords | `55.869923, -4.261365` | same | PASS |
| Main record type | `MaintenanceWorks` | Above screenshot viewport | NOT VISIBLE in this screenshot |
| Back navigation / orientation | Return to list/maintain state | Not shown | NOT RUN |

**Outcome:** PASS for all displayed M8 roadworks fields (first selected roadwork), not proof of all 405 situations, Back or Landscape. Student should archive original screenshot and record installed commit SHA. **Next critical task:** Search M8 vs M80 from each list and verify actual results; capture clear screenshots.

## UX-SEARCH-01 — Roadworks M8 query (18 November 2026)

Student supplied a Pixel 9 Pro emulator screenshot at approximately 12:42 UK time with Roadworks search text `M8`, **21 situations** shown, and visible first results labelled `M8, A82, A81, A804` / `M8, A82, A804`, roadwork descriptions and `Long delay` badges. Historic lecturer XML provenance is clearly shown. Android API 36 was established in earlier emulator screenshots. Exact installed APK commit SHA is unconfirmed; screenshot PNG has not yet been archived in GitHub.

**Outcome:** **PARTIAL PASS:** search input filters and renders a set of 21 M8-related Roadworks rows, without observed UI crash. This screenshot alone does **not** prove all 21 source records match, that M80 is excluded, or that 21 is the independently verified expected count. Next compare M80 query and capture both screens; do not mark full 4-mark road-search requirement PASS yet.

## UX-SEARCH-02 — Roadworks M80 query (18 November 2026)

Student supplied Pixel 9 Pro API 36 screenshot at approximately 12:43 UK time. Roadworks search input contained `M80` and rendered **11 situations**. Visible first cards included road identifiers `M876, M80`, `M80`, `M80`, and `M73, M80, M74`. None of these visible cards was a standalone `M8` result, supporting exact-route filtering. Historic lecturer XML source label remained visible. Previous `M8` search screenshot showed **21 situations**.

**Outcome: PASS for the observed M8 vs M80 interactive filtering comparison** (21 vs 11 different displayed result sets, correctly labelled routes). UI renders correct-looking matches and does not hang. This is not an independent audit of *all* 11 or 21 records, and exact installed APK commit SHA and archived screenshots remain pending. The earlier JVM regression test checks exact matching algorithm separately; distinguish CI from emulator observation.

**Next test:** Nearby toggle, radius and actual fixed-reference filtering, first on Roadworks without search text.

## UX-NEARBY-01 — Roadworks 25 km (18 November 2026)

**Student-observed screenshot:** Pixel 9 Pro Android emulator API 36, 18 November 2026 approximately 12:44 local. Search field empty; "Show nearby situations" checked. Radius label explicitly reads `25 km from Glasgow city centre (fixed reference)`; slider is visible. Roadworks result count is **53 situations** compared with 405 without nearby filtering. First visible entries include M8 Woodside roadworks. Historical sample-data warning remains present.

**Observed UI verdict: PASS (25-km nearby control and list filtering)**. This screenshot confirms that the radius controls become visible, the UI reports the fixed reference and filters results to 53; it does **not independently validate all 53 geospatial inclusion decisions**, or the 5 km / 200 km extremes. Follow-up: set slider to 5 km, record result count and screenshot, then 200 km.

Screenshot is in original ChatGPT conversation only (not yet stored in GitHub); installed APK commit SHA still needs student confirmation. No live API inference.

## UX-NEARBY-02 / UX-NEARBY-03 — 5 km and 200 km extremes (18 November 2026)

**User-supplied Pixel 9 Pro emulator screenshots, 18 Nov 2026 approx. 12:47 UK.** Both show Roadworks, blank road search, Nearby checked, labelled fixed Glasgow city centre reference, visible radius slider, original lecturer historic XML source warning and result cards. Screenshot PNG files remain attached to the ChatGPT conversation and are **not yet archived in the repository**. Exact installed APK SHA not provided.

| Radius | Visible result count | Comparison / UI outcome | Status |
| --- | ---: | --- | --- |
| No Nearby | 405 | Full lecturer sample | Prior screenshot |
| 5 km (left edge) | **14** | 14 < 53; visible roadworks cards and working slider | PASS (UI) |
| 25 km (middle value) | **53** | Previously documented check | PASS (UI) |
| 200 km (right edge) | **370** | 370 > 53 and < 405; labels/cards render | PASS (UI) |

**Scope:** Verifies requested radius range, UI filtering and expected monotonic increase in reported counts for these samples. This does **not** independently verify all point-to-segment distance calculations or inclusion/exclusion of all 405 roadworks. Existing JVM distance regression tests cover representative geometry separately. **Next:** rotate to Landscape with Nearby 5 or 200 km active and verify filter state and distinct navigation rail; capture a landscape screenshot.

## UX-ROT-01 — Roadworks / Nearby landscape rotation (18 November 2026)

Student supplied Pixel 9 Pro API 36 emulator screenshot at approximately 12:49 UK local time, following preceding Roadworks Nearby test at **200 km / 370 situations**. Screenshot shows distinct landscape layout: dark left navigation rail, `Roadworks` selected, search and Nearby controls in a left filter column, a scrollable roadworks results area on the right, checkbox still checked and **370 situations** preserved across rotation. Nearby distance label appears consistent with the previously selected 200 km setting. Lecturer historical XML warning remains visible.

**Observed status: PARTIAL PASS** — distinct landscape composition and retained selected section/filter/result count. **Known limitation:** software keyboard is open across the bottom of the screenshot; some lower layout/slider/list interactions cannot be assessed and text is cramped. Screenshot is in the ChatGPT conversation, not yet archived to GitHub; precise installed commit SHA still unconfirmed.

**Next:** dismiss the keyboard (Android Back once), verify radius label and list visibility without keyboard, capture uncluttered landscape screenshot; then separately rotate while Details is open and test Back state. Do not claim the full 8 orientation marks from this one screen.

## UX-ROT-02 — Roadworks Details visible in Landscape (18 November 2026)

**Evidence:** Student uploaded Pixel 9 Pro emulator screenshot at ~12:53 UK local time on 18 Nov 2026, after previous Roadworks Nearby/landscape exploration. The UI shows the distinct dark vertical navigation rail (Roadworks selected), a horizontally oriented details area, record `D_159301`, event subtype `roadworks`, validity `definedByValidityTimeSpec` and corresponding start/end/updated dates previously compared with lecturer's historic M8 XML. App remains responsive enough to render the details fragment after rotation.

**Observation:** PARTIAL PASS for selected record identity and visible text retained in landscape. **Not yet verified:** full content scroll/coordinates or Details back navigation in Landscape. Text looks unusually small at this AVD landscape size; potential legibility issue for user-facing usability. The screenshot does not visibly show the Details back button (likely vertically scrolled). Test steps for next pass: press system Back from Details, verify Roadworks filtered list is shown in landscape, then rotate to portrait and verify last search/Nearby state. Source remains historic lecturer XML; no live access implied.

**Evidence limitation:** Screenshot only in ChatGPT, not committed as a PNG to repo. Exact installed APK SHA to be recorded by student.

## UX-BACK-01 — Back from roadworks Details in Landscape (18 November 2026)

**Evidence:** Student-supplied screenshot at approximately 12:57 UK time, Pixel 9 Pro Android API 36 emulator. After opening M8 roadworks Details in Landscape, the student pressed system Back. The resulting screenshot displays the **Roadworks list** rather than Home, with the separate landscape left navigation rail, **Nearby checked**, **200 km** radius label and **370 situations**, consistent with the pre-Details state. Historic lecturer XML source warning still visible.

**Result:** **PASS (observed):** Details → Back returns to Roadworks, and the selected Nearby mode/radius/result count persist in Landscape. Screenshot visible in ChatGPT; original PNG not yet archived to GitHub. Exact installed APK commit SHA not independently established. This confirms the tested navigation path only, not all activity recreation/back-stack paths.

**Next:** test opening Map without a locally configured Google Maps key (expected clear no-key message, no crash) before attempting authenticated map tiles or multiple markers.

## UX-MAP-00 — No Maps SDK key fallback (18 November 2026)

**User screenshot:** Pixel 9 Pro emulator Android API 36 at ~12:58 UK time, premium UX branch. The Map tab is selected and shows the labelled historic lecturer XML source and native empty-key information panel: “Google Maps is permitted for this coursework. Add your own restricted Maps SDK for Android API key to local.properties as MAPS_API_KEY, then Gradle Sync. The traffic lists remain available without a Maps key.” App is visibly running, with no crash. The original screenshot is in this ChatGPT conversation but has not been added to GitHub; exact APK SHA remains unverified.

**Result: PASS for no-key fallback screen and no visible crash.** **BLOCKED for map tiles, source-derived markers, multiple selected records, info windows and navigation until user configures a restricted Google Maps key locally.** No marks are claimed for actual map rendering on this screenshot.

**Next:** configure `MAPS_API_KEY` privately in root `local.properties`, verify Android SDK restriction and fingerprint, then retest real map tiles/markers on Pixel 9 Pro. Do not share or commit the key, or redistribute a secret-bearing APK.

## UX-MAP-01 — Google Maps tiles and multiple XML-based markers (18 November 2026)

**Evidence:** Pixel 9 Pro API 36 emulator screenshot supplied by student in ChatGPT at approximately 13:22 UK time on 18 November 2026. After configuring a new Maps SDK for Android API key locally in the ignored root `local.properties`, the Map tab displays real Google basemap tiles and numerous orange roadworks marker icons distributed over Scotland, alongside the historic lecturer XML samples disclosure. Native Home/Incidents/Roadworks/Map navigation remains visible; no visible API authorization error or crash.

**Result: PASS (visible map rendering and multiple roadworks markers).** This screenshot does **not** independently validate every point's source coordinates, selected-results-only marker filtering, marker tap info windows, marker preview to Details, map rotation, or DATEX II LIVE API. These remain pending. Installed APK Git SHA not recorded. Original screenshot only in the ChatGPT conversation, not yet archived in GitHub. **Do not include the actual API key in any screenshot or repository file**.

**Next manual test:** tap an orange roadworks marker in Map, verify title and source-grounded preview; tap preview to open the matching Details record and confirm Back navigation returns to Map.

## UX-MAP-02 — Marker selection and XML-grounded preview (18 November 2026)

**Student-supplied screenshot:** Pixel 9 Pro Android API 36 emulator, approximately 13:24 UK time. In the Map tab, student tapped an orange roadworks marker north-east of mainland Scotland, and Google Maps displayed its native info window with roads `A90, A981` and description `A90 Fraserburgh - TTLS N/B`. The application's own native bottom preview simultaneously displayed the same road codes and description plus `Tap to open details`. Historic lecturer XML sample status remains clearly labelled at the top.

**Observed result: PASS for marker tap, matching title/summary and bottom preview.** No evidence yet that tapping the preview navigates to the matching Details or that system Back returns to Map; both remain **NOT RUN**. Matching is assessed between two UI surfaces, not by independently reading original XML for this specific Fraserburgh record. The source image is in the conversation but has not been committed to GitHub, and exact APK commit SHA has not been recorded.

**Next action:** tap the native bottom card `Tap to open details` and check road codes, type, record ID, description; then system Back. Screenshot the Details screen before marking that navigation PASS.

### Clarification — Map → Details screenshot sequence (18 November 2026)

An A90/A981 Fraserburgh marker-preview screenshot was followed by an A76/A702 Drumlanrig Details screenshot. This was **not a controlled same-marker test**: the student explained that they had left the first selection and subsequently tapped a different map item before opening Details. Accordingly, the different records **must not be classified as a Map → Details defect or a failed test**. Student reports that navigation works correctly. The screenshot sequence independently confirms that Details opens for a selected map item, but does not establish A90/A981 → A90/A981 identity preservation. **Status: student-reported working; exact same-marker-to-Details verification remains optional/pending.** Next highest-value map rubric test: use Roadworks M80 search → View results on map → confirm that only filtered mapped situations appear. No application code changes justified by this screenshot sequence.

## UX-MAP-03 — M80 filtered-map investigation (18 November 2026, ~13:30 UK)

Student screenshot displays Map with `All geolocated traffic situations`, `Show all situations` and numerous roadworks markers. **Observed:** map-selection state is empty, so the map is displaying all geolocated samples, not an 11-result M80 subset. The screenshot does **not** show how the user navigated to Map (direct Map tab versus the list's `View results on map` button). **Status: INCONCLUSIVE / NOT VERIFIED as a filtered-map test, rather than confirmed code failure.**

Static Java review: `TrafficListFragment.mapResults` calls `model.selectForMap(model.filtered(section, snapshot))` followed by `navigate(MAP)`; `TrafficMapFragment.drawMarkers` checks `model.mapSelection()` and filters marker IDs when nonempty. No code defect established from this static review. Controlled retest: return to Roadworks; uncheck Nearby, search M80; verify 11 list matches; specifically tap dark `View results on map` (not bottom Map tab); screenshot Map immediately. Expected header `11 selected situations` (or corresponding resource copy), only selected events with coordinates visible. Record actual and compare with source IDs before fixing anything.

## UX-MAP-03 — controlled Roadworks M80 filtered-map retest, PASS (18 November 2026)

**Evidence:** Student's Pixel 9 Pro Android API 36 emulator screenshot, ~13:32 local UK time, taken immediately after using Roadworks search `M80` (previously confirmed 11 matches) then the **dark `View results on map` button**, not the bottom Map tab. Map header reads exactly **`11 selected situations (tap markers for details)`**. Only a small cluster of orange roadworks markers is visible in central Scotland (Glasgow/Falkirk vicinity), with Google basemap tiles and explicit **historic lecturer XML, NOT current traffic** provenance.

**Result: PASS** for transferring the 11 selected Roadworks IDs and filtering displayed markers in the controlled UI flow. The earlier screenshot showing `All geolocated traffic situations` reflected an empty selection/default Map screen and is **not a defect**.

**Important evidence limit:** 11 selected *situations* need not mean 11 visually distinct markers—some records can share coordinates or overlap and not all records necessarily have mappable coordinates. The screenshot confirms subset appearance and the 11-selection label; a source-to-map verification of every ID/coordinate and `Show all situations` reset remains pending. Screenshot is in this ChatGPT conversation, not yet archived in GitHub; installed APK SHA not independently recorded.

**Next milestone:** authorised Traffic Scotland DATEX II connection; keep provider and Maps secrets out of repository and screenshots.

## DATEX-LIVE-00 — official Developer Guide integration (18 November 2026)

**Student provided** approved DATEX II Developer Guide excerpt. It confirms:
- Base URL: `https://datex2.trafficscotland.org/rest/2.3/`
- Unplanned Events: `publications/UnplannedEvents/Content.xml`
- Current Roadworks: `publications/CurrentRoadworks/Content.xml`
- Authorization per HTTP Basic: `Authorization: Basic <base64(clientId:clientKey)>` (Guide says RFC 7617; its PowerShell example includes `Basic ` prefix, despite Java snippet omitting the scheme name).
- Content is SOAP-wrapped DATEX II XML and may change around every five minutes; app's hourly refresh targets coursework requirements.

**Implementation:** `TrafficRepository.java` now uses confirmed static endpoints and includes the `Basic ` authorization scheme. `TRAFFIC_ROADWORKS_URL` no longer required; only `TRAFFIC_CLIENT_ID` and `TRAFFIC_CLIENT_KEY` are required locally in `local.properties`. These two values are **sensitive and extractable from APK BuildConfig**. Never share full screenshots/compiled secret-bearing APK.

| ID | Test | Expected | Status |
| --- | --- | --- | --- |
| DATEX-LIVE-01 | Local credentials + rebuilt app on Pixel 9 Pro | Header-based authenticated downloads of BOTH Content.xml endpoints; UI label `Official Traffic Scotland data`, not SAMPLE | NOT RUN |
| DATEX-LIVE-02 | Tap Refresh | New request and updated source/timestamp without UI blocking | NOT RUN |
| DATEX-LIVE-03 | Disconnect network after successful live fetch | Clearly marked CACHED, last successful XML remains shown | NOT RUN |
| DATEX-LIVE-04 | No cache/no internet while credentials configured | Explicit ERROR, never invented LIVE/SAMPLE | NOT RUN |
| DATEX-LIVE-05 | Verify original XML parser against current live payload | No SOAP/namespace parsing regressions; sensible incident and roadwork records | NOT RUN |

## DATEX-LIVE-01 — first live feed success (18 November 2026, 13:59 UK)

**Source:** Three screenshots supplied directly by the student in ChatGPT on 18 Nov 2026 from the Pixel 9 Pro API 36 emulator running the updated premium UX branch. Student previously configured Traffic Scotland Client ID and Client Key in ignored `local.properties`, pulled the changes based on the official DATEX II Developer Guide and rebuilt/launched the app. **Screenshots are in the ChatGPT conversation, not yet archived into the GitHub repository.** Exact local APK SHA not reported.

| Screen | Observed in emulator | Expected | Verdict |
| --- | --- | --- | --- |
| Home | **7 incidents, 421 roadworks**, source label `Official Traffic Scotland data` | Two official sources displayed as LIVE instead of historic lecturer fixtures | PASS, displayed |
| Incidents | **7 situations**, including A6091 road closure and M74 obstruction | Incidents list populated from authorised Unplanned Events `Content.xml` | PASS, displayed |
| Roadworks | **421 situations**, M8 Woodside records visible | Roadworks list populated from authorised Current Roadworks `Content.xml` | PASS, displayed |

The prior lecturer SAMPLE values were **4 incidents and 405 roadworks**. The changed counts and official source label strongly support authenticated live downloads and successful XML parsing of both feeds. Code only labels LIVE after both downloads and parses complete. These are **not** independent packet captures or cross-checks against current provider XML; further source-to-screen audit can strengthen evidence.

**Remaining NOT RUN:** manual refresh against live feed; first successful offline CACHED fallback; no-cache ERROR state; hourly refresh observation; map with live coordinates; full XML data comparison. Credentials in BuildConfig are extractable from the compiled APK: the locally tested authenticated APK must **not** be uploaded to GitHub or submission ZIP without an approved non-leaking distribution approach. Save redacted original screenshots with date/time and commit SHA before copying evidence to Word.
