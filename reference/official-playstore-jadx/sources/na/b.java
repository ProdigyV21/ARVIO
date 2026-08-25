package na;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ma.u f20529i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20530l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ c f20531m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20532n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, f7.c cVar2) {
        super(cVar2);
        this.f20531m = cVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20530l = obj;
        this.f20532n |= Integer.MIN_VALUE;
        return this.f20531m.c(null, this);
    }
}
