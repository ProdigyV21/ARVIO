package z8;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f23259b;

    public h(j jVar) {
        this.f23259b = jVar;
    }

    @Override // z8.b
    public final void f(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$1.visitEnd must not be null");
        }
        this.f23259b.f23261a.f23267d = strArr;
    }
}
