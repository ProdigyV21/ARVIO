package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository$signOut$6$1", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class AuthRepository$signOut$6$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    /* synthetic */ Object L$0;
    int label;

    public AuthRepository$signOut$6$1(d7.d<? super AuthRepository$signOut$6$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        AuthRepository$signOut$6$1 authRepository$signOut$6$1 = new AuthRepository$signOut$6$1(dVar);
        authRepository$signOut$6$1.L$0 = obj;
        return authRepository$signOut$6$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((AuthRepository$signOut$6$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        bVar.b();
        bVar.f7368a.clear();
        return t0.f22605a;
    }
}
