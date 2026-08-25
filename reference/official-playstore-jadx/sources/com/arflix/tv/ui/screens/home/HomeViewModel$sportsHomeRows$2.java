package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/Category;", "rows", "visibleIds", "", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$sportsHomeRows$2", f = "HomeViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$sportsHomeRows$2 extends f7.j implements r7.q<List<? extends Category>, Set<? extends String>, d7.d<? super List<? extends Category>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public HomeViewModel$sportsHomeRows$2(d7.d<? super HomeViewModel$sportsHomeRows$2> dVar) {
        super(3, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        List list = (List) this.L$0;
        Set set = (Set) this.L$1;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ArrayList arrayListW = androidx.compose.foundation.c.w(obj);
        for (Object obj2 : list) {
            if (set.contains(((Category) obj2).getId())) {
                arrayListW.add(obj2);
            }
        }
        return arrayListW;
    }

    @Override // r7.q
    public final Object invoke(List<Category> list, Set<String> set, d7.d<? super List<Category>> dVar) {
        HomeViewModel$sportsHomeRows$2 homeViewModel$sportsHomeRows$2 = new HomeViewModel$sportsHomeRows$2(dVar);
        homeViewModel$sportsHomeRows$2.L$0 = list;
        homeViewModel$sportsHomeRows$2.L$1 = set;
        return homeViewModel$sportsHomeRows$2.invokeSuspend(t0.f22605a);
    }
}
