package androidx.compose.material3.pulltorefresh;

import a0.c;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class PullToRefreshKt$PullToRefreshContainer$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<PullToRefreshState, Composer, Integer, t0> $indicator;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ State<Boolean> $showElevation;
    final /* synthetic */ PullToRefreshState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PullToRefreshKt$PullToRefreshContainer$1(Modifier modifier, PullToRefreshState pullToRefreshState, State<Boolean> state, Shape shape, long j10, q<? super PullToRefreshState, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$modifier = modifier;
        this.$state = pullToRefreshState;
        this.$showElevation = state;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$indicator = qVar;
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
            ComposerKt.traceEventStart(935555266, i10, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshContainer.<anonymous> (PullToRefresh.kt:122)");
        }
        Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(this.$modifier, PullToRefreshKt.getSpinnerContainerSize());
        composer.startReplaceableGroup(-1737250521);
        boolean zChanged = composer.changed(this.$state);
        PullToRefreshState pullToRefreshState = this.$state;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PullToRefreshKt$PullToRefreshContainer$1$1$1(pullToRefreshState);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(ShadowKt.m3146shadows4CzXII$default(GraphicsLayerModifierKt.graphicsLayer(modifierM578size3ABfNKs, (l) objRememberedValue), this.$showElevation.getValue().booleanValue() ? PullToRefreshKt.Elevation : Dp.m5678constructorimpl(0), this.$shape, true, 0L, 0L, 24, null), this.$containerColor, this.$shape);
        q<PullToRefreshState, Composer, Integer, t0> qVar = this.$indicator;
        PullToRefreshState pullToRefreshState2 = this.$state;
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
        p pVarU = c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        qVar.invoke(pullToRefreshState2, composer, 0);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
