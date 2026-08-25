package androidx.recyclerview.widget;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4883a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4884b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4885c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4886d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4887e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4888f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4889g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4890h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4891i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4892j;
    public List k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4893l;

    public final void a(View view) {
        int layoutPosition;
        int size = this.k.size();
        View view2 = null;
        int i10 = Integer.MAX_VALUE;
        for (int i11 = 0; i11 < size; i11++) {
            View view3 = ((p1) this.k.get(i11)).itemView;
            a1 a1Var = (a1) view3.getLayoutParams();
            if (view3 != view && !a1Var.f4875a.isRemoved() && (layoutPosition = (a1Var.f4875a.getLayoutPosition() - this.f4886d) * this.f4887e) >= 0 && layoutPosition < i10) {
                view2 = view3;
                if (layoutPosition == 0) {
                    break;
                } else {
                    i10 = layoutPosition;
                }
            }
        }
        if (view2 == null) {
            this.f4886d = -1;
        } else {
            this.f4886d = ((a1) view2.getLayoutParams()).f4875a.getLayoutPosition();
        }
    }

    public final View b(f1 f1Var) {
        List list = this.k;
        if (list == null) {
            View view = f1Var.k(this.f4886d, Long.MAX_VALUE).itemView;
            this.f4886d += this.f4887e;
            return view;
        }
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            View view2 = ((p1) this.k.get(i10)).itemView;
            a1 a1Var = (a1) view2.getLayoutParams();
            if (!a1Var.f4875a.isRemoved() && this.f4886d == a1Var.f4875a.getLayoutPosition()) {
                a(view2);
                return view2;
            }
        }
        return null;
    }
}
