package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4879a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4880b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f4881c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4882d;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            int i10 = this.f4879a;
            if (i10 != bVar.f4879a) {
                return false;
            }
            if (i10 != 8 || Math.abs(this.f4882d - this.f4880b) != 1 || this.f4882d != bVar.f4880b || this.f4880b != bVar.f4882d) {
                if (this.f4882d != bVar.f4882d || this.f4880b != bVar.f4880b) {
                    return false;
                }
                Object obj2 = this.f4881c;
                if (obj2 != null) {
                    if (!obj2.equals(bVar.f4881c)) {
                        return false;
                    }
                } else if (bVar.f4881c != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public final int hashCode() {
        return (((this.f4879a * 31) + this.f4880b) * 31) + this.f4882d;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("[");
        int i10 = this.f4879a;
        sb2.append(i10 != 1 ? i10 != 2 ? i10 != 4 ? i10 != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb2.append(",s:");
        sb2.append(this.f4880b);
        sb2.append("c:");
        sb2.append(this.f4882d);
        sb2.append(",p:");
        sb2.append(this.f4881c);
        sb2.append("]");
        return sb2.toString();
    }
}
