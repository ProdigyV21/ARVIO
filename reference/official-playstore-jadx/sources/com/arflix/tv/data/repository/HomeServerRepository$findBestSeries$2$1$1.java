package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.HomeServerRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$findBestSeries$2$1$1", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$findBestSeries$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HomeServerRepository.HomeServerItem>>, Object> {
    final /* synthetic */ HomeServerConnection $connection;
    final /* synthetic */ String $providerId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$findBestSeries$2$1$1(HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection, String str, d7.d<? super HomeServerRepository$findBestSeries$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$connection = homeServerConnection;
        this.$providerId = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$findBestSeries$2$1$1 homeServerRepository$findBestSeries$2$1$1 = new HomeServerRepository$findBestSeries$2$1$1(this.this$0, this.$connection, this.$providerId, dVar);
        homeServerRepository$findBestSeries$2$1$1.L$0 = obj;
        return homeServerRepository$findBestSeries$2$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            c0Var = this.this$0.queryItems(this.$connection, "Series", kotlin.collections.h0.t0(new x6.x("AnyProviderIdEquals", this.$providerId), new x6.x("Limit", "10")));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return c0Var instanceof x6.c0 ? kotlin.collections.z.f19728i : c0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<HomeServerRepository.HomeServerItem>> dVar) {
        return ((HomeServerRepository$findBestSeries$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
