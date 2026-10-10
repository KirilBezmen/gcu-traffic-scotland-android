# Gradle wrapper placeholder

The scaffold includes gradle-wrapper.properties, but not the standard wrapper binary and launchers.

Before a clean Android Studio import/build is verified:
1. Obtain the Gradle wrapper JAR and gradlew / gradlew.bat through a trusted local Gradle or Android Studio setup.
2. Use Gradle 8.9 with the scaffold's Android Gradle Plugin 8.7.3, or update the versions together to a compatible pair.
3. Commit the generated standard wrapper files (including the wrapper JAR, which is intentionally not ignored).
4. Sync, build and run on Android. Record real outcomes in docs/DEVELOPMENT_LOG.md.

Do not claim the starter is build-verified until those steps have succeeded.
