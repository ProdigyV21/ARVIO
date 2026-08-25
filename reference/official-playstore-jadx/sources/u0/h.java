package u0;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.g2;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements View.OnTouchListener {
    public static final int B = ViewConfiguration.getTapTimeout();
    public final g2 A;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f22021i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AccelerateInterpolator f22022l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g2 f22023m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.leanback.widget.u f22024n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final float[] f22025o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float[] f22026p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f22027q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f22028r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float[] f22029s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final float[] f22030t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final float[] f22031u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f22032v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f22033w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f22034y;
    public boolean z;

    public h(g2 g2Var) {
        a aVar = new a();
        aVar.f22016e = Long.MIN_VALUE;
        aVar.f22018g = -1L;
        aVar.f22017f = 0L;
        this.f22021i = aVar;
        this.f22022l = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.f22025o = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f22026p = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.f22029s = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.f22030t = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f22031u = fArr5;
        this.f22023m = g2Var;
        float f10 = Resources.getSystem().getDisplayMetrics().density;
        float f11 = ((int) ((1575.0f * f10) + 0.5f)) / 1000.0f;
        fArr5[0] = f11;
        fArr5[1] = f11;
        float f12 = ((int) ((f10 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f12;
        fArr4[1] = f12;
        this.f22027q = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.f22028r = B;
        aVar.f22012a = 500;
        aVar.f22013b = 500;
        this.A = g2Var;
    }

    public static float b(float f10, float f11, float f12) {
        return f10 > f12 ? f12 : f10 < f11 ? f11 : f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.f22025o
            r0 = r0[r4]
            float[] r1 = r3.f22026p
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.c(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.f22022l
            if (r6 >= 0) goto L25
            float r5 = -r5
            float r5 = r0.getInterpolation(r5)
            float r5 = -r5
            goto L2d
        L25:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L36
            float r5 = r0.getInterpolation(r5)
        L2d:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = b(r5, r6, r0)
            goto L37
        L36:
            r5 = r2
        L37:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L3c
            return r2
        L3c:
            float[] r0 = r3.f22029s
            r0 = r0[r4]
            float[] r1 = r3.f22030t
            r1 = r1[r4]
            float[] r2 = r3.f22031u
            r4 = r2[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L51
            float r5 = r5 * r0
            float r4 = b(r5, r1, r4)
            return r4
        L51:
            float r5 = -r5
            float r5 = r5 * r0
            float r4 = b(r5, r1, r4)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.h.a(int, float, float, float):float");
    }

    public final float c(float f10, float f11) {
        if (f11 != 0.0f) {
            int i10 = this.f22027q;
            if (i10 == 0 || i10 == 1) {
                if (f10 < f11) {
                    if (f10 >= 0.0f) {
                        return 1.0f - (f10 / f11);
                    }
                    if (this.f22034y && i10 == 1) {
                        return 1.0f;
                    }
                }
            } else if (i10 == 2 && f10 < 0.0f) {
                return f10 / (-f11);
            }
        }
        return 0.0f;
    }

    public final void d() {
        int i10 = 0;
        if (this.f22033w) {
            this.f22034y = false;
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        a aVar = this.f22021i;
        int i11 = (int) (jCurrentAnimationTimeMillis - aVar.f22016e);
        int i12 = aVar.f22013b;
        if (i11 > i12) {
            i10 = i12;
        } else if (i11 >= 0) {
            i10 = i11;
        }
        aVar.f22020i = i10;
        aVar.f22019h = aVar.a(jCurrentAnimationTimeMillis);
        aVar.f22018g = jCurrentAnimationTimeMillis;
    }

    public final boolean e() {
        g2 g2Var;
        int count;
        a aVar = this.f22021i;
        float f10 = aVar.f22015d;
        int iAbs = (int) (f10 / Math.abs(f10));
        Math.abs(aVar.f22014c);
        if (iAbs != 0 && (count = (g2Var = this.A).getCount()) != 0) {
            int childCount = g2Var.getChildCount();
            int firstVisiblePosition = g2Var.getFirstVisiblePosition();
            int i10 = firstVisiblePosition + childCount;
            if (iAbs <= 0 ? !(iAbs >= 0 || (firstVisiblePosition <= 0 && g2Var.getChildAt(0).getTop() >= 0)) : !(i10 >= count && g2Var.getChildAt(childCount - 1).getBottom() <= g2Var.getHeight())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0014, code lost:
    
        if (r0 != 3) goto L30;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r0 = r7.z
            r1 = 0
            if (r0 != 0) goto L7
            goto L7e
        L7:
            int r0 = r9.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1b
            if (r0 == r2) goto L17
            r3 = 2
            if (r0 == r3) goto L1f
            r8 = 3
            if (r0 == r8) goto L17
            goto L7e
        L17:
            r7.d()
            return r1
        L1b:
            r7.x = r2
            r7.f22032v = r1
        L1f:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            androidx.appcompat.widget.g2 r4 = r7.f22023m
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r1, r0, r3, r5)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r2, r9, r8, r3)
            u0.a r9 = r7.f22021i
            r9.f22014c = r0
            r9.f22015d = r8
            boolean r8 = r7.f22034y
            if (r8 != 0) goto L7e
            boolean r8 = r7.e()
            if (r8 == 0) goto L7e
            androidx.leanback.widget.u r8 = r7.f22024n
            if (r8 != 0) goto L62
            androidx.leanback.widget.u r8 = new androidx.leanback.widget.u
            r9 = 17
            r8.<init>(r7, r9)
            r7.f22024n = r8
        L62:
            r7.f22034y = r2
            r7.f22033w = r2
            boolean r8 = r7.f22032v
            if (r8 != 0) goto L77
            int r8 = r7.f22028r
            if (r8 <= 0) goto L77
            androidx.leanback.widget.u r9 = r7.f22024n
            long r5 = (long) r8
            java.util.WeakHashMap r8 = androidx.core.view.b2.f2200a
            r4.postOnAnimationDelayed(r9, r5)
            goto L7c
        L77:
            androidx.leanback.widget.u r8 = r7.f22024n
            r8.run()
        L7c:
            r7.f22032v = r2
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.h.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
