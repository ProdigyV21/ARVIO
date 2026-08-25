package androidx.fragment.app;

import android.view.View;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class v1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2847i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2848l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2849m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2850n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2851o;

    public v1(int i10, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f2847i = i10;
        this.f2848l = arrayList;
        this.f2849m = arrayList2;
        this.f2850n = arrayList3;
        this.f2851o = arrayList4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i10 = 0; i10 < this.f2847i; i10++) {
            View view = (View) this.f2848l.get(i10);
            String str = (String) this.f2849m.get(i10);
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            androidx.core.view.k1.v(view, str);
            androidx.core.view.k1.v((View) this.f2850n.get(i10), (String) this.f2851o.get(i10));
        }
    }
}
