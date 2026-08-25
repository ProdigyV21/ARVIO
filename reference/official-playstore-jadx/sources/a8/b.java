package a8;

import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f149l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f150m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f151n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f152o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f153p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final b f154q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final b f155r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final b f156s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final b f157t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final b f158u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final b f159v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final b f160w;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f161i;

    static {
        int i10 = 1;
        f149l = new b(i10, 0);
        f150m = new b(i10, 1);
        f151n = new b(i10, 2);
        f152o = new b(i10, 3);
        f153p = new b(i10, 4);
        f154q = new b(i10, 5);
        f155r = new b(i10, 6);
        f156s = new b(i10, 7);
        f157t = new b(i10, 8);
        f158u = new b(i10, 9);
        f159v = new b(i10, 10);
        f160w = new b(i10, 11);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i10, int i11) {
        super(i10);
        this.f161i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f161i;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        switch (i10) {
            case 0:
                return xc.d.Y((e0) c.f170a.c((Class) obj), zVar, false, zVar);
            case 1:
                return new ConcurrentHashMap();
            case 2:
                return xc.d.Y((e0) c.f170a.c((Class) obj), zVar, true, zVar);
            case 3:
                return new e0((Class) obj);
            case 4:
                return new y0((Class) obj);
            case 5:
                return m8.c.b(((Method) obj).getReturnType());
            case 6:
                return m8.c.b((Class) obj);
            case 7:
                g8.w wVar = (g8.w) obj;
                return h9.h.f15909e.v(wVar) + " | " + c2.c(wVar).c();
            case 8:
                g8.p0 p0Var = (g8.p0) obj;
                return h9.h.f15909e.v(p0Var) + " | " + c2.b(p0Var).c();
            case 9:
                h9.h hVar = b2.f169a;
                return b2.f169a.V(((g8.e1) obj).getType());
            case 10:
                h9.h hVar2 = b2.f169a;
                return b2.f169a.V(((g8.e1) obj).getType());
            default:
                return m8.c.b((Class) obj);
        }
    }
}
