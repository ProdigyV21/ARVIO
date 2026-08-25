package androidx.media3.exoplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaDataSource;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.FileDescriptorDataSource;
import androidx.media3.datasource.MediaDataSourceAdapter;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.UnrecognizedInputFormatException;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes3.dex */
public class MediaExtractorCompatInternal {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private static final String TAG = "MediaExtractorCompatInt";
    private DataSource currentDataSource;
    private Extractor currentExtractor;
    private ExtractorInput currentExtractorInput;
    private final DataSource.Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;
    private boolean hasBeenPrepared;
    private Map<String, String> httpRequestHeaders;
    private LogSessionId logSessionId;
    private long offsetInCurrentFile;
    private SeekPoint pendingSeek;
    private SeekMap seekMap;
    private boolean tracksEnded;
    private int upstreamFormatsCount;
    private final PositionHolder positionHolder = new PositionHolder();
    private final Allocator allocator = new DefaultAllocator(true, 65536);
    private final ArrayList<MediaExtractorTrack> tracks = new ArrayList<>();
    private final SparseArray<MediaExtractorSampleQueue> sampleQueues = new SparseArray<>();
    private final SampleMetadataQueue sampleMetadataQueue = new SampleMetadataQueue();
    private final FormatHolder formatHolder = new FormatHolder();
    private final DecoderInputBuffer sampleHolderWithBufferReplacementDisabled = DecoderInputBuffer.newNoDataInstance();
    private final DecoderInputBuffer sampleHolderWithBufferReplacementEnabled = new DecoderInputBuffer(2);
    private final Set<Integer> selectedTrackIndices = new HashSet();

