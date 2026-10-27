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
import com.example.data.FoodItem
import com.example.data.FoodStatus
import com.example.ui.FiloUiState
import com.example.ui.FiloViewModel
import com.example.ui.theme.*

@Composable
fun EducationScreen(
    uiState: FiloUiState,
    viewModel: FiloViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var inputQueryText by remember { mutableStateOf("") }
    var showScheduleConsultationDialog by remember { mutableStateOf(false) }

    val masterFoodList = remember {
        listOf(
            FoodItem("f1", "Telur Matang Sempurna", "Kaya kolin dan asam amino untuk pembentukan jaringan otak janin.", FoodStatus.AMAN, "telur matang rebus protein kolin"),
            FoodItem("f2", "Salmon Panggang Matang", "Sumber Omega-3 tinggi dengan kadar merkuri rendah teruji aman.", FoodStatus.AMAN, "salmon ikan omega dha epa"),
            FoodItem("f3", "Keju Mentah (Brie, Feta Lunak)", "Beresiko transmisi bakteri Listeria monocytogenes.", FoodStatus.HINDARI, "keju mentah susu unpasteurized listeria brie feta"),
            FoodItem("f4", "Daging & Ikan Mentah (Sushi)", "Potensi infeksi parasit Toxoplasma dan infeksi Salmonella.", FoodStatus.HINDARI, "sushi sashimi daging mentah steak rare toksoplasma"),
            FoodItem("f5", "Pepaya Mentah / Mengkal", "Kandungan getah lateks papain tinggi berisiko stimulasi kontraksi dini.", FoodStatus.BATASI, "pepaya mentah mengkal getah lateks"),
            FoodItem("f6", "Madu Alami Terpasteurisasi", "Meredakan radang tenggorokan alami dan meningkatkan daya tahan tubuh.", FoodStatus.AMAN, "madu lebah energi"),
            FoodItem("f7", "Kopi & Minuman Berkafein", "Batasi konsumsi di bawah 200 mg kafein (setara 1 cangkir kecil sehari).", FoodStatus.BATASI, "kopi teh kafein boba"),
            FoodItem("f8", "Sayuran Mentah Belum Dicuci", "Wajib dicuci bersih air mengalir untuk menghilangkan residu tanah & parasit.", FoodStatus.HINDARI, "sayur mentah lalapan selada")
        )
    }

    val filteredFoods = remember(uiState.foodSearchQuery) {
        val q = uiState.foodSearchQuery.trim().lowercase()
        if (q.isEmpty()) masterFoodList
        else masterFoodList.filter { it.name.lowercase().contains(q) || it.keywords.lowercase().contains(q) || it.description.lowercase().contains(q) }
    }

    val articlesList = remember {
        listOf(
            Article(
                id = "art_cardiff",
                title = "Panduan Menghitung Gerakan Janin dengan Metode Cardiff 10",
                category = "Perkembangan Janin",
                readTime = "4 Mnt",
                author = "dr. Sp.OG Konsultan",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDWnM_lvIeyzM-bthcA9UFj7cCKGW8-_TalH0xoCJADYVjksctxbIH3yckxa6gmOGAcFmc46E4cRyonwUXMLTtrQhDpASh6sV3XcLvoX7_bzMKc7Mt4KcAARH-Yn4QHrN-t41dGSNe5vtqX7HrojCOm0m18I0f9u_alIckoElERJgCpREHxQhlEVgN3wr6icfnseEzCMxkkFX9GDCOiduICP02e51R25faNRBql_uEHyViFDMTxvCNPJA",
                content = "Metode Cardiff Count-to-10 adalah standar klinis untuk memantau kesejahteraan janin secara mandiri. Mulai menghitung saat ibu dalam posisi tenang dan santai (misalnya setelah sarapan atau malam hari). Hitung berapa lama waktu yang diperlukan hingga tercapai 10 gerakan yang jelas (tendangan, gulungan, atau sentakan). Jika dalam 2 jam belum tercapai 10 gerakan, segera hubungi bidan atau dokter kandungan Anda."
            ),
            Article(
                id = "art_kram",
                title = "Mengatasi Kram Otot Kaki Saat Tidur Malam di Trimester 2",
                category = "Nutrisi",
                readTime = "3 Mnt",
                author = "Fisioterapis Maternal RSIA",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuClSJhApQw3bNJJKTWckRP3L8pAbd_Y7LlfTErv0mP4kC2Ua-o8Hn1gKCrVj7IIX3MejCS1Nl-rumCsNqsnfvANlLOouT-FjIYOC1SnFD0Q5aMQCmCCarpRKEMnbVOMfLwWPa9kfG8xWQpz_NU8u6T8SyIJeGb00_OPCSDv0yqyT97lhINa3PQfHo2SMfO7cvGezSDHkKh53Eoe0dScHmuyaRtl6cBWh8GhlNinNVk55_rOR_DOjJl5hA",
                content = "Kram betis di malam hari terjadi karena akumulasi asam laktat serta ketidakseimbangan elektrolit kalsium dan magnesium. Saat kram terjadi: luruskan kaki dan tekuk pergelangan kaki ke atas ke arah tulang kering (dorsofleksi). Lakukan pijatan lembut ke arah jantung dan kompres hangat."
            ),
            Article(
                id = "art_hb",
                title = "Daftar Makanan Penambah Hemoglobin untuk Cegah Anemia Ibu Hamil",
                category = "Nutrisi",
                readTime = "5 Mnt",
                author = "Ahli Gizi Klinis RSIA",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBsP332cRQ0zCYOiiF-IztV_iZDTJ191UgZ5sORpKXdSgdx9KIW8-G6stVEfPaoaOM1TSarEFPkIZSRNgu2x8GaUBRZnBnHIO66cEV6k5Wc40iO8h_RtBrzQg36DyDNov0IK3_m_xMi-TYC2oeafX_DaaCIJz0YSa8QFssmqyHbfs8F4KN2DkPUypiLlVkl_L-ryeHBlFttTQYLZtOsuiixYUbGiTE0hRBF8K_BiPYAbLjI1Oukh3y6cg",
                content = "Anemia defisiensi besi dapat meningkatkan risiko kelelahan ekstrem dan kelahiran prematur. Penuhi asupan zat besi dari hati ayam bersih, daging sapi tanpa lemak, bayam hijau, brokoli, dan kurma. Konsumsi bersama buah tinggi vitamin C seperti jeruk, stroberi, atau kiwi untuk melipatgandakan tingkat penyerapan zat besi."
            )
        )
    }

    val filteredArticles = remember(uiState.selectedArticleTab) {
        if (uiState.selectedArticleTab == "Semua") articlesList
        else articlesList.filter { it.category == uiState.selectedArticleTab }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Screen Header
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = EmeraldDot,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "KLINIS & TERVERIFIKASI MEDIS",
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    color = SlateGray,
                    letterSpacing = 0.5.sp
                )
            }
            Text(
                text = "Pusat Edukasi & AI",
                fontFamily = PlusJakartaSansFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = SlateDark
            )
            Text(
                text = "Panduan klinis obstetri terpadu dan asisten tanya jawab kesehatan ibu hamil 24/7.",
                fontSize = 12.sp,
                fontFamily = InterFamily,
                color = SlateGray,
                lineHeight = 16.sp
            )
        }

        // 1. BundaCare AI Chat Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_assistant_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SmartToy,
                                contentDescription = null,
                                tint = SlateDark,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "BundaCare AI",
                                    fontFamily = PlusJakartaSansFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = SlateDark
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(EmeraldBg)
                                        .padding(horizontal = 5.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "Aktif",
                                        fontSize = 9.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = EmeraldText
                                    )
                                }
                            }
                            Text(
                                text = "Spesialis Maternal & Kebidanan",
                                fontSize = 10.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }

                    Text(
                        text = "Model v2.4",
                        fontSize = 10.sp,
                        fontFamily = InterFamily,
                        color = SlateGray
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Chat Messages Stream
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    uiState.chatMessages.forEach { msg ->
                        if (msg.isUser) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .widthIn(max = 280.dp)
                                        .clip(RoundedCornerShape(14.dp, 2.dp, 14.dp, 14.dp))
                                        .background(SlateDark)
                                        .padding(12.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = msg.text,
                                            fontSize = 12.sp,
                                            fontFamily = InterFamily,
                                            color = SurfaceWhite,
                                            lineHeight = 16.sp
                                        )
                                        Text(
                                            text = msg.time,
                                            fontSize = 9.sp,
                                            fontFamily = InterFamily,
                                            color = SlateSubtle,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SlateSurface)
                                        .border(1.dp, BorderClean, RoundedCornerShape(6.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = SlateGray,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(2.dp, 14.dp, 14.dp, 14.dp))
                                        .background(SlateSurface)
                                        .border(1.dp, BorderClean, RoundedCornerShape(2.dp, 14.dp, 14.dp, 14.dp))
                                        .padding(12.dp)
                                ) {
                                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                        Text(
                                            text = msg.text,
                                            fontSize = 12.sp,
                                            fontFamily = InterFamily,
                                            color = SlateDark,
                                            lineHeight = 17.sp
                                        )
                                        if (msg.isValidated) {
                                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.CheckCircle,
                                                    contentDescription = null,
                                                    tint = EmeraldDot,
                                                    modifier = Modifier.size(12.dp)
                                                )
                                                Text(
                                                    text = "Divalidasi Konsil Obstetri & Ginekologi",
                                                    fontSize = 10.sp,
                                                    fontFamily = InterFamily,
                                                    color = SlateGray
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (uiState.isAiTyping) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(start = 32.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(12.dp),
                                strokeWidth = 2.dp,
                                color = CoralPink
                            )
                            Text(
                                text = "BundaCare AI sedang mengetik jawaban klinis...",
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }
                }

                // Quick Suggestion Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Posisi tidur", "Atasi mual", "Zat besi harian", "Kram kaki").forEach { prompt ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SlateSurface)
                                .border(1.dp, BorderClean, RoundedCornerShape(8.dp))
                                .clickable {
                                    inputQueryText = prompt
                                    viewModel.sendAiPrompt(prompt)
                                }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = prompt,
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                color = SlateGray
                            )
                        }
                    }
                }

                // Input Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputQueryText,
                        onValueChange = { inputQueryText = it },
                        placeholder = { Text("Tanyakan kondisi atau asupan gizi...", fontSize = 12.sp) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("ai_chat_input"),
                        textStyle = LocalTextStyle.current.copy(fontSize = 12.sp, fontFamily = InterFamily),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Suara",
                                tint = SlateGray,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CoralPink,
                            unfocusedBorderColor = BorderClean,
                            focusedContainerColor = SurfaceWhite,
                            unfocusedContainerColor = SlateSurface
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Button(
                        onClick = {
                            if (inputQueryText.isNotBlank()) {
                                viewModel.sendAiPrompt(inputQueryText)
                                inputQueryText = ""
                            }
                        },
                        modifier = Modifier
                            .size(46.dp)
                            .testTag("ai_send_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = CoralPink),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Kirim",
                            tint = SurfaceWhite,
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }
            }
        }

        // 2. Cek Keamanan Makanan
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("food_safety_card"),
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
                            text = "Cek Keamanan Makanan",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Indeks nutrisi medis & batas aman konsumsi",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Standar BPOM",
                            fontSize = 10.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }
                }

                // Search Bar
                OutlinedTextField(
                    value = uiState.foodSearchQuery,
                    onValueChange = { viewModel.setFoodQuery(it) },
                    placeholder = { Text("Cari bahan pangan (telur, susu, salmon)...", fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("food_search_input"),
                    textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    trailingIcon = {
                        if (uiState.foodSearchQuery.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Hapus",
                                tint = SlateGray,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable { viewModel.setFoodQuery("") }
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CoralPink,
                        unfocusedBorderColor = BorderClean,
                        focusedContainerColor = SurfaceWhite,
                        unfocusedContainerColor = SlateSurface
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Food Items List
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    filteredFoods.forEachIndexed { index, food ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                val (statusIcon, iconColor) = when (food.status) {
                                    FoodStatus.AMAN -> Icons.Default.CheckCircle to EmeraldDot
                                    FoodStatus.HINDARI -> Icons.Default.Cancel to RoseDanger
                                    FoodStatus.BATASI -> Icons.Default.ErrorOutline to AmberText
                                }
                                Icon(
                                    imageVector = statusIcon,
                                    contentDescription = null,
                                    tint = iconColor,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(top = 1.dp)
                                )
                                Column {
                                    Text(
                                        text = food.name,
                                        fontSize = 12.sp,
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SlateDark
                                    )
                                    Text(
                                        text = food.description,
                                        fontSize = 11.sp,
                                        fontFamily = InterFamily,
                                        color = SlateGray,
                                        lineHeight = 15.sp,
                                        modifier = Modifier.padding(top = 1.dp)
                                    )
                                }
                            }

                            val (badgeBg, badgeText) = when (food.status) {
                                FoodStatus.AMAN -> EmeraldBg to EmeraldText
                                FoodStatus.HINDARI -> RoseBg to RoseDanger
                                FoodStatus.BATASI -> AmberBg to AmberText
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(badgeBg)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = food.status.name,
                                    fontSize = 10.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = badgeText
                                )
                            }
                        }

                        if (index < filteredFoods.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        }
                    }
                }
            }
        }

        // 3. Koleksi Artikel Pilihan
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("articles_card"),
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
                            text = "Koleksi Artikel Pilihan",
                            fontFamily = PlusJakartaSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SlateDark
                        )
                        Text(
                            text = "Tinjauan klinis berkala dari dokter spesialis",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            color = SlateGray
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.clickable { viewModel.setArticleTab("Semua") }
                    ) {
                        Text(
                            text = "Lihat Semua",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = CoralPink
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = CoralPink,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }

                // Category Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Semua", "Nutrisi", "Perkembangan Janin", "Persiapan Persalinan").forEach { tab ->
                        val isSelected = uiState.selectedArticleTab == tab
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) SlateDark else SlateSurface)
                                .clickable { viewModel.setArticleTab(tab) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = tab,
                                fontSize = 11.sp,
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.Medium,
                                color = if (isSelected) SurfaceWhite else SlateGray
                            )
                        }
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = BorderClean)

                // Articles List
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    filteredArticles.forEachIndexed { index, art ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { viewModel.openArticle(art) }
                                .padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(62.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SlateSurface)
                            ) {
                                AsyncImage(
                                    model = art.imageUrl,
                                    contentDescription = art.title,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${art.category.uppercase()} • ${art.readTime.uppercase()}",
                                    fontSize = 9.sp,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SlateGray,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = art.title,
                                    fontSize = 12.sp,
                                    fontFamily = PlusJakartaSansFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = SlateDark,
                                    maxLines = 2,
                                    lineHeight = 16.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.padding(top = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = null,
                                        tint = EmeraldDot,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = art.author,
                                        fontSize = 11.sp,
                                        fontFamily = InterFamily,
                                        color = SlateGray
                                    )
                                }
                            }
                        }

                        if (index < filteredArticles.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = BorderClean)
                        }
                    }
                }
            }
        }

        // 4. Clinical Teleconsultation Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderClean)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "KONSULTASI MEDIS LANGSUNG",
                        fontSize = 10.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Bold,
                        color = SlateGray,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Butuh evaluasi dokter spesialis obgyn?",
                        fontFamily = PlusJakartaSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = SlateDark,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Text(
                        text = "Terhubung dengan tenaga medis bersertifikat dalam 15 menit.",
                        fontSize = 11.sp,
                        fontFamily = InterFamily,
                        color = SlateGray,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Button(
                        onClick = { showScheduleConsultationDialog = true },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(34.dp)
                            .testTag("schedule_consultation_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = SlateDark),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Jadwalkan Sesi",
                            fontSize = 11.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SlateSurface)
                        .border(1.dp, BorderClean, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MedicalServices,
                        contentDescription = null,
                        tint = SlateDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // Article Reading Modal
    if (uiState.activeArticleDetail != null) {
        val article = uiState.activeArticleDetail!!
        AlertDialog(
            onDismissRequest = { viewModel.openArticle(null) },
            title = {
                Text(
                    text = article.title,
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = article.imageUrl,
                            contentDescription = article.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = article.author,
                            fontSize = 12.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = CoralPink
                        )
                        Text(
                            text = article.readTime,
                            fontSize = 11.sp,
                            color = SlateGray
                        )
                    }
                    Text(
                        text = article.content,
                        fontSize = 12.sp,
                        fontFamily = InterFamily,
                        color = SlateDark,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.openArticle(null) },
                    colors = ButtonDefaults.buttonColors(containerColor = SlateDark)
                ) {
                    Text("Tutup")
                }
            }
        )
    }

    // Schedule Teleconsultation Modal
    if (showScheduleConsultationDialog) {
        AlertDialog(
            onDismissRequest = { showScheduleConsultationDialog = false },
            title = {
                Text(
                    text = "Jadwalkan Telekonsultasi",
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Text(
                    text = "Layanan konsultasi obgyn langsung akan menghubungkan Anda dengan spesialis kebidanan aktif di RSIA Bunda Jakarta. Sesi tersedia melalui panggilan video terenkripsi medis.",
                    fontSize = 12.sp,
                    fontFamily = InterFamily,
                    color = SlateDark
                )
            },
            confirmButton = {
                Button(
                    onClick = { showScheduleConsultationDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = CoralPink)
                ) {
                    Text("Pilih Waktu Konsultasi")
                }
            },
            dismissButton = {
                TextButton(onClick = { showScheduleConsultationDialog = false }) {
                    Text("Tutup")
                }
            }
        )
    }
}
