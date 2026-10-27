package com.example.ui.screens

import android.content.Intent
import com.example.data.NavTab
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FiloUiState
import com.example.ui.FiloViewModel
import com.example.ui.theme.*

@Composable
fun JournalScreen(
    uiState: FiloUiState,
    viewModel: FiloViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isEditingNote by remember { mutableStateOf(false) }
    var currentNoteText by remember { mutableStateOf(uiState.dailyJournalNote) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // 1. Header & Date Pill
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Jurnal Harian Bunda",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = SlateDark
                )
                Text(
                    text = "Catat momen, keluhan, dan gerak buah hati",
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = SlateGray,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SurfaceWhite)
                    .border(1.dp, BorderClean, CircleShape)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = SlateGray,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = "23 Sep 2025",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        color = SlateGray
                    )
                }
            }
        }

        // 2. Weekly Selector Strip (Flat Apple Health Style)
        val weekDays = listOf(
            Triple("Min", 21, false),
            Triple("Sen", 22, false),
            Triple("Sel", 23, true),
            Triple("Rab", 24, false),
            Triple("Kam", 25, false),
            Triple("Jum", 26, false)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            weekDays.forEach { (name, date, isSelected) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) SlateDark else SurfaceWhite)
                        .border(1.dp, if (isSelected) SlateDark else BorderClean, RoundedCornerShape(12.dp))
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = name,
                        fontSize = 10.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Medium,
                        color = if (isSelected) SlateLight else SlateSubtle
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "$date",
                        fontSize = 14.sp,
                        fontFamily = PlusJakartaSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) SurfaceWhite else SlateDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) CoralPink else if (name == "Sen") CoralPink else Color.Transparent)
                    )
                }
            }
        }

        // 3. Mood Tracker Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("mood_tracker_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                            imageVector = Icons.Default.SentimentSatisfied,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Kondisi Suasana Hati",
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = SlateDark
                            )
                            Text(
                                text = "Bagaimana perasaan Bunda hari ini?",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SurfaceWhite)
                            .border(1.dp, BorderClean, CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "08:30 WIB",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium,
                            color = SlateGray
                        )
                    }
                }

                // Mood Pills
                val moods = listOf(
                    "Bahagia" to "😊",
                    "Tenang" to "😌",
                    "Lelah" to "🥱",
                    "Cemas" to "🥺",
                    "Sensitif" to "🌸"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    moods.forEach { (moodName, emoji) ->
                        val isChosen = uiState.selectedMood == moodName
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isChosen) SlateDark else SurfaceWhite)
                                .border(1.dp, if (isChosen) SlateDark else BorderClean, RoundedCornerShape(12.dp))
                                .clickable { viewModel.setMood(moodName) }
                                .padding(horizontal = 12.dp, vertical = 7.dp)
                                .testTag("mood_$moodName")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(text = emoji, fontSize = 14.sp)
                                Text(
                                    text = moodName,
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isChosen) SurfaceWhite else SlateGray
                                )
                            }
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Reflection Note
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = SlateSubtle,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "\"Merasa segar setelah jalan pagi santai ✨\"",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        color = SlateGray,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }
        }

        // 4. Tendangan Janin (Kick Counter)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("kick_counter_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Header
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
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = CoralPink,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Tendangan Janin",
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = SlateDark
                            )
                            Text(
                                text = "Target: 10 gerakan dalam 2 jam",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SurfaceWhite)
                            .border(1.dp, BorderClean, CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(EmeraldDot)
                            )
                            Text(
                                text = "Aktif & Sehat",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = EmeraldText
                            )
                        }
                    }
                }

                // Circular Progress Ring & Center Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val progressFraction = (uiState.kickCount.toFloat() / uiState.kickTarget.toFloat()).coerceIn(0f, 1f)

                    Canvas(modifier = Modifier.size(150.dp)) {
                        val strokeWidth = 9.dp.toPx()
                        // Background track
                        drawCircle(
                            color = BorderClean,
                            style = Stroke(width = strokeWidth)
                        )
                        // Active coral arc
                        drawArc(
                            color = CoralPink,
                            startAngle = -90f,
                            sweepAngle = 360f * progressFraction,
                            useCenter = false,
                            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                        )
                    }

                    // Interactive Center Touch Target
                    Box(
                        modifier = Modifier
                            .size(116.dp)
                            .clip(CircleShape)
                            .background(SurfaceWhite)
                            .clickable { viewModel.recordKick() }
                            .testTag("kick_center_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "GERAKAN",
                                fontSize = 9.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateSubtle,
                                letterSpacing = 0.5.sp
                            )
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.padding(vertical = 1.dp)
                            ) {
                                Text(
                                    text = "${uiState.kickCount}",
                                    fontSize = 32.sp,
                                    fontFamily = PlusJakartaSansFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = SlateDark
                                )
                                Text(
                                    text = "/${uiState.kickTarget}",
                                    fontSize = 13.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = SlateSubtle,
                                    modifier = Modifier.padding(bottom = 4.dp, start = 1.dp)
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TouchApp,
                                    contentDescription = null,
                                    tint = CoralPink,
                                    modifier = Modifier.size(11.dp)
                                )
                                Text(
                                    text = "Ketuk Tambah",
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = CoralPink
                                )
                            }
                        }
                    }
                }

                // Telemetry Divider Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Durasi Sesi",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Text(
                            text = "${uiState.kickDurationMinutes} Menit",
                            fontSize = 13.sp,
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateDark,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(30.dp)
                            .background(BorderClean)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Rerata Frekuensi",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Text(
                            text = uiState.kickAvgFrequency,
                            fontSize = 13.sp,
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateDark,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Actions: Rekam Gerakan and Atur Ulang
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { viewModel.recordKick() },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .testTag("record_kick_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = CoralPink, contentColor = SurfaceWhite),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Rekam Gerakan",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = { viewModel.resetKicks() },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .testTag("reset_kick_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.RestartAlt,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Atur Ulang",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // History Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = SlateSubtle,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Kemarin: 10 tendangan dlm 45 mnt",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                    Text(
                        text = "Riwayat >",
                        fontSize = 11.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateDark
                    )
                }
            }
        }

        // 5. Keluhan & Sensasi Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("symptoms_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                            imageVector = Icons.Default.Healing,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Keluhan & Sensasi",
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = SlateDark
                            )
                            Text(
                                text = "${uiState.symptoms.size} keluhan tercatat hari ini",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Button(
                        onClick = { viewModel.setAddSymptomDialog(true) },
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = CircleShape,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier
                            .height(28.dp)
                            .testTag("add_symptom_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Tambah",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Symptom items list
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    uiState.symptoms.forEachIndexed { index, symptom ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val symIcon = when {
                                    symptom.name.contains("Punggung") -> Icons.Default.AirlineSeatReclineExtra
                                    symptom.name.contains("Mual") -> Icons.Default.Sick
                                    symptom.name.contains("Kram") -> Icons.Default.AccessibilityNew
                                    else -> Icons.Default.LocalFireDepartment
                                }
                                Icon(
                                    imageVector = symIcon,
                                    contentDescription = null,
                                    tint = if (symptom.isAlert) AmberText else SlateGray,
                                    modifier = Modifier.size(18.dp)
                                )
                                Column {
                                    Text(
                                        text = symptom.name,
                                        fontSize = 12.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.Medium,
                                        color = SlateDark
                                    )
                                    Text(
                                        text = symptom.description,
                                        fontSize = 11.sp,
                                        fontFamily = InterFamily,
                                        color = SlateGray
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (symptom.isAlert) AmberBg else SlateSurface)
                                    .border(1.dp, if (symptom.isAlert) AmberBorder else BorderClean, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = symptom.severity,
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = if (symptom.isAlert) AmberText else SlateGray
                                )
                            }
                        }

                        if (index < uiState.symptoms.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        }
                    }
                }

                // Alert Warning Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceWhite)
                        .border(1.dp, BorderClean, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = RoseDanger,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 1.dp)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "Perhatian Tanda Bahaya",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                            Text(
                                text = "Jika gerakan janin berkurang (<10x dalam 2 jam), segera hubungi dokter faskes terdekat.",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray,
                                lineHeight = 15.sp
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                modifier = Modifier.clickable { viewModel.setTab(NavTab.MEDIS) }
                            ) {
                                Text(
                                    text = "Konsul Dokter Darurat",
                                    fontSize = 11.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = RoseDanger
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = RoseDanger,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 6. Catatan Hari Ini
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
                            imageVector = Icons.Default.EditNote,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Catatan Hari Ini",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark
                        )
                    }
                    Text(
                        text = if (isEditingNote) "Simpan" else "Edit",
                        fontSize = 11.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = CoralPink,
                        modifier = Modifier.clickable {
                            if (isEditingNote) {
                                viewModel.updateJournalNote(currentNoteText)
                                isEditingNote = false
                            } else {
                                isEditingNote = true
                            }
                        }
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                if (isEditingNote) {
                    OutlinedTextField(
                        value = currentNoteText,
                        onValueChange = { currentNoteText = it },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            color = SlateDark
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CoralPink,
                            unfocusedBorderColor = BorderClean
                        )
                    )
                } else {
                    Text(
                        text = "\"${uiState.dailyJournalNote}\"",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        color = SlateDark,
                        lineHeight = 17.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = SlateSubtle,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "21:15 WIB",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = CoralPink,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Bunda & Ayah",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium,
                            color = SlateGray
                        )
                    }
                }
            }
        }

        // 7. Bagikan Jurnal ke Ayah
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = SlateGray,
                        modifier = Modifier.size(18.dp)
                    )
                    Column {
                        Text(
                            text = "Bagikan Jurnal ke Ayah",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateDark
                        )
                        Text(
                            text = "Kirimkan rangkuman harian via WhatsApp",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                }

                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Halo Ayah! Rangkuman Jurnal Bunda & Si Kecil hari ini (${uiState.gestationalWeek} Minggu): Tendangan ${uiState.kickCount}/${uiState.kickTarget}, hidrasi ${uiState.waterLiters}L. Kondisi: ${uiState.selectedMood}. Catatan: ${uiState.dailyJournalNote}"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Bagikan ke Ayah"))
                    },
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(SurfaceWhite)
                        .border(1.dp, BorderClean, CircleShape)
                        .testTag("share_whatsapp_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Bagikan",
                        tint = SlateDark,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // Add Symptom Dialog
    if (uiState.showAddSymptomDialog) {
        var symptomName by remember { mutableStateOf("") }
        var symptomDesc by remember { mutableStateOf("") }
        var symptomSeverity by remember { mutableStateOf("Ringan") }

        AlertDialog(
            onDismissRequest = { viewModel.setAddSymptomDialog(false) },
            title = {
                Text(
                    text = "Catat Keluhan Baru",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = symptomName,
                        onValueChange = { symptomName = it },
                        label = { Text("Nama Keluhan (mis. Nyeri Panggul)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = symptomDesc,
                        onValueChange = { symptomDesc = it },
                        label = { Text("Keterangan singkat") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Ringan", "Sedang", "Perlu Pantau").forEach { level ->
                            FilterChip(
                                selected = symptomSeverity == level,
                                onClick = { symptomSeverity = level },
                                label = { Text(level, fontSize = 11.sp) }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (symptomName.isNotBlank()) {
                            viewModel.addSymptom(symptomName, symptomDesc, symptomSeverity)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CoralPink)
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.setAddSymptomDialog(false) }) {
                    Text("Batal")
                }
            }
        )
    }
}
