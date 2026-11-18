# Trusted Gradle Wrapper provenance

**Added:** 18 November 2026 to fix the missing `gradlew.bat` / clean-clone reproducibility blocker.

- **Version:** Gradle **8.9** (stable), matching the previously working CI Gradle 8.9.
- **Upstream:** official Gradle [v8.9.0 GitHub source tree](https://github.com/gradle/gradle/tree/v8.9.0).
- **Files:** `gradlew`, `gradlew.bat`, and `gradle/wrapper/gradle-wrapper.jar` were taken from this official source tag. The wrapper JAR's Git blob SHA **`2c3521197d7c4586c843d1d3e9090525f1898cde`** was compared after upload to this repository and matched the upstream blob SHA **exactly**.
- **Distribution:** `https://services.gradle.org/distributions/gradle-8.9-bin.zip` pinned in `gradle-wrapper.properties`.
- **Distribution SHA-256:** `d725d707bfabd4dfdc958c624003b3c80accc03f7037b5122c4b1d0ef15cecab`, from [Gradle's official release checksums](https://gradle.org/release-checksums/).
- **Upstream Gradle 8.9 wrapper JAR SHA-256 (official reference):** `498495120a03b9a6ab5d155f5de3c8f0d986a449153702fb80fc80e134484f17`. This value should be checked independently when auditing the binary.
- Wrapper script is executable (`100755`) on Unix; Windows launcher committed as `gradlew.bat`.
- **Validation:** GitHub Actions is configured to run the wrapper for JVM tests/debug compilation. A clean Windows Git clone/Android Studio import is a **separate student-run check**, not automatically claimed PASS from CI.

This fixes the previous missing-file error. Do not replace wrapper JAR with an unknown binary.
