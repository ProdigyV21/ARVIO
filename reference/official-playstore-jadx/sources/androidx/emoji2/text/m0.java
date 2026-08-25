package androidx.emoji2.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import j$.util.stream.IntStream;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes3.dex */
public final class m0 implements Spannable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2624i = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Spannable f2625l;

    public m0(Spannable spannable) {
        this.f2625l = spannable;
    }

    public final void a() {
        Spannable spannable = this.f2625l;
        if (!this.f2624i) {
            if ((Build.VERSION.SDK_INT < 28 ? new a1.a() : new l0()).n(spannable)) {
                this.f2625l = new SpannableString(spannable);
            }
        }
        this.f2624i = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i10) {
        return this.f2625l.charAt(i10);
    }

    @Override // java.lang.CharSequence
    public /* synthetic */ IntStream chars() {
        return IntStream.Wrapper.convert(chars());
    }

    @Override // java.lang.CharSequence
    public /* synthetic */ java.util.stream.IntStream codePoints() {
        return IntStream.Wrapper.convert(codePoints());
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f2625l.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f2625l.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f2625l.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i10, int i11, Class cls) {
        return this.f2625l.getSpans(i10, i11, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f2625l.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i10, int i11, Class cls) {
        return this.f2625l.nextSpanTransition(i10, i11, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.f2625l.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i10, int i11, int i12) {
        a();
        this.f2625l.setSpan(obj, i10, i11, i12);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i10, int i11) {
        return this.f2625l.subSequence(i10, i11);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f2625l.toString();
    }

    @Override // java.lang.CharSequence
    public final j$.util.stream.IntStream chars() {
        return k0.a(this.f2625l);
    }

    @Override // java.lang.CharSequence
    public final j$.util.stream.IntStream codePoints() {
        return k0.b(this.f2625l);
    }
}
