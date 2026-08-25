package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final class SessionPositionInfo {
    public static final SessionPositionInfo DEFAULT;
    public static final Player.PositionInfo DEFAULT_POSITION_INFO;
    private static final String FIELD_BUFFERED_PERCENTAGE;
    static final String FIELD_BUFFERED_POSITION_MS;
    static final String FIELD_CONTENT_BUFFERED_POSITION_MS;
    private static final String FIELD_CONTENT_DURATION_MS;
    private static final String FIELD_CURRENT_LIVE_OFFSET_MS;
    private static final String FIELD_DURATION_MS;
    private static final String FIELD_EVENT_TIME_MS;
    private static final String FIELD_IS_PLAYING_AD;
    static final String FIELD_POSITION_INFO;
    private static final String FIELD_TOTAL_BUFFERED_DURATION_MS;
    public final int bufferedPercentage;
    public final long bufferedPositionMs;
    public final long contentBufferedPositionMs;
    public final long contentDurationMs;
    public final long currentLiveOffsetMs;
    public final long durationMs;
    public final long eventTimeMs;
    public final boolean isPlayingAd;
    public final Player.PositionInfo positionInfo;
    public final long totalBufferedDurationMs;

    static {
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, 0, null, null, 0, 0L, 0L, -1, -1);
        DEFAULT_POSITION_INFO = positionInfo;
        DEFAULT = new SessionPositionInfo(positionInfo, false, C.TIME_UNSET, C.TIME_UNSET, 0L, 0, 0L, C.TIME_UNSET, C.TIME_UNSET, 0L);
        FIELD_POSITION_INFO = Util.intToStringMaxRadix(0);
        FIELD_IS_PLAYING_AD = Util.intToStringMaxRadix(1);
        FIELD_EVENT_TIME_MS = Util.intToStringMaxRadix(2);
        FIELD_DURATION_MS = Util.intToStringMaxRadix(3);
        FIELD_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(4);
        FIELD_BUFFERED_PERCENTAGE = Util.intToStringMaxRadix(5);
        FIELD_TOTAL_BUFFERED_DURATION_MS = Util.intToStringMaxRadix(6);
        FIELD_CURRENT_LIVE_OFFSET_MS = Util.intToStringMaxRadix(7);
        FIELD_CONTENT_DURATION_MS = Util.intToStringMaxRadix(8);
        FIELD_CONTENT_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(9);
    }

    public SessionPositionInfo(Player.PositionInfo positionInfo, boolean z, long j10, long j11, long j12, int i10, long j13, long j14, long j15, long j16) {
        ac.b.j(z == (positionInfo.adGroupIndex != -1));
        this.positionInfo = positionInfo;
        this.isPlayingAd = z;
        this.eventTimeMs = j10;
        this.durationMs = j11;
        this.bufferedPositionMs = j12;
        this.bufferedPercentage = i10;
        this.totalBufferedDurationMs = j13;
        this.currentLiveOffsetMs = j14;
        this.contentDurationMs = j15;
        this.contentBufferedPositionMs = j16;
    }

    public static SessionPositionInfo fromBundle(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(FIELD_POSITION_INFO);
        return new SessionPositionInfo(bundle2 == null ? DEFAULT_POSITION_INFO : Player.PositionInfo.fromBundle(bundle2), bundle.getBoolean(FIELD_IS_PLAYING_AD, false), bundle.getLong(FIELD_EVENT_TIME_MS, C.TIME_UNSET), bundle.getLong(FIELD_DURATION_MS, C.TIME_UNSET), bundle.getLong(FIELD_BUFFERED_POSITION_MS, 0L), bundle.getInt(FIELD_BUFFERED_PERCENTAGE, 0), bundle.getLong(FIELD_TOTAL_BUFFERED_DURATION_MS, 0L), bundle.getLong(FIELD_CURRENT_LIVE_OFFSET_MS, C.TIME_UNSET), bundle.getLong(FIELD_CONTENT_DURATION_MS, C.TIME_UNSET), bundle.getLong(FIELD_CONTENT_BUFFERED_POSITION_MS, 0L));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SessionPositionInfo.class == obj.getClass()) {
            SessionPositionInfo sessionPositionInfo = (SessionPositionInfo) obj;
            if (this.eventTimeMs == sessionPositionInfo.eventTimeMs && this.positionInfo.equals(sessionPositionInfo.positionInfo) && this.isPlayingAd == sessionPositionInfo.isPlayingAd && this.durationMs == sessionPositionInfo.durationMs && this.bufferedPositionMs == sessionPositionInfo.bufferedPositionMs && this.bufferedPercentage == sessionPositionInfo.bufferedPercentage && this.totalBufferedDurationMs == sessionPositionInfo.totalBufferedDurationMs && this.currentLiveOffsetMs == sessionPositionInfo.currentLiveOffsetMs && this.contentDurationMs == sessionPositionInfo.contentDurationMs && this.contentBufferedPositionMs == sessionPositionInfo.contentBufferedPositionMs) {
                return true;
            }
        }
        return false;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public androidx.media3.session.SessionPositionInfo filterByAvailableCommands(boolean r24, boolean r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            if (r1 == 0) goto Lb
            if (r2 == 0) goto Lb
            return r0
        Lb:
            androidx.media3.session.SessionPositionInfo r3 = new androidx.media3.session.SessionPositionInfo
            androidx.media3.common.Player$PositionInfo r4 = r0.positionInfo
            androidx.media3.common.Player$PositionInfo r2 = r4.filterByAvailableCommands(r1, r2)
            r4 = 0
            if (r1 == 0) goto L1c
            boolean r5 = r0.isPlayingAd
            if (r5 == 0) goto L1c
            r5 = 1
            goto L1d
        L1c:
            r5 = r4
        L1d:
            long r6 = r0.eventTimeMs
            if (r1 == 0) goto L24
            long r10 = r0.durationMs
            goto L29
        L24:
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L29:
            if (r1 == 0) goto L2e
            long r14 = r0.bufferedPositionMs
            goto L30
        L2e:
            r14 = 0
        L30:
            if (r1 == 0) goto L34
            int r4 = r0.bufferedPercentage
        L34:
            if (r1 == 0) goto L39
            long r8 = r0.totalBufferedDurationMs
            goto L3b
        L39:
            r8 = 0
        L3b:
            if (r1 == 0) goto L40
            long r12 = r0.currentLiveOffsetMs
            goto L45
        L40:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L45:
            r25 = r2
            if (r1 == 0) goto L4e
            long r1 = r0.contentDurationMs
            r16 = r1
            goto L53
        L4e:
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L53:
            if (r24 == 0) goto L70
            long r1 = r0.contentBufferedPositionMs
            r19 = r1
            r1 = r3
            r3 = r5
            r21 = r10
            r10 = r4
            r4 = r6
            r6 = r21
            r21 = r16
            r17 = r19
            r19 = r12
            r11 = r8
            r8 = r14
            r15 = r21
        L6b:
            r2 = r25
            r13 = r19
            goto L81
        L70:
            r1 = r3
            r3 = r5
            r19 = r10
            r10 = r4
            r4 = r6
            r6 = r19
            r19 = r12
            r11 = r8
            r8 = r14
            r15 = r16
            r17 = 0
            goto L6b
        L81:
            r1.<init>(r2, r3, r4, r6, r8, r10, r11, r13, r15, r17)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.SessionPositionInfo.filterByAvailableCommands(boolean, boolean):androidx.media3.session.SessionPositionInfo");
    }

    public int hashCode() {
        return Objects.hash(this.positionInfo, Boolean.valueOf(this.isPlayingAd));
    }

    public Bundle toBundle(int i10) {
        Bundle bundle = new Bundle();
        if (i10 < 3 || !DEFAULT_POSITION_INFO.equalsForBundling(this.positionInfo)) {
            bundle.putBundle(FIELD_POSITION_INFO, this.positionInfo.toBundle(i10));
        }
        boolean z = this.isPlayingAd;
        if (z) {
            bundle.putBoolean(FIELD_IS_PLAYING_AD, z);
        }
        long j10 = this.eventTimeMs;
        if (j10 != C.TIME_UNSET) {
            bundle.putLong(FIELD_EVENT_TIME_MS, j10);
        }
        long j11 = this.durationMs;
        if (j11 != C.TIME_UNSET) {
            bundle.putLong(FIELD_DURATION_MS, j11);
        }
        if (i10 < 3 || this.bufferedPositionMs != 0) {
            bundle.putLong(FIELD_BUFFERED_POSITION_MS, this.bufferedPositionMs);
        }
        int i11 = this.bufferedPercentage;
        if (i11 != 0) {
            bundle.putInt(FIELD_BUFFERED_PERCENTAGE, i11);
        }
        long j12 = this.totalBufferedDurationMs;
        if (j12 != 0) {
            bundle.putLong(FIELD_TOTAL_BUFFERED_DURATION_MS, j12);
        }
        long j13 = this.currentLiveOffsetMs;
        if (j13 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CURRENT_LIVE_OFFSET_MS, j13);
        }
        long j14 = this.contentDurationMs;
        if (j14 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CONTENT_DURATION_MS, j14);
        }
        if (i10 >= 3 && this.contentBufferedPositionMs == 0) {
            return bundle;
        }
        bundle.putLong(FIELD_CONTENT_BUFFERED_POSITION_MS, this.contentBufferedPositionMs);
        return bundle;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SessionPositionInfo {PositionInfo {mediaItemIndex=");
        sb2.append(this.positionInfo.mediaItemIndex);
        sb2.append(", periodIndex=");
        sb2.append(this.positionInfo.periodIndex);
        sb2.append(", positionMs=");
        sb2.append(this.positionInfo.positionMs);
        sb2.append(", contentPositionMs=");
        sb2.append(this.positionInfo.contentPositionMs);
        sb2.append(", adGroupIndex=");
        sb2.append(this.positionInfo.adGroupIndex);
        sb2.append(", adIndexInAdGroup=");
        sb2.append(this.positionInfo.adIndexInAdGroup);
        sb2.append("}, isPlayingAd=");
        sb2.append(this.isPlayingAd);
        sb2.append(", eventTimeMs=");
        sb2.append(this.eventTimeMs);
        sb2.append(", durationMs=");
        sb2.append(this.durationMs);
        sb2.append(", bufferedPositionMs=");
        sb2.append(this.bufferedPositionMs);
        sb2.append(", bufferedPercentage=");
        sb2.append(this.bufferedPercentage);
        sb2.append(", totalBufferedDurationMs=");
        sb2.append(this.totalBufferedDurationMs);
        sb2.append(", currentLiveOffsetMs=");
        sb2.append(this.currentLiveOffsetMs);
        sb2.append(", contentDurationMs=");
        sb2.append(this.contentDurationMs);
        sb2.append(", contentBufferedPositionMs=");
        return a0.c.j(this.contentBufferedPositionMs, "}", sb2);
    }
}
