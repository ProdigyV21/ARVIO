package androidx.media3.exoplayer;

import androidx.media3.exoplayer.source.MediaSource;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final class MediaPeriodInfo {
    public final long durationUs;
    public final long endPositionUs;
    public final MediaSource.MediaPeriodId id;
    public final boolean isFinal;
    public final boolean isFollowedByTransitionToSameStream;
    public final boolean isLastInTimelinePeriod;
    public final boolean isLastInTimelineWindow;
    public final boolean isPrecededByTransitionFromSameStream;
    public final long requestedContentPositionUs;
    public final long startPositionUs;

    public MediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j10, long j11, long j12, long j13, boolean z, boolean z5, boolean z10, boolean z11, boolean z12) {
        boolean z13 = true;
        ac.b.j(!z12 || z10);
        ac.b.j(!z11 || z10);
        if (z5 && (z10 || z11 || z12)) {
            z13 = false;
        }
        ac.b.j(z13);
        this.id = mediaPeriodId;
        this.startPositionUs = j10;
        this.requestedContentPositionUs = j11;
        this.endPositionUs = j12;
        this.durationUs = j13;
        this.isPrecededByTransitionFromSameStream = z;
        this.isFollowedByTransitionToSameStream = z5;
        this.isLastInTimelinePeriod = z10;
        this.isLastInTimelineWindow = z11;
        this.isFinal = z12;
    }

    public MediaPeriodInfo copyWithRequestedContentPositionUs(long j10) {
        return j10 == this.requestedContentPositionUs ? this : new MediaPeriodInfo(this.id, this.startPositionUs, j10, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public MediaPeriodInfo copyWithStartPositionUs(long j10) {
        return j10 == this.startPositionUs ? this : new MediaPeriodInfo(this.id, j10, this.requestedContentPositionUs, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaPeriodInfo.class == obj.getClass()) {
            MediaPeriodInfo mediaPeriodInfo = (MediaPeriodInfo) obj;
            if (this.startPositionUs == mediaPeriodInfo.startPositionUs && this.requestedContentPositionUs == mediaPeriodInfo.requestedContentPositionUs && this.endPositionUs == mediaPeriodInfo.endPositionUs && this.durationUs == mediaPeriodInfo.durationUs && this.isPrecededByTransitionFromSameStream == mediaPeriodInfo.isPrecededByTransitionFromSameStream && this.isFollowedByTransitionToSameStream == mediaPeriodInfo.isFollowedByTransitionToSameStream && this.isLastInTimelinePeriod == mediaPeriodInfo.isLastInTimelinePeriod && this.isLastInTimelineWindow == mediaPeriodInfo.isLastInTimelineWindow && this.isFinal == mediaPeriodInfo.isFinal && Objects.equals(this.id, mediaPeriodInfo.id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((((this.id.hashCode() + 527) * 31) + ((int) this.startPositionUs)) * 31) + ((int) this.requestedContentPositionUs)) * 31) + ((int) this.endPositionUs)) * 31) + ((int) this.durationUs)) * 31) + (this.isPrecededByTransitionFromSameStream ? 1 : 0)) * 31) + (this.isFollowedByTransitionToSameStream ? 1 : 0)) * 31) + (this.isLastInTimelinePeriod ? 1 : 0)) * 31) + (this.isLastInTimelineWindow ? 1 : 0)) * 31) + (this.isFinal ? 1 : 0);
    }
}
