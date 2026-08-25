package androidx.compose.foundation.text2.input.internal;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nR\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManagerImplApi21;", "Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManagerImpl;", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "Landroid/view/KeyEvent;", "event", "Lx6/t0;", "sendKeyEvent", "(Landroid/view/KeyEvent;)V", "Landroid/view/inputmethod/BaseInputConnection;", "baseInputConnection", "Landroid/view/inputmethod/BaseInputConnection;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
class ComposeInputMethodManagerImplApi21 extends ComposeInputMethodManagerImpl {
    private BaseInputConnection baseInputConnection;

    public ComposeInputMethodManagerImplApi21(View view) {
        super(view);
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void sendKeyEvent(KeyEvent event) {
        BaseInputConnection baseInputConnection = this.baseInputConnection;
        if (baseInputConnection == null) {
            baseInputConnection = new BaseInputConnection(getView(), false);
            this.baseInputConnection = baseInputConnection;
        }
        baseInputConnection.sendKeyEvent(event);
    }
}
