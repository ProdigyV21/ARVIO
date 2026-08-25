package z0;

/* JADX INFO: loaded from: classes3.dex */
public final class y extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f23145i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f23146l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23147m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23148n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ a0 f23149o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23150p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23149o = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23148n = obj;
        this.f23150p |= Integer.MIN_VALUE;
        return this.f23149o.i(null, null, this);
    }
}
