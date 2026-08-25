package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray f4916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Set f4918c;

    public final d1 a(int i10) {
        SparseArray sparseArray = this.f4916a;
        d1 d1Var = (d1) sparseArray.get(i10);
        if (d1Var != null) {
            return d1Var;
        }
        d1 d1Var2 = new d1();
        sparseArray.put(i10, d1Var2);
        return d1Var2;
    }
}
