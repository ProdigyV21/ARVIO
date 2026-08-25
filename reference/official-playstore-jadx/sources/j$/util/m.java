package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f18572c = new m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18574b;

    public m() {
        this.f18573a = false;
        this.f18574b = 0;
    }

    public m(int i10) {
        this.f18573a = true;
        this.f18574b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        boolean z = mVar.f18573a;
        boolean z5 = this.f18573a;
        return (z5 && z) ? this.f18574b == mVar.f18574b : z5 == z;
    }

    public final int hashCode() {
        if (this.f18573a) {
            return this.f18574b;
        }
        return 0;
    }

    public final String toString() {
        if (this.f18573a) {
            return "OptionalInt[" + this.f18574b + "]";
        }
        return "OptionalInt.empty";
    }
}
