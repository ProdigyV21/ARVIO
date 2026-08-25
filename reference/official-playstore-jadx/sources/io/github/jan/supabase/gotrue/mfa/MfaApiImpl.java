package io.github.jan.supabase.gotrue.mfa;

import d7.d;
import db.b;
import db.b0;
import db.m;
import db.n;
import e7.a;
import f7.c;
import f7.e;
import io.github.jan.supabase.gotrue.AuthImpl;
import io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi;
import io.github.jan.supabase.gotrue.SessionStatus;
import io.github.jan.supabase.gotrue.mfa.AuthenticatorAssuranceLevel;
import io.github.jan.supabase.gotrue.mfa.MfaApi;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserMfaFactor;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.ktor.util.Base64Kt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.text.o;
import na.h1;
import na.j;
import na.k;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J>\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0004\b\u0000\u0010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0096@¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\tH\u0096@¢\u0006\u0004\b\u0011\u0010\u0012J0\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0015H\u0096@¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\tH\u0096@¢\u0006\u0004\b\u001b\u0010\u0012J\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0096@¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010%R \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150&8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b'\u0010)R \u0010*\u001a\b\u0012\u0004\u0012\u00020\u00150&8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010(\u001a\u0004\b+\u0010)R\u0017\u0010-\u001a\u00020,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020 0\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102¨\u00064"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaApiImpl;", "Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "Lio/github/jan/supabase/gotrue/AuthImpl;", "gotrue", "<init>", "(Lio/github/jan/supabase/gotrue/AuthImpl;)V", "Response", "Lio/github/jan/supabase/gotrue/mfa/FactorType;", "factorType", "", "issuer", "friendlyName", "Lio/github/jan/supabase/gotrue/mfa/MfaFactor;", "enroll", "(Lio/github/jan/supabase/gotrue/mfa/FactorType;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "factorId", "Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;", "createChallenge", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "challengeId", "code", "", "saveSession", "Lio/github/jan/supabase/gotrue/user/UserSession;", "verifyChallenge", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "Lx6/t0;", "unenroll", "Lio/github/jan/supabase/gotrue/mfa/MfaLevel;", "getAuthenticatorAssuranceLevel", "()Lio/github/jan/supabase/gotrue/mfa/MfaLevel;", "", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "retrieveFactorsForCurrentUser", "(Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/AuthImpl;", "getGotrue", "()Lio/github/jan/supabase/gotrue/AuthImpl;", "Lna/j;", "isMfaEnabledFlow", "Lna/j;", "()Lna/j;", "loggedInUsingMfaFlow", "getLoggedInUsingMfaFlow", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "api", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi", "()Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getVerifiedFactors", "()Ljava/util/List;", "verifiedFactors", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MfaApiImpl implements MfaApi {
    private final AuthenticatedSupabaseApi api;
    private final AuthImpl gotrue;
    private final j<Boolean> isMfaEnabledFlow;
    private final j<Boolean> loggedInUsingMfaFlow;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl", f = "MfaApi.kt", l = {191, 196}, m = "createChallenge")
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
            return MfaApiImpl.this.createChallenge(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$enroll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl", f = "MfaApi.kt", l = {197, 203, 141}, m = "enroll")
    public static final class C16991<Response> extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16991(d<? super C16991> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MfaApiImpl.this.enroll(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$retrieveFactorsForCurrentUser$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl", f = "MfaApi.kt", l = {187}, m = "retrieveFactorsForCurrentUser")
    public static final class C17001 extends c {
        int label;
        /* synthetic */ Object result;

        public C17001(d<? super C17001> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MfaApiImpl.this.retrieveFactorsForCurrentUser(this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$verifyChallenge$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl", f = "MfaApi.kt", l = {196, 202, 167}, m = "verifyChallenge")
    public static final class C17011 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C17011(d<? super C17011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MfaApiImpl.this.verifyChallenge(null, null, null, false, this);
        }
    }

    public MfaApiImpl(AuthImpl authImpl) {
        this.gotrue = authImpl;
        final h1<SessionStatus> sessionStatus = authImpl.getSessionStatus();
        this.isMfaEnabledFlow = new j<Boolean>() { // from class: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ MfaApiImpl this$0;

                /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2", f = "MfaApi.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends c {
                    Object L$0;
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

                public AnonymousClass2(k kVar, MfaApiImpl mfaApiImpl) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = mfaApiImpl;
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
                        boolean r0 = r6 instanceof io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2$1 r0 = (io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2$1 r0 = new io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L2d
                        if (r1 != r2) goto L25
                        k2.c.G(r6)
                        goto L6b
                    L25:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2d:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        io.github.jan.supabase.gotrue.SessionStatus r5 = (io.github.jan.supabase.gotrue.SessionStatus) r5
                        boolean r1 = r5 instanceof io.github.jan.supabase.gotrue.SessionStatus.Authenticated
                        if (r1 == 0) goto L3f
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl r5 = r4.this$0
                        boolean r5 = r5.isMfaEnabled()
                        goto L5c
                    L3f:
                        io.github.jan.supabase.gotrue.SessionStatus$LoadingFromStorage r1 = io.github.jan.supabase.gotrue.SessionStatus.LoadingFromStorage.INSTANCE
                        boolean r1 = kotlin.jvm.internal.p.a(r5, r1)
                        r3 = 0
                        if (r1 == 0) goto L4a
                    L48:
                        r5 = r3
                        goto L5c
                    L4a:
                        io.github.jan.supabase.gotrue.SessionStatus$NetworkError r1 = io.github.jan.supabase.gotrue.SessionStatus.NetworkError.INSTANCE
                        boolean r1 = kotlin.jvm.internal.p.a(r5, r1)
                        if (r1 == 0) goto L53
                        goto L48
                    L53:
                        io.github.jan.supabase.gotrue.SessionStatus$NotAuthenticated r1 = io.github.jan.supabase.gotrue.SessionStatus.NotAuthenticated.INSTANCE
                        boolean r5 = kotlin.jvm.internal.p.a(r5, r1)
                        if (r5 == 0) goto L6e
                        goto L48
                    L5c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L6b
                        return r6
                    L6b:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    L6e:
                        kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                        r5.<init>()
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d dVar) {
                Object objCollect = sessionStatus.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == a.f15033i ? objCollect : t0.f22605a;
            }
        };
        final h1<SessionStatus> sessionStatus2 = authImpl.getSessionStatus();
        this.loggedInUsingMfaFlow = new j<Boolean>() { // from class: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ MfaApiImpl this$0;

                /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2", f = "MfaApi.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends c {
                    Object L$0;
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

                public AnonymousClass2(k kVar, MfaApiImpl mfaApiImpl) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = mfaApiImpl;
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
                        boolean r0 = r6 instanceof io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2$1 r0 = (io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2$1 r0 = new io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L2d
                        if (r1 != r2) goto L25
                        k2.c.G(r6)
                        goto L6b
                    L25:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2d:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        io.github.jan.supabase.gotrue.SessionStatus r5 = (io.github.jan.supabase.gotrue.SessionStatus) r5
                        boolean r1 = r5 instanceof io.github.jan.supabase.gotrue.SessionStatus.Authenticated
                        if (r1 == 0) goto L3f
                        io.github.jan.supabase.gotrue.mfa.MfaApiImpl r5 = r4.this$0
                        boolean r5 = r5.getLoggedInUsingMfa()
                        goto L5c
                    L3f:
                        io.github.jan.supabase.gotrue.SessionStatus$LoadingFromStorage r1 = io.github.jan.supabase.gotrue.SessionStatus.LoadingFromStorage.INSTANCE
                        boolean r1 = kotlin.jvm.internal.p.a(r5, r1)
                        r3 = 0
                        if (r1 == 0) goto L4a
                    L48:
                        r5 = r3
                        goto L5c
                    L4a:
                        io.github.jan.supabase.gotrue.SessionStatus$NetworkError r1 = io.github.jan.supabase.gotrue.SessionStatus.NetworkError.INSTANCE
                        boolean r1 = kotlin.jvm.internal.p.a(r5, r1)
                        if (r1 == 0) goto L53
                        goto L48
                    L53:
                        io.github.jan.supabase.gotrue.SessionStatus$NotAuthenticated r1 = io.github.jan.supabase.gotrue.SessionStatus.NotAuthenticated.INSTANCE
                        boolean r5 = kotlin.jvm.internal.p.a(r5, r1)
                        if (r5 == 0) goto L6e
                        goto L48
                    L5c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L6b
                        return r6
                    L6b:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    L6e:
                        kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                        r5.<init>()
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d dVar) {
                Object objCollect = sessionStatus2.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == a.f15033i ? objCollect : t0.f22605a;
            }
        };
        this.api = authImpl.getApi();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        if (r8 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createChallenge(java.lang.String r7, d7.d<? super io.github.jan.supabase.gotrue.mfa.MfaChallenge> r8) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.github.jan.supabase.gotrue.mfa.MfaApiImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$1 r0 = (io.github.jan.supabase.gotrue.mfa.MfaApiImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$1 r0 = new io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L5d
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            k2.c.G(r8)
            goto L51
        L36:
            k2.c.G(r8)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r6.api
            java.lang.String r1 = "factors/"
            java.lang.String r5 = "/challenge"
            java.lang.String r7 = a0.c.l(r1, r7, r5)
            io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$$inlined$post$default$1 r1 = new io.github.jan.supabase.gotrue.mfa.MfaApiImpl$createChallenge$$inlined$post$default$1
            r1.<init>()
            r0.label = r3
            java.lang.Object r8 = r8.request(r7, r1, r0)
            if (r8 != r4) goto L51
            goto L5c
        L51:
            io.ktor.client.statement.HttpResponse r8 = (io.ktor.client.statement.HttpResponse) r8
            r0.label = r2
            r7 = 0
            java.lang.Object r8 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r8, r7, r0, r3, r7)
            if (r8 != r4) goto L5d
        L5c:
            return r4
        L5d:
            java.lang.String r8 = (java.lang.String) r8
            db.b r7 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L71
            r7.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L71
            io.github.jan.supabase.gotrue.mfa.MfaChallenge$Companion r0 = io.github.jan.supabase.gotrue.mfa.MfaChallenge.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L71
            ya.h r0 = r0.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L71
            java.lang.Object r7 = r7.c(r8, r0)     // Catch: kotlinx.serialization.MissingFieldException -> L71
            return r7
        L71:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r7 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Couldn't decode payload as "
            r0.<init>(r1)
            java.lang.Class<io.github.jan.supabase.gotrue.mfa.MfaChallenge> r1 = io.github.jan.supabase.gotrue.mfa.MfaChallenge.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            java.lang.String r1 = r1.r()
            r0.append(r1)
            java.lang.String r1 = ". Input: "
            r0.append(r1)
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "\n"
            java.lang.String r8 = kotlin.text.u.O(r8, r3, r1, r2)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl.createChallenge(java.lang.String, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public Object createChallengeAndVerify(String str, String str2, boolean z, d<? super UserSession> dVar) {
        return MfaApi.DefaultImpls.createChallengeAndVerify(this, str, str2, z, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
    
        if (r12 == r5) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <Response> java.lang.Object enroll(io.github.jan.supabase.gotrue.mfa.FactorType<Response> r9, java.lang.String r10, java.lang.String r11, d7.d<? super io.github.jan.supabase.gotrue.mfa.MfaFactor<Response>> r12) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl.enroll(io.github.jan.supabase.gotrue.mfa.FactorType, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public final AuthenticatedSupabaseApi getApi() {
        return this.api;
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public MfaLevel getAuthenticatorAssuranceLevel() {
        String strB;
        String strCurrentAccessTokenOrNull = this.gotrue.currentAccessTokenOrNull();
        if (strCurrentAccessTokenOrNull == null) {
            throw new IllegalStateException("Current session is null");
        }
        List listY0 = o.y0(strCurrentAccessTokenOrNull, new String[]{"."}, 0, 6);
        db.a aVar = b.f14877d;
        String strDecodeBase64String = Base64Kt.decodeBase64String((String) listY0.get(1));
        aVar.getClass();
        b0 b0Var = (b0) aVar.c(strDecodeBase64String, b0.Companion.serializer());
        AuthenticatorAssuranceLevel.Companion companion = AuthenticatorAssuranceLevel.INSTANCE;
        m mVar = (m) b0Var.get("aal");
        if (mVar == null || (strB = n.g(mVar).b()) == null) {
            throw new IllegalStateException("No 'aal' claim found in JWT");
        }
        return new MfaLevel(companion.from(strB), !getVerifiedFactors().isEmpty() ? AuthenticatorAssuranceLevel.AAL2 : AuthenticatorAssuranceLevel.AAL1);
    }

    public final AuthImpl getGotrue() {
        return this.gotrue;
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public boolean getLoggedInUsingMfa() {
        return MfaApi.DefaultImpls.getLoggedInUsingMfa(this);
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public j<Boolean> getLoggedInUsingMfaFlow() {
        return this.loggedInUsingMfaFlow;
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public List<UserMfaFactor> getVerifiedFactors() {
        UserInfo user;
        List<UserMfaFactor> factors;
        Object value = this.gotrue.getSessionStatus().getValue();
        UserSession userSessionM6640unboximpl = value instanceof SessionStatus.Authenticated ? ((SessionStatus.Authenticated) value).m6640unboximpl() : null;
        if (userSessionM6640unboximpl == null || (user = userSessionM6640unboximpl.getUser()) == null || (factors = user.getFactors()) == null) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : factors) {
            if (((UserMfaFactor) obj).isVerified()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public boolean isMfaEnabled() {
        return MfaApi.DefaultImpls.isMfaEnabled(this);
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public j<Boolean> isMfaEnabledFlow() {
        return this.isMfaEnabledFlow;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveFactorsForCurrentUser(d7.d<? super java.util.List<io.github.jan.supabase.gotrue.user.UserMfaFactor>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.github.jan.supabase.gotrue.mfa.MfaApiImpl.C17001
            if (r0 == 0) goto L13
            r0 = r5
            io.github.jan.supabase.gotrue.mfa.MfaApiImpl$retrieveFactorsForCurrentUser$1 r0 = (io.github.jan.supabase.gotrue.mfa.MfaApiImpl.C17001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.mfa.MfaApiImpl$retrieveFactorsForCurrentUser$1 r0 = new io.github.jan.supabase.gotrue.mfa.MfaApiImpl$retrieveFactorsForCurrentUser$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            io.github.jan.supabase.gotrue.AuthImpl r5 = r4.gotrue
            java.lang.String r1 = r5.currentAccessTokenOrNull()
            if (r1 == 0) goto L4a
            r0.label = r2
            java.lang.Object r5 = r5.retrieveUser(r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            io.github.jan.supabase.gotrue.user.UserInfo r5 = (io.github.jan.supabase.gotrue.user.UserInfo) r5
            java.util.List r5 = r5.getFactors()
            return r5
        L4a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Current session is null"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl.retrieveFactorsForCurrentUser(d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    public Object unenroll(String str, d<? super t0> dVar) {
        Object objRequest = this.api.request(androidx.compose.material3.d.C("factors/", str), new MfaApiImpl$unenroll$$inlined$delete$default$1(), dVar);
        return objRequest == a.f15033i ? objRequest : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // io.github.jan.supabase.gotrue.mfa.MfaApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object verifyChallenge(java.lang.String r9, java.lang.String r10, java.lang.String r11, boolean r12, d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r13) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApiImpl.verifyChallenge(java.lang.String, java.lang.String, java.lang.String, boolean, d7.d):java.lang.Object");
    }
}
