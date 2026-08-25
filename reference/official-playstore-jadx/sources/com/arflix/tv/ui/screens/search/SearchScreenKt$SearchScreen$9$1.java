package com.arflix.tv.ui.screens.search;

import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$SearchScreen$9$1", f = "SearchScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$SearchScreen$9$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<Category> $activeCategories;
    final /* synthetic */ MutableIntState $currentItemIndex$delegate;
    final /* synthetic */ MutableIntState $currentRowIndex$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$SearchScreen$9$1(List<Category> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super SearchScreenKt$SearchScreen$9$1> dVar) {
        super(2, dVar);
        this.$activeCategories = list;
        this.$currentRowIndex$delegate = mutableIntState;
        this.$currentItemIndex$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchScreenKt$SearchScreen$9$1(this.$activeCategories, this.$currentRowIndex$delegate, this.$currentItemIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        List<MediaItem> items;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        MutableIntState mutableIntState = this.$currentRowIndex$delegate;
        int intValue = mutableIntState.getIntValue();
        int size = this.$activeCategories.size() - 1;
        if (size < 0) {
            size = 0;
        }
        mutableIntState.setIntValue(qb.d.n(intValue, 0, size));
        Category category = (Category) kotlin.collections.x.p0(this.$currentRowIndex$delegate.getIntValue(), this.$activeCategories);
        int size2 = ((category == null || (items = category.getItems()) == null) ? 1 : items.size()) - 1;
        MutableIntState mutableIntState2 = this.$currentItemIndex$delegate;
        int intValue2 = mutableIntState2.getIntValue();
        if (size2 < 0) {
            size2 = 0;
        }
        mutableIntState2.setIntValue(qb.d.n(intValue2, 0, size2));
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$SearchScreen$9$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
