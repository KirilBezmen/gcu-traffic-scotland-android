# Verified full XML Android instrumentation tests — 11 October 2026

**Android Studio / Pixel 9 Pro AVD, API 36.** Student submitted a screenshot of the test runner reporting **2 passed / 2 tests, BUILD SUCCESSFUL in 8s**, `:app:connectedDebugAndroidTest`.

| Test method | Expected in full lecturer fixture | Observed result |
| --- | --- | --- |
| `originalIncidentsContainFourSituations` | 4 situations, required record ID/summary/start date, two locations and first coordinates | **PASS** |
| `originalRoadworksContain405Situations` | 405 situations, record validity/summary and coordinate coverage of >390 records | **PASS** |

**Screenshot evidence:** visible in 11 October 2026 ChatGPT conversation; raw image not yet uploaded to GitHub. Save it under `docs/evidence/screenshots/` for assessment.

**Source revision at runtime:** not visible in the screenshot; verify the local commit before claiming a particular SHA. Latest main before this documentation update was `85263b9cfad6a985877083c858baf0d09db74a11`.

**Scope:** these are the original *historical lecturer-supplied* XML examples, not live Traffic Scotland. Tests validate particular data-model assertions; they do not validate UI Details, navigation, Google Maps, network and offline modes.

**M1 remaining:** compare a selected incident and roadwork's `generalPublicComment`, impact, validity, location names and coordinates against the original XML and the app's Details; document expected versus actual and run remaining edge cases.
