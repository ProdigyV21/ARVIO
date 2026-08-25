package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$12 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ MutableState $categoryHasHadFocus$delegate$inlined;
    final /* synthetic */ boolean $expanded$inlined;
    final /* synthetic */ MutableState $expandedCountry$delegate$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ r7.l $onTopBoundaryFocusChanged$inlined;
    final /* synthetic */ FocusRequester $selectedCategoryFocusRequester$inlined;
    final /* synthetic */ String $selectedId$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$12(List list, String str, FocusRequester focusRequester, boolean z, MutableState mutableState, r7.l lVar, r7.l lVar2, MutableState mutableState2) {
        super(4);
        this.$items = list;
        this.$selectedId$inlined = str;
        this.$selectedCategoryFocusRequester$inlined = focusRequester;
        this.$expanded$inlined = z;
        this.$expandedCountry$delegate$inlined = mutableState;
        this.$onSelect$inlined = lVar;
        this.$onTopBoundaryFocusChanged$inlined = lVar2;
        this.$categoryHasHadFocus$delegate$inlined = mutableState2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12 = (i11 & 14) == 0 ? i11 | (composer.changed(lazyItemScope) ? 4 : 2) : i11;
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
        composer.startReplaceGroup(2039973966);
        final boolean zA = kotlin.jvm.internal.p.a(CategorySidebarKt.CategorySidebar$lambda$11(this.$expandedCountry$delegate$inlined), liveCategory.getId());
        String strLiveCategoryLabel = LiveCategoryKt.liveCategoryLabel(liveCategory.getLabel(), composer, 0);
        int count = liveCategory.getCount();
        String id = liveCategory.getId();
        boolean zA2 = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory.getId());
        boolean z = !liveCategory.getChildren().isEmpty();
        FocusRequester focusRequester = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory.getId()) ? this.$selectedCategoryFocusRequester$inlined : null;
        boolean z5 = this.$expanded$inlined;
        boolean zChanged = composer.changed(zA) | composer.changed(this.$expandedCountry$delegate$inlined) | composer.changedInstance(liveCategory) | composer.changed(this.$onSelect$inlined);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelect$inlined;
            final MutableState mutableState = this.$expandedCountry$delegate$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$11$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6345invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6345invoke() {
                    if (zA) {
                        mutableState.setValue(null);
                    } else {
                        mutableState.setValue(liveCategory.getId());
                        lVar.invoke(liveCategory.getId());
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChanged2 = composer.changed(this.$onTopBoundaryFocusChanged$inlined);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar2 = this.$onTopBoundaryFocusChanged$inlined;
            final MutableState mutableState2 = this.$categoryHasHadFocus$delegate$inlined;
            objRememberedValue2 = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$11$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6346invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6346invoke() {
                    CategorySidebarKt.CategorySidebar$onCategoryFocused(lVar2, mutableState2);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        CategorySidebarKt.m6342SidebarRowcR7IE_o(strLiveCategoryLabel, count, null, zA2, z5, aVar, (r7.a) objRememberedValue2, null, null, id, z, zA, 0.0f, 0L, focusRequester, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 12672);
        Composer composer2 = composer;
        if (zA && this.$expanded$inlined) {
            composer2.startReplaceGroup(2041322248);
            for (final LiveCategory liveCategory2 : liveCategory.getChildren()) {
                String strLiveCategoryLabel2 = LiveCategoryKt.liveCategoryLabel(liveCategory2.getLabel(), composer2, 0);
                int count2 = liveCategory2.getCount();
                boolean zA3 = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory2.getId());
                float fM5678constructorimpl = Dp.m5678constructorimpl(40);
                long sp = TextUnitKt.getSp(10.5d);
                FocusRequester focusRequester2 = kotlin.jvm.internal.p.a(this.$selectedId$inlined, liveCategory2.getId()) ? this.$selectedCategoryFocusRequester$inlined : null;
                boolean zChanged3 = composer2.changed(this.$onSelect$inlined) | composer2.changedInstance(liveCategory2);
                Object objRememberedValue3 = composer2.rememberedValue();
                if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    final r7.l lVar3 = this.$onSelect$inlined;
                    objRememberedValue3 = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$11$3$1$1
                        @Override // r7.a
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6347invoke();
                            return x6.t0.f22605a;
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6347invoke() {
                            lVar3.invoke(liveCategory2.getId());
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                r7.a aVar2 = (r7.a) objRememberedValue3;
                boolean zChanged4 = composer2.changed(this.$onTopBoundaryFocusChanged$inlined);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    final r7.l lVar4 = this.$onTopBoundaryFocusChanged$inlined;
                    final MutableState mutableState3 = this.$categoryHasHadFocus$delegate$inlined;
                    objRememberedValue4 = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$16$3$1$11$3$2$1
                        @Override // r7.a
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m6348invoke();
                            return x6.t0.f22605a;
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m6348invoke() {
                            CategorySidebarKt.CategorySidebar$onCategoryFocused(lVar4, mutableState3);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                CategorySidebarKt.m6342SidebarRowcR7IE_o(strLiveCategoryLabel2, count2, null, zA3, true, aVar2, (r7.a) objRememberedValue4, null, null, null, false, false, fM5678constructorimpl, sp, focusRequester2, composer, 24960, 3456, 3968);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(2042108346);
            composer2.endReplaceGroup();
        }
        composer2.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
