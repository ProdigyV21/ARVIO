package androidx.tv.material3;

import androidx.compose.ui.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6254c;

    public o(long j10, long j11, long j12) {
        this.f6252a = j10;
        this.f6253b = j11;
        this.f6254c = j12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        return Color.m3473equalsimpl0(this.f6252a, oVar.f6252a) && Color.m3473equalsimpl0(this.f6253b, oVar.f6253b) && Color.m3473equalsimpl0(this.f6254c, oVar.f6254c);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f6254c) + androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f6252a) * 31, 31, this.f6253b);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("CardContainerContentColor(contentColor=");
        androidx.compose.foundation.c.x(this.f6252a, ", focusedContentColor=", sb2);
        androidx.compose.foundation.c.x(this.f6253b, ", pressedContentColor=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.f6254c));
        sb2.append(')');
        return sb2.toString();
    }
}
