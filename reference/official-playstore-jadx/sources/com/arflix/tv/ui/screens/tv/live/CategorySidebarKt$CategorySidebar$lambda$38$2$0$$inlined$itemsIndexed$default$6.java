package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ MutableState $activeMenu$delegate$inlined;
    final /* synthetic */ MutableState $categoryHasHadFocus$delegate$inlined;
    final /* synthetic */ boolean $expanded$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ r7.l $onTopBoundaryFocusChanged$inlined;
    final /* synthetic */ FocusRequester $selectedCategoryFocusRequester$inlined;
    final /* synthetic */ String $selectedId$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6(List list, String str, FocusRequester focusRequester, boolean z, r7.l lVar, r7.l lVar2, MutableState mutableState, MutableState mutableState2) {
        super(4);
        this.$items = list;
        this.$selectedId$inlined = str;
        this.$selectedCategoryFocusRequester$inlined = focusRequester;
        this.$expanded$inlined = z;
        this.$onSelect$inlined = lVar;
        this.$onTopBoundaryFocusChanged$inlined = lVar2;
        this.$categoryHasHadFocus$delegate$inlined = mutableState;
        this.$activeMenu$delegate$inlined = mutableState2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = i11 | (composer.changed(lazyItemScope) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 112) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 731) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
        }
        final LiveCategory liveCategory = (LiveCategory) this.$items.get(i10);
        composer.startReplaceGroup(-1088997615);
        String strLiveCategoryLabel = LiveCategoryKt.liveCategoryLabel(liveCategory.getLabel(), composer, 0);
        int count = liveCategory.getCount();
        ImageVector imageVectorIconFor = CategorySidebarKt.iconFor(liveCategory);
        boolean zA = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory.getId());
        FocusRequester focusRequester = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory.getId()) ? this.$selectedCategoryFocusRequester$inlined : null;
        boolean z = this.$expanded$inlined;
        boolean zChanged = composer.changed(this.$onSelect$inlined) | composer.changedInstance(liveCategory);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelect$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$5$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6357invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6357invoke() {
                    lVar.invoke(liveCategory.getId());
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChanged2 = composer.changed(this.$onTopBoundaryFocusChanged$inlined);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar2 = this.$onTopBoundaryFocusChanged$inlined;
            final MutableState mutableState = this.$categoryHasHadFocus$delegate$inlined;
            objRememberedValue2 = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$5$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6358invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6358invoke() {
                    CategorySidebarKt.CategorySidebar$onCategoryFocused(lVar2, mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar2 = (r7.a) objRememberedValue2;
        boolean zChangedInstance = composer.changedInstance(liveCategory);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final MutableState mutableState2 = this.$activeMenu$delegate$inlined;
            objRememberedValue3 = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$5$3$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6359invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6359invoke() {
                    CategorySidebarKt.CategorySidebar$openCategoryMenu(mutableState2, liveCategory, false);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        CategorySidebarKt.m6342SidebarRowcR7IE_o(strLiveCategoryLabel, count, imageVectorIconFor, zA, z, aVar, aVar2, (r7.a) objRememberedValue3, null, null, false, false, 0.0f, 0L, focusRequester, composer, 0, 0, 16128);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
