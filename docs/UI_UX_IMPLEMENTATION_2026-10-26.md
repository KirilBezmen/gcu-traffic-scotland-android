# Premium native UI/UX implementation — 26 October 2026

**Scope:** Android coursework Traffic Scotland Explorer, single Activity + Fragments + ViewModel, Java and native XML Views. **Working review branch:** `feat/premium-native-ux-2026-10-26` / draft PR #14. The AI-generated presentation board was a **design reference**, not real UI, source code or testing evidence.

## Design decisions

The student's selected reference board proposed a Scottish-road identity, navy/blue/amber/red palette, four destination tabs, a scenic hero, structured incidents/roadworks, detailed information hierarchy, multi-pin map and restrained microinteractions. This branch translates the feasible parts into native Android code **without** replacing the assessed XML parser/repository/MVVM architecture.

| Reference feature | Implementation | Source |
| --- | --- | --- |
| Premium palette, soft cards and incident/roadwork colours | Centralized colour and shape resources, native backgrounds, status badges | `res/values/colors.xml`, `res/drawable/bg_*.xml` |
| Home road/mountain graphic | Original XML VectorDrawable, not external or copyrighted photos | `res/drawable/hero_road_scene.xml` |
| Home real counts and source chip | Counts populated only from Repository snapshot; errors remain distinct; historic fixtures clearly labelled SAMPLE | `HomeFragment.java`, `fragment_home.xml` / `layout-land` |
| Modern 4-section navigation | Portrait bottom bar, *distinct* landscape left rail; checked-item tint and state | `activity_main.xml`, `layout-land/activity_main.xml`, `MainActivity.java` |
| Native incidents/roadworks cards | Parsed road/record type/description; status colours reflect only DATEX delaysType | `row_traffic_situation.xml`, `TrafficSituationAdapter.java` |
| Road search / Nearby | Existing actual road-ID matching and fixed Glasgow radius remain unchanged | `TrafficListFragment.java`, `TrafficViewModel.java` |
| Native Details layout | Larger hierarchy, road illustration, summary and highlighted *actual* field labels, native ScrollView | `fragment_traffic_details.xml`, `TrafficDetailsFragment.java` |
| Multiple markers / summary preview | Google Maps SDK already permitted; selected marker preview built from current parsed records; tapping opens Details | `TrafficMapFragment.java`, `fragment_map.xml` |
| Microinteractions | Native 180-ms faded Fragment transitions; 600-ms manually triggered refresh spin if animations enabled | `MainActivity.java`, `HomeFragment.java`, `TrafficListFragment.java`, `res/anim` |

## Fidelity and limitations

- The mockup's sample incident numbers (e.g. 12), descriptions, severity tiers, "12 minutes ago", predicted delays in minutes and promotional traffic-flow controls **are not true data**. They must never be displayed as real Traffic Scotland facts. App counts come from the XML and 4/405 lecturer fixtures are explicitly labelled historic SAMPLE in debug.
- Real incident **severity** is not inferred from an icon or event type. DATEX II `delaysType` categories are preserved as `Long delay`, `Delay reported`, `No delay` or `Delay unknown`. The project does not invent a 10–20 minute estimate.
- The original scenic VectorDrawable avoids third-party image licensing and packaging a photographic incident that has no basis in the XML. The generated board should not be included as verification of a running Android application.
- DATEX II access was refused on 26 October 2026. This branch remains compatible with original lecturer-provided DATEX II XML samples while the student and tutor clarify approved submission requirements. Do not substitute another API feed without explicit approval.
- Map tiles are intentionally unavailable until a *separate, locally configured restricted Maps SDK key* is supplied; map source and selected points must be checked in a real emulator.
- All source changes in this PR are GenAI-assisted; the student must review code, run tests, make design decisions and document observed (not assumed) outcomes.

## Required acceptance testing — NOT RUN unless verified by student

| ID | User action | Expected observation | Current evidence |
| --- | --- | --- | --- |
| UX-REDESIGN-01 | Cold launch Pixel 9 Pro portrait | Home visual, actual SAMPLE counts 4/405, source explicitly SAMPLE | NOT RUN |
| UX-REDESIGN-02 | Open Incidents then Roadworks | Bottom tab selected state, native cards show actual XML comments | NOT RUN |
| UX-REDESIGN-03 | Tap A74(M) / M8 | Full Details displays record-specific type, dates, direction, from/to coordinates | NOT RUN on new UI (prior version passed) |
| UX-REDESIGN-04 | Search M8 then M80 | Non-substring road-ID matching, results correct | NOT RUN |
| UX-REDESIGN-05 | Toggle Nearby / drag radius | Radius label and list update with fixed Glasgow reference | NOT RUN |
| UX-REDESIGN-06 | Rotate from search, list and Details | Distinct landscape rail, retained filters/back-stack/record | NOT RUN |
| UX-REDESIGN-07 | Tap refresh | One short clockwise animation; real repository refresh invoked | NOT RUN |
| UX-REDESIGN-08 | Configure restricted Maps key and map selected list results | Tiles and multi-selection markers; marker tap reveals real summary and opens Details | BLOCKED: local Maps key |
| UX-REDESIGN-09 | No Maps key | Helpful native message, no crash | NOT RUN |
| UX-REDESIGN-10 | Enable airplane mode after genuine live download | Stale cache labelled CACHED, no fabricated LIVE; fresh-install offline gives ERROR | BLOCKED: DATEX approval |

Record Pixel 9 Pro Android API level, revision SHA, date, observed actual result, screenshot/log path and PASS/FAIL in `docs/QA_EXECUTION_MATRIX.md`. **GitHub CI compile passing is not proof that a screen has been tested on-device.** Do not close M3/M4/M6 before evidence is recorded.

## Next steps before merging

1. Check GitHub Actions for PR #14.
2. On Windows: checkout `feat/premium-native-ux-2026-10-26`, Gradle Sync and run Pixel 9 Pro. Execute `TrafficXmlParserTest`, `OfficialXmlFixturesTest`, `NavigationChromeTest` manually.
3. Capture real portrait and landscape screenshots into `docs/evidence/screenshots/` (no API keys), document actual/expected results, and report layout or crashes for corrections.
4. Reconcile UI with official rubric: six applicable icons, delays semantics, map selected result coverage, testing and narrator video.
