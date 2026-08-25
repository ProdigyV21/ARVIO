package e1;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.widget.TextView;
import androidx.emoji2.text.o;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends androidx.emoji2.text.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f15005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference f15006b;

    public c(TextView textView, d dVar) {
        this.f15005a = new WeakReference(textView);
        this.f15006b = new WeakReference(dVar);
    }

    @Override // androidx.emoji2.text.j
    public final void onInitialized() throws Throwable {
        InputFilter[] filters;
        int length;
        TextView textView = (TextView) this.f15005a.get();
        InputFilter inputFilter = (InputFilter) this.f15006b.get();
        if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
            return;
        }
        for (InputFilter inputFilter2 : filters) {
            if (inputFilter2 == inputFilter) {
                if (textView.isAttachedToWindow()) {
                    CharSequence text = textView.getText();
                    o oVarA = o.a();
                    if (text == null) {
                        length = 0;
                    } else {
                        oVarA.getClass();
                        length = text.length();
                    }
                    CharSequence charSequenceF = oVarA.f(text, 0, length);
                    if (text == charSequenceF) {
                        return;
                    }
                    int selectionStart = Selection.getSelectionStart(charSequenceF);
                    int selectionEnd = Selection.getSelectionEnd(charSequenceF);
                    textView.setText(charSequenceF);
                    if (charSequenceF instanceof Spannable) {
                        Spannable spannable = (Spannable) charSequenceF;
                        if (selectionStart >= 0 && selectionEnd >= 0) {
                            Selection.setSelection(spannable, selectionStart, selectionEnd);
                            return;
                        } else if (selectionStart >= 0) {
                            Selection.setSelection(spannable, selectionStart);
                            return;
                        } else {
                            if (selectionEnd >= 0) {
                                Selection.setSelection(spannable, selectionEnd);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            }
        }
    }
}
