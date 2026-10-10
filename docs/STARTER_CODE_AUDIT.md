# Supplied skeletonCode.zip audit — 10 October 2026

The lecturer's ZIP contains **three starter files only**: `MainActivity.java`, `activity_main.xml`, `AndroidManifest.xml`. It is not a complete Gradle project. It should be used as an educational baseline, **not mistaken for a final answer**.

## Exactly what it does
- Uses `HttpURLConnection` in `new Thread(new Task(unplannedEventsURL)).start()` after a button click.
- Calls official UnplannedEvents endpoint: `https://datex2.trafficscotland.org/rest/2.3/publications/UnplannedEvents/Content.xml`.
- Sends Base64 of `clientId:clientKey` as `Authorization` request header.
- Downloads raw response text to a `String result`.
- Instantiates namespace-aware `XmlPullParser` and `StringReader(result)`, but **contains no parser body**.
- Uses `MainActivity.this.runOnUiThread(... rawDataDisplay.setText(result))` to display raw XML in a TextView.
- Manifest has `INTERNET` permission.

## Gaps / risky starter behaviour to fix
1. Has embedded `YOUR_ID_HERE` / `YOUR_KEY_HERE` placeholders: never commit real keys.
2. Only downloads **incidents**, does not obtain CurrentRoadworks.
3. Requires a button: zero automatic update marks unless changed.
4. `runOnUiThread` and ad-hoc anonymous Thread: **0 for threading improvement** if left unchanged.
5. `result.substring(result.indexOf("<?"))` can fail if the response is missing XML prolog or error/empty.
6. Error path `urlConnection.getErrorStream().toString()` may throw when no stream exists and does not read response body.
7. Response concatenation via `result = result + inputLine` is inefficient on large feeds (~405 roadworks in provided sample).
8. No HTTP timeouts, robust connection handling, offline cache, response validation, error UI, or retry strategy.
9. Handles no XML data models, collections, filtering, detail, icons, map, landscape layout or saved state.
10. Package name is a placeholder; the current temporary scaffold is also not yet compliant with required student-naming convention.

## Safe adaptation plan
1. Set Android Studio project name per spec, use **Java** and maintain XML resources.
2. Complete Internet permission, local key config, official feed endpoints and networking test **without publishing credentials**.
3. Extract XML parser into a focused Java class using only PullParser; validate on both supplied XML examples.
4. Build typed Java models, including nested locations / impacts / validity and optional fields.
5. Separate repository/network parsing from UI; use one Activity + Fragments + Android ViewModel/observables as allowed.
6. Replace UI update with an observable/state-driven alternative and correct background executor scheduling.
7. Build feature screens and evidence against official rubric.

Do not upload lecturer starter content verbatim to GitHub unnecessarily; implement documented original code or adapt it with full GenAI and academic disclosure.
