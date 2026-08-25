package androidx.fragment.app;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2710i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2711l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2712m;

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, int i10) {
        this.f2710i = i10;
        this.f2711l = obj2;
        this.f2712m = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2710i) {
            case 0:
                ArrayList arrayList = (ArrayList) this.f2711l;
                z1 z1Var = (z1) this.f2712m;
                if (arrayList.contains(z1Var)) {
                    arrayList.remove(z1Var);
                    a2.a(z1Var.f2890a, z1Var.f2892c.mView);
                }
                break;
            case 1:
                u1.b((View) this.f2711l, (Rect) this.f2712m);
                break;
            default:
                ((m) this.f2711l).a();
                if (b1.G(2)) {
                    Log.v("FragmentManager", "Transition for operation " + ((z1) this.f2712m) + "has completed");
                }
                break;
        }
    }

    public e(m mVar, z1 z1Var) {
        this.f2710i = 2;
        this.f2711l = mVar;
        this.f2712m = z1Var;
    }
}
