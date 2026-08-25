package na;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public oa.t f20516i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20517l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t0 f20518m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20519n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(t0 t0Var, d7.d dVar) {
        super(dVar);
        this.f20518m = t0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20517l = obj;
        this.f20519n |= Integer.MIN_VALUE;
        return this.f20518m.collect(null, this);
    }
}
