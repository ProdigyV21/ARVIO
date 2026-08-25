package z8;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f23260b;

    public i(j jVar) {
        this.f23260b = jVar;
    }

    @Override // z8.b
    public final void f(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$2.visitEnd must not be null");
        }
        this.f23260b.f23261a.f23268e = strArr;
    }
}
