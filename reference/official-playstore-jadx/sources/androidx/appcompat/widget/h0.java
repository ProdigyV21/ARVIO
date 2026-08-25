package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends MultiAutoCompleteTextView implements androidx.core.view.y0, h2, u0.u {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f1594n = {R.attr.popupBackground};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1595i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k1 f1596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j0 f1597m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.arvio.tv.R.attr.autoCompleteTextViewStyle);
        f4.a(context);
        d4.a(getContext(), this);
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(getContext(), attributeSet, f1594n, com.arvio.tv.R.attr.autoCompleteTextViewStyle);
        if (((TypedArray) i1VarZ.f1060m).hasValue(0)) {
            setDropDownBackgroundDrawable(i1VarZ.T(0));
        }
        i1VarZ.b0();
        u uVar = new u(this);
        this.f1595i = uVar;
        uVar.l(attributeSet, com.arvio.tv.R.attr.autoCompleteTextViewStyle);
        k1 k1Var = new k1(this);
        this.f1596l = k1Var;
        k1Var.f(attributeSet, com.arvio.tv.R.attr.autoCompleteTextViewStyle);
        k1Var.b();
        j0 j0Var = new j0(this);
        this.f1597m = j0Var;
        j0Var.b(attributeSet, com.arvio.tv.R.attr.autoCompleteTextViewStyle);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = isFocusable();
        boolean zIsClickable = isClickable();
        boolean zIsLongClickable = isLongClickable();
        int inputType = getInputType();
        KeyListener keyListenerA = j0Var.a(keyListener);
        if (keyListenerA == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerA);
        setRawInputType(inputType);
        setFocusable(zIsFocusable);
        setClickable(zIsClickable);
        setLongClickable(zIsLongClickable);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1595i;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1596l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1595i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1595i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1596l.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1596l.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        k2.c.x(inputConnectionOnCreateInputConnection, editorInfo, this);
        return this.f1597m.c(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1595i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1595i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1596l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1596l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i10) {
        setDropDownBackgroundDrawable(m2.f0.o(getContext(), i10));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.f1597m.d(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f1597m.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1595i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1595i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1596l;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1596l;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i10) {
        super.setTextAppearance(context, i10);
        k1 k1Var = this.f1596l;
        if (k1Var != null) {
            k1Var.g(context, i10);
        }
    }
}
