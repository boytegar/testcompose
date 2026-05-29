package apps.boytegar.dev.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import apps.boytegar.dev.shared.platform.AppContextHolder

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val context = requireNotNull(AppContextHolder.context as? Context) {
        "Application context was not initialized before creating the database."
    }
    val dbFile = context.getDatabasePath(APP_DATABASE_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}
