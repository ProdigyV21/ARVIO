package na;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20639i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20640l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s f20641m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public s f20642n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public k f20643o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(s sVar, d7.d dVar) {
        super(dVar);
        this.f20641m = sVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20639i = obj;
        this.f20640l |= Integer.MIN_VALUE;
        return this.f20641m.collect(null, this);
    }
}
