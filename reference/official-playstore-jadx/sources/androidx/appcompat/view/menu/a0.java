package androidx.appcompat.view.menu;

import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c0 f1262i;

    public a0(c0 c0Var) {
        this.f1262i = c0Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f1262i.c();
    }
}
