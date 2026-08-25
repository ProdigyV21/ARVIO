package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class s1 extends w1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19630m = AtomicIntegerFieldUpdater.newUpdater(s1.class, "_invoked");

    @q7.w
    private volatile int _invoked;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r7.l f19631l;

    public s1(r7.l lVar) {
        this.f19631l = lVar;
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x6.t0.f22605a;
    }

    @Override // ka.y1
    public final void invoke(Throwable th) {
        if (f19630m.compareAndSet(this, 0, 1)) {
            this.f19631l.invoke(th);
        }
    }
}
