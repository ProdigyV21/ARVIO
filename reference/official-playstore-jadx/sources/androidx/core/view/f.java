package androidx.core.view;

import android.R;
import android.content.ClipData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes3.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2228a;

    public f(View view) {
        this.f2228a = view;
    }

    public n a() {
        return ((i) this.f2228a).build();
    }

    public void b() {
        View view = (View) this.f2228a;
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void c(Bundle bundle) {
        ((i) this.f2228a).setExtras(bundle);
    }

    public void d(int i10) {
        ((i) this.f2228a).setFlags(i10);
    }

    public void e(Uri uri) {
        ((i) this.f2228a).a(uri);
    }

    public void f() {
        View viewFindViewById;
        View view = (View) this.f2228a;
        if (view == null) {
            return;
        }
        if (view.isInEditMode() || view.onCheckIsTextEditor()) {
            view.requestFocus();
            viewFindViewById = view;
        } else {
            viewFindViewById = view.getRootView().findFocus();
        }
        if (viewFindViewById == null) {
            viewFindViewById = view.getRootView().findViewById(R.id.content);
        }
        if (viewFindViewById == null || !viewFindViewById.hasWindowFocus()) {
            return;
        }
        viewFindViewById.post(new androidx.activity.n(viewFindViewById, 6));
    }

    public f(ClipData clipData, int i10) {
        if (Build.VERSION.SDK_INT >= 31) {
            this.f2228a = new h(clipData, i10);
            return;
        }
        j jVar = new j();
        jVar.f2268b = clipData;
        jVar.f2269c = i10;
        this.f2228a = jVar;
    }
}
