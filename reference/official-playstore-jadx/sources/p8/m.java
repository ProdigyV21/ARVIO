package p8;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.b f21169a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f21170b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w8.g f21171c;

    public m(f9.b bVar, w8.g gVar, int i10) {
        gVar = (i10 & 4) != 0 ? null : gVar;
        this.f21169a = bVar;
        this.f21170b = null;
        this.f21171c = gVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return kotlin.jvm.internal.p.a(this.f21169a, mVar.f21169a) && kotlin.jvm.internal.p.a(this.f21170b, mVar.f21170b) && kotlin.jvm.internal.p.a(this.f21171c, mVar.f21171c);
    }

    public final int hashCode() {
        int iHashCode = this.f21169a.hashCode() * 31;
        byte[] bArr = this.f21170b;
        int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
        w8.g gVar = this.f21171c;
        return iHashCode2 + (gVar != null ? gVar.hashCode() : 0);
    }

    public final String toString() {
        return "Request(classId=" + this.f21169a + ", previouslyFoundClassFileContent=" + Arrays.toString(this.f21170b) + ", outerClass=" + this.f21171c + ')';
    }
}
