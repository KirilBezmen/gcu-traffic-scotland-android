# Traffic Scotland Explorer — Simple CW1 development board

> **Current project status — 18 November 2026:** The app's native UI, authorised DATEX II LIVE feeds, Maps and key flows were student-tested and merged into `main` via [PR #14](https://github.com/KirilBezmen/gcu-traffic-scotland-android/pull/14). Seven [current Issues](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues) now list verified tasks and remaining evidence. The earlier 26 October plan below is **historical**, not the latest status. For current status use [README](../README.md), [QA Matrix](QA_EXECUTION_MATRIX.md) and [Student Work](STUDENT_WORK_AND_EVIDENCE.md). Gradle Wrapper 8.9 is now committed; a fresh Windows clone still needs testing. APK credential security and hand-in artefacts remain open.


> **Visual GitHub Project:** student confirmed creating the project and auto-importing 7 open issues on 11 October 2026. Board statuses/settings have not been verified through an API; this document remains the issue workflow plan.


## Fastest responsible route to full CW1 submission — 26 October 2026

**Stop cosmetic work.** Implement only missing marking criteria, fix defects found by tests, and finish a defensible submission. Main branch as checked on 26 Oct: last green CI was `8f4cf8a` (11 Oct); additional parser tests committed `4591b68` 26 Oct — **new Android tests not yet run on emulator**.

1. **Blocker P0: real feeds.** Student says developer access was granted. Verify it is **authorised DATEX II Unplanned Events and Current Roadworks**. Put Client ID, Client Key and exact Roadworks `Content.xml` URL ONLY in untracked `local.properties`. Never share real secrets via chat/GitHub/issue/screenshots. Run startup and retry, confirm live records and source badge. Address distributable APK credential exposure before hand-in. Verify Maps API key separately.
2. **P0 rubric 42/100: user journey.** Demonstrate Incidents/Roadworks, exact road filters (M8 vs M80), Nearby radius, meaningful 6+ icons with licencing, roadworks short/long colour-coded delays correctly interpreted from official fields, and **multiple user-selected map markers**. If the rubric's duration/delay semantics or icon max 6/8 is unclear, ask Mario Mata rather than inventing a rule.
3. **P0 rubric 18/100: architecture + rotation.** One Activity / Fragments / ViewModel already in source; check landscape layout distinction, retained selected item, search and radius. Run `NavigationChromeTest` on device.
4. **P0 rubric 10/100: XML.** 4 small plus 2 full-fixture parser tests already PASS; source-to-screen A74(M) and M8 audited. Three new tests for truncated XML, multiple records and absent optional fields are **NOT RUN**; execute full parser suite on Pixel 9 Pro and record results. Exercise live feed validation.
5. **P0 rubric 10/100: background/offline.** Verify first automatic refresh, another timed refresh, loss of connectivity after genuine live fetch (cached label), and fresh-install offline (error, not fake LIVE).
6. **P0 rubric 20/100: evidence and hand-in.** Complete actual/expected evidence table, archive images, narrate both orientations, build and verify APK and clean-clone Android source, Word summary + originality + complete exact GenAI prompts + OneDrive video link, plus required examiner ZIP. Check GCULearn final instructions. **23 Nov CW1:** conflicting 12:00 vs 17:00 documentation; use 12:00 until clarified. **30 Nov CW2:** separate supervised class test.

**Definitions of Done:** Code committed; Android emulator execution PASS with date/API/commit/evidence; security and source constraints checked; only then mark Issue completed. CI compilation is not an Android test run. M0–M6 stay open until every acceptance item is evidenced.

## One project · seven active tasks · no duplicate work

**Keep just seven open GitHub Issues, one for each coursework phase (M0–M6).** Each issue contains current verified progress, remaining acceptance checkboxes and evidence needed for full marks. Treat **Issues as tasks, not errors**; an Open Issue does not imply broken software.

