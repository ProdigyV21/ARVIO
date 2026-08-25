package androidx.core.view;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public abstract class q1 {
    public static int a(View view) {
        return view.getImportantForContentCapture();
    }

    public static CharSequence b(View view) {
        return view.getStateDescription();
    }

    public static boolean c(View view) {
        return view.isImportantForContentCapture();
    }

    public static void d(View view, int i10) {
        view.setImportantForContentCapture(i10);
    }

    public static void e(View view, CharSequence charSequence) {
        view.setStateDescription(charSequence);
    }
}
