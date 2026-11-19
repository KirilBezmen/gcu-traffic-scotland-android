# CW1 selected AI-assisted development prompts — concise English versions

The three prompts below are edited English formulations of the **types of requests made during development**, not a verbatim English chat transcript. The detailed original-language conversation history and documented AI contributions remain in [GENAI_USAGE_LOG.md](GENAI_USAGE_LOG.md). This selected appendix does not pretend to reproduce every prompt.

## Prompt 1 — Java/XML application structure

> Can you help me write the Java code for my Android app using one MainActivity, Fragments and a shared ViewModel? I want separate Incidents and Roadworks screens, with XML layouts that work in both portrait and landscape. Please keep the code simple and explain how the classes connect.

## Prompt 2 — XML parsing

> I have two Traffic Scotland DATEX II XML feeds. Can you help me use XmlPullParser to extract the situation description, road, dates, delays and coordinates into Java classes? Please show how to handle missing fields and unknown XML tags without crashing.

## Prompt 3 — Debugging map selection

> When I filter roadworks by M80 and open the map, I want to see only those matching situations. Can you review how the selected IDs move from TrafficListFragment to TrafficMapFragment and help me check whether the problem is in my navigation or the filtering code?

**Contribution note:** The student directed the architecture and product decisions, set up the repository, configured APIs locally and verified app behaviour in Android Studio; ChatGPT substantially assisted in developing and refining Java/XML, design, diagnostics, tests and documentation. The examples above illustrate these forms of assistance; they are not fabricated exact quotations.
