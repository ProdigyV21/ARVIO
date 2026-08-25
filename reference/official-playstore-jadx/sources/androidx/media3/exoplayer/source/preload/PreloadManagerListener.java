package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;

/* JADX INFO: loaded from: classes3.dex */
public interface PreloadManagerListener {
    void onCompleted(MediaItem mediaItem);

    void onError(PreloadException preloadException);
}
