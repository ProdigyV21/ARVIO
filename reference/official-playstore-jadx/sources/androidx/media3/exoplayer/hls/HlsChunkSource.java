package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.DataChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
class HlsChunkSource {
    public static final int CHUNK_PUBLICATION_STATE_PRELOAD = 0;
    public static final int CHUNK_PUBLICATION_STATE_PUBLISHED = 1;
    public static final int CHUNK_PUBLICATION_STATE_REMOVED = 2;
    private static final int KEY_CACHE_SIZE = 4;
    private final CmcdConfiguration cmcdConfiguration;
    private final DataSource encryptionDataSource;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isPrimaryTimestampSource;
    private Uri lastPlaylistErrorUrl;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private Uri nextChunkStuckOnPlaylistUrl;
    private final PlayerId playerId;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private ExoTrackSelection trackSelection;
    private long lastChunkRequestRealtimeMs = C.TIME_UNSET;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache(4);
    private byte[] scratchSpace = Util.EMPTY_BYTE_ARRAY;
    private long liveEdgeInPeriodTimeUs = C.TIME_UNSET;

    public static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i10, Object obj, byte[] bArr) {
            super(dataSource, dataSpec, 3, format, i10, obj, bArr);
        }

        @Override // androidx.media3.exoplayer.source.chunk.DataChunk
        public void consume(byte[] bArr, int i10) {
            this.result = Arrays.copyOf(bArr, i10);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    public static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final String playlistBaseUri;
        private final List<HlsMediaPlaylist.SegmentBase> segmentBases;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(String str, long j10, List<HlsMediaPlaylist.SegmentBase> list) {
            super(0L, list.size() - 1);
            this.playlistBaseUri = str;
            this.startOfPlaylistInPeriodUs = j10;
            this.segmentBases = list;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return this.startOfPlaylistInPeriodUs + segmentBase.relativeStartTimeUs + segmentBase.durationUs;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + this.segmentBases.get((int) getCurrentIndex()).relativeStartTimeUs;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return new DataSpec(UriUtil.resolveToUri(this.playlistBaseUri, segmentBase.url), segmentBase.byteRangeOffset, segmentBase.byteRangeLength);
        }
    }

    public static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(iArr[0]));
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
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j10, long j11, long j12, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (isTrackExcluded(this.selectedIndex, jElapsedRealtime)) {
                for (int i10 = this.length - 1; i10 >= 0; i10--) {
                    if (!isTrackExcluded(i10, jElapsedRealtime)) {
                        this.selectedIndex = i10;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }
    }

    public static final class SegmentBaseHolder {
        public final boolean isPreload;
        public final long mediaSequence;
        public final int partIndex;
        public final HlsMediaPlaylist.SegmentBase segmentBase;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase segmentBase, long j10, int i10) {
            this.segmentBase = segmentBase;
            this.mediaSequence = j10;
            this.partIndex = i10;
            this.isPreload = (segmentBase instanceof HlsMediaPlaylist.Part) && ((HlsMediaPlaylist.Part) segmentBase).isPreload;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, Uri[] uriArr, Format[] formatArr, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider, long j10, List<Format> list, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.playlistUrls = uriArr;
        this.playlistFormats = formatArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider;
        this.timestampAdjusterInitializationTimeoutMs = j10;
        this.muxedCaptionFormats = list;
        this.playerId = playerId;
        this.cmcdConfiguration = cmcdConfiguration;
        DataSource dataSourceCreateDataSource = hlsDataSourceFactory.createDataSource(1);
        this.mediaDataSource = dataSourceCreateDataSource;
        if (transferListener != null) {
            dataSourceCreateDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < uriArr.length; i10++) {
            if ((formatArr[i10].roleFlags & 16384) == 0) {
                arrayList.add(Integer.valueOf(i10));
            }
        }
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, f0.O(arrayList));
    }

    private void deactivatePlaylistForSelectedTrack() {
        this.playlistTracker.deactivatePlaylistForPlayback(this.playlistUrls[this.trackSelection.getSelectedIndexInTrackGroup()]);
    }

    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.SegmentBase segmentBase) {
        String str;
        if (segmentBase == null || (str = segmentBase.fullSegmentEncryptionKeyUri) == null) {
            return null;
        }
        return UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, str);
    }

    private Pair<Long, Integer> getNextMediaSequenceAndPartIndex(HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j10, long j11) {
        List<HlsMediaPlaylist.Part> list;
        if (hlsMediaChunk != null && !z) {
            if (!hlsMediaChunk.isLoadCompleted()) {
                return new Pair<>(Long.valueOf(hlsMediaChunk.chunkIndex), Integer.valueOf(hlsMediaChunk.partIndex));
            }
            Long lValueOf = Long.valueOf(hlsMediaChunk.partIndex == -1 ? hlsMediaChunk.getNextChunkIndex() : hlsMediaChunk.chunkIndex);
            int i10 = hlsMediaChunk.partIndex;
            return new Pair<>(lValueOf, Integer.valueOf(i10 != -1 ? i10 + 1 : -1));
        }
        long j12 = hlsMediaPlaylist.durationUs + j10;
        if (hlsMediaChunk != null && !this.independentSegments) {
            j11 = hlsMediaChunk.startTimeUs;
        }
        if (!hlsMediaPlaylist.hasEndTag && j11 >= j12) {
            return new Pair<>(Long.valueOf(hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size())), -1);
        }
        long j13 = j11 - j10;
        int i11 = 0;
        int iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) hlsMediaPlaylist.segments, Long.valueOf(j13), true, !this.playlistTracker.isLive() || hlsMediaChunk == null);
        long j14 = ((long) iBinarySearchFloor) + hlsMediaPlaylist.mediaSequence;
        if (!this.playlistTracker.isLive()) {
            return new Pair<>(Long.valueOf(j14), -1);
        }
        if (iBinarySearchFloor >= 0) {
            if (hlsMediaPlaylist.segments.isEmpty()) {
                list = hlsMediaPlaylist.trailingParts;
            } else {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(iBinarySearchFloor);
                list = j13 < segment.relativeStartTimeUs + segment.durationUs ? segment.parts : hlsMediaPlaylist.trailingParts;
            }
            while (true) {
                if (i11 >= list.size()) {
                    break;
                }
                HlsMediaPlaylist.Part part = list.get(i11);
                if (j13 >= part.relativeStartTimeUs + part.durationUs) {
                    i11++;
                } else if (part.isIndependent) {
                    j14 += (list != hlsMediaPlaylist.trailingParts || hlsMediaPlaylist.segments.isEmpty()) ? 0L : 1L;
                    i = i11;
                }
            }
        }
        return new Pair<>(Long.valueOf(j14), Integer.valueOf(i));
    }

    private static SegmentBaseHolder getNextSegmentHolder(HlsMediaPlaylist hlsMediaPlaylist, long j10, int i10) {
        int i11 = (int) (j10 - hlsMediaPlaylist.mediaSequence);
        if (i11 == hlsMediaPlaylist.segments.size()) {
            if (i10 == -1) {
                i10 = 0;
            }
            if (i10 < hlsMediaPlaylist.trailingParts.size()) {
                return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(i10), j10, i10);
            }
            return null;
        }
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i11);
        if (i10 == -1) {
            return new SegmentBaseHolder(segment, j10, -1);
        }
        if (i10 < segment.parts.size()) {
            return new SegmentBaseHolder(segment.parts.get(i10), j10, i10);
        }
        int i12 = i11 + 1;
        if (i12 < hlsMediaPlaylist.segments.size()) {
            return new SegmentBaseHolder(hlsMediaPlaylist.segments.get(i12), j10 + 1, -1);
        }
        if (hlsMediaPlaylist.trailingParts.isEmpty()) {
            return null;
        }
        return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(0), j10 + 1, 0);
    }

    public static List<HlsMediaPlaylist.SegmentBase> getSegmentBaseList(HlsMediaPlaylist hlsMediaPlaylist, long j10, int i10) {
        int i11 = (int) (j10 - hlsMediaPlaylist.mediaSequence);
        if (i11 < 0 || hlsMediaPlaylist.segments.size() < i11) {
            f1 f1Var = h1.f14020l;
            return o3.f14078o;
        }
        ArrayList arrayList = new ArrayList();
        if (i11 < hlsMediaPlaylist.segments.size()) {
            if (i10 != -1) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i11);
                if (i10 == 0) {
                    arrayList.add(segment);
                } else if (i10 < segment.parts.size()) {
                    List<HlsMediaPlaylist.Part> list = segment.parts;
                    arrayList.addAll(list.subList(i10, list.size()));
                }
                i11++;
            }
            List<HlsMediaPlaylist.Segment> list2 = hlsMediaPlaylist.segments;
            arrayList.addAll(list2.subList(i11, list2.size()));
            i10 = 0;
        }
        if (hlsMediaPlaylist.partTargetDurationUs != C.TIME_UNSET) {
            int i12 = i10 != -1 ? i10 : 0;
            if (i12 < hlsMediaPlaylist.trailingParts.size()) {
                List<HlsMediaPlaylist.Part> list3 = hlsMediaPlaylist.trailingParts;
                arrayList.addAll(list3.subList(i12, list3.size()));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static boolean isIndependent(SegmentBaseHolder segmentBaseHolder, HlsMediaPlaylist hlsMediaPlaylist) {
        HlsMediaPlaylist.SegmentBase segmentBase = segmentBaseHolder.segmentBase;
        if (!(segmentBase instanceof HlsMediaPlaylist.Part)) {
            return hlsMediaPlaylist.hasIndependentSegments;
        }
        if (((HlsMediaPlaylist.Part) segmentBase).isIndependent) {
            return true;
        }
        return segmentBaseHolder.partIndex == 0 && hlsMediaPlaylist.hasIndependentSegments;
    }

    private Chunk maybeCreateEncryptionChunkFor(Uri uri, int i10, boolean z, CmcdData.Factory factory) {
        if (uri == null) {
            return null;
        }
        byte[] bArrRemove = this.keyCache.remove(uri);
        if (bArrRemove != null) {
            this.keyCache.put(uri, bArrRemove);
            return null;
        }
        DataSpec dataSpecBuild = new DataSpec.Builder().setUri(uri).setFlags(1).build();
        if (factory != null) {
            if (z) {
                factory.setObjectType(CmcdData.OBJECT_TYPE_INIT_SEGMENT);
            }
            dataSpecBuild = factory.createCmcdData().addToDataSpec(dataSpecBuild);
        }
        return new EncryptionKeyChunk(this.encryptionDataSource, dataSpecBuild, this.playlistFormats[i10], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private long resolveTimeToLiveEdgeUs(long j10) {
        long j11 = this.liveEdgeInPeriodTimeUs;
        return j11 != C.TIME_UNSET ? j11 - j10 : C.TIME_UNSET;
    }

    private static boolean shouldForceKeepCurrentTrackSelection(boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j10, int i10, HlsMediaChunk hlsMediaChunk, long j11, long j12) {
        if (!z || hlsMediaChunk == null) {
            return false;
        }
        if (j10 < hlsMediaPlaylist.mediaSequence) {
            return true;
        }
        SegmentBaseHolder nextSegmentHolder = getNextSegmentHolder(hlsMediaPlaylist, j10, i10);
        return nextSegmentHolder != null && j11 + nextSegmentHolder.segmentBase.relativeStartTimeUs < j12;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeInPeriodTimeUs = hlsMediaPlaylist.hasEndTag ? C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
    }

    public MediaChunkIterator[] createMediaChunkIterators(HlsMediaChunk hlsMediaChunk, long j10) {
        int iIndexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        int length = this.trackSelection.length();
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[length];
        for (int i10 = 0; i10 < length; i10++) {
            int indexInTrackGroup = this.trackSelection.getIndexInTrackGroup(i10);
            Uri uri = this.playlistUrls[indexInTrackGroup];
            if (this.playlistTracker.isSnapshotValid(uri)) {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri, false);
                playlistSnapshot.getClass();
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, indexInTrackGroup != iIndexOf, playlistSnapshot, initialStartTimeUs, j10);
                mediaChunkIteratorArr[i10] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot.baseUri, initialStartTimeUs, getSegmentBaseList(playlistSnapshot, ((Long) nextMediaSequenceAndPartIndex.first).longValue(), ((Integer) nextMediaSequenceAndPartIndex.second).intValue()));
            } else {
                mediaChunkIteratorArr[i10] = MediaChunkIterator.EMPTY;
            }
        }
        return mediaChunkIteratorArr;
    }

    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        int selectedIndex = this.trackSelection.getSelectedIndex();
        Uri[] uriArr = this.playlistUrls;
        HlsMediaPlaylist playlistSnapshot = (selectedIndex >= uriArr.length || selectedIndex == -1) ? null : this.playlistTracker.getPlaylistSnapshot(uriArr[this.trackSelection.getSelectedIndexInTrackGroup()], true);
        if (playlistSnapshot == null || playlistSnapshot.segments.isEmpty()) {
            return j10;
        }
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long j11 = j10 - initialStartTimeUs;
        int iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) playlistSnapshot.segments, Long.valueOf(j11), true, true);
        long j12 = playlistSnapshot.segments.get(iBinarySearchFloor).relativeStartTimeUs;
        return seekParameters.resolveSeekPositionUs(j11, j12, (!playlistSnapshot.hasIndependentSegments || iBinarySearchFloor == playlistSnapshot.segments.size() - 1) ? j12 : playlistSnapshot.segments.get(iBinarySearchFloor + 1).relativeStartTimeUs) + initialStartTimeUs;
    }

    public int getChunkPublicationState(HlsMediaChunk hlsMediaChunk) {
        if (hlsMediaChunk.partIndex == -1) {
            return 1;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackGroup.indexOf(hlsMediaChunk.trackFormat)], false);
        playlistSnapshot.getClass();
        int i10 = (int) (hlsMediaChunk.chunkIndex - playlistSnapshot.mediaSequence);
        if (i10 < 0) {
            return 1;
        }
        List<HlsMediaPlaylist.Part> list = i10 < playlistSnapshot.segments.size() ? playlistSnapshot.segments.get(i10).parts : playlistSnapshot.trailingParts;
        if (hlsMediaChunk.partIndex >= list.size()) {
            return 2;
        }
        HlsMediaPlaylist.Part part = list.get(hlsMediaChunk.partIndex);
        if (part.isPreload) {
            return 0;
        }
        return Objects.equals(Uri.parse(UriUtil.resolve(playlistSnapshot.baseUri, part.url)), hlsMediaChunk.dataSpec.uri) ? 1 : 2;
    }

    public void getNextChunk(LoadingInfo loadingInfo, long j10, long j11, List<HlsMediaChunk> list, boolean z, HlsChunkHolder hlsChunkHolder) {
        boolean z5;
        int i10;
        long j12;
        long j13;
        boolean z10;
        HlsMediaChunk hlsMediaChunk = list.isEmpty() ? null : (HlsMediaChunk) a0.j(list);
        int iIndexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        long j14 = loadingInfo.playbackPositionUs;
        long jMax = j10 - j14;
        long jResolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j14);
        if (hlsMediaChunk != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            jMax = Math.max(0L, jMax - durationUs);
            if (jResolveTimeToLiveEdgeUs != C.TIME_UNSET) {
                jResolveTimeToLiveEdgeUs = Math.max(0L, jResolveTimeToLiveEdgeUs - durationUs);
            }
        }
        long j15 = jMax;
        this.trackSelection.updateSelectedTrack(j14, j15, jResolveTimeToLiveEdgeUs, list, createMediaChunkIterators(hlsMediaChunk, j10));
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z11 = iIndexOf != selectedIndexInTrackGroup;
        Uri uri = this.playlistUrls[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(uri)) {
            hlsChunkHolder.playlistUrl = uri;
            this.nextChunkStuckOnPlaylistUrl = uri;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri, true);
        playlistSnapshot.getClass();
        this.independentSegments = playlistSnapshot.hasIndependentSegments;
        updateLiveEdgeTimeUs(playlistSnapshot);
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        Uri uri2 = uri;
        Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, z11, playlistSnapshot, initialStartTimeUs, j10);
        long jLongValue = ((Long) nextMediaSequenceAndPartIndex.first).longValue();
        int iIntValue = ((Integer) nextMediaSequenceAndPartIndex.second).intValue();
        int i11 = iIndexOf;
        HlsMediaPlaylist hlsMediaPlaylist = playlistSnapshot;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        long j16 = initialStartTimeUs;
        int i12 = selectedIndexInTrackGroup;
        long j17 = j15;
        long j18 = jLongValue;
        if (shouldForceKeepCurrentTrackSelection(z11, hlsMediaPlaylist, j18, iIntValue, hlsMediaChunk2, j16, j11)) {
            uri2 = this.playlistUrls[i11];
            HlsMediaPlaylist playlistSnapshot2 = this.playlistTracker.getPlaylistSnapshot(uri2, true);
            playlistSnapshot2.getClass();
            long initialStartTimeUs2 = playlistSnapshot2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            i10 = i11;
            Pair<Long, Integer> nextMediaSequenceAndPartIndex2 = getNextMediaSequenceAndPartIndex(hlsMediaChunk2, false, playlistSnapshot2, initialStartTimeUs2, j10);
            long jLongValue2 = ((Long) nextMediaSequenceAndPartIndex2.first).longValue();
            iIntValue = ((Integer) nextMediaSequenceAndPartIndex2.second).intValue();
            hlsMediaPlaylist = playlistSnapshot2;
            i12 = i10;
            z5 = true;
            j18 = jLongValue2;
            j17 = j17;
            j16 = initialStartTimeUs2;
        } else {
            z5 = true;
            i10 = i11;
        }
        if (i12 != i10 && i10 != -1) {
            this.playlistTracker.deactivatePlaylistForPlayback(this.playlistUrls[i10]);
        }
        if (j18 < hlsMediaPlaylist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        SegmentBaseHolder nextSegmentHolder = getNextSegmentHolder(hlsMediaPlaylist, j18, iIntValue);
        if (nextSegmentHolder != null) {
            j12 = j17;
            j13 = 1;
        } else if (!hlsMediaPlaylist.hasEndTag) {
            hlsChunkHolder.playlistUrl = uri2;
            this.nextChunkStuckOnPlaylistUrl = uri2;
            return;
        } else if (z || hlsMediaPlaylist.segments.isEmpty()) {
            hlsChunkHolder.endOfStream = z5;
            return;
        } else {
            j13 = 1;
            j12 = j17;
            nextSegmentHolder = new SegmentBaseHolder((HlsMediaPlaylist.SegmentBase) a0.j(hlsMediaPlaylist.segments), (hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size())) - 1, -1);
        }
        CmcdData.Factory chunkDurationUs = null;
        this.nextChunkStuckOnPlaylistUrl = null;
        if (this.cmcdConfiguration != null) {
            z10 = z5;
            chunkDurationUs = new CmcdData.Factory(this.cmcdConfiguration, CmcdData.STREAMING_FORMAT_HLS).setTrackSelection(this.trackSelection).setBufferedDurationUs(Math.max(0L, j12)).setPlaybackRate(loadingInfo.playbackSpeed).setIsLive(hlsMediaPlaylist.hasEndTag ^ z10).setDidRebuffer(loadingInfo.rebufferedSince(this.lastChunkRequestRealtimeMs)).setIsBufferEmpty(list.isEmpty()).setChunkDurationUs(nextSegmentHolder.segmentBase.durationUs);
            int i13 = nextSegmentHolder.partIndex;
            SegmentBaseHolder nextSegmentHolder2 = getNextSegmentHolder(hlsMediaPlaylist, i13 == -1 ? nextSegmentHolder.mediaSequence + j13 : nextSegmentHolder.mediaSequence, i13 == -1 ? -1 : i13 + 1);
            if (nextSegmentHolder2 != null) {
                chunkDurationUs.setNextObjectRequest(UriUtil.getRelativePath(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, nextSegmentHolder.segmentBase.url), UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, nextSegmentHolder2.segmentBase.url)));
                String strJ = a0.c.j(nextSegmentHolder2.segmentBase.byteRangeOffset, "-", new StringBuilder());
                if (nextSegmentHolder2.segmentBase.byteRangeLength != -1) {
                    StringBuilder sbS = androidx.compose.material3.d.s(strJ);
                    HlsMediaPlaylist.SegmentBase segmentBase = nextSegmentHolder2.segmentBase;
                    sbS.append(segmentBase.byteRangeOffset + segmentBase.byteRangeLength);
                    strJ = sbS.toString();
                }
                chunkDurationUs.setNextRangeRequest(strJ);
            }
        } else {
            z10 = z5;
        }
        CmcdData.Factory factory = chunkDurationUs;
        this.lastChunkRequestRealtimeMs = SystemClock.elapsedRealtime();
        Uri fullEncryptionKeyUri = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase.initializationSegment);
        Chunk chunkMaybeCreateEncryptionChunkFor = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri, i12, z10, factory);
        hlsChunkHolder.chunk = chunkMaybeCreateEncryptionChunkFor;
        if (chunkMaybeCreateEncryptionChunkFor == null) {
            Uri fullEncryptionKeyUri2 = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase);
            Chunk chunkMaybeCreateEncryptionChunkFor2 = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri2, i12, false, factory);
            hlsChunkHolder.chunk = chunkMaybeCreateEncryptionChunkFor2;
            if (chunkMaybeCreateEncryptionChunkFor2 == null) {
                boolean zIsIndependent = isIndependent(nextSegmentHolder, hlsMediaPlaylist);
                int i14 = i12;
                SegmentBaseHolder segmentBaseHolder = nextSegmentHolder;
                Uri uri3 = uri2;
                long j19 = j16;
                boolean zShouldSpliceIn = HlsMediaChunk.shouldSpliceIn(hlsMediaChunk2, j10, uri3, zIsIndependent, segmentBaseHolder, j19);
                if (zShouldSpliceIn && segmentBaseHolder.isPreload) {
                    return;
                }
                hlsChunkHolder.chunk = HlsMediaChunk.createInstance(this.extractorFactory, this.mediaDataSource, this.playlistFormats[i14], j19, hlsMediaPlaylist, segmentBaseHolder, uri3, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.isPrimaryTimestampSource, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, hlsMediaChunk2, this.keyCache.get(fullEncryptionKeyUri2), this.keyCache.get(fullEncryptionKeyUri), zShouldSpliceIn, zIsIndependent, this.playerId, factory);
            }
        }
    }

    public int getPreferredQueueSize(long j10, List<? extends MediaChunk> list) {
        return (this.fatalError != null || this.trackSelection.length() < 2) ? list.size() : this.trackSelection.evaluateQueueSize(j10, list);
    }

    public long getPublishedPartDurationUs(HlsMediaChunk hlsMediaChunk) {
        ac.b.s(hlsMediaChunk.partIndex != -1);
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackGroup.indexOf(hlsMediaChunk.trackFormat)], false);
        playlistSnapshot.getClass();
        int i10 = (int) (hlsMediaChunk.chunkIndex - playlistSnapshot.mediaSequence);
        if (i10 < 0) {
            return 0L;
        }
        return (i10 < playlistSnapshot.segments.size() ? playlistSnapshot.segments.get(i10).parts : playlistSnapshot.trailingParts).get(hlsMediaChunk.partIndex).durationUs;
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public ExoTrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public boolean hasIndependentSegments() {
        return this.independentSegments;
    }

    public boolean maybeExcludeTrack(Chunk chunk, long j10) {
        ExoTrackSelection exoTrackSelection = this.trackSelection;
        return exoTrackSelection.excludeTrack(exoTrackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j10);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        Uri uri = this.lastPlaylistErrorUrl;
        if (uri == null || !uri.equals(this.nextChunkStuckOnPlaylistUrl)) {
            return;
        }
        this.playlistTracker.maybeThrowPlaylistRefreshError(this.lastPlaylistErrorUrl);
    }

    public boolean obtainsChunksForPlaylist(Uri uri) {
        return Util.contains(this.playlistUrls, uri);
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            FullSegmentEncryptionKeyCache fullSegmentEncryptionKeyCache = this.keyCache;
            Uri uri = encryptionKeyChunk.dataSpec.uri;
            byte[] result = encryptionKeyChunk.getResult();
            result.getClass();
            fullSegmentEncryptionKeyCache.put(uri, result);
        }
    }

    public boolean onPlaylistError(Uri uri, long j10) {
        int iIndexOf;
        int i10 = 0;
        while (true) {
            Uri[] uriArr = this.playlistUrls;
            if (i10 >= uriArr.length) {
                i10 = -1;
                break;
            }
            if (uriArr[i10].equals(uri)) {
                break;
            }
            i10++;
        }
        if (i10 == -1 || (iIndexOf = this.trackSelection.indexOf(i10)) == -1) {
            return true;
        }
        this.lastPlaylistErrorUrl = uri;
        return j10 != C.TIME_UNSET && this.trackSelection.excludeTrack(iIndexOf, j10) && this.playlistTracker.excludeMediaPlaylist(uri, j10);
    }

    public void reset() {
        deactivatePlaylistForSelectedTrack();
        this.fatalError = null;
    }

    public void setIsPrimaryTimestampSource(boolean z) {
        this.isPrimaryTimestampSource = z;
    }

    public void setTrackSelection(ExoTrackSelection exoTrackSelection) {
        deactivatePlaylistForSelectedTrack();
        this.trackSelection = exoTrackSelection;
    }

    public boolean shouldCancelLoad(long j10, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j10, chunk, list);
    }
}
