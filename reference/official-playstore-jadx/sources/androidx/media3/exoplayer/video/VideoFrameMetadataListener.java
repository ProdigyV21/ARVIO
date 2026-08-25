package androidx.media3.exoplayer.video;

import android.media.MediaFormat;
import androidx.media3.common.Format;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoFrameMetadataListener {
    void onVideoFrameAboutToBeRendered(long j10, long j11, Format format, MediaFormat mediaFormat);
}
