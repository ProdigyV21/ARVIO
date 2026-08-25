package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class y0 extends w2 implements b1 {
    public CharSequence M;
    public v0 N;
    public final Rect O;
    public int P;
    public final /* synthetic */ c1 Q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(c1 c1Var, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle);
        this.Q = c1Var;
        this.O = new Rect();
        this.f1824y = c1Var;
        this.H = true;
        this.I.setFocusable(true);
        this.z = new w0(this, 0);
    }

    @Override // androidx.appcompat.widget.b1
    public final CharSequence e() {
        return this.M;
    }

    @Override // androidx.appcompat.widget.b1
    public final void g(CharSequence charSequence) {
        this.M = charSequence;
    }

    @Override // androidx.appcompat.widget.b1
    public final void i(int i10) {
        this.P = i10;
    }

    @Override // androidx.appcompat.widget.b1
    public final void j(int i10, int i11) {
        ViewTreeObserver viewTreeObserver;
        i0 i0Var = this.I;
        boolean zIsShowing = i0Var.isShowing();
        p();
        i0Var.setInputMethodMode(2);
        show();
        g2 g2Var = this.f1813m;
        g2Var.setChoiceMode(1);
        s0.d(g2Var, i10);
        s0.c(g2Var, i11);
        c1 c1Var = this.Q;
        int selectedItemPosition = c1Var.getSelectedItemPosition();
        g2 g2Var2 = this.f1813m;
        if (i0Var.isShowing() && g2Var2 != null) {
            g2Var2.setListSelectionHidden(false);
            g2Var2.setSelection(selectedItemPosition);
            if (g2Var2.getChoiceMode() != 0) {
                g2Var2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zIsShowing || (viewTreeObserver = c1Var.getViewTreeObserver()) == null) {
            return;
        }
        q0 q0Var = new q0(this, 1);
        viewTreeObserver.addOnGlobalLayoutListener(q0Var);
        i0Var.setOnDismissListener(new x0(this, q0Var));
    }

    @Override // androidx.appcompat.widget.w2, androidx.appcompat.widget.b1
    public final void k(ListAdapter listAdapter) {
        super.k(listAdapter);
        this.N = (v0) listAdapter;
    }

    public final void p() {
        int i10;
        c1 c1Var = this.Q;
        Rect rect = c1Var.f1535r;
        i0 i0Var = this.I;
        Drawable background = i0Var.getBackground();
        if (background != null) {
            background.getPadding(rect);
            i10 = w4.a(c1Var) ? rect.right : -rect.left;
        } else {
            i10 = 0;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = c1Var.getPaddingLeft();
        int paddingRight = c1Var.getPaddingRight();
        int width = c1Var.getWidth();
        int i11 = c1Var.f1534q;
        if (i11 == -2) {
            int iA = c1Var.a(this.N, i0Var.getBackground());
            int i12 = (c1Var.getContext().getResources().getDisplayMetrics().widthPixels - rect.left) - rect.right;
            if (iA > i12) {
                iA = i12;
            }
            o(Math.max(iA, (width - paddingLeft) - paddingRight));
        } else if (i11 == -1) {
            o((width - paddingLeft) - paddingRight);
        } else {
            o(i11);
        }
        this.f1816p = w4.a(c1Var) ? (((width - paddingRight) - this.f1815o) - this.P) + i10 : paddingLeft + this.P + i10;
    }
}
