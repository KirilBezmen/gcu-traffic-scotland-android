# CW1 development prompts — edited English renderings

**Assessment:** GCU MHI326841 Mobile Platform Development CW1. This page presents clear, complete, professionally edited English versions of development instructions originally discussed in Russian. They are organised by technical workstream and preserve the actual tasks requested. They are **not verbatim English transcripts** or invented historical chat messages. The original-language chronology is retained in [GENAI_USAGE_LOG.md](GENAI_USAGE_LOG.md).

## 01 — Project setup and documentation

> Set up an organised GitHub workspace for the Traffic Scotland Android coursework. Establish a clear repository structure, README, documentation folders, issue tracking and a progress checklist. Link development milestones to the assessment requirements and make it easy to distinguish completed work from tasks that still require testing. Keep the repository understandable for someone importing the project into Android Studio.

## 02 — Native Android architecture

> Develop the Android application in Java with XML layouts, following the coursework restrictions. Structure it around a single MainActivity, Fragments, a shared ViewModel and a Repository so that data handling is separated from the user interface. Provide navigation between Home, Incidents, Roadworks, Details and Map screens. Use different arrangements for portrait and landscape and preserve the relevant screen state when the orientation changes.

## 03 — DATEX II XML processing and data models

> Implement data processing for the two authorised Traffic Scotland DATEX II publications using Android XmlPullParser on downloaded XML strings. Extract situation descriptions, record types, validity, road names, direction, impact, delay information and available location coordinates. Store the results in appropriate typed Java model classes and collections. The parser should handle nested records, missing optional fields and unexpected XML elements without inventing values.

## 04 — Search, Nearby and Google Maps interaction

> Implement road-name search for both incident and roadworks data, ensuring that identifiers such as M8 and M80 are treated as different routes. Provide a Nearby filter based on the fixed Glasgow coordinate reference allowed by the coursework, with a selectable radius. Use Google Maps only to visualise the traffic data. Allow users to transfer multiple filtered situations to the map, select markers and open the corresponding record details.

## 05 — Interface refinement and usability

> Improve the native Java/XML interface so that it has a consistent, clear presentation. Use a Scottish road illustration, readable traffic cards, appropriate category icons, labelled delay indicators and useful loading or source-status messages. Keep the navigation visible, make the lists independently scrollable and replace a large refresh control with a familiar circular refresh icon. Check that the layouts remain practical on the emulator in both orientations.

## 06 — Debugging, refresh and regression checks

> Review the repository implementation and help resolve faults identified during Android Studio testing. Investigate parsing errors, search and navigation behaviour, map selection, rotation, loading states and network failures. Use a background worker for downloading and parsing, communicate state changes to the interface and include startup and scheduled refresh logic. Preserve the last successfully downloaded live data when the connection fails, clearly identifying cached information. Add regression tests and record which checks have actually been executed.

## 07 — Testing evidence and final report

> Review the completed application against the official coursework brief and marking rubric. Prepare a documented test strategy with each scenario, expected outcome, actual observed result and evidence limitations. Distinguish emulator testing from compilation and GitHub Actions results. Help prepare the Word report, screenshots, GitHub documentation and an English narration plan for the video demonstration. Identify outstanding checks and submission requirements without describing unperformed tests as successful.

## Attribution and responsibilities

**Student:** created and organised the GitHub repository, guided scope and design, supplied prompts and task decisions, configured authorised local Traffic Scotland and Maps API access, integrated the Android Studio project, investigated faults and carried out documented emulator checks.

**ChatGPT:** assisted substantially with Java/XML code and UI design, MVVM/Repository implementation, XML parser/models, map and filtering logic, debugging guidance, regression tests, GitHub workflow/documentation setup and preparation of the Word report.

Prompts are grouped edited English renderings, rather than a fictitious verbatim English message history. Review original-language chat records for exact-wording requirements.
