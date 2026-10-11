# Icon sources and appropriate situations

All icons in the app were created specifically for this educational project as **original hand-authored Android VectorDrawable XML shapes**. No external icons or Traffic Scotland brand graphics have been copied. Authorship: student project, with AI-assisted design and coding disclosed in `GENAI_USAGE_LOG.md`.

| Drawable | Intended situation subtype | Usage |
| --- | --- | --- |
| `ic_cone.xml` | MaintenanceWorks / current roadworks | Always used for roadworks |
| `ic_road_closed.xml` | roadClosed / carriagewayClosed | Unplanned closed road |
| `ic_queue.xml` | queuingTraffic / abnormalTraffic | Congestion/queue |
| `ic_lane_closed.xml` | laneClosed / laneClosure | Lane restriction |
| `ic_weather.xml` | weather, snow or ice | Weather issue |
| `ic_obstruction.xml` | obstruction, debris | Road obstruction |
| `ic_warning.xml` | Other unplanned incident | Generic fallback |

Source code: `app/src/main/res/drawable/ic_*.xml`, used by `TrafficSituationAdapter.iconFor()`.

**Rubric validation still needed:** Existence of seven icons alone does not prove all were adequately used. Screenshots or suitable data cases are required showing appropriate usage across at least six types, and lecturer rubric has inconsistent icon max (6 vs 8). Provide visible labels in addition to colour/icon.
