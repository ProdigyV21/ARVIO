package androidx.appcompat.app;

import android.view.Window;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class r0 implements d0.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ s0 f1167i;

    public r0(s0 s0Var) {
        this.f1167i = s0Var;
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
        q0 q0Var;
        androidx.appcompat.view.menu.q qVarK = qVar.k();
        int i10 = 0;
        boolean z5 = qVarK != qVar;
        if (z5) {
            qVar = qVarK;
        }
        s0 s0Var = this.f1167i;
        q0[] q0VarArr = s0Var.V;
        int length = q0VarArr != null ? q0VarArr.length : 0;
        while (true) {
            if (i10 < length) {
                q0Var = q0VarArr[i10];
                if (q0Var != null && q0Var.f1158h == qVar) {
                    break;
                } else {
                    i10++;
                }
            } else {
                q0Var = null;
                break;
            }
        }
        if (q0Var != null) {
            if (!z5) {
                s0Var.t(q0Var, z);
            } else {
                s0Var.r(q0Var.f1151a, q0Var, qVarK);
                s0Var.t(q0Var, true);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final boolean c(androidx.appcompat.view.menu.q qVar) {
        Window.Callback callback;
        if (qVar != qVar.k()) {
            return true;
        }
        s0 s0Var = this.f1167i;
        if (!s0Var.P || (callback = s0Var.f1190v.getCallback()) == null || s0Var.f1173a0) {
            return true;
        }
        callback.onMenuOpened(108, qVar);
        return true;
    }
}
