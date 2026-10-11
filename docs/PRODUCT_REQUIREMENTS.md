# Traffic Scotland Explorer — End-to-end product and logic specification

**Basis:** GCU 2026/27 Mobile Platform Development CW1 detailed task, marking rubric, starter, lecture sample XMLs, and verified project code. **Implementation states** below distinguish source code written from functionality observed on Android. This is an independent coursework app, **not** an official Traffic Scotland product.

## 1. Constraints that override design
- Android Studio, **Java** for app logic, XML Views, **API 26+**, single Activity + Fragments + explicit MVVM.
- ONLY the specified authorised **Traffic Scotland DATEX II Unplanned Events / Current Roadworks** XML. Alternate feeds risk **zero overall coursework marks**. Historical lecturer fixtures for debug/test only, explicitly labelled.
- Request full XML **String** and parse that String with **XmlPullParser** — do not parse network InputStream directly, do not substitute DOM/SAX/Gson parsing.
- No HTML/WebView implementation and no unapproved external framework. **Google Maps** / permitted free map is an explicit exception. Every icon must be original or licensed, attributed where applicable.
- Updated data at start + periodically (nominally hourly); do not use starter's runOnUiThread or deprecated AsyncTask. Clearly handle no internet or no cache.
- Never commit Traffic Scotland credentials or unrestricted Maps key. An APK containing BuildConfig secrets is still extractable; address distribution security before submission.
- GenAI category **G — Guided**. Disclose how tools helped with code/tests/report and include actual prompts.

## 2. User journeys

### A. Enter Home
1. Show premium visual identity: **Scottish highway, traffic cones, triangular warning sign**, title and navigation.
2. Non-blocking initial state: **Loading traffic data…**.
3. Successful response: counters for both feeds + **source label** (LIVE if fetched, SAMPLE if lecturer fixtures, CACHED if last verified live feed).
4. Errors: helpful message, never claim stale historical XML describes today's traffic.
5. Home buttons navigate to Incidents, Roadworks and Map; default to a meaningful screen; refresh button triggers worker request.

