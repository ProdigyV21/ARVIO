package e1;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements InputFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f15007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f15008b;

    public d(TextView textView) {
        this.f15007a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i10, int i11, Spanned spanned, int i12, int i13) {
        TextView textView = this.f15007a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int iB = o.a().b();
        if (iB != 0) {
            if (iB == 1) {
                if ((i13 == 0 && i12 == 0 && spanned.length() == 0 && charSequence == textView.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i10 != 0 || i11 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i10, i11);
                }
                return o.a().f(charSequence, 0, charSequence.length());
            }
            if (iB != 3) {
                return charSequence;
            }
        }
        o oVarA = o.a();
        if (this.f15008b == null) {
            this.f15008b = new c(textView, this);
        }
        oVarA.g(this.f15008b);
        return charSequence;
    }
}
