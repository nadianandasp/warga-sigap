package com.nsp.wargasigap.ui.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.unit.dp
import com.nsp.wargasigap.data.remote.RetrofitInstance
import com.nsp.wargasigap.data.model.Gempa
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GempaInfoScreen(navController: NavController) {
    val scope = rememberCoroutineScope()

    var gempaList by remember { mutableStateOf<List<Gempa>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            val response = RetrofitInstance.api.getGempaList()
            gempaList = response.Infogempa.gempa
            errorMessage = null
        } catch (e: Exception) {
            errorMessage = "📡 Tidak ada koneksi internet\nSilakan aktifkan data seluler atau WiFi"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Info Gempa Real-time",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            when {
                isLoading -> {
                    Text("⏳ Memuat data gempa...")
                }

                errorMessage != null -> {
                    Column {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(onClick = {
                            errorMessage = null
                            isLoading = true

                            scope.launch {
                                fetchGempa(
                                    onSuccess = {
                                        gempaList = it
                                        errorMessage = null
                                    },
                                    onError = {
                                        errorMessage = "📡 Tidak ada koneksi internet"
                                    }
                                )
                                isLoading = false
                            }
                        }) {
                            Text("🔄 Coba Lagi")
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(gempaList) { gempa ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("📌 Lokasi: ${gempa.Wilayah}")
                                    Text("📊 Magnitude: ${gempa.Magnitude}")
                                    Text("🕒 Waktu: ${gempa.Tanggal} ${gempa.Jam}")
                                    Text("📏 Kedalaman: ${gempa.Kedalaman}")
                                    Text("⚠️ Potensi: ${gempa.Potensi}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun fetchGempa(
    onSuccess: (List<Gempa>) -> Unit,
    onError: () -> Unit
) {
    try {
        val response = RetrofitInstance.api.getGempaList()
        onSuccess(response.Infogempa.gempa)
    } catch (e: Exception) {
        onError()
    }
}