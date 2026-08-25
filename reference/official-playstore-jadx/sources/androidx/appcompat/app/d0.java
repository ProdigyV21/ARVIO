package androidx.appcompat.app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.core.view.b2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends com.google.common.util.concurrent.p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1037b;

    public /* synthetic */ d0(Object obj, int i10) {
        this.f1036a = i10;
        this.f1037b = obj;
    }

    @Override // com.google.common.util.concurrent.p0, androidx.core.view.o2
    public void b() {
        int i10 = this.f1036a;
        Object obj = this.f1037b;
        switch (i10) {
            case 0:
                ((s0) ((a0) obj).f1031l).F.setVisibility(0);
                break;
            case 1:
                s0 s0Var = (s0) obj;
                s0Var.F.setVisibility(0);
                if (s0Var.F.getParent() instanceof View) {
                    View view = (View) s0Var.F.getParent();
                    WeakHashMap weakHashMap = b2.f2200a;
                    androidx.core.view.i1.c(view);
                }
                break;
        }
    }

    @Override // androidx.core.view.o2
    public final void c() {
        int i10 = this.f1036a;
        Object obj = this.f1037b;
        switch (i10) {
            case 0:
                s0 s0Var = (s0) ((a0) obj).f1031l;
                s0Var.F.setAlpha(1.0f);
                s0Var.I.d(null);
                s0Var.I = null;
                break;
            case 1:
                s0 s0Var2 = (s0) obj;
                s0Var2.F.setAlpha(1.0f);
                s0Var2.I.d(null);
                s0Var2.I = null;
                break;
            default:
                s0 s0Var3 = (s0) ((io.sentry.internal.debugmeta.c) obj).f17368m;
                s0Var3.F.setVisibility(8);
                PopupWindow popupWindow = s0Var3.G;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (s0Var3.F.getParent() instanceof View) {
                    View view = (View) s0Var3.F.getParent();
                    WeakHashMap weakHashMap = b2.f2200a;
                    androidx.core.view.i1.c(view);
                }
                s0Var3.F.e();
                s0Var3.I.d(null);
                s0Var3.I = null;
                ViewGroup viewGroup = s0Var3.K;
                WeakHashMap weakHashMap2 = b2.f2200a;
                androidx.core.view.i1.c(viewGroup);
                break;
        }
    }
}
