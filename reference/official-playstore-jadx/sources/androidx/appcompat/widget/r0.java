package androidx.appcompat.widget;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public abstract class r0 {
    public static void a(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }
}
