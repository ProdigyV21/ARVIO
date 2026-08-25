package androidx.compose.foundation.text2;

import a0.c;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "it", "<anonymous>", "(Lr7/a;)V"}, k = 3, mv = {1, 8, 0})
public final class BasicTextField2Kt$DefaultTextFieldDecorator$1 implements TextFieldDecorator {
    public static final BasicTextField2Kt$DefaultTextFieldDecorator$1 INSTANCE = new BasicTextField2Kt$DefaultTextFieldDecorator$1();

    @Override // androidx.compose.foundation.text2.TextFieldDecorator
    public final void Decoration(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-186734623);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i11 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-186734623, i11, -1, "androidx.compose.foundation.text2.DefaultTextFieldDecorator.<no name provided>.Decoration (BasicTextField2.kt:541)");
            }
            if (c.A(composerStartRestartGroup, i11 & 14, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new BasicTextField2Kt$DefaultTextFieldDecorator$1$Decoration$1(this, pVar, i10));
        }
    }
}
