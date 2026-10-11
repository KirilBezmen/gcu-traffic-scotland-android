# Traffic Scotland Explorer

**Glasgow Caledonian University · Mobile Platform Development (MHI326841) · CW1 · 2026/27**

![Android](https://img.shields.io/badge/Android-native-3DDC84?logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-coursework-007396)
![XML](https://img.shields.io/badge/UI-XML%20Views-546E7A)
![Assessment](https://img.shields.io/badge/CW1-70%25%20of%20module-4B5563)
![Progress](https://img.shields.io/badge/status-foundation%20only-DAA520)

> **Independent student coursework. This is NOT an official Traffic Scotland app.**
>
> This repository currently contains a **project scaffold**, not a finished application. All Traffic Scotland functionality is subject to the *full coursework instructions, marking rubric and DOs/DON'Ts*. Do not submit this starter as a completed assignment.

## Overview

Traffic Scotland Explorer is an Android Studio / Java project intended to read Traffic Scotland incident and roadworks XML streams, transform relevant records into Java objects, and present useful information and selections to mobile users. The design favours straightforward, testable implementation over unnecessary features.

### Planned pipeline

1. Retrieve or load authorised Traffic Scotland XML data.
2. Parse XML into typed Java data objects using the permitted module approach.
3. Store and manage the items in a suitable collection.
4. Show incidents and roadworks with relevant filtering/selection.
5. Show details, handle loading and errors, and verify behaviour with documented tests.

The above is a **plan**. A single-Activity/Fragments/ViewModel **navigation scaffold** is now committed, with clearly labelled placeholder screens. Traffic functionality is not yet implemented or verified.

## Current status

| Area | Status |
| --- | --- |
| GitHub repository, source scaffold and documentation | Prepared and committed |
| Local Android Studio build and emulator run | **GitHub snapshot not verified** (only separate local starter Hello World app ran) |
| XML feed loading, parsing and collections | Parser/models source committed; Android instrumentation not yet run; network download not implemented |
| Incidents/roadworks list, filter and details | Not implemented |
| Official detailed marking rubric | Reviewed; see docs/ASSESSMENT_TRACKER.md |
| Test evidence, APK and narrated video | Not produced |

### Source layout

| Path | Responsibility |
| --- | --- |
| app/src/main/java/com/kirilbezmen/trafficscotland/MainActivity.java | Temporary setup screen |
| app/src/main/java/.../data/model/ | Java traffic record classes (planned) |
| app/src/main/java/.../data/parser/ | XML parsing (planned) |
| app/src/main/java/.../data/repository/ | Data retrieval/storage boundary (planned) |
| app/src/main/java/.../ui/list/ | List adapter/UI (planned) |
| app/src/main/java/.../ui/detail/ | Details display (planned) |
| app/src/main/res/ | Layout, theme, strings, drawable resources |
| app/src/test/ | JVM unit tests (planned) |
| app/src/androidTest/ | Instrumented Android tests (planned) |
| docs/ | Traceability, decisions, architecture, test and AI records |
| deliverables/apk/ | Placeholder for submission APK (not a generated APK) |
| .github/ | Issue and PR templates |

## Local setup

**Requirements:** Android Studio; Android SDK Platform 35 for the provided default config; JDK 17; an emulator or Android device.

> **Incomplete Gradle wrapper:** This initial remote scaffold includes Gradle configuration and gradle-wrapper.properties but **does not include gradle-wrapper.jar or launcher scripts**. These standard generated files need to be added via a local Android Studio / Gradle setup before the project can be built from a clean checkout. See [setup guide](docs/ANDROID_STUDIO_SETUP.md). Do not mistake a GitHub upload for a verified build.

After the wrapper is fixed, sync, launch the app on an emulator and press **Check setup**. That exercises the temporary UI only — it does **not** demonstrate traffic XML functionality.

## Tracking and quality

- [Assessment tracker](docs/ASSESSMENT_TRACKER.md): confirmed 100-mark rubric and traceability plan.
- [Architecture](docs/ARCHITECTURE.md): revised one-Activity + Fragments + MVVM architecture.
- [Coursework constraints](docs/COURSEWORK_CONSTRAINTS.md): hard requirements and zero-mark pitfalls.
- [Starter code audit](docs/STARTER_CODE_AUDIT.md): what must be replaced, rather than merely copied.
- [Sample XML audit](docs/SAMPLE_XML_AUDIT.md): checked data counts and optional fields.
- [Development log](docs/DEVELOPMENT_LOG.md): chronological implementation and verification.
- [Testing strategy](docs/TEST_STRATEGY.md): test cases and evidence, kept separate from planned tests.
- [GenAI usage and prompts](docs/GENAI_USAGE_LOG.md): mandatory Guided GenAI disclosure.
- [Design decisions](docs/DECISIONS.md): architectural choices and constraints.
- [Submission checklist](docs/SUBMISSION_CHECKLIST.md): final hand-in artifacts.

## Dates

- **CW1:** Monday **23 November 2026**. The detailed CW1 assignment and module schedule both say **17:00**. An earlier generic assessment brief states noon, so use **12:00 as an internal safety cutoff** unless clarified with the module team.
- **CW2:** Monday **30 November 2026**, invigilated at GCU during lab time.
- **Internal completion target:** 18–20 November 2026.

## Academic integrity and data

The brief designates GenAI as **G — AI Guided**: AI assistance to code, debug and test must be fully disclosed, including the relevant prompts. The student must review, understand and test the final work. Do not silently use techniques ruled out by the rubric.

Traffic Scotland data originates from Traffic Scotland / Transport Scotland. No live stream has been fetched or certified here, and no sample traffic data is bundled yet. Check permissions and attribution terms for any feed incorporated later.

**Principle:** Rubric first, reliable functionality second, polish third. Honours Project remains the main academic priority.
