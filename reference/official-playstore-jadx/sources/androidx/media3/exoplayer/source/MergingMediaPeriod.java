package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.ForwardingTrackSelection;
import com.google.common.collect.a0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaPeriod[] periods;
    private final boolean[] periodsWithTimeOffsets;
    private TrackGroupArray trackGroups;
    private final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    private MediaPeriod[] enabledPeriods = new MediaPeriod[0];

    public static final class MergingMediaPeriodTrackSelection extends ForwardingTrackSelection {
        private final TrackGroup trackGroup;

        public MergingMediaPeriodTrackSelection(ExoTrackSelection exoTrackSelection, TrackGroup trackGroup) {
            super(exoTrackSelection);
            this.trackGroup = trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection
        public boolean equals(Object obj) {
            if (super.equals(obj) && (obj instanceof MergingMediaPeriodTrackSelection)) {
                return this.trackGroup.equals(((MergingMediaPeriodTrackSelection) obj).trackGroup);
            }
            return false;
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection, androidx.media3.exoplayer.trackselection.TrackSelection
        public Format getFormat(int i10) {
            return this.trackGroup.getFormat(getWrappedInstance().getIndexInTrackGroup(i10));
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Format getSelectedFormat() {
            return this.trackGroup.getFormat(getWrappedInstance().getSelectedIndexInTrackGroup());
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection, androidx.media3.exoplayer.trackselection.TrackSelection
        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection
        public int hashCode() {
            return this.trackGroup.hashCode() + (super.hashCode() * 31);
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection, androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(Format format) {
            return getWrappedInstance().indexOf(this.trackGroup.indexOf(format));
        }
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, long[] jArr, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.periods = mediaPeriodArr;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.empty();
        this.periodsWithTimeOffsets = new boolean[mediaPeriodArr.length];
        for (int i10 = 0; i10 < mediaPeriodArr.length; i10++) {
            long j10 = jArr[i10];
            if (j10 != 0) {
                this.periodsWithTimeOffsets[i10] = true;
                this.periods[i10] = new TimeOffsetMediaPeriod(mediaPeriodArr[i10], j10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$selectTracks$0(MediaPeriod mediaPeriod) {
        return mediaPeriod.getTrackGroups().getTrackTypes();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.childrenPendingPreparation.isEmpty()) {
            return this.compositeSequenceableLoader.continueLoading(loadingInfo);
        }
        int size = this.childrenPendingPreparation.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.childrenPendingPreparation.get(i10).continueLoading(loadingInfo);
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j10, boolean z) {
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            mediaPeriod.discardBuffer(j10, z);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
        return (mediaPeriodArr.length > 0 ? mediaPeriodArr[0] : this.periods[0]).getAdjustedSeekPositionUs(j10, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public MediaPeriod getChildPeriod(int i10) {
        return this.periodsWithTimeOffsets[i10] ? ((TimeOffsetMediaPeriod) this.periods[i10]).getWrappedMediaPeriod() : this.periods[i10];
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public final /* synthetic */ List getStreamKeys(List list) {
        return f.a(this, list);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        TrackGroupArray trackGroupArray = this.trackGroups;
        trackGroupArray.getClass();
        return trackGroupArray;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.childrenPendingPreparation.remove(mediaPeriod);
        if (!this.childrenPendingPreparation.isEmpty()) {
            return;
        }
        int i10 = 0;
        for (MediaPeriod mediaPeriod2 : this.periods) {
            i10 += mediaPeriod2.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i10];
        int i11 = 0;
        int i12 = 0;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.periods;
            if (i11 >= mediaPeriodArr.length) {
                this.trackGroups = new TrackGroupArray(trackGroupArr);
                MediaPeriod.Callback callback = this.callback;
                callback.getClass();
                callback.onPrepared(this);
                return;
            }
            TrackGroupArray trackGroups = mediaPeriodArr[i11].getTrackGroups();
            int i13 = trackGroups.length;
            int i14 = 0;
            while (i14 < i13) {
                TrackGroup trackGroup = trackGroups.get(i14);
                Format[] formatArr = new Format[trackGroup.length];
                for (int i15 = 0; i15 < trackGroup.length; i15++) {
                    Format format = trackGroup.getFormat(i15);
                    Format.Builder builderBuildUpon = format.buildUpon();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i11);
                    sb2.append(":");
                    String str = format.id;
                    if (str == null) {
                        str = "";
                    }
                    sb2.append(str);
                    formatArr[i15] = builderBuildUpon.setId(sb2.toString()).build();
                }
                TrackGroup trackGroup2 = new TrackGroup(i11 + ":" + trackGroup.id, formatArr);
                this.childTrackGroupByMergedTrackGroup.put(trackGroup2, trackGroup);
                trackGroupArr[i12] = trackGroup2;
                i14++;
                i12++;
            }
            i11++;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j10) {
        this.callback = callback;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.prepare(this, j10);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long j10 = -9223372036854775807L;
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            long discontinuity = mediaPeriod.readDiscontinuity();
            if (discontinuity == C.TIME_UNSET) {
                if (j10 != C.TIME_UNSET && mediaPeriod.seekToUs(j10) != j10) {
                    throw new IllegalStateException("Unexpected child seekToUs result.");
                }
            } else if (j10 == C.TIME_UNSET) {
                for (MediaPeriod mediaPeriod2 : this.enabledPeriods) {
                    if (mediaPeriod2 == mediaPeriod) {
                        break;
                    }
                    if (mediaPeriod2.seekToUs(discontinuity) != discontinuity) {
                        throw new IllegalStateException("Unexpected child seekToUs result.");
                    }
                }
                j10 = discontinuity;
            } else if (discontinuity != j10) {
                throw new IllegalStateException("Conflicting discontinuities.");
            }
        }
        return j10;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
        this.compositeSequenceableLoader.reevaluateBuffer(j10);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j10) {
        long jSeekToUs = this.enabledPeriods[0].seekToUs(j10);
        int i10 = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i10 >= mediaPeriodArr.length) {
                return jSeekToUs;
            }
            if (mediaPeriodArr[i10].seekToUs(jSeekToUs) != jSeekToUs) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
            i10++;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
        int[] iArr = new int[exoTrackSelectionArr.length];
        int[] iArr2 = new int[exoTrackSelectionArr.length];
        int i10 = 0;
        for (int i11 = 0; i11 < exoTrackSelectionArr.length; i11++) {
            SampleStream sampleStream = sampleStreamArr[i11];
            Integer num = sampleStream == null ? null : this.streamPeriodIndices.get(sampleStream);
            iArr[i11] = num == null ? -1 : num.intValue();
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i11];
            if (exoTrackSelection != null) {
                String str = exoTrackSelection.getTrackGroup().id;
                iArr2[i11] = Integer.parseInt(str.substring(0, str.indexOf(":")));
            } else {
                iArr2[i11] = -1;
            }
        }
        this.streamPeriodIndices.clear();
        int length = exoTrackSelectionArr.length;
        SampleStream[] sampleStreamArr2 = new SampleStream[length];
        SampleStream[] sampleStreamArr3 = new SampleStream[exoTrackSelectionArr.length];
        ExoTrackSelection[] exoTrackSelectionArr2 = new ExoTrackSelection[exoTrackSelectionArr.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j11 = j10;
        int i12 = 0;
        while (i12 < this.periods.length) {
            for (int i13 = i10; i13 < exoTrackSelectionArr.length; i13++) {
                sampleStreamArr3[i13] = iArr[i13] == i12 ? sampleStreamArr[i13] : null;
                if (iArr2[i13] == i12) {
                    ExoTrackSelection exoTrackSelection2 = exoTrackSelectionArr[i13];
                    exoTrackSelection2.getClass();
                    TrackGroup trackGroup = this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection2.getTrackGroup());
                    trackGroup.getClass();
                    exoTrackSelectionArr2[i13] = new MergingMediaPeriodTrackSelection(exoTrackSelection2, trackGroup);
                } else {
                    exoTrackSelectionArr2[i13] = null;
                }
            }
            int i14 = i12;
            long jSelectTracks = this.periods[i12].selectTracks(exoTrackSelectionArr2, zArr, sampleStreamArr3, zArr2, j11);
            if (i14 == 0) {
                j11 = jSelectTracks;
            } else if (jSelectTracks != j11) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z = false;
            for (int i15 = 0; i15 < exoTrackSelectionArr.length; i15++) {
                if (iArr2[i15] == i14) {
                    SampleStream sampleStream2 = sampleStreamArr3[i15];
                    sampleStream2.getClass();
                    sampleStreamArr2[i15] = sampleStreamArr3[i15];
                    this.streamPeriodIndices.put(sampleStream2, Integer.valueOf(i14));
                    z = true;
                } else if (iArr[i15] == i14) {
                    ac.b.s(sampleStreamArr3[i15] == null);
                }
            }
            if (z) {
                arrayList.add(this.periods[i14]);
            }
            i12 = i14 + 1;
            i10 = 0;
        }
        int i16 = i10;
        System.arraycopy(sampleStreamArr2, i16, sampleStreamArr, i16, length);
        this.enabledPeriods = (MediaPeriod[]) arrayList.toArray(new MediaPeriod[i16]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(arrayList, a0.x(arrayList, new k(0)));
        return j11;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }
}
