package na;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class k1 extends oa.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20604a = AtomicReferenceFieldUpdater.newUpdater(k1.class, Object.class, "_state");

    @q7.w
    private volatile Object _state;

    @Override // oa.c
    public final boolean a(oa.a aVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20604a;
        if (atomicReferenceFieldUpdater.get(this) != null) {
            return false;
        }
        atomicReferenceFieldUpdater.set(this, y0.f20690b);
        return true;
    }

    @Override // oa.c
    public final d7.d[] b(oa.a aVar) {
        f20604a.set(this, null);
        return oa.b.f20806a;
    }
}
