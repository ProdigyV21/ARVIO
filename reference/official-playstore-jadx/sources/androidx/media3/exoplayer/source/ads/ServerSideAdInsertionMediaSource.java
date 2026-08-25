package androidx.media3.exoplayer.source.ads;

import android.os.Handler;
import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.StreamKey;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.exoplayer.drm.f;
import androidx.media3.exoplayer.source.BaseMediaSource;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.ForwardingTimeline;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.a0;
import com.google.common.collect.g2;
import com.google.common.collect.l1;
import com.google.common.collect.o4;
import com.google.common.collect.t3;
import com.google.common.collect.v;
import com.google.common.collect.w1;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ServerSideAdInsertionMediaSource extends BaseMediaSource implements MediaSource.MediaSourceCaller, MediaSourceEventListener, DrmSessionEventListener {
    private final AdPlaybackStateUpdater adPlaybackStateUpdater;
    private SharedMediaPeriod lastUsedMediaPeriod;
    private final MediaSource mediaSource;
    private Handler playbackHandler;
    private final g2<Pair<Long, Object>, SharedMediaPeriod> mediaPeriods = new v();
    private l1 adPlaybackStates = t3.f14114q;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcherWithoutId = createEventDispatcher(null);
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcherWithoutId = createDrmEventDispatcher(null);

    public interface AdPlaybackStateUpdater {
        boolean onAdPlaybackStateUpdateRequested(Timeline timeline);
    }

    public static final class MediaPeriodImpl implements MediaPeriod {
        public MediaPeriod.Callback callback;
        public final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
        public boolean[] hasNotifiedDownstreamFormatChange = new boolean[0];
        public boolean isPrepared;
        public long lastStartPositionUs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
        public final SharedMediaPeriod sharedPeriod;

        public MediaPeriodImpl(SharedMediaPeriod sharedMediaPeriod, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
            this.sharedPeriod = sharedMediaPeriod;
            this.mediaPeriodId = mediaPeriodId;
            this.mediaSourceEventDispatcher = eventDispatcher;
            this.drmEventDispatcher = eventDispatcher2;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(LoadingInfo loadingInfo) {
            return this.sharedPeriod.continueLoading(this, loadingInfo);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void discardBuffer(long j10, boolean z) {
            this.sharedPeriod.discardBuffer(this, j10, z);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
            return this.sharedPeriod.getAdjustedSeekPositionUs(this, j10, seekParameters);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            return this.sharedPeriod.getBufferedPositionUs(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            return this.sharedPeriod.getNextLoadPositionUs(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.sharedPeriod.getStreamKeys(list);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public TrackGroupArray getTrackGroups() {
            return this.sharedPeriod.getTrackGroups();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return this.sharedPeriod.isLoading(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void maybeThrowPrepareError() throws IOException {
            this.sharedPeriod.maybeThrowPrepareError();
        }

        public void onPrepared() {
            MediaPeriod.Callback callback = this.callback;
            if (callback != null) {
                callback.onPrepared(this);
            }
            this.isPrepared = true;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void prepare(MediaPeriod.Callback callback, long j10) {
            this.callback = callback;
            this.sharedPeriod.prepare(this, j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long readDiscontinuity() {
            return this.sharedPeriod.readDiscontinuity(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long j10) {
            this.sharedPeriod.reevaluateBuffer(this, j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long seekToUs(long j10) {
            return this.sharedPeriod.seekToUs(this, j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
            if (this.hasNotifiedDownstreamFormatChange.length == 0) {
                this.hasNotifiedDownstreamFormatChange = new boolean[sampleStreamArr.length];
            }
            return this.sharedPeriod.selectTracks(this, exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j10);
        }
    }

    public static final class SampleStreamImpl implements SampleStream {
        private final MediaPeriodImpl mediaPeriod;
        private final int streamIndex;

        public SampleStreamImpl(MediaPeriodImpl mediaPeriodImpl, int i10) {
            this.mediaPeriod = mediaPeriodImpl;
            this.streamIndex = i10;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.mediaPeriod.sharedPeriod.isReady(this.streamIndex);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.mediaPeriod.sharedPeriod.maybeThrowError(this.streamIndex);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
            MediaPeriodImpl mediaPeriodImpl = this.mediaPeriod;
            return mediaPeriodImpl.sharedPeriod.readData(mediaPeriodImpl, this.streamIndex, formatHolder, decoderInputBuffer, i10);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j10) {
            MediaPeriodImpl mediaPeriodImpl = this.mediaPeriod;
            return mediaPeriodImpl.sharedPeriod.skipData(mediaPeriodImpl, this.streamIndex, j10);
        }
    }

    public static final class ServerSideAdInsertionTimeline extends ForwardingTimeline {
        private final l1 adPlaybackStates;

        public ServerSideAdInsertionTimeline(Timeline timeline, l1 l1Var) {
            super(timeline);
            ac.b.s(timeline.getWindowCount() == 1);
            Timeline.Period period = new Timeline.Period();
            for (int i10 = 0; i10 < timeline.getPeriodCount(); i10++) {
                timeline.getPeriod(i10, period, true);
                Object obj = period.uid;
                obj.getClass();
                ac.b.s(l1Var.containsKey(obj));
            }
            this.adPlaybackStates = l1Var;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            super.getPeriod(i10, period, true);
            AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(period.uid);
            adPlaybackState.getClass();
            long j10 = period.durationUs;
            long mediaPeriodPositionUsForContent = j10 == C.TIME_UNSET ? adPlaybackState.contentDurationUs : ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(j10, -1, adPlaybackState);
            Timeline.Period period2 = new Timeline.Period();
            long mediaPeriodPositionUsForContent2 = 0;
            for (int i11 = 0; i11 < i10 + 1; i11++) {
                this.timeline.getPeriod(i11, period2, true);
                AdPlaybackState adPlaybackState2 = (AdPlaybackState) this.adPlaybackStates.get(period2.uid);
                adPlaybackState2.getClass();
                if (i11 == 0) {
                    mediaPeriodPositionUsForContent2 = -ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(-period2.getPositionInWindowUs(), -1, adPlaybackState2);
                }
                if (i11 != i10) {
                    mediaPeriodPositionUsForContent2 = ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(period2.durationUs, -1, adPlaybackState2) + mediaPeriodPositionUsForContent2;
                }
            }
            period.set(period.id, period.uid, period.windowIndex, mediaPeriodPositionUsForContent, mediaPeriodPositionUsForContent2, adPlaybackState, period.isPlaceholder);
            return period;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            super.getWindow(i10, window, j10);
            Timeline.Period period = new Timeline.Period();
            Object obj = getPeriod(window.firstPeriodIndex, period, true).uid;
            obj.getClass();
            AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(obj);
            adPlaybackState.getClass();
            long mediaPeriodPositionUsForContent = ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(window.positionInFirstPeriodUs, -1, adPlaybackState);
            if (window.durationUs == C.TIME_UNSET) {
                long j11 = adPlaybackState.contentDurationUs;
                if (j11 != C.TIME_UNSET) {
                    window.durationUs = j11 - mediaPeriodPositionUsForContent;
                }
            } else {
                Timeline.Period period2 = super.getPeriod(window.lastPeriodIndex, period, true);
                long j12 = period2.positionInWindowUs;
                AdPlaybackState adPlaybackState2 = (AdPlaybackState) this.adPlaybackStates.get(period2.uid);
                adPlaybackState2.getClass();
                Timeline.Period period3 = getPeriod(window.lastPeriodIndex, period);
                window.durationUs = period3.positionInWindowUs + ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(window.durationUs - j12, -1, adPlaybackState2);
            }
            window.positionInFirstPeriodUs = mediaPeriodPositionUsForContent;
            return window;
        }
    }

    public static final class SharedMediaPeriod implements MediaPeriod.Callback {
        private final MediaPeriod actualMediaPeriod;
        private AdPlaybackState adPlaybackState;
        private boolean hasStartedPreparing;
        private boolean isPrepared;
        private MediaPeriodImpl loadingPeriod;
        private final Object periodUid;
        private final List<MediaPeriodImpl> mediaPeriods = new ArrayList();
        private final Map<Long, Pair<LoadEventInfo, MediaLoadData>> activeLoads = new HashMap();
        public ExoTrackSelection[] trackSelections = new ExoTrackSelection[0];
        public SampleStream[] sampleStreams = new SampleStream[0];
        public MediaLoadData[] lastDownstreamFormatChangeData = new MediaLoadData[0];

        public SharedMediaPeriod(MediaPeriod mediaPeriod, Object obj, AdPlaybackState adPlaybackState) {
            this.actualMediaPeriod = mediaPeriod;
            this.periodUid = obj;
            this.adPlaybackState = adPlaybackState;
        }

        private int findMatchingStreamIndex(MediaLoadData mediaLoadData) {
            String str;
            if (mediaLoadData.trackFormat == null) {
                return -1;
            }
            int i10 = 0;
            loop0: while (true) {
                ExoTrackSelection[] exoTrackSelectionArr = this.trackSelections;
                if (i10 >= exoTrackSelectionArr.length) {
                    return -1;
                }
                ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i10];
                if (exoTrackSelection != null) {
                    TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
                    boolean z = mediaLoadData.trackType == 0 && trackGroup.equals(getTrackGroups().get(0));
                    for (int i11 = 0; i11 < trackGroup.length; i11++) {
                        Format format = trackGroup.getFormat(i11);
                        if (format.equals(mediaLoadData.trackFormat) || (z && (str = format.id) != null && str.equals(mediaLoadData.trackFormat.id))) {
                            break loop0;
                        }
                    }
                }
                i10++;
            }
            return i10;
        }

        private long getMediaPeriodPositionUsWithEndOfSourceHandling(MediaPeriodImpl mediaPeriodImpl, long j10) {
            if (j10 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            long mediaPeriodPositionUs = ServerSideAdInsertionUtil.getMediaPeriodPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
            if (mediaPeriodPositionUs >= ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState)) {
                return Long.MIN_VALUE;
            }
            return mediaPeriodPositionUs;
        }

        private long getStreamPositionUsWithNotYetStartedHandling(MediaPeriodImpl mediaPeriodImpl, long j10) {
            long j11 = mediaPeriodImpl.lastStartPositionUs;
            return j10 < j11 ? ServerSideAdInsertionUtil.getStreamPositionUs(j11, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState) - (mediaPeriodImpl.lastStartPositionUs - j10) : ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        private void maybeNotifyDownstreamFormatChanged(MediaPeriodImpl mediaPeriodImpl, int i10) {
            MediaLoadData mediaLoadData;
            boolean[] zArr = mediaPeriodImpl.hasNotifiedDownstreamFormatChange;
            if (zArr[i10] || (mediaLoadData = this.lastDownstreamFormatChangeData[i10]) == null) {
                return;
            }
            zArr[i10] = true;
            mediaPeriodImpl.mediaSourceEventDispatcher.downstreamFormatChanged(ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl, mediaLoadData, this.adPlaybackState));
        }

        public void add(MediaPeriodImpl mediaPeriodImpl) {
            this.mediaPeriods.add(mediaPeriodImpl);
        }

        public boolean canReuseMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, long j10) {
            MediaPeriodImpl mediaPeriodImpl = (MediaPeriodImpl) a0.j(this.mediaPeriods);
            return ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodId, this.adPlaybackState) == ServerSideAdInsertionUtil.getStreamPositionUs(ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public boolean continueLoading(MediaPeriodImpl mediaPeriodImpl, LoadingInfo loadingInfo) {
            MediaPeriodImpl mediaPeriodImpl2 = this.loadingPeriod;
            if (mediaPeriodImpl2 != null && !mediaPeriodImpl.equals(mediaPeriodImpl2)) {
                for (Pair<LoadEventInfo, MediaLoadData> pair : this.activeLoads.values()) {
                    mediaPeriodImpl2.mediaSourceEventDispatcher.loadCompleted((LoadEventInfo) pair.first, ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl2, (MediaLoadData) pair.second, this.adPlaybackState));
                    mediaPeriodImpl.mediaSourceEventDispatcher.loadStarted((LoadEventInfo) pair.first, ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl, (MediaLoadData) pair.second, this.adPlaybackState), 0);
                }
            }
            this.loadingPeriod = mediaPeriodImpl;
            return this.actualMediaPeriod.continueLoading(loadingInfo.buildUpon().setPlaybackPositionUs(getStreamPositionUsWithNotYetStartedHandling(mediaPeriodImpl, loadingInfo.playbackPositionUs)).build());
        }

        public void discardBuffer(MediaPeriodImpl mediaPeriodImpl, long j10, boolean z) {
            this.actualMediaPeriod.discardBuffer(ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState), z);
        }

        public long getAdjustedSeekPositionUs(MediaPeriodImpl mediaPeriodImpl, long j10, SeekParameters seekParameters) {
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(this.actualMediaPeriod.getAdjustedSeekPositionUs(ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState), seekParameters), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public long getBufferedPositionUs(MediaPeriodImpl mediaPeriodImpl) {
            return getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, this.actualMediaPeriod.getBufferedPositionUs());
        }

        public MediaPeriodImpl getMediaPeriodForEvent(MediaLoadData mediaLoadData) {
            if (mediaLoadData == null || mediaLoadData.mediaStartTimeMs == C.TIME_UNSET) {
                return null;
            }
            for (int i10 = 0; i10 < this.mediaPeriods.size(); i10++) {
                MediaPeriodImpl mediaPeriodImpl = this.mediaPeriods.get(i10);
                if (mediaPeriodImpl.isPrepared) {
                    long mediaPeriodPositionUs = ServerSideAdInsertionUtil.getMediaPeriodPositionUs(Util.msToUs(mediaLoadData.mediaStartTimeMs), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
                    long mediaPeriodEndPositionUs = ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState);
                    if (mediaPeriodPositionUs >= 0 && mediaPeriodPositionUs < mediaPeriodEndPositionUs) {
                        return mediaPeriodImpl;
                    }
                }
            }
            return null;
        }

        public long getNextLoadPositionUs(MediaPeriodImpl mediaPeriodImpl) {
            return getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, this.actualMediaPeriod.getNextLoadPositionUs());
        }

        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.actualMediaPeriod.getStreamKeys(list);
        }

        public TrackGroupArray getTrackGroups() {
            return this.actualMediaPeriod.getTrackGroups();
        }

        public boolean isLoading(MediaPeriodImpl mediaPeriodImpl) {
            return mediaPeriodImpl.equals(this.loadingPeriod) && this.actualMediaPeriod.isLoading();
        }

        public boolean isReady(int i10) {
            return ((SampleStream) Util.castNonNull(this.sampleStreams[i10])).isReady();
        }

        public boolean isUnused() {
            return this.mediaPeriods.isEmpty();
        }

        public void maybeThrowError(int i10) throws IOException {
            ((SampleStream) Util.castNonNull(this.sampleStreams[i10])).maybeThrowError();
        }

        public void maybeThrowPrepareError() throws IOException {
            this.actualMediaPeriod.maybeThrowPrepareError();
        }

        public void onDownstreamFormatChanged(MediaPeriodImpl mediaPeriodImpl, MediaLoadData mediaLoadData) {
            int iFindMatchingStreamIndex = findMatchingStreamIndex(mediaLoadData);
            if (iFindMatchingStreamIndex != -1) {
                this.lastDownstreamFormatChangeData[iFindMatchingStreamIndex] = mediaLoadData;
                mediaPeriodImpl.hasNotifiedDownstreamFormatChange[iFindMatchingStreamIndex] = true;
            }
        }

        public void onLoadFinished(LoadEventInfo loadEventInfo) {
            this.activeLoads.remove(Long.valueOf(loadEventInfo.loadTaskId));
        }

        public void onLoadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            this.activeLoads.put(Long.valueOf(loadEventInfo.loadTaskId), Pair.create(loadEventInfo, mediaLoadData));
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.isPrepared = true;
            for (int i10 = 0; i10 < this.mediaPeriods.size(); i10++) {
                this.mediaPeriods.get(i10).onPrepared();
            }
        }

        public void prepare(MediaPeriodImpl mediaPeriodImpl, long j10) {
            mediaPeriodImpl.lastStartPositionUs = j10;
            if (this.hasStartedPreparing) {
                if (this.isPrepared) {
                    mediaPeriodImpl.onPrepared();
                }
            } else {
                this.hasStartedPreparing = true;
                this.actualMediaPeriod.prepare(this, ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState));
            }
        }

        public int readData(MediaPeriodImpl mediaPeriodImpl, int i10, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i11) {
            long bufferedPositionUs = getBufferedPositionUs(mediaPeriodImpl);
            int data = ((SampleStream) Util.castNonNull(this.sampleStreams[i10])).readData(formatHolder, decoderInputBuffer, i11 | 5);
            long mediaPeriodPositionUsWithEndOfSourceHandling = getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, decoderInputBuffer.timeUs);
            if ((data == -4 && mediaPeriodPositionUsWithEndOfSourceHandling == Long.MIN_VALUE) || (data == -3 && bufferedPositionUs == Long.MIN_VALUE && !decoderInputBuffer.waitingForKeys)) {
                maybeNotifyDownstreamFormatChanged(mediaPeriodImpl, i10);
                decoderInputBuffer.clear();
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            if (data == -4) {
                maybeNotifyDownstreamFormatChanged(mediaPeriodImpl, i10);
                ((SampleStream) Util.castNonNull(this.sampleStreams[i10])).readData(formatHolder, decoderInputBuffer, i11);
                decoderInputBuffer.timeUs = mediaPeriodPositionUsWithEndOfSourceHandling;
            }
            return data;
        }

        public long readDiscontinuity(MediaPeriodImpl mediaPeriodImpl) {
            if (!mediaPeriodImpl.equals(this.mediaPeriods.get(0))) {
                return C.TIME_UNSET;
            }
            long discontinuity = this.actualMediaPeriod.readDiscontinuity();
            return discontinuity == C.TIME_UNSET ? C.TIME_UNSET : ServerSideAdInsertionUtil.getMediaPeriodPositionUs(discontinuity, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public void reevaluateBuffer(MediaPeriodImpl mediaPeriodImpl, long j10) {
            this.actualMediaPeriod.reevaluateBuffer(getStreamPositionUsWithNotYetStartedHandling(mediaPeriodImpl, j10));
        }

        public void release(MediaSource mediaSource) {
            mediaSource.releasePeriod(this.actualMediaPeriod);
        }

        public void remove(MediaPeriodImpl mediaPeriodImpl) {
            if (mediaPeriodImpl.equals(this.loadingPeriod)) {
                this.loadingPeriod = null;
                this.activeLoads.clear();
            }
            this.mediaPeriods.remove(mediaPeriodImpl);
        }

        public long seekToUs(MediaPeriodImpl mediaPeriodImpl, long j10) {
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(this.actualMediaPeriod.seekToUs(ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState)), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public long selectTracks(MediaPeriodImpl mediaPeriodImpl, ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
            mediaPeriodImpl.lastStartPositionUs = j10;
            if (!mediaPeriodImpl.equals(this.mediaPeriods.get(0))) {
                for (int i10 = 0; i10 < exoTrackSelectionArr.length; i10++) {
                    ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i10];
                    boolean z = true;
                    if (exoTrackSelection != null) {
                        if (zArr[i10] && sampleStreamArr[i10] != null) {
                            z = false;
                        }
                        zArr2[i10] = z;
                        if (z) {
                            sampleStreamArr[i10] = Objects.equals(this.trackSelections[i10], exoTrackSelection) ? new SampleStreamImpl(mediaPeriodImpl, i10) : new EmptySampleStream();
                        }
                    } else {
                        sampleStreamArr[i10] = null;
                        zArr2[i10] = true;
                    }
                }
                return j10;
            }
            this.trackSelections = (ExoTrackSelection[]) Arrays.copyOf(exoTrackSelectionArr, exoTrackSelectionArr.length);
            long streamPositionUs = ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
            SampleStream[] sampleStreamArr2 = this.sampleStreams;
            SampleStream[] sampleStreamArr3 = sampleStreamArr2.length == 0 ? new SampleStream[exoTrackSelectionArr.length] : (SampleStream[]) Arrays.copyOf(sampleStreamArr2, sampleStreamArr2.length);
            long jSelectTracks = this.actualMediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, streamPositionUs);
            this.sampleStreams = (SampleStream[]) Arrays.copyOf(sampleStreamArr3, sampleStreamArr3.length);
            this.lastDownstreamFormatChangeData = (MediaLoadData[]) Arrays.copyOf(this.lastDownstreamFormatChangeData, sampleStreamArr3.length);
            for (int i11 = 0; i11 < sampleStreamArr3.length; i11++) {
                if (sampleStreamArr3[i11] == null) {
                    sampleStreamArr[i11] = null;
                    this.lastDownstreamFormatChangeData[i11] = null;
                } else if (sampleStreamArr[i11] == null || zArr2[i11]) {
                    sampleStreamArr[i11] = new SampleStreamImpl(mediaPeriodImpl, i11);
                    this.lastDownstreamFormatChangeData[i11] = null;
                }
            }
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(jSelectTracks, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public int skipData(MediaPeriodImpl mediaPeriodImpl, int i10, long j10) {
            return ((SampleStream) Util.castNonNull(this.sampleStreams[i10])).skipData(ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState));
        }

        public void updateAdPlaybackState(AdPlaybackState adPlaybackState) {
            this.adPlaybackState = adPlaybackState;
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            MediaPeriodImpl mediaPeriodImpl = this.loadingPeriod;
            if (mediaPeriodImpl == null) {
                return;
            }
            MediaPeriod.Callback callback = mediaPeriodImpl.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this.loadingPeriod);
        }
    }

    public ServerSideAdInsertionMediaSource(MediaSource mediaSource, AdPlaybackStateUpdater adPlaybackStateUpdater) {
        this.mediaSource = mediaSource;
        this.adPlaybackStateUpdater = adPlaybackStateUpdater;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaLoadData correctMediaLoadData(MediaPeriodImpl mediaPeriodImpl, MediaLoadData mediaLoadData, AdPlaybackState adPlaybackState) {
        return new MediaLoadData(mediaLoadData.dataType, mediaLoadData.trackType, mediaLoadData.trackFormat, mediaLoadData.trackSelectionReason, mediaLoadData.trackSelectionData, correctMediaLoadDataPositionMs(mediaLoadData.mediaStartTimeMs, mediaPeriodImpl, adPlaybackState), correctMediaLoadDataPositionMs(mediaLoadData.mediaEndTimeMs, mediaPeriodImpl, adPlaybackState));
    }

    private static long correctMediaLoadDataPositionMs(long j10, MediaPeriodImpl mediaPeriodImpl, AdPlaybackState adPlaybackState) {
        if (j10 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long jMsToUs = Util.msToUs(j10);
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodImpl.mediaPeriodId;
        return Util.usToMs(mediaPeriodId.isAd() ? ServerSideAdInsertionUtil.getMediaPeriodPositionUsForAd(jMsToUs, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState) : ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(jMsToUs, -1, adPlaybackState));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getMediaPeriodEndPositionUs(MediaPeriodImpl mediaPeriodImpl, AdPlaybackState adPlaybackState) {
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodImpl.mediaPeriodId;
        if (mediaPeriodId.isAd()) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(mediaPeriodId.adGroupIndex);
            if (adGroup.count == -1) {
                return 0L;
            }
            return adGroup.durationsUs[mediaPeriodId.adIndexInAdGroup];
        }
        int i10 = mediaPeriodId.nextAdGroupIndex;
        if (i10 == -1) {
            return Long.MAX_VALUE;
        }
        long j10 = adPlaybackState.getAdGroup(i10).timeUs;
        if (j10 == Long.MIN_VALUE) {
            return Long.MAX_VALUE;
        }
        return j10;
    }

    private MediaPeriodImpl getMediaPeriodForEvent(MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData, boolean z) {
        if (mediaPeriodId == null) {
            return null;
        }
        List list = this.mediaPeriods.get((Object) new Pair(Long.valueOf(mediaPeriodId.windowSequenceNumber), mediaPeriodId.periodUid));
        if (list.isEmpty()) {
            return null;
        }
        if (z) {
            SharedMediaPeriod sharedMediaPeriod = (SharedMediaPeriod) a0.j(list);
            return sharedMediaPeriod.loadingPeriod != null ? sharedMediaPeriod.loadingPeriod : (MediaPeriodImpl) a0.j(sharedMediaPeriod.mediaPeriods);
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            MediaPeriodImpl mediaPeriodForEvent = ((SharedMediaPeriod) list.get(i10)).getMediaPeriodForEvent(mediaLoadData);
            if (mediaPeriodForEvent != null) {
                return mediaPeriodForEvent;
            }
        }
        return (MediaPeriodImpl) ((SharedMediaPeriod) list.get(0)).mediaPeriods.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAdPlaybackStates$0(l1 l1Var, Timeline timeline) {
        AdPlaybackState adPlaybackState;
        for (SharedMediaPeriod sharedMediaPeriod : this.mediaPeriods.values()) {
            AdPlaybackState adPlaybackState2 = (AdPlaybackState) l1Var.get(sharedMediaPeriod.periodUid);
            if (adPlaybackState2 != null) {
                sharedMediaPeriod.updateAdPlaybackState(adPlaybackState2);
            }
        }
        SharedMediaPeriod sharedMediaPeriod2 = this.lastUsedMediaPeriod;
        if (sharedMediaPeriod2 != null && (adPlaybackState = (AdPlaybackState) l1Var.get(sharedMediaPeriod2.periodUid)) != null) {
            this.lastUsedMediaPeriod.updateAdPlaybackState(adPlaybackState);
        }
        this.adPlaybackStates = l1Var;
        refreshSourceInfo(new ServerSideAdInsertionTimeline(timeline, l1Var));
    }

    private void releaseLastUsedMediaPeriod() {
        SharedMediaPeriod sharedMediaPeriod = this.lastUsedMediaPeriod;
        if (sharedMediaPeriod != null) {
            sharedMediaPeriod.release(this.mediaSource);
            this.lastUsedMediaPeriod = null;
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        SharedMediaPeriod sharedMediaPeriod;
        Pair pair = new Pair(Long.valueOf(mediaPeriodId.windowSequenceNumber), mediaPeriodId.periodUid);
        SharedMediaPeriod sharedMediaPeriod2 = this.lastUsedMediaPeriod;
        SharedMediaPeriod sharedMediaPeriod3 = null;
        boolean z = false;
        if (sharedMediaPeriod2 != null) {
            if (sharedMediaPeriod2.periodUid.equals(mediaPeriodId.periodUid)) {
                sharedMediaPeriod = this.lastUsedMediaPeriod;
                this.mediaPeriods.put(pair, sharedMediaPeriod);
                z = true;
            } else {
                this.lastUsedMediaPeriod.release(this.mediaSource);
                sharedMediaPeriod = null;
            }
            this.lastUsedMediaPeriod = null;
            sharedMediaPeriod3 = sharedMediaPeriod;
        }
        if (sharedMediaPeriod3 == null && ((sharedMediaPeriod3 = (SharedMediaPeriod) a0.k(this.mediaPeriods.get((Object) pair))) == null || !sharedMediaPeriod3.canReuseMediaPeriod(mediaPeriodId, j10))) {
            AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodId.periodUid);
            adPlaybackState.getClass();
            sharedMediaPeriod3 = new SharedMediaPeriod(this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(mediaPeriodId.periodUid, mediaPeriodId.windowSequenceNumber), allocator, ServerSideAdInsertionUtil.getStreamPositionUs(j10, mediaPeriodId, adPlaybackState)), mediaPeriodId.periodUid, adPlaybackState);
            this.mediaPeriods.put(pair, sharedMediaPeriod3);
        }
        MediaPeriodImpl mediaPeriodImpl = new MediaPeriodImpl(sharedMediaPeriod3, mediaPeriodId, createEventDispatcher(mediaPeriodId), createDrmEventDispatcher(mediaPeriodId));
        sharedMediaPeriod3.add(mediaPeriodImpl);
        if (z && sharedMediaPeriod3.trackSelections.length > 0) {
            mediaPeriodImpl.seekToUs(j10);
        }
        return mediaPeriodImpl;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void disableInternal() {
        releaseLastUsedMediaPeriod();
        this.mediaSource.disable(this);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void enableInternal() {
        this.mediaSource.enable(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int i10, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, false);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.downstreamFormatChanged(mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onDownstreamFormatChanged(mediaPeriodForEvent, mediaLoadData);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.downstreamFormatChanged(correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final /* synthetic */ void onDrmKeysLoaded(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        f.a(this, i10, mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysRemoved(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysRemoved();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysRemoved();
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysRestored(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysRestored();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysRestored();
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionAcquired(int i10, MediaSource.MediaPeriodId mediaPeriodId, int i11) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, true);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionAcquired(i11);
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionAcquired(i11);
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionManagerError(int i10, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionManagerError(exc);
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionManagerError(exc);
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionReleased(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionReleased();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionReleased();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadCanceled(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadCanceled(loadEventInfo, mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadCanceled(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadCompleted(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadCompleted(loadEventInfo, mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadCompleted(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadError(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadError(loadEventInfo, mediaLoadData, iOException, z);
            return;
        }
        if (z) {
            mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        }
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadError(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState), iOException, z);
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadStarted(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i11) {
        if (i11 == 0) {
            MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
            if (mediaPeriodForEvent == null) {
                this.mediaSourceEventDispatcherWithoutId.loadStarted(loadEventInfo, mediaLoadData, 0);
                return;
            }
            mediaPeriodForEvent.sharedPeriod.onLoadStarted(loadEventInfo, mediaLoadData);
            MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
            AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
            adPlaybackState.getClass();
            eventDispatcher.loadStarted(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState), 0);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        AdPlaybackStateUpdater adPlaybackStateUpdater = this.adPlaybackStateUpdater;
        if ((adPlaybackStateUpdater == null || !adPlaybackStateUpdater.onAdPlaybackStateUpdateRequested(timeline)) && !this.adPlaybackStates.isEmpty()) {
            refreshSourceInfo(new ServerSideAdInsertionTimeline(timeline, this.adPlaybackStates));
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int i10, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, false);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.upstreamDiscarded(mediaLoadData);
            return;
        }
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = (AdPlaybackState) this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.upstreamDiscarded(correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
        synchronized (this) {
            this.playbackHandler = handlerCreateHandlerForCurrentLooper;
        }
        this.mediaSource.addEventListener(handlerCreateHandlerForCurrentLooper, this);
        this.mediaSource.addDrmEventListener(handlerCreateHandlerForCurrentLooper, this);
        this.mediaSource.prepareSource(this, transferListener, getPlayerId());
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaPeriodImpl mediaPeriodImpl = (MediaPeriodImpl) mediaPeriod;
        mediaPeriodImpl.sharedPeriod.remove(mediaPeriodImpl);
        if (mediaPeriodImpl.sharedPeriod.isUnused()) {
            this.mediaPeriods.remove(new Pair(Long.valueOf(mediaPeriodImpl.mediaPeriodId.windowSequenceNumber), mediaPeriodImpl.mediaPeriodId.periodUid), mediaPeriodImpl.sharedPeriod);
            if (this.mediaPeriods.isEmpty()) {
                this.lastUsedMediaPeriod = mediaPeriodImpl.sharedPeriod;
            } else {
                mediaPeriodImpl.sharedPeriod.release(this.mediaSource);
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        releaseLastUsedMediaPeriod();
        synchronized (this) {
            this.playbackHandler = null;
        }
        this.mediaSource.releaseSource(this);
        this.mediaSource.removeEventListener(this);
        this.mediaSource.removeDrmEventListener(this);
    }

    public void setAdPlaybackStates(l1 l1Var, Timeline timeline) {
        ac.b.j(!l1Var.isEmpty());
        Object obj = ((AdPlaybackState) l1Var.values().a().get(0)).adsId;
        obj.getClass();
        w1 w1VarC = l1Var.f14047i;
        if (w1VarC == null) {
            w1VarC = l1Var.c();
            l1Var.f14047i = w1VarC;
        }
        o4 it = w1VarC.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            AdPlaybackState adPlaybackState = (AdPlaybackState) entry.getValue();
            ac.b.j(obj.equals(adPlaybackState.adsId));
            AdPlaybackState adPlaybackState2 = (AdPlaybackState) this.adPlaybackStates.get(key);
            if (adPlaybackState2 != null) {
                for (int i10 = adPlaybackState.removedAdGroupCount; i10 < adPlaybackState.adGroupCount; i10++) {
                    AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
                    ac.b.j(adGroup.isServerSideInserted);
                    if (i10 < adPlaybackState2.adGroupCount && ServerSideAdInsertionUtil.getAdCountInGroup(adPlaybackState, i10) < ServerSideAdInsertionUtil.getAdCountInGroup(adPlaybackState2, i10)) {
                        AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(i10 + 1);
                        ac.b.j(adGroup.contentResumeOffsetUs + adGroup2.contentResumeOffsetUs == adPlaybackState2.getAdGroup(i10).contentResumeOffsetUs);
                        ac.b.j(adGroup.timeUs + adGroup.contentResumeOffsetUs == adGroup2.timeUs);
                    }
                    if (adGroup.timeUs == Long.MIN_VALUE) {
                        ac.b.j(ServerSideAdInsertionUtil.getAdCountInGroup(adPlaybackState, i10) == 0);
                    }
                }
            }
        }
        synchronized (this) {
            try {
                Handler handler = this.playbackHandler;
                if (handler == null) {
                    this.adPlaybackStates = l1Var;
                } else {
                    handler.post(new d(this, l1Var, timeline, 1));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSource.updateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysLoaded(int i10, MediaSource.MediaPeriodId mediaPeriodId, KeyRequestInfo keyRequestInfo) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysLoaded(keyRequestInfo);
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysLoaded(keyRequestInfo);
        }
    }
}
