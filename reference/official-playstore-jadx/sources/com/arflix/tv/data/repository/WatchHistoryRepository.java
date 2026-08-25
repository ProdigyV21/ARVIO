package com.arflix.tv.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchHistoryRecord;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.util.AppLogger;
import io.ktor.http.LinkHeader;
import j$.time.Instant;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B5\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0014\u0010\u000fJ#\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019JB\u0010\u001f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u001b\u001a\u00020\r2\"\u0010\u001e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cH\u0082@¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\rH\u0082@¢\u0006\u0004\b!\u0010\"J\u0019\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b%\u0010&J\u0017\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020\u0016H\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020$2\u0006\u0010#\u001a\u00020$H\u0002¢\u0006\u0004\b+\u0010,J \u0001\u0010@\u001a\u00020?2\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u00010\r2\b\u00103\u001a\u0004\u0018\u00010\r2\b\u00104\u001a\u0004\u0018\u00010/2\b\u00105\u001a\u0004\u0018\u00010/2\b\u00106\u001a\u0004\u0018\u00010\r2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020$2\u0006\u0010:\u001a\u00020$2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010>\u001a\u00020$H\u0086@¢\u0006\u0004\b@\u0010AJ\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@¢\u0006\u0004\bB\u0010\"J\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@¢\u0006\u0004\bC\u0010\"J6\u0010D\u001a\u0004\u0018\u00010\u00162\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u00010/2\b\u00105\u001a\u0004\u0018\u00010/H\u0086@¢\u0006\u0004\bD\u0010EJ\"\u0010F\u001a\u0004\u0018\u00010\u00162\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/H\u0086@¢\u0006\u0004\bF\u0010GJ,\u0010H\u001a\u00020?2\u0006\u00100\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u00010/2\b\u00105\u001a\u0004\u0018\u00010/H\u0086@¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020?H\u0086@¢\u0006\u0004\bJ\u0010\"J\r\u0010K\u001a\u00020?¢\u0006\u0004\bK\u0010LR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010MR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010NR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010OR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010MR\u001c\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010QR&\u0010S\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR&\u0010U\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010TR\u001c\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010Q¨\u0006W"}, d2 = {"Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "", "Ljavax/inject/Provider;", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepositoryProvider", "Lcom/arflix/tv/data/api/SupabaseApi;", "supabaseApi", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/RealtimeSyncManager;", "realtimeSyncManagerProvider", "<init>", "(Ljavax/inject/Provider;Lcom/arflix/tv/data/api/SupabaseApi;Lcom/arflix/tv/data/repository/ProfileManager;Ljavax/inject/Provider;)V", "", "currentProfileId", "()Ljava/lang/String;", "currentProfileQuery", TtmlNode.RUBY_BASE, "profileHistorySource", "(Ljava/lang/String;)Ljava/lang/String;", "profileHistorySourceFilter", "", "Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "entries", "filterByProfile", "(Ljava/util/List;)Ljava/util/List;", "T", "operation", "Lkotlin/Function2;", "Ld7/d;", "block", "executeSupabaseCall", "(Ljava/lang/String;Lr7/p;Ld7/d;)Ljava/lang/Object;", "getSupabaseAuth", "(Ld7/d;)Ljava/lang/Object;", "value", "", "parseEpoch", "(Ljava/lang/String;)J", "entry", "", "isEntryInProgress", "(Lcom/arflix/tv/data/repository/WatchHistoryEntry;)Z", "normalizeStoredSeconds", "(J)J", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", LinkHeader.Parameters.Title, "poster", "backdrop", "season", "episode", "episodeTitle", "", "progress", "duration", "position", "streamKey", "streamAddonId", "streamTitle", "sessionStartTime", "Lx6/t0;", "saveProgress", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLd7/d;)Ljava/lang/Object;", "getWatchHistory", "getContinueWatching", "getProgress", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "getLatestProgress", "(Lcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "removeFromHistory", "(ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "clearHistory", "clearProfileCaches", "()V", "Ljavax/inject/Provider;", "Lcom/arflix/tv/data/api/SupabaseApi;", "Lcom/arflix/tv/data/repository/ProfileManager;", "cachedContinueWatching", "Ljava/util/List;", "j$/util/concurrent/ConcurrentHashMap", "cachedContinueWatchingByProfile", "Lj$/util/concurrent/ConcurrentHashMap;", "cachedWatchHistoryByProfile", "cachedWatchHistory", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WatchHistoryRepository {
    public static final int $stable = 8;
    private final Provider<AuthRepository> authRepositoryProvider;
    private volatile List<WatchHistoryEntry> cachedContinueWatching;
    private final ConcurrentHashMap<String, List<WatchHistoryEntry>> cachedContinueWatchingByProfile;
    private volatile List<WatchHistoryEntry> cachedWatchHistory;
    private final ConcurrentHashMap<String, List<WatchHistoryEntry>> cachedWatchHistoryByProfile;
    private final ProfileManager profileManager;
    private final Provider<RealtimeSyncManager> realtimeSyncManagerProvider;
    private final SupabaseApi supabaseApi;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {488}, m = "clearHistory", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.clearHistory(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$2", f = "WatchHistoryRepository.kt", l = {489}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
        final /* synthetic */ String $userId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$userId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = WatchHistoryRepository.this.new AnonymousClass2(this.$userId, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SupabaseApi supabaseApi = WatchHistoryRepository.this.supabaseApi;
                String strC = androidx.compose.material3.d.C("eq.", this.$userId);
                String strCurrentProfileQuery = WatchHistoryRepository.this.currentProfileQuery();
                String strProfileHistorySourceFilter = WatchHistoryRepository.this.profileHistorySourceFilter();
                this.L$0 = null;
                this.label = 1;
                Object objD = com.arflix.tv.data.api.f.d(supabaseApi, str, null, strC, strCurrentProfileQuery, null, null, null, null, strProfileHistorySourceFilter, this, 242, null);
                e7.a aVar = e7.a.f15033i;
                if (objD == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$executeSupabaseCall$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {515, 517, 521, 523}, m = "executeSupabaseCall", v = 2)
    public static final class C12171<T> extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C12171(d7.d<? super C12171> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.executeSupabaseCall(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$getContinueWatching$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {325}, m = "getContinueWatching", v = 2)
    public static final class C12181 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12181(d7.d<? super C12181> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.getContinueWatching(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$getLatestProgress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {407}, m = "getLatestProgress", v = 2)
    public static final class C12191 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12191(d7.d<? super C12191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.getLatestProgress(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$getProgress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {368}, m = "getProgress", v = 2)
    public static final class C12201 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C12201(d7.d<? super C12201> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.getProgress(null, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$getSupabaseAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {532, 534}, m = "getSupabaseAuth", v = 2)
    public static final class C12211 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12211(d7.d<? super C12211> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.getSupabaseAuth(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$getWatchHistory$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {277}, m = "getWatchHistory", v = 2)
    public static final class C12221 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12221(d7.d<? super C12221> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.getWatchHistory(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$removeFromHistory$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {457}, m = "removeFromHistory", v = 2)
    public static final class C12231 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C12231(d7.d<? super C12231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.removeFromHistory(0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$removeFromHistory$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository$removeFromHistory$6", f = "WatchHistoryRepository.kt", l = {458}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass6 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        final /* synthetic */ String $userId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(String str, int i10, Integer num, Integer num2, d7.d<? super AnonymousClass6> dVar) {
            super(2, dVar);
            this.$userId = str;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass6 anonymousClass6 = WatchHistoryRepository.this.new AnonymousClass6(this.$userId, this.$tmdbId, this.$season, this.$episode, dVar);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SupabaseApi supabaseApi = WatchHistoryRepository.this.supabaseApi;
                String strC = androidx.compose.material3.d.C("eq.", this.$userId);
                String strI = a0.c.i(this.$tmdbId, "eq.");
                String strCurrentProfileQuery = WatchHistoryRepository.this.currentProfileQuery();
                String strProfileHistorySourceFilter = WatchHistoryRepository.this.profileHistorySourceFilter();
                Integer num = this.$season;
                String strI2 = num != null ? a0.c.i(num.intValue(), "eq.") : null;
                Integer num2 = this.$episode;
                String strI3 = num2 != null ? a0.c.i(num2.intValue(), "eq.") : null;
                this.L$0 = null;
                this.label = 1;
                Object objD = com.arflix.tv.data.api.f.d(supabaseApi, str, null, strC, strCurrentProfileQuery, strI, null, strI2, strI3, strProfileHistorySourceFilter, this, 34, null);
                e7.a aVar = e7.a.f15033i;
                if (objD == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<? super t0> dVar) {
            return ((AnonymousClass6) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$saveProgress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository", f = "WatchHistoryRepository.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD, 209, 216}, m = "saveProgress", v = 2)
    public static final class C12241 extends f7.c {
        float F$0;
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
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

        public C12241(d7.d<? super C12241> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchHistoryRepository.this.saveProgress(null, 0, null, null, null, null, null, null, 0.0f, 0L, 0L, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$saveProgress$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository$saveProgress$5", f = "WatchHistoryRepository.kt", l = {210}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass5 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchHistoryEntry $entry;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(WatchHistoryEntry watchHistoryEntry, d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
            this.$entry = watchHistoryEntry;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass5 anonymousClass5 = WatchHistoryRepository.this.new AnonymousClass5(this.$entry, dVar);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SupabaseApi supabaseApi = WatchHistoryRepository.this.supabaseApi;
                WatchHistoryRecord record = WatchHistoryRepositoryKt.toRecord(this.$entry);
                this.L$0 = null;
                this.label = 1;
                Object objX = com.arflix.tv.data.api.f.x(supabaseApi, str, null, null, record, this, 6, null);
                e7.a aVar = e7.a.f15033i;
                if (objX == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchHistoryRepository$saveProgress$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository$saveProgress$6", f = "WatchHistoryRepository.kt", l = {217}, m = "invokeSuspend", v = 2)
    public static final class C12256 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchHistoryEntry $fallback;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12256(WatchHistoryEntry watchHistoryEntry, d7.d<? super C12256> dVar) {
            super(2, dVar);
            this.$fallback = watchHistoryEntry;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C12256 c12256 = WatchHistoryRepository.this.new C12256(this.$fallback, dVar);
            c12256.L$0 = obj;
            return c12256;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SupabaseApi supabaseApi = WatchHistoryRepository.this.supabaseApi;
                WatchHistoryRecord record = WatchHistoryRepositoryKt.toRecord(this.$fallback);
                this.L$0 = null;
                this.label = 1;
                Object objX = com.arflix.tv.data.api.f.x(supabaseApi, str, null, null, record, this, 6, null);
                e7.a aVar = e7.a.f15033i;
                if (objX == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<? super t0> dVar) {
            return ((C12256) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public WatchHistoryRepository(Provider<AuthRepository> provider, SupabaseApi supabaseApi, ProfileManager profileManager, Provider<RealtimeSyncManager> provider2) {
        this.authRepositoryProvider = provider;
        this.supabaseApi = supabaseApi;
        this.profileManager = profileManager;
        this.realtimeSyncManagerProvider = provider2;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        this.cachedContinueWatching = zVar;
        this.cachedContinueWatchingByProfile = new ConcurrentHashMap<>();
        this.cachedWatchHistoryByProfile = new ConcurrentHashMap<>();
        this.cachedWatchHistory = zVar;
    }

    private final String currentProfileId() {
        String profileIdSync = this.profileManager.getProfileIdSync();
        return kotlin.text.o.h0(profileIdSync) ? "default" : profileIdSync;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String currentProfileQuery() {
        String strCurrentProfileId = currentProfileId();
        if (this.profileManager.isDefaultProfile()) {
            return null;
        }
        return androidx.compose.material3.d.C("eq.", strCurrentProfileId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object executeSupabaseCall(java.lang.String r8, r7.p<? super java.lang.String, ? super d7.d<? super T>, ? extends java.lang.Object> r9, d7.d<? super T> r10) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.executeSupabaseCall(java.lang.String, r7.p, d7.d):java.lang.Object");
    }

    private final List<WatchHistoryEntry> filterByProfile(List<WatchHistoryEntry> entries) {
        boolean zA;
        String strCurrentProfileId = currentProfileId();
        String profileNameSync = this.profileManager.getProfileNameSync();
        String strL = a0.c.l("profile:", strCurrentProfileId, ":");
        String strL2 = a0.c.l("profile:", profileNameSync, ":");
        boolean zIsDefaultProfile = this.profileManager.isDefaultProfile();
        ArrayList arrayList = new ArrayList();
        for (Object obj : entries) {
            WatchHistoryEntry watchHistoryEntry = (WatchHistoryEntry) obj;
            String profile_id = watchHistoryEntry.getProfile_id();
            if (profile_id == null || kotlin.text.o.h0(profile_id)) {
                String source = watchHistoryEntry.getSource();
                zA = (source == null || !kotlin.text.u.P(source, "profile:", false)) ? zIsDefaultProfile : kotlin.text.u.P(source, strL, false) || kotlin.text.u.P(source, strL2, false);
            } else {
                zA = kotlin.jvm.internal.p.a(watchHistoryEntry.getProfile_id(), strCurrentProfileId);
            }
            if (zA) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private static final List<WatchHistoryEntry> getContinueWatching$filterLive(List<WatchHistoryEntry> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            WatchHistoryEntry watchHistoryEntry = (WatchHistoryEntry) obj;
            if (!SportsAddonCapabilities.isLiveStreamOrSportsItem$default(SportsAddonCapabilities.INSTANCE, kotlin.jvm.internal.p.a(watchHistoryEntry.getMedia_type(), "tv") ? MediaType.TV : MediaType.MOVIE, Integer.valueOf(watchHistoryEntry.getShow_tmdb_id()), null, watchHistoryEntry.getStream_addon_id(), watchHistoryEntry.getTitle(), false, null, 100, null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
    
        if (r8 == r6) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSupabaseAuth(d7.d<? super java.lang.String> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.WatchHistoryRepository.C12211
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.WatchHistoryRepository$getSupabaseAuth$1 r0 = (com.arflix.tv.data.repository.WatchHistoryRepository.C12211) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.WatchHistoryRepository$getSupabaseAuth$1 r0 = new com.arflix.tv.data.repository.WatchHistoryRepository$getSupabaseAuth$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            java.lang.String r2 = "Bearer "
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L45
            if (r1 == r4) goto L3d
            if (r1 != r3) goto L35
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            com.arflix.tv.data.repository.AuthRepository r0 = (com.arflix.tv.data.repository.AuthRepository) r0
            k2.c.G(r8)
            goto L79
        L35:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L3d:
            java.lang.Object r1 = r0.L$0
            com.arflix.tv.data.repository.AuthRepository r1 = (com.arflix.tv.data.repository.AuthRepository) r1
            k2.c.G(r8)
            goto L5c
        L45:
            k2.c.G(r8)
            javax.inject.Provider<com.arflix.tv.data.repository.AuthRepository> r8 = r7.authRepositoryProvider
            java.lang.Object r8 = r8.get()
            r1 = r8
            com.arflix.tv.data.repository.AuthRepository r1 = (com.arflix.tv.data.repository.AuthRepository) r1
            r0.L$0 = r1
            r0.label = r4
            java.lang.Object r8 = r1.getAccessToken(r0)
            if (r8 != r6) goto L5c
            goto L78
        L5c:
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L6c
            boolean r4 = kotlin.text.o.h0(r8)
            if (r4 == 0) goto L67
            goto L6c
        L67:
            java.lang.String r8 = androidx.compose.material3.d.C(r2, r8)
            return r8
        L6c:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r8 = r1.refreshAccessToken(r0)
            if (r8 != r6) goto L79
        L78:
            return r6
        L79:
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L82
            java.lang.String r8 = r2.concat(r8)
            return r8
        L82:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.getSupabaseAuth(d7.d):java.lang.Object");
    }

    private final boolean isEntryInProgress(WatchHistoryEntry entry) {
        float fM = qb.d.m(entry.getProgress(), 0.0f, 1.0f);
        long jNormalizeStoredSeconds = normalizeStoredSeconds(entry.getDuration_seconds());
        long jNormalizeStoredSeconds2 = normalizeStoredSeconds(entry.getPosition_seconds());
        if (fM <= 0.0f) {
            fM = (jNormalizeStoredSeconds <= 0 || jNormalizeStoredSeconds2 <= 0) ? 0.0f : qb.d.m(jNormalizeStoredSeconds2 / jNormalizeStoredSeconds, 0.0f, 1.0f);
        }
        return fM > 0.0f ? fM < 0.9f : jNormalizeStoredSeconds2 > 0;
    }

    private final long normalizeStoredSeconds(long value) {
        return value > 86400 ? value / 1000 : value;
    }

    private final long parseEpoch(String value) {
        if (value != null && !kotlin.text.o.h0(value)) {
            try {
                return Instant.parse(value).toEpochMilli();
            } catch (Exception e5) {
                AppLogger.INSTANCE.e("WatchHistoryRepository", "Error parsing date, fallback 0L", e5);
            }
        }
        return 0L;
    }

    private final String profileHistorySource(String base) {
        return androidx.compose.material3.d.n("profile:", currentProfileId(), ":", base);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String profileHistorySourceFilter() {
        return a0.c.l("like.profile:", currentProfileId(), ":*");
    }

    private static final boolean removeFromHistory$keepEntry(int i10, Integer num, Integer num2, WatchHistoryEntry watchHistoryEntry) {
        if (watchHistoryEntry.getShow_tmdb_id() != i10) {
            return true;
        }
        if (num != null) {
            Integer season = watchHistoryEntry.getSeason();
            int iIntValue = num.intValue();
            if (season == null || season.intValue() != iIntValue) {
                return true;
            }
        }
        if (num2 == null) {
            return false;
        }
        Integer episode = watchHistoryEntry.getEpisode();
        return episode == null || episode.intValue() != num2.intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object clearHistory(d7.d<? super x6.t0> r8) {
        /*
            r7 = this;
            x6.t0 r0 = x6.t0.f22605a
            boolean r1 = r8 instanceof com.arflix.tv.data.repository.WatchHistoryRepository.AnonymousClass1
            if (r1 == 0) goto L15
            r1 = r8
            com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$1 r1 = (com.arflix.tv.data.repository.WatchHistoryRepository.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$1 r1 = new com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$1
            r1.<init>(r8)
        L1a:
            java.lang.Object r8 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L37
            if (r3 != r4) goto L2f
            java.lang.Object r1 = r1.L$0
            java.lang.String r1 = (java.lang.String) r1
            k2.c.G(r8)     // Catch: java.lang.Exception -> L2d
            goto L82
        L2d:
            r8 = move-exception
            goto L79
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L37:
            k2.c.G(r8)
            com.arflix.tv.util.Constants r8 = com.arflix.tv.util.Constants.INSTANCE
            boolean r8 = r8.getUSE_NETLIFY_CLOUD_SYNC()
            if (r8 == 0) goto L57
            java.lang.String r8 = r7.currentProfileId()
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<com.arflix.tv.data.repository.WatchHistoryEntry>> r1 = r7.cachedWatchHistoryByProfile
            r1.remove(r8)
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<com.arflix.tv.data.repository.WatchHistoryEntry>> r1 = r7.cachedContinueWatchingByProfile
            r1.remove(r8)
            kotlin.collections.z r8 = kotlin.collections.z.f19728i
            r7.cachedWatchHistory = r8
            r7.cachedContinueWatching = r8
            return r0
        L57:
            javax.inject.Provider<com.arflix.tv.data.repository.AuthRepository> r8 = r7.authRepositoryProvider
            java.lang.Object r8 = r8.get()
            com.arflix.tv.data.repository.AuthRepository r8 = (com.arflix.tv.data.repository.AuthRepository) r8
            java.lang.String r8 = r8.getCurrentUserId()
            if (r8 != 0) goto L66
            return r0
        L66:
            java.lang.String r3 = "clear watch history"
            com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$2 r5 = new com.arflix.tv.data.repository.WatchHistoryRepository$clearHistory$2     // Catch: java.lang.Exception -> L2d
            r6 = 0
            r5.<init>(r8, r6)     // Catch: java.lang.Exception -> L2d
            r1.L$0 = r6     // Catch: java.lang.Exception -> L2d
            r1.label = r4     // Catch: java.lang.Exception -> L2d
            java.lang.Object r8 = r7.executeSupabaseCall(r3, r5, r1)     // Catch: java.lang.Exception -> L2d
            if (r8 != r2) goto L82
            return r2
        L79:
            com.arflix.tv.util.AppLogger r1 = com.arflix.tv.util.AppLogger.INSTANCE
            java.lang.String r2 = "WatchHistoryRepository"
            java.lang.String r3 = "Silently handled error"
            r1.e(r2, r3, r8)
        L82:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.clearHistory(d7.d):java.lang.Object");
    }

    public final void clearProfileCaches() {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        this.cachedContinueWatching = zVar;
        this.cachedWatchHistory = zVar;
        this.cachedContinueWatchingByProfile.clear();
        this.cachedWatchHistoryByProfile.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getContinueWatching(d7.d<? super java.util.List<com.arflix.tv.data.repository.WatchHistoryEntry>> r10) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.getContinueWatching(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLatestProgress(com.arflix.tv.data.model.MediaType r12, int r13, d7.d<? super com.arflix.tv.data.repository.WatchHistoryEntry> r14) {
        /*
            Method dump skipped, instruction units count: 495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.getLatestProgress(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getProgress(com.arflix.tv.data.model.MediaType r13, int r14, java.lang.Integer r15, java.lang.Integer r16, d7.d<? super com.arflix.tv.data.repository.WatchHistoryEntry> r17) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.getProgress(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWatchHistory(d7.d<? super java.util.List<com.arflix.tv.data.repository.WatchHistoryEntry>> r10) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.getWatchHistory(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeFromHistory(int r15, java.lang.Integer r16, java.lang.Integer r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.removeFromHistory(int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:230:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveProgress(com.arflix.tv.data.model.MediaType r74, int r75, java.lang.String r76, java.lang.String r77, java.lang.String r78, java.lang.Integer r79, java.lang.Integer r80, java.lang.String r81, float r82, long r83, long r85, java.lang.String r87, java.lang.String r88, java.lang.String r89, long r90, d7.d<? super x6.t0> r92) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 1545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchHistoryRepository.saveProgress(com.arflix.tv.data.model.MediaType, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, float, long, long, java.lang.String, java.lang.String, java.lang.String, long, d7.d):java.lang.Object");
    }
}
