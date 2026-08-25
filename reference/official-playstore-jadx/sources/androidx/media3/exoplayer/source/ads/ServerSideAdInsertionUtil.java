package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ServerSideAdInsertionUtil {
    private ServerSideAdInsertionUtil() {
    }

    public static AdPlaybackState addAdGroupToAdPlaybackState(AdPlaybackState adPlaybackState, long j10, long j11, long... jArr) {
        long mediaPeriodPositionUsForContent = getMediaPeriodPositionUsForContent(j10, -1, adPlaybackState);
        int i10 = adPlaybackState.removedAdGroupCount;
        while (i10 < adPlaybackState.adGroupCount && adPlaybackState.getAdGroup(i10).timeUs != Long.MIN_VALUE && adPlaybackState.getAdGroup(i10).timeUs <= mediaPeriodPositionUsForContent) {
            i10++;
        }
        AdPlaybackState adPlaybackStateWithContentResumeOffsetUs = adPlaybackState.withNewAdGroup(i10, mediaPeriodPositionUsForContent).withIsServerSideInserted(i10, true).withAdCount(i10, jArr.length).withAdDurationsUs(i10, jArr).withContentResumeOffsetUs(i10, j11);
        AdPlaybackState adPlaybackStateWithSkippedAd = adPlaybackStateWithContentResumeOffsetUs;
        for (int i11 = 0; i11 < jArr.length && jArr[i11] == 0; i11++) {
            adPlaybackStateWithSkippedAd = adPlaybackStateWithSkippedAd.withSkippedAd(i10, i11);
        }
        return correctFollowingAdGroupTimes(adPlaybackStateWithSkippedAd, i10, Util.sum(jArr), j11);
    }

    private static AdPlaybackState correctFollowingAdGroupTimes(AdPlaybackState adPlaybackState, int i10, long j10, long j11) {
        long j12 = (-j10) + j11;
        while (true) {
            i10++;
            if (i10 >= adPlaybackState.adGroupCount) {
                return adPlaybackState;
            }
            long j13 = adPlaybackState.getAdGroup(i10).timeUs;
            if (j13 != Long.MIN_VALUE) {
                adPlaybackState = adPlaybackState.withAdGroupTimeUs(i10, j13 + j12);
            }
        }
    }

    public static int getAdCountInGroup(AdPlaybackState adPlaybackState, int i10) {
        int i11 = adPlaybackState.getAdGroup(i10).count;
        if (i11 == -1) {
            return 0;
        }
        return i11;
    }

    public static long getMediaPeriodPositionUs(long j10, MediaSource.MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        return mediaPeriodId.isAd() ? getMediaPeriodPositionUsForAd(j10, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState) : getMediaPeriodPositionUsForContent(j10, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }

    public static long getMediaPeriodPositionUsForAd(long j10, int i10, int i11, AdPlaybackState adPlaybackState) {
        int i12;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
        long j11 = j10 - adGroup.timeUs;
        int i13 = adPlaybackState.removedAdGroupCount;
        while (true) {
            i12 = 0;
            if (i13 >= i10) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(i13);
            while (i12 < getAdCountInGroup(adPlaybackState, i13)) {
                j11 -= adGroup2.durationsUs[i12];
                i12++;
            }
            j11 += adGroup2.contentResumeOffsetUs;
            i13++;
        }
        if (i11 < getAdCountInGroup(adPlaybackState, i10)) {
            while (i12 < i11) {
                j11 -= adGroup.durationsUs[i12];
                i12++;
            }
        }
        return j11;
    }

    public static long getMediaPeriodPositionUsForContent(long j10, int i10, AdPlaybackState adPlaybackState) {
        if (i10 == -1) {
            i10 = adPlaybackState.adGroupCount;
        }
        long j11 = 0;
        for (int i11 = adPlaybackState.removedAdGroupCount; i11 < i10; i11++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i11);
            long j12 = adGroup.timeUs;
            if (j12 == Long.MIN_VALUE || j12 > j10 - j11) {
                break;
            }
            for (int i12 = 0; i12 < getAdCountInGroup(adPlaybackState, i11); i12++) {
                j11 += adGroup.durationsUs[i12];
            }
            long j13 = adGroup.contentResumeOffsetUs;
            j11 -= j13;
            long j14 = adGroup.timeUs;
            long j15 = j10 - j11;
            if (j13 + j14 > j15) {
                return Math.max(j14, j15);
            }
        }
        return j10 - j11;
    }

    public static long getStreamPositionUs(Player player, Object obj) {
        Timeline currentTimeline = player.getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return C.TIME_UNSET;
        }
        Timeline.Period period = currentTimeline.getPeriod(player.getCurrentPeriodIndex(), new Timeline.Period());
        if (!Objects.equals(period.getAdsId(), obj)) {
            return C.TIME_UNSET;
        }
        if (!player.isPlayingAd()) {
            return getStreamPositionUsForContent(Util.msToUs(player.getCurrentPosition()) - period.getPositionInWindowUs(), -1, period.adPlaybackState);
        }
        return getStreamPositionUsForAd(Util.msToUs(player.getCurrentPosition()), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), period.adPlaybackState);
    }

    public static long getStreamPositionUsForAd(long j10, int i10, int i11, AdPlaybackState adPlaybackState) {
        int i12;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
        long j11 = j10 + adGroup.timeUs;
        int i13 = adPlaybackState.removedAdGroupCount;
        while (true) {
            i12 = 0;
            if (i13 >= i10) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(i13);
            while (i12 < getAdCountInGroup(adPlaybackState, i13)) {
                j11 += adGroup2.durationsUs[i12];
                i12++;
            }
            j11 -= adGroup2.contentResumeOffsetUs;
            i13++;
        }
        if (i11 < getAdCountInGroup(adPlaybackState, i10)) {
            while (i12 < i11) {
                j11 += adGroup.durationsUs[i12];
                i12++;
            }
        }
        return j11;
    }

    public static long getStreamPositionUsForContent(long j10, int i10, AdPlaybackState adPlaybackState) {
        if (i10 == -1) {
            i10 = adPlaybackState.adGroupCount;
        }
        long j11 = 0;
        for (int i11 = adPlaybackState.removedAdGroupCount; i11 < i10; i11++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i11);
            long j12 = adGroup.timeUs;
            if (j12 == Long.MIN_VALUE || j12 > j10) {
                break;
            }
            long j13 = j12 + j11;
            for (int i12 = 0; i12 < getAdCountInGroup(adPlaybackState, i11); i12++) {
                j11 += adGroup.durationsUs[i12];
            }
            long j14 = adGroup.contentResumeOffsetUs;
            j11 -= j14;
            if (adGroup.timeUs + j14 > j10) {
                return Math.max(j13, j10 + j11);
            }
        }
        return j10 + j11;
    }

    public static long getStreamPositionUs(long j10, MediaSource.MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        if (mediaPeriodId.isAd()) {
            return getStreamPositionUsForAd(j10, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState);
        }
        return getStreamPositionUsForContent(j10, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }
}
