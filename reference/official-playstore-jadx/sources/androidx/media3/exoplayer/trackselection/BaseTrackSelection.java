package androidx.media3.exoplayer.trackselection;

import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    private boolean playWhenReady;
    protected final int[] tracks;
    private final int type;

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        this(trackGroup, iArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$0(Format format, Format format2) {
        return format2.bitrate - format.bitrate;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void disable() {
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void enable() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
            if (this.group.equals(baseTrackSelection.group) && Arrays.equals(this.tracks, baseTrackSelection.tracks)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long j10, List<? extends MediaChunk> list) {
        return list.size();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean excludeTrack(int i10, long j10) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zIsTrackExcluded = isTrackExcluded(i10, jElapsedRealtime);
        int i11 = 0;
        while (i11 < this.length && !zIsTrackExcluded) {
            zIsTrackExcluded = (i11 == i10 || isTrackExcluded(i11, jElapsedRealtime)) ? false : true;
            i11++;
        }
        if (!zIsTrackExcluded) {
            return false;
        }
        long[] jArr = this.excludeUntilTimes;
        jArr[i10] = Math.max(jArr[i10], Util.addWithOverflowDefault(jElapsedRealtime, j10, Long.MAX_VALUE));
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final Format getFormat(int i10) {
        return this.formats[i10];
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int getIndexInTrackGroup(int i10) {
        return this.tracks[i10];
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public /* synthetic */ long getLatestBitrateEstimate() {
        return e.a(this);
    }

    public final boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.tracks) + (System.identityHashCode(this.group) * 31);
        }
        return this.hashCode;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int indexOf(Format format) {
        for (int i10 = 0; i10 < this.length; i10++) {
            if (this.formats[i10] == format) {
                return i10;
            }
        }
        return -1;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean isTrackExcluded(int i10, long j10) {
        return this.excludeUntilTimes[i10] > j10;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int length() {
        return this.tracks.length;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final /* synthetic */ void onDiscontinuity() {
        e.b(this);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlayWhenReadyChanged(boolean z) {
        this.playWhenReady = z;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float f10) {
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final /* synthetic */ void onRebuffer() {
        e.d(this);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final /* synthetic */ boolean shouldCancelChunkLoad(long j10, Chunk chunk, List list) {
        return e.e(this, j10, chunk, list);
    }

    public BaseTrackSelection(TrackGroup trackGroup, int[] iArr, int i10) {
        ac.b.s(iArr.length > 0);
        this.type = i10;
        trackGroup.getClass();
        this.group = trackGroup;
        int length = iArr.length;
        this.length = length;
        this.formats = new Format[length];
        for (int i11 = 0; i11 < iArr.length; i11++) {
            this.formats[i11] = trackGroup.getFormat(iArr[i11]);
        }
        Arrays.sort(this.formats, new a(6));
        this.tracks = new int[this.length];
        int i12 = 0;
        while (true) {
            int i13 = this.length;
            if (i12 >= i13) {
                this.excludeUntilTimes = new long[i13];
                this.playWhenReady = false;
                return;
            } else {
                this.tracks[i12] = trackGroup.indexOf(this.formats[i12]);
                i12++;
            }
        }
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int indexOf(int i10) {
        for (int i11 = 0; i11 < this.length; i11++) {
            if (this.tracks[i11] == i10) {
                return i11;
            }
        }
        return -1;
    }
}
