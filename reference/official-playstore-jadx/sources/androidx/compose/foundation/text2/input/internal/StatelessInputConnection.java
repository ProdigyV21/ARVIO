package androidx.compose.foundation.text2.input.internal;

import a0.c;
import android.R;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0011\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0012J\u001f\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001a\u0010\u0016J\u001f\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001b\u0010\u0016J\u001f\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001c\u0010\u0016J\u000f\u0010\u001d\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001d\u0010\bJ\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b \u0010!J\u001f\u0010$\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\b&\u0010%J\u0019\u0010'\u001a\u0004\u0018\u00010\r2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u000fH\u0016¢\u0006\u0004\b*\u0010+J!\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000fH\u0016¢\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u000fH\u0016¢\u0006\u0004\b5\u0010+J\u0017\u00107\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u000fH\u0016¢\u0006\u0004\b7\u0010+J\u0019\u00109\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0004\b9\u0010:J\u0019\u0010=\u001a\u00020\u00062\b\u0010<\u001a\u0004\u0018\u00010;H\u0016¢\u0006\u0004\b=\u0010>J\u0011\u0010@\u001a\u0004\u0018\u00010?H\u0016¢\u0006\u0004\b@\u0010AJ\u0017\u0010C\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u000fH\u0016¢\u0006\u0004\bC\u0010+J\u0017\u0010E\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\u0006H\u0016¢\u0006\u0004\bE\u0010FJ#\u0010K\u001a\u00020\u00062\b\u0010H\u001a\u0004\u0018\u00010G2\b\u0010J\u001a\u0004\u0018\u00010IH\u0016¢\u0006\u0004\bK\u0010LJ)\u0010P\u001a\u00020\u00062\u0006\u0010N\u001a\u00020M2\u0006\u0010#\u001a\u00020\u000f2\b\u0010O\u001a\u0004\u0018\u00010IH\u0016¢\u0006\u0004\bP\u0010QJ(\u0010V\u001a\u00020\n2\u0017\u0010U\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020\n0R¢\u0006\u0002\bTH\u0002¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020\u0006H\u0002¢\u0006\u0004\bX\u0010\bJ\u000f\u0010Y\u001a\u00020\u0006H\u0002¢\u0006\u0004\bY\u0010\bJ\u0017\u0010[\u001a\u00020\n2\u0006\u0010Z\u001a\u00020\u000fH\u0002¢\u0006\u0004\b[\u0010\\J\u0017\u0010^\u001a\u00020\n2\u0006\u0010]\u001a\u00020GH\u0002¢\u0006\u0004\b^\u0010_R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010`R\u0016\u0010a\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010bR+\u0010d\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020\n0R¢\u0006\u0002\bT0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010\u000e\u001a\u00020f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bg\u0010h¨\u0006i"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/StatelessInputConnection;", "Landroid/view/inputmethod/InputConnection;", "Landroidx/compose/foundation/text2/input/internal/TextInputSession;", SettingsSessionManager.SETTINGS_KEY, "<init>", "(Landroidx/compose/foundation/text2/input/internal/TextInputSession;)V", "", "beginBatchEdit", "()Z", "endBatchEdit", "Lx6/t0;", "closeConnection", "()V", "", "text", "", "newCursorPosition", "commitText", "(Ljava/lang/CharSequence;I)Z", TtmlNode.START, TtmlNode.END, "setComposingRegion", "(II)Z", "setComposingText", "beforeLength", "afterLength", "deleteSurroundingTextInCodePoints", "deleteSurroundingText", "setSelection", "finishComposingText", "Landroid/view/KeyEvent;", "event", "sendKeyEvent", "(Landroid/view/KeyEvent;)Z", "maxChars", "flags", "getTextBeforeCursor", "(II)Ljava/lang/CharSequence;", "getTextAfterCursor", "getSelectedText", "(I)Ljava/lang/CharSequence;", "cursorUpdateMode", "requestCursorUpdates", "(I)Z", "Landroid/view/inputmethod/ExtractedTextRequest;", "request", "Landroid/view/inputmethod/ExtractedText;", "getExtractedText", "(Landroid/view/inputmethod/ExtractedTextRequest;I)Landroid/view/inputmethod/ExtractedText;", "reqModes", "getCursorCapsMode", "(I)I", TtmlNode.ATTR_ID, "performContextMenuAction", "editorAction", "performEditorAction", "Landroid/view/inputmethod/CompletionInfo;", "commitCompletion", "(Landroid/view/inputmethod/CompletionInfo;)Z", "Landroid/view/inputmethod/CorrectionInfo;", "correctionInfo", "commitCorrection", "(Landroid/view/inputmethod/CorrectionInfo;)Z", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "states", "clearMetaKeyStates", "enabled", "reportFullscreenMode", "(Z)Z", "", "action", "Landroid/os/Bundle;", "data", "performPrivateCommand", "(Ljava/lang/String;Landroid/os/Bundle;)Z", "Landroid/view/inputmethod/InputContentInfo;", "inputContentInfo", "opts", "commitContent", "(Landroid/view/inputmethod/InputContentInfo;ILandroid/os/Bundle;)Z", "Lkotlin/Function1;", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/n;", "editCommand", "addEditCommandWithBatch", "(Lr7/l;)V", "beginBatchEditInternal", "endBatchEditInternal", "code", "sendSynthesizedKeyEvent", "(I)V", "message", "logDebug", "(Ljava/lang/String;)V", "Landroidx/compose/foundation/text2/input/internal/TextInputSession;", "batchDepth", "I", "Landroidx/compose/runtime/collection/MutableVector;", "editCommands", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StatelessInputConnection implements InputConnection {
    public static final int $stable = 8;
    private int batchDepth;
    private final MutableVector<l<EditingBuffer, t0>> editCommands = new MutableVector<>(new l[16], 0);
    private final TextInputSession session;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$commitText$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $newCursorPosition;
        final /* synthetic */ CharSequence $text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CharSequence charSequence, int i10) {
            super(1);
            this.$text = charSequence;
            this.$newCursorPosition = i10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.commitText(editingBuffer, String.valueOf(this.$text), this.$newCursorPosition);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$deleteSurroundingText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04091 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $afterLength;
        final /* synthetic */ int $beforeLength;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04091(int i10, int i11) {
            super(1);
            this.$beforeLength = i10;
            this.$afterLength = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.deleteSurroundingText(editingBuffer, this.$beforeLength, this.$afterLength);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$deleteSurroundingTextInCodePoints$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04101 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $afterLength;
        final /* synthetic */ int $beforeLength;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04101(int i10, int i11) {
            super(1);
            this.$beforeLength = i10;
            this.$afterLength = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.deleteSurroundingTextInCodePoints(editingBuffer, this.$beforeLength, this.$afterLength);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$endBatchEditInternal$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04111 extends r implements l<EditingBuffer, t0> {
        public C04111() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            MutableVector mutableVector = StatelessInputConnection.this.editCommands;
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    ((l) content[i10]).invoke(editingBuffer);
                    i10++;
                } while (i10 < size);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$finishComposingText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04121 extends r implements l<EditingBuffer, t0> {
        public static final C04121 INSTANCE = new C04121();

        public C04121() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.finishComposingText(editingBuffer);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$performContextMenuAction$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04131 extends r implements l<EditingBuffer, t0> {
        public C04131() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            editingBuffer.setSelection(0, StatelessInputConnection.this.getText().length());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$setComposingRegion$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04141 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $end;
        final /* synthetic */ int $start;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04141(int i10, int i11) {
            super(1);
            this.$start = i10;
            this.$end = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.setComposingRegion(editingBuffer, this.$start, this.$end);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$setComposingText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04151 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $newCursorPosition;
        final /* synthetic */ CharSequence $text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04151(CharSequence charSequence, int i10) {
            super(1);
            this.$text = charSequence;
            this.$newCursorPosition = i10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            EditCommandKt.setComposingText(editingBuffer, String.valueOf(this.$text), this.$newCursorPosition);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StatelessInputConnection$setSelection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04161 extends r implements l<EditingBuffer, t0> {
        final /* synthetic */ int $end;
        final /* synthetic */ int $start;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04161(int i10, int i11) {
            super(1);
            this.$start = i10;
            this.$end = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((EditingBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(EditingBuffer editingBuffer) {
            editingBuffer.setSelection(this.$start, this.$end);
        }
    }

    public StatelessInputConnection(TextInputSession textInputSession) {
        this.session = textInputSession;
    }

    private final void addEditCommandWithBatch(l<? super EditingBuffer, t0> editCommand) {
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
        if (i10 == 0 && this.editCommands.isNotEmpty()) {
            this.session.requestEdit(new C04111());
            this.editCommands.clear();
        }
        return this.batchDepth > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextFieldCharSequence getText() {
        return this.session.getText();
    }

    private final void logDebug(String message) {
    }

    private final void sendSynthesizedKeyEvent(int code) {
        sendKeyEvent(new KeyEvent(0, code));
        sendKeyEvent(new KeyEvent(1, code));
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        logDebug("beginBatchEdit()");
        return beginBatchEditInternal();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int states) {
        logDebug(c.k("clearMetaKeyStates(", states, ')'));
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
        logDebug("closeConnection()");
        this.editCommands.clear();
        this.batchDepth = 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo text) {
        StringBuilder sb2 = new StringBuilder("commitCompletion(");
        sb2.append((Object) (text != null ? text.getText() : null));
        sb2.append(')');
        logDebug(sb2.toString());
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
        logDebug("commitContent(" + inputContentInfo + ", " + flags + ", " + opts + ')');
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        logDebug("commitText(\"" + ((Object) text) + "\", " + newCursorPosition + ')');
        addEditCommandWithBatch(new AnonymousClass1(text, newCursorPosition));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        logDebug(androidx.compose.foundation.c.r("deleteSurroundingText(", beforeLength, ", ", afterLength, ')'));
        addEditCommandWithBatch(new C04091(beforeLength, afterLength));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        logDebug(androidx.compose.foundation.c.r("deleteSurroundingTextInCodePoints(", beforeLength, ", ", afterLength, ')'));
        addEditCommandWithBatch(new C04101(beforeLength, afterLength));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        logDebug("endBatchEdit()");
        return endBatchEditInternal();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        logDebug("finishComposingText()");
        addEditCommandWithBatch(C04121.INSTANCE);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int reqModes) {
        logDebug(c.k("getCursorCapsMode(", reqModes, ')'));
        return TextUtils.getCapsMode(getText(), TextRange.m5190getMinimpl(getText().getSelectionInChars()), reqModes);
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        logDebug("getExtractedText(" + request + ", " + flags + ')');
        return StatelessInputConnection_androidKt.toExtractedText(getText());
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        logDebug("getHandler()");
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int flags) {
        String string = TextRange.m5186getCollapsedimpl(getText().getSelectionInChars()) ? null : TextFieldCharSequenceKt.getSelectedText(getText()).toString();
        logDebug("getSelectedText(" + flags + "): " + ((Object) string));
        return string;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int maxChars, int flags) {
        String string = TextFieldCharSequenceKt.getTextAfterSelection(getText(), maxChars).toString();
        StringBuilder sbV = androidx.compose.foundation.c.v("getTextAfterCursor(", maxChars, ", ", flags, "): ");
        sbV.append(string);
        logDebug(sbV.toString());
        return string;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int maxChars, int flags) {
        String string = TextFieldCharSequenceKt.getTextBeforeSelection(getText(), maxChars).toString();
        StringBuilder sbV = androidx.compose.foundation.c.v("getTextBeforeCursor(", maxChars, ", ", flags, "): ");
        sbV.append(string);
        logDebug(sbV.toString());
        return string;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int id) {
        logDebug(c.k("performContextMenuAction(", id, ')'));
        switch (id) {
            case R.id.selectAll:
                addEditCommandWithBatch(new C04131());
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
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int editorAction) {
        int iM5367getDefaulteUduSuo;
        logDebug(c.k("performEditorAction(", editorAction, ')'));
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
                    logDebug(c.i(editorAction, "IME sent an unrecognized editor action: "));
                    iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5367getDefaulteUduSuo();
                    break;
            }
        } else {
            iM5367getDefaulteUduSuo = ImeAction.INSTANCE.m5367getDefaulteUduSuo();
        }
        this.session.mo1082onImeActionKlQnJC8(iM5367getDefaulteUduSuo);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String action, Bundle data) {
        logDebug("performPrivateCommand(" + action + ", " + data + ')');
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean enabled) {
        logDebug("reportFullscreenMode(" + enabled + ')');
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        logDebug(c.k("requestCursorUpdates(", cursorUpdateMode, ')'));
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        logDebug("sendKeyEvent(" + event + ')');
        this.session.sendKeyEvent(event);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int start, int end) {
        logDebug(androidx.compose.foundation.c.r("setComposingRegion(", start, ", ", end, ')'));
        addEditCommandWithBatch(new C04141(start, end));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        logDebug("setComposingText(\"" + ((Object) text) + "\", " + newCursorPosition + ')');
        addEditCommandWithBatch(new C04151(text, newCursorPosition));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int start, int end) {
        logDebug(androidx.compose.foundation.c.r("setSelection(", start, ", ", end, ')'));
        addEditCommandWithBatch(new C04161(start, end));
        return true;
    }
}
