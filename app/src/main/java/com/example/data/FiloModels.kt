package com.example.data

enum class NavTab(val label: String) {
    BERANDA("Beranda"),
    JURNAL("Jurnal"),
    EDUKASI("Edukasi"),
    MEDIS("Medis"),
    PROFIL("Profil")
}

data class DayInfo(
    val dayName: String,
    val dateNumber: Int,
    val isSelected: Boolean = false,
    val hasDot: Boolean = false
)

data class OrganMilestone(
    val id: String,
    val title: String,
    val description: String,
    val iconType: String
)

data class Symptom(
    val id: String,
    val name: String,
    val description: String,
    val severity: String,
    val isAlert: Boolean = false
)

data class DoctorQuestion(
    val id: String,
    val question: String,
    val note: String,
    val isDone: Boolean = false,
    val isPriority: Boolean = false
)

enum class FoodStatus {
    AMAN,
    HINDARI,
    BATASI
}

data class FoodItem(
    val id: String,
    val name: String,
    val description: String,
    val status: FoodStatus,
    val keywords: String
)

data class Article(
    val id: String,
    val title: String,
    val category: String,
    val readTime: String,
    val author: String,
    val imageUrl: String,
    val content: String = ""
)

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val time: String,
    val isValidated: Boolean = true
)

data class UsgScan(
    val id: String,
    val title: String,
    val dateHospital: String,
    val metric1: String,
    val metric2: String,
    val status: String,
    val imageUrl: String
)

data class ScreeningItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val isChecked: Boolean = false
)
