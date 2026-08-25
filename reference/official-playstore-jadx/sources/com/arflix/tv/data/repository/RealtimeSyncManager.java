package com.arflix.tv.data.repository;

import android.content.Context;
import android.util.Log;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.util.Constants;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.v0;
import gb.w0;
import io.ktor.client.utils.CacheControl;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.s0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.r0;
import na.u0;
import na.y0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 [2\u00020\u0001:\u0001[B#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n¢\u0006\u0004\b\r\u0010\fJ\r\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\fJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\fJ\u000f\u0010\u0010\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0010\u0010\fJ\u001f\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001d\u0010\fJ\u000f\u0010\u001e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001e\u0010\fJ\u0017\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\nH\u0002¢\u0006\u0004\b!\u0010\fJ\u000f\u0010\"\u001a\u00020\nH\u0002¢\u0006\u0004\b\"\u0010\fJ\u000f\u0010#\u001a\u00020\nH\u0002¢\u0006\u0004\b#\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001b\u00105\u001a\u0002008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010:R\u0018\u0010<\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010:R\u0018\u0010=\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010:R\u0018\u0010>\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010:R\u0018\u0010?\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010:R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010BR\u0016\u0010D\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010BR\u0018\u0010E\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\n0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u001d\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0J8\u0006¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\n0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010IR\u001d\u0010P\u001a\b\u0012\u0004\u0012\u00020\n0J8\u0006¢\u0006\f\n\u0004\bP\u0010L\u001a\u0004\bQ\u0010NR\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020S0R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010UR\u001d\u0010W\u001a\b\u0012\u0004\u0012\u00020S0V8\u0006¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u0010Z¨\u0006\\"}, d2 = {"Lcom/arflix/tv/data/repository/RealtimeSyncManager;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/CloudSyncRepository;Lcom/arflix/tv/data/repository/AuthRepository;)V", "Lx6/t0;", "markPush", "()V", "markLocalWatchHistoryWrite", TtmlNode.START, "stop", "connectWebSocket", "", "userId", "accessToken", "connectWebSocketWithToken", "(Ljava/lang/String;Ljava/lang/String;)V", "Lgb/v0;", "ws", "joinChannel", "(Lgb/v0;Ljava/lang/String;)V", "text", "handleMessage", "(Ljava/lang/String;)V", "debouncedPull", "debouncedWatchHistoryEmit", "startHeartbeat", "(Lgb/v0;)V", "scheduleReconnect", "startPeriodicSync", "startTokenRefreshLoop", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lka/k0;", "scope", "Lka/k0;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isRunning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicInteger;", "msgRef", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lgb/h0;", "wsClient$delegate", "Lx6/s;", "getWsClient", "()Lgb/h0;", "wsClient", "webSocket", "Lgb/v0;", "Lka/v1;", "heartbeatJob", "Lka/v1;", "periodicSyncJob", "reconnectJob", "pendingPullJob", "pendingWatchHistoryEmitJob", "tokenRefreshJob", "", "currentReconnectDelay", "J", "lastPushTimestamp", "lastLocalWatchHistoryWriteTimestamp", "currentAccessToken", "Ljava/lang/String;", "Lna/p0;", "_watchHistoryEvents", "Lna/p0;", "Lna/u0;", "watchHistoryEvents", "Lna/u0;", "getWatchHistoryEvents", "()Lna/u0;", "_accountSyncEvents", "accountSyncEvents", "getAccountSyncEvents", "Lna/q0;", "Lcom/arflix/tv/data/repository/CloudSyncStatus;", "_syncStatusFlow", "Lna/q0;", "Lna/h1;", "syncStatusFlow", "Lna/h1;", "getSyncStatusFlow", "()Lna/h1;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealtimeSyncManager {
    private static final long DEBOUNCE_MS = 2000;
    private static final long HEARTBEAT_INTERVAL_MS = 30000;
    private static final long INITIAL_RECONNECT_DELAY_MS = 5000;
    private static final long MAX_RECONNECT_DELAY_MS = 40000;
    private static final long PERIODIC_SYNC_INTERVAL_MS = 900000;
    private static final String TAG = "RealtimeSync";
    private static final long TOKEN_REFRESH_INTERVAL_MS = 1800000;
    private static final long WATCH_HISTORY_DEBOUNCE_MS = 1000;
    private static final long WATCH_HISTORY_SELF_ECHO_GUARD_MS = 1500;
    private final na.p0<t0> _accountSyncEvents;
    private final q0<CloudSyncStatus> _syncStatusFlow;
    private final na.p0<t0> _watchHistoryEvents;
    private final u0<t0> accountSyncEvents;
    private final AuthRepository authRepository;
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;
    private volatile String currentAccessToken;
    private v1 heartbeatJob;
    private volatile long lastLocalWatchHistoryWriteTimestamp;
    private volatile long lastPushTimestamp;
    private v1 pendingPullJob;
    private v1 pendingWatchHistoryEmitJob;
    private v1 periodicSyncJob;
    private v1 reconnectJob;
    private final h1<CloudSyncStatus> syncStatusFlow;
    private v1 tokenRefreshJob;
    private final u0<t0> watchHistoryEvents;
    private v0 webSocket;
    public static final int $stable = 8;
    private final ka.k0 scope = ka.l0.a(d7.h.a(ka.m0.d(), x0.f19655d));
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicInteger msgRef = new AtomicInteger(1);

    /* JADX INFO: renamed from: wsClient$delegate, reason: from kotlin metadata */
    private final x6.s wsClient = new x6.i0(new g0(7));
    private long currentReconnectDelay = 5000;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$connectWebSocket$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$connectWebSocket$1", f = "RealtimeSyncManager.kt", l = {171, 178}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        Object L$0;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RealtimeSyncManager.this.new AnonymousClass1(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
        
            if (r7 == r5) goto L20;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                int r0 = r6.label
                java.lang.String r1 = "RealtimeSync"
                r2 = 2
                r3 = 1
                x6.t0 r4 = x6.t0.f22605a
                e7.a r5 = e7.a.f15033i
                if (r0 == 0) goto L24
                if (r0 == r3) goto L20
                if (r0 != r2) goto L18
                java.lang.Object r0 = r6.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r7)
                goto L53
            L18:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L20:
                k2.c.G(r7)
                goto L36
            L24:
                k2.c.G(r7)
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                com.arflix.tv.data.repository.AuthRepository r7 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getAuthRepository$p(r7)
                r6.label = r3
                java.lang.Object r7 = r7.getCurrentUserIdForSync(r6)
                if (r7 != r5) goto L36
                goto L52
            L36:
                r0 = r7
                java.lang.String r0 = (java.lang.String) r0
                if (r0 == 0) goto L7a
                boolean r7 = kotlin.text.o.h0(r0)
                if (r7 == 0) goto L42
                goto L7a
            L42:
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                com.arflix.tv.data.repository.AuthRepository r7 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getAuthRepository$p(r7)
                r6.L$0 = r0
                r6.label = r2
                java.lang.Object r7 = r7.getAccessToken(r6)
                if (r7 != r5) goto L53
            L52:
                return r5
            L53:
                java.lang.String r7 = (java.lang.String) r7
                if (r7 == 0) goto L64
                boolean r2 = kotlin.text.o.h0(r7)
                if (r2 == 0) goto L5e
                goto L64
            L5e:
                com.arflix.tv.data.repository.RealtimeSyncManager r1 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                com.arflix.tv.data.repository.RealtimeSyncManager.access$connectWebSocketWithToken(r1, r0, r7)
                return r4
            L64:
                java.lang.String r7 = "No access token, skipping WebSocket connection"
                android.util.Log.w(r1, r7)
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                na.q0 r7 = com.arflix.tv.data.repository.RealtimeSyncManager.access$get_syncStatusFlow$p(r7)
                com.arflix.tv.data.repository.CloudSyncStatus r0 = com.arflix.tv.data.repository.CloudSyncStatus.NOT_SIGNED_IN
                r7.setValue(r0)
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                com.arflix.tv.data.repository.RealtimeSyncManager.access$scheduleReconnect(r7)
                return r4
            L7a:
                java.lang.String r7 = "Not logged in, skipping WebSocket connection"
                android.util.Log.w(r1, r7)
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                na.q0 r7 = com.arflix.tv.data.repository.RealtimeSyncManager.access$get_syncStatusFlow$p(r7)
                com.arflix.tv.data.repository.CloudSyncStatus r0 = com.arflix.tv.data.repository.CloudSyncStatus.NOT_SIGNED_IN
                r7.setValue(r0)
                com.arflix.tv.data.repository.RealtimeSyncManager r7 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                com.arflix.tv.data.repository.RealtimeSyncManager.access$scheduleReconnect(r7)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.RealtimeSyncManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$debouncedPull$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$debouncedPull$1", f = "RealtimeSyncManager.kt", l = {441, 444}, m = "invokeSuspend", v = 2)
    public static final class C09611 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public C09611(d7.d<? super C09611> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RealtimeSyncManager.this.new C09611(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
        
            if (r9 == r5) goto L23;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Exception {
            /*
                r8 = this;
                int r0 = r8.label
                x6.t0 r1 = x6.t0.f22605a
                r2 = 2
                r3 = 1
                java.lang.String r4 = "RealtimeSync"
                e7.a r5 = e7.a.f15033i
                if (r0 == 0) goto L26
                if (r0 == r3) goto L22
                if (r0 != r2) goto L1a
                k2.c.G(r9)     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                goto L4a
            L14:
                r9 = move-exception
                goto L5a
            L16:
                r9 = move-exception
                goto L69
            L18:
                r9 = move-exception
                goto L73
            L1a:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L22:
                k2.c.G(r9)
                goto L34
            L26:
                k2.c.G(r9)
                r8.label = r3
                r6 = 2000(0x7d0, double:9.88E-321)
                java.lang.Object r9 = ka.s0.a(r6, r8)
                if (r9 != r5) goto L34
                goto L49
            L34:
                java.lang.String r9 = "Pulling cloud state after realtime notification"
                android.util.Log.i(r4, r9)
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                com.arflix.tv.data.repository.CloudSyncRepository r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getCloudSyncRepository$p(r9)     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                r8.label = r2     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                r0 = 0
                r2 = 0
                java.lang.Object r9 = com.arflix.tv.data.repository.CloudSyncRepository.pullFromCloud$default(r9, r0, r8, r3, r2)     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                if (r9 != r5) goto L4a
            L49:
                return r5
            L4a:
                com.arflix.tv.data.repository.CloudSyncRepository$RestoreResult r9 = (com.arflix.tv.data.repository.CloudSyncRepository.RestoreResult) r9     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                com.arflix.tv.data.repository.CloudSyncRepository$RestoreResult r0 = com.arflix.tv.data.repository.CloudSyncRepository.RestoreResult.RESTORED     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                if (r9 != r0) goto L7c
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                na.p0 r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$get_accountSyncEvents$p(r9)     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                r9.b(r1)     // Catch: java.lang.Exception -> L14 java.io.IOException -> L16 retrofit2.HttpException -> L18
                goto L7c
            L5a:
                boolean r0 = r9 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto L68
                java.lang.String r9 = r9.getMessage()
                java.lang.String r0 = "Realtime pull failed: "
                androidx.compose.material3.d.y(r0, r9, r4)
                goto L7c
            L68:
                throw r9
            L69:
                java.lang.String r9 = r9.getMessage()
                java.lang.String r0 = "Realtime pull failed (Network): "
                androidx.compose.material3.d.y(r0, r9, r4)
                goto L7c
            L73:
                java.lang.String r9 = r9.getMessage()
                java.lang.String r0 = "Realtime pull failed (HTTP): "
                androidx.compose.material3.d.y(r0, r9, r4)
            L7c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.RealtimeSyncManager.C09611.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09611) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$debouncedWatchHistoryEmit$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$debouncedWatchHistoryEmit$1", f = "RealtimeSyncManager.kt", l = {467}, m = "invokeSuspend", v = 2)
    public static final class C09621 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public C09621(d7.d<? super C09621> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RealtimeSyncManager.this.new C09621(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(1000L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            Log.i(RealtimeSyncManager.TAG, "Emitting watch_history event for Home refresh");
            na.p0 p0Var = RealtimeSyncManager.this._watchHistoryEvents;
            t0 t0Var = t0.f22605a;
            p0Var.b(t0Var);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09621) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$scheduleReconnect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$scheduleReconnect$1", f = "RealtimeSyncManager.kt", l = {502}, m = "invokeSuspend", v = 2)
    public static final class C09631 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public C09631(d7.d<? super C09631> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RealtimeSyncManager.this.new C09631(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                long j10 = RealtimeSyncManager.this.currentReconnectDelay;
                this.label = 1;
                Object objA = s0.a(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            RealtimeSyncManager realtimeSyncManager = RealtimeSyncManager.this;
            long j11 = realtimeSyncManager.currentReconnectDelay * ((long) 2);
            if (j11 > RealtimeSyncManager.MAX_RECONNECT_DELAY_MS) {
                j11 = 40000;
            }
            realtimeSyncManager.currentReconnectDelay = j11;
            if (RealtimeSyncManager.this.isRunning.get()) {
                Log.i(RealtimeSyncManager.TAG, "Reconnecting WebSocket (backoff: " + (RealtimeSyncManager.this.currentReconnectDelay / ((long) 1000)) + "s next)...");
                RealtimeSyncManager.this.connectWebSocket();
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09631) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$start$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$start$1", f = "RealtimeSyncManager.kt", l = {TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "invokeSuspend", v = 2)
    public static final class C09641 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        Object L$0;
        int label;

        public C09641(d7.d<? super C09641> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RealtimeSyncManager.this.new C09641(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            q0 q0Var;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                q0Var = RealtimeSyncManager.this._syncStatusFlow;
                AuthRepository authRepository = RealtimeSyncManager.this.authRepository;
                this.L$0 = q0Var;
                this.label = 1;
                obj = authRepository.getCurrentUserIdForSync(this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                q0Var = (q0) this.L$0;
                k2.c.G(obj);
            }
            CharSequence charSequence = (CharSequence) obj;
            q0Var.setValue((charSequence == null || kotlin.text.o.h0(charSequence)) ? CloudSyncStatus.NOT_SIGNED_IN : CloudSyncStatus.CONNECTED);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09641) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$startHeartbeat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$startHeartbeat$1", f = "RealtimeSyncManager.kt", l = {479}, m = "invokeSuspend", v = 2)
    public static final class C09651 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ v0 $ws;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09651(v0 v0Var, d7.d<? super C09651> dVar) {
            super(2, dVar);
            this.$ws = v0Var;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09651 c09651 = RealtimeSyncManager.this.new C09651(this.$ws, dVar);
            c09651.L$0 = obj;
            return c09651;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x003a A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0038 -> B:16:0x003b). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) throws org.json.JSONException {
            /*
                r5 = this;
                java.lang.Object r0 = r5.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                k2.c.G(r6)
                goto L3b
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                k2.c.G(r6)
            L1a:
                boolean r6 = ka.l0.d(r0)
                if (r6 == 0) goto L7f
                com.arflix.tv.data.repository.RealtimeSyncManager r6 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                java.util.concurrent.atomic.AtomicBoolean r6 = com.arflix.tv.data.repository.RealtimeSyncManager.access$isRunning$p(r6)
                boolean r6 = r6.get()
                if (r6 == 0) goto L7f
                r5.L$0 = r0
                r5.label = r2
                r3 = 30000(0x7530, double:1.4822E-319)
                java.lang.Object r6 = ka.s0.a(r3, r5)
                e7.a r1 = e7.a.f15033i
                if (r6 != r1) goto L3b
                return r1
            L3b:
                org.json.JSONObject r6 = new org.json.JSONObject
                r6.<init>()
                com.arflix.tv.data.repository.RealtimeSyncManager r1 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                java.lang.String r3 = "topic"
                java.lang.String r4 = "phoenix"
                r6.put(r3, r4)
                java.lang.String r3 = "event"
                java.lang.String r4 = "heartbeat"
                r6.put(r3, r4)
                org.json.JSONObject r3 = new org.json.JSONObject
                r3.<init>()
                java.lang.String r4 = "payload"
                r6.put(r4, r3)
                java.util.concurrent.atomic.AtomicInteger r1 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getMsgRef$p(r1)
                int r1 = r1.getAndIncrement()
                java.lang.String r1 = java.lang.String.valueOf(r1)
                java.lang.String r3 = "ref"
                r6.put(r3, r1)
                gb.v0 r1 = r5.$ws     // Catch: java.lang.Exception -> L75
                java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L75
                r1.a(r6)     // Catch: java.lang.Exception -> L75
                goto L1a
            L75:
                r6 = move-exception
                com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
                java.lang.String r1 = "RealtimeSyncManager"
                java.lang.String r2 = "Error parsing watch history event"
                r0.e(r1, r2, r6)
            L7f:
                x6.t0 r6 = x6.t0.f22605a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.RealtimeSyncManager.C09651.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09651) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$startPeriodicSync$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$startPeriodicSync$1", f = "RealtimeSyncManager.kt", l = {518, 528, 543}, m = "invokeSuspend", v = 2)
    public static final class C09661 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C09661(d7.d<? super C09661> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09661 c09661 = RealtimeSyncManager.this.new C09661(dVar);
            c09661.L$0 = obj;
            return c09661;
        }

        /* JADX WARN: Path cross not found for [B:38:0x007e, B:48:0x00d7], limit reached: 54 */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00ea -> B:27:0x0045). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00f1 -> B:27:0x0045). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00fd -> B:27:0x0045). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0108 -> B:27:0x0045). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 278
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.RealtimeSyncManager.C09661.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09661) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.RealtimeSyncManager$startTokenRefreshLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.RealtimeSyncManager$startTokenRefreshLoop$1", f = "RealtimeSyncManager.kt", l = {569, 572}, m = "invokeSuspend", v = 2)
    public static final class C09671 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C09671(d7.d<? super C09671> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09671 c09671 = RealtimeSyncManager.this.new C09671(dVar);
            c09671.L$0 = obj;
            return c09671;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0062, code lost:
        
            if (r9 == r5) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0054 A[Catch: Exception -> 0x0016, TRY_ENTER, TryCatch #0 {Exception -> 0x0016, blocks: (B:6:0x0012, B:26:0x0065, B:23:0x0054, B:28:0x0069, B:31:0x0070, B:33:0x007c, B:35:0x0089, B:36:0x0090, B:38:0x009e, B:39:0x00a1), top: B:47:0x0012 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0062 -> B:26:0x0065). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Exception {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r8.label
                java.lang.String r2 = "RealtimeSync"
                r3 = 2
                r4 = 1
                e7.a r5 = e7.a.f15033i
                if (r1 == 0) goto L25
                if (r1 == r4) goto L21
                if (r1 != r3) goto L19
                k2.c.G(r9)     // Catch: java.lang.Exception -> L16
                goto L65
            L16:
                r9 = move-exception
                goto La7
            L19:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L21:
                k2.c.G(r9)
                goto L48
            L25:
                k2.c.G(r9)
            L28:
                boolean r9 = ka.l0.d(r0)
                if (r9 == 0) goto Lb7
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                java.util.concurrent.atomic.AtomicBoolean r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$isRunning$p(r9)
                boolean r9 = r9.get()
                if (r9 == 0) goto Lb7
                r8.L$0 = r0
                r8.label = r4
                r6 = 1800000(0x1b7740, double:8.89318E-318)
                java.lang.Object r9 = ka.s0.a(r6, r8)
                if (r9 != r5) goto L48
                goto L64
            L48:
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this
                java.util.concurrent.atomic.AtomicBoolean r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$isRunning$p(r9)
                boolean r9 = r9.get()
                if (r9 == 0) goto Lb7
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.AuthRepository r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getAuthRepository$p(r9)     // Catch: java.lang.Exception -> L16
                r8.L$0 = r0     // Catch: java.lang.Exception -> L16
                r8.label = r3     // Catch: java.lang.Exception -> L16
                java.lang.Object r9 = r9.getAccessToken(r8)     // Catch: java.lang.Exception -> L16
                if (r9 != r5) goto L65
            L64:
                return r5
            L65:
                java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> L16
                if (r9 == 0) goto L28
                boolean r1 = kotlin.text.o.h0(r9)     // Catch: java.lang.Exception -> L16
                if (r1 == 0) goto L70
                goto L28
            L70:
                com.arflix.tv.data.repository.RealtimeSyncManager r1 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                java.lang.String r1 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getCurrentAccessToken$p(r1)     // Catch: java.lang.Exception -> L16
                boolean r9 = r9.equals(r1)     // Catch: java.lang.Exception -> L16
                if (r9 != 0) goto L28
                java.lang.String r9 = "Access token changed, reconnecting WebSocket with fresh token"
                android.util.Log.i(r2, r9)     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                gb.v0 r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getWebSocket$p(r9)     // Catch: java.lang.Exception -> L16
                if (r9 == 0) goto L90
                java.lang.String r1 = "Token refresh"
                r6 = 1000(0x3e8, float:1.401E-42)
                r9.e(r6, r1)     // Catch: java.lang.Exception -> L16
            L90:
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                r1 = 0
                com.arflix.tv.data.repository.RealtimeSyncManager.access$setWebSocket$p(r9, r1)     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                ka.v1 r9 = com.arflix.tv.data.repository.RealtimeSyncManager.access$getHeartbeatJob$p(r9)     // Catch: java.lang.Exception -> L16
                if (r9 == 0) goto La1
                r9.cancel(r1)     // Catch: java.lang.Exception -> L16
            La1:
                com.arflix.tv.data.repository.RealtimeSyncManager r9 = com.arflix.tv.data.repository.RealtimeSyncManager.this     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.RealtimeSyncManager.access$connectWebSocket(r9)     // Catch: java.lang.Exception -> L16
                goto L28
            La7:
                boolean r1 = r9 instanceof java.util.concurrent.CancellationException
                if (r1 != 0) goto Lb6
                java.lang.String r9 = r9.getMessage()
                java.lang.String r1 = "Token refresh check failed: "
                androidx.compose.material3.d.y(r1, r9, r2)
                goto L28
            Lb6:
                throw r9
            Lb7:
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.RealtimeSyncManager.C09671.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09671) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public RealtimeSyncManager(@ApplicationContext Context context, CloudSyncRepository cloudSyncRepository, AuthRepository authRepository) {
        this.context = context;
        this.cloudSyncRepository = cloudSyncRepository;
        this.authRepository = authRepository;
        na.x0 x0VarA = y0.a(4, 5);
        this._watchHistoryEvents = x0VarA;
        this.watchHistoryEvents = new r0(x0VarA);
        na.x0 x0VarA2 = y0.a(4, 5);
        this._accountSyncEvents = x0VarA2;
        this.accountSyncEvents = new r0(x0VarA2);
        j1 j1VarB = y0.b(CloudSyncStatus.NOT_SIGNED_IN);
        this._syncStatusFlow = j1VarB;
        this.syncStatusFlow = y0.e(j1VarB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectWebSocket() {
        if (this.isRunning.get()) {
            this._syncStatusFlow.setValue(CloudSyncStatus.RECONNECTING);
            ka.m0.p(this.scope, null, 0, new AnonymousClass1(null), 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectWebSocketWithToken(final String userId, String accessToken) {
        if (this.isRunning.get()) {
            Constants constants = Constants.INSTANCE;
            String str = kotlin.text.u.O(kotlin.text.u.O(constants.getSUPABASE_URL(), "https://", "wss://", false), "http://", "ws://", false) + "/realtime/v1/websocket?apikey=" + constants.getSUPABASE_ANON_KEY() + "&vsn=1.0.0";
            gb.j0 j0Var = new gb.j0();
            j0Var.i(str);
            gb.k0 k0VarB = j0Var.b();
            this.currentAccessToken = accessToken;
            this.webSocket = getWsClient().c(k0VarB, new w0() { // from class: com.arflix.tv.data.repository.RealtimeSyncManager.connectWebSocketWithToken.1
                @Override // gb.w0
                public void onClosed(v0 webSocket, int code, String reason) {
                    Log.i(RealtimeSyncManager.TAG, "WebSocket closed: " + code);
                    if (RealtimeSyncManager.this.isRunning.get()) {
                        RealtimeSyncManager.this._syncStatusFlow.setValue(CloudSyncStatus.RECONNECTING);
                        RealtimeSyncManager.this.scheduleReconnect();
                    }
                }

                @Override // gb.w0
                public void onClosing(v0 webSocket, int code, String reason) {
                    Log.i(RealtimeSyncManager.TAG, "WebSocket closing: " + code + " " + reason);
                    webSocket.e(1000, null);
                    if (RealtimeSyncManager.this.isRunning.get()) {
                        RealtimeSyncManager.this._syncStatusFlow.setValue(CloudSyncStatus.RECONNECTING);
                        RealtimeSyncManager.this.scheduleReconnect();
                    }
                }

                @Override // gb.w0
                public void onFailure(v0 webSocket, Throwable t2, gb.p0 response) {
                    androidx.compose.material3.d.y("WebSocket failure: ", t2.getMessage(), RealtimeSyncManager.TAG);
                    RealtimeSyncManager.this._syncStatusFlow.setValue(CloudSyncStatus.RECONNECTING);
                    RealtimeSyncManager.this.scheduleReconnect();
                }

                @Override // gb.w0
                public void onMessage(v0 webSocket, String text) throws Exception {
                    RealtimeSyncManager.this.handleMessage(text);
                }

                @Override // gb.w0
                public void onOpen(v0 webSocket, gb.p0 response) throws JSONException {
                    Log.i(RealtimeSyncManager.TAG, "WebSocket connected");
                    RealtimeSyncManager.this.currentReconnectDelay = 5000L;
                    RealtimeSyncManager.this._syncStatusFlow.setValue(CloudSyncStatus.CONNECTED);
                    RealtimeSyncManager.this.joinChannel(webSocket, userId);
                    RealtimeSyncManager.this.startHeartbeat(webSocket);
                }
            });
        }
    }

    private final void debouncedPull() {
        if (System.currentTimeMillis() - this.lastPushTimestamp < C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS) {
            Log.d(TAG, "Skipping pull - recent push detected");
            return;
        }
        v1 v1Var = this.pendingPullJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.pendingPullJob = ka.m0.p(this.scope, null, 0, new C09611(null), 3);
    }

    private final void debouncedWatchHistoryEmit() {
        if (System.currentTimeMillis() - this.lastLocalWatchHistoryWriteTimestamp < 1500) {
            Log.d(TAG, "Skipping watch_history emit - recent local write");
            return;
        }
        v1 v1Var = this.pendingWatchHistoryEmitJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.pendingWatchHistoryEmitJob = ka.m0.p(this.scope, null, 0, new C09621(null), 3);
    }

    private final gb.h0 getWsClient() {
        return (gb.h0) this.wsClient.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void handleMessage(String text) throws Exception {
        try {
            JSONObject jSONObject = new JSONObject(text);
            String strOptString = jSONObject.optString("event", "");
            String strOptString2 = jSONObject.optString("topic", "");
            if (strOptString != null) {
                switch (strOptString.hashCode()) {
                    case -2059942263:
                        if (strOptString.equals("phx_error")) {
                            Log.w(TAG, "Channel error: " + text);
                            return;
                        }
                        return;
                    case -2048325781:
                        if (strOptString.equals("phx_reply")) {
                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("payload");
                            Log.d(TAG, "Channel reply (" + strOptString2 + "): " + (jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("status") : null));
                            return;
                        }
                        return;
                    case -887328209:
                        if (strOptString.equals("system")) {
                            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("payload");
                            if (kotlin.jvm.internal.p.a(jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("status") : null, "ok")) {
                                Log.i(TAG, "Subscription confirmed (" + strOptString2 + ")");
                                return;
                            }
                            return;
                        }
                        return;
                    case 1251496957:
                        if (strOptString.equals("postgres_changes")) {
                            if (strOptString2 != null) {
                                int iHashCode = strOptString2.hashCode();
                                if (iHashCode != -1786261732) {
                                    if (iHashCode != -207088299) {
                                        if (iHashCode == 1248581010 && strOptString2.equals("realtime:watched_status")) {
                                            Log.i(TAG, "Received watched_status change (movies/episodes)");
                                            debouncedWatchHistoryEmit();
                                            return;
                                        }
                                    } else if (strOptString2.equals("realtime:watch_history")) {
                                        Log.i(TAG, "Received watch_history change");
                                        debouncedWatchHistoryEmit();
                                        return;
                                    }
                                } else if (strOptString2.equals("realtime:account_sync")) {
                                    Log.i(TAG, "Received account_sync change");
                                    debouncedPull();
                                    return;
                                }
                            }
                            Log.w(TAG, "postgres_changes on unknown topic: " + strOptString2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            androidx.compose.material3.d.y("Failed to parse realtime message: ", e5.getMessage(), TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void joinChannel(v0 ws, String userId) throws JSONException {
        Boolean bool;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("topic", "realtime:account_sync");
        jSONObject.put("event", "phx_join");
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("event", "INSERT");
        jSONObject4.put("schema", CacheControl.PUBLIC);
        jSONObject4.put("table", "account_sync_state");
        jSONObject4.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("event", "UPDATE");
        jSONObject5.put("schema", CacheControl.PUBLIC);
        jSONObject5.put("table", "account_sync_state");
        jSONObject5.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject5);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("event", "DELETE");
        jSONObject6.put("schema", CacheControl.PUBLIC);
        jSONObject6.put("table", "account_sync_state");
        jSONObject6.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject6);
        JSONObject jSONObject7 = new JSONObject();
        jSONObject7.put("event", "INSERT");
        jSONObject7.put("schema", CacheControl.PUBLIC);
        jSONObject7.put("table", "user_settings");
        jSONObject7.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject7);
        JSONObject jSONObject8 = new JSONObject();
        jSONObject8.put("event", "UPDATE");
        jSONObject8.put("schema", CacheControl.PUBLIC);
        jSONObject8.put("table", "user_settings");
        jSONObject8.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject8);
        JSONObject jSONObject9 = new JSONObject();
        jSONObject9.put("event", "DELETE");
        jSONObject9.put("schema", CacheControl.PUBLIC);
        jSONObject9.put("table", "user_settings");
        jSONObject9.put("filter", "user_id=eq." + userId);
        jSONArray.put(jSONObject9);
        jSONObject3.put("postgres_changes", jSONArray);
        jSONObject2.put("config", jSONObject3);
        Object obj = this.currentAccessToken;
        if (obj != null) {
            jSONObject2.put("access_token", obj);
        }
        jSONObject.put("payload", jSONObject2);
        jSONObject.put("ref", String.valueOf(this.msgRef.getAndIncrement()));
        ws.a(jSONObject.toString());
        Boolean bool2 = BuildConfig.ENABLE_REALTIME_WATCH_SYNC;
        if (bool2.booleanValue()) {
            bool = bool2;
            JSONObject jSONObject10 = new JSONObject();
            jSONObject10.put("topic", "realtime:watch_history");
            jSONObject10.put("event", "phx_join");
            JSONObject jSONObject11 = new JSONObject();
            JSONObject jSONObject12 = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject13 = new JSONObject();
            jSONObject13.put("event", "INSERT");
            jSONObject13.put("schema", CacheControl.PUBLIC);
            jSONObject13.put("table", "watch_history");
            jSONObject13.put("filter", "user_id=eq." + userId);
            jSONArray2.put(jSONObject13);
            JSONObject jSONObject14 = new JSONObject();
            jSONObject14.put("event", "UPDATE");
            jSONObject14.put("schema", CacheControl.PUBLIC);
            jSONObject14.put("table", "watch_history");
            jSONObject14.put("filter", "user_id=eq." + userId);
            jSONArray2.put(jSONObject14);
            JSONObject jSONObject15 = new JSONObject();
            jSONObject15.put("event", "DELETE");
            jSONObject15.put("schema", CacheControl.PUBLIC);
            jSONObject15.put("table", "watch_history");
            jSONObject15.put("filter", "user_id=eq." + userId);
            jSONArray2.put(jSONObject15);
            jSONObject12.put("postgres_changes", jSONArray2);
            jSONObject11.put("config", jSONObject12);
            Object obj2 = this.currentAccessToken;
            if (obj2 != null) {
                jSONObject11.put("access_token", obj2);
            }
            jSONObject10.put("payload", jSONObject11);
            jSONObject10.put("ref", String.valueOf(this.msgRef.getAndIncrement()));
            ws.a(jSONObject10.toString());
            JSONObject jSONObject16 = new JSONObject();
            jSONObject16.put("topic", "realtime:watched_status");
            jSONObject16.put("event", "phx_join");
            JSONObject jSONObject17 = new JSONObject();
            JSONObject jSONObject18 = new JSONObject();
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject19 = new JSONObject();
            jSONObject19.put("event", "INSERT");
            jSONObject19.put("schema", CacheControl.PUBLIC);
            jSONObject19.put("table", "watched_movies");
            jSONObject19.put("filter", "user_id=eq." + userId);
            jSONArray3.put(jSONObject19);
            JSONObject jSONObject20 = new JSONObject();
            jSONObject20.put("event", "DELETE");
            jSONObject20.put("schema", CacheControl.PUBLIC);
            jSONObject20.put("table", "watched_movies");
            jSONObject20.put("filter", "user_id=eq." + userId);
            jSONArray3.put(jSONObject20);
            JSONObject jSONObject21 = new JSONObject();
            jSONObject21.put("event", "INSERT");
            jSONObject21.put("schema", CacheControl.PUBLIC);
            jSONObject21.put("table", "watched_episodes");
            jSONObject21.put("filter", "user_id=eq." + userId);
            jSONArray3.put(jSONObject21);
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("event", "DELETE");
            jSONObject22.put("schema", CacheControl.PUBLIC);
            jSONObject22.put("table", "watched_episodes");
            jSONObject22.put("filter", "user_id=eq." + userId);
            jSONArray3.put(jSONObject22);
            jSONObject18.put("postgres_changes", jSONArray3);
            jSONObject17.put("config", jSONObject18);
            Object obj3 = this.currentAccessToken;
            if (obj3 != null) {
                jSONObject17.put("access_token", obj3);
            }
            jSONObject16.put("payload", jSONObject17);
            jSONObject16.put("ref", String.valueOf(this.msgRef.getAndIncrement()));
            ws.a(jSONObject16.toString());
        } else {
            bool = bool2;
        }
        Log.i(TAG, "Joined " + (bool.booleanValue() ? "account_sync + watch_history + watched_status" : "account_sync") + " channels for user " + userId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleReconnect() {
        v1 v1Var = this.heartbeatJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = this.reconnectJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        this.reconnectJob = ka.m0.p(this.scope, null, 0, new C09631(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startHeartbeat(v0 ws) {
        v1 v1Var = this.heartbeatJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.heartbeatJob = ka.m0.p(this.scope, null, 0, new C09651(ws, null), 3);
    }

    private final void startPeriodicSync() {
        v1 v1Var = this.periodicSyncJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.periodicSyncJob = ka.m0.p(this.scope, null, 0, new C09661(null), 3);
    }

    private final void startTokenRefreshLoop() {
        v1 v1Var = this.tokenRefreshJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.tokenRefreshJob = ka.m0.p(this.scope, null, 0, new C09671(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final gb.h0 wsClient_delegate$lambda$0() {
        gb.g0 g0Var = new gb.g0();
        g0Var.e(0L, TimeUnit.MILLISECONDS);
        g0Var.A = ib.c.b("interval", 25L, TimeUnit.SECONDS);
        return new gb.h0(g0Var);
    }

    public final u0<t0> getAccountSyncEvents() {
        return this.accountSyncEvents;
    }

    public final h1<CloudSyncStatus> getSyncStatusFlow() {
        return this.syncStatusFlow;
    }

    public final u0<t0> getWatchHistoryEvents() {
        return this.watchHistoryEvents;
    }

    public final void markLocalWatchHistoryWrite() {
        this.lastLocalWatchHistoryWriteTimestamp = System.currentTimeMillis();
    }

    public final void markPush() {
        this.lastPushTimestamp = System.currentTimeMillis();
    }

    public final void start() {
        if (this.isRunning.getAndSet(true)) {
            return;
        }
        Log.i(TAG, "Starting realtime sync");
        if (Constants.INSTANCE.getUSE_NETLIFY_CLOUD_SYNC()) {
            Log.i(TAG, "Netlify cloud sync enabled; Supabase realtime socket disabled");
            ka.m0.p(this.scope, null, 0, new C09641(null), 3);
            return;
        }
        this._syncStatusFlow.setValue(CloudSyncStatus.RECONNECTING);
        connectWebSocket();
        if (BuildConfig.ENABLE_PERIODIC_CLOUD_PULL.booleanValue()) {
            startPeriodicSync();
        }
        startTokenRefreshLoop();
    }

    public final void stop() {
        if (this.isRunning.getAndSet(false)) {
            Log.i(TAG, "Stopping realtime sync");
            v0 v0Var = this.webSocket;
            if (v0Var != null) {
                v0Var.e(1000, "App stopping");
            }
            this.webSocket = null;
            v1 v1Var = this.heartbeatJob;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            v1 v1Var2 = this.periodicSyncJob;
            if (v1Var2 != null) {
                v1Var2.cancel((CancellationException) null);
            }
            v1 v1Var3 = this.reconnectJob;
            if (v1Var3 != null) {
                v1Var3.cancel((CancellationException) null);
            }
            v1 v1Var4 = this.pendingPullJob;
            if (v1Var4 != null) {
                v1Var4.cancel((CancellationException) null);
            }
            v1 v1Var5 = this.pendingWatchHistoryEmitJob;
            if (v1Var5 != null) {
                v1Var5.cancel((CancellationException) null);
            }
            v1 v1Var6 = this.tokenRefreshJob;
            if (v1Var6 != null) {
                v1Var6.cancel((CancellationException) null);
            }
            this._syncStatusFlow.setValue(CloudSyncStatus.NOT_SIGNED_IN);
        }
    }
}
