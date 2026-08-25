package androidx.emoji2.text;

import android.text.PrecomputedText;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 extends a1.a {
    @Override // a1.a
    public final boolean n(CharSequence charSequence) {
        return (charSequence instanceof PrecomputedText) || (charSequence instanceof androidx.core.text.g);
    }
}
