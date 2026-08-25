package e9;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15071b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f15072c;

    public e(String str, String str2) {
        super(9);
        this.f15071b = str;
        this.f15072c = str2;
    }

    @Override // a.a
    public final String F() {
        return this.f15071b + this.f15072c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return p.a(this.f15071b, eVar.f15071b) && p.a(this.f15072c, eVar.f15072c);
    }

    @Override // a.a
    public final int hashCode() {
        return this.f15072c.hashCode() + (this.f15071b.hashCode() * 31);
    }
}
