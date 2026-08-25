package l5;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f20029b;

    public a(String str, ArrayList arrayList) {
        if (str == null) {
            throw new NullPointerException("Null userAgent");
        }
        this.f20028a = str;
        this.f20029b = arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f20028a.equals(aVar.f20028a) && this.f20029b.equals(aVar.f20029b);
    }

    public final int hashCode() {
        return ((this.f20028a.hashCode() ^ 1000003) * 1000003) ^ this.f20029b.hashCode();
    }

    public final String toString() {
        return "HeartBeatResult{userAgent=" + this.f20028a + ", usedDates=" + this.f20029b + "}";
    }
}
