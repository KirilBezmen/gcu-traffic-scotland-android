# Architecture decision records

### ADR-001 — Scope: rubric-first
**10 October 2026.** Traffic Scotland is the assigned domain. Avoid unrelated functionality until mandatory rubric criteria are confirmed.

### ADR-002 — Language and interface
**10 October 2026.** Java application source with classic XML View layouts, aligning with MPD lab teaching. Gradle Kotlin DSL is only build configuration, not Kotlin application code.

### ADR-003 — Parsing
**10 October 2026.** Propose Android XmlPullParser because lecture materials explicitly describe XML parsing for coursework. Confirm feed structure and allowed methods against the official DOs/DON'Ts first.

### ADR-004 — Packaging
**10 October 2026.** Scaffold uploaded to a dedicated private coursework GitHub repository. Gradle wrapper binary and launchers are still missing, so the app is not yet build-verified. Do not represent repository upload as successful Android deployment.
