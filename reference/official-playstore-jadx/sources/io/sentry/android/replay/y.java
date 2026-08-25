package io.sentry.android.replay;

/* JADX INFO: loaded from: classes5.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17090b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f17091c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f17092d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f17093e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f17094f;

    public y(int i10, int i11, float f10, float f11, int i12, int i13) {
        this.f17089a = i10;
        this.f17090b = i11;
        this.f17091c = f10;
        this.f17092d = f11;
        this.f17093e = i12;
        this.f17094f = i13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return this.f17089a == yVar.f17089a && this.f17090b == yVar.f17090b && Float.compare(this.f17091c, yVar.f17091c) == 0 && Float.compare(this.f17092d, yVar.f17092d) == 0 && this.f17093e == yVar.f17093e && this.f17094f == yVar.f17094f;
    }

    public final int hashCode() {
        return ((a0.c.b(this.f17092d, a0.c.b(this.f17091c, ((this.f17089a * 31) + this.f17090b) * 31, 31), 31) + this.f17093e) * 31) + this.f17094f;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ScreenshotRecorderConfig(recordingWidth=");
        sb2.append(this.f17089a);
        sb2.append(", recordingHeight=");
        sb2.append(this.f17090b);
        sb2.append(", scaleFactorX=");
        sb2.append(this.f17091c);
        sb2.append(", scaleFactorY=");
        sb2.append(this.f17092d);
        sb2.append(", frameRate=");
        sb2.append(this.f17093e);
        sb2.append(", bitRate=");
        return a0.c.o(sb2, this.f17094f, ')');
    }
}
