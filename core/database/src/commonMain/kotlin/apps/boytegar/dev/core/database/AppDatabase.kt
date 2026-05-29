package apps.boytegar.dev.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import apps.boytegar.dev.core.database.dao.FavoritePhotoRoomDao
import apps.boytegar.dev.core.database.entity.FavoritePhotoEntity

internal const val APP_DATABASE_NAME = "app.db"

/**
 * Central application database.
 * 
 * To add a new entity:
 * 1. Create your entity class with @Entity annotation in core/database
 * 2. Create your DAO interface with @Dao annotation in core/database
 * 3. Add the entity to the entities array below
 * 4. Add the DAO abstract function below
 * 5. Increment the version number if needed
 */
@Database(
    entities = [
        FavoritePhotoEntity::class,
        // Add more entities here as you create new features
    ],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritePhotoDao(): FavoritePhotoRoomDao
    // Add more DAO accessors here
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
