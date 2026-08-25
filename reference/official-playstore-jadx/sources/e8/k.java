package e8;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f15061a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15062b;

    public k(j jVar, int i10) {
        this.f15061a = jVar;
        this.f15062b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return p.a(this.f15061a, kVar.f15061a) && this.f15062b == kVar.f15062b;
    }

    public final int hashCode() {
        return (this.f15061a.hashCode() * 31) + this.f15062b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("KindWithArity(kind=");
        sb2.append(this.f15061a);
        sb2.append(", arity=");
        return a0.c.o(sb2, this.f15062b, ')');
    }
}
