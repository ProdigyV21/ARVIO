package io.sentry.android.core;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import io.sentry.ILogger;
import io.sentry.a7;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.j5;
import io.sentry.w5;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class ViewHierarchyEventProcessor implements io.sentry.d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SentryAndroidOptions f16393i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f16394l;

    public ViewHierarchyEventProcessor(SentryAndroidOptions sentryAndroidOptions) {
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16393i = sentryAndroidOptions;
        this.f16394l = new io.sentry.android.core.internal.util.g(2000L, 3);
        if (sentryAndroidOptions.isAttachViewHierarchy()) {
            xc.d.H("ViewHierarchy");
        }
    }

    public static void a(View view, io.sentry.protocol.k0 k0Var, List list) {
        if (view instanceof ViewGroup) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((io.sentry.internal.viewhierarchy.a) it.next()).a()) {
                    return;
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(childCount);
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = viewGroup.getChildAt(i10);
                if (childAt != null) {
                    io.sentry.protocol.k0 k0VarB = b(childAt);
                    arrayList.add(k0VarB);
                    a(childAt, k0VarB, list);
                }
            }
            k0Var.f17632u = arrayList;
        }
    }

    public static io.sentry.protocol.k0 b(View view) {
        io.sentry.protocol.k0 k0Var = new io.sentry.protocol.k0();
        k0Var.f17623l = qb.l.p(view);
        try {
            k0Var.f17624m = io.sentry.android.core.internal.gestures.k.b(view);
        } catch (Throwable unused) {
        }
        k0Var.f17628q = Double.valueOf(view.getX());
        k0Var.f17629r = Double.valueOf(view.getY());
        k0Var.f17626o = Double.valueOf(view.getWidth());
        k0Var.f17627p = Double.valueOf(view.getHeight());
        k0Var.f17631t = Double.valueOf(view.getAlpha());
        int visibility = view.getVisibility();
        if (visibility == 0) {
            k0Var.f17630s = "visible";
        } else if (visibility == 4) {
            k0Var.f17630s = "invisible";
        } else if (visibility == 8) {
            k0Var.f17630s = "gone";
        }
        return k0Var;
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, io.sentry.j0 j0Var) {
        return a7Var;
    }

    @Override // io.sentry.d0
    public final j5 j(j5 j5Var, io.sentry.j0 j0Var) {
        if (j5Var.g()) {
            SentryAndroidOptions sentryAndroidOptions = this.f16393i;
            if (!sentryAndroidOptions.isAttachViewHierarchy()) {
                sentryAndroidOptions.getLogger().q(w5.DEBUG, "attachViewHierarchy is disabled.", new Object[0]);
                return j5Var;
            }
            if (!qb.l.u(j0Var)) {
                boolean zB = this.f16394l.b();
                SentryAndroidOptions.a beforeViewHierarchyCaptureCallback = sentryAndroidOptions.getBeforeViewHierarchyCaptureCallback();
                if (beforeViewHierarchyCaptureCallback == null ? !zB : beforeViewHierarchyCaptureCallback.execute()) {
                    WeakReference weakReference = (WeakReference) r0.f16734b.f16735a;
                    io.sentry.protocol.j0 j0Var2 = null;
                    Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
                    final List<io.sentry.internal.viewhierarchy.a> viewHierarchyExporters = sentryAndroidOptions.getViewHierarchyExporters();
                    io.sentry.util.thread.a threadChecker = sentryAndroidOptions.getThreadChecker();
                    final ILogger logger = sentryAndroidOptions.getLogger();
                    if (activity == null) {
                        logger.q(w5.INFO, "Missing activity for view hierarchy snapshot.", new Object[0]);
                    } else {
                        Window window = activity.getWindow();
                        if (window == null) {
                            logger.q(w5.INFO, "Missing window for view hierarchy snapshot.", new Object[0]);
                        } else {
                            final View viewPeekDecorView = window.peekDecorView();
                            if (viewPeekDecorView == null) {
                                logger.q(w5.INFO, "Missing decor view for view hierarchy snapshot.", new Object[0]);
                            } else {
                                try {
                                    if (threadChecker.a()) {
                                        ArrayList arrayList = new ArrayList(1);
                                        io.sentry.protocol.j0 j0Var3 = new io.sentry.protocol.j0("android_view_system", arrayList);
                                        io.sentry.protocol.k0 k0VarB = b(viewPeekDecorView);
                                        arrayList.add(k0VarB);
                                        a(viewPeekDecorView, k0VarB, viewHierarchyExporters);
                                        j0Var2 = j0Var3;
                                    } else {
                                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                                        final AtomicReference atomicReference = new AtomicReference(null);
                                        activity.runOnUiThread(new Runnable() { // from class: io.sentry.android.core.z1
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                AtomicReference atomicReference2 = atomicReference;
                                                View view = viewPeekDecorView;
                                                List list = viewHierarchyExporters;
                                                CountDownLatch countDownLatch2 = countDownLatch;
                                                try {
                                                    ArrayList arrayList2 = new ArrayList(1);
                                                    io.sentry.protocol.j0 j0Var4 = new io.sentry.protocol.j0("android_view_system", arrayList2);
                                                    io.sentry.protocol.k0 k0VarB2 = ViewHierarchyEventProcessor.b(view);
                                                    arrayList2.add(k0VarB2);
                                                    ViewHierarchyEventProcessor.a(view, k0VarB2, list);
                                                    atomicReference2.set(j0Var4);
                                                    countDownLatch2.countDown();
                                                } catch (Throwable th) {
                                                    logger.l(w5.ERROR, "Failed to process view hierarchy.", th);
                                                }
                                            }
                                        });
                                        if (countDownLatch.await(1000L, TimeUnit.MILLISECONDS)) {
                                            j0Var2 = (io.sentry.protocol.j0) atomicReference.get();
                                        }
                                    }
                                } catch (Throwable th) {
                                    logger.l(w5.ERROR, "Failed to process view hierarchy.", th);
                                }
                            }
                        }
                    }
                    if (j0Var2 != null) {
                        j0Var.f17394e = new io.sentry.a(j0Var2);
                    }
                }
            }
        }
        return j5Var;
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var) {
        return e0Var;
    }
}
