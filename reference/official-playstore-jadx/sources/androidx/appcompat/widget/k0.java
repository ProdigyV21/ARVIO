package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends RadioButton implements u0.t, androidx.core.view.y0, h2, u0.u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y f1621i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u f1622l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k1 f1623m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d0 f1624n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.radioButtonStyle);
        f4.a(context);
        d4.a(getContext(), this);
        y yVar = new y(this);
        this.f1621i = yVar;
        yVar.c(attributeSet, R.attr.radioButtonStyle);
        u uVar = new u(this);
        this.f1622l = uVar;
        uVar.l(attributeSet, R.attr.radioButtonStyle);
        k1 k1Var = new k1(this);
        this.f1623m = k1Var;
        k1Var.f(attributeSet, R.attr.radioButtonStyle);
        getEmojiTextViewHelper().a(attributeSet, R.attr.radioButtonStyle);
    }

    private d0 getEmojiTextViewHelper() {
        if (this.f1624n == null) {
            this.f1624n = new d0(this);
        }
        return this.f1624n;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1622l;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1623m;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        y yVar = this.f1621i;
        if (yVar != null) {
            yVar.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1622l;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1622l;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        y yVar = this.f1621i;
        if (yVar != null) {
            return yVar.f1835a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        y yVar = this.f1621i;
        if (yVar != null) {
            return yVar.f1836b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1623m.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1623m.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1622l;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1622l;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        y yVar = this.f1621i;
        if (yVar != null) {
            if (yVar.f1839e) {
                yVar.f1839e = false;
            } else {
                yVar.f1839e = true;
                yVar.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1623m;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1623m;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((qb.d) getEmojiTextViewHelper().f1545b.f183l).E(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1622l;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1622l;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        y yVar = this.f1621i;
        if (yVar != null) {
            yVar.f1835a = colorStateList;
            yVar.f1837c = true;
            yVar.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        y yVar = this.f1621i;
        if (yVar != null) {
            yVar.f1836b = mode;
            yVar.f1838d = true;
            yVar.a();
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1623m;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1623m;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i10) {
        setButtonDrawable(m2.f0.o(getContext(), i10));
    }
}
