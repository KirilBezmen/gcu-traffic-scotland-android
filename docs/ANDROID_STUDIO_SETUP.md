# Android Studio setup

This is a **scaffold**, not a verified Android build. Requirements: Android Studio, SDK Platform 35, JDK 17, emulator/device.

## Import
1. Clone the repository to your PC. Use Android Studio > Get from VCS, or use Git clone.
2. Obtain the missing standard Gradle wrapper scripts and gradle-wrapper.jar from a trusted local Gradle/Android Studio setup. The wrapper properties target Gradle 8.9; AGP is 8.7.3.
3. Commit the standard wrapper files so clean clones can use the same setup.
4. Open the root folder (not the app subfolder) in Android Studio.
5. Allow Gradle to sync and install SDK Platform 35 if prompted.
6. Run the app on an emulator at API 26 or above.
7. Press the **Check setup** button and inspect a Toast / updated status label.
8. Write the actual result, Android version, logs and commit ID into the development log.

## Important
No successful build or app run is claimed at repository creation time. Traffic parsing is not yet implemented. If the lecturer provides a preferred starter project, compare before continuing and keep the mandatory Java/XML approach.
