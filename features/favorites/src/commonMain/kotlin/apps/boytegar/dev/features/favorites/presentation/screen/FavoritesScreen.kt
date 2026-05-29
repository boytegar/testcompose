package apps.boytegar.dev.features.favorites.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import apps.boytegar.dev.features.favorites.di.FavoritesDi
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.shared.components.BaseView
import apps.boytegar.dev.shared.components.EmptyLayout
import apps.boytegar.dev.shared.components.LoadingLayout
import apps.boytegar.dev.shared.components.MultiStateView
import apps.boytegar.dev.shared.navigation.LocalAppNavigationActions

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = remember { FavoritesDi.favoritesViewModel() }
    val navigationActions = LocalAppNavigationActions.current
    BaseView(
        modifier = modifier,
        enableToolbar = true,
        title = "Favorites",
        showBackButton = true,
        onBackClick = navigationActions.onBack,
    ) {
        MultiStateView(
            state = viewModel.uiState,
            loadingLayout = { LoadingLayout(message = "Menata koleksi favorit...") },
            emptyLayout = {
                EmptyLayout(message = "Belum ada foto favorit. Simpan dari Home untuk membangun koleksi.")
            },
        ) { favorites ->
            FavoritesContent(
                favorites = favorites,
                onPhotoClick = { photo ->
                    navigationActions.onFavoritePhotoClick(
                        photo.albumId,
                        photo.id,
                        photo.title,
                        photo.url,
                        photo.thumbnailUrl,
                    )
                },
                onRemoveClick = viewModel::toggleFavorite,
            )
        }
    }
}

@Composable
private fun FavoritesContent(
    favorites: List<FavoritePhoto>,
    onPhotoClick: (FavoritePhoto) -> Unit,
    onRemoveClick: (FavoritePhoto) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = CoreSpacingTokens.Md,
            vertical = CoreSpacingTokens.Lg,
        ),
        verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
    ) {
        item {
            FavoritesHeroCard(count = favorites.size)
        }

        itemsIndexed(favorites, key = { _, photo -> photo.id }) { index, photo ->
            FavoritePhotoCard(
                photo = photo,
                index = index,
                onPhotoClick = onPhotoClick,
                onRemoveClick = onRemoveClick,
            )
        }
    }
}

@Composable
private fun FavoritesHeroCard(count: Int) {
    CoreCard(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
            Text(
                text = "LOCAL COLLECTION",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "Saved from Home",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Foto favorit tersimpan di Room dan tampil dengan kartu yang sedikit berbeda dari feed utama.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.84f),
            ) {
                Text(
                    text = "$count saved",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                )
            }
        }
    }
}

@Composable
private fun FavoritePhotoCard(
    photo: FavoritePhoto,
    index: Int,
    onPhotoClick: (FavoritePhoto) -> Unit,
    onRemoveClick: (FavoritePhoto) -> Unit,
) {
    CoreCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPhotoClick(photo) },
        containerColor = when (index % 2) {
            0 -> MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)
            else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
        },
    ) {
        if (index % 2 == 0) {
            Column(
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs),
            ) {
                Text(
                    text = "Album ${photo.albumId} • Photo #${photo.id}",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = photo.url,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                TextButton(onClick = { onRemoveClick(photo) }) {
                    Text(text = "Remove")
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
            ) {
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
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = photo.thumbnailUrl,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
                ) {
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
                    ) {
                        Text(
                            text = "Saved",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        )
                    }
                    TextButton(onClick = { onRemoveClick(photo) }) {
                        Text(text = "Remove")
                    }
                }
            }
        }
    }
}
