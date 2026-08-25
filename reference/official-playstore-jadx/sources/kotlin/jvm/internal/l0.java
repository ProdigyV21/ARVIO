package kotlin.jvm.internal;

import a8.a2;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m0 f19747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.reflect.d[] f19748b;

    static {
        m0 m0Var = null;
        try {
            m0Var = (m0) a2.class.newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (m0Var == null) {
            m0Var = new m0();
        }
        f19747a = m0Var;
        f19748b = new kotlin.reflect.d[0];
    }

    public static kotlin.reflect.q a(Class cls) {
        m0 m0Var = f19747a;
        return m0Var.k(m0Var.b(cls), Collections.EMPTY_LIST);
    }

    public static kotlin.reflect.q b(kotlin.reflect.t tVar) {
        m0 m0Var = f19747a;
        return m0Var.k(m0Var.b(List.class), Collections.singletonList(tVar));
    }
}
