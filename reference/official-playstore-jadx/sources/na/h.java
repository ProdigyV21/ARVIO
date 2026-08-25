package na;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j f20574i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r7.p f20575l;

    public h(j jVar, r7.p pVar) {
        this.f20574i = jVar;
        this.f20575l = pVar;
    }

    @Override // na.j
    public final Object collect(k kVar, d7.d dVar) {
        kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
        k0Var.f19746i = oa.b.f20807b;
        Object objCollect = this.f20574i.collect(new g(this, k0Var, kVar), dVar);
        return objCollect == e7.a.f15033i ? objCollect : x6.t0.f22605a;
    }
}
