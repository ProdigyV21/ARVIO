package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 extends w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f4783l;

    public s0(Class cls) {
        super(true);
        if (!Parcelable.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException((cls + " does not implement Parcelable.").toString());
        }
        try {
            this.f4783l = Class.forName("[L" + cls.getName() + ';');
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(e5);
        }
    }

    @Override // androidx.navigation.w0
    public final Object a(Bundle bundle, String str) {
        return (Parcelable[]) bundle.get(str);
    }

    @Override // androidx.navigation.w0
    public final String b() {
        return this.f4783l.getName();
    }

    @Override // androidx.navigation.w0
    public final Object c(String str) {
        throw new UnsupportedOperationException("Arrays don't support default values.");
    }

    @Override // androidx.navigation.w0
    public final void e(Bundle bundle, String str, Object obj) {
        Parcelable[] parcelableArr = (Parcelable[]) obj;
        this.f4783l.cast(parcelableArr);
        bundle.putParcelableArray(str, parcelableArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !s0.class.equals(obj.getClass())) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f4783l, ((s0) obj).f4783l);
    }

    public final int hashCode() {
        return this.f4783l.hashCode();
    }
}
