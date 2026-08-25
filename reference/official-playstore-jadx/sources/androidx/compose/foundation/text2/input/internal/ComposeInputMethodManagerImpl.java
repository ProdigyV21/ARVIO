package androidx.compose.foundation.text2.input.internal;

import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodManager;
import androidx.core.view.x0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\"\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\u000bJ\u000f\u0010\r\u001a\u00020\tH\u0016¢\u0006\u0004\b\r\u0010\u000bJ\u001f\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0006H\u0004¢\u0006\u0004\b\u001a\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManagerImpl;", "Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManager;", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "Landroid/view/inputmethod/InputMethodManager;", "createImm", "()Landroid/view/inputmethod/InputMethodManager;", "Lx6/t0;", "restartInput", "()V", "showSoftInput", "hideSoftInput", "", "token", "Landroid/view/inputmethod/ExtractedText;", "extractedText", "updateExtractedText", "(ILandroid/view/inputmethod/ExtractedText;)V", "selectionStart", "selectionEnd", "compositionStart", "compositionEnd", "updateSelection", "(IIII)V", "requireImm", "Landroid/view/View;", "getView", "()Landroid/view/View;", "imm", "Landroid/view/inputmethod/InputMethodManager;", "Landroidx/core/view/x0;", "softwareKeyboardControllerCompat", "Landroidx/core/view/x0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class ComposeInputMethodManagerImpl implements ComposeInputMethodManager {
    private InputMethodManager imm;
    private final x0 softwareKeyboardControllerCompat;
    private final View view;

    public ComposeInputMethodManagerImpl(View view) {
        this.view = view;
        this.softwareKeyboardControllerCompat = new x0(view);
    }

    private final InputMethodManager createImm() {
        return (InputMethodManager) this.view.getContext().getSystemService("input_method");
    }

    public final View getView() {
        return this.view;
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void hideSoftInput() {
        this.softwareKeyboardControllerCompat.f2333a.b();
    }

    public final InputMethodManager requireImm() {
        InputMethodManager inputMethodManager = this.imm;
        if (inputMethodManager != null) {
            return inputMethodManager;
        }
        InputMethodManager inputMethodManagerCreateImm = createImm();
        this.imm = inputMethodManagerCreateImm;
        return inputMethodManagerCreateImm;
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void restartInput() {
        requireImm().restartInput(this.view);
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void showSoftInput() {
        this.softwareKeyboardControllerCompat.f2333a.f();
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void updateExtractedText(int token, ExtractedText extractedText) {
        requireImm().updateExtractedText(this.view, token, extractedText);
    }

    @Override // androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager
    public void updateSelection(int selectionStart, int selectionEnd, int compositionStart, int compositionEnd) {
        requireImm().updateSelection(this.view, selectionStart, selectionEnd, compositionStart, compositionEnd);
    }
}
