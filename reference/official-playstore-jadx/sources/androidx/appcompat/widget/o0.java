package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends j0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n0 f1679e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1680f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f1681g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f1682h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1683i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1684j;

    public o0(n0 n0Var) {
        super(n0Var);
        this.f1681g = null;
        this.f1682h = null;
        this.f1683i = false;
        this.f1684j = false;
        this.f1679e = n0Var;
    }

    @Override // androidx.appcompat.widget.j0
    public final void b(AttributeSet attributeSet, int i10) {
        super.b(attributeSet, R.attr.seekBarStyle);
        n0 n0Var = this.f1679e;
        Context context = n0Var.getContext();
        int[] iArr = j.a.f19023g;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, R.attr.seekBarStyle);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        androidx.core.view.b2.g(n0Var, n0Var.getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, R.attr.seekBarStyle);
        Drawable drawableU = i1VarZ.U(0);
        if (drawableU != null) {
            n0Var.setThumb(drawableU);
        }
        Drawable drawableT = i1VarZ.T(1);
        Drawable drawable = this.f1680f;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f1680f = drawableT;
        if (drawableT != null) {
            drawableT.setCallback(n0Var);
            j0.b.b(drawableT, n0Var.getLayoutDirection());
            if (drawableT.isStateful()) {
                drawableT.setState(n0Var.getDrawableState());
            }
            f();
        }
        n0Var.invalidate();
        if (typedArray.hasValue(3)) {
            this.f1682h = z1.b(typedArray.getInt(3, -1), this.f1682h);
            this.f1684j = true;
        }
        if (typedArray.hasValue(2)) {
            this.f1681g = i1VarZ.S(2);
            this.f1683i = true;
        }
        i1VarZ.b0();
        f();
    }

    public final void f() {
        Drawable drawable = this.f1680f;
        if (drawable != null) {
            if (this.f1683i || this.f1684j) {
                Drawable drawableMutate = drawable.mutate();
                this.f1680f = drawableMutate;
                if (this.f1683i) {
                    j0.a.h(drawableMutate, this.f1681g);
                }
                if (this.f1684j) {
                    j0.a.i(this.f1680f, this.f1682h);
                }
                if (this.f1680f.isStateful()) {
                    this.f1680f.setState(this.f1679e.getDrawableState());
                }
            }
        }
    }

    public final void g(Canvas canvas) {
        if (this.f1680f != null) {
            int max = this.f1679e.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1680f.getIntrinsicWidth();
                int intrinsicHeight = this.f1680f.getIntrinsicHeight();
                int i10 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i11 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1680f.setBounds(-i10, -i11, i10, i11);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i12 = 0; i12 <= max; i12++) {
                    this.f1680f.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
