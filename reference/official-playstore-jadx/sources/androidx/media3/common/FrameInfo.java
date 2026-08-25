package androidx.media3.common;

/* JADX INFO: loaded from: classes3.dex */
public class FrameInfo {
    public final Format format;
    public final long offsetToAddUs;

    public FrameInfo(Format format, long j10) {
        ac.b.k(format.colorInfo != null, "format colorInfo must be set");
        int i10 = format.width;
        ac.b.f(i10, "format width must be positive, but is: %s", i10 > 0);
        int i11 = format.height;
        ac.b.f(i11, "format height must be positive, but is: %s", i11 > 0);
        this.format = format;
        this.offsetToAddUs = j10;
    }
}
