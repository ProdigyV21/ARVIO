package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup implements androidx.core.view.c0 {
    public int A;
    public int B;
    public int C;
    public l3 D;
    public int E;
    public int F;
    public final int G;
    public CharSequence H;
    public CharSequence I;
    public ColorStateList J;
    public ColorStateList K;
    public boolean L;
    public boolean M;
    public final ArrayList N;
    public final ArrayList O;
    public final int[] P;
    public final androidx.core.view.f0 Q;
    public ArrayList R;
    public a S;
    public final j4 T;
    public q4 U;
    public m V;
    public l4 W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public androidx.appcompat.app.d1 f1475a0;
    public androidx.appcompat.app.e1 b0;
    public boolean c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public OnBackInvokedCallback f1476d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public OnBackInvokedDispatcher f1477e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f1478f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final f2 f1479g0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ActionMenuView f1480i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o1 f1481l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o1 f1482m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e0 f1483n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public g0 f1484o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Drawable f1485p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final CharSequence f1486q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public e0 f1487r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View f1488s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Context f1489t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1490u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1491v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1492w;
    public final int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f1493y;
    public int z;

    public interface a {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.toolbarStyle);
        this.G = 8388627;
        this.N = new ArrayList();
        this.O = new ArrayList();
        this.P = new int[2];
        this.Q = new androidx.core.view.f0(new i4(this, 1));
        this.R = new ArrayList();
        this.T = new j4(this);
        this.f1479g0 = new f2(this, 1);
        Context context2 = getContext();
        int[] iArr = j.a.x;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context2, attributeSet, iArr, R.attr.toolbarStyle);
        androidx.core.view.b2.g(this, context, iArr, attributeSet, (TypedArray) i1VarZ.f1060m, R.attr.toolbarStyle);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        this.f1491v = typedArray.getResourceId(28, 0);
        this.f1492w = typedArray.getResourceId(19, 0);
        this.G = typedArray.getInteger(0, 8388627);
        this.x = typedArray.getInteger(2, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(22, 0);
        dimensionPixelOffset = typedArray.hasValue(27) ? typedArray.getDimensionPixelOffset(27, dimensionPixelOffset) : dimensionPixelOffset;
        this.C = dimensionPixelOffset;
        this.B = dimensionPixelOffset;
        this.A = dimensionPixelOffset;
        this.z = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.z = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.A = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(26, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.B = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(23, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.C = dimensionPixelOffset5;
        }
        this.f1493y = typedArray.getDimensionPixelSize(13, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(9, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(5, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(7, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(8, 0);
        d();
        l3 l3Var = this.D;
        l3Var.f1655h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            l3Var.f1652e = dimensionPixelSize;
            l3Var.f1648a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            l3Var.f1653f = dimensionPixelSize2;
            l3Var.f1649b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            l3Var.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.E = typedArray.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        this.F = typedArray.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        this.f1485p = i1VarZ.T(4);
        this.f1486q = typedArray.getText(3);
        CharSequence text = typedArray.getText(21);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(18);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.f1489t = getContext();
        setPopupTheme(typedArray.getResourceId(17, 0));
        Drawable drawableT = i1VarZ.T(16);
        if (drawableT != null) {
            setNavigationIcon(drawableT);
        }
        CharSequence text3 = typedArray.getText(15);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawableT2 = i1VarZ.T(11);
        if (drawableT2 != null) {
            setLogo(drawableT2);
        }
        CharSequence text4 = typedArray.getText(12);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(29)) {
            setTitleTextColor(i1VarZ.S(29));
        }
        if (typedArray.hasValue(20)) {
            setSubtitleTextColor(i1VarZ.S(20));
        }
        if (typedArray.hasValue(14)) {
            getMenuInflater().inflate(typedArray.getResourceId(14, 0), getMenu());
        }
        i1VarZ.b0();
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i10 = 0; i10 < menu.size(); i10++) {
            arrayList.add(menu.getItem(i10));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new n.j(getContext());
    }

    public static m4 h() {
        m4 m4Var = new m4(-2, -2);
        m4Var.f1667b = 0;
        m4Var.f1666a = 8388627;
        return m4Var;
    }

    public static m4 i(ViewGroup.LayoutParams layoutParams) {
        boolean z = layoutParams instanceof m4;
        if (z) {
            m4 m4Var = (m4) layoutParams;
            m4 m4Var2 = new m4(m4Var);
            m4Var2.f1667b = 0;
            m4Var2.f1667b = m4Var.f1667b;
            return m4Var2;
        }
        if (z) {
            m4 m4Var3 = new m4((m4) layoutParams);
            m4Var3.f1667b = 0;
            return m4Var3;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            m4 m4Var4 = new m4(layoutParams);
            m4Var4.f1667b = 0;
            return m4Var4;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        m4 m4Var5 = new m4(marginLayoutParams);
        m4Var5.f1667b = 0;
        ((ViewGroup.MarginLayoutParams) m4Var5).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) m4Var5).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) m4Var5).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) m4Var5).bottomMargin = marginLayoutParams.bottomMargin;
        return m4Var5;
    }

    public static int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(int i10, ArrayList arrayList) {
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        boolean z = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i10, getLayoutDirection());
        arrayList.clear();
        if (!z) {
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                m4 m4Var = (m4) childAt.getLayoutParams();
                if (m4Var.f1667b == 0 && s(childAt)) {
                    int i12 = m4Var.f1666a;
                    WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i12, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i13 = childCount - 1; i13 >= 0; i13--) {
            View childAt2 = getChildAt(i13);
            m4 m4Var2 = (m4) childAt2.getLayoutParams();
            if (m4Var2.f1667b == 0 && s(childAt2)) {
                int i14 = m4Var2.f1666a;
                WeakHashMap weakHashMap3 = androidx.core.view.b2.f2200a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i14, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    @Override // androidx.core.view.c0
    public final void addMenuProvider(androidx.core.view.i0 i0Var) {
        androidx.core.view.f0 f0Var = this.Q;
        f0Var.f2230b.add(i0Var);
        f0Var.f2229a.run();
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        m4 m4VarH = layoutParams == null ? h() : !checkLayoutParams(layoutParams) ? i(layoutParams) : (m4) layoutParams;
        m4VarH.f1667b = 1;
        if (!z || this.f1488s == null) {
            addView(view, m4VarH);
        } else {
            view.setLayoutParams(m4VarH);
            this.O.add(view);
        }
    }

    public final void c() {
        if (this.f1487r == null) {
            e0 e0Var = new e0(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.f1487r = e0Var;
            e0Var.setImageDrawable(this.f1485p);
            this.f1487r.setContentDescription(this.f1486q);
            m4 m4VarH = h();
            m4VarH.f1666a = (this.x & 112) | 8388611;
            m4VarH.f1667b = 2;
            this.f1487r.setLayoutParams(m4VarH);
            this.f1487r.setOnClickListener(new c(this, 1));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof m4);
    }

    public final void d() {
        if (this.D == null) {
            l3 l3Var = new l3();
            l3Var.f1648a = 0;
            l3Var.f1649b = 0;
            l3Var.f1650c = Integer.MIN_VALUE;
            l3Var.f1651d = Integer.MIN_VALUE;
            l3Var.f1652e = 0;
            l3Var.f1653f = 0;
            l3Var.f1654g = false;
            l3Var.f1655h = false;
            this.D = l3Var;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.f1480i;
        if (actionMenuView.z == null) {
            androidx.appcompat.view.menu.q qVar = (androidx.appcompat.view.menu.q) actionMenuView.getMenu();
            if (this.W == null) {
                this.W = new l4(this);
            }
            this.f1480i.setExpandedActionViewsExclusive(true);
            qVar.b(this.W, this.f1489t);
            u();
        }
    }

    public final void f() {
        if (this.f1480i == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.f1480i = actionMenuView;
            actionMenuView.setPopupTheme(this.f1490u);
            this.f1480i.setOnMenuItemClickListener(this.T);
            ActionMenuView actionMenuView2 = this.f1480i;
            androidx.appcompat.app.d1 d1Var = this.f1475a0;
            m1 m1Var = new m1(this);
            actionMenuView2.E = d1Var;
            actionMenuView2.F = m1Var;
            m4 m4VarH = h();
            m4VarH.f1666a = (this.x & 112) | 8388613;
            this.f1480i.setLayoutParams(m4VarH);
            b(this.f1480i, false);
        }
    }

    public final void g() {
        if (this.f1483n == null) {
            this.f1483n = new e0(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            m4 m4VarH = h();
            m4VarH.f1666a = (this.x & 112) | 8388611;
            this.f1483n.setLayoutParams(m4VarH);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return i(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        e0 e0Var = this.f1487r;
        if (e0Var != null) {
            return e0Var.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        e0 e0Var = this.f1487r;
        if (e0Var != null) {
            return e0Var.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        l3 l3Var = this.D;
        if (l3Var != null) {
            return l3Var.f1654g ? l3Var.f1648a : l3Var.f1649b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i10 = this.F;
        return i10 != Integer.MIN_VALUE ? i10 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        l3 l3Var = this.D;
        if (l3Var != null) {
            return l3Var.f1648a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        l3 l3Var = this.D;
        if (l3Var != null) {
            return l3Var.f1649b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        l3 l3Var = this.D;
        if (l3Var != null) {
            return l3Var.f1654g ? l3Var.f1649b : l3Var.f1648a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i10 = this.E;
        return i10 != Integer.MIN_VALUE ? i10 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.q qVar;
        ActionMenuView actionMenuView = this.f1480i;
        return (actionMenuView == null || (qVar = actionMenuView.z) == null || !qVar.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.F, 0));
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.E, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        g0 g0Var = this.f1484o;
        if (g0Var != null) {
            return g0Var.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        g0 g0Var = this.f1484o;
        if (g0Var != null) {
            return g0Var.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.f1480i.getMenu();
    }

    public View getNavButtonView() {
        return this.f1483n;
    }

    public CharSequence getNavigationContentDescription() {
        e0 e0Var = this.f1483n;
        if (e0Var != null) {
            return e0Var.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        e0 e0Var = this.f1483n;
        if (e0Var != null) {
            return e0Var.getDrawable();
        }
        return null;
    }

    public m getOuterActionMenuPresenter() {
        return this.V;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.f1480i.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f1489t;
    }

    public int getPopupTheme() {
        return this.f1490u;
    }

    public CharSequence getSubtitle() {
        return this.I;
    }

    public final TextView getSubtitleTextView() {
        return this.f1482m;
    }

    public CharSequence getTitle() {
        return this.H;
    }

    public int getTitleMarginBottom() {
        return this.C;
    }

    public int getTitleMarginEnd() {
        return this.A;
    }

    public int getTitleMarginStart() {
        return this.z;
    }

    public int getTitleMarginTop() {
        return this.B;
    }

    public final TextView getTitleTextView() {
        return this.f1481l;
    }

    public y1 getWrapper() {
        if (this.U == null) {
            this.U = new q4(this, true);
        }
        return this.U;
    }

    public final int j(int i10, View view) {
        m4 m4Var = (m4) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i11 = i10 > 0 ? (measuredHeight - i10) / 2 : 0;
        int i12 = m4Var.f1666a & 112;
        if (i12 != 16 && i12 != 48 && i12 != 80) {
            i12 = this.G & 112;
        }
        if (i12 == 48) {
            return getPaddingTop() - i11;
        }
        if (i12 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) m4Var).bottomMargin) - i11;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i13 = ((ViewGroup.MarginLayoutParams) m4Var).topMargin;
        if (iMax < i13) {
            iMax = i13;
        } else {
            int i14 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i15 = ((ViewGroup.MarginLayoutParams) m4Var).bottomMargin;
            if (i14 < i15) {
                iMax = Math.max(0, iMax - (i15 - i14));
            }
        }
        return paddingTop + iMax;
    }

    public final void m() {
        Iterator it = this.R.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        MenuInflater menuInflater = getMenuInflater();
        Iterator it2 = this.Q.f2230b.iterator();
        while (it2.hasNext()) {
            ((androidx.core.view.i0) it2.next()).d(menu, menuInflater);
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.R = currentMenuItems2;
    }

    public final boolean n(View view) {
        return view.getParent() == this || this.O.contains(view);
    }

    public final int o(View view, int i10, int i11, int[] iArr) {
        m4 m4Var = (m4) view.getLayoutParams();
        int i12 = ((ViewGroup.MarginLayoutParams) m4Var).leftMargin - iArr[0];
        int iMax = Math.max(0, i12) + i10;
        iArr[0] = Math.max(0, -i12);
        int iJ = j(i11, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iJ, iMax + measuredWidth, view.getMeasuredHeight() + iJ);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) m4Var).rightMargin + iMax;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1479g0);
        u();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.M = false;
        }
        if (!this.M) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.M = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.M = false;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x028f A[LOOP:0: B:102:0x028d->B:103:0x028f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02a7 A[LOOP:1: B:105:0x02a5->B:106:0x02a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02c7 A[LOOP:2: B:108:0x02c5->B:109:0x02c7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x031a A[LOOP:3: B:117:0x0318->B:118:0x031a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0218  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 811
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        int iK;
        int iMax;
        int iCombineMeasuredStates;
        int iK2;
        int iL;
        int iCombineMeasuredStates2;
        int iMax2;
        boolean zA = w4.a(this);
        int i12 = !zA ? 1 : 0;
        int i13 = 0;
        if (s(this.f1483n)) {
            r(this.f1483n, i10, 0, i11, this.f1493y);
            iK = k(this.f1483n) + this.f1483n.getMeasuredWidth();
            iMax = Math.max(0, l(this.f1483n) + this.f1483n.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1483n.getMeasuredState());
        } else {
            iK = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (s(this.f1487r)) {
            r(this.f1487r, i10, 0, i11, this.f1493y);
            iK = k(this.f1487r) + this.f1487r.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.f1487r) + this.f1487r.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1487r.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iK);
        int iMax4 = Math.max(0, currentContentInsetStart - iK);
        int[] iArr = this.P;
        iArr[zA ? 1 : 0] = iMax4;
        if (s(this.f1480i)) {
            r(this.f1480i, i10, iMax3, i11, this.f1493y);
            iK2 = k(this.f1480i) + this.f1480i.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.f1480i) + this.f1480i.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1480i.getMeasuredState());
        } else {
            iK2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, iK2);
        iArr[i12] = Math.max(0, currentContentInsetEnd - iK2);
        if (s(this.f1488s)) {
            iMax5 += q(this.f1488s, i10, iMax5, i11, 0, iArr);
            iMax = Math.max(iMax, l(this.f1488s) + this.f1488s.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1488s.getMeasuredState());
        }
        if (s(this.f1484o)) {
            iMax5 += q(this.f1484o, i10, iMax5, i11, 0, iArr);
            iMax = Math.max(iMax, l(this.f1484o) + this.f1484o.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1484o.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (((m4) childAt.getLayoutParams()).f1667b == 0 && s(childAt)) {
                iMax5 += q(childAt, i10, iMax5, i11, 0, iArr);
                int iMax6 = Math.max(iMax, l(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
                iMax = iMax6;
            } else {
                iMax5 = iMax5;
            }
        }
        int i15 = iMax5;
        int i16 = this.B + this.C;
        int i17 = this.z + this.A;
        if (s(this.f1481l)) {
            q(this.f1481l, i10, i15 + i17, i11, i16, iArr);
            int iK3 = k(this.f1481l) + this.f1481l.getMeasuredWidth();
            iL = l(this.f1481l) + this.f1481l.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1481l.getMeasuredState());
            iMax2 = iK3;
        } else {
            iL = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (s(this.f1482m)) {
            iMax2 = Math.max(iMax2, q(this.f1482m, i10, i15 + i17, i11, i16 + iL, iArr));
            iL += l(this.f1482m) + this.f1482m.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f1482m.getMeasuredState());
        }
        int iMax7 = Math.max(iMax, iL);
        int paddingRight = getPaddingRight() + getPaddingLeft() + i15 + iMax2;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax7;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i10, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i11, iCombineMeasuredStates2 << 16);
        if (!this.c0) {
            i13 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i18 = 0; i18 < childCount2; i18++) {
            View childAt2 = getChildAt(i18);
            if (s(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i13 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i13);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof n4)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        n4 n4Var = (n4) parcelable;
        super.onRestoreInstanceState(n4Var.f22549i);
        ActionMenuView actionMenuView = this.f1480i;
        androidx.appcompat.view.menu.q qVar = actionMenuView != null ? actionMenuView.z : null;
        int i10 = n4Var.f1671m;
        if (i10 != 0 && this.W != null && qVar != null && (menuItemFindItem = qVar.findItem(i10)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (n4Var.f1672n) {
            f2 f2Var = this.f1479g0;
            removeCallbacks(f2Var);
            post(f2Var);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i10) {
        super.onRtlPropertiesChanged(i10);
        d();
        l3 l3Var = this.D;
        boolean z = i10 == 1;
        if (z == l3Var.f1654g) {
            return;
        }
        l3Var.f1654g = z;
        if (!l3Var.f1655h) {
            l3Var.f1648a = l3Var.f1652e;
            l3Var.f1649b = l3Var.f1653f;
            return;
        }
        if (z) {
            int i11 = l3Var.f1651d;
            if (i11 == Integer.MIN_VALUE) {
                i11 = l3Var.f1652e;
            }
            l3Var.f1648a = i11;
            int i12 = l3Var.f1650c;
            if (i12 == Integer.MIN_VALUE) {
                i12 = l3Var.f1653f;
            }
            l3Var.f1649b = i12;
            return;
        }
        int i13 = l3Var.f1650c;
        if (i13 == Integer.MIN_VALUE) {
            i13 = l3Var.f1652e;
        }
        l3Var.f1648a = i13;
        int i14 = l3Var.f1651d;
        if (i14 == Integer.MIN_VALUE) {
            i14 = l3Var.f1653f;
        }
        l3Var.f1649b = i14;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        m mVar;
        androidx.appcompat.view.menu.t tVar;
        n4 n4Var = new n4(super.onSaveInstanceState());
        l4 l4Var = this.W;
        if (l4Var != null && (tVar = l4Var.f1657l) != null) {
            n4Var.f1671m = tVar.f1367a;
        }
        ActionMenuView actionMenuView = this.f1480i;
        n4Var.f1672n = (actionMenuView == null || (mVar = actionMenuView.D) == null || !mVar.k()) ? false : true;
        return n4Var;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.L = false;
        }
        if (!this.L) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.L = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.L = false;
        return true;
    }

    public final int p(View view, int i10, int i11, int[] iArr) {
        m4 m4Var = (m4) view.getLayoutParams();
        int i12 = ((ViewGroup.MarginLayoutParams) m4Var).rightMargin - iArr[1];
        int iMax = i10 - Math.max(0, i12);
        iArr[1] = Math.max(0, -i12);
        int iJ = j(i11, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iJ, iMax, view.getMeasuredHeight() + iJ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) m4Var).leftMargin);
    }

    public final int q(View view, int i10, int i11, int i12, int i13, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i14 = marginLayoutParams.leftMargin - iArr[0];
        int i15 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i15) + Math.max(0, i14);
        iArr[0] = Math.max(0, -i14);
        iArr[1] = Math.max(0, -i15);
        view.measure(ViewGroup.getChildMeasureSpec(i10, getPaddingRight() + getPaddingLeft() + iMax + i11, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i12, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i13, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void r(View view, int i10, int i11, int i12, int i13) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i11, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i12, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i13 >= 0) {
            if (mode != 0) {
                i13 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i13);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i13, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    @Override // androidx.core.view.c0
    public final void removeMenuProvider(androidx.core.view.i0 i0Var) {
        this.Q.a(i0Var);
    }

    public final boolean s(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.f1478f0 != z) {
            this.f1478f0 = z;
            u();
        }
    }

    public void setCollapseContentDescription(int i10) {
        setCollapseContentDescription(i10 != 0 ? getContext().getText(i10) : null);
    }

    public void setCollapseIcon(int i10) {
        setCollapseIcon(m2.f0.o(getContext(), i10));
    }

    public void setCollapsible(boolean z) {
        this.c0 = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i10) {
        if (i10 < 0) {
            i10 = Integer.MIN_VALUE;
        }
        if (i10 != this.F) {
            this.F = i10;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i10) {
        if (i10 < 0) {
            i10 = Integer.MIN_VALUE;
        }
        if (i10 != this.E) {
            this.E = i10;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i10) {
        setLogo(m2.f0.o(getContext(), i10));
    }

    public void setLogoDescription(int i10) {
        setLogoDescription(getContext().getText(i10));
    }

    public void setNavigationContentDescription(int i10) {
        setNavigationContentDescription(i10 != 0 ? getContext().getText(i10) : null);
    }

    public void setNavigationIcon(int i10) {
        setNavigationIcon(m2.f0.o(getContext(), i10));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.f1483n.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(a aVar) {
        this.S = aVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.f1480i.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i10) {
        if (this.f1490u != i10) {
            this.f1490u = i10;
            if (i10 == 0) {
                this.f1489t = getContext();
            } else {
                this.f1489t = new ContextThemeWrapper(getContext(), i10);
            }
        }
    }

    public void setSubtitle(int i10) {
        setSubtitle(getContext().getText(i10));
    }

    public void setSubtitleTextColor(int i10) {
        setSubtitleTextColor(ColorStateList.valueOf(i10));
    }

    public void setTitle(int i10) {
        setTitle(getContext().getText(i10));
    }

    public void setTitleMarginBottom(int i10) {
        this.C = i10;
        requestLayout();
    }

    public void setTitleMarginEnd(int i10) {
        this.A = i10;
        requestLayout();
    }

    public void setTitleMarginStart(int i10) {
        this.z = i10;
        requestLayout();
    }

    public void setTitleMarginTop(int i10) {
        this.B = i10;
        requestLayout();
    }

    public void setTitleTextColor(int i10) {
        setTitleTextColor(ColorStateList.valueOf(i10));
    }

    public final boolean t() {
        m mVar;
        ActionMenuView actionMenuView = this.f1480i;
        return (actionMenuView == null || (mVar = actionMenuView.D) == null || !mVar.l()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u() {
        /*
            r4 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r0 < r1) goto L4f
            android.window.OnBackInvokedDispatcher r0 = androidx.appcompat.widget.k4.a(r4)
            androidx.appcompat.widget.l4 r1 = r4.W
            r2 = 0
            if (r1 == 0) goto L23
            androidx.appcompat.view.menu.t r1 = r1.f1657l
            if (r1 == 0) goto L23
            if (r0 == 0) goto L23
            java.util.WeakHashMap r1 = androidx.core.view.b2.f2200a
            boolean r1 = r4.isAttachedToWindow()
            if (r1 == 0) goto L23
            boolean r1 = r4.f1478f0
            if (r1 == 0) goto L23
            r1 = 1
            goto L24
        L23:
            r1 = r2
        L24:
            if (r1 == 0) goto L41
            android.window.OnBackInvokedDispatcher r3 = r4.f1477e0
            if (r3 != 0) goto L41
            android.window.OnBackInvokedCallback r1 = r4.f1476d0
            if (r1 != 0) goto L39
            androidx.appcompat.widget.i4 r1 = new androidx.appcompat.widget.i4
            r1.<init>(r4, r2)
            android.window.OnBackInvokedCallback r1 = androidx.appcompat.widget.k4.b(r1)
            r4.f1476d0 = r1
        L39:
            android.window.OnBackInvokedCallback r1 = r4.f1476d0
            androidx.appcompat.widget.k4.c(r0, r1)
            r4.f1477e0 = r0
            return
        L41:
            if (r1 != 0) goto L4f
            android.window.OnBackInvokedDispatcher r0 = r4.f1477e0
            if (r0 == 0) goto L4f
            android.window.OnBackInvokedCallback r1 = r4.f1476d0
            androidx.appcompat.widget.k4.d(r0, r1)
            r0 = 0
            r4.f1477e0 = r0
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.u():void");
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        m4 m4Var = new m4(context, attributeSet);
        m4Var.f1666a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19018b);
        m4Var.f1666a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        m4Var.f1667b = 0;
        return m4Var;
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        e0 e0Var = this.f1487r;
        if (e0Var != null) {
            e0Var.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.f1487r.setImageDrawable(drawable);
        } else {
            e0 e0Var = this.f1487r;
            if (e0Var != null) {
                e0Var.setImageDrawable(this.f1485p);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f1484o == null) {
                this.f1484o = new g0(getContext(), null, 0);
            }
            if (!n(this.f1484o)) {
                b(this.f1484o, true);
            }
        } else {
            g0 g0Var = this.f1484o;
            if (g0Var != null && n(g0Var)) {
                removeView(this.f1484o);
                this.O.remove(this.f1484o);
            }
        }
        g0 g0Var2 = this.f1484o;
        if (g0Var2 != null) {
            g0Var2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f1484o == null) {
            this.f1484o = new g0(getContext(), null, 0);
        }
        g0 g0Var = this.f1484o;
        if (g0Var != null) {
            g0Var.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        e0 e0Var = this.f1483n;
        if (e0Var != null) {
            e0Var.setContentDescription(charSequence);
            kotlin.reflect.b0.E(this.f1483n, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!n(this.f1483n)) {
                b(this.f1483n, true);
            }
        } else {
            e0 e0Var = this.f1483n;
            if (e0Var != null && n(e0Var)) {
                removeView(this.f1483n);
                this.O.remove(this.f1483n);
            }
        }
        e0 e0Var2 = this.f1483n;
        if (e0Var2 != null) {
            e0Var2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            o1 o1Var = this.f1482m;
            if (o1Var != null && n(o1Var)) {
                removeView(this.f1482m);
                this.O.remove(this.f1482m);
            }
        } else {
            if (this.f1482m == null) {
                Context context = getContext();
                o1 o1Var2 = new o1(context, null);
                this.f1482m = o1Var2;
                o1Var2.setSingleLine();
                this.f1482m.setEllipsize(TextUtils.TruncateAt.END);
                int i10 = this.f1492w;
                if (i10 != 0) {
                    this.f1482m.setTextAppearance(context, i10);
                }
                ColorStateList colorStateList = this.K;
                if (colorStateList != null) {
                    this.f1482m.setTextColor(colorStateList);
                }
            }
            if (!n(this.f1482m)) {
                b(this.f1482m, true);
            }
        }
        o1 o1Var3 = this.f1482m;
        if (o1Var3 != null) {
            o1Var3.setText(charSequence);
        }
        this.I = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.K = colorStateList;
        o1 o1Var = this.f1482m;
        if (o1Var != null) {
            o1Var.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            o1 o1Var = this.f1481l;
            if (o1Var != null && n(o1Var)) {
                removeView(this.f1481l);
                this.O.remove(this.f1481l);
            }
        } else {
            if (this.f1481l == null) {
                Context context = getContext();
                o1 o1Var2 = new o1(context, null);
                this.f1481l = o1Var2;
                o1Var2.setSingleLine();
                this.f1481l.setEllipsize(TextUtils.TruncateAt.END);
                int i10 = this.f1491v;
                if (i10 != 0) {
                    this.f1481l.setTextAppearance(context, i10);
                }
                ColorStateList colorStateList = this.J;
                if (colorStateList != null) {
                    this.f1481l.setTextColor(colorStateList);
                }
            }
            if (!n(this.f1481l)) {
                b(this.f1481l, true);
            }
        }
        o1 o1Var3 = this.f1481l;
        if (o1Var3 != null) {
            o1Var3.setText(charSequence);
        }
        this.H = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.J = colorStateList;
        o1 o1Var = this.f1481l;
        if (o1Var != null) {
            o1Var.setTextColor(colorStateList);
        }
    }
}
