package androidx.appcompat.widget;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class n3 implements TextWatcher {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SearchView f1670i;

    public n3(SearchView searchView) {
        this.f1670i = searchView;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        SearchView searchView = this.f1670i;
        Editable text = searchView.z.getText();
        searchView.f1463m0 = text;
        boolean zIsEmpty = TextUtils.isEmpty(text);
        searchView.u(!zIsEmpty);
        int i13 = 8;
        if (searchView.f1461k0 && !searchView.f1454d0 && zIsEmpty) {
            searchView.E.setVisibility(8);
            i13 = 0;
        }
        searchView.G.setVisibility(i13);
        searchView.q();
        searchView.t();
        if (searchView.U != null && !TextUtils.equals(charSequence, searchView.f1462l0)) {
            SearchView.b bVar = searchView.U;
            charSequence.toString();
            bVar.b();
        }
        searchView.f1462l0 = charSequence.toString();
    }
}
