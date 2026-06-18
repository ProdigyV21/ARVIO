package com.arflix.tv.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import com.arflix.tv.data.model.Profile
import com.arflix.tv.util.ProfileAvatarFiles
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

data class ImportedProfileAvatar(
    val version: Long,
    val storagePath: String?
)

@Singleton
class ProfileAvatarImageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun importAvatar(profileId: String, uriString: String): ImportedProfileAvatar =
        withContext(Dispatchers.IO) {
            val version = System.currentTimeMillis()
            val file = ProfileAvatarFiles.localFile(context, profileId, version)
            decodeSquareAvatar(Uri.parse(uriString))?.let { bitmap ->
                file.outputStream().use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 86, out)
                }
                bitmap.recycle()
            } ?: throw IllegalArgumentException("Could not decode selected avatar image")

            ProfileAvatarFiles.cleanupProfile(context, profileId, keepVersion = version)
            ImportedProfileAvatar(
                version = version,
                storagePath = null
            )
        }

    suspend fun restoreAvatarIfNeeded(profile: Profile, inlineBase64: String? = null) =
        withContext(Dispatchers.IO) {
            if (profile.avatarImageVersion <= 0L) {
                // Avatar removal is handled explicitly by profile edit/delete.
                // Do not delete files during cloud restore: stale/older clients can
                // send profile objects without custom-avatar fields and would wipe
                // the cached photo before a newer payload can restore it.
                return@withContext
            }

            val file = ProfileAvatarFiles.localFile(context, profile) ?: return@withContext
            if (file.exists() && file.length() > 0L) return@withContext

            if (!inlineBase64.isNullOrBlank()) {
                runCatching {
                    val bytes = Base64.decode(inlineBase64, Base64.NO_WRAP)
                    file.writeBytes(bytes)
                    ProfileAvatarFiles.cleanupProfile(context, profile.id, keepVersion = profile.avatarImageVersion)
                }.onSuccess { return@withContext }
            }
        }

    fun readInlineBase64(profile: Profile): String? {
        val file = ProfileAvatarFiles.localFile(context, profile) ?: return null
        if (!file.exists() || file.length() <= 0L) return null
        return runCatching { Base64.encodeToString(file.readBytes(), Base64.NO_WRAP) }.getOrNull()
    }

    fun clearLocalAvatar(profileId: String) {
        ProfileAvatarFiles.cleanupProfile(context, profileId)
    }

    private fun decodeSquareAvatar(uri: Uri): Bitmap? {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        context.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

        val sample = calculateSampleSize(bounds.outWidth, bounds.outHeight, 1024)
        val decodeOptions = BitmapFactory.Options().apply {
            inSampleSize = sample
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        val decoded = context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, decodeOptions)
        } ?: return null

        val side = min(decoded.width, decoded.height)
        val x = max(0, (decoded.width - side) / 2)
        val y = max(0, (decoded.height - side) / 2)
        val cropped = Bitmap.createBitmap(decoded, x, y, side, side)
        if (cropped != decoded) decoded.recycle()

        val scaled = Bitmap.createScaledBitmap(cropped, 512, 512, true)
        if (scaled != cropped) cropped.recycle()
        return scaled
    }

    private fun calculateSampleSize(width: Int, height: Int, target: Int): Int {
        var sample = 1
        var halfWidth = width / 2
        var halfHeight = height / 2
        while (halfWidth / sample >= target && halfHeight / sample >= target) {
            sample *= 2
        }
        return sample.coerceAtLeast(1)
    }

}
