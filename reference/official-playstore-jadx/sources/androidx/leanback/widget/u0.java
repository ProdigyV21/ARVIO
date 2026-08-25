package androidx.leanback.widget;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 implements View.OnFocusChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3147i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3148l;

    public /* synthetic */ u0(SearchBar searchBar, int i10) {
        this.f3147i = i10;
        this.f3148l = searchBar;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        switch (this.f3147i) {
            case 0:
                SearchBar searchBar = this.f3148l;
                if (z) {
                    searchBar.f3011s.post(new v0(searchBar, 1));
                } else {
                    searchBar.a();
                }
                searchBar.e(z);
                break;
            default:
                SearchBar searchBar2 = this.f3148l;
                if (z) {
                    searchBar2.a();
                    if (searchBar2.f3013u) {
                        searchBar2.b();
                        searchBar2.f3013u = false;
                    }
                } else {
                    searchBar2.c();
                }
                searchBar2.e(z);
                break;
        }
    }
}
