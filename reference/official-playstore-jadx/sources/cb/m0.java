package cb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 implements kotlin.reflect.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.q f7532i;

    public m0(kotlin.reflect.q qVar) {
        this.f7532i = qVar;
    }

    @Override // kotlin.reflect.q
    public final kotlin.reflect.e b() {
        return this.f7532i.b();
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        m0 m0Var = obj instanceof m0 ? (m0) obj : null;
        kotlin.reflect.q qVar = m0Var != null ? m0Var.f7532i : null;
        kotlin.reflect.q qVar2 = this.f7532i;
        if (!kotlin.jvm.internal.p.a(qVar2, qVar)) {
            return false;
        }
        kotlin.reflect.e eVarB = qVar2.b();
        if (eVarB instanceof kotlin.reflect.d) {
            kotlin.reflect.q qVar3 = obj instanceof kotlin.reflect.q ? (kotlin.reflect.q) obj : null;
            kotlin.reflect.e eVarB2 = qVar3 != null ? qVar3.b() : null;
            if (eVarB2 != null && (eVarB2 instanceof kotlin.reflect.d)) {
                return ((kotlin.jvm.internal.f) ((kotlin.reflect.d) eVarB)).c().equals(((kotlin.jvm.internal.f) ((kotlin.reflect.d) eVarB2)).c());
            }
        }
        return false;
    }

    @Override // kotlin.reflect.q
    public final List g() {
        return this.f7532i.g();
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        return this.f7532i.getAnnotations();
    }

    public final int hashCode() {
        return this.f7532i.hashCode();
    }

    @Override // kotlin.reflect.q
    public final boolean j() {
        return this.f7532i.j();
    }

    public final String toString() {
        return "KTypeWrapper: " + this.f7532i;
    }
}
