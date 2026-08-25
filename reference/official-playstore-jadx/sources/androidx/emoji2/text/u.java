package androidx.emoji2.text;

import android.text.Spannable;
import android.text.SpannableString;

/* JADX INFO: loaded from: classes3.dex */
public final class u implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m0 f2645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f2646b;

    public u(m0 m0Var, n nVar) {
        this.f2645a = m0Var;
        this.f2646b = nVar;
    }

    @Override // androidx.emoji2.text.v
    public final boolean b(CharSequence charSequence, int i10, int i11, i0 i0Var) {
        if ((i0Var.f2618c & 4) > 0) {
            return true;
        }
        if (this.f2645a == null) {
            this.f2645a = new m0(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
        }
        this.f2645a.setSpan(this.f2646b.h(i0Var), i10, i11, 33);
        return true;
    }

    @Override // androidx.emoji2.text.v
    public final Object getResult() {
        return this.f2645a;
    }
}
