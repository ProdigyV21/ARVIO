package androidx.compose.ui.text.input;

import androidx.compose.ui.text.JvmCharHelpers_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/text/input/BackspaceCommand;", "Landroidx/compose/ui/text/input/EditCommand;", "<init>", "()V", "Landroidx/compose/ui/text/input/EditingBuffer;", "buffer", "Lx6/t0;", "applyTo", "(Landroidx/compose/ui/text/input/EditingBuffer;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BackspaceCommand implements EditCommand {
    public static final int $stable = 0;

    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer buffer) {
        if (buffer.hasComposition$ui_text_release()) {
            buffer.delete$ui_text_release(buffer.getCompositionStart(), buffer.getCompositionEnd());
            return;
        }
        if (buffer.getCursor$ui_text_release() != -1) {
            if (buffer.getCursor$ui_text_release() == 0) {
                return;
            }
            buffer.delete$ui_text_release(JvmCharHelpers_androidKt.findPrecedingBreak(buffer.toString(), buffer.getCursor$ui_text_release()), buffer.getCursor$ui_text_release());
        } else {
            int selectionStart = buffer.getSelectionStart();
            int selectionEnd = buffer.getSelectionEnd();
            buffer.setCursor$ui_text_release(buffer.getSelectionStart());
            buffer.delete$ui_text_release(selectionStart, selectionEnd);
        }
    }

    public boolean equals(Object other) {
        return other instanceof BackspaceCommand;
    }

    public int hashCode() {
        return l0.f19747a.b(BackspaceCommand.class).hashCode();
    }

    public String toString() {
        return "BackspaceCommand()";
    }
}
