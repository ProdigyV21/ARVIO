package t;

import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends f7.h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21817i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f21818l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long[] f21819m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21820n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f21821o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21822p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21823q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f21824r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f21825s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public /* synthetic */ Object f21826t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f21827u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g0(Object obj, d7.d dVar, int i10) {
        super(2, dVar);
        this.f21817i = i10;
        this.f21827u = obj;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f21817i) {
            case 0:
                g0 g0Var = new g0((z) this.f21827u, dVar, 0);
                g0Var.f21826t = obj;
                return g0Var;
            default:
                g0 g0Var2 = new g0((j0) this.f21827u, dVar, 1);
                g0Var2.f21826t = obj;
                return g0Var2;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ga.o oVar = (ga.o) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f21817i) {
        }
        return ((g0) create(oVar, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0052 -> B:23:0x0096). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0054 -> B:16:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x006e -> B:20:0x008d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00e9 -> B:46:0x012d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00eb -> B:39:0x00fc). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0105 -> B:43:0x0124). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.g0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
