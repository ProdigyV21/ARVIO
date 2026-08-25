package androidx.compose.foundation.text;

import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionRunner;", "Landroidx/compose/foundation/text/KeyboardActionScope;", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "keyboardController", "<init>", "(Landroidx/compose/ui/platform/SoftwareKeyboardController;)V", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Lx6/t0;", "runAction-KlQnJC8", "(I)V", "runAction", "defaultKeyboardAction-KlQnJC8", "defaultKeyboardAction", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "getKeyboardActions", "()Landroidx/compose/foundation/text/KeyboardActions;", "setKeyboardActions", "(Landroidx/compose/foundation/text/KeyboardActions;)V", "Landroidx/compose/ui/focus/FocusManager;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "getFocusManager", "()Landroidx/compose/ui/focus/FocusManager;", "setFocusManager", "(Landroidx/compose/ui/focus/FocusManager;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyboardActionRunner implements KeyboardActionScope {
    public static final int $stable = 8;
    public FocusManager focusManager;
    public KeyboardActions keyboardActions;
    private final SoftwareKeyboardController keyboardController;

    public KeyboardActionRunner(SoftwareKeyboardController softwareKeyboardController) {
        this.keyboardController = softwareKeyboardController;
    }

    @Override // androidx.compose.foundation.text.KeyboardActionScope
    /* JADX INFO: renamed from: defaultKeyboardAction-KlQnJC8, reason: not valid java name */
    public void mo828defaultKeyboardActionKlQnJC8(int imeAction) {
        ImeAction.Companion companion = ImeAction.INSTANCE;
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5370getNexteUduSuo())) {
            getFocusManager().mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3163getNextdhqQ8s());
            return;
        }
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5372getPreviouseUduSuo())) {
            getFocusManager().mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3164getPreviousdhqQ8s());
            return;
        }
        if (!ImeAction.m5355equalsimpl0(imeAction, companion.m5368getDoneeUduSuo())) {
            if (ImeAction.m5355equalsimpl0(imeAction, companion.m5369getGoeUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5373getSearcheUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5374getSendeUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5367getDefaulteUduSuo())) {
                return;
            }
            ImeAction.m5355equalsimpl0(imeAction, companion.m5371getNoneeUduSuo());
        } else {
            SoftwareKeyboardController softwareKeyboardController = this.keyboardController;
            if (softwareKeyboardController != null) {
                softwareKeyboardController.hide();
            }
        }
    }

    public final FocusManager getFocusManager() {
        FocusManager focusManager = this.focusManager;
        if (focusManager != null) {
            return focusManager;
        }
        p.i("focusManager");
        throw null;
    }

    public final KeyboardActions getKeyboardActions() {
        KeyboardActions keyboardActions = this.keyboardActions;
        if (keyboardActions != null) {
            return keyboardActions;
        }
        p.i("keyboardActions");
        throw null;
    }

    /* JADX INFO: renamed from: runAction-KlQnJC8, reason: not valid java name */
    public final void m829runActionKlQnJC8(int imeAction) {
        l<KeyboardActionScope, t0> onSend;
        ImeAction.Companion companion = ImeAction.INSTANCE;
        t0 t0Var = null;
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5368getDoneeUduSuo())) {
            onSend = getKeyboardActions().getOnDone();
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5369getGoeUduSuo())) {
            onSend = getKeyboardActions().getOnGo();
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5370getNexteUduSuo())) {
            onSend = getKeyboardActions().getOnNext();
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5372getPreviouseUduSuo())) {
            onSend = getKeyboardActions().getOnPrevious();
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5373getSearcheUduSuo())) {
            onSend = getKeyboardActions().getOnSearch();
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5374getSendeUduSuo())) {
            onSend = getKeyboardActions().getOnSend();
        } else {
            if (!(ImeAction.m5355equalsimpl0(imeAction, companion.m5367getDefaulteUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5371getNoneeUduSuo()))) {
                throw new IllegalStateException("invalid ImeAction");
            }
            onSend = null;
        }
        if (onSend != null) {
            onSend.invoke(this);
            t0Var = t0.f22605a;
        }
        if (t0Var == null) {
            mo828defaultKeyboardActionKlQnJC8(imeAction);
        }
    }

    public final void setFocusManager(FocusManager focusManager) {
        this.focusManager = focusManager;
    }

    public final void setKeyboardActions(KeyboardActions keyboardActions) {
        this.keyboardActions = keyboardActions;
    }
}
