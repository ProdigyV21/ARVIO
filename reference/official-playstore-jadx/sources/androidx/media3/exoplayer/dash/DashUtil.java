package androidx.media3.exoplayer.dash;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.DashManifestParser;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.chunk.InitializationChunk;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.t3;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class DashUtil {
    private DashUtil() {
    }

    public static DataSpec buildDataSpec(Representation representation, String str, RangedUri rangedUri, int i10, Map<String, String> map) {
        return new DataSpec.Builder().setUri(rangedUri.resolveUri(str)).setPosition(rangedUri.start).setLength(rangedUri.length).setKey(resolveCacheKey(representation, rangedUri)).setFlags(i10).setHttpRequestHeaders(map).build();
    }

    private static Representation getFirstRepresentation(Period period, int i10) {
        int adaptationSetIndex = period.getAdaptationSetIndex(i10);
        if (adaptationSetIndex == -1) {
            return null;
        }
        List<Representation> list = period.adaptationSets.get(adaptationSetIndex).representations;
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static ChunkIndex loadChunkIndex(DataSource dataSource, int i10, Representation representation, int i11) throws IOException {
        if (representation.getInitializationUri() == null) {
            return null;
        }
        ChunkExtractor chunkExtractorNewChunkExtractor = newChunkExtractor(i10, representation.format);
        try {
            loadInitializationData(chunkExtractorNewChunkExtractor, dataSource, representation, i11, true);
            chunkExtractorNewChunkExtractor.release();
            return chunkExtractorNewChunkExtractor.getChunkIndex();
        } catch (Throwable th) {
            chunkExtractorNewChunkExtractor.release();
            throw th;
        }
    }

    public static Format loadFormatWithDrmInitData(DataSource dataSource, Period period) throws IOException {
        int i10 = 2;
        Representation firstRepresentation = getFirstRepresentation(period, 2);
        if (firstRepresentation == null) {
            i10 = 1;
            firstRepresentation = getFirstRepresentation(period, 1);
            if (firstRepresentation == null) {
                return null;
            }
        }
        Format format = firstRepresentation.format;
        Format formatLoadSampleFormat = loadSampleFormat(dataSource, i10, firstRepresentation);
        return formatLoadSampleFormat == null ? format : formatLoadSampleFormat.withManifestFormatInfo(format);
    }

    private static void loadInitializationData(ChunkExtractor chunkExtractor, DataSource dataSource, Representation representation, int i10, boolean z) throws IOException {
        RangedUri initializationUri = representation.getInitializationUri();
        initializationUri.getClass();
        if (z) {
            RangedUri indexUri = representation.getIndexUri();
            if (indexUri == null) {
                return;
            }
            RangedUri rangedUriAttemptMerge = initializationUri.attemptMerge(indexUri, ((BaseUrl) representation.baseUrls.get(i10)).url);
            if (rangedUriAttemptMerge == null) {
                loadInitializationData(dataSource, representation, i10, chunkExtractor, initializationUri);
                initializationUri = indexUri;
            } else {
                initializationUri = rangedUriAttemptMerge;
            }
        }
        loadInitializationData(dataSource, representation, i10, chunkExtractor, initializationUri);
    }

    public static DashManifest loadManifest(DataSource dataSource, Uri uri) throws IOException {
        return (DashManifest) ParsingLoadable.load(dataSource, new DashManifestParser(), uri, 4);
    }

    public static Format loadSampleFormat(DataSource dataSource, int i10, Representation representation, int i11) throws IOException {
        if (representation.getInitializationUri() == null) {
            return null;
        }
        ChunkExtractor chunkExtractorNewChunkExtractor = newChunkExtractor(i10, representation.format);
        try {
            loadInitializationData(chunkExtractorNewChunkExtractor, dataSource, representation, i11, false);
            chunkExtractorNewChunkExtractor.release();
            Format[] sampleFormats = chunkExtractorNewChunkExtractor.getSampleFormats();
            sampleFormats.getClass();
            return sampleFormats[0];
        } catch (Throwable th) {
            chunkExtractorNewChunkExtractor.release();
            throw th;
        }
    }

    private static ChunkExtractor newChunkExtractor(int i10, Format format) {
        String str = format.containerMimeType;
        return new BundledChunkExtractor((str == null || !(str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM))) ? new FragmentedMp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 32) : new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2), i10, format);
    }

    public static String resolveCacheKey(Representation representation, RangedUri rangedUri) {
        String cacheKey = representation.getCacheKey();
        return cacheKey != null ? cacheKey : rangedUri.resolveUri(((BaseUrl) representation.baseUrls.get(0)).url).toString();
    }

    public static ChunkIndex loadChunkIndex(DataSource dataSource, int i10, Representation representation) throws IOException {
        return loadChunkIndex(dataSource, i10, representation, 0);
    }

    public static void loadInitializationData(ChunkExtractor chunkExtractor, DataSource dataSource, Representation representation, boolean z) throws IOException {
        loadInitializationData(chunkExtractor, dataSource, representation, 0, z);
    }

    @Deprecated
    public static DataSpec buildDataSpec(Representation representation, String str, RangedUri rangedUri, int i10) {
        return buildDataSpec(representation, str, rangedUri, i10, t3.f14114q);
    }

    private static void loadInitializationData(DataSource dataSource, Representation representation, int i10, ChunkExtractor chunkExtractor, RangedUri rangedUri) throws IOException {
        new InitializationChunk(dataSource, buildDataSpec(representation, ((BaseUrl) representation.baseUrls.get(i10)).url, rangedUri, 0, t3.f14114q), representation.format, 0, null, chunkExtractor).load();
    }

    public static Format loadSampleFormat(DataSource dataSource, int i10, Representation representation) throws IOException {
        return loadSampleFormat(dataSource, i10, representation, 0);
    }

    @Deprecated
    public static DataSpec buildDataSpec(Representation representation, RangedUri rangedUri, int i10) {
        return buildDataSpec(representation, ((BaseUrl) representation.baseUrls.get(0)).url, rangedUri, i10, t3.f14114q);
    }
}
