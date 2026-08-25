package z8;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f23255b;

    public d(e eVar) {
        this.f23255b = eVar;
    }

    @Override // z8.b
    public final void f(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$2.visitEnd must not be null");
        }
        this.f23255b.f23256a.f23268e = strArr;
    }
}
