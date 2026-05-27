package com.arflix.tv.util

import android.content.Context
import com.arflix.tv.data.model.Profile
import java.io.File

object ProfileAvatarFiles {
    fun directory(context: Context): File =
        File(context.filesDir, "profile_avatars").apply { mkdirs() }

    fun localFile(context: Context, profile: Profile): File? {
        if (profile.avatarImageVersion <= 0L) return null
        return localFile(context, profile.id, profile.avatarImageVersion)
    }

    fun localFile(context: Context, profileId: String, version: Long): File =
        File(directory(context), "${safeName(profileId)}_$version.jpg")

    fun cleanupProfile(context: Context, profileId: String, keepVersion: Long? = null) {
        val prefix = "${safeName(profileId)}_"
        directory(context).listFiles()?.forEach { file ->
            if (!file.name.startsWith(prefix)) return@forEach
            if (keepVersion != null && file.name == "${prefix}$keepVersion.jpg") return@forEach
            runCatching { file.delete() }
        }
    }

    private fun safeName(value: String): String =
        value.replace(ProfileAvatarRegexes.SANITIZATION_REGEX, "_")
}

private object ProfileAvatarRegexes {
    val SANITIZATION_REGEX = Regex("[^A-Za-z0-9._-]")
}
