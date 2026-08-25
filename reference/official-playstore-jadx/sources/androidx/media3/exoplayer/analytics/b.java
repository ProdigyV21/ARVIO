package androidx.media3.exoplayer.analytics;

import android.content.Context;
import android.net.Uri;
import android.os.Trace;
import androidx.appcompat.app.i1;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.text.SubtitleParser;
import com.arflix.tv.player.dvmkv.MatroskaExtractor;
import com.arflix.tv.util.SentryCrashReporter;
import io.sentry.a1;
import io.sentry.android.core.ActivityLifecycleIntegration;
import io.sentry.b4;
import io.sentry.e4;
import io.sentry.h0;
import io.sentry.j0;
import io.sentry.j5;
import io.sentry.j6;
import io.sentry.m1;
import io.sentry.y6;
import io.sentry.z3;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import l3.a;
import m5.a;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements ListenerSet.Event, ListenerSet.IterationFinishedEvent, ExtractorsFactory, j6, a.InterfaceC0243a, h0, b4, e4, z3, a.InterfaceC0242a, com.google.firebase.components.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3459i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3460l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3461m;

    public /* synthetic */ b(Object obj, Object obj2, int i10) {
        this.f3459i = i10;
        this.f3460l = obj;
        this.f3461m = obj2;
    }

    @Override // io.sentry.j6
    public j5 a(j5 j5Var, j0 j0Var) {
        return SentryCrashReporter.initialize$lambda$0$0$0((SentryCrashReporter) this.f3460l, (Context) this.f3461m, j5Var, j0Var);
    }

    @Override // io.sentry.b4
    public void b(m1 m1Var) {
        switch (this.f3459i) {
            case 16:
                m1 m1Var2 = (m1) this.f3460l;
                a1 a1Var = (a1) this.f3461m;
                if (m1Var == m1Var2) {
                    a1Var.C();
                }
                break;
            default:
                io.sentry.android.core.internal.gestures.g gVar = (io.sentry.android.core.internal.gestures.g) this.f3460l;
                a1 a1Var2 = (a1) this.f3461m;
                if (m1Var == gVar.f16545o) {
                    a1Var2.C();
                }
                break;
        }
    }

    @Override // io.sentry.z3
    public void c(i1 i1Var) {
        a1 a1Var = (a1) this.f3460l;
        y6 y6Var = (y6) this.f3461m;
        io.sentry.d dVar = (io.sentry.d) i1Var.f1061n;
        if (dVar.f17208e) {
            i1 i1VarF = a1Var.F();
            io.sentry.protocol.v vVarO = a1Var.o();
            dVar.b("sentry-trace_id", ((io.sentry.protocol.v) i1VarF.f1059l).toString());
            dVar.b("sentry-public_key", y6Var.retrieveParsedDsn().f16296b);
            dVar.b("sentry-release", y6Var.getRelease());
            dVar.b("sentry-environment", y6Var.getEnvironment());
            if (!io.sentry.protocol.v.f17707l.equals(vVarO)) {
                dVar.b("sentry-replay_id", vVarO.toString());
            }
            dVar.b("sentry-org_id", y6Var.getEffectiveOrgId());
            dVar.b("sentry-transaction", null);
            if (dVar.f17208e) {
                dVar.f17206c = null;
            }
            dVar.b("sentry-sampled", null);
            dVar.f17208e = false;
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        return MatroskaExtractor.lambda$newFactory$1((SubtitleParser.Factory) this.f3460l, (MatroskaExtractor.DolbyVisionSampleTransformer) this.f3461m);
    }

    @Override // io.sentry.e4
    public void e(a1 a1Var) {
        switch (this.f3459i) {
            case 17:
                a1Var.N(new io.sentry.android.core.g((ActivityLifecycleIntegration) this.f3460l, a1Var, (m1) this.f3461m));
                break;
            default:
                a1Var.N(new androidx.media3.exoplayer.hls.b((io.sentry.android.core.internal.gestures.g) this.f3460l, a1Var, (m1) this.f3461m, 5));
                break;
        }
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        switch (this.f3459i) {
            case 21:
                ((k3.f) this.f3460l).f19490c.n((Iterable) this.f3461m);
                break;
            default:
                k3.f fVar = (k3.f) this.f3460l;
                Iterator it = ((HashMap) this.f3461m).entrySet().iterator();
                while (it.hasNext()) {
                    fVar.f19496i.l(((Integer) r2.getValue()).intValue(), g3.c.INVALID_PAYLOD, (String) ((Map.Entry) it.next()).getKey());
                }
                break;
        }
        return null;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        return androidx.media3.extractor.c.b(this, i10);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        return androidx.media3.extractor.c.c(this, z);
    }

    @Override // com.google.firebase.components.f
    public Object f(com.google.firebase.components.u uVar) {
        switch (this.f3459i) {
            case 23:
                return new t5.a((String) this.f3460l, ((t5.d) this.f3461m).a((Context) uVar.a(Context.class)));
            default:
                String str = (String) this.f3460l;
                com.google.firebase.components.b bVar = (com.google.firebase.components.b) this.f3461m;
                try {
                    Trace.beginSection(str);
                    return bVar.f14260f.f(uVar);
                } finally {
                    Trace.endSection();
                }
        }
    }

    @Override // m5.a.InterfaceC0243a
    public void h(m5.b bVar) {
        a.InterfaceC0243a interfaceC0243a = (a.InterfaceC0243a) this.f3460l;
        a.InterfaceC0243a interfaceC0243a2 = (a.InterfaceC0243a) this.f3461m;
        interfaceC0243a.h(bVar);
        interfaceC0243a2.h(bVar);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3459i) {
            case 0:
                ((AnalyticsListener) obj).onPlaybackParametersChanged((AnalyticsListener.EventTime) this.f3460l, (PlaybackParameters) this.f3461m);
                break;
            case 1:
                ((AnalyticsListener) obj).onTrackSelectionParametersChanged((AnalyticsListener.EventTime) this.f3460l, (TrackSelectionParameters) this.f3461m);
                break;
            case 2:
                ((AnalyticsListener) obj).onAvailableCommandsChanged((AnalyticsListener.EventTime) this.f3460l, (Player.Commands) this.f3461m);
                break;
            case 3:
            default:
                ((AnalyticsListener) obj).onCues((AnalyticsListener.EventTime) this.f3460l, (CueGroup) this.f3461m);
                break;
            case 4:
                ((AnalyticsListener) obj).onAudioAttributesChanged((AnalyticsListener.EventTime) this.f3460l, (AudioAttributes) this.f3461m);
                break;
            case 5:
                ((AnalyticsListener) obj).onMetadata((AnalyticsListener.EventTime) this.f3460l, (Metadata) this.f3461m);
                break;
            case 6:
                ((AnalyticsListener) obj).onTracksChanged((AnalyticsListener.EventTime) this.f3460l, (Tracks) this.f3461m);
                break;
            case 7:
                DefaultAnalyticsCollector.lambda$onDrmKeysLoaded$65((AnalyticsListener.EventTime) this.f3460l, (KeyRequestInfo) this.f3461m, (AnalyticsListener) obj);
                break;
            case 8:
                ((AnalyticsListener) obj).onCues((AnalyticsListener.EventTime) this.f3460l, (List<Cue>) this.f3461m);
                break;
            case 9:
                ((AnalyticsListener) obj).onDeviceInfoChanged((AnalyticsListener.EventTime) this.f3460l, (DeviceInfo) this.f3461m);
                break;
            case 10:
                DefaultAnalyticsCollector.lambda$onVideoSizeChanged$59((AnalyticsListener.EventTime) this.f3460l, (VideoSize) this.f3461m, (AnalyticsListener) obj);
                break;
        }
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return androidx.media3.extractor.c.d(this, factory);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        return androidx.media3.extractor.c.a(this, uri, map);
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public void invoke(Object obj, FlagSet flagSet) {
        ((DefaultAnalyticsCollector) this.f3460l).lambda$setPlayer$0((Player) this.f3461m, (AnalyticsListener) obj, flagSet);
    }
}
