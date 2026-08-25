package ya;

import j8.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends cb.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.reflect.d f22987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f22988b = f4.f.o(2, new t0(this, 14));

    public k(kotlin.reflect.d dVar) {
        this.f22987a = dVar;
    }

    @Override // cb.b
    public final kotlin.reflect.d c() {
        return this.f22987a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return (ab.g) this.f22988b.getValue();
    }

    public final String toString() {
        return "kotlinx.serialization.PolymorphicSerializer(baseClass: " + this.f22987a + ')';
    }
}
