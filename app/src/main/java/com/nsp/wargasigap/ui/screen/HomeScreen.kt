package com.nsp.wargasigap.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nsp.wargasigap.R
import com.nsp.wargasigap.ui.theme.AppFont
import com.nsp.wargasigap.ui.theme.WargaSigapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Home",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                actions = {

                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {

                        DropdownMenuItem(
                            text = { Text("🌍 Info Gempa Real-time") },
                            onClick = {
                                expanded = false
                                navController.navigate("gempa_info")
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("ℹ️ Tentang Aplikasi") },
                            onClick = {
                                expanded = false
                                navController.navigate("about")
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 2.dp)
        ) {

            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.wargasigap_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Warga Sigap",
                        style = TextStyle(
                            fontFamily = AppFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Siap menghadapi situasi darurat",
                        style = TextStyle(
                            fontFamily = AppFont,
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // PRIMARY ACTION
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate("emergency/bantuan") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Text("🚨 Butuh Bantuan Darurat", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // AI
            OutlinedButton(
                onClick = { navController.navigate("chat") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🤖 Tanya AI")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Jenis Bencana", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { navController.navigate("emergency/gempa") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Gempa") }

                    Button(
                        onClick = { navController.navigate("emergency/tsunami") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Tsunami") }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { navController.navigate("emergency/gunung") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Gunung Api") }

                    Button(
                        onClick = { navController.navigate("emergency/longsor") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Longsor") }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { navController.navigate("emergency/banjir") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Banjir") }

                    Button(
                        onClick = { navController.navigate("emergency/kekeringan") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Kekeringan") }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { navController.navigate("emergency/kebakaran") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Kebakaran") }

                    Button(
                        onClick = { navController.navigate("emergency/puting_beliung") },
                        modifier = Modifier.weight(1f)
                    ) { Text("Puting Beliung") }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WargaSigapTheme {
        HomeScreen(rememberNavController())
    }
}