package androidx.recyclerview.widget;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class o1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5003i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5004l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public OverScroller f5005m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Interpolator f5006n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5007o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5008p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5009q;

    public o1(RecyclerView recyclerView) {
        this.f5009q = recyclerView;
        i0 i0Var = RecyclerView.Q0;
        this.f5006n = i0Var;
        this.f5007o = false;
        this.f5008p = false;
        this.f5005m = new OverScroller(recyclerView.getContext(), i0Var);
    }

    public final void a(int i10, int i11) {
        RecyclerView recyclerView = this.f5009q;
        recyclerView.setScrollState(2);
        this.f5004l = 0;
        this.f5003i = 0;
        Interpolator interpolator = this.f5006n;
        i0 i0Var = RecyclerView.Q0;
        if (interpolator != i0Var) {
            this.f5006n = i0Var;
            this.f5005m = new OverScroller(recyclerView.getContext(), i0Var);
        }
        this.f5005m.fling(0, 0, i10, i11, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        b();
    }

    public final void b() {
        if (this.f5007o) {
            this.f5008p = true;
            return;
        }
        RecyclerView recyclerView = this.f5009q;
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        recyclerView.postOnAnimation(this);
    }

    public final void c(int i10, int i11, int i12, Interpolator interpolator) {
        RecyclerView recyclerView = this.f5009q;
        if (i12 == Integer.MIN_VALUE) {
            int iAbs = Math.abs(i10);
            int iAbs2 = Math.abs(i11);
            boolean z = iAbs > iAbs2;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            if (!z) {
                iAbs = iAbs2;
            }
            i12 = Math.min((int) (((iAbs / width) + 1.0f) * 300.0f), 2000);
        }
        int i13 = i12;
        if (interpolator == null) {
            interpolator = RecyclerView.Q0;
        }
        if (this.f5006n != interpolator) {
            this.f5006n = interpolator;
            this.f5005m = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.f5004l = 0;
        this.f5003i = 0;
        recyclerView.setScrollState(2);
        this.f5005m.startScroll(0, 0, i10, i11, i13);
        b();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i10;
        int i11;
        int i12;
        int i13;
        RecyclerView recyclerView = this.f5009q;
        int[] iArr = recyclerView.D0;
        if (recyclerView.x == null) {
            recyclerView.removeCallbacks(this);
            this.f5005m.abortAnimation();
            return;
        }
        this.f5008p = false;
        this.f5007o = true;
        recyclerView.k();
        OverScroller overScroller = this.f5005m;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i14 = currX - this.f5003i;
            int i15 = currY - this.f5004l;
            this.f5003i = currX;
            this.f5004l = currY;
            int iJ = RecyclerView.j(i14, recyclerView.S, recyclerView.U, recyclerView.getWidth());
            int iJ2 = RecyclerView.j(i15, recyclerView.T, recyclerView.V, recyclerView.getHeight());
            int[] iArr2 = recyclerView.D0;
            iArr2[0] = 0;
            iArr2[1] = 0;
            if (recyclerView.p(iJ, iJ2, 1, iArr2, null)) {
                iJ -= iArr[0];
                iJ2 -= iArr[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.i(iJ, iJ2);
            }
            if (recyclerView.f4856w != null) {
                iArr[0] = 0;
                iArr[1] = 0;
                recyclerView.a0(iJ, iJ2, iArr);
                int i16 = iArr[0];
                int i17 = iArr[1];
                int i18 = iJ - i16;
                int i19 = iJ2 - i17;
                androidx.leanback.widget.w wVar = recyclerView.x.f5117e;
                if (wVar != null && !wVar.f3155d && wVar.f3156e) {
                    int iB = recyclerView.f4846q0.b();
                    if (iB == 0) {
                        wVar.e();
                    } else if (wVar.f3152a >= iB) {
                        wVar.f3152a = iB - 1;
                        wVar.c(i16, i17);
                    } else {
                        wVar.c(i16, i17);
                    }
                }
                i10 = i18;
                i12 = i16;
                i11 = i19;
                i13 = i17;
            } else {
                i10 = iJ;
                i11 = iJ2;
                i12 = 0;
                i13 = 0;
            }
            if (!recyclerView.A.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.D0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            recyclerView.q(i12, i13, i10, i11, null, 1, iArr3);
            int i20 = i10 - iArr[0];
            int i21 = i11 - iArr[1];
            if (i12 != 0 || i13 != 0) {
                recyclerView.r(i12, i13);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i20 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i21 != 0));
            androidx.leanback.widget.w wVar2 = recyclerView.x.f5117e;
            if ((wVar2 == null || !wVar2.f3155d) && z) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i22 = i20 < 0 ? -currVelocity : i20 > 0 ? currVelocity : 0;
                    if (i21 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i21 <= 0) {
                        currVelocity = 0;
                    }
                    if (i22 < 0) {
                        recyclerView.t();
                        if (recyclerView.S.isFinished()) {
                            recyclerView.S.onAbsorb(-i22);
                        }
                    } else if (i22 > 0) {
                        recyclerView.u();
                        if (recyclerView.U.isFinished()) {
                            recyclerView.U.onAbsorb(i22);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.v();
                        if (recyclerView.T.isFinished()) {
                            recyclerView.T.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.s();
                        if (recyclerView.V.isFinished()) {
                            recyclerView.V.onAbsorb(currVelocity);
                        }
                    }
                    if (i22 != 0 || currVelocity != 0) {
                        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                        recyclerView.postInvalidateOnAnimation();
                    }
                }
                if (RecyclerView.O0) {
                    s sVar = recyclerView.f4844p0;
                    int[] iArr4 = (int[]) sVar.f5043d;
                    if (iArr4 != null) {
                        Arrays.fill(iArr4, -1);
                    }
                    sVar.f5042c = 0;
                }
            } else {
                b();
                u uVar = recyclerView.f4842o0;
                if (uVar != null) {
                    uVar.a(recyclerView, i12, i13);
                }
            }
        }
        androidx.leanback.widget.w wVar3 = recyclerView.x.f5117e;
        if (wVar3 != null && wVar3.f3155d) {
            wVar3.c(0, 0);
        }
        this.f5007o = false;
        if (!this.f5008p) {
            recyclerView.setScrollState(0);
            recyclerView.h0(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
            recyclerView.postOnAnimation(this);
        }
    }
}
