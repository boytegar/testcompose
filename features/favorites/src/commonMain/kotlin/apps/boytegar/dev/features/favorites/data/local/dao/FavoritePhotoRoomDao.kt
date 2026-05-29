package apps.boytegar.dev.features.favorites.data.local.dao

import apps.boytegar.dev.features.favorites.data.local.entity.FavoritePhotoEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhotoRoomDao {
    @Query("SELECT * FROM favorite_photos ORDER BY id DESC")
    fun observeFavorites(): Flow<List<FavoritePhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(photo: FavoritePhotoEntity)

    @Query("DELETE FROM favorite_photos WHERE id = :photoId")
    suspend fun deleteById(photoId: Int)
}
