package androidx.media3.exoplayer.dash.manifest;

/* JADX INFO: loaded from: classes3.dex */
public final class ServiceDescriptionElement {
    public final long maxOffsetMs;
    public final float maxPlaybackSpeed;
    public final long minOffsetMs;
    public final float minPlaybackSpeed;
    public final long targetOffsetMs;

    public ServiceDescriptionElement(long j10, long j11, long j12, float f10, float f11) {
        this.targetOffsetMs = j10;
        this.minOffsetMs = j11;
        this.maxOffsetMs = j12;
        this.minPlaybackSpeed = f10;
        this.maxPlaybackSpeed = f11;
    }
}
