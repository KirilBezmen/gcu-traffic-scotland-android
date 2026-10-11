# Traffic Scotland restricted API setup (after administrator approval)

**Never send Client ID/Key in ChatGPT, GitHub, screenshots, Issues or README.**

1. In Android Studio, locate the local `local.properties` at project root. It is already excluded from Git.
2. Once Traffic Scotland has formally approved the Public Restricted API request, copy credentials from the developer portal.
3. Append the following values to `local.properties` locally (not this document). Copy the **official Current Roadworks Content.xml URL from the authorised DATEX II guide**; never substitute another provider or guess a URL.

```properties
TRAFFIC_CLIENT_ID=<your client ID>
TRAFFIC_CLIENT_KEY=<your client key>
TRAFFIC_ROADWORKS_URL=<the exact authorised roadworks Content.xml URL>
```

4. Perform **Sync Project with Gradle Files**. The app sets constants from your untracked local properties in BuildConfig at build time, then requests the official unplanned URL and your verified official roadworks URL with the supplied base64 Authorization convention.
5. Run the **debug app** to verify the source badge changes from `Historic lecturer XML samples` to `Official Traffic Scotland data`. Confirm in Logcat and the feed/UI test evidence that records are really retrieved. If network fails, the app uses only its most recent successfully downloaded *live* XML pair (if available), otherwise a visible error. It does not pretend samples are live.

**Important security limitation:** BuildConfig credentials are extractable from a distributed Android APK. Do not upload a credentials-bearing APK to a public repository; discuss safer credential delivery with the module team before final APK submission. The app is intended as an educational prototype.

**Current status (11 Oct 2026):** developer request has been submitted but approval and actual authenticated retrieval are **not yet confirmed**.
