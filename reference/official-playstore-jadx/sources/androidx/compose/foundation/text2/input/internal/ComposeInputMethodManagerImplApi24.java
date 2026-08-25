package androidx.compose.foundation.text2.input.internal;

import android.view.KeyEvent;
import android.view.View;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0013\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManagerImplApi24;", "Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManagerImplApi21;", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "Landroid/view/KeyEvent;", "event", "Lx6/t0;", "sendKeyEvent", "(Landroid/view/KeyEvent;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
class ComposeInputMethodManagerImplApi24 extends ComposeInputMethodManagerImplApi21 {
    public ComposeInputMethodManagerImplApi24(View view) {
        super(view);
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManagerImplApi21, androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void sendKeyEvent(KeyEvent event) {
        requireImm().dispatchKeyEventFromInputMethod(getView(), event);
    }
}
