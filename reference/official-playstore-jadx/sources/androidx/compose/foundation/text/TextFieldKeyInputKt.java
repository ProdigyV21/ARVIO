package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001al\u0010\u0015\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/TextFieldState;", "state", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "manager", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "", "editable", "singleLine", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "Landroidx/compose/foundation/text/UndoManager;", "undoManager", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "textFieldKeyInput-2WJ9YEU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;ZZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;I)Landroidx/compose/ui/Modifier;", "textFieldKeyInput", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldKeyInputKt {
    /* JADX INFO: renamed from: textFieldKeyInput-2WJ9YEU, reason: not valid java name */
    public static final Modifier m890textFieldKeyInput2WJ9YEU(Modifier modifier, TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, boolean z, boolean z5, OffsetMapping offsetMapping, UndoManager undoManager, int i10) {
        return ComposedModifierKt.composed$default(modifier, null, new TextFieldKeyInputKt$textFieldKeyInput$2(textFieldState, textFieldSelectionManager, textFieldValue, z, z5, offsetMapping, undoManager, lVar, i10), 1, null);
    }

    /* JADX INFO: renamed from: textFieldKeyInput-2WJ9YEU$default, reason: not valid java name */
    public static /* synthetic */ Modifier m891textFieldKeyInput2WJ9YEU$default(Modifier modifier, TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, l lVar, boolean z, boolean z5, OffsetMapping offsetMapping, UndoManager undoManager, int i10, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            lVar = TextFieldKeyInputKt$textFieldKeyInput$1.INSTANCE;
        }
        return m890textFieldKeyInput2WJ9YEU(modifier, textFieldState, textFieldSelectionManager, textFieldValue, lVar, z, z5, offsetMapping, undoManager, i10);
    }
}
