package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.media3.session.MediaUtils;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1401i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f1402l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f1403m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f1404n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f1405o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f1406p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f1407q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1408r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f1409s;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b bVar = new b(this);
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        setBackground(bVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19017a);
        boolean z = false;
        this.f1404n = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f1405o = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f1409s = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f1407q = true;
            this.f1406p = typedArrayObtainStyledAttributes.getDrawable(1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f1407q ? !(this.f1404n != null || this.f1405o != null) : this.f1406p == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1404n;
        if (drawable != null && drawable.isStateful()) {
            this.f1404n.setState(getDrawableState());
        }
        Drawable drawable2 = this.f1405o;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f1405o.setState(getDrawableState());
        }
        Drawable drawable3 = this.f1406p;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f1406p.setState(getDrawableState());
    }

    public View getTabContainer() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1404n;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f1405o;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f1406p;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f1402l = findViewById(R.id.action_bar);
        this.f1403m = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1401i || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        boolean z5 = true;
        if (this.f1407q) {
            Drawable drawable = this.f1406p;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z5 = false;
            }
        } else {
            if (this.f1404n == null) {
                z5 = false;
            } else if (this.f1402l.getVisibility() == 0) {
                this.f1404n.setBounds(this.f1402l.getLeft(), this.f1402l.getTop(), this.f1402l.getRight(), this.f1402l.getBottom());
            } else {
                View view = this.f1403m;
                if (view == null || view.getVisibility() != 0) {
                    this.f1404n.setBounds(0, 0, 0, 0);
                } else {
                    this.f1404n.setBounds(this.f1403m.getLeft(), this.f1403m.getTop(), this.f1403m.getRight(), this.f1403m.getBottom());
                }
            }
            this.f1408r = false;
        }
        if (z5) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        int i12;
        if (this.f1402l == null && View.MeasureSpec.getMode(i11) == Integer.MIN_VALUE && (i12 = this.f1409s) >= 0) {
            i11 = View.MeasureSpec.makeMeasureSpec(Math.min(i12, View.MeasureSpec.getSize(i11)), Integer.MIN_VALUE);
        }
        super.onMeasure(i10, i11);
        if (this.f1402l == null) {
            return;
        }
        View.MeasureSpec.getMode(i11);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f1404n;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f1404n);
        }
        this.f1404n = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f1402l;
            if (view != null) {
                this.f1404n.setBounds(view.getLeft(), this.f1402l.getTop(), this.f1402l.getRight(), this.f1402l.getBottom());
            }
        }
        boolean z = false;
        if (!this.f1407q ? !(this.f1404n != null || this.f1405o != null) : this.f1406p == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1406p;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1406p);
        }
        this.f1406p = drawable;
        boolean z = this.f1407q;
        boolean z5 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z && (drawable2 = this.f1406p) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z ? !(this.f1404n != null || this.f1405o != null) : this.f1406p == null) {
            z5 = true;
        }
        setWillNotDraw(z5);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.f1405o;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f1405o);
        }
        this.f1405o = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1408r && this.f1405o != null) {
                throw null;
            }
        }
        boolean z = false;
        if (!this.f1407q ? !(this.f1404n != null || this.f1405o != null) : this.f1406p == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(m3 m3Var) {
    }

    public void setTransitioning(boolean z) {
        this.f1401i = z;
        setDescendantFocusability(z ? 393216 : MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
    }

    @Override // android.view.View
    public void setVisibility(int i10) {
        super.setVisibility(i10);
        boolean z = i10 == 0;
        Drawable drawable = this.f1404n;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f1405o;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f1406p;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1404n;
        boolean z = this.f1407q;
        if (drawable == drawable2 && !z) {
            return true;
        }
        if (drawable == this.f1405o && this.f1408r) {
            return true;
        }
        return (drawable == this.f1406p && z) || super.verifyDrawable(drawable);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i10) {
        if (i10 != 0) {
            return super.startActionModeForChild(view, callback, i10);
        }
        return null;
    }
}
