# GenAI use and prompts — GCU CW1

Official category: **G — Guided**. The coursework brief requires describing AI assistance to code, tests, debugging and related work, plus an appendix listing prompts. Keep exact prompts and accurately identify AI-generated work adopted or changed.

## Entry 001 — 10 October 2026
**Tool:** ChatGPT (OpenAI).

**Exact student prompt:**
> Смотри, я не знаю, как работает этот плагин твой, но ты можешь сейчас создать в моем GitHub'е, в нашем GitHub'е, будем вместе работать, мы же с тобой одна большая команда, создать репозиторий с нашим этим проектом. Вот, оформить полностью всю структуру, расписать там readme, создать все нужные папки, которые будут использоваться в проекте. Вот. Ты сможешь это оформить? Я тебе делегирую эту задачу, чтобы ты сделал красивое оформление, всё чётко оформил. Вот. Сможешь это сделать?

**AI contribution:** proposed repository structure, README, starter Java/XML screen, Android configuration and documentation templates. A ZIP was supplied for student review.

**Adopted:** foundation scaffold only; at this step no real GitHub upload or Android build was verified.

## Entry 002 — 10 October 2026
**Tool:** ChatGPT with connected GitHub.

**Exact student prompt:**
> готово теперь у тебя будет доступ к этому репрозоторию и можешь все создать?

**AI contribution:** checked private repository and its push access; authored and committed foundation files to main, including README, project Java/XML source, Gradle metadata, documentation, GitHub templates and tracking files.

**Adopted:** source/documentation scaffold, *not* a fully functioning Traffic Scotland application.

**Verification:** repo metadata and write permission checked through connected GitHub. Android Studio build/device test is still outstanding; wrapper JAR/scripts pending. The student should review the content and test it locally.

## Template for all future entries
Date:
Exact student prompt(s):
Tool/model:
Suggestions, code or tests AI produced:
What was adopted/changed/rejected:
How independently checked:
Related commit/issue/test IDs:

**Integrity:** Never hide AI-assisted code or fabricate testing. Consolidate actual entries into the submitted Word appendix.

## Entry 003 — 10 October 2026
**Tool:** ChatGPT with file analysis and connected GitHub.
**User message:** Begins `такс теперь важная инфа` and contains the pasted assessment schedule/rubric plus uploads `skeletonCode.zip`, `xmlStructure_summary.txt`, `roadworksContentExample.xml`, `unplannedContentExample.xml`, `MarksBreakdown_Traffic.pdf`, `MPD_26_27_Traffic.docx`. This is an identifying excerpt, **not falsely presented as a verbatim full prompt**; retain the complete user message in the ChatGPT chat history for the final appendix.
**Assistance:** Analysed rubric, inspected Java skeleton code, audited the XML samples and their item counts, proposed rubric-led MVVM architecture, created requirements/tracing/audit documentation. No new Android runtime functionality written in this entry.
**Adopted / verification:** Documentation committed to GitHub; XML sample inspection with an XML parser was conducted for **analysis only**, *not* as the implementation parser. No Android build or app testing performed.
**Student follow-up:** Review assessment matrix and source restrictions; confirm source access, check app locally, maintain complete prompt transcript for GCU declaration.

## Entry 004 — 11 October 2026
**Tool:** ChatGPT with connected GitHub.
**User prompt (last instruction, verbatim):**
> теперь давай сюда и пишем сразу идеальный полноценный код который мы не будем 100 раз редактировать сразу на 100%

**Context:** The user also pasted their local Android Studio `MainActivity.java`, beginning `package com.kirilbezmen.trafficscotland;`. Keep the complete user message in the original chat transcript for the final prompt appendix.

**AI contribution:** Authored the first one-Activity navigation shell, MVVM section state, three separate Fragment classes, portrait and landscape activity_main layouts, fragment placeholder layouts, strings, Internet permission and Gradle dependency declarations. The old starter raw-XML UI is superseded in the GitHub copy; professor's code remains an annotated reference.

**Adopted:** Commit `07734399ad8c24715f29066f548d4c57944a150f` on the private GitHub repository. A subsequent documentation commit maps the project naming convention.

**Verification:** GitHub write succeeded; **no Android Studio build/emulator test of this commit has been performed**. User's separate local Empty Views app previously ran the default Hello World screen, which does not verify this newer GitHub project. No XML parser/network functionality yet.

