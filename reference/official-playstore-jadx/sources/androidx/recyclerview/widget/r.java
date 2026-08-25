package androidx.recyclerview.widget;

import android.R;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends v0 implements RecyclerView.c {
    public static final int[] C = {R.attr.state_pressed};
    public static final int[] D = new int[0];
    public int A;
    public final n B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StateListDrawable f5017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Drawable f5018d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5020f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final StateListDrawable f5021g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Drawable f5022h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5023i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f5024j;
    public int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5025l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5027n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5028o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5029p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final RecyclerView f5032s;
    public final ValueAnimator z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5030q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f5031r = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f5033t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5034u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f5035v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f5036w = 0;
    public final int[] x = new int[2];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int[] f5037y = new int[2];

    public r(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i10, int i11, int i12) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.z = valueAnimatorOfFloat;
        this.A = 0;
        n nVar = new n(this, 0);
        this.B = nVar;
        o oVar = new o(this);
        this.f5017c = stateListDrawable;
        this.f5018d = drawable;
        this.f5021g = stateListDrawable2;
        this.f5022h = drawable2;
        this.f5019e = Math.max(i10, stateListDrawable.getIntrinsicWidth());
        this.f5020f = Math.max(i10, drawable.getIntrinsicWidth());
        this.f5023i = Math.max(i10, stateListDrawable2.getIntrinsicWidth());
        this.f5024j = Math.max(i10, drawable2.getIntrinsicWidth());
        this.f5015a = i11;
        this.f5016b = i12;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new p(this));
        valueAnimatorOfFloat.addUpdateListener(new q(this));
        RecyclerView recyclerView2 = this.f5032s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            ArrayList arrayList = recyclerView2.A;
            z0 z0Var = recyclerView2.x;
            if (z0Var != null) {
                z0Var.b("Cannot remove item decoration during a scroll  or layout");
            }
            arrayList.remove(this);
            if (arrayList.isEmpty()) {
                recyclerView2.setWillNotDraw(recyclerView2.getOverScrollMode() == 2);
            }
            recyclerView2.M();
            recyclerView2.requestLayout();
            RecyclerView recyclerView3 = this.f5032s;
            recyclerView3.B.remove(this);
            if (recyclerView3.C == this) {
                recyclerView3.C = null;
            }
            ArrayList arrayList2 = this.f5032s.f4850s0;
            if (arrayList2 != null) {
                arrayList2.remove(oVar);
            }
            this.f5032s.removeCallbacks(nVar);
        }
        this.f5032s = recyclerView;
        ArrayList arrayList3 = recyclerView.A;
        z0 z0Var2 = recyclerView.x;
        if (z0Var2 != null) {
            z0Var2.b("Cannot add item decoration during a scroll  or layout");
        }
        if (arrayList3.isEmpty()) {
            recyclerView.setWillNotDraw(false);
        }
        arrayList3.add(this);
        recyclerView.M();
        recyclerView.requestLayout();
        this.f5032s.B.add(this);
        RecyclerView recyclerView4 = this.f5032s;
        if (recyclerView4.f4850s0 == null) {
            recyclerView4.f4850s0 = new ArrayList();
        }
        recyclerView4.f4850s0.add(oVar);
    }

    public static int e(float f10, float f11, int[] iArr, int i10, int i11, int i12) {
        int i13 = iArr[1] - iArr[0];
        if (i13 != 0) {
            int i14 = i10 - i12;
            int i15 = (int) (((f11 - f10) / i13) * i14);
            int i16 = i11 + i15;
            if (i16 < i14 && i16 >= 0) {
                return i15;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.c
    public final void a(MotionEvent motionEvent) {
        if (this.f5035v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zD = d(motionEvent.getX(), motionEvent.getY());
            boolean zC = c(motionEvent.getX(), motionEvent.getY());
            if (zD || zC) {
                if (zC) {
                    this.f5036w = 1;
                    this.f5029p = (int) motionEvent.getX();
                } else if (zD) {
                    this.f5036w = 2;
                    this.f5026m = (int) motionEvent.getY();
                }
                f(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f5035v == 2) {
            this.f5026m = 0.0f;
            this.f5029p = 0.0f;
            f(1);
            this.f5036w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f5035v == 2) {
            g();
            int i10 = this.f5036w;
            int i11 = this.f5016b;
            if (i10 == 1) {
                float x = motionEvent.getX();
                int[] iArr = this.f5037y;
                iArr[0] = i11;
                int i12 = this.f5030q - i11;
                iArr[1] = i12;
                float fMax = Math.max(i11, Math.min(i12, x));
                if (Math.abs(this.f5028o - fMax) >= 2.0f) {
                    int iE = e(this.f5029p, fMax, iArr, this.f5032s.computeHorizontalScrollRange(), this.f5032s.computeHorizontalScrollOffset(), this.f5030q);
                    if (iE != 0) {
                        this.f5032s.scrollBy(iE, 0);
                    }
                    this.f5029p = fMax;
                }
            }
            if (this.f5036w == 2) {
                float y10 = motionEvent.getY();
                int[] iArr2 = this.x;
                iArr2[0] = i11;
                int i13 = this.f5031r - i11;
                iArr2[1] = i13;
                float fMax2 = Math.max(i11, Math.min(i13, y10));
                if (Math.abs(this.f5025l - fMax2) < 2.0f) {
                    return;
                }
                int iE2 = e(this.f5026m, fMax2, iArr2, this.f5032s.computeVerticalScrollRange(), this.f5032s.computeVerticalScrollOffset(), this.f5031r);
                if (iE2 != 0) {
                    this.f5032s.scrollBy(0, iE2);
                }
                this.f5026m = fMax2;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.c
    public final boolean b(MotionEvent motionEvent) {
        int i10 = this.f5035v;
        if (i10 != 1) {
            return i10 == 2;
        }
        boolean zD = d(motionEvent.getX(), motionEvent.getY());
        boolean zC = c(motionEvent.getX(), motionEvent.getY());
        if (motionEvent.getAction() != 0) {
            return false;
        }
        if (!zD && !zC) {
            return false;
        }
        if (zC) {
            this.f5036w = 1;
            this.f5029p = (int) motionEvent.getX();
        } else if (zD) {
            this.f5036w = 2;
            this.f5026m = (int) motionEvent.getY();
        }
        f(2);
        return true;
    }

    public final boolean c(float f10, float f11) {
        if (f11 < this.f5031r - this.f5023i) {
            return false;
        }
        int i10 = this.f5028o;
        int i11 = this.f5027n;
        return f10 >= ((float) (i10 - (i11 / 2))) && f10 <= ((float) ((i11 / 2) + i10));
    }

    public final boolean d(float f10, float f11) {
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        int layoutDirection = this.f5032s.getLayoutDirection();
        int i10 = this.f5019e;
        if (layoutDirection == 1) {
            if (f10 > i10) {
                return false;
            }
        } else if (f10 < this.f5030q - i10) {
            return false;
        }
        int i11 = this.f5025l;
        int i12 = this.k / 2;
        return f11 >= ((float) (i11 - i12)) && f11 <= ((float) (i12 + i11));
    }

    public final void f(int i10) {
        n nVar = this.B;
        StateListDrawable stateListDrawable = this.f5017c;
        if (i10 == 2 && this.f5035v != 2) {
            stateListDrawable.setState(C);
            this.f5032s.removeCallbacks(nVar);
        }
        if (i10 == 0) {
            this.f5032s.invalidate();
        } else {
            g();
        }
        if (this.f5035v == 2 && i10 != 2) {
            stateListDrawable.setState(D);
            this.f5032s.removeCallbacks(nVar);
            this.f5032s.postDelayed(nVar, 1200);
        } else if (i10 == 1) {
            this.f5032s.removeCallbacks(nVar);
            this.f5032s.postDelayed(nVar, 1500);
        }
        this.f5035v = i10;
    }

    public final void g() {
        int i10 = this.A;
        ValueAnimator valueAnimator = this.z;
        if (i10 != 0) {
            if (i10 != 3) {
                return;
            } else {
                valueAnimator.cancel();
            }
        }
        this.A = 1;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        valueAnimator.setDuration(500L);
        valueAnimator.setStartDelay(0L);
        valueAnimator.start();
    }
}
