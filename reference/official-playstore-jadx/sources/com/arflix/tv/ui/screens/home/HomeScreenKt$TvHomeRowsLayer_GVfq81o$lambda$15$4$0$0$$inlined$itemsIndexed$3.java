package com.arflix.tv.ui.screens.home;

import android.os.SystemClock;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.CardLayoutMode;
import com.arflix.tv.ui.components.CardLayoutModeKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class HomeScreenKt$TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Map $cardLogoUrls$inlined;
    final /* synthetic */ Map $categoryHasMoreMap$inlined;
    final /* synthetic */ float $contentStartPadding$inlined;
    final /* synthetic */ long $featuredTrailerDelayMs$inlined;
    final /* synthetic */ String $featuredTrailerKey$inlined;
    final /* synthetic */ float $featuredTrailerVolume$inlined;
    final /* synthetic */ HomeFocusState $focusState$inlined;
    final /* synthetic */ MutableState $isFastScrolling$delegate$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onItemClick$inlined;
    final /* synthetic */ r7.l $onLoadMoreCategory$inlined;
    final /* synthetic */ int $rowWindowStart$inlined;
    final /* synthetic */ boolean $smoothScrolling$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$$inlined$itemsIndexed$3(List list, int i10, HomeFocusState homeFocusState, r7.l lVar, Map map, float f10, Map map2, boolean z, String str, long j10, float f11, r7.l lVar2, MutableState mutableState) {
        super(4);
        this.$items = list;
        this.$rowWindowStart$inlined = i10;
        this.$focusState$inlined = homeFocusState;
        this.$onLoadMoreCategory$inlined = lVar;
        this.$cardLogoUrls$inlined = map;
        this.$contentStartPadding$inlined = f10;
        this.$categoryHasMoreMap$inlined = map2;
        this.$smoothScrolling$inlined = z;
        this.$featuredTrailerKey$inlined = str;
        this.$featuredTrailerDelayMs$inlined = j10;
        this.$featuredTrailerVolume$inlined = f11;
        this.$onItemClick$inlined = lVar2;
        this.$isFastScrolling$delegate$inlined = mutableState;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        int i13;
        boolean z;
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
        final Category category = (Category) this.$items.get(i10);
        composer.startReplaceGroup(2015625207);
        final int i14 = this.$rowWindowStart$inlined + i10;
        boolean z5 = false;
        boolean z10 = !this.$focusState$inlined.isSidebarFocused() && i14 == this.$focusState$inlined.getCurrentRowIndex();
        boolean zChanged = composer.changed(category.getId());
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = "home:" + category.getId();
            composer.updateRememberedValue(objRememberedValue);
        }
        if (CardLayoutModeKt.rememberCatalogueRowLayoutMode((String) objRememberedValue, composer, 0) == CardLayoutMode.POSTER) {
            i13 = -1;
            z = true;
        } else {
            i13 = -1;
            z = false;
        }
        float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 245 : 202);
        boolean zChanged2 = composer.changed(category.getId());
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onLoadMoreCategory$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$6$2$1$1$3$onRowLoadMore$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6256invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6256invoke() {
                    lVar.invoke(category.getId());
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar = (r7.a) objRememberedValue2;
        boolean zChanged3 = composer.changed(category.getId()) | composer.changed(i14);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final HomeFocusState homeFocusState = this.$focusState$inlined;
            objRememberedValue3 = new r7.p<MediaItem, Integer, t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$6$2$1$1$3$onRowItemFocused$1$1
                @Override // r7.p
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((MediaItem) obj, ((Number) obj2).intValue());
                    return t0.f22605a;
                }

                public final void invoke(MediaItem mediaItem, int i15) {
                    homeFocusState.setCurrentRowIndex(i14);
                    homeFocusState.setCurrentItemIndex(i15);
                    homeFocusState.getRowItemIndicesByCategoryId().put(category.getId(), Integer.valueOf(i15));
                    homeFocusState.setSidebarFocused(false);
                    homeFocusState.setLastNavEventTime(SystemClock.elapsedRealtime());
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        r7.p pVar = (r7.p) objRememberedValue3;
        Modifier modifierClipToBounds = ClipKt.clipToBounds(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), fM5678constructorimpl));
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierClipToBounds);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Map map = this.$cardLogoUrls$inlined;
        boolean zT = kotlin.text.o.T(category.getTitle(), "Top 10", true);
        float f10 = this.$contentStartPadding$inlined;
        boolean zA = kotlin.jvm.internal.p.a(this.$categoryHasMoreMap$inlined.get(category.getId()), Boolean.TRUE);
        int currentItemIndex = i13;
        boolean z11 = this.$smoothScrolling$inlined;
        if (z10) {
            currentItemIndex = this.$focusState$inlined.getCurrentItemIndex();
        }
        if (z10 && HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$12(this.$isFastScrolling$delegate$inlined)) {
            z5 = true;
        }
        HomeScreenKt.m6239ContentRowIOMxRjY(category, map, z10, zT, z, f10, zA, z11, aVar, currentItemIndex, z5, z10 ? this.$featuredTrailerKey$inlined : null, this.$featuredTrailerDelayMs$inlined, this.$featuredTrailerVolume$inlined, this.$onItemClick$inlined, pVar, composer, 0, 0, 0);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
