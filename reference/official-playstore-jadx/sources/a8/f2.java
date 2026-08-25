package a8;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class f2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f192b;

    public f2(ClassLoader classLoader) {
        this.f191a = new WeakReference(classLoader);
        this.f192b = System.identityHashCode(classLoader);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f2) && this.f191a.get() == ((f2) obj).f191a.get();
    }

    public final int hashCode() {
        return this.f192b;
    }

    public final String toString() {
        String string;
        ClassLoader classLoader = (ClassLoader) this.f191a.get();
        return (classLoader == null || (string = classLoader.toString()) == null) ? "<null>" : string;
    }
}
