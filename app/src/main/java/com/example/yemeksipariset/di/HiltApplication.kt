package com.example.yemeksipariset.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : Application() {

} //bu sınıf uygulamanın yasam döngüsüne hilti entegre eder yani hilt,burada başlar ve tum uygulama boyunca kullanılabılır hale gelir
