package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends u0 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static TimeInterpolator f4988s;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4989g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f4990h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f4991i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f4992j;
    public ArrayList k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList f4993l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f4994m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f4995n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList f4996o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f4997p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f4998q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f4999r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((p1) arrayList.get(size)).itemView.animate().cancel();
        }
    }

    @Override // androidx.recyclerview.widget.u0
    public final boolean a(p1 p1Var, p1 p1Var2, t0 t0Var, t0 t0Var2) {
        int i10;
        int i11;
        int i12 = t0Var.f5049a;
        int i13 = t0Var.f5050b;
        if (p1Var2.shouldIgnore()) {
            int i14 = t0Var.f5049a;
            i11 = t0Var.f5050b;
            i10 = i14;
        } else {
            i10 = t0Var2.f5049a;
            i11 = t0Var2.f5050b;
        }
        if (p1Var == p1Var2) {
            return g(p1Var, i12, i13, i10, i11);
        }
        float translationX = p1Var.itemView.getTranslationX();
        float translationY = p1Var.itemView.getTranslationY();
        float alpha = p1Var.itemView.getAlpha();
        l(p1Var);
        p1Var.itemView.setTranslationX(translationX);
        p1Var.itemView.setTranslationY(translationY);
        p1Var.itemView.setAlpha(alpha);
        l(p1Var2);
        p1Var2.itemView.setTranslationX(-((int) ((i10 - i12) - translationX)));
        p1Var2.itemView.setTranslationY(-((int) ((i11 - i13) - translationY)));
        p1Var2.itemView.setAlpha(0.0f);
        ArrayList arrayList = this.k;
        k kVar = new k();
        kVar.f4960a = p1Var;
        kVar.f4961b = p1Var2;
        kVar.f4962c = i12;
        kVar.f4963d = i13;
        kVar.f4964e = i10;
        kVar.f4965f = i11;
        arrayList.add(kVar);
        return true;
    }

    @Override // androidx.recyclerview.widget.u0
    public final void d(p1 p1Var) {
        ArrayList arrayList = this.f4993l;
        ArrayList arrayList2 = this.f4994m;
        ArrayList arrayList3 = this.f4995n;
        View view = p1Var.itemView;
        view.animate().cancel();
        ArrayList arrayList4 = this.f4992j;
        int size = arrayList4.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((l) arrayList4.get(size)).f4968a == p1Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(p1Var);
                arrayList4.remove(size);
            }
        }
        j(this.k, p1Var);
        if (this.f4990h.remove(p1Var)) {
            view.setAlpha(1.0f);
            c(p1Var);
        }
        if (this.f4991i.remove(p1Var)) {
            view.setAlpha(1.0f);
            c(p1Var);
        }
        for (int size2 = arrayList3.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList5 = (ArrayList) arrayList3.get(size2);
            j(arrayList5, p1Var);
            if (arrayList5.isEmpty()) {
                arrayList3.remove(size2);
            }
        }
        for (int size3 = arrayList2.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList6 = (ArrayList) arrayList2.get(size3);
            int size4 = arrayList6.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((l) arrayList6.get(size4)).f4968a == p1Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(p1Var);
                    arrayList6.remove(size4);
                    if (arrayList6.isEmpty()) {
                        arrayList2.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = arrayList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList.get(size5);
            if (arrayList7.remove(p1Var)) {
                view.setAlpha(1.0f);
                c(p1Var);
                if (arrayList7.isEmpty()) {
                    arrayList.remove(size5);
                }
            }
        }
        this.f4998q.remove(p1Var);
        this.f4996o.remove(p1Var);
        this.f4999r.remove(p1Var);
        this.f4997p.remove(p1Var);
        i();
    }

    @Override // androidx.recyclerview.widget.u0
    public final void e() {
        ArrayList arrayList = this.f4995n;
        ArrayList arrayList2 = this.f4993l;
        ArrayList arrayList3 = this.f4994m;
        ArrayList arrayList4 = this.k;
        ArrayList arrayList5 = this.f4991i;
        ArrayList arrayList6 = this.f4990h;
        ArrayList arrayList7 = this.f4992j;
        int size = arrayList7.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            l lVar = (l) arrayList7.get(size);
            View view = lVar.f4968a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(lVar.f4968a);
            arrayList7.remove(size);
        }
        for (int size2 = arrayList6.size() - 1; size2 >= 0; size2--) {
            c((p1) arrayList6.get(size2));
            arrayList6.remove(size2);
        }
        int size3 = arrayList5.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            p1 p1Var = (p1) arrayList5.get(size3);
            p1Var.itemView.setAlpha(1.0f);
            c(p1Var);
            arrayList5.remove(size3);
        }
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            k kVar = (k) arrayList4.get(size4);
            p1 p1Var2 = kVar.f4960a;
            if (p1Var2 != null) {
                k(kVar, p1Var2);
            }
            p1 p1Var3 = kVar.f4961b;
            if (p1Var3 != null) {
                k(kVar, p1Var3);
            }
        }
        arrayList4.clear();
        if (f()) {
            for (int size5 = arrayList3.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList8 = (ArrayList) arrayList3.get(size5);
                for (int size6 = arrayList8.size() - 1; size6 >= 0; size6--) {
                    l lVar2 = (l) arrayList8.get(size6);
                    View view2 = lVar2.f4968a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(lVar2.f4968a);
                    arrayList8.remove(size6);
                    if (arrayList8.isEmpty()) {
                        arrayList3.remove(arrayList8);
                    }
                }
            }
            for (int size7 = arrayList2.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList9 = (ArrayList) arrayList2.get(size7);
                for (int size8 = arrayList9.size() - 1; size8 >= 0; size8--) {
                    p1 p1Var4 = (p1) arrayList9.get(size8);
                    p1Var4.itemView.setAlpha(1.0f);
                    c(p1Var4);
                    arrayList9.remove(size8);
                    if (arrayList9.isEmpty()) {
                        arrayList2.remove(arrayList9);
                    }
                }
            }
            for (int size9 = arrayList.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    k kVar2 = (k) arrayList10.get(size10);
                    p1 p1Var5 = kVar2.f4960a;
                    if (p1Var5 != null) {
                        k(kVar2, p1Var5);
                    }
                    p1 p1Var6 = kVar2.f4961b;
                    if (p1Var6 != null) {
                        k(kVar2, p1Var6);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList.remove(arrayList10);
                    }
                }
            }
            h(this.f4998q);
            h(this.f4997p);
            h(this.f4996o);
            h(this.f4999r);
            ArrayList arrayList11 = this.f5065b;
            int size11 = arrayList11.size();
            for (int i10 = 0; i10 < size11; i10++) {
                ((r0) arrayList11.get(i10)).a();
            }
            arrayList11.clear();
        }
    }

    @Override // androidx.recyclerview.widget.u0
    public final boolean f() {
        return (this.f4991i.isEmpty() && this.k.isEmpty() && this.f4992j.isEmpty() && this.f4990h.isEmpty() && this.f4997p.isEmpty() && this.f4998q.isEmpty() && this.f4996o.isEmpty() && this.f4999r.isEmpty() && this.f4994m.isEmpty() && this.f4993l.isEmpty() && this.f4995n.isEmpty()) ? false : true;
    }

    public final boolean g(p1 p1Var, int i10, int i11, int i12, int i13) {
        View view = p1Var.itemView;
        int translationX = i10 + ((int) view.getTranslationX());
        int translationY = i11 + ((int) p1Var.itemView.getTranslationY());
        l(p1Var);
        int i14 = i12 - translationX;
        int i15 = i13 - translationY;
        if (i14 == 0 && i15 == 0) {
            c(p1Var);
            return false;
        }
        if (i14 != 0) {
            view.setTranslationX(-i14);
        }
        if (i15 != 0) {
            view.setTranslationY(-i15);
        }
        ArrayList arrayList = this.f4992j;
        l lVar = new l();
        lVar.f4968a = p1Var;
        lVar.f4969b = translationX;
        lVar.f4970c = translationY;
        lVar.f4971d = i12;
        lVar.f4972e = i13;
        arrayList.add(lVar);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.f5065b;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((r0) arrayList.get(i10)).a();
        }
        arrayList.clear();
    }

    public final void j(ArrayList arrayList, p1 p1Var) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            k kVar = (k) arrayList.get(size);
            if (k(kVar, p1Var) && kVar.f4960a == null && kVar.f4961b == null) {
                arrayList.remove(kVar);
            }
        }
    }

    public final boolean k(k kVar, p1 p1Var) {
        if (kVar.f4961b == p1Var) {
            kVar.f4961b = null;
        } else {
            if (kVar.f4960a != p1Var) {
                return false;
            }
            kVar.f4960a = null;
        }
        p1Var.itemView.setAlpha(1.0f);
        p1Var.itemView.setTranslationX(0.0f);
        p1Var.itemView.setTranslationY(0.0f);
        c(p1Var);
        return true;
    }

    public final void l(p1 p1Var) {
        if (f4988s == null) {
            f4988s = new ValueAnimator().getInterpolator();
        }
        p1Var.itemView.animate().setInterpolator(f4988s);
        d(p1Var);
    }
}
