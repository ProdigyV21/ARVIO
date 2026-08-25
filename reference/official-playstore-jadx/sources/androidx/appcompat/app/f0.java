package androidx.appcompat.app;

import android.view.Window;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class f0 implements d0.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ s0 f1041i;

    public f0(s0 s0Var) {
        this.f1041i = s0Var;
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
        this.f1041i.s(qVar);
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final boolean c(androidx.appcompat.view.menu.q qVar) {
        Window.Callback callback = this.f1041i.f1190v.getCallback();
        if (callback == null) {
            return true;
        }
        callback.onMenuOpened(108, qVar);
        return true;
    }
}
