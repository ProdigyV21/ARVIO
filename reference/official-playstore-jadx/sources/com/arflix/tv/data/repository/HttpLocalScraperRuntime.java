package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonManifest;
import com.arflix.tv.data.model.ProxyHeaders;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import com.google.android.gms.cast.MediaError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0010\b\u0007\u0018\u0000 \u008b\u00012\u00020\u0001:\u000e\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001\u008b\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ$\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J8\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJH\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\nH\u0086@¢\u0006\u0004\b \u0010!J\\\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b'\u0010(JL\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b*\u0010+JL\u0010,\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b,\u0010+JL\u0010-\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b-\u0010+JX\u00103\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010/\u001a\u00020.2\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00192\u0006\u00102\u001a\u00020\nH\u0082@¢\u0006\u0004\b3\u00104J:\u00105\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b5\u00106J:\u00107\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b7\u00106JL\u00108\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b8\u0010+JL\u00109\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b9\u0010+J:\u0010:\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b:\u00106JL\u0010;\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b;\u0010+J:\u0010<\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\b<\u00106J6\u0010@\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010=\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\nH\u0082@¢\u0006\u0004\b@\u0010AJ\u0012\u0010B\u001a\u0004\u0018\u00010\nH\u0082@¢\u0006\u0004\bB\u0010CJJ\u0010G\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010E\u001a\u00020D2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010F\u001a\u00020\nH\u0082@¢\u0006\u0004\bG\u0010HJJ\u0010O\u001a\b\u0012\u0004\u0012\u00020N0\u00192\u0006\u0010I\u001a\u00020\n2\u0006\u0010E\u001a\u00020D2\u0006\u0010J\u001a\u00020\n2\u0006\u0010K\u001a\u00020\n2\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0LH\u0082@¢\u0006\u0004\bO\u0010PJ2\u0010Q\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0004\bQ\u0010RJ\"\u0010S\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0082@¢\u0006\u0004\bS\u0010\u000fJ\u001a\u0010U\u001a\u0004\u0018\u00010\"2\u0006\u0010T\u001a\u00020\nH\u0082@¢\u0006\u0004\bU\u0010VJ.\u0010W\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0014\b\u0002\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0LH\u0082@¢\u0006\u0004\bW\u0010XJ0\u0010Y\u001a\u0004\u0018\u00010N2\u0006\u0010\u000b\u001a\u00020\n2\u0014\b\u0002\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0LH\u0082@¢\u0006\u0004\bY\u0010XJ0\u0010[\u001a\u0004\u0018\u00010Z2\u0006\u0010\u000b\u001a\u00020\n2\u0014\b\u0002\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0LH\u0082@¢\u0006\u0004\b[\u0010XJ.\u0010\\\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0LH\u0082@¢\u0006\u0004\b\\\u0010XJ\"\u0010^\u001a\u0004\u0018\u00010N2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010]\u001a\u00020\nH\u0082@¢\u0006\u0004\b^\u0010\u000fJ\u0017\u0010_\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b_\u0010`J\u0019\u0010a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\ba\u0010`J\u0017\u0010c\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0002¢\u0006\u0004\bc\u0010`J\u0013\u0010e\u001a\u00020\u0012*\u00020dH\u0002¢\u0006\u0004\be\u0010fJ\u0013\u0010g\u001a\u00020\u0012*\u00020dH\u0002¢\u0006\u0004\bg\u0010fJ\u001b\u0010h\u001a\u00020\u001a*\u00020)2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\bh\u0010iJ\u0017\u0010j\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0002¢\u0006\u0004\bj\u0010`J\u0017\u0010k\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0002¢\u0006\u0004\bk\u0010`J\u0017\u0010l\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0002¢\u0006\u0004\bl\u0010`J\u0013\u0010m\u001a\u00020\n*\u00020\nH\u0002¢\u0006\u0004\bm\u0010`J\u001d\u0010o\u001a\u0004\u0018\u00010\n*\u00020N2\u0006\u0010n\u001a\u00020\nH\u0002¢\u0006\u0004\bo\u0010pJ\u001d\u0010q\u001a\u0004\u0018\u00010N*\u00020N2\u0006\u0010n\u001a\u00020\nH\u0002¢\u0006\u0004\bq\u0010rJ\u001d\u0010t\u001a\u0004\u0018\u00010s*\u00020N2\u0006\u0010n\u001a\u00020\nH\u0002¢\u0006\u0004\bt\u0010uJ\u0015\u0010v\u001a\u0004\u0018\u00010N*\u00020ZH\u0002¢\u0006\u0004\bv\u0010wJ\u0015\u0010x\u001a\u0004\u0018\u00010s*\u00020ZH\u0002¢\u0006\u0004\bx\u0010yJ\u0015\u0010z\u001a\u0004\u0018\u00010\n*\u00020ZH\u0002¢\u0006\u0004\bz\u0010{R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010|R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010}R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010~R\u0017\u0010\u0080\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R$\u0010\u0083\u0001\u001a\u000f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\"0\u0082\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R&\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0082\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0084\u0001R \u0010\u008a\u0001\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0086\u0001\u0010\u0087\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001¨\u0006\u0092\u0001"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "<init>", "(Landroid/content/Context;Lgb/h0;Lcom/arflix/tv/data/api/TmdbApi;)V", "", "url", "customName", "Lcom/arflix/tv/data/repository/HttpLocalScraperInstallCandidate;", "fetchInstallCandidate", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/Addon;", "addon", "", "canHandle", "(Lcom/arflix/tv/data/model/Addon;)Z", "imdbId", LinkHeader.Parameters.Title, "", "year", "", "Lcom/arflix/tv/data/model/StreamSource;", "resolveMovieStreams", "(Lcom/arflix/tv/data/model/Addon;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "season", "episode", "tmdbId", "resolveEpisodeStreams", "(Lcom/arflix/tv/data/model/Addon;Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperManifest;", "manifest", "mediaType", "fallbackTitle", "fallbackYear", "resolveHttpStreams", "(Lcom/arflix/tv/data/model/Addon;Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperManifest;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;", "resolveVidEasy", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "resolveFMovies", "resolveBrazucaPlay", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperTmdbDetails;", "details", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$VideasyServer;", "servers", "providerName", "resolveVideasyServers", "(ILcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperTmdbDetails;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "resolveVidMody", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "resolveVidLink", "resolveRgShows", "resolvePlayImdb", "resolveDooFlix", "resolveNetMirror", "resolveVidSrc", "provider", "sourceUrl", "referer", "decryptCloudnestraStreams", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "netMirrorCookie", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$NetMirrorPlatform;", "platform", "cookies", "fetchNetMirrorPlatform", "(Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$NetMirrorPlatform;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", TtmlNode.RUBY_BASE, "contentId", "seasonId", "", "headers", "Lcom/google/gson/JsonObject;", "fetchNetMirrorEpisodes", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$NetMirrorPlatform;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "fetchTmdbDetails", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "resolveTmdbId", "manifestUrl", "fetchManifest", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getText", "(Ljava/lang/String;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "getJson", "Lcom/google/gson/JsonElement;", "getJsonElement", "resolveRedirectUrl", TtmlNode.TAG_BODY, "postJson", "manifestUrlFor", "(Ljava/lang/String;)Ljava/lang/String;", "githubManifestUrlFor", "value", "shortHash", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperEntry;", "isHttpOnlyEnabled", "(Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperEntry;)Z", "isSupportedHttpLocalScraper", "toStreamSource", "(Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;Lcom/arflix/tv/data/model/Addon;)Lcom/arflix/tv/data/model/StreamSource;", "normalizeQuality", "qualityFromText", "sanitizeProviderLabel", "urlEncode", ContentDisposition.Parameters.Name, "string", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "getObject", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "Lcom/google/gson/JsonArray;", "getArray", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonArray;", "asJsonObjectOrNull", "(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonObject;", "asJsonArrayOrNull", "(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonArray;", "asStringOrNull", "(Lcom/google/gson/JsonElement;)Ljava/lang/String;", "Landroid/content/Context;", "Lgb/h0;", "Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "", "manifestCache", "Ljava/util/Map;", "tmdbIdCache", "noRedirectClient$delegate", "Lx6/s;", "getNoRedirectClient", "()Lgb/h0;", "noRedirectClient", "Companion", "HttpScraperManifest", "HttpScraperEntry", "HttpScraperTmdbDetails", "HttpResolvedStream", "VideasyServer", "NetMirrorPlatform", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HttpLocalScraperRuntime {
    private static final String HTTP_LOCAL_MANIFEST_PREFIX = "http.local.";
    private static final String LEGACY_LOCAL_MANIFEST_PREFIX = "nuvio.local.";
    private final Context context;
    private final gb.h0 okHttpClient;
    private final TmdbApi tmdbApi;
    public static final int $stable = 8;
    private static final kotlin.text.m DIV_EP_REGEX = new kotlin.text.m("<div[^>]+class=[\"']ep[^>]*>.*?</div>", kotlin.collections.r.p0(new kotlin.text.n[]{kotlin.text.n.IGNORE_CASE, kotlin.text.n.DOT_MATCHES_ALL}));
    private static final kotlin.text.m DATA_IFRAME_REGEX = new kotlin.text.m("data-iframe=[\"']([^\"']+)[\"']", 0);
    private static final kotlin.text.m IFRAME_PLAYER_REGEX = new kotlin.text.m("iframe\\s+id=[\"']player_iframe[\"']\\s+src=[\"']([^\"']+)[\"']", 0);
    private static final kotlin.text.m T_HASH_T_REGEX = new kotlin.text.m("t_hash_t=([^;]+)");
    private static final kotlin.text.m IFRAME_SRC_REGEX = new kotlin.text.m("<iframe[^>]+src=[\"']([^\"']+)[\"']", 0);
    private static final kotlin.text.m PRORCP_SRC_REGEX = new kotlin.text.m("src:\\s*['\"]([^'\"]+)['\"]", 0);
    private static final kotlin.text.m DIV_MATCH_REGEX = new kotlin.text.m("<div id=\"([^\"]+)\"[^>]*style=[\"']display\\s*:\\s*none;?[\"'][^>]*>([a-zA-Z0-9:/.,{}\\-_=+ ]+)</div>", 0);
    private static final kotlin.text.m IMDB_ID_REGEX = new kotlin.text.m("tt\\d{5,}");
    private static final kotlin.text.m NUVIO_REGEX = new kotlin.text.m("nuvio", 0);
    private static final Set<String> HTTP_FORMATS = kotlin.collections.r.p0(new String[]{"mp4", "mkv", "m3u8", "hls", "dash"});
    private static final Set<String> P2P_FORMATS = kotlin.collections.r.p0(new String[]{"torrent", "magnet", "p2p", "infohash"});
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36";
    private static final Map<String, String> VIDEASY_HEADERS = kotlin.collections.h0.t0(new x6.x("User-Agent", USER_AGENT), new x6.x("Accept", "application/json, text/plain, */*"), new x6.x("Origin", "https://player.videasy.net"), new x6.x("Referer", "https://player.videasy.net/"));
    private static final Map<String, String> VIDLINK_HEADERS = kotlin.collections.h0.t0(new x6.x("User-Agent", USER_AGENT), new x6.x("Accept", "application/json,*/*"), new x6.x("Referer", "https://vidlink.pro/"), new x6.x("Origin", "https://vidlink.pro"));
    private static final Set<String> IMPLEMENTED_HTTP_LOCAL_PROVIDERS = kotlin.collections.r.p0(new String[]{"multivid", "videasy", "vidlink", "vidsrc", "vixsrc", "rgshows", "playimdb", "playimdb_series", "dooflix", "fmovies", "brazucaplay", "netmirror"});
    private final Gson gson = new Gson();
    private final Map<String, HttpScraperManifest> manifestCache = new LinkedHashMap();
    private final Map<String, Integer> tmdbIdCache = new LinkedHashMap();

    /* JADX INFO: renamed from: noRedirectClient$delegate, reason: from kotlin metadata */
    private final x6.s noRedirectClient = new x6.i0(new a0(this, 3));

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperEntry;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "enabled", "", "formats", "", "logo", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getEnabled", "()Z", "getFormats", "()Ljava/util/List;", "getLogo", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HttpScraperEntry {
        private final boolean enabled;
        private final List<String> formats;
        private final String id;
        private final String logo;
        private final String name;

        public HttpScraperEntry() {
            this(null, null, false, null, null, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HttpScraperEntry copy$default(HttpScraperEntry httpScraperEntry, String str, String str2, boolean z, List list, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = httpScraperEntry.id;
            }
            if ((i10 & 2) != 0) {
                str2 = httpScraperEntry.name;
            }
            if ((i10 & 4) != 0) {
                z = httpScraperEntry.enabled;
            }
            if ((i10 & 8) != 0) {
                list = httpScraperEntry.formats;
            }
            if ((i10 & 16) != 0) {
                str3 = httpScraperEntry.logo;
            }
            String str4 = str3;
            boolean z5 = z;
            return httpScraperEntry.copy(str, str2, z5, list, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        public final List<String> component4() {
            return this.formats;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLogo() {
            return this.logo;
        }

        public final HttpScraperEntry copy(String id, String name, boolean enabled, List<String> formats, String logo) {
            return new HttpScraperEntry(id, name, enabled, formats, logo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpScraperEntry)) {
                return false;
            }
            HttpScraperEntry httpScraperEntry = (HttpScraperEntry) other;
            return kotlin.jvm.internal.p.a(this.id, httpScraperEntry.id) && kotlin.jvm.internal.p.a(this.name, httpScraperEntry.name) && this.enabled == httpScraperEntry.enabled && kotlin.jvm.internal.p.a(this.formats, httpScraperEntry.formats) && kotlin.jvm.internal.p.a(this.logo, httpScraperEntry.logo);
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final List<String> getFormats() {
            return this.formats;
        }

        public final String getId() {
            return this.id;
        }

        public final String getLogo() {
            return this.logo;
        }

        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            int iE = androidx.compose.material3.d.e(this.formats, (androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name) + (this.enabled ? 1231 : 1237)) * 31, 31);
            String str = this.logo;
            return iE + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            String str = this.id;
            String str2 = this.name;
            boolean z = this.enabled;
            List<String> list = this.formats;
            String str3 = this.logo;
            StringBuilder sbR = a2.r("HttpScraperEntry(id=", str, ", name=", str2, ", enabled=");
            sbR.append(z);
            sbR.append(", formats=");
            sbR.append(list);
            sbR.append(", logo=");
            return a0.c.p(sbR, str3, ")");
        }

        public HttpScraperEntry(String str, String str2, boolean z, List<String> list, String str3) {
            this.id = str;
            this.name = str2;
            this.enabled = z;
            this.formats = list;
            this.logo = str3;
        }

        public /* synthetic */ HttpScraperEntry(String str, String str2, boolean z, List list, String str3, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? kotlin.collections.z.f19728i : list, (i10 & 16) != 0 ? null : str3);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperManifest;", "", ContentDisposition.Parameters.Name, "", "version", "scrapers", "", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperEntry;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getVersion", "getScrapers", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HttpScraperManifest {
        private final String name;
        private final List<HttpScraperEntry> scrapers;
        private final String version;

        public HttpScraperManifest() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HttpScraperManifest copy$default(HttpScraperManifest httpScraperManifest, String str, String str2, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = httpScraperManifest.name;
            }
            if ((i10 & 2) != 0) {
                str2 = httpScraperManifest.version;
            }
            if ((i10 & 4) != 0) {
                list = httpScraperManifest.scrapers;
            }
            return httpScraperManifest.copy(str, str2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVersion() {
            return this.version;
        }

        public final List<HttpScraperEntry> component3() {
            return this.scrapers;
        }

        public final HttpScraperManifest copy(String name, String version, List<HttpScraperEntry> scrapers) {
            return new HttpScraperManifest(name, version, scrapers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpScraperManifest)) {
                return false;
            }
            HttpScraperManifest httpScraperManifest = (HttpScraperManifest) other;
            return kotlin.jvm.internal.p.a(this.name, httpScraperManifest.name) && kotlin.jvm.internal.p.a(this.version, httpScraperManifest.version) && kotlin.jvm.internal.p.a(this.scrapers, httpScraperManifest.scrapers);
        }

        public final String getName() {
            return this.name;
        }

        public final List<HttpScraperEntry> getScrapers() {
            return this.scrapers;
        }

        public final String getVersion() {
            return this.version;
        }

        public int hashCode() {
            return this.scrapers.hashCode() + androidx.compose.foundation.c.c(this.name.hashCode() * 31, 31, this.version);
        }

        public String toString() {
            String str = this.name;
            String str2 = this.version;
            return a0.c.q(a2.r("HttpScraperManifest(name=", str, ", version=", str2, ", scrapers="), this.scrapers, ")");
        }

        public HttpScraperManifest(String str, String str2, List<HttpScraperEntry> list) {
            this.name = str;
            this.version = str2;
            this.scrapers = list;
        }

        public /* synthetic */ HttpScraperManifest(String str, String str2, List list, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "1.0.0" : str2, (i10 & 4) != 0 ? kotlin.collections.z.f19728i : list);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J?\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpScraperTmdbDetails;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "year", "imdbId", "mediaType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getYear", "getImdbId", "getMediaType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HttpScraperTmdbDetails {
        private final String id;
        private final String imdbId;
        private final String mediaType;
        private final String title;
        private final String year;

        public HttpScraperTmdbDetails(String str, String str2, String str3, String str4, String str5) {
            this.id = str;
            this.title = str2;
            this.year = str3;
            this.imdbId = str4;
            this.mediaType = str5;
        }

        public static /* synthetic */ HttpScraperTmdbDetails copy$default(HttpScraperTmdbDetails httpScraperTmdbDetails, String str, String str2, String str3, String str4, String str5, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = httpScraperTmdbDetails.id;
            }
            if ((i10 & 2) != 0) {
                str2 = httpScraperTmdbDetails.title;
            }
            if ((i10 & 4) != 0) {
                str3 = httpScraperTmdbDetails.year;
            }
            if ((i10 & 8) != 0) {
                str4 = httpScraperTmdbDetails.imdbId;
            }
            if ((i10 & 16) != 0) {
                str5 = httpScraperTmdbDetails.mediaType;
            }
            String str6 = str5;
            String str7 = str3;
            return httpScraperTmdbDetails.copy(str, str2, str7, str4, str6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        public final HttpScraperTmdbDetails copy(String id, String title, String year, String imdbId, String mediaType) {
            return new HttpScraperTmdbDetails(id, title, year, imdbId, mediaType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpScraperTmdbDetails)) {
                return false;
            }
            HttpScraperTmdbDetails httpScraperTmdbDetails = (HttpScraperTmdbDetails) other;
            return kotlin.jvm.internal.p.a(this.id, httpScraperTmdbDetails.id) && kotlin.jvm.internal.p.a(this.title, httpScraperTmdbDetails.title) && kotlin.jvm.internal.p.a(this.year, httpScraperTmdbDetails.year) && kotlin.jvm.internal.p.a(this.imdbId, httpScraperTmdbDetails.imdbId) && kotlin.jvm.internal.p.a(this.mediaType, httpScraperTmdbDetails.mediaType);
        }

        public final String getId() {
            return this.id;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getYear() {
            return this.year;
        }

        public int hashCode() {
            int iC = androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.title);
            String str = this.year;
            int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.imdbId;
            return this.mediaType.hashCode() + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31);
        }

        public String toString() {
            String str = this.id;
            String str2 = this.title;
            String str3 = this.year;
            String str4 = this.imdbId;
            String str5 = this.mediaType;
            StringBuilder sbR = a2.r("HttpScraperTmdbDetails(id=", str, ", title=", str2, ", year=");
            y.a.i(sbR, str3, ", imdbId=", str4, ", mediaType=");
            return a0.c.p(sbR, str5, ")");
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$NetMirrorPlatform;", "", ContentDisposition.Parameters.Name, "", "ott", "search", "post", "episodes", "playlist", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getOtt", "getSearch", "getPost", "getEpisodes", "getPlaylist", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class NetMirrorPlatform {
        private final String episodes;
        private final String name;
        private final String ott;
        private final String playlist;
        private final String post;
        private final String search;

        public NetMirrorPlatform(String str, String str2, String str3, String str4, String str5, String str6) {
            this.name = str;
            this.ott = str2;
            this.search = str3;
            this.post = str4;
            this.episodes = str5;
            this.playlist = str6;
        }

        public static /* synthetic */ NetMirrorPlatform copy$default(NetMirrorPlatform netMirrorPlatform, String str, String str2, String str3, String str4, String str5, String str6, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = netMirrorPlatform.name;
            }
            if ((i10 & 2) != 0) {
                str2 = netMirrorPlatform.ott;
            }
            if ((i10 & 4) != 0) {
                str3 = netMirrorPlatform.search;
            }
            if ((i10 & 8) != 0) {
                str4 = netMirrorPlatform.post;
            }
            if ((i10 & 16) != 0) {
                str5 = netMirrorPlatform.episodes;
            }
            if ((i10 & 32) != 0) {
                str6 = netMirrorPlatform.playlist;
            }
            String str7 = str5;
            String str8 = str6;
            return netMirrorPlatform.copy(str, str2, str3, str4, str7, str8);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getOtt() {
            return this.ott;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSearch() {
            return this.search;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPost() {
            return this.post;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getEpisodes() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPlaylist() {
            return this.playlist;
        }

        public final NetMirrorPlatform copy(String name, String ott, String search, String post, String episodes, String playlist) {
            return new NetMirrorPlatform(name, ott, search, post, episodes, playlist);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NetMirrorPlatform)) {
                return false;
            }
            NetMirrorPlatform netMirrorPlatform = (NetMirrorPlatform) other;
            return kotlin.jvm.internal.p.a(this.name, netMirrorPlatform.name) && kotlin.jvm.internal.p.a(this.ott, netMirrorPlatform.ott) && kotlin.jvm.internal.p.a(this.search, netMirrorPlatform.search) && kotlin.jvm.internal.p.a(this.post, netMirrorPlatform.post) && kotlin.jvm.internal.p.a(this.episodes, netMirrorPlatform.episodes) && kotlin.jvm.internal.p.a(this.playlist, netMirrorPlatform.playlist);
        }

        public final String getEpisodes() {
            return this.episodes;
        }

        public final String getName() {
            return this.name;
        }

        public final String getOtt() {
            return this.ott;
        }

        public final String getPlaylist() {
            return this.playlist;
        }

        public final String getPost() {
            return this.post;
        }

        public final String getSearch() {
            return this.search;
        }

        public int hashCode() {
            return this.playlist.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.name.hashCode() * 31, 31, this.ott), 31, this.search), 31, this.post), 31, this.episodes);
        }

        public String toString() {
            String str = this.name;
            String str2 = this.ott;
            String str3 = this.search;
            String str4 = this.post;
            String str5 = this.episodes;
            String str6 = this.playlist;
            StringBuilder sbR = a2.r("NetMirrorPlatform(name=", str, ", ott=", str2, ", search=");
            y.a.i(sbR, str3, ", post=", str4, ", episodes=");
            return a2.n(sbR, str5, ", playlist=", str6, ")");
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$decryptCloudnestraStreams$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {571, 577, 579}, m = "decryptCloudnestraStreams", v = 2)
    public static final class AnonymousClass1 extends f7.c {
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
            return HttpLocalScraperRuntime.this.decryptCloudnestraStreams(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchInstallCandidate$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/HttpLocalScraperInstallCandidate;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/HttpLocalScraperInstallCandidate;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchInstallCandidate$2", f = "HttpLocalScraperRuntime.kt", l = {65}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super HttpLocalScraperInstallCandidate>, Object> {
        final /* synthetic */ String $customName;
        final /* synthetic */ String $url;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = str;
            this.$customName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return HttpLocalScraperRuntime.this.new AnonymousClass2(this.$url, this.$customName, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x008e  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) {
            /*
                Method dump skipped, instruction units count: 327
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super HttpLocalScraperInstallCandidate> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchManifest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {746}, m = "fetchManifest", v = 2)
    public static final class C07581 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C07581(d7.d<? super C07581> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.fetchManifest(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchNetMirrorEpisodes$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {695}, m = "fetchNetMirrorEpisodes", v = 2)
    public static final class C07591 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C07591(d7.d<? super C07591> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.fetchNetMirrorEpisodes(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchNetMirrorPlatform$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {637, 643, 649, 657}, m = "fetchNetMirrorPlatform", v = 2)
    public static final class C07601 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07601(d7.d<? super C07601> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.fetchNetMirrorPlatform(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchTmdbDetails$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {712}, m = "fetchTmdbDetails", v = 2)
    public static final class C07611 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C07611(d7.d<? super C07611> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.fetchTmdbDetails(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJson$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {772}, m = "getJson", v = 2)
    public static final class C07621 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07621(d7.d<? super C07621> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.getJson(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJsonElement$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {776}, m = "getJsonElement", v = 2)
    public static final class C07631 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07631(d7.d<? super C07631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.getJsonElement(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$getText$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$getText$2", f = "HttpLocalScraperRuntime.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07642 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ HttpLocalScraperRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07642(String str, HttpLocalScraperRuntime httpLocalScraperRuntime, Map<String, String> map, d7.d<? super C07642> dVar) {
            super(2, dVar);
            this.$url = str;
            this.this$0 = httpLocalScraperRuntime;
            this.$headers = map;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C07642(this.$url, this.this$0, this.$headers, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Map<String, String> map = this.$headers;
            z6.f fVar = new z6.f();
            fVar.put("User-Agent", HttpLocalScraperRuntime.USER_AGENT);
            fVar.putAll(map);
            z6.f fVarC = fVar.c();
            gb.j0 j0Var = new gb.j0();
            j0Var.i(this.$url);
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : (z6.g) fVarC.entrySet()) {
                kotlin.collections.x.b0(arrayList, t7.a.E(entry.getKey(), entry.getValue()));
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            j0Var.f15724c = t7.a.H((String[]) Arrays.copyOf(strArr, strArr.length)).c();
            j0Var.d();
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            String str = this.$url;
            try {
                if (p0VarD.k()) {
                    s0 s0Var = p0VarD.f15786q;
                    String strX = s0Var != null ? s0Var.x() : null;
                    if (strX == null) {
                        strX = "";
                    }
                    p0VarD.close();
                    return strX;
                }
                throw new IllegalStateException(("HTTP " + p0VarD.f15783n + " for " + str).toString());
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C07642) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$netMirrorCookie$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {614}, m = "netMirrorCookie", v = 2)
    public static final class C07651 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07651(d7.d<? super C07651> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.netMirrorCookie(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$postJson$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/google/gson/JsonObject;", "<anonymous>", "(Lka/k0;)Lcom/google/gson/JsonObject;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$postJson$2", f = "HttpLocalScraperRuntime.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07662 extends f7.j implements r7.p<ka.k0, d7.d<? super JsonObject>, Object> {
        final /* synthetic */ String $body;
        final /* synthetic */ String $url;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HttpLocalScraperRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07662(String str, String str2, HttpLocalScraperRuntime httpLocalScraperRuntime, d7.d<? super C07662> dVar) {
            super(2, dVar);
            this.$url = str;
            this.$body = str2;
            this.this$0 = httpLocalScraperRuntime;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07662 c07662 = new C07662(this.$url, this.$body, this.this$0, dVar);
            c07662.L$0 = obj;
            return c07662;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            gb.j0 j0Var = new gb.j0();
            j0Var.i(this.$url);
            String str = this.$body;
            Pattern pattern = gb.d0.f15633d;
            gb.d0 d0VarI0 = xc.d.i0("application/json");
            Charset charset = kotlin.text.a.f19924a;
            Object obj2 = null;
            Charset charsetA = d0VarI0.a(null);
            if (charsetA == null) {
                try {
                    d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                } catch (IllegalArgumentException unused) {
                    d0VarI0 = null;
                }
            } else {
                charset = charsetA;
            }
            byte[] bytes = str.getBytes(charset);
            int length = bytes.length;
            ib.c.c(bytes.length, 0, length);
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            HttpLocalScraperRuntime httpLocalScraperRuntime = this.this$0;
            try {
                if (!p0VarD.k()) {
                    p0VarD.close();
                    return null;
                }
                try {
                    Gson gson = httpLocalScraperRuntime.gson;
                    s0 s0Var = p0VarD.f15786q;
                    String strX = s0Var != null ? s0Var.x() : null;
                    if (strX == null) {
                        strX = "";
                    }
                    c0Var = (JsonObject) gson.fromJson(strX, JsonObject.class);
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                if (!(c0Var instanceof x6.c0)) {
                    obj2 = c0Var;
                }
                JsonObject jsonObject = (JsonObject) obj2;
                p0VarD.close();
                return jsonObject;
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super JsonObject> dVar) {
            return ((C07662) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveBrazucaPlay$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {251, 258}, m = "resolveBrazucaPlay", v = 2)
    public static final class C07671 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C07671(d7.d<? super C07671> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveBrazucaPlay(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveDooFlix$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {477, 481}, m = "resolveDooFlix", v = 2)
    public static final class C07681 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07681(d7.d<? super C07681> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveDooFlix(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveEpisodeStreams$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {TsExtractor.TS_STREAM_TYPE_AC3, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 131}, m = "resolveEpisodeStreams", v = 2)
    public static final class C07691 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C07691(d7.d<? super C07691> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveEpisodeStreams(null, null, 0, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveFMovies$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {235, PsExtractor.VIDEO_STREAM_MASK}, m = "resolveFMovies", v = 2)
    public static final class C07701 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C07701(d7.d<? super C07701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveFMovies(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveHttpStreams$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveHttpStreams$2", f = "HttpLocalScraperRuntime.kt", l = {194}, m = "invokeSuspend", v = 2)
    public static final class C07712 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
        final /* synthetic */ Addon $addon;
        final /* synthetic */ Integer $episode;
        final /* synthetic */ String $fallbackTitle;
        final /* synthetic */ Integer $fallbackYear;
        final /* synthetic */ HttpScraperManifest $manifest;
        final /* synthetic */ String $mediaType;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ HttpLocalScraperRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07712(HttpScraperManifest httpScraperManifest, HttpLocalScraperRuntime httpLocalScraperRuntime, String str, int i10, Integer num, Integer num2, String str2, Integer num3, Addon addon, d7.d<? super C07712> dVar) {
            super(2, dVar);
            this.$manifest = httpScraperManifest;
            this.this$0 = httpLocalScraperRuntime;
            this.$mediaType = str;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
            this.$fallbackTitle = str2;
            this.$fallbackYear = num3;
            this.$addon = addon;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07712 c07712 = new C07712(this.$manifest, this.this$0, this.$mediaType, this.$tmdbId, this.$season, this.$episode, this.$fallbackTitle, this.$fallbackYear, this.$addon, dVar);
            c07712.L$0 = obj;
            return c07712;
        }

        /* JADX WARN: Removed duplicated region for block: B:49:0x0145  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 714
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07712.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
            return ((C07712) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveMovieStreams$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {107, 108, 109}, m = "resolveMovieStreams", v = 2)
    public static final class C07721 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C07721(d7.d<? super C07721> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveMovieStreams(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveNetMirror$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {503, 504, 513}, m = "resolveNetMirror", v = 2)
    public static final class C07731 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07731(d7.d<? super C07731> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveNetMirror(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolvePlayImdb$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {MediaError.DetailedErrorCode.DASH_MANIFEST_NO_PERIODS, 425, 440, 453}, m = "resolvePlayImdb", v = 2)
    public static final class C07741 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07741(d7.d<? super C07741> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolvePlayImdb(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveRedirectUrl$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveRedirectUrl$2", f = "HttpLocalScraperRuntime.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07752 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ HttpLocalScraperRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07752(String str, Map<String, String> map, HttpLocalScraperRuntime httpLocalScraperRuntime, d7.d<? super C07752> dVar) {
            super(2, dVar);
            this.$url = str;
            this.$headers = map;
            this.this$0 = httpLocalScraperRuntime;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C07752(this.$url, this.$headers, this.this$0, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0093 A[PHI: r0
          0x0093: PHI (r0v20 java.lang.String) = (r0v19 java.lang.String), (r0v21 java.lang.String) binds: [B:19:0x00a1, B:14:0x0091] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                int r0 = r6.label
                if (r0 != 0) goto Lae
                k2.c.G(r7)
                gb.j0 r7 = new gb.j0
                r7.<init>()
                java.lang.String r0 = r6.$url
                r7.i(r0)
                java.util.Map<java.lang.String, java.lang.String> r0 = r6.$headers
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L20:
                boolean r2 = r0.hasNext()
                r3 = 0
                if (r2 == 0) goto L45
                java.lang.Object r2 = r0.next()
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                r4 = 2
                java.lang.String[] r4 = new java.lang.String[r4]
                java.lang.Object r5 = r2.getKey()
                r4[r3] = r5
                r3 = 1
                java.lang.Object r2 = r2.getValue()
                r4[r3] = r2
                java.util.List r2 = t7.a.E(r4)
                kotlin.collections.x.b0(r1, r2)
                goto L20
            L45:
                java.lang.String[] r0 = new java.lang.String[r3]
                java.lang.Object[] r0 = r1.toArray(r0)
                java.lang.String[] r0 = (java.lang.String[]) r0
                int r1 = r0.length
                java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r1)
                java.lang.String[] r0 = (java.lang.String[]) r0
                gb.a0 r0 = t7.a.H(r0)
                gb.z r0 = r0.c()
                r7.f15724c = r0
                r7.d()
                gb.k0 r7 = r7.b()
                com.arflix.tv.data.repository.HttpLocalScraperRuntime r0 = r6.this$0
                gb.h0 r0 = com.arflix.tv.data.repository.HttpLocalScraperRuntime.access$getNoRedirectClient(r0)
                lb.i r7 = r0.a(r7)
                gb.p0 r7 = r7.d()
                java.lang.String r0 = r6.$url
                java.lang.String r1 = "Location"
                gb.a0 r2 = r7.f15785p     // Catch: java.lang.Throwable -> L95
                java.lang.String r1 = r2.a(r1)     // Catch: java.lang.Throwable -> L95
                r2 = 0
                if (r1 != 0) goto L81
                r1 = r2
            L81:
                if (r1 == 0) goto L97
                java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> L95
                java.net.URL r4 = new java.net.URL     // Catch: java.lang.Throwable -> L95
                r4.<init>(r0)     // Catch: java.lang.Throwable -> L95
                r3.<init>(r4, r1)     // Catch: java.lang.Throwable -> L95
                java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L95
                if (r0 == 0) goto L97
            L93:
                r2 = r0
                goto La4
            L95:
                r0 = move-exception
                goto La8
            L97:
                gb.k0 r0 = r7.f15780i     // Catch: java.lang.Throwable -> L95
                gb.b0 r0 = r0.f15730a     // Catch: java.lang.Throwable -> L95
                java.lang.String r0 = r0.f15625i     // Catch: java.lang.Throwable -> L95
                boolean r1 = r7.k()     // Catch: java.lang.Throwable -> L95
                if (r1 == 0) goto La4
                goto L93
            La4:
                r7.close()
                return r2
            La8:
                throw r0     // Catch: java.lang.Throwable -> La9
            La9:
                r1 = move-exception
                xc.d.L(r7, r0)
                throw r1
            Lae:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07752.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C07752) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveRgShows$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {380, 393}, m = "resolveRgShows", v = 2)
    public static final class C07761 extends f7.c {
        int I$0;
        int I$1;
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

        public C07761(d7.d<? super C07761> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveRgShows(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveTmdbId$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {734}, m = "resolveTmdbId", v = 2)
    public static final class C07771 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C07771(d7.d<? super C07771> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveTmdbId(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVidEasy$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {211, 224}, m = "resolveVidEasy", v = 2)
    public static final class C07781 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C07781(d7.d<? super C07781> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveVidEasy(0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVidLink$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {351, 359}, m = "resolveVidLink", v = 2)
    public static final class C07791 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C07791(d7.d<? super C07791> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveVidLink(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVidMody$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 330}, m = "resolveVidMody", v = 2)
    public static final class C07801 extends f7.c {
        int I$0;
        int I$1;
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

        public C07801(d7.d<? super C07801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveVidMody(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVidSrc$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime", f = "HttpLocalScraperRuntime.kt", l = {525, 532, 538, 544, 546}, m = "resolveVidSrc", v = 2)
    public static final class C07811 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07811(d7.d<? super C07811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpLocalScraperRuntime.this.resolveVidSrc(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVideasyServers$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVideasyServers$2", f = "HttpLocalScraperRuntime.kt", l = {305}, m = "invokeSuspend", v = 2)
    public static final class C07822 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HttpResolvedStream>>, Object> {
        final /* synthetic */ HttpScraperTmdbDetails $details;
        final /* synthetic */ Integer $episode;
        final /* synthetic */ String $mediaType;
        final /* synthetic */ String $providerName;
        final /* synthetic */ Integer $season;
        final /* synthetic */ List<VideasyServer> $servers;
        final /* synthetic */ int $tmdbId;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HttpLocalScraperRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07822(List<VideasyServer> list, String str, HttpLocalScraperRuntime httpLocalScraperRuntime, HttpScraperTmdbDetails httpScraperTmdbDetails, int i10, Integer num, Integer num2, String str2, d7.d<? super C07822> dVar) {
            super(2, dVar);
            this.$servers = list;
            this.$mediaType = str;
            this.this$0 = httpLocalScraperRuntime;
            this.$details = httpScraperTmdbDetails;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
            this.$providerName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07822 c07822 = new C07822(this.$servers, this.$mediaType, this.this$0, this.$details, this.$tmdbId, this.$season, this.$episode, this.$providerName, dVar);
            c07822.L$0 = obj;
            return c07822;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<VideasyServer> list = this.$servers;
                String str = this.$mediaType;
                HttpLocalScraperRuntime httpLocalScraperRuntime = this.this$0;
                HttpScraperTmdbDetails httpScraperTmdbDetails = this.$details;
                int i11 = this.$tmdbId;
                Integer num = this.$season;
                Integer num2 = this.$episode;
                String str2 = this.$providerName;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(2, x0.f19655d, k0Var, new HttpLocalScraperRuntime$resolveVideasyServers$2$1$1(str, (VideasyServer) it.next(), httpLocalScraperRuntime, httpScraperTmdbDetails, i11, num, num2, str2, null)));
                }
                this.L$0 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
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
            return kotlin.collections.s.V((Iterable) obj);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<HttpResolvedStream>> dVar) {
            return ((C07822) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public HttpLocalScraperRuntime(@ApplicationContext Context context, gb.h0 h0Var, TmdbApi tmdbApi) {
        this.context = context;
        this.okHttpClient = h0Var;
        this.tmdbApi = tmdbApi;
    }

    private final JsonArray asJsonArrayOrNull(JsonElement jsonElement) {
        if (jsonElement.isJsonArray()) {
            return jsonElement.getAsJsonArray();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonObject asJsonObjectOrNull(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        }
        return null;
    }

    private final String asStringOrNull(JsonElement jsonElement) {
        Object c0Var;
        try {
            c0Var = jsonElement.isJsonNull() ? null : jsonElement.getAsString();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        String str = (String) c0Var;
        if (str == null || kotlin.text.o.h0(str)) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x014c, code lost:
    
        if (r2 == r11) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object decryptCloudnestraStreams(java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r23) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.decryptCloudnestraStreams(java.lang.String, java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchManifest(java.lang.String r8, d7.d<? super com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperManifest> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07581
            if (r0 == 0) goto L14
            r0 = r9
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchManifest$1 r0 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchManifest$1 r0 = new com.arflix.tv.data.repository.HttpLocalScraperRuntime$fetchManifest$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            e7.a r0 = e7.a.f15033i
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L41
            if (r1 != r2) goto L39
            java.lang.Object r8 = r4.L$1
            com.arflix.tv.data.repository.HttpLocalScraperRuntime r8 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime) r8
            java.lang.Object r0 = r4.L$0
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L34
            r2 = r1
            r1 = r7
            goto L6a
        L34:
            r0 = move-exception
            r8 = r0
            r2 = r1
            r1 = r7
            goto L7e
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L41:
            k2.c.G(r9)
            java.util.Map<java.lang.String, com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest> r9 = r7.manifestCache
            monitor-enter(r9)
            java.util.Map<java.lang.String, com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest> r1 = r7.manifestCache     // Catch: java.lang.Throwable -> Lb5
            java.lang.Object r1 = r1.get(r8)     // Catch: java.lang.Throwable -> Lb5
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest r1 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperManifest) r1     // Catch: java.lang.Throwable -> Lb5
            if (r1 == 0) goto L53
            monitor-exit(r9)
            return r1
        L53:
            monitor-exit(r9)
            r4.L$0 = r8     // Catch: java.lang.Throwable -> L7a
            r4.L$1 = r7     // Catch: java.lang.Throwable -> L7a
            r9 = 0
            r4.I$0 = r9     // Catch: java.lang.Throwable -> L7a
            r4.label = r2     // Catch: java.lang.Throwable -> L7a
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r7
            r2 = r8
            java.lang.Object r9 = getText$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L77
            if (r9 != r0) goto L69
            return r0
        L69:
            r8 = r1
        L6a:
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Throwable -> L77
            com.google.gson.Gson r8 = r8.gson     // Catch: java.lang.Throwable -> L77
            java.lang.Class<com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest> r0 = com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperManifest.class
            java.lang.Object r8 = r8.fromJson(r9, r0)     // Catch: java.lang.Throwable -> L77
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest r8 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperManifest) r8     // Catch: java.lang.Throwable -> L77
            goto L84
        L77:
            r0 = move-exception
        L78:
            r8 = r0
            goto L7e
        L7a:
            r0 = move-exception
            r1 = r7
            r2 = r8
            goto L78
        L7e:
            x6.c0 r9 = new x6.c0
            r9.<init>(r8)
            r8 = r9
        L84:
            boolean r9 = r8 instanceof x6.c0
            r0 = 0
            if (r9 == 0) goto L8b
            r8 = r0
        L8b:
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest r8 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperManifest) r8
            if (r8 == 0) goto La4
            java.lang.String r9 = r8.getName()
            boolean r9 = kotlin.text.o.h0(r9)
            if (r9 != 0) goto La4
            java.util.List r9 = r8.getScrapers()
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto La4
            r0 = r8
        La4:
            if (r0 == 0) goto Lb4
            java.util.Map<java.lang.String, com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest> r8 = r1.manifestCache
            monitor-enter(r8)
            java.util.Map<java.lang.String, com.arflix.tv.data.repository.HttpLocalScraperRuntime$HttpScraperManifest> r9 = r1.manifestCache     // Catch: java.lang.Throwable -> Lb0
            r9.put(r2, r0)     // Catch: java.lang.Throwable -> Lb0
            monitor-exit(r8)
            goto Lb4
        Lb0:
            r0 = move-exception
            r9 = r0
            monitor-exit(r8)
            throw r9
        Lb4:
            return r0
        Lb5:
            r0 = move-exception
            r1 = r7
            r8 = r0
            monitor-exit(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.fetchManifest(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00a5 -> B:21:0x00aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchNetMirrorEpisodes(java.lang.String r10, com.arflix.tv.data.repository.HttpLocalScraperRuntime.NetMirrorPlatform r11, java.lang.String r12, java.lang.String r13, java.util.Map<java.lang.String, java.lang.String> r14, d7.d<? super java.util.List<com.google.gson.JsonObject>> r15) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.fetchNetMirrorEpisodes(java.lang.String, com.arflix.tv.data.repository.HttpLocalScraperRuntime$NetMirrorPlatform, java.lang.String, java.lang.String, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x044f, code lost:
    
        if (r2 != r5) goto L121;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0364 A[LOOP:2: B:81:0x035e->B:83:0x0364, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0382  */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x034d -> B:80:0x0358). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchNetMirrorPlatform(com.arflix.tv.data.repository.HttpLocalScraperRuntime.NetMirrorPlatform r26, java.lang.String r27, java.lang.String r28, java.lang.Integer r29, java.lang.Integer r30, java.lang.String r31, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r32) {
        /*
            Method dump skipped, instruction units count: 1406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.fetchNetMirrorPlatform(com.arflix.tv.data.repository.HttpLocalScraperRuntime$NetMirrorPlatform, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bd A[Catch: all -> 0x00c6, TryCatch #2 {all -> 0x00c6, blocks: (B:33:0x00b9, B:35:0x00bd, B:41:0x00cd, B:47:0x00db, B:51:0x00e6, B:54:0x00ee, B:55:0x00f3, B:57:0x00f9, B:60:0x0107, B:70:0x0121, B:72:0x0129, B:79:0x013c, B:77:0x0135, B:65:0x0112), top: B:101:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00db A[Catch: all -> 0x00c6, TryCatch #2 {all -> 0x00c6, blocks: (B:33:0x00b9, B:35:0x00bd, B:41:0x00cd, B:47:0x00db, B:51:0x00e6, B:54:0x00ee, B:55:0x00f3, B:57:0x00f9, B:60:0x0107, B:70:0x0121, B:72:0x0129, B:79:0x013c, B:77:0x0135, B:65:0x0112), top: B:101:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ee A[Catch: all -> 0x00c6, TryCatch #2 {all -> 0x00c6, blocks: (B:33:0x00b9, B:35:0x00bd, B:41:0x00cd, B:47:0x00db, B:51:0x00e6, B:54:0x00ee, B:55:0x00f3, B:57:0x00f9, B:60:0x0107, B:70:0x0121, B:72:0x0129, B:79:0x013c, B:77:0x0135, B:65:0x0112), top: B:101:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0112 A[Catch: all -> 0x00c6, TRY_LEAVE, TryCatch #2 {all -> 0x00c6, blocks: (B:33:0x00b9, B:35:0x00bd, B:41:0x00cd, B:47:0x00db, B:51:0x00e6, B:54:0x00ee, B:55:0x00f3, B:57:0x00f9, B:60:0x0107, B:70:0x0121, B:72:0x0129, B:79:0x013c, B:77:0x0135, B:65:0x0112), top: B:101:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0135 A[Catch: all -> 0x00c6, TryCatch #2 {all -> 0x00c6, blocks: (B:33:0x00b9, B:35:0x00bd, B:41:0x00cd, B:47:0x00db, B:51:0x00e6, B:54:0x00ee, B:55:0x00f3, B:57:0x00f9, B:60:0x0107, B:70:0x0121, B:72:0x0129, B:79:0x013c, B:77:0x0135, B:65:0x0112), top: B:101:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0162  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchTmdbDetails(int r21, java.lang.String r22, java.lang.String r23, java.lang.Integer r24, d7.d<? super com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpScraperTmdbDetails> r25) {
        /*
            Method dump skipped, instruction units count: 377
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.fetchTmdbDetails(int, java.lang.String, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonArray getArray(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            return asJsonArrayOrNull(jsonElement);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getJson(java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, d7.d<? super com.google.gson.JsonObject> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07621
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJson$1 r0 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07621) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJson$1 r0 = new com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJson$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            java.lang.Object r6 = r0.L$3
            com.google.gson.Gson r6 = (com.google.gson.Gson) r6
            java.lang.Object r7 = r0.L$2
            com.arflix.tv.data.repository.HttpLocalScraperRuntime r7 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime) r7
            java.lang.Object r7 = r0.L$1
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L36
            goto L5e
        L36:
            r6 = move-exception
            goto L69
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            k2.c.G(r8)
            com.google.gson.Gson r8 = r5.gson     // Catch: java.lang.Throwable -> L36
            r0.L$0 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$1 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$2 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L36
            r1 = 0
            r0.I$0 = r1     // Catch: java.lang.Throwable -> L36
            r0.label = r2     // Catch: java.lang.Throwable -> L36
            java.lang.Object r6 = r5.getText(r6, r7, r0)     // Catch: java.lang.Throwable -> L36
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L5b
            return r7
        L5b:
            r4 = r8
            r8 = r6
            r6 = r4
        L5e:
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L36
            java.lang.Class<com.google.gson.JsonObject> r7 = com.google.gson.JsonObject.class
            java.lang.Object r6 = r6.fromJson(r8, r7)     // Catch: java.lang.Throwable -> L36
            com.google.gson.JsonObject r6 = (com.google.gson.JsonObject) r6     // Catch: java.lang.Throwable -> L36
            goto L6f
        L69:
            x6.c0 r7 = new x6.c0
            r7.<init>(r6)
            r6 = r7
        L6f:
            boolean r7 = r6 instanceof x6.c0
            if (r7 == 0) goto L75
            goto L76
        L75:
            r3 = r6
        L76:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.getJson(java.lang.String, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getJson$default(HttpLocalScraperRuntime httpLocalScraperRuntime, String str, Map map, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = kotlin.collections.a0.f19683i;
        }
        return httpLocalScraperRuntime.getJson(str, map, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getJsonElement(java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, d7.d<? super com.google.gson.JsonElement> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07631
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJsonElement$1 r0 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime.C07631) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJsonElement$1 r0 = new com.arflix.tv.data.repository.HttpLocalScraperRuntime$getJsonElement$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            java.lang.Object r6 = r0.L$3
            com.google.gson.Gson r6 = (com.google.gson.Gson) r6
            java.lang.Object r7 = r0.L$2
            com.arflix.tv.data.repository.HttpLocalScraperRuntime r7 = (com.arflix.tv.data.repository.HttpLocalScraperRuntime) r7
            java.lang.Object r7 = r0.L$1
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L36
            goto L5e
        L36:
            r6 = move-exception
            goto L69
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            k2.c.G(r8)
            com.google.gson.Gson r8 = r5.gson     // Catch: java.lang.Throwable -> L36
            r0.L$0 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$1 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$2 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L36
            r1 = 0
            r0.I$0 = r1     // Catch: java.lang.Throwable -> L36
            r0.label = r2     // Catch: java.lang.Throwable -> L36
            java.lang.Object r6 = r5.getText(r6, r7, r0)     // Catch: java.lang.Throwable -> L36
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L5b
            return r7
        L5b:
            r4 = r8
            r8 = r6
            r6 = r4
        L5e:
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L36
            java.lang.Class<com.google.gson.JsonElement> r7 = com.google.gson.JsonElement.class
            java.lang.Object r6 = r6.fromJson(r8, r7)     // Catch: java.lang.Throwable -> L36
            com.google.gson.JsonElement r6 = (com.google.gson.JsonElement) r6     // Catch: java.lang.Throwable -> L36
            goto L6f
        L69:
            x6.c0 r7 = new x6.c0
            r7.<init>(r6)
            r6 = r7
        L6f:
            boolean r7 = r6 instanceof x6.c0
            if (r7 == 0) goto L75
            goto L76
        L75:
            r3 = r6
        L76:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.getJsonElement(java.lang.String, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getJsonElement$default(HttpLocalScraperRuntime httpLocalScraperRuntime, String str, Map map, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = kotlin.collections.a0.f19683i;
        }
        return httpLocalScraperRuntime.getJsonElement(str, map, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final gb.h0 getNoRedirectClient() {
        return (gb.h0) this.noRedirectClient.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonObject getObject(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            return asJsonObjectOrNull(jsonElement);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getText(String str, Map<String, String> map, d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new C07642(str, this, map, null), dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getText$default(HttpLocalScraperRuntime httpLocalScraperRuntime, String str, Map map, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = kotlin.collections.a0.f19683i;
        }
        return httpLocalScraperRuntime.getText(str, map, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String githubManifestUrlFor(java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.githubManifestUrlFor(java.lang.String):java.lang.String");
    }

    private final boolean isHttpOnlyEnabled(HttpScraperEntry httpScraperEntry) {
        if (!httpScraperEntry.getEnabled()) {
            return false;
        }
        List<String> formats = httpScraperEntry.getFormats();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(formats, 10));
        Iterator<T> it = formats.iterator();
        while (it.hasNext()) {
            arrayList.add(((String) it.next()).toLowerCase(Locale.US));
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        Set set = setG1;
        boolean z = set instanceof Collection;
        if (!z || !set.isEmpty()) {
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                if (P2P_FORMATS.contains((String) it2.next())) {
                    return false;
                }
            }
        }
        if (setG1.isEmpty()) {
            return true;
        }
        if (!z || !set.isEmpty()) {
            Iterator it3 = set.iterator();
            while (it3.hasNext()) {
                if (HTTP_FORMATS.contains((String) it3.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSupportedHttpLocalScraper(HttpScraperEntry httpScraperEntry) {
        if (isHttpOnlyEnabled(httpScraperEntry)) {
            return IMPLEMENTED_HTTP_LOCAL_PROVIDERS.contains(httpScraperEntry.getId().toLowerCase(Locale.US));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String manifestUrlFor(String url) {
        String string = kotlin.text.o.L0(url).toString();
        String strO0 = kotlin.text.o.O0(kotlin.text.o.G0('#', string, string), '/');
        String strGithubManifestUrlFor = githubManifestUrlFor(strO0);
        return strGithubManifestUrlFor != null ? strGithubManifestUrlFor : kotlin.text.u.K(strO0, "/manifest.json", true) ? strO0 : strO0.concat("/manifest.json");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object netMirrorCookie(d7.d<? super java.lang.String> r20) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.netMirrorCookie(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final gb.h0 noRedirectClient_delegate$lambda$0(HttpLocalScraperRuntime httpLocalScraperRuntime) {
        gb.g0 g0VarB = httpLocalScraperRuntime.okHttpClient.b();
        g0VarB.f15670h = false;
        g0VarB.f15671i = false;
        return new gb.h0(g0VarB);
    }

    private final String normalizeQuality(String value) {
        String lowerCase = value.toLowerCase(Locale.US);
        return (kotlin.text.o.T(lowerCase, "2160", false) || kotlin.text.o.T(lowerCase, "4k", false)) ? "4K" : kotlin.text.o.T(lowerCase, "1440", false) ? "1440p" : kotlin.text.o.T(lowerCase, "1080", false) ? "1080p" : kotlin.text.o.T(lowerCase, "720", false) ? "720p" : kotlin.text.o.T(lowerCase, "480", false) ? "480p" : kotlin.text.o.T(lowerCase, "360", false) ? "360p" : "Auto";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object postJson(String str, String str2, d7.d<? super JsonObject> dVar) {
        return ka.m0.y(x0.f19655d, new C07662(str, str2, this, null), dVar);
    }

    private final String qualityFromText(String value) {
        if (kotlin.text.o.h0(value)) {
            value = "Auto";
        }
        return normalizeQuality(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveBrazucaPlay(int r21, java.lang.String r22, java.lang.Integer r23, java.lang.Integer r24, java.lang.String r25, java.lang.Integer r26, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r27) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveBrazucaPlay(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019d A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #1 {all -> 0x009d, blocks: (B:13:0x008c, B:47:0x0197, B:49:0x019d, B:20:0x00ca, B:38:0x0171, B:40:0x0175, B:42:0x017d, B:46:0x0186, B:23:0x00dc, B:27:0x00eb, B:30:0x00f4, B:32:0x00fb, B:34:0x012a, B:33:0x011b), top: B:83:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x024c A[Catch: all -> 0x025b, TryCatch #0 {all -> 0x025b, blocks: (B:61:0x0237, B:71:0x02a4, B:54:0x01be, B:57:0x01ce, B:73:0x02b5, B:64:0x024c, B:69:0x025d), top: B:81:0x0237 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02a4 A[Catch: all -> 0x025b, TryCatch #0 {all -> 0x025b, blocks: (B:61:0x0237, B:71:0x02a4, B:54:0x01be, B:57:0x01ce, B:73:0x02b5, B:64:0x024c, B:69:0x025d), top: B:81:0x0237 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02b5 A[Catch: all -> 0x025b, TRY_LEAVE, TryCatch #0 {all -> 0x025b, blocks: (B:61:0x0237, B:71:0x02a4, B:54:0x01be, B:57:0x01ce, B:73:0x02b5, B:64:0x024c, B:69:0x025d), top: B:81:0x0237 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x022d -> B:81:0x0237). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveDooFlix(int r29, java.lang.String r30, java.lang.Integer r31, java.lang.Integer r32, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r33) {
        /*
            Method dump skipped, instruction units count: 714
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveDooFlix(int, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveFMovies(int r19, java.lang.String r20, java.lang.Integer r21, java.lang.Integer r22, java.lang.String r23, java.lang.Integer r24, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r25) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveFMovies(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveHttpStreams(Addon addon, HttpScraperManifest httpScraperManifest, int i10, String str, Integer num, Integer num2, String str2, Integer num3, d7.d<? super List<StreamSource>> dVar) {
        return ka.l0.c(new C07712(httpScraperManifest, this, str, i10, num, num2, str2, num3, addon, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0126 A[Catch: all -> 0x0073, TryCatch #0 {all -> 0x0073, blocks: (B:15:0x0069, B:46:0x01e3, B:40:0x0192, B:42:0x0198, B:22:0x00a0, B:36:0x011f, B:39:0x0126, B:25:0x00c3, B:32:0x00fd, B:28:0x00cf), top: B:56:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0198 A[Catch: all -> 0x0073, TryCatch #0 {all -> 0x0073, blocks: (B:15:0x0069, B:46:0x01e3, B:40:0x0192, B:42:0x0198, B:22:0x00a0, B:36:0x011f, B:39:0x0126, B:25:0x00c3, B:32:0x00fd, B:28:0x00cf), top: B:56:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x01d7 -> B:46:0x01e3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveNetMirror(int r28, java.lang.String r29, java.lang.Integer r30, java.lang.Integer r31, java.lang.String r32, java.lang.Integer r33, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r34) {
        /*
            Method dump skipped, instruction units count: 511
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveNetMirror(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0357, code lost:
    
        if (r0 == r13) goto L103;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01bf A[Catch: all -> 0x007b, TryCatch #0 {all -> 0x007b, blocks: (B:16:0x0076, B:104:0x035a, B:23:0x00ba, B:79:0x02af, B:81:0x02b9, B:83:0x02bf, B:95:0x02e6, B:97:0x02ef, B:101:0x0317, B:98:0x0301, B:100:0x0308, B:86:0x02c9, B:88:0x02d1, B:90:0x02d7, B:26:0x00ec, B:43:0x01ad, B:45:0x01bf, B:46:0x01ca, B:48:0x01d0, B:50:0x01dd, B:53:0x01ec, B:56:0x020b, B:58:0x0211, B:63:0x0235, B:65:0x0239, B:67:0x0245, B:69:0x024b, B:71:0x0254, B:73:0x025b, B:74:0x026a, B:75:0x026c, B:29:0x0112, B:36:0x0153, B:39:0x015e, B:32:0x0121), top: B:112:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02c9 A[Catch: all -> 0x007b, TryCatch #0 {all -> 0x007b, blocks: (B:16:0x0076, B:104:0x035a, B:23:0x00ba, B:79:0x02af, B:81:0x02b9, B:83:0x02bf, B:95:0x02e6, B:97:0x02ef, B:101:0x0317, B:98:0x0301, B:100:0x0308, B:86:0x02c9, B:88:0x02d1, B:90:0x02d7, B:26:0x00ec, B:43:0x01ad, B:45:0x01bf, B:46:0x01ca, B:48:0x01d0, B:50:0x01dd, B:53:0x01ec, B:56:0x020b, B:58:0x0211, B:63:0x0235, B:65:0x0239, B:67:0x0245, B:69:0x024b, B:71:0x0254, B:73:0x025b, B:74:0x026a, B:75:0x026c, B:29:0x0112, B:36:0x0153, B:39:0x015e, B:32:0x0121), top: B:112:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolvePlayImdb(int r18, java.lang.String r19, java.lang.Integer r20, java.lang.Integer r21, java.lang.String r22, java.lang.Integer r23, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r24) {
        /*
            Method dump skipped, instruction units count: 875
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolvePlayImdb(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveRedirectUrl(String str, Map<String, String> map, d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new C07752(str, map, this, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:0|2|(2:4|(1:6)(1:8))(0)|7|9|(1:87)|(1:(1:(7:13|14|15|46|(1:78)(3:52|(1:57)(1:56)|(3:59|(5:61|(1:63)(1:64)|65|(1:67)|68)(4:69|(1:71)(1:72)|(1:74)|75)|76)(0))|80|(2:82|88)(2:83|84))(2:18|19))(3:20|21|22))(3:23|24|(2:26|44)(1:27))|28|29|85|30|(3:(1:33)(1:36)|(1:38)(1:39)|40)(1:41)|42|(5:45|46|(2:48|78)(0)|80|(0)(0))|44) */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0137, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0262, code lost:
    
        r0 = new x6.c0(r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:78:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r14v12, types: [int] */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveRgShows(int r21, java.lang.String r22, java.lang.Integer r23, java.lang.Integer r24, java.lang.String r25, java.lang.Integer r26, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r27) {
        /*
            Method dump skipped, instruction units count: 626
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveRgShows(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a9 A[Catch: all -> 0x003e, TryCatch #3 {all -> 0x003e, blocks: (B:13:0x003a, B:39:0x009e, B:41:0x00a9, B:43:0x00b5, B:45:0x00c1, B:47:0x00cd), top: B:73:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1 A[Catch: all -> 0x003e, TryCatch #3 {all -> 0x003e, blocks: (B:13:0x003a, B:39:0x009e, B:41:0x00a9, B:43:0x00b5, B:45:0x00c1, B:47:0x00cd), top: B:73:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveTmdbId(java.lang.String r11, java.lang.String r12, d7.d<? super java.lang.Integer> r13) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveTmdbId(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveVidEasy(int r27, java.lang.String r28, java.lang.Integer r29, java.lang.Integer r30, java.lang.String r31, java.lang.Integer r32, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r33) {
        /*
            Method dump skipped, instruction units count: 523
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveVidEasy(int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveVidLink(int r17, java.lang.String r18, java.lang.Integer r19, java.lang.Integer r20, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r21) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveVidLink(int, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01c3 A[Catch: all -> 0x011d, TRY_LEAVE, TryCatch #1 {all -> 0x011d, blocks: (B:52:0x01b8, B:55:0x01c3, B:40:0x010a, B:42:0x0118, B:46:0x0122, B:49:0x0168, B:48:0x0154), top: B:63:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveVidMody(int r22, java.lang.String r23, java.lang.Integer r24, java.lang.Integer r25, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r26) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveVidMody(int, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0241 A[Catch: all -> 0x008f, TryCatch #0 {all -> 0x008f, blocks: (B:17:0x0083, B:85:0x0334, B:87:0x0338, B:89:0x0340, B:93:0x034a, B:94:0x0354, B:96:0x035a, B:98:0x0362, B:103:0x03a8, B:101:0x036f, B:105:0x03ad, B:106:0x03b0, B:24:0x00d2, B:79:0x02bf, B:82:0x02cb, B:27:0x010c, B:70:0x026e, B:72:0x0278, B:74:0x027e, B:76:0x0286, B:30:0x013b, B:57:0x0220, B:59:0x022a, B:61:0x0230, B:63:0x0238, B:65:0x0241, B:66:0x0245, B:33:0x0160, B:40:0x01a9, B:43:0x01b5, B:46:0x01c0, B:49:0x01c9, B:51:0x01d0, B:54:0x01f4, B:53:0x01ef, B:36:0x0174), top: B:113:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02cb A[Catch: all -> 0x008f, TryCatch #0 {all -> 0x008f, blocks: (B:17:0x0083, B:85:0x0334, B:87:0x0338, B:89:0x0340, B:93:0x034a, B:94:0x0354, B:96:0x035a, B:98:0x0362, B:103:0x03a8, B:101:0x036f, B:105:0x03ad, B:106:0x03b0, B:24:0x00d2, B:79:0x02bf, B:82:0x02cb, B:27:0x010c, B:70:0x026e, B:72:0x0278, B:74:0x027e, B:76:0x0286, B:30:0x013b, B:57:0x0220, B:59:0x022a, B:61:0x0230, B:63:0x0238, B:65:0x0241, B:66:0x0245, B:33:0x0160, B:40:0x01a9, B:43:0x01b5, B:46:0x01c0, B:49:0x01c9, B:51:0x01d0, B:54:0x01f4, B:53:0x01ef, B:36:0x0174), top: B:113:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x035a A[Catch: all -> 0x008f, TryCatch #0 {all -> 0x008f, blocks: (B:17:0x0083, B:85:0x0334, B:87:0x0338, B:89:0x0340, B:93:0x034a, B:94:0x0354, B:96:0x035a, B:98:0x0362, B:103:0x03a8, B:101:0x036f, B:105:0x03ad, B:106:0x03b0, B:24:0x00d2, B:79:0x02bf, B:82:0x02cb, B:27:0x010c, B:70:0x026e, B:72:0x0278, B:74:0x027e, B:76:0x0286, B:30:0x013b, B:57:0x0220, B:59:0x022a, B:61:0x0230, B:63:0x0238, B:65:0x0241, B:66:0x0245, B:33:0x0160, B:40:0x01a9, B:43:0x01b5, B:46:0x01c0, B:49:0x01c9, B:51:0x01d0, B:54:0x01f4, B:53:0x01ef, B:36:0x0174), top: B:113:0x003b }] */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r17v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v27, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v4, types: [x6.c0] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveVidSrc(int r26, java.lang.String r27, java.lang.Integer r28, java.lang.Integer r29, d7.d<? super java.util.List<com.arflix.tv.data.repository.HttpLocalScraperRuntime.HttpResolvedStream>> r30) {
        /*
            Method dump skipped, instruction units count: 958
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveVidSrc(int, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveVideasyServers(int i10, HttpScraperTmdbDetails httpScraperTmdbDetails, String str, Integer num, Integer num2, List<VideasyServer> list, String str2, d7.d<? super List<HttpResolvedStream>> dVar) {
        return ka.l0.c(new C07822(list, str, this, httpScraperTmdbDetails, i10, num, num2, str2, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String sanitizeProviderLabel(String value) {
        return g.o(NUVIO_REGEX, value, "HTTP");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String shortHash(String value) {
        return kotlin.collections.x.u0(kotlin.collections.r.l0(8, MessageDigest.getInstance("SHA-256").digest(value.getBytes(kotlin.text.a.f19924a))), "", null, null, new f(25), 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence shortHash$lambda$0(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String string(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            return asStringOrNull(jsonElement);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StreamSource toStreamSource(HttpResolvedStream httpResolvedStream, Addon addon) {
        Map<String, String> headers = httpResolvedStream.getHeaders();
        LinkedHashMap linkedHashMap = new LinkedHashMap(kotlin.collections.i0.q0(headers.size()));
        Iterator<T> it = headers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(kotlin.text.o.L0((String) entry.getKey()).toString(), entry.getValue());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), kotlin.text.o.L0((String) entry2.getValue()).toString());
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry entry3 : linkedHashMap2.entrySet()) {
            if (!kotlin.text.o.h0((CharSequence) entry3.getKey()) && !kotlin.text.o.h0((CharSequence) entry3.getValue())) {
                linkedHashMap3.put(entry3.getKey(), entry3.getValue());
            }
        }
        String title = httpResolvedStream.getTitle();
        if (kotlin.text.o.h0(title)) {
            title = httpResolvedStream.getProvider();
        }
        String str = title;
        String strT = androidx.compose.foundation.c.t(sanitizeProviderLabel(addon.getName()), " - ", httpResolvedStream.getProvider());
        String id = addon.getId();
        String strNormalizeQuality = normalizeQuality(httpResolvedStream.getQuality());
        String url = httpResolvedStream.getUrl();
        if (linkedHashMap3.isEmpty()) {
            linkedHashMap3 = null;
        }
        StreamBehaviorHints streamBehaviorHints = linkedHashMap3 != null ? new StreamBehaviorHints(false, null, null, null, new ProxyHeaders(linkedHashMap3, null, 2, null), null, null, null, null, null, null, null, null, null, 16367, null) : null;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        return new StreamSource(str, strT, id, strNormalizeQuality, "", null, url, null, null, streamBehaviorHints, zVar, zVar, null, null, null, 28672, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String urlEncode(String str) {
        return kotlin.text.u.O(URLEncoder.encode(str, "UTF-8"), "+", "%20", false);
    }

    public final boolean canHandle(Addon addon) {
        String id;
        AddonManifest manifest = addon.getManifest();
        return (manifest == null || (id = manifest.getId()) == null || (!kotlin.text.u.P(id, HTTP_LOCAL_MANIFEST_PREFIX, false) && !kotlin.text.u.P(id, LEGACY_LOCAL_MANIFEST_PREFIX, false))) ? false : true;
    }

    public final Object fetchInstallCandidate(String str, String str2, d7.d<? super HttpLocalScraperInstallCandidate> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(str, str2, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x012b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x012c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveEpisodeStreams(com.arflix.tv.data.model.Addon r17, java.lang.String r18, int r19, int r20, java.lang.Integer r21, java.lang.String r22, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r23) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveEpisodeStreams(com.arflix.tv.data.model.Addon, java.lang.String, int, int, java.lang.Integer, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveMovieStreams(com.arflix.tv.data.model.Addon r14, java.lang.String r15, java.lang.String r16, java.lang.Integer r17, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r18) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime.resolveMovieStreams(com.arflix.tv.data.model.Addon, java.lang.String, java.lang.String, java.lang.Integer, d7.d):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$VideasyServer;", "", "provider", "", ContentDisposition.Parameters.Name, "endpoint", "moviesOnly", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getProvider", "()Ljava/lang/String;", "getName", "getEndpoint", "getMoviesOnly", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideasyServer {
        private final String endpoint;
        private final boolean moviesOnly;
        private final String name;
        private final String provider;

        public VideasyServer(String str, String str2, String str3, boolean z) {
            this.provider = str;
            this.name = str2;
            this.endpoint = str3;
            this.moviesOnly = z;
        }

        public static /* synthetic */ VideasyServer copy$default(VideasyServer videasyServer, String str, String str2, String str3, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = videasyServer.provider;
            }
            if ((i10 & 2) != 0) {
                str2 = videasyServer.name;
            }
            if ((i10 & 4) != 0) {
                str3 = videasyServer.endpoint;
            }
            if ((i10 & 8) != 0) {
                z = videasyServer.moviesOnly;
            }
            return videasyServer.copy(str, str2, str3, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEndpoint() {
            return this.endpoint;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getMoviesOnly() {
            return this.moviesOnly;
        }

        public final VideasyServer copy(String provider, String name, String endpoint, boolean moviesOnly) {
            return new VideasyServer(provider, name, endpoint, moviesOnly);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideasyServer)) {
                return false;
            }
            VideasyServer videasyServer = (VideasyServer) other;
            return kotlin.jvm.internal.p.a(this.provider, videasyServer.provider) && kotlin.jvm.internal.p.a(this.name, videasyServer.name) && kotlin.jvm.internal.p.a(this.endpoint, videasyServer.endpoint) && this.moviesOnly == videasyServer.moviesOnly;
        }

        public final String getEndpoint() {
            return this.endpoint;
        }

        public final boolean getMoviesOnly() {
            return this.moviesOnly;
        }

        public final String getName() {
            return this.name;
        }

        public final String getProvider() {
            return this.provider;
        }

        public int hashCode() {
            return androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.provider.hashCode() * 31, 31, this.name), 31, this.endpoint) + (this.moviesOnly ? 1231 : 1237);
        }

        public String toString() {
            String str = this.provider;
            String str2 = this.name;
            String str3 = this.endpoint;
            boolean z = this.moviesOnly;
            StringBuilder sbR = a2.r("VideasyServer(provider=", str, ", name=", str2, ", endpoint=");
            sbR.append(str3);
            sbR.append(", moviesOnly=");
            sbR.append(z);
            sbR.append(")");
            return sbR.toString();
        }

        public /* synthetic */ VideasyServer(String str, String str2, String str3, boolean z, int i10, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, (i10 & 8) != 0 ? false : z);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003JG\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;", "", "provider", "", LinkHeader.Parameters.Title, "url", "quality", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getProvider", "()Ljava/lang/String;", "getTitle", "getUrl", "getQuality", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HttpResolvedStream {
        private final Map<String, String> headers;
        private final String provider;
        private final String quality;
        private final String title;
        private final String url;

        public HttpResolvedStream(String str, String str2, String str3, String str4, Map<String, String> map) {
            this.provider = str;
            this.title = str2;
            this.url = str3;
            this.quality = str4;
            this.headers = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HttpResolvedStream copy$default(HttpResolvedStream httpResolvedStream, String str, String str2, String str3, String str4, Map map, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = httpResolvedStream.provider;
            }
            if ((i10 & 2) != 0) {
                str2 = httpResolvedStream.title;
            }
            if ((i10 & 4) != 0) {
                str3 = httpResolvedStream.url;
            }
            if ((i10 & 8) != 0) {
                str4 = httpResolvedStream.quality;
            }
            if ((i10 & 16) != 0) {
                map = httpResolvedStream.headers;
            }
            Map map2 = map;
            String str5 = str3;
            return httpResolvedStream.copy(str, str2, str5, str4, map2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getQuality() {
            return this.quality;
        }

        public final Map<String, String> component5() {
            return this.headers;
        }

        public final HttpResolvedStream copy(String provider, String title, String url, String quality, Map<String, String> headers) {
            return new HttpResolvedStream(provider, title, url, quality, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpResolvedStream)) {
                return false;
            }
            HttpResolvedStream httpResolvedStream = (HttpResolvedStream) other;
            return kotlin.jvm.internal.p.a(this.provider, httpResolvedStream.provider) && kotlin.jvm.internal.p.a(this.title, httpResolvedStream.title) && kotlin.jvm.internal.p.a(this.url, httpResolvedStream.url) && kotlin.jvm.internal.p.a(this.quality, httpResolvedStream.quality) && kotlin.jvm.internal.p.a(this.headers, httpResolvedStream.headers);
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final String getProvider() {
            return this.provider;
        }

        public final String getQuality() {
            return this.quality;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return this.headers.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.provider.hashCode() * 31, 31, this.title), 31, this.url), 31, this.quality);
        }

        public String toString() {
            String str = this.provider;
            String str2 = this.title;
            String str3 = this.url;
            String str4 = this.quality;
            Map<String, String> map = this.headers;
            StringBuilder sbR = a2.r("HttpResolvedStream(provider=", str, ", title=", str2, ", url=");
            y.a.i(sbR, str3, ", quality=", str4, ", headers=");
            sbR.append(map);
            sbR.append(")");
            return sbR.toString();
        }

        public /* synthetic */ HttpResolvedStream(String str, String str2, String str3, String str4, Map map, int i10, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, str4, (i10 & 16) != 0 ? kotlin.collections.a0.f19683i : map);
        }
    }
}
