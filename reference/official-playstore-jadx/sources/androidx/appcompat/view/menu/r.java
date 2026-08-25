package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class r implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, d0.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i0 f1364i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public androidx.appcompat.app.q f1365l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public m f1366m;

    @Override // androidx.appcompat.view.menu.d0.a
    public final void b(q qVar, boolean z) {
        androidx.appcompat.app.q qVar2;
        if ((z || qVar == this.f1364i) && (qVar2 = this.f1365l) != null) {
            qVar2.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final boolean c(q qVar) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i10) {
        i0 i0Var = this.f1364i;
        m mVar = this.f1366m;
        if (mVar.f1334p == null) {
            mVar.f1334p = new l(mVar);
        }
        i0Var.q(mVar.f1334p.getItem(i10), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f1366m.b(this.f1364i, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i10, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        i0 i0Var = this.f1364i;
        if (i10 == 82 || i10 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1365l.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1365l.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                i0Var.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return i0Var.performShortcut(i10, keyEvent, 0);
    }
}
