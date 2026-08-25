package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public final class t0 extends w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f4784l;

    public t0(Class cls) {
        super(true);
        if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
            this.f4784l = cls;
            return;
        }
        throw new IllegalArgumentException((cls + " does not implement Parcelable or Serializable.").toString());
    }

    @Override // androidx.navigation.w0
    public final Object a(Bundle bundle, String str) {
        return bundle.get(str);
    }

    @Override // androidx.navigation.w0
    public final String b() {
        return this.f4784l.getName();
    }

    @Override // androidx.navigation.w0
    public final Object c(String str) {
        throw new UnsupportedOperationException("Parcelables don't support default values.");
    }

    @Override // androidx.navigation.w0
    public final void e(Bundle bundle, String str, Object obj) {
        this.f4784l.cast(obj);
        if (obj == null || (obj instanceof Parcelable)) {
            bundle.putParcelable(str, (Parcelable) obj);
        } else if (obj instanceof Serializable) {
            bundle.putSerializable(str, (Serializable) obj);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !t0.class.equals(obj.getClass())) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f4784l, ((t0) obj).f4784l);
    }

    public final int hashCode() {
        return this.f4784l.hashCode();
    }
}
