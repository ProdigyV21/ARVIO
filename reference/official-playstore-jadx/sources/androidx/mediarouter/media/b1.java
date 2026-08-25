package androidx.mediarouter.media;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4304i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4305l;

    public /* synthetic */ b1(Object obj, int i10) {
        this.f4304i = i10;
        this.f4305l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4304i) {
            case 0:
                SparseArray sparseArray = ((c1) this.f4305l).f4317h;
                int size = sparseArray.size();
                for (int i10 = 0; i10 < size; i10++) {
                    ((g1) sparseArray.valueAt(i10)).getClass();
                    g1.a(null, null);
                }
                sparseArray.clear();
                break;
            case 1:
                c1 c1Var = (c1) this.f4305l;
                j1 j1Var = c1Var.f4318i;
                if (j1Var.x == c1Var) {
                    j1Var.j();
                }
                break;
            default:
                ((m1) this.f4305l).a();
                break;
        }
    }
}
