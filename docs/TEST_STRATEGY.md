# Written test strategy — draft

## Objectives
Demonstrate correctness of Traffic Scotland data handling, meaningful UI, resilience to invalid data and usability across mobile conditions. Full required test types depend on official rubric.

## Test levels
- **Unit:** XML parsing, item conversion, missing/optional fields, business rules when present.
- **Integration:** parsed data reaches collection and Android list; refresh behaviour; error paths.
- **System/UI:** launch, navigation, selection, details, loading/empty/error states, portrait/landscape and accessibility.
- **Regression:** rerun representative tests after each change.

## Planned cases (NOT YET EXECUTED)
| Test ID | Scenario | Expected result | Actual result | Status |
| --- | --- | --- | --- | --- |
| ENV-01 | Android clean clone, Gradle sync and install | Successful build/run | Not attempted | Not run |
| UI-01 | Tap Check setup on starter screen | Text changes, Toast appears | Not attempted | Not run |
| XML-01 | Valid incident XML | Correct number and fields | Not implemented | Not run |
| XML-02 | Valid roadworks XML | Correct number and fields | Not implemented | Not run |
| XML-03 | Invalid / truncated XML | Clear error, no crash | Not implemented | Not run |
| XML-04 | Empty items list | Useful empty UI | Not implemented | Not run |
| UI-02 | Choose an item | Matching detail view | Not implemented | Not run |
| UI-03 | Screen rotation | Usable layout/state | Not implemented | Not run |
| NET-01 | Network unavailable | No UI hang, useful error | Not implemented | Not run |

## Evidence policy
Record test date, device/emulator, Android/API level, commit SHA, steps, expected/actual result, PASS/FAIL, screenshots or Logcat paths. Planned tests are not evidence of a successful test.

## Submission
Summarise execution and any open defects in the Word test strategy. Never claim an unexecuted test has passed.
