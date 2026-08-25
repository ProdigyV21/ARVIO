package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class j0 implements e2, f, a, s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f4952a;

    public /* synthetic */ j0(RecyclerView recyclerView) {
        this.f4952a = recyclerView;
    }

    public void a(b bVar) {
        int i10 = bVar.f4879a;
        RecyclerView recyclerView = this.f4952a;
        if (i10 == 1) {
            recyclerView.x.e0(bVar.f4880b, bVar.f4882d);
            return;
        }
        if (i10 == 2) {
            recyclerView.x.h0(bVar.f4880b, bVar.f4882d);
        } else if (i10 == 4) {
            recyclerView.x.j0(recyclerView, bVar.f4880b, bVar.f4882d);
        } else {
            if (i10 != 8) {
                return;
            }
            recyclerView.x.g0(bVar.f4880b, bVar.f4882d);
        }
    }

    public p1 b(int i10) {
        RecyclerView recyclerView = this.f4952a;
        int childCount = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildCount();
        int i11 = 0;
        p1 p1Var = null;
        while (true) {
            if (i11 >= childCount) {
                break;
            }
            p1 p1VarG = RecyclerView.G(((j0) recyclerView.f4843p.f1059l).f4952a.getChildAt(i11));
            if (p1VarG != null && !p1VarG.isRemoved() && p1VarG.mPosition == i10) {
                if (!((ArrayList) recyclerView.f4843p.f1061n).contains(p1VarG.itemView)) {
                    p1Var = p1VarG;
                    break;
                }
                p1Var = p1VarG;
            }
            i11++;
        }
        if (p1Var != null) {
            if (!((ArrayList) recyclerView.f4843p.f1061n).contains(p1Var.itemView)) {
                return p1Var;
            }
        }
        return null;
    }

    public void c(int i10, int i11, Object obj) {
        int i12;
        int i13;
        RecyclerView recyclerView = this.f4952a;
        int childCount = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildCount();
        int i14 = i11 + i10;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildAt(i15);
            p1 p1VarG = RecyclerView.G(childAt);
            if (p1VarG != null && !p1VarG.shouldIgnore() && (i13 = p1VarG.mPosition) >= i10 && i13 < i14) {
                p1VarG.addFlags(2);
                p1VarG.addChangePayload(obj);
                ((a1) childAt.getLayoutParams()).f4877c = true;
            }
        }
        f1 f1Var = recyclerView.f4837m;
        ArrayList arrayList = f1Var.f4921c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            p1 p1Var = (p1) arrayList.get(size);
            if (p1Var != null && (i12 = p1Var.mPosition) >= i10 && i12 < i14) {
                p1Var.addFlags(2);
                f1Var.g(size);
            }
        }
        recyclerView.f4854u0 = true;
    }

    public void d(int i10, int i11) {
        RecyclerView recyclerView = this.f4952a;
        int childCount = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            p1 p1VarG = RecyclerView.G(((j0) recyclerView.f4843p.f1059l).f4952a.getChildAt(i12));
            if (p1VarG != null && !p1VarG.shouldIgnore() && p1VarG.mPosition >= i10) {
                p1VarG.offsetPosition(i11, false);
                recyclerView.f4846q0.f4978f = true;
            }
        }
        ArrayList arrayList = recyclerView.f4837m.f4921c;
        int size = arrayList.size();
        for (int i13 = 0; i13 < size; i13++) {
            p1 p1Var = (p1) arrayList.get(i13);
            if (p1Var != null && p1Var.mPosition >= i10) {
                p1Var.offsetPosition(i11, false);
            }
        }
        recyclerView.requestLayout();
        recyclerView.f4852t0 = true;
    }

    public void e(int i10, int i11) {
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        RecyclerView recyclerView = this.f4952a;
        int childCount = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildCount();
        int i19 = -1;
        if (i10 < i11) {
            i13 = i10;
            i12 = i11;
            i14 = -1;
        } else {
            i12 = i10;
            i13 = i11;
            i14 = 1;
        }
        for (int i20 = 0; i20 < childCount; i20++) {
            p1 p1VarG = RecyclerView.G(((j0) recyclerView.f4843p.f1059l).f4952a.getChildAt(i20));
            if (p1VarG != null && (i18 = p1VarG.mPosition) >= i13 && i18 <= i12) {
                if (i18 == i10) {
                    p1VarG.offsetPosition(i11 - i10, false);
                } else {
                    p1VarG.offsetPosition(i14, false);
                }
                recyclerView.f4846q0.f4978f = true;
            }
        }
        ArrayList arrayList = recyclerView.f4837m.f4921c;
        if (i10 < i11) {
            i16 = i10;
            i15 = i11;
        } else {
            i15 = i10;
            i16 = i11;
            i19 = 1;
        }
        int size = arrayList.size();
        for (int i21 = 0; i21 < size; i21++) {
            p1 p1Var = (p1) arrayList.get(i21);
            if (p1Var != null && (i17 = p1Var.mPosition) >= i16 && i17 <= i15) {
                if (i17 == i10) {
                    p1Var.offsetPosition(i11 - i10, false);
                } else {
                    p1Var.offsetPosition(i19, false);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.f4852t0 = true;
    }

    public void f(int i10, int i11) {
        RecyclerView recyclerView = this.f4952a;
        recyclerView.N(i10, i11, true);
        recyclerView.f4852t0 = true;
        recyclerView.f4846q0.f4975c += i11;
    }

    public void g(View view) {
        p1 p1VarG = RecyclerView.G(view);
        if (p1VarG != null) {
            p1VarG.onLeftHiddenState(this.f4952a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(androidx.recyclerview.widget.p1 r9, androidx.recyclerview.widget.t0 r10, androidx.recyclerview.widget.t0 r11) {
        /*
            r8 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r8.f4952a
            r0.getClass()
            r1 = 0
            r9.setIsRecyclable(r1)
            androidx.recyclerview.widget.u0 r1 = r0.W
            r2 = r1
            androidx.recyclerview.widget.m r2 = (androidx.recyclerview.widget.m) r2
            if (r10 == 0) goto L20
            r2.getClass()
            int r4 = r10.f5049a
            int r6 = r11.f5049a
            if (r4 != r6) goto L22
            int r1 = r10.f5050b
            int r3 = r11.f5050b
            if (r1 == r3) goto L20
            goto L22
        L20:
            r3 = r9
            goto L2c
        L22:
            int r5 = r10.f5050b
            int r7 = r11.f5050b
            r3 = r9
            boolean r9 = r2.g(r3, r4, r5, r6, r7)
            goto L3b
        L2c:
            r2.l(r3)
            android.view.View r9 = r3.itemView
            r10 = 0
            r9.setAlpha(r10)
            java.util.ArrayList r9 = r2.f4991i
            r9.add(r3)
            r9 = 1
        L3b:
            if (r9 == 0) goto L40
            r0.R()
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.j0.h(androidx.recyclerview.widget.p1, androidx.recyclerview.widget.t0, androidx.recyclerview.widget.t0):void");
    }

    public void i(p1 p1Var, t0 t0Var, t0 t0Var2) {
        boolean zG;
        RecyclerView recyclerView = this.f4952a;
        recyclerView.f4837m.l(p1Var);
        recyclerView.e(p1Var);
        p1Var.setIsRecyclable(false);
        m mVar = (m) recyclerView.W;
        mVar.getClass();
        int i10 = t0Var.f5049a;
        int i11 = t0Var.f5050b;
        View view = p1Var.itemView;
        int left = t0Var2 == null ? view.getLeft() : t0Var2.f5049a;
        int top = t0Var2 == null ? view.getTop() : t0Var2.f5050b;
        if (p1Var.isRemoved() || (i10 == left && i11 == top)) {
            mVar.l(p1Var);
            mVar.f4990h.add(p1Var);
            zG = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            zG = mVar.g(p1Var, i10, i11, left, top);
        }
        if (zG) {
            recyclerView.R();
        }
    }

    public void j(int i10) {
        RecyclerView recyclerView = this.f4952a;
        View childAt = recyclerView.getChildAt(i10);
        if (childAt != null) {
            p1 p1VarG = RecyclerView.G(childAt);
            l0 l0Var = recyclerView.f4856w;
            if (l0Var != null && p1VarG != null) {
                l0Var.onViewDetachedFromWindow(p1VarG);
            }
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i10);
    }
}
