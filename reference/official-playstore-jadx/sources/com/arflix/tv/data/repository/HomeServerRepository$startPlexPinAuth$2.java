package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$startPlexPinAuth$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$startPlexPinAuth$2(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$startPlexPinAuth$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$startPlexPinAuth$2 homeServerRepository$startPlexPinAuth$2 = new HomeServerRepository$startPlexPinAuth$2(this.this$0, dVar);
        homeServerRepository$startPlexPinAuth$2.L$0 = obj;
        return homeServerRepository$startPlexPinAuth$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            c0Var = this.this$0.startPlexPinAuthInternal();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$startPlexPinAuth$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
