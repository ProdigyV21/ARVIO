package androidx.compose.foundation.text;

import android.view.InputDevice;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"interceptDPadAndMoveFocus", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "isKeyCode", "", "Landroidx/compose/ui/input/key/KeyEvent;", "keyCode", "", "isKeyCode-YhN2O0w", "(Landroid/view/KeyEvent;I)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldFocusModifier_androidKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldFocusModifier_androidKt$interceptDPadAndMoveFocus$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "invoke-ZmokQxo", "(Landroid/view/KeyEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<KeyEvent, Boolean> {
        final /* synthetic */ FocusManager $focusManager;
        final /* synthetic */ TextFieldState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FocusManager focusManager, TextFieldState textFieldState) {
            super(1);
            this.$focusManager = focusManager;
            this.$state = textFieldState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m887invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
        }

        /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
        public final Boolean m887invokeZmokQxo(android.view.KeyEvent keyEvent) {
            InputDevice device = keyEvent.getDevice();
            boolean zMo3168moveFocus3ESFkO8 = false;
            if (device != null && device.supportsSource(513) && !device.isVirtual() && KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                if (TextFieldFocusModifier_androidKt.m886isKeyCodeYhN2O0w(keyEvent, 19)) {
                    zMo3168moveFocus3ESFkO8 = this.$focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3166getUpdhqQ8s());
                } else if (TextFieldFocusModifier_androidKt.m886isKeyCodeYhN2O0w(keyEvent, 20)) {
                    zMo3168moveFocus3ESFkO8 = this.$focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3159getDowndhqQ8s());
                } else if (TextFieldFocusModifier_androidKt.m886isKeyCodeYhN2O0w(keyEvent, 21)) {
                    zMo3168moveFocus3ESFkO8 = this.$focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3162getLeftdhqQ8s());
                } else if (TextFieldFocusModifier_androidKt.m886isKeyCodeYhN2O0w(keyEvent, 22)) {
                    zMo3168moveFocus3ESFkO8 = this.$focusManager.mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3165getRightdhqQ8s());
                } else if (TextFieldFocusModifier_androidKt.m886isKeyCodeYhN2O0w(keyEvent, 23)) {
                    SoftwareKeyboardController keyboardController = this.$state.getKeyboardController();
                    if (keyboardController != null) {
                        keyboardController.show();
                    }
                    zMo3168moveFocus3ESFkO8 = true;
                }
            }
            return Boolean.valueOf(zMo3168moveFocus3ESFkO8);
        }
    }

    public static final Modifier interceptDPadAndMoveFocus(Modifier modifier, TextFieldState textFieldState, FocusManager focusManager) {
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new AnonymousClass1(focusManager, textFieldState));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m886isKeyCodeYhN2O0w(android.view.KeyEvent keyEvent, int i10) {
        return Key_androidKt.m4408getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent)) == i10;
    }
}
