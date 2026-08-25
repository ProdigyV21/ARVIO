package io.sentry.android.core;

import android.app.Activity;
import android.view.View;
import io.sentry.a7;
import io.sentry.w5;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class ScreenshotEventProcessor implements io.sentry.d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SentryAndroidOptions f16352i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f16353l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f16354m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f16355n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AtomicBoolean f16356o = new AtomicBoolean(false);

    public ScreenshotEventProcessor(SentryAndroidOptions sentryAndroidOptions, r0 r0Var, boolean z) {
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16352i = sentryAndroidOptions;
        this.f16353l = r0Var;
        this.f16354m = new io.sentry.android.core.internal.util.g(2000L, 3);
        this.f16355n = z;
        if (sentryAndroidOptions.isAttachScreenshot()) {
            xc.d.H("Screenshot");
        }
    }

    public final io.sentry.android.replay.viewhierarchy.g a(Activity activity) {
        SentryAndroidOptions sentryAndroidOptions = this.f16352i;
        try {
            View rootView = (activity.getWindow() == null || activity.getWindow().peekDecorView() == null || activity.getWindow().peekDecorView().getRootView() == null) ? null : activity.getWindow().peekDecorView().getRootView();
            if (rootView == null) {
                return null;
            }
            io.sentry.android.replay.viewhierarchy.g gVarQ = androidx.work.impl.t.q(rootView, null, sentryAndroidOptions.getScreenshot());
            io.sentry.android.replay.util.l.b(rootView, gVarQ, sentryAndroidOptions.getScreenshot(), sentryAndroidOptions.getLogger());
            return gVarQ;
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to build view hierarchy", th);
            return null;
        }
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, io.sentry.j0 j0Var) {
        return a7Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x015a  */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v8 */
    @Override // io.sentry.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.j5 j(io.sentry.j5 r14, io.sentry.j0 r15) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.ScreenshotEventProcessor.j(io.sentry.j5, io.sentry.j0):io.sentry.j5");
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var) {
        return e0Var;
    }
}
