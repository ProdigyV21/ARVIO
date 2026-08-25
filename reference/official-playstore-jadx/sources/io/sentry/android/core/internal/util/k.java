package io.sentry.android.core.internal.util;

import android.view.PixelCopy;
import android.view.View;
import androidx.work.impl.t;
import io.sentry.w5;
import io.sentry.y6;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class k implements PixelCopy.OnPixelCopyFinishedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f16623b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f16624c;

    public /* synthetic */ k(Object obj, Object obj2, int i10) {
        this.f16622a = i10;
        this.f16623b = obj;
        this.f16624c = obj2;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int i10) {
        switch (this.f16622a) {
            case 0:
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.f16623b;
                CountDownLatch countDownLatch = (CountDownLatch) this.f16624c;
                atomicBoolean.set(i10 == 0);
                countDownLatch.countDown();
                break;
            default:
                io.sentry.android.replay.screenshot.e eVar = (io.sentry.android.replay.screenshot.e) this.f16623b;
                View view = (View) this.f16624c;
                AtomicBoolean atomicBoolean2 = eVar.k;
                AtomicBoolean atomicBoolean3 = eVar.f17020h;
                y6 y6Var = eVar.f17014b;
                if (atomicBoolean2.get()) {
                    y6Var.getLogger().q(w5.DEBUG, "PixelCopyStrategy is closed, ignoring capture result", new Object[0]);
                } else if (i10 != 0) {
                    y6Var.getLogger().q(w5.INFO, "Failed to capture replay recording: %d", Integer.valueOf(i10));
                    atomicBoolean3.set(false);
                } else if (!eVar.f17022j.get()) {
                    io.sentry.android.replay.viewhierarchy.g gVarQ = t.q(view, null, y6Var.getSessionReplay());
                    io.sentry.android.replay.util.l.b(view, gVarQ, y6Var.getSessionReplay(), y6Var.getLogger());
                    eVar.f17016d.submit(new io.sentry.android.replay.util.g("screenshot_recorder.mask", new androidx.emoji2.text.q(eVar, gVarQ, view, 12)));
                } else {
                    y6Var.getLogger().q(w5.INFO, "Failed to determine view hierarchy, not capturing", new Object[0]);
                    atomicBoolean3.set(false);
                }
                break;
        }
    }
}
