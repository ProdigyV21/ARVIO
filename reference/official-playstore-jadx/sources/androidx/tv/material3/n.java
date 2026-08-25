package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6223a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6225c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6226d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6227e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6228f;

    public n(long j10, long j11, long j12, long j13, long j14, long j15) {
        this.f6223a = j10;
        this.f6224b = j11;
        this.f6225c = j12;
        this.f6226d = j13;
        this.f6227e = j14;
        this.f6228f = j15;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        return Color.m3473equalsimpl0(this.f6223a, nVar.f6223a) && Color.m3473equalsimpl0(this.f6224b, nVar.f6224b) && Color.m3473equalsimpl0(this.f6225c, nVar.f6225c) && Color.m3473equalsimpl0(this.f6226d, nVar.f6226d) && Color.m3473equalsimpl0(this.f6227e, nVar.f6227e) && Color.m3473equalsimpl0(this.f6228f, nVar.f6228f);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6228f) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6223a) * 31, 31, this.f6224b), 31, this.f6225c), 31, this.f6226d), 31, this.f6227e);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("CardColors(containerColor=");
        androidx.compose.foundation.c.x(this.f6223a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6224b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6225c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6226d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f6227e, ", pressedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6228f));
        sb2.append(')');
        return sb2.toString();
    }
}
