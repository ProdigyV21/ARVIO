package a8;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes5.dex */
public final class z1 implements r7.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a1.a f316m = new a1.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r7.a f317i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile SoftReference f318l;

    public z1(Object obj, r7.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'initializer' of kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal.<init> must not be null");
        }
        this.f318l = null;
        this.f317i = aVar;
        if (obj != null) {
            this.f318l = new SoftReference(obj);
        }
    }

    @Override // r7.a
    public final Object invoke() {
        Object obj;
        Object obj2 = f316m;
        SoftReference softReference = this.f318l;
        if (softReference != null && (obj = softReference.get()) != null) {
            if (obj == obj2) {
                return null;
            }
            return obj;
        }
        Object objInvoke = this.f317i.invoke();
        if (objInvoke != null) {
            obj2 = objInvoke;
        }
        this.f318l = new SoftReference(obj2);
        return objInvoke;
    }
}
