package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class c3 extends w2 implements x2 {
    public static final Method N;
    public a8.e M;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                N = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    @Override // androidx.appcompat.widget.x2
    public final void c(androidx.appcompat.view.menu.q qVar, androidx.appcompat.view.menu.t tVar) {
        a8.e eVar = this.M;
        if (eVar != null) {
            eVar.c(qVar, tVar);
        }
    }

    @Override // androidx.appcompat.widget.w2
    public final g2 n(Context context, boolean z) {
        b3 b3Var = new b3(context, z);
        b3Var.setHoverListener(this);
        return b3Var;
    }

    @Override // androidx.appcompat.widget.x2
    public final void z(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        a8.e eVar = this.M;
        if (eVar != null) {
            eVar.z(qVar, menuItem);
        }
    }
}
