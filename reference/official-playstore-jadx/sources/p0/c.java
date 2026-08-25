package p0;

import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f21036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f21037b;

    public c(Object obj, Object obj2) {
        this.f21036a = obj;
        this.f21037b = obj2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Objects.equals(cVar.f21036a, this.f21036a) && Objects.equals(cVar.f21037b, this.f21037b);
    }

    public final int hashCode() {
        Object obj = this.f21036a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f21037b;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "Pair{" + this.f21036a + " " + this.f21037b + "}";
    }
}
