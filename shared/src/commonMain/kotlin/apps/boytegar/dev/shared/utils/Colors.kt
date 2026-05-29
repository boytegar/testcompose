package apps.boytegar.dev.shared.utils

import androidx.compose.ui.graphics.Color

object Colors{
    // Light Mode
    val BluePrimary = Color(0xFF004DC0)
    val BlueBackground = Color(0xFFF0F9FF)
    val AccentTeal = Color(0xFF00D7C4)
    val TextGray = Color(0xFF757575)
    val White = Color(0xFFFFFFFF)
    
    // Dark Mode
    val DarkDeepBackground = Color(0xFF0B1118)
    val DarkSurface = Color(0xFF1A232E)
    val BlueLightPrimary = Color(0xFF4D91FF)
    val DarkTextPrimary = Color(0xFFE1E4E8)
    val DarkTextSecondary = Color(0xFF9BA3AF)
    
    // Legacy (for backward compatibility)
    val bgColor = DarkSurface
    val itemBgColor = DarkDeepBackground
    val btnColor = BluePrimary
}
