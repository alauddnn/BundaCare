package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = CoralPink,
    onPrimary = SurfaceWhite,
    primaryContainer = CoralPinkLight,
    onPrimaryContainer = CoralPinkDark,
    secondary = SlateDark,
    onSecondary = SurfaceWhite,
    secondaryContainer = SlateSurface,
    onSecondaryContainer = SlateDark,
    tertiary = SkyBlueAccent,
    onTertiary = SurfaceWhite,
    background = BgGlobal,
    onBackground = SlateDark,
    surface = SurfaceWhite,
    onSurface = SlateDark,
    surfaceVariant = SlateSurface,
    onSurfaceVariant = SlateGray,
    outline = SlateSubtle,
    outlineVariant = BorderClean,
)

private val DarkColorScheme = darkColorScheme(
    primary = CoralPink,
    onPrimary = SurfaceWhite,
    primaryContainer = CoralPinkDark,
    onPrimaryContainer = CoralPinkLight,
    secondary = SlateLight,
    onSecondary = SlateDark,
    background = SlateDark,
    onBackground = SurfaceWhite,
    surface = Color(0xFF1E293B),
    onSurface = SurfaceWhite,
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = SlateSubtle,
    outline = SlateGray,
    outlineVariant = Color(0xFF334155),
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = BgGlobal.toArgb()
                window.navigationBarColor = SurfaceWhite.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
