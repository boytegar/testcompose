package apps.boytegar.dev.shared.utils

// Expect/Actual logging API to mirror Log.d / Log.e
expect object Log {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}