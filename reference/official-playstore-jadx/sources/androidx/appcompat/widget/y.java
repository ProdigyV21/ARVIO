package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ColorStateList f1835a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PorterDuff.Mode f1836b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1837c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1838d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1839e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextView f1840f;

    public /* synthetic */ y(TextView textView) {
        this.f1840f = textView;
    }

    public void a() {
        CompoundButton compoundButton = (CompoundButton) this.f1840f;
        Drawable drawableA = u0.d.a(compoundButton);
        if (drawableA != null) {
            if (this.f1837c || this.f1838d) {
                Drawable drawableMutate = drawableA.mutate();
                if (this.f1837c) {
                    j0.a.h(drawableMutate, this.f1835a);
                }
                if (this.f1838d) {
                    j0.a.i(drawableMutate, this.f1836b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public void b() {
        x xVar = (x) this.f1840f;
        Drawable checkMarkDrawable = xVar.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.f1837c || this.f1838d) {
                Drawable drawableMutate = checkMarkDrawable.mutate();
                if (this.f1837c) {
                    j0.a.h(drawableMutate, this.f1835a);
                }
                if (this.f1838d) {
                    j0.a.i(drawableMutate, this.f1836b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(xVar.getDrawableState());
                }
                xVar.setCheckMarkDrawable(drawableMutate);
            }
        }
    }

    public void c(AttributeSet attributeSet, int i10) {
        int resourceId;
        int resourceId2;
        CompoundButton compoundButton = (CompoundButton) this.f1840f;
        Context context = compoundButton.getContext();
        int[] iArr = j.a.f19028m;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, i10);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        androidx.core.view.b2.g(compoundButton, compoundButton.getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, i10);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    compoundButton.setButtonDrawable(m2.f0.o(compoundButton.getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        compoundButton.setButtonDrawable(m2.f0.o(compoundButton.getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                compoundButton.setButtonDrawable(m2.f0.o(compoundButton.getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                u0.c.c(compoundButton, i1VarZ.S(2));
            }
            if (typedArray.hasValue(3)) {
                u0.c.d(compoundButton, z1.b(typedArray.getInt(3, -1), null));
            }
            i1VarZ.b0();
        } catch (Throwable th) {
            i1VarZ.b0();
            throw th;
        }
    }
}
