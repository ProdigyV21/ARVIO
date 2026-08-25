package androidx.recyclerview.widget;

import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class g1 extends n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f4931a;

    public g1(RecyclerView recyclerView) {
        this.f4931a = recyclerView;
    }

    public final void a() {
        boolean z = RecyclerView.N0;
        RecyclerView recyclerView = this.f4931a;
        if (!z || !recyclerView.E || !recyclerView.D) {
            recyclerView.L = true;
            recyclerView.requestLayout();
        } else {
            h0 h0Var = recyclerView.f4849s;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            recyclerView.postOnAnimation(h0Var);
        }
    }
}
