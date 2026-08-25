package io.sentry.android.replay.screenshot;

import a8.g0;
import android.graphics.Bitmap;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;
import androidx.activity.n;
import io.sentry.android.core.internal.util.k;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.f0;
import io.sentry.android.replay.y;
import io.sentry.w5;
import io.sentry.y6;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReplayIntegration f17013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y6 f17014b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f17015c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ScheduledExecutorService f17016d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final androidx.work.impl.c f17017e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bitmap f17018f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f17019g = f4.f.o(3, new g0(this, 26));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f17020h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.android.replay.util.e f17021i = new io.sentry.android.replay.util.e();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AtomicBoolean f17022j = new AtomicBoolean(false);
    public final AtomicBoolean k = new AtomicBoolean(false);

    public e(f0 f0Var, ReplayIntegration replayIntegration, y6 y6Var, y yVar, io.sentry.android.replay.util.b bVar) {
        this.f17013a = replayIntegration;
        this.f17014b = y6Var;
        this.f17015c = yVar;
        this.f17016d = f0Var.f16945o;
        this.f17017e = f0Var.f16944n;
        this.f17018f = Bitmap.createBitmap(yVar.f17089a, yVar.f17090b, Bitmap.Config.ARGB_8888);
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final boolean a() {
        return this.f17020h.get();
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void b() {
        if (this.f17020h.get()) {
            Bitmap bitmap = this.f17018f;
            if (bitmap.isRecycled()) {
                return;
            }
            this.f17013a.T(bitmap);
        }
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void c(View view) {
        Window windowR = a.a.R(view);
        y6 y6Var = this.f17014b;
        if (windowR == null) {
            y6Var.getLogger().q(w5.DEBUG, "Window is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        if (this.k.get()) {
            y6Var.getLogger().q(w5.DEBUG, "PixelCopyStrategy is closed, not capturing screenshot", new Object[0]);
            return;
        }
        try {
            this.f17022j.set(false);
            PixelCopy.request(windowR, this.f17018f, new k(this, view, 1), this.f17017e.f6788a);
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.WARNING, "Failed to capture replay recording", th);
            this.f17020h.set(false);
        }
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void close() {
        this.k.set(true);
        this.f17016d.submit(new io.sentry.android.replay.util.g("PixelCopyStrategy.close", new n(this, 25)));
    }

    @Override // io.sentry.android.replay.screenshot.f
    public final void onContentChanged() {
        this.f17022j.set(true);
    }
}
