package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.UsgScan
import com.example.ui.FiloUiState
import com.example.ui.FiloViewModel
import com.example.ui.theme.*

@Composable
fun MedicalScreen(
    uiState: FiloUiState,
    viewModel: FiloViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isAddingQuestion by remember { mutableStateOf(false) }
    var newQuestionInput by remember { mutableStateOf("") }
    var selectedUsgToView by remember { mutableStateOf<UsgScan?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Page Header
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(EmeraldBg)
                        .border(1.dp, EmeraldDot.copy(alpha = 0.3f), CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldText,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "Buku KIA Digital Aktif",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = EmeraldText
                        )
                    }
                }

                Text(
                    text = "Minggu 24 (Bulan 6)",
                    fontSize = 12.sp,
                    fontFamily = InterFamily,
                    color = SlateGray
                )
            }

            Text(
                text = "Jadwal & Catatan Medis",
                fontFamily = PlusJakartaSansFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = SlateDark
            )
            Text(
                text = "Pemeriksaan rutin kehamilan Trimester 2",
                fontFamily = InterFamily,
                fontSize = 12.sp,
                color = SlateGray
            )
        }

        // Section 1: Doctor Appointment Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("appointment_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(CoralPink)
                        )
                        Text(
                            text = "KUNJUNGAN TERDEKAT",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = SlateGray,
                            letterSpacing = 0.5.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "5 Hari Lagi",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Medium,
                                color = SlateDark
                            )
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Doctor Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBaQTCq94ySPQtsIpAE5B0uBNpupsI1n7DwN11CFh8dCKyZXRXSqajvrB5UsdEb0k5-7a1IXu1-NplL6l-GfX-7syw1Ney_aNy5UOkFrtPhPWNd_qNIzpt_qgzD1HBWhVao_zA61qmmTEgBrhJfS26ZacCC_5K2bCGlX9SZ5J7AuhGDr-hDd_w64PTtGmp7XQq8y5KwW82a9peQRoQr5lxbI3Die6tXTR1l8R8cNXumq16VU_c8O_9hjw",
                        contentDescription = "dr. Andini, Sp.OG",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(1.dp, BorderClean, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "dr. Andini, Sp.OG",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Konsultan Fetomaternal",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = SlateSubtle,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "RSIA Bunda Jakarta • Kebidanan Lt. 3",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Time & Queue
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
                            imageVector = Icons.Default.Event,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Kamis, 28 September 2025 • 10:30 WIB",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                            Text(
                                text = "USG Skrining & Konsultasi Rutin",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Antrean #04",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = SlateDark
                        )
                    }
                }

                // Action Buttons
                Button(
                    onClick = { viewModel.setTicketDialog(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .testTag("ticket_detail_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = CoralPink, contentColor = SurfaceWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ConfirmationNumber,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Detail Janji Temu & E-Tiket",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=RSIA+Bunda+Jakarta"))
                            context.startActivity(mapIntent)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .testTag("directions_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Directions,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Petunjuk Arah",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = { viewModel.syncCalendar() },
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .testTag("sync_calendar_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = if (uiState.isCalendarSynced) Icons.Default.Check else Icons.Default.EventAvailable,
                            contentDescription = null,
                            tint = if (uiState.isCalendarSynced) EmeraldText else SlateGray,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (uiState.isCalendarSynced) "Tersimpan ✓" else "Sync Kalender",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium,
                            color = if (uiState.isCalendarSynced) EmeraldText else SlateDark
                        )
                    }
                }
            }
        }

        // Section 2: Pemantauan Mandiri - Skrining Gejala Trimester 2
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("screening_card"),
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
                    Column {
                        Text(
                            text = "PEMANTAUAN MANDIRI",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = SlateGray,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Skrining Gejala Trimester 2",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        val checkedCount = uiState.screeningItems.count { it.isChecked }
                        Text(
                            text = "$checkedCount dari 5 Selesai",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium,
                            color = SlateGray
                        )
                    }
                }

                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(CircleShape),
                    color = SlateDark,
                    trackColor = BorderClean
                )

                Text(
                    text = "Tandai jika Bunda merasakan gejala waspada dalam 48 jam terakhir:",
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    color = SlateGray
                )

                // Checklist Items
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    uiState.screeningItems.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.toggleScreening(item.id) }
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Checkbox(
                                checked = item.isChecked,
                                onCheckedChange = { viewModel.toggleScreening(item.id) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = CoralPink,
                                    uncheckedColor = SlateSubtle
                                ),
                                modifier = Modifier.size(20.dp)
                            )
                            Column {
                                Text(
                                    text = item.title,
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = SlateDark
                                )
                                Text(
                                    text = item.subtitle,
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray,
                                    modifier = Modifier.padding(top = 1.dp)
                                )
                            }
                        }

                        if (index < uiState.screeningItems.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Footer
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
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "Hasil 20 Sep: Normal",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }

                    Button(
                        onClick = { viewModel.saveScreening() },
                        colors = ButtonDefaults.buttonColors(containerColor = SlateSurface, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("save_screening_button")
                    ) {
                        Icon(
                            imageVector = if (uiState.screeningSaved) Icons.Default.Check else Icons.Default.Save,
                            contentDescription = null,
                            tint = if (uiState.screeningSaved) EmeraldText else SlateGray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (uiState.screeningSaved) "Tersimpan" else "Simpan Data",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium,
                            color = if (uiState.screeningSaved) EmeraldText else SlateDark
                        )
                    }
                }
            }
        }

        // Section 3: Tanya Dokter (Persiapan Konsultasi 28 Sep)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("doctor_questions_card"),
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
                    Column {
                        Text(
                            text = "Tanya Dokter",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Persiapan Konsultasi 28 Sep",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }

                    Button(
                        onClick = { isAddingQuestion = !isAddingQuestion },
                        colors = ButtonDefaults.buttonColors(containerColor = SlateSurface, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                        modifier = Modifier.height(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
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

                // Inline Add Question Input
                AnimatedVisibility(visible = isAddingQuestion) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        OutlinedTextField(
                            value = newQuestionInput,
                            onValueChange = { newQuestionInput = it },
                            placeholder = { Text("Tulis pertanyaan untuk dr. Andini...", fontSize = 12.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = LocalTextStyle.current.copy(fontSize = 12.sp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(onClick = { isAddingQuestion = false }) {
                                Text("Batal", fontSize = 11.sp, color = SlateGray)
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Button(
                                onClick = {
                                    if (newQuestionInput.isNotBlank()) {
                                        viewModel.addQuestion(newQuestionInput)
                                        newQuestionInput = ""
                                        isAddingQuestion = false
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = SlateDark),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Text("Simpan", fontSize = 11.sp)
                            }
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Questions List
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    uiState.doctorQuestions.forEachIndexed { index, q ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.toggleQuestion(q.id) }
                                .padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(if (q.isDone) EmeraldDot else Color.Transparent)
                                    .border(1.dp, if (q.isDone) EmeraldDot else SlateSubtle, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (q.isDone) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = SurfaceWhite,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = q.question,
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = if (q.isDone) FontWeight.Normal else FontWeight.Medium,
                                    color = if (q.isDone) SlateGray else SlateDark,
                                    textDecoration = if (q.isDone) TextDecoration.LineThrough else TextDecoration.None
                                )
                                Text(
                                    text = q.note,
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = if (q.isPriority) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (q.isPriority) AmberText else SlateGray,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        if (index < uiState.doctorQuestions.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        }
                    }
                }
            }
        }

        // Section 4: Riwayat & Rekam USG
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("usg_records_card"),
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
                    Column {
                        Text(
                            text = "Riwayat & Rekam USG",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Arsip digital sonografi & lab",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.FolderOpen,
                        contentDescription = null,
                        tint = SlateGray,
                        modifier = Modifier.size(18.dp)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                uiState.usgScans.forEachIndexed { index, scan ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedUsgToView = scan },
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Black)
                            ) {
                                AsyncImage(
                                    model = scan.imageUrl,
                                    contentDescription = scan.title,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = scan.title,
                                        fontSize = 12.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SlateDark
                                    )
                                    Text(
                                        text = scan.status,
                                        fontSize = 10.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = EmeraldText
                                    )
                                }
                                Text(
                                    text = scan.dateHospital,
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                                Row(
                                    modifier = Modifier.padding(top = 3.dp),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(SlateSurface)
                                            .border(1.dp, BorderClean, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(text = scan.metric1, fontSize = 9.sp, color = SlateGray)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(SlateSurface)
                                            .border(1.dp, BorderClean, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(text = scan.metric2, fontSize = 9.sp, color = SlateGray)
                                    }
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                modifier = Modifier.clickable { selectedUsgToView = scan }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FileDownload,
                                    contentDescription = null,
                                    tint = SlateGray,
                                    modifier = Modifier.size(13.dp)
                                )
                                Text(
                                    text = "Lihat & Unduh PDF",
                                    fontSize = 11.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                            }
                        }
                    }

                    if (index < uiState.usgScans.lastIndex) {
                        HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    }
                }
            }
        }

        // Section 5: Bantuan Siaga Medis 24 Jam
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:02131922005"))
                    context.startActivity(phoneIntent)
                },
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SlateSurface)
                        .border(1.dp, BorderClean, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Emergency,
                        contentDescription = null,
                        tint = SlateDark,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Bantuan Siaga Medis 24 Jam",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateDark
                    )
                    Text(
                        text = "IGD Kebidanan RSIA Bunda: 021-3192-2005",
                        fontSize = 11.sp,
                        fontFamily = InterFamily,
                        color = SlateGray
                    )
                }

                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = CoralPink,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // E-Ticket & Appointment Detail Dialog
    if (uiState.showTicketDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setTicketDialog(false) },
            title = {
                Text(
                    text = "E-Tiket Janji Temu Medis",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "dr. Andini, Sp.OG (Konsultan Fetomaternal)",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = SlateDark
                    )
                    Text(
                        text = "RSIA Bunda Jakarta • Lantai 3 Kebidanan",
                        fontSize = 12.sp,
                        color = SlateGray
                    )
                    Text(
                        text = "Waktu: Kamis, 28 September 2025 • 10:30 WIB",
                        fontSize = 12.sp,
                        color = SlateDark
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SlateSurface)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "NOMOR ANTREAN",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                color = SlateGray,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "#04",
                                fontSize = 32.sp,
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                color = CoralPink
                            )
                            Text(
                                text = "Kode Booking: BND-20250928-883",
                                fontSize = 10.sp,
                                color = SlateSubtle
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.setTicketDialog(false) },
                    colors = ButtonDefaults.buttonColors(containerColor = CoralPink)
                ) {
                    Text("Tutup")
                }
            }
        )
    }

    // USG Scan Viewer Modal
    if (selectedUsgToView != null) {
        AlertDialog(
            onDismissRequest = { selectedUsgToView = null },
            title = {
                Text(
                    text = selectedUsgToView?.title ?: "Rekam USG",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Black)
                    ) {
                        AsyncImage(
                            model = selectedUsgToView?.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Text(
                        text = "${selectedUsgToView?.dateHospital} • Status: ${selectedUsgToView?.status}",
                        fontSize = 12.sp,
                        color = SlateDark
                    )
                    Text(
                        text = "Hasil parameter: ${selectedUsgToView?.metric1}, ${selectedUsgToView?.metric2}. Struktur anatomi janin teramati sesuai dengan usia gestasi.",
                        fontSize = 11.sp,
                        color = SlateGray
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedUsgToView = null },
                    colors = ButtonDefaults.buttonColors(containerColor = SlateDark)
                ) {
                    Text("Selesai")
                }
            }
        )
    }
}
