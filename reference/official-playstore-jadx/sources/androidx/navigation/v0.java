package androidx.navigation;

import android.os.Bundle;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class v0 extends w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f4788l;

    public v0(Class cls) {
        super(true);
        if (!Serializable.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException((cls + " does not implement Serializable.").toString());
        }
        if (!cls.isEnum()) {
            this.f4788l = cls;
            return;
        }
        throw new IllegalArgumentException((cls + " is an Enum. You should use EnumType instead.").toString());
    }

    @Override // androidx.navigation.w0
    public final Object a(Bundle bundle, String str) {
        return (Serializable) bundle.get(str);
    }

    @Override // androidx.navigation.w0
    public String b() {
        return this.f4788l.getName();
    }

    @Override // androidx.navigation.w0
    public final void e(Bundle bundle, String str, Object obj) {
        Serializable serializable = (Serializable) obj;
        this.f4788l.cast(serializable);
        bundle.putSerializable(str, serializable);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v0)) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f4788l, ((v0) obj).f4788l);
    }

    @Override // androidx.navigation.w0
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Serializable c(String str) {
        throw new UnsupportedOperationException("Serializables don't support default values.");
    }

    public final int hashCode() {
        return this.f4788l.hashCode();
    }

    public v0(Class cls, int i10) {
        super(false);
        if (Serializable.class.isAssignableFrom(cls)) {
            this.f4788l = cls;
            return;
        }
        throw new IllegalArgumentException((cls + " does not implement Serializable.").toString());
    }
}
