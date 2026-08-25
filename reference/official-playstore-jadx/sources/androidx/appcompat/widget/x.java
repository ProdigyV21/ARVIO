package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class x extends CheckedTextView implements u0.s, androidx.core.view.y0, h2, u0.u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y f1828i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u f1829l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k1 f1830m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d0 f1831n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        f4.a(context);
        d4.a(getContext(), this);
        k1 k1Var = new k1(this);
        this.f1830m = k1Var;
        k1Var.f(attributeSet, R.attr.checkedTextViewStyle);
        k1Var.b();
        u uVar = new u(this);
        this.f1829l = uVar;
        uVar.l(attributeSet, R.attr.checkedTextViewStyle);
        this.f1828i = new y(this);
        Context context2 = getContext();
        int[] iArr = j.a.f19027l;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context2, attributeSet, iArr, R.attr.checkedTextViewStyle);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        androidx.core.view.b2.g(this, getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, R.attr.checkedTextViewStyle);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    setCheckMarkDrawable(m2.f0.o(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        setCheckMarkDrawable(m2.f0.o(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                setCheckMarkDrawable(m2.f0.o(getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                setCheckMarkTintList(i1VarZ.S(2));
            }
            if (typedArray.hasValue(3)) {
                setCheckMarkTintMode(z1.b(typedArray.getInt(3, -1), null));
            }
            i1VarZ.b0();
            getEmojiTextViewHelper().a(attributeSet, R.attr.checkedTextViewStyle);
        } catch (Throwable th) {
            i1VarZ.b0();
            throw th;
        }
    }

    private d0 getEmojiTextViewHelper() {
        if (this.f1831n == null) {
            this.f1831n = new d0(this);
        }
        return this.f1831n;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        k1 k1Var = this.f1830m;
        if (k1Var != null) {
            k1Var.b();
        }
        u uVar = this.f1829l;
        if (uVar != null) {
            uVar.i();
        }
        y yVar = this.f1828i;
        if (yVar != null) {
            yVar.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return m2.f0.W(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1829l;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1829l;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        y yVar = this.f1828i;
        if (yVar != null) {
            return yVar.f1835a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        y yVar = this.f1828i;
        if (yVar != null) {
            return yVar.f1836b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1830m.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1830m.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        k2.c.x(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().b(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1829l;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1829l;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        y yVar = this.f1828i;
        if (yVar != null) {
            if (yVar.f1839e) {
                yVar.f1839e = false;
            } else {
                yVar.f1839e = true;
                yVar.b();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1830m;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1830m;
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

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1829l;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1829l;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        y yVar = this.f1828i;
        if (yVar != null) {
            yVar.f1835a = colorStateList;
            yVar.f1837c = true;
            yVar.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        y yVar = this.f1828i;
        if (yVar != null) {
            yVar.f1836b = mode;
            yVar.f1838d = true;
            yVar.b();
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1830m;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1830m;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i10) {
        super.setTextAppearance(context, i10);
        k1 k1Var = this.f1830m;
        if (k1Var != null) {
            k1Var.g(context, i10);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i10) {
        setCheckMarkDrawable(m2.f0.o(getContext(), i10));
    }
}
