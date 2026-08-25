package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j0 f5064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f5065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f5068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5069f;

    public static void b(p1 p1Var) {
        int i10 = p1Var.mFlags;
        if (!p1Var.isInvalid() && (i10 & 4) == 0) {
            p1Var.getOldPosition();
            p1Var.getAbsoluteAdapterPosition();
        }
    }

    public abstract boolean a(p1 p1Var, p1 p1Var2, t0 t0Var, t0 t0Var2);

    public final void c(p1 p1Var) {
        j0 j0Var = this.f5064a;
        if (j0Var != null) {
            RecyclerView recyclerView = j0Var.f4952a;
            boolean z = true;
            p1Var.setIsRecyclable(true);
            if (p1Var.mShadowedHolder != null && p1Var.mShadowingHolder == null) {
                p1Var.mShadowedHolder = null;
            }
            p1Var.mShadowingHolder = null;
            if (p1Var.shouldBeKeptAsChild()) {
                return;
            }
            View view = p1Var.itemView;
            f1 f1Var = recyclerView.f4837m;
            recyclerView.f0();
            androidx.appcompat.app.i1 i1Var = recyclerView.f4843p;
            e eVar = (e) i1Var.f1060m;
            j0 j0Var2 = (j0) i1Var.f1059l;
            int iIndexOfChild = j0Var2.f4952a.indexOfChild(view);
            if (iIndexOfChild == -1) {
                i1Var.e0(view);
            } else if (eVar.g(iIndexOfChild)) {
                eVar.i(iIndexOfChild);
                i1Var.e0(view);
                j0Var2.j(iIndexOfChild);
            } else {
                z = false;
            }
            if (z) {
                p1 p1VarG = RecyclerView.G(view);
                f1Var.l(p1VarG);
                f1Var.i(p1VarG);
            }
            recyclerView.g0(!z);
            if (z || !p1Var.isTmpDetached()) {
                return;
            }
            recyclerView.removeDetachedView(p1Var.itemView, false);
        }
    }

    public abstract void d(p1 p1Var);

    public abstract void e();

    public abstract boolean f();
}
