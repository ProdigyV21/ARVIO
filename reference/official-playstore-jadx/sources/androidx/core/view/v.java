package androidx.core.view;

import android.view.DisplayCutout;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayCutout f2323a;

    public v(DisplayCutout displayCutout) {
        this.f2323a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f2323a, ((v) obj).f2323a);
    }

    public final int hashCode() {
        DisplayCutout displayCutout = this.f2323a;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f2323a + "}";
    }
}
