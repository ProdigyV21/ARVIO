package com.arflix.tv.data.telegram;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.media3.common.C;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.R;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.model.StreamSource;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002;<B+\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0001\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0018\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019JL\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010\u001f\u001a\u00020\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\fH\u0002¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\fH\u0002¢\u0006\u0004\b&\u0010'J8\u0010*\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f0)2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\fH\u0082@¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0002¢\u0006\u0004\b,\u0010 J\u0017\u0010.\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0017H\u0002¢\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u0015¢\u0006\u0004\b0\u00101JT\u00102\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b2\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00104R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00105R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u00106R \u00109\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000208078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:¨\u0006="}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramSourceResolver;", "", "Lcom/arflix/tv/data/telegram/TelegramRepository;", "repository", "Lcom/arflix/tv/data/telegram/TelegramSearchMatcher;", "matcher", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "Landroid/content/Context;", "context", "<init>", "(Lcom/arflix/tv/data/telegram/TelegramRepository;Lcom/arflix/tv/data/telegram/TelegramSearchMatcher;Lcom/arflix/tv/data/api/TmdbApi;Landroid/content/Context;)V", "", "imdbId", LinkHeader.Parameters.Title, "", "season", "episode", "cacheKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "year", "", "isMovie", "", "cacheTtl", "(Ljava/lang/Integer;Z)J", "", "Lcom/arflix/tv/data/model/StreamSource;", "resolveInternal", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "raw", "friendlyError", "(Ljava/lang/String;)Ljava/lang/String;", "message", "Lx6/t0;", "showToast", "(Ljava/lang/String;)V", "quality", "qualityTier", "(Ljava/lang/String;)I", "langCode", "Lx6/x;", "fetchTitles", "(Ljava/lang/String;ZLjava/lang/String;Ld7/d;)Ljava/lang/Object;", "parseQuality", "bytes", "formatBytes", "(J)Ljava/lang/String;", "isEnabled", "()Z", "resolve", "Lcom/arflix/tv/data/telegram/TelegramRepository;", "Lcom/arflix/tv/data/telegram/TelegramSearchMatcher;", "Lcom/arflix/tv/data/api/TmdbApi;", "Landroid/content/Context;", "j$/util/concurrent/ConcurrentHashMap", "Lcom/arflix/tv/data/telegram/TelegramSourceResolver$CacheEntry;", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "Companion", "CacheEntry", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramSourceResolver {
    private static final long CACHE_TTL_LONG_MS = 86400000;
    private static final long CACHE_TTL_SHORT_MS = 7200000;
    private static final int MAX_RESULTS = 100;
    private static final int SCORE_THRESHOLD = 55;
    private static final long SEARCH_TIMEOUT_MS = 20000;
    private static final String TAG = "TelegramResolver";
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final Context context;
    private final TelegramSearchMatcher matcher;
    private final TelegramRepository repository;
    private final TmdbApi tmdbApi;
    public static final int $stable = 8;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramSourceResolver$CacheEntry;", "", "results", "", "Lcom/arflix/tv/data/model/StreamSource;", "expiresAt", "", "<init>", "(Ljava/util/List;J)V", "getResults", "()Ljava/util/List;", "getExpiresAt", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CacheEntry {
        private final long expiresAt;
        private final List<StreamSource> results;

        public CacheEntry(List<StreamSource> list, long j10) {
            this.results = list;
            this.expiresAt = j10;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CacheEntry copy$default(CacheEntry cacheEntry, List list, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = cacheEntry.results;
            }
            if ((i10 & 2) != 0) {
                j10 = cacheEntry.expiresAt;
            }
            return cacheEntry.copy(list, j10);
        }

        public final List<StreamSource> component1() {
            return this.results;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final CacheEntry copy(List<StreamSource> results, long expiresAt) {
            return new CacheEntry(results, expiresAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CacheEntry)) {
                return false;
            }
            CacheEntry cacheEntry = (CacheEntry) other;
            return p.a(this.results, cacheEntry.results) && this.expiresAt == cacheEntry.expiresAt;
        }

        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final List<StreamSource> getResults() {
            return this.results;
        }

        public int hashCode() {
            int iHashCode = this.results.hashCode() * 31;
            long j10 = this.expiresAt;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "CacheEntry(results=" + this.results + ", expiresAt=" + this.expiresAt + ")";
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramSourceResolver$fetchTitles$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver", f = "TelegramSourceResolver.kt", l = {209, 216, 219, 224, 227}, m = "fetchTitles", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramSourceResolver.this.fetchTitles(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramSourceResolver$resolve$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver", f = "TelegramSourceResolver.kt", l = {TsExtractor.TS_SYNC_BYTE}, m = "resolve", v = 2)
    public static final class C12981 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12981(d7.d<? super C12981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramSourceResolver.this.resolve(null, null, null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramSourceResolver$resolveInternal$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver", f = "TelegramSourceResolver.kt", l = {95, 101, 111}, m = "resolveInternal", v = 2)
    public static final class C12991 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12991(d7.d<? super C12991> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramSourceResolver.this.resolveInternal(null, null, null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramSourceResolver$resolveInternal$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver$resolveInternal$2", f = "TelegramSourceResolver.kt", l = {128}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<TelegramVideoMessage> $allMessages;
        final /* synthetic */ Set<Long> $excludedIds;
        final /* synthetic */ List<String> $queries;
        final /* synthetic */ Set<x> $seen;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TelegramSourceResolver this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List<String> list, TelegramSourceResolver telegramSourceResolver, Set<Long> set, Set<x> set2, List<TelegramVideoMessage> list2, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$queries = list;
            this.this$0 = telegramSourceResolver;
            this.$excludedIds = set;
            this.$seen = set2;
            this.$allMessages = list2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$queries, this.this$0, this.$excludedIds, this.$seen, this.$allMessages, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<String> list = this.$queries;
                TelegramSourceResolver telegramSourceResolver = this.this$0;
                Set<Long> set = this.$excludedIds;
                ArrayList arrayList = new ArrayList(s.U(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(m0.e(3, null, k0Var, new TelegramSourceResolver$resolveInternal$2$1$1(telegramSourceResolver, (String) it.next(), set, null)));
                }
                this.L$0 = null;
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
            ArrayList<TelegramVideoMessage> arrayListV = s.V((Iterable) obj);
            Set<x> set2 = this.$seen;
            List<TelegramVideoMessage> list2 = this.$allMessages;
            for (TelegramVideoMessage telegramVideoMessage : arrayListV) {
                if (set2.add(new x(telegramVideoMessage.getFileName(), new Long(telegramVideoMessage.getFileSize())))) {
                    list2.add(telegramVideoMessage);
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public TelegramSourceResolver(TelegramRepository telegramRepository, TelegramSearchMatcher telegramSearchMatcher, TmdbApi tmdbApi, @ApplicationContext Context context) {
        this.repository = telegramRepository;
        this.matcher = telegramSearchMatcher;
        this.tmdbApi = tmdbApi;
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v4 */
    private final String cacheKey(String imdbId, String title, Integer season, Integer episode) {
        Object obj = season;
        Object obj2 = season;
        if (o.h0(imdbId)) {
            if (season == null) {
                obj2 = "";
            }
            if (episode == 0) {
                episode = "";
            }
            return title + ":" + obj2 + ":" + episode;
        }
        if (season == null) {
            obj = "";
        }
        if (episode == 0) {
            episode = "";
        }
        return imdbId + ":" + obj + ":" + episode;
    }

    private final long cacheTtl(Integer year, boolean isMovie) {
        if (!isMovie) {
            return CACHE_TTL_SHORT_MS;
        }
        int i10 = Calendar.getInstance().get(1);
        if (year == null || year.intValue() >= i10 - 1) {
            return CACHE_TTL_SHORT_MS;
        }
        return 86400000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0097: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]) (LINE:152), block:B:30:0x0097 */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e A[Catch: Exception -> 0x00cd, TryCatch #1 {Exception -> 0x00cd, blocks: (B:66:0x018c, B:69:0x0199, B:72:0x01a1, B:80:0x01d1, B:58:0x014f, B:35:0x00c4, B:47:0x010a, B:49:0x010e, B:52:0x0125, B:54:0x012b, B:62:0x0168, B:90:0x0207, B:50:0x0119), top: B:101:0x00c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0119 A[Catch: Exception -> 0x00cd, TryCatch #1 {Exception -> 0x00cd, blocks: (B:66:0x018c, B:69:0x0199, B:72:0x01a1, B:80:0x01d1, B:58:0x014f, B:35:0x00c4, B:47:0x010a, B:49:0x010e, B:52:0x0125, B:54:0x012b, B:62:0x0168, B:90:0x0207, B:50:0x0119), top: B:101:0x00c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0125 A[Catch: Exception -> 0x00cd, TryCatch #1 {Exception -> 0x00cd, blocks: (B:66:0x018c, B:69:0x0199, B:72:0x01a1, B:80:0x01d1, B:58:0x014f, B:35:0x00c4, B:47:0x010a, B:49:0x010e, B:52:0x0125, B:54:0x012b, B:62:0x0168, B:90:0x0207, B:50:0x0119), top: B:101:0x00c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015b A[PHI: r0 r2 r3 r4 r5 r6
      0x015b: PHI (r0v25 e7.a) = (r0v12 e7.a), (r0v26 e7.a) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x015b: PHI (r2v19 int) = (r2v11 int), (r2v21 int) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x015b: PHI (r3v19 boolean) = (r3v6 boolean), (r3v21 boolean) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x015b: PHI (r4v10 java.lang.String) = (r4v3 java.lang.String), (r4v12 java.lang.String) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x015b: PHI (r5v8 ??) = (r5v31 ??), (r5v32 ??) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x015b: PHI (r6v7 java.lang.String) = (r6v4 java.lang.String), (r6v11 java.lang.String) binds: [B:68:0x0198, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0163 A[PHI: r0 r2 r3 r4 r5
      0x0163: PHI (r0v13 e7.a) = (r0v12 e7.a), (r0v26 e7.a) binds: [B:67:0x0196, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x0163: PHI (r2v12 int) = (r2v11 int), (r2v21 int) binds: [B:67:0x0196, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x0163: PHI (r3v7 boolean) = (r3v6 boolean), (r3v21 boolean) binds: [B:67:0x0196, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x0163: PHI (r4v4 java.lang.String) = (r4v3 java.lang.String), (r4v12 java.lang.String) binds: [B:67:0x0196, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]
      0x0163: PHI (r5v6 ??) = (r5v35 ??), (r5v36 ??) binds: [B:67:0x0196, B:59:0x0159] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01cf A[PHI: r2 r3
      0x01cf: PHI (r2v16 java.lang.String) = (r2v14 java.lang.String), (r2v17 java.lang.String), (r2v18 java.lang.String) binds: [B:85:0x01fc, B:77:0x01cc, B:87:0x01ff] A[DONT_GENERATE, DONT_INLINE]
      0x01cf: PHI (r3v11 ??) = (r3v48 ??), (r3v49 ??), (r3v14 ??) binds: [B:85:0x01fc, B:77:0x01cc, B:87:0x01ff] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0207 A[Catch: Exception -> 0x00cd, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x00cd, blocks: (B:66:0x018c, B:69:0x0199, B:72:0x01a1, B:80:0x01d1, B:58:0x014f, B:35:0x00c4, B:47:0x010a, B:49:0x010e, B:52:0x0125, B:54:0x012b, B:62:0x0168, B:90:0x0207, B:50:0x0119), top: B:101:0x00c4 }] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchTitles(java.lang.String r18, boolean r19, java.lang.String r20, d7.d<? super x6.x> r21) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramSourceResolver.fetchTitles(java.lang.String, boolean, java.lang.String, d7.d):java.lang.Object");
    }

    private final String formatBytes(long bytes) {
        return bytes <= 0 ? "" : bytes >= C.NANOS_PER_SECOND ? String.format("%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1.0E9d)}, 1)) : bytes >= 1000000 ? String.format("%.1f MB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1000000.0d)}, 1)) : String.format("%.0f KB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1000.0d)}, 1));
    }

    private final String friendlyError(String raw) {
        if (raw == null) {
            return this.context.getString(R.string.telegram_search_failed);
        }
        Integer numR = u.R(o.r0(raw, "FLOOD_WAIT_"));
        return numR != null ? this.context.getString(R.string.telegram_too_many_searches, numR) : this.context.getString(R.string.telegram_error_raw, raw);
    }

    private final String parseQuality(String raw) {
        String strReplace = raw.toLowerCase(Locale.ROOT).replace(' ', '.');
        return parseQuality$has(strReplace, "dvdscr", "screener", ".scr.") ? "SCR" : parseQuality$has(strReplace, ".cam.", "camrip", "hdcam", "hdts", "telesync") ? "CAM" : parseQuality$has(strReplace, "360", "36o") ? "360p" : parseQuality$has(strReplace, "480", "48o") ? "480p" : parseQuality$has(strReplace, "720", "72o") ? "720p" : parseQuality$has(strReplace, "1080", "1o8o", "108o", "1o80", ".fhd.") ? "1080p" : parseQuality$has(strReplace, "2160", "216o", ".4k.", ".uhd.", "ultrahd") ? "4K" : "Unknown";
    }

    private static final boolean parseQuality$has(String str, String... strArr) {
        for (String str2 : strArr) {
            if (o.T(str, str2, false)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int qualityTier(String quality) {
        switch (quality.hashCode()) {
            case 1687:
                return !quality.equals("4K") ? 0 : 6;
            case 66479:
                return !quality.equals("CAM") ? 0 : 1;
            case 81922:
                return !quality.equals("SCR") ? 0 : 1;
            case 1572835:
                return !quality.equals("360p") ? 0 : 2;
            case 1604548:
                return !quality.equals("480p") ? 0 : 3;
            case 1688155:
                return !quality.equals("720p") ? 0 : 4;
            case 46737913:
                return !quality.equals("1080p") ? 0 : 5;
            default:
                return 0;
        }
    }

    public static /* synthetic */ Object resolve$default(TelegramSourceResolver telegramSourceResolver, String str, Integer num, Integer num2, Integer num3, String str2, boolean z, d7.d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            num2 = null;
        }
        if ((i10 & 8) != 0) {
            num3 = null;
        }
        if ((i10 & 16) != 0) {
            str2 = "";
        }
        if ((i10 & 32) != 0) {
            z = true;
        }
        return telegramSourceResolver.resolve(str, num, num2, num3, str2, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveInternal(java.lang.String r34, java.lang.Integer r35, java.lang.Integer r36, java.lang.Integer r37, java.lang.String r38, boolean r39, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r40) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramSourceResolver.resolveInternal(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, boolean, d7.d):java.lang.Object");
    }

    private final void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(new androidx.activity.s(this, message, 15));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showToast$lambda$0(TelegramSourceResolver telegramSourceResolver, String str) {
        Toast.makeText(telegramSourceResolver.context, str, 1).show();
    }

    public final boolean isEnabled() {
        return this.repository.isAuthenticated();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolve(java.lang.String r18, java.lang.Integer r19, java.lang.Integer r20, java.lang.Integer r21, java.lang.String r22, boolean r23, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramSourceResolver.resolve(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, boolean, d7.d):java.lang.Object");
    }
}
