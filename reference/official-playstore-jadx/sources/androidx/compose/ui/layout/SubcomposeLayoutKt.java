package androidx.compose.ui.layout;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001\u0013\u001a8\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u001d\u0010\u0007\u001a\u0019\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\u001a@\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u001d\u0010\u0007\u001a\u0019\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\t\u0010\r\u001a\u0015\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "measurePolicy", "Lx6/t0;", "SubcomposeLayout", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "state", "(Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "", "maxSlotsToRetainForReuse", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "SubcomposeSlotReusePolicy", "(I)Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "androidx/compose/ui/layout/SubcomposeLayoutKt$ReusedSlotId$1", "ReusedSlotId", "Landroidx/compose/ui/layout/SubcomposeLayoutKt$ReusedSlotId$1;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SubcomposeLayoutKt {
    private static final SubcomposeLayoutKt$ReusedSlotId$1 ReusedSlotId = new Object() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$ReusedSlotId$1
        public String toString() {
            return "ReusedSlotId";
        }
    };

    /* JADX INFO: renamed from: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Modifier modifier, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$measurePolicy = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SubcomposeLayoutKt.SubcomposeLayout(this.$modifier, this.$measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.a<t0> {
        final /* synthetic */ SubcomposeLayoutState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(SubcomposeLayoutState subcomposeLayoutState) {
            super(0);
            this.$state = subcomposeLayoutState;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4705invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4705invoke() {
            this.$state.forceRecomposeChildren$ui_release();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<SubcomposeMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ SubcomposeLayoutState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(SubcomposeLayoutState subcomposeLayoutState, Modifier modifier, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, int i10, int i11) {
            super(2);
            this.$state = subcomposeLayoutState;
            this.$modifier = modifier;
            this.$measurePolicy = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SubcomposeLayoutKt.SubcomposeLayout(this.$state, this.$modifier, this.$measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    public static final void SubcomposeLayout(Modifier modifier, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, Composer composer, int i10, int i11) {
        int i12;
        p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1298353104);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i12 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            pVar2 = pVar;
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier2 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1298353104, i12, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:74)");
            }
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SubcomposeLayoutState();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i14 = i12 << 3;
            pVar2 = pVar;
            SubcomposeLayout((SubcomposeLayoutState) objRememberedValue, modifier2, pVar2, composerStartRestartGroup, (i14 & 112) | 8 | (i14 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(modifier, pVar2, i10, i11));
        }
    }

    public static final SubcomposeSlotReusePolicy SubcomposeSlotReusePolicy(int i10) {
        return new FixedCountSubcomposeSlotReusePolicy(i10);
    }

    public static final void SubcomposeLayout(SubcomposeLayoutState subcomposeLayoutState, Modifier modifier, p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, Composer composer, int i10, int i11) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-511989831);
        if ((i11 & 2) != 0) {
            modifier = Modifier.INSTANCE;
        }
        Modifier modifier2 = modifier;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511989831, i10, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:108)");
        }
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
        CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
        r7.a<LayoutNode> constructor$ui_release = LayoutNode.INSTANCE.getConstructor$ui_release();
        composerStartRestartGroup.startReplaceableGroup(1405779621);
        if (composerStartRestartGroup.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(new SubcomposeLayoutKt$SubcomposeLayout$$inlined$ReusableComposeNode$1(constructor$ui_release));
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
        Updater.m2998setimpl(composerM2991constructorimpl, subcomposeLayoutState, subcomposeLayoutState.getSetRoot$ui_release());
        Updater.m2998setimpl(composerM2991constructorimpl, compositionContextRememberCompositionContext, subcomposeLayoutState.getSetCompositionContext$ui_release());
        Updater.m2998setimpl(composerM2991constructorimpl, pVar, subcomposeLayoutState.getSetMeasurePolicy$ui_release());
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        Updater.m2998setimpl(composerM2991constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
        }
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        if (!composerStartRestartGroup.getSkipping()) {
            EffectsKt.SideEffect(new AnonymousClass4(subcomposeLayoutState), composerStartRestartGroup, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass5(subcomposeLayoutState, modifier2, pVar, i10, i11));
        }
    }
}
