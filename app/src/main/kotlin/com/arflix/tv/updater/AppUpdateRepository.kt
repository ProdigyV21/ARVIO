package com.arflix.tv.updater

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.arflix.tv.BuildConfig
import com.arflix.tv.R
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

private data class GitHubReleaseDto(
    @SerializedName("tag_name") val tagName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("draft") val draft: Boolean,
    @SerializedName("prerelease") val prerelease: Boolean,
    @SerializedName("assets") val assets: List<GitHubAssetDto>
)

@Singleton
class AppUpdateRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient
) {
    private val gson = Gson()

    fun isPlayStoreInstall(): Boolean {
        val installer = getInstallerPackageName()
        return installer == "com.android.vending"
    }

    fun supportsSelfUpdate(): Boolean = BuildConfig.SELF_UPDATE_ENABLED && !isPlayStoreInstall()

    /**
     * Returns the actually installed version name from PackageManager,
     * which reflects the real installed APK even if the old process is still running.
     */
    fun getInstalledVersionName(): String {
        return try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            info.versionName ?: BuildConfig.VERSION_NAME
        } catch (_: Exception) {
            BuildConfig.VERSION_NAME
        }
    }

    suspend fun getLatestUpdate(): Result<AppUpdate> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val url = "https://api.github.com/repos/${BuildConfig.GITHUB_OWNER}/${BuildConfig.GITHUB_REPO}/releases/latest"
                val request = Request.Builder()
                    .url(url)
                    .header("Accept", "application/vnd.github+json")
                    .header("User-Agent", "ARVIO/${BuildConfig.VERSION_NAME}")
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        error(context.getString(R.string.update_error_github_api, response.code))
                    }
                    val body = response.body?.string().orEmpty()
                    val dto = gson.fromJson(body, GitHubReleaseDto::class.java)
                        ?: error(context.getString(R.string.update_error_empty_release_response))

                    if (dto.draft || dto.prerelease) {
                        error(context.getString(R.string.update_error_draft_prerelease))
                    }

                    val tag = dto.tagName?.takeIf { it.isNotBlank() }
                        ?: dto.name?.takeIf { it.isNotBlank() }
                        ?: error(context.getString(R.string.update_error_missing_release_tag))
                    val asset = AbiSelector.chooseBestApkAsset(dto.assets)
                        ?: error(context.getString(R.string.update_error_no_apk_asset))

                    AppUpdate(
                        tag = tag,
                        title = dto.name?.takeIf { it.isNotBlank() } ?: tag,
                        notes = dto.body.orEmpty(),
                        releaseUrl = dto.htmlUrl,
                        assetName = asset.name,
                        assetUrl = asset.browserDownloadUrl,
                        assetSizeBytes = asset.size
                    )
                }
            }
        }
    }

    private fun getInstallerPackageName(): String? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                context.packageManager.getInstallSourceInfo(context.packageName).installingPackageName
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getInstallerPackageName(context.packageName)
            }
        } catch (_: PackageManager.NameNotFoundException) {
            null
        } catch (_: Exception) {
            null
        }
    }
}
