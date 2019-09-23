package com.ta.netredcat.permission

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager

/**
 * support:
 * 1.mate7 android:6.0/emui 4.0.1
 * 2.畅享7 android:7.0/emui 5.1
 *
 * manager permissions page, permissions manage page, or [Protogenesis.settingIntent]
 *
 */
class HUAWEI(private val context: Activity) : PermissionsPage {
    //private final String SINGLE_CLS = "com.huawei.permissionmanager.ui.SingleAppActivity";
//private final String SINGLE_TAG = "SingleAppActivity";

    private val PKG = "com.huawei.systemmanager"
    private val MANAGER_OUT_CLS = "com.huawei.permissionmanager.ui.MainActivity"

    @Throws(ActivityNotFoundException::class)
    override fun settingIntent(): Intent {
        val intent = Protogenesis(context).settingIntent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(PermissionsPage.PACK_TAG, context.packageName)
        var comp: ComponentName? = null
        try {
            val pi = context.packageManager.getPackageInfo(
                PKG,
                PackageManager.GET_ACTIVITIES
            )
            for (activityInfo in pi.activities) {
                if (activityInfo.name == MANAGER_OUT_CLS) {
                    comp = ComponentName(PKG, MANAGER_OUT_CLS)
                }
            }
            if (comp != null) {
                intent.component = comp
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return intent
        }

        return intent
    }
}
