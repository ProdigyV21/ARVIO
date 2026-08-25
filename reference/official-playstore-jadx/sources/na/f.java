package na;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20558i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g f20559l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20560m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, d7.d dVar) {
        super(dVar);
        this.f20559l = gVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20558i = obj;
        this.f20560m |= Integer.MIN_VALUE;
        return this.f20559l.emit(null, this);
    }
}
