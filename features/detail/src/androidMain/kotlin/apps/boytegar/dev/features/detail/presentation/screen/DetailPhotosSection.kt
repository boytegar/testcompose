package apps.boytegar.dev.features.detail.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreColorTokens
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.detail.presentation.viewmodel.DetailViewModel
import androidx.compose.ui.unit.dp

@Composable
internal actual fun DetailPhotosSection(
    viewModel: DetailViewModel,
    selectedPhotoId: Int,
) {
    val photos = viewModel.photos.collectAsLazyPagingItems()
    val refreshState = photos.loadState.refresh

    when {
        refreshState is LoadState.Loading && photos.itemCount == 0 -> LoadingState()

        refreshState is LoadState.Error && photos.itemCount == 0 -> ErrorState(
            message = refreshState.error.message,
            onRetry = photos::retry,
        )

        else -> DetailPagedList(
            photos = photos,
            selectedPhotoId = selectedPhotoId,
        )
    }
}

@Composable
private fun DetailPagedList(
    photos: androidx.paging.compose.LazyPagingItems<DetailPhoto>,
    selectedPhotoId: Int,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = CoreSpacingTokens.Md),
        verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
    ) {
        items(
            count = photos.itemCount,
            key = { index -> photos.peek(index)?.id ?: index },
            contentType = { _ -> "photo" },
        ) { index ->
            val photo = photos[index] ?: return@items
            DetailPhotoCard(
                photo = photo,
                selected = photo.id == selectedPhotoId,
                index = index,
            )
        }

        val appendState = photos.loadState.append
        when {
            appendState is LoadState.Loading -> item {
                AppendStateCard(
                    title = "Memuat halaman berikutnya",
                    message = "Menambahkan foto lain ke daftar detail.",
                    trailing = { CircularProgressIndicator() },
                )
            }

            appendState is LoadState.Error -> item {
                AppendStateCard(
                    title = "Gagal memuat halaman berikutnya",
                    message = appendState.error.message,
                    actionLabel = "Coba lagi",
                    onAction = photos::retry,
                )
            }
        }
    }
}

@Composable
private fun DetailPhotoCard(
    photo: DetailPhoto,
    selected: Boolean,
    index: Int,
) {
    CoreCard(modifier = Modifier.fillMaxWidth()) {
        when (index % 3) {
            0 -> Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs)) {
                Text(
                    text = "Album ${photo.albumId} • Photo #${photo.id}",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = if (selected) "Selected item" else photo.url,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
                Text(
                    text = photo.thumbnailUrl,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
            }

            1 -> Row(horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs),
                ) {
                    Text(
                        text = "Album ${photo.albumId}",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = photo.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = photo.url,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                    )
                }
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = if (selected) 0.16f else 0.10f),
                ) {
                    Text(
                        text = if (selected) "Selected" else "Open",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    )
                }
            }

            else -> Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    MetaChip(text = "Album ${photo.albumId}")
                    MetaChip(text = if (selected) "Selected" else "Available")
                }
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = photo.url,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
                Text(
                    text = photo.thumbnailUrl,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun AppendStateCard(
    title: String,
    message: String?,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    CoreCard(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = message ?: "Terjadi gangguan saat memuat data.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
            )
            trailing?.invoke()
            if (actionLabel != null && onAction != null) {
                Button(onClick = onAction) {
                    Text(actionLabel)
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    AppendStateCard(
        title = "Memuat foto",
        message = "Menarik halaman pertama agar daftar terasa lebih hidup.",
        trailing = { CircularProgressIndicator() },
    )
}

@Composable
private fun ErrorState(
    message: String?,
    onRetry: () -> Unit,
) {
    AppendStateCard(
        title = "Gagal memuat foto",
        message = message,
        actionLabel = "Coba lagi",
        onAction = onRetry,
    )
}

@Composable
private fun MetaChip(text: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = CoreColorTokens.AccentTeal.copy(alpha = 0.10f),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        )
    }
}
