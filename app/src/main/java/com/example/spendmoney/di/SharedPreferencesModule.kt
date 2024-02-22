package com.example.spendmoney.di

import android.app.Application
import android.content.SharedPreferences
import com.example.spendmoney.models.ObjSpend
import com.example.spendmoney.untils.Constant
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        Constant.SHARED_PREF_ROOT,
        android.content.Context.MODE_PRIVATE
    )
}
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)

fun List<ObjSpend>.toJsonString(): String {
    val gson = Gson()
    return gson.toJson(this)
}