package androidx.leanback.widget;

import android.os.Handler;
import android.view.KeyEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public final class a1 implements TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3075a;

    public a1(SearchBar searchBar) {
        this.f3075a = searchBar;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
        SearchBar searchBar = this.f3075a;
        Handler handler = searchBar.f3011s;
        if ((3 == i10 || i10 == 0) && searchBar.f3003i != null) {
            searchBar.a();
            handler.postDelayed(new y0(this, 0), 500L);
            return true;
        }
        if (1 == i10 && searchBar.f3003i != null) {
            searchBar.a();
            handler.postDelayed(new z0(this), 500L);
            return true;
        }
        if (2 != i10) {
            return false;
        }
        searchBar.a();
        handler.postDelayed(new y0(this, 1), 500L);
        return true;
    }
}
