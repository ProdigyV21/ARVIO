package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u000f\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, d2 = {"Landroidx/compose/ui/input/key/KeyEvent;", "", "cancelsTextSelection-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "cancelsTextSelection", "Lx6/t0;", "showCharacterPalette", "()V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyEventHelpers_androidKt {
    /* JADX INFO: renamed from: cancelsTextSelection-ZmokQxo, reason: not valid java name */
    public static final boolean m826cancelsTextSelectionZmokQxo(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 && KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4399getKeyUpCS__XNY());
    }

    public static final void showCharacterPalette() {
    }
}
