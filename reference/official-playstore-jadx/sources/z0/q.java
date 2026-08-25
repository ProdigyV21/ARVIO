package z0;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f23100i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a0 f23101l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ka.s f23102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23103n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ a0 f23104o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23105p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23104o = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23103n = obj;
        this.f23105p |= Integer.MIN_VALUE;
        return a0.b(this.f23104o, null, this);
    }
}
