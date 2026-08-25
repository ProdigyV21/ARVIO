package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.repository.MediaRepository;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Category;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1", f = "HomeViewModel.kt", l = {2390}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Category>, Object> {
    final /* synthetic */ CatalogConfig $cfg;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1(HomeViewModel homeViewModel, CatalogConfig catalogConfig, d7.d<? super HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
        this.$cfg = catalogConfig;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1(this.this$0, this.$cfg, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                MediaRepository mediaRepository = this.this$0.mediaRepository;
                CatalogConfig catalogConfig = this.$cfg;
                int i11 = this.this$0.isHardCappedTop10Catalog(catalogConfig.getId()) ? 10 : 20;
                this.label = 1;
                obj = mediaRepository.loadCustomCatalogPage(catalogConfig, 0, i11, this);
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
            MediaRepository.CategoryPageResult categoryPageResult = (MediaRepository.CategoryPageResult) obj;
            if (categoryPageResult.getItems().isEmpty()) {
                return null;
            }
            return this.this$0.withTop10CapIfNeeded(new Category(this.$cfg.getId(), this.$cfg.getTitle(), categoryPageResult.getItems()));
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return null;
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Category> dVar) {
        return ((HomeViewModel$loadHomeData$1$categories$1$mdblistInitial$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
