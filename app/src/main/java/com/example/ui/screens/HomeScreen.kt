package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.Article
import com.example.data.NavTab
import com.example.ui.FiloUiState
import com.example.ui.FiloViewModel
import com.example.ui.components.InteractiveFetusVisualizer
import com.example.ui.components.WeeklyCalendarStrip
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    uiState: FiloUiState,
    viewModel: FiloViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // 1. Header Greeting & Week Status
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Hai ${uiState.motherNickname}",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = SlateDark
                )
                Text(
                    text = "Janin tumbuh sehat & aktif hari ini",
                    fontFamily = InterFamily,
                    fontSize = 13.sp,
                    color = SlateGray,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SurfaceWhite)
                    .border(1.dp, BorderClean, CircleShape)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(CoralPink)
                    )
                    Text(
                        text = "Minggu ${uiState.gestationalWeek}",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = SlateDark
                    )
                }
            }
        }

        // 2. Weekly Calendar Strip
        WeeklyCalendarStrip()

        // 3. Gestational Age & Countdown Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("pregnancy_countdown_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "USIA KEHAMILAN",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateGray,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Minggu ${uiState.gestationalWeek}, Hari ${uiState.gestationalDays}",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = SlateDark,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "MENUJU PERSALINAN",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateGray,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "${uiState.daysUntilBirth} Hari Lagi",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = CoralPink,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

                // Progress Bar
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Progres Kehamilan",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Text(
                            text = "60%",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = SlateDark
                        )
                    }
                    LinearProgressIndicator(
                        progress = { 0.6f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                            .clip(CircleShape),
                        color = CoralPink,
                        trackColor = BorderClean
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hari Pertama Terakhir (HPHT)",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "HPL: ${uiState.hplDate}",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                        }
                    }
                }
            }
        }

        // 4. Baby Growth Section (Perkembangan Janin)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("baby_growth_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timeline,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "PERKEMBANGAN JANIN • MINGGU ${uiState.gestationalWeek}",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = SlateDark,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Text(
                        text = "Trimester 2",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        color = SlateGray
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // 3D Interactive Fetus Visualizer
                InteractiveFetusVisualizer()

                // Fruit size comparison
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, CircleShape)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "🌽 Ukuran Sebesar Jagung Manis",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateDark
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Si kecil sekarang sudah bisa mendengar detak jantung dan merespons nada lembut suara Bunda.",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        color = SlateGray,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Metrics
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Scale,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Estimasi Berat",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "± 600 ",
                                fontSize = 18.sp,
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateDark
                            )
                            Text(
                                text = "gram",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                color = SlateGray,
                                modifier = Modifier.padding(bottom = 1.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(38.dp)
                            .background(BorderClean)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Straighten,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Panjang Janin",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "30.0 ",
                                fontSize = 18.sp,
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateDark
                            )
                            Text(
                                text = "cm",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                color = SlateGray,
                                modifier = Modifier.padding(bottom = 1.dp)
                            )
                        }
                    }
                }

                // Accordion Trigger
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SlateSurface)
                        .border(1.dp, BorderClean, RoundedCornerShape(12.dp))
                        .clickable { viewModel.toggleOrganAccordion() }
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pencapaian Organ Janin Minggu Ini",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateDark
                        )
                        Icon(
                            imageVector = if (uiState.organAccordionExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Accordion Content
                AnimatedVisibility(visible = uiState.organAccordionExpanded) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MilestoneRow(
                            icon = Icons.Default.Hearing,
                            title = "Sensorik Pendengaran Matang",
                            desc = "Mulai mengenali vokal suara Bunda dan detak ritme jantung Bunda."
                        )
                        HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        MilestoneRow(
                            icon = Icons.Default.Air,
                            title = "Surfaktan Paru-Paru Berkembang",
                            desc = "Kantung udara mulai memproduksi zat surfaktan untuk bernapas pasca lahir."
                        )
                        HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        MilestoneRow(
                            icon = Icons.Default.Visibility,
                            title = "Refleks Kelopak Mata Terbuka",
                            desc = "Mata janin telah terbentuk utuh dan mulai merespons rangsangan cahaya lembut."
                        )
                    }
                }
            }
        }

        // 5. Rutinitas Harian Bunda
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("daily_routine_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Rutinitas Harian Bunda",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Jaga stamina & nutrisi tubuh setiap hari",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, CircleShape)
                            .padding(horizontal = 9.dp, vertical = 3.dp)
                    ) {
                        val completedCount = (if (uiState.waterLiters >= uiState.waterTargetLiters) 1 else 0) +
                                (if (uiState.vitaminCompleted) 1 else 0) +
                                (if (uiState.exerciseSessionActive) 1 else 0)
                        Text(
                            text = "$completedCount dari 3 Selesai",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateGray
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Task 1: Water Tracker
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.WaterDrop,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Column {
                                Text(
                                    text = "Target Hidrasi Air Putih",
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = SlateDark
                                )
                                Text(
                                    text = "Optimal untuk ketuban & sirkulasi",
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                            }
                        }
                        Text(
                            text = "${uiState.waterLiters} L / ${uiState.waterTargetLiters} L",
                            fontSize = 12.sp,
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            color = SlateDark
                        )
                    }

                    val waterProgress = (uiState.waterLiters / uiState.waterTargetLiters).coerceIn(0f, 1f)
                    LinearProgressIndicator(
                        progress = { waterProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = CoralPink,
                        trackColor = BorderClean
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val remainingMl = ((uiState.waterTargetLiters - uiState.waterLiters) * 1000).toInt()
                        Text(
                            text = if (remainingMl > 0) "Kurang $remainingMl ml lagi" else "Target Tercapai ✓",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = if (remainingMl > 0) SlateGray else EmeraldText,
                            fontWeight = if (remainingMl > 0) FontWeight.Normal else FontWeight.SemiBold
                        )

                        Button(
                            onClick = { viewModel.addWater() },
                            modifier = Modifier
                                .height(30.dp)
                                .testTag("add_water_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SlateSurface,
                                contentColor = SlateDark
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                            enabled = uiState.waterLiters < uiState.waterTargetLiters
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "250 ml",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Task 2: Vitamins
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Medication,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Vitamin & TTD (Zat Besi)",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateDark
                            )
                            Text(
                                text = "Diminum pukul 08:00 WIB",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(SlateDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selesai",
                            tint = SurfaceWhite,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Task 3: Exercise
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SelfImprovement,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Senam Relaksasi Panggul",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateDark
                            )
                            Text(
                                text = "Durasi ringan 15 menit",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Button(
                        onClick = { viewModel.toggleExercise() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (uiState.exerciseSessionActive) SlateDark else CoralPink,
                            contentColor = SurfaceWhite
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("exercise_session_button")
                    ) {
                        Text(
                            text = if (uiState.exerciseSessionActive) "Sedang Berjalan" else "Mulai Sesi",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // 6. Fitur Cepat
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fitur Cepat",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = SlateDark
                )
                Text(
                    text = "Lainnya",
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = SlateGray,
                    modifier = Modifier.clickable { viewModel.setTab(NavTab.EDUKASI) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.SportsMartialArts,
                    label = "Hitung Tendangan",
                    onClick = { viewModel.setTab(NavTab.JURNAL) },
                    testTag = "quick_action_kick"
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.EventAvailable,
                    label = "Jadwal Kontrol",
                    onClick = { viewModel.setTab(NavTab.MEDIS) },
                    testTag = "quick_action_medical"
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Forum,
                    label = "Tanya BundaCare",
                    onClick = { viewModel.setTab(NavTab.EDUKASI) },
                    testTag = "quick_action_ai"
                )
            }
        }

        // 7. Edukasi Hari Ini
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Edukasi Hari Ini",
                        fontFamily = PlusJakartaSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = SlateDark
                    )
                    Text(
                        text = "Panduan medis terkurasi untuk Trimester 2",
                        fontSize = 11.sp,
                        fontFamily = InterFamily,
                        color = SlateGray
                    )
                }
                Text(
                    text = "Semua",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = SlateGray,
                    modifier = Modifier.clickable { viewModel.setTab(NavTab.EDUKASI) }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EducationHomeCard(
                    title = "Tips Posisi Tidur Miring ke Kiri untuk Sirkulasi Janin Optimal",
                    author = "dr. Anita, Sp.OG",
                    readTime = "3 Menit Baca",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAabYGZ_fE744FnpJDtI-2qp6x7mE5_cKgKwLqhY_HoQelcV7xni2wedIfqPvAXP6jQMmvH0bMxhJ8j8h8f7FBsyN5tbnBe5MDn41ikBM_UlHAdXrAjXNla_NJfTtXoGpLWVtokJq2vHC8Tx7FGHcFPruuLtNunyv4tFhwpTw--chM_h9hMI-n0_3_DcAEykotPdr7oJWL5zrqy1DWjwbxXjPEa__B-uUXDhiw56n3Q7kFo736WZ5nKbA",
                    onClick = {
                        viewModel.openArticle(
                            Article(
                                id = "art1",
                                title = "Tips Posisi Tidur Miring ke Kiri untuk Sirkulasi Janin Optimal",
                                category = "Kenyamanan Ibu",
                                readTime = "3 Menit Baca",
                                author = "dr. Anita, Sp.OG",
                                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAabYGZ_fE744FnpJDtI-2qp6x7mE5_cKgKwLqhY_HoQelcV7xni2wedIfqPvAXP6jQMmvH0bMxhJ8j8h8f7FBsyN5tbnBe5MDn41ikBM_UlHAdXrAjXNla_NJfTtXoGpLWVtokJq2vHC8Tx7FGHcFPruuLtNunyv4tFhwpTw--chM_h9hMI-n0_3_DcAEykotPdr7oJWL5zrqy1DWjwbxXjPEa__B-uUXDhiw56n3Q7kFo736WZ5nKbA",
                                content = "Tidur dengan posisi miring ke sisi kiri (Left Lateral Decubitus) sangat dianjurkan mulai minggu ke-20 kehamilan. Posisi ini mencegah kompresi vena cava inferior oleh rahim yang membesar, memperlancar perfusi darah ke plasenta, serta mendukung fungsi ginjal dalam membuang sisa metabolisme cairan tubuh."
                            )
                        )
                    }
                )

                EducationHomeCard(
                    title = "Kebutuhan Kalsium & DHA di Usia Kehamilan 24 Minggu",
                    author = "Nutrisionis Ibu & Anak",
                    readTime = "5 Menit Baca",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCcbKje_7eA6WSFt146ezXNGUm-_L9h22PgGCaVKgQ3qfRjE1jj82XseS3wvpSw6YDZNVKEP5heuVZDkVc5zALxm0Mh-HL00ZowCN6SLVk1U3iW6Dyej-Qf43llkE0ZNl4yASq3jW76c65LcXSKXCotoASxhSeU2UuI7eSnPzhgz7xgbt3z47qtcAKeNCqXRcNv0hWylx_TKIPwJzkm-naldgISOkLUfijEon7aJb-HUk1vrFNrITMtEw",
                    onClick = {
                        viewModel.openArticle(
                            Article(
                                id = "art2",
                                title = "Kebutuhan Kalsium & DHA di Usia Kehamilan 24 Minggu",
                                category = "Gizi & Nutrisi",
                                readTime = "5 Menit Baca",
                                author = "Nutrisionis Ibu & Anak",
                                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCcbKje_7eA6WSFt146ezXNGUm-_L9h22PgGCaVKgQ3qfRjE1jj82XseS3wvpSw6YDZNVKEP5heuVZDkVc5zALxm0Mh-HL00ZowCN6SLVk1U3iW6Dyej-Qf43llkE0ZNl4yASq3jW76c65LcXSKXCotoASxhSeU2UuI7eSnPzhgz7xgbt3z47qtcAKeNCqXRcNv0hWylx_TKIPwJzkm-naldgISOkLUfijEon7aJb-HUk1vrFNrITMtEw",
                                content = "Pada usia 24 minggu, kalsium sebanyak 1.000–1.200 mg/hari dibutuhkan untuk mineralisasi tulang janin dan mencegah keropos tulang ibu. Sumber kalsium dapat diperoleh dari susu pasteurisasi, yoghurt, keju matang, tahu, serta ikan salmon panggang yang juga kaya asam lemak DHA untuk perkembangan retina dan sel saraf otak janin."
                            )
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun MilestoneRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(SlateSurface)
                .border(1.dp, BorderClean, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SlateGray,
                modifier = Modifier.size(15.dp)
            )
        }
        Column {
            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold,
                color = SlateDark
            )
            Text(
                text = desc,
                fontSize = 11.sp,
                fontFamily = InterFamily,
                color = SlateGray,
                lineHeight = 15.sp,
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }
}

@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag(testTag),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = SlateDark,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold,
                color = SlateDark,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun EducationHomeCard(
    title: String,
    author: String,
    readTime: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(260.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SlateSurface)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .background(SurfaceWhite.copy(alpha = 0.92f), RoundedCornerShape(6.dp))
                        .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                        .padding(horizontal = 7.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = readTime,
                        fontSize = 10.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = PlusJakartaSansFamily,
                fontWeight = FontWeight.Bold,
                color = SlateDark,
                maxLines = 2,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(thickness = 1.dp, color = BorderClean)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = author,
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    color = SlateGray
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = SlateGray,
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}
