package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f19637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f19638b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.l f19639c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f19640d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f19641e;

    public u(Object obj, j jVar, r7.l lVar, Object obj2, Throwable th) {
        this.f19637a = obj;
        this.f19638b = jVar;
        this.f19639c = lVar;
        this.f19640d = obj2;
        this.f19641e = th;
    }

    public static u a(u uVar, j jVar, Throwable th, int i10) {
        Object obj = uVar.f19637a;
        if ((i10 & 2) != 0) {
            jVar = uVar.f19638b;
        }
        j jVar2 = jVar;
        r7.l lVar = uVar.f19639c;
        Object obj2 = uVar.f19640d;
        if ((i10 & 16) != 0) {
            th = uVar.f19641e;
        }
        return new u(obj, jVar2, lVar, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return kotlin.jvm.internal.p.a(this.f19637a, uVar.f19637a) && kotlin.jvm.internal.p.a(this.f19638b, uVar.f19638b) && kotlin.jvm.internal.p.a(this.f19639c, uVar.f19639c) && kotlin.jvm.internal.p.a(this.f19640d, uVar.f19640d) && kotlin.jvm.internal.p.a(this.f19641e, uVar.f19641e);
    }

    public final int hashCode() {
        Object obj = this.f19637a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        j jVar = this.f19638b;
        int iHashCode2 = (iHashCode + (jVar == null ? 0 : jVar.hashCode())) * 31;
        r7.l lVar = this.f19639c;
        int iHashCode3 = (iHashCode2 + (lVar == null ? 0 : lVar.hashCode())) * 31;
        Object obj2 = this.f19640d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f19641e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f19637a + ", cancelHandler=" + this.f19638b + ", onCancellation=" + this.f19639c + ", idempotentResume=" + this.f19640d + ", cancelCause=" + this.f19641e + ')';
    }

    public /* synthetic */ u(Object obj, j jVar, r7.l lVar, Throwable th, int i10) {
        this(obj, (i10 & 2) != 0 ? null : jVar, (i10 & 4) != 0 ? null : lVar, (Object) null, (i10 & 16) != 0 ? null : th);
    }
}
