package e9;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f15070c;

    public d(String str, String str2) {
        super(9);
        this.f15069b = str;
        this.f15070c = str2;
    }

    @Override // a.a
    public final String F() {
        return this.f15069b + ':' + this.f15070c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return p.a(this.f15069b, dVar.f15069b) && p.a(this.f15070c, dVar.f15070c);
    }

    @Override // a.a
    public final int hashCode() {
        return this.f15070c.hashCode() + (this.f15069b.hashCode() * 31);
    }
}
