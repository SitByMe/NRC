package com.ta.netredcat.permission

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * support:
 * 1.hongmi 5X android:6.0.1/miui 8.2
 *
 *
 * manager home page, or [Protogenesis.settingIntent]
 *
 *
 * Created by joker on 2017/8/4.
 */
class XIAOMI(private val context: Activity) : PermissionsPage {
    private val PKG = "com.miui.securitycenter"
    // manager
    private val MIUI8_MANAGER_OUT_CLS = "com.miui.securityscan.MainActivity"
    private val MIUI7_MANAGER_OUT_CLS =
        "com.miui.permcenter.permissions" + ".AppPermissionsEditorActivity"
    // xiaomi permissions setting page
    private val MIUI8_OUT_CLS = "com.android.settings.applications.InstalledAppDetailsTop"

    private val systemProperty: String
        get() {
            var line = ""
            var input: BufferedReader? = null
            try {
                val p = Runtime.getRuntime().exec("getprop ro.miui.ui.version.name")
                input = BufferedReader(InputStreamReader(p.inputStream), 1024)
                line = input.readLine()
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ""
            } finally {
                if (input != null) {
                    try {
                        input.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
            return line
        }

    @Throws(ActivityNotFoundException::class)
    override fun settingIntent(): Intent {
        var intent = Intent()
        val miuiInfo = systemProperty
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (miuiInfo.contains("7") || miuiInfo.contains("6")) {
            intent = Intent("miui.intent.action.APP_PERM_EDITOR")
            intent.setClassName(PKG, MIUI7_MANAGER_OUT_CLS)
            intent.putExtra("extra_pkgname", context.packageName)
        } else {
            // miui 8
            intent.putExtra(PermissionsPage.PACK_TAG, context.packageName)
            val comp = ComponentName(PKG, MIUI8_MANAGER_OUT_CLS)
            intent.component = comp
        }

        return intent
    }
}
