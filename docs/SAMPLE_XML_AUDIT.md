# Supplied Traffic Scotland DATEX II XML examples — inspection log

Read on **10 October 2026** from coursework attachments. These are samples for development, **not live feed claims**.

| XML file | Situations | Situation records | Types |
| --- | ---: | ---: | --- |
| `unplannedContentExample.xml` | **4** | **4** | 3 RoadOrCarriagewayOrLaneManagement, 1 AbnormalTraffic |
| `roadworksContentExample.xml` | **405** | **405** | 405 MaintenanceWorks |

## Important parsed properties
- XML SOAP envelope includes a DATEX II namespace (`http://datex2.eu/schema/2/2_0`).
- `situation/@id`, `situationRecord/@id` and `situationRecord/@xsi:type` identify records.
- `generalPublicComment > comment > values > value` gives summary text, but generic `value` also appears under many other paths. Parser **must track XML context**.
- `validity > validityTimeSpecification > overallStartTime` appears in all 409 situations across both files; `overallEndTime` appears for the **405 roadworks**, **not** the 4 incident sample situations.
- `impact` may contain `numberOfOperationalLanes`, `numberOfLanesRestricted` or `delays > delaysType`. Some roadwork records have no delaysType at all.
- Roadworks sample recorded `delaysType` counts: **130 delays**, **26 longDelays**, **1 veryLongDelays**, others absent.
- Both data sets include `groupOfLocations` with `to` and `from` points and nested `pointCoordinates`: **8 incident point coordinate nodes**, **810 roadworks point coordinate nodes**.
- Location names appear under `name/descriptor/values/value` and `ilc` paths. Do not assume identical nesting between incidence and roadworks record types.
- Direction and road name can be inferred from appropriate location descriptors and/or descriptions, but must handle missing or inconsistent data and not misparse generic value tags.
- Dynamic feeds can introduce unknown tags. Ignore safely, do not crash.

## Test design
- Confirm **4** and **405** situation objects when parsing the full provided samples.
- Verify known incident summary, roadwork description, road name, dates, impact/delays and both coordinates against specific source XML nodes.
- Missing end date and missing delaysType should not cause crash.
- Test road filtering on M8, A9, etc., using **actual parsed road identifiers** rather than brittle substring matches.
- Test large-file parse performance; data must not freeze UI.
- Document parsing from downloaded String via `XmlPullParser`; fixture loading is test-only.

Fixtures are kept in the student's coursework materials. Their content is not automatically committed to this GitHub repository.
