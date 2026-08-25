package io.sentry.android.replay.util;

import android.os.Build;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class j {
    public static String a(h hVar) {
        if (Build.VERSION.SDK_INT < 31) {
            return "";
        }
        int i10 = i.f17054a[hVar.ordinal()];
        if (i10 == 1) {
            return Build.SOC_MODEL;
        }
        if (i10 == 2) {
            return Build.SOC_MANUFACTURER;
        }
        throw new NoWhenBranchMatchedException();
    }
}
