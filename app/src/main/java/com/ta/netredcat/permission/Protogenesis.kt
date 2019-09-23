package com.ta.netredcat.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class Protogenesis(private val activity: Activity) : PermissionsPage {

    // system details setting page
    override fun settingIntent(): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri

        return intent
    }
}
