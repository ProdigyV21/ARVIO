package io.sentry.android.core.internal.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f16524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16525b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16526c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f16527d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f16528e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f16529f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f16530g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f16531h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f16532i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f16533j;
    public MotionEvent k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public VelocityTracker f16534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.b f16535m = new io.sentry.util.b();

    public c(Context context, g gVar) {
        this.f16524a = gVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.f16525b = scaledTouchSlop * scaledTouchSlop;
        this.f16526c = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f16527d = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public final void a() {
        io.sentry.util.a aVarA = this.f16535m.a();
        try {
            MotionEvent motionEvent = this.k;
            this.k = null;
            VelocityTracker velocityTracker = this.f16534l;
            this.f16534l = null;
            aVarA.close();
            if (motionEvent != null) {
                motionEvent.recycle();
            }
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
