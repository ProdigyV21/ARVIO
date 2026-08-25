package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {8, 13, 11, 2, 0, 1, 7};
    private int codecsToParseWithinGopSampleDependencies;
    private final boolean exposeCea608WhenMissingDeclarations;
    private boolean parseSubtitlesDuringExtraction;
    private final int payloadReaderFactoryFlags;
    private SubtitleParser.Factory subtitleParserFactory;

    public DefaultHlsExtractorFactory() {
        this(0, true);
    }

    private static void addFileTypeIfValidAndNotPresent(int i10, List<Integer> list) {
        int[] iArr = DEFAULT_EXTRACTOR_ORDER;
        if (f0.z(iArr, i10, 0, iArr.length) == -1 || list.contains(Integer.valueOf(i10))) {
            return;
        }
        list.add(Integer.valueOf(i10));
    }

    private Extractor createExtractorByFileType(int i10, Format format, List<Format> list, TimestampAdjuster timestampAdjuster) {
        if (i10 == 0) {
            return new Ac3Extractor();
        }
        if (i10 == 1) {
            return new Ac4Extractor();
        }
        if (i10 == 2) {
            return new AdtsExtractor();
        }
        if (i10 == 7) {
            return new Mp3Extractor(0, 0L);
        }
        if (i10 == 8) {
            return createFragmentedMp4Extractor(this.subtitleParserFactory, this.parseSubtitlesDuringExtraction, timestampAdjuster, format, list, this.codecsToParseWithinGopSampleDependencies);
        }
        if (i10 == 11) {
            return createTsExtractor(this.payloadReaderFactoryFlags, this.exposeCea608WhenMissingDeclarations, format, list, timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
        }
        if (i10 != 13) {
            return null;
        }
        return new WebvttExtractor(format.language, timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    }

    private static FragmentedMp4Extractor createFragmentedMp4Extractor(SubtitleParser.Factory factory, boolean z, TimestampAdjuster timestampAdjuster, Format format, List<Format> list, int i10) {
        int i11 = isFmp4Variant(format) ? 4 : 0;
        if (!z) {
            factory = SubtitleParser.Factory.UNSUPPORTED;
            i11 |= 32;
        }
        SubtitleParser.Factory factory2 = factory;
        int iCodecsToParseWithinGopSampleDependenciesAsFlags = i11 | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(i10);
        if (list == null) {
            f1 f1Var = h1.f14020l;
            list = o3.f14078o;
        }
        return new FragmentedMp4Extractor(factory2, iCodecsToParseWithinGopSampleDependenciesAsFlags, timestampAdjuster, null, list, null);
    }

    private static TsExtractor createTsExtractor(int i10, boolean z, Format format, List<Format> list, TimestampAdjuster timestampAdjuster, SubtitleParser.Factory factory, boolean z5) {
        int i11;
        int i12 = i10 | 16;
        if (list != null) {
            i12 = i10 | 48;
        } else {
            list = z ? Collections.singletonList(new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA608).build()) : Collections.EMPTY_LIST;
        }
        String str = format.codecs;
        if (!TextUtils.isEmpty(str)) {
            if (!MimeTypes.containsCodecsCorrespondingToMimeType(str, MimeTypes.AUDIO_AAC)) {
                i12 |= 2;
            }
            if (!MimeTypes.containsCodecsCorrespondingToMimeType(str, MimeTypes.VIDEO_H264)) {
                i12 |= 4;
            }
        }
        if (z5) {
            i11 = 0;
        } else {
            factory = SubtitleParser.Factory.UNSUPPORTED;
            i11 = 1;
        }
        return new TsExtractor(2, i11, factory, timestampAdjuster, new DefaultTsPayloadReaderFactory(i12, list), TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    private static boolean isFmp4Variant(Format format) {
        Metadata metadata = format.metadata;
        return (metadata == null || metadata.getFirstMatchingEntry(HlsTrackMetadataEntry.class, new k(1)) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isFmp4Variant$0(HlsTrackMetadataEntry hlsTrackMetadataEntry) {
        return !hlsTrackMetadataEntry.variantInfos.isEmpty();
    }

    private static boolean sniffQuietly(Extractor extractor, ExtractorInput extractorInput) throws IOException {
        try {
            boolean zSniff = extractor.sniff(extractorInput);
            extractorInput.resetPeekPosition();
            return zSniff;
        } catch (EOFException unused) {
            extractorInput.resetPeekPosition();
            return false;
        } catch (Throwable th) {
            extractorInput.resetPeekPosition();
            throw th;
        }
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* bridge */ /* synthetic */ HlsMediaChunkExtractor createExtractor(Uri uri, Format format, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput, PlayerId playerId) throws IOException {
        return createExtractor(uri, format, (List<Format>) list, timestampAdjuster, (Map<String, List<String>>) map, extractorInput, playerId);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
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

    public DefaultHlsExtractorFactory(int i10, boolean z) {
        this.payloadReaderFactoryFlags = i10;
        this.exposeCea608WhenMissingDeclarations = z;
        this.subtitleParserFactory = new DefaultSubtitleParserFactory();
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public BundledHlsMediaChunkExtractor createExtractor(Uri uri, Format format, List<Format> list, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput, PlayerId playerId) throws IOException {
        Format format2 = format;
        int iInferFileTypeFromMimeType = FileTypes.inferFileTypeFromMimeType(format2.sampleMimeType);
        int iInferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(map);
        int iInferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
        int[] iArr = DEFAULT_EXTRACTOR_ORDER;
        ArrayList arrayList = new ArrayList(iArr.length);
        addFileTypeIfValidAndNotPresent(iInferFileTypeFromMimeType, arrayList);
        addFileTypeIfValidAndNotPresent(iInferFileTypeFromResponseHeaders, arrayList);
        addFileTypeIfValidAndNotPresent(iInferFileTypeFromUri, arrayList);
        int i10 = 0;
        for (int i11 : iArr) {
            addFileTypeIfValidAndNotPresent(i11, arrayList);
        }
        extractorInput.resetPeekPosition();
        Extractor extractor = null;
        while (i10 < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i10)).intValue();
            int i12 = iInferFileTypeFromResponseHeaders;
            int i13 = iInferFileTypeFromUri;
            Extractor extractorCreateExtractorByFileType = createExtractorByFileType(iIntValue, format, list, timestampAdjuster);
            extractorCreateExtractorByFileType.getClass();
            if (sniffQuietly(extractorCreateExtractorByFileType, extractorInput)) {
                return new BundledHlsMediaChunkExtractor(extractorCreateExtractorByFileType, format2, timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
            }
            if (extractor == null && (iIntValue == iInferFileTypeFromMimeType || iIntValue == i12 || iIntValue == i13 || iIntValue == 11)) {
                extractor = extractorCreateExtractorByFileType;
            }
            i10++;
            format2 = format;
            iInferFileTypeFromUri = i13;
            iInferFileTypeFromResponseHeaders = i12;
        }
        extractor.getClass();
        return new BundledHlsMediaChunkExtractor(extractor, format, timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean z) {
        this.parseSubtitlesDuringExtraction = z;
        return this;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        this.codecsToParseWithinGopSampleDependencies = i10;
        return this;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        return this;
    }
}
