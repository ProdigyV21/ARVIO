package androidx.leanback.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.b2;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public class PagingIndicator extends View {
    public static final DecelerateInterpolator G = new DecelerateInterpolator();
    public static final r0 H;
    public static final r0 I;
    public static final r0 J;
    public final Paint A;
    public final Paint B;
    public Bitmap C;
    public Paint D;
    public final Rect E;
    public final float F;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2975i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2976l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2977m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2978n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2979o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f2980p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f2981q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f2982r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public s0[] f2983s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int[] f2984t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int[] f2985u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int[] f2986v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2987w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2988y;
    public int z;

    static {
        Class<Float> cls = Float.class;
        H = new r0(cls, "alpha", 0);
        I = new r0(cls, "diameter", 1);
        J = new r0(cls, "translation_x", 2);
    }

    public PagingIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        Resources resources = getResources();
        int[] iArr = k1.a.f19452b;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(6, getResources().getDimensionPixelOffset(R.dimen.lb_page_indicator_dot_radius));
        this.f2977m = dimensionPixelOffset;
        int i10 = dimensionPixelOffset * 2;
        this.f2976l = i10;
        int dimensionPixelOffset2 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, getResources().getDimensionPixelOffset(R.dimen.lb_page_indicator_arrow_radius));
        this.f2980p = dimensionPixelOffset2;
        int i11 = dimensionPixelOffset2 * 2;
        this.f2979o = i11;
        this.f2978n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(5, getResources().getDimensionPixelOffset(R.dimen.lb_page_indicator_dot_gap));
        this.f2981q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(4, getResources().getDimensionPixelOffset(R.dimen.lb_page_indicator_arrow_gap));
        int color = typedArrayObtainStyledAttributes.getColor(3, getResources().getColor(R.color.lb_page_indicator_dot));
        Paint paint = new Paint(1);
        this.A = paint;
        paint.setColor(color);
        this.z = typedArrayObtainStyledAttributes.getColor(0, getResources().getColor(R.color.lb_page_indicator_arrow_background));
        if (this.D == null && typedArrayObtainStyledAttributes.hasValue(1)) {
            setArrowColor(typedArrayObtainStyledAttributes.getColor(1, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f2975i = resources.getConfiguration().getLayoutDirection() == 0;
        int color2 = resources.getColor(R.color.lb_page_indicator_arrow_shadow);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_radius);
        this.f2982r = dimensionPixelSize;
        Paint paint2 = new Paint(1);
        this.B = paint2;
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_offset);
        paint2.setShadowLayer(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, color2);
        this.C = d();
        this.E = new Rect(0, 0, this.C.getWidth(), this.C.getHeight());
        float f10 = i11;
        this.F = this.C.getWidth() / f10;
        AnimatorSet animatorSet2 = new AnimatorSet();
        r0 r0Var = H;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, r0Var, 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(167L);
        DecelerateInterpolator decelerateInterpolator = G;
        objectAnimatorOfFloat.setInterpolator(decelerateInterpolator);
        float f11 = i10;
        r0 r0Var2 = I;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat((Object) null, r0Var2, f11, f10);
        objectAnimatorOfFloat2.setDuration(417L);
        objectAnimatorOfFloat2.setInterpolator(decelerateInterpolator);
        animatorSet2.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, c());
        AnimatorSet animatorSet3 = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat((Object) null, r0Var, 1.0f, 0.0f);
        objectAnimatorOfFloat3.setDuration(167L);
        objectAnimatorOfFloat3.setInterpolator(decelerateInterpolator);
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat((Object) null, r0Var2, f10, f11);
        objectAnimatorOfFloat4.setDuration(417L);
        objectAnimatorOfFloat4.setInterpolator(decelerateInterpolator);
        animatorSet3.playTogether(objectAnimatorOfFloat3, objectAnimatorOfFloat4, c());
        animatorSet.playTogether(animatorSet2, animatorSet3);
        setLayerType(1, null);
    }

    private int getDesiredHeight() {
        return getPaddingBottom() + getPaddingTop() + this.f2979o + this.f2982r;
    }

    private int getDesiredWidth() {
        return getPaddingRight() + getPaddingLeft() + getRequiredWidth();
    }

    private int getRequiredWidth() {
        return ((this.x - 3) * this.f2978n) + (this.f2981q * 2) + (this.f2977m * 2);
    }

    private void setSelectedPage(int i10) {
        if (i10 == this.f2988y) {
            return;
        }
        this.f2988y = i10;
        a();
    }

    public final void a() {
        int i10;
        int i11 = 0;
        while (true) {
            i10 = this.f2988y;
            if (i11 >= i10) {
                break;
            }
            this.f2983s[i11].b();
            s0 s0Var = this.f2983s[i11];
            if (i11 != 0) {
                f = 1.0f;
            }
            s0Var.f3122h = f;
            s0Var.f3118d = this.f2985u[i11];
            i11++;
        }
        s0 s0Var2 = this.f2983s[i10];
        s0Var2.f3117c = 0.0f;
        s0Var2.f3118d = 0.0f;
        PagingIndicator pagingIndicator = s0Var2.f3124j;
        s0Var2.f3119e = pagingIndicator.f2979o;
        float f10 = pagingIndicator.f2980p;
        s0Var2.f3120f = f10;
        s0Var2.f3121g = f10 * pagingIndicator.F;
        s0Var2.f3115a = 1.0f;
        s0Var2.a();
        s0[] s0VarArr = this.f2983s;
        int i12 = this.f2988y;
        s0 s0Var3 = s0VarArr[i12];
        s0Var3.f3122h = i12 <= 0 ? 1.0f : -1.0f;
        s0Var3.f3118d = this.f2984t[i12];
        while (true) {
            i12++;
            if (i12 >= this.x) {
                return;
            }
            this.f2983s[i12].b();
            s0 s0Var4 = this.f2983s[i12];
            s0Var4.f3122h = 1.0f;
            s0Var4.f3118d = this.f2986v[i12];
        }
    }

    public final void b() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int requiredWidth = getRequiredWidth();
        int i10 = (paddingLeft + width) / 2;
        int i11 = this.x;
        int[] iArr = new int[i11];
        this.f2984t = iArr;
        int[] iArr2 = new int[i11];
        this.f2985u = iArr2;
        int[] iArr3 = new int[i11];
        this.f2986v = iArr3;
        boolean z = this.f2975i;
        int i12 = this.f2977m;
        int i13 = this.f2981q;
        int i14 = this.f2978n;
        int i15 = 1;
        if (z) {
            int i16 = i10 - (requiredWidth / 2);
            iArr[0] = ((i16 + i12) - i14) + i13;
            iArr2[0] = i16 + i12;
            iArr3[0] = (i13 * 2) + ((i16 + i12) - (i14 * 2));
            while (i15 < this.x) {
                int[] iArr4 = this.f2984t;
                int[] iArr5 = this.f2985u;
                int i17 = i15 - 1;
                iArr4[i15] = iArr5[i17] + i13;
                iArr5[i15] = iArr5[i17] + i14;
                this.f2986v[i15] = iArr4[i17] + i13;
                i15++;
            }
        } else {
            int i18 = (requiredWidth / 2) + i10;
            iArr[0] = ((i18 - i12) + i14) - i13;
            iArr2[0] = i18 - i12;
            iArr3[0] = ((i14 * 2) + (i18 - i12)) - (i13 * 2);
            while (i15 < this.x) {
                int[] iArr6 = this.f2984t;
                int[] iArr7 = this.f2985u;
                int i19 = i15 - 1;
                iArr6[i15] = iArr7[i19] - i13;
                iArr7[i15] = iArr7[i19] - i14;
                this.f2986v[i15] = iArr6[i19] - i13;
                i15++;
            }
        }
        this.f2987w = paddingTop + this.f2980p;
        a();
    }

    public final ObjectAnimator c() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, J, (-this.f2981q) + this.f2978n, 0.0f);
        objectAnimatorOfFloat.setDuration(417L);
        objectAnimatorOfFloat.setInterpolator(G);
        return objectAnimatorOfFloat;
    }

    public final Bitmap d() {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.lb_ic_nav_arrow);
        if (this.f2975i) {
            return bitmapDecodeResource;
        }
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmapDecodeResource, 0, 0, bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), matrix, false);
    }

    public int[] getDotSelectedLeftX() {
        return this.f2985u;
    }

    public int[] getDotSelectedRightX() {
        return this.f2986v;
    }

    public int[] getDotSelectedX() {
        return this.f2984t;
    }

    public int getPageCount() {
        return this.x;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        for (int i10 = 0; i10 < this.x; i10++) {
            s0 s0Var = this.f2983s[i10];
            float f10 = s0Var.f3118d + s0Var.f3117c;
            PagingIndicator pagingIndicator = s0Var.f3124j;
            int i11 = pagingIndicator.f2987w;
            Paint paint = pagingIndicator.B;
            canvas.drawCircle(f10, i11, s0Var.f3120f, pagingIndicator.A);
            if (s0Var.f3115a > 0.0f) {
                paint.setColor(s0Var.f3116b);
                canvas.drawCircle(f10, pagingIndicator.f2987w, s0Var.f3120f, paint);
                Bitmap bitmap = pagingIndicator.C;
                Rect rect = pagingIndicator.E;
                float f11 = s0Var.f3121g;
                float f12 = pagingIndicator.f2987w;
                canvas.drawBitmap(bitmap, rect, new Rect((int) (f10 - f11), (int) (f12 - f11), (int) (f10 + f11), (int) (f12 + f11)), pagingIndicator.D);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        int desiredHeight = getDesiredHeight();
        int mode = View.MeasureSpec.getMode(i11);
        if (mode == Integer.MIN_VALUE) {
            desiredHeight = Math.min(desiredHeight, View.MeasureSpec.getSize(i11));
        } else if (mode == 1073741824) {
            desiredHeight = View.MeasureSpec.getSize(i11);
        }
        int desiredWidth = getDesiredWidth();
        int mode2 = View.MeasureSpec.getMode(i10);
        if (mode2 == Integer.MIN_VALUE) {
            desiredWidth = Math.min(desiredWidth, View.MeasureSpec.getSize(i10));
        } else if (mode2 == 1073741824) {
            desiredWidth = View.MeasureSpec.getSize(i10);
        }
        setMeasuredDimension(desiredWidth, desiredHeight);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i10) {
        super.onRtlPropertiesChanged(i10);
        boolean z = i10 == 0;
        if (this.f2975i != z) {
            this.f2975i = z;
            this.C = d();
            s0[] s0VarArr = this.f2983s;
            if (s0VarArr != null) {
                for (s0 s0Var : s0VarArr) {
                    s0Var.f3123i = s0Var.f3124j.f2975i ? 1.0f : -1.0f;
                }
            }
            b();
            invalidate();
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i10, int i11, int i12, int i13) {
        setMeasuredDimension(i10, i11);
        b();
    }

    public void setArrowBackgroundColor(int i10) {
        this.z = i10;
    }

    public void setArrowColor(int i10) {
        if (this.D == null) {
            this.D = new Paint();
        }
        this.D.setColorFilter(new PorterDuffColorFilter(i10, PorterDuff.Mode.SRC_IN));
    }

    public void setDotBackgroundColor(int i10) {
        this.A.setColor(i10);
    }

    public void setPageCount(int i10) {
        if (i10 <= 0) {
            throw new IllegalArgumentException("The page count should be a positive integer");
        }
        this.x = i10;
        this.f2983s = new s0[i10];
        for (int i11 = 0; i11 < this.x; i11++) {
            this.f2983s[i11] = new s0(this);
        }
        b();
        setSelectedPage(0);
    }
}
