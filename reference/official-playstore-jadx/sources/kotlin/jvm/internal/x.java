package kotlin.jvm.internal;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f19753i;

    public x(Class cls) {
        this.f19753i = cls;
    }

    @Override // kotlin.jvm.internal.f
    public final Class c() {
        return this.f19753i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof x) {
            return p.a(this.f19753i, ((x) obj).f19753i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f19753i.hashCode();
    }

    public final String toString() {
        return this.f19753i.toString() + " (Kotlin reflection is not available)";
    }
}
