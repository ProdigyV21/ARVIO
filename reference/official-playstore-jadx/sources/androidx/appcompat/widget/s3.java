package androidx.appcompat.widget;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class s3 implements View.OnKeyListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SearchView f1740i;

    public s3(SearchView searchView) {
        this.f1740i = searchView;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i10, KeyEvent keyEvent) {
        SearchView searchView = this.f1740i;
        SearchView.SearchAutoComplete searchAutoComplete = searchView.z;
        if (searchView.f1466p0 != null) {
            if (!searchAutoComplete.isPopupShowing() || searchAutoComplete.getListSelection() == -1) {
                if (TextUtils.getTrimmedLength(searchAutoComplete.getText()) != 0 && keyEvent.hasNoModifiers() && keyEvent.getAction() == 1 && i10 == 66) {
                    view.cancelLongPress();
                    searchView.getContext().startActivity(searchView.i("android.intent.action.SEARCH", null, null, searchAutoComplete.getText().toString()));
                    return true;
                }
            } else if (searchView.f1466p0 != null && searchView.f1455e0 != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
                if (i10 == 66 || i10 == 84 || i10 == 61) {
                    return searchView.m(searchAutoComplete.getListSelection());
                }
                if (i10 == 21 || i10 == 22) {
                    searchAutoComplete.setSelection(i10 == 21 ? 0 : searchAutoComplete.length());
                    searchAutoComplete.setListSelection(0);
                    searchAutoComplete.clearListSelection();
                    searchAutoComplete.a();
                    return true;
                }
                if (i10 == 19) {
                    searchAutoComplete.getListSelection();
                    return false;
                }
            }
        }
        return false;
    }
}
