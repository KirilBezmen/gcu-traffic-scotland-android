# Student-led work, decisions and assessment evidence

**Updated:** 18 November 2026 · **Project:** Traffic Scotland Explorer (GCU Mobile Platform Development CW1).

This page documents **what the student actually did**, based on project conversations, submitted emulator screenshots, and repository history. It does **not** reassign AI-authored Java/XML source or AI-written commits to the student.

## Product leadership and implementation involvement

| Contribution area | Student action / evidence | AI-assisted work alongside it |
| --- | --- | --- |
| Scope and rubric | Defined the goal of a polished, navigable native Android traffic app; prioritised First Class rubric requirements, source fidelity, real maps and offline handling | Translated priorities into architecture and implementation suggestions |
| UI/UX review | Chose original road/mountains/cones visual direction; reviewed old vs premium UI on Pixel 9 Pro, assessed card readability and navigation layout | Generated code for Java/XML layouts, original vector graphics, transition effects and styling |
| Official data access | Registered with Traffic Scotland, obtained credentials, escalated mistaken repeated-request warning through lecturer Mario Mata; received confirmation of provider-side issue | Gave guidance, interpreted screenshots/docs; did not own credentials or make the real access request |
| Integration on local computer | Added keys to ignored `local.properties`, configured Maps SDK for Android restrictions and debug certificate SHA-1, ran Gradle Sync and installed app | Implemented official DATEX endpoint selection and Basic auth code, supplied setup guidance |
| Debugging and code review | Observed actual app behaviour, compared incident/roadwork fields with XML, identified when apparently differing map screens came from selecting different markers; requested changes where needed | Reviewed code, investigated suspected issues and implemented fixes with GitHub assistance |
| Verification | Operated Android Studio emulator, ran initial parser tests and repeated hands-on flows across portrait/landscape, search, Nearby, Google Maps, LIVE and offline | Prepared automated regression tests, test descriptions, expected results and QA documentation drafts |

## Device verification directly performed by student

| Date | Student-observed result | Limits |
| --- | --- | --- |
| 11 Oct | 4/4 small XML parser Android tests and 2/2 full lecturer sample tests (4 incidents + 405 roadworks) | Earlier build; new Android tests not yet rerun |
| 18 Nov | A74(M) incident and M8 Woodside roadwork Details compared with original fixture fields and coordinates | Two representative records, not a full-field audit of all entries |
| 18 Nov | Roadworks search **M8 → 21** and **M80 → 11** on historical samples | Visual subset and route matching, not independently checking all 32 records |
| 18 Nov | Glasgow Nearby filtered **405 → 14 (5 km), 53 (25 km), 370 (200 km)** | UI counts / slider and representative source records; geographic audit of all 405 pending |
| 18 Nov | Landscape side rail, selected M8 record, Back navigation and **200 km/370 results** retained | Some landscape text is small; full accessibility audit pending |
| 18 Nov | Google map tiles, marker preview, Details screen, **11 selected M80 situations** displayed as a subset | No per-marker coordinate audit of all 11; one screenshot pair was from two different markers, not a bug |
| 18 Nov | Authorised official DATEX II **7 incidents / 421 roadworks**, then manual refreshed Roadworks **421 → 423** | Direct emulator observation; independent provider XML comparison still useful |
| 18 Nov | Student explicitly confirmed offline cache functioning | Screenshot of CACHED indicator / network-off proof not yet archived |

**Original screenshots:** Student supplied screenshots in the working conversation; those files are **not necessarily committed to GitHub**. Before hand-in, archive appropriately redacted screenshots in the Word report and evidence folder with date, Android API and test outcome.

## Contributions and credit

- **Student:** requirements decisions, design feedback, local implementation/integration steps, debugging observations, supervised checks, test execution and reporting, ownership of the final application and report.
- **ChatGPT / GenAI:** Java/XML code authoring, original visual assets, suggested architecture, some bug fixes, automated tests, draft documentation and review of results. Refer to [full AI prompt log](GENAI_USAGE_LOG.md).
- **Third parties:** Traffic Scotland provides authorised XML; Google Maps renders map tiles; university provided historic test XML.

The GitHub commit author shown by the platform reflects who/what authored or committed changes through GitHub. It is **not deliberately modified** to make AI assistance appear to be independent student code authoring. The student's own final Word report must independently review and accurately describe these contributions.

## Remaining student-owned sign-off

- Run full current Android instrumentation test suite; capture results.
- Screenshot CACHED/offline and explicit no-cache error; verify full scheduled hourly refresh or document its limits.
- Verify repository can build from a **clean Windows checkout** using the now-committed trusted Gradle Wrapper; capture actual Gradle output.
- Resolve safe APK distribution without publishing Traffic Scotland keys and prepare Word, narrated MP4, backup ZIP and links.
