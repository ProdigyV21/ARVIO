package androidx.leanback.widget;

import androidx.leanback.widget.SearchBar;
import androidx.leanback.widget.SearchEditText;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 implements SearchEditText.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3176a;

    public x0(SearchBar searchBar) {
        this.f3176a = searchBar;
    }

    @Override // androidx.leanback.widget.SearchEditText.a
    public final void a() {
        SearchBar.a aVar = this.f3176a.f3003i;
        if (aVar != null) {
            aVar.a();
        }
    }
}
