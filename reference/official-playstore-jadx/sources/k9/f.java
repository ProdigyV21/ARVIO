package k9;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.b f19528a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f19529b;

    public f(f9.b bVar, int i10) {
        this.f19528a = bVar;
        this.f19529b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return kotlin.jvm.internal.p.a(this.f19528a, fVar.f19528a) && this.f19529b == fVar.f19529b;
    }

    public final int hashCode() {
        return (this.f19528a.hashCode() * 31) + this.f19529b;
    }

    public final String toString() {
        int i10;
        StringBuilder sb2 = new StringBuilder();
        int i11 = 0;
        while (true) {
            i10 = this.f19529b;
            if (i11 >= i10) {
                break;
            }
            sb2.append("kotlin/Array<");
            i11++;
        }
        sb2.append(this.f19528a);
        for (int i12 = 0; i12 < i10; i12++) {
            sb2.append(">");
        }
        return sb2.toString();
    }
}
