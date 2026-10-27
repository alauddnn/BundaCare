package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.DayInfo
import com.example.data.NavTab
import com.example.ui.theme.*

@Composable
fun FiloTopBar(
    title: String = "Filo",
    subtitle: String = "Trimester 2 • W24",
    avatarUrl: String,
    onAvatarClick: () -> Unit,
    onNotificationClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = SurfaceWhite.copy(alpha = 0.95f),
        border = null
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PregnantWoman,
                            contentDescription = "Filo Logo",
                            tint = CoralPink,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = title,
                                fontFamily = PlusJakartaSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = SlateDark
                            )
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(SlateSurface)
                                    .border(1.dp, BorderClean, CircleShape)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = subtitle,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 10.sp,
                                    color = SlateGray
                                )
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(SlateSurface)
                            .border(1.dp, BorderClean, CircleShape)
                            .testTag("notification_button")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifikasi",
                            tint = SlateGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .border(1.dp, BorderClean, CircleShape)
                            .clickable(onClick = onAvatarClick)
                            .testTag("avatar_button")
                    ) {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = "Profil Bunda",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            HorizontalDivider(thickness = 1.dp, color = BorderClean)
        }
    }
}

@Composable
fun FiloBottomBar(
    currentTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = SurfaceWhite,
        shadowElevation = 4.dp
    ) {
        Column {
            HorizontalDivider(thickness = 1.dp, color = BorderClean)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TabItem(
                    tab = NavTab.BERANDA,
                    isSelected = currentTab == NavTab.BERANDA,
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    onClick = { onTabSelected(NavTab.BERANDA) }
                )
                TabItem(
                    tab = NavTab.JURNAL,
                    isSelected = currentTab == NavTab.JURNAL,
                    selectedIcon = Icons.Filled.Favorite,
                    unselectedIcon = Icons.Outlined.FavoriteBorder,
                    onClick = { onTabSelected(NavTab.JURNAL) }
                )
                TabItem(
                    tab = NavTab.EDUKASI,
                    isSelected = currentTab == NavTab.EDUKASI,
                    selectedIcon = Icons.Filled.AutoAwesome,
                    unselectedIcon = Icons.Outlined.AutoAwesome,
                    onClick = { onTabSelected(NavTab.EDUKASI) }
                )
                TabItem(
                    tab = NavTab.MEDIS,
                    isSelected = currentTab == NavTab.MEDIS,
                    selectedIcon = Icons.Filled.MedicalServices,
                    unselectedIcon = Icons.Outlined.MedicalServices,
                    onClick = { onTabSelected(NavTab.MEDIS) }
                )
                TabItem(
                    tab = NavTab.PROFIL,
                    isSelected = currentTab == NavTab.PROFIL,
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    onClick = { onTabSelected(NavTab.PROFIL) }
                )
            }
        }
    }
}

@Composable
private fun TabItem(
    tab: NavTab,
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .widthIn(min = 58.dp)
            .fillMaxHeight()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
            .testTag("tab_${tab.name.lowercase()}"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = tab.label,
            tint = if (isSelected) CoralPink else SlateSubtle,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = tab.label,
            fontSize = 10.sp,
            fontFamily = InterFamily,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) CoralPink else SlateSubtle
        )
    }
}

@Composable
fun WeeklyCalendarStrip(
    modifier: Modifier = Modifier,
    activeDay: String = "Sel",
    activeDate: Int = 23,
    onDayClick: (String, Int) -> Unit = { _, _ -> }
) {
    val days = listOf(
        DayInfo("Min", 21),
        DayInfo("Sen", 22),
        DayInfo("Sel", 23, isSelected = true, hasDot = true),
        DayInfo("Rab", 24),
        DayInfo("Kam", 25),
        DayInfo("Jum", 26),
        DayInfo("Sab", 27)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceWhite)
            .border(1.dp, BorderClean, RoundedCornerShape(16.dp))
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        days.forEach { day ->
            val isCurrent = day.dayName == activeDay && day.dateNumber == activeDate
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isCurrent) CoralPink else Color.Transparent)
                    .clickable { onDayClick(day.dayName, day.dateNumber) }
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day.dayName,
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                    color = if (isCurrent) SurfaceWhite.copy(alpha = 0.9f) else SlateGray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${day.dateNumber}",
                    fontSize = 14.sp,
                    fontFamily = PlusJakartaSansFamily,
                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.SemiBold,
                    color = if (isCurrent) SurfaceWhite else SlateDark
                )
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(if (isCurrent) SurfaceWhite else Color.Transparent)
                )
            }
        }
    }
}
