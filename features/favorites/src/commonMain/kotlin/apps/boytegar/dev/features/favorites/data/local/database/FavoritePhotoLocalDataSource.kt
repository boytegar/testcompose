package apps.boytegar.dev.features.favorites.data.local.database

import apps.boytegar.dev.core.database.AppDatabaseProvider
import apps.boytegar.dev.core.database.entity.FavoritePhotoEntity
import apps.boytegar.dev.features.favorites.data.local.dao.FavoritePhotoLocalDataSource

fun createFavoritePhotoLocalDataSource(): FavoritePhotoLocalDataSource {
    val database = AppDatabaseProvider.database

    return object : FavoritePhotoLocalDataSource {
        override fun observeFavorites() = database.favoritePhotoDao().observeFavorites()

        override suspend fun upsert(photo: FavoritePhotoEntity) {
            database.favoritePhotoDao().upsert(photo)
        }

        override suspend fun deleteById(photoId: Int) {
            database.favoritePhotoDao().deleteById(photoId)
        }
    }
}
