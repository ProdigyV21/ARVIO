package t8;

import g8.u0;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final j f21937l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j f21938m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final j f21939n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final j f21940o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final j f21941p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21942i;

    static {
        int i10 = 1;
        f21937l = new j(i10, 0);
        f21938m = new j(i10, 1);
        f21939n = new j(i10, 2);
        f21940o = new j(i10, 3);
        f21941p = new j(i10, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(int i10, int i11) {
        super(i10);
        this.f21942i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f21942i) {
            case 0:
                return Boolean.valueOf(!((w8.p) obj).G());
            case 1:
                return (u0) obj;
            case 2:
                return Boolean.valueOf(((w8.p) obj).G());
            case 3:
                return ((p9.n) obj).b();
            default:
                g8.h hVarE = ((v9.w) obj).I0().e();
                if (hVarE instanceof g8.f) {
                    return (g8.f) hVarE;
                }
                return null;
        }
    }
}
