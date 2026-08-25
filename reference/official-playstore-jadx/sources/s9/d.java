package s9;

import g8.v0;

/* JADX INFO: loaded from: classes5.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c9.f f21683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a9.n f21684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c9.a f21685c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v0 f21686d;

    public d(c9.f fVar, a9.n nVar, c9.a aVar, v0 v0Var) {
        this.f21683a = fVar;
        this.f21684b = nVar;
        this.f21685c = aVar;
        this.f21686d = v0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return kotlin.jvm.internal.p.a(this.f21683a, dVar.f21683a) && kotlin.jvm.internal.p.a(this.f21684b, dVar.f21684b) && kotlin.jvm.internal.p.a(this.f21685c, dVar.f21685c) && kotlin.jvm.internal.p.a(this.f21686d, dVar.f21686d);
    }

    public final int hashCode() {
        return this.f21686d.hashCode() + ((this.f21685c.hashCode() + ((this.f21684b.hashCode() + (this.f21683a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ClassData(nameResolver=" + this.f21683a + ", classProto=" + this.f21684b + ", metadataVersion=" + this.f21685c + ", sourceElement=" + this.f21686d + ')';
    }
}
