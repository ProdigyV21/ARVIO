package p8;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f9.f f21145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f21146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f21147d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f21148e;

    public g0(String str, f9.f fVar, String str2, String str3) {
        this.f21144a = str;
        this.f21145b = fVar;
        this.f21146c = str2;
        this.f21147d = str3;
        this.f21148e = androidx.compose.foundation.c.m('.', str, fVar + '(' + str2 + ')' + str3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g0)) {
            return false;
        }
        g0 g0Var = (g0) obj;
        return kotlin.jvm.internal.p.a(this.f21144a, g0Var.f21144a) && kotlin.jvm.internal.p.a(this.f21145b, g0Var.f21145b) && kotlin.jvm.internal.p.a(this.f21146c, g0Var.f21146c) && kotlin.jvm.internal.p.a(this.f21147d, g0Var.f21147d);
    }

    public final int hashCode() {
        return this.f21147d.hashCode() + androidx.compose.foundation.c.c((this.f21145b.hashCode() + (this.f21144a.hashCode() * 31)) * 31, 31, this.f21146c);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NameAndSignature(classInternalName=");
        sb2.append(this.f21144a);
        sb2.append(", name=");
        sb2.append(this.f21145b);
        sb2.append(", parameters=");
        sb2.append(this.f21146c);
        sb2.append(", returnType=");
        return androidx.compose.foundation.c.u(sb2, this.f21147d, ')');
    }
}
