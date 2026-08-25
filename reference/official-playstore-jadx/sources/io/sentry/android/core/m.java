package io.sentry.android.core;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16665i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ e0 f16666l;

    public /* synthetic */ m(e0 e0Var, int i10) {
        this.f16665i = i10;
        this.f16666l = e0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16665i) {
            case 0:
                ((n) this.f16666l).b(5000L);
                break;
            default:
                ((p) this.f16666l).b(5000L);
                break;
        }
    }
}
