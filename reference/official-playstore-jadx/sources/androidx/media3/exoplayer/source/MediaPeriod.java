package androidx.media3.exoplayer.source;

import androidx.media3.common.StreamKey;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaPeriod extends SequenceableLoader {

    public interface Callback extends SequenceableLoader.Callback<MediaPeriod> {
        void onPrepared(MediaPeriod mediaPeriod);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    boolean continueLoading(LoadingInfo loadingInfo);

    void discardBuffer(long j10, boolean z);

    long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters);

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    long getBufferedPositionUs();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    long getNextLoadPositionUs();

    List<StreamKey> getStreamKeys(List<ExoTrackSelection> list);

    TrackGroupArray getTrackGroups();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    boolean isLoading();

    void maybeThrowPrepareError() throws IOException;

    void prepare(Callback callback, long j10);

    long readDiscontinuity();

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    void reevaluateBuffer(long j10);

    long seekToUs(long j10);

    long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10);
}
