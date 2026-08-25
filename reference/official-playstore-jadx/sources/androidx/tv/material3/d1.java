package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5898c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5899d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5900e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5901f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5902g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5903h;

    public d1(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17) {
        this.f5896a = j10;
        this.f5897b = j11;
        this.f5898c = j12;
        this.f5899d = j13;
        this.f5900e = j14;
        this.f5901f = j15;
        this.f5902g = j16;
        this.f5903h = j17;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d1.class != obj.getClass()) {
            return false;
        }
        d1 d1Var = (d1) obj;
        return Color.m3473equalsimpl0(this.f5896a, d1Var.f5896a) && Color.m3473equalsimpl0(this.f5897b, d1Var.f5897b) && Color.m3473equalsimpl0(this.f5898c, d1Var.f5898c) && Color.m3473equalsimpl0(this.f5899d, d1Var.f5899d) && Color.m3473equalsimpl0(this.f5900e, d1Var.f5900e) && Color.m3473equalsimpl0(this.f5901f, d1Var.f5901f) && Color.m3473equalsimpl0(this.f5902g, d1Var.f5902g) && Color.m3473equalsimpl0(this.f5903h, d1Var.f5903h);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5903h) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5896a) * 31, 31, this.f5897b), 31, this.f5898c), 31, this.f5899d), 31, this.f5900e), 31, this.f5901f), 31, this.f5902g);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ClickableSurfaceColors(containerColor=");
        androidx.compose.foundation.c.x(this.f5896a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5897b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5898c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5899d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5900e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5901f, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5902g, ", disabledContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f5903h));
        sb2.append(')');
        return sb2.toString();
    }
}
