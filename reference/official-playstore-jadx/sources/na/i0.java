package na;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f0 f20582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20583l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20584m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f20585n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f20586o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i0(f0 f0Var, d7.d dVar) {
        super(dVar);
        this.f20585n = f0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20583l = obj;
        this.f20584m |= Integer.MIN_VALUE;
        return this.f20585n.emit(null, this);
    }
}
