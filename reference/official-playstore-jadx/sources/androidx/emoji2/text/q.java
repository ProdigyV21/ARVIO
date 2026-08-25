package androidx.emoji2.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioTrack;
import android.os.Handler;
import androidx.appcompat.app.i1;
import androidx.media3.common.Format;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioTrackAudioOutput;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.discord.org.webrtc.EglRenderer;
import com.discord.org.webrtc.VideoFileRenderer;
import com.discord.org.webrtc.VideoFrame;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.q1;
import com.google.firebase.crashlytics.internal.common.Utils;
import io.sentry.c4;
import io.sentry.l7;
import io.sentry.o7;
import io.sentry.w5;
import io.sentry.y6;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.r0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class q implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2637i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2638l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2639m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f2640n;

    public /* synthetic */ q(Object obj, Object obj2, Object obj3, int i10) {
        this.f2637i = i10;
        this.f2638l = obj;
        this.f2639m = obj2;
        this.f2640n = obj3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void a() {
        boolean zBooleanValue;
        androidx.work.impl.g gVar = (androidx.work.impl.g) this.f2638l;
        d1 d1Var = (d1) this.f2639m;
        androidx.work.impl.v vVar = (androidx.work.impl.v) this.f2640n;
        try {
            zBooleanValue = ((Boolean) d1Var.get()).booleanValue();
        } catch (InterruptedException | ExecutionException unused) {
            zBooleanValue = true;
        }
        synchronized (gVar.k) {
            try {
                androidx.work.impl.model.j jVarN = a.a.N(vVar.f7090n);
                String str = jVarN.f6914a;
                if (gVar.e(str) == vVar) {
                    gVar.c(str);
                }
                m2.t.d().a(androidx.work.impl.g.f6878l, androidx.work.impl.g.class.getSimpleName() + " " + str + " executed; reschedule = " + zBooleanValue);
                Iterator it = gVar.f6888j.iterator();
                while (it.hasNext()) {
                    ((androidx.work.impl.d) it.next()).c(jVarN, zBooleanValue);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, x6.s] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2637i) {
            case 0:
                x xVar = (x) this.f2638l;
                m2.f0 f0Var = (m2.f0) this.f2639m;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.f2640n;
                try {
                    b0 b0VarL = kotlin.reflect.b0.l((Context) xVar.f2650a);
                    if (b0VarL == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    a0 a0Var = (a0) ((l) b0VarL.f2613b);
                    synchronized (a0Var.f2589d) {
                        a0Var.f2591f = threadPoolExecutor;
                        break;
                    }
                    ((l) b0VarL.f2613b).a(new r(f0Var, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    f0Var.D(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 1:
                Util.lambda$transformFutureAsync$2((d1) this.f2638l, (q1) this.f2639m, (com.google.common.util.concurrent.w) this.f2640n);
                return;
            case 2:
                Util.lambda$postOrRunWithCompletion$0((q1) this.f2638l, (Runnable) this.f2639m, this.f2640n);
                return;
            case 3:
                ((AudioRendererEventListener.EventDispatcher) this.f2638l).lambda$inputFormatChanged$2((Format) this.f2639m, (DecoderReuseEvaluation) this.f2640n);
                return;
            case 4:
                AudioTrackAudioOutput.lambda$releaseAudioTrackAsync$1((AudioTrack) this.f2638l, (Handler) this.f2639m, (ListenerSet) this.f2640n);
                return;
            case 5:
                ((DrmSessionEventListener.EventDispatcher) this.f2638l).lambda$drmSessionManagerError$2((DrmSessionEventListener) this.f2639m, (Exception) this.f2640n);
                return;
            case 6:
                ((DrmSessionEventListener.EventDispatcher) this.f2638l).lambda$drmKeysLoaded$1((DrmSessionEventListener) this.f2639m, (KeyRequestInfo) this.f2640n);
                return;
            case 7:
                a();
                return;
            case 8:
                ((EglRenderer) this.f2638l).lambda$removeFrameListener$2((CountDownLatch) this.f2639m, (EglRenderer.FrameListener) this.f2640n);
                return;
            case 9:
                ((EglRenderer) this.f2638l).lambda$removeRenderListener$3((CountDownLatch) this.f2639m, (EglRenderer.RenderListener) this.f2640n);
                return;
            case 10:
                ((VideoFileRenderer) this.f2638l).lambda$renderFrameOnRenderThread$1((VideoFrame.I420Buffer) this.f2639m, (VideoFrame) this.f2640n);
                return;
            case 11:
                Utils.lambda$callTask$3((Callable) this.f2638l, (Executor) this.f2639m, (com.google.android.gms.tasks.i) this.f2640n);
                return;
            case 12:
                io.sentry.android.replay.screenshot.e eVar = (io.sentry.android.replay.screenshot.e) this.f2638l;
                io.sentry.android.replay.viewhierarchy.g gVar = (io.sentry.android.replay.viewhierarchy.g) this.f2639m;
                AtomicBoolean atomicBoolean = eVar.k;
                y6 y6Var = eVar.f17014b;
                Bitmap bitmap = eVar.f17018f;
                if (atomicBoolean.get() || bitmap.isRecycled()) {
                    y6Var.getLogger().q(w5.DEBUG, "PixelCopyStrategy is closed, skipping masking", new Object[0]);
                    return;
                }
                eVar.f17021i.i(bitmap, gVar, (Matrix) eVar.f17019g.getValue());
                y6Var.getReplayController().getClass();
                eVar.f17013a.T(bitmap);
                eVar.f17020h.set(true);
                eVar.f17022j.set(false);
                return;
            case 13:
                io.sentry.cache.g gVar2 = (io.sentry.cache.g) this.f2638l;
                l7 l7Var = (l7) this.f2639m;
                c4 c4Var = (c4) this.f2640n;
                if (l7Var != null) {
                    gVar2.f(l7Var, "trace.json");
                    return;
                }
                i1 i1Var = c4Var.C;
                l7 l7Var2 = new l7((io.sentry.protocol.v) i1Var.f1059l, (o7) i1Var.f1060m, "default", null);
                l7Var2.f17443s = TtmlNode.TEXT_EMPHASIS_AUTO;
                gVar2.f(l7Var2, "trace.json");
                return;
            default:
                fi.iki.elonen.f fVar = (fi.iki.elonen.f) this.f2638l;
                retrofit2.f fVar2 = (retrofit2.f) this.f2639m;
                r0 r0Var = (r0) this.f2640n;
                retrofit2.m mVar = (retrofit2.m) fVar.f15417l;
                if (mVar.f21522l.k()) {
                    fVar2.onFailure(new IOException("Canceled"));
                    return;
                } else {
                    fVar2.C(mVar, r0Var);
                    return;
                }
        }
    }
}
