# Traffic Scotland Explorer — UI/functional design specification (11 Oct 2026)

## Product objective

A readable **native Java/XML Android app**, built for GCU Mobile Platform Development CW1, using the specified authorised **Traffic Scotland DATEX II XML**. A visually attractive interface must never substitute unimplemented requirements.

## Concept

**Home:** original illustrated Scottish mountain highway with **traffic cones and warning sign**, title *Roads in focus* and live/fixture data status; use the **single MainActivity navigation bar** (no duplicate Home actions). No copied Traffic Scotland identity. This illustration is drawn from self-authored Android VectorDrawable shapes.

**Visual language:** navy #0B2D5B, blue #2563EB, off-white #F3F6FA; semantic amber for reported delay, red for long delay, muted when unknown. Large legible text, high-contrast cards, 48dp navigation touch targets, colour always paired with text.

**Portrait:** title + horizontal four-section navigation; Home is fixed-height, and list headers/filters are fixed above the independently scrolling ListView.
**Landscape:** navy left navigation rail; Home has side-by-side hero/summary panels and list fragments place fixed filters to the left of the scrollable cards. Must preserve section and selected details on rotation (requires device check).

## Screens and data flow

| Destination | Content | Required interactions | Implementation |
| --- | --- | --- | --- |
| Home | Illustrated road, counts, clear data provenance | Use global tabs, 48dp circular-arrow refresh | Revised fixed Home source committed; Android UI test pending |
| Incidents | Feed record type, road ID(s), generalPublicComment, original icon | Search road exactly, select -> details | Source committed; Android UI test pending |
| Roadworks | Same plus colour-coded delay-type label | Search road, select -> details | Source committed; Android UI test pending |
| Details | Record fields, subtype, validity, impact, lane restrictions, direction, from/to coords | Back navigation | Source committed; Android UI test pending |
| Map | Multiple geolocated events; from/to segments | Select a filtered road result set, marker tap -> details | **Google Maps source committed, needs local Maps API key + device test** |

### Information quality and ethics
- When data are lecturer samples: show **Historic lecturer XML samples — NOT current traffic conditions** on each screen. Never display them as live.
- When credentials approved and official feeds succeed: show **LIVE**. Download on background worker.
- When offline and cached genuine response exists: label **CACHED** and show stale message. When no cache: show error/empty, never invent data.
- Never claim severity, duration or completion from missing fields. Delay labels derive strictly from actual `delaysType` values; if absent show `Delay unknown`.
- Sample XML may contain situations no longer current; figures on home are record counts, not an alert of current conditions.
- Third-party icons only if license allows it and source documented; current cone + warning vector paths are original and require no third-party license.

## Technical acceptance checklist

- [x] One Activity + Fragments + AndroidViewModel + repository **source created**
- [x] Java PullParser parses String with typed record/location models
- [x] Official sample fixtures bundled for **debug-time** UI verification (4 incidents, 405 roadworks)
- [x] First four representative parser tests passed **11 Oct 2026, API 36**
- [ ] Full supplied XML instrumentation tests executed and screenshot archived
- [ ] UI app rebuilt and device-verified after these changes
- [ ] Road filtering, selection/details, and rotated display verified on device
- [ ] Approved live DATEX II endpoints and credentials verified (API access pending)
- [x] Google Maps SDK integration, road-result multi-selection and fixed-point nearby-radius filter **source written**; live map requires local Google Maps API key and device testing
- [x] Seven original vector icon drawables and type mapping **source written**; adequate use must still be proved in screenshots
- [ ] Thorough test plan evidence, offline/refresh tests, screen recording, APK, Word/AI disclosures

## Rubric protections
Official feed only. Parse **downloaded String** with XmlPullParser. No WebView. Android Java/XML. Single Activity + Fragments + MVVM. Avoid deprecated AsyncTask/runOnUiThread. Auto-update startup and hourly while app is active. Offline shows last verified LIVE cached data (not old demo samples).

## Implementation updates
- **Google Maps SDK 20.0.0**, official Google Maps Android API. Map cannot display tiles without separately supplied/restricted `MAPS_API_KEY`. AndroidManifest and Gradle read key from untracked local.properties.
- Selecting **Show filtered results on map** from a road/nearby list records multiple situation IDs in ViewModel. Google Map displays markers and polylines for available to/from endpoints; tap info window for details. Label distinguishes lecturer samples vs actual feed.
- Seven **original vector icons** and provenance documented in [ICON_SOURCES.md](ICON_SOURCES.md). No external third-party icon library.

## 11 October follow-up — UI refinement
See [UI_REFINEMENT_2026-10-11.md](UI_REFINEMENT_2026-10-11.md). The duplicate Home navigation was removed; Refresh is a vector icon; full-page ScrollView was removed from Home, and only the card ListView scrolls under fixed header/filters. An independent compact landscape list layout is committed. New on-device UI tests are **NOT RUN** until the student executes them.
