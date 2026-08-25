package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\b*\u00020\u0005H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\r2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\r¢\u0006\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u0017R$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u001c8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/compose/ui/text/input/EditProcessor;", "", "<init>", "()V", "", "Landroidx/compose/ui/text/input/EditCommand;", "editCommands", "failedCommand", "", "generateBatchErrorMessage", "(Ljava/util/List;Landroidx/compose/ui/text/input/EditCommand;)Ljava/lang/String;", "toStringForLog", "(Landroidx/compose/ui/text/input/EditCommand;)Ljava/lang/String;", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Landroidx/compose/ui/text/input/TextInputSession;", "textInputSession", "Lx6/t0;", "reset", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextInputSession;)V", "apply", "(Ljava/util/List;)Landroidx/compose/ui/text/input/TextFieldValue;", "toTextFieldValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "<set-?>", "mBufferState", "Landroidx/compose/ui/text/input/TextFieldValue;", "getMBufferState$ui_text_release", "Landroidx/compose/ui/text/input/EditingBuffer;", "mBuffer", "Landroidx/compose/ui/text/input/EditingBuffer;", "getMBuffer$ui_text_release", "()Landroidx/compose/ui/text/input/EditingBuffer;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EditProcessor {
    public static final int $stable = 8;
    private TextFieldValue mBufferState = new TextFieldValue(AnnotatedStringKt.emptyAnnotatedString(), TextRange.INSTANCE.m5197getZerod9O1mEE(), (TextRange) null, (h) null);
    private EditingBuffer mBuffer = new EditingBuffer(this.mBufferState.getAnnotatedString(), this.mBufferState.getSelection(), (h) null);

    private final String generateBatchErrorMessage(List<? extends EditCommand> editCommands, EditCommand failedCommand) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Error while applying EditCommand batch to buffer (length=" + this.mBuffer.getLength$ui_text_release() + ", composition=" + this.mBuffer.m5349getCompositionMzsxiRA$ui_text_release() + ", selection=" + ((Object) TextRange.m5195toStringimpl(this.mBuffer.m5350getSelectiond9O1mEE$ui_text_release())) + "):");
        sb2.append('\n');
        x.t0(editCommands, sb2, "\n", null, null, new EditProcessor$generateBatchErrorMessage$1$1(failedCommand, this), 60);
        return sb2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toStringForLog(EditCommand editCommand) {
        if (editCommand instanceof CommitTextCommand) {
            StringBuilder sb2 = new StringBuilder("CommitTextCommand(text.length=");
            CommitTextCommand commitTextCommand = (CommitTextCommand) editCommand;
            sb2.append(commitTextCommand.getText().length());
            sb2.append(", newCursorPosition=");
            sb2.append(commitTextCommand.getNewCursorPosition());
            sb2.append(')');
            return sb2.toString();
        }
        if (editCommand instanceof SetComposingTextCommand) {
            StringBuilder sb3 = new StringBuilder("SetComposingTextCommand(text.length=");
            SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) editCommand;
            sb3.append(setComposingTextCommand.getText().length());
            sb3.append(", newCursorPosition=");
            sb3.append(setComposingTextCommand.getNewCursorPosition());
            sb3.append(')');
            return sb3.toString();
        }
        if (editCommand instanceof SetComposingRegionCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof DeleteSurroundingTextCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof DeleteSurroundingTextInCodePointsCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof SetSelectionCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof FinishComposingTextCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof BackspaceCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof MoveCursorCommand) {
            return editCommand.toString();
        }
        if (editCommand instanceof DeleteAllCommand) {
            return editCommand.toString();
        }
        String strR = l0.f19747a.b(editCommand.getClass()).r();
        if (strR == null) {
            strR = "{anonymous EditCommand}";
        }
        return "Unknown EditCommand: ".concat(strR);
    }

    public final TextFieldValue apply(List<? extends EditCommand> editCommands) {
        EditCommand editCommand;
        EditCommand editCommand2 = null;
        try {
            int size = editCommands.size();
            int i10 = 0;
            EditCommand editCommand3 = null;
            while (i10 < size) {
                try {
                    editCommand = editCommands.get(i10);
                } catch (Exception e5) {
                    e = e5;
                    editCommand2 = editCommand3;
                }
                try {
                    editCommand.applyTo(this.mBuffer);
                    i10++;
                    editCommand3 = editCommand;
                } catch (Exception e6) {
                    e = e6;
                    editCommand2 = editCommand;
                    throw new RuntimeException(generateBatchErrorMessage(editCommands, editCommand2), e);
                }
            }
            AnnotatedString annotatedString$ui_text_release = this.mBuffer.toAnnotatedString$ui_text_release();
            long jM5350getSelectiond9O1mEE$ui_text_release = this.mBuffer.m5350getSelectiond9O1mEE$ui_text_release();
            TextRange textRangeM5180boximpl = TextRange.m5180boximpl(jM5350getSelectiond9O1mEE$ui_text_release);
            textRangeM5180boximpl.getPackedValue();
            TextRange textRange = TextRange.m5191getReversedimpl(this.mBufferState.getSelection()) ? null : textRangeM5180boximpl;
            TextFieldValue textFieldValue = new TextFieldValue(annotatedString$ui_text_release, textRange != null ? textRange.getPackedValue() : TextRangeKt.TextRange(TextRange.m5189getMaximpl(jM5350getSelectiond9O1mEE$ui_text_release), TextRange.m5190getMinimpl(jM5350getSelectiond9O1mEE$ui_text_release)), this.mBuffer.m5349getCompositionMzsxiRA$ui_text_release(), (h) null);
            this.mBufferState = textFieldValue;
            return textFieldValue;
        } catch (Exception e10) {
            e = e10;
        }
    }

    /* JADX INFO: renamed from: getMBuffer$ui_text_release, reason: from getter */
    public final EditingBuffer getMBuffer() {
        return this.mBuffer;
    }

    /* JADX INFO: renamed from: getMBufferState$ui_text_release, reason: from getter */
    public final TextFieldValue getMBufferState() {
        return this.mBufferState;
    }

    public final void reset(TextFieldValue value, TextInputSession textInputSession) {
        boolean zA = p.a(value.getComposition(), this.mBuffer.m5349getCompositionMzsxiRA$ui_text_release());
        boolean z = true;
        boolean z5 = false;
        if (!p.a(this.mBufferState.getAnnotatedString(), value.getAnnotatedString())) {
            this.mBuffer = new EditingBuffer(value.getAnnotatedString(), value.getSelection(), (h) null);
        } else if (TextRange.m5185equalsimpl0(this.mBufferState.getSelection(), value.getSelection())) {
            z = false;
        } else {
            this.mBuffer.setSelection$ui_text_release(TextRange.m5190getMinimpl(value.getSelection()), TextRange.m5189getMaximpl(value.getSelection()));
            z5 = true;
            z = false;
        }
        if (value.getComposition() == null) {
            this.mBuffer.commitComposition$ui_text_release();
        } else if (!TextRange.m5186getCollapsedimpl(value.getComposition().getPackedValue())) {
            this.mBuffer.setComposition$ui_text_release(TextRange.m5190getMinimpl(value.getComposition().getPackedValue()), TextRange.m5189getMaximpl(value.getComposition().getPackedValue()));
        }
        if (z || (!z5 && !zA)) {
            this.mBuffer.commitComposition$ui_text_release();
            value = TextFieldValue.m5424copy3r_uNRQ$default(value, (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null);
        }
        TextFieldValue textFieldValue = this.mBufferState;
        this.mBufferState = value;
        if (textInputSession != null) {
            textInputSession.updateState(textFieldValue, value);
        }
    }

    public final TextFieldValue toTextFieldValue() {
        return this.mBufferState;
    }
}
