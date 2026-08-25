package io.sentry.android.core.internal.gestures;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.Window;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.sentry.w5;
import io.sentry.y6;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Window.Callback f16548l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g f16549m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f16550n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y6 f16551o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h f16552p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile boolean f16553q;

    public j(Window.Callback callback, Activity activity, g gVar, y6 y6Var) {
        c cVar = new c(activity, gVar);
        h hVar = new h();
        super(callback);
        this.f16548l = callback;
        this.f16549m = gVar;
        this.f16551o = y6Var;
        this.f16550n = cVar;
        this.f16552p = hVar;
    }

    public final void a(MotionEvent motionEvent) {
        if (this.f16553q) {
            return;
        }
        c cVar = this.f16550n;
        int i10 = cVar.f16526c;
        g gVar = cVar.f16524a;
        io.sentry.util.a aVarA = cVar.f16535m.a();
        try {
            int actionMasked = motionEvent.getActionMasked();
            if (cVar.f16534l == null) {
                cVar.f16534l = VelocityTracker.obtain();
            }
            cVar.f16534l.addMovement(motionEvent);
            if (actionMasked == 0) {
                cVar.f16530g = motionEvent.getX();
                float y10 = motionEvent.getY();
                cVar.f16531h = y10;
                cVar.f16532i = cVar.f16530g;
                cVar.f16533j = y10;
                cVar.f16528e = true;
                cVar.f16529f = false;
                MotionEvent motionEvent2 = cVar.k;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                cVar.k = MotionEvent.obtain(motionEvent);
                gVar.onDown(motionEvent);
            } else if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float x = motionEvent.getX();
                    float y11 = motionEvent.getY();
                    float f10 = x - cVar.f16530g;
                    float f11 = y11 - cVar.f16531h;
                    if ((f11 * f11) + (f10 * f10) > cVar.f16525b) {
                        gVar.onScroll(cVar.k, motionEvent, cVar.f16532i - x, cVar.f16533j - y11);
                        cVar.f16528e = false;
                        cVar.f16532i = x;
                        cVar.f16533j = y11;
                    }
                } else if (actionMasked == 3) {
                    cVar.a();
                } else if (actionMasked == 5) {
                    cVar.f16528e = false;
                    cVar.f16529f = true;
                }
            } else if (cVar.f16529f) {
                cVar.a();
            } else {
                if (cVar.f16528e) {
                    gVar.onSingleTapUp(motionEvent);
                } else {
                    int pointerId = motionEvent.getPointerId(0);
                    cVar.f16534l.computeCurrentVelocity(1000, cVar.f16527d);
                    float xVelocity = cVar.f16534l.getXVelocity(pointerId);
                    float yVelocity = cVar.f16534l.getYVelocity(pointerId);
                    float f12 = i10;
                    if (Math.abs(xVelocity) > f12 || Math.abs(yVelocity) > f12) {
                        gVar.onFling(cVar.k, motionEvent, xVelocity, yVelocity);
                    }
                }
                cVar.a();
            }
            aVarA.close();
            if (motionEvent.getActionMasked() == 1) {
                g gVar2 = this.f16549m;
                View viewB = gVar2.b("onUp");
                f fVar = gVar2.f16547q;
                io.sentry.internal.gestures.c cVar2 = fVar.f16538b;
                if (viewB == null || cVar2 == null) {
                    return;
                }
                e eVar = fVar.f16537a;
                e eVar2 = e.Unknown;
                if (eVar == eVar2) {
                    gVar2.f16543m.getLogger().q(w5.DEBUG, "Unable to define scroll type. No breadcrumb captured.", new Object[0]);
                    return;
                }
                float x10 = motionEvent.getX() - fVar.f16539c;
                float y12 = motionEvent.getY() - fVar.f16540d;
                gVar2.a(cVar2, fVar.f16537a, Collections.singletonMap("direction", Math.abs(x10) > Math.abs(y12) ? x10 > 0.0f ? TtmlNode.RIGHT : TtmlNode.LEFT : y12 > 0.0f ? "down" : "up"), motionEvent);
                gVar2.c(cVar2, fVar.f16537a);
                fVar.f16538b = null;
                fVar.f16537a = eVar2;
                fVar.f16539c = 0.0f;
                fVar.f16540d = 0.0f;
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

    @Override // io.sentry.android.core.internal.gestures.l, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        y6 y6Var;
        if (motionEvent != null) {
            this.f16552p.getClass();
            try {
                a(MotionEvent.obtain(motionEvent));
            } finally {
                if (y6Var != null) {
                    try {
                    } finally {
                    }
                }
            }
        }
        return this.f16555i.dispatchTouchEvent(motionEvent);
    }
}
