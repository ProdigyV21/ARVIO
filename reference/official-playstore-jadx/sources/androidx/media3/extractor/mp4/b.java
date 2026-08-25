package androidx.media3.extractor.mp4;

import android.app.ApplicationExitInfo;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.media.AudioRecordingConfiguration;
import android.net.Uri;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.c;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import coil.EventListener;
import coil.request.ImageRequest;
import com.arflix.tv.player.dvmkv.MatroskaExtractor;
import com.discord.org.webrtc.EglThread;
import com.google.firebase.components.f;
import com.google.firebase.components.o;
import com.google.firebase.components.u;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.p;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import m5.a;
import z2.d;
import z2.g;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements ExtractorsFactory, EventListener.Factory, EglThread.ReleaseMonitor, g, a.InterfaceC0243a, f, d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3899i;

    public /* synthetic */ b(int i10) {
        this.f3899i = i10;
    }

    public static /* bridge */ /* synthetic */ ApplicationExitInfo b(Object obj) {
        return (ApplicationExitInfo) obj;
    }

    public static /* bridge */ /* synthetic */ ImageDecoder d(Object obj) {
        return (ImageDecoder) obj;
    }

    public static /* bridge */ /* synthetic */ AnimatedImageDrawable e(Object obj) {
        return (AnimatedImageDrawable) obj;
    }

    public static /* bridge */ /* synthetic */ AudioRecordingConfiguration g(Object obj) {
        return (AudioRecordingConfiguration) obj;
    }

    @Override // z2.g
    public void a(Exception exc) {
    }

    @Override // z2.d
    public Object apply(Object obj) {
        return DataTransportCrashlyticsReportSender.lambda$static$0((CrashlyticsReport) obj);
    }

    @Override // coil.EventListener.Factory
    public EventListener create(ImageRequest imageRequest) {
        return coil.b.a(imageRequest);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        switch (this.f3899i) {
            case 0:
                return Mp4Extractor.lambda$static$1();
            case 1:
                return OggExtractor.lambda$static$0();
            case 2:
                return Ac3Extractor.lambda$static$0();
            case 3:
                return Ac4Extractor.lambda$static$0();
            case 4:
                return AdtsExtractor.lambda$static$0();
            case 5:
                return PsExtractor.lambda$static$0();
            case 6:
                return TsExtractor.lambda$static$1();
            case 7:
                return WavExtractor.lambda$static$0();
            default:
                return MatroskaExtractor.lambda$static$2();
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        int i11 = this.f3899i;
        return c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        int i10 = this.f3899i;
        return c.c(this, z);
    }

    @Override // com.google.firebase.components.f
    public Object f(u uVar) {
        switch (this.f3899i) {
            case 25:
                return (ScheduledExecutorService) ExecutorsRegistrar.f14305a.get();
            case 26:
                return (ScheduledExecutorService) ExecutorsRegistrar.f14307c.get();
            case 27:
                return (ScheduledExecutorService) ExecutorsRegistrar.f14306b.get();
            default:
                o oVar = ExecutorsRegistrar.f14305a;
                return p.f14343i;
        }
    }

    @Override // m5.a.InterfaceC0243a
    public void h(m5.b bVar) {
    }

    @Override // com.discord.org.webrtc.EglThread.ReleaseMonitor
    public boolean onRelease(EglThread eglThread) {
        return EglThread.lambda$create$1(eglThread);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        int i10 = this.f3899i;
        return c.d(this, factory);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        int i10 = this.f3899i;
        return c.a(this, uri, map);
    }
}
