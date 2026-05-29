package apps.boytegar.dev.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens

@Composable
fun HomeEmptyLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CoreCard(
            modifier = Modifier.padding(CoreSpacingTokens.Lg),
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.06f),
            shadowElevation = 0.dp,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs),
            ) {
                Text(
                    text = "Belum ada foto",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Saat data tersedia, galeri akan muncul di sini.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}