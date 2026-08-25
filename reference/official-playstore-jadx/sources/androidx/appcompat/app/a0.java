package androidx.appcompat.app;

import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.view.b2;
import androidx.core.view.n2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1030i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1031l;

    public /* synthetic */ a0(Object obj, int i10) {
        this.f1030i = i10;
        this.f1031l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewGroup viewGroup;
        int i10 = this.f1030i;
        Object obj = this.f1031l;
        int i11 = 0;
        switch (i10) {
            case 0:
                s0 s0Var = (s0) obj;
                if ((s0Var.f1180j0 & 1) != 0) {
                    s0Var.w(0);
                }
                if ((s0Var.f1180j0 & 4096) != 0) {
                    s0Var.w(108);
                }
                s0Var.f1179i0 = false;
                s0Var.f1180j0 = 0;
                return;
            case 1:
                s0 s0Var2 = (s0) obj;
                s0Var2.G.showAtLocation(s0Var2.F, 55, 0, 0);
                n2 n2Var = s0Var2.I;
                if (n2Var != null) {
                    n2Var.b();
                }
                if (s0Var2.J && (viewGroup = s0Var2.K) != null) {
                    WeakHashMap weakHashMap = b2.f2200a;
                    if (viewGroup.isLaidOut()) {
                        s0Var2.F.setAlpha(0.0f);
                        n2 n2VarA = b2.a(s0Var2.F);
                        n2VarA.a(1.0f);
                        s0Var2.I = n2VarA;
                        n2VarA.d(new d0(this, i11));
                        return;
                    }
                }
                s0Var2.F.setAlpha(1.0f);
                s0Var2.F.setVisibility(0);
                return;
            default:
                f1 f1Var = (f1) obj;
                Window.Callback callback = f1Var.f1043b;
                Menu menuX = f1Var.x();
                androidx.appcompat.view.menu.q qVar = menuX instanceof androidx.appcompat.view.menu.q ? (androidx.appcompat.view.menu.q) menuX : null;
                if (qVar != null) {
                    qVar.w();
                }
                try {
                    menuX.clear();
                    if (!callback.onCreatePanelMenu(0, menuX) || !callback.onPreparePanel(0, null, menuX)) {
                        menuX.clear();
                        break;
                    }
                    if (qVar != null) {
                        qVar.v();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (qVar != null) {
                        qVar.v();
                    }
                    throw th;
                }
        }
    }
}
