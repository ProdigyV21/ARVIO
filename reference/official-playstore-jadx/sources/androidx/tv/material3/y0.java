package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6545c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6546d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6548f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6549g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6550h;

    public y0(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17) {
        this.f6543a = j10;
        this.f6544b = j11;
        this.f6545c = j12;
        this.f6546d = j13;
        this.f6547e = j14;
        this.f6548f = j15;
        this.f6549g = j16;
        this.f6550h = j17;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || y0.class != obj.getClass()) {
            return false;
        }
        y0 y0Var = (y0) obj;
        return Color.m3473equalsimpl0(this.f6543a, y0Var.f6543a) && Color.m3473equalsimpl0(this.f6544b, y0Var.f6544b) && Color.m3473equalsimpl0(this.f6545c, y0Var.f6545c) && Color.m3473equalsimpl0(this.f6546d, y0Var.f6546d) && Color.m3473equalsimpl0(this.f6547e, y0Var.f6547e) && Color.m3473equalsimpl0(this.f6548f, y0Var.f6548f) && Color.m3473equalsimpl0(this.f6549g, y0Var.f6549g) && Color.m3473equalsimpl0(this.f6550h, y0Var.f6550h);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6550h) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6543a) * 31, 31, this.f6544b), 31, this.f6545c), 31, this.f6546d), 31, this.f6547e), 31, this.f6548f), 31, this.f6549g);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ClickableChipColors(containerColor=");
        androidx.compose.foundation.c.x(this.f6543a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6544b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6545c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6546d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6547e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6548f, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6549g, ", disabledContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6550h));
        sb2.append(')');
        return sb2.toString();
    }
}
