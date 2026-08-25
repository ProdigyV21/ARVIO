package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class f3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5981a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5982b;

    public f3(long j10, long j11) {
        this.f5981a = j10;
        this.f5982b = j11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f3.class != obj.getClass()) {
            return false;
        }
        f3 f3Var = (f3) obj;
        return Color.m3473equalsimpl0(this.f5981a, f3Var.f5981a) && Color.m3473equalsimpl0(this.f5982b, f3Var.f5982b);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5982b) + (Color.m3479hashCodeimpl(this.f5981a) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SurfaceColors(containerColor=");
        androidx.compose.foundation.c.x(this.f5981a, ", contentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f5982b));
        sb2.append(')');
        return sb2.toString();
    }
}
