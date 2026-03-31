package com.nsp.wargasigap.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.nsp.wargasigap.ai.DisasterContent
import com.nsp.wargasigap.utils.fetchLocation
import com.nsp.wargasigap.ui.theme.WargaSigapTheme
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyScreen(type: String) {

    val context = LocalContext.current
    var locationStatus by remember { mutableStateOf("") }

// launcher permission
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            locationStatus = "⏳ Mengambil lokasi..."
            fetchLocation(context) { locationStatus = it }
        } else {
            locationStatus = "❌ Izin lokasi ditolak"
        }
    }

    val steps = DisasterContent.DATA[type]
        ?: DisasterContent.DATA["default"]!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {

        Text("Situasi: $type", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        steps.forEach {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (type == "bantuan") {

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationStatus = "⏳ Mengambil lokasi..."
                        fetchLocation(context) { locationStatus = it }

                    } else {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("📍 Kirim Lokasi Saya")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(locationStatus)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyScreenGempaPreview() {
    WargaSigapTheme {
        EmergencyScreen("gempa")
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyScreenPanicPreview() {
    WargaSigapTheme {
        EmergencyScreen("bantuan")
    }
}