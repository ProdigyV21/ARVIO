package androidx.appcompat.app;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.core.view.b2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class j1 extends com.google.common.util.concurrent.p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l1 f1066b;

    public /* synthetic */ j1(l1 l1Var, int i10) {
        this.f1065a = i10;
        this.f1066b = l1Var;
    }

    @Override // androidx.core.view.o2
    public final void c() {
        View view;
        int i10 = this.f1065a;
        l1 l1Var = this.f1066b;
        switch (i10) {
            case 0:
                if (l1Var.f1106o && (view = l1Var.f1099g) != null) {
                    view.setTranslationY(0.0f);
                    l1Var.f1096d.setTranslationY(0.0f);
                }
                l1Var.f1096d.setVisibility(8);
                l1Var.f1096d.setTransitioning(false);
                l1Var.f1110s = null;
                io.sentry.internal.debugmeta.c cVar = l1Var.k;
                if (cVar != null) {
                    cVar.t(l1Var.f1102j);
                    l1Var.f1102j = null;
                    l1Var.k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = l1Var.f1095c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = b2.f2200a;
                    androidx.core.view.i1.c(actionBarOverlayLayout);
                }
                break;
            default:
                l1Var.f1110s = null;
                l1Var.f1096d.requestLayout();
                break;
        }
    }
}
