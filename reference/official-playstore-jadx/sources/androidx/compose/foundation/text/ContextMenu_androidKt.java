package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.runtime.Composer;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0081\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a+\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\b2\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0081\b¢\u0006\u0004\b\u0006\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "manager", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "ContextMenuArea", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/SelectionManager;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextMenu_androidKt {
    public static final void ContextMenuArea(TextFieldSelectionManager textFieldSelectionManager, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1985516685);
        pVar.invoke(composer, Integer.valueOf((i10 >> 3) & 14));
        composer.endReplaceableGroup();
    }

    public static final void ContextMenuArea(SelectionManager selectionManager, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(605522716);
        pVar.invoke(composer, Integer.valueOf((i10 >> 3) & 14));
        composer.endReplaceableGroup();
    }
}
