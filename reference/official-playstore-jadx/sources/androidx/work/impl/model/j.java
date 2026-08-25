package androidx.work.impl.model;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6914a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6915b;

    public j(String str, int i10) {
        this.f6914a = str;
        this.f6915b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return kotlin.jvm.internal.p.a(this.f6914a, jVar.f6914a) && this.f6915b == jVar.f6915b;
    }

    public final int hashCode() {
        return (this.f6914a.hashCode() * 31) + this.f6915b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("WorkGenerationalId(workSpecId=");
        sb2.append(this.f6914a);
        sb2.append(", generation=");
        return a0.c.o(sb2, this.f6915b, ')');
    }
}
