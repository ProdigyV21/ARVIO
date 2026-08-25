package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class o1 extends TextView implements androidx.core.view.y0, u0.u, u0.b, h2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1685i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k1 f1686l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j0 f1687m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d0 f1688n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1689o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m1 f1690p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Future f1691q;

    public o1(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private d0 getEmojiTextViewHelper() {
        if (this.f1688n == null) {
            this.f1688n = new d0(this);
        }
        return this.f1688n;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1685i;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (w4.f1827b) {
            return getSuperCaller().i();
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1791e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (w4.f1827b) {
            return getSuperCaller().e();
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1790d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (w4.f1827b) {
            return getSuperCaller().m();
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1789c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (w4.f1827b) {
            return getSuperCaller().g();
        }
        k1 k1Var = this.f1686l;
        return k1Var != null ? k1Var.f1633i.f1792f : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (w4.f1827b) {
            return getSuperCaller().d() == 1 ? 1 : 0;
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            return k1Var.f1633i.f1787a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return m2.f0.W(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public l1 getSuperCaller() {
        if (this.f1690p == null) {
            int i10 = Build.VERSION.SDK_INT;
            if (i10 >= 28) {
                this.f1690p = new n1(this);
            } else if (i10 >= 26) {
                this.f1690p = new m1(this);
            }
        }
        return this.f1690p;
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1685i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1685i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1686l.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1686l.e();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        Future future = this.f1691q;
        if (future != null) {
            try {
                this.f1691q = null;
                if (future.get() != null) {
                    throw new ClassCastException();
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    throw null;
                }
                m2.f0.y(this);
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        j0 j0Var;
        if (Build.VERSION.SDK_INT >= 28 || (j0Var = this.f1687m) == null) {
            return getSuperCaller().h();
        }
        TextClassifier textClassifier = (TextClassifier) j0Var.f1606c;
        return textClassifier == null ? d1.a((TextView) j0Var.f1605b) : textClassifier;
    }

    public androidx.core.text.f getTextMetricsParamsCompat() {
        return m2.f0.y(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1686l.getClass();
        if (Build.VERSION.SDK_INT < 30 && inputConnectionOnCreateInputConnection != null) {
            androidx.core.view.inputmethod.b.c(editorInfo, getText());
        }
        k2.c.x(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        k1 k1Var = this.f1686l;
        if (k1Var == null || w4.f1827b) {
            return;
        }
        k1Var.f1633i.a();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i10, int i11) {
        Future future = this.f1691q;
        if (future != null) {
            try {
                this.f1691q = null;
                if (future.get() != null) {
                    throw new ClassCastException();
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    throw null;
                }
                m2.f0.y(this);
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        super.onMeasure(i10, i11);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        super.onTextChanged(charSequence, i10, i11, i12);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            v1 v1Var = k1Var.f1633i;
            if (w4.f1827b || !v1Var.f()) {
                return;
            }
            v1Var.a();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i10, int i11, int i12, int i13) {
        if (w4.f1827b) {
            getSuperCaller().k(i10, i11, i12, i13);
            return;
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.h(i10, i11, i12, i13);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i10) {
        if (w4.f1827b) {
            getSuperCaller().a(iArr, i10);
            return;
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.i(iArr, i10);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i10) {
        if (w4.f1827b) {
            getSuperCaller().n(i10);
            return;
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.j(i10);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1685i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1685i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m2.f0.X(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((qb.d) getEmojiTextViewHelper().f1545b.f183l).E(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i10) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().l(i10);
        } else {
            m2.f0.M(this, i10);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i10) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().c(i10);
        } else {
            m2.f0.N(this, i10);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException();
        }
        if (i10 != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i10 - r0, 1.0f);
        }
    }

    public void setPrecomputedText(androidx.core.text.g gVar) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        m2.f0.y(this);
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1685i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1685i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1686l;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1686l;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i10) {
        super.setTextAppearance(context, i10);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.g(context, i10);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        j0 j0Var;
        if (Build.VERSION.SDK_INT >= 28 || (j0Var = this.f1687m) == null) {
            getSuperCaller().j(textClassifier);
        } else {
            j0Var.f1606c = textClassifier;
        }
    }

    public void setTextFuture(Future<androidx.core.text.g> future) {
        this.f1691q = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(androidx.core.text.f fVar) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = fVar.f2183b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i10 = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i10 = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i10 = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i10 = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i10 = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i10 = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i10 = 7;
            }
        }
        setTextDirection(i10);
        getPaint().set(fVar.f2182a);
        u0.m.e(this, fVar.f2184c);
        u0.m.h(this, fVar.f2185d);
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i10, float f10) {
        boolean z = w4.f1827b;
        if (z) {
            super.setTextSize(i10, f10);
            return;
        }
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            v1 v1Var = k1Var.f1633i;
            if (z || v1Var.f()) {
                return;
            }
            v1Var.g(f10, i10);
        }
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i10) {
        Typeface typefaceCreate;
        if (this.f1689o) {
            return;
        }
        if (typeface == null || i10 <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            androidx.core.graphics.o oVar = androidx.core.graphics.g.f2103a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i10);
        }
        this.f1689o = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i10);
        } finally {
            this.f1689o = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o1(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        f4.a(context);
        this.f1689o = false;
        this.f1690p = null;
        d4.a(getContext(), this);
        u uVar = new u(this);
        this.f1685i = uVar;
        uVar.l(attributeSet, i10);
        k1 k1Var = new k1(this);
        this.f1686l = k1Var;
        k1Var.f(attributeSet, i10);
        k1Var.b();
        j0 j0Var = new j0();
        j0Var.f1605b = this;
        this.f1687m = j0Var;
        getEmojiTextViewHelper().a(attributeSet, i10);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i10, int i11, int i12, int i13) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i10 != 0 ? m2.f0.o(context, i10) : null, i11 != 0 ? m2.f0.o(context, i11) : null, i12 != 0 ? m2.f0.o(context, i12) : null, i13 != 0 ? m2.f0.o(context, i13) : null);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i10, int i11, int i12, int i13) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i10 != 0 ? m2.f0.o(context, i10) : null, i11 != 0 ? m2.f0.o(context, i11) : null, i12 != 0 ? m2.f0.o(context, i12) : null, i13 != 0 ? m2.f0.o(context, i13) : null);
        k1 k1Var = this.f1686l;
        if (k1Var != null) {
            k1Var.b();
        }
    }
}
