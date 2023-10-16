package com.example.pinjamgkm.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF9434),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE3CA),
    onPrimaryContainer = Color(0xFF002647),
    secondary = Color(0xFF002647),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFE3CA),
    onSecondaryContainer = Color(0xFF002647),
    tertiary = Color(0xFF8C8C8C),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFA0A0A0),
    onTertiaryContainer = Color(0xFFFFFCFA),
    error = Color(0xFFB00020),
    errorContainer = Color(0xFFCF6679),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF000000),
    background = Color(0xFFFFF9F4),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFEFEFEF),
    onSurfaceVariant = Color(0xFF000000),
    outline = Color(0xFFD1D1D1),
    inverseOnSurface = Color(0xFFFFFFFF),
    inverseSurface = Color(0xFF202124),
    inversePrimary = Color(0xFF000000),
    surfaceTint = Color(0xFFFAFAFA),
    outlineVariant = Color(0xFFD1D1D1),
    scrim = Color(0x99FFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFF9434),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE3CA),
    onPrimaryContainer = Color(0xFF002647),
    secondary = Color(0xFF002647),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFE3CA),
    onSecondaryContainer = Color(0xFF002647),
    tertiary = Color(0xFFCACACA),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFA0A0A0),
    onTertiaryContainer = Color(0xFFFFFCFA),
    error = Color(0xFFB00020),
    errorContainer = Color(0xFFCF6679),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFFFFFFFF),
    background = Color(0xFFFFF9F4),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF333333),
    onSurfaceVariant = Color(0xFFFFFFFF),
    outline = Color(0xFF3C3C3C),
    inverseOnSurface = Color(0xFF202124),
    inverseSurface = Color(0xFFEFEFEF),
    inversePrimary = Color(0xFFFFFFFF),
    surfaceTint = Color(0xFF1F1F1F),
    outlineVariant = Color(0xFF3C3C3C),
    scrim = Color(0xCC121212)
)

@Composable
fun PinjamgkmTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = shapes
    )
}