package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4927i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ArrayList f4928l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m f4929m;

    public /* synthetic */ g(m mVar, ArrayList arrayList, int i10) {
        this.f4927i = i10;
        this.f4929m = mVar;
        this.f4928l = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4927i) {
            case 0:
                ArrayList arrayList = this.f4928l;
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    m mVar = this.f4929m;
                    if (!zHasNext) {
                        arrayList.clear();
                        mVar.f4994m.remove(arrayList);
                    } else {
                        l lVar = (l) it.next();
                        p1 p1Var = lVar.f4968a;
                        int i10 = lVar.f4969b;
                        int i11 = lVar.f4970c;
                        int i12 = lVar.f4971d;
                        int i13 = lVar.f4972e;
                        mVar.getClass();
                        View view = p1Var.itemView;
                        int i14 = i12 - i10;
                        int i15 = i13 - i11;
                        if (i14 != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i15 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        mVar.f4997p.add(p1Var);
                        viewPropertyAnimatorAnimate.setDuration(mVar.f5068e).setListener(new i(mVar, p1Var, i14, view, i15, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                ArrayList arrayList2 = this.f4928l;
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    boolean zHasNext2 = it2.hasNext();
                    m mVar2 = this.f4929m;
                    if (!zHasNext2) {
                        arrayList2.clear();
                        mVar2.f4995n.remove(arrayList2);
                        break;
                    } else {
                        k kVar = (k) it2.next();
                        ArrayList arrayList3 = mVar2.f4999r;
                        long j10 = mVar2.f5069f;
                        p1 p1Var2 = kVar.f4960a;
                        View view2 = p1Var2 == null ? null : p1Var2.itemView;
                        p1 p1Var3 = kVar.f4961b;
                        View view3 = p1Var3 != null ? p1Var3.itemView : null;
                        if (view2 != null) {
                            ViewPropertyAnimator duration = view2.animate().setDuration(j10);
                            arrayList3.add(kVar.f4960a);
                            duration.translationX(kVar.f4964e - kVar.f4962c);
                            duration.translationY(kVar.f4965f - kVar.f4963d);
                            duration.alpha(0.0f).setListener(new j(mVar2, kVar, duration, view2, 0)).start();
                        }
                        if (view3 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                            arrayList3.add(kVar.f4961b);
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j10).alpha(1.0f).setListener(new j(mVar2, kVar, viewPropertyAnimatorAnimate2, view3, 1)).start();
                        }
                    }
                }
                break;
            default:
                ArrayList arrayList4 = this.f4928l;
                Iterator it3 = arrayList4.iterator();
                while (true) {
                    boolean zHasNext3 = it3.hasNext();
                    m mVar3 = this.f4929m;
                    if (!zHasNext3) {
                        arrayList4.clear();
                        mVar3.f4993l.remove(arrayList4);
                    } else {
                        p1 p1Var4 = (p1) it3.next();
                        mVar3.getClass();
                        View view4 = p1Var4.itemView;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                        mVar3.f4996o.add(p1Var4);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(mVar3.f5066c).setListener(new h(mVar3, p1Var4, view4, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
        }
    }
}
