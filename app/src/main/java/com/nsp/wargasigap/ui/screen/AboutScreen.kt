package com.nsp.wargasigap.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nsp.wargasigap.ui.theme.WargaSigapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Tentang Aplikasi",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Warga Sigap adalah aplikasi tanggap darurat berbasis mobile yang dirancang untuk membantu masyarakat menghadapi situasi bencana dengan cepat, tepat, dan terarah.\n")
            Text("Aplikasi ini hadir sebagai solusi sederhana namun berdampak, terutama bagi masyarakat yang membutuhkan panduan instan dan akses bantuan dalam kondisi darurat.\n")
            Text("Fitur Utama", style = MaterialTheme.typography.titleMedium)
            Text("""
1. 🚨 Butuh Bantuan Darurat
Mengirimkan lokasi pengguna secara real-time untuk mempercepat penanganan oleh relawan.

2. 🤖 Tanya AI
Memberikan panduan berdasarkan kondisi yang dialami pengguna secara cepat.

3. 📖 Edukasi Bencana
Menyediakan langkah-langkah penanganan untuk berbagai jenis bencana seperti banjir dan gempa.
   
4. 🌍 Info Gempa Real-time (BMKG)
Menampilkan data gempa terbaru secara langsung dari BMKG:
   - Informasi lokasi gempa
   - Magnitude dan kedalaman
   - Waktu kejadian
   - Potensi dampak
   - Diperbarui secara berkala
        """.trimIndent())

            Spacer(modifier = Modifier.height(16.dp))

            Text("Keunggulan", style = MaterialTheme.typography.titleMedium)
            Text("""
1. Cepat dan ringan digunakan
2. Tidak bergantung pada koneksi internet untuk fitur dasar (AI lokal)
3. Fokus pada kebutuhan masyarakat Indonesia
4. Dirancang dengan antarmuka sederhana dan mudah dipahami
        """.trimIndent())

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tujuan", style = MaterialTheme.typography.titleMedium)
            Text("Tujuan utama WargaSigap adalah membantu masyarakat agar lebih siap menghadapi situasi darurat serta mengurangi risiko korban akibat keterlambatan penanganan.\n")

            Text(
                """
Dengan adanya aplikasi ini, diharapkan masyarakat dapat:
- Bertindak lebih cepat dalam kondisi darurat
- Mengurangi kepanikan
- Meningkatkan keselamatan diri dan orang sekitar

WargaSigap bukan hanya aplikasi, tetapi langkah kecil menuju masyarakat yang lebih tanggap dan siap menghadapi bencana.
""".trimIndent()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    WargaSigapTheme {
        AboutScreen(rememberNavController())
    }
}