package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.c3;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class f implements ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1287i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ z f1288l;

    public /* synthetic */ f(z zVar, int i10) {
        this.f1287i = i10;
        this.f1288l = zVar;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        switch (this.f1287i) {
            case 0:
                k kVar = (k) this.f1288l;
                ArrayList arrayList = kVar.f1320r;
                if (kVar.a() && arrayList.size() > 0 && !((i) arrayList.get(0)).f1310a.H) {
                    View view = kVar.f1326y;
                    if (view != null && view.isShown()) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            ((i) it.next()).f1310a.show();
                        }
                    } else {
                        kVar.dismiss();
                    }
                    break;
                }
                break;
            default:
                h0 h0Var = (h0) this.f1288l;
                c3 c3Var = h0Var.f1303r;
                if (h0Var.a() && !c3Var.H) {
                    View view2 = h0Var.f1308w;
                    if (view2 != null && view2.isShown()) {
                        c3Var.show();
                    } else {
                        h0Var.dismiss();
                    }
                    break;
                }
                break;
        }
    }
}
