package androidx.media3.exoplayer.source;

import android.media.MediaParser;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import androidx.media3.common.DataReader;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.mediaparser.InputReaderAdapterV30;
import androidx.media3.exoplayer.source.mediaparser.MediaParserUtil;
import androidx.media3.exoplayer.source.mediaparser.OutputConsumerAdapterV30;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import com.google.common.collect.t3;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaParserExtractorAdapter implements ProgressiveMediaExtractor {

    @Deprecated
    public static final ProgressiveMediaExtractor.Factory FACTORY = new p(4);
    private final InputReaderAdapterV30 inputReaderAdapter;
    private final MediaParser mediaParser;
    private final OutputConsumerAdapterV30 outputConsumerAdapter;
    private String parserName;

    public static final class Factory implements ProgressiveMediaExtractor.Factory {
        private static final Map<String, Object> parameters = new HashMap();

        public void setConstantBitrateSeekingEnabled(boolean z) {
            if (!z) {
                Map<String, Object> map = parameters;
                map.remove("android.media.mediaparser.adts.enableCbrSeeking");
                map.remove("android.media.mediaparser.amr.enableCbrSeeking");
                map.remove("android.media.mediaparser.mp3.enableCbrSeeking");
                return;
            }
            Map<String, Object> map2 = parameters;
            Boolean bool = Boolean.TRUE;
            map2.put("android.media.mediaparser.adts.enableCbrSeeking", bool);
            map2.put("android.media.mediaparser.amr.enableCbrSeeking", bool);
            map2.put("android.media.mediaparser.mp3.enableCbrSeeking", bool);
        }

        @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor.Factory
        public MediaParserExtractorAdapter createProgressiveMediaExtractor(PlayerId playerId) {
            return new MediaParserExtractorAdapter(playerId, parameters);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ProgressiveMediaExtractor lambda$static$0(PlayerId playerId) {
        return new MediaParserExtractorAdapter(playerId, t3.f14114q);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        if ("android.media.mediaparser.Mp3Parser".equals(this.parserName)) {
            this.outputConsumerAdapter.disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        return this.inputReaderAdapter.getPosition();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> map, long j10, long j11, ExtractorOutput extractorOutput) throws IOException {
        this.outputConsumerAdapter.setExtractorOutput(extractorOutput);
        this.inputReaderAdapter.setDataReader(dataReader, j11);
        this.inputReaderAdapter.setCurrentPosition(j10);
        String parserName = this.mediaParser.getParserName();
        if ("android.media.mediaparser.UNKNOWN".equals(parserName)) {
            this.mediaParser.advance(this.inputReaderAdapter);
            String parserName2 = this.mediaParser.getParserName();
            this.parserName = parserName2;
            this.outputConsumerAdapter.setSelectedParserName(parserName2);
            return;
        }
        if (parserName.equals(this.parserName)) {
            return;
        }
        String parserName3 = this.mediaParser.getParserName();
        this.parserName = parserName3;
        this.outputConsumerAdapter.setSelectedParserName(parserName3);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        boolean zAdvance = this.mediaParser.advance(this.inputReaderAdapter);
        long andResetSeekPosition = this.inputReaderAdapter.getAndResetSeekPosition();
        positionHolder.position = andResetSeekPosition;
        if (zAdvance) {
            return andResetSeekPosition != -1 ? 1 : 0;
        }
        return -1;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        this.mediaParser.release();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long j10, long j11) {
        this.inputReaderAdapter.setCurrentPosition(j10);
        Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.outputConsumerAdapter.getSeekPoints(j11);
        MediaParser mediaParser = this.mediaParser;
        Object obj = seekPoints.second;
        mediaParser.seek(p.b(obj).position == j10 ? p.b(obj) : p.b(seekPoints.first));
    }

    @Deprecated
    public MediaParserExtractorAdapter(PlayerId playerId) {
        this(playerId, t3.f14114q);
    }

    private MediaParserExtractorAdapter(PlayerId playerId, Map<String, Object> map) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30();
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        this.inputReaderAdapter = new InputReaderAdapterV30();
        MediaParser mediaParserCreate = MediaParser.create(outputConsumerAdapterV30, new String[0]);
        this.mediaParser = mediaParserCreate;
        Boolean bool = Boolean.TRUE;
        mediaParserCreate.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, bool);
        mediaParserCreate.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, bool);
        mediaParserCreate.setParameter(MediaParserUtil.PARAMETER_INCLUDE_SUPPLEMENTAL_DATA, bool);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.mediaParser.setParameter(entry.getKey(), entry.getValue());
        }
        this.parserName = "android.media.mediaparser.UNKNOWN";
        if (Build.VERSION.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(this.mediaParser, playerId);
        }
    }
}
