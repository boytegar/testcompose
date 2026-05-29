package apps.boytegar.dev.features.home.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens
import apps.boytegar.dev.core.ui.theme.CoreColorTokens
import apps.boytegar.dev.features.favorites.di.FavoritesDi
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.features.home.di.HomeDi
import apps.boytegar.dev.features.home.domain.model.HomePhoto
import apps.boytegar.dev.features.home.presentation.components.HomeEmptyLayout
import apps.boytegar.dev.features.home.presentation.components.HomeErrorLayout
import apps.boytegar.dev.features.home.presentation.components.HomeLoadingLayout
import apps.boytegar.dev.shared.components.MultiStateView
import apps.boytegar.dev.shared.navigation.LocalAppNavigationActions
import apps.boytegar.dev.shared.utils.Results

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = remember { HomeDi.homeViewModel() }
    val favoritesViewModel = remember { FavoritesDi.favoritesViewModel() }
    val navigationActions = LocalAppNavigationActions.current
    val favoritesState = favoritesViewModel.uiState.collectAsState().value
    val favoriteIds = when (favoritesState) {
        is Results.Success -> favoritesState.data.map { it.id }.toSet()
        else -> emptySet()
    }
    
    MultiStateView(
        modifier = modifier.fillMaxSize(),
        state = viewModel.uiState,
        loadingLayout = { HomeLoadingLayout() },
        errorLayout = { message -> HomeErrorLayout(message = message, onRetry = viewModel::refresh) },
        emptyLayout = { HomeEmptyLayout() },
    ) { photos ->
        HomePhotosContent(
            photos = photos,
            favoriteIds = favoriteIds,
            onRefresh = viewModel::refresh,
            onPhotoClick = { photo ->
                navigationActions.onHomePhotoClick(
                    photo.albumId,
                    photo.id,
                    photo.title,
                    photo.url,
                    photo.thumbnailUrl,
                )
            },
            onFavoriteToggle = { photo ->
                favoritesViewModel.toggleFavorite(
                    FavoritePhoto(
                        albumId = photo.albumId,
                        id = photo.id,
                        title = photo.title,
                        url = photo.url,
                        thumbnailUrl = photo.thumbnailUrl,
                    )
                )
            },
            onFavoritesClick = navigationActions.onFavoritesClick,
        )
    }
}

@Composable
private fun HomePhotosContent(
    photos: List<HomePhoto>,
    favoriteIds: Set<Int>,
    onRefresh: () -> Unit,
    onPhotoClick: (HomePhoto) -> Unit,
    onFavoriteToggle: (HomePhoto) -> Unit,
    onFavoritesClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = CoreSpacingTokens.Md,
            vertical = CoreSpacingTokens.Lg,
        ),
        verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
    ) {
        item {
            HomeHeroCard(
                photoCount = photos.size,
                albumCount = photos.map { it.albumId }.distinct().size,
                favoriteCount = favoriteIds.size,
                onFavoritesClick = onFavoritesClick,
                onRefresh = onRefresh,
            )
        }

        item {
            SectionHeader(
                title = "Explore gallery",
                description = "Setiap kartu tampil sedikit berbeda supaya alurnya tidak terasa repetitif.",
            )
        }

        itemsIndexed(photos, key = { _, photo -> photo.id }) { index, photo ->
            HomePhotoCard(
                photo = photo,
                index = index,
                isFavorite = favoriteIds.contains(photo.id),
                onClick = onPhotoClick,
                onFavoriteToggle = onFavoriteToggle,
            )
        }
    }
}

@Composable
private fun HomeHeroCard(
    photoCount: Int,
    albumCount: Int,
    favoriteCount: Int,
    onFavoritesClick: () -> Unit,
    onRefresh: () -> Unit,
) {
    CoreCard(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        shadowElevation = 0.dp,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
            Text(
                text = "REMOTE FEED",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "JSONPlaceholder Photos",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Stream foto remote, simpan favorit, dan buka detail dengan alur visual yang lebih variatif.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
            ) {
                StatPill(
                    modifier = Modifier.weight(1f),
                    label = "Photos",
                    value = photoCount.toString(),
                )
                StatPill(
                    modifier = Modifier.weight(1f),
                    label = "Albums",
                    value = albumCount.toString(),
                )
                StatPill(
                    modifier = Modifier.weight(1f),
                    label = "Saved",
                    value = favoriteCount.toString(),
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
            ) {
                TextButton(onClick = onRefresh) {
                    Text(text = "Refresh")
                }
                TextButton(onClick = onFavoritesClick) {
                    Text(text = "Favorites ($favoriteCount)")
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    description: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun StatPill(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun HomePhotoCard(
    photo: HomePhoto,
    index: Int,
    isFavorite: Boolean,
    onClick: (HomePhoto) -> Unit,
    onFavoriteToggle: (HomePhoto) -> Unit,
) {
    CoreCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(photo) },
        containerColor = when (index % 3) {
            0 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
            1 -> MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)
            else -> CoreColorTokens.AccentTeal.copy(alpha = 0.08f)
        },
        shadowElevation = 0.dp,
    ) {
        when (index % 3) {
            0 -> Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Xs)) {
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
                Row(horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
                    TextButton(onClick = { onFavoriteToggle(photo) }) {
                        Text(text = if (isFavorite) "Saved" else "Save")
                    }
                    Text(
                        text = if (isFavorite) "In favorites" else "Tap to save",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            1 -> Row(
                modifier = Modifier.fillMaxWidth(),
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
                            text = if (isFavorite) "Saved" else "Open",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        )
                    }
                    TextButton(onClick = { onFavoriteToggle(photo) }) {
                        Text(text = if (isFavorite) "Remove" else "Favorite")
                    }
                }
            }

            else -> Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Photo #${photo.id}",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = if (isFavorite) "Saved" else "Fresh",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
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
                TextButton(onClick = { onFavoriteToggle(photo) }) {
                    Text(text = if (isFavorite) "Unfavorite" else "Favorite")
                }
            }
        }
    }
}
