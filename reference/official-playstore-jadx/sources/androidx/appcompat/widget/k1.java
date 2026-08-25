package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g4 f1626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g4 f1627c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g4 f1628d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g4 f1629e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g4 f1630f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g4 f1631g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g4 f1632h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v1 f1633i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1634j = 0;
    public int k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Typeface f1635l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1636m;

    public k1(TextView textView) {
        this.f1625a = textView;
        this.f1633i = new v1(textView);
    }

    public static g4 c(Context context, a0 a0Var, int i10) {
        ColorStateList colorStateListH;
        synchronized (a0Var) {
            colorStateListH = a0Var.f1504a.h(context, i10);
        }
        if (colorStateListH == null) {
            return null;
        }
        g4 g4Var = new g4();
        g4Var.f1590b = true;
        g4Var.f1591c = colorStateListH;
        return g4Var;
    }

    public final void a(Drawable drawable, g4 g4Var) {
        if (drawable == null || g4Var == null) {
            return;
        }
        a0.d(drawable, g4Var, this.f1625a.getDrawableState());
    }

    public final void b() {
        g4 g4Var = this.f1626b;
        TextView textView = this.f1625a;
        if (g4Var != null || this.f1627c != null || this.f1628d != null || this.f1629e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f1626b);
            a(compoundDrawables[1], this.f1627c);
            a(compoundDrawables[2], this.f1628d);
            a(compoundDrawables[3], this.f1629e);
        }
        if (this.f1630f == null && this.f1631g == null) {
            return;
        }
        Drawable[] drawableArrA = f1.a(textView);
        a(drawableArrA[0], this.f1630f);
        a(drawableArrA[2], this.f1631g);
    }

    public final ColorStateList d() {
        g4 g4Var = this.f1632h;
        if (g4Var != null) {
            return (ColorStateList) g4Var.f1591c;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        g4 g4Var = this.f1632h;
        if (g4Var != null) {
            return (PorterDuff.Mode) g4Var.f1592d;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f(AttributeSet attributeSet, int i10) {
        boolean z;
        boolean z5;
        String string;
        String string2;
        ColorStateList colorStateList;
        int resourceId;
        int i11;
        int resourceId2;
        TextView textView = this.f1625a;
        Context context = textView.getContext();
        a0 a0VarA = a0.a();
        int[] iArr = j.a.f19024h;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, i10);
        androidx.core.view.b2.g(textView, textView.getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, i10);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        int resourceId3 = typedArray.getResourceId(0, -1);
        if (typedArray.hasValue(3)) {
            this.f1626b = c(context, a0VarA, typedArray.getResourceId(3, 0));
        }
        if (typedArray.hasValue(1)) {
            this.f1627c = c(context, a0VarA, typedArray.getResourceId(1, 0));
        }
        if (typedArray.hasValue(4)) {
            this.f1628d = c(context, a0VarA, typedArray.getResourceId(4, 0));
        }
        if (typedArray.hasValue(2)) {
            this.f1629e = c(context, a0VarA, typedArray.getResourceId(2, 0));
        }
        int i12 = Build.VERSION.SDK_INT;
        if (typedArray.hasValue(5)) {
            this.f1630f = c(context, a0VarA, typedArray.getResourceId(5, 0));
        }
        if (typedArray.hasValue(6)) {
            this.f1631g = c(context, a0VarA, typedArray.getResourceId(6, 0));
        }
        i1VarZ.b0();
        boolean z10 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = j.a.f19038w;
        if (resourceId3 != -1) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(resourceId3, iArr2);
            androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(context, typedArrayObtainStyledAttributes);
            if (z10 || !typedArrayObtainStyledAttributes.hasValue(14)) {
                z = false;
                z5 = false;
            } else {
                z5 = typedArrayObtainStyledAttributes.getBoolean(14, false);
                z = true;
            }
            m(context, i1Var);
            string2 = typedArrayObtainStyledAttributes.hasValue(15) ? typedArrayObtainStyledAttributes.getString(15) : null;
            string = (i12 < 26 || !typedArrayObtainStyledAttributes.hasValue(13)) ? null : typedArrayObtainStyledAttributes.getString(13);
            i1Var.b0();
        } else {
            z = false;
            z5 = false;
            string = null;
            string2 = null;
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i10, 0);
        androidx.appcompat.app.i1 i1Var2 = new androidx.appcompat.app.i1(context, typedArrayObtainStyledAttributes2);
        if (!z10 && typedArrayObtainStyledAttributes2.hasValue(14)) {
            z5 = typedArrayObtainStyledAttributes2.getBoolean(14, false);
            z = true;
        }
        boolean z11 = z5;
        if (typedArrayObtainStyledAttributes2.hasValue(15)) {
            string2 = typedArrayObtainStyledAttributes2.getString(15);
        }
        String str = string2;
        if (i12 >= 26 && typedArrayObtainStyledAttributes2.hasValue(13)) {
            string = typedArrayObtainStyledAttributes2.getString(13);
        }
        if (i12 >= 28 && typedArrayObtainStyledAttributes2.hasValue(0) && typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        m(context, i1Var2);
        i1Var2.b0();
        if (!z10 && z) {
            textView.setAllCaps(z11);
        }
        Typeface typeface = this.f1635l;
        if (typeface != null) {
            if (this.k == -1) {
                textView.setTypeface(typeface, this.f1634j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (string != null) {
            i1.d(textView, string);
        }
        if (str != null) {
            if (i12 >= 24) {
                h1.b(textView, h1.a(str));
            } else {
                f1.c(textView, g1.a(str.split(",")[0]));
            }
        }
        v1 v1Var = this.f1633i;
        Context context2 = v1Var.f1796j;
        int[] iArr3 = j.a.f19025i;
        TypedArray typedArrayObtainStyledAttributes3 = context2.obtainStyledAttributes(attributeSet, iArr3, i10, 0);
        TextView textView2 = v1Var.f1795i;
        androidx.core.view.b2.g(textView2, textView2.getContext(), iArr3, attributeSet, typedArrayObtainStyledAttributes3, i10);
        if (typedArrayObtainStyledAttributes3.hasValue(5)) {
            v1Var.f1787a = typedArrayObtainStyledAttributes3.getInt(5, 0);
        }
        float dimension = typedArrayObtainStyledAttributes3.hasValue(4) ? typedArrayObtainStyledAttributes3.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes3.hasValue(2) ? typedArrayObtainStyledAttributes3.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes3.hasValue(1) ? typedArrayObtainStyledAttributes3.getDimension(1, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes3.hasValue(3) && (resourceId2 = typedArrayObtainStyledAttributes3.getResourceId(3, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes3.getResources().obtainTypedArray(resourceId2);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i13 = 0; i13 < length; i13++) {
                    iArr4[i13] = typedArrayObtainTypedArray.getDimensionPixelSize(i13, -1);
                }
                v1Var.f1792f = v1.b(iArr4);
                v1Var.i();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes3.recycle();
        if (!v1Var.j()) {
            v1Var.f1787a = 0;
        } else if (v1Var.f1787a == 1) {
            if (!v1Var.f1793g) {
                DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i11 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i11 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i11, 112.0f, displayMetrics);
                }
                float f10 = dimension3;
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                v1Var.k(dimension2, f10, dimension);
            }
            v1Var.h();
        }
        if (w4.f1827b && v1Var.f1787a != 0) {
            int[] iArr5 = v1Var.f1792f;
            if (iArr5.length > 0) {
                if (i1.a(textView) != -1.0f) {
                    i1.b(textView, Math.round(v1Var.f1790d), Math.round(v1Var.f1791e), Math.round(v1Var.f1789c), 0);
                } else {
                    i1.c(textView, iArr5, 0);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, iArr3);
        int resourceId4 = typedArrayObtainStyledAttributes4.getResourceId(8, -1);
        Drawable drawableB = resourceId4 != -1 ? a0VarA.b(context, resourceId4) : null;
        int resourceId5 = typedArrayObtainStyledAttributes4.getResourceId(13, -1);
        Drawable drawableB2 = resourceId5 != -1 ? a0VarA.b(context, resourceId5) : null;
        int resourceId6 = typedArrayObtainStyledAttributes4.getResourceId(9, -1);
        Drawable drawableB3 = resourceId6 != -1 ? a0VarA.b(context, resourceId6) : null;
        int resourceId7 = typedArrayObtainStyledAttributes4.getResourceId(6, -1);
        Drawable drawableB4 = resourceId7 != -1 ? a0VarA.b(context, resourceId7) : null;
        int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(10, -1);
        Drawable drawableB5 = resourceId8 != -1 ? a0VarA.b(context, resourceId8) : null;
        int resourceId9 = typedArrayObtainStyledAttributes4.getResourceId(7, -1);
        Drawable drawableB6 = resourceId9 != -1 ? a0VarA.b(context, resourceId9) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] drawableArrA = f1.a(textView);
            if (drawableB5 == null) {
                drawableB5 = drawableArrA[0];
            }
            if (drawableB2 == null) {
                drawableB2 = drawableArrA[1];
            }
            if (drawableB6 == null) {
                drawableB6 = drawableArrA[2];
            }
            if (drawableB4 == null) {
                drawableB4 = drawableArrA[3];
            }
            f1.b(textView, drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] drawableArrA2 = f1.a(textView);
            Drawable drawable = drawableArrA2[0];
            if (drawable == null && drawableArrA2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (drawableB == null) {
                    drawableB = compoundDrawables[0];
                }
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawables[1];
                }
                if (drawableB3 == null) {
                    drawableB3 = compoundDrawables[2];
                }
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                if (drawableB2 == null) {
                    drawableB2 = drawableArrA2[1];
                }
                Drawable drawable2 = drawableArrA2[2];
                if (drawableB4 == null) {
                    drawableB4 = drawableArrA2[3];
                }
                f1.b(textView, drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (typedArrayObtainStyledAttributes4.hasValue(11)) {
            if (!typedArrayObtainStyledAttributes4.hasValue(11) || (resourceId = typedArrayObtainStyledAttributes4.getResourceId(11, 0)) == 0 || (colorStateList = qb.d.C(context, resourceId)) == null) {
                colorStateList = typedArrayObtainStyledAttributes4.getColorStateList(11);
            }
            if (Build.VERSION.SDK_INT >= 24) {
                u0.m.f(textView, colorStateList);
            } else if (textView instanceof u0.u) {
                ((u0.u) textView).setSupportCompoundDrawablesTintList(colorStateList);
            }
        }
        if (typedArrayObtainStyledAttributes4.hasValue(12)) {
            PorterDuff.Mode modeB = z1.b(typedArrayObtainStyledAttributes4.getInt(12, -1), null);
            if (Build.VERSION.SDK_INT >= 24) {
                u0.m.g(textView, modeB);
            } else if (textView instanceof u0.u) {
                ((u0.u) textView).setSupportCompoundDrawablesTintMode(modeB);
            }
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes4.getDimensionPixelSize(15, -1);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(18, -1);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(19, -1);
        typedArrayObtainStyledAttributes4.recycle();
        if (dimensionPixelSize != -1) {
            m2.f0.M(textView, dimensionPixelSize);
        }
        if (dimensionPixelSize2 != -1) {
            m2.f0.N(textView, dimensionPixelSize2);
        }
        if (dimensionPixelSize3 != -1) {
            if (dimensionPixelSize3 < 0) {
                throw new IllegalArgumentException();
            }
            if (dimensionPixelSize3 != textView.getPaint().getFontMetricsInt(null)) {
                textView.setLineSpacing(dimensionPixelSize3 - r1, 1.0f);
            }
        }
    }

    public final void g(Context context, int i10) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i10, j.a.f19038w);
        androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(context, typedArrayObtainStyledAttributes);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(14);
        TextView textView = this.f1625a;
        if (zHasValue) {
            textView.setAllCaps(typedArrayObtainStyledAttributes.getBoolean(14, false));
        }
        int i11 = Build.VERSION.SDK_INT;
        if (typedArrayObtainStyledAttributes.hasValue(0) && typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        m(context, i1Var);
        if (i11 >= 26 && typedArrayObtainStyledAttributes.hasValue(13) && (string = typedArrayObtainStyledAttributes.getString(13)) != null) {
            i1.d(textView, string);
        }
        i1Var.b0();
        Typeface typeface = this.f1635l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1634j);
        }
    }

    public final void h(int i10, int i11, int i12, int i13) {
        v1 v1Var = this.f1633i;
        if (v1Var.j()) {
            DisplayMetrics displayMetrics = v1Var.f1796j.getResources().getDisplayMetrics();
            v1Var.k(TypedValue.applyDimension(i13, i10, displayMetrics), TypedValue.applyDimension(i13, i11, displayMetrics), TypedValue.applyDimension(i13, i12, displayMetrics));
            if (v1Var.h()) {
                v1Var.a();
            }
        }
    }

    public final void i(int[] iArr, int i10) {
        v1 v1Var = this.f1633i;
        if (v1Var.j()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i10 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = v1Var.f1796j.getResources().getDisplayMetrics();
                    for (int i11 = 0; i11 < length; i11++) {
                        iArrCopyOf[i11] = Math.round(TypedValue.applyDimension(i10, iArr[i11], displayMetrics));
                    }
                }
                v1Var.f1792f = v1.b(iArrCopyOf);
                if (!v1Var.i()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                v1Var.f1793g = false;
            }
            if (v1Var.h()) {
                v1Var.a();
            }
        }
    }

    public final void j(int i10) {
        v1 v1Var = this.f1633i;
        if (v1Var.j()) {
            if (i10 == 0) {
                v1Var.f1787a = 0;
                v1Var.f1790d = -1.0f;
                v1Var.f1791e = -1.0f;
                v1Var.f1789c = -1.0f;
                v1Var.f1792f = new int[0];
                v1Var.f1788b = false;
                return;
            }
            if (i10 != 1) {
                throw new IllegalArgumentException(a0.c.i(i10, "Unknown auto-size text type: "));
            }
            DisplayMetrics displayMetrics = v1Var.f1796j.getResources().getDisplayMetrics();
            v1Var.k(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (v1Var.h()) {
                v1Var.a();
            }
        }
    }

    public final void k(ColorStateList colorStateList) {
        if (this.f1632h == null) {
            this.f1632h = new g4();
        }
        g4 g4Var = this.f1632h;
        g4Var.f1591c = colorStateList;
        g4Var.f1590b = colorStateList != null;
        this.f1626b = g4Var;
        this.f1627c = g4Var;
        this.f1628d = g4Var;
        this.f1629e = g4Var;
        this.f1630f = g4Var;
        this.f1631g = g4Var;
    }

    public final void l(PorterDuff.Mode mode) {
        if (this.f1632h == null) {
            this.f1632h = new g4();
        }
        g4 g4Var = this.f1632h;
        g4Var.f1592d = mode;
        g4Var.f1589a = mode != null;
        this.f1626b = g4Var;
        this.f1627c = g4Var;
        this.f1628d = g4Var;
        this.f1629e = g4Var;
        this.f1630f = g4Var;
        this.f1631g = g4Var;
    }

    public final void m(Context context, androidx.appcompat.app.i1 i1Var) {
        String string;
        int i10 = this.f1634j;
        TypedArray typedArray = (TypedArray) i1Var.f1060m;
        this.f1634j = typedArray.getInt(2, i10);
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 28) {
            int i12 = typedArray.getInt(11, -1);
            this.k = i12;
            if (i12 != -1) {
                this.f1634j &= 2;
            }
        }
        if (!typedArray.hasValue(10) && !typedArray.hasValue(12)) {
            if (typedArray.hasValue(1)) {
                this.f1636m = false;
                int i13 = typedArray.getInt(1, 1);
                if (i13 == 1) {
                    this.f1635l = Typeface.SANS_SERIF;
                    return;
                } else if (i13 == 2) {
                    this.f1635l = Typeface.SERIF;
                    return;
                } else {
                    if (i13 != 3) {
                        return;
                    }
                    this.f1635l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f1635l = null;
        int i14 = typedArray.hasValue(12) ? 12 : 10;
        int i15 = this.k;
        int i16 = this.f1634j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceV = i1Var.V(i14, this.f1634j, new e1(this, i15, i16, new WeakReference(this.f1625a)));
                if (typefaceV != null) {
                    if (i11 < 28 || this.k == -1) {
                        this.f1635l = typefaceV;
                    } else {
                        this.f1635l = j1.a(Typeface.create(typefaceV, 0), this.k, (this.f1634j & 2) != 0);
                    }
                }
                this.f1636m = this.f1635l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1635l != null || (string = typedArray.getString(i14)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.f1635l = Typeface.create(string, this.f1634j);
        } else {
            this.f1635l = j1.a(Typeface.create(string, 0), this.k, (this.f1634j & 2) != 0);
        }
    }
}
