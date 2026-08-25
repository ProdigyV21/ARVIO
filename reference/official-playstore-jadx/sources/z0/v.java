package z0;

/* JADX INFO: loaded from: classes3.dex */
public final class v extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f23131i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f23132l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ a0 f23133m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23134n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23133m = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23132l = obj;
        this.f23134n |= Integer.MIN_VALUE;
        return this.f23133m.f(this);
    }
}
