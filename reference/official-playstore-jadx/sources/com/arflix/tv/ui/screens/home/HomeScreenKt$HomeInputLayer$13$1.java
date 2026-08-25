package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.Category;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeInputLayer$13$1", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeInputLayer$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<Category> $categories;
    final /* synthetic */ List<String> $categoryIds;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ MutableState<String> $preferredCategoryId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeInputLayer$13$1(List<Category> list, HomeFocusState homeFocusState, List<String> list2, MutableState<String> mutableState, d7.d<? super HomeScreenKt$HomeInputLayer$13$1> dVar) {
        super(2, dVar);
        this.$categories = list;
        this.$focusState = homeFocusState;
        this.$categoryIds = list2;
        this.$preferredCategoryId$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeInputLayer$13$1(this.$categories, this.$focusState, this.$categoryIds, this.$preferredCategoryId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int iPreferredHomeStartRowIndex;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zIsEmpty = this.$categories.isEmpty();
        t0 t0Var = t0.f22605a;
        if (zIsEmpty) {
            return t0Var;
        }
        if (!this.$focusState.getUserHasNavigated() && !this.$focusState.isSidebarFocused() && this.$focusState.getCurrentRowIndex() != (iPreferredHomeStartRowIndex = HomeScreenKt.preferredHomeStartRowIndex(this.$categories))) {
            this.$focusState.setCurrentRowIndex(iPreferredHomeStartRowIndex);
            this.$focusState.setCurrentItemIndex(0);
            MutableState<String> mutableState = this.$preferredCategoryId$delegate;
            Category category = (Category) kotlin.collections.x.p0(iPreferredHomeStartRowIndex, this.$categories);
            mutableState.setValue(category != null ? category.getId() : null);
        }
        this.$focusState.setCurrentRowIndex(HomeScreenKt.resolveHomeCategoryIndex(this.$categoryIds, HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$19(this.$preferredCategoryId$delegate), this.$focusState.getCurrentRowIndex()));
        MutableState<String> mutableState2 = this.$preferredCategoryId$delegate;
        Category category2 = (Category) kotlin.collections.x.p0(this.$focusState.getCurrentRowIndex(), this.$categories);
        mutableState2.setValue(category2 != null ? category2.getId() : null);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeInputLayer$13$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
