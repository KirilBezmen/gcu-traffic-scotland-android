# CW1 final sprint — 17 to 23 November 2026

**Goal:** Highest available rubric score with defensible evidence. **No new cosmetic scope.** Work branch: `feat/premium-native-ux-2026-10-26` / draft PR #14; preserve working main until student validates.

**Deadline:** Monday 23 Nov 2026. Official sources disagree between **12:00** and **17:00** — until Mario Mata confirms, treat **12:00** as the deadline. Internal target: all artefacts uploaded on 22 Nov.

## Current verified / not verified
- VERIFIED from 11 Oct student device evidence: 4 representative parser tests, 2 full lecturer XML tests (4 incidents + 405 roadworks), the original A74(M)/M8 XML-to-details comparisons.
- VERIFIED by GitHub Actions on 26 Oct: draft PR #14 Java/XML compile and JVM tests; **not** an emulator/UI/live API verification.
- CODED but **NOT RUN** on the redesigned UI: Search, Nearby, selected map markers, error states, landscape retention and refresh indicators.
- CURRENT BLOCKER: DATEX II approval/actual live endpoint and safe local credential integration, plus separate Google Maps API key. **Public Restricted API is not automatically an authorised replacement**. No silent switch of feed/source.

## Work split
**AI-assisted code tasks:** compile/debug work branch, fix observed Java/XML issues, keep source/source-status labels accurate, create regression tests and expected outcomes, record GitHub changes and full AI disclosure.

**Student device/academic tasks:** check Traffic Scotland approval locally; configure keys in untracked `local.properties` **without sending them to ChatGPT or committing them**; run Pixel 9 Pro emulator tests; choose UX decisions; capture screenshots with SHA; record actual pass/fail; write final report in own voice, review AI-assisted wording, voice-record video, verify links and upload.

## Critical run order (do not skip)
1. **Checkout PR #14 branch**, Gradle Sync, launch Pixel 9 Pro. Snapshot the exact Git SHA from Git window. Screen: Home should show clearly labelled **historic SAMPLE**, 4 incidents and 405 roadworks in DEBUG without credentials.
2. Run `TrafficXmlParserTest` (now 7 tests including 3 previously unrun), `OfficialXmlFixturesTest` (2), `NavigationChromeTest` (2). Record exact test count, PASS/FAIL, date, AVD, SHA and screenshot. CI is not enough.
3. Inspect **Incidents -> A74(M) Details -> Back** and **Roadworks -> M8 Woodside Details -> Back**, compare visible type/comment/direction/validity/from/to with provided XML; do not convert absent dates into fabricated dates.
4. Search M8 then M80 (do not match M8 within M80). Toggle Nearby, adjust radius, verify visible count and location reference (fixed Glasgow point). Verify delay colours, 6+ meaningful icons and impact details.
5. Rotate Home, each list with active search and Nearby, then Details and Map. Confirm **different** landscape rail layout, state and Back preservation; screenshot each distinct layout.
6. If Google Maps key is locally configured, select **Show filtered results on map**, verify several markers and tapping a marker to open its XML-grounded Details. Without key, verify helpful no-key message (do not label map as PASS).
7. If **authorised DATEX II** access is available, configure official credentials and exact Roadworks URL in untracked `local.properties`. Check live source label, startup download, refresh and hourly scheduling. After first successful live download, turn off network and confirm CACHED label, then test fresh install without network -> ERROR. Capture no credential/header screenshots.
8. Update `docs/QA_EXECUTION_MATRIX.md` with **actual observed** results and screenshot paths for every test, plus defects and fixes.
9. Record **narrated MP4** showing 4 tabs, real XML source status, selected lists/details, search, nearby, delays, map with multiple selected, rotation, refresh, offline; never present lecturer samples as live.
10. Build distributable APK **without exposing API credentials**. Test installation, clean source clone/Gradle Sync; final Word summary (originality + truthful AI use and exact prompts + test strategy/results + source/APK/OneDrive MP4 links). Prepare examiner ZIP containing APK, MP4 and full cleaned project. Test links/incognito and actual GCULearn upload.

## Suggested evidence filenames
`01_home_portrait_sample.png`, `02_xml_test_results.png`, `03_incidents_a74_details.png`, `04_roadworks_m8_details.png`, `05_search_m8_m80.png`, `06_nearby_radius.png`, `07_map_multiple_markers.png`, `08_landscape_tabs.png`, `09_offline_cached.png`, `10_offline_no_cache.png`, `11_gradle_build.png`, `12_apk_install.png`.

For each screenshot: date/time, device/emulator, Android API, source state, exact commit, what is visible and what is NOT established. An asset may be reused in the Word document only if it demonstrates an actually executed scenario.

## Submission risk reminders
- Alternative unapproved feed may result in **0 overall** per assessed restrictions. Tutor must approve any fallback **in writing**.
- G-Guided: disclose AI-generated Java/XML, review/debug/test support and provide **actual** prompts. Do not claim AI was limited to visual mockups.
- `BuildConfig` credentials can be recovered from a built APK. Do not distribute secret-bearing builds. Confirm an approved solution with Mario where a working authenticated build is necessary.
- 15 marks for documented testing and 5 for narrated video are unavailable without genuine evidence; allocate time to those, not new animation.
