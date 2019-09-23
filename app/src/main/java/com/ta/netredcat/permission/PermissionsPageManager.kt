package com.ta.netredcat.permission

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log

object PermissionsPageManager {
    /**
     * Build.MANUFACTURER
     */
    private val MANUFACTURER_HUAWEI = "HUAWEI"
    internal val MANUFACTURER_XIAOMI = "XIAOMI"
    internal val MANUFACTURER_OPPO = "OPPO"
    private val MANUFACTURER_VIVO = "vivo"
    internal val MANUFACTURER_MEIZU = "meizu"
    val manufacturer = Build.MANUFACTURER

    val isXIAOMI: Boolean
        get() = manufacturer.equals(MANUFACTURER_XIAOMI, ignoreCase = true)

    val isOPPO: Boolean
        get() = manufacturer.equals(MANUFACTURER_OPPO, ignoreCase = true)

    val isMEIZU: Boolean
        get() = manufacturer.equals(MANUFACTURER_MEIZU, ignoreCase = true)

    fun getIntent(activity: Activity): Intent {
        var permissionsPage: PermissionsPage = Protogenesis(activity)
        try {
            if (MANUFACTURER_HUAWEI.equals(manufacturer, ignoreCase = true)) {
                permissionsPage = HUAWEI(activity)
            } else if (MANUFACTURER_OPPO.equals(manufacturer, ignoreCase = true)) {
                permissionsPage = OPPO(activity)
            } else if (MANUFACTURER_VIVO.equals(manufacturer, ignoreCase = true)) {
                permissionsPage = VIVO(activity)
            } else if (MANUFACTURER_XIAOMI.equals(manufacturer, ignoreCase = true)) {
                permissionsPage = XIAOMI(activity)
            } else if (MANUFACTURER_MEIZU.equals(manufacturer, ignoreCase = true)) {
                permissionsPage = MEIZU(activity)
            }

            return permissionsPage.settingIntent()
        } catch (e: Exception) {
            Log.e("Permissions4M", "手机品牌为：" + manufacturer + "异常抛出，：" + e.message)
            permissionsPage = Protogenesis(activity)
            return permissionsPage.settingIntent()
        }
    }

    fun getSettingIntent(activity: Activity): Intent {
        return Protogenesis(activity).settingIntent()
    }
}
