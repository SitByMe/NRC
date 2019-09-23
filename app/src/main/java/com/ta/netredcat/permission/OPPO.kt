package com.ta.netredcat.permission

import android.app.Activity
import android.content.ComponentName
import android.content.Intent

/**
 * support:
 * 1. oppo a57 android 6.0.1/coloros3.0
 *
 * manager home page, permissions manage page does not work!!!, or
 * [Protogenesis.settingIntent]
 *
 */
class OPPO internal constructor(private val context: Activity) : PermissionsPage {
    private val PKG = "com.coloros.safecenter"
    private val MANAGER_OUT_CLS =
        "com.coloros.safecenter.permission.singlepage" + ".PermissionSinglePageActivity"

    override fun settingIntent(): Intent {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(PermissionsPage.PACK_TAG, context.packageName)
        val comp = ComponentName(PKG, MANAGER_OUT_CLS)
        // do not work!!
        //        comp = new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission" + ".PermissionAppAllPermissionActivity");
        intent.component = comp
        return intent
    }
}
