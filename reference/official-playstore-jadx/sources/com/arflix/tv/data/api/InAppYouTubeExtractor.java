package com.arflix.tv.data.api;

import android.net.Uri;
import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import com.google.gson.Gson;
import f7.j;
import gb.a0;
import gb.g0;
import gb.h0;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.f0;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import kotlinx.coroutines.TimeoutCancellationException;
import r7.p;
import x6.j0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\r\u0010\bJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0082@¢\u0006\u0004\b\u000e\u0010\bJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0016\u0010\u0017JC\u0010\u001e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001d2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010 \u001a\u00020\u0004H\u0002¢\u0006\u0004\b\"\u0010#J#\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010$\u001a\u00020\u0004H\u0002¢\u0006\u0004\b%\u0010&J#\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0(2\u0006\u0010'\u001a\u00020\u0004H\u0002¢\u0006\u0004\b*\u0010+J\u001b\u0010-\u001a\u0004\u0018\u00010)2\b\u0010,\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020\u0004H\u0002¢\u0006\u0004\b1\u00102J'\u00107\u001a\u0002052\u0006\u00103\u001a\u00020)2\u0006\u00104\u001a\u00020)2\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\b7\u00108J\u001f\u0010:\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00109\u001a\u000205H\u0002¢\u0006\u0004\b:\u0010;J#\u0010?\u001a\b\u0012\u0004\u0012\u00020=0<2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020=0<H\u0002¢\u0006\u0004\b?\u0010@J\u0017\u0010B\u001a\u00020)2\u0006\u0010A\u001a\u00020\u0004H\u0002¢\u0006\u0004\bB\u0010CJ'\u0010E\u001a\u0004\u0018\u00010=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010D\u001a\u00020\u0004H\u0002¢\u0006\u0004\bE\u0010FJ\u001f\u0010I\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u0004H\u0002¢\u0006\u0004\bI\u0010JJ?\u0010O\u001a\u00020N2\u0006\u0010/\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001d2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\bO\u0010PJ#\u0010S\u001a\u00020R2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001dH\u0002¢\u0006\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR,\u0010]\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\\0(0[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010`\u001a\u00020_8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010b\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bb\u0010cR\u0016\u0010d\u001a\u00020\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010e¨\u0006f"}, d2 = {"Lcom/arflix/tv/data/api/InAppYouTubeExtractor;", "", "<init>", "()V", "", "youtubeUrl", "Lcom/arflix/tv/data/api/TrailerPlaybackSource;", "extractPlaybackSource", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "videoId", "Lx6/t0;", "evictCache", "(Ljava/lang/String;)V", "refreshWatchConfigFromPage", "extractPlaybackSourceInternal", "Lcom/arflix/tv/data/api/WatchConfig;", "getWatchConfig", "(Ld7/d;)Ljava/lang/Object;", "html", "parseWatchConfig", "(Ljava/lang/String;)Lcom/arflix/tv/data/api/WatchConfig;", "input", "extractVideoId", "(Ljava/lang/String;)Ljava/lang/String;", "apiKey", "Lcom/arflix/tv/data/api/YouTubeClient;", "client", "visitorData", "cookieHeader", "", "fetchPlayerResponse", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/YouTubeClient;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map;", "manifestUrl", "Lcom/arflix/tv/data/api/ManifestBestVariant;", "parseHlsManifest", "(Ljava/lang/String;)Lcom/arflix/tv/data/api/ManifestBestVariant;", "line", "parseHlsAttributeList", "(Ljava/lang/String;)Ljava/util/Map;", "raw", "Lx6/x;", "", "parseResolution", "(Ljava/lang/String;)Lx6/x;", "label", "parseQualityLabel", "(Ljava/lang/String;)Ljava/lang/Integer;", "url", "", "hasNParam", "(Ljava/lang/String;)Z", "height", "fps", "", "bitrate", "videoScore", "(IID)D", "audioSampleRate", "audioScore", "(DD)D", "", "Lcom/arflix/tv/data/api/StreamCandidate;", "items", "sortCandidates", "(Ljava/util/List;)Ljava/util/List;", "ext", "containerPreference", "(Ljava/lang/String;)I", "clientKey", "pickBestForClient", "(Ljava/util/List;Ljava/lang/String;)Lcom/arflix/tv/data/api/StreamCandidate;", "baseUrl", "maybeRelative", "absolutizeUrl", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "method", "headers", TtmlNode.TAG_BODY, "Lcom/arflix/tv/data/api/RequestResponse;", "performRequest", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)Lcom/arflix/tv/data/api/RequestResponse;", "source", "Lgb/a0;", "buildHeaders", "(Ljava/util/Map;)Lgb/a0;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Lgb/h0;", "httpClient", "Lgb/h0;", "j$/util/concurrent/ConcurrentHashMap", "", "urlCache", "Lj$/util/concurrent/ConcurrentHashMap;", "Lua/a;", "watchConfigMutex", "Lua/a;", "cachedWatchConfig", "Lcom/arflix/tv/data/api/WatchConfig;", "watchConfigFetchedAt", "J", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InAppYouTubeExtractor {
    public static final int $stable = 8;
    private WatchConfig cachedWatchConfig;
    private final Gson gson = new Gson();
    private final h0 httpClient;
    private final ConcurrentHashMap<String, x> urlCache;
    private long watchConfigFetchedAt;
    private final ua.a watchConfigMutex;

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSource$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/TrailerPlaybackSource;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/TrailerPlaybackSource;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSource$2", f = "YouTubeExtractor.kt", l = {176}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super TrailerPlaybackSource>, Object> {
        final /* synthetic */ String $youtubeUrl;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ InAppYouTubeExtractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, InAppYouTubeExtractor inAppYouTubeExtractor, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$youtubeUrl = str;
            this.this$0 = inAppYouTubeExtractor;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$youtubeUrl, this.this$0, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r0v7 */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            String str;
            String strExtractVideoId;
            String str2 = this.label;
            TrailerPlaybackSource trailerPlaybackSource = null;
            try {
                if (str2 == 0) {
                    k2.c.G(obj);
                    if (o.h0(this.$youtubeUrl) || (strExtractVideoId = this.this$0.extractVideoId(this.$youtubeUrl)) == null) {
                        return null;
                    }
                    x xVar = (x) this.this$0.urlCache.get(strExtractVideoId);
                    if (xVar != null && System.currentTimeMillis() - ((Number) xVar.f22609l).longValue() < 300000) {
                        return xVar.f22608i;
                    }
                    InAppYouTubeExtractor$extractPlaybackSource$2$source$1 inAppYouTubeExtractor$extractPlaybackSource$2$source$1 = new InAppYouTubeExtractor$extractPlaybackSource$2$source$1(this.this$0, strExtractVideoId, null);
                    this.L$0 = strExtractVideoId;
                    this.L$1 = null;
                    this.label = 1;
                    obj = m0.z(30000L, inAppYouTubeExtractor$extractPlaybackSource$2$source$1, this);
                    e7.a aVar = e7.a.f15033i;
                    str2 = strExtractVideoId;
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (str2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    String str3 = (String) this.L$0;
                    k2.c.G(obj);
                    str2 = str3;
                }
                trailerPlaybackSource = (TrailerPlaybackSource) obj;
                str = str2;
            } catch (TimeoutCancellationException unused) {
                Log.w("InAppYouTubeExtractor", "[" + str2 + "] extraction timed out");
                str = str2;
            } catch (CancellationException e5) {
                throw e5;
            } catch (Exception e6) {
                if (e6 instanceof CancellationException) {
                    throw e6;
                }
                Log.w("InAppYouTubeExtractor", "[" + str2 + "] extraction failed: " + e6.getMessage());
                str = str2;
            }
            if (trailerPlaybackSource != null) {
                this.this$0.urlCache.put(str, new x(trailerPlaybackSource, new Long(System.currentTimeMillis())));
            } else {
                f7.f.c(Log.w("InAppYouTubeExtractor", "[" + str + "] no playable source found"));
            }
            return trailerPlaybackSource;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super TrailerPlaybackSource> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor", f = "YouTubeExtractor.kt", l = {200, 211, 296, 297, 306}, m = "extractPlaybackSourceInternal", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppYouTubeExtractor.this.extractPlaybackSourceInternal(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$2", f = "YouTubeExtractor.kt", l = {235}, m = "invokeSuspend", v = 2)
    public static final class C06512 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<StreamCandidate> $adaptiveAudio;
        final /* synthetic */ List<StreamCandidate> $adaptiveVideo;
        final /* synthetic */ String $apiKey;
        final /* synthetic */ f0 $keyRejected;
        final /* synthetic */ List<j0> $manifestUrls;
        final /* synthetic */ List<StreamCandidate> $progressive;
        final /* synthetic */ String $videoId;
        final /* synthetic */ WatchConfig $watchConfig;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06512(String str, String str2, WatchConfig watchConfig, f0 f0Var, List<j0> list, List<StreamCandidate> list2, List<StreamCandidate> list3, List<StreamCandidate> list4, d7.d<? super C06512> dVar) {
            super(2, dVar);
            this.$apiKey = str;
            this.$videoId = str2;
            this.$watchConfig = watchConfig;
            this.$keyRejected = f0Var;
            this.$manifestUrls = list;
            this.$progressive = list2;
            this.$adaptiveVideo = list3;
            this.$adaptiveAudio = list4;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C06512 c06512 = InAppYouTubeExtractor.this.new C06512(this.$apiKey, this.$videoId, this.$watchConfig, this.$keyRejected, this.$manifestUrls, this.$progressive, this.$adaptiveVideo, this.$adaptiveAudio, dVar);
            c06512.L$0 = obj;
            return c06512;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objF;
            Iterator it;
            double dDoubleValue;
            double dDoubleValue2;
            Iterator it2;
            double d4;
            k0 k0Var = (k0) this.L$0;
            e7.a aVar = e7.a.f15033i;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List list = YouTubeExtractorKt.CLIENTS;
                InAppYouTubeExtractor inAppYouTubeExtractor = InAppYouTubeExtractor.this;
                String str = this.$apiKey;
                String str2 = this.$videoId;
                WatchConfig watchConfig = this.$watchConfig;
                f0 f0Var = this.$keyRejected;
                ArrayList arrayList = new ArrayList(s.U(list, 10));
                Iterator it3 = list.iterator();
                while (it3.hasNext()) {
                    arrayList.add(m0.e(2, x0.f19655d, k0Var, new InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1(inAppYouTubeExtractor, str, str2, (YouTubeClient) it3.next(), watchConfig, f0Var, null)));
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                objF = m0.f(arrayList, this);
                if (objF == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objF = obj;
            }
            ArrayList arrayListK0 = kotlin.collections.x.k0((Iterable) objF);
            List<j0> list2 = this.$manifestUrls;
            InAppYouTubeExtractor inAppYouTubeExtractor2 = InAppYouTubeExtractor.this;
            List<StreamCandidate> list3 = this.$progressive;
            List<StreamCandidate> list4 = this.$adaptiveVideo;
            List<StreamCandidate> list5 = this.$adaptiveAudio;
            Iterator it4 = arrayListK0.iterator();
            while (it4.hasNext()) {
                x xVar = (x) it4.next();
                YouTubeClient youTubeClient = (YouTubeClient) xVar.f22608i;
                Map mapMapValue = YouTubeExtractorKt.mapValue((Map) xVar.f22609l, "streamingData");
                if (mapMapValue == null) {
                    it = it4;
                } else {
                    String strStringValue = YouTubeExtractorKt.stringValue(mapMapValue, "hlsManifestUrl");
                    if (strStringValue != null && !o.h0(strStringValue)) {
                        synchronized (list2) {
                            list2.add(new j0(youTubeClient.getKey(), new Integer(youTubeClient.getPriority()), strStringValue));
                        }
                    }
                    for (Map map : YouTubeExtractorKt.listMapValue(mapMapValue, "formats")) {
                        String strStringValue2 = YouTubeExtractorKt.stringValue(map, "url");
                        if (strStringValue2 != null) {
                            String strStringValue3 = YouTubeExtractorKt.stringValue(map, "mimeType");
                            if (strStringValue3 == null) {
                                strStringValue3 = "";
                            }
                            if (o.T(strStringValue3, "video/", false) || o.h0(strStringValue3)) {
                                Double dNumberValue = YouTubeExtractorKt.numberValue(map, "height");
                                if (dNumberValue != null) {
                                    dDoubleValue2 = dNumberValue.doubleValue();
                                } else {
                                    Double d10 = inAppYouTubeExtractor2.parseQualityLabel(YouTubeExtractorKt.stringValue(map, "qualityLabel")) != null ? new Double(r13.intValue()) : null;
                                    dDoubleValue2 = d10 != null ? d10.doubleValue() : 0.0d;
                                }
                                int i11 = (int) dDoubleValue2;
                                Double dNumberValue2 = YouTubeExtractorKt.numberValue(map, "fps");
                                int iDoubleValue = (int) (dNumberValue2 != null ? dNumberValue2.doubleValue() : 0.0d);
                                Double dNumberValue3 = YouTubeExtractorKt.numberValue(map, "bitrate");
                                if (dNumberValue3 == null && (dNumberValue3 = YouTubeExtractorKt.numberValue(map, "averageBitrate")) == null) {
                                    it2 = it4;
                                    d4 = 0.0d;
                                } else {
                                    double dDoubleValue3 = dNumberValue3.doubleValue();
                                    it2 = it4;
                                    d4 = dDoubleValue3;
                                }
                                synchronized (list3) {
                                    try {
                                        String key = youTubeClient.getKey();
                                        int priority = youTubeClient.getPriority();
                                        double dVideoScore = inAppYouTubeExtractor2.videoScore(i11, iDoubleValue, d4);
                                        boolean zHasNParam = inAppYouTubeExtractor2.hasNParam(strStringValue2);
                                        String strStringValue4 = YouTubeExtractorKt.stringValue(map, "itag");
                                        if (strStringValue4 == null) {
                                            strStringValue4 = "";
                                        }
                                        list3.add(new StreamCandidate(key, priority, strStringValue2, dVideoScore, zHasNParam, strStringValue4, i11, iDoubleValue, o.T(strStringValue3, "webm", false) ? "webm" : "mp4"));
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                }
                                it4 = it2;
                            }
                        }
                    }
                    it = it4;
                    for (Map map2 : YouTubeExtractorKt.listMapValue(mapMapValue, "adaptiveFormats")) {
                        String strStringValue5 = YouTubeExtractorKt.stringValue(map2, "url");
                        if (strStringValue5 != null) {
                            String strStringValue6 = YouTubeExtractorKt.stringValue(map2, "mimeType");
                            if (strStringValue6 == null) {
                                strStringValue6 = "";
                            }
                            if (o.T(strStringValue6, "video/", false)) {
                                Double dNumberValue4 = YouTubeExtractorKt.numberValue(map2, "height");
                                if (dNumberValue4 != null) {
                                    dDoubleValue = dNumberValue4.doubleValue();
                                } else {
                                    Double d11 = inAppYouTubeExtractor2.parseQualityLabel(YouTubeExtractorKt.stringValue(map2, "qualityLabel")) != null ? new Double(r10.intValue()) : null;
                                    dDoubleValue = d11 != null ? d11.doubleValue() : 0.0d;
                                }
                                int i12 = (int) dDoubleValue;
                                Double dNumberValue5 = YouTubeExtractorKt.numberValue(map2, "fps");
                                int iDoubleValue2 = (int) (dNumberValue5 != null ? dNumberValue5.doubleValue() : 0.0d);
                                Double dNumberValue6 = YouTubeExtractorKt.numberValue(map2, "bitrate");
                                double dDoubleValue4 = (dNumberValue6 == null && (dNumberValue6 = YouTubeExtractorKt.numberValue(map2, "averageBitrate")) == null) ? 0.0d : dNumberValue6.doubleValue();
                                synchronized (list4) {
                                    try {
                                        String key2 = youTubeClient.getKey();
                                        int priority2 = youTubeClient.getPriority();
                                        double dVideoScore2 = inAppYouTubeExtractor2.videoScore(i12, iDoubleValue2, dDoubleValue4);
                                        boolean zHasNParam2 = inAppYouTubeExtractor2.hasNParam(strStringValue5);
                                        String strStringValue7 = YouTubeExtractorKt.stringValue(map2, "itag");
                                        if (strStringValue7 == null) {
                                            strStringValue7 = "";
                                        }
                                        list4.add(new StreamCandidate(key2, priority2, strStringValue5, dVideoScore2, zHasNParam2, strStringValue7, i12, iDoubleValue2, o.T(strStringValue6, "webm", false) ? "webm" : "mp4"));
                                    } catch (Throwable th2) {
                                        throw th2;
                                    }
                                }
                            } else if (o.T(strStringValue6, "audio/", false) || u.P(strStringValue6, "audio/", false)) {
                                Double dNumberValue7 = YouTubeExtractorKt.numberValue(map2, "bitrate");
                                double dDoubleValue5 = (dNumberValue7 == null && (dNumberValue7 = YouTubeExtractorKt.numberValue(map2, "averageBitrate")) == null) ? 0.0d : dNumberValue7.doubleValue();
                                Double dNumberValue8 = YouTubeExtractorKt.numberValue(map2, "audioSampleRate");
                                double dDoubleValue6 = dNumberValue8 != null ? dNumberValue8.doubleValue() : 0.0d;
                                synchronized (list5) {
                                    try {
                                        String key3 = youTubeClient.getKey();
                                        int priority3 = youTubeClient.getPriority();
                                        double dAudioScore = inAppYouTubeExtractor2.audioScore(dDoubleValue5, dDoubleValue6);
                                        boolean zHasNParam3 = inAppYouTubeExtractor2.hasNParam(strStringValue5);
                                        String strStringValue8 = YouTubeExtractorKt.stringValue(map2, "itag");
                                        if (strStringValue8 == null) {
                                            strStringValue8 = "";
                                        }
                                        list5.add(new StreamCandidate(key3, priority3, strStringValue5, dAudioScore, zHasNParam3, strStringValue8, 0, 0, o.T(strStringValue6, "webm", false) ? "webm" : "m4a"));
                                    } catch (Throwable th3) {
                                        throw th3;
                                    }
                                }
                            }
                        }
                    }
                }
                it4 = it;
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C06512) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$3", f = "YouTubeExtractor.kt", l = {MediaError.DetailedErrorCode.DASH_NETWORK}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ kotlin.jvm.internal.k0 $bestManifest;
        final /* synthetic */ List<j0> $manifestUrls;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ InAppYouTubeExtractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(List<j0> list, InAppYouTubeExtractor inAppYouTubeExtractor, kotlin.jvm.internal.k0 k0Var, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$manifestUrls = list;
            this.this$0 = inAppYouTubeExtractor;
            this.$bestManifest = k0Var;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$manifestUrls, this.this$0, this.$bestManifest, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<j0> list = this.$manifestUrls;
                InAppYouTubeExtractor inAppYouTubeExtractor = this.this$0;
                ArrayList arrayList = new ArrayList(s.U(list, 10));
                for (j0 j0Var : list) {
                    String str = (String) j0Var.f22587i;
                    int iIntValue = ((Number) j0Var.f22588l).intValue();
                    arrayList.add(m0.e(2, x0.f19655d, k0Var, new InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1(inAppYouTubeExtractor, (String) j0Var.f22589m, str, iIntValue, null)));
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                obj = m0.f(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ArrayList<ManifestCandidate> arrayListK0 = kotlin.collections.x.k0((Iterable) obj);
            kotlin.jvm.internal.k0 k0Var2 = this.$bestManifest;
            for (ManifestCandidate manifestCandidate : arrayListK0) {
                if (k0Var2.f19746i == null || manifestCandidate.getHeight() > ((ManifestCandidate) k0Var2.f19746i).getHeight() || (manifestCandidate.getHeight() == ((ManifestCandidate) k0Var2.f19746i).getHeight() && manifestCandidate.getBandwidth() > ((ManifestCandidate) k0Var2.f19746i).getBandwidth())) {
                    k0Var2.f19746i = manifestCandidate;
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$getWatchConfig$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor", f = "YouTubeExtractor.kt", l = {616}, m = "getWatchConfig", v = 2)
    public static final class C06521 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06521(d7.d<? super C06521> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppYouTubeExtractor.this.getWatchConfig(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.api.InAppYouTubeExtractor$refreshWatchConfigFromPage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor", f = "YouTubeExtractor.kt", l = {616}, m = "refreshWatchConfigFromPage", v = 2)
    public static final class C06531 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06531(d7.d<? super C06531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppYouTubeExtractor.this.refreshWatchConfigFromPage(null, this);
        }
    }

    @Inject
    public InAppYouTubeExtractor() {
        g0 g0Var = new g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(20L, timeUnit);
        g0Var.e(20L, timeUnit);
        g0Var.f(20L);
        g0Var.f15670h = true;
        g0Var.f15671i = true;
        this.httpClient = new h0(g0Var);
        this.urlCache = new ConcurrentHashMap<>();
        this.watchConfigMutex = ua.e.a();
    }

    private final String absolutizeUrl(String baseUrl, String maybeRelative) {
        try {
            return new URL(new URL(baseUrl), maybeRelative).toString();
        } catch (MalformedURLException unused) {
            return maybeRelative;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double audioScore(double bitrate, double audioSampleRate) {
        return (bitrate * 1000000.0d) + audioSampleRate;
    }

    private final a0 buildHeaders(Map<String, String> source) {
        ArrayList arrayList = new ArrayList(20);
        for (Map.Entry<String, String> entry : source.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!u.L(key, "Accept-Encoding", true)) {
                t7.a.g(key);
                t7.a.h(value, key);
                arrayList.add(key);
                a2.x(value, arrayList);
            }
        }
        Set<String> setKeySet = source.keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            t7.a.g("User-Agent");
            t7.a.h("Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36", "User-Agent");
            arrayList.add("User-Agent");
            a2.x("Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36", arrayList);
        } else {
            Iterator<T> it = setKeySet.iterator();
            while (it.hasNext()) {
                if (u.L((String) it.next(), "User-Agent", true)) {
                    break;
                }
            }
            t7.a.g("User-Agent");
            t7.a.h("Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36", "User-Agent");
            arrayList.add("User-Agent");
            a2.x("Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36", arrayList);
        }
        return new a0((String[]) arrayList.toArray(new String[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int containerPreference(String ext) {
        String lowerCase = ext.toLowerCase(Locale.ROOT);
        int iHashCode = lowerCase.hashCode();
        return iHashCode != 106458 ? iHashCode != 108273 ? (iHashCode == 3645337 && lowerCase.equals("webm")) ? 1 : 2 : !lowerCase.equals("mp4") ? 2 : 0 : !lowerCase.equals("m4a") ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01bf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object extractPlaybackSourceInternal(java.lang.String r21, d7.d<? super com.arflix.tv.data.api.TrailerPlaybackSource> r22) {
        /*
            Method dump skipped, instruction units count: 603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.extractPlaybackSourceInternal(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractVideoId(String input) {
        String str;
        String string = o.L0(input).toString();
        YouTubeExtractorRegexes youTubeExtractorRegexes = YouTubeExtractorRegexes.INSTANCE;
        if (youTubeExtractorRegexes.getVIDEO_ID_REGEX().e(string)) {
            return string;
        }
        if (!u.P(string, "http://", false) && !u.P(string, "https://", false)) {
            string = "https://".concat(string);
        }
        try {
            Uri uri = Uri.parse(string);
            String host = uri.getHost();
            String lowerCase = host != null ? host.toLowerCase(Locale.ROOT) : null;
            if (lowerCase == null) {
                lowerCase = "";
            }
            if (u.K(lowerCase, "youtu.be", false) && (str = (String) kotlin.collections.x.o0(uri.getPathSegments())) != null && !o.h0(str) && youTubeExtractorRegexes.getVIDEO_ID_REGEX().e(str)) {
                return str;
            }
            String queryParameter = uri.getQueryParameter("v");
            if (queryParameter != null && !o.h0(queryParameter) && youTubeExtractorRegexes.getVIDEO_ID_REGEX().e(queryParameter)) {
                return queryParameter;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() >= 2) {
                String str2 = pathSegments.get(0);
                String str3 = pathSegments.get(1);
                if (kotlin.jvm.internal.p.a(str2, "embed") || kotlin.jvm.internal.p.a(str2, "shorts") || kotlin.jvm.internal.p.a(str2, "live")) {
                    if (youTubeExtractorRegexes.getVIDEO_ID_REGEX().e(str3)) {
                        return str3;
                    }
                }
            }
        } catch (IllegalArgumentException | UnsupportedOperationException unused) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<?, ?> fetchPlayerResponse(String apiKey, String videoId, YouTubeClient client, String visitorData, String cookieHeader) {
        String strC = androidx.compose.material3.d.C("https://www.youtube.com/youtubei/v1/player?key=", Uri.encode(apiKey));
        z6.f fVar = new z6.f();
        fVar.putAll(YouTubeExtractorKt.DEFAULT_HEADERS);
        fVar.put("content-type", "application/json");
        fVar.put(TtmlNode.ATTR_TTS_ORIGIN, "https://www.youtube.com");
        fVar.put("x-youtube-client-name", client.getId());
        fVar.put("x-youtube-client-version", client.getVersion());
        fVar.put("user-agent", client.getUserAgent());
        if (visitorData != null && !o.h0(visitorData)) {
            fVar.put("x-goog-visitor-id", visitorData);
        }
        if (cookieHeader != null && !o.h0(cookieHeader)) {
            fVar.put("cookie", cookieHeader);
        }
        z6.f fVarC = fVar.c();
        x xVar = new x("videoId", videoId);
        Boolean bool = Boolean.TRUE;
        RequestResponse requestResponsePerformRequest = performRequest(strC, HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, fVarC, this.gson.toJson(kotlin.collections.h0.t0(xVar, new x("contentCheckOk", bool), new x("racyCheckOk", bool), new x("context", Collections.singletonMap("client", client.getContext())), new x("playbackContext", Collections.singletonMap("contentPlaybackContext", Collections.singletonMap("html5Preference", "HTML5_PREF_WANTS"))))));
        if (requestResponsePerformRequest.getOk()) {
            Map<?, ?> map = (Map) this.gson.fromJson(requestResponsePerformRequest.getBody(), Map.class);
            return map == null ? kotlin.collections.a0.f19683i : map;
        }
        String key = client.getKey();
        int status = requestResponsePerformRequest.getStatus();
        String strI0 = o.I0(200, requestResponsePerformRequest.getBody());
        StringBuilder sbQ = a2.q("player API ", key, " failed (", status, "): ");
        sbQ.append(strI0);
        throw new IllegalStateException(sbQ.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWatchConfig(d7.d<? super com.arflix.tv.data.api.WatchConfig> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.api.InAppYouTubeExtractor.C06521
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.api.InAppYouTubeExtractor$getWatchConfig$1 r0 = (com.arflix.tv.data.api.InAppYouTubeExtractor.C06521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.api.InAppYouTubeExtractor$getWatchConfig$1 r0 = new com.arflix.tv.data.api.InAppYouTubeExtractor$getWatchConfig$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r0 = r0.L$0
            ua.a r0 = (ua.a) r0
            k2.c.G(r9)
            goto L47
        L29:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L31:
            k2.c.G(r9)
            ua.a r9 = r8.watchConfigMutex
            r0.L$0 = r9
            r1 = 0
            r0.I$0 = r1
            r0.label = r2
            java.lang.Object r0 = r9.c(r0)
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L46
            return r1
        L46:
            r0 = r9
        L47:
            r9 = 0
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L5c
            com.arflix.tv.data.api.WatchConfig r3 = r8.cachedWatchConfig     // Catch: java.lang.Throwable -> L5c
            if (r3 == 0) goto L5e
            long r4 = r8.watchConfigFetchedAt     // Catch: java.lang.Throwable -> L5c
            long r4 = r1 - r4
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L5e
            goto L69
        L5c:
            r1 = move-exception
            goto L6d
        L5e:
            com.arflix.tv.data.api.WatchConfig r3 = new com.arflix.tv.data.api.WatchConfig     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8"
            r3.<init>(r4, r9)     // Catch: java.lang.Throwable -> L5c
            r8.cachedWatchConfig = r3     // Catch: java.lang.Throwable -> L5c
            r8.watchConfigFetchedAt = r1     // Catch: java.lang.Throwable -> L5c
        L69:
            r0.b(r9)
            return r3
        L6d:
            r0.b(r9)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.getWatchConfig(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean hasNParam(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L18
            java.lang.String r1 = "n"
            java.lang.String r3 = r3.getQueryParameter(r1)     // Catch: java.lang.Throwable -> L18
            r1 = 1
            if (r3 == 0) goto L14
            boolean r3 = kotlin.text.o.h0(r3)     // Catch: java.lang.Throwable -> L18
            if (r3 == 0) goto L15
        L14:
            r0 = r1
        L15:
            r3 = r0 ^ 1
            return r3
        L18:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.hasNParam(java.lang.String):boolean");
    }

    private final Map<String, String> parseHlsAttributeList(String line) {
        int iE0 = o.e0(line, ':', 0, false, 6);
        if (iE0 == -1) {
            return kotlin.collections.a0.f19683i;
        }
        String strSubstring = line.substring(iE0 + 1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        int length = strSubstring.length();
        boolean z = true;
        boolean z5 = false;
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = strSubstring.charAt(i10);
            if (z) {
                if (cCharAt == '=') {
                    z = false;
                } else {
                    sb2.append(cCharAt);
                }
            } else if (cCharAt == '\"') {
                z5 = !z5;
            } else if (cCharAt != ',' || z5) {
                sb3.append(cCharAt);
            } else {
                String string = o.L0(sb2.toString()).toString();
                if (string.length() > 0) {
                    linkedHashMap.put(string, o.L0(sb3.toString()).toString());
                }
                sb2.setLength(0);
                sb3.setLength(0);
                z = true;
            }
        }
        String string2 = o.L0(sb2.toString()).toString();
        if (string2.length() > 0) {
            linkedHashMap.put(string2, o.L0(sb3.toString()).toString());
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.api.ManifestBestVariant parseHlsManifest(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.parseHlsManifest(java.lang.String):com.arflix.tv.data.api.ManifestBestVariant");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parseHlsManifest$lambda$0(String str) {
        return o.L0(str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parseHlsManifest$lambda$1(String str) {
        return !o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Integer parseQualityLabel(String label) {
        k kVarB;
        String str;
        if (label == null || o.h0(label) || (kVarB = m.b(YouTubeExtractorRegexes.INSTANCE.getQUALITY_LABEL_REGEX(), label)) == null || (str = (String) kotlin.collections.x.p0(1, kVarB.b())) == null) {
            return null;
        }
        return u.R(str);
    }

    private final x parseResolution(String raw) {
        List listX0 = o.x0(raw, new char[]{'x'}, 6);
        if (listX0.size() != 2) {
            return new x(0, 0);
        }
        Integer numR = u.R((String) listX0.get(0));
        Integer numValueOf = Integer.valueOf(numR != null ? numR.intValue() : 0);
        Integer numR2 = u.R((String) listX0.get(1));
        return new x(numValueOf, Integer.valueOf(numR2 != null ? numR2.intValue() : 0));
    }

    private final WatchConfig parseWatchConfig(String html) {
        List listB;
        List listB2;
        YouTubeExtractorRegexes youTubeExtractorRegexes = YouTubeExtractorRegexes.INSTANCE;
        k kVarB = m.b(youTubeExtractorRegexes.getAPI_KEY_REGEX(), html);
        String str = null;
        String str2 = (kVarB == null || (listB2 = kVarB.b()) == null) ? null : (String) kotlin.collections.x.p0(1, listB2);
        k kVarB2 = m.b(youTubeExtractorRegexes.getVISITOR_DATA_REGEX(), html);
        if (kVarB2 != null && (listB = kVarB2.b()) != null) {
            str = (String) kotlin.collections.x.p0(1, listB);
        }
        return new WatchConfig(str2, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.arflix.tv.data.api.RequestResponse performRequest(java.lang.String r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.lang.String r13) {
        /*
            r9 = this;
            gb.j0 r0 = new gb.j0
            r0.<init>()
            r0.i(r10)
            gb.a0 r10 = r9.buildHeaders(r12)
            gb.z r10 = r10.c()
            r0.f15724c = r10
            java.util.Locale r10 = java.util.Locale.ROOT
            java.lang.String r10 = r11.toUpperCase(r10)
            int r11 = r10.hashCode()
            r12 = 79599(0x136ef, float:1.11542E-40)
            java.lang.String r1 = ""
            r2 = 0
            if (r11 == r12) goto L52
            r12 = 2461856(0x2590a0, float:3.449795E-39)
            if (r11 == r12) goto L3e
            r12 = 2012838315(0x77f979ab, float:1.0119919E34)
            if (r11 == r12) goto L2f
            goto L5a
        L2f:
            java.lang.String r11 = "DELETE"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L38
            goto L5a
        L38:
            gb.m0 r10 = ib.c.f16271d
            r0.f(r11, r10)
            goto L68
        L3e:
            java.lang.String r11 = "POST"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L47
            goto L5a
        L47:
            if (r13 != 0) goto L4a
            r13 = r1
        L4a:
            gb.m0 r10 = gb.u.d(r13, r2)
            r0.f(r11, r10)
            goto L68
        L52:
            java.lang.String r11 = "PUT"
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L5e
        L5a:
            r0.d()
            goto L68
        L5e:
            if (r13 != 0) goto L61
            r13 = r1
        L61:
            gb.m0 r10 = gb.u.d(r13, r2)
            r0.f(r11, r10)
        L68:
            gb.h0 r10 = r9.httpClient
            gb.k0 r11 = r0.b()
            lb.i r10 = r10.a(r11)
            gb.p0 r10 = r10.d()
            com.arflix.tv.data.api.RequestResponse r3 = new com.arflix.tv.data.api.RequestResponse     // Catch: java.lang.Throwable -> L8f
            boolean r4 = r10.k()     // Catch: java.lang.Throwable -> L8f
            int r5 = r10.f15783n     // Catch: java.lang.Throwable -> L8f
            java.lang.String r6 = r10.f15782m     // Catch: java.lang.Throwable -> L8f
            gb.k0 r11 = r10.f15780i     // Catch: java.lang.Throwable -> L8f
            gb.b0 r11 = r11.f15730a     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = r11.f15625i     // Catch: java.lang.Throwable -> L8f
            gb.s0 r11 = r10.f15786q     // Catch: java.lang.Throwable -> L8f
            if (r11 == 0) goto L92
            java.lang.String r2 = r11.x()     // Catch: java.lang.Throwable -> L8f
            goto L92
        L8f:
            r0 = move-exception
            r11 = r0
            goto L9e
        L92:
            if (r2 != 0) goto L96
            r8 = r1
            goto L97
        L96:
            r8 = r2
        L97:
            r3.<init>(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L8f
            r10.close()
            return r3
        L9e:
            throw r11     // Catch: java.lang.Throwable -> L9f
        L9f:
            r0 = move-exception
            r12 = r0
            xc.d.L(r10, r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.performRequest(java.lang.String, java.lang.String, java.util.Map, java.lang.String):com.arflix.tv.data.api.RequestResponse");
    }

    public static /* synthetic */ RequestResponse performRequest$default(InAppYouTubeExtractor inAppYouTubeExtractor, String str, String str2, Map map, String str3, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return inAppYouTubeExtractor.performRequest(str, str2, map, str3);
    }

    private final StreamCandidate pickBestForClient(List<StreamCandidate> items, String clientKey) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : items) {
            if (kotlin.jvm.internal.p.a(((StreamCandidate) obj).getClient(), clientKey)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            items = arrayList;
        }
        return (StreamCandidate) kotlin.collections.x.o0(sortCandidates(items));
    }

    private final List<StreamCandidate> sortCandidates(List<StreamCandidate> items) {
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.api.InAppYouTubeExtractor$sortCandidates$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Double.valueOf(((StreamCandidate) t10).getScore()), Double.valueOf(((StreamCandidate) t2).getScore()));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.data.api.InAppYouTubeExtractor$sortCandidates$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                return r0.e(((StreamCandidate) t2).getHasN() ? 1 : num, ((StreamCandidate) t10).getHasN() ? 1 : 0);
            }
        };
        final Comparator comparator3 = new Comparator() { // from class: com.arflix.tv.data.api.InAppYouTubeExtractor$sortCandidates$$inlined$thenBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator2.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Integer.valueOf(this.containerPreference(((StreamCandidate) t2).getExt())), Integer.valueOf(this.containerPreference(((StreamCandidate) t10).getExt())));
            }
        };
        return kotlin.collections.x.W0(items, new Comparator() { // from class: com.arflix.tv.data.api.InAppYouTubeExtractor$sortCandidates$$inlined$thenBy$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator3.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Integer.valueOf(((StreamCandidate) t2).getPriority()), Integer.valueOf(((StreamCandidate) t10).getPriority()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double videoScore(int height, int fps, double bitrate) {
        return (((double) fps) * 1000000.0d) + (((double) height) * 1.0E9d) + bitrate;
    }

    public final void evictCache(String videoId) {
        this.urlCache.remove(videoId);
    }

    public final Object extractPlaybackSource(String str, d7.d<? super TrailerPlaybackSource> dVar) {
        return m0.y(x0.f19655d, new AnonymousClass2(str, this, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshWatchConfigFromPage(java.lang.String r11, d7.d<? super x6.t0> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            java.lang.String r0 = "https://www.youtube.com/watch?v="
            boolean r1 = r12 instanceof com.arflix.tv.data.api.InAppYouTubeExtractor.C06531
            if (r1 == 0) goto L15
            r1 = r12
            com.arflix.tv.data.api.InAppYouTubeExtractor$refreshWatchConfigFromPage$1 r1 = (com.arflix.tv.data.api.InAppYouTubeExtractor.C06531) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.arflix.tv.data.api.InAppYouTubeExtractor$refreshWatchConfigFromPage$1 r1 = new com.arflix.tv.data.api.InAppYouTubeExtractor$refreshWatchConfigFromPage$1
            r1.<init>(r12)
        L1a:
            java.lang.Object r12 = r1.result
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r11 = r1.L$1
            ua.a r11 = (ua.a) r11
            java.lang.Object r1 = r1.L$0
            java.lang.String r1 = (java.lang.String) r1
            k2.c.G(r12)
            r12 = r11
            r11 = r1
            goto L50
        L31:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L39:
            k2.c.G(r12)
            ua.a r12 = r10.watchConfigMutex
            r1.L$0 = r11
            r1.L$1 = r12
            r2 = 0
            r1.I$0 = r2
            r1.label = r3
            java.lang.Object r1 = r12.c(r1)
            e7.a r2 = e7.a.f15033i
            if (r1 != r2) goto L50
            return r2
        L50:
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L97
            r2.append(r11)     // Catch: java.lang.Throwable -> L97
            java.lang.String r11 = "&hl=en"
            r2.append(r11)     // Catch: java.lang.Throwable -> L97
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L97
            java.lang.String r5 = "GET"
            java.util.Map r6 = com.arflix.tv.data.api.YouTubeExtractorKt.access$getDEFAULT_HEADERS$p()     // Catch: java.lang.Throwable -> L97
            r8 = 8
            r9 = 0
            r7 = 0
            r3 = r10
            com.arflix.tv.data.api.RequestResponse r11 = performRequest$default(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8e
            boolean r0 = r11.getOk()     // Catch: java.lang.Throwable -> L8e
            if (r0 == 0) goto L91
            java.lang.String r11 = r11.getBody()     // Catch: java.lang.Throwable -> L8e
            com.arflix.tv.data.api.WatchConfig r11 = r10.parseWatchConfig(r11)     // Catch: java.lang.Throwable -> L8e
            java.lang.String r0 = r11.getApiKey()     // Catch: java.lang.Throwable -> L8e
            if (r0 == 0) goto L91
            r3.cachedWatchConfig = r11     // Catch: java.lang.Throwable -> L8e
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L8e
            r3.watchConfigFetchedAt = r4     // Catch: java.lang.Throwable -> L8e
            goto L91
        L8e:
            r0 = move-exception
        L8f:
            r11 = r0
            goto L9a
        L91:
            r12.b(r1)
            x6.t0 r11 = x6.t0.f22605a
            return r11
        L97:
            r0 = move-exception
            r3 = r10
            goto L8f
        L9a:
            r12.b(r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.InAppYouTubeExtractor.refreshWatchConfigFromPage(java.lang.String, d7.d):java.lang.Object");
    }
}
