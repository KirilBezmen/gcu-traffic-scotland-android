# Development log

## 10 October 2026 — Repository foundation
**Goal:** version-controlled coursework structure and honest status.

**Completed:** Created private GitHub repository (by student), confirmed assistant write access, prepared Android Gradle + Java/XML startup structure, docs, evidence folders, templates and tracking files.

**Not completed:** Full marking rubric review, app build/emulator run, Gradle wrapper generation, Traffic Scotland networking/parsing, actual Android functionality.

**Testing performed:** Reviewed package names and XML layout structure before GitHub upload. No Android compilation or emulator run yet.

**Next actions:** obtain rubric/DOs/DON'Ts/starter code; add wrapper; build and test baseline; implement required feed parser using coursework-approved methods.

**AI disclosure:** see GENAI_USAGE_LOG.md. All implementation/testing claims must reference an actual run, not an assumption.

## Later entries template
Date / commit:
Goal:
Changes:
Testing evidence (pass/fail/not run):
Problems:
Next steps:
GenAI assistance logged:

## 10 October 2026 — Official rubric and starter/code samples audit
**Inputs:** Detailed CW1 assignment, 4-page rubric, lecturer starter ZIP, 2 DATEX II XML fixtures and XML structure note.

**Analysis completed:** Mapped 100-mark rubric to implementation plan, discovered mandatory one-Activity+Fragments+MVVM for full architecture points and hard feed/PullParser restrictions; inspected starter and counted 4 incidents / 405 roadworks. Prepared documented pitfalls and test targets.

**Changes:** Documentation only, not the app's runtime logic. Existing GitHub scaffold has not yet been built/tested.

**Next:** Register official Traffic Scotland restricted API with GCU email; test Android Studio build; then implement and test PullParser before UI features.

**GenAI disclosure:** see GENAI_USAGE_LOG.md entry 003.

## 11 October 2026 — First architecture-level code commit
**Goal:** Stabilise `MainActivity` to avoid rewriting network/UI code in it during feature development.

**Code commit:** `07734399ad8c24715f29066f548d4c57944a150f`. Added 5 Java files, main layout variants, 2 fragment layouts, strings, dependencies and Internet manifest permission. UI is a navigable, **unverified scaffold**; map/list/detail content are explicit placeholders.

**Local status:** Student demonstrated a successful Android Studio Hello World emulator run in a **separate local generated project**, not this GitHub snapshot. These must be reconciled before reporting a successful run.

**Next action:** Clone the GitHub repo and supply a compatible wrapper from local generated project, sync/build/test; then implement full XML parsing and repository functionality in focused classes.

**AI assistance:** GENAI_USAGE_LOG.md Entry 004.
