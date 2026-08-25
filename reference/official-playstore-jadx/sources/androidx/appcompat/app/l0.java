package androidx.appcompat.app;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements Window.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Window.Callback f1086i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e0 f1087l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1088m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1089n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1090o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s0 f1091p;

    public l0(s0 s0Var, Window.Callback callback) {
        this.f1091p = s0Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f1086i = callback;
    }

    public final void a(Window.Callback callback) {
        try {
            this.f1088m = true;
            callback.onContentChanged();
        } finally {
            this.f1088m = false;
        }
    }

    public final boolean b(int i10, Menu menu) {
        return this.f1086i.onMenuOpened(i10, menu);
    }

    public final void c(int i10, Menu menu) {
        this.f1086i.onPanelClosed(i10, menu);
    }

    public final void d(List list, Menu menu, int i10) {
        n.m.a(this.f1086i, list, menu, i10);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f1086i.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = this.f1089n;
        Window.Callback callback = this.f1086i;
        return z ? callback.dispatchKeyEvent(keyEvent) : this.f1091p.v(keyEvent) || callback.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (!this.f1086i.dispatchKeyShortcutEvent(keyEvent)) {
            int keyCode = keyEvent.getKeyCode();
            s0 s0Var = this.f1091p;
            s0Var.C();
            f fVar = s0Var.f1192y;
            if (fVar == null || !fVar.m(keyCode, keyEvent)) {
                q0 q0Var = s0Var.W;
                if (q0Var == null || !s0Var.H(q0Var, keyEvent.getKeyCode(), keyEvent)) {
                    if (s0Var.W == null) {
                        q0 q0VarB = s0Var.B(0);
                        s0Var.I(q0VarB, keyEvent);
                        boolean zH = s0Var.H(q0VarB, keyEvent.getKeyCode(), keyEvent);
                        q0VarB.k = false;
                        if (zH) {
                        }
                    }
                    return false;
                }
                q0 q0Var2 = s0Var.W;
                if (q0Var2 != null) {
                    q0Var2.f1161l = true;
                    return true;
                }
            }
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f1086i.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f1086i.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f1086i.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        this.f1086i.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        this.f1086i.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.f1086i.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.f1088m) {
            this.f1086i.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i10, Menu menu) {
        if (i10 != 0 || (menu instanceof androidx.appcompat.view.menu.q)) {
            return this.f1086i.onCreatePanelMenu(i10, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i10) {
        View viewOnCreatePanelView;
        e0 e0Var = this.f1087l;
        return (e0Var == null || (viewOnCreatePanelView = e0Var.onCreatePanelView(i10)) == null) ? this.f1086i.onCreatePanelView(i10) : viewOnCreatePanelView;
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.f1086i.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i10, MenuItem menuItem) {
        return this.f1086i.onMenuItemSelected(i10, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i10, Menu menu) {
        b(i10, menu);
        s0 s0Var = this.f1091p;
        if (i10 == 108) {
            s0Var.C();
            f fVar = s0Var.f1192y;
            if (fVar != null) {
                fVar.g(true);
            }
        } else {
            s0Var.getClass();
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i10, Menu menu) {
        if (this.f1090o) {
            this.f1086i.onPanelClosed(i10, menu);
            return;
        }
        c(i10, menu);
        s0 s0Var = this.f1091p;
        if (i10 == 108) {
            s0Var.C();
            f fVar = s0Var.f1192y;
            if (fVar != null) {
                fVar.g(false);
                return;
            }
            return;
        }
        if (i10 == 0) {
            q0 q0VarB = s0Var.B(i10);
            if (q0VarB.f1162m) {
                s0Var.t(q0VarB, false);
            }
        }
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z) {
        n.n.a(this.f1086i, z);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i10, View view, Menu menu) {
        androidx.appcompat.view.menu.q qVar = menu instanceof androidx.appcompat.view.menu.q ? (androidx.appcompat.view.menu.q) menu : null;
        if (i10 == 0 && qVar == null) {
            return false;
        }
        if (qVar != null) {
            qVar.x = true;
        }
        e0 e0Var = this.f1087l;
        if (e0Var != null) {
            e0Var.a(i10);
        }
        boolean zOnPreparePanel = this.f1086i.onPreparePanel(i10, view, menu);
        if (qVar != null) {
            qVar.x = false;
        }
        return zOnPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i10) {
        androidx.appcompat.view.menu.q qVar = this.f1091p.B(0).f1158h;
        if (qVar != null) {
            d(list, qVar, i10);
        } else {
            d(list, menu, i10);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return n.l.a(this.f1086i, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f1086i.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        this.f1086i.onWindowFocusChanged(z);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.f1086i.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i10) {
        if (i10 != 0) {
            return n.l.b(this.f1086i, callback, i10);
        }
        s0 s0Var = this.f1091p;
        androidx.emoji2.text.e0 e0Var = new androidx.emoji2.text.e0(s0Var.f1189u, callback);
        n.b bVarN = s0Var.n(e0Var);
        if (bVarN != null) {
            return e0Var.u(bVarN);
        }
        return null;
    }
}
