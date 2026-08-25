package androidx.media3.extractor.mkv;

import android.net.Uri;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.c;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements ExtractorsFactory {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3896i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SubtitleParser.Factory f3897l;

    public /* synthetic */ a(SubtitleParser.Factory factory, int i10) {
        this.f3896i = i10;
        this.f3897l = factory;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final Extractor[] createExtractors() {
        switch (this.f3896i) {
            case 0:
                return MatroskaExtractor.lambda$newFactory$0(this.f3897l);
            case 1:
                return FragmentedMp4Extractor.lambda$newFactory$0(this.f3897l);
            case 2:
                return Mp4Extractor.lambda$newFactory$0(this.f3897l);
            default:
                return com.arflix.tv.player.dvmkv.MatroskaExtractor.lambda$newFactory$0(this.f3897l);
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        int i11 = this.f3896i;
        return c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        int i10 = this.f3896i;
        return c.c(this, z);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        int i10 = this.f3896i;
        return c.d(this, factory);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        int i10 = this.f3896i;
        return c.a(this, uri, map);
    }
}
