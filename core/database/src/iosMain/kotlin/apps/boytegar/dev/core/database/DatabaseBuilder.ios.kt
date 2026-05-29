package apps.boytegar.dev.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = documentDirectory() + "/" + APP_DATABASE_NAME
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
private fun documentDirectory(): String {
    memScoped {
        val errorPtr = alloc<ObjCObjectVar<NSError?>>()
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = errorPtr.ptr,
        )

        if (documentDirectory != null) {
            return requireNotNull(documentDirectory.path) {
                "Couldn't determine the document directory path."
            }
        }

        val localizedDescription = errorPtr.value?.localizedDescription ?: "Unknown error occurred"
        error("Couldn't determine the document directory path. Error: $localizedDescription")
    }
}
