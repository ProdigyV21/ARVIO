package androidx.core.view;

import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d2 {
    public static int a(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledHoverSlop();
    }

    public static boolean b(ViewConfiguration viewConfiguration) {
        return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
}
