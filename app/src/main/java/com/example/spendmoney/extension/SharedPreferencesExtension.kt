package com.example.spendmoney.extension

import android.content.Context
import android.content.SharedPreferences
import com.example.spendmoney.untils.Constant

fun SharedPreferences.saveStatusUserFirstTime(status: String){
    this.edit().putString(Constant.SHARED_STATUS_USER_USE_FIRST, status).apply()
}
fun SharedPreferences.getStatusUserFirstTime(): String?{
    return this.getString(Constant.SHARED_STATUS_USER_USE_FIRST, Constant.SHARED_DEFAULT_STATUS_USER_USE_FIRST)
}

fun SharedPreferences.saveUserName(userName: String){
    this.edit().putString(Constant.SHARED_USER_NAME, userName).apply()
}
fun SharedPreferences.getUserName(): String?{
    return this.getString(Constant.SHARED_USER_NAME, Constant.SHARED_USER_NAME_DEFAULT_FIRST)
}

fun SharedPreferences.saveMoney(money: String){
    this.edit().putString(Constant.SHARED_MONEY, money).apply()
}
fun SharedPreferences.getMoney(): String?{
    return this.getString(Constant.SHARED_MONEY, Constant.SHARED_MONEY_DEFAULT_FIRST)
}

fun SharedPreferences.saveUriAvatar(uri: String){
    this.edit().putString(Constant.SHARED_URI_AVATAR, uri).apply()
}
fun SharedPreferences.getUriAvatar(): String?{
    return this.getString(Constant.SHARED_URI_AVATAR, Constant.SHARED_URI_AVATAR_DEFAULT_FIRST)
}


