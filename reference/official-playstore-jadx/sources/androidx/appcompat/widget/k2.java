package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class k2 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f1637i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f1638l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1639m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final View f1640n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public j2 f1641o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public j2 f1642p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1643q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1644r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int[] f1645s = new int[2];

    public k2(View view) {
        this.f1640n = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1637i = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1638l = tapTimeout;
        this.f1639m = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        j2 j2Var = this.f1642p;
        View view = this.f1640n;
        if (j2Var != null) {
            view.removeCallbacks(j2Var);
        }
        j2 j2Var2 = this.f1641o;
        if (j2Var2 != null) {
            view.removeCallbacks(j2Var2);
        }
    }

    public abstract androidx.appcompat.view.menu.g0 b();

    public abstract boolean c();

    public boolean d() {
        androidx.appcompat.view.menu.g0 g0VarB = b();
        if (g0VarB == null || !g0VarB.a()) {
            return true;
        }
        g0VarB.dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0100  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.k2.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f1643q = false;
        this.f1644r = -1;
        j2 j2Var = this.f1641o;
        if (j2Var != null) {
            this.f1640n.removeCallbacks(j2Var);
        }
    }
}
