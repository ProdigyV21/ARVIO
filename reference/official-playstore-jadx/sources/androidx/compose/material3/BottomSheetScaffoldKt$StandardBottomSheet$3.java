package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BottomSheetScaffoldKt$StandardBottomSheet$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ SheetState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$StandardBottomSheet$3(p<? super Composer, ? super Integer, t0> pVar, SheetState sheetState, boolean z, k0 k0Var, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$dragHandle = pVar;
        this.$state = sheetState;
        this.$sheetSwipeEnabled = z;
        this.$scope = k0Var;
        this.$content = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        Object bottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1;
        Alignment.Companion companion;
        int i11;
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1070542936, i10, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:271)");
        }
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
        p<Composer, Integer, t0> pVar = this.$dragHandle;
        SheetState sheetState = this.$state;
        boolean z = this.$sheetSwipeEnabled;
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
        composer.startReplaceableGroup(-176229282);
        if (pVar != null) {
            Strings.Companion companion5 = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer, 0);
            String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer, 0);
            String strM1994getStringNWtq283 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer, 0);
            Modifier modifierAlign = columnScopeInstance.align(companion2, companion3.getCenterHorizontally());
            composer.startReplaceableGroup(-176228795);
            boolean zChanged = composer.changed(sheetState) | composer.changed(z) | composer.changed(strM1994getStringNWtq283) | composer.changedInstance(k0Var) | composer.changed(strM1994getStringNWtq28) | composer.changed(strM1994getStringNWtq282);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                companion = companion3;
                i11 = -1323940314;
                bottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1 = new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1(sheetState, z, strM1994getStringNWtq283, strM1994getStringNWtq28, strM1994getStringNWtq282, k0Var);
                composer.updateRememberedValue(bottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1);
            } else {
                bottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1 = objRememberedValue;
                companion = companion3;
                i11 = -1323940314;
            }
            composer.endReplaceableGroup();
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierAlign, true, (l) bottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1);
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
