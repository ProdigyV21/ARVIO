package androidx.core.view;

import android.view.KeyEvent;
import android.view.View;
import com.arvio.tv.R;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public abstract class o1 {
    public static void a(View view, final x1 x1Var) {
        t.l0 l0Var = (t.l0) view.getTag(R.id.tag_unhandled_key_listeners);
        if (l0Var == null) {
            l0Var = new t.l0(0);
            view.setTag(R.id.tag_unhandled_key_listeners, l0Var);
        }
        Objects.requireNonNull(x1Var);
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = new View.OnUnhandledKeyEventListener() { // from class: androidx.core.view.n1
            @Override // android.view.View.OnUnhandledKeyEventListener
            public final boolean onUnhandledKeyEvent(View view2, KeyEvent keyEvent) {
                return x1Var.a();
            }
        };
        l0Var.put(x1Var, onUnhandledKeyEventListener);
        view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean c(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean d(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void e(View view, x1 x1Var) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        t.l0 l0Var = (t.l0) view.getTag(R.id.tag_unhandled_key_listeners);
        if (l0Var == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) l0Var.get(x1Var)) == null) {
            return;
        }
        view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static <T> T f(View view, int i10) {
        return (T) view.requireViewById(i10);
    }

    public static void g(View view, boolean z) {
        view.setAccessibilityHeading(z);
    }

    public static void h(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    public static void i(View view, s0.a aVar) {
        view.setAutofillId(null);
    }

    public static void j(View view, boolean z) {
        view.setScreenReaderFocusable(z);
    }
}
