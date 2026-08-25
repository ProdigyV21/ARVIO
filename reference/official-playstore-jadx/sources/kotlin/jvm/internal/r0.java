package kotlin.jvm.internal;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 implements kotlin.reflect.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.e f19751i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f19752l;

    public r0(kotlin.reflect.e eVar, List list) {
        this.f19751i = eVar;
        this.f19752l = list;
    }

    @Override // kotlin.reflect.q
    public final kotlin.reflect.e b() {
        return this.f19751i;
    }

    public final String c(boolean z) {
        kotlin.reflect.e eVar = this.f19751i;
        kotlin.reflect.d dVar = eVar instanceof kotlin.reflect.d ? (kotlin.reflect.d) eVar : null;
        Class clsC = dVar != null ? ((f) dVar).c() : null;
        return androidx.compose.foundation.c.t(clsC == null ? eVar.toString() : clsC.isArray() ? clsC.equals(boolean[].class) ? "kotlin.BooleanArray" : clsC.equals(char[].class) ? "kotlin.CharArray" : clsC.equals(byte[].class) ? "kotlin.ByteArray" : clsC.equals(short[].class) ? "kotlin.ShortArray" : clsC.equals(int[].class) ? "kotlin.IntArray" : clsC.equals(float[].class) ? "kotlin.FloatArray" : clsC.equals(long[].class) ? "kotlin.LongArray" : clsC.equals(double[].class) ? "kotlin.DoubleArray" : "kotlin.Array" : (z && clsC.isPrimitive()) ? com.google.common.util.concurrent.r0.n((kotlin.reflect.d) eVar).getName() : clsC.getName(), this.f19752l.isEmpty() ? "" : kotlin.collections.x.u0(this.f19752l, ", ", "<", ">", new com.arflix.tv.util.a(this), 24), "");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r0)) {
            return false;
        }
        r0 r0Var = (r0) obj;
        return p.a(this.f19751i, r0Var.f19751i) && p.a(this.f19752l, r0Var.f19752l);
    }

    @Override // kotlin.reflect.q
    public final List g() {
        return this.f19752l;
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    public final int hashCode() {
        return androidx.compose.material3.d.e(this.f19752l, this.f19751i.hashCode() * 31, 31);
    }

    @Override // kotlin.reflect.q
    public final boolean j() {
        return false;
    }

    public final String toString() {
        return c(false) + " (Kotlin reflection is not available)";
    }
}
