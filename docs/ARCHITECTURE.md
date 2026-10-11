# Architecture — rubric-led implementation and outstanding validation

**Rubric target: 10/10 architecture marks.** Required path is explicitly **Single Activity + Fragments + MVVM**. Prior multiple-Activities proposal is superseded. No third-party frameworks beyond allowed mapping library; use standard Android / permitted AndroidX components.

## Implemented-source target flow (newest device verification pending)
```text
Traffic Scotland official authenticated DATEX II XML endpoints
   -> background HTTP fetch (on start + periodic)
   -> downloaded XML String
   -> XmlPullParser (not DOM, Gson, SAX, or parsing directly from network stream)
   -> Java model objects: Situation, Impact, Validity, LocationPoint(s)
   -> Repository with last-successful cache and load/error state
   -> ViewModel holds list, selection, query, status
   -> MainActivity (fragment host)
       -> List / Search Fragment
       -> Details Fragment
       -> Map Fragment (permitted Google Maps / free mapping)
```

## Resources and orientation
- `res/layout/activity_main.xml`: portrait main Activity host and navigation arrangement.
- `res/layout-land/activity_main.xml`: materially different landscape (e.g. list and details side-by-side) using same state.
- Keep selected incident, filters, feed data and scroll/display context across rotation using ViewModel and appropriate state restoration.
- Access to the map should support **multiple selected situations**, not only a single marker, to target the full rubric 8 for maps.

## Background / update
- Do **not** keep starter `runOnUiThread` nor adopt deprecated `AsyncTask`. Design callback/observable updates from a background `ExecutorService` (or a permitted equivalent).
- On app start and every hour (shorter during demonstration) check official feeds; preserve most recent good data on network loss, with clearly labelled stale state.
- Never commit client credentials. Store in ignored local settings; ensure build instructions explain local configuration.
- Only platform services, AndroidX/Jetpack and explicitly permitted map service; check lecturer's restriction before any third-party dependencies.

## Domain model (provisional)
`TrafficSituation`: record id, feed category, situation subtype, public comment, dates/status, road name(s), direction, impact/delays, location(s). Extra nested Java model classes as data merits. Different situation types have optional fields.

## Current test gate
Parser/models, repository, fragments, map integration and different XML navigation layouts exist in source and compiled via CI. Four representative Android XML tests passed on 11 Oct. The full 4/405 original-fixture tests, current UI navigation/rotation, authenticated live API and Google Maps drawing are **still not demonstrated on the user's emulator**. See [product spec](PRODUCT_REQUIREMENTS.md) and [QA matrix](QA_EXECUTION_MATRIX.md).
