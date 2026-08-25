package androidx.appcompat.widget;

import android.app.Activity;
import android.os.Handler;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.w5;
import java.io.Serializable;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class u4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f1773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f1774b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1775c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1776d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1777e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Serializable f1778f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1779g;

    public void a(Activity activity) {
        io.sentry.util.a aVarA = ((io.sentry.util.b) this.f1778f).a();
        try {
            if (!c()) {
                aVarA.close();
                return;
            }
            d("FrameMetricsAggregator.add", new io.sentry.android.core.c(this, activity, 0));
            io.sentry.android.core.d dVarB = b();
            if (dVarB != null) {
                ((WeakHashMap) this.f1776d).put(activity, dVarB);
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public io.sentry.android.core.d b() {
        int i10;
        int i11;
        SparseIntArray sparseIntArray;
        if (!c() || !((Boolean) ((io.sentry.util.k) this.f1779g).a()).booleanValue()) {
            return null;
        }
        SparseIntArray[] sparseIntArrayArrK = ((FrameMetricsAggregator) ((io.sentry.util.k) this.f1773a).a()).f1949a.k();
        int i12 = 0;
        if (sparseIntArrayArrK == null || sparseIntArrayArrK.length <= 0 || (sparseIntArray = sparseIntArrayArrK[0]) == null) {
            i10 = 0;
            i11 = 0;
        } else {
            int i13 = 0;
            i10 = 0;
            i11 = 0;
            while (i12 < sparseIntArray.size()) {
                int iKeyAt = sparseIntArray.keyAt(i12);
                int iValueAt = sparseIntArray.valueAt(i12);
                i13 += iValueAt;
                if (iKeyAt > 700) {
                    i11 += iValueAt;
                } else if (iKeyAt > 16) {
                    i10 += iValueAt;
                }
                i12++;
            }
            i12 = i13;
        }
        return new io.sentry.android.core.d(i12, i10, i11);
    }

    public boolean c() {
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f1774b;
        return ((Boolean) ((io.sentry.util.k) this.f1779g).a()).booleanValue() && sentryAndroidOptions.isEnableFramesTracking() && !sentryAndroidOptions.isEnablePerformanceV2();
    }

    public void d(String str, Runnable runnable) {
        try {
            if (io.sentry.android.core.internal.util.e.f16600a.a()) {
                runnable.run();
                return;
            }
            io.sentry.android.core.r0 r0Var = (io.sentry.android.core.r0) this.f1777e;
            ((Handler) r0Var.f16735a).post(new io.sentry.android.core.i1(this, runnable, str, 1));
        } catch (Throwable unused) {
            if (str != null) {
                ((SentryAndroidOptions) this.f1774b).getLogger().q(w5.WARNING, "Failed to execute ".concat(str), new Object[0]);
            }
        }
    }
}
