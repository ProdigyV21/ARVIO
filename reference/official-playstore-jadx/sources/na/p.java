package na;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20632i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20633l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q f20634m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f20635n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public k f20636o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(q qVar, d7.d dVar) {
        super(dVar);
        this.f20634m = qVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20632i = obj;
        this.f20633l |= Integer.MIN_VALUE;
        return this.f20634m.collect(null, this);
    }
}
