# Traffic Scotland Explorer CW1 — narrated MP4 recording script

**Status:** prepared script, **not** a recorded video. 19 November 2026. Aim for a clear 4–6 minute MP4 with the **actual Pixel 9 Pro emulator** and English voice-over. Record ONLY what the running build actually shows, and use the true provenance label. If using historical lecturer fixtures, explicitly say **sample data**, never live.

## Pre-recording
- Open the **approved** Android Studio build, connect emulator, keep device audio/microphone working; hide email, credentials, Google Cloud credential screen, local.properties, BuildConfig secrets and notifications.
- Verify actual screenshot source label: “Official Traffic Scotland data” for authorised LIVE (only if genuinely displayed), or “Historic lecturer XML samples — NOT current traffic conditions” for DEBUG samples.
- Check Maps API key locally; if maps are unavailable, do not falsely narrate successful marker demo.
- Keep output portrait first, landscape near the end; no rapid jump cuts needed. Do not show any made-up information.

## Shot 1 — Home and purpose (about 35 s)
**Action:** show Home, source label, two counters and four navigation destinations.

**Voiceover:** “Hello, this is Traffic Scotland Explorer, my native Android application developed for Glasgow Caledonian University's Mobile Platform Development coursework. The app retrieves and processes Traffic Scotland DATEX II information for incidents and roadworks. Its interface is implemented using Java and XML, with a single Activity, Fragments, a shared ViewModel and a Repository. At the moment, the data status on screen is [read the actual label aloud].”

## Shot 2 — Incidents list and details (about 45 s)
**Action:** tap Incidents, scroll two or three cards, choose a real incident; show fields actually present, press Back.

**Voiceover:** “The Incidents screen displays selectable summaries with road references, event types and public descriptions extracted from the Traffic Scotland XML. Opening a situation reveals additional source-supplied information such as the record type, validity, direction and locations when those fields are available. Missing optional values are not invented.”

## Shot 3 — Roadworks, delay indicators and details (about 40 s)
**Action:** switch Roadworks, point to traffic-colour badge, open M8 example if present; Back.

**Voiceover:** “Roadworks are presented separately, using the same native list-and-details approach. Where the provider reports a delay category, the application displays a corresponding colour and readable label. These labels come from the parsed data rather than a generated severity estimate.”

## Shot 4 — Road name search (about 45 s)
**Action:** demonstrate M8, then M80, with Nearby OFF to isolate road search. Read actual result counts without fixing them in the script. If current LIVE data has different counts, that is expected.

**Voiceover:** “Users can search for situations associated with a specific road. The road identifier matching is designed to distinguish M8 from M80 rather than return a broad substring match. The number of results changes as the source data changes.”

## Shot 5 — Nearby filtering (about 35 s)
**Action:** clear search, tick Nearby, choose two clearly visible distances such as 5 and 25 kilometres and show both counts.

**Voiceover:** “Nearby filtering uses a declared fixed coordinate reference in Glasgow, which is explicitly permitted by the assignment. The selected distance controls which situations are displayed. The selected source and radius remain visible to the user.”

## Shot 6 — Multi-selection Google Maps (about 55 s)
**Action:** filter to a road with multiple geolocated results; click **View results on map**, not the bottom Map tab; show “N selected situations” label and visible markers. Tap a marker, show source-derived summary and open corresponding Details; press Back. Optionally Show all.

**Voiceover:** “The Map screen uses the Google Maps SDK exclusively for visualisation. Selecting ‘View results on map’ sends the matching situation identifiers to shared application state, so the map can display multiple user-selected situations rather than all records. The pins use available XML coordinates; where a situation has two points, the app may also show the corresponding segment.”

## Shot 7 — Rotation, startup/refresh and no-network (about 70 s)
**Action:** rotate to landscape while viewing an active list or Details; show distinct left-side navigation rail. Rotate back. Tap Refresh while network ON and point to loading/source labels. For offline, disable both emulator Wi-Fi and mobile data **only after a successful LIVE retrieval**, press Refresh and show CACHED result while records remain available. Avoid claiming scheduled hourly refresh unless actually observed; narrate the implementation design instead.

**Voiceover:** “Portrait and landscape are intentionally different layouts. Landscape uses a navigation rail and makes better use of available horizontal space. The current selection and filters are intended to remain available across orientation changes. Network operations run in an ExecutorService and are communicated to the interface using LiveData. The Repository schedules reloading at startup and at hourly intervals while active. With the network unavailable after a previous successful download, the app displays the last successfully retrieved data as cached rather than claiming it is live.”

## Shot 8 — Ending (about 15 s)
**Action:** Home, source state, no private information.

**Voiceover:** “This demonstration covers the native interface, incident and roadworks browsing, source-based details, road and distance searches, mapped selections, orientation behaviour, and the application's data-state handling. Thank you for watching.”

## Recording and integrity checklist
- [ ] Actual MP4 recorded with **suitable English voiceover**, not just a slide deck or screen captures.
- [ ] Both orientations are shown; incident and roadworks selections both work.
- [ ] Road search, Nearby radius, delay colours and selected-results map are legible.
- [ ] Offline cache shown only if tested and screenshot/audio correspond; if not, state limitation honestly.
- [ ] Start-up and hourly refresh differentiated; not claiming a 60-minute observation on a 5-minute recording.
- [ ] Video output named `Bezmen_Kiril_s2338746_Video.mp4`.
- [ ] Hosted on OneDrive with the link checked from a signed-out/private browser; link added to report.
- [ ] Source state always reported accurately; historical fixture screenshots never passed off as current LIVE.
