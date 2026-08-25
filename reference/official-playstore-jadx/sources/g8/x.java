package g8;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.f f15556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y9.i f15557b;

    public x(f9.f fVar, y9.i iVar) {
        this.f15556a = fVar;
        this.f15557b = iVar;
    }

    @Override // g8.c1
    public final boolean a(f9.f fVar) {
        return kotlin.jvm.internal.p.a(this.f15556a, fVar);
    }

    public final String toString() {
        return "InlineClassRepresentation(underlyingPropertyName=" + this.f15556a + ", underlyingType=" + this.f15557b + ')';
    }
}
