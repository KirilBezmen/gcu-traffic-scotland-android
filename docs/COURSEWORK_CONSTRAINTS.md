# Coursework constraints — DOs/DON'Ts and official submission

## Non-negotiable technology
- Android Studio, **Java for application logic** and XML Views; Android 8.0 (API 26) or later.
- Both Traffic Scotland DATEX II **Unplanned Events** and **Current Roadworks** XML streams.
- Use the provided feed URL(s) only; **using a different data feed earns 0 overall for coursework**.
- Use **XmlPullParser** on the **downloaded XML String inside the app**, not raw network stream; other parsers earn zero for XML-parsing criterion.
- Traffic Scotland website hyperlink only as attribution; no functionality by linking to website; **no HTML-based WebView functionality**.
- Google Maps API / free mapping is expressly allowed, but other external frameworks prohibited. Vet dependencies against this rule.

## Auth and privacy
- Request and maintain **DATEX II** access using the GCU university account through https://developer.trafficscotland.org/ (this is separate from Public Restricted API).
- The student obtained approved DATEX II access and observed authentic LIVE downloads on Pixel 9 Pro on 18 November 2026.
- Use the official DATEX II Developer Guide for Client ID/Client Key, both `Content.xml` URLs and HTTP Basic `Authorization: Basic base64(clientId:clientKey)`. Never substitute the Public Restricted API as the assessed feed.
- Never put real credentials in Java source, screenshots, chat, GitHub, build logs or APK resources accidentally. Use ignored local config and explain the realistic limitations of secrets in shipped mobile apps.
- A local sample fixture is only for development/tests; final app loads a fresh official feed or last successfully downloaded version when offline.

## Features and architecture
- Selectable summaries of both feed types with generalPublicComment.
- Details: impact, validity/start and end where available, location/direction/coordinates where available.
- Road search, distance filtering (fixed centre lat/long permitted), colour-coded delays, licensed situation-type icons, mapped items.
- Two distinct res/layout/activity_main.xml and res/layout-land/activity_main.xml; state preserved during rotation.
- For full architecture marks: **one Activity + Fragments + MVVM**.
- For full update marks: automatic startup and periodic refresh; for threading: replace starter runOnUiThread with alternative and improve code organisation; **do not use AsyncTask**.

## Deadlines and hand-in
- Detailed coursework spec: **23 November 2026, 17:00**.
- Separate generic assessment brief: **23 November 2026, noon**; resolve with module team, use noon as internal safety.
- CW2 invigilated class test 30 November.

### Three required submission components
1. **Word** `Surname_GivenName_StudentId.docx`: GCU originality statement, GenAI disclosure + exact prompts, OneDrive narrated video link, GitHub repo/project link, GitHub APK link and *documented test strategy*.
2. **Narrated video** `Surname_GivenName_StudentId_Video.mp4` or .wmv, showing the Android app running, including portrait and landscape; video should also be accessible through OneDrive.
3. **External Examiner ZIP**: a folder `Surname_GivenName_StudentId` containing APK, video and a ZIP of the complete cleaned Android Studio project, then ZIP that folder. **ZIP only, not RAR/7z and no password.**

The app project should be created with naming convention `Surname_GivenName_StudentID`. The GitHub repository name itself can stay descriptive; rename Android Studio project's root metadata at setup.

## AI
- GenAI classification **G: Guided** — disclosure of all AI use for coding, fixes, tests, reports and prompts required. Record actual changes and verification honestly.
