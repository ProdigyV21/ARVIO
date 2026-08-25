package androidx.media3.exoplayer.mediacodec;

import android.os.Bundle;
import androidx.media3.exoplayer.mediacodec.LoudnessCodecController;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements MediaCodecUtil.ScoreProvider, LoudnessCodecController.LoudnessParameterUpdateListener, MediaCodecSelector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3704a;

    public /* synthetic */ j(int i10) {
        this.f3704a = i10;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecSelector
    public List getDecoderInfos(String str, boolean z, boolean z5) {
        switch (this.f3704a) {
            case 4:
                return MediaCodecUtil.getDecoderInfos(str, z, z5);
            default:
                return MediaCodecUtil.getDecoderInfosSortedBySoftwareOnly(MediaCodecSelector.DEFAULT.getDecoderInfos(str, z, z5));
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
    public int getScore(Object obj) {
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) obj;
        switch (this.f3704a) {
            case 0:
                return MediaCodecUtil.lambda$getDecoderInfosSortedBySoftwareOnly$2(mediaCodecInfo);
            default:
                return MediaCodecUtil.lambda$applyWorkarounds$3(mediaCodecInfo);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.LoudnessCodecController.LoudnessParameterUpdateListener
    public Bundle onLoudnessParameterUpdate(Bundle bundle) {
        return e.a(bundle);
    }
}
