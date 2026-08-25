package androidx.tv.foundation.lazy.layout;

import a0.c;
import a8.g0;
import a8.l0;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigation.compose.h;
import androidx.tv.material3.d4;
import com.arflix.tv.data.repository.g;
import d7.k;
import i2.m;
import i2.o;
import i2.p;
import ka.k0;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001aA\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProviderLambda", "Li2/m;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "userScrollEnabled", "reverseScrolling", "lazyLayoutSemantics", "(Landroidx/compose/ui/Modifier;Lr7/a;Li2/m;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt {
    public static final Modifier lazyLayoutSemantics(Modifier modifier, a<? extends LazyLayoutItemProvider> aVar, m mVar, Orientation orientation, boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1093700445);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1093700445, i10, -1, "androidx.tv.foundation.lazy.layout.lazyLayoutSemantics (LazyLayoutSemantics.kt:50)");
        }
        Object objG = c.g(773894976, composer, -492369756);
        Composer.Companion companion = Composer.INSTANCE;
        if (objG == companion.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-1614350787);
        boolean z10 = ((((57344 & i10) ^ 24576) > 16384 && composer.changed(z)) || (i10 & 24576) == 16384) | ((((i10 & 112) ^ 48) > 32 && composer.changed(aVar)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(mVar)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i10 & 7168) ^ 3072) > 2048 && composer.changed(orientation)) || (i10 & 3072) == 2048);
        Object objRememberedValue = composer.rememberedValue();
        if (z10 || objRememberedValue == companion.getEmpty()) {
            boolean z11 = orientation == Orientation.Vertical;
            objRememberedValue = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new o(new d4(1, aVar), z11, new ScrollAxisRange(new g0(mVar, 22), new l0(aVar, mVar, 15), z5), z ? new p(z11, coroutineScope, mVar) : null, z ? new h(aVar, coroutineScope, mVar, 7) : null, mVar.collectionInfo()), 1, null);
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
