package io.sentry;

import java.net.InetAddress;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d5 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f17213b;

    public /* synthetic */ d5(Object obj, int i10) {
        this.f17212a = i10;
        this.f17213b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f17212a) {
            case 0:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 1:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 2:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 3:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 4:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 5:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 6:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 7:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 8:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 9:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 10:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 11:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 12:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 13:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 14:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 15:
                return ((fi.iki.elonen.f) this.f17213b).M();
            case 16:
                return Integer.valueOf(((fi.iki.elonen.f) this.f17213b).M().length);
            case 17:
                return ((fi.iki.elonen.f) this.f17213b).M();
            default:
                m0 m0Var = (m0) this.f17213b;
                try {
                    m0Var.f17466e.getClass();
                    m0Var.f17463b = InetAddress.getLocalHost().getCanonicalHostName();
                    m0Var.f17464c = System.currentTimeMillis() + m0Var.f17462a;
                    m0Var.f17465d.set(false);
                    return null;
                } catch (Throwable th) {
                    m0Var.f17465d.set(false);
                    throw th;
                }
        }
    }
}
