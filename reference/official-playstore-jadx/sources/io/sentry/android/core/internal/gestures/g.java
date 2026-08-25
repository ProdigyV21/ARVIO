package io.sentry.android.core.internal.gestures;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import io.sentry.ILogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.o0;
import io.sentry.c1;
import io.sentry.j0;
import io.sentry.j4;
import io.sentry.m1;
import io.sentry.protocol.h0;
import io.sentry.q7;
import io.sentry.u7;
import io.sentry.w5;
import io.sentry.w7;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements GestureDetector.OnGestureListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final WeakReference f16541i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final c1 f16542l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SentryAndroidOptions f16543m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.internal.gestures.c f16544n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m1 f16545o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e f16546p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f f16547q;

    public g(Activity activity, j4 j4Var, SentryAndroidOptions sentryAndroidOptions) {
        e eVar = e.Unknown;
        this.f16546p = eVar;
        f fVar = new f();
        fVar.f16537a = eVar;
        fVar.f16539c = 0.0f;
        fVar.f16540d = 0.0f;
        this.f16547q = fVar;
        this.f16541i = new WeakReference(activity);
        this.f16542l = j4Var;
        this.f16543m = sentryAndroidOptions;
    }

    public final void a(io.sentry.internal.gestures.c cVar, e eVar, Map map, MotionEvent motionEvent) {
        if (this.f16543m.isEnableUserInteractionBreadcrumbs()) {
            int i10 = d.f16536a[eVar.ordinal()];
            String str = i10 != 1 ? i10 != 2 ? i10 != 3 ? "unknown" : "swipe" : "scroll" : "click";
            j0 j0Var = new j0();
            j0Var.d("android:motionEvent", motionEvent);
            j0Var.d("android:view", cVar.f17369a.get());
            String str2 = cVar.f17371c;
            String str3 = cVar.f17370b;
            io.sentry.f fVar = new io.sentry.f();
            fVar.f17247o = "user";
            fVar.f17249q = "ui.".concat(str);
            if (str2 != null) {
                fVar.f(str2, "view.id");
            }
            if (str3 != null) {
                fVar.f(str3, "view.class");
            }
            for (Map.Entry entry : map.entrySet()) {
                fVar.f17248p.put((String) entry.getKey(), entry.getValue());
            }
            fVar.f17251s = w5.INFO;
            this.f16542l.h(fVar, j0Var);
        }
    }

    public final View b(String str) {
        Activity activity = (Activity) this.f16541i.get();
        SentryAndroidOptions sentryAndroidOptions = this.f16543m;
        if (activity == null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, a0.c.l("Activity is null in ", str, ". No breadcrumb captured."), new Object[0]);
            return null;
        }
        Window window = activity.getWindow();
        if (window == null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, a0.c.l("Window is null in ", str, ". No breadcrumb captured."), new Object[0]);
            return null;
        }
        View viewPeekDecorView = window.peekDecorView();
        if (viewPeekDecorView != null) {
            return viewPeekDecorView;
        }
        sentryAndroidOptions.getLogger().q(w5.DEBUG, a0.c.l("DecorView is null in ", str, ". No breadcrumb captured."), new Object[0]);
        return null;
    }

    public final void c(io.sentry.internal.gestures.c cVar, e eVar) {
        boolean z = eVar == e.Click || !(eVar == this.f16546p && cVar.equals(this.f16544n));
        SentryAndroidOptions sentryAndroidOptions = this.f16543m;
        boolean zIsTracingEnabled = sentryAndroidOptions.isTracingEnabled();
        c1 c1Var = this.f16542l;
        if (!zIsTracingEnabled || !sentryAndroidOptions.isEnableUserInteractionTracing()) {
            if (z) {
                if (sentryAndroidOptions.isEnableAutoTraceIdGeneration()) {
                    c1Var.s(new o0(11));
                }
                this.f16544n = cVar;
                this.f16546p = eVar;
                return;
            }
            return;
        }
        Activity activity = (Activity) this.f16541i.get();
        if (activity == null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "Activity is null, no transaction captured.", new Object[0]);
            return;
        }
        String str = cVar.f17371c;
        if (str == null) {
            ac.b.V(null, "UiElement.tag can't be null");
            str = null;
        }
        m1 m1Var = this.f16545o;
        if (m1Var != null) {
            if (!z && !m1Var.isFinished()) {
                sentryAndroidOptions.getLogger().q(w5.DEBUG, a0.c.l("The view with id: ", str, " already has an ongoing transaction assigned. Rescheduling finish"), new Object[0]);
                if (sentryAndroidOptions.getIdleTimeout() != null) {
                    this.f16545o.m();
                    return;
                }
                return;
            }
            d(q7.OK);
        }
        String str2 = activity.getClass().getSimpleName() + "." + str;
        int i10 = d.f16536a[eVar.ordinal()];
        String strConcat = "ui.action.".concat(i10 != 1 ? i10 != 2 ? i10 != 3 ? "unknown" : "swipe" : "scroll" : "click");
        w7 w7Var = new w7();
        w7Var.f18041f = true;
        long deadlineTimeout = sentryAndroidOptions.getDeadlineTimeout();
        w7Var.f18043h = deadlineTimeout <= 0 ? null : Long.valueOf(deadlineTimeout);
        w7Var.f18042g = sentryAndroidOptions.getIdleTimeout();
        w7Var.f17518a = true;
        w7Var.f17521d = "auto.ui.gesture_listener." + cVar.f17372d;
        m1 m1VarU = c1Var.u(new u7(str2, h0.COMPONENT, strConcat, null), w7Var);
        c1Var.s(new androidx.media3.exoplayer.analytics.b(this, m1VarU, 18));
        this.f16545o = m1VarU;
        this.f16544n = cVar;
        this.f16546p = eVar;
    }

    public final void d(q7 q7Var) {
        m1 m1Var = this.f16545o;
        if (m1Var != null) {
            if (m1Var.getStatus() == null) {
                this.f16545o.i(q7Var);
            } else {
                this.f16545o.d();
            }
        }
        this.f16542l.s(new a3.b(this, 27));
        this.f16545o = null;
        if (this.f16544n != null) {
            this.f16544n = null;
        }
        this.f16546p = e.Unknown;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        f fVar = this.f16547q;
        fVar.f16538b = null;
        fVar.f16537a = e.Unknown;
        fVar.f16539c = 0.0f;
        fVar.f16540d = 0.0f;
        fVar.f16539c = motionEvent.getX();
        fVar.f16540d = motionEvent.getY();
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f10, float f11) {
        this.f16547q.f16537a = e.Swipe;
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f10, float f11) {
        View viewB = b("onScroll");
        if (viewB != null && motionEvent != null) {
            f fVar = this.f16547q;
            if (fVar.f16537a == e.Unknown) {
                float x = motionEvent.getX();
                float y10 = motionEvent.getY();
                io.sentry.internal.gestures.b bVar = io.sentry.internal.gestures.b.SCROLLABLE;
                SentryAndroidOptions sentryAndroidOptions = this.f16543m;
                io.sentry.internal.gestures.c cVarA = k.a(sentryAndroidOptions, viewB, x, y10, bVar);
                if (cVarA == null) {
                    sentryAndroidOptions.getLogger().q(w5.DEBUG, "Unable to find scroll target. No breadcrumb captured.", new Object[0]);
                    fVar.f16537a = e.Scroll;
                    return false;
                }
                ILogger logger = sentryAndroidOptions.getLogger();
                w5 w5Var = w5.DEBUG;
                StringBuilder sb2 = new StringBuilder("Scroll target found: ");
                String str = cVarA.f17371c;
                if (str == null) {
                    ac.b.V(null, "UiElement.tag can't be null");
                    str = null;
                }
                sb2.append(str);
                logger.q(w5Var, sb2.toString(), new Object[0]);
                fVar.f16538b = cVarA;
                fVar.f16537a = e.Scroll;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        View viewB = b("onSingleTapUp");
        if (viewB != null && motionEvent != null) {
            float x = motionEvent.getX();
            float y10 = motionEvent.getY();
            io.sentry.internal.gestures.b bVar = io.sentry.internal.gestures.b.CLICKABLE;
            SentryAndroidOptions sentryAndroidOptions = this.f16543m;
            io.sentry.internal.gestures.c cVarA = k.a(sentryAndroidOptions, viewB, x, y10, bVar);
            if (cVarA == null) {
                sentryAndroidOptions.getLogger().q(w5.DEBUG, "Unable to find click target. No breadcrumb captured.", new Object[0]);
                return false;
            }
            e eVar = e.Click;
            a(cVarA, eVar, Collections.EMPTY_MAP, motionEvent);
            c(cVarA, eVar);
        }
        return false;
    }
}
