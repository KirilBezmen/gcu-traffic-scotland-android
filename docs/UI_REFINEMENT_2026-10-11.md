# UI refinement — fixed navigation and list scrolling (11 October 2026)

## Request and observed starting point

Student showed the updated Pixel 9 Pro emulator: **4/4** `TrafficXmlParserTest` passed, and the Incidents list displayed **4 situations** from clearly-labelled *historic lecturer XML samples*. The Incidents layout showed a long **REFRESH** text button, filters and incident cards. A separate Home screenshot revealed a redundant **Explore traffic** section with a second trio of navigation buttons.

Student requested:
1. Remove the redundant **Explore traffic** links because Home / Incidents / Roadworks / Map already exist in `MainActivity`.
2. Do not scroll the **whole screen**. Maintain fixed app/navigation/filter areas and allow the **list of event cards only** to scroll.
3. Replace **REFRESH** text buttons with a recognisable circular-arrow icon.
4. Implement, version and document changes via GitHub.

## Final design decision

| Area | Earlier state | New design/source change | Result required for acceptance |
| --- | --- | --- | --- |
| Global navigation | MainActivity primary 4 tabs; Home duplicated 3 large buttons | Keep ONLY MainActivity tabs, remove Explore traffic label and three Home navigation buttons | Test Home no duplicate navigation |
| Home portrait | Whole fragment `ScrollView` | `LinearLayout` full-height; stretchable illustrated hero and bottom data overview card | Page stays fixed at normal size; no clipping |
| Home landscape | Same fragment structure as portrait | New `layout-land/fragment_home.xml`: road illustration and data summary side by side | Fits landscape alongside MainActivity left navigation rail |
| Incidents & Roadworks portrait | Header, filters, large Refresh button, ListView | Fixed header, source label, search, Nearby toggle, radius when enabled, compact map action + result count; `ListView` alone uses `layout_weight=1` and scrolls | Filter bar stays fixed when user scrolls 405 roadworks |
| Incidents & Roadworks landscape | Portrait fragment squeezed beside MainActivity left rail | New `layout-land/fragment_traffic_list.xml`: filters in left column and list on right | All major controls reachable and independent list scroll |
| Refresh | Large text Button | Original hand-authored `ic_refresh.xml` circular arrow inside 48dp `ImageButton`; `contentDescription` + ripple | Accessible tap refresh on Home and list |
| Nearby slider | Always visible, consuming vertical space | Show slider/label only when Nearby checked; keep fixed Glasgow radius and value from ViewModel | Controls hide/show and filtering still works |
| Lists | Existing native ListView + BaseAdapter already worked | **Retain ListView**: it already recycles rows and scrolls independently; no unnecessary RecyclerView dependency or code churn | Preserve existing XML model/bindings, selection and map flow |
| Empty list | Message beneath list sometimes consumed layout | Centre overlay message in weighted `FrameLayout`; show for loading/empty/error | Cards never obscured; clear empty/error state |

## Files revised
- `app/src/main/java/com/kirilbezmen/trafficscotland/ui/HomeFragment.java`
- `app/src/main/java/com/kirilbezmen/trafficscotland/ui/TrafficListFragment.java`
- `app/src/main/res/layout/fragment_home.xml`
- `app/src/main/res/layout-land/fragment_home.xml` (new)
- `app/src/main/res/layout/fragment_traffic_list.xml`
- `app/src/main/res/layout-land/fragment_traffic_list.xml` (new)
- `app/src/main/res/drawable/ic_refresh.xml` (new original SVG-style Android vector)
- `app/src/main/res/values/strings.xml` (accessible label; compact Map action; remove Explore title)
- `app/src/androidTest/java/com/kirilbezmen/trafficscotland/ui/NavigationChromeTest.java` (new unrun Android smoke tests)

**Unaffected and intentionally preserved:** official-source XML parser, typed models, repository, auto-update, map marker selection, existing incident/roadworks card adapter and rubric-required MVVM architecture. No alternate feed or WebView.

## Acceptance checklist — **not automatically completed by code commits**
- [ ] GitHub Actions `assembleDebug + assembleDebugAndroidTest + testDebugUnitTest` passes on latest commit.
- [ ] Pull latest code in Android Studio and launch on Pixel 9 Pro emulator.
- [ ] Home has ONE main navigation bar; no Explore traffic buttons; road illustration and summary fit without whole-page scrolling.
- [ ] Incidents list: static title + source label + search + Nearby control + refresh icon; finger scrolling moves **only cards**, not header/filter controls.
- [ ] Roadworks list: repeat with ~405 example situations.
- [ ] Nearby enables slider, disabling Nearby hides it; checked radius still matches model data.
- [ ] Refresh icon is tappable/accessibly described; manual refresh triggers repository call.
- [ ] `NavigationChromeTest` two Android tests PASS on emulator.
- [ ] In landscape, MainActivity rail + side-by-side Home/filters-list layouts fit; orientation preserves data and selection correctly.
- [ ] Take 4 screenshots (Home portrait/landscape, Incidents list scrolled, Roadworks list scrolled) and log actual results in `QA_EXECUTION_MATRIX.md`.

## Evidence and marking honesty

The student's pre-refactor screenshot verifies **4 existing parser smoke tests passed** and visually shows 4 incident cards sourced from lecturer examples. This **does not** prove that this new UI revision runs on their emulator, that full official 4+405 instrumentation tests pass, or that the map/API is functional. A green GitHub Actions build verifies compilation/JVM tests and compiles instrumented tests, **not Android execution**.

The original vector refresh icon is authored for the coursework and uses no external icon file. GenAI changes must be disclosed in the final coursework prompt appendix.
