package androidx.appcompat.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class s0 {
    public static int a(View view) {
        return view.getTextAlignment();
    }

    public static int b(View view) {
        return view.getTextDirection();
    }

    public static void c(View view, int i10) {
        view.setTextAlignment(i10);
    }

    public static void d(View view, int i10) {
        view.setTextDirection(i10);
    }
}
