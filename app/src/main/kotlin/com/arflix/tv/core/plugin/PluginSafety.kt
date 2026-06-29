package com.arflix.tv.core.plugin

internal object PluginSafety {

    // List of known dangerous package names/prefixes that should be blocked
    private val BLOCKED_PACKAGES = setOf(
        "com.google",
        "android",
        "java",
        "javax",
        "kotlin",
        "com.arflix.tv.core" // Prevent plugins from shadowing our own core logic
    )

    // Allowed plugin file extensions
    private val ALLOWED_EXTENSIONS = setOf("cs3", "apk", "dex", "js")

    /**
     * Validates a plugin based on its metadata before allowing it to load.
     */
    fun isSafeToLoad(
        pluginName: String?,
        pluginPackage: String?,
        filename: String?
    ): Boolean {
        // Basic presence checks
        if (pluginName.isNullOrBlank() || filename.isNullOrBlank()) {
            return false
        }

        // Validate extension
        val ext = filename.substringAfterLast('.', "").lowercase()
        if (ext !in ALLOWED_EXTENSIONS) {
            return false
        }

        // Validate package name (if provided) to prevent namespace shadowing
        if (pluginPackage != null) {
            if (BLOCKED_PACKAGES.any { pluginPackage.startsWith(it, ignoreCase = true) }) {
                return false
            }
        }

        // No path traversal or absolute paths allowed
        val file = java.io.File(filename)
        if (file.isAbsolute) {
            return false
        }
        val normalized = file.normalize().path.replace("\\", "/")
        if (normalized.startsWith("../") || normalized == "..") {
            return false
        }

        return true
    }
}
