# Architecture decision records

### ADR-001 — Scope: rubric-first
**10 October 2026.** Traffic Scotland is the assigned domain. Avoid unrelated functionality until mandatory rubric criteria are confirmed.

### ADR-002 — Language and interface
**10 October 2026.** Java application source with classic XML View layouts, aligning with MPD lab teaching. Gradle Kotlin DSL is only build configuration, not Kotlin application code.

### ADR-003 — Parsing
**10 October 2026.** Propose Android XmlPullParser because lecture materials explicitly describe XML parsing for coursework. Confirm feed structure and allowed methods against the official DOs/DON'Ts first.

### ADR-004 — Packaging
**10 October 2026.** Scaffold uploaded to a dedicated private coursework GitHub repository. Gradle wrapper binary and launchers are still missing, so the app is not yet build-verified. Do not represent repository upload as successful Android deployment.

### ADR-005 — Rubric verified and architecture superseded
**10 October 2026.** Full PDF marking rubric and detailed coursework description supplied. Adopt **one Activity + Fragments + MVVM** to target 10/10 architecture points rather than earlier multiple-Activity strategy.

### ADR-006 — Hard source and parser constraints
**10 October 2026.** Only the supplied Traffic Scotland DATEX II streams are acceptable; other feeds cause overall zero. Parse downloaded XML String inside app using XmlPullParser; avoid WebView and unapproved external frameworks.

### ADR-007 — Start with professor's skeleton audit
**10 October 2026.** Supplied skeleton zip has only MainActivity.java, activity_main.xml, AndroidManifest.xml. It is button-only and retains runOnUiThread; main feature/threading gaps documented. Do not copy credentials into repo.

### ADR-008 — Rubric inconsistencies disclosed
**10 October 2026.** Icons row heading says 6 marks, its explanatory note says 8, while 42 total implies 8. Detailed CW1 brief and module page give deadline 17:00; an earlier assessment brief says noon. Seek clarification; use noon internal cutoff until then.
