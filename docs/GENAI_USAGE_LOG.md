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
