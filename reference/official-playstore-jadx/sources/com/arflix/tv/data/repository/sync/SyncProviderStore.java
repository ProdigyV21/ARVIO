package com.arflix.tv.data.repository.sync;

import android.content.Context;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.work.impl.t;
import c1.b;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.util.DataStoresKt;
import com.arflix.tv.util.SecureStorage;
import com.google.android.gms.cast.MediaError;
import d7.d;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.c;
import f7.e;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.text.o;
import na.j;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 S2\u00020\u0001:\u0002STB\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u0086@¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0086@¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b\u0010\u0010\nJ \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J \u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\u0019\u0010\u001aJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001bH\u0086@¢\u0006\u0004\b\u001e\u0010\nJ\u0018\u0010\u001f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u001f\u0010\u000eJ\u0018\u0010 \u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0086@¢\u0006\u0004\b \u0010\u000eJ\u0012\u0010\"\u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0004\b\"\u0010\nJ\u001a\u0010$\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0004\b$\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0004\b&\u0010\nJ\u001a\u0010(\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0004\b(\u0010%J*\u0010-\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020,0+2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020!0)H\u0086@¢\u0006\u0004\b-\u0010.J$\u00100\u001a\u00020\f2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020,0+H\u0086@¢\u0006\u0004\b0\u00101J\u0015\u00103\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b3\u00104J\u001d\u00106\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\b6\u00107J\u0015\u00108\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b8\u00104J\u001d\u00109\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\b9\u00107J\u0015\u0010:\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b:\u00104J\u0015\u0010;\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b;\u00104J\u0015\u0010<\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b<\u00104J\u0015\u0010=\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\b=\u00104J\u0015\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001702H\u0002¢\u0006\u0004\b>\u00104J\u0015\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001702H\u0002¢\u0006\u0004\b?\u00104J\u001d\u0010@\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\b@\u00107J\u001d\u0010A\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\bA\u00107J\u001d\u0010B\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\bB\u00107J\u001d\u0010C\u001a\b\u0012\u0004\u0012\u00020\u0017022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\bC\u00107J\u001d\u0010D\u001a\b\u0012\u0004\u0012\u00020\u0017022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\bD\u00107J/\u0010H\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u0017H\u0002¢\u0006\u0004\bH\u0010IJ\u0015\u0010J\u001a\b\u0012\u0004\u0012\u00020!02H\u0002¢\u0006\u0004\bJ\u00104J\u001d\u0010K\u001a\b\u0012\u0004\u0012\u00020!022\u0006\u00105\u001a\u00020!H\u0002¢\u0006\u0004\bK\u00107R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010LR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010MR\u001d\u0010O\u001a\b\u0012\u0004\u0012\u00020\b0N8\u0006¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R¨\u0006U"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;)V", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getProvider", "(Ld7/d;)Ljava/lang/Object;", "provider", "Lx6/t0;", "setProvider", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/sync/TrackingPreferences;", "getTrackingPreferences", "Lcom/arflix/tv/data/repository/sync/TrackingFeature;", "feature", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "mode", "setReadMode", "(Lcom/arflix/tv/data/repository/sync/TrackingFeature;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Ld7/d;)Ljava/lang/Object;", "", "enabled", "setWriteTarget", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;ZLd7/d;)Ljava/lang/Object;", "", "readProviders", "(Lcom/arflix/tv/data/repository/sync/TrackingFeature;Ld7/d;)Ljava/lang/Object;", "writeProviders", "onProviderConnected", "onProviderDisconnected", "", "getSimklAccessToken", "token", "setSimklAccessToken", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getMdbListApiKey", "apiKey", "setMdbListApiKey", "", "profileIds", "", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore$ProfileSyncSelection;", "exportForProfiles", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "values", "importForProfiles", "(Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "Lc1/e;", "providerKey", "()Lc1/e;", "profileId", "providerKeyFor", "(Ljava/lang/String;)Lc1/e;", "mdbListKey", "mdbListKeyFor", "traktAccessTokenKey", "watchlistReadModeKey", "continueWatchingReadModeKey", "watchedReadModeKey", "writeToTraktKey", "writeToSimklKey", "watchlistReadModeKeyFor", "continueWatchingReadModeKeyFor", "watchedReadModeKeyFor", "writeToTraktKeyFor", "writeToSimklKeyFor", "hasTrakt", "hasSimkl", "hasMdbList", "defaultReadMode", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;ZZZ)Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "simklAccessTokenKey", "simklAccessTokenKeyFor", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lna/j;", "providerFlow", "Lna/j;", "getProviderFlow", "()Lna/j;", "Companion", "ProfileSyncSelection", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncProviderStore {

    @Deprecated
    public static final String SIMKL_TOKEN_ALIAS = "arvio_simkl_access_token";
    private final Context context;
    private final ProfileManager profileManager;
    private final j<SyncProvider> providerFlow;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SyncProviderStore$Companion;", "", "<init>", "()V", "SIMKL_TOKEN_ALIAS", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[TrackingFeature.values().length];
            try {
                iArr[TrackingFeature.WATCHLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TrackingFeature.CONTINUE_WATCHING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TrackingFeature.WATCHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TrackingReadMode.values().length];
            try {
                iArr2[TrackingReadMode.TRAKT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[TrackingReadMode.SIMKL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[TrackingReadMode.BOTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[TrackingReadMode.MDBLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[TrackingReadMode.AUTO.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[SyncProvider.values().length];
            try {
                iArr3[SyncProvider.TRAKT.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[SyncProvider.SIMKL.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[SyncProvider.MDBLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr3[SyncProvider.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$exportForProfiles$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {261, 262}, m = "exportForProfiles", v = 2)
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.exportForProfiles(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$getMdbListApiKey$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {243}, m = "getMdbListApiKey", v = 2)
    public static final class C12791 extends c {
        int label;
        /* synthetic */ Object result;

        public C12791(d<? super C12791> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.getMdbListApiKey(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$getProvider$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {60}, m = "getProvider", v = 2)
    public static final class C12801 extends c {
        int label;
        /* synthetic */ Object result;

        public C12801(d<? super C12801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.getProvider(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {218, 224}, m = "getSimklAccessToken", v = 2)
    public static final class C12811 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12811(d<? super C12811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.getSimklAccessToken(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "current", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $token;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$token = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = SyncProviderStore.this.new AnonymousClass2(this.$token, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(SyncProviderStore.this.simklAccessTokenKey(), SecureStorage.INSTANCE.encrypt(this.$token, SyncProviderStore.SIMKL_TOKEN_ALIAS));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$getTrackingPreferences$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {95, 96}, m = "getTrackingPreferences", v = 2)
    public static final class C12821 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12821(d<? super C12821> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.getTrackingPreferences(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {297, MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD}, m = "importForProfiles", v = 2)
    public static final class C12831 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12831(d<? super C12831> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.importForProfiles(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12842 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ Map<String, ProfileSyncSelection> $values;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12842(Map<String, ProfileSyncSelection> map, SyncProviderStore syncProviderStore, d<? super C12842> dVar) {
            super(2, dVar);
            this.$values = map;
            this.this$0 = syncProviderStore;
        }

        private static final void invokeSuspend$lambda$0$storeMode(b bVar, c1.e eVar, TrackingReadMode trackingReadMode) {
            if (trackingReadMode == TrackingReadMode.AUTO) {
                bVar.c(eVar);
            } else {
                bVar.d(eVar, trackingReadMode.toStorage());
            }
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12842 c12842 = new C12842(this.$values, this.this$0, dVar);
            c12842.L$0 = obj;
            return c12842;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12842) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Map<String, ProfileSyncSelection> map = this.$values;
            SyncProviderStore syncProviderStore = this.this$0;
            for (Map.Entry<String, ProfileSyncSelection> entry : map.entrySet()) {
                String key = entry.getKey();
                ProfileSyncSelection value = entry.getValue();
                if (value.getProvider() == SyncProvider.NONE) {
                    bVar.c(syncProviderStore.providerKeyFor(key));
                } else {
                    bVar.d(syncProviderStore.providerKeyFor(key), value.getProvider().toStorage());
                }
                c1.e eVarWatchlistReadModeKeyFor = syncProviderStore.watchlistReadModeKeyFor(key);
                TrackingReadMode watchlistReadMode = value.getWatchlistReadMode();
                if (watchlistReadMode == null) {
                    watchlistReadMode = TrackingReadMode.AUTO;
                }
                invokeSuspend$lambda$0$storeMode(bVar, eVarWatchlistReadModeKeyFor, watchlistReadMode);
                c1.e eVarContinueWatchingReadModeKeyFor = syncProviderStore.continueWatchingReadModeKeyFor(key);
                TrackingReadMode continueWatchingReadMode = value.getContinueWatchingReadMode();
                if (continueWatchingReadMode == null) {
                    continueWatchingReadMode = TrackingReadMode.AUTO;
                }
                invokeSuspend$lambda$0$storeMode(bVar, eVarContinueWatchingReadModeKeyFor, continueWatchingReadMode);
                c1.e eVarWatchedReadModeKeyFor = syncProviderStore.watchedReadModeKeyFor(key);
                TrackingReadMode watchedReadMode = value.getWatchedReadMode();
                if (watchedReadMode == null) {
                    watchedReadMode = TrackingReadMode.AUTO;
                }
                invokeSuspend$lambda$0$storeMode(bVar, eVarWatchedReadModeKeyFor, watchedReadMode);
                Boolean writeToTrakt = value.getWriteToTrakt();
                if (writeToTrakt != null) {
                    bVar.d(syncProviderStore.writeToTraktKeyFor(key), writeToTrakt);
                }
                Boolean writeToSimkl = value.getWriteToSimkl();
                if (writeToSimkl != null) {
                    bVar.d(syncProviderStore.writeToSimklKeyFor(key), writeToSimkl);
                }
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$3", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ Map<String, ProfileSyncSelection> $values;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Map<String, ProfileSyncSelection> map, SyncProviderStore syncProviderStore, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$values = map;
            this.this$0 = syncProviderStore;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$values, this.this$0, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((AnonymousClass3) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Map<String, ProfileSyncSelection> map = this.$values;
            SyncProviderStore syncProviderStore = this.this$0;
            for (Map.Entry<String, ProfileSyncSelection> entry : map.entrySet()) {
                String key = entry.getKey();
                ProfileSyncSelection value = entry.getValue();
                String mdbListApiKey = value.getMdbListApiKey();
                String string = mdbListApiKey != null ? o.L0(mdbListApiKey).toString() : null;
                if (string == null) {
                    string = "";
                }
                if (string.length() == 0) {
                    bVar.c(syncProviderStore.mdbListKeyFor(key));
                } else {
                    bVar.d(syncProviderStore.mdbListKeyFor(key), string);
                }
                String simklAccessToken = value.getSimklAccessToken();
                String string2 = simklAccessToken != null ? o.L0(simklAccessToken).toString() : null;
                String str = string2 != null ? string2 : "";
                if (str.length() == 0) {
                    bVar.c(syncProviderStore.simklAccessTokenKeyFor(key));
                } else {
                    bVar.d(syncProviderStore.simklAccessTokenKeyFor(key), SecureStorage.INSTANCE.encrypt(str, SyncProviderStore.SIMKL_TOKEN_ALIAS));
                }
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$onProviderConnected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {165, 166, 176, 178, 179, 182, 183}, m = "onProviderConnected", v = 2)
    public static final class C12851 extends c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C12851(d<? super C12851> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.onProviderConnected(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$onProviderDisconnected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {187, TsExtractor.TS_PACKET_SIZE, 193, 200, 201, 202, 203}, m = "onProviderDisconnected", v = 2)
    public static final class C12861 extends c {
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

        public C12861(d<? super C12861> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.onProviderDisconnected(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$readProviders$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_UHD}, m = "readProviders", v = 2)
    public static final class C12871 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12871(d<? super C12871> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.readProviders(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setMdbListApiKey$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$setMdbListApiKey$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12882 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $apiKey;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12882(String str, SyncProviderStore syncProviderStore, d<? super C12882> dVar) {
            super(2, dVar);
            this.$apiKey = str;
            this.this$0 = syncProviderStore;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12882 c12882 = new C12882(this.$apiKey, this.this$0, dVar);
            c12882.L$0 = obj;
            return c12882;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12882) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = this.$apiKey;
            String string = str != null ? o.L0(str).toString() : null;
            if (string == null) {
                string = "";
            }
            if (string.length() == 0) {
                bVar.c(this.this$0.mdbListKey());
            } else {
                bVar.d(this.this$0.mdbListKey(), string);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setProvider$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$setProvider$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12892 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ SyncProvider $provider;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12892(SyncProvider syncProvider, SyncProviderStore syncProviderStore, d<? super C12892> dVar) {
            super(2, dVar);
            this.$provider = syncProvider;
            this.this$0 = syncProviderStore;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12892 c12892 = new C12892(this.$provider, this.this$0, dVar);
            c12892.L$0 = obj;
            return c12892;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12892) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$provider == SyncProvider.NONE) {
                bVar.c(this.this$0.providerKey());
            } else {
                bVar.d(this.this$0.providerKey(), this.$provider.toStorage());
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setReadMode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$setReadMode$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12902 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ TrackingFeature $feature;
        final /* synthetic */ TrackingReadMode $mode;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setReadMode$2$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TrackingFeature.values().length];
                try {
                    iArr[TrackingFeature.WATCHLIST.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TrackingFeature.CONTINUE_WATCHING.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[TrackingFeature.WATCHED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12902(TrackingFeature trackingFeature, SyncProviderStore syncProviderStore, TrackingReadMode trackingReadMode, d<? super C12902> dVar) {
            super(2, dVar);
            this.$feature = trackingFeature;
            this.this$0 = syncProviderStore;
            this.$mode = trackingReadMode;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12902 c12902 = new C12902(this.$feature, this.this$0, this.$mode, dVar);
            c12902.L$0 = obj;
            return c12902;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12902) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.e eVarWatchlistReadModeKey;
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.$feature.ordinal()];
            if (i10 == 1) {
                eVarWatchlistReadModeKey = this.this$0.watchlistReadModeKey();
            } else if (i10 == 2) {
                eVarWatchlistReadModeKey = this.this$0.continueWatchingReadModeKey();
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                eVarWatchlistReadModeKey = this.this$0.watchedReadModeKey();
            }
            TrackingReadMode trackingReadMode = this.$mode;
            if (trackingReadMode == TrackingReadMode.AUTO) {
                bVar.c(eVarWatchlistReadModeKey);
            } else {
                bVar.d(eVarWatchlistReadModeKey, trackingReadMode.toStorage());
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setSimklAccessToken$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$setSimklAccessToken$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12912 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $token;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12912(String str, SyncProviderStore syncProviderStore, d<? super C12912> dVar) {
            super(2, dVar);
            this.$token = str;
            this.this$0 = syncProviderStore;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12912 c12912 = new C12912(this.$token, this.this$0, dVar);
            c12912.L$0 = obj;
            return c12912;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12912) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = this.$token;
            String string = str != null ? o.L0(str).toString() : null;
            if (string == null) {
                string = "";
            }
            if (string.length() == 0) {
                bVar.c(this.this$0.simklAccessTokenKey());
            } else {
                bVar.d(this.this$0.simklAccessTokenKey(), SecureStorage.INSTANCE.encrypt(string, SyncProviderStore.SIMKL_TOKEN_ALIAS));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setWriteTarget$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$setWriteTarget$2", f = "SyncProviderStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12922 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ SyncProvider $provider;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SyncProviderStore this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$setWriteTarget$2$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SyncProvider.values().length];
                try {
                    iArr[SyncProvider.TRAKT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[SyncProvider.SIMKL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12922(SyncProvider syncProvider, SyncProviderStore syncProviderStore, boolean z, d<? super C12922> dVar) {
            super(2, dVar);
            this.$provider = syncProvider;
            this.this$0 = syncProviderStore;
            this.$enabled = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12922 c12922 = new C12922(this.$provider, this.this$0, this.$enabled, dVar);
            c12922.L$0 = obj;
            return c12922;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C12922) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.$provider.ordinal()];
            if (i10 == 1) {
                bVar.d(this.this$0.writeToTraktKey(), Boolean.valueOf(this.$enabled));
            } else if (i10 == 2) {
                bVar.d(this.this$0.writeToSimklKey(), Boolean.valueOf(this.$enabled));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$writeProviders$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore", f = "SyncProviderStore.kt", l = {155, 156}, m = "writeProviders", v = 2)
    public static final class C12931 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12931(d<? super C12931> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncProviderStore.this.writeProviders(this);
        }
    }

    @Inject
    public SyncProviderStore(@ApplicationContext Context context, ProfileManager profileManager) {
        this.context = context;
        this.profileManager = profileManager;
        final j data = DataStoresKt.getSettingsDataStore(context).getData();
        this.providerFlow = new j<SyncProvider>() { // from class: com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ SyncProviderStore this$0;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @e(c = "com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2", f = "SyncProviderStore.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(k kVar, SyncProviderStore syncProviderStore) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = syncProviderStore;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r5 = r0.L$3
                        na.k r5 = (na.k) r5
                        java.lang.Object r5 = r0.L$1
                        com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1$2$1 r5 = (com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L69
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        c1.g r5 = (c1.g) r5
                        com.arflix.tv.data.repository.sync.SyncProvider$Companion r1 = com.arflix.tv.data.repository.sync.SyncProvider.INSTANCE
                        com.arflix.tv.data.repository.sync.SyncProviderStore r3 = r4.this$0
                        c1.e r3 = com.arflix.tv.data.repository.sync.SyncProviderStore.access$providerKey(r3)
                        c1.b r5 = (c1.b) r5
                        java.util.LinkedHashMap r5 = r5.f7368a
                        java.lang.Object r5 = r5.get(r3)
                        java.lang.String r5 = (java.lang.String) r5
                        com.arflix.tv.data.repository.sync.SyncProvider r5 = r1.fromStorage(r5)
                        r1 = 0
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L69
                        return r6
                    L69:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d dVar) {
                Object objCollect = data.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e continueWatchingReadModeKey() {
        return this.profileManager.profileStringKey("tracking_continue_read_mode_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e continueWatchingReadModeKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "tracking_continue_read_mode_v2");
    }

    private final TrackingReadMode defaultReadMode(SyncProvider provider, boolean hasTrakt, boolean hasSimkl, boolean hasMdbList) {
        return (provider == SyncProvider.MDBLIST && hasMdbList) ? TrackingReadMode.MDBLIST : (provider == SyncProvider.SIMKL && hasSimkl) ? TrackingReadMode.SIMKL : (provider == SyncProvider.TRAKT && hasTrakt) ? TrackingReadMode.TRAKT : (hasTrakt && hasSimkl) ? TrackingReadMode.BOTH : hasTrakt ? TrackingReadMode.TRAKT : hasSimkl ? TrackingReadMode.SIMKL : hasMdbList ? TrackingReadMode.MDBLIST : TrackingReadMode.AUTO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e mdbListKey() {
        return this.profileManager.profileStringKey("mdblist_api_key");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e mdbListKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "mdblist_api_key");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e providerKey() {
        return this.profileManager.profileStringKey("sync_provider");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e providerKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "sync_provider");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e simklAccessTokenKey() {
        return this.profileManager.profileStringKey("simkl_access_token");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e simklAccessTokenKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "simkl_access_token");
    }

    private final c1.e traktAccessTokenKey() {
        return this.profileManager.profileStringKey("trakt_access_token");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchedReadModeKey() {
        return this.profileManager.profileStringKey("tracking_watched_read_mode_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchedReadModeKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "tracking_watched_read_mode_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchlistReadModeKey() {
        return this.profileManager.profileStringKey("tracking_watchlist_read_mode_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchlistReadModeKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "tracking_watchlist_read_mode_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e writeToSimklKey() {
        return this.profileManager.profileBooleanKey("tracking_write_simkl_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e writeToSimklKeyFor(String profileId) {
        return this.profileManager.profileBooleanKeyFor(profileId, "tracking_write_simkl_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e writeToTraktKey() {
        return this.profileManager.profileBooleanKey("tracking_write_trakt_v2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e writeToTraktKeyFor(String profileId) {
        return this.profileManager.profileBooleanKeyFor(profileId, "tracking_write_trakt_v2");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object exportForProfiles(java.util.List<java.lang.String> r20, d7.d<? super java.util.Map<java.lang.String, com.arflix.tv.data.repository.sync.SyncProviderStore.ProfileSyncSelection>> r21) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.exportForProfiles(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getMdbListApiKey(d7.d<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12791
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.sync.SyncProviderStore$getMdbListApiKey$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12791) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$getMdbListApiKey$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$getMdbListApiKey$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L45
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            android.content.Context r5 = r4.context
            z0.g r5 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r5)
            na.j r5 = r5.getData()
            r0.label = r2
            java.lang.Object r5 = na.y0.l(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L45
            return r0
        L45:
            c1.g r5 = (c1.g) r5
            c1.e r0 = r4.mdbListKey()
            c1.b r5 = (c1.b) r5
            java.util.LinkedHashMap r5 = r5.f7368a
            java.lang.Object r5 = r5.get(r0)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L68
            java.lang.CharSequence r5 = kotlin.text.o.L0(r5)
            java.lang.String r5 = r5.toString()
            if (r5 == 0) goto L68
            int r0 = r5.length()
            if (r0 <= 0) goto L68
            return r5
        L68:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.getMdbListApiKey(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getProvider(d7.d<? super com.arflix.tv.data.repository.sync.SyncProvider> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12801
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.sync.SyncProviderStore$getProvider$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12801) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$getProvider$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$getProvider$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L45
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            android.content.Context r5 = r4.context
            z0.g r5 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r5)
            na.j r5 = r5.getData()
            r0.label = r2
            java.lang.Object r5 = na.y0.l(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L45
            return r0
        L45:
            c1.g r5 = (c1.g) r5
            com.arflix.tv.data.repository.sync.SyncProvider$Companion r0 = com.arflix.tv.data.repository.sync.SyncProvider.INSTANCE
            c1.e r1 = r4.providerKey()
            c1.b r5 = (c1.b) r5
            java.util.LinkedHashMap r5 = r5.f7368a
            java.lang.Object r5 = r5.get(r1)
            java.lang.String r5 = (java.lang.String) r5
            com.arflix.tv.data.repository.sync.SyncProvider r5 = r0.fromStorage(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.getProvider(d7.d):java.lang.Object");
    }

    public final j<SyncProvider> getProviderFlow() {
        return this.providerFlow;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSimklAccessToken(d7.d<? super java.lang.String> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12811
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r1 = r0.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r0 = r0.L$0
            c1.g r0 = (c1.g) r0
            k2.c.G(r8)
            return r1
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L3e:
            k2.c.G(r8)
            goto L58
        L42:
            k2.c.G(r8)
            android.content.Context r8 = r7.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r8)
            na.j r8 = r8.getData()
            r0.label = r3
            java.lang.Object r8 = na.y0.l(r8, r0)
            if (r8 != r4) goto L58
            goto La6
        L58:
            c1.g r8 = (c1.g) r8
            c1.e r1 = r7.simklAccessTokenKey()
            c1.b r8 = (c1.b) r8
            java.util.LinkedHashMap r8 = r8.f7368a
            java.lang.Object r8 = r8.get(r1)
            java.lang.String r8 = (java.lang.String) r8
            com.arflix.tv.util.SecureStorage r1 = com.arflix.tv.util.SecureStorage.INSTANCE
            java.lang.String r3 = "arvio_simkl_access_token"
            java.lang.String r3 = r1.decrypt(r8, r3)
            r5 = 0
            if (r3 == 0) goto L84
            java.lang.CharSequence r3 = kotlin.text.o.L0(r3)
            java.lang.String r3 = r3.toString()
            if (r3 == 0) goto L84
            int r6 = r3.length()
            if (r6 <= 0) goto L84
            goto L85
        L84:
            r3 = r5
        L85:
            if (r3 == 0) goto La7
            boolean r8 = r1.isEncrypted(r8)
            if (r8 != 0) goto La7
            android.content.Context r8 = r7.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r8)
            com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$2 r1 = new com.arflix.tv.data.repository.sync.SyncProviderStore$getSimklAccessToken$2
            r1.<init>(r3, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r3
            r0.label = r2
            java.lang.Object r8 = androidx.work.impl.t.o(r8, r1, r0)
            if (r8 != r4) goto La7
        La6:
            return r4
        La7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.getSimklAccessToken(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTrackingPreferences(d7.d<? super com.arflix.tv.data.repository.sync.TrackingPreferences> r18) {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.getTrackingPreferences(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object importForProfiles(java.util.Map<java.lang.String, com.arflix.tv.data.repository.sync.SyncProviderStore.ProfileSyncSelection> r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12831
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L41
            if (r1 == r4) goto L39
            if (r1 != r3) goto L31
            java.lang.Object r8 = r0.L$0
            java.util.Map r8 = (java.util.Map) r8
            k2.c.G(r9)
            goto L77
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            java.lang.Object r8 = r0.L$0
            java.util.Map r8 = (java.util.Map) r8
            k2.c.G(r9)
            goto L61
        L41:
            k2.c.G(r9)
            boolean r9 = r8.isEmpty()
            if (r9 == 0) goto L4b
            return r2
        L4b:
            android.content.Context r9 = r7.context
            z0.g r9 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r9)
            com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$2 r1 = new com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$2
            r1.<init>(r8, r7, r5)
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r9 = androidx.work.impl.t.o(r9, r1, r0)
            if (r9 != r6) goto L61
            goto L76
        L61:
            android.content.Context r9 = r7.context
            z0.g r9 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r9)
            com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$3 r1 = new com.arflix.tv.data.repository.sync.SyncProviderStore$importForProfiles$3
            r1.<init>(r8, r7, r5)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = androidx.work.impl.t.o(r9, r1, r0)
            if (r8 != r6) goto L77
        L76:
            return r6
        L77:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.importForProfiles(java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c1, code lost:
    
        if (r14 != r6) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x015f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider r13, d7.d<? super x6.t0> r14) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x01cb, code lost:
    
        if (setReadMode(r2, r9, r0) == r7) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0246, code lost:
    
        if (setProvider(r15, r0) == r7) goto L88;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c2 A[PHI: r14 r15
      0x00c2: PHI (r14v4 com.arflix.tv.data.repository.sync.SyncProvider) = (r14v1 com.arflix.tv.data.repository.sync.SyncProvider), (r14v15 com.arflix.tv.data.repository.sync.SyncProvider) binds: [B:24:0x00ed, B:17:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x00c2: PHI (r15v7 java.lang.Object) = (r15v6 java.lang.Object), (r15v1 java.lang.Object) binds: [B:24:0x00ed, B:17:0x00bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0223  */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider r14, d7.d<? super x6.t0> r15) {
        /*
            Method dump skipped, instruction units count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readProviders(com.arflix.tv.data.repository.sync.TrackingFeature r5, d7.d<? super java.util.Set<? extends com.arflix.tv.data.repository.sync.SyncProvider>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12871
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.sync.SyncProviderStore$readProviders$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12871) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$readProviders$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$readProviders$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            com.arflix.tv.data.repository.sync.TrackingFeature r5 = (com.arflix.tv.data.repository.sync.TrackingFeature) r5
            k2.c.G(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.getTrackingPreferences(r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L41
            return r0
        L41:
            com.arflix.tv.data.repository.sync.TrackingPreferences r6 = (com.arflix.tv.data.repository.sync.TrackingPreferences) r6
            int[] r0 = com.arflix.tv.data.repository.sync.SyncProviderStore.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r5 = r0[r5]
            r0 = 3
            r1 = 2
            if (r5 == r2) goto L63
            if (r5 == r1) goto L5e
            if (r5 != r0) goto L58
            com.arflix.tv.data.repository.sync.TrackingReadMode r5 = r6.getWatchedReadMode()
            goto L67
        L58:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
            r5.<init>()
            throw r5
        L5e:
            com.arflix.tv.data.repository.sync.TrackingReadMode r5 = r6.getContinueWatchingReadMode()
            goto L67
        L63:
            com.arflix.tv.data.repository.sync.TrackingReadMode r5 = r6.getWatchlistReadMode()
        L67:
            int[] r6 = com.arflix.tv.data.repository.sync.SyncProviderStore.WhenMappings.$EnumSwitchMapping$1
            int r5 = r5.ordinal()
            r5 = r6[r5]
            if (r5 == r2) goto La2
            if (r5 == r1) goto L9b
            if (r5 == r0) goto L8b
            r6 = 4
            if (r5 == r6) goto L84
            r6 = 5
            if (r5 != r6) goto L7e
            kotlin.collections.b0 r5 = kotlin.collections.b0.f19686i
            return r5
        L7e:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
            r5.<init>()
            throw r5
        L84:
            com.arflix.tv.data.repository.sync.SyncProvider r5 = com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST
            java.util.Set r5 = java.util.Collections.singleton(r5)
            return r5
        L8b:
            com.arflix.tv.data.repository.sync.SyncProvider[] r5 = new com.arflix.tv.data.repository.sync.SyncProvider[r1]
            com.arflix.tv.data.repository.sync.SyncProvider r6 = com.arflix.tv.data.repository.sync.SyncProvider.TRAKT
            r0 = 0
            r5[r0] = r6
            com.arflix.tv.data.repository.sync.SyncProvider r6 = com.arflix.tv.data.repository.sync.SyncProvider.SIMKL
            r5[r2] = r6
            java.util.Set r5 = kotlin.collections.r.p0(r5)
            return r5
        L9b:
            com.arflix.tv.data.repository.sync.SyncProvider r5 = com.arflix.tv.data.repository.sync.SyncProvider.SIMKL
            java.util.Set r5 = java.util.Collections.singleton(r5)
            return r5
        La2:
            com.arflix.tv.data.repository.sync.SyncProvider r5 = com.arflix.tv.data.repository.sync.SyncProvider.TRAKT
            java.util.Set r5 = java.util.Collections.singleton(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.readProviders(com.arflix.tv.data.repository.sync.TrackingFeature, d7.d):java.lang.Object");
    }

    public final Object setMdbListApiKey(String str, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getTraktDataStore(this.context), new C12882(str, this, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setProvider(SyncProvider syncProvider, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getSettingsDataStore(this.context), new C12892(syncProvider, this, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setReadMode(TrackingFeature trackingFeature, TrackingReadMode trackingReadMode, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getSettingsDataStore(this.context), new C12902(trackingFeature, this, trackingReadMode, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setSimklAccessToken(String str, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getTraktDataStore(this.context), new C12912(str, this, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setWriteTarget(SyncProvider syncProvider, boolean z, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getSettingsDataStore(this.context), new C12922(syncProvider, this, z, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeProviders(d7.d<? super java.util.Set<? extends com.arflix.tv.data.repository.sync.SyncProvider>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.sync.SyncProviderStore.C12931
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.sync.SyncProviderStore$writeProviders$1 r0 = (com.arflix.tv.data.repository.sync.SyncProviderStore.C12931) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SyncProviderStore$writeProviders$1 r0 = new com.arflix.tv.data.repository.sync.SyncProviderStore$writeProviders$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            com.arflix.tv.data.repository.sync.TrackingPreferences r0 = (com.arflix.tv.data.repository.sync.TrackingPreferences) r0
            k2.c.G(r7)
            goto L56
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            k2.c.G(r7)
            goto L46
        L3a:
            k2.c.G(r7)
            r0.label = r3
            java.lang.Object r7 = r6.getTrackingPreferences(r0)
            if (r7 != r4) goto L46
            goto L52
        L46:
            com.arflix.tv.data.repository.sync.TrackingPreferences r7 = (com.arflix.tv.data.repository.sync.TrackingPreferences) r7
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r0 = r6.getProvider(r0)
            if (r0 != r4) goto L53
        L52:
            return r4
        L53:
            r5 = r0
            r0 = r7
            r7 = r5
        L56:
            com.arflix.tv.data.repository.sync.SyncProvider r7 = (com.arflix.tv.data.repository.sync.SyncProvider) r7
            z6.i r1 = new z6.i
            r1.<init>()
            java.lang.Boolean r2 = r0.getWriteToTrakt()
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            boolean r2 = kotlin.jvm.internal.p.a(r2, r3)
            if (r2 == 0) goto L6e
            com.arflix.tv.data.repository.sync.SyncProvider r2 = com.arflix.tv.data.repository.sync.SyncProvider.TRAKT
            r1.add(r2)
        L6e:
            java.lang.Boolean r0 = r0.getWriteToSimkl()
            boolean r0 = kotlin.jvm.internal.p.a(r0, r3)
            if (r0 == 0) goto L7d
            com.arflix.tv.data.repository.sync.SyncProvider r0 = com.arflix.tv.data.repository.sync.SyncProvider.SIMKL
            r1.add(r0)
        L7d:
            com.arflix.tv.data.repository.sync.SyncProvider r0 = com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST
            if (r7 != r0) goto L84
            r1.add(r0)
        L84:
            z6.i r7 = ac.b.c(r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SyncProviderStore.writeProviders(d7.d):java.lang.Object");
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010$\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001aJl\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001a¨\u0006,"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SyncProviderStore$ProfileSyncSelection;", "", "provider", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "mdbListApiKey", "", "simklAccessToken", "watchlistReadMode", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "continueWatchingReadMode", "watchedReadMode", "writeToTrakt", "", "writeToSimkl", "<init>", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getProvider", "()Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getMdbListApiKey", "()Ljava/lang/String;", "getSimklAccessToken", "getWatchlistReadMode", "()Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "getContinueWatchingReadMode", "getWatchedReadMode", "getWriteToTrakt", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getWriteToSimkl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/arflix/tv/data/repository/sync/SyncProviderStore$ProfileSyncSelection;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ProfileSyncSelection {
        public static final int $stable = 0;
        private final TrackingReadMode continueWatchingReadMode;
        private final String mdbListApiKey;
        private final SyncProvider provider;
        private final String simklAccessToken;
        private final TrackingReadMode watchedReadMode;
        private final TrackingReadMode watchlistReadMode;
        private final Boolean writeToSimkl;
        private final Boolean writeToTrakt;

        public ProfileSyncSelection(SyncProvider syncProvider, String str, String str2, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, Boolean bool, Boolean bool2) {
            this.provider = syncProvider;
            this.mdbListApiKey = str;
            this.simklAccessToken = str2;
            this.watchlistReadMode = trackingReadMode;
            this.continueWatchingReadMode = trackingReadMode2;
            this.watchedReadMode = trackingReadMode3;
            this.writeToTrakt = bool;
            this.writeToSimkl = bool2;
        }

        public static /* synthetic */ ProfileSyncSelection copy$default(ProfileSyncSelection profileSyncSelection, SyncProvider syncProvider, String str, String str2, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, Boolean bool, Boolean bool2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                syncProvider = profileSyncSelection.provider;
            }
            if ((i10 & 2) != 0) {
                str = profileSyncSelection.mdbListApiKey;
            }
            if ((i10 & 4) != 0) {
                str2 = profileSyncSelection.simklAccessToken;
            }
            if ((i10 & 8) != 0) {
                trackingReadMode = profileSyncSelection.watchlistReadMode;
            }
            if ((i10 & 16) != 0) {
                trackingReadMode2 = profileSyncSelection.continueWatchingReadMode;
            }
            if ((i10 & 32) != 0) {
                trackingReadMode3 = profileSyncSelection.watchedReadMode;
            }
            if ((i10 & 64) != 0) {
                bool = profileSyncSelection.writeToTrakt;
            }
            if ((i10 & 128) != 0) {
                bool2 = profileSyncSelection.writeToSimkl;
            }
            Boolean bool3 = bool;
            Boolean bool4 = bool2;
            TrackingReadMode trackingReadMode4 = trackingReadMode2;
            TrackingReadMode trackingReadMode5 = trackingReadMode3;
            return profileSyncSelection.copy(syncProvider, str, str2, trackingReadMode, trackingReadMode4, trackingReadMode5, bool3, bool4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SyncProvider getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMdbListApiKey() {
            return this.mdbListApiKey;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSimklAccessToken() {
            return this.simklAccessToken;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final TrackingReadMode getWatchlistReadMode() {
            return this.watchlistReadMode;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final TrackingReadMode getContinueWatchingReadMode() {
            return this.continueWatchingReadMode;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TrackingReadMode getWatchedReadMode() {
            return this.watchedReadMode;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getWriteToTrakt() {
            return this.writeToTrakt;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getWriteToSimkl() {
            return this.writeToSimkl;
        }

        public final ProfileSyncSelection copy(SyncProvider provider, String mdbListApiKey, String simklAccessToken, TrackingReadMode watchlistReadMode, TrackingReadMode continueWatchingReadMode, TrackingReadMode watchedReadMode, Boolean writeToTrakt, Boolean writeToSimkl) {
            return new ProfileSyncSelection(provider, mdbListApiKey, simklAccessToken, watchlistReadMode, continueWatchingReadMode, watchedReadMode, writeToTrakt, writeToSimkl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfileSyncSelection)) {
                return false;
            }
            ProfileSyncSelection profileSyncSelection = (ProfileSyncSelection) other;
            return this.provider == profileSyncSelection.provider && kotlin.jvm.internal.p.a(this.mdbListApiKey, profileSyncSelection.mdbListApiKey) && kotlin.jvm.internal.p.a(this.simklAccessToken, profileSyncSelection.simklAccessToken) && this.watchlistReadMode == profileSyncSelection.watchlistReadMode && this.continueWatchingReadMode == profileSyncSelection.continueWatchingReadMode && this.watchedReadMode == profileSyncSelection.watchedReadMode && kotlin.jvm.internal.p.a(this.writeToTrakt, profileSyncSelection.writeToTrakt) && kotlin.jvm.internal.p.a(this.writeToSimkl, profileSyncSelection.writeToSimkl);
        }

        public final TrackingReadMode getContinueWatchingReadMode() {
            return this.continueWatchingReadMode;
        }

        public final String getMdbListApiKey() {
            return this.mdbListApiKey;
        }

        public final SyncProvider getProvider() {
            return this.provider;
        }

        public final String getSimklAccessToken() {
            return this.simklAccessToken;
        }

        public final TrackingReadMode getWatchedReadMode() {
            return this.watchedReadMode;
        }

        public final TrackingReadMode getWatchlistReadMode() {
            return this.watchlistReadMode;
        }

        public final Boolean getWriteToSimkl() {
            return this.writeToSimkl;
        }

        public final Boolean getWriteToTrakt() {
            return this.writeToTrakt;
        }

        public int hashCode() {
            int iHashCode = this.provider.hashCode() * 31;
            String str = this.mdbListApiKey;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.simklAccessToken;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            TrackingReadMode trackingReadMode = this.watchlistReadMode;
            int iHashCode4 = (iHashCode3 + (trackingReadMode == null ? 0 : trackingReadMode.hashCode())) * 31;
            TrackingReadMode trackingReadMode2 = this.continueWatchingReadMode;
            int iHashCode5 = (iHashCode4 + (trackingReadMode2 == null ? 0 : trackingReadMode2.hashCode())) * 31;
            TrackingReadMode trackingReadMode3 = this.watchedReadMode;
            int iHashCode6 = (iHashCode5 + (trackingReadMode3 == null ? 0 : trackingReadMode3.hashCode())) * 31;
            Boolean bool = this.writeToTrakt;
            int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
            Boolean bool2 = this.writeToSimkl;
            return iHashCode7 + (bool2 != null ? bool2.hashCode() : 0);
        }

        public String toString() {
            return "ProfileSyncSelection(provider=" + this.provider + ", mdbListApiKey=" + this.mdbListApiKey + ", simklAccessToken=" + this.simklAccessToken + ", watchlistReadMode=" + this.watchlistReadMode + ", continueWatchingReadMode=" + this.continueWatchingReadMode + ", watchedReadMode=" + this.watchedReadMode + ", writeToTrakt=" + this.writeToTrakt + ", writeToSimkl=" + this.writeToSimkl + ")";
        }

        public /* synthetic */ ProfileSyncSelection(SyncProvider syncProvider, String str, String str2, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, Boolean bool, Boolean bool2, int i10, h hVar) {
            this(syncProvider, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : trackingReadMode, (i10 & 16) != 0 ? null : trackingReadMode2, (i10 & 32) != 0 ? null : trackingReadMode3, (i10 & 64) != 0 ? null : bool, (i10 & 128) != 0 ? null : bool2);
        }
    }
}
