package e1;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements TextWatcher {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final EditText f15016i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public h f15017l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15018m = true;

    public i(EditText editText) {
        this.f15016i = editText;
    }

    public static void a(EditText editText, int i10) {
        int length;
        if (i10 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            o oVarA = o.a();
            if (editableText == null) {
                length = 0;
            } else {
                oVarA.getClass();
                length = editableText.length();
            }
            oVarA.f(editableText, 0, length);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) throws Throwable {
        EditText editText = this.f15016i;
        if (!editText.isInEditMode() && this.f15018m && o.c() && i11 <= i12 && (charSequence instanceof Spannable)) {
            int iB = o.a().b();
            if (iB != 0) {
                if (iB == 1) {
                    o.a().f((Spannable) charSequence, i10, i12 + i10);
                    return;
                } else if (iB != 3) {
                    return;
                }
            }
            o oVarA = o.a();
            if (this.f15017l == null) {
                this.f15017l = new h(editText);
            }
            oVarA.g(this.f15017l);
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
    }
}
