package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class j2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1607i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k2 f1608l;

    public /* synthetic */ j2(k2 k2Var, int i10) {
        this.f1607i = i10;
        this.f1608l = k2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1607i) {
            case 0:
                ViewParent parent = this.f1608l.f1640n.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                k2 k2Var = this.f1608l;
                k2Var.a();
                View view = k2Var.f1640n;
                if (view.isEnabled() && !view.isLongClickable() && k2Var.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    k2Var.f1643q = true;
                    break;
                }
                break;
        }
    }
}
