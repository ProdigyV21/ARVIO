package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.b2;

/* JADX INFO: loaded from: classes3.dex */
public class HorizontalGridView extends h {

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public boolean f2951d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public boolean f2952e1;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public final Paint f2953f1;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public Bitmap f2954g1;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public LinearGradient f2955h1;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public int f2956i1;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public int f2957j1;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public Bitmap f2958k1;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public LinearGradient f2959l1;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public int f2960m1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public int f2961n1;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public final Rect f2962o1;

    public HorizontalGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2953f1 = new Paint();
        this.f2962o1 = new Rect();
        this.S0.p1(0);
        i0(context, attributeSet);
        int[] iArr = k1.a.f19455e;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0);
        setRowHeight(typedArrayObtainStyledAttributes);
        setNumRows(typedArrayObtainStyledAttributes.getInt(0, 1));
        typedArrayObtainStyledAttributes.recycle();
        j0();
        Paint paint = new Paint();
        this.f2953f1 = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    private Bitmap getTempBitmapHigh() {
        Bitmap bitmap = this.f2958k1;
        if (bitmap == null || bitmap.getWidth() != this.f2960m1 || this.f2958k1.getHeight() != getHeight()) {
            this.f2958k1 = Bitmap.createBitmap(this.f2960m1, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.f2958k1;
    }

    private Bitmap getTempBitmapLow() {
        Bitmap bitmap = this.f2954g1;
        if (bitmap == null || bitmap.getWidth() != this.f2956i1 || this.f2954g1.getHeight() != getHeight()) {
            this.f2954g1 = Bitmap.createBitmap(this.f2956i1, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.f2954g1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        boolean z5 = this.f2951d1;
        a0 a0Var = this.S0;
        boolean z10 = true;
        if (z5) {
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                a0Var.getClass();
                x xVar = (x) childAt.getLayoutParams();
                xVar.getClass();
                if (childAt.getLeft() + xVar.f3170e < getPaddingLeft() - this.f2957j1) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (this.f2952e1) {
            for (int childCount2 = getChildCount() - 1; childCount2 >= 0; childCount2--) {
                View childAt2 = getChildAt(childCount2);
                a0Var.getClass();
                x xVar2 = (x) childAt2.getLayoutParams();
                xVar2.getClass();
                if (childAt2.getRight() - xVar2.f3172g > (getWidth() - getPaddingRight()) + this.f2961n1) {
                    break;
                }
            }
            z10 = false;
        } else {
            z10 = false;
        }
        if (!z) {
            this.f2954g1 = null;
        }
        if (!z10) {
            this.f2958k1 = null;
        }
        if (!z && !z10) {
            super.draw(canvas);
            return;
        }
        int paddingLeft = this.f2951d1 ? (getPaddingLeft() - this.f2957j1) - this.f2956i1 : 0;
        int width = this.f2952e1 ? (getWidth() - getPaddingRight()) + this.f2961n1 + this.f2960m1 : getWidth();
        int iSave = canvas.save();
        canvas.clipRect((this.f2951d1 ? this.f2956i1 : 0) + paddingLeft, 0, width - (this.f2952e1 ? this.f2960m1 : 0), getHeight());
        super.draw(canvas);
        canvas.restoreToCount(iSave);
        Canvas canvas2 = new Canvas();
        Rect rect = this.f2962o1;
        rect.top = 0;
        rect.bottom = getHeight();
        if (z && this.f2956i1 > 0) {
            Bitmap tempBitmapLow = getTempBitmapLow();
            tempBitmapLow.eraseColor(0);
            canvas2.setBitmap(tempBitmapLow);
            int iSave2 = canvas2.save();
            canvas2.clipRect(0, 0, this.f2956i1, getHeight());
            float f10 = -paddingLeft;
            canvas2.translate(f10, 0.0f);
            super.draw(canvas2);
            canvas2.restoreToCount(iSave2);
            this.f2953f1.setShader(this.f2955h1);
            canvas2.drawRect(0.0f, 0.0f, this.f2956i1, getHeight(), this.f2953f1);
            rect.left = 0;
            rect.right = this.f2956i1;
            canvas.translate(paddingLeft, 0.0f);
            canvas.drawBitmap(tempBitmapLow, rect, rect, (Paint) null);
            canvas.translate(f10, 0.0f);
        }
        if (!z10 || this.f2960m1 <= 0) {
            return;
        }
        Bitmap tempBitmapHigh = getTempBitmapHigh();
        tempBitmapHigh.eraseColor(0);
        canvas2.setBitmap(tempBitmapHigh);
        int iSave3 = canvas2.save();
        canvas2.clipRect(0, 0, this.f2960m1, getHeight());
        canvas2.translate(-(width - this.f2960m1), 0.0f);
        super.draw(canvas2);
        canvas2.restoreToCount(iSave3);
        this.f2953f1.setShader(this.f2959l1);
        canvas2.drawRect(0.0f, 0.0f, this.f2960m1, getHeight(), this.f2953f1);
        rect.left = 0;
        rect.right = this.f2960m1;
        canvas.translate(width - r4, 0.0f);
        canvas.drawBitmap(tempBitmapHigh, rect, rect, (Paint) null);
        canvas.translate(-(width - this.f2960m1), 0.0f);
    }

    public final boolean getFadingLeftEdge() {
        return this.f2951d1;
    }

    public final int getFadingLeftEdgeLength() {
        return this.f2956i1;
    }

    public final int getFadingLeftEdgeOffset() {
        return this.f2957j1;
    }

    public final boolean getFadingRightEdge() {
        return this.f2952e1;
    }

    public final int getFadingRightEdgeLength() {
        return this.f2960m1;
    }

    public final int getFadingRightEdgeOffset() {
        return this.f2961n1;
    }

    public final void j0() {
        if (this.f2951d1 || this.f2952e1) {
            setLayerType(2, null);
            setWillNotDraw(false);
        } else {
            setLayerType(0, null);
            setWillNotDraw(true);
        }
    }

    public final void setFadingLeftEdge(boolean z) {
        if (this.f2951d1 != z) {
            this.f2951d1 = z;
            if (!z) {
                this.f2954g1 = null;
            }
            invalidate();
            j0();
        }
    }

    public final void setFadingLeftEdgeLength(int i10) {
        if (this.f2956i1 != i10) {
            this.f2956i1 = i10;
            if (i10 != 0) {
                this.f2955h1 = new LinearGradient(0.0f, 0.0f, this.f2956i1, 0.0f, 0, -16777216, Shader.TileMode.CLAMP);
            } else {
                this.f2955h1 = null;
            }
            invalidate();
        }
    }

    public final void setFadingLeftEdgeOffset(int i10) {
        if (this.f2957j1 != i10) {
            this.f2957j1 = i10;
            invalidate();
        }
    }

    public final void setFadingRightEdge(boolean z) {
        if (this.f2952e1 != z) {
            this.f2952e1 = z;
            if (!z) {
                this.f2958k1 = null;
            }
            invalidate();
            j0();
        }
    }

    public final void setFadingRightEdgeLength(int i10) {
        if (this.f2960m1 != i10) {
            this.f2960m1 = i10;
            if (i10 != 0) {
                this.f2959l1 = new LinearGradient(0.0f, 0.0f, this.f2960m1, 0.0f, -16777216, 0, Shader.TileMode.CLAMP);
            } else {
                this.f2959l1 = null;
            }
            invalidate();
        }
    }

    public final void setFadingRightEdgeOffset(int i10) {
        if (this.f2961n1 != i10) {
            this.f2961n1 = i10;
            invalidate();
        }
    }

    public void setNumRows(int i10) {
        a0 a0Var = this.S0;
        if (i10 < 0) {
            a0Var.getClass();
            throw new IllegalArgumentException();
        }
        a0Var.V = i10;
        requestLayout();
    }

    public void setRowHeight(TypedArray typedArray) {
        if (typedArray.peekValue(1) != null) {
            setRowHeight(typedArray.getLayoutDimension(1, 0));
        }
    }

    public void setRowHeight(int i10) {
        this.S0.q1(i10);
        requestLayout();
    }
}
