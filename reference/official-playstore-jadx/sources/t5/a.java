package t5;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f21890b;

    public a(String str, String str2) {
        this.f21889a = str;
        if (str2 == null) {
            throw new NullPointerException("Null version");
        }
        this.f21890b = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f21889a.equals(aVar.f21889a) && this.f21890b.equals(aVar.f21890b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f21889a.hashCode() ^ 1000003) * 1000003) ^ this.f21890b.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("LibraryVersion{libraryName=");
        sb2.append(this.f21889a);
        sb2.append(", version=");
        return a0.c.p(sb2, this.f21890b, "}");
    }
}
