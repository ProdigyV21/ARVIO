package androidx.appcompat.widget;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class p2 implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1713i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1714l;

    public /* synthetic */ p2(Object obj, int i10) {
        this.f1713i = i10;
        this.f1714l = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
        g2 g2Var;
        switch (this.f1713i) {
            case 0:
                if (i10 != -1 && (g2Var = ((w2) this.f1714l).f1813m) != null) {
                    g2Var.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) this.f1714l).n(i10);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
        int i10 = this.f1713i;
    }

    private final void a(AdapterView adapterView) {
    }

    private final void b(AdapterView adapterView) {
    }
}