## Entry 005 — 11 October 2026
**Tool:** ChatGPT with GitHub connector and local XML inspection/test harness.
**Exact user prompt:** `запросил что мы можем теперь делать дальше` (with screenshot showing Public Restricted API request submitted; complete screenshot remains in original chat).
**Contribution:** Analysed two provided DATEX II samples; implemented Java PullParser and three nested model classes for incidents/roadworks and included an Android instrumentation test suite. Pushed code to private GitHub repository. Generated source fixture values from real coursework examples; external StAX adapter used only to preliminarily validate Java parsing algorithm offline and not included in app.
**Adopted:** Code commit `d723f2e` and follow-up tests/docs commit. No Android emulator execution claimed. Student must Git Pull, review and run test suite; prompt is disclosed here for assessed use.

## Entry 006 — 11 October 2026
**Tool:** ChatGPT with connected GitHub.
**Exact user prompt:**
> хочу пару уточнений 
> 1) У нас развязаны руки на счет дизайна приложение? если да мы можем сделать красивое приложение с дизайном 
> 2) Когда мы начнем вести документацию документ? Нужен ли он нам? Так же мы ведем документацию на гит хабе 
> 3) Нужно разобраться с гитом чтобы там отобрадалось что мы сделали что нам нужно сделать шаги и таблица с действиями я хз надо или нет или у нас и так очень классная стуктура нам нужен 1 класс 100%

**AI contribution:** Answered app-design/documentation questions using course brief, reviewed repository status, drafted a concise design direction and rewrote GitHub README as transparent progress dashboard with issue links, evidence status and final Word/submission requirements. No production app functionality changed.

**Human review:** User should verify dashboard status and decide whether to adopt proposed styling. No marks claimed as already awarded.

## Entry 007 — 11 October 2026
**Tool:** ChatGPT with connected GitHub.
**Exact user text prompt (screenshots separately provided):**
> вот я про это говорил это нам нужно вести? и давай закроем то что мы сделали с тобой уже будем двигаться ?

