package io.sentry.android.replay.screenshot;

import a8.g0;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.View;
import androidx.work.impl.t;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.f0;
import io.sentry.android.replay.y;
import io.sentry.w5;
import io.sentry.y6;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f17000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ReplayIntegration f17001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y6 f17002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y f17003d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Bitmap f17004e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReference f17005f = new AtomicReference(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.util.b f17006g = new io.sentry.util.b();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f17007h = f4.f.o(3, new g0(this, 25));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f17008i = new AtomicBoolean(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final g f17009j = new g();
    public final AtomicBoolean k = new AtomicBoolean(false);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SurfaceTexture f17010l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Surface f17011m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f17012n;

    public d(y6 y6Var, ReplayIntegration replayIntegration, y yVar, f0 f0Var) {
        this.f17000a = f0Var;
        this.f17001b = replayIntegration;
        this.f17002c = y6Var;
        this.f17003d = yVar;
        SurfaceTexture surfaceTextureA = a.a();
        surfaceTextureA.setDefaultBufferSize(yVar.f17089a, yVar.f17090b);
        this.f17010l = surfaceTextureA;
        this.f17011m = new Surface(surfaceTextureA);
        xc.d.H("ReplayCanvasStrategy");
        this.f17012n = new b(this, 0);
    }

    public static void d(final d dVar) {
        if (dVar.k.get()) {
            dVar.f17002c.getLogger().q(w5.DEBUG, "Canvas Strategy already closed, skipping picture render", new Object[0]);
            return;
        }
        Picture picture = (Picture) dVar.f17005f.getAndSet(null);
        if (picture == null) {
            return;
        }
        try {
            Canvas canvasLockHardwareCanvas = dVar.f17011m.lockHardwareCanvas();
            try {
                canvasLockHardwareCanvas.drawColor(-16777216, PorterDuff.Mode.CLEAR);
                picture.draw(canvasLockHardwareCanvas);
                dVar.f17011m.unlockCanvasAndPost(canvasLockHardwareCanvas);
                if (dVar.f17004e == null) {
                    io.sentry.util.a aVarA = dVar.f17006g.a();
                    try {
                        if (dVar.f17004e == null) {
                            y yVar = dVar.f17003d;
                            dVar.f17004e = Bitmap.createBitmap(yVar.f17089a, yVar.f17090b, Bitmap.Config.ARGB_8888);
                        }
                        t.h(aVarA, null);
                    } finally {
                    }
                }
                if (dVar.k.get()) {
                    dVar.f17002c.getLogger().q(w5.DEBUG, "Canvas Strategy already closed, skipping pixel copy request", new Object[0]);
                } else {
                    PixelCopy.request(dVar.f17011m, dVar.f17004e, new PixelCopy.OnPixelCopyFinishedListener() { // from class: io.sentry.android.replay.screenshot.c
                        @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                        public final void onPixelCopyFinished(int i10) {
                            d dVar2 = this.f16999a;
                            if (dVar2.k.get()) {
                                dVar2.f17002c.getLogger().q(w5.DEBUG, "CanvasStrategy is closed, ignoring capture result", new Object[0]);
                                return;
                            }
                            if (i10 != 0) {
                                dVar2.f17002c.getLogger().q(w5.ERROR, a0.c.i(i10, "Canvas Strategy: PixelCopy failed with code "), new Object[0]);
                                dVar2.f17008i.set(false);
                                return;
                            }
                            dVar2.f17008i.set(true);
                            Bitmap bitmap = dVar2.f17004e;
                            if (bitmap == null || bitmap.isRecycled()) {
                                return;
                            }
                            dVar2.f17001b.T(bitmap);
                        }
                    }, dVar.f17000a.k());
                }
            } catch (Throwable th) {
                dVar.f17011m.unlockCanvasAndPost(canvasLockHardwareCanvas);
                throw th;
            }
        } catch (Throwable th2) {
            dVar.f17002c.getLogger().l(w5.ERROR, "Canvas Strategy: picture render failed", th2);
            dVar.f17008i.set(false);
        }
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final boolean a() {
        return this.f17008i.get();
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void b() {
        Bitmap bitmap;
        if (!this.f17008i.get() || (bitmap = this.f17004e) == null || bitmap.isRecycled()) {
            return;
        }
        this.f17001b.T(bitmap);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, x6.s] */
    @Override // io.sentry.android.replay.screenshot.f
    public final void c(View view) {
        AtomicBoolean atomicBoolean = this.k;
        if (atomicBoolean.get()) {
            return;
        }
        Picture picture = new Picture();
        y yVar = this.f17003d;
        Canvas canvasBeginRecording = picture.beginRecording(yVar.f17089a, yVar.f17090b);
        g gVar = this.f17009j;
        gVar.f17023a = canvasBeginRecording;
        gVar.setMatrix((Matrix) this.f17007h.getValue());
        view.draw(gVar);
        picture.endRecording();
        if (atomicBoolean.get()) {
            return;
        }
        this.f17005f.set(picture);
        e(this.f17000a.k(), new io.sentry.android.replay.util.g("screenshot_recorder.canvas", this.f17012n));
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void close() {
        this.k.set(true);
        e(this.f17000a.k(), new io.sentry.android.replay.util.g("CanvasStrategy.close", new b(this, 1)));
        this.f17005f.getAndSet(null);
    }

    public final void e(Handler handler, io.sentry.android.replay.util.g gVar) {
        try {
            handler.post(gVar);
        } catch (Throwable th) {
            this.f17002c.getLogger().l(w5.ERROR, "Canvas Strategy: failed to post runnable " + gVar.f17052i, th);
        }
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void onContentChanged() {
    }
}
