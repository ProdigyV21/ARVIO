package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.d0;
import androidx.core.view.b2;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f1266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1268d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f1269e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1271g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d0.a f1272h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public z f1273i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1274j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1270f = 8388611;
    public final a0 k = new a0(this);

    public c0(Context context, q qVar, View view, boolean z, int i10, int i11) {
        this.f1265a = context;
        this.f1266b = qVar;
        this.f1269e = view;
        this.f1267c = z;
        this.f1268d = i10;
    }

    public final z a() {
        z h0Var;
        if (this.f1273i == null) {
            Context context = this.f1265a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            b0.a(defaultDisplay, point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                h0Var = new k(context, this.f1269e, this.f1268d, this.f1267c);
            } else {
                h0Var = new h0(this.f1265a, this.f1266b, this.f1269e, this.f1268d, this.f1267c);
            }
            h0Var.j(this.f1266b);
            h0Var.q(this.k);
            h0Var.m(this.f1269e);
            h0Var.c(this.f1272h);
            h0Var.n(this.f1271g);
            h0Var.o(this.f1270f);
            this.f1273i = h0Var;
        }
        return this.f1273i;
    }

    public final boolean b() {
        z zVar = this.f1273i;
        return zVar != null && zVar.a();
    }

    public void c() {
        this.f1273i = null;
        PopupWindow.OnDismissListener onDismissListener = this.f1274j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i10, int i11, boolean z, boolean z5) {
        z zVarA = a();
        zVarA.r(z5);
        if (z) {
            int i12 = this.f1270f;
            View view = this.f1269e;
            WeakHashMap weakHashMap = b2.f2200a;
            if ((Gravity.getAbsoluteGravity(i12, view.getLayoutDirection()) & 7) == 5) {
                i10 -= this.f1269e.getWidth();
            }
            zVarA.p(i10);
            zVarA.s(i11);
            int i13 = (int) ((this.f1265a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            zVarA.f1400i = new Rect(i10 - i13, i11 - i13, i10 + i13, i11 + i13);
        }
        zVarA.show();
    }
}
