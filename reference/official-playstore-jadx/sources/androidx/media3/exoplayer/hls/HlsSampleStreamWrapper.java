package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseIntArray;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsChunkSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.id3.PrivFrame;
import com.google.common.collect.a0;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import j$.util.Objects;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m2.f0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
final class HlsSampleStreamWrapper implements Loader.Callback<Chunk>, Loader.ReleaseCallback, SequenceableLoader, ExtractorOutput, SampleQueue.UpstreamFormatChangedListener {
    private static final Set<Integer> MAPPABLE_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(1, 2, 5)));
    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL = -2;
    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL = -3;
    public static final int SAMPLE_QUEUE_INDEX_PENDING = -1;
    private static final String TAG = "HlsSampleStreamWrapper";
    private final Allocator allocator;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    private Format downstreamTrackFormat;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private DrmInitData drmInitData;
    private final DrmSessionManager drmSessionManager;
    private TrackOutput emsgUnwrappingTrackOutput;
    private int enabledTrackGroupCount;
    private final Handler handler;
    private boolean haveAudioVideoSampleQueues;
    private final ArrayList<HlsSampleStream> hlsSampleStreams;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private Chunk loadingChunk;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final ArrayList<HlsMediaChunk> mediaChunks;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final int metadataType;
    private final Format muxedAudioFormat;
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder;
    private final Runnable onTracksEndedRunnable;
    private Set<TrackGroup> optionalTrackGroups;
    private final Map<String, DrmInitData> overridingDrmInitData;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primarySampleQueueIndex;
    private int primarySampleQueueType;
    private int primaryTrackGroupIndex;
    private final List<HlsMediaChunk> readOnlyMediaChunks;
    private boolean released;
    private long sampleOffsetUs;
    private SparseIntArray sampleQueueIndicesByType;
    private boolean[] sampleQueueIsAudioVideoFlags;
    private Set<Integer> sampleQueueMappingDoneByType;
    private int[] sampleQueueTrackIds;
    private HlsSampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private boolean[] sampleQueuesEnabledStates;
    private boolean seenFirstTrackSelection;
    private HlsMediaChunk sourceChunk;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private final String uid;
    private Format upstreamTrackFormat;

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(Uri uri);

        void onPrepared();
    }

    public static class EmsgUnwrappingTrackOutput implements TrackOutput {
        private byte[] buffer;
        private int bufferPosition;
        private final TrackOutput delegate;
        private final Format delegateFormat;
        private final EventMessageDecoder emsgDecoder = new EventMessageDecoder();
        private Format format;
        private static final Format ID3_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_ID3).build();
        private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();

        public EmsgUnwrappingTrackOutput(TrackOutput trackOutput, int i10) {
            this.delegate = trackOutput;
            if (i10 == 1) {
                this.delegateFormat = ID3_FORMAT;
            } else {
                if (i10 != 3) {
                    throw new IllegalArgumentException(a0.c.i(i10, "Unknown metadataType: "));
                }
                this.delegateFormat = EMSG_FORMAT;
            }
            this.buffer = new byte[0];
            this.bufferPosition = 0;
        }

        private boolean emsgContainsExpectedWrappedFormat(EventMessage eventMessage) {
            Format wrappedMetadataFormat = eventMessage.getWrappedMetadataFormat();
            return wrappedMetadataFormat != null && Objects.equals(this.delegateFormat.sampleMimeType, wrappedMetadataFormat.sampleMimeType);
        }

        private void ensureBufferCapacity(int i10) {
            byte[] bArr = this.buffer;
            if (bArr.length < i10) {
                this.buffer = Arrays.copyOf(bArr, (i10 / 2) + i10);
            }
        }

        private ParsableByteArray getSampleAndTrimBuffer(int i10, int i11) {
            int i12 = this.bufferPosition - i11;
            ParsableByteArray parsableByteArray = new ParsableByteArray(Arrays.copyOfRange(this.buffer, i12 - i10, i12));
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, i12, bArr, 0, i11);
            this.bufferPosition = i11;
            return parsableByteArray;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ void durationUs(long j10) {
            androidx.media3.extractor.e.a(this, j10);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format format) {
            this.format = format;
            this.delegate.format(this.delegateFormat);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ int sampleData(DataReader dataReader, int i10, boolean z) {
            return androidx.media3.extractor.e.b(this, dataReader, i10, z);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
            this.format.getClass();
            ParsableByteArray sampleAndTrimBuffer = getSampleAndTrimBuffer(i11, i12);
            if (!Objects.equals(this.format.sampleMimeType, this.delegateFormat.sampleMimeType)) {
                if (!MimeTypes.APPLICATION_EMSG.equals(this.format.sampleMimeType)) {
                    Log.w(HlsSampleStreamWrapper.TAG, "Ignoring sample for unsupported format: " + this.format.sampleMimeType);
                    return;
                }
                EventMessage eventMessageDecode = this.emsgDecoder.decode(sampleAndTrimBuffer);
                if (!emsgContainsExpectedWrappedFormat(eventMessageDecode)) {
                    Log.w(HlsSampleStreamWrapper.TAG, "Ignoring EMSG. Expected it to contain wrapped " + this.delegateFormat.sampleMimeType + " but actual wrapped format: " + eventMessageDecode.getWrappedMetadataFormat());
                    return;
                }
                byte[] wrappedMetadataBytes = eventMessageDecode.getWrappedMetadataBytes();
                wrappedMetadataBytes.getClass();
                sampleAndTrimBuffer = new ParsableByteArray(wrappedMetadataBytes);
            }
            int iBytesLeft = sampleAndTrimBuffer.bytesLeft();
            this.delegate.sampleData(sampleAndTrimBuffer, iBytesLeft);
            this.delegate.sampleMetadata(j10, i10, iBytesLeft, 0, cryptoData);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i10) {
            androidx.media3.extractor.e.c(this, parsableByteArray, i10);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i10, boolean z, int i11) throws IOException {
            ensureBufferCapacity(this.bufferPosition + i10);
            int i12 = dataReader.read(this.buffer, this.bufferPosition, i10);
            if (i12 != -1) {
                this.bufferPosition += i12;
                return i12;
            }
            if (z) {
                return -1;
            }
            throw new EOFException();
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i10, int i11) {
            ensureBufferCapacity(this.bufferPosition + i10);
            parsableByteArray.readBytes(this.buffer, this.bufferPosition, i10);
            this.bufferPosition += i10;
        }
    }

    public static final class HlsSampleQueue extends SampleQueue {
        private DrmInitData drmInitData;
        private final Map<String, DrmInitData> overridingDrmInitData;

        private Metadata getAdjustedMetadata(Metadata metadata) {
            if (metadata == null) {
                return null;
            }
            int length = metadata.length();
            int i10 = 0;
            int i11 = 0;
            while (true) {
                if (i11 >= length) {
                    i11 = -1;
                    break;
                }
                Metadata.Entry entry = metadata.get(i11);
                if ((entry instanceof PrivFrame) && HlsMediaChunk.PRIV_TIMESTAMP_FRAME_OWNER.equals(((PrivFrame) entry).owner)) {
                    break;
                }
                i11++;
            }
            if (i11 == -1) {
                return metadata;
            }
            if (length == 1) {
                return null;
            }
            Metadata.Entry[] entryArr = new Metadata.Entry[length - 1];
            while (i10 < length) {
                if (i10 != i11) {
                    entryArr[i10 < i11 ? i10 : i10 - 1] = metadata.get(i10);
                }
                i10++;
            }
            return new Metadata(entryArr);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue
        public Format getAdjustedUpstreamFormat(Format format) {
            DrmInitData drmInitData;
            DrmInitData drmInitData2 = this.drmInitData;
            if (drmInitData2 == null) {
                drmInitData2 = format.drmInitData;
            }
            if (drmInitData2 != null && (drmInitData = this.overridingDrmInitData.get(drmInitData2.schemeType)) != null) {
                drmInitData2 = drmInitData;
            }
            Metadata adjustedMetadata = getAdjustedMetadata(format.metadata);
            if (drmInitData2 != format.drmInitData || adjustedMetadata != format.metadata) {
                format = format.buildUpon().setDrmInitData(drmInitData2).setMetadata(adjustedMetadata).build();
            }
            return super.getAdjustedUpstreamFormat(format);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
            super.sampleMetadata(j10, i10, i11, i12, cryptoData);
        }

        public void setDrmInitData(DrmInitData drmInitData) {
            this.drmInitData = drmInitData;
            invalidateUpstreamFormatAdjustment();
        }

        public void setSourceChunk(HlsMediaChunk hlsMediaChunk) {
            sourceId(hlsMediaChunk.uid);
        }

        private HlsSampleQueue(Allocator allocator, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, Map<String, DrmInitData> map) {
            super(allocator, drmSessionManager, eventDispatcher);
            this.overridingDrmInitData = map;
        }
    }

    public HlsSampleStreamWrapper(String str, int i10, Callback callback, HlsChunkSource hlsChunkSource, Map<String, DrmInitData> map, Allocator allocator, long j10, Format format, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, int i11, ReleasableExecutor releasableExecutor) {
        this.uid = str;
        this.trackType = i10;
        this.callback = callback;
        this.chunkSource = hlsChunkSource;
        this.overridingDrmInitData = map;
        this.allocator = allocator;
        this.muxedAudioFormat = format;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.metadataType = i11;
        this.loader = releasableExecutor != null ? new Loader(releasableExecutor) : new Loader("Loader:HlsSampleStreamWrapper");
        this.nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
        this.sampleQueueTrackIds = new int[0];
        Set<Integer> set = MAPPABLE_TYPES;
        this.sampleQueueMappingDoneByType = new HashSet(set.size());
        this.sampleQueueIndicesByType = new SparseIntArray(set.size());
        this.sampleQueues = new HlsSampleQueue[0];
        this.sampleQueueIsAudioVideoFlags = new boolean[0];
        this.sampleQueuesEnabledStates = new boolean[0];
        ArrayList<HlsMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        this.hlsSampleStreams = new ArrayList<>();
        this.maybeFinishPrepareRunnable = new m(this, 0);
        this.onTracksEndedRunnable = new m(this, 1);
        this.handler = Util.createHandlerForCurrentLooper();
        this.lastSeekPositionUs = j10;
        this.pendingResetPositionUs = j10;
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups"})
    private void assertIsPrepared() {
        ac.b.s(this.prepared);
        this.trackGroups.getClass();
        this.optionalTrackGroups.getClass();
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups", "trackGroupToSampleQueueIndex"})
    private void buildTracksFromSampleStreams() {
        Format format;
        int length = this.sampleQueues.length;
        int i10 = -2;
        int i11 = -1;
        int i12 = 0;
        while (true) {
            if (i12 >= length) {
                break;
            }
            Format upstreamFormat = this.sampleQueues[i12].getUpstreamFormat();
            upstreamFormat.getClass();
            String str = upstreamFormat.sampleMimeType;
            int i13 = MimeTypes.isVideo(str) ? 2 : MimeTypes.isAudio(str) ? 1 : MimeTypes.isText(str) ? 3 : -2;
            if (getTrackTypeScore(i13) > getTrackTypeScore(i10)) {
                i11 = i12;
                i10 = i13;
            } else if (i13 == i10 && i11 != -1) {
                i11 = -1;
            }
            i12++;
        }
        TrackGroup trackGroup = this.chunkSource.getTrackGroup();
        int i14 = trackGroup.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupToSampleQueueIndex = new int[length];
        for (int i15 = 0; i15 < length; i15++) {
            this.trackGroupToSampleQueueIndex[i15] = i15;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        int i16 = 0;
        while (i16 < length) {
            Format upstreamFormat2 = this.sampleQueues[i16].getUpstreamFormat();
            upstreamFormat2.getClass();
            if (i16 == i11) {
                Format[] formatArr = new Format[i14];
                for (int i17 = 0; i17 < i14; i17++) {
                    Format format2 = trackGroup.getFormat(i17);
                    if (i10 == 1 && (format = this.muxedAudioFormat) != null) {
                        format2 = format2.withManifestFormatInfo(format);
                    }
                    formatArr[i17] = i14 == 1 ? upstreamFormat2.withManifestFormatInfo(format2) : deriveFormat(format2, upstreamFormat2, true);
                }
                trackGroupArr[i16] = new TrackGroup(this.uid, formatArr);
                this.primaryTrackGroupIndex = i16;
            } else {
                Format format3 = (i10 == 2 && MimeTypes.isAudio(upstreamFormat2.sampleMimeType)) ? this.muxedAudioFormat : null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.uid);
                sb2.append(":muxed:");
                sb2.append(i16 < i11 ? i16 : i16 - 1);
                trackGroupArr[i16] = new TrackGroup(sb2.toString(), deriveFormat(format3, upstreamFormat2, false));
            }
            i16++;
        }
        this.trackGroups = createTrackGroupArrayWithDrmInfo(trackGroupArr);
        ac.b.s(this.optionalTrackGroups == null);
        this.optionalTrackGroups = Collections.EMPTY_SET;
    }

    private boolean canDiscardUpstreamMediaChunksFromIndex(int i10) {
        for (int i11 = i10; i11 < this.mediaChunks.size(); i11++) {
            if (this.mediaChunks.get(i11).shouldSpliceIn()) {
                return false;
            }
        }
        HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(i10);
        for (int i12 = 0; i12 < this.sampleQueues.length; i12++) {
            if (this.sampleQueues[i12].getReadIndex() > hlsMediaChunk.getFirstSampleIndex(i12)) {
                return false;
            }
        }
        return true;
    }

    private static DiscardingTrackOutput createDiscardingTrackOutput(int i10, int i11) {
        Log.w(TAG, "Unmapped track with id " + i10 + " of type " + i11);
        return new DiscardingTrackOutput();
    }

    private SampleQueue createSampleQueue(int i10, int i11) {
        int length = this.sampleQueues.length;
        boolean z = true;
        if (i11 != 1 && i11 != 2) {
            z = false;
        }
        HlsSampleQueue hlsSampleQueue = new HlsSampleQueue(this.allocator, this.drmSessionManager, this.drmEventDispatcher, this.overridingDrmInitData);
        hlsSampleQueue.setStartTimeUs(this.lastSeekPositionUs);
        if (z) {
            hlsSampleQueue.setDrmInitData(this.drmInitData);
        }
        hlsSampleQueue.setSampleOffsetUs(this.sampleOffsetUs);
        HlsMediaChunk hlsMediaChunk = this.sourceChunk;
        if (hlsMediaChunk != null) {
            hlsSampleQueue.setSourceChunk(hlsMediaChunk);
        }
        hlsSampleQueue.setUpstreamFormatChangeListener(this);
        int i12 = length + 1;
        int[] iArrCopyOf = Arrays.copyOf(this.sampleQueueTrackIds, i12);
        this.sampleQueueTrackIds = iArrCopyOf;
        iArrCopyOf[length] = i10;
        this.sampleQueues = (HlsSampleQueue[]) Util.nullSafeArrayAppend(this.sampleQueues, hlsSampleQueue);
        boolean[] zArrCopyOf = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, i12);
        this.sampleQueueIsAudioVideoFlags = zArrCopyOf;
        zArrCopyOf[length] = z;
        this.haveAudioVideoSampleQueues |= z;
        this.sampleQueueMappingDoneByType.add(Integer.valueOf(i11));
        this.sampleQueueIndicesByType.append(i11, length);
        if (getTrackTypeScore(i11) > getTrackTypeScore(this.primarySampleQueueType)) {
            this.primarySampleQueueIndex = length;
            this.primarySampleQueueType = i11;
        }
        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, i12);
        return hlsSampleQueue;
    }

    private TrackGroupArray createTrackGroupArrayWithDrmInfo(TrackGroup[] trackGroupArr) {
        for (int i10 = 0; i10 < trackGroupArr.length; i10++) {
            TrackGroup trackGroup = trackGroupArr[i10];
            Format[] formatArr = new Format[trackGroup.length];
            for (int i11 = 0; i11 < trackGroup.length; i11++) {
                Format format = trackGroup.getFormat(i11);
                formatArr[i11] = format.copyWithCryptoType(this.drmSessionManager.getCryptoType(format));
            }
            trackGroupArr[i10] = new TrackGroup(trackGroup.id, formatArr);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    private static Format deriveFormat(Format format, Format format2, boolean z) {
        String codecsCorrespondingToMimeType;
        String mediaMimeType;
        if (format == null) {
            return format2;
        }
        int trackType = MimeTypes.getTrackType(format2.sampleMimeType);
        if (Util.getCodecCountOfType(format.codecs, trackType) == 1) {
            codecsCorrespondingToMimeType = Util.getCodecsOfType(format.codecs, trackType);
            mediaMimeType = MimeTypes.getMediaMimeType(codecsCorrespondingToMimeType);
        } else {
            codecsCorrespondingToMimeType = MimeTypes.getCodecsCorrespondingToMimeType(format.codecs, format2.sampleMimeType);
            mediaMimeType = format2.sampleMimeType;
        }
        Format.Builder codecs = format2.buildUpon().setId(format.id).setLabel(format.label).setLabels(format.labels).setLanguage(format.language).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).setAverageBitrate(z ? format.averageBitrate : -1).setPeakBitrate(z ? format.peakBitrate : -1).setCodecs(codecsCorrespondingToMimeType);
        if (trackType == 2) {
            codecs.setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate);
        }
        if (mediaMimeType != null) {
            codecs.setSampleMimeType(mediaMimeType);
        }
        int i10 = format.channelCount;
        if (i10 != -1 && trackType == 1) {
            codecs.setChannelCount(i10);
        }
        Metadata metadataCopyWithAppendedEntriesFrom = format.metadata;
        if (metadataCopyWithAppendedEntriesFrom != null) {
            Metadata metadata = format2.metadata;
            if (metadata != null) {
                metadataCopyWithAppendedEntriesFrom = metadata.copyWithAppendedEntriesFrom(metadataCopyWithAppendedEntriesFrom);
            }
            codecs.setMetadata(metadataCopyWithAppendedEntriesFrom);
        }
        return codecs.build();
    }

    private void discardUpstream(int i10) {
        ac.b.s(!this.loader.isLoading());
        while (true) {
            if (i10 >= this.mediaChunks.size()) {
                i10 = -1;
                break;
            } else if (canDiscardUpstreamMediaChunksFromIndex(i10)) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 == -1) {
            return;
        }
        long j10 = getLastMediaChunk().endTimeUs;
        HlsMediaChunk hlsMediaChunkDiscardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(i10);
        if (this.mediaChunks.isEmpty()) {
            this.pendingResetPositionUs = this.lastSeekPositionUs;
        } else {
            ((HlsMediaChunk) a0.j(this.mediaChunks)).invalidateExtractor();
        }
        this.loadingFinished = false;
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primarySampleQueueType, hlsMediaChunkDiscardUpstreamMediaChunksFromIndex.startTimeUs, j10);
    }

    private HlsMediaChunk discardUpstreamMediaChunksFromIndex(int i10) {
        HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(i10);
        ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i10, arrayList.size());
        for (int i11 = 0; i11 < this.sampleQueues.length; i11++) {
            this.sampleQueues[i11].discardUpstreamSamples(hlsMediaChunk.getFirstSampleIndex(i11));
        }
        return hlsMediaChunk;
    }

    private boolean finishedReadingChunk(HlsMediaChunk hlsMediaChunk) {
        int i10 = hlsMediaChunk.uid;
        int length = this.sampleQueues.length;
        for (int i11 = 0; i11 < length; i11++) {
            if (this.sampleQueuesEnabledStates[i11] && this.sampleQueues[i11].peekSourceId() == i10) {
                return false;
            }
        }
        return true;
    }

    private static boolean formatsMatch(Format format, Format format2) {
        String str = format.sampleMimeType;
        String str2 = format2.sampleMimeType;
        int trackType = MimeTypes.getTrackType(str);
        if (trackType != 3) {
            return trackType == MimeTypes.getTrackType(str2);
        }
        if (Objects.equals(str, str2)) {
            return !(MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str)) || format.accessibilityChannel == format2.accessibilityChannel;
        }
        return false;
    }

    private HlsMediaChunk getLastMediaChunk() {
        return (HlsMediaChunk) a0.c.h(1, this.mediaChunks);
    }

    private TrackOutput getMappedTrackOutput(int i10, int i11) {
        ac.b.j(MAPPABLE_TYPES.contains(Integer.valueOf(i11)));
        int i12 = this.sampleQueueIndicesByType.get(i11, -1);
        if (i12 == -1) {
            return null;
        }
        if (this.sampleQueueMappingDoneByType.add(Integer.valueOf(i11))) {
            this.sampleQueueTrackIds[i12] = i10;
        }
        return this.sampleQueueTrackIds[i12] == i10 ? this.sampleQueues[i12] : createDiscardingTrackOutput(i10, i11);
    }

    private static int getTrackTypeScore(int i10) {
        if (i10 == 1) {
            return 2;
        }
        if (i10 != 2) {
            return i10 != 3 ? 0 : 1;
        }
        return 3;
    }

    private void initMediaChunkLoad(HlsMediaChunk hlsMediaChunk) {
        this.sourceChunk = hlsMediaChunk;
        this.upstreamTrackFormat = hlsMediaChunk.trackFormat;
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.mediaChunks.add(hlsMediaChunk);
        e1 e1VarK = h1.k();
        for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
            e1VarK.c(Integer.valueOf(hlsSampleQueue.getWriteIndex()));
        }
        hlsMediaChunk.init(this, e1VarK.f());
        for (HlsSampleQueue hlsSampleQueue2 : this.sampleQueues) {
            hlsSampleQueue2.setSourceChunk(hlsMediaChunk);
            if (hlsMediaChunk.shouldSpliceIn()) {
                hlsSampleQueue2.splice();
            }
        }
    }

    private static boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPlaylistUpdated$0(HlsMediaChunk hlsMediaChunk) {
        this.callback.onPlaylistRefreshRequired(hlsMediaChunk.playlistUrl);
    }

    @EnsuresNonNull({"trackGroupToSampleQueueIndex"})
    @RequiresNonNull({"trackGroups"})
    private void mapSampleQueuesToMatchTrackGroups() {
        int i10 = this.trackGroups.length;
        int[] iArr = new int[i10];
        this.trackGroupToSampleQueueIndex = iArr;
        Arrays.fill(iArr, -1);
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = 0;
            while (true) {
                HlsSampleQueue[] hlsSampleQueueArr = this.sampleQueues;
                if (i12 < hlsSampleQueueArr.length) {
                    Format upstreamFormat = hlsSampleQueueArr[i12].getUpstreamFormat();
                    upstreamFormat.getClass();
                    if (formatsMatch(upstreamFormat, this.trackGroups.get(i11).getFormat(0))) {
                        this.trackGroupToSampleQueueIndex[i11] = i12;
                        break;
                    }
                    i12++;
                }
            }
        }
        Iterator<HlsSampleStream> it = this.hlsSampleStreams.iterator();
        while (it.hasNext()) {
            it.next().bindSampleQueue();
        }
    }

    private void maybeDiscardUpstreamForNewMediaChunk(HlsMediaChunk hlsMediaChunk) {
        if (this.mediaChunks.isEmpty()) {
            return;
        }
        if (!getLastMediaChunk().isPublished()) {
            discardUpstream(this.mediaChunks.size() - 1);
        }
        if (hlsMediaChunk.isIndependent && hlsMediaChunk.shouldSpliceIn()) {
            for (int size = this.mediaChunks.size() - 1; size >= 0; size--) {
                long j10 = this.mediaChunks.get(size).startTimeUs;
                long j11 = hlsMediaChunk.startTimeUs;
                if (j10 < j11) {
                    return;
                }
                if (j10 == j11 && canDiscardUpstreamMediaChunksFromIndex(size)) {
                    discardUpstream(size);
                    hlsMediaChunk.clearShouldSpliceIn();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (!this.released && this.trackGroupToSampleQueueIndex == null && this.sampleQueuesBuilt) {
            for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                if (hlsSampleQueue.getUpstreamFormat() == null) {
                    return;
                }
            }
            if (this.trackGroups != null) {
                mapSampleQueuesToMatchTrackGroups();
                return;
            }
            buildTracksFromSampleStreams();
            setIsPrepared();
            this.callback.onPrepared();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTracksEnded() {
        this.sampleQueuesBuilt = true;
        maybeFinishPrepare();
    }

    private void resetSampleQueues() {
        for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
            hlsSampleQueue.reset(this.pendingResetUpstreamFormats);
        }
        this.pendingResetUpstreamFormats = false;
    }

    private boolean seekInsideBufferUs(long j10, HlsMediaChunk hlsMediaChunk) {
        boolean zSeekTo;
        int length = this.sampleQueues.length;
        int i10 = 0;
        while (true) {
            boolean z = true;
            if (i10 >= length) {
                return true;
            }
            HlsSampleQueue hlsSampleQueue = this.sampleQueues[i10];
            if (hlsMediaChunk != null) {
                zSeekTo = hlsSampleQueue.seekTo(hlsMediaChunk.getFirstSampleIndex(i10));
            } else {
                long nextLoadPositionUs = getNextLoadPositionUs();
                if (nextLoadPositionUs != Long.MIN_VALUE && j10 >= nextLoadPositionUs) {
                    z = false;
                }
                zSeekTo = hlsSampleQueue.seekTo(j10, z);
            }
            if (!zSeekTo && (this.sampleQueueIsAudioVideoFlags[i10] || !this.haveAudioVideoSampleQueues)) {
                break;
            }
            i10++;
        }
        return false;
    }

    @RequiresNonNull({"trackGroups", "optionalTrackGroups"})
    private void setIsPrepared() {
        this.prepared = true;
    }

    private void updateSampleStreams(SampleStream[] sampleStreamArr) {
        this.hlsSampleStreams.clear();
        for (SampleStream sampleStream : sampleStreamArr) {
            if (sampleStream != null) {
                this.hlsSampleStreams.add((HlsSampleStream) sampleStream);
            }
        }
    }

    public int bindSampleQueueToSampleStream(int i10) {
        assertIsPrepared();
        this.trackGroupToSampleQueueIndex.getClass();
        int i11 = this.trackGroupToSampleQueueIndex[i10];
        if (i11 == -1) {
            return this.optionalTrackGroups.contains(this.trackGroups.get(i10)) ? -3 : -2;
        }
        boolean[] zArr = this.sampleQueuesEnabledStates;
        if (zArr[i11]) {
            return -2;
        }
        zArr[i11] = true;
        return i11;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        List<HlsMediaChunk> list;
        long j10;
        long j11;
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        if (isPendingReset()) {
            List<HlsMediaChunk> list2 = Collections.EMPTY_LIST;
            long j12 = this.pendingResetPositionUs;
            for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                hlsSampleQueue.setStartTimeUs(this.pendingResetPositionUs);
            }
            list = list2;
            j10 = j12;
            j11 = j10;
        } else {
            List<HlsMediaChunk> list3 = this.readOnlyMediaChunks;
            HlsMediaChunk lastMediaChunk = getLastMediaChunk();
            long publishedEndTimeUs = (lastMediaChunk.isLoadCompleted() && lastMediaChunk.isPublished()) ? lastMediaChunk.getPublishedEndTimeUs() : Math.max(this.lastSeekPositionUs, lastMediaChunk.startTimeUs);
            long jMax = this.lastSeekPositionUs;
            if (this.sampleQueuesBuilt) {
                for (HlsSampleQueue hlsSampleQueue2 : this.sampleQueues) {
                    jMax = Math.max(jMax, hlsSampleQueue2.getLargestReadTimestampUs());
                }
            }
            list = list3;
            j10 = publishedEndTimeUs;
            j11 = jMax;
        }
        this.nextChunkHolder.clear();
        this.chunkSource.getNextChunk(loadingInfo, j10, j11, list, this.prepared || !list.isEmpty(), this.nextChunkHolder);
        HlsChunkSource.HlsChunkHolder hlsChunkHolder = this.nextChunkHolder;
        boolean z = hlsChunkHolder.endOfStream;
        Chunk chunk = hlsChunkHolder.chunk;
        Uri uri = hlsChunkHolder.playlistUrl;
        if (z) {
            this.pendingResetPositionUs = C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            if (uri != null) {
                this.callback.onPlaylistRefreshRequired(uri);
            }
            return false;
        }
        if (isMediaChunk(chunk)) {
            HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) chunk;
            maybeDiscardUpstreamForNewMediaChunk(hlsMediaChunk);
            initMediaChunkLoad(hlsMediaChunk);
        }
        this.loadingChunk = chunk;
        this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type));
        return true;
    }

    public void continuePreparing() {
        if (this.prepared) {
            return;
        }
        continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
    }

    public void discardBuffer(long j10, boolean z) {
        if (!this.sampleQueuesBuilt || isPendingReset()) {
            return;
        }
        int length = this.sampleQueues.length;
        for (int i10 = 0; i10 < length; i10++) {
            this.sampleQueues[i10].discardTo(j10, z, this.sampleQueuesEnabledStates[i10]);
        }
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
    }

    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j10, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long jMax = this.lastSeekPositionUs;
        HlsMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            lastMediaChunk = this.mediaChunks.size() > 1 ? (HlsMediaChunk) a0.c.h(2, this.mediaChunks) : null;
        }
        if (lastMediaChunk != null) {
            jMax = Math.max(jMax, lastMediaChunk.endTimeUs);
        }
        if (this.sampleQueuesBuilt) {
            for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                jMax = Math.max(jMax, hlsSampleQueue.getLargestQueuedTimestampUs());
            }
        }
        return jMax;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    public int getPrimaryTrackGroupIndex() {
        return this.primaryTrackGroupIndex;
    }

    public TrackGroupArray getTrackGroups() {
        assertIsPrepared();
        return this.trackGroups;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isReady(int i10) {
        return !isPendingReset() && this.sampleQueues[i10].isReady(this.loadingFinished);
    }

    public boolean isVideoSampleStream() {
        return this.primarySampleQueueType == 2;
    }

    public void maybeThrowError(int i10) throws IOException {
        maybeThrowError();
        this.sampleQueues[i10].maybeThrowError();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", null);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
            hlsSampleQueue.release();
        }
    }

    public void onNewExtractor() {
        this.sampleQueueMappingDoneByType.clear();
    }

    public boolean onPlaylistError(Uri uri, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, boolean z) {
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelectionFor;
        if (this.chunkSource.obtainsChunksForPlaylist(uri)) {
            return this.chunkSource.onPlaylistError(uri, (z || (fallbackSelectionFor = this.loadErrorHandlingPolicy.getFallbackSelectionFor(TrackSelectionUtil.createFallbackOptions(this.chunkSource.getTrackSelection()), loadErrorInfo)) == null || fallbackSelectionFor.type != 2) ? C.TIME_UNSET : fallbackSelectionFor.exclusionDurationMs);
        }
        return true;
    }

    public void onPlaylistUpdated() {
        if (this.mediaChunks.isEmpty()) {
            return;
        }
        final HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) a0.j(this.mediaChunks);
        int chunkPublicationState = this.chunkSource.getChunkPublicationState(hlsMediaChunk);
        if (chunkPublicationState == 1) {
            if (hlsMediaChunk.isPublished()) {
                return;
            }
            hlsMediaChunk.publish(this.chunkSource.getPublishedPartDurationUs(hlsMediaChunk));
        } else if (chunkPublicationState == 0) {
            this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.hls.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3666i.lambda$onPlaylistUpdated$0(hlsMediaChunk);
                }
            });
        } else if (chunkPublicationState == 2 && !this.loadingFinished && this.loader.isLoading()) {
            this.loader.cancelLoading();
        }
    }

    @Override // androidx.media3.exoplayer.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepareWithMultivariantPlaylistInfo(TrackGroup[] trackGroupArr, int i10, int... iArr) {
        this.trackGroups = createTrackGroupArrayWithDrmInfo(trackGroupArr);
        this.optionalTrackGroups = new HashSet();
        for (int i11 : iArr) {
            this.optionalTrackGroups.add(this.trackGroups.get(i11));
        }
        this.primaryTrackGroupIndex = i10;
        Handler handler = this.handler;
        Callback callback = this.callback;
        Objects.requireNonNull(callback);
        handler.post(new m(callback, 2));
        setIsPrepared();
    }

    public int readData(int i10, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i11) {
        Format format;
        if (isPendingReset()) {
            return -3;
        }
        int i12 = 0;
        if (!this.mediaChunks.isEmpty()) {
            int i13 = 0;
            while (i13 < this.mediaChunks.size() - 1 && finishedReadingChunk(this.mediaChunks.get(i13))) {
                i13++;
            }
            Util.removeRange(this.mediaChunks, 0, i13);
            HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(0);
            Format format2 = hlsMediaChunk.trackFormat;
            if (!format2.equals(this.downstreamTrackFormat)) {
                this.mediaSourceEventDispatcher.downstreamFormatChanged(this.trackType, format2, hlsMediaChunk.trackSelectionReason, hlsMediaChunk.trackSelectionData, hlsMediaChunk.startTimeUs);
            }
            this.downstreamTrackFormat = format2;
        }
        if (!this.mediaChunks.isEmpty() && !this.mediaChunks.get(0).isPublished()) {
            return -3;
        }
        int i14 = this.sampleQueues[i10].read(formatHolder, decoderInputBuffer, i11, this.loadingFinished);
        if (i14 == -5) {
            Format formatWithManifestFormatInfo = formatHolder.format;
            formatWithManifestFormatInfo.getClass();
            if (i10 == this.primarySampleQueueIndex) {
                int iF = f0.f(this.sampleQueues[i10].peekSourceId());
                while (i12 < this.mediaChunks.size() && this.mediaChunks.get(i12).uid != iF) {
                    i12++;
                }
                if (i12 < this.mediaChunks.size()) {
                    format = this.mediaChunks.get(i12).trackFormat;
                } else {
                    format = this.upstreamTrackFormat;
                    format.getClass();
                }
                formatWithManifestFormatInfo = formatWithManifestFormatInfo.withManifestFormatInfo(format);
            }
            formatHolder.format = formatWithManifestFormatInfo;
        }
        return i14;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
        if (this.loader.hasFatalError() || isPendingReset()) {
            return;
        }
        if (this.loader.isLoading()) {
            this.loadingChunk.getClass();
            if (this.chunkSource.shouldCancelLoad(j10, this.loadingChunk, this.readOnlyMediaChunks)) {
                this.loader.cancelLoading();
                return;
            }
            return;
        }
        int size = this.readOnlyMediaChunks.size();
        while (size > 0 && this.chunkSource.getChunkPublicationState(this.readOnlyMediaChunks.get(size - 1)) == 2) {
            size--;
        }
        if (size < this.readOnlyMediaChunks.size()) {
            discardUpstream(size);
        }
        int preferredQueueSize = this.chunkSource.getPreferredQueueSize(j10, this.readOnlyMediaChunks);
        if (preferredQueueSize < this.mediaChunks.size()) {
            discardUpstream(preferredQueueSize);
        }
    }

    public void release() {
        if (this.prepared) {
            for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                hlsSampleQueue.preRelease();
            }
        }
        this.chunkSource.reset();
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
        this.hlsSampleStreams.clear();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
    }

    public boolean seekToUs(long j10, boolean z) {
        HlsMediaChunk hlsMediaChunk;
        this.lastSeekPositionUs = j10;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j10;
            return true;
        }
        if (this.chunkSource.hasIndependentSegments()) {
            for (int i10 = 0; i10 < this.mediaChunks.size(); i10++) {
                hlsMediaChunk = this.mediaChunks.get(i10);
                if (hlsMediaChunk.startTimeUs == j10) {
                    break;
                }
            }
            hlsMediaChunk = null;
        } else {
            hlsMediaChunk = null;
        }
        if (this.sampleQueuesBuilt && !z && !this.mediaChunks.isEmpty() && seekInsideBufferUs(j10, hlsMediaChunk)) {
            return false;
        }
        this.pendingResetPositionUs = j10;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            if (this.sampleQueuesBuilt) {
                for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                    hlsSampleQueue.discardToEnd();
                }
            }
            this.loader.cancelLoading();
        } else {
            this.loader.clearFatalError();
            resetSampleQueues();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r14, boolean[] r15, androidx.media3.exoplayer.source.SampleStream[] r16, boolean[] r17, long r18, boolean r20) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[], boolean[], androidx.media3.exoplayer.source.SampleStream[], boolean[], long, boolean):boolean");
    }

    public void setDrmInitData(DrmInitData drmInitData) {
        if (Objects.equals(this.drmInitData, drmInitData)) {
            return;
        }
        this.drmInitData = drmInitData;
        int i10 = 0;
        while (true) {
            HlsSampleQueue[] hlsSampleQueueArr = this.sampleQueues;
            if (i10 >= hlsSampleQueueArr.length) {
                return;
            }
            if (this.sampleQueueIsAudioVideoFlags[i10]) {
                hlsSampleQueueArr[i10].setDrmInitData(drmInitData);
            }
            i10++;
        }
    }

    public void setIsPrimaryTimestampSource(boolean z) {
        this.chunkSource.setIsPrimaryTimestampSource(z);
    }

    public void setSampleOffsetUs(long j10) {
        if (this.sampleOffsetUs != j10) {
            this.sampleOffsetUs = j10;
            for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
                hlsSampleQueue.setSampleOffsetUs(j10);
            }
        }
    }

    public int skipData(int i10, long j10) {
        if (isPendingReset()) {
            return 0;
        }
        HlsSampleQueue hlsSampleQueue = this.sampleQueues[i10];
        int skipCount = hlsSampleQueue.getSkipCount(j10, this.loadingFinished);
        HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) a0.k(this.mediaChunks);
        if (hlsMediaChunk != null && !hlsMediaChunk.isPublished()) {
            skipCount = Math.min(skipCount, hlsMediaChunk.getFirstSampleIndex(i10) - hlsSampleQueue.getReadIndex());
        }
        hlsSampleQueue.skip(skipCount);
        return skipCount;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i10, int i11) {
        TrackOutput trackOutputCreateSampleQueue;
        if (!MAPPABLE_TYPES.contains(Integer.valueOf(i11))) {
            int i12 = 0;
            while (true) {
                TrackOutput[] trackOutputArr = this.sampleQueues;
                if (i12 >= trackOutputArr.length) {
                    trackOutputCreateSampleQueue = null;
                    break;
                }
                if (this.sampleQueueTrackIds[i12] == i10) {
                    trackOutputCreateSampleQueue = trackOutputArr[i12];
                    break;
                }
                i12++;
            }
        } else {
            trackOutputCreateSampleQueue = getMappedTrackOutput(i10, i11);
        }
        if (trackOutputCreateSampleQueue == null) {
            if (this.tracksEnded) {
                return createDiscardingTrackOutput(i10, i11);
            }
            trackOutputCreateSampleQueue = createSampleQueue(i10, i11);
        }
        if (i11 != 5) {
            return trackOutputCreateSampleQueue;
        }
        if (this.emsgUnwrappingTrackOutput == null) {
            this.emsgUnwrappingTrackOutput = new EmsgUnwrappingTrackOutput(trackOutputCreateSampleQueue, this.metadataType);
        }
        return this.emsgUnwrappingTrackOutput;
    }

    public void unbindSampleQueue(int i10) {
        assertIsPrepared();
        this.trackGroupToSampleQueueIndex.getClass();
        int i11 = this.trackGroupToSampleQueueIndex[i10];
        ac.b.s(this.sampleQueuesEnabledStates[i11]);
        this.sampleQueuesEnabledStates[i11] = false;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j10, long j11, boolean z) {
        this.loadingChunk = null;
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
        if (z) {
            return;
        }
        if (isPendingReset() || this.enabledTrackGroupCount == 0) {
            resetSampleQueues();
        }
        if (this.enabledTrackGroupCount > 0) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j10, long j11) {
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(chunk);
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
        if (this.prepared) {
            this.callback.onContinueLoadingRequested(this);
        } else {
            continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public Loader.LoadErrorAction onLoadError(Chunk chunk, long j10, long j11, IOException iOException, int i10) {
        Loader.LoadErrorAction loadErrorActionCreateRetryAction;
        int i11;
        boolean zIsMediaChunk = isMediaChunk(chunk);
        if (zIsMediaChunk && !((HlsMediaChunk) chunk).isPublished() && (iOException instanceof HttpDataSource.InvalidResponseCodeException) && ((i11 = ((HttpDataSource.InvalidResponseCodeException) iOException).responseCode) == 410 || i11 == 404)) {
            return Loader.RETRY;
        }
        long jBytesLoaded = chunk.bytesLoaded();
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, jBytesLoaded);
        LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo = new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, Util.usToMs(chunk.startTimeUs), Util.usToMs(chunk.endTimeUs)), iOException, i10);
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelectionFor = this.loadErrorHandlingPolicy.getFallbackSelectionFor(TrackSelectionUtil.createFallbackOptions(this.chunkSource.getTrackSelection()), loadErrorInfo);
        boolean zMaybeExcludeTrack = (fallbackSelectionFor == null || fallbackSelectionFor.type != 2) ? false : this.chunkSource.maybeExcludeTrack(chunk, fallbackSelectionFor.exclusionDurationMs);
        if (zMaybeExcludeTrack) {
            if (zIsMediaChunk && jBytesLoaded == 0) {
                ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
                ac.b.s(arrayList.remove(arrayList.size() - 1) == chunk);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                } else {
                    ((HlsMediaChunk) a0.j(this.mediaChunks)).invalidateExtractor();
                }
            }
            loadErrorActionCreateRetryAction = Loader.DONT_RETRY;
        } else {
            long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(loadErrorInfo);
            loadErrorActionCreateRetryAction = retryDelayMsFor != C.TIME_UNSET ? Loader.createRetryAction(false, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
        }
        Loader.LoadErrorAction loadErrorAction = loadErrorActionCreateRetryAction;
        boolean zIsRetry = loadErrorAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, iOException, !zIsRetry);
        if (!zIsRetry) {
            this.loadingChunk = null;
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
        }
        if (zMaybeExcludeTrack) {
            if (!this.prepared) {
                continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
                return loadErrorAction;
            }
            this.callback.onContinueLoadingRequested(this);
        }
        return loadErrorAction;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadStarted(Chunk chunk, long j10, long j11, int i10) {
        this.mediaSourceEventDispatcher.loadStarted(i10 == 0 ? new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, j10) : new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded()), chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, i10);
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }
}
