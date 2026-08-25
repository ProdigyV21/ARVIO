package s9;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e9.f f21741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f21742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f21743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e9.f f21744d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f21745e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f9.b f21746f;

    public q(e9.f fVar, Object obj, e9.f fVar2, e9.f fVar3, String str, f9.b bVar) {
        this.f21741a = fVar;
        this.f21742b = obj;
        this.f21743c = fVar2;
        this.f21744d = fVar3;
        this.f21745e = str;
        this.f21746f = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.f21741a.equals(qVar.f21741a) && kotlin.jvm.internal.p.a(this.f21742b, qVar.f21742b) && kotlin.jvm.internal.p.a(this.f21743c, qVar.f21743c) && this.f21744d.equals(qVar.f21744d) && kotlin.jvm.internal.p.a(this.f21745e, qVar.f21745e) && kotlin.jvm.internal.p.a(this.f21746f, qVar.f21746f);
    }

    public final int hashCode() {
        int iHashCode = this.f21741a.hashCode() * 31;
        Object obj = this.f21742b;
        int iHashCode2 = (iHashCode + (obj == null ? 0 : obj.hashCode())) * 31;
        Object obj2 = this.f21743c;
        return this.f21746f.hashCode() + androidx.compose.foundation.c.c((this.f21744d.hashCode() + ((iHashCode2 + (obj2 != null ? obj2.hashCode() : 0)) * 31)) * 31, 31, this.f21745e);
    }

    public final String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.f21741a + ", compilerVersion=" + this.f21742b + ", languageVersion=" + this.f21743c + ", expectedVersion=" + this.f21744d + ", filePath=" + this.f21745e + ", classId=" + this.f21746f + ')';
    }
}
