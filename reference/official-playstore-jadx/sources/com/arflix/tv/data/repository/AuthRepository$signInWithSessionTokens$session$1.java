package com.arflix.tv.data.repository;

import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository$signInWithSessionTokens$session$1", f = "AuthRepository.kt", l = {711, 713}, m = "invokeSuspend", v = 2)
public final class AuthRepository$signInWithSessionTokens$session$1 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
    final /* synthetic */ String $accessToken;
    final /* synthetic */ String $refreshToken;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ AuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthRepository$signInWithSessionTokens$session$1(AuthRepository authRepository, String str, String str2, d7.d<? super AuthRepository$signInWithSessionTokens$session$1> dVar) {
        super(2, dVar);
        this.this$0 = authRepository;
        this.$accessToken = str;
        this.$refreshToken = str2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        AuthRepository$signInWithSessionTokens$session$1 authRepository$signInWithSessionTokens$session$1 = new AuthRepository$signInWithSessionTokens$session$1(this.this$0, this.$accessToken, this.$refreshToken, dVar);
        authRepository$signInWithSessionTokens$session$1.L$0 = obj;
        return authRepository$signInWithSessionTokens$session$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
    
        if (r14 == r6) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r13.label
            r2 = 2
            r3 = 0
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r4) goto L2d
            if (r1 != r2) goto L25
            java.lang.Object r0 = r13.L$3
            ka.k0 r0 = (ka.k0) r0
            java.lang.Object r0 = r13.L$2
            ka.k0 r0 = (ka.k0) r0
            java.lang.Object r0 = r13.L$1
            com.arflix.tv.data.repository.AuthRepository r0 = (com.arflix.tv.data.repository.AuthRepository) r0
            k2.c.G(r14)     // Catch: java.lang.Throwable -> L23
            r12 = r13
            goto L88
        L23:
            r12 = r13
            goto L8d
        L25:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L2d:
            java.lang.Object r0 = r13.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r14)     // Catch: java.lang.Throwable -> L34
        L34:
            r12 = r13
            goto L59
        L36:
            k2.c.G(r14)
            com.arflix.tv.data.repository.AuthRepository r14 = r13.this$0
            java.lang.String r8 = r13.$accessToken
            java.lang.String r9 = r13.$refreshToken
            io.github.jan.supabase.SupabaseClient r14 = com.arflix.tv.data.repository.AuthRepository.access$getSupabase(r14)     // Catch: java.lang.Throwable -> L34
            io.github.jan.supabase.gotrue.Auth r7 = io.github.jan.supabase.gotrue.AuthKt.getAuth(r14)     // Catch: java.lang.Throwable -> L34
            r13.L$0 = r0     // Catch: java.lang.Throwable -> L34
            r13.L$1 = r5     // Catch: java.lang.Throwable -> L34
            r13.I$0 = r3     // Catch: java.lang.Throwable -> L34
            r13.label = r4     // Catch: java.lang.Throwable -> L34
            r10 = 0
            r11 = 1
            r12 = r13
            java.lang.Object r14 = r7.importAuthToken(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L59
            if (r14 != r6) goto L59
            goto L87
        L59:
            com.arflix.tv.data.repository.AuthRepository r14 = r12.this$0
            io.github.jan.supabase.SupabaseClient r14 = com.arflix.tv.data.repository.AuthRepository.access$getSupabase(r14)
            io.github.jan.supabase.gotrue.Auth r14 = io.github.jan.supabase.gotrue.AuthKt.getAuth(r14)
            io.github.jan.supabase.gotrue.user.UserSession r14 = r14.currentSessionOrNull()
            if (r14 != 0) goto L99
            com.arflix.tv.data.repository.AuthRepository r0 = r12.this$0
            io.github.jan.supabase.SupabaseClient r14 = com.arflix.tv.data.repository.AuthRepository.access$getSupabase(r0)     // Catch: java.lang.Throwable -> L8d
            io.github.jan.supabase.gotrue.Auth r14 = io.github.jan.supabase.gotrue.AuthKt.getAuth(r14)     // Catch: java.lang.Throwable -> L8d
            r12.L$0 = r5     // Catch: java.lang.Throwable -> L8d
            r12.L$1 = r0     // Catch: java.lang.Throwable -> L8d
            r12.L$2 = r5     // Catch: java.lang.Throwable -> L8d
            r12.L$3 = r5     // Catch: java.lang.Throwable -> L8d
            r12.I$0 = r3     // Catch: java.lang.Throwable -> L8d
            r12.I$1 = r3     // Catch: java.lang.Throwable -> L8d
            r12.label = r2     // Catch: java.lang.Throwable -> L8d
            java.lang.Object r14 = r14.loadFromStorage(r4, r13)     // Catch: java.lang.Throwable -> L8d
            if (r14 != r6) goto L88
        L87:
            return r6
        L88:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch: java.lang.Throwable -> L8d
            r14.booleanValue()     // Catch: java.lang.Throwable -> L8d
        L8d:
            io.github.jan.supabase.SupabaseClient r14 = com.arflix.tv.data.repository.AuthRepository.access$getSupabase(r0)
            io.github.jan.supabase.gotrue.Auth r14 = io.github.jan.supabase.gotrue.AuthKt.getAuth(r14)
            io.github.jan.supabase.gotrue.user.UserSession r14 = r14.currentSessionOrNull()
        L99:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository$signInWithSessionTokens$session$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
        return ((AuthRepository$signInWithSessionTokens$session$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
