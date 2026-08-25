package na;

import ka.v1;

/* JADX INFO: loaded from: classes5.dex */
public final class i1 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j1 f20587i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public k f20588l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public k1 f20589m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public v1 f20590n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f20591o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f20592p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ j1 f20593q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f20594r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(j1 j1Var, d7.d dVar) {
        super(dVar);
        this.f20593q = j1Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20592p = obj;
        this.f20594r |= Integer.MIN_VALUE;
        this.f20593q.collect(null, this);
        return e7.a.f15033i;
    }
}
