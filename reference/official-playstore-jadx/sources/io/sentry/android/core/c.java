package io.sentry.android.core;

import android.app.Activity;
import androidx.appcompat.widget.u4;
import androidx.core.app.FrameMetricsAggregator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16451i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ u4 f16452l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Activity f16453m;

    public /* synthetic */ c(u4 u4Var, Activity activity, int i10) {
        this.f16451i = i10;
        this.f16452l = u4Var;
        this.f16453m = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16451i) {
            case 0:
                ((FrameMetricsAggregator) ((io.sentry.util.k) this.f16452l.f1773a).a()).f1949a.i(this.f16453m);
                break;
            default:
                ((FrameMetricsAggregator) ((io.sentry.util.k) this.f16452l.f1773a).a()).f1949a.p(this.f16453m);
                break;
        }
    }
}
