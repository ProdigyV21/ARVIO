package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ MutableState $categoryHasHadFocus$delegate$inlined;
    final /* synthetic */ boolean $expanded$inlined;
    final /* synthetic */ MutableState $expandedAll$delegate$inlined;
    final /* synthetic */ FocusRequester $firstCategoryFocusRequester$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ r7.l $onTopBoundaryFocusChanged$inlined;
    final /* synthetic */ FocusRequester $selectedCategoryFocusRequester$inlined;
    final /* synthetic */ String $selectedId$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$3(List list, String str, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z, MutableState mutableState, r7.l lVar, r7.l lVar2, MutableState mutableState2) {
        super(4);
        this.$items = list;
        this.$selectedId$inlined = str;
        this.$selectedCategoryFocusRequester$inlined = focusRequester;
        this.$firstCategoryFocusRequester$inlined = focusRequester2;
        this.$expanded$inlined = z;
        this.$expandedAll$delegate$inlined = mutableState;
        this.$onSelect$inlined = lVar;
        this.$onTopBoundaryFocusChanged$inlined = lVar2;
        this.$categoryHasHadFocus$delegate$inlined = mutableState2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(androidx.compose.foundation.lazy.LazyItemScope r28, int r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instruction units count: 768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$3.invoke(androidx.compose.foundation.lazy.LazyItemScope, int, androidx.compose.runtime.Composer, int):void");
    }
}
