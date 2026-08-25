package androidx.media3.exoplayer.source;

import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.e1;
import com.google.common.collect.e4;
import com.google.common.collect.h1;
import com.google.common.collect.w1;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class FilteringMediaSource extends WrappingMediaSource {
    private final w1 trackTypes;

    public static final class FilteringMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
        private MediaPeriod.Callback callback;
        private TrackGroupArray filteredTrackGroups;
        public final MediaPeriod mediaPeriod;
        private final w1 trackTypes;

        public FilteringMediaPeriod(MediaPeriod mediaPeriod, w1 w1Var) {
            this.mediaPeriod = mediaPeriod;
            this.trackTypes = w1Var;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(LoadingInfo loadingInfo) {
            return this.mediaPeriod.continueLoading(loadingInfo);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void discardBuffer(long j10, boolean z) {
            this.mediaPeriod.discardBuffer(j10, z);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
            return this.mediaPeriod.getAdjustedSeekPositionUs(j10, seekParameters);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            return this.mediaPeriod.getBufferedPositionUs();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            return this.mediaPeriod.getNextLoadPositionUs();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.mediaPeriod.getStreamKeys(list);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public TrackGroupArray getTrackGroups() {
            TrackGroupArray trackGroupArray = this.filteredTrackGroups;
            trackGroupArray.getClass();
            return trackGroupArray;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return this.mediaPeriod.isLoading();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void maybeThrowPrepareError() throws IOException {
            this.mediaPeriod.maybeThrowPrepareError();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            TrackGroupArray trackGroups = mediaPeriod.getTrackGroups();
            e1 e1VarK = h1.k();
            for (int i10 = 0; i10 < trackGroups.length; i10++) {
                TrackGroup trackGroup = trackGroups.get(i10);
                if (this.trackTypes.contains(Integer.valueOf(trackGroup.type))) {
                    e1VarK.c(trackGroup);
                }
            }
            this.filteredTrackGroups = new TrackGroupArray((TrackGroup[]) e1VarK.f().toArray(new TrackGroup[0]));
            MediaPeriod.Callback callback = this.callback;
            callback.getClass();
            callback.onPrepared(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void prepare(MediaPeriod.Callback callback, long j10) {
            this.callback = callback;
            this.mediaPeriod.prepare(this, j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long readDiscontinuity() {
            return this.mediaPeriod.readDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long j10) {
            this.mediaPeriod.reevaluateBuffer(j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long seekToUs(long j10) {
            return this.mediaPeriod.seekToUs(j10);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
            return this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j10);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            MediaPeriod.Callback callback = this.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FilteringMediaSource(MediaSource mediaSource, int i10) {
        Integer numValueOf = Integer.valueOf(i10);
        int i11 = w1.f14132m;
        this(mediaSource, new e4(numValueOf));
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        return new FilteringMediaPeriod(super.createPeriod(mediaPeriodId, allocator, j10), this.trackTypes);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        super.releasePeriod(((FilteringMediaPeriod) mediaPeriod).mediaPeriod);
    }

    public FilteringMediaSource(MediaSource mediaSource, Set<Integer> set) {
        super(mediaSource);
        this.trackTypes = w1.m(set);
    }
}
