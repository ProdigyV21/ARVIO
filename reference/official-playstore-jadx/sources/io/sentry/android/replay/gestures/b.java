package io.sentry.android.replay.gestures;

import android.view.View;
import android.view.Window;
import androidx.work.impl.t;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.e0;
import io.sentry.android.replay.h;
import io.sentry.w5;
import io.sentry.y6;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f16966i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ReplayIntegration f16967l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f16968m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.b f16969n = new io.sentry.util.b();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final WeakHashMap f16970o = new WeakHashMap();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.util.b f16971p = new io.sentry.util.b();

    public b(y6 y6Var, ReplayIntegration replayIntegration) {
        this.f16966i = y6Var;
        this.f16967l = replayIntegration;
    }

    public final void a(View view) throws IllegalAccessException, InvocationTargetException {
        WeakHashMap weakHashMap = this.f16970o;
        Window windowR = a.a.R(view);
        y6 y6Var = this.f16966i;
        if (windowR == null) {
            y6Var.getLogger().q(w5.DEBUG, "Window is invalid, not tracking gestures", new Object[0]);
            return;
        }
        io.sentry.util.b bVar = this.f16971p;
        io.sentry.util.a aVarA = bVar.a();
        try {
            WeakReference weakReference = (WeakReference) weakHashMap.get(windowR);
            if ((weakReference != null ? (a) weakReference.get() : null) != null) {
                t.h(aVarA, null);
                return;
            }
            t.h(aVarA, null);
            a aVar = new a(y6Var, this.f16967l, windowR.getCallback());
            windowR.setCallback(aVar);
            aVarA = bVar.a();
            try {
                weakHashMap.put(windowR, new WeakReference(aVar));
                t.h(aVarA, null);
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final void b() throws IllegalAccessException, InvocationTargetException {
        ArrayList arrayList = this.f16968m;
        io.sentry.util.a aVarA = this.f16969n.a();
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                View view = (View) ((WeakReference) it.next()).get();
                if (view != null) {
                    c(view);
                }
            }
            arrayList.clear();
            t.h(aVarA, null);
        } finally {
        }
    }

    public final void c(View view) throws IllegalAccessException, InvocationTargetException {
        io.sentry.util.a aVarA;
        Window windowR = a.a.R(view);
        if (windowR == null) {
            this.f16966i.getLogger().q(w5.DEBUG, "Window was null in stopGestureTracking", new Object[0]);
            return;
        }
        Window.Callback callback = windowR.getCallback();
        if (callback instanceof a) {
            windowR.setCallback(((a) callback).f17042i);
            aVarA = this.f16971p.a();
            try {
                t.h(aVarA, null);
                return;
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        }
        aVarA = this.f16971p.a();
        try {
            WeakReference weakReference = (WeakReference) this.f16970o.get(windowR);
            a aVar = weakReference != null ? (a) weakReference.get() : null;
            t.h(aVarA, null);
            if (aVar != null) {
                aVar.f16965m = null;
            }
        } catch (Throwable th) {
        }
    }

    @Override // io.sentry.android.replay.h
    public final void i(View view, boolean z) throws IllegalAccessException, InvocationTargetException {
        io.sentry.util.a aVarA = this.f16969n.a();
        ArrayList arrayList = this.f16968m;
        try {
            if (z) {
                arrayList.add(new WeakReference(view));
                a(view);
            } else {
                c(view);
                x.M0(arrayList, new e0(view, 1));
            }
            t.h(aVarA, null);
        } finally {
        }
    }
}
