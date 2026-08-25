package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Category $category;
    final /* synthetic */ r7.l<String, t0> $onLoadMoreCategory;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1(r7.l<? super String, t0> lVar, Category category, d7.d<? super HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1> dVar) {
        super(2, dVar);
        this.$onLoadMoreCategory = lVar;
        this.$category = category;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1(this.$onLoadMoreCategory, this.$category, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$onLoadMoreCategory.invoke(this.$category.getId());
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
