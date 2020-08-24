package com.jizhi.optheme.utils.livepermissions

/**
 * https://github.com/LGD2009/LivePermissions
 * author GaoPC
 * date 2019-10-28 11:41
 * description
 */
sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permissions: Array<String>) : PermissionResult()
    class Rationale(val permissions: Array<String>) : PermissionResult()
}