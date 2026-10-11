# Roadworks record D_159301 — source XML vs Android Details

**Date:** 11 October 2026, around 20:29 UK local time.  
**Device:** Pixel 9 Pro Android emulator (API 36).  
**Source:** `app/src/main/assets/sample_roadworks.xml`, first `situationRecord` `D_159301` in historical lecturer-provided DATEX II XML.  
**Evidence:** Two Android Studio screenshots in ChatGPT conversation: initial Roadworks Details and scrolled view. The image files are not yet present in GitHub evidence directory. Commit SHA of running APK has not been recorded.

## Outcome — selected record passes XML-to-UI comparison

| XML field | Expected value from `sample_roadworks.xml` | Observed on Android Details | Result |
| --- | --- | --- | --- |
| `situationRecord/@xsi:type` | `MaintenanceWorks` | `MaintenanceWorks` | MATCH |
| `situationRecord/@id` | `D_159301` | `D_159301` | MATCH |
| `roadMaintenanceType` | `roadworks` | `roadworks` | MATCH |
| `generalPublicComment` | `M8 WB Woodside - A804 Phoenix Rd - Closure` | Same text | MATCH |
| `validityStatus` | `definedByValidityTimeSpec` | Same text | MATCH |
| `overallStartTime` | `2023-07-22T00:00:00+01:00` | Same text | MATCH |
| `overallEndTime` | `2027-12-31T23:59:00+00:00` | Same text | MATCH |
| `situationRecordVersionTime` | `2026-06-25T17:43:07+01:00` | Same last-updated time | MATCH |
| `tpegDirection` | `westBound` | `westBound` | MATCH |
| `impact/delays/delaysType` | `longDelays` | `longDelays` | MATCH |
| `to` point name(s) | `M8`; `M8 (Jct 18 Off Slip to Entry A82)` | Shown after Location (to) | MATCH |
| `to` coordinates | `55.870380, -4.268265` (source latitude `55.87038`) | `55.870380, -4.268265` | MATCH after six-decimal formatting |
| `from` point name(s) | `M8`; `M8 (A81 Underpass to Jct 18 Off Slip)` | Shown after Location (from) | MATCH |
| `from` coordinates | `55.869923, -4.261365` | `55.869923, -4.261365` | MATCH |

**Road label:** App's `M8, A82, A81, A804` heading is a composite of road names/metadata and the comment. The original record's named point descriptors are focused on M8; A81/A82 occur in point descriptions and A804 in the public comment. This composite label is contextual rather than a single source element.

**Provenance:** The app explicitly says **Historic lecturer XML samples — NOT current traffic conditions**. Publication time in fixture is 2026-08-31, so this is *not* proof of current or live Traffic Scotland API access.

## Limits and remaining acceptance tests
- [x] First roadworks record shows correct type, public comment, validity range, delay category, direction and **both** locations with six-decimal coordinates.
- [ ] Verify delay **colour coding** (short vs long) in the roadworks *list*, not merely text on Details.
- [ ] Verify a record with absent `delaysType` is handled correctly.
- [ ] Verify changing record selection, filtered results, back navigation, 405-item scrolling, portrait/landscape and empty states.
- [ ] Run Android `NavigationChromeTest` (2 new cases) and archive screenshot/actual result.
- [ ] Archive source screenshots in `docs/evidence/screenshots/` and record the precise running source SHA.

**Conclusion:** This selected roadworks Details **passes its displayed-field comparison**. The broader M1/M2 acceptance and the rubric's colour-coded delay requirement are **not yet signed off**.
