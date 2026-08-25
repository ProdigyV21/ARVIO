package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class o4 implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.appcompat.view.menu.a f1708i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ q4 f1709l;

    public o4(q4 q4Var) {
        this.f1709l = q4Var;
        Context context = q4Var.f1723a.getContext();
        CharSequence charSequence = q4Var.f1730h;
        androidx.appcompat.view.menu.a aVar = new androidx.appcompat.view.menu.a();
        aVar.f1251e = 4096;
        aVar.f1253g = 4096;
        aVar.f1257l = null;
        aVar.f1258m = null;
        aVar.f1259n = false;
        aVar.f1260o = false;
        aVar.f1261p = 16;
        aVar.f1255i = context;
        aVar.f1247a = charSequence;
        this.f1708i = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        q4 q4Var = this.f1709l;
        Window.Callback callback = q4Var.k;
        if (callback == null || !q4Var.f1733l) {
            return;
        }
        callback.onMenuItemSelected(0, this.f1708i);
    }
}
