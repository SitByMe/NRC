package com.ta.netredcat.permission

import android.app.Activity
import android.content.ComponentName
import android.content.Intent

class MEIZU internal constructor(private val activity: Activity) : PermissionsPage {
    private val N_MANAGER_OUT_CLS = "com.meizu.safe.permission.PermissionMainActivity"
    private val L_MANAGER_OUT_CLS = "com.meizu.safe.SecurityMainActivity"
    private val PKG = "com.meizu.safe"

    private val cls: String
        get() = if (ManufacturerSupportUtil.isAndroidL) {
            L_MANAGER_OUT_CLS
        } else {
            N_MANAGER_OUT_CLS
        }

    override fun settingIntent(): Intent {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(PermissionsPage.PACK_TAG, activity.packageName)
        val comp = ComponentName(PKG, cls)
        intent.component = comp
        return intent
    }
}
