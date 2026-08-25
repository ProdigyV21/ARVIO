package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class t2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6414d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6416f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6417g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6418h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f6419i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f6420j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f6421l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f6422m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f6423n;

    public t2(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23) {
        this.f6411a = j10;
        this.f6412b = j11;
        this.f6413c = j12;
        this.f6414d = j13;
        this.f6415e = j14;
        this.f6416f = j15;
        this.f6417g = j16;
        this.f6418h = j17;
        this.f6419i = j18;
        this.f6420j = j19;
        this.k = j20;
        this.f6421l = j21;
        this.f6422m = j22;
        this.f6423n = j23;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || t2.class != obj.getClass()) {
            return false;
        }
        t2 t2Var = (t2) obj;
        return Color.m3473equalsimpl0(this.f6411a, t2Var.f6411a) && Color.m3473equalsimpl0(this.f6412b, t2Var.f6412b) && Color.m3473equalsimpl0(this.f6413c, t2Var.f6413c) && Color.m3473equalsimpl0(this.f6414d, t2Var.f6414d) && Color.m3473equalsimpl0(this.f6415e, t2Var.f6415e) && Color.m3473equalsimpl0(this.f6416f, t2Var.f6416f) && Color.m3473equalsimpl0(this.f6417g, t2Var.f6417g) && Color.m3473equalsimpl0(this.f6418h, t2Var.f6418h) && Color.m3473equalsimpl0(this.f6419i, t2Var.f6419i) && Color.m3473equalsimpl0(this.f6420j, t2Var.f6420j) && Color.m3473equalsimpl0(this.k, t2Var.k) && Color.m3473equalsimpl0(this.f6421l, t2Var.f6421l) && Color.m3473equalsimpl0(this.f6422m, t2Var.f6422m) && Color.m3473equalsimpl0(this.f6423n, t2Var.f6423n);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6423n) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6411a) * 31, 31, this.f6412b), 31, this.f6413c), 31, this.f6414d), 31, this.f6415e), 31, this.f6416f), 31, this.f6417g), 31, this.f6418h), 31, this.f6419i), 31, this.f6420j), 31, this.k), 31, this.f6421l), 31, this.f6422m);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SelectableChipColors(containerColor=");
        androidx.compose.foundation.c.x(this.f6411a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6412b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6413c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6414d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6415e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6416f, ", selectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6417g, ", selectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6418h, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6419i, ", disabledContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6420j, ", focusedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.k, ", focusedSelectedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6421l, ", pressedSelectedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6422m, ", pressedSelectedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6423n));
        sb2.append(')');
        return sb2.toString();
    }
}
