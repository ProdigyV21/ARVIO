package androidx.media3.exoplayer.source.chunk;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.h;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexProvider;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.e;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import j$.util.Objects;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {

    @Deprecated
    public static final Factory FACTORY = new Factory();
    private static final PositionHolder POSITION_HOLDER = new PositionHolder();
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs;
    private long endTimeUs;
    private final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final ManifestFormatMerger primaryTrackManifestFormatInfoMerger;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public static final class BindingTrackOutput implements TrackOutput {
        private long endTimeUs;
        private final DiscardingTrackOutput fakeTrackOutput;
        private final int id;
        private final Format manifestFormat;
        private final ManifestFormatMerger manifestFormatMerger;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public void bind(ChunkExtractor.TrackOutputProvider trackOutputProvider, long j10) {
            if (trackOutputProvider == null) {
                this.trackOutput = this.fakeTrackOutput;
                return;
            }
            this.endTimeUs = j10;
            TrackOutput trackOutputTrack = trackOutputProvider.track(this.id, this.type);
            this.trackOutput = trackOutputTrack;
            Format format = this.sampleFormat;
            if (format != null) {
                trackOutputTrack.format(format);
            }
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ void durationUs(long j10) {
            e.a(this, j10);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format format) {
            this.sampleFormat = this.manifestFormatMerger.merge(format, this.manifestFormat);
            ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.sampleFormat);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ int sampleData(DataReader dataReader, int i10, boolean z) {
            return e.b(this, dataReader, i10, z);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
            long j11 = this.endTimeUs;
            if (j11 != C.TIME_UNSET && j10 >= j11) {
                this.trackOutput = this.fakeTrackOutput;
            }
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata(j10, i10, i11, i12, cryptoData);
        }

        private BindingTrackOutput(int i10, int i11, Format format, ManifestFormatMerger manifestFormatMerger) {
            this.id = i10;
            this.type = i11;
            this.manifestFormat = format;
            this.fakeTrackOutput = new DiscardingTrackOutput();
            this.manifestFormatMerger = manifestFormatMerger;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public final /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i10) {
            e.c(this, parsableByteArray, i10);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i10, boolean z, int i11) throws IOException {
            return ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(dataReader, i10, z);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i10, int i11) {
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(parsableByteArray, i10);
        }
    }

    public static final class Factory implements ChunkExtractor.Factory {
        private int codecsToParseWithinGopSampleDependencies;
        private boolean parseSubtitlesDuringExtraction;
        private SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public ChunkExtractor createProgressiveMediaExtractor(int i10, Format format, boolean z, List<Format> list, TrackOutput trackOutput, PlayerId playerId) {
            Extractor fragmentedMp4Extractor;
            String str = format.containerMimeType;
            if (!MimeTypes.isText(str)) {
                if (MimeTypes.isMatroska(str)) {
                    fragmentedMp4Extractor = new MatroskaExtractor(this.subtitleParserFactory, this.parseSubtitlesDuringExtraction ? 1 : 3);
                } else if (Objects.equals(str, "image/jpeg")) {
                    fragmentedMp4Extractor = new JpegExtractor(1);
                } else if (Objects.equals(str, MimeTypes.IMAGE_PNG)) {
                    fragmentedMp4Extractor = new PngExtractor();
                } else {
                    int i11 = z ? 4 : 0;
                    if (!this.parseSubtitlesDuringExtraction) {
                        i11 |= 32;
                    }
                    fragmentedMp4Extractor = new FragmentedMp4Extractor(this.subtitleParserFactory, i11 | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies), null, null, list, trackOutput);
                }
            } else {
                if (!this.parseSubtitlesDuringExtraction) {
                    return null;
                }
                fragmentedMp4Extractor = new SubtitleExtractor(this.subtitleParserFactory.create(format), format);
            }
            return new BundledChunkExtractor(fragmentedMp4Extractor, i10, format);
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Format getOutputTextFormat(Format format) {
            String str;
            if (!this.parseSubtitlesDuringExtraction || !this.subtitleParserFactory.supportsFormat(format)) {
                return format;
            }
            Format.Builder cueReplacementBehavior = format.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(format));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(format.sampleMimeType);
            if (format.codecs != null) {
                str = " " + format.codecs;
            } else {
                str = "";
            }
            sb2.append(str);
            return cueReplacementBehavior.setCodecs(sb2.toString()).setSubsampleOffsetUs(Long.MAX_VALUE).build();
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
            this.codecsToParseWithinGopSampleDependencies = i10;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            factory.getClass();
            this.subtitleParserFactory = factory;
            return this;
        }
    }

    public interface ManifestFormatMerger {
        public static final ManifestFormatMerger DEFAULT = new h(11);

        Format merge(Format format, Format format2);
    }

    public BundledChunkExtractor(Extractor extractor, int i10, Format format) {
        this(extractor, i10, format, ManifestFormatMerger.DEFAULT);
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i10 = 0; i10 < this.bindingTrackOutputs.size(); i10++) {
            Format format = this.bindingTrackOutputs.valueAt(i10).sampleFormat;
            format.getClass();
            formatArr[i10] = format;
        }
        this.sampleFormats = formatArr;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public ChunkIndex getChunkIndex() {
        SeekMap seekMap = this.seekMap;
        if (seekMap instanceof ChunkIndex) {
            return (ChunkIndex) seekMap;
        }
        if (seekMap instanceof ChunkIndexProvider) {
            return ((ChunkIndexProvider) seekMap).getChunkIndex();
        }
        return null;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider, long j10, long j11) {
        this.trackOutputProvider = trackOutputProvider;
        this.endTimeUs = j11;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            if (j10 != C.TIME_UNSET) {
                this.extractor.seek(0L, j10);
            }
            this.extractorInitialized = true;
            return;
        }
        Extractor extractor = this.extractor;
        if (j10 == C.TIME_UNSET) {
            j10 = 0;
        }
        extractor.seek(0L, j10);
        for (int i10 = 0; i10 < this.bindingTrackOutputs.size(); i10++) {
            this.bindingTrackOutputs.valueAt(i10).bind(trackOutputProvider, j11);
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput extractorInput) throws IOException {
        int i10 = this.extractor.read(extractorInput, POSITION_HOLDER);
        ac.b.s(i10 != 1);
        return i10 == 0;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void release() {
        this.extractor.release();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i10, int i11) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i10);
        if (bindingTrackOutput != null) {
            return bindingTrackOutput;
        }
        ac.b.s(this.sampleFormats == null);
        BindingTrackOutput bindingTrackOutput2 = new BindingTrackOutput(i10, i11, i11 == this.primaryTrackType ? this.primaryTrackManifestFormat : null, this.primaryTrackManifestFormatInfoMerger);
        bindingTrackOutput2.bind(this.trackOutputProvider, this.endTimeUs);
        this.bindingTrackOutputs.put(i10, bindingTrackOutput2);
        return bindingTrackOutput2;
    }

    public BundledChunkExtractor(Extractor extractor, int i10, Format format, ManifestFormatMerger manifestFormatMerger) {
        this.extractor = extractor;
        this.primaryTrackType = i10;
        this.primaryTrackManifestFormat = format;
        this.bindingTrackOutputs = new SparseArray<>();
        this.primaryTrackManifestFormatInfoMerger = manifestFormatMerger;
    }
}
