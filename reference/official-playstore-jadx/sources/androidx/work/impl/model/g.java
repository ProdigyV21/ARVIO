package androidx.work.impl.model;

/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6912c;

    public g(String str, int i10, int i11) {
        this.f6910a = str;
        this.f6911b = i10;
        this.f6912c = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return kotlin.jvm.internal.p.a(this.f6910a, gVar.f6910a) && this.f6911b == gVar.f6911b && this.f6912c == gVar.f6912c;
    }

    public final int hashCode() {
        return (((this.f6910a.hashCode() * 31) + this.f6911b) * 31) + this.f6912c;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SystemIdInfo(workSpecId=");
        sb2.append(this.f6910a);
        sb2.append(", generation=");
        sb2.append(this.f6911b);
        sb2.append(", systemId=");
        return a0.c.o(sb2, this.f6912c, ')');
    }
}
