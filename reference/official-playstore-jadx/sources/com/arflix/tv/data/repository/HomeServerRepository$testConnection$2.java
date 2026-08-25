package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$testConnection$2", f = "HomeServerRepository.kt", l = {402}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$testConnection$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$testConnection$2(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$testConnection$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeServerRepository$testConnection$2(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objM6139testConnectionsIoAF18A;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            HomeServerRepository homeServerRepository = this.this$0;
            this.label = 1;
            objM6139testConnectionsIoAF18A = homeServerRepository.m6139testConnectionsIoAF18A(this);
            e7.a aVar = e7.a.f15033i;
            if (objM6139testConnectionsIoAF18A == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            objM6139testConnectionsIoAF18A = ((x6.d0) obj).f22580i;
        }
        if (!(objM6139testConnectionsIoAF18A instanceof x6.c0)) {
            objM6139testConnectionsIoAF18A = (HomeServerConnection) kotlin.collections.x.m0((List) objM6139testConnectionsIoAF18A);
        }
        return new x6.d0(objM6139testConnectionsIoAF18A);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$testConnection$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
