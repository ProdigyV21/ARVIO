package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class r4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6354c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6355d;

    public r4(long j10, long j11, long j12, long j13) {
        this.f6352a = j10;
        this.f6353b = j11;
        this.f6354c = j12;
        this.f6355d = j13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r4.class != obj.getClass()) {
            return false;
        }
        r4 r4Var = (r4) obj;
        return Color.m3473equalsimpl0(this.f6352a, r4Var.f6352a) && Color.m3473equalsimpl0(this.f6353b, r4Var.f6353b) && Color.m3473equalsimpl0(this.f6354c, r4Var.f6354c) && Color.m3473equalsimpl0(this.f6355d, r4Var.f6355d);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6355d) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6352a) * 31, 31, this.f6353b), 31, this.f6354c);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("WideButtonContentColor(contentColor=");
        androidx.compose.foundation.c.x(this.f6352a, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6353b, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6354c, ", disabledContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6355d));
        sb2.append(')');
        return sb2.toString();
    }
}
