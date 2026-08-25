package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public final class s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s1 f6376c = new s1(Color.INSTANCE.m3507getTransparent0d7_KjU(), Dp.m5678constructorimpl(0));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f6378b;

    public s1(long j10, float f10) {
        this.f6377a = j10;
        this.f6378b = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s1.class != obj.getClass()) {
            return false;
        }
        s1 s1Var = (s1) obj;
        return Color.m3473equalsimpl0(this.f6377a, s1Var.f6377a) && Dp.m5683equalsimpl0(this.f6378b, s1Var.f6378b);
    }

    public final int hashCode() {
        return Dp.m5684hashCodeimpl(this.f6378b) + (Color.m3479hashCodeimpl(this.f6377a) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Glow(elevationColor=");
        androidx.compose.foundation.c.x(this.f6377a, ", elevation=", sb2);
        sb2.append((Object) Dp.m5689toStringimpl(this.f6378b));
        sb2.append(')');
        return sb2.toString();
    }
}
