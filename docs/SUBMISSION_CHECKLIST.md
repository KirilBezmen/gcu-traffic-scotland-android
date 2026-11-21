# CW1 final submission checklist

**Official GCULearn deadline: Monday 23 November 2026 at 17:00 GMT.**

This checklist reflects the **GCULearn Submission 1 instructions visible to the student on 21 November**: GitHub must contain a **clean Android Studio source project only, NO APK**, with API keys excluded or replaced with placeholders. The Word document must include the GitHub link and a OneDrive video link.

## Submission 1: Word testing document

- [x] Word file drafted and signed by the student, name `Bezmen_Kiril_s2338746.docx`.
- [x] Originality statement, GenAI contribution disclosure and selected prompt appendix incorporated into the student's Word file.
- [x] Documented testing with expected and actual results.
- [x] Repository URL included: https://github.com/KirilBezmen/gcu-traffic-scotland-android
- [ ] Student confirms OneDrive MP4 link appears in the actual submitted DOCX and is accessible to anyone with the link.
- [ ] Student uploads the final DOCX to GCULearn Submission 1 and saves the receipt.

## Submission 2: narrated video

- [x] Student reports a seven-minute recorded English demonstration.
- [ ] Student uploads `Bezmen_Kiril_s2338746_Video.mp4` or an accessible OneDrive link to Submission 2, and checks the receipt.

## Submission 3: External Examiner ZIP

- [ ] Folder `Bezmen_Kiril_s2338746/` contains APK, the narrated MP4, and an **independent cleaned Android Studio source ZIP**.
- [ ] Source ZIP excludes `local.properties`, credentials, signing keys, `.gradle` caches and `build/` directories.
- [ ] Archive the enclosing folder to `Bezmen_Kiril_s2338746.zip` (ZIP format only, no password) and test extraction.
- [ ] Only a permitted/controlled university hand-in should contain an authenticated LIVE APK. Embedded DATEX credentials can be recovered from such an APK, so check provider sharing conditions. A credential-free SAMPLE APK cannot by itself demonstrate LIVE functionality.
- [ ] Student uploads ZIP through GCULearn Submission 3 and checks the receipt.

## Public GitHub source check

- [x] The `main` branch has Java/XML source, `gradlew.bat`, `gradlew`, the Gradle Wrapper JAR, SDK configuration and unit/instrumentation test sources.
- [x] `local.properties` excluded by `.gitignore`; no credential file tracked in current `main` tree.
- [ ] Student confirms public visibility or explicitly grants assessor access, since a private repository is not accessible merely by sharing a URL.
- [ ] Student or examiner verifies fresh Android Studio clone and Gradle Sync.
- [ ] GitHub repo contains **no APK release or downloadable APK artifacts** and CI only compiles/tests source.
- [ ] The actual repository link is opened in a signed-out browser to verify access.

**Testing honesty:** main GitHub Actions run 44332929422 succeeded at compilation and JVM tests on 18 November. Student-executed Android device test observations are documented in [QA matrix](QA_EXECUTION_MATRIX.md). New full emulator instrumentation, timed hourly refresh and no-cache ERROR remain unverified, and should not be labelled PASS.

**Note:** This checklist and GitHub changes do not modify the student's approved Word document. The signed DOCX must be verified separately for broken or missing hyperlinks.
