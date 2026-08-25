package androidx.datastore.preferences.protobuf;

import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class j2 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j2 f2452c = new j2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f2454b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f2453a = new v();

    public final o2 a(Class cls) {
        o2 o2VarW;
        Class cls2;
        f1.a(cls, "messageType");
        ConcurrentHashMap concurrentHashMap = this.f2454b;
        o2 o2Var = (o2) concurrentHashMap.get(cls);
        if (o2Var != null) {
            return o2Var;
        }
        v vVar = this.f2453a;
        vVar.getClass();
        Class cls3 = q2.f2507a;
        if (!r0.class.isAssignableFrom(cls) && (cls2 = q2.f2507a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
        t1 t1VarA = ((n1) vVar.f2540a).a(cls);
        if (t1VarA.a()) {
            if (r0.class.isAssignableFrom(cls)) {
                o2VarW = new z1(q2.f2510d, g0.f2425a, t1VarA.b());
            } else {
                f3 f3Var = q2.f2508b;
                e0 e0Var = g0.f2426b;
                if (e0Var == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                o2VarW = new z1(f3Var, e0Var, t1VarA.b());
            }
        } else if (r0.class.isAssignableFrom(cls)) {
            o2VarW = t1VarA.c() == 1 ? y1.w(t1VarA, f2.f2424b, l1.f2469b, q2.f2510d, g0.f2425a, s1.f2527b) : y1.w(t1VarA, f2.f2424b, l1.f2469b, q2.f2510d, null, s1.f2527b);
        } else if (t1VarA.c() == 1) {
            d2 d2Var = f2.f2423a;
            j1 j1Var = l1.f2468a;
            f3 f3Var2 = q2.f2508b;
            e0 e0Var2 = g0.f2426b;
            if (e0Var2 == null) {
                throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
            }
            o2VarW = y1.w(t1VarA, d2Var, j1Var, f3Var2, e0Var2, s1.f2526a);
        } else {
            o2VarW = y1.w(t1VarA, f2.f2423a, l1.f2468a, q2.f2509c, null, s1.f2526a);
        }
        o2 o2Var2 = (o2) concurrentHashMap.putIfAbsent(cls, o2VarW);
        return o2Var2 != null ? o2Var2 : o2VarW;
    }
}
