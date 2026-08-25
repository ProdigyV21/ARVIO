package androidx.appcompat.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public final class z3 extends TouchDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1850a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f1851b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1852c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f1853d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1854e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1855f;

    public z3(Rect rect, Rect rect2, View view) {
        super(rect, view);
        int scaledTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.f1854e = scaledTouchSlop;
        Rect rect3 = new Rect();
        this.f1851b = rect3;
        Rect rect4 = new Rect();
        this.f1853d = rect4;
        Rect rect5 = new Rect();
        this.f1852c = rect5;
        rect3.set(rect);
        rect4.set(rect);
        int i10 = -scaledTouchSlop;
        rect4.inset(i10, i10);
        rect5.set(rect2);
        this.f1850a = view;
    }

    @Override // android.view.TouchDelegate
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z5;
        int x = (int) motionEvent.getX();
        int y10 = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z10 = true;
        if (action != 0) {
            if (action == 1 || action == 2) {
                z5 = this.f1855f;
                if (z5 && !this.f1853d.contains(x, y10)) {
                    z10 = z5;
                    z = false;
                }
            } else {
                if (action == 3) {
                    z5 = this.f1855f;
                    this.f1855f = false;
                }
                z = true;
                z10 = false;
            }
            z10 = z5;
            z = true;
        } else if (this.f1851b.contains(x, y10)) {
            this.f1855f = true;
            z = true;
        } else {
            z = true;
            z10 = false;
        }
        if (!z10) {
            return false;
        }
        Rect rect = this.f1852c;
        View view = this.f1850a;
        if (!z || rect.contains(x, y10)) {
            motionEvent.setLocation(x - rect.left, y10 - rect.top);
        } else {
            motionEvent.setLocation(view.getWidth() / 2, view.getHeight() / 2);
        }
        return view.dispatchTouchEvent(motionEvent);
    }
}
