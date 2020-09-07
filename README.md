# Dictionary Application

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:matterox/dictionary-app.git
```

## Configuration
### Keystores:
Create `app/keystores/keystore.gradle` file with the following info:
```gradle
ext.key_alias='KEY_ALIAS'
ext.key_password='KEY_PASSWORD'
ext.store_password='STORE_PASSWORD'
```

## Build variants
Use the Android Studio *Build Variants* button to choose between **release** and **debug** build variants

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(That you entered in KEY_ALIAS, KEY_PASSWORD and STORE_PASSWORD)*

## Launching
You can launch an app directly from Android Studio with emulator, select build variant and press *Run App*

## Used Libriaries
- [Retrofit](https://github.com/square/retrofit) - network calls
- [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - logging network calls
- [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - navigation
- [Safe Args](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - navigation safe arguments
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - managing livecycle of Activities/Fragments
- [Kodein](https://github.com/Kodein-Framework/Kodein-DI) - dependency injection
- [Timber](https://github.com/JakeWharton/timber) - logs
- [Coil](https://github.com/coil-kt/coil) - image loading
- [MockK](https://github.com/mockk/mockk) - mocking for kotlin
- [Kotlinx Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) - testing kotlin coroutines
- [Arch Core Testing](https://developer.android.com/jetpack/androidx/releases/arch-core) - Arcitecture components testing