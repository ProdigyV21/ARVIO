package retrofit2;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f21467l = new a(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f21468m = new a(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f21469n = new a(2);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f21470o = new a(3);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f21471p = new a(4);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final a f21472q = new a(5);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21473i;

    public /* synthetic */ a(int i10) {
        this.f21473i = i10;
    }

    /* JADX WARN: Finally extract failed */
    @Override // retrofit2.l
    public final Object w(Object obj) {
        switch (this.f21473i) {
            case 0:
                return obj.toString();
            case 1:
                gb.s0 s0Var = (gb.s0) obj;
                try {
                    xb.j jVar = new xb.j();
                    s0Var.o().D(jVar);
                    gb.r0 r0Var = new gb.r0(s0Var.l(), s0Var.k(), jVar, 0);
                    s0Var.close();
                    return r0Var;
                } catch (Throwable th) {
                    s0Var.close();
                    throw th;
                }
            case 2:
                return (gb.n0) obj;
            case 3:
                return (gb.s0) obj;
            case 4:
                ((gb.s0) obj).close();
                return x6.t0.f22605a;
            default:
                ((gb.s0) obj).close();
                return null;
        }
    }
}
