package na;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ j f20657i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f20658l;

    public v(j jVar, int i10) {
        this.f20657i = jVar;
        this.f20658l = i10;
    }

    @Override // na.j
    public final Object collect(k kVar, d7.d dVar) {
        Object objCollect = this.f20657i.collect(new x(new kotlin.jvm.internal.i0(), this.f20658l, kVar), dVar);
        return objCollect == e7.a.f15033i ? objCollect : x6.t0.f22605a;
    }
}
