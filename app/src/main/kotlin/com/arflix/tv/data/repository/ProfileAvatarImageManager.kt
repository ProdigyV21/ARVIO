package com.arflix.tv.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.util.Constants
import com.arflix.tv.util.ProfileAvatarFiles
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.File
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
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository
) {
    private val httpClient = OkHttpClient()

    suspend fun importAvatar(profileId: String, uriString: String): ImportedProfileAvatar =
        withContext(Dispatchers.IO) {
            val version = System.currentTimeMillis()
            val file = ProfileAvatarFiles.localFile(context, profileId, version)
            decodeSquareAvatar(Uri.parse(uriString))?.let { bitmap ->
                file.outputStream().use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 86, out)
                }
                bitmap.recycle()
            } ?: throw IllegalArgumentException(context.getString(R.string.avatar_decode_failed))

            ProfileAvatarFiles.cleanupProfile(context, profileId, keepVersion = version)
            ImportedProfileAvatar(
                version = version,
                storagePath = uploadAvatar(profileId, version, file).getOrNull()
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

            val resolvedInlineBase64 = inlineBase64
                ?.takeIf { it.isNotBlank() }
                ?: loadInlineAvatarFromCloud(profile.id)

            if (!resolvedInlineBase64.isNullOrBlank()) {
                runCatching {
                    val bytes = Base64.decode(resolvedInlineBase64, Base64.NO_WRAP)
                    file.writeBytes(bytes)
                    ProfileAvatarFiles.cleanupProfile(context, profile.id, keepVersion = profile.avatarImageVersion)
                }.onSuccess { return@withContext }
            }

            val storagePath = profile.avatarImageStoragePath?.trim().orEmpty()
            if (storagePath.isBlank()) return@withContext
            downloadAvatar(storagePath, file).onSuccess {
                ProfileAvatarFiles.cleanupProfile(context, profile.id, keepVersion = profile.avatarImageVersion)
            }
        }

    fun buildInlineAvatarImagesJson(
        profiles: List<Profile>,
        existingImagesById: JSONObject? = null
    ): JSONObject {
        val result = JSONObject()
        profiles
            .filter { it.avatarImageVersion > 0L }
            .forEach { profile ->
                val localImage = readInlineBase64(profile)
                val preservedImage = existingImagesById
                    ?.optString(profile.id)
                    ?.takeIf { it.isNotBlank() }
                val image = localImage ?: preservedImage
                if (!image.isNullOrBlank()) {
                    result.put(profile.id, image)
                }
            }
        return result
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

    private suspend fun uploadAvatar(profileId: String, version: Long, file: File): Result<String> =
        withContext(Dispatchers.IO) {
            runCatching {
                if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                    error("Remote avatar storage is handled by account sync")
                }
                val userId = authRepository.getCurrentUserId().orEmpty()
                val token = authRepository.getAccessToken().orEmpty()
                if (userId.isBlank() || token.isBlank()) error(context.getString(R.string.error_not_logged_in))

                val path = "$userId/$profileId/$version.jpg"
                val request = Request.Builder()
                    .url("${Constants.SUPABASE_URL.trimEnd('/')}/storage/v1/object/$BUCKET/$path")
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer $token")
                    .header("Content-Type", "image/jpeg")
                    .header("cache-control", "31536000")
                    .header("x-upsert", "true")
                    .post(file.asRequestBody("image/jpeg".toMediaType()))
                    .build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) error(context.getString(R.string.avatar_upload_failed, response.code))
                }
                path
            }
        }

    private suspend fun downloadAvatar(storagePath: String, destination: File): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                    error("Remote avatar storage is handled by account sync")
                }
                val token = authRepository.getAccessToken().orEmpty()
                if (token.isBlank()) error(context.getString(R.string.error_not_logged_in))
                val request = Request.Builder()
                    .url("${Constants.SUPABASE_URL.trimEnd('/')}/storage/v1/object/$BUCKET/$storagePath")
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer $token")
                    .get()
                    .build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) error(context.getString(R.string.avatar_download_failed, response.code))
                    val bytes = response.body?.bytes() ?: error(context.getString(R.string.avatar_response_empty))
                    destination.writeBytes(bytes)
                }
            }
        }

    private suspend fun loadInlineAvatarFromCloud(profileId: String): String? {
        return authRepository.loadAccountSyncPayload().getOrNull()
            ?.takeIf { it.isNotBlank() }
            ?.let { payload ->
                runCatching {
                    JSONObject(payload)
                        .optJSONObject("profileAvatarImagesById")
                        ?.optString(profileId)
                        ?.takeIf { it.isNotBlank() }
                }.getOrNull()
            }
    }

    private companion object {
        const val BUCKET = "profile-avatars"
    }
}
