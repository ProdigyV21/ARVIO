package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1", f = "HomeServerRepository.kt", l = {713}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ HomeServerConnection $connection;
    final /* synthetic */ List<HomeServerRepository.HomeServerItem> $episodeItems;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1(List<HomeServerRepository.HomeServerItem> list, HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection, d7.d<? super HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1> dVar) {
        super(2, dVar);
        this.$episodeItems = list;
        this.this$0 = homeServerRepository;
        this.$connection = homeServerConnection;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1 homeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1 = new HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1(this.$episodeItems, this.this$0, this.$connection, dVar);
        homeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1.L$0 = obj;
        return homeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            List<HomeServerRepository.HomeServerItem> list = this.$episodeItems;
            HomeServerRepository homeServerRepository = this.this$0;
            HomeServerConnection homeServerConnection = this.$connection;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ka.m0.e(3, null, k0Var, new HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1$1$1(homeServerRepository, homeServerConnection, (HomeServerRepository.HomeServerItem) it.next(), null)));
            }
            this.L$0 = null;
            this.label = 1;
            obj = ka.m0.f(arrayList, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return kotlin.collections.s.V((Iterable) obj);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((HomeServerRepository$resolveEpisodeSources$2$1$1$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
