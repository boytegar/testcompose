package apps.boytegar.dev.features.detail.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import apps.boytegar.dev.features.detail.di.DetailDi
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.detail.presentation.viewmodel.DetailViewModel
import apps.boytegar.dev.shared.components.BaseView

@Composable
fun DetailScreen(
    selectedPhoto: DetailPhoto,
    onBack: () -> Unit = {},
) {
    val viewModel = remember { DetailDi.detailViewModel() }

    BaseView(
        enableToolbar = true,
        title = "Photo detail",
        showBackButton = true,
        onBackClick = onBack,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
        ) {
            DetailHeaderCard(selectedPhoto = selectedPhoto)
            Text(
                text = "More from this feed",
                style = MaterialTheme.typography.titleMedium,
            )
            DetailPhotosSection(
                viewModel = viewModel,
                selectedPhotoId = selectedPhoto.id,
            )
        }
    }
}

@Composable
private fun DetailHeaderCard(selectedPhoto: DetailPhoto) {
    CoreCard(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
            Text(
                text = "SELECTED PHOTO",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = selectedPhoto.title,
                style = MaterialTheme.typography.headlineMedium,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
                MetaChip(text = "Album ${selectedPhoto.albumId}")
                MetaChip(text = "Photo #${selectedPhoto.id}")
                MetaChip(text = "In focus")
            }
            Text(
                text = selectedPhoto.url,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
            )
            Text(
                text = selectedPhoto.thumbnailUrl,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
            )
        }
    }
}

@Composable
internal expect fun DetailPhotosSection(
    viewModel: DetailViewModel,
    selectedPhotoId: Int,
)

@Composable
private fun MetaChip(text: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        )
    }
}
