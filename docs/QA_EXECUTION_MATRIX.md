# CW1 QA execution matrix — expected vs actual

**Rules:** \`NOT RUN\` and \`BLOCKED\` are not PASS. After running each scenario, replace placeholders with date/time, emulator/Android version, commit SHA, actual observed result and evidence path. On 11 October student screenshots confirmed 4/4 small XML tests and 2/2 tests on complete lecturer XML files. Neither verifies live API or exact UI fields.

| ID | Scenario / Steps | Expected | Actual | State | Evidence |
| --- | --- | --- | --- | --- | --- |
| BUILD-01 | Clean Git clone, Gradle Sync / assembleDebug / install | Importable/reproducible app | Await wrapper + fresh clone | NOT RUN | — |
| XML-01 | Run four representative \`TrafficXmlParserTest\` | 4 passes | 4/4 green, Pixel 9 Pro API 36, 11 Oct | **PASS** | Screenshot in ChatGPT, not yet GitHub |
| XML-02 | Run `OfficialXmlFixturesTest`, sample_unplanned | 4 incidents, nested dates/locations | 4 incidents parsed; originalIncidentsContainFourSituations green on Pixel 9 Pro API36, 11 Oct | **PASS** | [Student test screenshot noted](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md) |
| XML-03 | Run `OfficialXmlFixturesTest`, sample_roadworks | 405 roadworks; valid records and location coverage | 405 roadworks parsed; originalRoadworksContain405Situations green on Pixel 9 Pro API36, 11 Oct | **PASS** | [Student test screenshot noted](evidence/test-logs/XML_FULL_FIXTURES_2026-10-11.md) |
| XML-04 | Malformed XML, missing/unknown nested fields | Safe error / sensible optional defaults | — | NOT RUN | — |
| UI-01 | New Home on Pixel 9 Pro | Road/cones/warning illustration, counters, source state | — | NOT RUN | — |
| UI-02 | Incidents list, tap 1st item | Type + exact comment, correct details ID | — | NOT RUN | — |
| UI-03 | Roadworks list, tap long-delay item | Correct type/description, impact/validity shown | — | NOT RUN | — |
| UI-04 | Compare sample XML and UI rows/details | Exact values from XML, no fabricated fields | — | NOT RUN | — |
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
