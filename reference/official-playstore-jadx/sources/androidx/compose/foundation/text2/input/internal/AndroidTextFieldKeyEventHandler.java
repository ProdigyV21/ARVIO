package androidx.compose.foundation.text2.input.internal;

import android.view.InputDevice;
import android.view.KeyEvent;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/AndroidTextFieldKeyEventHandler;", "Landroidx/compose/foundation/text2/input/internal/TextFieldKeyEventHandler;", "()V", "onPreKeyEvent", "", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "onPreKeyEvent-MyFupTE", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/focus/FocusManager;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextFieldKeyEventHandler extends TextFieldKeyEventHandler {
    public static final int $stable = 0;

    @Override // androidx.compose.foundation.text2.input.internal.TextFieldKeyEventHandler
    /* JADX INFO: renamed from: onPreKeyEvent-MyFupTE, reason: not valid java name */
    public boolean mo1081onPreKeyEventMyFupTE(KeyEvent event, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, FocusManager focusManager, SoftwareKeyboardController keyboardController) {
        if (super.mo1081onPreKeyEventMyFupTE(event, textFieldState, textFieldSelectionState, focusManager, keyboardController)) {
            return true;
        }
        InputDevice device = event.getDevice();
        if (device == null || !device.supportsSource(513) || device.isVirtual() || !KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(event), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
            return false;
        }
        if (TextFieldKeyEventHandler_androidKt.m1116isKeyCodeYhN2O0w(event, 19)) {
            return focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3166getUpdhqQ8s());
        }
        if (TextFieldKeyEventHandler_androidKt.m1116isKeyCodeYhN2O0w(event, 20)) {
            return focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3159getDowndhqQ8s());
        }
        if (TextFieldKeyEventHandler_androidKt.m1116isKeyCodeYhN2O0w(event, 21)) {
            return focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3162getLeftdhqQ8s());
        }
        if (TextFieldKeyEventHandler_androidKt.m1116isKeyCodeYhN2O0w(event, 22)) {
            return focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3165getRightdhqQ8s());
        }
        if (!TextFieldKeyEventHandler_androidKt.m1116isKeyCodeYhN2O0w(event, 23)) {
            return false;
        }
        keyboardController.show();
        return true;
    }
}
