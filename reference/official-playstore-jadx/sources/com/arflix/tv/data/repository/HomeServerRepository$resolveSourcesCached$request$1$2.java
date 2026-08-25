package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveSourcesCached$request$1$2", f = "HomeServerRepository.kt", l = {911}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$resolveSourcesCached$request$1$2 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ String $key;
    final /* synthetic */ r7.l<d7.d<? super List<StreamSource>>, Object> $loader;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeServerRepository$resolveSourcesCached$request$1$2(r7.l<? super d7.d<? super List<StreamSource>>, ? extends Object> lVar, HomeServerRepository homeServerRepository, String str, d7.d<? super HomeServerRepository$resolveSourcesCached$request$1$2> dVar) {
        super(2, dVar);
        this.$loader = lVar;
        this.this$0 = homeServerRepository;
        this.$key = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeServerRepository$resolveSourcesCached$request$1$2(this.$loader, this.this$0, this.$key, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            r7.l<d7.d<? super List<StreamSource>>, Object> lVar = this.$loader;
            this.label = 1;
            obj = lVar.invoke(this);
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
        this.this$0.putCachedSources(this.$key, (List) obj);
        return obj;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((HomeServerRepository$resolveSourcesCached$request$1$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
