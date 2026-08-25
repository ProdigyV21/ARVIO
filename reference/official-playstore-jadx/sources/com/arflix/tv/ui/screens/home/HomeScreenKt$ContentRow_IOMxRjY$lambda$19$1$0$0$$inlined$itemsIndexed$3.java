package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
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
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.MediaCardKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class HomeScreenKt$ContentRow_IOMxRjY$lambda$19$1$0$0$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Map $cardLogoUrls$inlined;
    final /* synthetic */ Category $category$inlined;
    final /* synthetic */ boolean $effectiveCategoryHasMore$inlined;
    final /* synthetic */ boolean $effectivePosterMode$inlined;
    final /* synthetic */ boolean $featuredExpanded$inlined;
    final /* synthetic */ String $featuredTrailerKey$inlined;
    final /* synthetic */ float $featuredTrailerVolume$inlined;
    final /* synthetic */ int $focusedCardIndex$inlined;
    final /* synthetic */ boolean $hasFeaturedCard$inlined;
    final /* synthetic */ boolean $isCollectionRow$inlined;
    final /* synthetic */ boolean $isContinueWatching$inlined;
    final /* synthetic */ boolean $isCurrentRow$inlined;
    final /* synthetic */ boolean $isFastScrolling$inlined;
    final /* synthetic */ boolean $isRanked$inlined;
    final /* synthetic */ float $itemWidth$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ State $latestOnItemClick$inlined;
    final /* synthetic */ State $latestOnItemFocused$inlined;
    final /* synthetic */ r7.a $onLoadMore$inlined;
    final /* synthetic */ boolean $railFocusOverlayActive$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$ContentRow_IOMxRjY$lambda$19$1$0$0$$inlined$itemsIndexed$3(List list, r7.a aVar, boolean z, Category category, boolean z5, int i10, boolean z10, boolean z11, Map map, boolean z12, boolean z13, float f10, String str, float f11, boolean z14, boolean z15, boolean z16, boolean z17, State state, State state2) {
        super(4);
        this.$items = list;
        this.$onLoadMore$inlined = aVar;
        this.$effectiveCategoryHasMore$inlined = z;
        this.$category$inlined = category;
        this.$isCurrentRow$inlined = z5;
        this.$focusedCardIndex$inlined = i10;
        this.$isRanked$inlined = z10;
        this.$isCollectionRow$inlined = z11;
        this.$cardLogoUrls$inlined = map;
        this.$hasFeaturedCard$inlined = z12;
        this.$featuredExpanded$inlined = z13;
        this.$itemWidth$inlined = f10;
        this.$featuredTrailerKey$inlined = str;
        this.$featuredTrailerVolume$inlined = f11;
        this.$effectivePosterMode$inlined = z14;
        this.$isFastScrolling$inlined = z15;
        this.$railFocusOverlayActive$inlined = z16;
        this.$isContinueWatching$inlined = z17;
        this.$latestOnItemFocused$inlined = state;
        this.$latestOnItemClick$inlined = state2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        String str;
        boolean z;
        AnimationSpec animationSpecSnap$default;
        boolean z5;
        String str2;
        Composer composer2;
        boolean z10 = false;
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
        composer.startReplaceGroup(1715512627);
        if (mediaItem.isPlaceholder()) {
            composer.startReplaceGroup(1715331183);
            Integer numValueOf = Integer.valueOf(mediaItem.getId());
            boolean zChanged = composer.changed(this.$onLoadMore$inlined);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new HomeScreenKt$ContentRow$6$2$1$1$3$1$1(this.$onLoadMore$inlined, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.LaunchedEffect(numValueOf, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue, composer, 0);
            composer.endReplaceGroup();
        } else if (!this.$effectiveCategoryHasMore$inlined || i10 < this.$category$inlined.getItems().size() - 5) {
            composer.startReplaceGroup(1715656683);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1715525987);
            Integer numValueOf2 = Integer.valueOf(this.$category$inlined.getItems().size());
            boolean zChanged2 = composer.changed(this.$onLoadMore$inlined);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new HomeScreenKt$ContentRow$6$2$1$1$3$2$1(this.$onLoadMore$inlined, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(numValueOf2, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, 0);
            composer.endReplaceGroup();
        }
        boolean z11 = this.$isCurrentRow$inlined && i10 == this.$focusedCardIndex$inlined;
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(mediaItem, composer, 0);
        boolean z12 = (((i12 & 112) ^ 48) > 32 && composer.changed(i10)) || (i12 & 48) == 32;
        Object objRememberedValue3 = composer.rememberedValue();
        if (z12 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final State state = this.$latestOnItemFocused$inlined;
            objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$6$2$1$1$3$onCardFocused$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6250invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6250invoke() {
                    state.getValue().invoke(stateRememberUpdatedState.getValue(), Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        r7.a aVar = (r7.a) objRememberedValue3;
        Object objRememberedValue4 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue4 == companion.getEmpty()) {
            final State state2 = this.$latestOnItemClick$inlined;
            objRememberedValue4 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$6$2$1$1$3$onCardClick$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6249invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6249invoke() {
                    state2.getValue().invoke(stateRememberUpdatedState.getValue());
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        r7.a aVar2 = (r7.a) objRememberedValue4;
        if (!this.$isRanked$inlined || i10 >= 10) {
            boolean z13 = z11;
            composer.startReplaceGroup(1719959173);
            if (this.$isCollectionRow$inlined) {
                str = null;
            } else {
                str = (String) this.$cardLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
            }
            boolean z14 = this.$hasFeaturedCard$inlined && z13 && this.$featuredExpanded$inlined;
            float fM5678constructorimpl = z14 ? Dp.m5678constructorimpl(380) : this.$itemWidth$inlined;
            if (z14) {
                animationSpecSnap$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                z = true;
            } else {
                z = true;
                animationSpecSnap$default = AnimationSpecKt.snap$default(0, 1, null);
            }
            boolean z15 = z;
            State<Dp> stateM81animateDpAsStateAjpBEmI = AnimateAsStateKt.m81animateDpAsStateAjpBEmI(fM5678constructorimpl, animationSpecSnap$default, "featuredCardWidth", null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (z14) {
                composer.startReplaceGroup(1720445470);
                MediaCardKt.m6164FeaturedMediaCardZUYZQmM(mediaItem, HomeScreenKt.ContentRow_IOMxRjY$lambda$19$1$0$0$2$8(stateM81animateDpAsStateAjpBEmI), Dp.m5678constructorimpl(146), this.$featuredTrailerKey$inlined, 0L, this.$featuredTrailerVolume$inlined, aVar2, composer, 1597824);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1721043863);
                MediaCardKt.m6165MediaCardL7BDBA(mediaItem, this.$itemWidth$inlined, !this.$effectivePosterMode$inlined, str, null, (this.$isCollectionRow$inlined || this.$isFastScrolling$inlined) ? false : z15, false, true, !this.$isFastScrolling$inlined, this.$isContinueWatching$inlined, (!this.$isCollectionRow$inlined || mediaItem.getCollectionHideTitle()) ? false : z15, false, 0, 0, (!z13 || this.$railFocusOverlayActive$inlined) ? false : z15, 1.0f, false, aVar, aVar2, null, null, composer, 14155776, 102432768, 0, 1587216);
                composer.endReplaceGroup();
            }
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1716232880);
            if (this.$isCollectionRow$inlined) {
                str2 = null;
                z5 = true;
            } else {
                z5 = true;
                str2 = (String) this.$cardLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
            }
            if (this.$hasFeaturedCard$inlined && z11 && this.$featuredExpanded$inlined) {
                composer.startReplaceGroup(1716413486);
                Object objRememberedValue5 = composer.rememberedValue();
                if (objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = AnimatableKt.Animatable$default(this.$itemWidth$inlined, 0.0f, 2, null);
                    composer.updateRememberedValue(objRememberedValue5);
                }
                Animatable animatable = (Animatable) objRememberedValue5;
                boolean zChangedInstance = composer.changedInstance(animatable);
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue6 == companion.getEmpty()) {
                    objRememberedValue6 = new HomeScreenKt$ContentRow$6$2$1$1$3$3$1(animatable, null);
                    composer.updateRememberedValue(objRememberedValue6);
                }
                EffectsKt.LaunchedEffect(t0.f22605a, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue6, composer, 6);
                float fM5678constructorimpl2 = Dp.m5678constructorimpl(((Number) animatable.getValue()).floatValue());
                Modifier.Companion companion2 = Modifier.INSTANCE;
                Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(companion2, fM5678constructorimpl2);
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
                a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                MediaCardKt.m6164FeaturedMediaCardZUYZQmM(mediaItem, fM5678constructorimpl2, Dp.m5678constructorimpl(146), this.$featuredTrailerKey$inlined, 0L, this.$featuredTrailerVolume$inlined, aVar2, composer, 1597824);
                composer2 = composer;
                HomeScreenKt.TopRankRibbon(i10 + 1, z11, !this.$effectivePosterMode$inlined, PaddingKt.m533paddingqDBjuR0$default(ZIndexModifierKt.zIndex(boxScopeInstance.align(companion2, companion3.getTopStart()), 2.0f), Dp.m5678constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), composer2, 0, 0);
                com.arflix.tv.data.repository.g.q(composer2);
            } else {
                boolean z16 = z11;
                composer.startReplaceGroup(1718043807);
                Modifier.Companion companion5 = Modifier.INSTANCE;
                Modifier modifierM583width3ABfNKs2 = SizeKt.m583width3ABfNKs(companion5, this.$itemWidth$inlined);
                composer.startReplaceableGroup(733328855);
                Alignment.Companion companion6 = Alignment.INSTANCE;
                MeasurePolicy measurePolicyD2 = a0.c.d(companion6, false, composer, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor2 = companion7.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs2);
                if (composer.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor2);
                } else {
                    composer.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
                r7.p pVarU2 = a0.c.u(companion7, composerM2991constructorimpl2, measurePolicyD2, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                boolean z17 = !this.$effectivePosterMode$inlined;
                boolean z18 = !this.$isFastScrolling$inlined;
                boolean z19 = (!this.$isCollectionRow$inlined || mediaItem.getCollectionHideTitle()) ? false : z5;
                boolean z20 = (!z16 || this.$railFocusOverlayActive$inlined) ? false : z5;
                if (!this.$isCollectionRow$inlined && !this.$isFastScrolling$inlined) {
                    z10 = z5;
                }
                MediaCardKt.m6165MediaCardL7BDBA(mediaItem, this.$itemWidth$inlined, z17, str2, null, z10, false, true, z18, false, z19, false, 0, 0, z20, 1.0f, false, aVar, aVar2, null, null, composer, 819462144, 102432768, 0, 1587216);
                composer2 = composer;
                HomeScreenKt.TopRankRibbon(i10 + 1, z16, !this.$effectivePosterMode$inlined, PaddingKt.m533paddingqDBjuR0$default(ZIndexModifierKt.zIndex(boxScopeInstance2.align(companion5, companion6.getTopStart()), 2.0f), Dp.m5678constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), composer2, 0, 0);
                com.arflix.tv.data.repository.g.q(composer2);
            }
            composer2.endReplaceGroup();
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
