package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class y2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6558b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6559c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6560d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6561e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6562f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6563g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6564h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f6565i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f6566j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f6567l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f6568m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f6569n;

    public y2(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23) {
        this.f6557a = j10;
        this.f6558b = j11;
        this.f6559c = j12;
        this.f6560d = j13;
        this.f6561e = j14;
        this.f6562f = j15;
        this.f6563g = j16;
        this.f6564h = j17;
        this.f6565i = j18;
        this.f6566j = j19;
        this.k = j20;
        this.f6567l = j21;
        this.f6568m = j22;
        this.f6569n = j23;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || y2.class != obj.getClass()) {
            return false;
        }
        y2 y2Var = (y2) obj;
        return Color.m3473equalsimpl0(this.f6557a, y2Var.f6557a) && Color.m3473equalsimpl0(this.f6558b, y2Var.f6558b) && Color.m3473equalsimpl0(this.f6559c, y2Var.f6559c) && Color.m3473equalsimpl0(this.f6560d, y2Var.f6560d) && Color.m3473equalsimpl0(this.f6561e, y2Var.f6561e) && Color.m3473equalsimpl0(this.f6562f, y2Var.f6562f) && Color.m3473equalsimpl0(this.f6563g, y2Var.f6563g) && Color.m3473equalsimpl0(this.f6564h, y2Var.f6564h) && Color.m3473equalsimpl0(this.f6565i, y2Var.f6565i) && Color.m3473equalsimpl0(this.f6566j, y2Var.f6566j) && Color.m3473equalsimpl0(this.k, y2Var.k) && Color.m3473equalsimpl0(this.f6567l, y2Var.f6567l) && Color.m3473equalsimpl0(this.f6568m, y2Var.f6568m) && Color.m3473equalsimpl0(this.f6569n, y2Var.f6569n);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6569n) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6557a) * 31, 31, this.f6558b), 31, this.f6559c), 31, this.f6560d), 31, this.f6561e), 31, this.f6562f), 31, this.f6563g), 31, this.f6564h), 31, this.f6565i), 31, this.f6566j), 31, this.k), 31, this.f6567l), 31, this.f6568m);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SelectableSurfaceColors(containerColor=");
        androidx.compose.foundation.c.x(this.f6557a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6558b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6559c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6560d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6561e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6562f, ", selectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6563g, ", selectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6564h, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6565i, ", disabledContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6566j, ", focusedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.k, ", focusedSelectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6567l, ", pressedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6568m, ", pressedSelectedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6569n));
        sb2.append(')');
        return sb2.toString();
    }
}
