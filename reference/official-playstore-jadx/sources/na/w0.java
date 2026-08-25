package na;

import ka.v1;

/* JADX INFO: loaded from: classes5.dex */
public final class w0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x0 f20666i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public k f20667l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public z0 f20668m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public v1 f20669n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public /* synthetic */ Object f20670o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ x0 f20671p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20672q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(x0 x0Var, d7.d dVar) {
        super(dVar);
        this.f20671p = x0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.f20670o = obj;
        this.f20672q |= Integer.MIN_VALUE;
        x0.j(this.f20671p, null, this);
        return e7.a.f15033i;
    }
}
