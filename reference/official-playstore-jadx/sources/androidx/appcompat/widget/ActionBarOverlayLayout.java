package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.view.menu.d0;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements x1, androidx.core.view.p0, androidx.core.view.n0, androidx.core.view.o0 {
    public static final int[] L = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    public androidx.core.view.q3 A;
    public androidx.core.view.q3 B;
    public androidx.core.view.q3 C;
    public androidx.core.view.q3 D;
    public a E;
    public OverScroller F;
    public ViewPropertyAnimator G;
    public final d H;
    public final e I;
    public final e J;
    public final androidx.core.view.q0 K;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1424i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1425l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ContentFrameLayout f1426m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ActionBarContainer f1427n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public y1 f1428o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f1429p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1430q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1431r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1432s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1433t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1434u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1435v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1436w;
    public final Rect x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f1437y;
    public final Rect z;

    public interface a {
        void a();

        void b(boolean z);

        void c();

        void d();

        void onWindowVisibilityChanged(int i10);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1425l = 0;
        this.x = new Rect();
        this.f1437y = new Rect();
        this.z = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        androidx.core.view.q3 q3Var = androidx.core.view.q3.f2299b;
        this.A = q3Var;
        this.B = q3Var;
        this.C = q3Var;
        this.D = q3Var;
        this.H = new d(this);
        this.I = new e(this, 0);
        this.J = new e(this, 1);
        l(context);
        this.K = new androidx.core.view.q0();
    }

    public static boolean j(View view, Rect rect, boolean z) {
        boolean z5;
        f fVar = (f) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) fVar).leftMargin;
        int i11 = rect.left;
        if (i10 != i11) {
            ((ViewGroup.MarginLayoutParams) fVar).leftMargin = i11;
            z5 = true;
        } else {
            z5 = false;
        }
        int i12 = ((ViewGroup.MarginLayoutParams) fVar).topMargin;
        int i13 = rect.top;
        if (i12 != i13) {
            ((ViewGroup.MarginLayoutParams) fVar).topMargin = i13;
            z5 = true;
        }
        int i14 = ((ViewGroup.MarginLayoutParams) fVar).rightMargin;
        int i15 = rect.right;
        if (i14 != i15) {
            ((ViewGroup.MarginLayoutParams) fVar).rightMargin = i15;
            z5 = true;
        }
        if (z) {
            int i16 = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
            int i17 = rect.bottom;
            if (i16 != i17) {
                ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = i17;
                return true;
            }
        }
        return z5;
    }

    @Override // androidx.appcompat.widget.x1
    public final boolean a() {
        m();
        return this.f1428o.a();
    }

    @Override // androidx.appcompat.widget.x1
    public final boolean b() {
        m();
        return this.f1428o.b();
    }

    @Override // androidx.appcompat.widget.x1
    public final boolean c() {
        m();
        return this.f1428o.c();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof f;
    }

    @Override // androidx.appcompat.widget.x1
    public final void d(Menu menu, d0.a aVar) {
        m();
        this.f1428o.d(menu, aVar);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f1429p == null || this.f1430q) {
            return;
        }
        if (this.f1427n.getVisibility() == 0) {
            translationY = (int) (this.f1427n.getTranslationY() + this.f1427n.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.f1429p.setBounds(0, translationY, getWidth(), this.f1429p.getIntrinsicHeight() + translationY);
        this.f1429p.draw(canvas);
    }

    @Override // androidx.appcompat.widget.x1
    public final boolean e() {
        m();
        return this.f1428o.e();
    }

    @Override // androidx.appcompat.widget.x1
    public final void f() {
        m();
        this.f1428o.f();
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.x1
    public final boolean g() {
        m();
        return this.f1428o.g();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new f(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1427n;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        androidx.core.view.q0 q0Var = this.K;
        return q0Var.f2298b | q0Var.f2297a;
    }

    public CharSequence getTitle() {
        m();
        return this.f1428o.getTitle();
    }

    @Override // androidx.appcompat.widget.x1
    public final void h(int i10) {
        m();
        if (i10 == 2) {
            this.f1428o.l();
        } else if (i10 == 5) {
            this.f1428o.t();
        } else {
            if (i10 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.x1
    public final void i() {
        m();
        this.f1428o.n();
    }

    public final void k() {
        removeCallbacks(this.I);
        removeCallbacks(this.J);
        ViewPropertyAnimator viewPropertyAnimator = this.G;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void l(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(L);
        this.f1424i = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f1429p = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f1430q = context.getApplicationInfo().targetSdkVersion < 19;
        this.F = new OverScroller(context);
    }

    public final void m() {
        y1 wrapper;
        if (this.f1426m == null) {
            this.f1426m = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f1427n = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof y1) {
                wrapper = (y1) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f1428o = wrapper;
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        m();
        androidx.core.view.q3 q3VarC = androidx.core.view.q3.c(this, windowInsets);
        androidx.core.view.n3 n3Var = q3VarC.f2300a;
        boolean zJ = j(this.f1427n, new Rect(n3Var.k().f2086a, n3Var.k().f2087b, n3Var.k().f2088c, n3Var.k().f2089d), false);
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        Rect rect = this.x;
        androidx.core.view.k1.b(this, q3VarC, rect);
        androidx.core.view.q3 q3VarM = n3Var.m(rect.left, rect.top, rect.right, rect.bottom);
        this.A = q3VarM;
        boolean z = true;
        if (!this.B.equals(q3VarM)) {
            this.B = this.A;
            zJ = true;
        }
        Rect rect2 = this.f1437y;
        if (rect2.equals(rect)) {
            z = zJ;
        } else {
            rect2.set(rect);
        }
        if (z) {
            requestLayout();
        }
        return n3Var.a().f2300a.c().f2300a.b().b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        l(getContext());
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        androidx.core.view.i1.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i15 = ((ViewGroup.MarginLayoutParams) fVar).leftMargin + paddingLeft;
                int i16 = ((ViewGroup.MarginLayoutParams) fVar).topMargin + paddingTop;
                childAt.layout(i15, i16, measuredWidth + i15, measuredHeight + i16);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        int measuredHeight;
        m();
        measureChildWithMargins(this.f1427n, i10, 0, i11, 0);
        f fVar = (f) this.f1427n.getLayoutParams();
        int iMax = Math.max(0, this.f1427n.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin + ((ViewGroup.MarginLayoutParams) fVar).rightMargin);
        int iMax2 = Math.max(0, this.f1427n.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar).topMargin + ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1427n.getMeasuredState());
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        boolean z = (getWindowSystemUiVisibility() & 256) != 0;
        if (z) {
            measuredHeight = this.f1424i;
            if (this.f1432s && this.f1427n.getTabContainer() != null) {
                measuredHeight += this.f1424i;
            }
        } else {
            measuredHeight = this.f1427n.getVisibility() != 8 ? this.f1427n.getMeasuredHeight() : 0;
        }
        Rect rect = this.x;
        Rect rect2 = this.z;
        rect2.set(rect);
        androidx.core.view.q3 q3Var = this.A;
        this.C = q3Var;
        if (this.f1431r || z) {
            androidx.core.graphics.c cVarB = androidx.core.graphics.c.b(q3Var.f2300a.k().f2086a, this.C.f2300a.k().f2087b + measuredHeight, this.C.f2300a.k().f2088c, this.C.f2300a.k().f2089d);
            androidx.core.view.q3 q3Var2 = this.C;
            int i12 = Build.VERSION.SDK_INT;
            androidx.core.view.h3 g3Var = i12 >= 30 ? new androidx.core.view.g3(q3Var2) : i12 >= 29 ? new androidx.core.view.f3(q3Var2) : new androidx.core.view.e3(q3Var2);
            g3Var.g(cVarB);
            this.C = g3Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.C = q3Var.f2300a.m(0, measuredHeight, 0, 0);
        }
        j(this.f1426m, rect2, true);
        if (!this.D.equals(this.C)) {
            androidx.core.view.q3 q3Var3 = this.C;
            this.D = q3Var3;
            ContentFrameLayout contentFrameLayout = this.f1426m;
            WindowInsets windowInsetsB = q3Var3.b();
            if (windowInsetsB != null) {
                WindowInsets windowInsetsA = androidx.core.view.i1.a(contentFrameLayout, windowInsetsB);
                if (!windowInsetsA.equals(windowInsetsB)) {
                    androidx.core.view.q3.c(contentFrameLayout, windowInsetsA);
                }
            }
        }
        measureChildWithMargins(this.f1426m, i10, 0, i11, 0);
        f fVar2 = (f) this.f1426m.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f1426m.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar2).leftMargin + ((ViewGroup.MarginLayoutParams) fVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f1426m.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar2).topMargin + ((ViewGroup.MarginLayoutParams) fVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1426m.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i10, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i11, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f10, float f11, boolean z) {
        if (!this.f1433t || !z) {
            return false;
        }
        this.F.fling(0, 0, 0, (int) f11, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.F.getFinalY() > this.f1427n.getHeight()) {
            k();
            this.J.run();
        } else {
            k();
            this.I.run();
        }
        this.f1434u = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f10, float f11) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i10, int i11, int[] iArr) {
    }

    @Override // androidx.core.view.o0
    public final void onNestedScroll(View view, int i10, int i11, int i12, int i13, int i14, int[] iArr) {
        onNestedScroll(view, i10, i11, i12, i13, i14);
    }

    @Override // androidx.core.view.n0
    public final void onNestedScrollAccepted(View view, View view2, int i10, int i11) {
        if (i11 == 0) {
            onNestedScrollAccepted(view, view2, i10);
        }
    }

    @Override // androidx.core.view.n0
    public final boolean onStartNestedScroll(View view, View view2, int i10, int i11) {
        return i11 == 0 && onStartNestedScroll(view, view2, i10);
    }

    @Override // androidx.core.view.n0
    public final void onStopNestedScroll(View view, int i10) {
        if (i10 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i10) {
        super.onWindowSystemUiVisibilityChanged(i10);
        m();
        int i11 = this.f1436w ^ i10;
        this.f1436w = i10;
        boolean z = (i10 & 4) == 0;
        boolean z5 = (i10 & 256) != 0;
        a aVar = this.E;
        if (aVar != null) {
            aVar.b(!z5);
            if (z || !z5) {
                this.E.a();
            } else {
                this.E.c();
            }
        }
        if ((i11 & 256) == 0 || this.E == null) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        androidx.core.view.i1.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i10) {
        super.onWindowVisibilityChanged(i10);
        this.f1425l = i10;
        a aVar = this.E;
        if (aVar != null) {
            aVar.onWindowVisibilityChanged(i10);
        }
    }

    public void setActionBarHideOffset(int i10) {
        k();
        this.f1427n.setTranslationY(-Math.max(0, Math.min(i10, this.f1427n.getHeight())));
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.E = aVar;
        if (getWindowToken() != null) {
            this.E.onWindowVisibilityChanged(this.f1425l);
            int i10 = this.f1436w;
            if (i10 != 0) {
                onWindowSystemUiVisibilityChanged(i10);
                WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                androidx.core.view.i1.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.f1432s = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.f1433t) {
            this.f1433t = z;
            if (z) {
                return;
            }
            k();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i10) {
        m();
        this.f1428o.setIcon(i10);
    }

    public void setLogo(int i10) {
        m();
        this.f1428o.p(i10);
    }

    public void setOverlayMode(boolean z) {
        this.f1431r = z;
        this.f1430q = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i10) {
    }

    @Override // androidx.appcompat.widget.x1
    public void setWindowCallback(Window.Callback callback) {
        m();
        this.f1428o.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.x1
    public void setWindowTitle(CharSequence charSequence) {
        m();
        this.f1428o.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // androidx.core.view.n0
    public final void onNestedPreScroll(View view, int i10, int i11, int[] iArr, int i12) {
    }

    @Override // androidx.core.view.n0
    public final void onNestedScroll(View view, int i10, int i11, int i12, int i13, int i14) {
        if (i14 == 0) {
            onNestedScroll(view, i10, i11, i12, i13);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i10) {
        this.K.f2297a = i10;
        this.f1435v = getActionBarHideOffset();
        k();
        a aVar = this.E;
        if (aVar != null) {
            aVar.d();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i10) {
        if ((i10 & 2) == 0 || this.f1427n.getVisibility() != 0) {
            return false;
        }
        return this.f1433t;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f1433t || this.f1434u) {
            return;
        }
        if (this.f1435v <= this.f1427n.getHeight()) {
            k();
            postDelayed(this.I, 600L);
        } else {
            k();
            postDelayed(this.J, 600L);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new f(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i10, int i11, int i12, int i13) {
        int i14 = this.f1435v + i11;
        this.f1435v = i14;
        setActionBarHideOffset(i14);
    }

    public void setIcon(Drawable drawable) {
        m();
        this.f1428o.setIcon(drawable);
    }
}
