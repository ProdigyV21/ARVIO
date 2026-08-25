package androidx.leanback.widget;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3076i;

    public b1(SearchBar searchBar) {
        this.f3076i = searchBar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SearchBar searchBar = this.f3076i;
        if (searchBar.H) {
            searchBar.c();
        } else {
            searchBar.b();
        }
    }
}
