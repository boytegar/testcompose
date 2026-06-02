package apps.boytegar.dev.core.ui.primitives

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PillButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = if (containerColor == Color.Unspecified || contentColor == Color.Unspecified) {
        ButtonDefaults.buttonColors()
    } else {
        ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    }

    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 13.dp),
        colors = colors,
        content = content,
    )
}
