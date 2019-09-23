package com.ta.netredcat.permission

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Build

/**
 * support:
 * 1.Y55A androi:6.0.1/Funtouch 2.6
 * 2.Xplay5A android: 5.1.1/Funtouch 3
 *
 * manager home page, or [Protogenesis.settingIntent]
 *
 */
class VIVO internal constructor(private val context: Activity) : PermissionsPage {

    override fun settingIntent(): Intent {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(PermissionsPage.PACK_TAG, context.packageName)
        val comp: ComponentName
        if (Build.VERSION.SDK_INT >= 23) {
            comp = ComponentName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity.PurviewTabActivity"
            )
        } else {
            comp = ComponentName(
                "com.iqoo.secure",
                "com.iqoo.secure.ui.phoneoptimize.SoftwareManagerActivity"
            )
        }
        intent.component = comp
        return intent
    }
}
