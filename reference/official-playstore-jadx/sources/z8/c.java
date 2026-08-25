package z8;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f23254b;

    public c(e eVar) {
        this.f23254b = eVar;
    }

    @Override // z8.b
    public final void f(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$1.visitEnd must not be null");
        }
        this.f23254b.f23256a.f23267d = strArr;
    }
}
