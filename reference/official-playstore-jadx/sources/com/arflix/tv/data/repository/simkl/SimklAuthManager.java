package com.arflix.tv.data.repository.simkl;

import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.SimklPinResponse;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import com.arflix.tv.util.Constants;
import d7.d;
import f7.c;
import f7.e;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\f\u0010\nJ\u0010\u0010\u000e\u001a\u00020\rH\u0086@¢\u0006\u0004\b\u000e\u0010\nJ\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0004\b\u0012\u0010\nJ\u0010\u0010\u0014\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0014\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "", "Lcom/arflix/tv/data/api/SimklApi;", "simklApi", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "syncProviderStore", "<init>", "(Lcom/arflix/tv/data/api/SimklApi;Lcom/arflix/tv/data/repository/sync/SyncProviderStore;)V", "", "getAccessToken", "(Ld7/d;)Ljava/lang/Object;", "", "isConnected", "Lcom/arflix/tv/data/api/SimklPinResponse;", "startPinAuth", "userCode", "pollPinAuth", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "fetchUsername", "Lx6/t0;", "disconnect", "Lcom/arflix/tv/data/api/SimklApi;", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "getClientId", "()Ljava/lang/String;", "clientId", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SimklAuthManager {
    public static final int $stable = 8;
    private final SimklApi simklApi;
    private final SyncProviderStore syncProviderStore;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklAuthManager$disconnect$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklAuthManager", f = "SimklAuthManager.kt", l = {66, MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "disconnect", v = 2)
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklAuthManager.this.disconnect(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklAuthManager$fetchUsername$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklAuthManager", f = "SimklAuthManager.kt", l = {54, 58}, m = "fetchUsername", v = 2)
    public static final class C12421 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12421(d<? super C12421> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklAuthManager.this.fetchUsername(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklAuthManager$isConnected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklAuthManager", f = "SimklAuthManager.kt", l = {32}, m = "isConnected", v = 2)
    public static final class C12431 extends c {
        int label;
        /* synthetic */ Object result;

        public C12431(d<? super C12431> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklAuthManager.this.isConnected(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklAuthManager$pollPinAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklAuthManager", f = "SimklAuthManager.kt", l = {43, TsExtractor.TS_STREAM_TYPE_MHAS, 46, 47}, m = "pollPinAuth", v = 2)
    public static final class C12441 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12441(d<? super C12441> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklAuthManager.this.pollPinAuth(null, this);
        }
    }

    @Inject
    public SimklAuthManager(SimklApi simklApi, SyncProviderStore syncProviderStore) {
        this.simklApi = simklApi;
        this.syncProviderStore = syncProviderStore;
    }

    private final String getClientId() {
        return Constants.INSTANCE.getSIMKL_CLIENT_ID();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
    
        if (r6.onProviderDisconnected(r1, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object disconnect(d7.d<? super x6.t0> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.simkl.SimklAuthManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.simkl.SimklAuthManager$disconnect$1 r0 = (com.arflix.tv.data.repository.simkl.SimklAuthManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklAuthManager$disconnect$1 r0 = new com.arflix.tv.data.repository.simkl.SimklAuthManager$disconnect$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r6)
            goto L52
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            k2.c.G(r6)
            goto L45
        L36:
            k2.c.G(r6)
            com.arflix.tv.data.repository.sync.SyncProviderStore r6 = r5.syncProviderStore
            r0.label = r3
            r1 = 0
            java.lang.Object r6 = r6.setSimklAccessToken(r1, r0)
            if (r6 != r4) goto L45
            goto L51
        L45:
            com.arflix.tv.data.repository.sync.SyncProviderStore r6 = r5.syncProviderStore
            com.arflix.tv.data.repository.sync.SyncProvider r1 = com.arflix.tv.data.repository.sync.SyncProvider.SIMKL
            r0.label = r2
            java.lang.Object r6 = r6.onProviderDisconnected(r1, r0)
            if (r6 != r4) goto L52
        L51:
            return r4
        L52:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklAuthManager.disconnect(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0074, code lost:
    
        if (r7 == r5) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchUsername(d7.d<? super java.lang.String> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.simkl.SimklAuthManager.C12421
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.simkl.SimklAuthManager$fetchUsername$1 r0 = (com.arflix.tv.data.repository.simkl.SimklAuthManager.C12421) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklAuthManager$fetchUsername$1 r0 = new com.arflix.tv.data.repository.simkl.SimklAuthManager$fetchUsername$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L43
            if (r1 == r3) goto L3f
            if (r1 != r2) goto L37
            java.lang.Object r1 = r0.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r7)     // Catch: java.lang.Exception -> L92
            goto L77
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L3f:
            k2.c.G(r7)
            goto L4f
        L43:
            k2.c.G(r7)
            r0.label = r3
            java.lang.Object r7 = r6.getAccessToken(r0)
            if (r7 != r5) goto L4f
            goto L76
        L4f:
            java.lang.String r7 = (java.lang.String) r7
            if (r7 != 0) goto L54
            return r4
        L54:
            java.lang.String r1 = "Bearer "
            java.lang.String r7 = r1.concat(r7)
            java.lang.String r1 = r6.getClientId()
            boolean r3 = kotlin.text.o.h0(r1)
            if (r3 == 0) goto L66
            java.lang.String r1 = "simkl_proxy"
        L66:
            com.arflix.tv.data.api.SimklApi r3 = r6.simklApi     // Catch: java.lang.Exception -> L92
            r0.L$0 = r4     // Catch: java.lang.Exception -> L92
            r0.L$1 = r4     // Catch: java.lang.Exception -> L92
            r0.L$2 = r4     // Catch: java.lang.Exception -> L92
            r0.label = r2     // Catch: java.lang.Exception -> L92
            java.lang.Object r7 = r3.getUserSettings(r7, r1, r0)     // Catch: java.lang.Exception -> L92
            if (r7 != r5) goto L77
        L76:
            return r5
        L77:
            com.arflix.tv.data.api.SimklUserSettingsResponse r7 = (com.arflix.tv.data.api.SimklUserSettingsResponse) r7     // Catch: java.lang.Exception -> L92
            com.arflix.tv.data.api.SimklUser r0 = r7.getUser()     // Catch: java.lang.Exception -> L92
            if (r0 == 0) goto L87
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Exception -> L92
            if (r0 != 0) goto L86
            goto L87
        L86:
            return r0
        L87:
            com.arflix.tv.data.api.SimklUser r7 = r7.getUser()     // Catch: java.lang.Exception -> L92
            if (r7 == 0) goto L92
            java.lang.String r7 = r7.getUsername()     // Catch: java.lang.Exception -> L92
            return r7
        L92:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklAuthManager.fetchUsername(d7.d):java.lang.Object");
    }

    public final Object getAccessToken(d<? super String> dVar) {
        return this.syncProviderStore.getSimklAccessToken(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isConnected(d7.d<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.simkl.SimklAuthManager.C12431
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.simkl.SimklAuthManager$isConnected$1 r0 = (com.arflix.tv.data.repository.simkl.SimklAuthManager.C12431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklAuthManager$isConnected$1 r0 = new com.arflix.tv.data.repository.simkl.SimklAuthManager$isConnected$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.getAccessToken(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L48
            boolean r5 = kotlin.text.o.h0(r5)
            if (r5 == 0) goto L46
            goto L48
        L46:
            r5 = 0
            goto L49
        L48:
            r5 = r2
        L49:
            r5 = r5 ^ r2
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklAuthManager.isConnected(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e2, code lost:
    
        if (r9.onProviderConnected(r10, r0) != r7) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pollPinAuth(java.lang.String r9, d7.d<? super java.lang.Boolean> r10) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklAuthManager.pollPinAuth(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object startPinAuth(d<? super SimklPinResponse> dVar) {
        String clientId = getClientId();
        if (o.h0(clientId)) {
            clientId = "simkl_proxy";
        }
        return this.simklApi.getPinCode(clientId, dVar);
    }
}
