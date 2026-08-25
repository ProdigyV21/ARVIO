package androidx.mediarouter.media;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f4454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z f4455b;

    public n(z zVar, boolean z) {
        if (zVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        Bundle bundle = new Bundle();
        this.f4454a = bundle;
        this.f4455b = zVar;
        bundle.putBundle("selector", zVar.f4517a);
        bundle.putBoolean("activeScan", z);
    }

    public final void a() {
        if (this.f4455b == null) {
            z zVarB = z.b(this.f4454a.getBundle("selector"));
            this.f4455b = zVarB;
            if (zVarB == null) {
                this.f4455b = z.f4516c;
            }
        }
    }

    public final boolean b() {
        return this.f4454a.getBoolean("activeScan");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            a();
            z zVar = this.f4455b;
            nVar.a();
            if (zVar.equals(nVar.f4455b) && b() == nVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        a();
        return this.f4455b.hashCode() ^ (b() ? 1 : 0);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("DiscoveryRequest{ selector=");
        a();
        sb2.append(this.f4455b);
        sb2.append(", activeScan=");
        sb2.append(b());
        sb2.append(", isValid=");
        a();
        this.f4455b.a();
        return a0.c.m(" }", !r1.f4518b.contains(null), sb2);
    }
}
