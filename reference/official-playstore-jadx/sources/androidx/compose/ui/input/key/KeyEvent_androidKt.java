package androidx.compose.ui.input.key;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004\"\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012*\n\u0010\u0016\"\u00020\u00172\u00020\u0017¨\u0006\u0018"}, d2 = {"isAltPressed", "", "Landroidx/compose/ui/input/key/KeyEvent;", "isAltPressed-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isCtrlPressed", "isCtrlPressed-ZmokQxo", "isMetaPressed", "isMetaPressed-ZmokQxo", "isShiftPressed", "isShiftPressed-ZmokQxo", "key", "Landroidx/compose/ui/input/key/Key;", "getKey-ZmokQxo", "(Landroid/view/KeyEvent;)J", LinkHeader.Parameters.Type, "Landroidx/compose/ui/input/key/KeyEventType;", "getType-ZmokQxo", "(Landroid/view/KeyEvent;)I", "utf16CodePoint", "", "getUtf16CodePoint-ZmokQxo", "NativeKeyEvent", "Landroid/view/KeyEvent;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyEvent_androidKt {
    /* JADX INFO: renamed from: getKey-ZmokQxo, reason: not valid java name */
    public static final long m4401getKeyZmokQxo(android.view.KeyEvent keyEvent) {
        return Key_androidKt.Key(keyEvent.getKeyCode());
    }

    /* JADX INFO: renamed from: getType-ZmokQxo, reason: not valid java name */
    public static final int m4402getTypeZmokQxo(android.view.KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        return action != 0 ? action != 1 ? KeyEventType.INSTANCE.m4400getUnknownCS__XNY() : KeyEventType.INSTANCE.m4399getKeyUpCS__XNY() : KeyEventType.INSTANCE.m4398getKeyDownCS__XNY();
    }

    /* JADX INFO: renamed from: getUtf16CodePoint-ZmokQxo, reason: not valid java name */
    public static final int m4403getUtf16CodePointZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.getUnicodeChar();
    }

    /* JADX INFO: renamed from: isAltPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m4404isAltPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isAltPressed();
    }

    /* JADX INFO: renamed from: isCtrlPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m4405isCtrlPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isCtrlPressed();
    }

    /* JADX INFO: renamed from: isMetaPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m4406isMetaPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isMetaPressed();
    }

    /* JADX INFO: renamed from: isShiftPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m4407isShiftPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isShiftPressed();
    }
}
