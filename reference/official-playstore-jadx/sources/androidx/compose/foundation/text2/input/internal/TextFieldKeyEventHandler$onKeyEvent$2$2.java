package androidx.compose.foundation.text2.input.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldKeyEventHandler$onKeyEvent$2$2 extends r implements l<TextFieldPreparedSelection, t0> {
    public static final TextFieldKeyEventHandler$onKeyEvent$2$2 INSTANCE = new TextFieldKeyEventHandler$onKeyEvent$2$2();

    public TextFieldKeyEventHandler$onKeyEvent$2$2() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextFieldPreparedSelection) obj);
        return t0.f22605a;
    }

    public final void invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
        textFieldPreparedSelection.moveCursorRight();
    }
}
