package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Category;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$favoriteTvCategory$1", f = "HomeViewModel.kt", l = {2301}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$favoriteTvCategory$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Category>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$favoriteTvCategory$1(HomeViewModel homeViewModel, d7.d<? super HomeViewModel$loadHomeData$1$favoriteTvCategory$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$loadHomeData$1$favoriteTvCategory$1 homeViewModel$loadHomeData$1$favoriteTvCategory$1 = new HomeViewModel$loadHomeData$1$favoriteTvCategory$1(this.this$0, dVar);
        homeViewModel$loadHomeData$1$favoriteTvCategory$1.L$0 = obj;
        return homeViewModel$loadHomeData$1$favoriteTvCategory$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                HomeViewModel homeViewModel = this.this$0;
                this.L$0 = null;
                this.L$1 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = homeViewModel.buildFavoriteTvCategory(this);
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
            c0Var = (Category) obj;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            return null;
        }
        return c0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Category> dVar) {
        return ((HomeViewModel$loadHomeData$1$favoriteTvCategory$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
