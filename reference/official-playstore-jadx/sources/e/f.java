package e;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.t;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.z0;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ViewGroup.LayoutParams f14947a = new ViewGroup.LayoutParams(-2, -2);

    public static void a(t tVar, ComposableLambda composableLambda) {
        View childAt = ((ViewGroup) tVar.getWindow().getDecorView().findViewById(R.id.content)).getChildAt(0);
        ComposeView composeView = childAt instanceof ComposeView ? (ComposeView) childAt : null;
        if (composeView != null) {
            composeView.setParentCompositionContext(null);
            composeView.setContent(composableLambda);
            return;
        }
        ComposeView composeView2 = new ComposeView(tVar, null, 0, 6, null);
        composeView2.setParentCompositionContext(null);
        composeView2.setContent(composableLambda);
        View decorView = tVar.getWindow().getDecorView();
        if (z0.e(decorView) == null) {
            z0.i(decorView, tVar);
        }
        if (z0.f(decorView) == null) {
            z0.j(decorView, tVar);
        }
        if (f4.f.k(decorView) == null) {
            f4.f.w(decorView, tVar);
        }
        tVar.setContentView(composeView2, f14947a);
    }
}
