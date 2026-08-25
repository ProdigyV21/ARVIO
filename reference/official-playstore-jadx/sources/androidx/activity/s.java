package androidx.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.metrics.NetworkEvent;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;
import android.os.Process;
import android.os.StrictMode;
import android.view.Choreographer;
import androidx.appcompat.app.z0;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView;
import androidx.media3.ui.PlayerView;
import com.arflix.tv.data.telegram.TelegramSourceResolver;
import com.discord.socialsdk.AuthenticationClientCallback;
import com.discord.socialsdk.NativeCalls;
import com.google.common.collect.c1;
import com.google.common.util.concurrent.d1;
import io.sentry.ILogger;
import io.sentry.android.core.ActivityLifecycleIntegration;
import io.sentry.android.core.AnrIntegration;
import io.sentry.android.core.FeedbackShakeIntegration;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.anr.AnrProfilingIntegration;
import io.sentry.android.core.s1;
import io.sentry.k1;
import io.sentry.w5;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import m5.a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1016i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1017l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f1018m;

    public /* synthetic */ s(ActivityLifecycleIntegration activityLifecycleIntegration, k1 k1Var, k1 k1Var2) {
        this.f1016i = 23;
        this.f1017l = k1Var;
        this.f1018m = k1Var2;
    }

    private final void a() {
        a.InterfaceC0243a interfaceC0243a;
        com.google.firebase.components.q qVar = (com.google.firebase.components.q) this.f1017l;
        m5.b bVar = (m5.b) this.f1018m;
        if (qVar.f14293b != com.google.firebase.components.q.f14291d) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (qVar) {
            interfaceC0243a = qVar.f14292a;
            qVar.f14292a = null;
            qVar.f14293b = bVar;
        }
        interfaceC0243a.h(bVar);
    }

    private final void b() {
        com.google.firebase.components.p pVar = (com.google.firebase.components.p) this.f1017l;
        m5.b bVar = (m5.b) this.f1018m;
        synchronized (pVar) {
            try {
                if (pVar.f14289b == null) {
                    pVar.f14288a.add(bVar);
                } else {
                    pVar.f14289b.add(bVar.get());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1016i) {
            case 0:
                t tVar = (t) this.f1017l;
                tVar.getLifecycle().addObserver(new i((l0) this.f1018m, tVar, 0));
                return;
            case 1:
                z0 z0Var = (z0) this.f1017l;
                Runnable runnable = (Runnable) this.f1018m;
                z0Var.getClass();
                try {
                    runnable.run();
                    return;
                } finally {
                    z0Var.a();
                }
            case 2:
                ((SimpleBasePlayer) this.f1017l).lambda$updateStateForPendingOperation$64((d1) this.f1018m);
                return;
            case 3:
                AudioManagerCompat.lambda$getAudioManager$0((Context) this.f1017l, (ConditionVariable) this.f1018m);
                return;
            case 4:
                ((MediaMetricsListener) this.f1017l).lambda$reportTrackChangeEvent$3((TrackChangeEvent) this.f1018m);
                return;
            case 5:
                ((MediaMetricsListener) this.f1017l).lambda$maybeReportNetworkChange$1((NetworkEvent) this.f1018m);
                return;
            case 6:
                ((MediaMetricsListener) this.f1017l).lambda$maybeReportPlaybackError$0((PlaybackErrorEvent) this.f1018m);
                return;
            case 7:
                ((MediaMetricsListener) this.f1017l).lambda$finishCurrentSession$4((PlaybackMetrics) this.f1018m);
                return;
            case 8:
                ((MediaMetricsListener) this.f1017l).lambda$maybeReportPlaybackStateChange$2((PlaybackStateEvent) this.f1018m);
                return;
            case 9:
                ((SphericalGLSurfaceView) this.f1017l).lambda$onSurfaceTextureAvailable$1((SurfaceTexture) this.f1018m);
                return;
            case 10:
                ((PlayerView) this.f1017l).lambda$onImageAvailable$1((Bitmap) this.f1018m);
                return;
            case 11:
                androidx.work.impl.g gVar = (androidx.work.impl.g) this.f1017l;
                androidx.work.impl.model.j jVar = (androidx.work.impl.model.j) this.f1018m;
                synchronized (gVar.k) {
                    try {
                        Iterator it = gVar.f6888j.iterator();
                        while (it.hasNext()) {
                            ((androidx.work.impl.d) it.next()).c(jVar, false);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 12:
                androidx.work.impl.v vVar = (androidx.work.impl.v) this.f1017l;
                d1 d1Var = (d1) this.f1018m;
                if (vVar.A.f7037i instanceof androidx.work.impl.utils.futures.a) {
                    d1Var.cancel(true);
                    return;
                }
                return;
            case 13:
                List list = (List) this.f1017l;
                androidx.work.impl.constraints.trackers.f fVar = (androidx.work.impl.constraints.trackers.f) this.f1018m;
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((androidx.work.impl.constraints.a) it2.next()).a(fVar.f6831e);
                }
                return;
            case 14:
                androidx.work.impl.utils.p pVar = (androidx.work.impl.utils.p) this.f1017l;
                androidx.work.impl.utils.futures.k kVar = (androidx.work.impl.utils.futures.k) this.f1018m;
                if (pVar.f7056i.f7037i instanceof androidx.work.impl.utils.futures.a) {
                    kVar.cancel(true);
                    return;
                } else {
                    kVar.j(pVar.f7059n.getForegroundInfoAsync());
                    return;
                }
            case 15:
                TelegramSourceResolver.showToast$lambda$0((TelegramSourceResolver) this.f1017l, (String) this.f1018m);
                return;
            case 16:
                NativeCalls.lambda$authorize$2((String) this.f1017l, (AuthenticationClientCallback) this.f1018m);
                return;
            case 17:
                ((com.google.common.util.concurrent.n0) this.f1017l).h((c1) this.f1018m);
                return;
            case 18:
                a();
                return;
            case 19:
                b();
                return;
            case 20:
                com.google.firebase.concurrent.a aVar = (com.google.firebase.concurrent.a) this.f1017l;
                Runnable runnable2 = (Runnable) this.f1018m;
                Process.setThreadPriority(aVar.f14312c);
                StrictMode.ThreadPolicy threadPolicy = aVar.f14313d;
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                runnable2.run();
                return;
            case 21:
                Callable callable = (Callable) this.f1017l;
                com.google.firebase.concurrent.g gVar2 = (com.google.firebase.concurrent.g) this.f1018m;
                try {
                    gVar2.a(callable.call());
                    return;
                } catch (Exception e5) {
                    gVar2.b(e5);
                    return;
                }
            case 22:
                ((i0.o) this.f1017l).onFontRetrieved((Typeface) this.f1018m);
                return;
            case 23:
                ActivityLifecycleIntegration.j((k1) this.f1017l, (k1) this.f1018m);
                return;
            case 24:
                AnrIntegration anrIntegration = (AnrIntegration) this.f1017l;
                SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f1018m;
                io.sentry.util.a aVarA = anrIntegration.f16322m.a();
                try {
                    if (!anrIntegration.f16321l) {
                        anrIntegration.i(sentryAndroidOptions);
                        break;
                    }
                    aVarA.close();
                    return;
                } catch (Throwable th2) {
                    try {
                        aVarA.close();
                        break;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            case 25:
                ((io.sentry.android.core.h0) this.f1017l).j((ILogger) this.f1018m);
                return;
            case 26:
                FeedbackShakeIntegration feedbackShakeIntegration = (FeedbackShakeIntegration) this.f1017l;
                Activity activity = (Activity) this.f1018m;
                if (feedbackShakeIntegration.f16344o || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    feedbackShakeIntegration.f16344o = true;
                    Runnable runnable3 = feedbackShakeIntegration.f16342m.getFeedbackOptions().f17784h;
                    feedbackShakeIntegration.f16345p = runnable3;
                    feedbackShakeIntegration.f16342m.getFeedbackOptions().f17784h = new s(feedbackShakeIntegration, runnable3, 27);
                    new s1(activity).show();
                    return;
                } catch (Throwable th4) {
                    feedbackShakeIntegration.f16344o = false;
                    feedbackShakeIntegration.f16342m.getFeedbackOptions().f17784h = feedbackShakeIntegration.f16345p;
                    feedbackShakeIntegration.f16345p = null;
                    feedbackShakeIntegration.f16342m.getLogger().l(w5.ERROR, "Failed to show feedback dialog on shake.", th4);
                    return;
                }
            case 27:
                FeedbackShakeIntegration feedbackShakeIntegration2 = (FeedbackShakeIntegration) this.f1017l;
                Runnable runnable4 = (Runnable) this.f1018m;
                feedbackShakeIntegration2.f16344o = false;
                feedbackShakeIntegration2.f16342m.getFeedbackOptions().f17784h = runnable4;
                if (runnable4 != null) {
                    runnable4.run();
                }
                feedbackShakeIntegration2.f16345p = null;
                return;
            case 28:
                AnrProfilingIntegration anrProfilingIntegration = (AnrProfilingIntegration) this.f1017l;
                io.sentry.android.core.anr.d dVar = (io.sentry.android.core.anr.d) this.f1018m;
                anrProfilingIntegration.getClass();
                if (dVar == null) {
                    return;
                }
                try {
                    dVar.close();
                    return;
                } catch (IOException unused) {
                    anrProfilingIntegration.f16413s.q(w5.WARNING, "Failed to close AnrProfileManager", new Object[0]);
                    return;
                }
            default:
                io.sentry.android.core.internal.util.q qVar = (io.sentry.android.core.internal.util.q) this.f1017l;
                ILogger iLogger = (ILogger) this.f1018m;
                try {
                    qVar.f16641t = Choreographer.getInstance();
                    return;
                } catch (Throwable th5) {
                    iLogger.l(w5.ERROR, "Error retrieving Choreographer instance. Slow and frozen frames will not be reported.", th5);
                    return;
                }
        }
    }

    public /* synthetic */ s(Object obj, Object obj2, int i10) {
        this.f1016i = i10;
        this.f1017l = obj;
        this.f1018m = obj2;
    }
}
