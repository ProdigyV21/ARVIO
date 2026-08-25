package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.widget.u4;
import io.sentry.ILogger;
import io.sentry.android.core.EnvelopeFileObserverIntegration;
import io.sentry.android.core.anr.AnrProfilingIntegration;
import io.sentry.android.distribution.DistributionIntegration;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import io.sentry.compose.gestures.ComposeGestureTargetLocator;
import io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter;
import io.sentry.e3;
import io.sentry.j3;
import io.sentry.o2;
import io.sentry.o4;
import io.sentry.p2;
import io.sentry.q2;
import io.sentry.r2;
import io.sentry.w2;
import io.sentry.w5;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r {
    public static void a(SentryAndroidOptions sentryAndroidOptions, Context context, r0 r0Var, io.sentry.util.l lVar, u4 u4Var, boolean z) {
        if (sentryAndroidOptions.getCacheDirPath() != null && (sentryAndroidOptions.getEnvelopeDiskCache() instanceof io.sentry.transport.i)) {
            sentryAndroidOptions.setEnvelopeDiskCache(new io.sentry.android.core.cache.e(sentryAndroidOptions));
        }
        if (sentryAndroidOptions.getConnectionStatusProvider() instanceof p2) {
            sentryAndroidOptions.setConnectionStatusProvider(new io.sentry.android.core.internal.util.c(context, r0Var, sentryAndroidOptions));
        }
        if (sentryAndroidOptions.getCacheDirPath() != null) {
            sentryAndroidOptions.addScopeObserver(new io.sentry.cache.g(sentryAndroidOptions));
            sentryAndroidOptions.addOptionsObserver(new io.sentry.cache.e(sentryAndroidOptions));
        }
        sentryAndroidOptions.addEventProcessor(new io.sentry.n(sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new s0(context, r0Var, sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new h1(sentryAndroidOptions, u4Var));
        sentryAndroidOptions.addEventProcessor(new ScreenshotEventProcessor(sentryAndroidOptions, r0Var, z));
        sentryAndroidOptions.addEventProcessor(new ViewHierarchyEventProcessor(sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new k0(context, r0Var, sentryAndroidOptions));
        if (sentryAndroidOptions.getTransportGate() instanceof io.sentry.transport.k) {
            sentryAndroidOptions.setTransportGate(new z(sentryAndroidOptions));
        }
        io.sentry.android.core.performance.g gVarC = io.sentry.android.core.performance.g.c();
        if (sentryAndroidOptions.getModulesLoader() instanceof io.sentry.internal.modules.e) {
            sentryAndroidOptions.setModulesLoader(new io.sentry.internal.modules.f(context, sentryAndroidOptions));
        }
        if (sentryAndroidOptions.getDebugMetaLoader() instanceof io.sentry.internal.debugmeta.b) {
            ILogger logger = sentryAndroidOptions.getLogger();
            fi.iki.elonen.f fVar = new fi.iki.elonen.f();
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            fVar.f15416i = applicationContext;
            fVar.f15417l = logger;
            sentryAndroidOptions.setDebugMetaLoader(fVar);
        }
        if (sentryAndroidOptions.getVersionDetector() instanceof j3) {
            sentryAndroidOptions.setVersionDetector(new io.sentry.u(sentryAndroidOptions, 0));
        }
        io.sentry.util.k kVar = new io.sentry.util.k(new io.sentry.cache.a(lVar, sentryAndroidOptions, 2));
        boolean zL = io.sentry.util.l.l(sentryAndroidOptions, "androidx.compose.ui.node.Owner");
        if (sentryAndroidOptions.getGestureTargetLocators().isEmpty()) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new io.sentry.android.core.internal.gestures.a(kVar));
            if (zL && io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.compose.gestures.ComposeGestureTargetLocator")) {
                arrayList.add(new ComposeGestureTargetLocator(sentryAndroidOptions.getLogger()));
            }
            sentryAndroidOptions.setGestureTargetLocators(arrayList);
        }
        if (sentryAndroidOptions.getViewHierarchyExporters().isEmpty() && zL && io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter")) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(new ComposeViewHierarchyExporter(sentryAndroidOptions.getLogger()));
            sentryAndroidOptions.setViewHierarchyExporters(arrayList2);
        }
        if (sentryAndroidOptions.getThreadChecker() instanceof io.sentry.util.thread.b) {
            sentryAndroidOptions.setThreadChecker(io.sentry.android.core.internal.util.e.f16600a);
        }
        if (sentryAndroidOptions.getSocketTagger() instanceof e3) {
            sentryAndroidOptions.setSocketTagger(v.f16791l);
        }
        if (sentryAndroidOptions.getPerformanceCollectors().isEmpty()) {
            sentryAndroidOptions.addPerformanceCollector(new o());
            sentryAndroidOptions.addPerformanceCollector(new j(sentryAndroidOptions.getLogger()));
            if (sentryAndroidOptions.isEnablePerformanceV2()) {
                io.sentry.android.core.internal.util.q frameMetricsCollector = sentryAndroidOptions.getFrameMetricsCollector();
                ac.b.V(frameMetricsCollector, "options.getFrameMetricsCollector is required");
                sentryAndroidOptions.addPerformanceCollector(new u1(sentryAndroidOptions, frameMetricsCollector));
            }
        }
        if (sentryAndroidOptions.getCompositePerformanceCollector() instanceof o2) {
            sentryAndroidOptions.setCompositePerformanceCollector(new io.sentry.r(sentryAndroidOptions));
        }
        if (z && (sentryAndroidOptions.getReplayController().getX() instanceof w2)) {
            sentryAndroidOptions.getReplayController().j(new io.sentry.android.replay.d(sentryAndroidOptions));
        }
        io.sentry.util.a aVarA = io.sentry.android.core.performance.g.B.a();
        try {
            io.sentry.n1 n1Var = gVarC.f16714s;
            i iVar = gVarC.f16715t;
            gVarC.f16714s = null;
            gVarC.f16715t = null;
            aVarA.close();
            io.sentry.l compositePerformanceCollector = sentryAndroidOptions.getCompositePerformanceCollector();
            if (sentryAndroidOptions.isProfilingEnabled() || sentryAndroidOptions.getProfilesSampleRate() != null) {
                sentryAndroidOptions.setContinuousProfiler(q2.f17747i);
                if (iVar != null) {
                    iVar.a(true);
                }
                if (n1Var != null) {
                    sentryAndroidOptions.setTransactionProfiler(n1Var);
                    return;
                }
                io.sentry.android.core.internal.util.q frameMetricsCollector2 = sentryAndroidOptions.getFrameMetricsCollector();
                ac.b.V(frameMetricsCollector2, "options.getFrameMetricsCollector is required");
                sentryAndroidOptions.setTransactionProfiler(new x(context, r0Var, frameMetricsCollector2, sentryAndroidOptions.getLogger(), sentryAndroidOptions.getProfilingTracesDirPath(), sentryAndroidOptions.isProfilingEnabled(), sentryAndroidOptions.getProfilingTracesHz(), new q(sentryAndroidOptions, 3)));
                return;
            }
            sentryAndroidOptions.setTransactionProfiler(r2.f17774m);
            if (n1Var != null) {
                n1Var.close();
            }
            if (iVar == null) {
                io.sentry.android.core.internal.util.q frameMetricsCollector3 = sentryAndroidOptions.getFrameMetricsCollector();
                ac.b.V(frameMetricsCollector3, "options.getFrameMetricsCollector is required");
                sentryAndroidOptions.setContinuousProfiler(new i(r0Var, frameMetricsCollector3, sentryAndroidOptions.getLogger(), sentryAndroidOptions.getProfilingTracesDirPath(), sentryAndroidOptions.getProfilingTracesHz(), new q(sentryAndroidOptions, 0)));
            } else {
                sentryAndroidOptions.setContinuousProfiler(iVar);
                io.sentry.protocol.v vVar = iVar.f16517y;
                if (!iVar.f16512s || vVar.equals(io.sentry.protocol.v.f17707l)) {
                    return;
                }
                compositePerformanceCollector.f(vVar.toString());
            }
        } finally {
        }
    }

    public static void b(Context context, SentryAndroidOptions sentryAndroidOptions, r0 r0Var, io.sentry.util.l lVar, u4 u4Var, boolean z, boolean z5, boolean z10, boolean z11) {
        io.sentry.util.k kVar = new io.sentry.util.k(new q(sentryAndroidOptions, 1));
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new o4(new q(sentryAndroidOptions, 2), 0), kVar));
        sentryAndroidOptions.addIntegration(new NdkIntegration(io.sentry.util.l.o("io.sentry.android.ndk.SentryNdk", sentryAndroidOptions.getLogger())));
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 31) {
            sentryAndroidOptions.addIntegration(new TombstoneIntegration(context));
        }
        sentryAndroidOptions.addIntegration(new EnvelopeFileObserverIntegration.OutboxEnvelopeFileObserverIntegration(0));
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new o4(new q(sentryAndroidOptions, 2), 1), kVar));
        sentryAndroidOptions.addIntegration(new AppLifecycleIntegration());
        sentryAndroidOptions.addIntegration(i10 >= 30 ? new AnrV2Integration(context) : new AnrIntegration(context));
        sentryAndroidOptions.addIntegration(new AnrProfilingIntegration());
        if (context instanceof Application) {
            Application application = (Application) context;
            sentryAndroidOptions.addIntegration(new ActivityLifecycleIntegration(application, r0Var, u4Var));
            sentryAndroidOptions.addIntegration(new ActivityBreadcrumbsIntegration(application));
            sentryAndroidOptions.addIntegration(new UserInteractionIntegration(application));
            sentryAndroidOptions.addIntegration(new FeedbackShakeIntegration(application));
            if (z) {
                sentryAndroidOptions.addIntegration(new FragmentLifecycleIntegration(application, true, true));
            }
        } else {
            sentryAndroidOptions.getLogger().q(w5.WARNING, "ActivityLifecycle, FragmentLifecycle and UserInteraction Integrations need an Application class to be installed.", new Object[0]);
        }
        if (z5) {
            sentryAndroidOptions.addIntegration(new SentryTimberIntegration());
        }
        sentryAndroidOptions.addIntegration(new AppComponentsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new SystemEventsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new NetworkBreadcrumbsIntegration(context, r0Var));
        if (z10) {
            ReplayIntegration replayIntegration = new ReplayIntegration(context);
            sentryAndroidOptions.addIntegration(replayIntegration);
            sentryAndroidOptions.setReplayController(replayIntegration);
        }
        if (z11) {
            DistributionIntegration distributionIntegration = new DistributionIntegration(context);
            sentryAndroidOptions.setDistributionController(distributionIntegration);
            sentryAndroidOptions.addIntegration(distributionIntegration);
        }
        sentryAndroidOptions.getFeedbackOptions().f17785i = new v(6);
    }
}
