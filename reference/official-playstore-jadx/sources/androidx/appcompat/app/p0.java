package androidx.appcompat.app;

import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.ContentFrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends ContentFrameLayout {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s0 f1149s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(s0 s0Var, n.e eVar) {
        super(eVar, null);
        this.f1149s = s0Var;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f1149s.v(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x = (int) motionEvent.getX();
            int y10 = (int) motionEvent.getY();
            if (x < -5 || y10 < -5 || x > getWidth() + 5 || y10 > getHeight() + 5) {
                s0 s0Var = this.f1149s;
                s0Var.t(s0Var.B(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i10) {
        setBackgroundDrawable(m2.f0.o(getContext(), i10));
    }
}
