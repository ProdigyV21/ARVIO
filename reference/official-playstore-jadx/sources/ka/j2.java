package ka;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes5.dex */
public final class j2 extends d7.a implements v1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final j2 f19602i = new j2(u1.f19642i);

    @Override // ka.v1
    public final o attachChild(q qVar) {
        return k2.f19604i;
    }

    @Override // ka.v1
    public final /* synthetic */ void cancel() {
    }

    @Override // ka.v1
    public final CancellationException getCancellationException() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // ka.v1
    public final ga.m getChildren() {
        return ga.g.f15574a;
    }

    @Override // ka.v1
    public final sa.b getOnJoin() {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // ka.v1
    public final v1 getParent() {
        return null;
    }

    @Override // ka.v1
    public final z0 invokeOnCompletion(r7.l lVar) {
        return k2.f19604i;
    }

    @Override // ka.v1
    public final boolean isActive() {
        return true;
    }

    @Override // ka.v1
    public final boolean isCancelled() {
        return false;
    }

    @Override // ka.v1
    public final boolean isCompleted() {
        return false;
    }

    @Override // ka.v1
    public final Object join(d7.d dVar) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // ka.v1
    public final boolean start() {
        return false;
    }

    public final String toString() {
        return "NonCancellable";
    }

    @Override // ka.v1
    public final void cancel(CancellationException cancellationException) {
    }

    @Override // ka.v1
    public final z0 invokeOnCompletion(boolean z, boolean z5, r7.l lVar) {
        return k2.f19604i;
    }

    @Override // ka.v1
    public final /* synthetic */ boolean cancel(Throwable th) {
        return false;
    }

    @Override // ka.v1
    public final v1 plus(v1 v1Var) {
        return v1Var;
    }
}
