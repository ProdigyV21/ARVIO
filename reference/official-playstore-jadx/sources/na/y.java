package na;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g f20684i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f20685l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f20686m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f20687n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20688o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(g gVar, d7.d dVar) {
        super(dVar);
        this.f20687n = gVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20686m = obj;
        this.f20688o |= Integer.MIN_VALUE;
        return this.f20687n.emit(null, this);
    }
}
