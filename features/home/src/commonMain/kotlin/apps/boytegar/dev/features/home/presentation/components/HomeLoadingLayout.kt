package apps.boytegar.dev.features.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreColorTokens
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens

@Composable
fun HomeLoadingLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CoreCard(
            modifier = Modifier.padding(CoreSpacingTokens.Lg),
            containerColor = CoreColorTokens.Surface,
            shadowElevation = 0.dp,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(CoreSpacingTokens.Xs),
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Memuat foto...",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = CoreColorTokens.Primary,
                )
                Text(
                    text = "Menata tampilan agar alurnya terasa lebih halus.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}