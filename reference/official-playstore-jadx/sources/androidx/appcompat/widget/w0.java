package androidx.appcompat.widget;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class w0 implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1806i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1807l;

    public /* synthetic */ w0(Object obj, int i10) {
        this.f1806i = i10;
        this.f1807l = obj;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i10, long j10) {
        switch (this.f1806i) {
            case 0:
                y0 y0Var = (y0) this.f1807l;
                c1 c1Var = y0Var.Q;
                c1Var.setSelection(i10);
                if (c1Var.getOnItemClickListener() != null) {
                    c1Var.performItemClick(view, i10, y0Var.N.getItemId(i10));
                }
                y0Var.dismiss();
                break;
            default:
                ((SearchView) this.f1807l).m(i10);
                break;
        }
    }
}
