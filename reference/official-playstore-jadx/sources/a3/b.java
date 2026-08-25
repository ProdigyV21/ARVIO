package a3;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.appcompat.widget.f0;
import androidx.compose.foundation.text2.SecureTextFieldController;
import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.runtime.State;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.datasource.ByteArrayDataSource;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.RandomTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleExtractor;
import b3.n;
import c2.d;
import coil.EventListener;
import coil.ImageLoader;
import coil.request.ImageRequest;
import com.arflix.tv.cast.CastManager;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.tasks.h;
import com.google.common.collect.a0;
import com.google.common.collect.a3;
import com.google.common.collect.e1;
import com.google.common.collect.q2;
import com.google.common.collect.u2;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.w;
import com.google.firebase.components.f;
import com.google.firebase.components.u;
import com.google.firebase.crashlytics.CrashlyticsRegistrar;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponentDeferredProxy;
import com.google.firebase.crashlytics.internal.CrashlyticsRemoteConfigListener;
import com.google.firebase.crashlytics.internal.RemoteConfigDeferredProxy;
import com.google.firebase.crashlytics.internal.common.SessionReportingCoordinator;
import com.google.firebase.crashlytics.internal.common.Utils;
import com.google.firebase.encoders.EncodingException;
import d2.g;
import io.sentry.a1;
import io.sentry.android.core.ActivityLifecycleIntegration;
import io.sentry.android.core.SentryPerformanceProvider;
import io.sentry.android.core.n1;
import io.sentry.android.replay.capture.o;
import io.sentry.e4;
import io.sentry.j7;
import io.sentry.m1;
import io.sentry.n5;
import io.sentry.util.j;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import m5.a;
import org.jsoup.helper.HttpConnection;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements i3.a, CodepointTransformation, ListenerSet.IterationFinishedEvent, w, ByteArrayDataSource.UriResolver, DefaultTrackSelector.TrackInfo.Factory, TrackSelectionUtil.AdaptiveTrackSelectionFactory, BinarySearchSeeker.SeekTimestampConverter, ReorderingBufferQueue.OutputConsumer, Consumer, d.a, EventListener.Factory, com.google.android.gms.tasks.e, com.google.android.gms.tasks.d, u2, f, a.InterfaceC0243a, com.google.android.gms.tasks.a, androidx.activity.result.b, e4, n1, j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f111i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f112l;

    public /* synthetic */ b(ActivityLifecycleIntegration activityLifecycleIntegration, m1 m1Var) {
        this.f111i = 23;
        this.f112l = m1Var;
    }

    @Override // androidx.activity.result.b
    public void a(Object obj) {
        ((l) ((State) this.f112l).getValue()).invoke(obj);
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f111i) {
            case 9:
                ((SubtitleExtractor) this.f112l).lambda$parseAndWriteToOutput$0((CuesWithTiming) obj);
                break;
            default:
                ((e1) this.f112l).c((CuesWithTiming) obj);
                break;
        }
    }

    @Override // com.google.common.util.concurrent.w
    public d1 apply(Object obj) {
        return SimpleBasePlayer.lambda$handleReplaceMediaItems$33((d1) this.f112l, obj);
    }

    @Override // c2.d.a
    public c2.d b(c2.c cVar) {
        Context context = (Context) this.f112l;
        String str = (String) cVar.f7383d;
        f0 f0Var = (f0) cVar.f7384e;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
        }
        return new g(context, str, f0Var, true, true);
    }

    public d c(Object obj) throws IOException {
        e eVar = (e) this.f112l;
        c cVar = (c) obj;
        URL url = cVar.f113a;
        String strI = ac.b.I("CctTransportBackend");
        if (Log.isLoggable(strI, 4)) {
            Log.i(strI, String.format("Making request to: %s", url));
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(eVar.f125g);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
        httpURLConnection.setRequestProperty("User-Agent", "datatransport/3.1.9 android/");
        httpURLConnection.setRequestProperty(HttpConnection.CONTENT_ENCODING, "gzip");
        httpURLConnection.setRequestProperty(HttpConnection.CONTENT_TYPE, "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = cVar.f115c;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    a8.e eVar2 = eVar.f119a;
                    b3.j jVar = cVar.f114b;
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(gZIPOutputStream));
                    i5.d dVar = (i5.d) eVar2.f183l;
                    i5.e eVar3 = new i5.e(bufferedWriter, dVar.f16222a, dVar.f16223b, dVar.f16224c, dVar.f16225d);
                    eVar3.h(jVar);
                    eVar3.j();
                    eVar3.f16227b.flush();
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    Integer numValueOf = Integer.valueOf(responseCode);
                    String strI2 = ac.b.I("CctTransportBackend");
                    if (Log.isLoggable(strI2, 4)) {
                        Log.i(strI2, String.format("Status Code: %d", numValueOf));
                    }
                    ac.b.x("CctTransportBackend", httpURLConnection.getHeaderField(HttpConnection.CONTENT_TYPE), "Content-Type: %s");
                    ac.b.x("CctTransportBackend", httpURLConnection.getHeaderField(HttpConnection.CONTENT_ENCODING), "Content-Encoding: %s");
                    if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                        return new d(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                    }
                    if (responseCode != 200) {
                        return new d(responseCode, null, 0L);
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        InputStream gZIPInputStream = "gzip".equals(httpURLConnection.getHeaderField(HttpConnection.CONTENT_ENCODING)) ? new GZIPInputStream(inputStream) : inputStream;
                        try {
                            d dVar2 = new d(responseCode, null, n.a(new BufferedReader(new InputStreamReader(gZIPInputStream))).f7189a);
                            if (gZIPInputStream != null) {
                                gZIPInputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return dVar2;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (EncodingException e5) {
            e = e5;
            ac.b.B("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new d(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, null, 0L);
        } catch (ConnectException e6) {
            e = e6;
            ac.b.B("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new d(500, null, 0L);
        } catch (UnknownHostException e10) {
            e = e10;
            ac.b.B("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new d(500, null, 0L);
        } catch (IOException e11) {
            e = e11;
            ac.b.B("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new d(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, null, 0L);
        }
    }

    @Override // androidx.media3.container.ReorderingBufferQueue.OutputConsumer
    public void consume(long j10, ParsableByteArray parsableByteArray) {
        ((FragmentedMp4Extractor) this.f112l).lambda$new$2(j10, parsableByteArray);
    }

    @Override // coil.EventListener.Factory
    public EventListener create(ImageRequest imageRequest) {
        return ImageLoader.Builder.eventListener$lambda$15((EventListener) this.f112l, imageRequest);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelectionUtil.AdaptiveTrackSelectionFactory
    public ExoTrackSelection createAdaptiveTrackSelection(ExoTrackSelection.Definition definition) {
        return ((RandomTrackSelection.Factory) this.f112l).lambda$createTrackSelections$0(definition);
    }

    @Override // com.google.common.collect.u2
    public Object d(Object obj, Object obj2) {
        return a0.x((List) ((Collection) obj2), new q2(((a3) this.f112l).f13936p, obj));
    }

    @Override // io.sentry.e4
    public void e(a1 a1Var) {
        j7 j7VarE;
        switch (this.f111i) {
            case 23:
                a1Var.N(new androidx.media3.exoplayer.analytics.b((m1) this.f112l, a1Var, 16));
                break;
            case 24:
            case 26:
            default:
                o oVar = (o) this.f112l;
                a1Var.p(oVar.e());
                String strX = a1Var.x();
                oVar.f16899l.setValue(oVar, io.sentry.android.replay.capture.c.f16888q[2], strX != null ? kotlin.text.o.E0('.', strX, strX) : null);
                break;
            case 25:
                AtomicLong atomicLong = ((io.sentry.android.core.a1) this.f112l).f16396i;
                if (atomicLong.get() == 0 && (j7VarE = a1Var.E()) != null && j7VarE.c() != null) {
                    atomicLong.set(j7VarE.c().getTime());
                    break;
                }
                break;
            case 27:
                a1Var.N(new androidx.media3.exoplayer.analytics.b((io.sentry.android.core.internal.gestures.g) this.f112l, a1Var, 19));
                break;
            case 28:
                a1Var.p(((io.sentry.android.replay.capture.f) this.f112l).e());
                break;
        }
    }

    @Override // com.google.firebase.components.f
    public Object f(u uVar) {
        switch (this.f111i) {
            case 16:
                return this.f112l;
            default:
                return ((CrashlyticsRegistrar) this.f112l).buildCrashlytics(uVar);
        }
    }

    @Override // io.sentry.util.j
    public Object g() {
        n5 n5Var = (n5) this.f112l;
        int i10 = SentryPerformanceProvider.f16368p;
        return n5Var;
    }

    @Override // m5.a.InterfaceC0243a
    public void h(m5.b bVar) {
        switch (this.f111i) {
            case 18:
                ((CrashlyticsNativeComponentDeferredProxy) this.f112l).lambda$new$0(bVar);
                break;
            default:
                RemoteConfigDeferredProxy.lambda$setupListener$0((CrashlyticsRemoteConfigListener) this.f112l, bVar);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public void invoke(Object obj, FlagSet flagSet) {
        ((SimpleBasePlayer) this.f112l).lambda$new$0((Player.Listener) obj, flagSet);
    }

    @Override // com.google.android.gms.tasks.d
    public void onFailure(Exception exc) {
        CastManager.initialize$lambda$2((CastManager) this.f112l, exc);
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        ((com.arflix.tv.cast.a) this.f112l).invoke(obj);
    }

    @Override // androidx.media3.datasource.ByteArrayDataSource.UriResolver
    public byte[] resolve(Uri uri) {
        return ByteArrayDataSource.lambda$new$0((byte[]) this.f112l, uri);
    }

    @Override // com.google.android.gms.tasks.a
    public Object then(h hVar) {
        switch (this.f111i) {
            case 20:
                return Boolean.valueOf(((SessionReportingCoordinator) this.f112l).onReportSendComplete(hVar));
            default:
                return Utils.lambda$awaitEvenIfOnMainThread$4((CountDownLatch) this.f112l, hVar);
        }
    }

    @Override // androidx.media3.extractor.BinarySearchSeeker.SeekTimestampConverter
    public long timeUsToTargetTime(long j10) {
        return ((FlacStreamMetadata) this.f112l).getSampleNumber(j10);
    }

    @Override // androidx.compose.foundation.text2.input.CodepointTransformation
    public int transform(int i10, int i11) {
        return SecureTextFieldController.codepointTransformation$lambda$0((SecureTextFieldController) this.f112l, i10, i11);
    }

    public /* synthetic */ b(Object obj, int i10) {
        this.f111i = i10;
        this.f112l = obj;
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public List create(int i10, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.lambda$selectImageTrack$5((DefaultTrackSelector.Parameters) this.f112l, i10, trackGroup, iArr);
    }
}
