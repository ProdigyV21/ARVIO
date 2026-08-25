package i0;

import android.content.res.Resources;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f16002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources.Theme f16003b;

    public n(Resources resources, Resources.Theme theme) {
        this.f16002a = resources;
        this.f16003b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && n.class == obj.getClass()) {
            n nVar = (n) obj;
            if (this.f16002a.equals(nVar.f16002a) && Objects.equals(this.f16003b, nVar.f16003b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.f16002a, this.f16003b);
    }
}
