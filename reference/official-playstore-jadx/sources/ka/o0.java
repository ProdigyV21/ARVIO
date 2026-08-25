package ka;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r0 f19625a;

    static {
        String property;
        r0 r0Var;
        int i10 = pa.y.f21300a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null ? Boolean.parseBoolean(property) : false) {
            x0 x0Var = x0.f19652a;
            d7.j jVar = pa.q.f21294a;
            la.d dVar = ((la.d) jVar).f20077n;
            r0Var = !(jVar instanceof r0) ? n0.f19622r : (r0) jVar;
        } else {
            r0Var = n0.f19622r;
        }
        f19625a = r0Var;
    }
}
