package apps.boytegar.dev.features.detail.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import apps.boytegar.dev.features.detail.presentation.viewmodel.DetailViewModel

@Composable
@Suppress("UNUSED_PARAMETER")
internal actual fun DetailPhotosSection(
    viewModel: DetailViewModel,
    selectedPhotoId: Int,
) {
    Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
        CoreCard(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs)) {
                Text(
                    text = "iOS preview",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "Paging foto masih placeholder di iOS.",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Photo #$selectedPhotoId",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
