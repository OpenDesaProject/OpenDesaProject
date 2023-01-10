import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    //android ui
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appCompact = "androidx.appcompat:appcompat:1.5.1"
    private const val material = "com.google.android.material:material:1.7.0"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.5.3"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:2.5.3"
    private const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"
    private const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
    private const val glide = "com.github.bumptech.glide:glide:4.11.0"
    private const val glideCompiler = "com.github.bumptech.glide:compiler:4.11.0"
    private const val moshi = "com.squareup.moshi:moshi-kotlin:1.13.0"
    private const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
    private const val coil = "io.coil-kt:coil:2.2.2"

    //jetpack compose
    private const val materialThree = "androidx.compose.material3:material3:${Versions.materialThree}"
    private const val materialTwo = "androidx.compose.material:material:${Versions.materialTwo}"
    private const val themeAdapter = "com.google.accompanist:accompanist-themeadapter-material:${Versions.themeAdapter}"
    private const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
    private const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
    private const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}"
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    private const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.composeVersion}"
    private const val composeUiTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    private const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    private const val composeDestination = "io.github.raamcosta.compose-destinations:animations-core:${Versions.composeDestination}"
    private const val composeDestinationKsp = "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestination}"
    private const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}"
    private const val materialIconExtended = "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
    private const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    private const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavCompose}"
    private const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    private const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    private const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    private const val coroutinePlayService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutine}"
    private const val accompanistPermission = "com.google.accompanist:accompanist-permissions:${Versions.accompanistPermission}"
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private const val loging = "com.squareup.okhttp3:logging-interceptor:${Versions.loging}"
    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationJson}"
    private const val timeAgo = "com.github.marlonlom:timeago:${Versions.timeAgo}"
    private const val composeDialogCore = "com.maxkeppeler.sheets-compose-dialogs:core:${Versions.composeDialogs}"
    private const val composeDialogCalendar = "com.maxkeppeler.sheets-compose-dialogs:calendar:${Versions.composeDialogs}"

    //test
    private const val junit = "junit:junit:${Versions.junit}"
    private const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    private const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    //debug
    private const val debugUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    private const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}"

    val appLibraries = arrayListOf<String>().apply {
        add(appCompact)
        add(material)
        add(constraintLayout)
        add(navigationFragment)
        add(navigationUi)
        add(liveData)
        add(legacySupport)
        add(glide)
        add(moshi)
        add(moshiConverter)
        add(coil)
        add(coreKtx)
        add(materialTwo)
        add(materialThree)
        add(composeRuntime)
        add(composeFoundation)
        add(runtimeKtx)
        add(activityCompose)
        add(composeUi)
        add(composeUiUtil)
        add(composeUiTooling)
        add(coilCompose)
        add(accompanistPermission)
        add(composeDestination)
        add(systemUiController)
        add(materialIconExtended)
        add(viewModel)
        add(hilt)
        add(hiltNavigationCompose)
        add(splashScreen)
        add(coroutineCore)
        add(coroutineAndroid)
        add(coroutinePlayService)
        add(activityKtx)
        add(retrofit)
        add(gsonConverter)
        add(loging)
        add(timber)
        add(serializationJson)
        add(timeAgo)
        add(composeDialogCore)
        add(composeDialogCalendar)
    }

    val kspLibraries = arrayListOf<String>().apply {
        add(composeDestinationKsp)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(glideCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJunit)
        add(espresso)
        add(composeUiJunit)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(debugUiTooling)
        add(testManifest)
    }

}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.ksp(list: List<String>) {
    list.forEach { dependency ->
        add("ksp", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementationPlatform(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", platform(dependency))
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}