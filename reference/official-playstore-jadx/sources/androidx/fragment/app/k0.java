package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.q3;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f2763i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f2764l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View.OnApplyWindowInsetsListener f2765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2766n;

    public k0(Context context, AttributeSet attributeSet, b1 b1Var) {
        View view;
        super(context, attributeSet);
        this.f2763i = new ArrayList();
        this.f2764l = new ArrayList();
        this.f2766n = true;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g1.a.f15459b, 0, 0);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(0) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        c0 c0VarA = b1Var.A(id);
        if (classAttribute != null && c0VarA == null) {
            if (id == -1) {
                throw new IllegalStateException(a0.c.l("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
            }
            s0 s0VarD = b1Var.D();
            context.getClassLoader();
            c0 c0VarInstantiate = c0.instantiate(s0VarD.f2836a.f2686u.f2780l, classAttribute, null);
            c0VarInstantiate.onInflate(context, attributeSet, (Bundle) null);
            a aVar = new a(b1Var);
            aVar.f2818o = true;
            c0VarInstantiate.mContainer = this;
            aVar.c(getId(), c0VarInstantiate, string);
            if (aVar.f2811g) {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
            aVar.f2660p.y(aVar, true);
        }
        for (l1 l1Var : b1Var.f2669c.d()) {
            c0 c0Var = l1Var.f2773c;
            if (c0Var.mContainerId == getId() && (view = c0Var.mView) != null && view.getParent() == null) {
                c0Var.mContainer = this;
                l1Var.a();
            }
        }
    }

    public final void a(View view) {
        if (this.f2764l.contains(view)) {
            this.f2763i.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i10, ViewGroup.LayoutParams layoutParams) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof c0 ? (c0) tag : null) != null) {
            super.addView(view, i10, layoutParams);
            return;
        }
        throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.").toString());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        q3 q3VarC;
        q3 q3VarC2 = q3.c(null, windowInsets);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.f2765m;
        if (onApplyWindowInsetsListener != null) {
            q3VarC = q3.c(null, onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets));
        } else {
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            WindowInsets windowInsetsB = q3VarC2.b();
            if (windowInsetsB != null) {
                WindowInsets windowInsetsB2 = androidx.core.view.i1.b(this, windowInsetsB);
                if (!windowInsetsB2.equals(windowInsetsB)) {
                    q3VarC2 = q3.c(this, windowInsetsB2);
                }
            }
            q3VarC = q3VarC2;
        }
        if (!q3VarC.f2300a.n()) {
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
                WindowInsets windowInsetsB3 = q3VarC.b();
                if (windowInsetsB3 != null) {
                    WindowInsets windowInsetsA = androidx.core.view.i1.a(childAt, windowInsetsB3);
                    if (!windowInsetsA.equals(windowInsetsB3)) {
                        q3.c(childAt, windowInsetsA);
                    }
                }
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.f2766n) {
            Iterator it = this.f2763i.iterator();
            while (it.hasNext()) {
                super.drawChild(canvas, (View) it.next(), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j10) {
        if (this.f2766n) {
            ArrayList arrayList = this.f2763i;
            if (!arrayList.isEmpty() && arrayList.contains(view)) {
                return false;
            }
        }
        return super.drawChild(canvas, view, j10);
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        this.f2764l.remove(view);
        if (this.f2763i.remove(view)) {
            this.f2766n = true;
        }
        super.endViewTransition(view);
    }

    public final <F extends c0> F getFragment() {
        h0 h0Var;
        c0 c0Var;
        b1 supportFragmentManager;
        View view = this;
        while (true) {
            h0Var = null;
            if (view == null) {
                c0Var = null;
                break;
            }
            Object tag = view.getTag(R.id.fragment_container_view_tag);
            c0Var = tag instanceof c0 ? (c0) tag : null;
            if (c0Var != null) {
                break;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        if (c0Var == null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                if (context instanceof h0) {
                    h0Var = (h0) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (h0Var == null) {
                throw new IllegalStateException("View " + this + " is not within a subclass of FragmentActivity.");
            }
            supportFragmentManager = h0Var.getSupportFragmentManager();
        } else {
            if (!c0Var.isAdded()) {
                throw new IllegalStateException("The Fragment " + c0Var + " that owns View " + this + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
            }
            supportFragmentManager = c0Var.getChildFragmentManager();
        }
        return (F) supportFragmentManager.A(getId());
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViewsInLayout() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                super.removeAllViewsInLayout();
                return;
            }
            a(getChildAt(childCount));
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i10) {
        a(getChildAt(i10));
        super.removeViewAt(i10);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i10, int i11) {
        int i12 = i10 + i11;
        for (int i13 = i10; i13 < i12; i13++) {
            a(getChildAt(i13));
        }
        super.removeViews(i10, i11);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i10, int i11) {
        int i12 = i10 + i11;
        for (int i13 = i10; i13 < i12; i13++) {
            a(getChildAt(i13));
        }
        super.removeViewsInLayout(i10, i11);
    }

    public final void setDrawDisappearingViewsLast(boolean z) {
        this.f2766n = z;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.f2765m = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        if (view.getParent() == this) {
            this.f2764l.add(view);
        }
        super.startViewTransition(view);
    }
}
