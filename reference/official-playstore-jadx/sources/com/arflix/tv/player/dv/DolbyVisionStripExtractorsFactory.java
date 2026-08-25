package com.arflix.tv.player.dv;

import android.net.Uri;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.c;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import com.arflix.tv.player.dvmkv.DolbyVisionCompatibility;
import com.arflix.tv.player.dvmkv.MatroskaExtractor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0007\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB)\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ7\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00140\u0012H\u0016¢\u0006\u0004\b\u000e\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0018R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionStripExtractorsFactory;", "Landroidx/media3/extractor/ExtractorsFactory;", "delegate", "", "stripHdr10PlusSei", "Lkotlin/Function0;", "enabledProvider", "<init>", "(Landroidx/media3/extractor/ExtractorsFactory;ZLr7/a;)V", "Landroidx/media3/extractor/Extractor;", "extractor", "wrap", "(Landroidx/media3/extractor/Extractor;)Landroidx/media3/extractor/Extractor;", "", "createExtractors", "()[Landroidx/media3/extractor/Extractor;", "Landroid/net/Uri;", "uri", "", "", "", "responseHeaders", "(Landroid/net/Uri;Ljava/util/Map;)[Landroidx/media3/extractor/Extractor;", "Landroidx/media3/extractor/ExtractorsFactory;", "Z", "Lr7/a;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DolbyVisionStripExtractorsFactory implements ExtractorsFactory {
    private static final String STOCK_MATROSKA_EXTRACTOR = "androidx.media3.extractor.mkv.MatroskaExtractor";
    private final ExtractorsFactory delegate;
    private final a<Boolean> enabledProvider;
    private final boolean stripHdr10PlusSei;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionStripExtractorsFactory$Companion;", "", "<init>", "()V", "STOCK_MATROSKA_EXTRACTOR", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    public DolbyVisionStripExtractorsFactory(ExtractorsFactory extractorsFactory, boolean z, a<Boolean> aVar) {
        this.delegate = extractorsFactory;
        this.stripHdr10PlusSei = z;
        this.enabledProvider = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return true;
    }

    private final Extractor wrap(Extractor extractor) {
        boolean zBooleanValue = ((Boolean) this.enabledProvider.invoke()).booleanValue();
        DolbyVisionCompatibility.setHdr10BaseLayerModeActive(zBooleanValue);
        return (zBooleanValue && extractor.getClass().getName().equals(STOCK_MATROSKA_EXTRACTOR)) ? new MatroskaExtractor(new DefaultSubtitleParserFactory(), 0, new DolbyVisionStripTransformer(this.stripHdr10PlusSei)) : extractor;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        Extractor[] extractorArrCreateExtractors = this.delegate.createExtractors();
        ArrayList arrayList = new ArrayList(extractorArrCreateExtractors.length);
        for (Extractor extractor : extractorArrCreateExtractors) {
            arrayList.add(wrap(extractor));
        }
        return (Extractor[]) arrayList.toArray(new Extractor[0]);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        return c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        return c.c(this, z);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return c.d(this, factory);
    }

    public /* synthetic */ DolbyVisionStripExtractorsFactory(ExtractorsFactory extractorsFactory, boolean z, a aVar, int i10, h hVar) {
        this(extractorsFactory, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? new com.arflix.tv.data.repository.simkl.a(8) : aVar);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors(Uri uri, Map<String, ? extends List<String>> responseHeaders) {
        Extractor[] extractorArrCreateExtractors = this.delegate.createExtractors(uri, responseHeaders);
        ArrayList arrayList = new ArrayList(extractorArrCreateExtractors.length);
        for (Extractor extractor : extractorArrCreateExtractors) {
            arrayList.add(wrap(extractor));
        }
        return (Extractor[]) arrayList.toArray(new Extractor[0]);
    }
}
