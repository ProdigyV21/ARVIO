package db;

import cb.b1;
import cb.r1;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final cb.h0 f14910a = b1.a("kotlinx.serialization.json.JsonUnquotedLiteral", r1.f7553a);

    public static final g0 a(Boolean bool) {
        return bool == null ? y.INSTANCE : new u(bool, false, null);
    }

    public static final g0 b(Number number) {
        return number == null ? y.INSTANCE : new u(number, false, null);
    }

    public static final g0 c(String str) {
        return str == null ? y.INSTANCE : new u(str, true, null);
    }

    public static final void d(m mVar, String str) {
        throw new IllegalArgumentException("Element " + l0.f19747a.b(mVar.getClass()) + " is not a " + str);
    }

    public static final d e(m mVar) {
        d dVar = mVar instanceof d ? (d) mVar : null;
        if (dVar != null) {
            return dVar;
        }
        d(mVar, "JsonArray");
        throw null;
    }

    public static final b0 f(m mVar) {
        b0 b0Var = mVar instanceof b0 ? (b0) mVar : null;
        if (b0Var != null) {
            return b0Var;
        }
        d(mVar, "JsonObject");
        throw null;
    }

    public static final g0 g(m mVar) {
        g0 g0Var = mVar instanceof g0 ? (g0) mVar : null;
        if (g0Var != null) {
            return g0Var;
        }
        d(mVar, "JsonPrimitive");
        throw null;
    }
}
