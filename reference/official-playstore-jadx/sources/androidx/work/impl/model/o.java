package androidx.work.impl.model;

import h.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6921b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return kotlin.jvm.internal.p.a(this.f6920a, oVar.f6920a) && this.f6921b == oVar.f6921b;
    }

    public final int hashCode() {
        return f0.c(this.f6921b) + (this.f6920a.hashCode() * 31);
    }

    public final String toString() {
        return "IdAndState(id=" + this.f6920a + ", state=" + g8.b.u(this.f6921b) + ')';
    }
}
