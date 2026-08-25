package androidx.leanback.widget;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements RecyclerView.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f3058a;

    public a(h hVar) {
        this.f3058a = hVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void a(androidx.recyclerview.widget.p1 p1Var) {
        a0 a0Var = this.f3058a.S0;
        a0Var.getClass();
        int absoluteAdapterPosition = p1Var.getAbsoluteAdapterPosition();
        if (absoluteAdapterPosition != -1) {
            r1 r1Var = a0Var.f3062d0;
            View view = p1Var.itemView;
            int i10 = r1Var.f3112b;
            if (i10 == 1) {
                t.r rVar = (t.r) r1Var.f3114d;
                if (rVar == null || rVar.size() == 0) {
                    return;
                }
                ((t.r) r1Var.f3114d).remove(Integer.toString(absoluteAdapterPosition));
                return;
            }
            if ((i10 == 2 || i10 == 3) && ((t.r) r1Var.f3114d) != null) {
                String string = Integer.toString(absoluteAdapterPosition);
                SparseArray<Parcelable> sparseArray = new SparseArray<>();
                view.saveHierarchyState(sparseArray);
                ((t.r) r1Var.f3114d).put(string, sparseArray);
            }
        }
    }
}
