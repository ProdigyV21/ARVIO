package com.arflix.tv.ui.screens.home;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeInputLayer$15$1", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeInputLayer$15$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ String $focusedCategoryId;
    final /* synthetic */ List<String> $focusedItemKeys;
    final /* synthetic */ boolean $focusedRowHasMore;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeInputLayer$15$1(String str, List<String> list, HomeFocusState homeFocusState, boolean z, d7.d<? super HomeScreenKt$HomeInputLayer$15$1> dVar) {
        super(2, dVar);
        this.$focusedCategoryId = str;
        this.$focusedItemKeys = list;
        this.$focusState = homeFocusState;
        this.$focusedRowHasMore = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeInputLayer$15$1(this.$focusedCategoryId, this.$focusedItemKeys, this.$focusState, this.$focusedRowHasMore, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String str = this.$focusedCategoryId;
        t0 t0Var = t0.f22605a;
        if (str == null) {
            return t0Var;
        }
        int iResolveHomeItemIndex = HomeScreenKt.resolveHomeItemIndex(this.$focusedItemKeys, this.$focusState.getRowItemKeysByCategoryId().get(str), this.$focusState.getCurrentItemIndex(), this.$focusedRowHasMore);
        if (this.$focusState.getCurrentItemIndex() != iResolveHomeItemIndex) {
            this.$focusState.setCurrentItemIndex(iResolveHomeItemIndex);
        }
        this.$focusState.getRowItemIndicesByCategoryId().put(str, new Integer(iResolveHomeItemIndex));
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeInputLayer$15$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