| GitHub Issue | Phase | Initial board status | What actually needs doing next |
| --- | --- | --- | --- |
| [#1](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/1) | **M0** Android setup and official API | **Blocked** | Fresh-clone Gradle wrapper; await Traffic Scotland approval; validate both official feeds |
| [#2](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/2) | **M1** DATEX II XmlPullParser | **Testing** | **2/2 full XML tests PASSED** (4 incidents, 405 roadworks); **A74(M) and M8 sample Details match XML**; edge cases/performance still pending |
| [#3](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/3) | **M2** Incident/roadworks UI + details | **Testing** | Home and first incident/roadworks Details verified against XML; next run navigation tests, rotation, other records and error states |
| [#4](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/4) | **M3** Single Activity/MVVM/rotation | **Testing** | Portrait↔landscape switching, preserve filters, selected detail and visible data |
| [#5](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/5) | **M4** Search/nearby/map/icons/delays | **In Progress** | Validate radius/search; restricted Maps key and multi-selection; proper 6+ icons |
| [#6](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/6) | **M5** Live updates/background/offline | **Blocked** | Test official startup/hourly download, no internet and caching after API approval |
| [#7](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/7) | **M6** QA/video/APK/Word/ZIP | **In Progress** | Record real expected/actual tests and final delivery assets |

**Already completed narrow checkpoints** (optional Done column): [#8](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/8) repo foundation, [#9](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/9) old app baseline on emulator, [#10](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/10) four small Android XML tests (4/4 PASS). They do **not** prove the final new app is complete.

**Archived duplicates:** [#11](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/11), [#12](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/12), [#13](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/13) were opened during planning, their tasks merged into #1 or #2, then closed with reason **duplicate**, **NOT completed**. Do not import these three into the new Project. This yields **7 Open and 6 Closed** issues, but only **3 of those Closed are verified completed checks**.

## Create the visual GitHub board — one-time owner action

1. Open [GitHub → repository → Projects](https://github.com/KirilBezmen/gcu-traffic-scotland-android/projects). The "No projects found" screen is normal at this stage.
2. Click the green **New project** button (top right), then choose the **Board** template and name it **Traffic Scotland Explorer — CW1**.
3. Create the project. Check that its visibility is **Private**, independently of repo visibility. If the project does not appear under the repo Projects tab afterwards, use **Link a project** to attach it.
4. From the Board use **+ Add item** or the project's repository issue picker and import **only issues #1 through #7**. These are existing Issues; **do not create new draft cards**.
5. Use four statuses: **Todo / In Progress / Testing / Done**. If GitHub allows, add **Blocked** for M0 and M5. Otherwise put M0/M5 in Todo with a clear blocker label/description. Move M1/M2/M3 to Testing and M4/M6 to In Progress.
6. Optionally import #8/#9/#10 into Done for context. **Do not import #11/#12/#13**, because those are administrative duplicates.
7. Share a screenshot of the created board once; from then on Issues contain task detail and the Board is only the high-level visual status. No second task list needs to be manually maintained.

> **Connector limitation:** the GitHub integration currently offers Issue and repository write operations but no creation/assignment of GitHub Projects. The assistant can maintain the issue bodies and repo docs; board setup and moving cards require the GitHub UI or a supported browser-enabled workflow.

## What "Done" means
A phase is complete **only** when its acceptance checklist is satisfied, actual Android evidence exists, source data constraints are met, and the main Issue is closed. GitHub green **BUILD SUCCESS** proves code compilation/JVM tests, not actual emulator/map/API success.

- **Development:** Java/XML Android code + targeted commit; link issue in commit message when relevant.
- **Verification:** run on Pixel 9 Pro, compare XML to views, verify orientation/network/map. Write actual vs expected with date, SHA, screenshots in [QA_EXECUTION_MATRIX.md](QA_EXECUTION_MATRIX.md).
- **Documentation:** update [DEVELOPMENT_LOG.md](DEVELOPMENT_LOG.md) and [GENAI_USAGE_LOG.md](GENAI_USAGE_LOG.md). Source requirements in [PRODUCT_REQUIREMENTS.md](PRODUCT_REQUIREMENTS.md), official [100-mark checklist](RUBRIC_100_CHECKLIST.md).
- **Closing:** do not mark completed before real acceptance. If blocked, keep Open.

## Internal delivery windows (not official submission deadlines)

| Dates | Main objective |
| --- | --- |
| **12–16 October** | M0 wrapper/clean build, M1 full XML tests, M2 new UI run; API request pending |
| **17–27 October** | M2/M3 UI and rotation verified; M4 road/nearby, map, icon/delay checks |
| **28 October–7 November** | M5 actual authorised feed and offline/refresh tests if API access granted |
| **8–15 November** | Full M6 system testing, screenshot evidence, accessibility and regression |
| **16–20 November** | Narrated video, Word + exact GenAI prompts, APK, clean-project ZIP, External Examiner ZIP |
| **23 November** | CW1 detailed instructions say **17:00**, generic assessment brief says **12:00**; internal **noon submission target** until clarified |
| **30 November** | Separate invigilated CW2 class test (30% of module) |

## Rubric breakdown — possible, not achieved

**42 functionality + 8 portrait/landscape + 10 XML parsing/collections + 10 MVVM architecture + 10 threading/refresh + 15 documented testing + 5 narrated demo = 100.**

There is an unresolved discrepancy within the icons rubric (heading max 6 vs explanatory max 8). Clarify with Mario Mata; detailed acceptance criteria are in [RUBRIC_100_CHECKLIST.md](RUBRIC_100_CHECKLIST.md). **100/100 is our development target, not an assured grade.**