    public final class ExtractorOutputImpl implements ExtractorOutput {
        private ExtractorOutputImpl() {
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void endTracks() {
            MediaExtractorCompatInternal.this.tracksEnded = true;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void seekMap(SeekMap seekMap) {
            MediaExtractorCompatInternal.this.seekMap = seekMap;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public TrackOutput track(int i10, int i11) {
            MediaExtractorSampleQueue mediaExtractorSampleQueue = (MediaExtractorSampleQueue) MediaExtractorCompatInternal.this.sampleQueues.get(i10);
            if (mediaExtractorSampleQueue != null) {
                return mediaExtractorSampleQueue;
            }
            if (MediaExtractorCompatInternal.this.tracksEnded) {
                return new DiscardingTrackOutput();
            }
            MediaExtractorCompatInternal mediaExtractorCompatInternal = MediaExtractorCompatInternal.this;
            MediaExtractorSampleQueue mediaExtractorSampleQueue2 = mediaExtractorCompatInternal.new MediaExtractorSampleQueue(mediaExtractorCompatInternal.allocator, i10);
            MediaExtractorCompatInternal.this.sampleQueues.put(i10, mediaExtractorSampleQueue2);
            return mediaExtractorSampleQueue2;
        }
    }

    public final class MediaExtractorSampleQueue extends SampleQueue {
        private int compatibilityTrackIndex;
        private int mainTrackIndex;
        public long trackDurationUs;
        public final int trackId;

        public MediaExtractorSampleQueue(Allocator allocator, int i10) {
            super(allocator, null, null);
            this.trackId = i10;
            this.trackDurationUs = C.TIME_UNSET;
            this.mainTrackIndex = -1;
            this.compatibilityTrackIndex = -1;
        }

        private void queueSampleMetadata(long j10, int i10) {
            int i11 = ((1073741824 & i10) != 0 ? 2 : 0) | ((i10 & 1) != 0 ? 1 : 0);
            if (this.compatibilityTrackIndex != -1) {
                MediaExtractorCompatInternal.this.sampleMetadataQueue.addLast(j10, i11, this.compatibilityTrackIndex);
            }
            MediaExtractorCompatInternal.this.sampleMetadataQueue.addLast(j10, i11, this.mainTrackIndex);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void durationUs(long j10) {
            this.trackDurationUs = j10;
            androidx.media3.extractor.e.a(this, j10);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue
        public Format getAdjustedUpstreamFormat(Format format) {
            if (getUpstreamFormat() == null) {
                MediaExtractorCompatInternal.this.onSampleQueueFormatInitialized(this, format);
            }
            return super.getAdjustedUpstreamFormat(format);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
            int i13 = i10 & (-536870913);
            ac.b.s(this.mainTrackIndex != -1);
            int writeIndex = getWriteIndex();
            super.sampleMetadata(j10, i13, i11, i12, cryptoData);
            if (getWriteIndex() == writeIndex + 1) {
                queueSampleMetadata(j10, i13);
            }
        }

        public void setCompatibilityTrackIndex(int i10) {
            this.compatibilityTrackIndex = i10;
        }

        public void setMainTrackIndex(int i10) {
            this.mainTrackIndex = i10;
        }

        public String toString() {
            int i10 = this.trackId;
            int i11 = this.mainTrackIndex;
            int i12 = this.compatibilityTrackIndex;
            StringBuilder sbV = androidx.compose.foundation.c.v("trackId: ", i10, ", mainTrackIndex: ", i11, ", compatibilityTrackIndex: ");
            sbV.append(i12);
            return sbV.toString();
        }
    }

    public static final class MediaExtractorTrack {
        public final String compatibilityTrackMimeType;
        public final boolean isCompatibilityTrack;
        public final MediaExtractorSampleQueue sampleQueue;

        /* JADX INFO: Access modifiers changed from: private */
        public Format getFormat(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer) {
            formatHolder.clear();
            this.sampleQueue.read(formatHolder, decoderInputBuffer, 2, false);
            Format format = formatHolder.format;
            format.getClass();
            formatHolder.clear();
            return format;
        }

        public MediaFormat createDownstreamMediaFormat(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer) {
            Format format = getFormat(formatHolder, decoderInputBuffer);
            MediaFormat mediaFormatCreateMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format);
            if (this.compatibilityTrackMimeType != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    mediaFormatCreateMediaFormatFromFormat.removeKey("codecs-string");
                }
                mediaFormatCreateMediaFormatFromFormat.setString("mime", this.compatibilityTrackMimeType);
            }
            Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
            if (codecProfileAndLevel != null) {
                mediaFormatCreateMediaFormatFromFormat.setInteger("profile", ((Integer) codecProfileAndLevel.first).intValue());
                mediaFormatCreateMediaFormatFromFormat.setInteger("level", ((Integer) codecProfileAndLevel.second).intValue());
            }
            return mediaFormatCreateMediaFormatFromFormat;
        }

        public void discardFrontSample() {
            this.sampleQueue.skip(1);
            this.sampleQueue.discardToRead();
        }

        public int getIdOfBackingTrack() {
            return this.sampleQueue.trackId;
        }

        public String toString() {
            return "MediaExtractorSampleQueue: " + this.sampleQueue + ", isCompatibilityTrack: " + this.isCompatibilityTrack + ", compatibilityTrackMimeType: " + this.compatibilityTrackMimeType;
        }

        private MediaExtractorTrack(MediaExtractorSampleQueue mediaExtractorSampleQueue, boolean z, String str) {
            this.sampleQueue = mediaExtractorSampleQueue;
            this.isCompatibilityTrack = z;
            this.compatibilityTrackMimeType = str;
        }
    }

    public static final class SampleMetadataQueue {
        private final ArrayDeque<SampleMetadata> sampleMetadataPool = new ArrayDeque<>();
        private final ArrayDeque<SampleMetadata> sampleMetadataQueue = new ArrayDeque<>();

        public static final class SampleMetadata {
            public int flags;
            public long timeUs;
            public int trackIndex;

            public SampleMetadata(long j10, int i10, int i11) {
                set(j10, i10, i11);
            }

            public void set(long j10, int i10, int i11) {
                this.timeUs = j10;
                this.flags = i10;
                this.trackIndex = i11;
            }
        }

        private SampleMetadata obtainSampleMetadata(long j10, int i10, int i11) {
            SampleMetadata sampleMetadata = this.sampleMetadataPool.isEmpty() ? new SampleMetadata(j10, i10, i11) : this.sampleMetadataPool.pop();
            sampleMetadata.set(j10, i10, i11);
            return sampleMetadata;
        }

        public void addLast(long j10, int i10, int i11) {
            this.sampleMetadataQueue.addLast(obtainSampleMetadata(j10, i10, i11));
        }

        public void clear() {
            Iterator<SampleMetadata> it = this.sampleMetadataQueue.iterator();
            while (it.hasNext()) {
                this.sampleMetadataPool.push(it.next());
            }
            this.sampleMetadataQueue.clear();
        }

        public boolean isEmpty() {
            return this.sampleMetadataQueue.isEmpty();
        }

        public SampleMetadata peekFirst() {
            return this.sampleMetadataQueue.peekFirst();
        }

        public SampleMetadata removeFirst() {
            SampleMetadata sampleMetadataRemoveFirst = this.sampleMetadataQueue.removeFirst();
            this.sampleMetadataPool.push(sampleMetadataRemoveFirst);
            return sampleMetadataRemoveFirst;
        }
    }

    public MediaExtractorCompatInternal(ExtractorsFactory extractorsFactory, DataSource.Factory factory) {
        this.extractorsFactory = extractorsFactory;
        this.dataSourceFactory = factory;
    }

    @EnsuresNonNullIf(expression = {"sampleMetadataQueue.peekFirst()"}, result = true)
    private boolean advanceToSampleOrEndOfInput() {
        int i10;
        try {
            maybeResolvePendingSeek();
            boolean z = false;
            while (true) {
                if (!this.sampleMetadataQueue.isEmpty()) {
                    Set<Integer> set = this.selectedTrackIndices;
                    SampleMetadataQueue.SampleMetadata sampleMetadataPeekFirst = this.sampleMetadataQueue.peekFirst();
                    sampleMetadataPeekFirst.getClass();
                    if (set.contains(Integer.valueOf(sampleMetadataPeekFirst.trackIndex))) {
                        return true;
                    }
                    skipOneSample();
                } else {
                    if (z) {
                        return false;
                    }
                    try {
                        Extractor extractor = this.currentExtractor;
                        extractor.getClass();
                        ExtractorInput extractorInput = this.currentExtractorInput;
                        extractorInput.getClass();
                        i10 = extractor.read(extractorInput, this.positionHolder);
                    } catch (Exception | OutOfMemoryError e5) {
                        Log.w(TAG, "Treating exception as the end of input.", e5);
                    }
                    if (i10 == -1) {
                        z = true;
                    } else if (i10 == 1) {
                        this.currentExtractorInput = reopenCurrentDataSource(this.positionHolder.position);
                    }
                }
            }
        } catch (IOException e6) {
            Log.w(TAG, "Treating exception as the end of input.", e6);
            return false;
        }
    }

    private DataSpec buildDataSpec(Uri uri, long j10) {
        DataSpec.Builder flags = new DataSpec.Builder().setUri(uri).setPosition(j10).setFlags(6);
        Map<String, String> map = this.httpRequestHeaders;
        if (map != null) {
            flags.setHttpRequestHeaders(map);
        }
        return flags.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$selectExtractor$0(Extractor extractor) {
        return extractor.getUnderlyingImplementation().getClass().getSimpleName();
    }

    private void maybeResolvePendingSeek() throws IOException {
        SeekPoint seekPoint = this.pendingSeek;
        if (seekPoint == null) {
            return;
        }
        seekPoint.getClass();
        Extractor extractor = this.currentExtractor;
        extractor.getClass();
        extractor.seek(seekPoint.position, seekPoint.timeUs);
        this.currentExtractorInput = reopenCurrentDataSource(seekPoint.position);
        this.pendingSeek = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void onSampleQueueFormatInitialized(MediaExtractorSampleQueue mediaExtractorSampleQueue, Format format) {
        boolean z = true;
        this.upstreamFormatsCount++;
        mediaExtractorSampleQueue.setMainTrackIndex(this.tracks.size());
        Object[] objArr = 0;
        this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, false, null));
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType != null) {
            mediaExtractorSampleQueue.setCompatibilityTrackIndex(this.tracks.size());
            this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, z, alternativeCodecMimeType));
        }
    }

