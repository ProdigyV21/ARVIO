package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1", f = "HomeViewModel.kt", l = {4206}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends String>>, Object> {
    final /* synthetic */ List<MediaItem> $itemsToLoad;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1(List<MediaItem> list, HomeViewModel homeViewModel, d7.d<? super HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1> dVar) {
        super(2, dVar);
        this.$itemsToLoad = list;
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1 homeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1 = new HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1(this.$itemsToLoad, this.this$0, dVar);
        homeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1.L$0 = obj;
        return homeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            List<MediaItem> list = this.$itemsToLoad;
            HomeViewModel homeViewModel = this.this$0;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ka.m0.e(2, homeViewModel.networkDispatcher, k0Var, new HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1(homeViewModel, (MediaItem) it.next(), null)));
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
        return kotlin.collections.x.k0((Iterable) obj);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<String>> dVar) {
        return ((HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
