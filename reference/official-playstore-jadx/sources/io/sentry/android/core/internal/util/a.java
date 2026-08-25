package io.sentry.android.core.internal.util;

import android.net.ConnectivityManager;
import io.sentry.android.core.h0;
import io.sentry.o0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c f16583l;

    public /* synthetic */ a(c cVar, int i10) {
        this.f16582i = i10;
        this.f16583l = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        io.sentry.util.a aVarA;
        io.sentry.util.a aVarA2;
        switch (this.f16582i) {
            case 0:
                c cVar = this.f16583l;
                cVar.T(true);
                aVarA = c.x.a();
                try {
                    c.f16587y.clear();
                    aVarA.close();
                    aVarA2 = c.f16585v.a();
                    try {
                        c.f16586w = null;
                        aVarA2.close();
                        h0.f16496o.l(cVar);
                        return;
                    } finally {
                        try {
                            aVarA2.close();
                            break;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                } finally {
                    try {
                        aVarA.close();
                        break;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            case 1:
                this.f16583l.l();
                return;
            case 2:
                this.f16583l.T(false);
                return;
            default:
                c cVar2 = this.f16583l;
                cVar2.V(null);
                o0.a aVarO = cVar2.o();
                if (aVarO == o0.a.DISCONNECTED) {
                    cVar2.f16598u.set(false);
                    aVarA = c.x.a();
                    try {
                        Iterator it = c.f16587y.iterator();
                        while (it.hasNext()) {
                            ((ConnectivityManager.NetworkCallback) it.next()).onLost(null);
                            break;
                        }
                        aVarA.close();
                    } finally {
                        try {
                            break;
                        } catch (Throwable th22) {
                        }
                    }
                }
                aVarA2 = cVar2.f16593p.a();
                try {
                    Iterator it2 = cVar2.f16592o.iterator();
                    while (it2.hasNext()) {
                        ((o0.b) it2.next()).i(aVarO);
                        break;
                    }
                    aVarA2.close();
                    cVar2.l();
                    return;
                } finally {
                    try {
                        break;
                    } catch (Throwable th3) {
                    }
                }
        }
    }
}
