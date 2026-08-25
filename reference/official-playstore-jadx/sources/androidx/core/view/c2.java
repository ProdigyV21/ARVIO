package androidx.core.view;

import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c2 {
    public static float a(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledHorizontalScrollFactor();
    }

    public static float b(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledVerticalScrollFactor();
    }
}
