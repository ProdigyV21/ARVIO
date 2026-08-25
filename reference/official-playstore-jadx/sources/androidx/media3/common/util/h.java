package androidx.media3.common.util;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheKeyFactory;
import androidx.media3.exoplayer.audio.AudioOutput;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.hls.HlsDataSourceFactory;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistTracker;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.c0;
import java.lang.reflect.Constructor;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements CacheKeyFactory, ListenerSet.Event, HlsPlaylistTracker.Factory, BundledChunkExtractor.ManifestFormatMerger, DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier, ExtractorsFactory, Id3Decoder.FramePredicate {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3419i;

    public /* synthetic */ h(int i10) {
        this.f3419i = i10;
    }

    @Override // androidx.media3.datasource.cache.CacheKeyFactory
    public String buildCacheKey(DataSpec dataSpec) {
        return androidx.media3.datasource.cache.a.a(dataSpec);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        switch (this.f3419i) {
            case 21:
                return androidx.media3.extractor.c.e();
            case 22:
                return AmrExtractor.lambda$static$0();
            case 23:
                return FlacExtractor.lambda$static$0();
            case 24:
                return FlvExtractor.lambda$static$0();
            case 25:
            default:
                return FragmentedMp4Extractor.lambda$static$1();
            case 26:
                return MatroskaExtractor.lambda$static$1();
            case 27:
                return Mp3Extractor.lambda$static$0();
        }
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.Factory
    public HlsPlaylistTracker createTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory, CmcdConfiguration cmcdConfiguration, c0 c0Var) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory, cmcdConfiguration, c0Var);
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate
    public boolean evaluate(int i10, int i11, int i12, int i13, int i14) {
        switch (this.f3419i) {
            case 25:
                return Id3Decoder.lambda$static$0(i10, i11, i12, i13, i14);
            default:
                return Mp3Extractor.lambda$static$1(i10, i11, i12, i13, i14);
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        int i11 = this.f3419i;
        return androidx.media3.extractor.c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        int i10 = this.f3419i;
        return androidx.media3.extractor.c.c(this, z);
    }

    @Override // androidx.media3.extractor.DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier
    public Constructor getConstructor() {
        switch (this.f3419i) {
            case 19:
                return DefaultExtractorsFactory.getFlacExtractorConstructor();
            default:
                return DefaultExtractorsFactory.getMidiExtractorConstructor();
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3419i) {
            case 5:
                ((AudioOutput.Listener) obj).onUnderrun();
                break;
            case 6:
                ((AudioOutput.Listener) obj).onReleased();
                break;
            case 7:
                ((AudioOutput.Listener) obj).onOffloadDataRequest();
                break;
            case 8:
                ((AudioOutput.Listener) obj).onOffloadPresentationEnded();
                break;
            default:
                ((AudioOutputProvider.Listener) obj).onFormatSupportChanged();
                break;
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.BundledChunkExtractor.ManifestFormatMerger
    public Format merge(Format format, Format format2) {
        return androidx.media3.exoplayer.source.chunk.a.a(format, format2);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        int i10 = this.f3419i;
        return androidx.media3.extractor.c.d(this, factory);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        int i10 = this.f3419i;
        return androidx.media3.extractor.c.a(this, uri, map);
    }
}
