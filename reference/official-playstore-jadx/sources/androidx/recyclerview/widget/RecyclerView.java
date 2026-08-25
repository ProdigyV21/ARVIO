package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.view.ScrollingView;
import androidx.media3.session.MediaUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class RecyclerView extends ViewGroup implements ScrollingView, androidx.core.view.j0, androidx.core.view.k0 {
    public static final int[] K0 = {R.attr.nestedScrollingEnabled};
    public static final float L0 = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final boolean M0 = true;
    public static final boolean N0 = true;
    public static final boolean O0 = true;
    public static final Class[] P0;
    public static final i0 Q0;
    public static final m1 R0;
    public final ArrayList A;
    public androidx.core.view.m0 A0;
    public final ArrayList B;
    public final int[] B0;
    public c C;
    public final int[] C0;
    public boolean D;
    public final int[] D0;
    public boolean E;
    public final ArrayList E0;
    public boolean F;
    public final h0 F0;
    public int G;
    public boolean G0;
    public boolean H;
    public int H0;
    public boolean I;
    public int I0;
    public boolean J;
    public final j0 J0;
    public int K;
    public boolean L;
    public final AccessibilityManager M;
    public boolean N;
    public boolean O;
    public int P;
    public int Q;
    public p0 R;
    public EdgeEffect S;
    public EdgeEffect T;
    public EdgeEffect U;
    public EdgeEffect V;
    public u0 W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f4825a0;
    public int b0;
    public VelocityTracker c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f4826d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f4827e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f4828f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f4829g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f4830h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f4831i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final int f4832i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final int f4833j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final float f4834k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final g1 f4835l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final float f4836l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f1 f4837m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public boolean f4838m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i1 f4839n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final o1 f4840n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final androidx.recyclerview.widget.c f4841o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public u f4842o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final androidx.appcompat.app.i1 f4843p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final s f4844p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final c2 f4845q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final l1 f4846q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4847r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public c1 f4848r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final h0 f4849s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public ArrayList f4850s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f4851t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public boolean f4852t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Rect f4853u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f4854u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final RectF f4855v;
    public final j0 v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public l0 f4856w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f4857w0;
    public z0 x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public r1 f4858x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public e f4859y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public a f4860y0;
    public final ArrayList z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final int[] f4861z0;

    public interface a {
        int a();
    }

    public interface b {
    }

    public interface c {
        void a(MotionEvent motionEvent);

        boolean b(MotionEvent motionEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface d {
    }

    public interface e {
        void a(p1 p1Var);
    }

    static {
        Class cls = Integer.TYPE;
        P0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        Q0 = new i0();
        R0 = new m1();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.arvio.tv.R.attr.recyclerViewStyle);
    }

    public static RecyclerView B(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            RecyclerView recyclerViewB = B(viewGroup.getChildAt(i10));
            if (recyclerViewB != null) {
                return recyclerViewB;
            }
        }
        return null;
    }

    public static p1 G(View view) {
        if (view == null) {
            return null;
        }
        return ((a1) view.getLayoutParams()).f4875a;
    }

    public static void g(p1 p1Var) {
        WeakReference<RecyclerView> weakReference = p1Var.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == p1Var.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            p1Var.mNestedRecyclerView = null;
        }
    }

    private androidx.core.view.m0 getScrollingChildHelper() {
        if (this.A0 == null) {
            this.A0 = new androidx.core.view.m0(this);
        }
        return this.A0;
    }

    public static int j(int i10, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i11) {
        if (i10 > 0 && edgeEffect != null && kotlin.reflect.b0.s(edgeEffect) != 0.0f) {
            int iRound = Math.round(kotlin.reflect.b0.y(edgeEffect, ((-i10) * 4.0f) / i11, 0.5f) * ((-i11) / 4.0f));
            if (iRound != i10) {
                edgeEffect.finish();
            }
            return i10 - iRound;
        }
        if (i10 >= 0 || edgeEffect2 == null || kotlin.reflect.b0.s(edgeEffect2) == 0.0f) {
            return i10;
        }
        float f10 = i11;
        int iRound2 = Math.round(kotlin.reflect.b0.y(edgeEffect2, (i10 * 4.0f) / f10, 0.5f) * (f10 / 4.0f));
        if (iRound2 != i10) {
            edgeEffect2.finish();
        }
        return i10 - iRound2;
    }

    public final void A(int[] iArr) {
        int iR = this.f4843p.R();
        if (iR == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i10 = Integer.MAX_VALUE;
        int i11 = Integer.MIN_VALUE;
        for (int i12 = 0; i12 < iR; i12++) {
            p1 p1VarG = G(this.f4843p.Q(i12));
            if (!p1VarG.shouldIgnore()) {
                int layoutPosition = p1VarG.getLayoutPosition();
                if (layoutPosition < i10) {
                    i10 = layoutPosition;
                }
                if (layoutPosition > i11) {
                    i11 = layoutPosition;
                }
            }
        }
        iArr[0] = i10;
        iArr[1] = i11;
    }

    public final p1 C(int i10) {
        p1 p1Var = null;
        if (this.N) {
            return null;
        }
        int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            p1 p1VarG = G(((j0) this.f4843p.f1059l).f4952a.getChildAt(i11));
            if (p1VarG != null && !p1VarG.isRemoved() && D(p1VarG) == i10) {
                if (!((ArrayList) this.f4843p.f1061n).contains(p1VarG.itemView)) {
                    return p1VarG;
                }
                p1Var = p1VarG;
            }
        }
        return p1Var;
    }

    public final int D(p1 p1Var) {
        if (p1Var.hasAnyOfTheFlags(524) || !p1Var.isBound()) {
            return -1;
        }
        int i10 = p1Var.mPosition;
        ArrayList arrayList = this.f4841o.f4895b;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            androidx.recyclerview.widget.b bVar = (androidx.recyclerview.widget.b) arrayList.get(i11);
            int i12 = bVar.f4879a;
            if (i12 != 1) {
                if (i12 == 2) {
                    int i13 = bVar.f4880b;
                    if (i13 <= i10) {
                        int i14 = bVar.f4882d;
                        if (i13 + i14 > i10) {
                            return -1;
                        }
                        i10 -= i14;
                    } else {
                        continue;
                    }
                } else if (i12 == 8) {
                    int i15 = bVar.f4880b;
                    if (i15 == i10) {
                        i10 = bVar.f4882d;
                    } else {
                        if (i15 < i10) {
                            i10--;
                        }
                        if (bVar.f4882d <= i10) {
                            i10++;
                        }
                    }
                }
            } else if (bVar.f4880b <= i10) {
                i10 += bVar.f4882d;
            }
        }
        return i10;
    }

    public final long E(p1 p1Var) {
        return this.f4856w.hasStableIds() ? p1Var.getItemId() : p1Var.mPosition;
    }

    public final p1 F(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return G(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect H(View view) {
        a1 a1Var = (a1) view.getLayoutParams();
        boolean z = a1Var.f4877c;
        Rect rect = a1Var.f4876b;
        if (!z || (this.f4846q0.f4979g && (a1Var.f4875a.isUpdated() || a1Var.f4875a.isInvalid()))) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.A;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            Rect rect2 = this.f4851t;
            rect2.set(0, 0, 0, 0);
            ((v0) arrayList.get(i10)).getClass();
            ((a1) view.getLayoutParams()).f4875a.getLayoutPosition();
            rect2.set(0, 0, 0, 0);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        a1Var.f4877c = false;
        return rect;
    }

    public final boolean I() {
        return !this.F || this.N || this.f4841o.g();
    }

    public boolean J() {
        return isChildrenDrawingOrderEnabled();
    }

    public final boolean K() {
        return this.P > 0;
    }

    public final void L(int i10) {
        if (this.x == null) {
            return;
        }
        setScrollState(2);
        this.x.B0(i10);
        awakenScrollBars();
    }

    public final void M() {
        int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            ((a1) ((j0) this.f4843p.f1059l).f4952a.getChildAt(i10).getLayoutParams()).f4877c = true;
        }
        ArrayList arrayList = this.f4837m.f4921c;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            a1 a1Var = (a1) ((p1) arrayList.get(i11)).itemView.getLayoutParams();
            if (a1Var != null) {
                a1Var.f4877c = true;
            }
        }
    }

    public final void N(int i10, int i11, boolean z) {
        int i12 = i10 + i11;
        int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
        for (int i13 = 0; i13 < childCount; i13++) {
            p1 p1VarG = G(((j0) this.f4843p.f1059l).f4952a.getChildAt(i13));
            if (p1VarG != null && !p1VarG.shouldIgnore()) {
                int i14 = p1VarG.mPosition;
                l1 l1Var = this.f4846q0;
                if (i14 >= i12) {
                    p1VarG.offsetPosition(-i11, z);
                    l1Var.f4978f = true;
                } else if (i14 >= i10) {
                    p1VarG.flagRemovedAndOffsetPosition(i10 - 1, -i11, z);
                    l1Var.f4978f = true;
                }
            }
        }
        f1 f1Var = this.f4837m;
        ArrayList arrayList = f1Var.f4921c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            p1 p1Var = (p1) arrayList.get(size);
            if (p1Var != null) {
                int i15 = p1Var.mPosition;
                if (i15 >= i12) {
                    p1Var.offsetPosition(-i11, z);
                } else if (i15 >= i10) {
                    p1Var.addFlags(8);
                    f1Var.g(size);
                }
            }
        }
        requestLayout();
    }

    public final void O() {
        this.P++;
    }

    public final void P(boolean z) {
        int i10;
        AccessibilityManager accessibilityManager;
        int i11 = this.P - 1;
        this.P = i11;
        if (i11 < 1) {
            this.P = 0;
            if (z) {
                int i12 = this.K;
                this.K = 0;
                if (i12 != 0 && (accessibilityManager = this.M) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i12);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.E0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    p1 p1Var = (p1) arrayList.get(size);
                    if (p1Var.itemView.getParent() == this && !p1Var.shouldIgnore() && (i10 = p1Var.mPendingAccessibilityState) != -1) {
                        View view = p1Var.itemView;
                        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                        view.setImportantForAccessibility(i10);
                        p1Var.mPendingAccessibilityState = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void Q(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.b0) {
            int i10 = actionIndex == 0 ? 1 : 0;
            this.b0 = motionEvent.getPointerId(i10);
            int x = (int) (motionEvent.getX(i10) + 0.5f);
            this.f4828f0 = x;
            this.f4826d0 = x;
            int y10 = (int) (motionEvent.getY(i10) + 0.5f);
            this.f4829g0 = y10;
            this.f4827e0 = y10;
        }
    }

    public final void R() {
        if (this.f4857w0 || !this.D) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        postOnAnimation(this.F0);
        this.f4857w0 = true;
    }

    public final void S() {
        boolean z;
        boolean z5 = false;
        if (this.N) {
            androidx.recyclerview.widget.c cVar = this.f4841o;
            cVar.k(cVar.f4895b);
            cVar.k(cVar.f4896c);
            cVar.f4899f = 0;
            if (this.O) {
                this.x.f0();
            }
        }
        if (this.W == null || !this.x.L0()) {
            this.f4841o.c();
        } else {
            this.f4841o.j();
        }
        boolean z10 = this.f4852t0 || this.f4854u0;
        boolean z11 = this.F && this.W != null && ((z = this.N) || z10 || this.x.f5118f) && (!z || this.f4856w.hasStableIds());
        l1 l1Var = this.f4846q0;
        l1Var.f4982j = z11;
        if (z11 && z10 && !this.N && this.W != null && this.x.L0()) {
            z5 = true;
        }
        l1Var.k = z5;
    }

    public final void T(boolean z) {
        this.O = z | this.O;
        this.N = true;
        int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            p1 p1VarG = G(((j0) this.f4843p.f1059l).f4952a.getChildAt(i10));
            if (p1VarG != null && !p1VarG.shouldIgnore()) {
                p1VarG.addFlags(6);
            }
        }
        M();
        f1 f1Var = this.f4837m;
        ArrayList arrayList = f1Var.f4921c;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            p1 p1Var = (p1) arrayList.get(i11);
            if (p1Var != null) {
                p1Var.addFlags(6);
                p1Var.addChangePayload(null);
            }
        }
        l0 l0Var = f1Var.f4926h.f4856w;
        if (l0Var == null || !l0Var.hasStableIds()) {
            f1Var.f();
        }
    }

    public final void U(p1 p1Var, t0 t0Var) {
        p1Var.setFlags(0, 8192);
        boolean z = this.f4846q0.f4980h;
        c2 c2Var = this.f4845q;
        if (z && p1Var.isUpdated() && !p1Var.isRemoved() && !p1Var.shouldIgnore()) {
            ((t.p) c2Var.f4904b).g(E(p1Var), p1Var);
        }
        t.l0 l0Var = (t.l0) c2Var.f4903a;
        d2 d2VarA = (d2) l0Var.get(p1Var);
        if (d2VarA == null) {
            d2VarA = d2.a();
            l0Var.put(p1Var, d2VarA);
        }
        d2VarA.f4911b = t0Var;
        d2VarA.f4910a |= 4;
    }

    public final int V(float f10, int i10) {
        float height = f10 / getHeight();
        float width = i10 / getWidth();
        EdgeEffect edgeEffect = this.S;
        float f11 = 0.0f;
        if (edgeEffect == null || kotlin.reflect.b0.s(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.U;
            if (edgeEffect2 != null && kotlin.reflect.b0.s(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.U.onRelease();
                } else {
                    float fY = kotlin.reflect.b0.y(this.U, width, height);
                    if (kotlin.reflect.b0.s(this.U) == 0.0f) {
                        this.U.onRelease();
                    }
                    f11 = fY;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.S.onRelease();
            } else {
                float f12 = -kotlin.reflect.b0.y(this.S, -width, 1.0f - height);
                if (kotlin.reflect.b0.s(this.S) == 0.0f) {
                    this.S.onRelease();
                }
                f11 = f12;
            }
            invalidate();
        }
        return Math.round(f11 * getWidth());
    }

    public final int W(float f10, int i10) {
        float width = f10 / getWidth();
        float height = i10 / getHeight();
        EdgeEffect edgeEffect = this.T;
        float f11 = 0.0f;
        if (edgeEffect == null || kotlin.reflect.b0.s(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.V;
            if (edgeEffect2 != null && kotlin.reflect.b0.s(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.V.onRelease();
                } else {
                    float fY = kotlin.reflect.b0.y(this.V, height, 1.0f - width);
                    if (kotlin.reflect.b0.s(this.V) == 0.0f) {
                        this.V.onRelease();
                    }
                    f11 = fY;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.T.onRelease();
            } else {
                float f12 = -kotlin.reflect.b0.y(this.T, -height, width);
                if (kotlin.reflect.b0.s(this.T) == 0.0f) {
                    this.T.onRelease();
                }
                f11 = f12;
            }
            invalidate();
        }
        return Math.round(f11 * getHeight());
    }

    public final void X(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.f4851t;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof a1) {
            a1 a1Var = (a1) layoutParams;
            if (!a1Var.f4877c) {
                Rect rect2 = a1Var.f4876b;
                rect.left -= rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.x.x0(this, view, this.f4851t, !this.F, view2 == null);
    }

    public final void Y() {
        VelocityTracker velocityTracker = this.c0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        h0(0);
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.S.isFinished();
        }
        EdgeEffect edgeEffect2 = this.T;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.T.isFinished();
        }
        EdgeEffect edgeEffect3 = this.U;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect4 = this.V;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean Z(int r18, int r19, android.view.MotionEvent r20, int r21) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Z(int, int, android.view.MotionEvent, int):boolean");
    }

    public final void a0(int i10, int i11, int[] iArr) {
        p1 p1Var;
        f0();
        O();
        int i12 = androidx.core.os.r.f2132a;
        Trace.beginSection("RV Scroll");
        l1 l1Var = this.f4846q0;
        x(l1Var);
        f1 f1Var = this.f4837m;
        int iA0 = i10 != 0 ? this.x.A0(i10, f1Var, l1Var) : 0;
        int iC0 = i11 != 0 ? this.x.C0(i11, f1Var, l1Var) : 0;
        Trace.endSection();
        androidx.appcompat.app.i1 i1Var = this.f4843p;
        int iR = i1Var.R();
        for (int i13 = 0; i13 < iR; i13++) {
            View viewQ = i1Var.Q(i13);
            p1 p1VarF = F(viewQ);
            if (p1VarF != null && (p1Var = p1VarF.mShadowingHolder) != null) {
                View view = p1Var.itemView;
                int left = viewQ.getLeft();
                int top = viewQ.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        P(true);
        g0(false);
        if (iArr != null) {
            iArr[0] = iA0;
            iArr[1] = iC0;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i10, int i11) {
        z0 z0Var = this.x;
        if (z0Var == null || !z0Var.W(this, arrayList, i10, i11)) {
            super.addFocusables(arrayList, i10, i11);
        }
    }

    public final boolean b0(EdgeEffect edgeEffect, int i10, int i11) {
        if (i10 > 0) {
            return true;
        }
        float fS = kotlin.reflect.b0.s(edgeEffect) * i11;
        float fAbs = Math.abs(-i10) * 0.35f;
        float f10 = this.f4831i * 0.015f;
        double dLog = Math.log(fAbs / f10);
        double d4 = L0;
        return ((float) (Math.exp((d4 / (d4 - 1.0d)) * dLog) * ((double) f10))) < fS;
    }

    public void c0(int i10, int i11) {
        e0(i10, i11);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof a1) && this.x.f((a1) layoutParams);
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollExtent() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.d()) {
            return this.x.j(this.f4846q0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollOffset() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.d()) {
            return this.x.k(this.f4846q0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollRange() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.d()) {
            return this.x.l(this.f4846q0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollExtent() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.e()) {
            return this.x.m(this.f4846q0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollOffset() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.e()) {
            return this.x.n(this.f4846q0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollRange() {
        z0 z0Var = this.x;
        if (z0Var != null && z0Var.e()) {
            return this.x.o(this.f4846q0);
        }
        return 0;
    }

    public final void d0(int i10, int i11, Interpolator interpolator, int i12, boolean z) {
        z0 z0Var = this.x;
        if (z0Var == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.I) {
            return;
        }
        if (!z0Var.d()) {
            i10 = 0;
        }
        if (!this.x.e()) {
            i11 = 0;
        }
        if (i10 == 0 && i11 == 0) {
            return;
        }
        if (i12 != Integer.MIN_VALUE && i12 <= 0) {
            scrollBy(i10, i11);
            return;
        }
        if (z) {
            int i13 = i10 != 0 ? 1 : 0;
            if (i11 != 0) {
                i13 |= 2;
            }
            getScrollingChildHelper().g(i13, 1);
        }
        this.f4840n0.c(i10, i11, i12, interpolator);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f10, float f11, boolean z) {
        return getScrollingChildHelper().a(f10, f11, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f10, float f11) {
        return getScrollingChildHelper().b(f10, f11);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i10, int i11, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i10, i11, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i10, int i11, int i12, int i13, int[] iArr) {
        return getScrollingChildHelper().d(i10, i11, i12, i13, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        ArrayList arrayList = this.A;
        int size = arrayList.size();
        boolean z5 = false;
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                break;
            }
            r rVar = (r) ((v0) arrayList.get(i10));
            if (rVar.f5030q != rVar.f5032s.getWidth() || rVar.f5031r != rVar.f5032s.getHeight()) {
                rVar.f5030q = rVar.f5032s.getWidth();
                rVar.f5031r = rVar.f5032s.getHeight();
                rVar.f(0);
            } else if (rVar.A != 0) {
                if (rVar.f5033t) {
                    int i11 = rVar.f5030q;
                    int i12 = rVar.f5019e;
                    int i13 = i11 - i12;
                    int i14 = rVar.f5025l;
                    int i15 = rVar.k;
                    int i16 = i14 - (i15 / 2);
                    StateListDrawable stateListDrawable = rVar.f5017c;
                    stateListDrawable.setBounds(0, 0, i12, i15);
                    Drawable drawable = rVar.f5018d;
                    drawable.setBounds(0, 0, rVar.f5020f, rVar.f5031r);
                    RecyclerView recyclerView = rVar.f5032s;
                    WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                    if (recyclerView.getLayoutDirection() == 1) {
                        drawable.draw(canvas);
                        canvas.translate(i12, i16);
                        canvas.scale(-1.0f, 1.0f);
                        stateListDrawable.draw(canvas);
                        canvas.scale(-1.0f, 1.0f);
                        canvas.translate(-i12, -i16);
                    } else {
                        canvas.translate(i13, 0.0f);
                        drawable.draw(canvas);
                        canvas.translate(0.0f, i16);
                        stateListDrawable.draw(canvas);
                        canvas.translate(-i13, -i16);
                    }
                }
                if (rVar.f5034u) {
                    int i17 = rVar.f5031r;
                    int i18 = rVar.f5023i;
                    int i19 = i17 - i18;
                    int i20 = rVar.f5028o;
                    int i21 = rVar.f5027n;
                    StateListDrawable stateListDrawable2 = rVar.f5021g;
                    stateListDrawable2.setBounds(0, 0, i21, i18);
                    Drawable drawable2 = rVar.f5022h;
                    drawable2.setBounds(0, 0, rVar.f5030q, rVar.f5024j);
                    canvas.translate(0.0f, i19);
                    drawable2.draw(canvas);
                    canvas.translate(i20 - (i21 / 2), 0.0f);
                    stateListDrawable2.draw(canvas);
                    canvas.translate(-r8, -i19);
                }
            }
            i10++;
        }
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.f4847r ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.S;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.f4847r) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.T;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.U;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f4847r ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.U;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.V;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f4847r) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.V;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z5 = true;
            }
            z |= z5;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.W == null || arrayList.size() <= 0 || !this.W.f()) ? z : true) {
            WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j10) {
        return super.drawChild(canvas, view, j10);
    }

    public final void e(p1 p1Var) {
        View view = p1Var.itemView;
        boolean z = view.getParent() == this;
        this.f4837m.l(F(view));
        if (p1Var.isTmpDetached()) {
            this.f4843p.c(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (z) {
            androidx.appcompat.app.i1 i1Var = this.f4843p;
            int iIndexOfChild = ((j0) i1Var.f1059l).f4952a.indexOfChild(view);
            if (iIndexOfChild < 0) {
                throw new IllegalArgumentException("view is not a child, cannot hide " + view);
            }
            ((androidx.recyclerview.widget.e) i1Var.f1060m).k(iIndexOfChild);
            ((ArrayList) i1Var.f1061n).add(view);
            j0 j0Var = (j0) i1Var.f1059l;
            p1 p1VarG = G(view);
            if (p1VarG != null) {
                p1VarG.onEnteredHiddenState(j0Var.f4952a);
                return;
            }
            return;
        }
        androidx.appcompat.app.i1 i1Var2 = this.f4843p;
        RecyclerView recyclerView = ((j0) i1Var2.f1059l).f4952a;
        int childCount = recyclerView.getChildCount();
        ((androidx.recyclerview.widget.e) i1Var2.f1060m).h(childCount, true);
        ((ArrayList) i1Var2.f1061n).add(view);
        p1 p1VarG2 = G(view);
        if (p1VarG2 != null) {
            p1VarG2.onEnteredHiddenState(recyclerView);
        }
        recyclerView.addView(view, childCount);
        p1 p1VarG3 = G(view);
        l0 l0Var = recyclerView.f4856w;
        if (l0Var == null || p1VarG3 == null) {
            return;
        }
        l0Var.onViewAttachedToWindow(p1VarG3);
    }

    public void e0(int i10, int i11) {
        d0(i10, i11, null, Integer.MIN_VALUE, false);
    }

    public final void f(String str) {
        if (K()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(androidx.fragment.app.a2.k(this, new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(androidx.fragment.app.a2.k(this, new StringBuilder(""))));
        }
    }

    public final void f0() {
        int i10 = this.G + 1;
        this.G = i10;
        if (i10 != 1 || this.I) {
            return;
        }
        this.H = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0197 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d0 A[ADDED_TO_REGION] */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g0(boolean z) {
        if (this.G < 1) {
            this.G = 1;
        }
        if (!z && !this.I) {
            this.H = false;
        }
        if (this.G == 1) {
            if (z && this.H && !this.I && this.x != null && this.f4856w != null) {
                m();
            }
            if (!this.I) {
                this.H = false;
            }
        }
        this.G--;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        z0 z0Var = this.x;
        if (z0Var != null) {
            return z0Var.s();
        }
        throw new IllegalStateException(androidx.fragment.app.a2.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        z0 z0Var = this.x;
        if (z0Var != null) {
            return z0Var.t(getContext(), attributeSet);
        }
        throw new IllegalStateException(androidx.fragment.app.a2.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public l0 getAdapter() {
        return this.f4856w;
    }

    @Override // android.view.View
    public int getBaseline() {
        z0 z0Var = this.x;
        if (z0Var == null) {
            return super.getBaseline();
        }
        z0Var.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i10, int i11) {
        a aVar = this.f4860y0;
        return aVar == null ? super.getChildDrawingOrder(i10, i11) : aVar.a();
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f4847r;
    }

    public r1 getCompatAccessibilityDelegate() {
        return this.f4858x0;
    }

    public p0 getEdgeEffectFactory() {
        return this.R;
    }

    public u0 getItemAnimator() {
        return this.W;
    }

    public int getItemDecorationCount() {
        return this.A.size();
    }

    public z0 getLayoutManager() {
        return this.x;
    }

    public int getMaxFlingVelocity() {
        return this.f4833j0;
    }

    public int getMinFlingVelocity() {
        return this.f4832i0;
    }

    public long getNanoTime() {
        if (O0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public b1 getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f4838m0;
    }

    public e1 getRecycledViewPool() {
        return this.f4837m.c();
    }

    public int getScrollState() {
        return this.f4825a0;
    }

    public final void h() {
        int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            p1 p1VarG = G(((j0) this.f4843p.f1059l).f4952a.getChildAt(i10));
            if (!p1VarG.shouldIgnore()) {
                p1VarG.clearOldPosition();
            }
        }
        f1 f1Var = this.f4837m;
        ArrayList arrayList = f1Var.f4919a;
        ArrayList arrayList2 = f1Var.f4921c;
        int size = arrayList2.size();
        for (int i11 = 0; i11 < size; i11++) {
            ((p1) arrayList2.get(i11)).clearOldPosition();
        }
        int size2 = arrayList.size();
        for (int i12 = 0; i12 < size2; i12++) {
            ((p1) arrayList.get(i12)).clearOldPosition();
        }
        ArrayList arrayList3 = f1Var.f4920b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i13 = 0; i13 < size3; i13++) {
                ((p1) f1Var.f4920b.get(i13)).clearOldPosition();
            }
        }
    }

    public final void h0(int i10) {
        getScrollingChildHelper().h(i10);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0);
    }

    public final void i(int i10, int i11) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished() || i10 <= 0) {
            zIsFinished = false;
        } else {
            this.S.onRelease();
            zIsFinished = this.S.isFinished();
        }
        EdgeEffect edgeEffect2 = this.U;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i10 < 0) {
            this.U.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i11 > 0) {
            this.T.onRelease();
            zIsFinished |= this.T.isFinished();
        }
        EdgeEffect edgeEffect4 = this.V;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i11 < 0) {
            this.V.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.D;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.I;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f2286d;
    }

    public final void k() {
        if (!this.F || this.N) {
            int i10 = androidx.core.os.r.f2132a;
            Trace.beginSection("RV FullInvalidate");
            m();
            Trace.endSection();
            return;
        }
        androidx.recyclerview.widget.c cVar = this.f4841o;
        if (cVar.g()) {
            int i11 = cVar.f4899f;
            if ((i11 & 4) == 0 || (i11 & 11) != 0) {
                if (cVar.g()) {
                    int i12 = androidx.core.os.r.f2132a;
                    Trace.beginSection("RV FullInvalidate");
                    m();
                    Trace.endSection();
                    return;
                }
                return;
            }
            int i13 = androidx.core.os.r.f2132a;
            Trace.beginSection("RV PartialInvalidate");
            f0();
            O();
            cVar.j();
            if (!this.H) {
                androidx.appcompat.app.i1 i1Var = this.f4843p;
                int iR = i1Var.R();
                int i14 = 0;
                while (true) {
                    if (i14 < iR) {
                        p1 p1VarG = G(i1Var.Q(i14));
                        if (p1VarG != null && !p1VarG.shouldIgnore() && p1VarG.isUpdated()) {
                            m();
                            break;
                        }
                        i14++;
                    } else {
                        cVar.b();
                        break;
                    }
                }
            }
            g0(true);
            P(true);
            Trace.endSection();
        }
    }

    public final void l(int i10, int i11) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        setMeasuredDimension(z0.g(i10, paddingRight, getMinimumWidth()), z0.g(i11, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03bf  */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19, types: [int] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m() {
        /*
            Method dump skipped, instruction units count: 1063
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m():void");
    }

    public final void n() {
        d2 d2Var;
        View viewY;
        l1 l1Var = this.f4846q0;
        l1Var.a(1);
        x(l1Var);
        l1Var.f4981i = false;
        f0();
        c2 c2Var = this.f4845q;
        t.l0 l0Var = (t.l0) c2Var.f4903a;
        t.l0 l0Var2 = (t.l0) c2Var.f4903a;
        l0Var.clear();
        t.p pVar = (t.p) c2Var.f4904b;
        pVar.b();
        O();
        S();
        p1 p1VarF = null;
        View focusedChild = (this.f4838m0 && hasFocus() && this.f4856w != null) ? getFocusedChild() : null;
        if (focusedChild != null && (viewY = y(focusedChild)) != null) {
            p1VarF = F(viewY);
        }
        if (p1VarF == null) {
            l1Var.f4984m = -1L;
            l1Var.f4983l = -1;
            l1Var.f4985n = -1;
        } else {
            l1Var.f4984m = this.f4856w.hasStableIds() ? p1VarF.getItemId() : -1L;
            l1Var.f4983l = this.N ? -1 : p1VarF.isRemoved() ? p1VarF.mOldPosition : p1VarF.getAbsoluteAdapterPosition();
            View focusedChild2 = p1VarF.itemView;
            int id = focusedChild2.getId();
            while (!focusedChild2.isFocused() && (focusedChild2 instanceof ViewGroup) && focusedChild2.hasFocus()) {
                focusedChild2 = ((ViewGroup) focusedChild2).getFocusedChild();
                if (focusedChild2.getId() != -1) {
                    id = focusedChild2.getId();
                }
            }
            l1Var.f4985n = id;
        }
        l1Var.f4980h = l1Var.f4982j && this.f4854u0;
        this.f4854u0 = false;
        this.f4852t0 = false;
        l1Var.f4979g = l1Var.k;
        l1Var.f4977e = this.f4856w.getItemCount();
        A(this.f4861z0);
        if (l1Var.f4982j) {
            int iR = this.f4843p.R();
            for (int i10 = 0; i10 < iR; i10++) {
                p1 p1VarG = G(this.f4843p.Q(i10));
                if (!p1VarG.shouldIgnore() && (!p1VarG.isInvalid() || this.f4856w.hasStableIds())) {
                    u0 u0Var = this.W;
                    u0.b(p1VarG);
                    p1VarG.getUnmodifiedPayloads();
                    u0Var.getClass();
                    t0 t0Var = new t0();
                    t0Var.a(p1VarG);
                    d2 d2VarA = (d2) l0Var2.get(p1VarG);
                    if (d2VarA == null) {
                        d2VarA = d2.a();
                        l0Var2.put(p1VarG, d2VarA);
                    }
                    d2VarA.f4911b = t0Var;
                    d2VarA.f4910a |= 4;
                    if (l1Var.f4980h && p1VarG.isUpdated() && !p1VarG.isRemoved() && !p1VarG.shouldIgnore() && !p1VarG.isInvalid()) {
                        pVar.g(E(p1VarG), p1VarG);
                    }
                }
            }
        }
        if (l1Var.k) {
            int childCount = ((j0) this.f4843p.f1059l).f4952a.getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                p1 p1VarG2 = G(((j0) this.f4843p.f1059l).f4952a.getChildAt(i11));
                if (!p1VarG2.shouldIgnore()) {
                    p1VarG2.saveOldPosition();
                }
            }
            boolean z = l1Var.f4978f;
            l1Var.f4978f = false;
            this.x.k0(this.f4837m, l1Var);
            l1Var.f4978f = z;
            for (int i12 = 0; i12 < this.f4843p.R(); i12++) {
                p1 p1VarG3 = G(this.f4843p.Q(i12));
                if (!p1VarG3.shouldIgnore() && ((d2Var = (d2) l0Var2.get(p1VarG3)) == null || (d2Var.f4910a & 4) == 0)) {
                    u0.b(p1VarG3);
                    boolean zHasAnyOfTheFlags = p1VarG3.hasAnyOfTheFlags(8192);
                    u0 u0Var2 = this.W;
                    p1VarG3.getUnmodifiedPayloads();
                    u0Var2.getClass();
                    t0 t0Var2 = new t0();
                    t0Var2.a(p1VarG3);
                    if (zHasAnyOfTheFlags) {
                        U(p1VarG3, t0Var2);
                    } else {
                        d2 d2VarA2 = (d2) l0Var2.get(p1VarG3);
                        if (d2VarA2 == null) {
                            d2VarA2 = d2.a();
                            l0Var2.put(p1VarG3, d2VarA2);
                        }
                        d2VarA2.f4910a |= 2;
                        d2VarA2.f4911b = t0Var2;
                    }
                }
            }
            h();
        } else {
            h();
        }
        P(true);
        g0(false);
        l1Var.f4976d = 2;
    }

    public final void o() {
        f0();
        O();
        l1 l1Var = this.f4846q0;
        l1Var.a(6);
        this.f4841o.c();
        l1Var.f4977e = this.f4856w.getItemCount();
        l1Var.f4975c = 0;
        if (this.f4839n != null && this.f4856w.canRestoreState()) {
            Parcelable parcelable = this.f4839n.f4946m;
            if (parcelable != null) {
                this.x.o0(parcelable);
            }
            this.f4839n = null;
        }
        l1Var.f4979g = false;
        this.x.k0(this.f4837m, l1Var);
        l1Var.f4978f = false;
        l1Var.f4982j = l1Var.f4982j && this.W != null;
        l1Var.f4976d = 4;
        P(true);
        g0(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.P = r0
            r1 = 1
            r5.D = r1
            boolean r2 = r5.F
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.F = r2
            androidx.recyclerview.widget.f1 r2 = r5.f4837m
            r2.d()
            androidx.recyclerview.widget.z0 r2 = r5.x
            if (r2 == 0) goto L23
            r2.f5119g = r1
        L23:
            r5.f4857w0 = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.O0
            if (r0 == 0) goto L78
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.u.f5058o
            java.lang.Object r1 = r0.get()
            androidx.recyclerview.widget.u r1 = (androidx.recyclerview.widget.u) r1
            r5.f4842o0 = r1
            if (r1 != 0) goto L71
            androidx.recyclerview.widget.u r1 = new androidx.recyclerview.widget.u
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.f5060i = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.f5063n = r2
            r5.f4842o0 = r1
            java.util.WeakHashMap r1 = androidx.core.view.b2.f2200a
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L63
            if (r1 == 0) goto L63
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L63
            goto L65
        L63:
            r1 = 1114636288(0x42700000, float:60.0)
        L65:
            androidx.recyclerview.widget.u r2 = r5.f4842o0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.f5062m = r3
            r0.set(r2)
        L71:
            androidx.recyclerview.widget.u r0 = r5.f4842o0
            java.util.ArrayList r0 = r0.f5060i
            r0.add(r5)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        u uVar;
        androidx.leanback.widget.w wVar;
        super.onDetachedFromWindow();
        u0 u0Var = this.W;
        if (u0Var != null) {
            u0Var.e();
        }
        int i10 = 0;
        setScrollState(0);
        o1 o1Var = this.f4840n0;
        o1Var.f5009q.removeCallbacks(o1Var);
        o1Var.f5005m.abortAnimation();
        z0 z0Var = this.x;
        if (z0Var != null && (wVar = z0Var.f5117e) != null) {
            wVar.e();
        }
        this.D = false;
        z0 z0Var2 = this.x;
        if (z0Var2 != null) {
            z0Var2.f5119g = false;
            z0Var2.X(this);
        }
        this.E0.clear();
        removeCallbacks(this.F0);
        this.f4845q.getClass();
        while (d2.f4909d.a() != null) {
        }
        f1 f1Var = this.f4837m;
        ArrayList arrayList = f1Var.f4921c;
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            androidx.work.impl.t.d(((p1) arrayList.get(i11)).itemView);
        }
        f1Var.e(f1Var.f4926h.f4856w, false);
        while (i10 < getChildCount()) {
            int i12 = i10 + 1;
            View childAt = getChildAt(i10);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            ArrayList arrayList2 = androidx.work.impl.t.s(childAt).f22337a;
            for (int iW = t7.a.w(arrayList2); -1 < iW; iW--) {
                ((w0.a) arrayList2.get(iW)).onRelease();
            }
            i10 = i12;
        }
        if (!O0 || (uVar = this.f4842o0) == null) {
            return;
        }
        uVar.f5060i.remove(this);
        this.f4842o0 = null;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.A;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((v0) arrayList.get(i10)).getClass();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0082  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z5;
        if (!this.I) {
            this.C = null;
            if (z(motionEvent)) {
                Y();
                setScrollState(0);
                return true;
            }
            z0 z0Var = this.x;
            if (z0Var != null) {
                boolean zD = z0Var.d();
                boolean zE = this.x.e();
                if (this.c0 == null) {
                    this.c0 = VelocityTracker.obtain();
                }
                this.c0.addMovement(motionEvent);
                int actionMasked = motionEvent.getActionMasked();
                int actionIndex = motionEvent.getActionIndex();
                if (actionMasked == 0) {
                    if (this.J) {
                        this.J = false;
                    }
                    this.b0 = motionEvent.getPointerId(0);
                    int x = (int) (motionEvent.getX() + 0.5f);
                    this.f4828f0 = x;
                    this.f4826d0 = x;
                    int y10 = (int) (motionEvent.getY() + 0.5f);
                    this.f4829g0 = y10;
                    this.f4827e0 = y10;
                    EdgeEffect edgeEffect = this.S;
                    if (edgeEffect == null || kotlin.reflect.b0.s(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
                        z = false;
                    } else {
                        kotlin.reflect.b0.y(this.S, 0.0f, 1.0f - (motionEvent.getY() / getHeight()));
                        z = true;
                    }
                    EdgeEffect edgeEffect2 = this.U;
                    boolean z10 = z;
                    if (edgeEffect2 != null) {
                        z10 = z;
                        if (kotlin.reflect.b0.s(edgeEffect2) != 0.0f) {
                            z10 = z;
                            if (!canScrollHorizontally(1)) {
                                kotlin.reflect.b0.y(this.U, 0.0f, motionEvent.getY() / getHeight());
                                z10 = true;
                            }
                        }
                    }
                    EdgeEffect edgeEffect3 = this.T;
                    boolean z11 = z10;
                    if (edgeEffect3 != null) {
                        z11 = z10;
                        if (kotlin.reflect.b0.s(edgeEffect3) != 0.0f) {
                            z11 = z10;
                            if (!canScrollVertically(-1)) {
                                kotlin.reflect.b0.y(this.T, 0.0f, motionEvent.getX() / getWidth());
                                z11 = true;
                            }
                        }
                    }
                    EdgeEffect edgeEffect4 = this.V;
                    boolean z12 = z11;
                    if (edgeEffect4 != null) {
                        z12 = z11;
                        if (kotlin.reflect.b0.s(edgeEffect4) != 0.0f) {
                            z12 = z11;
                            if (!canScrollVertically(1)) {
                                kotlin.reflect.b0.y(this.V, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
                                z12 = true;
                            }
                        }
                    }
                    if (z12 || this.f4825a0 == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        h0(1);
                    }
                    int[] iArr = this.C0;
                    iArr[1] = 0;
                    iArr[0] = 0;
                    int i10 = zD;
                    if (zE) {
                        i10 = (zD ? 1 : 0) | 2;
                    }
                    getScrollingChildHelper().g(i10, 0);
                } else if (actionMasked == 1) {
                    this.c0.clear();
                    h0(0);
                } else if (actionMasked == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.b0);
                    if (iFindPointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.b0 + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    int x10 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
                    int y11 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
                    if (this.f4825a0 != 1) {
                        int i11 = x10 - this.f4826d0;
                        int i12 = y11 - this.f4827e0;
                        if (!zD || Math.abs(i11) <= this.f4830h0) {
                            z5 = false;
                        } else {
                            this.f4828f0 = x10;
                            z5 = true;
                        }
                        if (zE && Math.abs(i12) > this.f4830h0) {
                            this.f4829g0 = y11;
                            z5 = true;
                        }
                        if (z5) {
                            setScrollState(1);
                        }
                    }
                } else if (actionMasked == 3) {
                    Y();
                    setScrollState(0);
                } else if (actionMasked == 5) {
                    this.b0 = motionEvent.getPointerId(actionIndex);
                    int x11 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.f4828f0 = x11;
                    this.f4826d0 = x11;
                    int y12 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.f4829g0 = y12;
                    this.f4827e0 = y12;
                } else if (actionMasked == 6) {
                    Q(motionEvent);
                }
                if (this.f4825a0 == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        int i14 = androidx.core.os.r.f2132a;
        Trace.beginSection("RV OnLayout");
        m();
        Trace.endSection();
        this.F = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        z0 z0Var = this.x;
        if (z0Var == null) {
            l(i10, i11);
            return;
        }
        boolean zQ = z0Var.Q();
        f1 f1Var = this.f4837m;
        boolean z = false;
        l1 l1Var = this.f4846q0;
        if (zQ) {
            int mode = View.MeasureSpec.getMode(i10);
            int mode2 = View.MeasureSpec.getMode(i11);
            this.x.m0(f1Var, l1Var, i10, i11);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.G0 = z;
            if (z || this.f4856w == null) {
                return;
            }
            if (l1Var.f4976d == 1) {
                n();
            }
            this.x.E0(i10, i11);
            l1Var.f4981i = true;
            o();
            this.x.G0(i10, i11);
            if (this.x.J0()) {
                this.x.E0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                l1Var.f4981i = true;
                o();
                this.x.G0(i10, i11);
            }
            this.H0 = getMeasuredWidth();
            this.I0 = getMeasuredHeight();
            return;
        }
        if (this.E) {
            this.x.m0(f1Var, l1Var, i10, i11);
            return;
        }
        if (this.L) {
            f0();
            O();
            S();
            P(true);
            if (l1Var.k) {
                l1Var.f4979g = true;
            } else {
                this.f4841o.c();
                l1Var.f4979g = false;
            }
            this.L = false;
            g0(false);
        } else if (l1Var.k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        l0 l0Var = this.f4856w;
        if (l0Var != null) {
            l1Var.f4977e = l0Var.getItemCount();
        } else {
            l1Var.f4977e = 0;
        }
        f0();
        this.x.m0(f1Var, l1Var, i10, i11);
        g0(false);
        l1Var.f4979g = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i10, Rect rect) {
        if (K()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i10, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof i1)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i1 i1Var = (i1) parcelable;
        this.f4839n = i1Var;
        super.onRestoreInstanceState(i1Var.f22549i);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        i1 i1Var = new i1(super.onSaveInstanceState());
        i1 i1Var2 = this.f4839n;
        if (i1Var2 != null) {
            i1Var.f4946m = i1Var2.f4946m;
            return i1Var;
        }
        z0 z0Var = this.x;
        if (z0Var != null) {
            i1Var.f4946m = z0Var.p0();
            return i1Var;
        }
        i1Var.f4946m = null;
        return i1Var;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i10, int i11, int i12, int i13) {
        super.onSizeChanged(i10, i11, i12, i13);
        if (i10 == i12 && i11 == i13) {
            return;
        }
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:141:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0292 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fd A[PHI: r1
      0x00fd: PHI (r1v57 int) = (r1v41 int), (r1v61 int) binds: [B:51:0x00e6, B:55:0x00f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0116  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 796
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean p(int i10, int i11, int i12, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i10, i11, i12, iArr, iArr2);
    }

    public final void q(int i10, int i11, int i12, int i13, int[] iArr, int i14, int[] iArr2) {
        getScrollingChildHelper().d(i10, i11, i12, i13, iArr, i14, iArr2);
    }

    public final void r(int i10, int i11) {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i10, scrollY - i11);
        c1 c1Var = this.f4848r0;
        if (c1Var != null) {
            c1Var.a(this);
        }
        ArrayList arrayList = this.f4850s0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((c1) this.f4850s0.get(size)).a(this);
            }
        }
        this.Q--;
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z) {
        p1 p1VarG = G(view);
        if (p1VarG != null) {
            if (p1VarG.isTmpDetached()) {
                p1VarG.clearTmpDetachFlag();
            } else if (!p1VarG.shouldIgnore()) {
                StringBuilder sb2 = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb2.append(p1VarG);
                throw new IllegalArgumentException(androidx.fragment.app.a2.k(this, sb2));
            }
        }
        view.clearAnimation();
        p1 p1VarG2 = G(view);
        l0 l0Var = this.f4856w;
        if (l0Var != null && p1VarG2 != null) {
            l0Var.onViewDetachedFromWindow(p1VarG2);
        }
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (!this.x.n0(this, view, view2) && view2 != null) {
            X(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.x.w0(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList arrayList = this.B;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((c) arrayList.get(i10)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.G != 0 || this.I) {
            this.H = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s() {
        if (this.V != null) {
            return;
        }
        ((m1) this.R).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.V = edgeEffect;
        if (this.f4847r) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public final void scrollBy(int i10, int i11) {
        z0 z0Var = this.x;
        if (z0Var == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.I) {
            return;
        }
        boolean zD = z0Var.d();
        boolean zE = this.x.e();
        if (zD || zE) {
            if (!zD) {
                i10 = 0;
            }
            if (!zE) {
                i11 = 0;
            }
            Z(i10, i11, null, 0);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i10, int i11) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!K()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.K |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public void setAccessibilityDelegateCompat(r1 r1Var) {
        this.f4858x0 = r1Var;
        androidx.core.view.b2.h(this, r1Var);
    }

    public void setAdapter(l0 l0Var) {
        setLayoutFrozen(false);
        l0 l0Var2 = this.f4856w;
        g1 g1Var = this.f4835l;
        if (l0Var2 != null) {
            l0Var2.unregisterAdapterDataObserver(g1Var);
            this.f4856w.onDetachedFromRecyclerView(this);
        }
        u0 u0Var = this.W;
        if (u0Var != null) {
            u0Var.e();
        }
        z0 z0Var = this.x;
        f1 f1Var = this.f4837m;
        if (z0Var != null) {
            z0Var.s0(f1Var);
            this.x.t0(f1Var);
        }
        f1Var.f4919a.clear();
        f1Var.f();
        androidx.recyclerview.widget.c cVar = this.f4841o;
        cVar.k(cVar.f4895b);
        cVar.k(cVar.f4896c);
        cVar.f4899f = 0;
        l0 l0Var3 = this.f4856w;
        this.f4856w = l0Var;
        if (l0Var != null) {
            l0Var.registerAdapterDataObserver(g1Var);
            l0Var.onAttachedToRecyclerView(this);
        }
        z0 z0Var2 = this.x;
        if (z0Var2 != null) {
            z0Var2.V(l0Var3, this.f4856w);
        }
        l0 l0Var4 = this.f4856w;
        f1Var.f4919a.clear();
        f1Var.f();
        f1Var.e(l0Var3, true);
        e1 e1VarC = f1Var.c();
        if (l0Var3 != null) {
            e1VarC.f4917b--;
        }
        if (e1VarC.f4917b == 0) {
            SparseArray sparseArray = e1VarC.f4916a;
            for (int i10 = 0; i10 < sparseArray.size(); i10++) {
                d1 d1Var = (d1) sparseArray.valueAt(i10);
                Iterator it = d1Var.f4905a.iterator();
                while (it.hasNext()) {
                    androidx.work.impl.t.d(((p1) it.next()).itemView);
                }
                d1Var.f4905a.clear();
            }
        }
        if (l0Var4 != null) {
            e1VarC.f4917b++;
        }
        f1Var.d();
        this.f4846q0.f4978f = true;
        T(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(a aVar) {
        if (aVar == this.f4860y0) {
            return;
        }
        this.f4860y0 = aVar;
        setChildrenDrawingOrderEnabled(aVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.f4847r) {
            this.V = null;
            this.T = null;
            this.U = null;
            this.S = null;
        }
        this.f4847r = z;
        super.setClipToPadding(z);
        if (this.F) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(p0 p0Var) {
        p0Var.getClass();
        this.R = p0Var;
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    public void setHasFixedSize(boolean z) {
        this.E = z;
    }

    public void setItemAnimator(u0 u0Var) {
        u0 u0Var2 = this.W;
        if (u0Var2 != null) {
            u0Var2.e();
            this.W.f5064a = null;
        }
        this.W = u0Var;
        if (u0Var != null) {
            u0Var.f5064a = this.v0;
        }
    }

    public void setItemViewCacheSize(int i10) {
        f1 f1Var = this.f4837m;
        f1Var.f4923e = i10;
        f1Var.m();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(z0 z0Var) {
        androidx.leanback.widget.w wVar;
        if (z0Var == this.x) {
            return;
        }
        setScrollState(0);
        o1 o1Var = this.f4840n0;
        o1Var.f5009q.removeCallbacks(o1Var);
        o1Var.f5005m.abortAnimation();
        z0 z0Var2 = this.x;
        if (z0Var2 != null && (wVar = z0Var2.f5117e) != null) {
            wVar.e();
        }
        z0 z0Var3 = this.x;
        f1 f1Var = this.f4837m;
        if (z0Var3 != null) {
            u0 u0Var = this.W;
            if (u0Var != null) {
                u0Var.e();
            }
            this.x.s0(f1Var);
            this.x.t0(f1Var);
            f1Var.f4919a.clear();
            f1Var.f();
            if (this.D) {
                z0 z0Var4 = this.x;
                z0Var4.f5119g = false;
                z0Var4.X(this);
            }
            this.x.H0(null);
            this.x = null;
        } else {
            f1Var.f4919a.clear();
            f1Var.f();
        }
        androidx.appcompat.app.i1 i1Var = this.f4843p;
        j0 j0Var = (j0) i1Var.f1059l;
        ((androidx.recyclerview.widget.e) i1Var.f1060m).j();
        ArrayList arrayList = (ArrayList) i1Var.f1061n;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            j0Var.g((View) arrayList.get(size));
            arrayList.remove(size);
        }
        RecyclerView recyclerView = j0Var.f4952a;
        int childCount = recyclerView.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = recyclerView.getChildAt(i10);
            p1 p1VarG = G(childAt);
            l0 l0Var = recyclerView.f4856w;
            if (l0Var != null && p1VarG != null) {
                l0Var.onViewDetachedFromWindow(p1VarG);
            }
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.x = z0Var;
        if (z0Var != null) {
            if (z0Var.f5114b != null) {
                StringBuilder sb2 = new StringBuilder("LayoutManager ");
                sb2.append(z0Var);
                sb2.append(" is already attached to a RecyclerView:");
                throw new IllegalArgumentException(androidx.fragment.app.a2.k(z0Var.f5114b, sb2));
            }
            z0Var.H0(this);
            if (this.D) {
                this.x.f5119g = true;
            }
        }
        f1Var.m();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        androidx.core.view.m0 scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.f2286d) {
            View view = scrollingChildHelper.f2285c;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            androidx.core.view.k1.z(view);
        }
        scrollingChildHelper.f2286d = z;
    }

    public void setOnFlingListener(b1 b1Var) {
    }

    @Deprecated
    public void setOnScrollListener(c1 c1Var) {
        this.f4848r0 = c1Var;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.f4838m0 = z;
    }

    public void setRecycledViewPool(e1 e1Var) {
        f1 f1Var = this.f4837m;
        RecyclerView recyclerView = f1Var.f4926h;
        f1Var.e(recyclerView.f4856w, false);
        if (f1Var.f4925g != null) {
            r2.f4917b--;
        }
        f1Var.f4925g = e1Var;
        if (e1Var != null && recyclerView.getAdapter() != null) {
            f1Var.f4925g.f4917b++;
        }
        f1Var.d();
    }

    @Deprecated
    public void setRecyclerListener(e eVar) {
        this.f4859y = eVar;
    }

    public void setScrollState(int i10) {
        androidx.leanback.widget.w wVar;
        if (i10 == this.f4825a0) {
            return;
        }
        this.f4825a0 = i10;
        if (i10 != 2) {
            o1 o1Var = this.f4840n0;
            o1Var.f5009q.removeCallbacks(o1Var);
            o1Var.f5005m.abortAnimation();
            z0 z0Var = this.x;
            if (z0Var != null && (wVar = z0Var.f5117e) != null) {
                wVar.e();
            }
        }
        z0 z0Var2 = this.x;
        if (z0Var2 != null) {
            z0Var2.q0(i10);
        }
        ArrayList arrayList = this.f4850s0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((c1) this.f4850s0.get(size)).getClass();
            }
        }
    }

    public void setScrollingTouchSlop(int i10) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i10 != 0) {
            if (i10 == 1) {
                this.f4830h0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i10 + "; using default value");
        }
        this.f4830h0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(n1 n1Var) {
        this.f4837m.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i10) {
        return getScrollingChildHelper().g(i10, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().h(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        androidx.leanback.widget.w wVar;
        if (z != this.I) {
            f("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.I = false;
                if (this.H && this.x != null && this.f4856w != null) {
                    requestLayout();
                }
                this.H = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.I = true;
            this.J = true;
            setScrollState(0);
            o1 o1Var = this.f4840n0;
            o1Var.f5009q.removeCallbacks(o1Var);
            o1Var.f5005m.abortAnimation();
            z0 z0Var = this.x;
            if (z0Var == null || (wVar = z0Var.f5117e) == null) {
                return;
            }
            wVar.e();
        }
    }

    public final void t() {
        if (this.S != null) {
            return;
        }
        ((m1) this.R).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.S = edgeEffect;
        if (this.f4847r) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void u() {
        if (this.U != null) {
            return;
        }
        ((m1) this.R).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.U = edgeEffect;
        if (this.f4847r) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void v() {
        if (this.T != null) {
            return;
        }
        ((m1) this.R).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.T = edgeEffect;
        if (this.f4847r) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String w() {
        return " " + super.toString() + ", adapter:" + this.f4856w + ", layout:" + this.x + ", context:" + getContext();
    }

    public final void x(l1 l1Var) {
        if (getScrollState() != 2) {
            l1Var.f4986o = 0;
            l1Var.f4987p = 0;
        } else {
            OverScroller overScroller = this.f4840n0.f5005m;
            l1Var.f4986o = overScroller.getFinalX() - overScroller.getCurrX();
            l1Var.f4987p = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public final View y(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public final boolean z(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        ArrayList arrayList = this.B;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            c cVar = (c) arrayList.get(i10);
            if (cVar.b(motionEvent) && action != 3) {
                this.C = cVar;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerView(Context context, AttributeSet attributeSet, int i10) {
        float fA;
        TypedArray typedArray;
        char c10;
        char c11;
        char c12;
        AttributeSet attributeSet2;
        int i11;
        int i12;
        Constructor constructor;
        super(context, attributeSet, i10);
        this.f4835l = new g1(this);
        this.f4837m = new f1(this);
        this.f4845q = new c2(3);
        this.f4849s = new h0(this, 0);
        this.f4851t = new Rect();
        this.f4853u = new Rect();
        this.f4855v = new RectF();
        this.z = new ArrayList();
        this.A = new ArrayList();
        this.B = new ArrayList();
        this.G = 0;
        this.N = false;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.R = R0;
        m mVar = new m();
        Object[] objArr = null;
        mVar.f5064a = null;
        mVar.f5065b = new ArrayList();
        mVar.f5066c = 120L;
        mVar.f5067d = 120L;
        mVar.f5068e = 250L;
        mVar.f5069f = 250L;
        int i13 = 1;
        mVar.f4989g = true;
        mVar.f4990h = new ArrayList();
        mVar.f4991i = new ArrayList();
        mVar.f4992j = new ArrayList();
        mVar.k = new ArrayList();
        mVar.f4993l = new ArrayList();
        mVar.f4994m = new ArrayList();
        mVar.f4995n = new ArrayList();
        mVar.f4996o = new ArrayList();
        mVar.f4997p = new ArrayList();
        mVar.f4998q = new ArrayList();
        mVar.f4999r = new ArrayList();
        this.W = mVar;
        this.f4825a0 = 0;
        this.b0 = -1;
        this.f4834k0 = Float.MIN_VALUE;
        this.f4836l0 = Float.MIN_VALUE;
        this.f4838m0 = true;
        this.f4840n0 = new o1(this);
        this.f4844p0 = O0 ? new s() : null;
        l1 l1Var = new l1();
        l1Var.f4973a = -1;
        l1Var.f4974b = 0;
        l1Var.f4975c = 0;
        l1Var.f4976d = 1;
        l1Var.f4977e = 0;
        l1Var.f4978f = false;
        l1Var.f4979g = false;
        l1Var.f4980h = false;
        l1Var.f4981i = false;
        l1Var.f4982j = false;
        l1Var.k = false;
        this.f4846q0 = l1Var;
        this.f4852t0 = false;
        this.f4854u0 = false;
        j0 j0Var = new j0(this);
        this.v0 = j0Var;
        this.f4857w0 = false;
        this.f4861z0 = new int[2];
        this.B0 = new int[2];
        this.C0 = new int[2];
        this.D0 = new int[2];
        this.E0 = new ArrayList();
        this.F0 = new h0(this, i13);
        this.H0 = 0;
        this.I0 = 0;
        this.J0 = new j0(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f4830h0 = viewConfiguration.getScaledTouchSlop();
        int i14 = Build.VERSION.SDK_INT;
        if (i14 >= 26) {
            Method method = androidx.core.view.f2.f2233a;
            fA = androidx.core.view.c2.a(viewConfiguration);
        } else {
            fA = androidx.core.view.f2.a(viewConfiguration, context);
        }
        this.f4834k0 = fA;
        this.f4836l0 = i14 >= 26 ? androidx.core.view.c2.b(viewConfiguration) : androidx.core.view.f2.a(viewConfiguration, context);
        this.f4832i0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f4833j0 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f4831i = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.W.f5064a = j0Var;
        this.f4841o = new androidx.recyclerview.widget.c(new j0(this));
        this.f4843p = new androidx.appcompat.app.i1(new j0(this));
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        if ((i14 >= 26 ? androidx.core.view.m1.c(this) : 0) == 0 && i14 >= 26) {
            androidx.core.view.m1.m(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.M = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new r1(this));
        int[] iArr = w1.a.f22338a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i10, 0);
        androidx.core.view.b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i10);
        String string = typedArrayObtainStyledAttributes.getString(8);
        if (typedArrayObtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
        }
        this.f4847r = typedArrayObtainStyledAttributes.getBoolean(1, true);
        if (typedArrayObtainStyledAttributes.getBoolean(3, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(6);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(5);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                throw new IllegalArgumentException(androidx.fragment.app.a2.k(this, new StringBuilder("Trying to set fast scroller without both required drawables.")));
            }
            Resources resources = getContext().getResources();
            c10 = 3;
            c12 = 2;
            i11 = i10;
            typedArray = typedArrayObtainStyledAttributes;
            c11 = 1;
            i12 = 4;
            attributeSet2 = attributeSet;
            new r(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.arvio.tv.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.arvio.tv.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.arvio.tv.R.dimen.fastscroll_margin));
        } else {
            typedArray = typedArrayObtainStyledAttributes;
            c10 = 3;
            c11 = 1;
            c12 = 2;
            attributeSet2 = attributeSet;
            i11 = i10;
            i12 = 4;
        }
        typedArray.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(".")) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                String str = strTrim;
                try {
                    Class<? extends U> clsAsSubclass = Class.forName(str, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(z0.class);
                    try {
                        Constructor constructor2 = clsAsSubclass.getConstructor(P0);
                        Object[] objArr2 = new Object[i12];
                        objArr2[0] = context;
                        objArr2[c11] = attributeSet2;
                        objArr2[c12] = Integer.valueOf(i11);
                        objArr2[c10] = 0;
                        objArr = objArr2;
                        constructor = constructor2;
                    } catch (NoSuchMethodException e5) {
                        try {
                            constructor = clsAsSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e6) {
                            e6.initCause(e5);
                            throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Error creating LayoutManager " + str, e6);
                        }
                    }
                    constructor.setAccessible(c11);
                    setLayoutManager((z0) constructor.newInstance(objArr));
                } catch (ClassCastException e10) {
                    throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Class is not a LayoutManager " + str, e10);
                } catch (ClassNotFoundException e11) {
                    throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Unable to find LayoutManager " + str, e11);
                } catch (IllegalAccessException e12) {
                    throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Cannot access non-public constructor " + str, e12);
                } catch (InstantiationException e13) {
                    throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e13);
                } catch (InvocationTargetException e14) {
                    throw new IllegalStateException(attributeSet2.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e14);
                }
            }
        }
        int[] iArr2 = K0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet2, iArr2, i11, 0);
        androidx.core.view.b2.g(this, context, iArr2, attributeSet2, typedArrayObtainStyledAttributes2, i11);
        boolean z = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z);
        setTag(com.arvio.tv.R.id.is_pooling_container_tag, Boolean.TRUE);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        z0 z0Var = this.x;
        if (z0Var != null) {
            return z0Var.u(layoutParams);
        }
        throw new IllegalStateException(androidx.fragment.app.a2.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }
}
