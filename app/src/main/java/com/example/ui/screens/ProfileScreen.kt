package com.example.ui.screens

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.FiloUiState
import com.example.ui.FiloViewModel
import com.example.ui.theme.*

@Composable
fun ProfileScreen(
    uiState: FiloUiState,
    viewModel: FiloViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // 1. Profile Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("profile_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Avatar with Camera Badge
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(1.dp, BorderClean, CircleShape)
                            .background(SlateSurface)
                    ) {
                        AsyncImage(
                            model = uiState.motherAvatarUrl,
                            contentDescription = uiState.motherName,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(SlateDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Ubah Foto",
                            tint = SurfaceWhite,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }

                // Mother Name & Pregnancy info
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = uiState.motherName,
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = SlateDark
                        )
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = CoralPink,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "Kehamilan Pertama • ${uiState.gestationalWeek} Minggu ${uiState.gestationalDays} Hari",
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        color = SlateGray,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // HPL Badge
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, CircleShape)
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "HPL: ${uiState.hplDate}",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                            Box(
                                modifier = Modifier
                                    .size(3.dp)
                                    .clip(CircleShape)
                                    .background(SlateSubtle)
                            )
                            Text(
                                text = "${uiState.daysUntilBirth} Hari Lagi",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = CoralPink
                            )
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Buttons: Ubah Foto & Edit Biodata
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { /* Photo picker */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SlateSurface, contentColor = SlateDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoLibrary,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Ubah Foto",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = { viewModel.setEditProfileDialog(true) },
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .testTag("edit_biodata_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = CoralPink, contentColor = SurfaceWhite),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Edit Biodata",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // 2. Kalibrasi Kehamilan Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "KALIBRASI KEHAMILAN",
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    color = SlateGray,
                    letterSpacing = 0.5.sp
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(EmeraldBg)
                        .border(1.dp, EmeraldDot.copy(alpha = 0.3f), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Tersinkronisasi",
                        fontSize = 10.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Medium,
                        color = EmeraldText
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Stepper: Usia Kehamilan Saat Ini
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Usia Kehamilan Saat Ini",
                                fontSize = 13.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                            Text(
                                text = "Menyesuaikan kalkulasi & janin harian",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }

                        // Stepper control
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, RoundedCornerShape(8.dp))
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .clickable { viewModel.updateGestationalWeek(-1) }
                                    .testTag("week_dec_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Kurang",
                                    tint = SlateGray,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Text(
                                text = "${uiState.gestationalWeek} Minggu",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Bold,
                                color = SlateDark,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(CoralPink)
                                    .clickable { viewModel.updateGestationalWeek(1) }
                                    .testTag("week_inc_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Tambah",
                                    tint = SurfaceWhite,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Pregnancy Type Selector
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tipe Kehamilan",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, RoundedCornerShape(8.dp))
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (!uiState.isTwin) SurfaceWhite else Color.Transparent)
                                    .clickable { viewModel.setTwin(false) }
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = if (!uiState.isTwin) CoralPink else SlateGray,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "Tunggal",
                                        fontSize = 11.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = if (!uiState.isTwin) FontWeight.SemiBold else FontWeight.Medium,
                                        color = if (!uiState.isTwin) SlateDark else SlateGray
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (uiState.isTwin) SurfaceWhite else Color.Transparent)
                                    .clickable { viewModel.setTwin(true) }
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Diversity1,
                                        contentDescription = null,
                                        tint = if (uiState.isTwin) CoralPink else SlateGray,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "Kembar",
                                        fontSize = 11.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = if (uiState.isTwin) FontWeight.SemiBold else FontWeight.Medium,
                                        color = if (uiState.isTwin) SlateDark else SlateGray
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // HPHT Row
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
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Column {
                                Text(
                                    text = "HPHT (Hari Pertama Haid Terakhir)",
                                    fontSize = 11.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                                Text(
                                    text = uiState.hphtDate,
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SlateDark
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.EditCalendar,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Doctor Row
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
                                imageVector = Icons.Default.LocalHospital,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Column {
                                Text(
                                    text = "Dokter Obgyn & Rumah Sakit",
                                    fontSize = 11.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                                Text(
                                    text = "dr. Andini Sp.OG (RSIA Bunda Jakarta)",
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SlateDark
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = SlateSubtle,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Allergy Row
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
                                imageVector = Icons.Default.HealthAndSafety,
                                contentDescription = null,
                                tint = SlateGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Column {
                                Text(
                                    text = "Riwayat Alergi / Kondisi Khusus",
                                    fontSize = 11.sp,
                                    fontFamily = InterFamily,
                                    color = SlateGray
                                )
                                Text(
                                    text = "Tidak ada riwayat alergi obat",
                                    fontSize = 12.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SlateDark
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, CircleShape)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Aman",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }
                }
            }
        }

        // 3. Data Personal Bunda Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "DATA PERSONAL BUNDA",
                fontSize = 11.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                color = SlateGray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    PersonalDataRow(label = "Nama Lengkap", value = "Sarah Amelia Rahmadani")
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    PersonalDataRow(label = "Nama Panggilan", value = uiState.motherNickname)
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    PersonalDataRow(label = "Tanggal Lahir & Usia", value = "14 Mei 1997 (28 Tahun)")
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    PersonalDataRow(label = "Email Terdaftar", value = "sarah.amelia@email.com")
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    PersonalDataRow(label = "Nomor WhatsApp", value = "+62 812-3456-7890")
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Kontak Darurat Suami",
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Dimas Pratama",
                                fontSize = 12.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = SlateDark
                            )
                            Text(
                                text = "+62 811-9876-5432",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Medium,
                                color = CoralPink
                            )
                        }
                    }
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    PersonalDataRow(label = "Alamat Domisili", value = "Jakarta Selatan, DKI Jakarta")
                }
            }
        }

        // 4. Pengingat & Rutinitas Harian Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "PENGINGAT & RUTINITAS HARIAN",
                fontSize = 11.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                color = SlateGray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    // Toggle 1: Vitamin
                    ReminderToggleRow(
                        icon = Icons.Default.Medication,
                        title = "Vitamin Prenatal & TTD",
                        subtitle = "Setiap hari pukul 08:00 WIB",
                        checked = uiState.reminderVitamin,
                        onCheckedChange = { viewModel.toggleReminder("vitamin") }
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Toggle 2: Hidrasi Air Minum
                    ReminderToggleRow(
                        icon = Icons.Default.WaterDrop,
                        title = "Interval Hidrasi Air Minum",
                        subtitle = "Target harian 2.500 ml • Setiap 2 Jam",
                        checked = uiState.reminderHydration,
                        onCheckedChange = { viewModel.toggleReminder("hydration") }
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Toggle 3: Jadwal Kontrol Dokter
                    ReminderToggleRow(
                        icon = Icons.Default.EventAvailable,
                        title = "Jadwal Kontrol Dokter",
                        subtitle = "Notifikasi H-1 dan Hari H",
                        checked = uiState.reminderDoctor,
                        onCheckedChange = { viewModel.toggleReminder("doctor") }
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Toggle 4: Artikel & Tips Pagi
                    ReminderToggleRow(
                        icon = Icons.Default.MenuBook,
                        title = "Artikel & Tips Pagi Bunda",
                        subtitle = "Setiap hari pukul 07:00 WIB",
                        checked = uiState.reminderTips,
                        onCheckedChange = { viewModel.toggleReminder("tips") }
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)

                    // Toggle 5: Suara & Haptik
                    ReminderToggleRow(
                        icon = Icons.Default.Vibration,
                        title = "Suara Lembut & Haptik",
                        subtitle = "Lonceng tenang ramah janin",
                        checked = uiState.soundHaptic,
                        onCheckedChange = { viewModel.toggleReminder("sound") }
                    )
                }
            }
        }

        // 5. Keamanan & Dukungan Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "KEAMANAN & DUKUNGAN",
                fontSize = 11.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                color = SlateGray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    NavigationRow(
                        icon = Icons.Default.CloudSync,
                        title = "Buku KIA Digital & Cadangkan Data",
                        subtitle = "Sinkron otomatis ke Cloud"
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    NavigationRow(
                        icon = Icons.Default.SupportAgent,
                        title = "Hubungi Dukungan BundaCare",
                        subtitle = "Konsultasi teknis respons 24/7"
                    )
                    HorizontalDivider(thickness = 1.dp, color = BorderClean)
                    NavigationRow(
                        icon = Icons.Default.VerifiedUser,
                        title = "Kebijakan Privasi Medis & Enkripsi",
                        subtitle = "Standar keamanan data enkripsi medis"
                    )
                }
            }
        }

        // 6. Log Out & App Version Footer
        Button(
            onClick = { showLogoutDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .testTag("logout_button"),
            colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite, contentColor = RoseDanger),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = null,
                tint = RoseDanger,
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Keluar Akun Bunda",
                fontSize = 12.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "Filo Pregnancy Care v2.4.1 (Build 108) • Dibuat dengan cinta untuk Bunda & Si Kecil",
            fontSize = 11.sp,
            fontFamily = InterFamily,
            color = SlateSubtle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

    // Edit Biodata Dialog
    if (uiState.showEditProfileDialog) {
        var tempName by remember { mutableStateOf(uiState.motherName) }
        var tempNickname by remember { mutableStateOf(uiState.motherNickname) }

        AlertDialog(
            onDismissRequest = { viewModel.setEditProfileDialog(false) },
            title = {
                Text(
                    text = "Edit Biodata Bunda",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = tempName,
                        onValueChange = { tempName = it },
                        label = { Text("Nama Lengkap") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = tempNickname,
                        onValueChange = { tempNickname = it },
                        label = { Text("Nama Panggilan") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.updateProfile(tempName, tempNickname) },
                    colors = ButtonDefaults.buttonColors(containerColor = CoralPink)
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.setEditProfileDialog(false) }) {
                    Text("Batal")
                }
            }
        )
    }

    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = "Keluar dari Akun?",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Text(
                    text = "Data jurnal dan catatan USG telah tersimpan aman di cloud Buku KIA Digital Anda.",
                    fontSize = 12.sp,
                    fontFamily = InterFamily,
                    color = SlateDark
                )
            },
            confirmButton = {
                Button(
                    onClick = { showLogoutDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RoseDanger)
                ) {
                    Text("Keluar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
private fun PersonalDataRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontFamily = InterFamily,
            color = SlateGray
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontFamily = InterFamily,
            fontWeight = FontWeight.SemiBold,
            color = SlateDark
        )
    }
}

@Composable
private fun ReminderToggleRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SlateGray,
                modifier = Modifier.size(18.dp)
            )
            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateDark
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    color = SlateGray
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SurfaceWhite,
                checkedTrackColor = CoralPink,
                uncheckedThumbColor = SurfaceWhite,
                uncheckedTrackColor = BorderClean
            )
        )
    }
}

@Composable
private fun NavigationRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Feature info */ },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SlateGray,
                modifier = Modifier.size(18.dp)
            )
            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateDark
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    color = SlateGray
                )
            }
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = SlateSubtle,
            modifier = Modifier.size(18.dp)
        )
    }
}
