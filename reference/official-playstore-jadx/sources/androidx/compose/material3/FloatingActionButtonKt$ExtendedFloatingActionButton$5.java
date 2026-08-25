package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.FabPrimaryTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class FloatingActionButtonKt$ExtendedFloatingActionButton$5 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ p<Composer, Integer, t0> $icon;
    final /* synthetic */ p<Composer, Integer, t0> $text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FloatingActionButtonKt$ExtendedFloatingActionButton$5(boolean z, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
        super(2);
        this.$expanded = z;
        this.$icon = pVar;
        this.$text = pVar2;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1172118032, i10, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:351)");
        }
        Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(SizeKt.m582sizeInqDBjuR0$default(Modifier.INSTANCE, this.$expanded ? FloatingActionButtonKt.ExtendedFabMinimumWidth : FabPrimaryTokens.INSTANCE.m2546getContainerWidthD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), this.$expanded ? FloatingActionButtonKt.ExtendedFabStartIconPadding : Dp.m5678constructorimpl(0), 0.0f, this.$expanded ? FloatingActionButtonKt.ExtendedFabTextPadding : Dp.m5678constructorimpl(0), 0.0f, 10, null);
        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
        Arrangement.Horizontal start = this.$expanded ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
        p<Composer, Integer, t0> pVar = this.$icon;
        boolean z = this.$expanded;
        p<Composer, Integer, t0> pVar2 = this.$text;
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer, 48);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        pVar.invoke(composer, 0);
        AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.composableLambda(composer, 176242764, true, new FloatingActionButtonKt$ExtendedFloatingActionButton$5$1$1(pVar2)), composer, 1600518, 18);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
    }
}
