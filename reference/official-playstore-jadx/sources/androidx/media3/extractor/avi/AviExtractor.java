package androidx.media3.extractor.avi;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.NoOpExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.b;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.collect.f1;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AviExtractor implements Extractor {
    private static final int AVIIF_KEYFRAME = 16;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 1;
    public static final int FOURCC_AVI_ = 541677121;
    public static final int FOURCC_JUNK = 1263424842;
    public static final int FOURCC_LIST = 1414744396;
    public static final int FOURCC_RIFF = 1179011410;
    public static final int FOURCC_auds = 1935963489;
    public static final int FOURCC_avih = 1751742049;
    public static final int FOURCC_hdrl = 1819436136;
    public static final int FOURCC_idx1 = 829973609;
    public static final int FOURCC_movi = 1769369453;
    public static final int FOURCC_strf = 1718776947;
    public static final int FOURCC_strh = 1752331379;
    public static final int FOURCC_strl = 1819440243;
    public static final int FOURCC_strn = 1852994675;
    public static final int FOURCC_txts = 1937012852;
    public static final int FOURCC_vids = 1935960438;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_FINDING_IDX1_HEADER = 4;
    private static final int STATE_FINDING_MOVI_HEADER = 3;
    private static final int STATE_READING_HDRL_BODY = 2;
    private static final int STATE_READING_HDRL_HEADER = 1;
    private static final int STATE_READING_IDX1_BODY = 5;
    private static final int STATE_READING_SAMPLES = 6;
    private static final int STATE_SKIPPING_TO_HDRL = 0;
    private static final String TAG = "AviExtractor";
    private AviMainHeaderChunk aviHeader;
    private final ChunkHeaderHolder chunkHeaderHolder;
    private ChunkReader[] chunkReaders;
    private ChunkReader currentChunkReader;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int hdrlSize;
    private int idx1BodySize;
    private long moviEnd;
    private long moviStart;
    private final boolean parseSubtitlesDuringExtraction;
    private long pendingReposition;
    private final ParsableByteArray scratch;
    private boolean seekMapHasBeenOutput;
    private int state;
    private final SubtitleParser.Factory subtitleParserFactory;

    public class AviSeekMap implements SeekMap {
        private final long durationUs;

        public AviSeekMap(long j10) {
            this.durationUs = j10;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j10) {
            SeekMap.SeekPoints seekPoints = AviExtractor.this.chunkReaders[0].getSeekPoints(j10);
            for (int i10 = 1; i10 < AviExtractor.this.chunkReaders.length; i10++) {
                SeekMap.SeekPoints seekPoints2 = AviExtractor.this.chunkReaders[i10].getSeekPoints(j10);
                if (seekPoints2.first.position < seekPoints.first.position) {
                    seekPoints = seekPoints2;
                }
            }
            return seekPoints;
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        private ChunkHeaderHolder() {
        }

        public void populateFrom(ParsableByteArray parsableByteArray) {
            this.chunkType = parsableByteArray.readLittleEndianInt();
            this.size = parsableByteArray.readLittleEndianInt();
            this.listType = 0;
        }

        public void populateWithListHeaderFrom(ParsableByteArray parsableByteArray) throws ParserException {
            populateFrom(parsableByteArray);
            if (this.chunkType == 1414744396) {
                this.listType = parsableByteArray.readLittleEndianInt();
            } else {
                throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, null);
            }
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Deprecated
    public AviExtractor() {
        this(1, SubtitleParser.Factory.UNSUPPORTED);
    }

    private static void alignInputToEvenPosition(ExtractorInput extractorInput) throws IOException {
        if ((extractorInput.getPosition() & 1) == 1) {
            extractorInput.skipFully(1);
        }
    }

    private ChunkReader getChunkReader(int i10) {
        for (ChunkReader chunkReader : this.chunkReaders) {
            if (chunkReader.handlesChunkId(i10)) {
                return chunkReader;
            }
        }
        return null;
    }

    private void parseHdrlBody(ParsableByteArray parsableByteArray) throws IOException {
        ListChunk from = ListChunk.parseFrom(FOURCC_hdrl, parsableByteArray);
        if (from.getType() != 1819436136) {
            throw ParserException.createForMalformedContainer("Unexpected header list type " + from.getType(), null);
        }
        AviMainHeaderChunk aviMainHeaderChunk = (AviMainHeaderChunk) from.getChild(AviMainHeaderChunk.class);
        if (aviMainHeaderChunk == null) {
            throw ParserException.createForMalformedContainer("AviHeader not found", null);
        }
        this.aviHeader = aviMainHeaderChunk;
        this.durationUs = ((long) aviMainHeaderChunk.totalFrames) * ((long) aviMainHeaderChunk.frameDurationUs);
        ArrayList arrayList = new ArrayList();
        f1 f1VarListIterator = from.children.listIterator(0);
        int i10 = 0;
        while (f1VarListIterator.hasNext()) {
            AviChunk aviChunk = (AviChunk) f1VarListIterator.next();
            if (aviChunk.getType() == 1819440243) {
                int i11 = i10 + 1;
                ChunkReader chunkReaderProcessStreamList = processStreamList((ListChunk) aviChunk, i10);
                if (chunkReaderProcessStreamList != null) {
                    arrayList.add(chunkReaderProcessStreamList);
                }
                i10 = i11;
            }
        }
        this.chunkReaders = (ChunkReader[]) arrayList.toArray(new ChunkReader[0]);
        this.extractorOutput.endTracks();
    }

    private void parseIdx1Body(ParsableByteArray parsableByteArray) {
        int i10;
        long jPeekSeekOffset = peekSeekOffset(parsableByteArray);
        while (true) {
            if (parsableByteArray.bytesLeft() < 16) {
                break;
            }
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
            long littleEndianInt3 = ((long) parsableByteArray.readLittleEndianInt()) + jPeekSeekOffset;
            parsableByteArray.skipBytes(4);
            ChunkReader chunkReader = getChunkReader(littleEndianInt);
            if (chunkReader != null) {
                chunkReader.appendIndexChunk(littleEndianInt3, (littleEndianInt2 & 16) == 16);
            }
        }
        for (ChunkReader chunkReader2 : this.chunkReaders) {
            chunkReader2.commitIndex();
        }
        this.seekMapHasBeenOutput = true;
        if (this.chunkReaders.length == 0) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
        } else {
            this.extractorOutput.seekMap(new AviSeekMap(this.durationUs));
        }
    }

    private long peekSeekOffset(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 16) {
            return 0L;
        }
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(8);
        long littleEndianInt = parsableByteArray.readLittleEndianInt();
        long j10 = this.moviStart;
        long j11 = littleEndianInt <= j10 ? 8 + j10 : 0L;
        parsableByteArray.setPosition(position);
        return j11;
    }

    private ChunkReader processStreamList(ListChunk listChunk, int i10) {
        AviStreamHeaderChunk aviStreamHeaderChunk = (AviStreamHeaderChunk) listChunk.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk streamFormatChunk = (StreamFormatChunk) listChunk.getChild(StreamFormatChunk.class);
        if (aviStreamHeaderChunk == null) {
            Log.w(TAG, "Missing Stream Header");
            return null;
        }
        if (streamFormatChunk == null) {
            Log.w(TAG, "Missing Stream Format");
            return null;
        }
        long durationUs = aviStreamHeaderChunk.getDurationUs();
        Format format = streamFormatChunk.format;
        Format.Builder builderBuildUpon = format.buildUpon();
        builderBuildUpon.setId(i10);
        int i11 = aviStreamHeaderChunk.suggestedBufferSize;
        if (i11 != 0) {
            builderBuildUpon.setMaxInputSize(i11);
        }
        StreamNameChunk streamNameChunk = (StreamNameChunk) listChunk.getChild(StreamNameChunk.class);
        if (streamNameChunk != null) {
            builderBuildUpon.setLabel(streamNameChunk.name);
        }
        int trackType = MimeTypes.getTrackType(format.sampleMimeType);
        if (trackType != 1 && trackType != 2) {
            return null;
        }
        TrackOutput trackOutputTrack = this.extractorOutput.track(i10, trackType);
        trackOutputTrack.format(builderBuildUpon.build());
        trackOutputTrack.durationUs(durationUs);
        this.durationUs = Math.max(this.durationUs, durationUs);
        return new ChunkReader(i10, aviStreamHeaderChunk, trackOutputTrack);
    }

    private int readMoviChunks(ExtractorInput extractorInput) throws IOException {
        if (extractorInput.getPosition() >= this.moviEnd) {
            return -1;
        }
        ChunkReader chunkReader = this.currentChunkReader;
        if (chunkReader == null) {
            alignInputToEvenPosition(extractorInput);
            extractorInput.peekFully(this.scratch.getData(), 0, 12);
            this.scratch.setPosition(0);
            int littleEndianInt = this.scratch.readLittleEndianInt();
            if (littleEndianInt == 1414744396) {
                this.scratch.setPosition(8);
                extractorInput.skipFully(this.scratch.readLittleEndianInt() != 1769369453 ? 8 : 12);
                extractorInput.resetPeekPosition();
                return 0;
            }
            int littleEndianInt2 = this.scratch.readLittleEndianInt();
            if (littleEndianInt == 1263424842) {
                this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt2) + 8;
                return 0;
            }
            extractorInput.skipFully(8);
            extractorInput.resetPeekPosition();
            ChunkReader chunkReader2 = getChunkReader(littleEndianInt);
            if (chunkReader2 == null) {
                this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt2);
                return 0;
            }
            chunkReader2.onChunkStart(littleEndianInt2);
            this.currentChunkReader = chunkReader2;
        } else if (chunkReader.onChunkData(extractorInput)) {
            this.currentChunkReader = null;
        }
        return 0;
    }

    private boolean resolvePendingReposition(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        boolean z;
        if (this.pendingReposition != -1) {
            long position = extractorInput.getPosition();
            long j10 = this.pendingReposition;
            if (j10 < position || j10 > 262144 + position) {
                positionHolder.position = j10;
                z = true;
            } else {
                extractorInput.skipFully((int) (j10 - position));
                z = false;
            }
        } else {
            z = false;
        }
        this.pendingReposition = -1L;
        return z;
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.state = 0;
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
        this.pendingReposition = -1L;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (resolvePendingReposition(extractorInput, positionHolder)) {
            return 1;
        }
        switch (this.state) {
            case 0:
                if (!sniff(extractorInput)) {
                    throw ParserException.createForMalformedContainer("AVI Header List not found", null);
                }
                extractorInput.skipFully(12);
                this.state = 1;
                return 0;
            case 1:
                extractorInput.readFully(this.scratch.getData(), 0, 12);
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
                ChunkHeaderHolder chunkHeaderHolder = this.chunkHeaderHolder;
                if (chunkHeaderHolder.listType == 1819436136) {
                    this.hdrlSize = chunkHeaderHolder.size;
                    this.state = 2;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, null);
            case 2:
                int i10 = this.hdrlSize - 4;
                ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
                extractorInput.readFully(parsableByteArray.getData(), 0, i10);
                parseHdrlBody(parsableByteArray);
                this.state = 3;
                return 0;
            case 3:
                if (this.moviStart != -1) {
                    long position = extractorInput.getPosition();
                    long j10 = this.moviStart;
                    if (position != j10) {
                        this.pendingReposition = j10;
                        return 0;
                    }
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 12);
                extractorInput.resetPeekPosition();
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateFrom(this.scratch);
                int littleEndianInt = this.scratch.readLittleEndianInt();
                int i11 = this.chunkHeaderHolder.chunkType;
                if (i11 == 1179011410) {
                    extractorInput.skipFully(12);
                    return 0;
                }
                if (i11 != 1414744396 || littleEndianInt != 1769369453) {
                    this.pendingReposition = extractorInput.getPosition() + ((long) this.chunkHeaderHolder.size) + 8;
                    return 0;
                }
                long position2 = extractorInput.getPosition();
                this.moviStart = position2;
                this.moviEnd = position2 + ((long) this.chunkHeaderHolder.size) + 8;
                if (!this.seekMapHasBeenOutput) {
                    AviMainHeaderChunk aviMainHeaderChunk = this.aviHeader;
                    aviMainHeaderChunk.getClass();
                    if (aviMainHeaderChunk.hasIndex()) {
                        this.state = 4;
                        this.pendingReposition = this.moviEnd;
                        return 0;
                    }
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                    this.seekMapHasBeenOutput = true;
                }
                this.pendingReposition = extractorInput.getPosition() + 12;
                this.state = 6;
                return 0;
            case 4:
                extractorInput.readFully(this.scratch.getData(), 0, 8);
                this.scratch.setPosition(0);
                int littleEndianInt2 = this.scratch.readLittleEndianInt();
                int littleEndianInt3 = this.scratch.readLittleEndianInt();
                if (littleEndianInt2 == 829973609) {
                    this.state = 5;
                    this.idx1BodySize = littleEndianInt3;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) littleEndianInt3);
                }
                return 0;
            case 5:
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.idx1BodySize);
                extractorInput.readFully(parsableByteArray2.getData(), 0, this.idx1BodySize);
                parseIdx1Body(parsableByteArray2);
                this.state = 6;
                this.pendingReposition = this.moviStart;
                return 0;
            case 6:
                return readMoviChunks(extractorInput);
            default:
                throw new AssertionError();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        this.pendingReposition = -1L;
        this.currentChunkReader = null;
        for (ChunkReader chunkReader : this.chunkReaders) {
            chunkReader.seekToPosition(j10);
        }
        if (j10 != 0) {
            this.state = 6;
        } else if (this.chunkReaders.length == 0) {
            this.state = 0;
        } else {
            this.state = 3;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() != 1179011410) {
            return false;
        }
        this.scratch.skipBytes(4);
        return this.scratch.readLittleEndianInt() == 541677121;
    }

    public AviExtractor(int i10, SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        this.parseSubtitlesDuringExtraction = (i10 & 1) == 0;
        this.scratch = new ParsableByteArray(12);
        this.chunkHeaderHolder = new ChunkHeaderHolder();
        this.extractorOutput = new NoOpExtractorOutput();
        this.chunkReaders = new ChunkReader[0];
        this.moviStart = -1L;
        this.moviEnd = -1L;
        this.hdrlSize = -1;
        this.durationUs = C.TIME_UNSET;
    }
}
