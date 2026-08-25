package androidx.compose.foundation.text2;

import a0.c;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.HeightInLinesModifierKt;
import androidx.compose.foundation.text.TextFieldSizeKt;
import androidx.compose.foundation.text2.input.TextFieldLineLimits;
import androidx.compose.foundation.text2.input.internal.TextFieldCoreModifier;
import androidx.compose.foundation.text2.input.internal.TextFieldTextLayoutModifier;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BasicTextField2Kt$BasicTextField2$5$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ Brush $cursorBrush;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ boolean $isFocused;
    final /* synthetic */ boolean $isWindowFocused;
    final /* synthetic */ TextFieldLineLimits $lineLimits;
    final /* synthetic */ p<Density, a<TextLayoutResult>, t0> $onTextLayout;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ boolean $readOnly;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ boolean $singleLine;
    final /* synthetic */ TextFieldSelectionState $textFieldSelectionState;
    final /* synthetic */ TextLayoutState $textLayoutState;
    final /* synthetic */ TextStyle $textStyle;
    final /* synthetic */ TransformedTextFieldState $transformedState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicTextField2Kt$BasicTextField2$5$1(TextFieldLineLimits textFieldLineLimits, TextLayoutState textLayoutState, TextStyle textStyle, boolean z, boolean z5, TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, Brush brush, boolean z10, boolean z11, ScrollState scrollState, Orientation orientation, boolean z12, p<? super Density, ? super a<TextLayoutResult>, t0> pVar) {
        super(2);
        this.$lineLimits = textFieldLineLimits;
        this.$textLayoutState = textLayoutState;
        this.$textStyle = textStyle;
        this.$isFocused = z;
        this.$isWindowFocused = z5;
        this.$transformedState = transformedTextFieldState;
        this.$textFieldSelectionState = textFieldSelectionState;
        this.$cursorBrush = brush;
        this.$enabled = z10;
        this.$readOnly = z11;
        this.$scrollState = scrollState;
        this.$orientation = orientation;
        this.$singleLine = z12;
        this.$onTextLayout = pVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        int minHeightInLines;
        int maxHeightInLines;
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1476233751, i10, -1, "androidx.compose.foundation.text2.BasicTextField2.<anonymous>.<anonymous> (BasicTextField2.kt:426)");
        }
        TextFieldLineLimits textFieldLineLimits = this.$lineLimits;
        if (textFieldLineLimits instanceof TextFieldLineLimits.MultiLine) {
            minHeightInLines = ((TextFieldLineLimits.MultiLine) textFieldLineLimits).getMinHeightInLines();
            maxHeightInLines = ((TextFieldLineLimits.MultiLine) this.$lineLimits).getMaxHeightInLines();
        } else {
            minHeightInLines = 1;
            maxHeightInLines = 1;
        }
        Modifier modifierThen = ClipKt.clipToBounds(TextFieldSizeKt.textFieldMinSize(HeightInLinesModifierKt.heightInLines(SizeKt.m566heightInVpY3zN4$default(Modifier.INSTANCE, this.$textLayoutState.m1125getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), this.$textStyle, minHeightInLines, maxHeightInLines), this.$textStyle)).then(new TextFieldCoreModifier(this.$isFocused && this.$isWindowFocused, this.$textLayoutState, this.$transformedState, this.$textFieldSelectionState, this.$cursorBrush, this.$enabled && !this.$readOnly, this.$scrollState, this.$orientation));
        TextLayoutState textLayoutState = this.$textLayoutState;
        TransformedTextFieldState transformedTextFieldState = this.$transformedState;
        TextStyle textStyle = this.$textStyle;
        boolean z = this.$singleLine;
        p<Density, a<TextLayoutResult>, t0> pVar = this.$onTextLayout;
        boolean z5 = this.$enabled;
        boolean z10 = this.$isFocused;
        boolean z11 = this.$isWindowFocused;
        TextFieldSelectionState textFieldSelectionState = this.$textFieldSelectionState;
        boolean z12 = this.$readOnly;
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = c.d(Alignment.INSTANCE, true, composer, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen);
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
        c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        BoxKt.Box(new TextFieldTextLayoutModifier(textLayoutState, transformedTextFieldState, textStyle, z, pVar), composer, 0);
        composer.startReplaceableGroup(-39277302);
        if (z5 && z10 && z11 && textFieldSelectionState.isInTouchMode()) {
            BasicTextField2Kt.TextFieldSelectionHandles(textFieldSelectionState, composer, 8);
            if (!z12) {
                BasicTextField2Kt.TextFieldCursorHandle(textFieldSelectionState, composer, 8);
            }
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
