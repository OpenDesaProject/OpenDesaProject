package com.example.core.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.provider.Settings.Secure.ANDROID_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

  @SuppressLint("HardwareIds")
  @Provides
  @Named("deviceUid")
  fun provideDeviceUid(@ApplicationContext context: Context): String {
    return Settings.Secure.getString(context.contentResolver, ANDROID_ID)
  }

}