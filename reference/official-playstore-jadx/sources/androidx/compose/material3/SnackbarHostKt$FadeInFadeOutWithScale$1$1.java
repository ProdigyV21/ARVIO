package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "children", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnackbarHostKt$FadeInFadeOutWithScale$1$1 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
    final /* synthetic */ SnackbarData $current;
    final /* synthetic */ SnackbarData $key;
    final /* synthetic */ List<SnackbarData> $keys;
    final /* synthetic */ FadeInFadeOutState<SnackbarData> $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnackbarHostKt$FadeInFadeOutWithScale$1$1(SnackbarData snackbarData, SnackbarData snackbarData2, List<SnackbarData> list, FadeInFadeOutState<SnackbarData> fadeInFadeOutState) {
        super(3);
        this.$key = snackbarData;
        this.$current = snackbarData2;
        this.$keys = list;
        this.$state = fadeInFadeOutState;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((p<? super Composer, ? super Integer, t0>) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer.changedInstance(pVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1654683077, i11, -1, "androidx.compose.material3.FadeInFadeOutWithScale.<anonymous>.<anonymous> (SnackbarHost.kt:357)");
        }
        boolean zA = kotlin.jvm.internal.p.a(this.$key, this.$current);
        int i12 = zA ? 150 : 75;
        int i13 = (!zA || ListUtilsKt.fastFilterNotNull(this.$keys).size() == 1) ? 0 : 75;
        TweenSpec tweenSpecTween = AnimationSpecKt.tween(i12, i13, EasingKt.getLinearEasing());
        composer.startReplaceableGroup(870026295);
        boolean zChanged = composer.changed(this.$key) | composer.changedInstance(this.$state);
        SnackbarData snackbarData = this.$key;
        FadeInFadeOutState<SnackbarData> fadeInFadeOutState = this.$state;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnackbarHostKt$FadeInFadeOutWithScale$1$1$opacity$1$1(snackbarData, fadeInFadeOutState);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        State stateAnimatedOpacity = SnackbarHostKt.animatedOpacity(tweenSpecTween, zA, (r7.a) objRememberedValue, composer, 0, 0);
        State stateAnimatedScale = SnackbarHostKt.animatedScale(AnimationSpecKt.tween(i12, i13, EasingKt.getFastOutSlowInEasing()), zA, composer, 0);
        Modifier modifierM3633graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(Modifier.INSTANCE, ((Number) stateAnimatedScale.getValue()).floatValue(), ((Number) stateAnimatedScale.getValue()).floatValue(), ((Number) stateAnimatedOpacity.getValue()).floatValue(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131064, null);
        composer.startReplaceableGroup(870027225);
        boolean zChanged2 = composer.changed(this.$key);
        SnackbarData snackbarData2 = this.$key;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new SnackbarHostKt$FadeInFadeOutWithScale$1$1$1$1(snackbarData2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM3633graphicsLayerAp8cVGQ$default, false, (l) objRememberedValue2, 1, null);
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierSemantics$default);
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
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        pVar.invoke(composer, Integer.valueOf(i11 & 14));
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
