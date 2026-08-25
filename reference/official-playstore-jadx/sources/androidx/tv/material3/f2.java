package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class f2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5966a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5967b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5968c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5969d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5970e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5971f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5972g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5973h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f5974i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f5975j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f5976l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f5977m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f5978n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f5979o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f5980p;

    public f2(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25) {
        this.f5966a = j10;
        this.f5967b = j11;
        this.f5968c = j12;
        this.f5969d = j13;
        this.f5970e = j14;
        this.f5971f = j15;
        this.f5972g = j16;
        this.f5973h = j17;
        this.f5974i = j18;
        this.f5975j = j19;
        this.k = j20;
        this.f5976l = j21;
        this.f5977m = j22;
        this.f5978n = j23;
        this.f5979o = j24;
        this.f5980p = j25;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f2.class != obj.getClass()) {
            return false;
        }
        f2 f2Var = (f2) obj;
        if (Color.m3473equalsimpl0(this.f5966a, f2Var.f5966a) && Color.m3473equalsimpl0(this.f5967b, f2Var.f5967b) && Color.m3473equalsimpl0(this.f5968c, f2Var.f5968c) && Color.m3473equalsimpl0(this.f5969d, f2Var.f5969d) && Color.m3473equalsimpl0(this.f5970e, f2Var.f5970e) && Color.m3473equalsimpl0(this.f5971f, f2Var.f5971f) && Color.m3473equalsimpl0(this.f5972g, f2Var.f5972g) && Color.m3473equalsimpl0(this.f5973h, f2Var.f5973h) && Color.m3473equalsimpl0(this.f5974i, f2Var.f5974i) && Color.m3473equalsimpl0(this.f5975j, f2Var.f5975j) && Color.m3473equalsimpl0(this.k, f2Var.k) && Color.m3473equalsimpl0(this.f5976l, f2Var.f5976l) && Color.m3473equalsimpl0(this.f5977m, f2Var.f5977m) && Color.m3473equalsimpl0(this.f5978n, f2Var.f5978n) && Color.m3473equalsimpl0(this.f5979o, f2Var.f5979o)) {
            return Color.m3473equalsimpl0(this.f5980p, f2Var.f5980p);
        }
        return false;
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5980p) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5966a) * 31, 31, this.f5967b), 31, this.f5968c), 31, this.f5969d), 31, this.f5970e), 31, this.f5971f), 31, this.f5972g), 31, this.f5973h), 31, this.f5974i), 31, this.f5975j), 31, this.k), 31, this.f5976l), 31, this.f5977m), 31, this.f5978n), 31, this.f5979o);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NavigationDrawerItemColors(containerColor=");
        androidx.compose.foundation.c.x(this.f5966a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5967b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5969d, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5970e, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5971f, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5972g, ", selectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5973h, ", selectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5974i, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5975j, ", disabledContentColor=", sb2);
        androidx.compose.foundation.c.x(this.k, ", focusedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5977m, ", focusedSelectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5978n, ", pressedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5979o, ", pressedSelectedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f5980p));
        sb2.append(')');
        return sb2.toString();
    }
}
