package androidx.compose.ui.text.input;

import android.R;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import androidx.compose.ui.text.TextRange;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010!\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0019\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ!\u0010\u001f\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001f\u0010\u001aJ\u001f\u0010\"\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\"\u0010\u001eJ\u001f\u0010#\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0016¢\u0006\u0004\b#\u0010\u001eJ\u001f\u0010$\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0017H\u0016¢\u0006\u0004\b$\u0010\u001eJ\u000f\u0010%\u001a\u00020\u0006H\u0016¢\u0006\u0004\b%\u0010\u0011J\u0017\u0010(\u001a\u00020\u00062\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b(\u0010)J\u001f\u0010,\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u0017H\u0016¢\u0006\u0004\b,\u0010-J\u001f\u0010.\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u0017H\u0016¢\u0006\u0004\b.\u0010-J\u0019\u0010/\u001a\u0004\u0018\u00010\u00152\u0006\u0010+\u001a\u00020\u0017H\u0016¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0017H\u0016¢\u0006\u0004\b2\u00103J!\u00107\u001a\u0002062\b\u00105\u001a\u0004\u0018\u0001042\u0006\u0010+\u001a\u00020\u0017H\u0016¢\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0017H\u0016¢\u0006\u0004\b:\u00103J\u0017\u0010<\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0017H\u0016¢\u0006\u0004\b<\u00103J\u0019\u0010>\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010=H\u0016¢\u0006\u0004\b>\u0010?J\u0019\u0010B\u001a\u00020\u00062\b\u0010A\u001a\u0004\u0018\u00010@H\u0016¢\u0006\u0004\bB\u0010CJ\u0011\u0010E\u001a\u0004\u0018\u00010DH\u0016¢\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u0017H\u0016¢\u0006\u0004\bH\u00103J\u0017\u0010J\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u0006H\u0016¢\u0006\u0004\bJ\u0010KJ\u0017\u0010M\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u0017H\u0016¢\u0006\u0004\bM\u0010NJ#\u0010S\u001a\u00020\u00062\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010R\u001a\u0004\u0018\u00010QH\u0016¢\u0006\u0004\bS\u0010TJ)\u0010X\u001a\u00020\u00062\u0006\u0010V\u001a\u00020U2\u0006\u0010+\u001a\u00020\u00172\b\u0010W\u001a\u0004\u0018\u00010QH\u0016¢\u0006\u0004\bX\u0010YJ\u001e\u0010\\\u001a\u00020\u00062\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\r0ZH\u0082\b¢\u0006\u0004\b\\\u0010]J\u0017\u0010`\u001a\u00020\r2\u0006\u0010_\u001a\u00020^H\u0002¢\u0006\u0004\b`\u0010aJ\u000f\u0010b\u001a\u00020\u0006H\u0002¢\u0006\u0004\bb\u0010\u0011J\u000f\u0010c\u001a\u00020\u0006H\u0002¢\u0006\u0004\bc\u0010\u0011J\u0017\u0010e\u001a\u00020\r2\u0006\u0010d\u001a\u00020\u0017H\u0002¢\u0006\u0004\be\u0010fJ\u0017\u0010h\u001a\u00020\r2\u0006\u0010g\u001a\u00020OH\u0002¢\u0006\u0004\bh\u0010iR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010j\u001a\u0004\bk\u0010lR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010m\u001a\u0004\bn\u0010\u0011R\u0016\u0010o\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010pR*\u0010r\u001a\u00020\u00022\u0006\u0010q\u001a\u00020\u00028\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\br\u0010s\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010pR\u0016\u0010y\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010mR\u001a\u0010{\u001a\b\u0012\u0004\u0012\u00020^0z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010m¨\u0006~"}, d2 = {"Landroidx/compose/ui/text/input/RecordingInputConnection;", "Landroid/view/inputmethod/InputConnection;", "Landroidx/compose/ui/text/input/TextFieldValue;", "initState", "Landroidx/compose/ui/text/input/InputEventCallback2;", "eventCallback", "", "autoCorrect", "<init>", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/InputEventCallback2;Z)V", "state", "Landroidx/compose/ui/text/input/InputMethodManager;", "inputMethodManager", "Lx6/t0;", "updateInputState", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/InputMethodManager;)V", "beginBatchEdit", "()Z", "endBatchEdit", "closeConnection", "()V", "", "text", "", "newCursorPosition", "commitText", "(Ljava/lang/CharSequence;I)Z", TtmlNode.START, TtmlNode.END, "setComposingRegion", "(II)Z", "setComposingText", "beforeLength", "afterLength", "deleteSurroundingTextInCodePoints", "deleteSurroundingText", "setSelection", "finishComposingText", "Landroid/view/KeyEvent;", "event", "sendKeyEvent", "(Landroid/view/KeyEvent;)Z", "maxChars", "flags", "getTextBeforeCursor", "(II)Ljava/lang/CharSequence;", "getTextAfterCursor", "getSelectedText", "(I)Ljava/lang/CharSequence;", "cursorUpdateMode", "requestCursorUpdates", "(I)Z", "Landroid/view/inputmethod/ExtractedTextRequest;", "request", "Landroid/view/inputmethod/ExtractedText;", "getExtractedText", "(Landroid/view/inputmethod/ExtractedTextRequest;I)Landroid/view/inputmethod/ExtractedText;", TtmlNode.ATTR_ID, "performContextMenuAction", "editorAction", "performEditorAction", "Landroid/view/inputmethod/CompletionInfo;", "commitCompletion", "(Landroid/view/inputmethod/CompletionInfo;)Z", "Landroid/view/inputmethod/CorrectionInfo;", "correctionInfo", "commitCorrection", "(Landroid/view/inputmethod/CorrectionInfo;)Z", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "states", "clearMetaKeyStates", "enabled", "reportFullscreenMode", "(Z)Z", "reqModes", "getCursorCapsMode", "(I)I", "", "action", "Landroid/os/Bundle;", "data", "performPrivateCommand", "(Ljava/lang/String;Landroid/os/Bundle;)Z", "Landroid/view/inputmethod/InputContentInfo;", "inputContentInfo", "opts", "commitContent", "(Landroid/view/inputmethod/InputContentInfo;ILandroid/os/Bundle;)Z", "Lkotlin/Function0;", "block", "ensureActive", "(Lr7/a;)Z", "Landroidx/compose/ui/text/input/EditCommand;", "editCommand", "addEditCommandWithBatch", "(Landroidx/compose/ui/text/input/EditCommand;)V", "beginBatchEditInternal", "endBatchEditInternal", "code", "sendSynthesizedKeyEvent", "(I)V", "message", "logDebug", "(Ljava/lang/String;)V", "Landroidx/compose/ui/text/input/InputEventCallback2;", "getEventCallback", "()Landroidx/compose/ui/text/input/InputEventCallback2;", "Z", "getAutoCorrect", "batchDepth", "I", "value", "mTextFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "getMTextFieldValue$ui_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setMTextFieldValue$ui_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "currentExtractedTextRequestToken", "extractedTextMonitorMode", "", "editCommands", "Ljava/util/List;", "isActive", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RecordingInputConnection implements InputConnection {
    public static final int $stable = 8;
    private final boolean autoCorrect;
    private int batchDepth;
    private int currentExtractedTextRequestToken;
    private final InputEventCallback2 eventCallback;
    private boolean extractedTextMonitorMode;
    private TextFieldValue mTextFieldValue;
    private final List<EditCommand> editCommands = new ArrayList();
    private boolean isActive = true;

    public RecordingInputConnection(TextFieldValue textFieldValue, InputEventCallback2 inputEventCallback2, boolean z) {
        this.eventCallback = inputEventCallback2;
        this.autoCorrect = z;
        this.mTextFieldValue = textFieldValue;
    }

    private final void addEditCommandWithBatch(EditCommand editCommand) {
        beginBatchEditInternal();
        try {
            this.editCommands.add(editCommand);
        } finally {
            endBatchEditInternal();
        }
    }

    private final boolean beginBatchEditInternal() {
        this.batchDepth++;
        return true;
    }

    private final boolean endBatchEditInternal() {
        int i10 = this.batchDepth - 1;
        this.batchDepth = i10;
        if (i10 == 0 && !this.editCommands.isEmpty()) {
            this.eventCallback.onEditCommands(new ArrayList(this.editCommands));
            this.editCommands.clear();
        }
        return this.batchDepth > 0;
    }

    private final boolean ensureActive(r7.a<t0> block) {
        boolean z = this.isActive;
        if (z) {
            block.invoke();
        }
        return z;
    }

    private final void logDebug(String message) {
    }

    private final void sendSynthesizedKeyEvent(int code) {
        sendKeyEvent(new KeyEvent(0, code));
        sendKeyEvent(new KeyEvent(1, code));
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        boolean z = this.isActive;
        return z ? beginBatchEditInternal() : z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int states) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
        this.editCommands.clear();
        this.batchDepth = 0;
        this.isActive = false;
        this.eventCallback.onConnectionClosed(this);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo text) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean z = this.isActive;
        return z ? this.autoCorrect : z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new CommitTextCommand(String.valueOf(text), newCursorPosition));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextCommand(beforeLength, afterLength));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextInCodePointsCommand(beforeLength, afterLength));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        return endBatchEditInternal();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new FinishComposingTextCommand());
        return true;
    }

    public final boolean getAutoCorrect() {
        return this.autoCorrect;
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int reqModes) {
        return TextUtils.getCapsMode(this.mTextFieldValue.getText(), TextRange.m5190getMinimpl(this.mTextFieldValue.getSelection()), reqModes);
    }

    public final InputEventCallback2 getEventCallback() {
        return this.eventCallback;
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        boolean z = (flags & 1) != 0;
        this.extractedTextMonitorMode = z;
        if (z) {
            this.currentExtractedTextRequestToken = request != null ? request.token : 0;
        }
        return InputState_androidKt.toExtractedText(this.mTextFieldValue);
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        return null;
    }

    /* JADX INFO: renamed from: getMTextFieldValue$ui_release, reason: from getter */
    public final TextFieldValue getMTextFieldValue() {
        return this.mTextFieldValue;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int flags) {
        if (TextRange.m5186getCollapsedimpl(this.mTextFieldValue.getSelection())) {
            return null;
        }
        return TextFieldValueKt.getSelectedText(this.mTextFieldValue).toString();
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int maxChars, int flags) {
        return TextFieldValueKt.getTextAfterSelection(this.mTextFieldValue, maxChars).toString();
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int maxChars, int flags) {
        return TextFieldValueKt.getTextBeforeSelection(this.mTextFieldValue, maxChars).toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int id) {
        boolean z = this.isActive;
        if (z) {
            z = false;
            switch (id) {
                case R.id.selectAll:
                    addEditCommandWithBatch(new SetSelectionCommand(0, this.mTextFieldValue.getText().length()));
                    break;
                case R.id.cut:
                    sendSynthesizedKeyEvent(277);
                    break;
                case R.id.copy:
                    sendSynthesizedKeyEvent(278);
                    break;
                case R.id.paste:
                    sendSynthesizedKeyEvent(279);
                    break;
            }
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int editorAction) {
        int iM5367getDefaulteUduSuo;
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        if (editorAction != 0) {
            switch (editorAction) {
                case 2:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5369getGoeUduSuo();
                    break;
                case 3:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5373getSearcheUduSuo();
                    break;
                case 4:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5374getSendeUduSuo();
                    break;
                case 5:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5370getNexteUduSuo();
                    break;
                case 6:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5368getDoneeUduSuo();
                    break;
                case 7:
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5372getPreviouseUduSuo();
                    break;
                default:
                    Log.w(RecordingInputConnection_androidKt.TAG, "IME sends unsupported Editor Action: " + editorAction);
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5367getDefaulteUduSuo();
                    break;
            }
        } else {
            iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5367getDefaulteUduSuo();
        }
        this.eventCallback.mo5383onImeActionKlQnJC8(iM5367getDefaulteUduSuo);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String action, Bundle data) {
        boolean z = this.isActive;
        if (z) {
            return true;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean enabled) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        boolean z;
        boolean z5;
        boolean z10;
        boolean z11;
        boolean z12 = this.isActive;
        if (!z12) {
            return z12;
        }
        boolean z13 = false;
        boolean z14 = (cursorUpdateMode & 1) != 0;
        boolean z15 = (cursorUpdateMode & 2) != 0;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            boolean z16 = (cursorUpdateMode & 16) != 0;
            boolean z17 = (cursorUpdateMode & 8) != 0;
            boolean z18 = (cursorUpdateMode & 4) != 0;
            if (i10 >= 34 && (cursorUpdateMode & 32) != 0) {
                z13 = true;
            }
            if (z16 || z17 || z18 || z13) {
                z5 = z13;
                z = z18;
                z11 = z17;
                z10 = z16;
            } else if (i10 >= 34) {
                z10 = true;
                z11 = true;
                z = true;
                z5 = true;
            } else {
                z5 = z13;
                z10 = true;
                z11 = true;
                z = true;
            }
        } else {
            z = false;
            z5 = false;
            z10 = true;
            z11 = true;
        }
        this.eventCallback.onRequestCursorAnchorInfo(z14, z15, z10, z11, z, z5);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        this.eventCallback.onKeyEvent(event);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int start, int end) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingRegionCommand(start, end));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingTextCommand(String.valueOf(text), newCursorPosition));
        }
        return z;
    }

    public final void setMTextFieldValue$ui_release(TextFieldValue textFieldValue) {
        this.mTextFieldValue = textFieldValue;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int start, int end) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new SetSelectionCommand(start, end));
        return true;
    }

    public final void updateInputState(TextFieldValue state, InputMethodManager inputMethodManager) {
        if (this.isActive) {
            setMTextFieldValue$ui_release(state);
            if (this.extractedTextMonitorMode) {
                inputMethodManager.updateExtractedText(this.currentExtractedTextRequestToken, InputState_androidKt.toExtractedText(state));
            }
            TextRange composition = state.getComposition();
            int iM5190getMinimpl = composition != null ? TextRange.m5190getMinimpl(composition.getPackedValue()) : -1;
            TextRange composition2 = state.getComposition();
            inputMethodManager.updateSelection(TextRange.m5190getMinimpl(state.getSelection()), TextRange.m5189getMaximpl(state.getSelection()), iM5190getMinimpl, composition2 != null ? TextRange.m5189getMaximpl(composition2.getPackedValue()) : -1);
        }
    }
}
