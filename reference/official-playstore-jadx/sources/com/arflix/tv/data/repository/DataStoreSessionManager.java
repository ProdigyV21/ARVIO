package com.arflix.tv.data.repository;

import io.github.jan.supabase.gotrue.SessionManager;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096@¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0096@¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u000bH\u0096@¢\u0006\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/repository/DataStoreSessionManager;", "Lio/github/jan/supabase/gotrue/SessionManager;", "Lz0/g;", "Lc1/g;", "dataStore", "Ldb/b;", "json", "<init>", "(Lz0/g;Ldb/b;)V", "Lio/github/jan/supabase/gotrue/user/UserSession;", SettingsSessionManager.SETTINGS_KEY, "Lx6/t0;", "saveSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "loadSession", "(Ld7/d;)Ljava/lang/Object;", "deleteSession", "Lz0/g;", "Ldb/b;", "", "TAG", "Ljava/lang/String;", "Lc1/e;", "sessionKey", "Lc1/e;", "Lua/a;", "mutex", "Lua/a;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DataStoreSessionManager implements SessionManager {
    public static final int $stable = 8;
    private final String TAG;
    private final z0.g<c1.g> dataStore;
    private final db.b json;
    private final ua.a mutex;
    private final c1.e sessionKey;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.DataStoreSessionManager", f = "DataStoreSessionManager.kt", l = {100, 82}, m = "deleteSession", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
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
            return DataStoreSessionManager.this.deleteSession(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.DataStoreSessionManager$loadSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.DataStoreSessionManager", f = "DataStoreSessionManager.kt", l = {100, 54, 69}, m = "loadSession", v = 2)
    public static final class C07381 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C07381(d7.d<? super C07381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataStoreSessionManager.this.loadSession(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.DataStoreSessionManager", f = "DataStoreSessionManager.kt", l = {100, 37}, m = "saveSession", v = 2)
    public static final class C07391 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07391(d7.d<? super C07391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataStoreSessionManager.this.saveSession(null, this);
        }
    }

    public DataStoreSessionManager(z0.g<c1.g> gVar, db.b bVar) {
        this.dataStore = gVar;
        this.json = bVar;
        this.TAG = "DataStoreSessionMgr";
        this.sessionKey = new c1.e("supabase_session");
        this.mutex = ua.e.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 _init_$lambda$0(db.g gVar) {
        gVar.f14895b = true;
        gVar.f14894a = true;
        gVar.f14896c = true;
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0093 A[Catch: all -> 0x0030, TryCatch #1 {all -> 0x0030, blocks: (B:13:0x002c, B:32:0x0078, B:41:0x008f, B:43:0x0093, B:44:0x0094, B:45:0x009d, B:46:0x009e), top: B:49:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0094 A[Catch: all -> 0x0030, TryCatch #1 {all -> 0x0030, blocks: (B:13:0x002c, B:32:0x0078, B:41:0x008f, B:43:0x0093, B:44:0x0094, B:45:0x009d, B:46:0x009e), top: B:49:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$1, d7.d] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object, ua.a] */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.github.jan.supabase.gotrue.SessionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object deleteSession(d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.repository.DataStoreSessionManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$1 r0 = (com.arflix.tv.data.repository.DataStoreSessionManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$1 r0 = new com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L4c
            if (r1 == r4) goto L41
            if (r1 != r3) goto L39
            java.lang.Object r0 = r0.L$0
            ua.a r0 = (ua.a) r0
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L33 java.util.concurrent.CancellationException -> L36
            goto L78
        L30:
            r10 = move-exception
            goto L9f
        L33:
            r10 = move-exception
            goto L8f
        L36:
            r10 = move-exception
            goto L9e
        L39:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L41:
            int r1 = r0.I$0
            java.lang.Object r4 = r0.L$0
            ua.a r4 = (ua.a) r4
            k2.c.G(r10)
            r10 = r4
            goto L5f
        L4c:
            k2.c.G(r10)
            ua.a r10 = r9.mutex
            r0.L$0 = r10
            r0.I$0 = r2
            r0.label = r4
            java.lang.Object r1 = r10.c(r0)
            if (r1 != r6) goto L5e
            goto L74
        L5e:
            r1 = r2
        L5f:
            z0.g<c1.g> r4 = r9.dataStore     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$2$1 r7 = new com.arflix.tv.data.repository.DataStoreSessionManager$deleteSession$2$1     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            r7.<init>(r9, r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            r0.I$0 = r1     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            r0.I$1 = r2     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            r0.label = r3     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            java.lang.Object r0 = androidx.work.impl.t.o(r4, r7, r0)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L85 java.util.concurrent.CancellationException -> L8a
            if (r0 != r6) goto L75
        L74:
            return r6
        L75:
            r8 = r0
            r0 = r10
            r10 = r8
        L78:
            c1.g r10 = (c1.g) r10     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L33 java.util.concurrent.CancellationException -> L36
            r0.b(r5)
            x6.t0 r10 = x6.t0.f22605a
            return r10
        L80:
            r0 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L9f
        L85:
            r0 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L8f
        L8a:
            r0 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L9e
        L8f:
            boolean r1 = r10 instanceof java.util.concurrent.CancellationException     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L94
            throw r10     // Catch: java.lang.Throwable -> L30
        L94:
            com.arflix.tv.util.AppLogger r1 = com.arflix.tv.util.AppLogger.INSTANCE     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = r9.TAG     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = "Failed to delete session"
            r1.e(r2, r3, r10)     // Catch: java.lang.Throwable -> L30
            throw r10     // Catch: java.lang.Throwable -> L30
        L9e:
            throw r10     // Catch: java.lang.Throwable -> L30
        L9f:
            r0.b(r5)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.DataStoreSessionManager.deleteSession(d7.d):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(2:4|(1:6)(1:7))(0)|8|75|(1:(1:(1:(7:13|76|14|60|42|67|68)(2:20|21))(7:22|82|23|40|(1:42)(1:43)|67|68))(1:31))(3:32|(1:35)|58)|80|36|(5:39|40|(0)(0)|67|68)|58|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b8, code lost:
    
        r0 = r12;
        r12 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00bc, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bd, code lost:
    
        r4 = r12;
        r12 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ce, code lost:
    
        com.arflix.tv.util.AppLogger.INSTANCE.e(r11.TAG, "Failed to load session", r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d7, code lost:
    
        r12 = r11.dataStore;
        r5 = new com.arflix.tv.data.repository.DataStoreSessionManager$loadSession$2$1(r11, null);
        r0.L$0 = r4;
        r0.L$1 = null;
        r0.I$0 = r1;
        r0.I$1 = r2;
        r0.label = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ec, code lost:
    
        if (androidx.work.impl.t.o(r12, r5, r0) != r7) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ef, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f2, code lost:
    
        r12 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f3, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x010a, code lost:
    
        r0.b(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x010d, code lost:
    
        throw r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a5 A[PHI: r4
      0x00a5: PHI (r4v10 ??) = (r4v14 ??), (r4v11 ??) binds: [B:41:0x00a3, B:60:0x00f0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a8 A[Catch: all -> 0x0052, Exception -> 0x0056, CancellationException -> 0x0059, TRY_LEAVE, TryCatch #1 {all -> 0x0052, blocks: (B:23:0x004e, B:40:0x0095, B:43:0x00a8, B:51:0x00c6, B:53:0x00ca, B:55:0x00ce, B:56:0x00d7, B:70:0x0107, B:71:0x0108), top: B:75:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f8 A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #5 {all -> 0x0038, blocks: (B:14:0x0033, B:63:0x00f4, B:65:0x00f8, B:69:0x0106, B:72:0x0109), top: B:75:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0106 A[Catch: all -> 0x0038, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x0038, blocks: (B:14:0x0033, B:63:0x00f4, B:65:0x00f8, B:69:0x0106, B:72:0x0109), top: B:75:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v16, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.arflix.tv.data.repository.DataStoreSessionManager$loadSession$1, d7.d] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v31 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Object, ua.a] */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [ua.a] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.github.jan.supabase.gotrue.SessionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadSession(d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.DataStoreSessionManager.loadSession(d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b3 A[Catch: all -> 0x0038, TryCatch #3 {all -> 0x0038, blocks: (B:13:0x0034, B:32:0x0098, B:41:0x00af, B:43:0x00b3, B:44:0x00b4, B:45:0x00bd, B:46:0x00be), top: B:49:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b4 A[Catch: all -> 0x0038, TryCatch #3 {all -> 0x0038, blocks: (B:13:0x0034, B:32:0x0098, B:41:0x00af, B:43:0x00b3, B:44:0x00b4, B:45:0x00bd, B:46:0x00be), top: B:49:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v0, types: [io.github.jan.supabase.gotrue.user.UserSession, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v1, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r4v1, types: [db.b] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.github.jan.supabase.gotrue.SessionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object saveSession(io.github.jan.supabase.gotrue.user.UserSession r10, d7.d<? super x6.t0> r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.arflix.tv.data.repository.DataStoreSessionManager.C07391
            if (r0 == 0) goto L13
            r0 = r11
            com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$1 r0 = (com.arflix.tv.data.repository.DataStoreSessionManager.C07391) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$1 r0 = new com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L5a
            if (r1 == r4) goto L49
            if (r1 != r3) goto L41
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            ua.a r10 = (ua.a) r10
            java.lang.Object r0 = r0.L$0
            io.github.jan.supabase.gotrue.user.UserSession r0 = (io.github.jan.supabase.gotrue.user.UserSession) r0
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b java.util.concurrent.CancellationException -> L3e
            goto L98
        L38:
            r11 = move-exception
            goto Lbf
        L3b:
            r11 = move-exception
            goto Laf
        L3e:
            r11 = move-exception
            goto Lbe
        L41:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L49:
            int r10 = r0.I$0
            java.lang.Object r1 = r0.L$1
            ua.a r1 = (ua.a) r1
            java.lang.Object r4 = r0.L$0
            io.github.jan.supabase.gotrue.user.UserSession r4 = (io.github.jan.supabase.gotrue.user.UserSession) r4
            k2.c.G(r11)
            r11 = r1
            r1 = r10
            r10 = r4
            goto L6f
        L5a:
            k2.c.G(r11)
            ua.a r11 = r9.mutex
            r0.L$0 = r10
            r0.L$1 = r11
            r0.I$0 = r2
            r0.label = r4
            java.lang.Object r1 = r11.c(r0)
            if (r1 != r6) goto L6e
            goto L94
        L6e:
            r1 = r2
        L6f:
            db.b r4 = r9.json     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            io.github.jan.supabase.gotrue.user.UserSession$Companion r7 = io.github.jan.supabase.gotrue.user.UserSession.INSTANCE     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            ya.h r7 = r7.serializer()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            java.lang.String r10 = r4.b(r7, r10)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            z0.g<c1.g> r4 = r9.dataStore     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$2$1 r7 = new com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$2$1     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r7.<init>(r9, r10, r5)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.L$0 = r5     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.L$1 = r11     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.L$2 = r5     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.I$0 = r1     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.I$1 = r2     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            r0.label = r3     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            java.lang.Object r10 = androidx.work.impl.t.o(r4, r7, r0)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La5 java.util.concurrent.CancellationException -> Laa
            if (r10 != r6) goto L95
        L94:
            return r6
        L95:
            r8 = r11
            r11 = r10
            r10 = r8
        L98:
            c1.g r11 = (c1.g) r11     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b java.util.concurrent.CancellationException -> L3e
            r10.b(r5)
            x6.t0 r10 = x6.t0.f22605a
            return r10
        La0:
            r10 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto Lbf
        La5:
            r10 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto Laf
        Laa:
            r10 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto Lbe
        Laf:
            boolean r0 = r11 instanceof java.util.concurrent.CancellationException     // Catch: java.lang.Throwable -> L38
            if (r0 == 0) goto Lb4
            throw r11     // Catch: java.lang.Throwable -> L38
        Lb4:
            com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE     // Catch: java.lang.Throwable -> L38
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L38
            java.lang.String r2 = "Failed to save session"
            r0.e(r1, r2, r11)     // Catch: java.lang.Throwable -> L38
            throw r11     // Catch: java.lang.Throwable -> L38
        Lbe:
            throw r11     // Catch: java.lang.Throwable -> L38
        Lbf:
            r10.b(r5)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.DataStoreSessionManager.saveSession(io.github.jan.supabase.gotrue.user.UserSession, d7.d):java.lang.Object");
    }

    public /* synthetic */ DataStoreSessionManager(z0.g gVar, db.b bVar, int i10, kotlin.jvm.internal.h hVar) {
        this(gVar, (i10 & 2) != 0 ? kotlin.reflect.b0.a(new f(22)) : bVar);
    }
}
