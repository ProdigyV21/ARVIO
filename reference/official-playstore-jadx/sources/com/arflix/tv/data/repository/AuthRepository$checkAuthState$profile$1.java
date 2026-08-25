package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/UserProfile;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/UserProfile;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$profile$1", f = "AuthRepository.kt", l = {512}, m = "invokeSuspend", v = 2)
public final class AuthRepository$checkAuthState$profile$1 extends f7.j implements r7.p<ka.k0, d7.d<? super UserProfile>, Object> {
    final /* synthetic */ String $userId;
    int label;
    final /* synthetic */ AuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthRepository$checkAuthState$profile$1(AuthRepository authRepository, String str, d7.d<? super AuthRepository$checkAuthState$profile$1> dVar) {
        super(2, dVar);
        this.this$0 = authRepository;
        this.$userId = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new AuthRepository$checkAuthState$profile$1(this.this$0, this.$userId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AuthRepository authRepository = this.this$0;
        String str = this.$userId;
        this.label = 1;
        Object objLoadUserProfile = authRepository.loadUserProfile(str, this);
        e7.a aVar = e7.a.f15033i;
        return objLoadUserProfile == aVar ? aVar : objLoadUserProfile;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super UserProfile> dVar) {
        return ((AuthRepository$checkAuthState$profile$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
