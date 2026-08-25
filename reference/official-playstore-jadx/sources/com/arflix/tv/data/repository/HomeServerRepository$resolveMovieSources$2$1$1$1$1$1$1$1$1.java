package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ HomeServerConnection $connection;
    final /* synthetic */ HomeServerRepository.HomeServerItem $item;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1(HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection, HomeServerRepository.HomeServerItem homeServerItem, d7.d<? super HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$connection = homeServerConnection;
        this.$item = homeServerItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1(this.this$0, this.$connection, this.$item, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.buildStreamSources(this.$connection, this.$item);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
