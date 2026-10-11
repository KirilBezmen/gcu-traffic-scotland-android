# A74(M) incident — XML-to-Android Details comparison

**Test date:** 11 October 2026 (student Android Studio screenshots at ~20:26 UK time)  
**Device:** Pixel 9 Pro emulator, API 36  
**Source:** historical lecturer fixture `app/src/main/assets/sample_unplanned.xml` (not live data)  
**UI navigation observed:** Home → Incidents → first incident A74(M) → Details  
**Evidence:** Two screenshots provided in the ChatGPT conversation. The PNG files are **not yet committed** to `docs/evidence/screenshots/`. Source commit of the exact local APK is not visible in screenshot; do not assume it matches any GitHub SHA.

## Observed UI behaviour
- Home displays one primary tab navigation, illustrated road/cones/warning hero, **4 incidents · 405 roadworks**, and explicit historic-sample warning.
- Incident Details is reachable by selecting A74(M) and shows field values from the first incident in the provided XML.
- The label makes clear that examples are not current traffic conditions.

## Field-by-field evidence

| Field | Expected in raw supplied XML | Observed on Details screenshot | Assessment |
| --- | --- | --- | --- |
| Road | `A74(M)` under location `ilc` | `A74(M)` header | MATCH |
| Record type | `RoadOrCarriagewayOrLaneManagement` (xsi:type) | same technical type under road | MATCH |
| Situation record ID | `I_20260810_248` | `I_20260810_248` | MATCH |
| Event subtype | `roadClosed` | `roadClosed` | MATCH |
| `generalPublicComment` | `The A74M(S) junction 19 (Ecclefechan) off slip is closed to allow for essential roadworks.` | Same text visible | MATCH |
| Validity | `definedByValidityTimeSpec` | `definedByValidityTimeSpec` | MATCH |
| Start | `2026-08-10T11:37:42+01:00` | same string | MATCH |
| Last updated | `2026-08-24T18:25:33+01:00` | same string | MATCH |
| Direction | `southBound` | `southBound` | MATCH |
| Operational lanes | `0` | `0` | MATCH |
| To location | to / J19 / A74(M) / A74(M) J19 Eaglesfield | Location (to): `J19; A74(M) J19 Eaglesfield` | MATCH for visible human-readable location; selected name fields |
| To coordinates | latitude `55.065432`, longitude `-3.273135` | `55.065432, -3.273135` | MATCH |
| Incident end date | `overallEndTime` **absent** from sample incident | No End line on visible details | EXPECTED: not invented |
| From location | from / J18 / A74(M) J18 Ecclefechan | Below screenshot viewport | **NOT VERIFIED** |
| From coordinates | `55.103477, -3.349595` | Below screenshot viewport | **NOT VERIFIED** |

## Outstanding criteria / next proof
- [ ] Scroll incident Details lower and capture **Location (from)** and **Coordinates (from)**; confirm the expected J18 location and pair `55.103477, -3.349595`.
- [ ] Open at least one Roadworks situation, including end date, impact/delay and two locations; compare UI against `sample_roadworks.xml`.
- [ ] Confirm large/long details work in both portrait and landscape, and Back returns to the correct list/query.
- [ ] Run `NavigationChromeTest` on the Pixel 9 Pro, and capture original screenshots for permanent GitHub evidence.
- [ ] Record actual git SHA from local Android Studio after run.

**Outcome:** A74(M) **visible field audit PASSED**; full M1/M2 acceptance remains **PARTIAL** pending From endpoint, Roadworks and other user-journey tests. This screenshot is *not* evidence of authenticated LIVE feed or Google Maps.
