package androidx.navigation;

import android.os.Bundle;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 extends w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f4785l;

    public u0(Class cls) {
        super(true);
        if (!Serializable.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException((cls + " does not implement Serializable.").toString());
        }
        try {
            this.f4785l = Class.forName("[L" + cls.getName() + ';');
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(e5);
        }
    }

    @Override // androidx.navigation.w0
    public final Object a(Bundle bundle, String str) {
        return (Serializable[]) bundle.get(str);
    }

    @Override // androidx.navigation.w0
    public final String b() {
        return this.f4785l.getName();
    }

    @Override // androidx.navigation.w0
    public final Object c(String str) {
        throw new UnsupportedOperationException("Arrays don't support default values.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.Serializable, java.io.Serializable[], java.lang.Object] */
    @Override // androidx.navigation.w0
    public final void e(Bundle bundle, String str, Object obj) {
        ?? r4 = (Serializable[]) obj;
        this.f4785l.cast(r4);
        bundle.putSerializable(str, r4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !u0.class.equals(obj.getClass())) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f4785l, ((u0) obj).f4785l);
    }

    public final int hashCode() {
        return this.f4785l.hashCode();
    }
}
