package z0;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f23140i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f23141l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23142m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ a0 f23143n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f23144o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23143n = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23142m = obj;
        this.f23144o |= Integer.MIN_VALUE;
        return this.f23143n.h(this);
    }
}
