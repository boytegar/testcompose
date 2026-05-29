package apps.boytegar.dev.shared.utils

import platform.Foundation.NSLog

actual object Log {
    actual fun d(tag: String, message: String) {
        NSLog("D/%@ %@", tag, message)
    }

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        val suffix = throwable?.message?.let { " | $it" } ?: ""
        NSLog("E/%@ %@%@", tag, message, suffix)
    }
}
