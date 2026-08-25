package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5948b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5949c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5950d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5951e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5952f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5953g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5954h;

    public f(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17) {
        this.f5947a = j10;
        this.f5948b = j11;
        this.f5949c = j12;
        this.f5950d = j13;
        this.f5951e = j14;
        this.f5952f = j15;
        this.f5953g = j16;
        this.f5954h = j17;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        return Color.m3473equalsimpl0(this.f5947a, fVar.f5947a) && Color.m3473equalsimpl0(this.f5948b, fVar.f5948b) && Color.m3473equalsimpl0(this.f5949c, fVar.f5949c) && Color.m3473equalsimpl0(this.f5950d, fVar.f5950d) && Color.m3473equalsimpl0(this.f5951e, fVar.f5951e) && Color.m3473equalsimpl0(this.f5952f, fVar.f5952f) && Color.m3473equalsimpl0(this.f5953g, fVar.f5953g) && Color.m3473equalsimpl0(this.f5954h, fVar.f5954h);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5954h) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5947a) * 31, 31, this.f5948b), 31, this.f5949c), 31, this.f5950d), 31, this.f5951e), 31, this.f5952f), 31, this.f5953g);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ButtonColors(containerColor=");
        androidx.compose.foundation.c.x(this.f5947a, ", contentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5948b, ", focusedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5949c, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5950d, ", pressedContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5951e, ", pressedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f5952f, ", disabledContainerColor=", sb2);
        androidx.compose.foundation.c.x(this.f5953g, ", disabledContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f5954h));
        sb2.append(')');
        return sb2.toString();
    }
}
