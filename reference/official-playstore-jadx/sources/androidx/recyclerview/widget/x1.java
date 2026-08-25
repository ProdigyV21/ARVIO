package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f5089a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5090b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5091c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5092d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5093e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f5094f;

    public x1(StaggeredGridLayoutManager staggeredGridLayoutManager, int i10) {
        this.f5094f = staggeredGridLayoutManager;
        this.f5093e = i10;
    }

    public final void a() {
        View view = (View) a0.c.h(1, this.f5089a);
        u1 u1Var = (u1) view.getLayoutParams();
        this.f5091c = this.f5094f.f4864r.b(view);
        u1Var.getClass();
    }

    public final void b() {
        this.f5089a.clear();
        this.f5090b = Integer.MIN_VALUE;
        this.f5091c = Integer.MIN_VALUE;
        this.f5092d = 0;
    }

    public final int c() {
        return this.f5094f.f4869w ? e(r1.size() - 1, -1) : e(0, this.f5089a.size());
    }

    public final int d() {
        return this.f5094f.f4869w ? e(0, this.f5089a.size()) : e(r1.size() - 1, -1);
    }

    public final int e(int i10, int i11) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f5094f;
        int iK = staggeredGridLayoutManager.f4864r.k();
        int iG = staggeredGridLayoutManager.f4864r.g();
        int i12 = i11 > i10 ? 1 : -1;
        while (i10 != i11) {
            View view = (View) this.f5089a.get(i10);
            int iE = staggeredGridLayoutManager.f4864r.e(view);
            int iB = staggeredGridLayoutManager.f4864r.b(view);
            boolean z = iE <= iG;
            boolean z5 = iB >= iK;
            if (z && z5 && (iE < iK || iB > iG)) {
                return z0.L(view);
            }
            i10 += i12;
        }
        return -1;
    }

    public final int f(int i10) {
        int i11 = this.f5091c;
        if (i11 != Integer.MIN_VALUE) {
            return i11;
        }
        if (this.f5089a.size() == 0) {
            return i10;
        }
        a();
        return this.f5091c;
    }

    public final View g(int i10, int i11) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f5094f;
        ArrayList arrayList = this.f5089a;
        View view = null;
        if (i11 != -1) {
            int size = arrayList.size() - 1;
            while (size >= 0) {
                View view2 = (View) arrayList.get(size);
                if ((staggeredGridLayoutManager.f4869w && z0.L(view2) >= i10) || ((!staggeredGridLayoutManager.f4869w && z0.L(view2) <= i10) || !view2.hasFocusable())) {
                    break;
                }
                size--;
                view = view2;
            }
            return view;
        }
        int size2 = arrayList.size();
        int i12 = 0;
        while (i12 < size2) {
            View view3 = (View) arrayList.get(i12);
            if ((staggeredGridLayoutManager.f4869w && z0.L(view3) <= i10) || ((!staggeredGridLayoutManager.f4869w && z0.L(view3) >= i10) || !view3.hasFocusable())) {
                break;
            }
            i12++;
            view = view3;
        }
        return view;
    }

    public final int h(int i10) {
        int i11 = this.f5090b;
        if (i11 != Integer.MIN_VALUE) {
            return i11;
        }
        ArrayList arrayList = this.f5089a;
        if (arrayList.size() == 0) {
            return i10;
        }
        View view = (View) arrayList.get(0);
        u1 u1Var = (u1) view.getLayoutParams();
        this.f5090b = this.f5094f.f4864r.e(view);
        u1Var.getClass();
        return this.f5090b;
    }
}
