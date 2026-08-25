package io.sentry.android.core.internal.util;

import android.os.Handler;
import android.view.Window;
import io.sentry.w5;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class l implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16625i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ q f16626l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Window f16627m;

    public /* synthetic */ l(q qVar, Window window, int i10) {
        this.f16625i = i10;
        this.f16626l = qVar;
        this.f16627m = window;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16625i) {
            case 0:
                q qVar = this.f16626l;
                CopyOnWriteArraySet copyOnWriteArraySet = qVar.f16633l;
                Window window = this.f16627m;
                if (copyOnWriteArraySet.add(window)) {
                    try {
                        d dVar = qVar.f16639r;
                        n nVar = qVar.f16640s;
                        Handler handler = qVar.f16635n;
                        dVar.getClass();
                        androidx.compose.ui.text.platform.extensions.a.a(window, nVar, handler);
                    } catch (Throwable th) {
                        qVar.f16634m.l(w5.ERROR, "Failed to add frameMetricsAvailableListener", th);
                        return;
                    }
                }
                break;
            default:
                q qVar2 = this.f16626l;
                Window window2 = this.f16627m;
                try {
                    if (qVar2.f16633l.remove(window2)) {
                        d dVar2 = qVar2.f16639r;
                        n nVar2 = qVar2.f16640s;
                        dVar2.getClass();
                        androidx.compose.ui.text.platform.extensions.a.b(window2, nVar2);
                    }
                } catch (Throwable th2) {
                    qVar2.f16634m.l(w5.ERROR, "Failed to remove frameMetricsAvailableListener", th2);
                }
                break;
        }
    }
}
