# CW1 Project Board — Traffic Scotland Explorer

> **Board blueprint, NOT an already-created GitHub Project.** The repository has working GitHub Issues and this project-management document. The GitHub connector currently does not expose the GitHub Projects creation/add-item operation. Create the visual board in GitHub with the steps below and link these existing issues.

## Create the board once (owner: KirilBezmen)
1. Visit the [repository Projects tab](https://github.com/KirilBezmen/gcu-traffic-scotland-android/projects), click **New project**, choose **Board**, and name it **Traffic Scotland Explorer — CW1 Development**.
2. Set visibility **Private** (check the GitHub Project privacy separately from repo privacy), then link it to the repository using **Link a project**, if it is not already associated.
3. Use the existing **Status** field. Rename/create workflow options **Backlog, In Progress, Testing, Done, Blocked** (status wording can be adapted to GitHub's current UI).
4. In the Board, **+ Add item** → search for existing repository issues. Add **#1–#13**. **Do not recreate them**. Move #8, #9, #10 to Done because those narrowly defined outcomes were actually verified; do not close broader milestones prematurely.
5. Optional: use the board's built-in filters or group by Status. Create a custom **Priority** field (P0 / P1 / P2) only if it saves time; priority already exists in task names/text.

## Status meanings
- **Backlog:** not started; has concrete acceptance criteria.
- **In Progress:** work started, but acceptance tests not complete.
- **Testing:** implementation exists, verification/evidence still pending.
- **Done:** tests ran and passed, evidence recorded, issue closed.
- **Blocked:** external dependency (Traffic Scotland approval, optional Maps key, lecturer clarification).

**Never move a task to Done solely because a source file is committed or GitHub Actions successfully compiled it.**

## Existing GitHub Issues — ready for board import

| Issue | Work package | Proposed board status | Priority | Target / dependency |
| --- | --- | --- | --- | --- |
| [#1](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/1) | M0 — Setup + Traffic Scotland API | In Progress (contains blocked API) | P0 | API approval; wrapper |
| [#11](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/11) | Portable Gradle wrapper / clean clone / project naming | In Progress | P0 | 14 Oct |
| [#12](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/12) | Approved endpoints, safe credentials + LIVE validation | Blocked | P0 | Traffic Scotland admin |
| [#2](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/2) | M1 — Full PullParser + data structures | Testing | P0 | Full XML tests |
| [#13](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/13) | Run real 4/405 XML instrumentation tests | Testing | P0 | 14 Oct, emulator |
| [#3](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/3) | M2 — Incidents, roadworks lists + details | Testing | P0 | UI emulator tests |
| [#4](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/4) | M3 — Distinct orientation + MVVM state | Testing | P0 | Rotation test |
| [#5](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/5) | M4 — Road/nearby, delay, icons, map | In Progress | P0 | Maps key; correct icon use |
| [#6](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/6) | M5 — Background refresh + offline resilience | In Progress | P0 | Approved live API |
| [#7](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/7) | M6 — Evidence, APK, video, Word, ZIP | In Progress | P0 | Final verification |
| [#8](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/8) | Repository + docs foundation | Done (Closed) | — | Verified |
| [#9](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/9) | Basic app ran on Pixel 9 Pro | Done (Closed) | — | Verified on older snapshot |
| [#10](https://github.com/KirilBezmen/gcu-traffic-scotland-android/issues/10) | Four *representative* Android parser tests | Done (Closed) | — | 4/4 PASS, not full samples |

The broad Milestone 0–6 titles are **Issues**, not automatically GitHub's separate 'Milestone' objects.

## Internal delivery plan (not official university dates)
- **11–14 October:** ensure fresh-clone Gradle build; execute full XML 4/405 tests; correct discrepancies. Blocked: live API approval.
- **15–20 October:** test both real sample lists, details, road search, nearby distance, Home, landscape, navigation and data provenance on device.
- **21–27 October:** live authenticated feed after approval, Google Maps key and multiple map markers, semantically appropriate icons, delay display.
- **28 October–7 November:** threading/periodic-refresh, offline caching, error recovery, UI and state persistence regression.
- **8–15 November:** complete rubric test matrix on emulator and/or phone, capture source-to-screen evidence; polish UI.
- **16–20 November:** final Word + GenAI prompt appendix, narrated MP4, clean APK, GitHub links and External Examiner ZIP; test fresh import and actual playback.
- **23 November:** university CW1 deadline is given as **17:00** in detailed brief; a generic document says **noon**. Use **noon as internal submission target** pending written clarification.
- **30 November:** separate invigilated CW2 class test (30% of module); prepare separately.

## Development operating procedure
1. Choose the **smallest open item** with an evidence-based definition of done.
2. Check the course constraints first. Implement in Java + XML Android Views, only approved Traffic Scotland feeds, mandatory XmlPullParser from downloaded String.
3. Commit coherent changes; if using a branch/PR, link the issue with **Fixes #N** only when all checks pass. A direct-to-main commit is acceptable for this solo coursework but is not automatically a PR.
4. Run CI. A successful build/JVM test **does not run Android instrumented tests on a real emulator**.
5. Student pulls, runs Pixel 9 Pro and captures tests; enter **expected**, **observed**, outcome, device/API, date, commit and screenshot in [QA matrix](QA_EXECUTION_MATRIX.md).
6. Update Development Log, GenAI prompts and README. Close the issue only after evidence. For blocked work, record dependency and choose another task.
7. Before release, verify no real private keys in repo/APK, rights for supplied samples, accessibility, and all hand-in links.

## 100-mark dependency overview
**Source approval + XML parsing → typed objects → repository / MVVM → lists & details → road & nearby search → colour/icons → selected map → orientation → live background refresh/offline → evidence/video/submission.**

The mark allocation comes from the original assessment brief. See [rubric requirements](RUBRIC_100_CHECKLIST.md) for the exact maxima and outstanding icon-mark ambiguity. **Aiming for 100 is a goal, not a grade promise.**
