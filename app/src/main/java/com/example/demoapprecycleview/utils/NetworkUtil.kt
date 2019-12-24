package com.commonlibrary.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

object NetworkUtil {

    val NOT_CONNECTED = 0
    val WIFI = 1
    val MOBILE = 2

    fun getConnectionStatus(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        @SuppressLint("MissingPermission") val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return WIFI
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return MOBILE
        }
        return NOT_CONNECTED
    }

    fun getConnectionStatusString(context: Context): String {
        val connectionStatus = NetworkUtil.getConnectionStatus(context)
        if (connectionStatus == NetworkUtil.WIFI)
            return "Connected to Wifi"
        return if (connectionStatus == NetworkUtil.MOBILE) "Connected to Mobile Data" else "No internet connection"
    }

    fun getWifiName(context: Context): String {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var wifiName: String? = wifiManager.connectionInfo.ssid
        if (wifiName != null) {
            if (!wifiName.contains("unknown ssid") && wifiName.length > 2) {
                if (wifiName.startsWith("\"") && wifiName.endsWith("\""))
                    wifiName = wifiName.subSequence(1, wifiName.length - 1).toString()
                return wifiName
            } else {
                return ""
            }
        } else {
            return ""
        }
    }

    fun getMobileNetworkName(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkName = tm.networkOperatorName
        return networkName ?: ""
    }

    fun getGateway(context: Context): String {
        val wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return NetworkUtil.intToIp(wm.dhcpInfo.gateway)
    }

    fun intToIp(i: Int): String {
        return (i shr 24 and 0xFF).toString() + "." +
                (i shr 16 and 0xFF) + "." +
                (i shr 8 and 0xFF) + "." +
                (i and 0xFF)
    }

    fun isGPSEnabled(mContext: Context): Boolean {
        val locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}