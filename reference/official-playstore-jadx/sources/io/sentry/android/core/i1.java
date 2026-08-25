package io.sentry.android.core;

import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.appcompat.widget.u4;
import io.sentry.l4;
import io.sentry.o0;
import io.sentry.p3;
import io.sentry.q3;
import io.sentry.w5;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class i1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16519i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16520l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16521m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16522n;

    public /* synthetic */ i1(SystemEventsBreadcrumbsIntegration systemEventsBreadcrumbsIntegration, io.sentry.c1 c1Var, SentryAndroidOptions sentryAndroidOptions) {
        this.f16519i = 4;
        this.f16520l = systemEventsBreadcrumbsIntegration;
        this.f16522n = c1Var;
        this.f16521m = sentryAndroidOptions;
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.lang.Runnable
    public final void run() {
        io.sentry.util.a aVarA;
        switch (this.f16519i) {
            case 0:
                SendCachedEnvelopeIntegration sendCachedEnvelopeIntegration = (SendCachedEnvelopeIntegration) this.f16520l;
                SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f16521m;
                io.sentry.c1 c1Var = (io.sentry.c1) this.f16522n;
                try {
                    if (sendCachedEnvelopeIntegration.f16365s.get()) {
                        sentryAndroidOptions.getLogger().q(w5.INFO, "SendCachedEnvelopeIntegration, not trying to send after closing.", new Object[0]);
                        return;
                    }
                    if (!sendCachedEnvelopeIntegration.f16364r.getAndSet(true)) {
                        io.sentry.o0 connectionStatusProvider = sentryAndroidOptions.getConnectionStatusProvider();
                        sendCachedEnvelopeIntegration.f16360n = connectionStatusProvider;
                        connectionStatusProvider.y0(sendCachedEnvelopeIntegration);
                        sendCachedEnvelopeIntegration.f16363q = sendCachedEnvelopeIntegration.f16357i.a(c1Var, sentryAndroidOptions);
                    }
                    io.sentry.o0 o0Var = sendCachedEnvelopeIntegration.f16360n;
                    if (o0Var != null && o0Var.M() == o0.a.DISCONNECTED) {
                        sentryAndroidOptions.getLogger().q(w5.INFO, "SendCachedEnvelopeIntegration, no connection.", new Object[0]);
                        return;
                    }
                    io.sentry.transport.p pVarF = c1Var.f();
                    if (pVarF != null && pVarF.j(io.sentry.m.All)) {
                        sentryAndroidOptions.getLogger().q(w5.INFO, "SendCachedEnvelopeIntegration, rate limiting active.", new Object[0]);
                        return;
                    }
                    l4 l4Var = sendCachedEnvelopeIntegration.f16363q;
                    if (l4Var == null) {
                        sentryAndroidOptions.getLogger().q(w5.ERROR, "SendCachedEnvelopeIntegration factory is null.", new Object[0]);
                        return;
                    } else {
                        l4Var.a();
                        return;
                    }
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed trying to send cached events.", th);
                    return;
                }
            case 1:
                u4 u4Var = (u4) this.f16520l;
                Runnable runnable = (Runnable) this.f16521m;
                String str = (String) this.f16522n;
                u4Var.getClass();
                try {
                    runnable.run();
                    return;
                } catch (Throwable unused) {
                    if (str != null) {
                        ((SentryAndroidOptions) u4Var.f1774b).getLogger().q(w5.WARNING, "Failed to execute ".concat(str), new Object[0]);
                        return;
                    }
                    return;
                }
            case 2:
                i iVar = (i) this.f16520l;
                y6 y6Var = (y6) this.f16521m;
                io.sentry.c1 c1Var2 = (io.sentry.c1) this.f16522n;
                ArrayList<p3> arrayList = iVar.f16516w;
                if (iVar.z.get()) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                aVarA = iVar.G.a();
                try {
                    for (p3 p3Var : arrayList) {
                        arrayList2.add(new q3(p3Var.f17508a, p3Var.f17509b, p3Var.f17511d, p3Var.f17510c, Double.valueOf(p3Var.f17512e), p3Var.f17513f, y6Var));
                    }
                    arrayList.clear();
                    aVarA.close();
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        c1Var2.l((q3) it.next());
                    }
                    return;
                } catch (Throwable th2) {
                }
            case 3:
                EnvelopeFileObserverIntegration envelopeFileObserverIntegration = (EnvelopeFileObserverIntegration) this.f16520l;
                y6 y6Var2 = (y6) this.f16521m;
                String str2 = (String) this.f16522n;
                aVarA = envelopeFileObserverIntegration.f16339n.a();
                try {
                    if (!envelopeFileObserverIntegration.f16338m) {
                        envelopeFileObserverIntegration.i(y6Var2, str2);
                        break;
                    }
                    aVarA.close();
                    return;
                } finally {
                    try {
                        aVarA.close();
                        throw th2;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                }
            default:
                SystemEventsBreadcrumbsIntegration systemEventsBreadcrumbsIntegration = (SystemEventsBreadcrumbsIntegration) this.f16520l;
                io.sentry.c1 c1Var3 = (io.sentry.c1) this.f16522n;
                SentryAndroidOptions sentryAndroidOptions2 = (SentryAndroidOptions) this.f16521m;
                aVarA = systemEventsBreadcrumbsIntegration.f16382u.a();
                try {
                    if (!systemEventsBreadcrumbsIntegration.f16377p && !systemEventsBreadcrumbsIntegration.f16378q && systemEventsBreadcrumbsIntegration.f16373l == null) {
                        systemEventsBreadcrumbsIntegration.f16373l = new w1(systemEventsBreadcrumbsIntegration, c1Var3, sentryAndroidOptions2);
                        if (systemEventsBreadcrumbsIntegration.f16379r == null) {
                            systemEventsBreadcrumbsIntegration.f16379r = new IntentFilter();
                            for (String str3 : systemEventsBreadcrumbsIntegration.f16376o) {
                                systemEventsBreadcrumbsIntegration.f16379r.addAction(str3);
                            }
                        }
                        if (systemEventsBreadcrumbsIntegration.f16380s == null) {
                            systemEventsBreadcrumbsIntegration.f16380s = new HandlerThread("SystemEventsReceiver", 10);
                            systemEventsBreadcrumbsIntegration.f16380s.start();
                        }
                        try {
                            q0.j(systemEventsBreadcrumbsIntegration.f16372i, new r0(sentryAndroidOptions2.getLogger()), systemEventsBreadcrumbsIntegration.f16373l, systemEventsBreadcrumbsIntegration.f16379r, new Handler(systemEventsBreadcrumbsIntegration.f16380s.getLooper()));
                            if (!systemEventsBreadcrumbsIntegration.f16381t.getAndSet(true)) {
                                sentryAndroidOptions2.getLogger().q(w5.DEBUG, "SystemEventsBreadcrumbsIntegration installed.", new Object[0]);
                                xc.d.H("SystemEventsBreadcrumbs");
                            }
                        } catch (Throwable th4) {
                            sentryAndroidOptions2.setEnableSystemEventBreadcrumbs(false);
                            sentryAndroidOptions2.getLogger().l(w5.ERROR, "Failed to initialize SystemEventsBreadcrumbsIntegration.", th4);
                        }
                        break;
                    }
                    aVarA.close();
                    return;
                } finally {
                }
        }
    }

    public /* synthetic */ i1(Object obj, Object obj2, Object obj3, int i10) {
        this.f16519i = i10;
        this.f16520l = obj;
        this.f16521m = obj2;
        this.f16522n = obj3;
    }
}