### B. Browse situations
1. Incidents and Roadworks separately show each **situation** with record/type, actual \`generalPublicComment\`, road identifier where present, situation-specific icon, clear tap target.
2. Source text retained above lists; empty, loading, no-results and connection-error states differ.
3. Selecting one row opens that exact situation; Back returns to list and preserves query/scroll/selection across rotation as far as supported by ViewModel/saved instance state.
4. Never render missing XML values as invented facts; use a clear unavailable state.

### C. Detailed incident / roadwork
Display if present: stable ID, precise event subtype, full description, validity status and start/end, record creation/update, impact/delays, restricted/operational lanes, direction, from/to named locations and numeric lat/long. Use section headings, readable dates, and context labels. On an incident without \`overallEndTime\`, **do not invent an end time**.

### D. Search by road
Case-insensitive road identifier search (e.g. M8 vs M80 exact route boundaries); both feeds. Empty query returns all matching type, no-result shows helpful message. Do not confuse roads by arbitrary substring. Search result set may be selected together for map.

### E. Search by distance
Rubric permits **fixed latitude/longitude**: fixed **Glasgow city centre** (55.8642,-4.2518) is transparently shown. Adjustable 5–200 km radius using Haversine + distance to segment between from/to endpoints. If coordinates missing, exclude from radius matches and explain why when necessary. This is *distance from fixed Glasgow point*, **not device live GPS**. User can combine road and radius filters.

### F. Delay severity and icon semantics
Roadworks must have colour-coded delay/duration status and **text legend**. Use only information actually in XML (e.g. \`delaysType\`: delays / longDelays / veryLongDelays / missing). Confirm with lecturer whether 'short or long duration' means validity duration versus \`delaysType\` semantics; don't fabricate thresholds. Accessibility: icon + label, not colour only.
At least six correctly assigned visually different icons in meaningful data contexts (roadworks, closure, queue, lane restriction, bad weather, obstruction, generic incident). Original Android VectorDrawables are recorded in \`ICON_SOURCES.md\`. Showing seven files **is not evidence** that six types were tested in-app.

### G. Map and multi-selection
Map with geographically correct markers and segment paths only when full source coordinates available. From a road/nearby result list select **multiple situations**, open map, distinguish incidents vs roadworks. Tap marker info window to open details. Gracefully explain absent locally restricted Google Maps key or no geolocated matches. No guessed coordinates, no substitute traffic feeds.

### H. Orientation and usability
\`res/layout/activity_main.xml\`: top navigation and content. \`res/layout-land/activity_main.xml\`: materially different side rail. Confirm data, selected details, queries, distance radius and scroll/list context survive rotate; fix recreation bugs if observed. Test small screens, readable text/contrast, 48dp minimum touch controls, accessible content descriptions, and absence of clipped labels.

## 3. Technical lifecycle

\`\`\`text
Application launch
 -> MainActivity (fragment host) + AndroidViewModel
 -> TrafficRepository.start() [background Executor]
 -> if authorised live credentials valid:
       request BOTH official XML feeds → String/String
       parse independently with XmlPullParser
       validate required records
       publish immutable LiveData Snapshot
       atomically cache last good authorised pair
    else DEBUG without credentials:
       parse labelled historical lecturer fixtures [NEVER claim live]
    else:
       useful unavailable/error state
 -> UI observes Snapshot: Home counters, lists, details, map
 -> after ~60 minutes while application process lives:
       fetch again in background, retain usable snapshot on errors
 -> if internet lost with last successful LIVE pair:
       CACHED / stale label, never silently show unlabelled samples
\`\`\`

**Pending engineering review:** current source's local BuildConfig credentials are extractable from APK; investigate permitted secure approach with module leader. Also inspect network failure, partial downloads, cache consistency and location/refresh lifecycle. Tests are necessary to confirm code actually meets the diagram.

### Classes/source map
- \`MainActivity\`: one Activity / navigation.
- \`ui/HomeFragment\`, \`TrafficListFragment\`, \`TrafficDetailsFragment\`, \`TrafficMapFragment\`: native XML View screens.
- \`ui/TrafficViewModel\`: section, query, radius and selected map situation IDs.
- \`data/repository/TrafficRepository\`: background fetch, sample mode, last-good live cache, LiveData Snapshot.
- \`data/parser/TrafficXmlParser\`: XML String → DATEX situation records using PullParser.
- \`data/model/{TrafficSituation,TrafficRecord,TrafficLocation}\`: typed data and optional fields.
- \`util/TrafficDistance\`: fixed-centre km distance and polyline-segment handling.
- \`res/layout\`, \`res/layout-land\`, \`res/drawable\`, \`res/values\`: native views and original illustrative vectors.

## 4. Model and data contract
One \`TrafficSituation\` contains the feed category and **one or more** \`TrafficRecord\` elements. Records have optional impact/validity fields and typed locations. Both supplied fixture files contain **4 unplanned situations and 405 current roadworks**, per offline audit. Count is not a live-world claim. Preserve records with unknown optional elements; fail safely on malformed XML.
- \`Loading\` = pending background retrieval.
- \`LIVE\` = authorised feed pair parsed successfully.
- \`SAMPLE\` = provided historic fixtures for DEBUG testing, explicitly tagged.
- \`CACHED\` = retained last successful LIVE feed; show stale/last update if known.
- \`ERROR/NONE\` = no reliable data available, actionable message.
These are display states, not academic test result statuses.

## 5. Acceptance and release criteria
- **Unit/JVM:** road matching, distance calculations incl. crossing segment, missing coordinates.
- **Android instrumented:** 4 representative parser cases already passed 11 Oct (Pixel 9 Pro API 36); **new full original XML 4/405 test still NOT RUN on emulator**.
- **UI system:** screenshot + actual vs expected for all rubric functions, map markers, navigation, both orientations, offline handling.
- **Security:** no credentials in repo, logs or redistributed APK without permitted handling; authenticated endpoint proof redacted.
- **CI:** current code compiles and JVM tests passed at GitHub Actions on 11 Oct; CI is **not** emulator proof.
- **Presentation:** narrated MP4 showing real functioning Android app in both orientations; complete Word test strategy and GenAI appendix; GitHub project and APK links; External Examiner ZIP.
- A task is complete only after **actual verified acceptance**, not when generated code is committed.

## 6. Open risks (prioritised)
| Risk | Effect | Mitigation |
| --- | --- | --- |
| Traffic Scotland request still being reviewed | Live feeds not testable | Use historic fixtures for development, test live immediately upon approval |
| API key in distributed APK | Credential leakage | Local ignored configuration; consult lecturer before final APK release |
| Google Maps restricted key absent | Tiles/markers can't be demonstrated | Set up restricted key locally, verify emulator and billing limitations |
| Gradle wrapper incomplete in repo | Clean import may fail | Add matching standard wrapper; fresh-clone Android Studio check |
| Icons heading=6 vs notes max=8 | Exact allocation ambiguous | Seek written clarification; implement evidence for 6+ distinct types |
| Detailed 17:00 vs generic noon deadline | Late-submission risk | Internal noon submission target, confirm on GCULearn |
| UI or fragment state on rotation | Lose marks for distinct layouts/state | Instrumented manual/automated rotation tests |
| Historical sample data mistaken for live | Incorrect product claims | Visible DEBUG SAMPLE labels on every relevant screen |
