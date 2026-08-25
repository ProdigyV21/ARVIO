package androidx.emoji2.text;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 extends SpannableStringBuilder {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f2610i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f2611l;

    public g0(Class cls, CharSequence charSequence) {
        super(charSequence);
        this.f2611l = new ArrayList();
        p0.g.b(cls, "watcherClass cannot be null");
        this.f2610i = cls;
    }

    public final void a() {
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f2611l;
            if (i10 >= arrayList.size()) {
                return;
            }
            ((f0) arrayList.get(i10)).f2606l.incrementAndGet();
            i10++;
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    public final void b() {
        e();
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f2611l;
            if (i10 >= arrayList.size()) {
                return;
            }
            ((f0) arrayList.get(i10)).onTextChanged(this, 0, length(), length());
            i10++;
        }
    }

    public final f0 c(Object obj) {
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f2611l;
            if (i10 >= arrayList.size()) {
                return null;
            }
            f0 f0Var = (f0) arrayList.get(i10);
            if (f0Var.f2605i == obj) {
                return f0Var;
            }
            i10++;
        }
    }

    public final boolean d(Object obj) {
        if (obj != null) {
            return this.f2610i == obj.getClass();
        }
        return false;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable delete(int i10, int i11) {
        super.delete(i10, i11);
        return this;
    }

    public final void e() {
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f2611l;
            if (i10 >= arrayList.size()) {
                return;
            }
            ((f0) arrayList.get(i10)).f2606l.decrementAndGet();
            i10++;
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanEnd(Object obj) {
        f0 f0VarC;
        if (d(obj) && (f0VarC = c(obj)) != null) {
            obj = f0VarC;
        }
        return super.getSpanEnd(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanFlags(Object obj) {
        f0 f0VarC;
        if (d(obj) && (f0VarC = c(obj)) != null) {
            obj = f0VarC;
        }
        return super.getSpanFlags(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanStart(Object obj) {
        f0 f0VarC;
        if (d(obj) && (f0VarC = c(obj)) != null) {
            obj = f0VarC;
        }
        return super.getSpanStart(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final Object[] getSpans(int i10, int i11, Class cls) {
        if (this.f2610i != cls) {
            return super.getSpans(i10, i11, cls);
        }
        f0[] f0VarArr = (f0[]) super.getSpans(i10, i11, f0.class);
        Object[] objArr = (Object[]) Array.newInstance((Class<?>) cls, f0VarArr.length);
        for (int i12 = 0; i12 < f0VarArr.length; i12++) {
            objArr[i12] = f0VarArr[i12].f2605i;
        }
        return objArr;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable insert(int i10, CharSequence charSequence) {
        super.insert(i10, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int nextSpanTransition(int i10, int i11, Class cls) {
        if (cls == null || this.f2610i == cls) {
            cls = f0.class;
        }
        return super.nextSpanTransition(i10, i11, cls);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public final void removeSpan(Object obj) {
        f0 f0VarC;
        if (d(obj)) {
            f0VarC = c(obj);
            if (f0VarC != null) {
                obj = f0VarC;
            }
        } else {
            f0VarC = null;
        }
        super.removeSpan(obj);
        if (f0VarC != null) {
            this.f2611l.remove(f0VarC);
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final /* bridge */ /* synthetic */ Editable replace(int i10, int i11, CharSequence charSequence) {
        replace(i10, i11, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public final void setSpan(Object obj, int i10, int i11, int i12) {
        if (d(obj)) {
            f0 f0Var = new f0(obj);
            this.f2611l.add(f0Var);
            obj = f0Var;
        }
        super.setSpan(obj, i10, i11, i12);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public final CharSequence subSequence(int i10, int i11) {
        return new g0(this.f2610i, this, i10, i11);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder delete(int i10, int i11) {
        super.delete(i10, i11);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder insert(int i10, CharSequence charSequence) {
        super.insert(i10, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final /* bridge */ /* synthetic */ Editable replace(int i10, int i11, CharSequence charSequence, int i12, int i13) {
        replace(i10, i11, charSequence, i12, i13);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable insert(int i10, CharSequence charSequence, int i11, int i12) {
        super.insert(i10, charSequence, i11, i12);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder replace(int i10, int i11, CharSequence charSequence) {
        a();
        super.replace(i10, i11, charSequence);
        e();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(char c10) {
        super.append(c10);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder insert(int i10, CharSequence charSequence, int i11, int i12) {
        super.insert(i10, charSequence, i11, i12);
        return this;
    }

    public g0(Class cls, g0 g0Var, int i10, int i11) {
        super(g0Var, i10, i11);
        this.f2611l = new ArrayList();
        p0.g.b(cls, "watcherClass cannot be null");
        this.f2610i = cls;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(char c10) {
        super.append(c10);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(char c10) {
        super.append(c10);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder replace(int i10, int i11, CharSequence charSequence, int i12, int i13) {
        a();
        super.replace(i10, i11, charSequence, i12, i13);
        e();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(CharSequence charSequence, int i10, int i11) {
        super.append(charSequence, i10, i11);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(CharSequence charSequence, int i10, int i11) {
        super.append(charSequence, i10, i11);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i10, int i11) {
        super.append(charSequence, i10, i11);
        return this;
    }

    @Override // android.text.SpannableStringBuilder
    public final SpannableStringBuilder append(CharSequence charSequence, Object obj, int i10) {
        super.append(charSequence, obj, i10);
        return this;
    }
}
