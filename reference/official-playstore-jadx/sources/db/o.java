package db;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o f14911l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o f14912m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final o f14913n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final o f14914o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final o f14915p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final o f14916q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14917i;

    static {
        int i10 = 0;
        f14911l = new o(i10, 0);
        f14912m = new o(i10, 1);
        f14913n = new o(i10, 2);
        f14914o = new o(i10, 3);
        f14915p = new o(i10, 4);
        f14916q = new o(i10, 5);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(int i10, int i11) {
        super(i10);
        this.f14917i = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14917i) {
            case 0:
                return h0.f14900b;
            case 1:
                return z.f14931b;
            case 2:
                return v.f14928b;
            case 3:
                return e0.f14891b;
            case 4:
                return f.f14893b;
            default:
                return z.f14930a;
        }
    }
}
