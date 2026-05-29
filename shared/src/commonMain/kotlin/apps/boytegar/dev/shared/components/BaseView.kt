package apps.boytegar.dev.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

@Composable
fun BaseView(
    modifier: Modifier = Modifier,
    enableToolbar: Boolean = false,
    title: String? = null,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val backgroundColor = MaterialTheme.colorScheme.background

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        topBar = {
            if (enableToolbar) {
                SimpleToolbar(title = title ?: "", showBackButton = showBackButton, onBackClick = onBackClick)
            }
        },
        containerColor = backgroundColor,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor),
        ) {
            content()
        }
    }
}

@Composable
private fun SimpleToolbar(title: String, showBackButton: Boolean, onBackClick: (() -> Unit)?) {
    BoxWithConstraints {
        val isCompactToolbar = maxWidth < 720.dp
        if (isCompactToolbar) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                ) {
                    if (showBackButton) {
                        IconButton(
                            onClick = { onBackClick?.invoke() },
                            modifier = Modifier.width(48.dp).height(48.dp),
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = IosBackArrow,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        } else {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                shape = RoundedCornerShape(28.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
                tonalElevation = 2.dp,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(72.dp)
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                ) {
                    if (showBackButton) {
                        Surface(
                            shape = RoundedCornerShape(18.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
                        ) {
                            IconButton(
                                onClick = { onBackClick?.invoke() },
                                modifier = Modifier.width(44.dp).height(44.dp),
                            ) {
                                androidx.compose.material3.Icon(
                                    imageVector = IosBackArrow,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
}

private val IosBackArrow = ImageVector.Builder(
    name = "IosBackArrow",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(
        fill = SolidColor(Color.Black),
    ) {
        moveTo(15.41f, 7.41f)
        lineTo(14f, 6f)
        lineTo(8f, 12f)
        lineTo(14f, 18f)
        lineTo(15.41f, 16.59f)
        lineTo(10.83f, 12f)
        close()
    }
}.build()
