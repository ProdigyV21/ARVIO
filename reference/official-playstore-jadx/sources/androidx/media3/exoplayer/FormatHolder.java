package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DrmSession;

/* JADX INFO: loaded from: classes3.dex */
public final class FormatHolder {
    public DrmSession drmSession;
    public Format format;

    public void clear() {
        this.drmSession = null;
        this.format = null;
    }
}
