package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.v1;
import ka.x0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\fH\u0082@¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0011¢\u0006\u0004\b\u001b\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001eR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001fR\u0014\u0010\u0015\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010!R\u0014\u0010\"\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010%\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006+"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncCoordinator;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "invalidationBus", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;Lcom/arflix/tv/data/repository/CloudSyncRepository;Lcom/arflix/tv/data/repository/AuthRepository;)V", "", "getSyncUserIdOrNull", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidation;", "invalidation", "Lx6/t0;", "scheduleFlush", "(Lcom/arflix/tv/data/repository/CloudSyncInvalidation;)V", "Lcom/arflix/tv/data/repository/CloudSyncScope;", "scope", "", "debounceMsFor", "(Lcom/arflix/tv/data/repository/CloudSyncScope;)J", TtmlNode.START, "()V", "stop", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lka/k0;", "Lka/k0;", "lifecycleLock", "Ljava/lang/Object;", "Lka/v1;", "collectorJob", "Lka/v1;", "flushJob", "Ljava/util/concurrent/atomic/AtomicBoolean;", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncCoordinator {
    public static final int $stable = 8;
    private final AuthRepository authRepository;
    private final CloudSyncRepository cloudSyncRepository;
    private v1 collectorJob;
    private final Context context;
    private v1 flushJob;
    private final CloudSyncInvalidationBus invalidationBus;
    private final ka.k0 scope = ka.l0.a(d7.h.a(ka.m0.d(), x0.f19655d));
    private final Object lifecycleLock = new Object();
    private final AtomicBoolean started = new AtomicBoolean(false);

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CloudSyncScope.values().length];
            try {
                iArr[CloudSyncScope.LOCAL_HISTORY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CloudSyncScope.IPTV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CloudSyncScope.PLUGINS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CloudSyncCoordinator$getSyncUserIdOrNull$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CloudSyncCoordinator", f = "CloudSyncCoordinator.kt", l = {TsExtractor.TS_STREAM_TYPE_H265}, m = "getSyncUserIdOrNull", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CloudSyncCoordinator.this.getSyncUserIdOrNull(this);
        }
    }

    @Inject
    public CloudSyncCoordinator(@ApplicationContext Context context, CloudSyncInvalidationBus cloudSyncInvalidationBus, CloudSyncRepository cloudSyncRepository, AuthRepository authRepository) {
        this.context = context;
        this.invalidationBus = cloudSyncInvalidationBus;
        this.cloudSyncRepository = cloudSyncRepository;
        this.authRepository = authRepository;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long debounceMsFor(CloudSyncScope scope) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[scope.ordinal()];
        if (i10 == 1) {
            return 2000L;
        }
        if (i10 != 2) {
            return i10 != 3 ? 500L : 1000L;
        }
        return 750L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSyncUserIdOrNull(d7.d<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.CloudSyncCoordinator.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.CloudSyncCoordinator$getSyncUserIdOrNull$1 r0 = (com.arflix.tv.data.repository.CloudSyncCoordinator.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CloudSyncCoordinator$getSyncUserIdOrNull$1 r0 = new com.arflix.tv.data.repository.CloudSyncCoordinator$getSyncUserIdOrNull$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            java.lang.String r3 = "CloudSyncCoordinator"
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            k2.c.G(r5)     // Catch: java.lang.Exception -> L27 java.io.IOException -> L29 retrofit2.HttpException -> L2b java.util.concurrent.CancellationException -> L2d
            goto L47
        L27:
            r5 = move-exception
            goto L4a
        L29:
            r5 = move-exception
            goto L50
        L2b:
            r5 = move-exception
            goto L56
        L2d:
            r5 = move-exception
            goto L5d
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L37:
            k2.c.G(r5)
            com.arflix.tv.data.repository.AuthRepository r5 = r4.authRepository     // Catch: java.lang.Exception -> L27 java.io.IOException -> L29 retrofit2.HttpException -> L2b java.util.concurrent.CancellationException -> L2d
            r0.label = r2     // Catch: java.lang.Exception -> L27 java.io.IOException -> L29 retrofit2.HttpException -> L2b java.util.concurrent.CancellationException -> L2d
            java.lang.Object r5 = r5.getCurrentUserIdForSync(r0)     // Catch: java.lang.Exception -> L27 java.io.IOException -> L29 retrofit2.HttpException -> L2b java.util.concurrent.CancellationException -> L2d
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L47
            return r0
        L47:
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L27 java.io.IOException -> L29 retrofit2.HttpException -> L2b java.util.concurrent.CancellationException -> L2d
            return r5
        L4a:
            java.lang.String r0 = "Failed to retrieve sync user ID"
            android.util.Log.w(r3, r0, r5)
            goto L5b
        L50:
            java.lang.String r0 = "Network error retrieving sync user ID"
            android.util.Log.w(r3, r0, r5)
            goto L5b
        L56:
            java.lang.String r0 = "HTTP error retrieving sync user ID"
            android.util.Log.w(r3, r0, r5)
        L5b:
            r5 = 0
            return r5
        L5d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncCoordinator.getSyncUserIdOrNull(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleFlush(CloudSyncInvalidation invalidation) {
        synchronized (this.lifecycleLock) {
            try {
                if (this.started.get()) {
                    v1 v1Var = this.flushJob;
                    if (v1Var != null) {
                        v1Var.cancel((CancellationException) null);
                    }
                    this.flushJob = ka.m0.p(this.scope, null, 0, new CloudSyncCoordinator$scheduleFlush$1$1(this, invalidation, null), 3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void start() {
        synchronized (this.lifecycleLock) {
            if (this.started.compareAndSet(false, true)) {
                this.collectorJob = ka.m0.p(this.scope, null, 0, new CloudSyncCoordinator$start$1$1(this, null), 3);
            }
        }
    }

    public final void stop() {
        synchronized (this.lifecycleLock) {
            try {
                this.started.set(false);
                v1 v1Var = this.collectorJob;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
                v1 v1Var2 = this.flushJob;
                if (v1Var2 != null) {
                    v1Var2.cancel((CancellationException) null);
                }
                this.collectorJob = null;
                this.flushJob = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
