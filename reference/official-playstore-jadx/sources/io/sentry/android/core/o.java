package io.sentry.android.core;

import android.os.Debug;
import io.sentry.n3;

/* JADX INFO: loaded from: classes5.dex */
public final class o implements io.sentry.x0 {
    @Override // io.sentry.x0
    public final void c() {
    }

    @Override // io.sentry.x0
    public final void d(n3 n3Var) {
        long jFreeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long nativeHeapSize = Debug.getNativeHeapSize() - Debug.getNativeHeapFreeSize();
        n3Var.f17485b = Long.valueOf(jFreeMemory);
        n3Var.f17486c = Long.valueOf(nativeHeapSize);
    }
}
