package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.Format;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaLoadData {
    public final int dataType;
    public final long mediaEndTimeMs;
    public final long mediaStartTimeMs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int trackType;

    public MediaLoadData(int i10) {
        this(i10, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
    }

    public MediaLoadData(int i10, int i11, Format format, int i12, Object obj, long j10, long j11) {
        this.dataType = i10;
        this.trackType = i11;
        this.trackFormat = format;
        this.trackSelectionReason = i12;
        this.trackSelectionData = obj;
        this.mediaStartTimeMs = j10;
        this.mediaEndTimeMs = j11;
    }
}
