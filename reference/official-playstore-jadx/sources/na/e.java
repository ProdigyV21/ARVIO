package na;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends oa.d {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f20546p = AtomicIntegerFieldUpdater.newUpdater(e.class, "consumed");

    @q7.w
    private volatile int consumed;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ma.w f20547n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f20548o;

    public /* synthetic */ e(ma.w wVar, boolean z) {
        this(wVar, z, d7.k.f14688i, -3, 1);
    }

    @Override // oa.d
    public final String a() {
        return "channel=" + this.f20547n;
    }

    @Override // oa.d
    public final Object c(ma.u uVar, d7.d dVar) throws Throwable {
        Object objK = y0.k(new oa.x(uVar), this.f20547n, this.f20548o, dVar);
        return objK == e7.a.f15033i ? objK : x6.t0.f22605a;
    }

    @Override // oa.d, na.j
    public final Object collect(k kVar, d7.d dVar) throws Throwable {
        int i10 = this.f20810l;
        e7.a aVar = e7.a.f15033i;
        if (i10 == -3) {
            boolean z = this.f20548o;
            if (z && f20546p.getAndSet(this, 1) != 0) {
                throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once");
            }
            Object objK = y0.k(kVar, this.f20547n, z, dVar);
            if (objK == aVar) {
                return objK;
            }
        } else {
            Object objCollect = super.collect(kVar, dVar);
            if (objCollect == aVar) {
                return objCollect;
            }
        }
        return x6.t0.f22605a;
    }

    @Override // oa.d
    public final oa.d d(d7.j jVar, int i10, int i11) {
        return new e(this.f20547n, this.f20548o, jVar, i10, i11);
    }

    @Override // oa.d
    public final j f() {
        return new e(this.f20547n, this.f20548o);
    }

    @Override // oa.d
    public final ma.w g(ka.k0 k0Var) {
        if (!this.f20548o || f20546p.getAndSet(this, 1) == 0) {
            return this.f20810l == -3 ? this.f20547n : super.g(k0Var);
        }
        throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once");
    }

    public e(ma.w wVar, boolean z, d7.j jVar, int i10, int i11) {
        super(jVar, i10, i11);
        this.f20547n = wVar;
        this.f20548o = z;
        this.consumed = 0;
    }
}
