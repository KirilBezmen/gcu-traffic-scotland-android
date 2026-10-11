# CW1 marking and requirement tracker — confirmed brief + rubric

Source documents supplied 10 October 2026: MPD_26_27_Traffic.docx (9 pages), MarksBreakdown_Traffic.pdf (4 pages), skeletonCode.zip, both XML samples. **All mark allocations below are criterion maxima, not achieved marks.**

## Total: 100 marks

| Category | Maximum | Definition of done / mark evidence | Status |
| --- | ---: | --- | --- |
| Functionality: incidents + roadworks and interactions | **42** | Working selectable lists, details, road/distance search, delay colours, licensed icons and multi-selection map | Not implemented |
| Portrait + landscape | **8** | Two different activity_main.xml arrangements; selection/display survives rotation | Two layouts committed; rotation not tested |
| PullParser + Java collections | **10** | XML String parsed into typed objects; optional impact/validity/location details in appropriate collections | Source committed; Android tests pending |
| Architecture | **10** | One Activity + Fragments + explicit MVVM to target full 10 | Foundation committed; integration/build not tested |
| Background threading + auto refresh | **10** | Replace starter runOnUiThread/Thread organisation (6); start-up + periodic refresh (4) | Not implemented |
| Documented testing | **15** | Feed/UI consistency (5), navigation (4), feature tests (4), offline (2) with actual and expected results and evidence | Test plan draft only |
| Narrated video | **5** | Show actual operation incl. portrait and landscape with voiceover | Not recorded |
| **TOTAL** | **100** | All rows are assessed | **0 verified for implementation** |

### Functionality subcriteria (rubric p2)
| Feature | Maximum / specific rubric wording | Implementation / evidence target |
| --- | --- | --- |
| Summary of incidents + roadworks | **4 + 4 = 8** | Type and generalPublicComment in independently selectable rows, accessible and readable |
| Selectable summary usability | **up to 2 + 2 = 4** | Select both types intuitively |
| Summary display formatting | **up to 2 + 2 = 4** | Consistent mobile formatting for both types |
| Selected situation details | **8** | 4 available fields extracted + 4 display formatting, include impact, validity, locations |
| Roadwork delay colour coding | **2** | Consistent colours with legend for delay type incl. missing values |
| Search by road | **4** | Case-insensitive useful query for both streams, useful empty state |
| Search by distance | **4** | Fixed coordinate origin explicitly allowed, accurate distance/filter handling, documented |
| Icons | **rubric discrepancy: heading says 6, explanatory line says up to 8** | 2 generic icons max 4, >=6 properly used icons described for max 8; licensed/attributed sources |
| Map | **8** | One/all display max 6; multiple user-selected road-search results max 8 |
| **Total as specified** | **42** | Note arithmetic conflict: with icon heading=6, features add to 40; with icon explanatory max=8, adds to 42. Clarify with lecturer. |

### Parsing / structures (rubric p3)
- PullParser with complete breakdown of XML including compound text = **6**.
- Suitable ArrayList (or similar) for situations = **2**.
- Suitable data structures for extra impact/locations etc. = **2**.

### Layout / architecture (rubric p3)
- Two separate **activity_main.xml** in res/layout and res/layout-land with materially different widget positions, preserving current state = **8**; duplicating the arrangement is capped at 3.
- Multiple Activities + Intents caps architecture at **3**.
- One Activity caps at **5**.
- One Activity + Fragments caps at **8**.
- **One Activity + Fragments + explicit MVVM** caps at **10**.

### Threading / updates (rubric p4)
- On app start + repeated refresh (nominal hourly, can shorten for demo) = **4**. Manual-only button refresh = 0. Startup-only = 2.
- Replace runOnUiThread and reorganise code using a better approach = **6**. Starter unchanged or AsyncTask = 0 for this category.

### Testing / video (rubric p4)
- Feed-vs-displayed result correspondence = 5, navigation = 4, feature functionality = 4, offline = 2. Actual/expected outcomes in a table with screenshots/data dumps.
- Full app demonstrated in portrait and landscape with suitable voiceover = 5. A minimal solution does not gain full testing/video credit.

## Required immediate order
1. Register Traffic Scotland DATEX II developer access **with GCU email**, request **Public Restricted API** (may take days); store client ID/key only in a local ignored config, not in repository.
2. Preserve official endpoint(s), especially `.../publications/UnplannedEvents/Content.xml`, and current roadworks stream provided by the service. Do **not** substitute another source.
3. Import or reconcile official starter with our scaffold. Project must follow naming pattern `Surname_GivenName_StudentID`, not the current temporary `TrafficScotlandExplorer` root name.
4. Implement and verify PullParser on sample XML **before** other features.
5. Add lists, full details, road/distance filters, map and at least six licensed icons.
6. Implement one Activity + Fragments + MVVM and two distinct activity_main layouts with rotation state preservation.
7. Replace starter threading + implement start and periodic refresh, locally retain last successful content for offline use.
8. Maintain test evidence, prompt log and prepare submission artifacts.

## Academic/format warnings
- The detailed coursework description states Monday **23 November 2026, 17:00**, as does the module page; an earlier generic assessment brief says noon. Treat noon as the safety target until the official contradiction is clarified.
- An **alternative feed causes zero for the entire coursework**. Non-PullParser parsing = zero in parsing criterion. WebView/HTML app does not earn functionality marks. Website link is only allowed as attribution.
- No external frameworks besides expressly allowed Google Maps/free mapping. Prefer platform and Android/AndroidX built-in components, confirm permitted libraries before adding dependencies.
- Missing client credentials must not be committed.
- Student work must be understandable, verified locally and **GenAI prompts fully disclosed**.
