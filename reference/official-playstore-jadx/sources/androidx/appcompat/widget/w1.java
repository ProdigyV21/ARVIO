package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public final class w1 extends ToggleButton implements androidx.core.view.y0, h2, u0.u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1808i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k1 f1809l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d0 f1810m;

    public w1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        d4.a(getContext(), this);
        u uVar = new u(this);
        this.f1808i = uVar;
        uVar.l(attributeSet, R.attr.buttonStyleToggle);
        k1 k1Var = new k1(this);
        this.f1809l = k1Var;
        k1Var.f(attributeSet, R.attr.buttonStyleToggle);
        getEmojiTextViewHelper().a(attributeSet, R.attr.buttonStyleToggle);
    }

    private d0 getEmojiTextViewHelper() {
        if (this.f1810m == null) {
            this.f1810m = new d0(this);
        }
        return this.f1810m;
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1808i;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1809l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1808i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1808i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1809l.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1809l.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1808i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1808i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1809l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1809l;
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
        u uVar = this.f1808i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1808i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1809l;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1809l;
        k1Var.l(mode);
        k1Var.b();
    }
}
