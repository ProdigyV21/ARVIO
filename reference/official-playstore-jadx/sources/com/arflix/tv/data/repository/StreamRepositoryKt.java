package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonManifest;
import com.arflix.tv.data.model.AddonResource;
import com.arflix.tv.data.model.QualityFilterConfig;
import com.arflix.tv.data.model.StreamSource;
import com.google.common.util.concurrent.r0;
import io.ktor.http.ContentDisposition;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.URI;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.PatternSyntaxException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aC\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\n\u001a3\u0010\u0010\u001a\u0004\u0018\u00010\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00000\u00072\u0006\u0010\u0012\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a1\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a/\u0010\u001a\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0017\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001cH\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010\"\u001a\u00020\u00002\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0007H\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001d\u0010$\u001a\u00020\u00002\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0007H\u0000¢\u0006\u0004\b$\u0010#\u001a\u0017\u0010&\u001a\u00020\u00042\u0006\u0010%\u001a\u00020 H\u0000¢\u0006\u0004\b&\u0010'\u001a\u0017\u0010)\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0000H\u0000¢\u0006\u0004\b)\u0010*\u001a\u0019\u0010+\u001a\u0004\u0018\u00010\u00002\u0006\u0010(\u001a\u00020\u0000H\u0000¢\u0006\u0004\b+\u0010*\u001a\u0017\u0010-\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0000H\u0000¢\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0000H\u0000¢\u0006\u0004\b/\u0010.\u001a\u001f\u00101\u001a\u0004\u0018\u00010\u00002\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007H\u0000¢\u0006\u0004\b1\u0010#\u001a\u0019\u00102\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b2\u0010*\u001a/\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00072\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00072\f\u00105\u001a\b\u0012\u0004\u0012\u0002040\u0007¢\u0006\u0004\b6\u00107\"%\u0010?\u001a\b\u0012\u0004\u0012\u00020:09*\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\" \u0010A\u001a\b\u0012\u0004\u0012\u00020\u00000@8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\" \u0010E\u001a\b\u0012\u0004\u0012\u00020\u00000@8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bE\u0010B\u001a\u0004\bF\u0010D\" \u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020H0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010J*É\u0001\u0010T\"W\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0007¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u0000¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0000¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(O\u0012\u0006\u0012\u0004\u0018\u0001`P\u0012\u0004\u0012\u00020Q0K2l\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0007¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u0000¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\u0000¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(O\u0012\u001b\u0012\u0019\u0018\u00010Rj\u0004\u0018\u0001`P¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020Q0K¨\u0006U"}, d2 = {"", "seriesId", "animeQuery", "tmdbEpisodeId", "", "preferNativeAnimeIds", "includeTmdbCandidate", "", "Lcom/arflix/tv/data/repository/EpisodeIdCandidate;", "buildEpisodeIdCandidates", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)Ljava/util/List;", "", "tmdbId", "season", "episode", "supportsTmdbIds", "buildTmdbEpisodeIdCandidate", "(Ljava/lang/Integer;IIZ)Ljava/lang/String;", "imdbId", "buildEpisodeAddonLookupIds", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/util/List;", "buildNativeAnimeRetryCandidates", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "genreIds", "originalLanguage", "nativeAnimeAddonAvailable", "shouldTryNativeAnimeFallback", "(Ljava/util/List;Ljava/lang/String;Z)Z", "Lcom/arflix/tv/data/model/StreamSource;", "stream", "providerScopedStreamIdentity", "(Lcom/arflix/tv/data/model/StreamSource;)Ljava/lang/String;", "Lcom/arflix/tv/data/model/Addon;", "addons", "streamAddonConfigurationFingerprint", "(Ljava/util/List;)Ljava/lang/String;", "streamAddonConfigurationRevision", "addon", "usesSlowAggregatorTimeout", "(Lcom/arflix/tv/data/model/Addon;)Z", "host", "registrableLabel", "(Ljava/lang/String;)Ljava/lang/String;", "gatedHubHostLabel", "url", "isHubCloudPageUrl", "(Ljava/lang/String;)Z", "isEmbeddedLinkLandingHost", "hrefs", "pickHubCloudDirectLink", "redactUrlForLog", "streams", "Lcom/arflix/tv/data/model/QualityFilterConfig;", "qualityFilters", "filterStreamsByQualityRegex", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Landroid/content/Context;", "Lz0/g;", "Lc1/g;", "streamDataStore$delegate", "Lu7/c;", "getStreamDataStore", "(Landroid/content/Context;)Lz0/g;", "streamDataStore", "", "HUB_DOMAIN_LABELS", "Ljava/util/Set;", "getHUB_DOMAIN_LABELS", "()Ljava/util/Set;", "HUB_GATED_DOMAIN_LABELS", "getHUB_GATED_DOMAIN_LABELS", "j$/util/concurrent/ConcurrentHashMap", "Lkotlin/text/m;", "qualityRegexCache", "Lj$/util/concurrent/ConcurrentHashMap;", "Lkotlin/Function4;", "Lx6/y;", ContentDisposition.Parameters.Name, "addonId", "addonName", "Lkotlin/Exception;", "Lx6/t0;", "Ljava/lang/Exception;", "error", "StreamCallback", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StreamRepositoryKt {
    static final /* synthetic */ kotlin.reflect.m<Object>[] $$delegatedProperties = {kotlin.jvm.internal.l0.f19747a.g(new kotlin.jvm.internal.b0(StreamRepositoryKt.class, "streamDataStore", "getStreamDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;"))};
    private static final u7.c streamDataStore$delegate = r0.B("stream_prefs");
    private static final Set<String> HUB_DOMAIN_LABELS = kotlin.collections.r.p0(new String[]{"hubcloud", "hubdrive", "hubcdn", "gamerxyt"});
    private static final Set<String> HUB_GATED_DOMAIN_LABELS = kotlin.collections.r.p0(new String[]{"hubcloud", "hubdrive"});
    private static final ConcurrentHashMap<String, kotlin.text.m> qualityRegexCache = new ConcurrentHashMap<>();

    public static final List<String> buildEpisodeAddonLookupIds(String str, Integer num) {
        z6.c cVarN = t7.a.n();
        cVarN.add(str);
        if (num != null) {
            cVarN.add("tmdb:" + num);
        }
        return kotlin.collections.x.g0(t7.a.e(cVarN));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<com.arflix.tv.data.repository.EpisodeIdCandidate> buildEpisodeIdCandidates(java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8, boolean r9) {
        /*
            r0 = 1
            r1 = 0
            if (r6 == 0) goto L1d
            boolean r2 = kotlin.text.o.h0(r6)
            if (r2 != 0) goto L12
            boolean r2 = r6.equals(r5)
            if (r2 != 0) goto L12
            r2 = r6
            goto L13
        L12:
            r2 = r1
        L13:
            if (r2 == 0) goto L1d
            com.arflix.tv.data.repository.EpisodeIdCandidate r3 = new com.arflix.tv.data.repository.EpisodeIdCandidate
            java.lang.String r4 = "kitsu"
            r3.<init>(r2, r4, r0)
            goto L1e
        L1d:
            r3 = r1
        L1e:
            if (r7 == 0) goto L43
            if (r9 == 0) goto L23
            goto L24
        L23:
            r7 = r1
        L24:
            if (r7 == 0) goto L43
            boolean r9 = kotlin.text.o.h0(r7)
            if (r9 != 0) goto L39
            boolean r9 = r7.equals(r5)
            if (r9 != 0) goto L39
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L39
            goto L3a
        L39:
            r7 = r1
        L3a:
            if (r7 == 0) goto L43
            com.arflix.tv.data.repository.EpisodeIdCandidate r1 = new com.arflix.tv.data.repository.EpisodeIdCandidate
            java.lang.String r6 = "tmdb"
            r1.<init>(r7, r6, r0)
        L43:
            com.arflix.tv.data.repository.EpisodeIdCandidate r6 = new com.arflix.tv.data.repository.EpisodeIdCandidate
            java.lang.String r7 = "imdb"
            r9 = 0
            r6.<init>(r5, r7, r9)
            r5 = 2
            r7 = 3
            if (r8 == 0) goto L5c
            com.arflix.tv.data.repository.EpisodeIdCandidate[] r7 = new com.arflix.tv.data.repository.EpisodeIdCandidate[r7]
            r7[r9] = r3
            r7[r0] = r1
            r7[r5] = r6
            java.util.ArrayList r5 = kotlin.collections.r.X(r7)
            goto L68
        L5c:
            com.arflix.tv.data.repository.EpisodeIdCandidate[] r7 = new com.arflix.tv.data.repository.EpisodeIdCandidate[r7]
            r7[r9] = r6
            r7[r0] = r3
            r7[r5] = r1
            java.util.ArrayList r5 = kotlin.collections.r.X(r7)
        L68:
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r5 = r5.iterator()
        L76:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto Laa
            java.lang.Object r8 = r5.next()
            r9 = r8
            com.arflix.tv.data.repository.EpisodeIdCandidate r9 = (com.arflix.tv.data.repository.EpisodeIdCandidate) r9
            java.lang.String r0 = r9.getContentId()
            boolean r9 = r9.getPreferAnimePath()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "|"
            r1.append(r0)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            boolean r9 = r6.add(r9)
            if (r9 == 0) goto L76
            r7.add(r8)
            goto L76
        Laa:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepositoryKt.buildEpisodeIdCandidates(java.lang.String, java.lang.String, java.lang.String, boolean, boolean):java.util.List");
    }

    public static /* synthetic */ List buildEpisodeIdCandidates$default(String str, String str2, String str3, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            z5 = true;
        }
        return buildEpisodeIdCandidates(str, str2, str3, z, z5);
    }

    public static final List<EpisodeIdCandidate> buildNativeAnimeRetryCandidates(String str, String str2, String str3) {
        List<EpisodeIdCandidate> listBuildEpisodeIdCandidates = buildEpisodeIdCandidates(str, str2, str3, true, true);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listBuildEpisodeIdCandidates) {
            if (!kotlin.jvm.internal.p.a(((EpisodeIdCandidate) obj).getLabel(), "imdb")) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final String buildTmdbEpisodeIdCandidate(Integer num, int i10, int i11, boolean z) {
        if (num == null || !z) {
            return null;
        }
        return "tmdb:" + num + ":" + i10 + ":" + i11;
    }

    public static final List<StreamSource> filterStreamsByQualityRegex(List<StreamSource> list, List<QualityFilterConfig> list2) {
        kotlin.text.m orPutFilterRegex;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            QualityFilterConfig qualityFilterConfig = (QualityFilterConfig) obj;
            if (qualityFilterConfig.getEnabled() && !kotlin.text.o.h0(qualityFilterConfig.getRegexPattern())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    orPutFilterRegex = StreamRepoRegexes.INSTANCE.getOrPutFilterRegex(((QualityFilterConfig) it.next()).getRegexPattern());
                } catch (PatternSyntaxException unused) {
                    orPutFilterRegex = null;
                }
                if (orPutFilterRegex != null) {
                    arrayList2.add(orPutFilterRegex);
                }
            }
            if (!arrayList2.isEmpty()) {
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : list) {
                    StreamSource streamSource = (StreamSource) obj2;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(streamSource.getQuality());
                    if (!kotlin.text.o.h0(streamSource.getSource())) {
                        sb2.append(' ');
                        sb2.append(streamSource.getSource());
                    }
                    String string = sb2.toString();
                    if (!arrayList2.isEmpty()) {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            if (((kotlin.text.m) it2.next()).a(string)) {
                                break;
                            }
                        }
                    }
                    arrayList3.add(obj2);
                }
                return arrayList3;
            }
        }
        return list;
    }

    public static final String gatedHubHostLabel(String str) {
        String strRegistrableLabel = registrableLabel(kotlin.text.o.r0(str.toLowerCase(Locale.US), "www."));
        if (HUB_GATED_DOMAIN_LABELS.contains(strRegistrableLabel)) {
            return strRegistrableLabel;
        }
        return null;
    }

    public static final Set<String> getHUB_DOMAIN_LABELS() {
        return HUB_DOMAIN_LABELS;
    }

    public static final Set<String> getHUB_GATED_DOMAIN_LABELS() {
        return HUB_GATED_DOMAIN_LABELS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final z0.g<c1.g> getStreamDataStore(Context context) {
        return (z0.g) streamDataStore$delegate.getValue(context, $$delegatedProperties[0]);
    }

    public static final boolean isEmbeddedLinkLandingHost(String str) {
        Object c0Var;
        try {
            c0Var = new URI(str);
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        URI uri = (URI) c0Var;
        if (uri == null) {
            return false;
        }
        String host = uri.getHost();
        String strR0 = host != null ? kotlin.text.o.r0(host.toLowerCase(Locale.US), "www.") : null;
        if (strR0 == null) {
            strR0 = "";
        }
        return HUB_DOMAIN_LABELS.contains(registrableLabel(strR0));
    }

    public static final boolean isHubCloudPageUrl(String str) {
        Object c0Var;
        try {
            c0Var = new URI(kotlin.text.o.L0(kotlin.text.o.G0('|', str, str)).toString());
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        URI uri = (URI) c0Var;
        if (uri != null) {
            String host = uri.getHost();
            String strR0 = host != null ? kotlin.text.o.r0(host.toLowerCase(Locale.US), "www.") : null;
            if (strR0 == null) {
                strR0 = "";
            }
            if (gatedHubHostLabel(strR0) != null) {
                String path = uri.getPath();
                String lowerCase = path != null ? path.toLowerCase(Locale.US) : null;
                String str2 = lowerCase != null ? lowerCase : "";
                return kotlin.text.o.T(str2, "/drive/", false) || kotlin.text.o.T(str2, "/video/", false) || kotlin.text.o.T(str2, "/file/", false) || kotlin.text.o.T(str2, "/s/", false);
            }
        }
        return false;
    }

    public static final String pickHubCloudDirectLink(List<String> list) {
        String strPickHubCloudDirectLink$firstMatching = pickHubCloudDirectLink$firstMatching(list, new o(25));
        return (strPickHubCloudDirectLink$firstMatching == null && (strPickHubCloudDirectLink$firstMatching = pickHubCloudDirectLink$firstMatching(list, new o(26))) == null && (strPickHubCloudDirectLink$firstMatching = pickHubCloudDirectLink$firstMatching(list, new o(27))) == null) ? pickHubCloudDirectLink$firstMatching(list, new o(28)) : strPickHubCloudDirectLink$firstMatching;
    }

    private static final String pickHubCloudDirectLink$firstMatching(List<String> list, r7.l<? super String, Boolean> lVar) {
        Object next;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Boolean) lVar.invoke(next)).booleanValue()) {
                break;
            }
        }
        return (String) next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pickHubCloudDirectLink$lambda$0(String str) {
        return kotlin.text.o.T(str, "r2.cloudflarestorage.com", true) || kotlin.text.o.T(str, "r2.dev", true) || kotlin.text.o.T(str, "response-content-disposition=attachment", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pickHubCloudDirectLink$lambda$1(String str) {
        if (kotlin.text.o.T(str, "fsl.", true)) {
            return kotlin.text.o.T(str, "token=", true) || kotlin.text.o.T(str, ".mkv", true) || kotlin.text.o.T(str, ".mp4", true);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pickHubCloudDirectLink$lambda$2(String str) {
        return kotlin.text.o.T(str, "pixel.", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pickHubCloudDirectLink$lambda$3(String str) {
        return kotlin.text.o.T(str, "workers.dev", true);
    }

    public static final String providerScopedStreamIdentity(StreamSource streamSource) {
        String string = kotlin.text.o.L0(streamSource.getAddonId()).toString();
        String url = streamSource.getUrl();
        String string2 = url != null ? kotlin.text.o.L0(url).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        return kotlin.collections.x.u0(t7.a.E(string, string2, kotlin.text.o.L0(streamSource.getSource()).toString()), "|", null, null, null, 62);
    }

    public static final String redactUrlForLog(String str) {
        String string = str != null ? kotlin.text.o.L0(str).toString() : null;
        if (string == null) {
            string = "";
        }
        if (kotlin.text.o.h0(string)) {
            return "";
        }
        int iE0 = kotlin.text.o.e0(string, '?', 0, false, 6);
        return iE0 >= 0 ? string.substring(0, iE0).concat("?…") : string;
    }

    public static final String registrableLabel(String str) {
        List listX0 = kotlin.text.o.x0(str, new char[]{'.'}, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listX0) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() >= 2) {
            return (String) a0.c.h(2, arrayList);
        }
        String str2 = (String) kotlin.collections.x.y0(arrayList);
        return str2 == null ? "" : str2;
    }

    public static final boolean shouldTryNativeAnimeFallback(List<Integer> list, String str, boolean z) {
        String string;
        if (!z || !list.contains(16)) {
            return false;
        }
        String lowerCase = (str == null || (string = kotlin.text.o.L0(str).toString()) == null) ? null : string.toLowerCase(Locale.US);
        return lowerCase == null || kotlin.text.o.h0(lowerCase) || kotlin.jvm.internal.p.a(lowerCase, "ja");
    }

    public static final String streamAddonConfigurationFingerprint(List<Addon> list) {
        return kotlin.collections.x.u0(list, "\n", null, null, new o(24), 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence streamAddonConfigurationFingerprint$lambda$0(Addon addon) {
        AddonManifest manifest = addon.getManifest();
        List<AddonResource> resources = manifest != null ? manifest.getResources() : null;
        List<String> types = manifest != null ? manifest.getTypes() : null;
        List<String> idPrefixes = manifest != null ? manifest.getIdPrefixes() : null;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        String strU0 = kotlin.collections.x.u0(resources == null ? zVar : resources, ";", null, null, new o(29), 30);
        String id = addon.getId();
        String strValueOf = String.valueOf(addon.isInstalled());
        String strValueOf2 = String.valueOf(addon.isEnabled());
        String strName = addon.getRuntimeKind().name();
        String strName2 = addon.getType().name();
        String strName3 = addon.getInstallSource().name();
        String url = addon.getUrl();
        String str = url == null ? "" : url;
        String transportUrl = addon.getTransportUrl();
        String str2 = transportUrl == null ? "" : transportUrl;
        String id2 = manifest != null ? manifest.getId() : null;
        String str3 = id2 == null ? "" : id2;
        String version = manifest != null ? manifest.getVersion() : null;
        return kotlin.collections.x.u0(t7.a.E(id, strValueOf, strValueOf2, strName, strName2, strName3, str, str2, str3, version == null ? "" : version, kotlin.collections.x.u0(types == null ? zVar : types, ",", null, null, null, 62), kotlin.collections.x.u0(idPrefixes == null ? zVar : idPrefixes, ",", null, null, null, 62), strU0), "|", null, null, null, 62);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence streamAddonConfigurationFingerprint$lambda$0$0(AddonResource addonResource) {
        List<String> types = addonResource.getTypes();
        String name = addonResource.getName();
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        String strU0 = kotlin.collections.x.u0(types == null ? zVar : types, ",", null, null, null, 62);
        List<String> idPrefixes = addonResource.getIdPrefixes();
        return kotlin.collections.x.u0(t7.a.E(name, strU0, kotlin.collections.x.u0(idPrefixes == null ? zVar : idPrefixes, ",", null, null, null, 62)), ":", null, null, null, 62);
    }

    public static final String streamAddonConfigurationRevision(List<Addon> list) {
        return kotlin.collections.x.u0(kotlin.collections.r.l0(12, MessageDigest.getInstance("SHA-256").digest(streamAddonConfigurationFingerprint(list).getBytes(kotlin.text.a.f19924a))), "", null, null, new h0(0), 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence streamAddonConfigurationRevision$lambda$0(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    public static final boolean usesSlowAggregatorTimeout(Addon addon) {
        String str;
        String str2;
        String str3;
        String id = addon.getId();
        String name = addon.getName();
        String url = addon.getUrl();
        if (url == null) {
            url = "";
        }
        String transportUrl = addon.getTransportUrl();
        if (transportUrl == null) {
            transportUrl = "";
        }
        AddonManifest manifest = addon.getManifest();
        String id2 = manifest != null ? manifest.getId() : null;
        if (id2 == null) {
            id2 = "";
        }
        AddonManifest manifest2 = addon.getManifest();
        String name2 = manifest2 != null ? manifest2.getName() : null;
        if (name2 == null) {
            String str4 = id2;
            str3 = "";
            str = transportUrl;
            str2 = str4;
        } else {
            str = transportUrl;
            str2 = id2;
            str3 = name2;
        }
        String lowerCase = kotlin.collections.x.u0(t7.a.E(id, name, url, str, str2, str3), " ", null, null, null, 62).toLowerCase(Locale.US);
        return kotlin.text.o.T(lowerCase, "aiostreams", false) || kotlin.text.o.T(lowerCase, "aio-streams", false) || kotlin.text.o.T(lowerCase, "comet", false) || kotlin.text.o.T(lowerCase, "mediafusion", false) || kotlin.text.o.T(lowerCase, "hdhub", false) || kotlin.text.o.T(lowerCase, "pengu", false);
    }
}
