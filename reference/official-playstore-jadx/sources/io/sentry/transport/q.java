package io.sentry.transport;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends AbstractQueuedSynchronizer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f17937i = 0;

    public q() {
        setState(0);
    }

    public static int a(q qVar) {
        return qVar.getState();
    }

    public static void b(q qVar) {
        int state;
        do {
            state = qVar.getState();
        } while (!qVar.compareAndSetState(state, state + 1));
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    public final int tryAcquireShared(int i10) {
        return getState() == 0 ? 1 : -1;
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    public final boolean tryReleaseShared(int i10) {
        int state;
        int i11;
        do {
            state = getState();
            if (state == 0) {
                return false;
            }
            i11 = state - 1;
        } while (!compareAndSetState(state, i11));
        return i11 == 0;
    }
}
