package androidx.leanback.widget;

import android.text.TextUtils;
import androidx.leanback.widget.SearchBar;

/* JADX INFO: loaded from: classes3.dex */
public final class y0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3180i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a1 f3181l;

    public /* synthetic */ y0(a1 a1Var, int i10) {
        this.f3180i = i10;
        this.f3181l = a1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SearchBar.a aVar;
        switch (this.f3180i) {
            case 0:
                SearchBar searchBar = this.f3181l.f3075a;
                if (!TextUtils.isEmpty(searchBar.f3007o) && (aVar = searchBar.f3003i) != null) {
                    aVar.b();
                    break;
                }
                break;
            default:
                SearchBar searchBar2 = this.f3181l.f3075a;
                searchBar2.f3013u = true;
                searchBar2.f3005m.requestFocus();
                break;
        }
    }
}
