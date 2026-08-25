package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import m2.d0;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f6788a;

    public c(int i10) {
        switch (i10) {
            case 1:
                this.f6788a = new Handler(Looper.getMainLooper());
                break;
            default:
                this.f6788a = a.a.I(Looper.getMainLooper());
                break;
        }
    }

    @Override // m2.d0
    public void a(Runnable runnable) {
        this.f6788a.removeCallbacks(runnable);
    }

    @Override // m2.d0
    public void b(long j10, Runnable runnable) {
        this.f6788a.postDelayed(runnable, j10);
    }
}
