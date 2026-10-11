# Official CW1 rubric — 100-mark acceptance checklist

**Source:** GCU Mobile Platform Development (MHI326841) official CW1 detailed rubric and coursework constraints provided by the student. The maxima are **possible marks, not scores achieved**. Aiming for 100% cannot guarantee a particular grade.

## Rubric summary
| Rubric category | Available marks | Evidence required | Issue |
| --- | ---: | --- | --- |
| Requested features | **42** | Browse, details, road & distance searches, delays, icons and multi-selected map | [#3](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/3), [#5](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/5) |
| Distinct portrait + landscape | **8** | Separate \`activity_main.xml\` positions plus retained displayed state | [#4](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/4) |
| PullParser + data structures | **10** | PullParser 6 + situations collection 2 + nested object collections 2 | [#2](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/2), [#13](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/13) |
| Architecture | **10** | One Activity, Fragments, explicit MVVM | [#4](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/4) |
| Threading + automatic refresh | **10** | No runOnUiThread/AsyncTask (6), startup + periodic fetch (4) | [#6](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/6) |
| Documented testing | **15** | Feed-vs-view 5, navigation 4, feature cases 4, no internet 2 | [#7](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/7) |
| Video demo | **5** | Narrated functional portrait + landscape video | [#7](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/7) |
| **TOTAL** | **100** | Evidence + deliverables, not just code | |

## Detailed feature acceptance (42 marks)

| Feature | Rubric maximum | Checklist | Actual device verification |
| --- | ---: | --- | --- |
| Incident summaries | **4** | Type + \`generalPublicComment\`; selectable, user-friendly formatting | NOT RUN on newest UI |
| Roadwork summaries | **4** | Same independent type + description, selectable and readable | NOT RUN |
| Detailed selected situation | **8** | Available impact/validity/locations 4 + display 4; show missing honestly | NOT RUN |
| Roadwork colour-coded delay/duration | **2** | Distinguish short/long suitably from actual data; legend | NOT RUN; semantics clarify |
| Search by road name | **4** | Intuitive, complete, case-insensitive, M8 ≠ M80 | NOT RUN |
| Near situations / distance | **4** | Radius from fixed position allowed; accurate results and empty state | JVM test source/CI; UI NOT RUN |
| Icons by type | **6 in heading, up to 8 in explanatory text** | At least 6 meaningfully distinct and appropriately used icons; sources/licence documented | 7 vector files, display proof NOT RUN |
| Map | **8** | Multiple **user-selected** situations earns highest level; source coords | Code compiled; map key/device NOT RUN |

**Important ambiguity:** The rubric heading lists the icon feature as 6 marks while its explanation says up to 8 for six icons. Using **8** gives 42 in the sum of the top-level feature maxima (4+4+8+2+4+4+8+8=42); using **6** gives 40. **Get Mario Mata's confirmation** rather than assuming the inconsistency is resolved.

**Do not double count summary mark commentary:** The usability (2+2) and display formatting (2+2) text elaborates the **4+4 summary marks**, not an extra 8-mark component. Previous tracker presentation of separate 4+4 extras should not be treated as a separate marking category.

## Hard failure / cap conditions
- [ ] Only authorised Traffic Scotland DATEX II feeds used; **alternative feed can give 0 overall**.
- [ ] Java/XML Views, no HTML WebView.
- [ ] Parsing downloaded XML **String** using \`XmlPullParser\`; non-PullParser approach scores 0 for parser item.
- [ ] No \`AsyncTask\` and no reliance on unchanged starter threading for marks.
- [ ] Distinct portrait/landscape \`activity_main.xml\`, **visible state retained**.
- [ ] Full architecture target requires one Activity + Fragments + **explicit MVVM** (other patterns cap marks).
- [ ] External libraries comply with brief; Google Maps permitted.
- [ ] GenAI disclosure and full prompt appendix supplied.

## Testing 15-mark requirement
- [ ] **5 marks**: compare upstream XML with corresponding cards, selected details and map fields, screenshot/data dump per field.
- [ ] **4 marks**: navigation and selectable flow both streams, screen back, portrait/landscape.
- [ ] **4 marks**: ALL required functionality: road filter, nearby, delays, icons, map, refresh.
- [ ] **2 marks**: no internet (with and without last good cache), no crash, honest source label.
- [ ] For every check: ID, steps, expected outcome, **actual observed**, status, date, environment, code SHA, evidence file.

## What is already actually verified (11 October)
- [x] Older GitHub navigation shell launched on Pixel 9 Pro API 36.
- [x] **Four small representative XML Android instrumentation tests** completed: **4/4 passed** on Pixel 9 Pro API36.
- [x] Green GitHub Actions compilation/JVM tests for newer source code.
- [ ] **Full lecturer XML (4 incidents + 405 roadworks) Android tests not yet confirmed.**
- [ ] **Current Home/list/detail/road/nearby/map build not yet student-device verified.**
- [ ] **Live authenticated feed / map-key / offline / orientation tests not yet confirmed.**

## Submission and time gate
- CW1 **70% of module** (CW2 30%, 90-minute invigilated test on 30 Nov).
- Official detailed CW1 deadline **23 Nov 2026, 17:00**; earlier generic brief says noon. Aim to finish upload before **12:00** until confirmed.
- [ ] Word \`Surname_GivenName_StudentId.docx\`: originality statement, GenAI how-used and actual prompts, OneDrive narrated MP4 link, project/APK GitHub links, documented testing strategy.
- [ ] Narrated video MP4 showing required functionality including both orientations.
- [ ] Valid GitHub source + APK and fresh clone import.
- [ ] External Examiner ZIP containing APK, narrated video and cleaned project ZIP; ZIP not RAR.
- [ ] Verify every link/permission and submissions on GCULearn.

**Related:** [Board](PROJECT_BOARD.md) · [End-to-end functional spec](PRODUCT_REQUIREMENTS.md) · [QA execution matrix](QA_EXECUTION_MATRIX.md).
