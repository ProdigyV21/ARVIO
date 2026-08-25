package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.arvio.tv.R;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class g2 extends ListView {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f1576i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1577l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1578m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1579n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1580o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1581p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d2 f1582q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1583r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f1584s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1585t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public u0.h f1586u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public f2 f1587v;

    public g2(Context context, boolean z) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.f1576i = new Rect();
        this.f1577l = 0;
        this.f1578m = 0;
        this.f1579n = 0;
        this.f1580o = 0;
        this.f1584s = z;
        setCacheColorHint(0);
    }

    public final int a(int i10, int i11) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i12 = 0;
        View view = null;
        for (int i13 = 0; i13 < count; i13++) {
            int itemViewType = adapter.getItemViewType(i13);
            if (itemViewType != i12) {
                view = null;
                i12 = itemViewType;
            }
            view = adapter.getView(i13, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i14 = layoutParams.height;
            view.measure(i10, i14 > 0 ? View.MeasureSpec.makeMeasureSpec(i14, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i13 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i11) {
                return i11;
            }
        }
        return measuredHeight;
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(android.view.MotionEvent r18, int r19) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.g2.b(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f1576i;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f1587v != null) {
            return;
        }
        super.drawableStateChanged();
        d2 d2Var = this.f1582q;
        if (d2Var != null) {
            d2Var.f1547l = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f1585t && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        return this.f1584s || super.hasFocus();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.f1584s || super.hasWindowFocus();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.f1584s || super.isFocused();
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        return (this.f1584s && this.f1583r) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f1587v = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1587v == null) {
            f2 f2Var = new f2(this, 0);
            this.f1587v = f2Var;
            post(f2Var);
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked != 9 && actionMasked != 7) {
            setSelection(-1);
            return zOnHoverEvent;
        }
        int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
            View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
            if (childAt.isEnabled()) {
                requestFocus();
                if (i10 < 30 || !b2.f1511d) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                } else {
                    try {
                        b2.f1508a.invoke(this, Integer.valueOf(iPointToPosition), childAt, Boolean.FALSE, -1, -1);
                        b2.f1509b.invoke(this, Integer.valueOf(iPointToPosition));
                        b2.f1510c.invoke(this, Integer.valueOf(iPointToPosition));
                    } catch (IllegalAccessException e5) {
                        e5.printStackTrace();
                    } catch (InvocationTargetException e6) {
                        e6.printStackTrace();
                    }
                }
            }
            Drawable selector = getSelector();
            if (selector != null && this.f1585t && isPressed()) {
                selector.setState(getDrawableState());
            }
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1581p = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        f2 f2Var = this.f1587v;
        if (f2Var != null) {
            g2 g2Var = (g2) f2Var.f1570l;
            g2Var.f1587v = null;
            g2Var.removeCallbacks(f2Var);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.f1583r = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        d2 d2Var = null;
        if (drawable != null) {
            d2 d2Var2 = new d2();
            Drawable drawable2 = d2Var2.f1546i;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            d2Var2.f1546i = drawable;
            drawable.setCallback(d2Var2);
            d2Var2.f1547l = true;
            d2Var = d2Var2;
        }
        this.f1582q = d2Var;
        super.setSelector(d2Var);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1577l = rect.left;
        this.f1578m = rect.top;
        this.f1579n = rect.right;
        this.f1580o = rect.bottom;
    }
}
