package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PreloadException extends Exception {
    public final MediaItem mediaItem;

    public PreloadException(MediaItem mediaItem, String str, Throwable th) {
        super(str, th);
        this.mediaItem = mediaItem;
    }

    public boolean errorInfoEquals(PreloadException preloadException) {
        if (this == preloadException) {
            return true;
        }
        if (preloadException != null) {
            Throwable cause = getCause();
            Throwable cause2 = preloadException.getCause();
            if (cause == null || cause2 == null) {
                if (cause == null && cause2 == null) {
                }
            } else if (!Objects.equals(cause.getMessage(), cause2.getMessage()) || !cause.getClass().equals(cause2.getClass())) {
                return false;
            }
            if (Objects.equals(this.mediaItem, preloadException.mediaItem) && Objects.equals(getMessage(), preloadException.getMessage())) {
                return true;
            }
        }
        return false;
    }
}
