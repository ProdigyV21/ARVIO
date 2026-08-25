package io.sentry.android.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import io.sentry.ILogger;
import io.sentry.d7;
import io.sentry.k4;
import io.sentry.r5;
import io.sentry.s3;
import io.sentry.u5;
import io.sentry.w5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b1 {
    public static void a(Context context, r0 r0Var, SentryAndroidOptions sentryAndroidOptions) {
        List listD;
        List listD2;
        List listD3;
        List listD4;
        ac.b.V(sentryAndroidOptions, "The options object is required.");
        try {
            sentryAndroidOptions.getLogger();
            ApplicationInfo applicationInfo = Build.VERSION.SDK_INT >= 33 ? (ApplicationInfo) q0.f16730d.a(context) : (ApplicationInfo) q0.f16731e.a(context);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            ILogger logger = sentryAndroidOptions.getLogger();
            if (bundle != null) {
                sentryAndroidOptions.setDebug(b(bundle, logger, "io.sentry.debug", sentryAndroidOptions.isDebug()));
                if (sentryAndroidOptions.isDebug()) {
                    String strName = sentryAndroidOptions.getDiagnosticLevel().name();
                    Locale locale = Locale.ROOT;
                    String strF = f(bundle, logger, "io.sentry.debug.level", strName.toLowerCase(locale));
                    if (strF != null) {
                        sentryAndroidOptions.setDiagnosticLevel(w5.valueOf(strF.toUpperCase(locale)));
                    }
                }
                sentryAndroidOptions.setAnrEnabled(b(bundle, logger, "io.sentry.anr.enable", sentryAndroidOptions.isAnrEnabled()));
                sentryAndroidOptions.setTombstoneEnabled(b(bundle, logger, "io.sentry.tombstone.enable", sentryAndroidOptions.isTombstoneEnabled()));
                sentryAndroidOptions.setEnableAutoSessionTracking(b(bundle, logger, "io.sentry.auto-session-tracking.enable", sentryAndroidOptions.isEnableAutoSessionTracking()));
                if (sentryAndroidOptions.getSampleRate() == null) {
                    double dC = c(bundle, logger, "io.sentry.sample-rate");
                    if (dC != -1.0d) {
                        sentryAndroidOptions.setSampleRate(Double.valueOf(dC));
                    }
                }
                sentryAndroidOptions.setAnrReportInDebug(b(bundle, logger, "io.sentry.anr.report-debug", sentryAndroidOptions.isAnrReportInDebug()));
                sentryAndroidOptions.setAnrTimeoutIntervalMillis(e(bundle, logger, "io.sentry.anr.timeout-interval-millis", sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                sentryAndroidOptions.setAttachAnrThreadDump(b(bundle, logger, "io.sentry.anr.attach-thread-dumps", sentryAndroidOptions.isAttachAnrThreadDump()));
                String strF2 = f(bundle, logger, "io.sentry.dsn", sentryAndroidOptions.getDsn());
                boolean zB = b(bundle, logger, "io.sentry.enabled", sentryAndroidOptions.isEnabled());
                if (!zB || (strF2 != null && strF2.isEmpty())) {
                    sentryAndroidOptions.getLogger().q(w5.DEBUG, "Sentry enabled flag set to false or DSN is empty: disabling sentry-android", new Object[0]);
                } else if (strF2 == null) {
                    sentryAndroidOptions.getLogger().q(w5.FATAL, "DSN is required. Use empty string to disable SDK.", new Object[0]);
                }
                sentryAndroidOptions.setEnabled(zB);
                sentryAndroidOptions.setDsn(strF2);
                sentryAndroidOptions.setEnableNdk(b(bundle, logger, "io.sentry.ndk.enable", sentryAndroidOptions.isEnableNdk()));
                sentryAndroidOptions.setEnableScopeSync(b(bundle, logger, "io.sentry.ndk.scope-sync.enable", sentryAndroidOptions.isEnableScopeSync()));
                String strF3 = f(bundle, logger, "io.sentry.ndk.sdk-name", sentryAndroidOptions.getNativeSdkName());
                if (strF3 != null) {
                    sentryAndroidOptions.setNativeSdkName(strF3);
                }
                sentryAndroidOptions.setRelease(f(bundle, logger, "io.sentry.release", sentryAndroidOptions.getRelease()));
                sentryAndroidOptions.setDist(f(bundle, logger, "io.sentry.dist", sentryAndroidOptions.getDist()));
                sentryAndroidOptions.setEnvironment(f(bundle, logger, "io.sentry.environment", sentryAndroidOptions.getEnvironment()));
                sentryAndroidOptions.setSessionTrackingIntervalMillis(e(bundle, logger, "io.sentry.session-tracking.timeout-interval-millis", sentryAndroidOptions.getSessionTrackingIntervalMillis()));
                sentryAndroidOptions.setMaxBreadcrumbs((int) e(bundle, logger, "io.sentry.max-breadcrumbs", sentryAndroidOptions.getMaxBreadcrumbs()));
                sentryAndroidOptions.setEnableActivityLifecycleBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.activity-lifecycle", sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs()));
                sentryAndroidOptions.setEnableAppLifecycleBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.app-lifecycle", sentryAndroidOptions.isEnableAppLifecycleBreadcrumbs()));
                sentryAndroidOptions.setEnableSystemEventBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.system-events", sentryAndroidOptions.isEnableSystemEventBreadcrumbs()));
                sentryAndroidOptions.setEnableAppComponentBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.app-components", sentryAndroidOptions.isEnableAppComponentBreadcrumbs()));
                sentryAndroidOptions.setEnableUserInteractionBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.user-interaction", sentryAndroidOptions.isEnableUserInteractionBreadcrumbs()));
                sentryAndroidOptions.setEnableNetworkEventBreadcrumbs(b(bundle, logger, "io.sentry.breadcrumbs.network-events", sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
                sentryAndroidOptions.setEnableUncaughtExceptionHandler(b(bundle, logger, "io.sentry.uncaught-exception-handler.enable", sentryAndroidOptions.isEnableUncaughtExceptionHandler()));
                sentryAndroidOptions.setAttachThreads(b(bundle, logger, "io.sentry.attach-threads", sentryAndroidOptions.isAttachThreads()));
                sentryAndroidOptions.setAttachScreenshot(b(bundle, logger, "io.sentry.attach-screenshot", sentryAndroidOptions.isAttachScreenshot()));
                sentryAndroidOptions.setAttachViewHierarchy(b(bundle, logger, "io.sentry.attach-view-hierarchy", sentryAndroidOptions.isAttachViewHierarchy()));
                sentryAndroidOptions.setSendClientReports(b(bundle, logger, "io.sentry.send-client-reports", sentryAndroidOptions.isSendClientReports()));
                if (b(bundle, logger, "io.sentry.auto-init", true)) {
                    sentryAndroidOptions.setInitPriority(io.sentry.q1.LOW);
                }
                sentryAndroidOptions.setForceInit(b(bundle, logger, "io.sentry.force-init", sentryAndroidOptions.isForceInit()));
                sentryAndroidOptions.setCollectAdditionalContext(b(bundle, logger, "io.sentry.additional-context", sentryAndroidOptions.isCollectAdditionalContext()));
                sentryAndroidOptions.setCollectExternalStorageContext(b(bundle, logger, "io.sentry.external-storage-context", sentryAndroidOptions.isCollectExternalStorageContext()));
                if (sentryAndroidOptions.getTracesSampleRate() == null) {
                    double dC2 = c(bundle, logger, "io.sentry.traces.sample-rate");
                    if (dC2 != -1.0d) {
                        sentryAndroidOptions.setTracesSampleRate(Double.valueOf(dC2));
                    }
                }
                sentryAndroidOptions.setTraceSampling(b(bundle, logger, "io.sentry.traces.trace-sampling", sentryAndroidOptions.isTraceSampling()));
                sentryAndroidOptions.setEnableAutoActivityLifecycleTracing(b(bundle, logger, "io.sentry.traces.activity.enable", sentryAndroidOptions.isEnableAutoActivityLifecycleTracing()));
                sentryAndroidOptions.setEnableActivityLifecycleTracingAutoFinish(b(bundle, logger, "io.sentry.traces.activity.auto-finish.enable", sentryAndroidOptions.isEnableActivityLifecycleTracingAutoFinish()));
                if (sentryAndroidOptions.getProfilesSampleRate() == null) {
                    double dC3 = c(bundle, logger, "io.sentry.traces.profiling.sample-rate");
                    if (dC3 != -1.0d) {
                        sentryAndroidOptions.setProfilesSampleRate(Double.valueOf(dC3));
                    }
                }
                if (sentryAndroidOptions.getProfileSessionSampleRate() == null) {
                    double dC4 = c(bundle, logger, "io.sentry.traces.profiling.session-sample-rate");
                    if (dC4 != -1.0d) {
                        sentryAndroidOptions.setProfileSessionSampleRate(Double.valueOf(dC4));
                    }
                }
                String strName2 = sentryAndroidOptions.getProfileLifecycle().name();
                Locale locale2 = Locale.ROOT;
                String strF4 = f(bundle, logger, "io.sentry.traces.profiling.lifecycle", strName2.toLowerCase(locale2));
                if (strF4 != null) {
                    sentryAndroidOptions.setProfileLifecycle(s3.valueOf(strF4.toUpperCase(locale2)));
                }
                sentryAndroidOptions.setStartProfilerOnAppStart(b(bundle, logger, "io.sentry.traces.profiling.start-on-app-start", sentryAndroidOptions.isStartProfilerOnAppStart()));
                sentryAndroidOptions.setEnableUserInteractionTracing(b(bundle, logger, "io.sentry.traces.user-interaction.enable", sentryAndroidOptions.isEnableUserInteractionTracing()));
                sentryAndroidOptions.setEnableTimeToFullDisplayTracing(b(bundle, logger, "io.sentry.traces.time-to-full-display.enable", sentryAndroidOptions.isEnableTimeToFullDisplayTracing()));
                long jE = e(bundle, logger, "io.sentry.traces.idle-timeout", -1L);
                if (jE != -1) {
                    sentryAndroidOptions.setIdleTimeout(Long.valueOf(jE));
                }
                List<String> listD5 = d(bundle, logger, "io.sentry.traces.trace-propagation-targets");
                if (bundle.containsKey("io.sentry.traces.trace-propagation-targets") && listD5 == null) {
                    sentryAndroidOptions.setTracePropagationTargets(Collections.EMPTY_LIST);
                } else if (listD5 != null) {
                    sentryAndroidOptions.setTracePropagationTargets(listD5);
                }
                sentryAndroidOptions.setEnableFramesTracking(b(bundle, logger, "io.sentry.traces.frames-tracking", true));
                sentryAndroidOptions.setProguardUuid(f(bundle, logger, "io.sentry.proguard-uuid", sentryAndroidOptions.getProguardUuid()));
                io.sentry.protocol.t sdkVersion = sentryAndroidOptions.getSdkVersion();
                if (sdkVersion == null) {
                    sdkVersion = new io.sentry.protocol.t("", "");
                }
                String strG = g(bundle, logger, "io.sentry.sdk.name", sdkVersion.a());
                ac.b.V(strG, "name is required.");
                sdkVersion.f17695i = strG;
                String strG2 = g(bundle, logger, "io.sentry.sdk.version", sdkVersion.b());
                ac.b.V(strG2, "version is required.");
                sdkVersion.f17696l = strG2;
                sentryAndroidOptions.setSdkVersion(sdkVersion);
                sentryAndroidOptions.setSendDefaultPii(b(bundle, logger, "io.sentry.send-default-pii", sentryAndroidOptions.isSendDefaultPii()));
                List listD6 = d(bundle, logger, "io.sentry.gradle-plugin-integrations");
                if (listD6 != null) {
                    Iterator it = listD6.iterator();
                    while (it.hasNext()) {
                        u5.d().a((String) it.next());
                    }
                }
                sentryAndroidOptions.setEnableRootCheck(b(bundle, logger, "io.sentry.enable-root-check", sentryAndroidOptions.isEnableRootCheck()));
                sentryAndroidOptions.setSendModules(b(bundle, logger, "io.sentry.send-modules", sentryAndroidOptions.isSendModules()));
                sentryAndroidOptions.setEnablePerformanceV2(b(bundle, logger, "io.sentry.performance-v2.enable", sentryAndroidOptions.isEnablePerformanceV2()));
                sentryAndroidOptions.setEnableAppStartProfiling(b(bundle, logger, "io.sentry.profiling.enable-app-start", sentryAndroidOptions.isEnableAppStartProfiling()));
                sentryAndroidOptions.setEnableScopePersistence(b(bundle, logger, "io.sentry.enable-scope-persistence", sentryAndroidOptions.isEnableScopePersistence()));
                sentryAndroidOptions.setEnableAutoTraceIdGeneration(b(bundle, logger, "io.sentry.traces.enable-auto-id-generation", sentryAndroidOptions.isEnableAutoTraceIdGeneration()));
                sentryAndroidOptions.setDeadlineTimeout(e(bundle, logger, "io.sentry.traces.deadline-timeout", sentryAndroidOptions.getDeadlineTimeout()));
                if (sentryAndroidOptions.getSessionReplay().s() == null) {
                    double dC5 = c(bundle, logger, "io.sentry.session-replay.session-sample-rate");
                    if (dC5 != -1.0d) {
                        sentryAndroidOptions.getSessionReplay().B(Double.valueOf(dC5));
                    }
                }
                if (sentryAndroidOptions.getSessionReplay().q() == null) {
                    double dC6 = c(bundle, logger, "io.sentry.session-replay.on-error-sample-rate");
                    if (dC6 != -1.0d) {
                        sentryAndroidOptions.getSessionReplay().A(Double.valueOf(dC6));
                    }
                }
                sentryAndroidOptions.getSessionReplay().j(b(bundle, logger, "io.sentry.session-replay.mask-all-text", true));
                sentryAndroidOptions.getSessionReplay().i(b(bundle, logger, "io.sentry.session-replay.mask-all-images", true));
                sentryAndroidOptions.getSessionReplay().u(b(bundle, logger, "io.sentry.session-replay.debug", false));
                String strF5 = f(bundle, logger, "io.sentry.session-replay.screenshot-strategy", null);
                if (strF5 != null) {
                    if ("canvas".equals(strF5.toLowerCase(Locale.ROOT))) {
                        sentryAndroidOptions.getSessionReplay().x = k4.CANVAS;
                    } else {
                        sentryAndroidOptions.getSessionReplay().x = k4.PIXEL_COPY;
                    }
                }
                if (sentryAndroidOptions.getSessionReplay().m().isEmpty() && (listD4 = d(bundle, logger, "io.sentry.session-replay.network-detail-allow-urls")) != null && !listD4.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it2 = listD4.iterator();
                    while (it2.hasNext()) {
                        String strTrim = ((String) it2.next()).trim();
                        if (!strTrim.isEmpty()) {
                            arrayList.add(strTrim);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        sentryAndroidOptions.getSessionReplay().w(arrayList);
                    }
                }
                if (sentryAndroidOptions.getSessionReplay().n().isEmpty() && (listD3 = d(bundle, logger, "io.sentry.session-replay.network-detail-deny-urls")) != null && !listD3.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it3 = listD3.iterator();
                    while (it3.hasNext()) {
                        String strTrim2 = ((String) it3.next()).trim();
                        if (!strTrim2.isEmpty()) {
                            arrayList2.add(strTrim2);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        sentryAndroidOptions.getSessionReplay().x(arrayList2);
                    }
                }
                sentryAndroidOptions.getSessionReplay().v(b(bundle, logger, "io.sentry.session-replay.network-capture-bodies", sentryAndroidOptions.getSessionReplay().t()));
                if (sentryAndroidOptions.getSessionReplay().o().size() == d7.D.size() && (listD2 = d(bundle, logger, "io.sentry.session-replay.network-request-headers")) != null) {
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it4 = listD2.iterator();
                    while (it4.hasNext()) {
                        String strTrim3 = ((String) it4.next()).trim();
                        if (!strTrim3.isEmpty()) {
                            arrayList3.add(strTrim3);
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        sentryAndroidOptions.getSessionReplay().y(arrayList3);
                    }
                }
                if (sentryAndroidOptions.getSessionReplay().p().size() == d7.D.size() && (listD = d(bundle, logger, "io.sentry.session-replay.network-response-headers")) != null && !listD.isEmpty()) {
                    ArrayList arrayList4 = new ArrayList();
                    Iterator it5 = listD.iterator();
                    while (it5.hasNext()) {
                        String strTrim4 = ((String) it5.next()).trim();
                        if (!strTrim4.isEmpty()) {
                            arrayList4.add(strTrim4);
                        }
                    }
                    if (!arrayList4.isEmpty()) {
                        sentryAndroidOptions.getSessionReplay().z(arrayList4);
                    }
                }
                sentryAndroidOptions.setIgnoredErrors(d(bundle, logger, "io.sentry.ignored-errors"));
                List listD7 = d(bundle, logger, "io.sentry.in-app-includes");
                if (listD7 != null && !listD7.isEmpty()) {
                    Iterator it6 = listD7.iterator();
                    while (it6.hasNext()) {
                        sentryAndroidOptions.addInAppInclude((String) it6.next());
                    }
                }
                List listD8 = d(bundle, logger, "io.sentry.in-app-excludes");
                if (listD8 != null && !listD8.isEmpty()) {
                    Iterator it7 = listD8.iterator();
                    while (it7.hasNext()) {
                        sentryAndroidOptions.addInAppExclude((String) it7.next());
                    }
                }
                sentryAndroidOptions.getLogs().b(b(bundle, logger, "io.sentry.logs.enabled", sentryAndroidOptions.getLogs().a()));
                sentryAndroidOptions.getMetrics().b(b(bundle, logger, "io.sentry.metrics.enabled", sentryAndroidOptions.getMetrics().a()));
                r5 feedbackOptions = sentryAndroidOptions.getFeedbackOptions();
                feedbackOptions.i(b(bundle, logger, "io.sentry.feedback.is-name-required", feedbackOptions.b()));
                feedbackOptions.l(b(bundle, logger, "io.sentry.feedback.show-name", feedbackOptions.e()));
                feedbackOptions.h(b(bundle, logger, "io.sentry.feedback.is-email-required", feedbackOptions.a()));
                feedbackOptions.k(b(bundle, logger, "io.sentry.feedback.show-email", feedbackOptions.d()));
                feedbackOptions.m(b(bundle, logger, "io.sentry.feedback.use-sentry-user", feedbackOptions.f()));
                feedbackOptions.j(b(bundle, logger, "io.sentry.feedback.show-branding", feedbackOptions.c()));
                feedbackOptions.n(b(bundle, logger, "io.sentry.feedback.use-shake-gesture", feedbackOptions.g()));
                sentryAndroidOptions.setStrictTraceContinuation(b(bundle, logger, "io.sentry.strict-trace-continuation.enabled", sentryAndroidOptions.isStrictTraceContinuation()));
                String strF6 = f(bundle, logger, "io.sentry.org-id", null);
                if (strF6 != null) {
                    sentryAndroidOptions.setOrgId(strF6);
                }
                sentryAndroidOptions.setEnableSpotlight(b(bundle, logger, "io.sentry.spotlight.enable", sentryAndroidOptions.isEnableSpotlight()));
                String strF7 = f(bundle, logger, "io.sentry.spotlight.url", null);
                if (strF7 != null) {
                    sentryAndroidOptions.setSpotlightConnectionUrl(strF7);
                }
                sentryAndroidOptions.getScreenshot().j(b(bundle, logger, "io.sentry.screenshot.mask-all-text", false));
                sentryAndroidOptions.getScreenshot().i(b(bundle, logger, "io.sentry.screenshot.mask-all-images", false));
                if (sentryAndroidOptions.getAnrProfilingSampleRate() == null) {
                    double dC7 = c(bundle, logger, "io.sentry.anr.profiling.sample-rate");
                    if (dC7 != -1.0d) {
                        sentryAndroidOptions.setAnrProfilingSampleRate(Double.valueOf(dC7));
                    }
                }
                sentryAndroidOptions.setEnableAnrFingerprinting(b(bundle, logger, "io.sentry.anr.enable-fingerprinting", sentryAndroidOptions.isEnableAnrFingerprinting()));
            }
            sentryAndroidOptions.getLogger().q(w5.INFO, "Retrieving configuration from AndroidManifest.xml", new Object[0]);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to read configuration from android manifest metadata.", th);
        }
    }

    public static boolean b(Bundle bundle, ILogger iLogger, String str, boolean z) {
        boolean z5 = bundle.getBoolean(str, z);
        iLogger.q(w5.DEBUG, str + " read: " + z5, new Object[0]);
        return z5;
    }

    public static double c(Bundle bundle, ILogger iLogger, String str) {
        double dDoubleValue = Float.valueOf(bundle.getFloat(str, -1.0f)).doubleValue();
        if (dDoubleValue == -1.0d) {
            dDoubleValue = Integer.valueOf(bundle.getInt(str, -1)).doubleValue();
        }
        iLogger.q(w5.DEBUG, str + " read: " + dDoubleValue, new Object[0]);
        return dDoubleValue;
    }

    public static List d(Bundle bundle, ILogger iLogger, String str) {
        String string = bundle.getString(str);
        iLogger.q(w5.DEBUG, androidx.compose.foundation.c.t(str, " read: ", string), new Object[0]);
        if (string != null) {
            return Arrays.asList(string.split(",", -1));
        }
        return null;
    }

    public static long e(Bundle bundle, ILogger iLogger, String str, long j10) {
        long j11 = bundle.getInt(str, (int) j10);
        iLogger.q(w5.DEBUG, str + " read: " + j11, new Object[0]);
        return j11;
    }

    public static String f(Bundle bundle, ILogger iLogger, String str, String str2) {
        String string = bundle.getString(str, str2);
        iLogger.q(w5.DEBUG, androidx.compose.foundation.c.t(str, " read: ", string), new Object[0]);
        return string;
    }

    public static String g(Bundle bundle, ILogger iLogger, String str, String str2) {
        String string = bundle.getString(str, str2);
        iLogger.q(w5.DEBUG, androidx.compose.foundation.c.t(str, " read: ", string), new Object[0]);
        return string;
    }
}
