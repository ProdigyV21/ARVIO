package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import com.arvio.tv.R;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class w2 extends a3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PathInterpolator f2329d = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j1.a f2330e = new j1.a(j1.a.f19040c);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DecelerateInterpolator f2331f = new DecelerateInterpolator();

    public static void d(View view, b3 b3Var) {
        t2 t2VarI = i(view);
        if (t2VarI != null) {
            t2VarI.onEnd(b3Var);
            if (t2VarI.getDispatchMode() == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
                d(viewGroup.getChildAt(i10), b3Var);
            }
        }
    }

    public static void e(View view, b3 b3Var, WindowInsets windowInsets, boolean z) {
        t2 t2VarI = i(view);
        if (t2VarI != null) {
            t2VarI.mDispachedInsets = windowInsets;
            if (!z) {
                t2VarI.onPrepare(b3Var);
                z = t2VarI.getDispatchMode() == 0;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
                e(viewGroup.getChildAt(i10), b3Var, windowInsets, z);
            }
        }
    }

    public static void f(View view, q3 q3Var, List list) {
        t2 t2VarI = i(view);
        if (t2VarI != null) {
            q3Var = t2VarI.onProgress(q3Var, list);
            if (t2VarI.getDispatchMode() == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
                f(viewGroup.getChildAt(i10), q3Var, list);
            }
        }
    }

    public static void g(View view, b3 b3Var, r2 r2Var) {
        t2 t2VarI = i(view);
        if (t2VarI != null) {
            t2VarI.onStart(b3Var, r2Var);
            if (t2VarI.getDispatchMode() == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
                g(viewGroup.getChildAt(i10), b3Var, r2Var);
            }
        }
    }

    public static WindowInsets h(View view, WindowInsets windowInsets) {
        return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
    }

    public static t2 i(View view) {
        Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
        if (tag instanceof v2) {
            return ((v2) tag).f2325a;
        }
        return null;
    }
}
