package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.repository.AuthState;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B+\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u0082@¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0013R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "<init>", "(Landroid/content/Context;Lgb/h0;Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/ProfileManager;)V", "", "getOrCreateInstallId", "(Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "recordAppOpen", "Landroid/content/Context;", "Lgb/h0;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lgb/d0;", "jsonMediaType", "Lgb/d0;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppUsageAnalyticsRepository {

    @Deprecated
    public static final long APP_OPEN_MIN_INTERVAL_MS = 86400000;

    @Deprecated
    public static final long AUTH_STATE_WAIT_MS = 10000;

    @Deprecated
    public static final String TAG = "AppUsageAnalytics";
    private final AuthRepository authRepository;
    private final Context context;
    private final gb.d0 jsonMediaType;
    private final gb.h0 okHttpClient;
    private final ProfileManager profileManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final c1.e INSTALL_ID_KEY = new c1.e("analytics_install_id_v1");
    private static final c1.e LAST_APP_OPEN_SENT_AT_KEY = new c1.e("analytics_last_app_open_sent_at_v1");

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0007\u001a\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00058\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\n8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\n8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository$Companion;", "", "<init>", "()V", "Lc1/e;", "", "INSTALL_ID_KEY", "Lc1/e;", "getINSTALL_ID_KEY", "()Lc1/e;", "", "LAST_APP_OPEN_SENT_AT_KEY", "getLAST_APP_OPEN_SENT_AT_KEY", "TAG", "Ljava/lang/String;", "AUTH_STATE_WAIT_MS", "J", "APP_OPEN_MIN_INTERVAL_MS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final c1.e getINSTALL_ID_KEY() {
            return AppUsageAnalyticsRepository.INSTALL_ID_KEY;
        }

        public final c1.e getLAST_APP_OPEN_SENT_AT_KEY() {
            return AppUsageAnalyticsRepository.LAST_APP_OPEN_SENT_AT_KEY;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository", f = "AppUsageAnalyticsRepository.kt", l = {128, 133}, m = "getOrCreateInstallId", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppUsageAnalyticsRepository.this.getOrCreateInstallId(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$2", f = "AppUsageAnalyticsRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $created;
        final /* synthetic */ c1.e $key;
        final /* synthetic */ kotlin.jvm.internal.k0 $resolved;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(c1.e eVar, String str, kotlin.jvm.internal.k0 k0Var, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$key = eVar;
            this.$created = str;
            this.$resolved = k0Var;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$key, this.$created, this.$resolved, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = (String) bVar.f7368a.get(this.$key);
            if (str == null || kotlin.text.o.h0(str)) {
                bVar.d(this.$key, this.$created);
            } else {
                this.$resolved.f19746i = str;
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2", f = "AppUsageAnalyticsRepository.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS, 50, 54, 56, 62, 84, 115}, m = "invokeSuspend", v = 2)
    public static final class C06722 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/AuthState;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/AuthState;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$1", f = "AppUsageAnalyticsRepository.kt", l = {51}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super AuthState>, Object> {
            int label;
            final /* synthetic */ AppUsageAnalyticsRepository this$0;

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/arflix/tv/data/repository/AuthState;"}, k = 3, mv = {2, 3, 0}, xi = 48)
            @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$1$1", f = "AppUsageAnalyticsRepository.kt", l = {}, m = "invokeSuspend", v = 2)
            public static final class C01291 extends f7.j implements r7.p<AuthState, d7.d<? super Boolean>, Object> {
                /* synthetic */ Object L$0;
                int label;

                public C01291(d7.d<? super C01291> dVar) {
                    super(2, dVar);
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C01291 c01291 = new C01291(dVar);
                    c01291.L$0 = obj;
                    return c01291;
                }

                @Override // r7.p
                public final Object invoke(AuthState authState, d7.d<? super Boolean> dVar) {
                    return ((C01291) create(authState, dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    AuthState authState = (AuthState) this.L$0;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return Boolean.valueOf(!(authState instanceof AuthState.Loading));
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AppUsageAnalyticsRepository appUsageAnalyticsRepository, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = appUsageAnalyticsRepository;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                h1<AuthState> authState = this.this$0.authRepository.getAuthState();
                C01291 c01291 = new C01291(null);
                this.label = 1;
                Object objM = y0.m(authState, c01291, this);
                e7.a aVar = e7.a.f15033i;
                return objM == aVar ? aVar : objM;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super AuthState> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$7, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.data.repository.AppUsageAnalyticsRepository$recordAppOpen$2$7", f = "AppUsageAnalyticsRepository.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass7 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
            final /* synthetic */ long $now;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(long j10, d7.d<? super AnonymousClass7> dVar) {
                super(2, dVar);
                this.$now = j10;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$now, dVar);
                anonymousClass7.L$0 = obj;
                return anonymousClass7;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
                return ((AnonymousClass7) create(bVar, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(AppUsageAnalyticsRepository.Companion.getLAST_APP_OPEN_SENT_AT_KEY(), new Long(this.$now));
                return t0.f22605a;
            }
        }

        public C06722(d7.d<? super C06722> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return AppUsageAnalyticsRepository.this.new C06722(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:132:0x036f, code lost:
        
            if (androidx.work.impl.t.o(r0, r2, r22) == r5) goto L133;
         */
        /* JADX WARN: Removed duplicated region for block: B:103:0x025c A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:109:0x026b A[Catch: Exception -> 0x0037, IOException -> 0x003a, PHI: r0 r6 r8 r10
          0x026b: PHI (r0v25 org.json.JSONObject) = (r0v22 org.json.JSONObject), (r0v50 org.json.JSONObject), (r0v50 org.json.JSONObject), (r0v50 org.json.JSONObject) binds: [B:89:0x0218, B:102:0x025a, B:107:0x0264, B:108:0x0266] A[DONT_GENERATE, DONT_INLINE]
          0x026b: PHI (r6v5 long) = (r6v4 long), (r6v7 long), (r6v7 long), (r6v7 long) binds: [B:89:0x0218, B:102:0x025a, B:107:0x0264, B:108:0x0266] A[DONT_GENERATE, DONT_INLINE]
          0x026b: PHI (r8v5 long) = (r8v4 long), (r8v6 long), (r8v6 long), (r8v6 long) binds: [B:89:0x0218, B:102:0x025a, B:107:0x0264, B:108:0x0266] A[DONT_GENERATE, DONT_INLINE]
          0x026b: PHI (r10v6 java.lang.String) = (r10v5 java.lang.String), (r10v11 java.lang.String), (r10v11 java.lang.String), (r10v11 java.lang.String) binds: [B:89:0x0218, B:102:0x025a, B:107:0x0264, B:108:0x0266] A[DONT_GENERATE, DONT_INLINE], TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:111:0x0287 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:121:0x02d4 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TRY_ENTER, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:127:0x0304 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:131:0x0343 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TRY_ENTER, TRY_LEAVE, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:134:0x0372 A[Catch: all -> 0x0386, TRY_ENTER, TryCatch #1 {all -> 0x0386, blocks: (B:129:0x033d, B:134:0x0372, B:135:0x0385), top: B:151:0x033d }] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x010e A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0113  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0120 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0147  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0149 A[Catch: Exception -> 0x0037, IOException -> 0x003a, PHI: r0 r6 r8
          0x0149: PHI (r0v74 java.lang.Object) = (r0v73 java.lang.Object), (r0v78 java.lang.Object) binds: [B:60:0x0145, B:28:0x0092] A[DONT_GENERATE, DONT_INLINE]
          0x0149: PHI (r6v23 long) = (r6v21 long), (r6v24 long) binds: [B:60:0x0145, B:28:0x0092] A[DONT_GENERATE, DONT_INLINE]
          0x0149: PHI (r8v11 long) = (r8v9 long), (r8v12 long) binds: [B:60:0x0145, B:28:0x0092] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0186  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x01a0  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0203  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0206  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x021a A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x0240 A[Catch: Exception -> 0x0037, IOException -> 0x003a, TryCatch #7 {IOException -> 0x003a, Exception -> 0x0037, blocks: (B:7:0x0032, B:14:0x0059, B:93:0x023c, B:95:0x0240, B:100:0x024a, B:101:0x0250, B:103:0x025c, B:108:0x0266, B:109:0x026b, B:111:0x0287, B:113:0x0290, B:114:0x02a1, B:118:0x02a9, B:121:0x02d4, B:123:0x02f4, B:126:0x02fb, B:128:0x032b, B:131:0x0343, B:140:0x038a, B:141:0x038d, B:127:0x0304, B:80:0x0199, B:84:0x01a1, B:88:0x0208, B:90:0x021a, B:77:0x018b, B:79:0x018f, B:142:0x038e, B:68:0x0168, B:143:0x038f, B:27:0x008f, B:62:0x0149, B:30:0x009a, B:59:0x0138, B:33:0x00a1, B:49:0x00f8, B:51:0x010e, B:53:0x0115, B:56:0x0120, B:46:0x00d9), top: B:153:0x000b }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 956
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AppUsageAnalyticsRepository.C06722.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C06722) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public AppUsageAnalyticsRepository(@ApplicationContext Context context, gb.h0 h0Var, AuthRepository authRepository, ProfileManager profileManager) {
        this.context = context;
        this.okHttpClient = h0Var;
        this.authRepository = authRepository;
        this.profileManager = profileManager;
        Pattern pattern = gb.d0.f15633d;
        this.jsonMediaType = xc.d.i0("application/json; charset=utf-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getOrCreateInstallId(d7.d<? super java.lang.String> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.AppUsageAnalyticsRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$1 r0 = (com.arflix.tv.data.repository.AppUsageAnalyticsRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$1 r0 = new com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4a
            if (r1 == r3) goto L42
            if (r1 != r2) goto L3a
            java.lang.Object r1 = r0.L$3
            kotlin.jvm.internal.k0 r1 = (kotlin.jvm.internal.k0) r1
            java.lang.Object r2 = r0.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r0 = r0.L$0
            c1.e r0 = (c1.e) r0
            k2.c.G(r9)
            goto La7
        L3a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L42:
            java.lang.Object r1 = r0.L$0
            c1.e r1 = (c1.e) r1
            k2.c.G(r9)
            goto L64
        L4a:
            k2.c.G(r9)
            c1.e r1 = com.arflix.tv.data.repository.AppUsageAnalyticsRepository.INSTALL_ID_KEY
            android.content.Context r9 = r8.context
            z0.g r9 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r9)
            na.j r9 = r9.getData()
            r0.L$0 = r1
            r0.label = r3
            java.lang.Object r9 = na.y0.l(r9, r0)
            if (r9 != r4) goto L64
            goto La5
        L64:
            c1.g r9 = (c1.g) r9
            c1.b r9 = (c1.b) r9
            java.util.LinkedHashMap r9 = r9.f7368a
            java.lang.Object r9 = r9.get(r1)
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L7a
            boolean r3 = kotlin.text.o.h0(r9)
            if (r3 == 0) goto L79
            goto L7a
        L79:
            return r9
        L7a:
            java.util.UUID r9 = java.util.UUID.randomUUID()
            java.lang.String r9 = r9.toString()
            kotlin.jvm.internal.k0 r3 = new kotlin.jvm.internal.k0
            r3.<init>()
            r3.f19746i = r9
            android.content.Context r5 = r8.context
            z0.g r5 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r5)
            com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$2 r6 = new com.arflix.tv.data.repository.AppUsageAnalyticsRepository$getOrCreateInstallId$2
            r7 = 0
            r6.<init>(r1, r9, r3, r7)
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.L$3 = r3
            r0.label = r2
            java.lang.Object r9 = androidx.work.impl.t.o(r5, r6, r0)
            if (r9 != r4) goto La6
        La5:
            return r4
        La6:
            r1 = r3
        La7:
            java.lang.Object r9 = r1.f19746i
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AppUsageAnalyticsRepository.getOrCreateInstallId(d7.d):java.lang.Object");
    }

    public final Object recordAppOpen(d7.d<? super t0> dVar) throws Throwable {
        Object objY = ka.m0.y(x0.f19655d, new C06722(null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }
}
