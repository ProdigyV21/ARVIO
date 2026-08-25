package g8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.b f15500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f15501b;

    public e0(f9.b bVar, List list) {
        this.f15500a = bVar;
        this.f15501b = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return kotlin.jvm.internal.p.a(this.f15500a, e0Var.f15500a) && kotlin.jvm.internal.p.a(this.f15501b, e0Var.f15501b);
    }

    public final int hashCode() {
        return this.f15501b.hashCode() + (this.f15500a.hashCode() * 31);
    }

    public final String toString() {
        return "ClassRequest(classId=" + this.f15500a + ", typeParametersCount=" + this.f15501b + ')';
    }
}
