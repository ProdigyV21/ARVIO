package androidx.core.view;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class k3 extends j3 {
    public k3(q3 q3Var, WindowInsets windowInsets) {
        super(q3Var, windowInsets);
    }

    @Override // androidx.core.view.n3
    public q3 a() {
        return q3.c(null, this.f2253c.consumeDisplayCutout());
    }

    @Override // androidx.core.view.n3
    public v e() {
        DisplayCutout displayCutout = this.f2253c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new v(displayCutout);
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k3)) {
            return false;
        }
        k3 k3Var = (k3) obj;
        return Objects.equals(this.f2253c, k3Var.f2253c) && Objects.equals(this.f2257g, k3Var.f2257g);
    }

    @Override // androidx.core.view.n3
    public int hashCode() {
        return this.f2253c.hashCode();
    }
}
