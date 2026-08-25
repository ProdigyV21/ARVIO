package androidx.media3.exoplayer.dash;

import androidx.media3.exoplayer.dash.manifest.RangedUri;

/* JADX INFO: loaded from: classes3.dex */
public interface DashSegmentIndex {
    public static final int INDEX_UNBOUNDED = -1;

    long getAvailableSegmentCount(long j10, long j11);

    long getDurationUs(long j10, long j11);

    long getFirstAvailableSegmentNum(long j10, long j11);

    long getFirstSegmentNum();

    long getNextSegmentAvailableTimeUs(long j10, long j11);

    long getSegmentCount(long j10);

    long getSegmentNum(long j10, long j11);

    RangedUri getSegmentUrl(long j10);

    long getTimeUs(long j10);

    boolean isExplicit();
}
