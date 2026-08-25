package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$13$1", f = "CategorySidebar.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CategorySidebarKt$CategorySidebar$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $expandedAll$delegate;
    final /* synthetic */ MutableState<String> $expandedCountry$delegate;
    final /* synthetic */ String $selectedId;
    final /* synthetic */ LiveCategoryTree $tree;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$13$1(String str, LiveCategoryTree liveCategoryTree, MutableState<String> mutableState, MutableState<Boolean> mutableState2, d7.d<? super CategorySidebarKt$CategorySidebar$13$1> dVar) {
        super(2, dVar);
        this.$selectedId = str;
        this.$tree = liveCategoryTree;
        this.$expandedCountry$delegate = mutableState;
        this.$expandedAll$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new CategorySidebarKt$CategorySidebar$13$1(this.$selectedId, this.$tree, this.$expandedCountry$delegate, this.$expandedAll$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object next;
        List<LiveCategory> children;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String strSelectedCountryGroupId = CategorySidebarKt.selectedCountryGroupId(this.$selectedId, this.$tree);
        if (strSelectedCountryGroupId != null) {
            this.$expandedCountry$delegate.setValue(strSelectedCountryGroupId);
        }
        Iterator<T> it = this.$tree.getTop().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(((LiveCategory) next).getId(), TtmlNode.COMBINE_ALL)) {
                break;
            }
        }
        LiveCategory liveCategory = (LiveCategory) next;
        if (liveCategory != null && (children = liveCategory.getChildren()) != null) {
            String str = this.$selectedId;
            if (!children.isEmpty()) {
                Iterator<T> it2 = children.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (CategorySidebarKt.containsId((LiveCategory) it2.next(), str)) {
                        CategorySidebarKt.CategorySidebar$lambda$15(this.$expandedAll$delegate, true);
                        break;
                    }
                }
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((CategorySidebarKt$CategorySidebar$13$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
