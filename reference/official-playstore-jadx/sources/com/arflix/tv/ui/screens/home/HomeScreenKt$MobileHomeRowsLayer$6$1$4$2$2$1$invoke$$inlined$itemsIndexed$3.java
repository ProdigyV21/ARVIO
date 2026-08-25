package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.MediaCardKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$invoke$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Map $cardLogoUrls$inlined;
    final /* synthetic */ Category $category$inlined;
    final /* synthetic */ boolean $isCollectionRow$inlined;
    final /* synthetic */ boolean $isContinueWatching$inlined;
    final /* synthetic */ boolean $isPortrait$inlined;
    final /* synthetic */ boolean $isRanked$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onItemClick$inlined;
    final /* synthetic */ r7.p $onItemLongClick$inlined;
    final /* synthetic */ r7.l $onLoadMoreCategory$inlined;
    final /* synthetic */ boolean $rowHasMore$inlined;
    final /* synthetic */ float $rowMobileItemWidth$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$invoke$$inlined$itemsIndexed$3(List list, r7.l lVar, Category category, boolean z, r7.p pVar, boolean z5, float f10, boolean z10, Map map, boolean z11, boolean z12, r7.l lVar2) {
        super(4);
        this.$items = list;
        this.$onLoadMoreCategory$inlined = lVar;
        this.$category$inlined = category;
        this.$rowHasMore$inlined = z;
        this.$onItemLongClick$inlined = pVar;
        this.$isRanked$inlined = z5;
        this.$rowMobileItemWidth$inlined = f10;
        this.$isCollectionRow$inlined = z10;
        this.$cardLogoUrls$inlined = map;
        this.$isPortrait$inlined = z11;
        this.$isContinueWatching$inlined = z12;
        this.$onItemClick$inlined = lVar2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        r7.a aVar;
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
        MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(910479815);
        String str = null;
        if (mediaItem.isPlaceholder()) {
            composer.startReplaceGroup(910402996);
            Integer numValueOf = Integer.valueOf(mediaItem.getId());
            boolean zChanged = composer.changed(this.$onLoadMoreCategory$inlined) | composer.changed(this.$category$inlined);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$1$1(this.$onLoadMoreCategory$inlined, this.$category$inlined, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.LaunchedEffect(numValueOf, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue, composer, 0);
            composer.endReplaceGroup();
        } else if (!this.$rowHasMore$inlined || i10 < this.$category$inlined.getItems().size() - 5) {
            composer.startReplaceGroup(910814211);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(910634504);
            Integer numValueOf2 = Integer.valueOf(this.$category$inlined.getItems().size());
            boolean zChanged2 = composer.changed(this.$onLoadMoreCategory$inlined) | composer.changed(this.$category$inlined);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$2$1(this.$onLoadMoreCategory$inlined, this.$category$inlined, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(numValueOf2, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, 0);
            composer.endReplaceGroup();
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(mediaItem, composer, 0);
        Object objRememberedValue3 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue3 == companion.getEmpty()) {
            final r7.l lVar = this.$onItemClick$inlined;
            objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$onCardClick$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6254invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6254invoke() {
                    lVar.invoke(stateRememberUpdatedState.getValue());
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        r7.a aVar2 = (r7.a) objRememberedValue3;
        if (this.$onItemLongClick$inlined != null) {
            composer.startReplaceGroup(911104712);
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                final r7.p pVar = this.$onItemLongClick$inlined;
                final boolean z = this.$isContinueWatching$inlined;
                objRememberedValue4 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$onCardLongClick$1$1
                    @Override // r7.a
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6255invoke();
                        return t0.f22605a;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6255invoke() {
                        pVar.invoke(stateRememberUpdatedState.getValue(), Boolean.valueOf(z));
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceGroup();
            aVar = (r7.a) objRememberedValue4;
        } else {
            composer.startReplaceGroup(911290494);
            composer.endReplaceGroup();
            aVar = null;
        }
        if (!this.$isRanked$inlined || i10 >= 10) {
            r7.a aVar3 = aVar;
            composer.startReplaceGroup(912932286);
            if (!this.$isCollectionRow$inlined) {
                str = (String) this.$cardLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
            }
            float f10 = this.$rowMobileItemWidth$inlined;
            boolean z5 = !this.$isPortrait$inlined;
            boolean z10 = this.$isContinueWatching$inlined;
            boolean z11 = !mediaItem.getCollectionHideTitle();
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$4$1
                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6253invoke() {
                    }

                    @Override // r7.a
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6253invoke();
                        return t0.f22605a;
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            MediaCardKt.m6165MediaCardL7BDBA(mediaItem, f10, z5, str, null, false, false, false, false, z10, z11, false, 0, 0, false, 0.0f, false, (r7.a) objRememberedValue5, aVar2, aVar3, null, composer, 0, 114843648, 0, 1096176);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(911395430);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(companion2, this.$rowMobileItemWidth$inlined);
            composer.startReplaceableGroup(733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (!this.$isCollectionRow$inlined) {
                str = (String) this.$cardLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
            }
            float f11 = this.$rowMobileItemWidth$inlined;
            boolean z12 = !this.$isPortrait$inlined;
            boolean z13 = !mediaItem.getCollectionHideTitle();
            Object objRememberedValue6 = composer.rememberedValue();
            if (objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$3$3$1$1
                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6252invoke() {
                    }

                    @Override // r7.a
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6252invoke();
                        return t0.f22605a;
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            MediaCardKt.m6165MediaCardL7BDBA(mediaItem, f11, z12, str, null, false, false, false, false, false, z13, false, 0, 0, false, 0.0f, false, (r7.a) objRememberedValue6, aVar2, aVar, null, composer, 805306368, 114843648, 0, 1096176);
            HomeScreenKt.TopRankRibbon(i10 + 1, false, true, PaddingKt.m533paddingqDBjuR0$default(ZIndexModifierKt.zIndex(boxScopeInstance.align(companion2, companion3.getTopStart()), 2.0f), Dp.m5678constructorimpl(6), 0.0f, 0.0f, 0.0f, 14, null), composer, 432, 0);
            com.arflix.tv.data.repository.g.q(composer);
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
