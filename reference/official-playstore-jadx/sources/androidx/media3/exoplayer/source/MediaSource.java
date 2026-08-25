package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.c0;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaSource {

    public interface Factory {
        public static final Factory UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;

        MediaSource createMediaSource(MediaItem mediaItem);

        @Deprecated
        Factory experimentalParseSubtitlesDuringExtraction(boolean z);

        Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10);

        int[] getSupportedTypes();

        Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory);

        Factory setDownloadExecutor(c0<ReleasableExecutor> c0Var);

        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider);

        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy);

        Factory setSubtitleParserFactory(SubtitleParser.Factory factory);
    }

    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int nextAdGroupIndex;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object obj) {
            this(obj, -1L);
        }

        public MediaPeriodId copyWithPeriodUid(Object obj) {
            return this.periodUid.equals(obj) ? this : new MediaPeriodId(obj, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
        }

        public MediaPeriodId copyWithWindowSequenceNumber(long j10) {
            return this.windowSequenceNumber == j10 ? this : new MediaPeriodId(this.periodUid, this.adGroupIndex, this.adIndexInAdGroup, j10, this.nextAdGroupIndex);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodId)) {
                return false;
            }
            MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
            return this.periodUid.equals(mediaPeriodId.periodUid) && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber && this.nextAdGroupIndex == mediaPeriodId.nextAdGroupIndex;
        }

        public int hashCode() {
            return ((((((((this.periodUid.hashCode() + 527) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
        }

        public boolean isAd() {
            return this.adGroupIndex != -1;
        }

        public MediaPeriodId(Object obj, long j10) {
            this(obj, -1, -1, j10, -1);
        }

        public MediaPeriodId(Object obj, long j10, int i10) {
            this(obj, -1, -1, j10, i10);
        }

        public MediaPeriodId(Object obj, int i10, int i11, long j10) {
            this(obj, i10, i11, j10, -1);
        }

        private MediaPeriodId(Object obj, int i10, int i11, long j10, int i12) {
            this.periodUid = obj;
            this.adGroupIndex = i10;
            this.adIndexInAdGroup = i11;
            this.windowSequenceNumber = j10;
            this.nextAdGroupIndex = i12;
        }
    }

    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline);
    }

    void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener);

    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    boolean canUpdateMediaItem(MediaItem mediaItem);

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j10);

    void disable(MediaSourceCaller mediaSourceCaller);

    void enable(MediaSourceCaller mediaSourceCaller);

    Timeline getInitialTimeline();

    MediaItem getMediaItem();

    boolean isSingleWindow();

    void maybeThrowSourceInfoRefreshError() throws IOException;

    @Deprecated
    void prepareSource(MediaSourceCaller mediaSourceCaller, TransferListener transferListener);

    void prepareSource(MediaSourceCaller mediaSourceCaller, TransferListener transferListener, PlayerId playerId);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource(MediaSourceCaller mediaSourceCaller);

    void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener);

    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);

    void updateMediaItem(MediaItem mediaItem);
}
