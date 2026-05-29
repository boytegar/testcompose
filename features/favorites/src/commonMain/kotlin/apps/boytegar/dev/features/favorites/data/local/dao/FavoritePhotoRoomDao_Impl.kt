package apps.boytegar.dev.features.favorites.data.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import apps.boytegar.dev.features.favorites.data.local.entity.FavoritePhotoEntity
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoritePhotoRoomDao_Impl(
    __db: RoomDatabase,
) : FavoritePhotoRoomDao {
    private val __db: RoomDatabase

    private val __insertAdapterOfFavoritePhotoEntity: EntityInsertAdapter<FavoritePhotoEntity>

    init {
        this.__db = __db
        this.__insertAdapterOfFavoritePhotoEntity = object : EntityInsertAdapter<FavoritePhotoEntity>() {
            override fun createQuery(): String =
                "INSERT OR REPLACE INTO `favorite_photos` (`albumId`,`id`,`title`,`url`,`thumbnailUrl`) VALUES (?,?,?,?,?)"

            override fun bind(statement: SQLiteStatement, entity: FavoritePhotoEntity) {
                statement.bindLong(1, entity.albumId.toLong())
                statement.bindLong(2, entity.id.toLong())
                statement.bindText(3, entity.title)
                statement.bindText(4, entity.url)
                statement.bindText(5, entity.thumbnailUrl)
            }
        }
    }

    override suspend fun upsert(photo: FavoritePhotoEntity): Unit = performSuspending(__db, false, true) { _connection ->
        __insertAdapterOfFavoritePhotoEntity.insert(_connection, photo)
    }

    override fun observeFavorites(): Flow<List<FavoritePhotoEntity>> {
        val _sql: String = "SELECT * FROM favorite_photos ORDER BY id DESC"
        return createFlow(__db, false, arrayOf("favorite_photos")) { _connection ->
            val _stmt: SQLiteStatement = _connection.prepare(_sql)
            try {
                val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "albumId")
                val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
                val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
                val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
                val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
                val _result: MutableList<FavoritePhotoEntity> = mutableListOf()
                while (_stmt.step()) {
                    val _tmpAlbumId: Int = _stmt.getLong(_columnIndexOfAlbumId).toInt()
                    val _tmpId: Int = _stmt.getLong(_columnIndexOfId).toInt()
                    val _tmpTitle: String = _stmt.getText(_columnIndexOfTitle)
                    val _tmpUrl: String = _stmt.getText(_columnIndexOfUrl)
                    val _tmpThumbnailUrl: String = _stmt.getText(_columnIndexOfThumbnailUrl)
                    _result.add(
                        FavoritePhotoEntity(
                            albumId = _tmpAlbumId,
                            id = _tmpId,
                            title = _tmpTitle,
                            url = _tmpUrl,
                            thumbnailUrl = _tmpThumbnailUrl,
                        ),
                    )
                }
                _result
            } finally {
                _stmt.close()
            }
        }
    }

    override suspend fun deleteById(photoId: Int) {
        val _sql: String = "DELETE FROM favorite_photos WHERE id = ?"
        return performSuspending(__db, false, true) { _connection ->
            val _stmt: SQLiteStatement = _connection.prepare(_sql)
            try {
                var _argIndex: Int = 1
                _stmt.bindLong(_argIndex, photoId.toLong())
                _stmt.step()
            } finally {
                _stmt.close()
            }
        }
    }

    public companion object {
        public fun getRequiredConverters(): List<KClass<*>> = emptyList()
    }
}
