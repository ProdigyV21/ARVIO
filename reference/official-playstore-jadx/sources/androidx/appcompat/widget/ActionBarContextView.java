package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {
    public final int A;
    public final int B;
    public boolean C;
    public final int D;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f1410i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f1411l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ActionMenuView f1412m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f1413n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1414o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public androidx.core.view.n2 f1415p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1416q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1417r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f1418s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CharSequence f1419t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public View f1420u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public View f1421v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View f1422w;
    public LinearLayout x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f1423y;
    public TextView z;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        int resourceId;
        super(context, attributeSet, R.attr.actionModeStyle);
        this.f1410i = new a(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f1411l = context;
        } else {
            this.f1411l = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19020d, R.attr.actionModeStyle, 0);
        Drawable drawable = (!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : m2.f0.o(context, resourceId);
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        setBackground(drawable);
        this.A = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.B = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.f1414o = typedArrayObtainStyledAttributes.getLayoutDimension(3, 0);
        this.D = typedArrayObtainStyledAttributes.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static int f(View view, int i10, int i11) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i10, Integer.MIN_VALUE), i11);
        return Math.max(0, i10 - view.getMeasuredWidth());
    }

    public static int g(View view, int i10, int i11, int i12, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i13 = ((i12 - measuredHeight) / 2) + i11;
        if (z) {
            view.layout(i10 - measuredWidth, i13, i10, measuredHeight + i13);
        } else {
            view.layout(i10, i13, i10 + measuredWidth, measuredHeight + i13);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public final void c(n.b bVar) {
        View view = this.f1420u;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.D, (ViewGroup) this, false);
            this.f1420u = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f1420u);
        }
        View viewFindViewById = this.f1420u.findViewById(R.id.action_mode_close_button);
        this.f1421v = viewFindViewById;
        viewFindViewById.setOnClickListener(new c(bVar, 0));
        androidx.appcompat.view.menu.q qVarD = bVar.d();
        m mVar = this.f1413n;
        if (mVar != null) {
            mVar.j();
            g gVar = mVar.D;
            if (gVar != null && gVar.b()) {
                gVar.f1273i.dismiss();
            }
        }
        m mVar2 = new m(getContext());
        this.f1413n = mVar2;
        mVar2.f1662v = true;
        mVar2.f1663w = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        qVarD.b(this.f1413n, this.f1411l);
        m mVar3 = this.f1413n;
        androidx.appcompat.view.menu.e0 e0Var = mVar3.f1282r;
        if (e0Var == null) {
            androidx.appcompat.view.menu.e0 e0Var2 = (androidx.appcompat.view.menu.e0) mVar3.f1278n.inflate(mVar3.f1280p, (ViewGroup) this, false);
            mVar3.f1282r = e0Var2;
            e0Var2.a(mVar3.f1277m);
            mVar3.e();
        }
        androidx.appcompat.view.menu.e0 e0Var3 = mVar3.f1282r;
        if (e0Var != e0Var3) {
            ((ActionMenuView) e0Var3).setPresenter(mVar3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) e0Var3;
        this.f1412m = actionMenuView;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        actionMenuView.setBackground(null);
        addView(this.f1412m, layoutParams);
    }

    public final void d() {
        if (this.x == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.x = linearLayout;
            this.f1423y = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.z = (TextView) this.x.findViewById(R.id.action_bar_subtitle);
            int i10 = this.A;
            if (i10 != 0) {
                this.f1423y.setTextAppearance(getContext(), i10);
            }
            int i11 = this.B;
            if (i11 != 0) {
                this.z.setTextAppearance(getContext(), i11);
            }
        }
        this.f1423y.setText(this.f1418s);
        this.z.setText(this.f1419t);
        boolean zIsEmpty = TextUtils.isEmpty(this.f1418s);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.f1419t);
        this.z.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.x.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.x.getParent() == null) {
            addView(this.x);
        }
    }

    public final void e() {
        removeAllViews();
        this.f1422w = null;
        this.f1412m = null;
        this.f1413n = null;
        View view = this.f1421v;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.f1415p != null ? this.f1410i.f1499a : getVisibility();
    }

    public int getContentHeight() {
        return this.f1414o;
    }

    public CharSequence getSubtitle() {
        return this.f1419t;
    }

    public CharSequence getTitle() {
        return this.f1418s;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i10) {
        if (i10 != getVisibility()) {
            androidx.core.view.n2 n2Var = this.f1415p;
            if (n2Var != null) {
                n2Var.b();
            }
            super.setVisibility(i10);
        }
    }

    public final androidx.core.view.n2 i(int i10, long j10) {
        androidx.core.view.n2 n2Var = this.f1415p;
        if (n2Var != null) {
            n2Var.b();
        }
        a aVar = this.f1410i;
        if (i10 != 0) {
            androidx.core.view.n2 n2VarA = androidx.core.view.b2.a(this);
            n2VarA.a(0.0f);
            n2VarA.c(j10);
            ((ActionBarContextView) aVar.f1501c).f1415p = n2VarA;
            aVar.f1499a = i10;
            n2VarA.d(aVar);
            return n2VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        androidx.core.view.n2 n2VarA2 = androidx.core.view.b2.a(this);
        n2VarA2.a(1.0f);
        n2VarA2.c(j10);
        ((ActionBarContextView) aVar.f1501c).f1415p = n2VarA2;
        aVar.f1499a = i10;
        n2VarA2.d(aVar);
        return n2VarA2;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, j.a.f19017a, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(13, 0));
        typedArrayObtainStyledAttributes.recycle();
        m mVar = this.f1413n;
        if (mVar != null) {
            Configuration configuration2 = mVar.f1276l.getResources().getConfiguration();
            int i10 = configuration2.screenWidthDp;
            int i11 = configuration2.screenHeightDp;
            mVar.z = (configuration2.smallestScreenWidthDp > 600 || i10 > 600 || (i10 > 960 && i11 > 720) || (i10 > 720 && i11 > 960)) ? 5 : (i10 >= 500 || (i10 > 640 && i11 > 480) || (i10 > 480 && i11 > 640)) ? 4 : i10 >= 360 ? 3 : 2;
            androidx.appcompat.view.menu.q qVar = mVar.f1277m;
            if (qVar != null) {
                qVar.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.f1413n;
        if (mVar != null) {
            mVar.j();
            g gVar = this.f1413n.D;
            if (gVar == null || !gVar.b()) {
                return;
            }
            gVar.f1273i.dismiss();
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1417r = false;
        }
        if (!this.f1417r) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f1417r = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.f1417r = false;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        boolean zA = w4.a(this);
        int paddingRight = zA ? (i12 - i10) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i13 - i11) - getPaddingTop()) - getPaddingBottom();
        View view = this.f1420u;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1420u.getLayoutParams();
            int i14 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i15 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i16 = zA ? paddingRight - i14 : paddingRight + i14;
            int iG = g(this.f1420u, i16, paddingTop, paddingTop2, zA) + i16;
            paddingRight = zA ? iG - i15 : iG + i15;
        }
        LinearLayout linearLayout = this.x;
        if (linearLayout != null && this.f1422w == null && linearLayout.getVisibility() != 8) {
            paddingRight += g(this.x, paddingRight, paddingTop, paddingTop2, zA);
        }
        View view2 = this.f1422w;
        if (view2 != null) {
            g(view2, paddingRight, paddingTop, paddingTop2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i12 - i10) - getPaddingRight();
        ActionMenuView actionMenuView = this.f1412m;
        if (actionMenuView != null) {
            g(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zA);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        if (View.MeasureSpec.getMode(i10) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i11) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i10);
        int size2 = this.f1414o;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i11);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f1420u;
        if (view != null) {
            int iF = f(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1420u.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f1412m;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.f1412m, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.x;
        if (linearLayout != null && this.f1422w == null) {
            if (this.C) {
                this.x.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.x.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.x.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.f1422w;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i12 = layoutParams.width;
            int i13 = i12 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i12 >= 0) {
                paddingLeft = Math.min(i12, paddingLeft);
            }
            int i14 = layoutParams.height;
            int i15 = i14 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i14 >= 0) {
                iMin = Math.min(i14, iMin);
            }
            this.f1422w.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i13), View.MeasureSpec.makeMeasureSpec(iMin, i15));
        }
        if (this.f1414o > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i16 = 0;
        for (int i17 = 0; i17 < childCount; i17++) {
            int measuredHeight = getChildAt(i17).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i16) {
                i16 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i16);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1416q = false;
        }
        if (!this.f1416q) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f1416q = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.f1416q = false;
        return true;
    }

    public void setContentHeight(int i10) {
        this.f1414o = i10;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f1422w;
        if (view2 != null) {
            removeView(view2);
        }
        this.f1422w = view;
        if (view != null && (linearLayout = this.x) != null) {
            removeView(linearLayout);
            this.x = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1419t = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1418s = charSequence;
        d();
        androidx.core.view.b2.i(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.C) {
            requestLayout();
        }
        this.C = z;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
