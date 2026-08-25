package j5;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19210a;

    public a(int i10) {
        this.f19210a = i10;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return e.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f19210a == eVar.tag() && d.f19212i.equals(eVar.intEncoding());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (14552422 ^ this.f19210a) + (d.f19212i.hashCode() ^ 2041407134);
    }

    @Override // j5.e
    public final d intEncoding() {
        return d.f19212i;
    }

    @Override // j5.e
    public final int tag() {
        return this.f19210a;
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f19210a + "intEncoding=" + d.f19212i + ')';
    }
}
