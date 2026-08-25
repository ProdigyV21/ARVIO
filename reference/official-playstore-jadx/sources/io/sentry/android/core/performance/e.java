package io.sentry.android.core.performance;

import android.os.MessageQueue;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements MessageQueue.IdleHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f16705a;

    public e(g gVar) {
        this.f16705a = gVar;
    }

    @Override // android.os.MessageQueue.IdleHandler
    public final boolean queueIdle() {
        this.f16705a.f16708m = SystemClock.uptimeMillis();
        g.a(this.f16705a);
        return false;
    }
}
