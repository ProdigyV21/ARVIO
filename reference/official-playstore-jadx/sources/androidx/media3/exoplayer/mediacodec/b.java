package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements MediaCodec.OnFrameRenderedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3698a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MediaCodecAdapter.OnFrameRenderedListener f3699b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MediaCodecAdapter f3700c;

    public /* synthetic */ b(MediaCodecAdapter mediaCodecAdapter, MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, int i10) {
        this.f3698a = i10;
        this.f3700c = mediaCodecAdapter;
        this.f3699b = onFrameRenderedListener;
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public final void onFrameRendered(MediaCodec mediaCodec, long j10, long j11) {
        switch (this.f3698a) {
            case 0:
                ((AsynchronousMediaCodecAdapter) this.f3700c).lambda$setOnFrameRenderedListener$1(this.f3699b, mediaCodec, j10, j11);
                break;
            default:
                ((SynchronousMediaCodecAdapter) this.f3700c).lambda$setOnFrameRenderedListener$0(this.f3699b, mediaCodec, j10, j11);
                break;
        }
    }
}
