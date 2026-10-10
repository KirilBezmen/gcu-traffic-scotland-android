# Provisional architecture (not yet implemented)

**Target:** Traffic Scotland XML → XML parser → typed Java records → collection/repository → Android Views → selected details.

Planned components:
- **MainActivity** — current setup placeholder, later actual navigation as rubric requires.
- **data/model** — Java class(es) for incident and roadwork data; final field list follows provided sample feed.
- **data/parser** — XmlPullParser implementation matching lecturer requirements.
- **data/repository** — obtain feeds, manage parsed collections and error states.
- **ui/list** — list displays, selections, filters as required.
- **ui/detail** — readable single-record view.
- **util** — minimal shared helpers only where justified.

No API client, backend, database, maps, analytic models or additional frameworks will be added without an assessment need.

## Validation principles
Handle malformed XML, empty feeds, absent fields, networking errors, device rotation and unusable UI states where relevant. Heavy or network work must not freeze the UI. Actual threading pattern to follow lab/starter guidance.

**Hold point:** obtain full official specification, DOs/DON'Ts, rubric and starter examples. Do not infer missing grading criteria.
