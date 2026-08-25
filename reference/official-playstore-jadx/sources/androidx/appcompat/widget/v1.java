package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class v1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final RectF f1785l = new RectF();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ConcurrentHashMap f1786m = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1787a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1788b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1789c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1790d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1791e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f1792f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1793g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextPaint f1794h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TextView f1795i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Context f1796j;
    public final s1 k;

    static {
        new ConcurrentHashMap();
    }

    public v1(TextView textView) {
        this.f1795i = textView;
        this.f1796j = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.k = new t1();
        } else {
            this.k = new s1();
        }
    }

    public static int[] b(int[] iArr) {
        int length = iArr.length;
        if (length != 0) {
            Arrays.sort(iArr);
            ArrayList arrayList = new ArrayList();
            for (int i10 : iArr) {
                if (i10 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i10)) < 0) {
                    arrayList.add(Integer.valueOf(i10));
                }
            }
            if (length != arrayList.size()) {
                int size = arrayList.size();
                int[] iArr2 = new int[size];
                for (int i11 = 0; i11 < size; i11++) {
                    iArr2[i11] = ((Integer) arrayList.get(i11)).intValue();
                }
                return iArr2;
            }
        }
        return iArr;
    }

    public static Method d(String str) {
        try {
            ConcurrentHashMap concurrentHashMap = f1786m;
            Method declaredMethod = (Method) concurrentHashMap.get(str);
            if (declaredMethod != null || (declaredMethod = TextView.class.getDeclaredMethod(str, null)) == null) {
                return declaredMethod;
            }
            declaredMethod.setAccessible(true);
            concurrentHashMap.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e5) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e5);
            return null;
        }
    }

    public static Object e(Object obj, String str, Object obj2) {
        try {
            return d(str).invoke(obj, null);
        } catch (Exception e5) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e5);
            return obj2;
        }
    }

    public final void a() {
        if (f()) {
            if (this.f1788b) {
                if (this.f1795i.getMeasuredHeight() <= 0 || this.f1795i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.k.b(this.f1795i) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : (this.f1795i.getMeasuredWidth() - this.f1795i.getTotalPaddingLeft()) - this.f1795i.getTotalPaddingRight();
                int height = (this.f1795i.getHeight() - this.f1795i.getCompoundPaddingBottom()) - this.f1795i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1785l;
                synchronized (rectF) {
                    try {
                        rectF.setEmpty();
                        rectF.right = measuredWidth;
                        rectF.bottom = height;
                        float fC = c(rectF);
                        if (fC != this.f1795i.getTextSize()) {
                            g(fC, 0);
                        }
                    } finally {
                    }
                }
            }
            this.f1788b = true;
        }
    }

    public final int c(RectF rectF) {
        CharSequence transformation;
        int length = this.f1792f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i10 = length - 1;
        int i11 = 0;
        int i12 = 1;
        while (i12 <= i10) {
            int i13 = (i12 + i10) / 2;
            int i14 = this.f1792f[i13];
            TextView textView = this.f1795i;
            CharSequence text = textView.getText();
            TransformationMethod transformationMethod = textView.getTransformationMethod();
            CharSequence charSequence = (transformationMethod == null || (transformation = transformationMethod.getTransformation(text, textView)) == null) ? text : transformation;
            int iB = p1.b(textView);
            TextPaint textPaint = this.f1794h;
            if (textPaint == null) {
                this.f1794h = new TextPaint();
            } else {
                textPaint.reset();
            }
            this.f1794h.set(textView.getPaint());
            this.f1794h.setTextSize(i14);
            StaticLayout staticLayoutA = r1.a(charSequence, (Layout.Alignment) e(textView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), iB, this.f1795i, this.f1794h, this.k);
            if ((iB == -1 || (staticLayoutA.getLineCount() <= iB && staticLayoutA.getLineEnd(staticLayoutA.getLineCount() - 1) == charSequence.length())) && staticLayoutA.getHeight() <= rectF.bottom) {
                int i15 = i13 + 1;
                i11 = i12;
                i12 = i15;
            } else {
                i11 = i13 - 1;
                i10 = i11;
            }
        }
        return this.f1792f[i11];
    }

    public final boolean f() {
        return j() && this.f1787a != 0;
    }

    public final void g(float f10, int i10) {
        Context context = this.f1796j;
        float fApplyDimension = TypedValue.applyDimension(i10, f10, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        TextView textView = this.f1795i;
        if (fApplyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(fApplyDimension);
            boolean zA = q1.a(textView);
            if (textView.getLayout() != null) {
                this.f1788b = false;
                try {
                    Method methodD = d("nullLayouts");
                    if (methodD != null) {
                        methodD.invoke(textView, null);
                    }
                } catch (Exception e5) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e5);
                }
                if (zA) {
                    textView.forceLayout();
                } else {
                    textView.requestLayout();
                }
                textView.invalidate();
            }
        }
    }

    public final boolean h() {
        if (j() && this.f1787a == 1) {
            if (!this.f1793g || this.f1792f.length == 0) {
                int iFloor = ((int) Math.floor((this.f1791e - this.f1790d) / this.f1789c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i10 = 0; i10 < iFloor; i10++) {
                    iArr[i10] = Math.round((i10 * this.f1789c) + this.f1790d);
                }
                this.f1792f = b(iArr);
            }
            this.f1788b = true;
        } else {
            this.f1788b = false;
        }
        return this.f1788b;
    }

    public final boolean i() {
        boolean z = this.f1792f.length > 0;
        this.f1793g = z;
        if (z) {
            this.f1787a = 1;
            this.f1790d = r0[0];
            this.f1791e = r0[r1 - 1];
            this.f1789c = -1.0f;
        }
        return z;
    }

    public final boolean j() {
        return !(this.f1795i instanceof c0);
    }

    public final void k(float f10, float f11, float f12) {
        if (f10 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f10 + "px) is less or equal to (0px)");
        }
        if (f11 <= f10) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f11 + "px) is less or equal to minimum auto-size text size (" + f10 + "px)");
        }
        if (f12 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f12 + "px) is less or equal to (0px)");
        }
        this.f1787a = 1;
        this.f1790d = f10;
        this.f1791e = f11;
        this.f1789c = f12;
        this.f1793g = false;
    }
}
