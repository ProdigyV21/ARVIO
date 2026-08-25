package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import com.arflix.tv.data.repository.g;
import d7.k;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001aA\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProviderLambda", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "userScrollEnabled", "reverseScrolling", "lazyLayoutSemantics", "(Landroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt {
    public static final Modifier lazyLayoutSemantics(Modifier modifier, r7.a<? extends LazyLayoutItemProvider> aVar, LazyLayoutSemanticState lazyLayoutSemanticState, Orientation orientation, boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1070136913);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1070136913, i10, -1, "androidx.compose.foundation.lazy.layout.lazyLayoutSemantics (LazyLayoutSemantics.kt:46)");
        }
        Object objG = a0.c.g(773894976, composer, -492369756);
        if (objG == Composer.INSTANCE.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        Object[] objArr = {aVar, lazyLayoutSemanticState, orientation, Boolean.valueOf(z)};
        composer.startReplaceableGroup(-568225417);
        boolean zChanged = false;
        for (int i11 = 0; i11 < 4; i11++) {
            zChanged |= composer.changed(objArr[i11]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            boolean z10 = orientation == Orientation.Vertical;
            objRememberedValue = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$1(new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$indexForKeyMapping$1(aVar), z10, new ScrollAxisRange(new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$accessibilityScrollState$1(lazyLayoutSemanticState), new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$accessibilityScrollState$2(lazyLayoutSemanticState), z5), z ? new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollByAction$1(z10, coroutineScope, lazyLayoutSemanticState) : null, z ? new LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollToIndexAction$1(aVar, coroutineScope, lazyLayoutSemanticState) : null, lazyLayoutSemanticState.collectionInfo()), 1, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then((Modifier) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }
}
