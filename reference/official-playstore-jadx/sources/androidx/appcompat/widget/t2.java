package androidx.appcompat.widget;

import android.database.DataSetObserver;

/* JADX INFO: loaded from: classes.dex */
public final class t2 extends DataSetObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1748b;

    public /* synthetic */ t2(Object obj, int i10) {
        this.f1747a = i10;
        this.f1748b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        switch (this.f1747a) {
            case 0:
                w2 w2Var = (w2) this.f1748b;
                if (w2Var.I.isShowing()) {
                    w2Var.show();
                }
                break;
            default:
                c4 c4Var = (c4) this.f1748b;
                c4Var.f2406i = true;
                c4Var.notifyDataSetChanged();
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        switch (this.f1747a) {
            case 0:
                ((w2) this.f1748b).dismiss();
                break;
            default:
                c4 c4Var = (c4) this.f1748b;
                c4Var.f2406i = false;
                c4Var.notifyDataSetInvalidated();
                break;
        }
    }
}
