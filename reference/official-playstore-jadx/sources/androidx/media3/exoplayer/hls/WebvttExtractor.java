package androidx.media3.exoplayer.hls;

import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.text.webvtt.WebvttParserUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class WebvttExtractor implements Extractor {
    private static final int HEADER_MAX_LENGTH = 9;
    private static final int HEADER_MIN_LENGTH = 6;
    private static final Pattern LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(-?\\d+)");
    private final String language;
    private ExtractorOutput output;
    private final boolean parseSubtitlesDuringExtraction;
    private byte[] sampleData;
    private final ParsableByteArray sampleDataWrapper;
    private int sampleSize;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;

    @Deprecated
    public WebvttExtractor(String str, TimestampAdjuster timestampAdjuster) {
        this(str, timestampAdjuster, SubtitleParser.Factory.UNSUPPORTED, false);
    }

    @RequiresNonNull({"output"})
    private TrackOutput buildTrackOutput(long j10) {
        TrackOutput trackOutputTrack = this.output.track(0, 3);
        trackOutputTrack.format(new Format.Builder().setSampleMimeType(MimeTypes.TEXT_VTT).setLanguage(this.language).setSubsampleOffsetUs(j10).build());
        this.output.endTracks();
        return trackOutputTrack;
    }

    @RequiresNonNull({"output"})
    private void processSample() throws ParserException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.sampleData);
        WebvttParserUtil.validateWebvttHeaderLine(parsableByteArray);
        long jPtsToUs = 0;
        long timestampUs = 0;
        for (String line = parsableByteArray.readLine(); !TextUtils.isEmpty(line); line = parsableByteArray.readLine()) {
            if (line.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = LOCAL_TIMESTAMP.matcher(line);
                if (!matcher.find()) {
                    throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain local timestamp: ".concat(line), null);
                }
                Matcher matcher2 = MEDIA_TIMESTAMP.matcher(line);
                if (!matcher2.find()) {
                    throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain media timestamp: ".concat(line), null);
                }
                String strGroup = matcher.group(1);
                strGroup.getClass();
                timestampUs = WebvttParserUtil.parseTimestampUs(strGroup);
                String strGroup2 = matcher2.group(1);
                strGroup2.getClass();
                jPtsToUs = TimestampAdjuster.ptsToUs(Long.parseLong(strGroup2));
            }
        }
        Matcher matcherFindNextCueHeader = WebvttParserUtil.findNextCueHeader(parsableByteArray);
        if (matcherFindNextCueHeader == null) {
            buildTrackOutput(0L);
            return;
        }
        String strGroup3 = matcherFindNextCueHeader.group(1);
        strGroup3.getClass();
        long timestampUs2 = WebvttParserUtil.parseTimestampUs(strGroup3);
        long jAdjustTsTimestamp = this.timestampAdjuster.adjustTsTimestamp(TimestampAdjuster.usToWrappedPts((jPtsToUs + timestampUs2) - timestampUs));
        TrackOutput trackOutputBuildTrackOutput = buildTrackOutput(jAdjustTsTimestamp - timestampUs2);
        this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
        trackOutputBuildTrackOutput.sampleData(this.sampleDataWrapper, this.sampleSize);
        trackOutputBuildTrackOutput.sampleMetadata(jAdjustTsTimestamp, 1, this.sampleSize, 0, null);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return androidx.media3.extractor.b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return androidx.media3.extractor.b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.output = extractorOutput;
        extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.output.getClass();
        int length = (int) extractorInput.getLength();
        int i10 = this.sampleSize;
        byte[] bArr = this.sampleData;
        if (i10 == bArr.length) {
            this.sampleData = Arrays.copyOf(bArr, ((length != -1 ? length : bArr.length) * 3) / 2);
        }
        byte[] bArr2 = this.sampleData;
        int i11 = this.sampleSize;
        int i12 = extractorInput.read(bArr2, i11, bArr2.length - i11);
        if (i12 != -1) {
            int i13 = this.sampleSize + i12;
            this.sampleSize = i13;
            if (length == -1 || i13 != length) {
                return 0;
            }
        }
        processSample();
        return -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        throw new IllegalStateException();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.sampleData, 0, 6, false);
        this.sampleDataWrapper.reset(this.sampleData, 6);
        if (WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper)) {
            return true;
        }
        extractorInput.peekFully(this.sampleData, 6, 3, false);
        this.sampleDataWrapper.reset(this.sampleData, 9);
        return WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper);
    }

    public WebvttExtractor(String str, TimestampAdjuster timestampAdjuster, SubtitleParser.Factory factory, boolean z) {
        this.language = str;
        this.timestampAdjuster = timestampAdjuster;
        this.sampleDataWrapper = new ParsableByteArray();
        this.sampleData = new byte[1024];
        this.subtitleParserFactory = factory;
        this.parseSubtitlesDuringExtraction = z;
    }
}