**AI contribution:** Explained the difference between GitHub Issues, optional Projects board and Pull Requests; inspected existing seven milestones, created and closed three narrowly scoped verification issues (#8 repo setup, #9 first emulator launch, #10 four smoke parser tests) and updated README/development log links. Did not assert full rubric features are complete, and did not change implementation code or claim new tests.

## Entry 008 — 11 October 2026
**Tool:** ChatGPT with connected GitHub, Files source access and official provided XML samples.
**Exact most recent user prompt:** `все начинаем разработку начинай делать все`
**Context:** User had earlier authorised design, GitHub commits and full implementation, proposing a road/cones/warning-sign home screen and insisting on rubric compliance. Original complete prompt and image remain in the chat transcript; this log does **not** falsely reproduce them as verbatim if abridged.

**GenAI contributions:** Reviewed current GitHub code and official coursework XML, uploaded lecturer fixtures, wrote Java repository and AndroidViewModel, full-XML test source, Java/Android XML Views layouts, list/detail/home Fragments, road search/filter, delay badges, original hand-authored vector artwork, Gradle local credential config, GitHub workflow and design/technical docs. Created GitHub commits including `f0e3a91`, `afc40d7`, `bc3b9fe`, `3bc3ee4`, `1dac057` and CI adjustments. The GUI designs/code were written with AI assistance and must be disclosed in the final coursework Word appendix.

**Validation honesty:** Previous small XML cases **4/4 PASS** on 11 October (student screenshot). **New full-fixture tests, UI integration, local demo, live fetch, rotation and map have NOT been run successfully on emulator yet.** CI SDK setup errors are not app-code verification. User must review and run these changes to establish real evidence.

## Entry 009 — 11 October 2026 (continuation of Entry 008, same user delegation)
**Tool:** ChatGPT with GitHub connector and public Google Maps Android documentation.
**Exact initiating prompt:** `все начинаем разработку начинай делать все` (same user request; not a separate user prompt).
**AI contribution:** Added nearby-distance filtering with point-to-line handling, JUnit tests, Google Maps SDK with optional locally configured key, marker/segment display and multiple selected road-search results, seven hand-authored vector icons with attribution and event mapping, CI compile/unit-test workflow + debug APK artifact, and updated development/GenAI documentation. Explained academic restrictions and avoided unapproved frameworks or WebView.
**Testing honesty:** Recent GitHub Actions green builds compile Java/XML and run JVM tests; they do **not** run Android instrumentation/emulator tests, validate the Google Cloud map key, or verify real Traffic Scotland credentials/live/offline behaviour. These must be performed and logged by student.

## Entry 010 — 11 October 2026
**Tool:** ChatGPT connected GitHub.
**Exact student prompt:** `да  создай и пропишу там всю логику все что нужно нам для 100%`
**Context:** Direct reply to prior discussion of a GitHub Projects Kanban board, all GCU CW1 assessment functions and verification stages.
**AI contribution:** Drafted project-board setup blueprint, full product/backend/UI flow, rubric acceptance matrix and expected/actual QA scenarios. Created GitHub Issues #11–#13 for clean build, approved feeds and full lecturer XML tests; corrected accounting of rubric summary submarks; linked documentation from README. No visual board was actually created, and no device test or grade is claimed.
**Student actions:** Create/link the visual private GitHub Project manually, add existing issues, review test/mark assumptions, execute tests, and disclose all AI prompt/code assistance in final Word appendix.

## Entry 011 — 11 October 2026
**Tool:** ChatGPT with connected GitHub.
**Exact student request:** такс я немного запутался тут ничего не появилось и у нас появилось теперь 10 ошибок которые нужно закрыть давай так чтобы у нас не было каши а четка структура
**AI contribution:** Explained that 10 GitHub Open Issues were planning tasks rather than bugs, and Projects was empty because the actual visual board had not been created. Reorganized Issues into seven single M0–M6 phases; consolidated acceptance checks from new planning Issues #11–#13 into #1/#2; closed redundant #11–#13 with **duplicate** reason (not completed). Rewrote PROJECT_BOARD.md and README to explain a one-time manual visual board creation and accurate 7 Open/6 Closed counts.
**Validation:** Checked GitHub Issue state changes; no new Android app test performed and no actual Projects board created.

## Entry 012 — 11 October 2026 (UI refactor)
**Tool:** ChatGPT with GitHub connector for code edits/commits and documentation.
**Exact most recent student prompt:**
> перепиши весь код исправь его задокументрий все на гит а я сюда запушу

**Preceding user requirement (quoted context):**
> пару изменений по дизауйну это убрать потому что у нас и так будет с тобой вкладки выше нельзя скролить вниз вот и вместо кнопки рефреш можно использовать круговую стрелку как это обычно делают

**AI contribution:** Inspected current source; wrote replacement native Java/XML Home and TrafficList fragment UI with removal of duplicate Explore traffic nav, full-page fixed layouts, scrolling restricted to ListView, conditional Nearby slider, circular refresh icon and contentDescription; authored separate responsive landscape fragment layouts; created new Android UI smoke tests; updated documentation and committed these changes. Retained Java/XML native approach, single Activity + Fragments + MVVM and Traffic Scotland source restrictions.
**Student review/validation required:** Pull code in Android Studio, sync Gradle, run actual emulator UI and two new tests, capture evidence, confirm layout fits landscape. GitHub green compilation alone is not device validation. These exact prompts plus other prompt entries must be included in the final GenAI declaration appendix.


## Entry 013 — 11 October 2026 (test reporting)
**Tool:** ChatGPT with connected GitHub.
**Student message:** Screenshot of Android Studio / Pixel 9 Pro with 2 passed tests in `OfficialXmlFixturesTest`.
**AI contribution:** Interpreted observed test results; updated M1 acceptance issue, test strategy, README, QA execution matrix and development log. Distinguishes historical XML fixture verification from live feed/UI tests; no Java or Android test code was generated in this step.
**Validation:** Student executed test; screenshot in conversation, still needs archiving to GitHub evidence folder.


## Entry 014 — 11 October 2026 (student-led XML-to-UI audit)
**Tool:** ChatGPT using screenshots from student and private GitHub original XML source.
**Student message:** Two Android Studio screenshots showing working Home with 4 incidents / 405 roadworks and first A74(M) Incident Details on Pixel 9 Pro.
**AI contribution:** Compared each field actually visible on the Details screenshot to the lecturer-supplied original `sample_unplanned.xml`, documented matching values, missing optional incident end date, and unverified From endpoint; updated QA matrix and issue progress. **No new Android source was generated** and no live API/map test has been claimed.
**Student next validation:** scroll Details to verify the From endpoint, inspect a Roadworks record and archive screenshots in GitHub.


## Entry 015 — 11 October 2026 (A74 + M8 Android Details evidence)
**Tool:** ChatGPT with connected private GitHub source files.
**Student contribution:** Three Pixel 9 Pro emulator screenshots showing the A74(M) incident's previously hidden From location and the full details of selected M8 Woodside Roadworks record.
**AI contribution:** Retrieved official lecturer-supplied original DATEX II XML samples from GitHub, compared screenshot values with the source (including dates, delays, direction and coordinates), authored the M8 test evidence report and updated the prior A74 report, QA matrix, development notes and GitHub milestone evidence. No Android Java/XML application code was generated in this entry.
**Validation boundaries:** Comparisons cover two selected historical fixture records and observed screenshots only. Remaining emulator regression tests and live API not performed. Screenshot binary evidence still needs permanent upload.

## Entry 016 — 26 October 2026 (fast-track rubric readiness)
**Tool:** ChatGPT + connected GitHub.
**Exact student's prompt:** "нужно закончить как можно скорее"
**AI contribution:** Reviewed current project Java, parser, XML, repository, navigation, build configuration, rubric tracker and GitHub Issues. Added three Android instrumentation regression cases for malformed/truncated XML, multiple DATEX situation records and missing optional values; revised README verification summary and prioritised P0 rubric acceptance in the existing PROJECT_BOARD.md; updated QA matrix. No user secret was retrieved or placed in GitHub.
**Validation:** Source committed as `4591b68`; tests are **not yet executed on Android emulator**. CI compilation, when green, only confirms build; student must run `TrafficXmlParserTest` and capture outcome. All previous confirmed results remain unchanged.

## Entry 017 — 26 October 2026 — Native premium UX implementation
**Student prompt (verbatim):** "Мне нравится а теперь давай реализуй это все в код"
**Tool:** ChatGPT with connected GitHub, plus the preceding AI-generated static UI reference board (not a real screenshot).
**GenAI work:** Generated and edited Android Java/XML files for portrait-bottom / landscape-rail tabs, a native navy/blue/amber/red design system, navigation vectors, an original Highland-road adaptive launcher, responsive home hero/stats, native card/ripple styling, colour-coded *actual* DATEX II delay values, styled Details, map-marker preview, transitions and refresh/hero microanimations. Also drafted the design implementation traceability / QA plan in `docs/UI_UX_IMPLEMENTATION_2026-10-26.md`.
**Student's responsibilities:** Examine the code, make/approve UX decisions, run Pixel 9 Pro tests in Android Studio, debug issues with or without AI assistance, compare visible fields against original XML, archive actual screenshots and decide on merging.
**Validation:** Github Actions CI build plus JVM tests verify code compiles on runs with `conclusion=success`. This does **not** verify visible appearance, orientation retention, maps, live DATEX II or on-device functionality. All new UX screenshots and tests are pending. No API keys were exposed.
**Academic restrictions:** Original Java/XML plus approved Google Maps SDK only, no WebViews or unauthorised traffic feed. The generated UI board contains fictional demo examples which were **not** implemented as live facts.

## Entry 018 — 17 November 2026 — User-delegated refresh UX reliability
**Exact student prompt:** "Так, какие задачи ты можешь взять на себя и начать их реализовывать?"
**Tool:** ChatGPT with connected GitHub.
**GenAI contributions:** Inspected the current draft UX branch, Repository/ViewModel/Fragment/UI sources and CI status. Fixed `TrafficRepository.refresh()` to announce a real in-progress state while preserving already-loaded source-labelled records; added indeterminate ProgressBars for Home and Incidents/Roadworks in both portrait and landscape; updated Home and TrafficListFragment so previous valid results remain displayed during a new fetch; added test instructions to QA Execution Matrix.
**Additional work under same student delegation:** Added four plain-JVM exact-road filtering regression cases in `TrafficSituationRoadSearchTest.java` (M8/M80, case-insensitive input, A74(M), and multi-record comment fallback). Updated final-sprint checklist and QA matrix. These new tests require GitHub Actions results before marking PASS.
**Code review:** Work placed on draft PR #14, without touching main or adding any API credentials. The student must review all changes and test them on Pixel 9 Pro.
**Verification:** Await latest GitHub Actions result, then manually run app and capture evidence. **No emulator pass, approved DATEX II live download, offline cache test or final Word submission is claimed.**
