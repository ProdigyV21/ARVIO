package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.core.view.b2;

/* JADX INFO: loaded from: classes3.dex */
public class VerticalGridView extends h {
    public VerticalGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.S0.p1(1);
        i0(context, attributeSet);
        int[] iArr = k1.a.f19460j;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0);
        setColumnWidth(typedArrayObtainStyledAttributes);
        setNumColumns(typedArrayObtainStyledAttributes.getInt(1, 1));
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setColumnWidth(TypedArray typedArray) {
        if (typedArray.peekValue(0) != null) {
            setColumnWidth(typedArray.getLayoutDimension(0, 0));
        }
    }

    public void setNumColumns(int i10) {
        a0 a0Var = this.S0;
        if (i10 < 0) {
            a0Var.getClass();
            throw new IllegalArgumentException();
        }
        a0Var.V = i10;
        requestLayout();
    }

    public void setColumnWidth(int i10) {
        this.S0.q1(i10);
        requestLayout();
    }
}
