package com.arflix.tv.util

import android.content.Context
import com.arflix.tv.BuildConfig
import io.sentry.Breadcrumb
import io.sentry.Sentry
import io.sentry.SentryLevel
import io.sentry.android.core.SentryAndroid
import io.sentry.protocol.User

/**
 * Sentry implementation of [AppLogger.CrashContextProvider].
 *
 * The SDK only starts when crash reporting is enabled for the variant and
 * SENTRY_DSN is set to a real Sentry DSN in secrets.properties.
 */
object SentryCrashReporter : AppLogger.CrashContextProvider {
    private const val DISABLED_DSN = "disabled"
    private var isInitialized = false

    fun initialize(context: Context): Boolean {
        val dsn = BuildConfig.SENTRY_DSN.trim()
        if (!BuildConfig.ENABLE_CRASH_REPORTING || dsn.isBlank() || dsn == DISABLED_DSN) {
            isInitialized = false
            AppLogger.init(null)
            return false
        }

        return runCatching {
            SentryAndroid.init(context) { options ->
                options.setDsn(dsn)
                options.setRelease("${BuildConfig.APPLICATION_ID}@${BuildConfig.VERSION_NAME}+${BuildConfig.VERSION_CODE}")
                options.setDist(BuildConfig.VERSION_CODE.toString())
                options.setEnvironment(BuildConfig.BUILD_TYPE)
                options.setDebug(BuildConfig.DEBUG)
                options.setSendDefaultPii(false)
                options.setAttachScreenshot(false)
                options.setAttachViewHierarchy(false)
                options.setEnableActivityLifecycleBreadcrumbs(true)
                options.setEnableAppLifecycleBreadcrumbs(true)
                options.setEnableSystemEventBreadcrumbs(false)
                options.setEnableNetworkEventBreadcrumbs(false)
                options.setEnableUserInteractionBreadcrumbs(false)
                options.setTracesSampleRate(0.0)
                options.setBeforeSend { event, _ ->
                    if (!CrashReportFilter.shouldSendSentryEvent(event.throwable, event.level)) {
                        return@setBeforeSend null
                    }
                    runCatching {
                        val prefs = context.getSharedPreferences("arvio_crash_store", Context.MODE_PRIVATE)
                        val eventId = event.eventId.toString()
                        val throwable = event.throwable
                        val msg = if (throwable != null) {
                            "${throwable::class.java.simpleName}: ${throwable.message?.take(200) ?: ""}"
                        } else {
                            event.message?.formatted ?: "Crash event"
                        }
                        val editor = prefs.edit()
                            .putString("last_crash_id", eventId)
                            .putString("last_crash_msg", msg)
                            .putLong("last_crash_time", System.currentTimeMillis())
                            .putString("last_crash_version", "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
                        if (event.isCrashed == true || event.level == SentryLevel.FATAL) {
                            editor.putBoolean("has_pending_crash_report", true)
                        }
                        editor.commit()
                    }
                    event.setUser(null)
                    event.setServerName(null)
                    event.setRequest(null)
                    event
                }
            }
            isInitialized = true
            AppLogger.init(this)
            true
        }.getOrElse {
            isInitialized = false
            AppLogger.init(null)
            false
        }
    }

    override fun setCustomKey(key: String, value: String) {
        if (!isInitialized) return
        Sentry.setTag(key, value)
    }

    override fun setCustomKey(key: String, value: Int) {
        if (!isInitialized) return
        Sentry.setExtra(key, value.toString())
    }

    override fun setCustomKey(key: String, value: Boolean) {
        if (!isInitialized) return
        Sentry.setExtra(key, value.toString())
    }

    override fun log(message: String) {
        if (!isInitialized) return
        val breadcrumb = Breadcrumb().apply {
            setCategory("arvio")
            setType("diagnostic")
            setMessage(message.take(500))
            setLevel(SentryLevel.INFO)
        }
        Sentry.addBreadcrumb(breadcrumb)
    }

    override fun recordException(throwable: Throwable) {
        if (!isInitialized) return
        if (!CrashReportFilter.shouldReportHandledException(throwable)) return
        Sentry.captureException(throwable)
    }

    override fun setUserId(userId: String?) {
        if (!isInitialized) return
        val user = userId?.takeIf { it.isNotBlank() }?.let { id ->
            User().apply {
                setId(id)
                setIpAddress(null)
            }
        }
        Sentry.setUser(user)
    }
}
