package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements MediaCodecUtil.ScoreProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Format f3706b;

    public /* synthetic */ k(Format format, int i10) {
        this.f3705a = i10;
        this.f3706b = format;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
    public final int getScore(Object obj) {
        switch (this.f3705a) {
            case 0:
                return MediaCodecUtil.lambda$getDecoderInfosSortedByFullFormatSupport$1(this.f3706b, (MediaCodecInfo) obj);
            default:
                return MediaCodecUtil.lambda$getDecoderInfosSortedByFormatSupport$0(this.f3706b, (MediaCodecInfo) obj);
        }
    }
}
