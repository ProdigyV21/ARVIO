package androidx.media3.exoplayer.mediacodec;

import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new j(4);
    public static final MediaCodecSelector PREFER_SOFTWARE = new j(5);

    List<MediaCodecInfo> getDecoderInfos(String str, boolean z, boolean z5) throws MediaCodecUtil.DecoderQueryException;
}
