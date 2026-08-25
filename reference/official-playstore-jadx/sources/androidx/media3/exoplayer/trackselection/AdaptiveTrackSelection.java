package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.collect.a0;
import com.google.common.collect.d3;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.collect.y2;
import com.google.common.collect.z2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.7f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_HEIGHT_TO_DISCARD = 719;
    public static final int DEFAULT_MAX_WIDTH_TO_DISCARD = 1279;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    private static final long MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 1000;
    private static final String TAG = "AdaptiveTrackSelection";
    private final h1 adaptationCheckpoints;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private MediaChunk lastBufferEvaluationMediaChunk;
    private long lastBufferEvaluationMs;
    private long latestBitrateEstimate;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxHeightToDiscard;
    private final int maxWidthToDiscard;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long j10, long j11) {
            this.totalBandwidth = j10;
            this.allocatedBandwidth = j11;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdaptationCheckpoint)) {
                return false;
            }
            AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
            return this.totalBandwidth == adaptationCheckpoint.totalBandwidth && this.allocatedBandwidth == adaptationCheckpoint.allocatedBandwidth;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }

    public static class Factory implements ExoTrackSelection.Factory {
        private final float bandwidthFraction;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxHeightToDiscard;
        private final int maxWidthToDiscard;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i10, BandwidthMeter bandwidthMeter, h1 h1Var) {
            return new AdaptiveTrackSelection(trackGroup, iArr, i10, bandwidthMeter, this.minDurationForQualityIncreaseMs, this.maxDurationForQualityDecreaseMs, this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, h1Var, this.clock);
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.media3.exoplayer.trackselection.ExoTrackSelection[] createTrackSelections(androidx.media3.exoplayer.trackselection.ExoTrackSelection.Definition[] r10, androidx.media3.exoplayer.upstream.BandwidthMeter r11, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r12, androidx.media3.common.Timeline r13) {
            /*
                r9 = this;
                com.google.common.collect.h1 r12 = androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection.access$000(r10)
                int r13 = r10.length
                androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r13 = new androidx.media3.exoplayer.trackselection.ExoTrackSelection[r13]
                r0 = 0
                r1 = r0
            L9:
                int r2 = r10.length
                if (r1 >= r2) goto L40
                r2 = r10[r1]
                if (r2 == 0) goto L15
                int[] r5 = r2.tracks
                int r3 = r5.length
                if (r3 != 0) goto L17
            L15:
                r7 = r11
                goto L3c
            L17:
                int r3 = r5.length
                r4 = 1
                if (r3 != r4) goto L28
                androidx.media3.exoplayer.trackselection.FixedTrackSelection r3 = new androidx.media3.exoplayer.trackselection.FixedTrackSelection
                androidx.media3.common.TrackGroup r4 = r2.group
                r5 = r5[r0]
                int r2 = r2.type
                r3.<init>(r4, r5, r2)
                r7 = r11
                goto L3a
            L28:
                androidx.media3.common.TrackGroup r4 = r2.group
                int r6 = r2.type
                java.lang.Object r2 = r12.get(r1)
                r8 = r2
                com.google.common.collect.h1 r8 = (com.google.common.collect.h1) r8
                r3 = r9
                r7 = r11
                androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection r11 = r3.createAdaptiveTrackSelection(r4, r5, r6, r7, r8)
                r3 = r11
            L3a:
                r13[r1] = r3
            L3c:
                int r1 = r1 + 1
                r11 = r7
                goto L9
            L40:
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection.Factory.createTrackSelections(androidx.media3.exoplayer.trackselection.ExoTrackSelection$Definition[], androidx.media3.exoplayer.upstream.BandwidthMeter, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId, androidx.media3.common.Timeline):androidx.media3.exoplayer.trackselection.ExoTrackSelection[]");
        }

        public Factory(int i10, int i11, int i12, float f10) {
            this(i10, i11, i12, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f10, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i10, int i11, int i12, int i13, int i14, float f10) {
            this(i10, i11, i12, i13, i14, f10, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i10, int i11, int i12, float f10, float f11, Clock clock) {
            this(i10, i11, i12, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f10, f11, clock);
        }

        public Factory(int i10, int i11, int i12, int i13, int i14, float f10, float f11, Clock clock) {
            this.minDurationForQualityIncreaseMs = i10;
            this.maxDurationForQualityDecreaseMs = i11;
            this.minDurationToRetainAfterDiscardMs = i12;
            this.maxWidthToDiscard = i13;
            this.maxHeightToDiscard = i14;
            this.bandwidthFraction = f10;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f11;
            this.clock = clock;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter) {
        this(trackGroup, iArr, 0, bandwidthMeter, 10000L, 25000L, 25000L, DEFAULT_MAX_WIDTH_TO_DISCARD, DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, o3.f14078o, Clock.DEFAULT);
        f1 f1Var = h1.f14020l;
    }

    private static void addCheckpoint(List<e1> list, long[] jArr) {
        long j10 = 0;
        for (long j11 : jArr) {
            j10 += j11;
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            e1 e1Var = list.get(i10);
            if (e1Var != null) {
                e1Var.c(new AdaptationCheckpoint(j10, jArr[i10]));
            }
        }
    }

    private int determineIdealSelectedIndex(long j10, long j11) {
        long allocatedBandwidth = getAllocatedBandwidth(j11);
        int i10 = 0;
        for (int i11 = 0; i11 < this.length; i11++) {
            if (j10 == Long.MIN_VALUE || !isTrackExcluded(i11, j10)) {
                Format format = getFormat(i11);
                if (canSelectFormat(format, format.bitrate, allocatedBandwidth)) {
                    return i11;
                }
                i10 = i11;
            }
        }
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static h1 getAdaptationCheckpoints(ExoTrackSelection.Definition[] definitionArr) {
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection.Definition definition : definitionArr) {
            if (definition == null || definition.tracks.length <= 1) {
                arrayList.add(null);
            } else {
                e1 e1VarK = h1.k();
                e1VarK.c(new AdaptationCheckpoint(0L, 0L));
                arrayList.add(e1VarK);
            }
        }
        long[][] sortedTrackBitrates = getSortedTrackBitrates(definitionArr);
        int[] iArr = new int[sortedTrackBitrates.length];
        long[] jArr = new long[sortedTrackBitrates.length];
        for (int i10 = 0; i10 < sortedTrackBitrates.length; i10++) {
            long[] jArr2 = sortedTrackBitrates[i10];
            jArr[i10] = jArr2.length == 0 ? 0L : jArr2[0];
        }
        addCheckpoint(arrayList, jArr);
        h1 switchOrder = getSwitchOrder(sortedTrackBitrates);
        for (int i11 = 0; i11 < switchOrder.size(); i11++) {
            int iIntValue = ((Integer) switchOrder.get(i11)).intValue();
            int i12 = iArr[iIntValue] + 1;
            iArr[iIntValue] = i12;
            jArr[iIntValue] = sortedTrackBitrates[iIntValue][i12];
            addCheckpoint(arrayList, jArr);
        }
        for (int i13 = 0; i13 < definitionArr.length; i13++) {
            if (arrayList.get(i13) != null) {
                jArr[i13] = jArr[i13] * 2;
            }
        }
        addCheckpoint(arrayList, jArr);
        e1 e1VarK2 = h1.k();
        for (int i14 = 0; i14 < arrayList.size(); i14++) {
            e1 e1Var = (e1) arrayList.get(i14);
            e1VarK2.c(e1Var == null ? o3.f14078o : e1Var.f());
        }
        return e1VarK2.f();
    }

    private long getAllocatedBandwidth(long j10) {
        long totalAllocatableBandwidth = getTotalAllocatableBandwidth(j10);
        if (this.adaptationCheckpoints.isEmpty()) {
            return totalAllocatableBandwidth;
        }
        int i10 = 1;
        while (i10 < this.adaptationCheckpoints.size() - 1 && ((AdaptationCheckpoint) this.adaptationCheckpoints.get(i10)).totalBandwidth < totalAllocatableBandwidth) {
            i10++;
        }
        AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) this.adaptationCheckpoints.get(i10 - 1);
        AdaptationCheckpoint adaptationCheckpoint2 = (AdaptationCheckpoint) this.adaptationCheckpoints.get(i10);
        long j11 = adaptationCheckpoint.totalBandwidth;
        float f10 = (totalAllocatableBandwidth - j11) / (adaptationCheckpoint2.totalBandwidth - j11);
        return adaptationCheckpoint.allocatedBandwidth + ((long) (f10 * (adaptationCheckpoint2.allocatedBandwidth - r2)));
    }

    private long getLastChunkDurationUs(List<? extends MediaChunk> list) {
        if (list.isEmpty()) {
            return C.TIME_UNSET;
        }
        MediaChunk mediaChunk = (MediaChunk) a0.j(list);
        long j10 = mediaChunk.startTimeUs;
        if (j10 != C.TIME_UNSET) {
            long j11 = mediaChunk.endTimeUs;
            if (j11 != C.TIME_UNSET) {
                return j11 - j10;
            }
        }
        return C.TIME_UNSET;
    }

    private long getNextChunkDurationUs(MediaChunkIterator[] mediaChunkIteratorArr, List<? extends MediaChunk> list) {
        int i10 = this.selectedIndex;
        if (i10 < mediaChunkIteratorArr.length && mediaChunkIteratorArr[i10].next()) {
            MediaChunkIterator mediaChunkIterator = mediaChunkIteratorArr[this.selectedIndex];
            return mediaChunkIterator.getChunkEndTimeUs() - mediaChunkIterator.getChunkStartTimeUs();
        }
        for (MediaChunkIterator mediaChunkIterator2 : mediaChunkIteratorArr) {
            if (mediaChunkIterator2.next()) {
                return mediaChunkIterator2.getChunkEndTimeUs() - mediaChunkIterator2.getChunkStartTimeUs();
            }
        }
        return getLastChunkDurationUs(list);
    }

    private static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length][];
        for (int i10 = 0; i10 < definitionArr.length; i10++) {
            ExoTrackSelection.Definition definition = definitionArr[i10];
            if (definition == null) {
                jArr[i10] = new long[0];
            } else {
                jArr[i10] = new long[definition.tracks.length];
                int i11 = 0;
                while (true) {
                    int[] iArr = definition.tracks;
                    if (i11 >= iArr.length) {
                        break;
                    }
                    long j10 = definition.group.getFormat(iArr[i11]).bitrate;
                    long[] jArr2 = jArr[i10];
                    if (j10 == -1) {
                        j10 = 0;
                    }
                    jArr2[i11] = j10;
                    i11++;
                }
                Arrays.sort(jArr[i10]);
            }
        }
        return jArr;
    }

    private static h1 getSwitchOrder(long[][] jArr) {
        a0.c(2, "expectedValuesPerKey");
        TreeMap treeMap = new TreeMap(d3.f13967l);
        y2 y2Var = new y2();
        z2 z2Var = new z2(treeMap);
        z2Var.f14146q = y2Var;
        for (int i10 = 0; i10 < jArr.length; i10++) {
            long[] jArr2 = jArr[i10];
            if (jArr2.length > 1) {
                int length = jArr2.length;
                double[] dArr = new double[length];
                int i11 = 0;
                while (true) {
                    long[] jArr3 = jArr[i10];
                    double dLog = 0.0d;
                    if (i11 >= jArr3.length) {
                        break;
                    }
                    long j10 = jArr3[i11];
                    if (j10 != -1) {
                        dLog = Math.log(j10);
                    }
                    dArr[i11] = dLog;
                    i11++;
                }
                int i12 = length - 1;
                double d4 = dArr[i12] - dArr[0];
                int i13 = 0;
                while (i13 < i12) {
                    double d10 = dArr[i13];
                    i13++;
                    z2Var.put(Double.valueOf(d4 == 0.0d ? 1.0d : (((d10 + dArr[i13]) * 0.5d) - dArr[0]) / d4), Integer.valueOf(i10));
                }
            }
        }
        return h1.n(z2Var.values());
    }

    private long getTotalAllocatableBandwidth(long j10) {
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        this.latestBitrateEstimate = bitrateEstimate;
        long j11 = (long) (bitrateEstimate * this.bandwidthFraction);
        long timeToFirstByteEstimateUs = this.bandwidthMeter.getTimeToFirstByteEstimateUs();
        if (timeToFirstByteEstimateUs == C.TIME_UNSET || j10 == C.TIME_UNSET) {
            return (long) (j11 / this.playbackSpeed);
        }
        float f10 = j10;
        return (long) ((j11 * Math.max((f10 / this.playbackSpeed) - timeToFirstByteEstimateUs, 0.0f)) / f10);
    }

    private long minDurationForQualityIncreaseUs(long j10, long j11) {
        if (j10 == C.TIME_UNSET) {
            return this.minDurationForQualityIncreaseUs;
        }
        if (j11 != C.TIME_UNSET) {
            j10 -= j11;
        }
        return Math.min((long) (j10 * this.bufferedFractionToLiveEdgeForQualityIncrease), this.minDurationForQualityIncreaseUs);
    }

    public boolean canSelectFormat(Format format, int i10, long j10) {
        return ((long) i10) <= j10;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void disable() {
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = C.TIME_UNSET;
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long j10, List<? extends MediaChunk> list) {
        int i10;
        int i11;
        long jElapsedRealtime = this.clock.elapsedRealtime();
        if (!shouldEvaluateQueueSize(jElapsedRealtime, list)) {
            return list.size();
        }
        this.lastBufferEvaluationMs = jElapsedRealtime;
        this.lastBufferEvaluationMediaChunk = list.isEmpty() ? null : (MediaChunk) a0.j(list);
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(list.get(size - 1).startTimeUs - j10, this.playbackSpeed);
        long minDurationToRetainAfterDiscardUs = getMinDurationToRetainAfterDiscardUs();
        if (playoutDurationForMediaDuration >= minDurationToRetainAfterDiscardUs) {
            Format format = getFormat(determineIdealSelectedIndex(jElapsedRealtime, getLastChunkDurationUs(list)));
            for (int i12 = 0; i12 < size; i12++) {
                MediaChunk mediaChunk = list.get(i12);
                Format format2 = mediaChunk.trackFormat;
                if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j10, this.playbackSpeed) >= minDurationToRetainAfterDiscardUs && format2.bitrate < format.bitrate && (i10 = format2.height) != -1 && i10 <= this.maxHeightToDiscard && (i11 = format2.width) != -1 && i11 <= this.maxWidthToDiscard && i10 < format.height) {
                    return i12;
                }
            }
        }
        return size;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public long getLatestBitrateEstimate() {
        return this.latestBitrateEstimate;
    }

    public long getMinDurationToRetainAfterDiscardUs() {
        return this.minDurationToRetainAfterDiscardUs;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public Object getSelectionData() {
        return null;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float f10) {
        this.playbackSpeed = f10;
    }

    public boolean shouldEvaluateQueueSize(long j10, List<? extends MediaChunk> list) {
        long j11 = this.lastBufferEvaluationMs;
        if (j11 == C.TIME_UNSET || j10 - j11 >= 1000) {
            return true;
        }
        return (list.isEmpty() || ((MediaChunk) a0.j(list)).equals(this.lastBufferEvaluationMediaChunk)) ? false : true;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void updateSelectedTrack(long j10, long j11, long j12, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long nextChunkDurationUs = getNextChunkDurationUs(mediaChunkIteratorArr, list);
        int i10 = this.reason;
        if (i10 == 0) {
            this.reason = 1;
            this.selectedIndex = determineIdealSelectedIndex(jElapsedRealtime, nextChunkDurationUs);
            return;
        }
        int i11 = this.selectedIndex;
        int iIndexOf = list.isEmpty() ? -1 : indexOf(((MediaChunk) a0.j(list)).trackFormat);
        if (iIndexOf != -1) {
            i10 = ((MediaChunk) a0.j(list)).trackSelectionReason;
            i11 = iIndexOf;
        }
        int iDetermineIdealSelectedIndex = determineIdealSelectedIndex(jElapsedRealtime, nextChunkDurationUs);
        if (iDetermineIdealSelectedIndex != i11 && !isTrackExcluded(i11, jElapsedRealtime)) {
            Format format = getFormat(i11);
            Format format2 = getFormat(iDetermineIdealSelectedIndex);
            long jMinDurationForQualityIncreaseUs = minDurationForQualityIncreaseUs(j12, nextChunkDurationUs);
            int i12 = format2.bitrate;
            int i13 = format.bitrate;
            if ((i12 > i13 && j11 < jMinDurationForQualityIncreaseUs) || (i12 < i13 && j11 >= this.maxDurationForQualityDecreaseUs)) {
                iDetermineIdealSelectedIndex = i11;
            }
        }
        if (iDetermineIdealSelectedIndex != i11) {
            i10 = 3;
        }
        this.reason = i10;
        this.selectedIndex = iDetermineIdealSelectedIndex;
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i10, BandwidthMeter bandwidthMeter, long j10, long j11, long j12, int i11, int i12, float f10, float f11, List<AdaptationCheckpoint> list, Clock clock) {
        long j13;
        super(trackGroup, iArr, i10);
        if (j12 < j10) {
            Log.w(TAG, "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
            j13 = j10;
        } else {
            j13 = j12;
        }
        this.bandwidthMeter = bandwidthMeter;
        this.minDurationForQualityIncreaseUs = j10 * 1000;
        this.maxDurationForQualityDecreaseUs = j11 * 1000;
        this.minDurationToRetainAfterDiscardUs = j13 * 1000;
        this.maxWidthToDiscard = i11;
        this.maxHeightToDiscard = i12;
        this.bandwidthFraction = f10;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f11;
        this.adaptationCheckpoints = h1.n(list);
        this.clock = clock;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = C.TIME_UNSET;
        this.latestBitrateEstimate = -2147483647L;
    }
}
