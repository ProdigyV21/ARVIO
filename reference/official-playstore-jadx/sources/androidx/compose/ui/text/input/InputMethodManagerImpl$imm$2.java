package androidx.compose.ui.text.input;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/view/inputmethod/InputMethodManager;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class InputMethodManagerImpl$imm$2 extends r implements r7.a<android.view.inputmethod.InputMethodManager> {
    final /* synthetic */ InputMethodManagerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputMethodManagerImpl$imm$2(InputMethodManagerImpl inputMethodManagerImpl) {
        super(0);
        this.this$0 = inputMethodManagerImpl;
    }

    @Override // r7.a
    public final android.view.inputmethod.InputMethodManager invoke() {
        return (android.view.inputmethod.InputMethodManager) this.this$0.view.getContext().getSystemService("input_method");
    }
}
