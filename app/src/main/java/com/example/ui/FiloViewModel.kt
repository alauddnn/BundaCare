package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FiloUiState(
    val currentTab: NavTab = NavTab.BERANDA,
    
    // Gestational Info
    val motherName: String = "Sarah Amelia",
    val motherNickname: String = "Bunda Sarah",
    val motherAvatarUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuC6VJf8O6HQYzba9t2WXTI4wtrXVUuQChWnplhct44OjraBVuVmEqt8p0DV9bCodmn8nFY01FFDLJk6tRM7bXorGJVCw4qIo5-9cIJcQXH9MXsWjMKm63eZXik3lRjcX5YWe8hF2jWnb84PGv2hsa8udCOJd4T_eH8csSTj1I_nr_GhYqp9OfScdP8UnXffqaid2u6mOcfabI_jq2adpKEkNqtWMvBVuiZm2U1bBhHpXJmJUDE-ivW_0w",
    val gestationalWeek: Int = 24,
    val gestationalDays: Int = 3,
    val daysUntilBirth: Int = 112,
    val hplDate: String = "18 Nov 2025",
    val hphtDate: String = "03 Juni 2025",
    val isTwin: Boolean = false,
    val organAccordionExpanded: Boolean = false,

    // Home Routine
    val waterLiters: Float = 1.75f,
    val waterTargetLiters: Float = 2.5f,
    val vitaminCompleted: Boolean = true,
    val exerciseSessionActive: Boolean = false,

    // Journal State
    val selectedMood: String = "Bahagia",
    val dailyJournalNote: String = "Si kecil sangat aktif malam ini setelah Bunda minum susu hangat dan Ayah membacakan dongeng. Terasa denyut dan tendangan lembut di perut kanan.",
    val kickCount: Int = 7,
    val kickTarget: Int = 10,
    val kickDurationMinutes: Int = 34,
    val kickAvgFrequency: String = "~4.8 mnt/gerak",
    val symptoms: List<Symptom> = listOf(
        Symptom("1", "Nyeri Punggung Bawah", "Mulai terasa saat duduk lama", "Ringan"),
        Symptom("2", "Mual Ringan (Morning Sickness)", "Membaik setelah sarapan", "Ringan"),
        Symptom("3", "Kram Betis Malam Hari", "Bangun 03:00, kompres hangat", "Perlu Pantau", isAlert = true),
        Symptom("4", "Heartburn / Asam Lambung", "Setelah camilan sore", "Ringan")
    ),

    // Medical State
    val isCalendarSynced: Boolean = false,
    val screeningSaved: Boolean = false,
    val screeningItems: List<ScreeningItem> = listOf(
        ScreeningItem("s1", "Sakit kepala berat berdenyut yang tidak hilang", "Terutama di dahi atau pelipis setelah istirahat", false),
        ScreeningItem("s2", "Pandangan berkunang-kunang atau buram mendadak", "Sensitif cahaya atau melihat bintik melayang", false),
        ScreeningItem("s3", "Bengkak tiba-tiba pada wajah & kedua tangan", "Cincin sulit dilepas atau kelopak mata sembab", false)
    ),
    val doctorQuestions: List<DoctorQuestion> = listOf(
        DoctorQuestion("q1", "Apakah letak plasenta aman & tidak menutupi jalan lahir?", "Sudah dibahas pada 28 Ags (Posisi Posterior, aman)", isDone = true),
        DoctorQuestion("q2", "Rekomendasi suplemen kalsium jika masih sering kram betis di malam hari?", "Prioritas Konsultasi", isDone = false, isPriority = true),
        DoctorQuestion("q3", "Jadwal skrining USG 4D Fetomaternal paling ideal di minggu ke berapa?", "Belum dibahas", isDone = false)
    ),
    val usgScans: List<UsgScan> = listOf(
        UsgScan(
            "usg1",
            "USG Morfologi Organ (W20)",
            "28 Ags 2025 • dr. Andini, Sp.OG",
            "TBB: 350 gr",
            "DJJ: 142 bpm",
            "Normal",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuDSMjwzH71_jsPYQ-fgYZ4Eq4JeaWrBmuUCPplYCnBLzOMNbNEg1HdYMaG-aXZ5bRynYeLeElvNbRcwU-EJ0UU-Gjsrus1jz-CJR3redPju2ZiaBbldhpDhZtcLSa6wpY8t9ZVjpE4MnQe97lfVhCTDX9W0ZxVYNkqXKhuforvb7q2-a78CZi6GVZCLN5CS9XOcKqQBIcnuJoIuoaBG6QRFXzBEt0oVBWcLSLe9441ScNHjUzV668yD8A"
        ),
        UsgScan(
            "usg2",
            "USG Nuchal Translucency (W12)",
            "05 Jul 2025 • RSIA Bunda",
            "NT: 1.2 mm",
            "Kromosom Normal",
            "Rendah Risiko",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuBwqLgbwNfQp27MMZCzkqrBTPLh2fgV0VzQG3LCAmr_mtelc2kT7QHeyKfo024exY6mbJUgkcVczu-lHZGcKhlwHbFeC7XoE4EJH84Ro8UPldIOTNhXMJFbIPJKb8kJQEnhz98fbj8J4QRynTS-M8f2wZqDCW91W1pGa-3VHjleAOUI43sL38K15epAnDX094oVseE6JFNf51b2m40EGQvhb9YHOLv6ihK4jMSNJy9WdqGSR8JipStMOg"
        )
    ),

    // Education State
    val chatMessages: List<ChatMessage> = listOf(
        ChatMessage("c1", "Apakah aman minum es air kelapa muda di trimester kedua?", true, "10:42 WIB"),
        ChatMessage(
            "c2",
            "Sangat aman, Bunda. Air kelapa alami kaya elektrolit dan kalium yang membantu mencegah dehidrasi serta kram betis pada malam hari. Hindari penambahan sirup atau pemanis sintetis.",
            false,
            "10:43 WIB",
            isValidated = true
        )
    ),
    val isAiTyping: Boolean = false,
    val foodSearchQuery: String = "",
    val selectedArticleTab: String = "Semua",
    val activeArticleDetail: Article? = null,

    // Profile Settings
    val reminderVitamin: Boolean = true,
    val reminderHydration: Boolean = true,
    val hydrationIntervalHours: Int = 2,
    val reminderDoctor: Boolean = true,
    val reminderTips: Boolean = true,
    val soundHaptic: Boolean = true,
    val showEditProfileDialog: Boolean = false,
    val showAddSymptomDialog: Boolean = false,
    val showTicketDialog: Boolean = false
)

class FiloViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FiloUiState())
    val uiState: StateFlow<FiloUiState> = _uiState.asStateFlow()

    fun setTab(tab: NavTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun toggleOrganAccordion() {
        _uiState.update { it.copy(organAccordionExpanded = !it.organAccordionExpanded) }
    }

    fun addWater() {
        _uiState.update { current ->
            val next = (current.waterLiters + 0.25f).coerceAtMost(current.waterTargetLiters)
            current.copy(waterLiters = (Math.round(next * 100f) / 100f))
        }
    }

    fun toggleExercise() {
        _uiState.update { it.copy(exerciseSessionActive = !it.exerciseSessionActive) }
    }

    fun setMood(mood: String) {
        _uiState.update { it.copy(selectedMood = mood) }
    }

    fun recordKick() {
        _uiState.update { current ->
            if (current.kickCount < 20) {
                current.copy(kickCount = current.kickCount + 1)
            } else current
        }
    }

    fun resetKicks() {
        _uiState.update { it.copy(kickCount = 0) }
    }

    fun addSymptom(name: String, desc: String, severity: String) {
        val newSymptom = Symptom(
            id = "sym_${System.currentTimeMillis()}",
            name = name,
            description = desc,
            severity = severity,
            isAlert = severity.contains("Pantau", ignoreCase = true)
        )
        _uiState.update { it.copy(symptoms = listOf(newSymptom) + it.symptoms, showAddSymptomDialog = false) }
    }

    fun toggleScreening(id: String) {
        _uiState.update { state ->
            val updated = state.screeningItems.map {
                if (it.id == id) it.copy(isChecked = !it.isChecked) else it
            }
            state.copy(screeningItems = updated)
        }
    }

    fun saveScreening() {
        _uiState.update { it.copy(screeningSaved = true) }
        viewModelScope.launch {
            delay(2500)
            _uiState.update { it.copy(screeningSaved = false) }
        }
    }

    fun toggleQuestion(id: String) {
        _uiState.update { state ->
            val updated = state.doctorQuestions.map {
                if (it.id == id) it.copy(isDone = !it.isDone) else it
            }
            state.copy(doctorQuestions = updated)
        }
    }

    fun addQuestion(text: String) {
        if (text.isBlank()) return
        val newQ = DoctorQuestion(
            id = "q_${System.currentTimeMillis()}",
            question = text,
            note = "Baru ditambahkan • Belum dibahas",
            isDone = false,
            isPriority = false
        )
        _uiState.update { it.copy(doctorQuestions = listOf(newQ) + it.doctorQuestions) }
    }

    fun syncCalendar() {
        _uiState.update { it.copy(isCalendarSynced = true) }
        viewModelScope.launch {
            delay(3000)
            _uiState.update { it.copy(isCalendarSynced = false) }
        }
    }

    fun sendAiPrompt(prompt: String) {
        if (prompt.isBlank()) return
        val userMsg = ChatMessage(
            id = "u_${System.currentTimeMillis()}",
            text = prompt,
            isUser = true,
            time = "Sekarang"
        )
        _uiState.update {
            it.copy(
                chatMessages = it.chatMessages + userMsg,
                isAiTyping = true
            )
        }

        viewModelScope.launch {
            delay(1200)
            val replyText = generateClinicalResponse(prompt)
            val aiMsg = ChatMessage(
                id = "ai_${System.currentTimeMillis()}",
                text = replyText,
                isUser = false,
                time = "Sekarang",
                isValidated = true
            )
            _uiState.update {
                it.copy(
                    chatMessages = it.chatMessages + aiMsg,
                    isAiTyping = false
                )
            }
        }
    }

    private fun generateClinicalResponse(query: String): String {
        val q = query.lowercase()
        return when {
            q.contains("posisi tidur") ->
                "Posisi tidur miring ke kiri (Left Lateral) adalah rekomendasi utama dokter spesialis obstetri di Trimester 2. Posisi ini memaksimalkan aliran darah vena cava inferior menuju plasenta, meringankan tekanan rahim pada hati, serta mengoptimalkan oksigenasi janin."
            q.contains("mual") ->
                "Untuk meredakan mual di usia kehamilan 24 minggu: konsumsi makanan dalam porsi kecil namun sering (5-6 kali sehari), hindari makanan berminyak pekat, konsumsi air jahe hangat atau biskuit gandum segera setelah bangun tidur sebelum beranjak dari ranjang."
            q.contains("besi") || q.contains("ttd") ->
                "Kebutuhan zat besi harian Trimester 2 adalah sekitar 27–30 mg per hari. Minum tablet tambah darah (TTD) bersama air putih atau jus jeruk kaya vitamin C untuk memaksimalkan penyerapan. Hindari minum bersama teh, kopi, atau susu karena kalsium dan tanin menghambat absorpsi zat besi."
            q.contains("kram") ->
                "Kram betis di malam hari umum terjadi akibat beban rahim dan perubahan sirkulasi mineral. Coba lakukan peregangan dorsofleksi jari kaki ke arah betis, penuhi kebutuhan kalsium 1.200 mg/hari, dan kompres hangat sebelum tidur."
            q.contains("tendang") || q.contains("gerak") ->
                "Pada minggu ke-24, janin rata-rata bergerak aktif setelah ibu makan atau beristirahat. Pantau setidaknya 10 gerakan dalam rentang waktu 2 jam saat sesi rileks (metode Cardiff Count-to-10)."
            else ->
                "Pertanyaan yang sangat baik, Bunda. Berdasarkan pedoman klinis maternal Trimester 2, selalu pastikan hidrasi minimal 2.5 liter per hari, asupan protein hewani & asam folat tercukupi, dan segera konsultasikan ke dokter jika terdapat demam, bercak flek, atau gerakan janin berkurang drastis."
        }
    }

    fun setFoodQuery(query: String) {
        _uiState.update { it.copy(foodSearchQuery = query) }
    }

    fun setArticleTab(tab: String) {
        _uiState.update { it.copy(selectedArticleTab = tab) }
    }

    fun openArticle(article: Article?) {
        _uiState.update { it.copy(activeArticleDetail = article) }
    }

    fun updateGestationalWeek(delta: Int) {
        _uiState.update {
            val newWeek = (it.gestationalWeek + delta).coerceIn(4, 42)
            val newDaysLeft = ((40 - newWeek) * 7).coerceAtLeast(0)
            it.copy(gestationalWeek = newWeek, daysUntilBirth = newDaysLeft)
        }
    }

    fun setTwin(isTwin: Boolean) {
        _uiState.update { it.copy(isTwin = isTwin) }
    }

    fun updateJournalNote(note: String) {
        _uiState.update { it.copy(dailyJournalNote = note) }
    }

    fun toggleReminder(type: String) {
        _uiState.update { current ->
            when (type) {
                "vitamin" -> current.copy(reminderVitamin = !current.reminderVitamin)
                "hydration" -> current.copy(reminderHydration = !current.reminderHydration)
                "doctor" -> current.copy(reminderDoctor = !current.reminderDoctor)
                "tips" -> current.copy(reminderTips = !current.reminderTips)
                "sound" -> current.copy(soundHaptic = !current.soundHaptic)
                else -> current
            }
        }
    }

    fun setEditProfileDialog(show: Boolean) {
        _uiState.update { it.copy(showEditProfileDialog = show) }
    }

    fun setAddSymptomDialog(show: Boolean) {
        _uiState.update { it.copy(showAddSymptomDialog = show) }
    }

    fun setTicketDialog(show: Boolean) {
        _uiState.update { it.copy(showTicketDialog = show) }
    }

    fun updateProfile(name: String, nickname: String) {
        _uiState.update { it.copy(motherName = name, motherNickname = nickname, showEditProfileDialog = false) }
    }
}
