package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6511d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6512e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6513f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6514g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6515h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f6516i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f6517j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f6518l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f6519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f6520n;

    public x1(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23) {
        this.f6508a = j10;
        this.f6509b = j11;
        this.f6510c = j12;
        this.f6511d = j13;
        this.f6512e = j14;
        this.f6513f = j15;
        this.f6514g = j16;
        this.f6515h = j17;
        this.f6516i = j18;
        this.f6517j = j19;
        this.k = j20;
        this.f6518l = j21;
        this.f6519m = j22;
        this.f6520n = j23;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x1.class != obj.getClass()) {
            return false;
        }
        x1 x1Var = (x1) obj;
        return Color.m3473equalsimpl0(this.f6508a, x1Var.f6508a) && Color.m3473equalsimpl0(this.f6509b, x1Var.f6509b) && Color.m3473equalsimpl0(this.f6510c, x1Var.f6510c) && Color.m3473equalsimpl0(this.f6511d, x1Var.f6511d) && Color.m3473equalsimpl0(this.f6512e, x1Var.f6512e) && Color.m3473equalsimpl0(this.f6513f, x1Var.f6513f) && Color.m3473equalsimpl0(this.f6514g, x1Var.f6514g) && Color.m3473equalsimpl0(this.f6515h, x1Var.f6515h) && Color.m3473equalsimpl0(this.f6516i, x1Var.f6516i) && Color.m3473equalsimpl0(this.f6517j, x1Var.f6517j) && Color.m3473equalsimpl0(this.k, x1Var.k) && Color.m3473equalsimpl0(this.f6518l, x1Var.f6518l) && Color.m3473equalsimpl0(this.f6519m, x1Var.f6519m) && Color.m3473equalsimpl0(this.f6520n, x1Var.f6520n);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6520n) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6508a) * 31, 31, this.f6509b), 31, this.f6510c), 31, this.f6511d), 31, this.f6512e), 31, this.f6513f), 31, this.f6514g), 31, this.f6515h), 31, this.f6516i), 31, this.f6517j), 31, this.k), 31, this.f6518l), 31, this.f6519m);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ListItemColors(containerColor=");
        androidx.compose.foundation.c.x(this.f6508a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6509b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6510c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6511d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6512e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6513f, ", selectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6514g, ", selectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6515h, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6516i, ", disabledContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6517j, ", focusedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.k, ", focusedSelectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6518l, ", pressedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6519m, ", pressedSelectedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6520n));
        sb2.append(')');
        return sb2.toString();
    }
}
