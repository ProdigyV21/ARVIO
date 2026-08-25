package androidx.compose.material3;

import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ModalBottomSheet_androidKt$ModalBottomSheet$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ r7.a<t0> $animateToDismiss;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ long $scrimColor;
    final /* synthetic */ l<Float, t0> $settleToDismiss;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ float $sheetMaxWidth;
    final /* synthetic */ SheetState $sheetState;
    final /* synthetic */ float $tonalElevation;

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$3$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements q<BoxWithConstraintsScope, Composer, Integer, t0> {
        final /* synthetic */ r7.a<t0> $animateToDismiss;
        final /* synthetic */ long $containerColor;
        final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
        final /* synthetic */ long $contentColor;
        final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ long $scrimColor;
        final /* synthetic */ l<Float, t0> $settleToDismiss;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ float $sheetMaxWidth;
        final /* synthetic */ SheetState $sheetState;
        final /* synthetic */ float $tonalElevation;

        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ r7.a<t0> $animateToDismiss;
            final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
            final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
            final /* synthetic */ k0 $scope;
            final /* synthetic */ SheetState $sheetState;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass5(p<? super Composer, ? super Integer, t0> pVar, SheetState sheetState, r7.a<t0> aVar, k0 k0Var, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
                super(2);
                this.$dragHandle = pVar;
                this.$sheetState = sheetState;
                this.$animateToDismiss = aVar;
                this.$scope = k0Var;
                this.$content = qVar;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                Object modalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1;
                Alignment.Companion companion;
                int i11;
                if ((i10 & 3) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1096570852, i10, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:230)");
                }
                Modifier.Companion companion2 = Modifier.INSTANCE;
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
                p<Composer, Integer, t0> pVar = this.$dragHandle;
                SheetState sheetState = this.$sheetState;
                r7.a<t0> aVar = this.$animateToDismiss;
                k0 k0Var = this.$scope;
                q<ColumnScope, Composer, Integer, t0> qVar = this.$content;
                composer.startReplaceableGroup(-483455358);
                Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
                Alignment.Companion companion3 = Alignment.INSTANCE;
                MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, top, composer, 0, -1323940314);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor = companion4.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
                p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
                if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
                }
                a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                composer.startReplaceableGroup(-11289086);
                if (pVar != null) {
                    Strings.Companion companion5 = Strings.INSTANCE;
                    String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
                    String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
                    String strM1994getStringNWtq283 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
                    Modifier modifierAlign = columnScopeInstance.align(companion2, companion3.getCenterHorizontally());
                    composer.startReplaceableGroup(-11288530);
                    boolean zChanged = composer.changed(sheetState) | composer.changed(strM1994getStringNWtq282) | composer.changed(aVar) | composer.changed(strM1994getStringNWtq283) | composer.changedInstance(k0Var) | composer.changed(strM1994getStringNWtq28);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        companion = companion3;
                        i11 = -1323940314;
                        modalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1 = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1(sheetState, strM1994getStringNWtq282, strM1994getStringNWtq283, strM1994getStringNWtq28, aVar, k0Var);
                        composer.updateRememberedValue(modalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1);
                    } else {
                        modalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1 = objRememberedValue;
                        companion = companion3;
                        i11 = -1323940314;
                    }
                    composer.endReplaceableGroup();
                    Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierAlign, true, (l) modalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1);
                    composer.startReplaceableGroup(733328855);
                    MeasurePolicy measurePolicyD = a0.c.d(companion, false, composer, 0, i11);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                    r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
                    q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierSemantics);
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
                    p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                    if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                    }
                    a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    pVar.invoke(composer, 0);
                    composer.endReplaceableGroup();
                    composer.endNode();
                    composer.endReplaceableGroup();
                    composer.endReplaceableGroup();
                }
                composer.endReplaceableGroup();
                qVar.invoke(columnScopeInstance, composer, 6);
                composer.endReplaceableGroup();
                composer.endNode();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(long j10, r7.a<t0> aVar, SheetState sheetState, Modifier modifier, float f10, l<? super Float, t0> lVar, Shape shape, long j11, long j12, float f11, p<? super Composer, ? super Integer, t0> pVar, k0 k0Var, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
            super(3);
            this.$scrimColor = j10;
            this.$animateToDismiss = aVar;
            this.$sheetState = sheetState;
            this.$modifier = modifier;
            this.$sheetMaxWidth = f10;
            this.$settleToDismiss = lVar;
            this.$shape = shape;
            this.$containerColor = j11;
            this.$contentColor = j12;
            this.$tonalElevation = f11;
            this.$dragHandle = pVar;
            this.$scope = k0Var;
            this.$content = qVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((BoxWithConstraintsScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = i10 | (composer.changed(boxWithConstraintsScope) ? 4 : 2);
            } else {
                i11 = i10;
            }
            if ((i11 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2008499679, i11, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:184)");
            }
            int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(boxWithConstraintsScope.mo465getConstraintsmsEJaDk());
            ModalBottomSheet_androidKt.m1685Scrim3JVO9M(this.$scrimColor, this.$animateToDismiss, this.$sheetState.getTargetValue() != SheetValue.Hidden, composer, 0);
            Strings.Companion companion = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_pane_title), composer, 0);
            Modifier modifierAlign = boxWithConstraintsScope.align(SizeKt.fillMaxWidth$default(SizeKt.m585widthInVpY3zN4$default(this.$modifier, 0.0f, this.$sheetMaxWidth, 1, null), 0.0f, 1, null), Alignment.INSTANCE.getTopCenter());
            composer.startReplaceableGroup(-1482644208);
            boolean zChanged = composer.changed(strM1994getStringNWtq28);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$1$1(strM1994getStringNWtq28);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierAlign, false, (l) objRememberedValue, 1, null);
            composer.startReplaceableGroup(-1482644143);
            boolean zChanged2 = composer.changed(this.$sheetState);
            SheetState sheetState = this.$sheetState;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$2$1(sheetState);
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            Modifier modifierOffset = OffsetKt.offset(modifierSemantics$default, (l) objRememberedValue2);
            composer.startReplaceableGroup(-1482643839);
            boolean zChanged3 = composer.changed(this.$sheetState);
            SheetState sheetState2 = this.$sheetState;
            l<Float, t0> lVar = this.$settleToDismiss;
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, lVar);
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            Modifier modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(modifierOffset, (NestedScrollConnection) objRememberedValue3, null, 2, null);
            DraggableState draggableState = this.$sheetState.getAnchoredDraggableState$material3_release().getDraggableState();
            Orientation orientation = Orientation.Vertical;
            boolean zIsVisible = this.$sheetState.isVisible();
            boolean zIsAnimationRunning = this.$sheetState.getAnchoredDraggableState$material3_release().isAnimationRunning();
            composer.startReplaceableGroup(-1482643097);
            boolean zChanged4 = composer.changed(this.$settleToDismiss);
            l<Float, t0> lVar2 = this.$settleToDismiss;
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1(lVar2, null);
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceableGroup();
            SurfaceKt.m2004SurfaceT9BRK9s(ModalBottomSheet_androidKt.modalBottomSheetAnchors(DraggableKt.draggable$default(modifierNestedScroll$default, draggableState, orientation, zIsVisible, null, zIsAnimationRunning, null, (q) objRememberedValue4, false, 168, null), this.$sheetState, iM5645getMaxHeightimpl), this.$shape, this.$containerColor, this.$contentColor, this.$tonalElevation, 0.0f, null, ComposableLambdaKt.composableLambda(composer, 1096570852, true, new AnonymousClass5(this.$dragHandle, this.$sheetState, this.$animateToDismiss, this.$scope, this.$content)), composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ModalBottomSheet_androidKt$ModalBottomSheet$3(long j10, r7.a<t0> aVar, SheetState sheetState, Modifier modifier, float f10, l<? super Float, t0> lVar, Shape shape, long j11, long j12, float f11, p<? super Composer, ? super Integer, t0> pVar, k0 k0Var, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$scrimColor = j10;
        this.$animateToDismiss = aVar;
        this.$sheetState = sheetState;
        this.$modifier = modifier;
        this.$sheetMaxWidth = f10;
        this.$settleToDismiss = lVar;
        this.$shape = shape;
        this.$containerColor = j11;
        this.$contentColor = j12;
        this.$tonalElevation = f11;
        this.$dragHandle = pVar;
        this.$scope = k0Var;
        this.$content = qVar;
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
            ComposerKt.traceEventStart(-1311525899, i10, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:183)");
        }
        BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, ComposableLambdaKt.composableLambda(composer, 2008499679, true, new AnonymousClass1(this.$scrimColor, this.$animateToDismiss, this.$sheetState, this.$modifier, this.$sheetMaxWidth, this.$settleToDismiss, this.$shape, this.$containerColor, this.$contentColor, this.$tonalElevation, this.$dragHandle, this.$scope, this.$content)), composer, 3078, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
