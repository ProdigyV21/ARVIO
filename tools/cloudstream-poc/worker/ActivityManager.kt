package android.app

/** Conservative JVM memory report for plugins selecting their extraction concurrency. */
class ActivityManager {
    class MemoryInfo {
        @JvmField var totalMem: Long = Runtime.getRuntime().maxMemory()
        @JvmField var availMem: Long = Runtime.getRuntime().freeMemory()
        @JvmField var lowMemory: Boolean = false
    }
    fun getMemoryInfo(info: MemoryInfo) {
        info.totalMem = Runtime.getRuntime().maxMemory()
        info.availMem = Runtime.getRuntime().freeMemory()
    }
}
