package androidx.compose.foundation.lazy.staggeredgrid;

import a0.c;
import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.arflix.tv.data.repository.g;
import d7.k;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0089\u0001\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "slots", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior", "userScrollEnabled", "Landroidx/compose/ui/unit/Dp;", "mainAxisSpacing", "crossAxisSpacing", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lx6/t0;", "Lx6/n;", "content", "LazyStaggeredGrid-LJWHXA8", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/FlingBehavior;ZFFLr7/l;Landroidx/compose/runtime/Composer;III)V", "LazyStaggeredGrid", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridKt {
    /* JADX INFO: renamed from: LazyStaggeredGrid-LJWHXA8, reason: not valid java name */
    public static final void m719LazyStaggeredGridLJWHXA8(LazyStaggeredGridState lazyStaggeredGridState, Orientation orientation, LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider, Modifier modifier, PaddingValues paddingValues, boolean z, FlingBehavior flingBehavior, boolean z5, float f10, float f11, l<? super LazyStaggeredGridScope, t0> lVar, Composer composer, int i10, int i11, int i12) {
        FlingBehavior flingBehavior2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(288295126);
        Modifier modifier2 = (i12 & 8) != 0 ? Modifier.INSTANCE : modifier;
        PaddingValues paddingValuesM522PaddingValues0680j_4 = (i12 & 16) != 0 ? PaddingKt.m522PaddingValues0680j_4(Dp.m5678constructorimpl(0)) : paddingValues;
        boolean z10 = (i12 & 32) != 0 ? false : z;
        if ((i12 & 64) != 0) {
            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
            i13 = i10 & (-3670017);
        } else {
            flingBehavior2 = flingBehavior;
            i13 = i10;
        }
        boolean z11 = (i12 & 128) != 0 ? true : z5;
        float fM5678constructorimpl = (i12 & 256) != 0 ? Dp.m5678constructorimpl(0) : f10;
        float fM5678constructorimpl2 = (i12 & 512) != 0 ? Dp.m5678constructorimpl(0) : f11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(288295126, i13, i11, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:61)");
        }
        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
        OverscrollEffect overscrollEffect = scrollableDefaults.overscrollEffect(composerStartRestartGroup, 6);
        r7.a<LazyStaggeredGridItemProvider> aVarRememberStaggeredGridItemProviderLambda = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(lazyStaggeredGridState, lVar, composerStartRestartGroup, ((i11 << 3) & 112) | 8);
        Object objG = c.g(773894976, composerStartRestartGroup, -492369756);
        if (objG == Composer.INSTANCE.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
        }
        composerStartRestartGroup.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composerStartRestartGroup.endReplaceableGroup();
        int i14 = i13 >> 6;
        int i15 = (i14 & 896) | 16777224;
        int i16 = i14 & 7168;
        int i17 = i13 >> 9;
        int i18 = i13;
        boolean z12 = z10;
        float f12 = fM5678constructorimpl;
        float f13 = fM5678constructorimpl2;
        p<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> pVarM729rememberStaggeredGridMeasurePolicy1tP8Re8 = LazyStaggeredGridMeasurePolicyKt.m729rememberStaggeredGridMeasurePolicy1tP8Re8(lazyStaggeredGridState, aVarRememberStaggeredGridItemProviderLambda, paddingValuesM522PaddingValues0680j_4, z12, orientation, f12, f13, coroutineScope, lazyGridStaggeredGridSlotsProvider, composerStartRestartGroup, i15 | i16 | ((i13 << 9) & 57344) | (i17 & 458752) | (i17 & 3670016) | ((i13 << 18) & 234881024));
        PaddingValues paddingValues2 = paddingValuesM522PaddingValues0680j_4;
        LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyStaggeredGridSemanticState = LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(lazyStaggeredGridState, z12, composerStartRestartGroup, ((i18 >> 12) & 112) | 8);
        boolean z13 = z11;
        FlingBehavior flingBehavior3 = flingBehavior2;
        LazyLayoutKt.LazyLayout(aVarRememberStaggeredGridItemProviderLambda, ScrollableKt.scrollable$default(OverscrollKt.overscroll(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier2.then(lazyStaggeredGridState.getRemeasurementModifier()).then(lazyStaggeredGridState.getAwaitLayoutModifier()), aVarRememberStaggeredGridItemProviderLambda, lazyLayoutSemanticStateRememberLazyStaggeredGridSemanticState, orientation, z13, z12, composerStartRestartGroup, ((i18 << 6) & 7168) | (i17 & 57344) | (i18 & 458752)), orientation), LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(lazyStaggeredGridState, composerStartRestartGroup, 8), lazyStaggeredGridState.getBeyondBoundsInfo(), z12, (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, z13, composerStartRestartGroup, ((i18 >> 3) & 3670016) | (MutableVector.$stable << 6) | i16 | ((i18 << 12) & 458752)), overscrollEffect), lazyStaggeredGridState, orientation, overscrollEffect, z13, scrollableDefaults.reverseDirection((LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, z12), flingBehavior3, lazyStaggeredGridState.getMutableInteractionSource(), null, 128, null), lazyStaggeredGridState.getPrefetchState(), pVarM729rememberStaggeredGridMeasurePolicy1tP8Re8, composerStartRestartGroup, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new LazyStaggeredGridKt$LazyStaggeredGrid$1(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier2, paddingValues2, z12, flingBehavior3, z13, f12, f13, lVar, i10, i11, i12));
        }
    }
}
