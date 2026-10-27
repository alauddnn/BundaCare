package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.NavTab
import com.example.ui.FiloViewModel
import com.example.ui.components.FiloBottomBar
import com.example.ui.components.FiloTopBar
import com.example.ui.screens.*
import com.example.ui.theme.BgGlobal
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: FiloViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val context = LocalContext.current

                // BackHandler: if not on BERANDA, return to BERANDA first
                BackHandler(enabled = uiState.currentTab != NavTab.BERANDA) {
                    viewModel.setTab(NavTab.BERANDA)
                }

                val topBarTitle = when (uiState.currentTab) {
                    NavTab.BERANDA -> "Filo"
                    NavTab.JURNAL -> "Filo"
                    NavTab.EDUKASI -> "Filo Edukasi"
                    NavTab.MEDIS -> "Filo"
                    NavTab.PROFIL -> "Akun Bunda"
                }

                val topBarSubtitle = when (uiState.currentTab) {
                    NavTab.BERANDA -> "Trimester 2"
                    NavTab.JURNAL -> "Jurnal • W${uiState.gestationalWeek}"
                    NavTab.EDUKASI -> "Trimester 2 • W${uiState.gestationalWeek}"
                    NavTab.MEDIS -> "Trimester 2 • W${uiState.gestationalWeek}"
                    NavTab.PROFIL -> "Trimester 2"
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BgGlobal),
                    topBar = {
                        FiloTopBar(
                            title = topBarTitle,
                            subtitle = topBarSubtitle,
                            avatarUrl = uiState.motherAvatarUrl,
                            onAvatarClick = { viewModel.setTab(NavTab.PROFIL) },
                            onNotificationClick = {
                                Toast.makeText(
                                    context,
                                    "Semua notifikasi pengingat & jadwal kontrol dalam kondisi aktif.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    },
                    bottomBar = {
                        FiloBottomBar(
                            currentTab = uiState.currentTab,
                            onTabSelected = { tab -> viewModel.setTab(tab) }
                        )
                    },
                    containerColor = BgGlobal
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (uiState.currentTab) {
                            NavTab.BERANDA -> HomeScreen(uiState = uiState, viewModel = viewModel)
                            NavTab.JURNAL -> JournalScreen(uiState = uiState, viewModel = viewModel)
                            NavTab.EDUKASI -> EducationScreen(uiState = uiState, viewModel = viewModel)
                            NavTab.MEDIS -> MedicalScreen(uiState = uiState, viewModel = viewModel)
                            NavTab.PROFIL -> ProfileScreen(uiState = uiState, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
