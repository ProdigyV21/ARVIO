package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5050b;

    public /* synthetic */ t0(int i10, int i11) {
        this.f5049a = i10;
        this.f5050b = i11;
    }

    public void a(p1 p1Var) {
        View view = p1Var.itemView;
        this.f5049a = view.getLeft();
        this.f5050b = view.getTop();
        view.getRight();
        view.getBottom();
    }
}
