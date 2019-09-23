package com.ta.netredcat.permission

import android.os.Build

import java.util.HashSet

import com.ta.netredcat.permission.PermissionsPageManager.MANUFACTURER_MEIZU
import com.ta.netredcat.permission.PermissionsPageManager.MANUFACTURER_OPPO
import com.ta.netredcat.permission.PermissionsPageManager.MANUFACTURER_XIAOMI

object ManufacturerSupportUtil {
    private val forceManufacturers = arrayOf(MANUFACTURER_XIAOMI, MANUFACTURER_MEIZU)
    val forceSet: Set<String> = HashSet(listOf(*forceManufacturers))
    private val underMHasPermissionsRequestManufacturer =
        arrayOf(MANUFACTURER_XIAOMI, MANUFACTURER_MEIZU, MANUFACTURER_OPPO)
    val underMSet: Set<String> = HashSet(listOf(*underMHasPermissionsRequestManufacturer))

    /**
     * those manufacturer that need request by some special measures, above
     * [Build.VERSION_CODES.M]
     *
     * @return
     */
    val isForceManufacturer: Boolean
        get() = forceSet.contains(PermissionsPageManager.manufacturer)

    /**
     * those manufacturer that need request permissions under [Build.VERSION_CODES.M],
     * above [Build.VERSION_CODES.LOLLIPOP]
     *
     * @return
     */
    val isUnderMHasPermissionRequestManufacturer: Boolean
        get() = underMSet.contains(PermissionsPageManager.manufacturer)

    val isLocationMustNeedGpsManufacturer: Boolean
        get() = PermissionsPageManager.manufacturer.equals(
            MANUFACTURER_OPPO,
            ignoreCase = true
        )

    /**
     * Build version code is under 6.0 but above 5.0
     *
     * @return
     */
    val isAndroidL: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES
            .LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M

    /**
     * 1.is under [Build.VERSION_CODES.M], above
     * [Build.VERSION_CODES.LOLLIPOP]
     * 2.has permissions check
     * 3.open under check
     *
     *
     * now, we know [PermissionsPageManager.isXIAOMI], [PermissionsPageManager.isMEIZU]
     *
     * @return
     */
    fun isUnderMNeedChecked(isUnderMNeedChecked: Boolean): Boolean {
        return isUnderMHasPermissionRequestManufacturer && isUnderMNeedChecked &&
                isAndroidL
    }
}
