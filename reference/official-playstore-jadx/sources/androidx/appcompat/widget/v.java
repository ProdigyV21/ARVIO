package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class v extends Button implements androidx.core.view.y0, u0.b, u0.u, h2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k1 f1781l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d0 f1782m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
        f4.a(context);
        d4.a(getContext(), this);
        u uVar = new u(this);
        this.f1780i = uVar;
        uVar.l(attributeSet, R.attr.buttonStyle);
        k1 k1Var = new k1(this);
        this.f1781l = k1Var;
        k1Var.f(attributeSet, R.attr.buttonStyle);
        k1Var.b();
        getEmojiTextViewHelper().a(attributeSet, R.attr.buttonStyle);
    }

    private d0 getEmojiTextViewHelper() {
        if (this.f1782m == null) {
            this.f1782m = new d0(this);
        }
        return this.f1782m;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1780i;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (w4.f1827b) {
            return super.getAutoSizeMaxTextSize();
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1791e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (w4.f1827b) {
            return super.getAutoSizeMinTextSize();
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1790d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (w4.f1827b) {
            return super.getAutoSizeStepGranularity();
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            return Math.round(k1Var.f1633i.f1789c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (w4.f1827b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        k1 k1Var = this.f1781l;
        return k1Var != null ? k1Var.f1633i.f1792f : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (w4.f1827b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            return k1Var.f1633i.f1787a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return m2.f0.W(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1780i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1780i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1781l.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1781l.e();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        k1 k1Var = this.f1781l;
        if (k1Var == null || w4.f1827b) {
            return;
        }
        k1Var.f1633i.a();
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        super.onTextChanged(charSequence, i10, i11, i12);
        k1 k1Var = this.f1781l;
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
            super.setAutoSizeTextTypeUniformWithConfiguration(i10, i11, i12, i13);
            return;
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.h(i10, i11, i12, i13);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i10) {
        if (w4.f1827b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i10);
            return;
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.i(iArr, i10);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i10) {
        if (w4.f1827b) {
            super.setAutoSizeTextTypeWithDefaults(i10);
            return;
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.j(i10);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1780i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1780i;
        if (uVar != null) {
            uVar.o(i10);
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

    public void setSupportAllCaps(boolean z) {
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.f1625a.setAllCaps(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1780i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1780i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1781l;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1781l;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i10) {
        super.setTextAppearance(context, i10);
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            k1Var.g(context, i10);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i10, float f10) {
        boolean z = w4.f1827b;
        if (z) {
            super.setTextSize(i10, f10);
            return;
        }
        k1 k1Var = this.f1781l;
        if (k1Var != null) {
            v1 v1Var = k1Var.f1633i;
            if (z || v1Var.f()) {
                return;
            }
            v1Var.g(f10, i10);
        }
    }
}
