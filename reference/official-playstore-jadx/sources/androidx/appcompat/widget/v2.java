package androidx.appcompat.widget;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class v2 implements View.OnTouchListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ w2 f1797i;

    public v2(w2 w2Var) {
        this.f1797i = w2Var;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        w2 w2Var = this.f1797i;
        s2 s2Var = w2Var.A;
        Handler handler = w2Var.E;
        i0 i0Var = w2Var.I;
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y10 = (int) motionEvent.getY();
        if (action == 0 && i0Var != null && i0Var.isShowing() && x >= 0 && x < i0Var.getWidth() && y10 >= 0 && y10 < i0Var.getHeight()) {
            handler.postDelayed(s2Var, 250L);
            return false;
        }
        if (action != 1) {
            return false;
        }
        handler.removeCallbacks(s2Var);
        return false;
    }
}
