package androidx.media3.extractor.ts;

import android.net.Uri;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.c;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements ReorderingBufferQueue.OutputConsumer, ExtractorsFactory {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3906i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3907l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f3906i = i10;
        this.f3907l = obj;
    }

    @Override // androidx.media3.container.ReorderingBufferQueue.OutputConsumer
    public void consume(long j10, ParsableByteArray parsableByteArray) {
        switch (this.f3906i) {
            case 0:
                ((UserDataReader) this.f3907l).lambda$new$0(j10, parsableByteArray);
                break;
            default:
                ((SeiReader) this.f3907l).lambda$new$0(j10, parsableByteArray);
                break;
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        return TsExtractor.lambda$newFactory$0((SubtitleParser.Factory) this.f3907l);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        return c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        return c.c(this, z);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return c.d(this, factory);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        return c.a(this, uri, map);
    }
}
