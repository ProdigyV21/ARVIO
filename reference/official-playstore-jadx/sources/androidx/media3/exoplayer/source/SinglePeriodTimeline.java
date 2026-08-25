package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;

/* JADX INFO: loaded from: classes3.dex */
public final class SinglePeriodTimeline extends Timeline {
    private final long elapsedRealtimeEpochOffsetMs;
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final MediaItem.LiveConfiguration liveConfiguration;
    private final Object manifest;
    private final MediaItem mediaItem;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final boolean suppressPositionProjection;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;
    private static final Object UID = new Object();
    private static final MediaItem MEDIA_ITEM = new MediaItem.Builder().setMediaId("SinglePeriodTimeline").setUri(Uri.EMPTY).build();

    @Deprecated
    public SinglePeriodTimeline(long j10, boolean z, boolean z5, boolean z10, Object obj, Object obj2) {
        this(j10, j10, 0L, 0L, z, z5, z10, obj, obj2);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object obj) {
        return UID.equals(obj) ? 0 : -1;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
        ac.b.n(i10, 1);
        return period.set(null, z ? UID : null, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return 1;
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int i10) {
        ac.b.n(i10, 1);
        return UID;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d A[PHI: r1
      0x002d: PHI (r1v2 long) = (r1v1 long), (r1v1 long), (r1v1 long), (r1v6 long) binds: [B:3:0x000c, B:5:0x0010, B:7:0x0016, B:12:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.media3.common.Timeline
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.media3.common.Timeline.Window getWindow(int r25, androidx.media3.common.Timeline.Window r26, long r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = 1
            r2 = r25
            ac.b.n(r2, r1)
            long r1 = r0.windowDefaultStartPositionUs
            boolean r14 = r0.isDynamic
            if (r14 == 0) goto L2d
            boolean r3 = r0.suppressPositionProjection
            if (r3 != 0) goto L2d
            r3 = 0
            int r3 = (r27 > r3 ? 1 : (r27 == r3 ? 0 : -1))
            if (r3 == 0) goto L2d
            long r3 = r0.windowDurationUs
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L26
        L23:
            r16 = r5
            goto L2f
        L26:
            long r1 = r1 + r27
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 <= 0) goto L2d
            goto L23
        L2d:
            r16 = r1
        L2f:
            java.lang.Object r4 = androidx.media3.common.Timeline.Window.SINGLE_WINDOW_UID
            androidx.media3.common.MediaItem r5 = r0.mediaItem
            java.lang.Object r6 = r0.manifest
            long r7 = r0.presentationStartTimeMs
            long r9 = r0.windowStartTimeMs
            long r11 = r0.elapsedRealtimeEpochOffsetMs
            boolean r13 = r0.isSeekable
            androidx.media3.common.MediaItem$LiveConfiguration r15 = r0.liveConfiguration
            long r1 = r0.windowDurationUs
            r21 = 0
            r18 = r1
            long r1 = r0.windowPositionInPeriodUs
            r20 = 0
            r3 = r26
            r22 = r1
            androidx.media3.common.Timeline$Window r1 = r3.set(r4, r5, r6, r7, r9, r11, r13, r14, r15, r16, r18, r20, r21, r22)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SinglePeriodTimeline.getWindow(int, androidx.media3.common.Timeline$Window, long):androidx.media3.common.Timeline$Window");
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j10, boolean z, boolean z5, boolean z10, Object obj, MediaItem mediaItem) {
        this(j10, j10, 0L, 0L, z, z5, z10, obj, mediaItem);
    }

    @Deprecated
    public SinglePeriodTimeline(long j10, long j11, long j12, long j13, boolean z, boolean z5, boolean z10, Object obj, Object obj2) {
        this(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, j10, j11, j12, j13, z, z5, z10, obj, obj2);
    }

    public SinglePeriodTimeline(long j10, long j11, long j12, long j13, boolean z, boolean z5, boolean z10, Object obj, MediaItem mediaItem) {
        this(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, j10, j11, j12, j13, z, z5, false, obj, mediaItem, z10 ? mediaItem.liveConfiguration : null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public SinglePeriodTimeline(long j10, long j11, long j12, long j13, long j14, long j15, long j16, boolean z, boolean z5, boolean z10, Object obj, Object obj2) {
        MediaItem mediaItem = MEDIA_ITEM;
        this(j10, j11, j12, j13, j14, j15, j16, z, z5, false, obj, mediaItem.buildUpon().setTag(obj2).build(), z10 ? mediaItem.liveConfiguration : null);
    }

    @Deprecated
    public SinglePeriodTimeline(long j10, long j11, long j12, long j13, long j14, long j15, long j16, boolean z, boolean z5, Object obj, MediaItem mediaItem, MediaItem.LiveConfiguration liveConfiguration) {
        this(j10, j11, j12, j13, j14, j15, j16, z, z5, false, obj, mediaItem, liveConfiguration);
    }

    public SinglePeriodTimeline(long j10, long j11, long j12, long j13, long j14, long j15, long j16, boolean z, boolean z5, boolean z10, Object obj, MediaItem mediaItem, MediaItem.LiveConfiguration liveConfiguration) {
        this.presentationStartTimeMs = j10;
        this.windowStartTimeMs = j11;
        this.elapsedRealtimeEpochOffsetMs = j12;
        this.periodDurationUs = j13;
        this.windowDurationUs = j14;
        this.windowPositionInPeriodUs = j15;
        this.windowDefaultStartPositionUs = j16;
        this.isSeekable = z;
        this.isDynamic = z5;
        this.suppressPositionProjection = z10;
        this.manifest = obj;
        mediaItem.getClass();
        this.mediaItem = mediaItem;
        this.liveConfiguration = liveConfiguration;
    }
}
