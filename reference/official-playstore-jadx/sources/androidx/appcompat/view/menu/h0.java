package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.d0;
import androidx.appcompat.widget.c3;
import androidx.appcompat.widget.g2;
import androidx.core.view.b2;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends z implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public boolean A;
    public int B;
    public boolean D;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f1297l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final q f1298m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final n f1299n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f1300o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1301p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f1302q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final c3 f1303r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1306u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public View f1307v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View f1308w;
    public d0.a x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ViewTreeObserver f1309y;
    public boolean z;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f f1304s = new f(this, 1);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final g f1305t = new g(this, 1);
    public int C = 0;

    public h0(Context context, q qVar, View view, int i10, boolean z) {
        this.f1297l = context;
        this.f1298m = qVar;
        this.f1300o = z;
        this.f1299n = new n(qVar, LayoutInflater.from(context), z, R.layout.abc_popup_menu_item_layout);
        this.f1302q = i10;
        Resources resources = context.getResources();
        this.f1301p = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1307v = view;
        this.f1303r = new c3(context, null, i10);
        qVar.b(this, context);
    }

    @Override // androidx.appcompat.view.menu.g0
    public final boolean a() {
        return !this.z && this.f1303r.I.isShowing();
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void b(q qVar, boolean z) {
        if (qVar != this.f1298m) {
            return;
        }
        dismiss();
        d0.a aVar = this.x;
        if (aVar != null) {
            aVar.b(qVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void c(d0.a aVar) {
        this.x = aVar;
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void dismiss() {
        if (a()) {
            this.f1303r.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void e() {
        this.A = false;
        n nVar = this.f1299n;
        if (nVar != null) {
            nVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean f(i0 i0Var) {
        boolean z;
        if (i0Var.hasVisibleItems()) {
            c0 c0Var = new c0(this.f1297l, i0Var, this.f1308w, this.f1300o, this.f1302q, 0);
            d0.a aVar = this.x;
            c0Var.f1272h = aVar;
            z zVar = c0Var.f1273i;
            if (zVar != null) {
                zVar.c(aVar);
            }
            int size = i0Var.f1347f.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    z = false;
                    break;
                }
                MenuItem item = i0Var.getItem(i10);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i10++;
            }
            c0Var.f1271g = z;
            z zVar2 = c0Var.f1273i;
            if (zVar2 != null) {
                zVar2.n(z);
            }
            c0Var.f1274j = this.f1306u;
            this.f1306u = null;
            this.f1298m.c(false);
            c3 c3Var = this.f1303r;
            int width = c3Var.f1816p;
            int verticalOffset = c3Var.getVerticalOffset();
            int i11 = this.C;
            View view = this.f1307v;
            WeakHashMap weakHashMap = b2.f2200a;
            if ((Gravity.getAbsoluteGravity(i11, view.getLayoutDirection()) & 7) == 5) {
                width += this.f1307v.getWidth();
            }
            if (!c0Var.b()) {
                if (c0Var.f1269e != null) {
                    c0Var.d(width, verticalOffset, true, true);
                }
            }
            d0.a aVar2 = this.x;
            if (aVar2 != null) {
                aVar2.c(i0Var);
            }
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean h() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void j(q qVar) {
    }

    @Override // androidx.appcompat.view.menu.g0
    public final g2 l() {
        return this.f1303r.f1813m;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void m(View view) {
        this.f1307v = view;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void n(boolean z) {
        this.f1299n.f1337m = z;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void o(int i10) {
        this.C = i10;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.z = true;
        this.f1298m.c(true);
        ViewTreeObserver viewTreeObserver = this.f1309y;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1309y = this.f1308w.getViewTreeObserver();
            }
            this.f1309y.removeGlobalOnLayoutListener(this.f1304s);
            this.f1309y = null;
        }
        this.f1308w.removeOnAttachStateChangeListener(this.f1305t);
        PopupWindow.OnDismissListener onDismissListener = this.f1306u;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i10, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i10 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void p(int i10) {
        this.f1303r.f1816p = i10;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void q(PopupWindow.OnDismissListener onDismissListener) {
        this.f1306u = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void r(boolean z) {
        this.D = z;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void s(int i10) {
        this.f1303r.h(i10);
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void show() {
        View view;
        if (a()) {
            return;
        }
        if (this.z || (view = this.f1307v) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.f1308w = view;
        c3 c3Var = this.f1303r;
        androidx.appcompat.widget.i0 i0Var = c3Var.I;
        androidx.appcompat.widget.i0 i0Var2 = c3Var.I;
        i0Var.setOnDismissListener(this);
        c3Var.z = this;
        c3Var.H = true;
        i0Var2.setFocusable(true);
        View view2 = this.f1308w;
        boolean z = this.f1309y == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f1309y = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f1304s);
        }
        view2.addOnAttachStateChangeListener(this.f1305t);
        c3Var.f1824y = view2;
        c3Var.f1822v = this.C;
        boolean z5 = this.A;
        Context context = this.f1297l;
        n nVar = this.f1299n;
        if (!z5) {
            this.B = z.k(nVar, context, this.f1301p);
            this.A = true;
        }
        c3Var.o(this.B);
        i0Var2.setInputMethodMode(2);
        Rect rect = this.f1400i;
        c3Var.G = rect != null ? new Rect(rect) : null;
        c3Var.show();
        g2 g2Var = c3Var.f1813m;
        g2Var.setOnKeyListener(this);
        if (this.D) {
            q qVar = this.f1298m;
            if (qVar.f1353m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) g2Var, false);
                TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                if (textView != null) {
                    textView.setText(qVar.f1353m);
                }
                frameLayout.setEnabled(false);
                g2Var.addHeaderView(frameLayout, null, false);
            }
        }
        c3Var.k(nVar);
        c3Var.show();
    }
}
