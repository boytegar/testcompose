package apps.boytegar.dev.features.favorites.di

import apps.boytegar.dev.features.favorites.data.local.dao.FavoritePhotoLocalDataSource
import apps.boytegar.dev.features.favorites.data.local.database.createFavoritePhotoLocalDataSource
import apps.boytegar.dev.features.favorites.data.repository.FavoritesRepositoryImpl
import apps.boytegar.dev.features.favorites.domain.repository.FavoritesRepository
import apps.boytegar.dev.features.favorites.presentation.viewmodel.FavoritesViewModel
import apps.boytegar.dev.features.favorites.domain.usecase.AddFavoriteUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.ObserveFavoritesUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.RemoveFavoriteUseCase
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph

@BindingContainer
internal object FavoritesBindings {
    @Provides
    fun provideFavoritePhotoLocalDataSource(): FavoritePhotoLocalDataSource =
        createFavoritePhotoLocalDataSource()

    @Provides
    fun provideFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository = repository

    @Provides
    fun provideObserveFavoritesUseCase(repository: FavoritesRepository): ObserveFavoritesUseCase =
        ObserveFavoritesUseCase(repository)

    @Provides
    fun provideAddFavoriteUseCase(repository: FavoritesRepository): AddFavoriteUseCase =
        AddFavoriteUseCase(repository)

    @Provides
    fun provideRemoveFavoriteUseCase(repository: FavoritesRepository): RemoveFavoriteUseCase =
        RemoveFavoriteUseCase(repository)
}

@DependencyGraph(bindingContainers = [FavoritesBindings::class])
internal interface FavoritesGraph {
    val favoritesViewModel: FavoritesViewModel
}

object FavoritesDi {
    fun favoritesViewModel(): FavoritesViewModel = createGraph<FavoritesGraph>().favoritesViewModel
}
