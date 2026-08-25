package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h0 implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12754i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Handler f12755l;

    public /* synthetic */ h0(androidx.loader.content.j jVar) {
        this.f12754i = 0;
        this.f12755l = jVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f12754i) {
            case 0:
                this.f12755l.post(runnable);
                break;
            default:
                this.f12755l.post(runnable);
                break;
        }
    }

    public h0() {
        this.f12754i = 1;
        this.f12755l = new Handler(Looper.getMainLooper());
    }
}
