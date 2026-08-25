package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.StreamKey;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final MediaPeriod mediaPeriod;
    private final long timeOffsetUs;

    public static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream, long j10) {
            this.sampleStream = sampleStream;
            this.timeOffsetUs = j10;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.sampleStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
            int data = this.sampleStream.readData(formatHolder, decoderInputBuffer, i10);
            if (data == -4) {
                decoderInputBuffer.timeUs += this.timeOffsetUs;
            }
            return data;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j10) {
            return this.sampleStream.skipData(j10 - this.timeOffsetUs);
        }
    }

    public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod, long j10) {
        this.mediaPeriod = mediaPeriod;
        this.timeOffsetUs = j10;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo.buildUpon().setPlaybackPositionUs(loadingInfo.playbackPositionUs - this.timeOffsetUs).build());
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j10, boolean z) {
        this.mediaPeriod.discardBuffer(j10 - this.timeOffsetUs, z);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j10 - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return bufferedPositionUs + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        return this.mediaPeriod.getStreamKeys(list);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
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
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j10) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, j10 - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long discontinuity = this.mediaPeriod.readDiscontinuity();
        return discontinuity == C.TIME_UNSET ? C.TIME_UNSET : discontinuity + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
        this.mediaPeriod.reevaluateBuffer(j10 - this.timeOffsetUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j10) {
        return this.mediaPeriod.seekToUs(j10 - this.timeOffsetUs) + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
        SampleStream[] sampleStreamArr2 = new SampleStream[sampleStreamArr.length];
        int i10 = 0;
        while (true) {
            SampleStream childStream = null;
            if (i10 >= sampleStreamArr.length) {
                break;
            }
            TimeOffsetSampleStream timeOffsetSampleStream = (TimeOffsetSampleStream) sampleStreamArr[i10];
            if (timeOffsetSampleStream != null) {
                childStream = timeOffsetSampleStream.getChildStream();
            }
            sampleStreamArr2[i10] = childStream;
            i10++;
        }
        long jSelectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr2, zArr2, j10 - this.timeOffsetUs);
        for (int i11 = 0; i11 < sampleStreamArr.length; i11++) {
            SampleStream sampleStream = sampleStreamArr2[i11];
            if (sampleStream == null) {
                sampleStreamArr[i11] = null;
            } else {
                SampleStream sampleStream2 = sampleStreamArr[i11];
                if (sampleStream2 == null || ((TimeOffsetSampleStream) sampleStream2).getChildStream() != sampleStream) {
                    sampleStreamArr[i11] = new TimeOffsetSampleStream(sampleStream, this.timeOffsetUs);
                }
            }
        }
        return jSelectTracks + this.timeOffsetUs;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }
}
