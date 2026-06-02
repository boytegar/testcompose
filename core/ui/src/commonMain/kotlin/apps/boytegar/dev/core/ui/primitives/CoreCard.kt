package apps.boytegar.dev.core.ui.primitives

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CoreCard(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
    shape: RoundedCornerShape = RoundedCornerShape(28.dp),
    shadowElevation: Dp = 1.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = shadowElevation,
            pressedElevation = shadowElevation,
            hoveredElevation = shadowElevation,
            focusedElevation = shadowElevation,
            draggedElevation = shadowElevation,
            disabledElevation = shadowElevation,
        ),
    ) {
        Column(modifier = Modifier.padding(CoreSpacingTokens.Md), content = content)
    }
}
