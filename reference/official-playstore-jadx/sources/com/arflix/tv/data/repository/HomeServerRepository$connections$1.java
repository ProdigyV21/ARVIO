package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "profileId", "Lc1/g;", "prefs", "", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Ljava/lang/String;Lc1/g;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$connections$1", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$connections$1 extends f7.j implements r7.q<String, c1.g, d7.d<? super List<? extends HomeServerConnection>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$connections$1(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$connections$1> dVar) {
        super(3, dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        c1.g gVar = (c1.g) this.L$1;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        HomeServerRepository homeServerRepository = this.this$0;
        return homeServerRepository.parseConnections((String) ((c1.b) gVar).f7368a.get(homeServerRepository.connectionKeyFor(str)));
    }

    @Override // r7.q
    public final Object invoke(String str, c1.g gVar, d7.d<? super List<HomeServerConnection>> dVar) {
        HomeServerRepository$connections$1 homeServerRepository$connections$1 = new HomeServerRepository$connections$1(this.this$0, dVar);
        homeServerRepository$connections$1.L$0 = str;
        homeServerRepository$connections$1.L$1 = gVar;
        return homeServerRepository$connections$1.invokeSuspend(t0.f22605a);
    }
}
