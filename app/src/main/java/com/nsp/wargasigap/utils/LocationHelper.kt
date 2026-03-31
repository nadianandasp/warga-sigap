package com.nsp.wargasigap.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import java.util.Locale

@SuppressLint("MissingPermission")
fun fetchLocation(context: Context, onResult: (String) -> Unit) {

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.getCurrentLocation(
        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
        null
    ).addOnSuccessListener { location ->

        if (location != null) {

            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

            val addressText = if (!addresses.isNullOrEmpty()) {
                addresses[0].getAddressLine(0)
            } else {
                "Alamat tidak ditemukan"
            }

            onResult(
                """
📍 Lokasi berhasil dikirim!

$addressText

🚑 Tim relawan menuju ke lokasi Anda
""".trimIndent()
            )

        } else {
            onResult("❌ Gagal mengambil lokasi. Coba aktifkan GPS atau buka Maps dulu")
        }
    }
}