    private void peekNextSelectedTrackSample(DecoderInputBuffer decoderInputBuffer) {
        ArrayList<MediaExtractorTrack> arrayList = this.tracks;
        SampleMetadataQueue.SampleMetadata sampleMetadataPeekFirst = this.sampleMetadataQueue.peekFirst();
        sampleMetadataPeekFirst.getClass();
        MediaExtractorSampleQueue mediaExtractorSampleQueue = arrayList.get(sampleMetadataPeekFirst.trackIndex).sampleQueue;
        int i10 = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, 1, false);
        if (i10 == -5) {
            i10 = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, 1, false);
        }
        this.formatHolder.clear();
        ac.b.s(i10 == -4);
    }

    private void populatePlatformCryptoInfoParameters(MediaCodec.CryptoInfo cryptoInfo) {
        CryptoInfo cryptoInfo2 = this.sampleHolderWithBufferReplacementEnabled.cryptoInfo;
        cryptoInfo2.getClass();
        MediaCodec.CryptoInfo frameworkCryptoInfo = cryptoInfo2.getFrameworkCryptoInfo();
        cryptoInfo.numSubSamples = frameworkCryptoInfo.numSubSamples;
        cryptoInfo.numBytesOfClearData = frameworkCryptoInfo.numBytesOfClearData;
        cryptoInfo.numBytesOfEncryptedData = frameworkCryptoInfo.numBytesOfEncryptedData;
        cryptoInfo.key = frameworkCryptoInfo.key;
        cryptoInfo.iv = frameworkCryptoInfo.iv;
        cryptoInfo.mode = frameworkCryptoInfo.mode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void prepareDataSource(DataSource dataSource, DataSpec dataSpec) throws IOException {
        int i10;
        ac.b.s(!this.hasBeenPrepared);
        this.hasBeenPrepared = true;
        this.offsetInCurrentFile = dataSpec.position;
        this.currentDataSource = dataSource;
        ExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.currentDataSource, 0L, dataSource.open(dataSpec));
        Extractor extractorSelectExtractor = selectExtractor(defaultExtractorInput);
        Throwable e5 = null;
        extractorSelectExtractor.init(new ExtractorOutputImpl());
        boolean z = true;
        while (z) {
            try {
                i10 = extractorSelectExtractor.read(defaultExtractorInput, this.positionHolder);
            } catch (Exception | OutOfMemoryError e6) {
                e5 = e6;
                i10 = -1;
            }
            boolean z5 = !this.tracksEnded || this.upstreamFormatsCount < this.sampleQueues.size() || this.seekMap == null;
            if (e5 != null || (z5 && i10 == -1)) {
                release();
                throw ParserException.createForMalformedContainer(e5 != null ? "Exception encountered while parsing input media." : "Reached end of input before preparation completed.", e5);
            }
            if (i10 == 1) {
                defaultExtractorInput = reopenCurrentDataSource(this.positionHolder.position);
            }
            z = z5;
        }
        this.currentExtractorInput = defaultExtractorInput;
        this.currentExtractor = extractorSelectExtractor;
    }

    private ExtractorInput reopenCurrentDataSource(long j10) throws IOException {
        DataSource dataSource = this.currentDataSource;
        dataSource.getClass();
        Uri uri = dataSource.getUri();
        uri.getClass();
        DataSourceUtil.closeQuietly(dataSource);
        long jOpen = dataSource.open(buildDataSpec(uri, this.offsetInCurrentFile + j10));
        if (jOpen != -1) {
            jOpen += j10;
        }
        return new DefaultExtractorInput(dataSource, j10, jOpen);
    }

    private Extractor selectExtractor(ExtractorInput extractorInput) throws IOException {
        Extractor extractor;
        Extractor[] extractorArrCreateExtractors = this.extractorsFactory.createExtractors();
        int length = extractorArrCreateExtractors.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                extractor = null;
                break;
            }
            extractor = extractorArrCreateExtractors[i10];
            try {
                if (extractor.sniff(extractorInput)) {
                    extractorInput.resetPeekPosition();
                    break;
                }
            } catch (EOFException unused) {
            } catch (Throwable th) {
                extractorInput.resetPeekPosition();
                throw th;
            }
            extractorInput.resetPeekPosition();
            i10++;
        }
        if (extractor != null) {
            return extractor;
        }
        String str = "None of the available extractors (" + new com.google.common.base.o(", ").b(com.google.common.collect.a0.x(h1.p(extractorArrCreateExtractors), new v0(4))) + ") could read the stream.";
        DataSource dataSource = this.currentDataSource;
        dataSource.getClass();
        Uri uri = dataSource.getUri();
        uri.getClass();
        throw new UnrecognizedInputFormatException(str, uri, o3.f14078o);
    }

    private void skipOneSample() {
        MediaExtractorTrack mediaExtractorTrack = this.tracks.get(this.sampleMetadataQueue.removeFirst().trackIndex);
        if (mediaExtractorTrack.isCompatibilityTrack) {
            return;
        }
        mediaExtractorTrack.discardFrontSample();
    }

    public boolean advance() {
        if (!advanceToSampleOrEndOfInput()) {
            return false;
        }
        skipOneSample();
        return advanceToSampleOrEndOfInput();
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getCachedDuration() {
        if (!advanceToSampleOrEndOfInput()) {
            return 0L;
        }
        long jMax = Long.MIN_VALUE;
        long jMax2 = Long.MIN_VALUE;
        for (int i10 = 0; i10 < this.tracks.size(); i10++) {
            MediaExtractorSampleQueue mediaExtractorSampleQueue = this.tracks.get(i10).sampleQueue;
            jMax2 = Math.max(jMax2, mediaExtractorSampleQueue.getLargestReadTimestampUs());
            jMax = Math.max(jMax, mediaExtractorSampleQueue.getLargestQueuedTimestampUs());
        }
        ac.b.s(jMax != Long.MIN_VALUE);
        if (jMax2 == jMax) {
            return 0L;
        }
        return (jMax - (jMax2 != Long.MIN_VALUE ? jMax2 : 0L)) + 10000;
    }

    public DrmInitData getDrmInitData() {
        for (int i10 = 0; i10 < this.tracks.size(); i10++) {
            DrmInitData drmInitData = this.tracks.get(i10).getFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled).drmInitData;
            if (drmInitData != null) {
                return drmInitData;
            }
        }
        return null;
    }

    public LogSessionId getLogSessionId() {
        LogSessionId logSessionId = this.logSessionId;
        return logSessionId != null ? logSessionId : LogSessionId.LOG_SESSION_ID_NONE;
    }

    public PersistableBundle getMetrics() {
        String str;
        PersistableBundle persistableBundle = new PersistableBundle();
        Extractor extractor = this.currentExtractor;
        if (extractor != null) {
            persistableBundle.putString("android.media.mediaextractor.fmt", extractor.getUnderlyingImplementation().getClass().getSimpleName());
        }
        if (!this.tracks.isEmpty() && (str = this.tracks.get(0).getFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled).containerMimeType) != null) {
            persistableBundle.putString("android.media.mediaextractor.mime", str);
        }
        persistableBundle.putInt("android.media.mediaextractor.ntrk", this.tracks.size());
        return persistableBundle;
    }

    public Map<UUID, byte[]> getPsshInfo() {
        PsshAtomUtil.PsshAtom psshAtom;
        DrmInitData drmInitData = getDrmInitData();
        if (drmInitData == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (int i10 = 0; i10 < drmInitData.schemeDataCount; i10++) {
            byte[] bArr = drmInitData.get(i10).data;
            if (bArr != null && (psshAtom = PsshAtomUtil.parsePsshAtom(bArr)) != null) {
                map.put(psshAtom.uuid, psshAtom.schemeData);
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    public boolean getSampleCryptoInfo(MediaCodec.CryptoInfo cryptoInfo) {
        if (!advanceToSampleOrEndOfInput() || (this.sampleMetadataQueue.peekFirst().flags & 2) == 0) {
            return false;
        }
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementEnabled);
        populatePlatformCryptoInfoParameters(cryptoInfo);
        return true;
    }

    public int getSampleFlags() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().flags;
        }
        return -1;
    }

    public long getSampleSize() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1L;
        }
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementEnabled);
        ByteBuffer byteBuffer = this.sampleHolderWithBufferReplacementEnabled.data;
        byteBuffer.getClass();
        int iPosition = byteBuffer.position();
        byteBuffer.position(0);
        return iPosition;
    }

    public long getSampleTime() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().timeUs;
        }
        return -1L;
    }

    public int getSampleTrackIndex() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().trackIndex;
        }
        return -1;
    }

    public int getTrackCount() {
        return this.tracks.size();
    }

    public MediaFormat getTrackFormat(int i10) {
        MediaExtractorTrack mediaExtractorTrack = this.tracks.get(i10);
        MediaFormat mediaFormatCreateDownstreamMediaFormat = mediaExtractorTrack.createDownstreamMediaFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled);
        long j10 = mediaExtractorTrack.sampleQueue.trackDurationUs;
        if (j10 != C.TIME_UNSET) {
            mediaFormatCreateDownstreamMediaFormat.setLong("durationUs", j10);
            return mediaFormatCreateDownstreamMediaFormat;
        }
        SeekMap seekMap = this.seekMap;
        if (seekMap != null && seekMap.getDurationUs() != C.TIME_UNSET) {
            mediaFormatCreateDownstreamMediaFormat.setLong("durationUs", this.seekMap.getDurationUs());
        }
        return mediaFormatCreateDownstreamMediaFormat;
    }

    public boolean hasCacheReachedEndOfStream() {
        return getCachedDuration() == 0;
    }

    public int readSampleData(ByteBuffer byteBuffer, int i10) {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        byteBuffer.position(i10);
        byteBuffer.limit(byteBuffer.capacity());
        DecoderInputBuffer decoderInputBuffer = this.sampleHolderWithBufferReplacementDisabled;
        decoderInputBuffer.data = byteBuffer;
        peekNextSelectedTrackSample(decoderInputBuffer);
        byteBuffer.flip();
        byteBuffer.position(i10);
        this.sampleHolderWithBufferReplacementDisabled.data = null;
        return byteBuffer.remaining();
    }

    public void release() {
        for (int i10 = 0; i10 < this.sampleQueues.size(); i10++) {
            this.sampleQueues.valueAt(i10).release();
        }
        this.sampleQueues.clear();
        Extractor extractor = this.currentExtractor;
        if (extractor != null) {
            extractor.release();
            this.currentExtractor = null;
        }
        this.currentExtractorInput = null;
        this.pendingSeek = null;
        DataSourceUtil.closeQuietly(this.currentDataSource);
        this.currentDataSource = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void seekTo(long r6, int r8) {
        /*
            r5 = this;
            androidx.media3.extractor.SeekMap r0 = r5.seekMap
            if (r0 != 0) goto L5
            return
        L5:
            java.util.Set<java.lang.Integer> r0 = r5.selectedTrackIndices
            int r0 = r0.size()
            r1 = 1
            if (r0 != r1) goto L42
            androidx.media3.extractor.SeekMap r0 = r5.seekMap
            boolean r2 = r0 instanceof androidx.media3.extractor.TrackAwareSeekMap
            if (r2 == 0) goto L42
            androidx.media3.extractor.TrackAwareSeekMap r0 = (androidx.media3.extractor.TrackAwareSeekMap) r0
            java.util.ArrayList<androidx.media3.exoplayer.MediaExtractorCompatInternal$MediaExtractorTrack> r2 = r5.tracks
            java.util.Set<java.lang.Integer> r3 = r5.selectedTrackIndices
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r3 = r3.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.lang.Object r2 = r2.get(r3)
            androidx.media3.exoplayer.MediaExtractorCompatInternal$MediaExtractorTrack r2 = (androidx.media3.exoplayer.MediaExtractorCompatInternal.MediaExtractorTrack) r2
            int r2 = r2.getIdOfBackingTrack()
            boolean r3 = r0.isSeekable(r2)
            if (r3 == 0) goto L3d
            androidx.media3.extractor.SeekMap$SeekPoints r0 = r0.getSeekPoints(r6, r2)
            goto L48
        L3d:
            androidx.media3.extractor.SeekMap$SeekPoints r0 = r0.getSeekPoints(r6)
            goto L48
        L42:
            androidx.media3.extractor.SeekMap r0 = r5.seekMap
            androidx.media3.extractor.SeekMap$SeekPoints r0 = r0.getSeekPoints(r6)
        L48:
            if (r8 == 0) goto L75
            if (r8 == r1) goto L72
            r1 = 2
            if (r8 != r1) goto L6c
            androidx.media3.extractor.SeekPoint r8 = r0.second
            long r1 = r8.timeUs
            long r1 = r6 - r1
            long r1 = java.lang.Math.abs(r1)
            androidx.media3.extractor.SeekPoint r8 = r0.first
            long r3 = r8.timeUs
            long r6 = r6 - r3
            long r6 = java.lang.Math.abs(r6)
            int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r6 >= 0) goto L69
            androidx.media3.extractor.SeekPoint r6 = r0.second
            goto L77
        L69:
            androidx.media3.extractor.SeekPoint r6 = r0.first
            goto L77
        L6c:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L72:
            androidx.media3.extractor.SeekPoint r6 = r0.second
            goto L77
        L75:
            androidx.media3.extractor.SeekPoint r6 = r0.first
        L77:
            androidx.media3.exoplayer.MediaExtractorCompatInternal$SampleMetadataQueue r7 = r5.sampleMetadataQueue
            r7.clear()
            r7 = 0
        L7d:
            android.util.SparseArray<androidx.media3.exoplayer.MediaExtractorCompatInternal$MediaExtractorSampleQueue> r8 = r5.sampleQueues
            int r8 = r8.size()
            if (r7 >= r8) goto L93
            android.util.SparseArray<androidx.media3.exoplayer.MediaExtractorCompatInternal$MediaExtractorSampleQueue> r8 = r5.sampleQueues
            java.lang.Object r8 = r8.valueAt(r7)
            androidx.media3.exoplayer.MediaExtractorCompatInternal$MediaExtractorSampleQueue r8 = (androidx.media3.exoplayer.MediaExtractorCompatInternal.MediaExtractorSampleQueue) r8
            r8.reset()
            int r7 = r7 + 1
            goto L7d
        L93:
            r5.pendingSeek = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaExtractorCompatInternal.seekTo(long, int):void");
    }

    public void selectTrack(int i10) {
        this.selectedTrackIndices.add(Integer.valueOf(i10));
    }

    public void setDataSource(Uri uri, long j10) throws IOException {
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(uri, j10));
    }

    public void setLogSessionId(LogSessionId logSessionId) {
        if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
            return;
        }
        this.logSessionId = logSessionId;
    }

    public void unselectTrack(int i10) {
        this.selectedTrackIndices.remove(Integer.valueOf(i10));
    }

    public void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IOException {
        if (assetFileDescriptor.getDeclaredLength() == -1) {
            setDataSource(assetFileDescriptor.getFileDescriptor());
        } else {
            setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
        }
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        setDataSource(fileDescriptor, 0L, -1L);
    }

    public void setDataSource(FileDescriptor fileDescriptor, long j10, long j11) throws IOException {
        prepareDataSource(new FileDescriptorDataSource(fileDescriptor, j10, j11), buildDataSpec(Uri.EMPTY, 0L));
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor;
        if (Util.isLocalFileUri(uri)) {
            String path = uri.getPath();
            path.getClass();
            setDataSource(path);
            return;
        }
        try {
            assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        } catch (FileNotFoundException | SecurityException unused) {
        }
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            try {
                setDataSource(assetFileDescriptorOpenAssetFileDescriptor);
                assetFileDescriptorOpenAssetFileDescriptor.close();
                return;
            } catch (Throwable th) {
                try {
                    assetFileDescriptorOpenAssetFileDescriptor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            assetFileDescriptorOpenAssetFileDescriptor.close();
        }
        setDataSource(uri.toString(), map);
    }

    public void setDataSource(String str) throws IOException {
        setDataSource(str, (Map<String, String>) null);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException {
        this.httpRequestHeaders = map;
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(Uri.parse(str), 0L));
    }

    public void setDataSource(MediaDataSource mediaDataSource) throws IOException {
        prepareDataSource(new MediaDataSourceAdapter(mediaDataSource, false), buildDataSpec(Uri.EMPTY, 0L));
    }
}
