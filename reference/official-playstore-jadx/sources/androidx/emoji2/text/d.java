package androidx.emoji2.text;

import android.text.TextPaint;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal f2595b = new ThreadLocal();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f2596a;

    public d() {
        TextPaint textPaint = new TextPaint();
        this.f2596a = textPaint;
        textPaint.setTextSize(10.0f);
    }

    @Override // androidx.emoji2.text.i
    public final boolean a(CharSequence charSequence, int i10, int i11) {
        ThreadLocal threadLocal = f2595b;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        StringBuilder sb2 = (StringBuilder) threadLocal.get();
        sb2.setLength(0);
        while (i10 < i11) {
            sb2.append(charSequence.charAt(i10));
            i10++;
        }
        String string = sb2.toString();
        int i12 = androidx.core.graphics.e.f2100a;
        return androidx.core.graphics.d.a(this.f2596a, string);
    }
}
