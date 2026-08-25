package androidx.core.view;

import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e2 {
    public static int a(ViewConfiguration viewConfiguration, int i10, int i11, int i12) {
        return viewConfiguration.getScaledMaximumFlingVelocity(i10, i11, i12);
    }

    public static int b(ViewConfiguration viewConfiguration, int i10, int i11, int i12) {
        return viewConfiguration.getScaledMinimumFlingVelocity(i10, i11, i12);
    }
}
