package androidx.leanback.widget;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: loaded from: classes3.dex */
public final class w0 implements TextWatcher {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ v0 f3168i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3169l;

    public w0(SearchBar searchBar, v0 v0Var) {
        this.f3169l = searchBar;
        this.f3168i = v0Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        SearchBar searchBar = this.f3169l;
        Handler handler = searchBar.f3011s;
        if (searchBar.H) {
            return;
        }
        v0 v0Var = this.f3168i;
        handler.removeCallbacks(v0Var);
        handler.post(v0Var);
    }
}
