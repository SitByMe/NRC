package com.ta.netredcat.permission

import android.content.Intent

interface PermissionsPage {

    // normally, ActivityNotFoundException
    @Throws(Exception::class)
    fun settingIntent(): Intent

    companion object {
        val PACK_TAG = "package"
    }
}
