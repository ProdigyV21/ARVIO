package n0;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewGroup;
import com.arflix.tv.MainActivity;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends kb.d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f20427m;

    public b(MainActivity mainActivity) {
        super(mainActivity, 11);
        this.f20427m = new a(this, mainActivity);
    }

    @Override // kb.d
    public final void l() {
        int i10;
        MainActivity mainActivity = (MainActivity) this.f19673l;
        Resources.Theme theme = mainActivity.getTheme();
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(R.attr.postSplashScreenTheme, typedValue, true) && (i10 = typedValue.resourceId) != 0) {
            mainActivity.setTheme(i10);
        }
        ((ViewGroup) mainActivity.getWindow().getDecorView()).setOnHierarchyChangeListener(this.f20427m);
    }
}
