package io.sentry;

import java.util.Iterator;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
public final class o extends TimerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17497i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f17498l;

    public /* synthetic */ o(Object obj, int i10) {
        this.f17497i = i10;
        this.f17498l = obj;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        switch (this.f17497i) {
            case 0:
                Iterator it = ((r) this.f17498l).f17766d.iterator();
                while (it.hasNext()) {
                    ((x0) it.next()).c();
                }
                break;
            case 1:
                io.sentry.android.core.a1 a1Var = (io.sentry.android.core.a1) this.f17498l;
                j4 j4Var = a1Var.f16401p;
                if (a1Var.f16402q) {
                    j4Var.m();
                }
                j4Var.getOptions().getReplayController().stop();
                j4Var.getOptions().getContinuousProfiler().a(false);
                break;
            default:
                io.sentry.transport.p pVar = (io.sentry.transport.p) this.f17498l;
                Iterator it2 = pVar.f17934n.iterator();
                while (it2.hasNext()) {
                    ((io.sentry.transport.o) it2.next()).k(pVar);
                }
                break;
        }
    }
}
