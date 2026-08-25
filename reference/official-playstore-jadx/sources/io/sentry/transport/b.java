package io.sentry.transport;

import com.google.common.util.concurrent.r0;
import io.sentry.b5;
import io.sentry.j0;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z4;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final fi.iki.elonen.f f17903i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j0 f17904l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.cache.d f17905m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r f17906n = new r(-1);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ c f17907o;

    public b(c cVar, fi.iki.elonen.f fVar, j0 j0Var, io.sentry.cache.d dVar) {
        this.f17907o = cVar;
        ac.b.V(fVar, "Envelope is required.");
        this.f17903i = fVar;
        this.f17904l = j0Var;
        ac.b.V(dVar, "EnvelopeCache is required.");
        this.f17905m = dVar;
    }

    public static /* synthetic */ void a(b bVar, r0 r0Var, io.sentry.hints.o oVar) {
        bVar.f17907o.f17910m.getLogger().q(w5.DEBUG, "Marking envelope submission result: %s", Boolean.valueOf(r0Var.t()));
        oVar.c(r0Var.t());
    }

    public final r0 b() {
        Class cls;
        z4 z4VarA;
        fi.iki.elonen.f fVar = this.f17903i;
        ((b5) fVar.f15416i).f17103n = null;
        io.sentry.cache.d dVar = this.f17905m;
        j0 j0Var = this.f17904l;
        boolean zR = dVar.R(fVar, j0Var);
        Object objB = j0Var.b("sentry:typeCheckHint");
        boolean zIsInstance = io.sentry.hints.f.class.isInstance(j0Var.b("sentry:typeCheckHint"));
        c cVar = this.f17907o;
        if (zIsInstance && objB != null) {
            io.sentry.hints.f fVar2 = (io.sentry.hints.f) objB;
            y6 y6Var = cVar.f17910m;
            if (fVar2.c(((b5) fVar.f15416i).f17100i)) {
                fVar2.e();
                y6Var.getLogger().q(w5.DEBUG, "Disk flush envelope fired", new Object[0]);
            } else {
                y6Var.getLogger().q(w5.DEBUG, "Not firing envelope flush as there's an ongoing transaction", new Object[0]);
            }
        }
        y6 y6Var2 = cVar.f17910m;
        if (!cVar.f17912o.isConnected()) {
            Object objB2 = j0Var.b("sentry:typeCheckHint");
            boolean zIsInstance2 = io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint"));
            r rVar = this.f17906n;
            if (zIsInstance2 && objB2 != null) {
                ((io.sentry.hints.l) objB2).d(true);
                return rVar;
            }
            if (!zR) {
                a.a.V(io.sentry.hints.l.class, objB2, y6Var2.getLogger());
                y6Var2.getClientReportRecorder().v(io.sentry.clientreport.d.NETWORK_ERROR, fVar);
            }
            return rVar;
        }
        fi.iki.elonen.f fVarZ = y6Var2.getClientReportRecorder().z(fVar);
        try {
            z4VarA = y6Var2.getDateProvider().a();
            cls = io.sentry.hints.l.class;
        } catch (IOException e5) {
            e = e5;
            cls = io.sentry.hints.l.class;
        }
        try {
            ((b5) fVarZ.f15416i).f17103n = k2.c.n(Double.valueOf(z4VarA.i() / 1000000.0d).longValue());
            r0 r0VarD = cVar.f17913p.d(fVarZ);
            if (r0VarD.t()) {
                dVar.q0(fVar);
                return r0VarD;
            }
            String str = "The transport failed to send the envelope with response code " + r0VarD.p();
            y6Var2.getLogger().q(w5.ERROR, str, new Object[0]);
            if (r0VarD.p() >= 400) {
                dVar.q0(fVar);
                if (r0VarD.p() != 429) {
                    y6Var2.getClientReportRecorder().v(io.sentry.clientreport.d.SEND_ERROR, fVarZ);
                }
            }
            throw new IllegalStateException(str);
        } catch (IOException e6) {
            e = e6;
            Object objB3 = j0Var.b("sentry:typeCheckHint");
            Class cls2 = cls;
            if (cls2.isInstance(j0Var.b("sentry:typeCheckHint")) && objB3 != null) {
                ((io.sentry.hints.l) objB3).d(true);
            } else if (!zR) {
                a.a.V(cls2, objB3, y6Var2.getLogger());
                y6Var2.getClientReportRecorder().v(io.sentry.clientreport.d.NETWORK_ERROR, fVarZ);
            }
            throw new IllegalStateException("Sending the event failed.", e);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f17907o.f17914q = this;
        r0 r0VarB = this.f17906n;
        try {
            r0VarB = b();
            this.f17907o.f17910m.getLogger().q(w5.DEBUG, "Envelope flushed", new Object[0]);
        } catch (Throwable th) {
            try {
                this.f17907o.f17910m.getLogger().k(w5.ERROR, th, "Envelope submission failed", new Object[0]);
                throw th;
            } finally {
                j0 j0Var = this.f17904l;
                Object objB = j0Var.b("sentry:typeCheckHint");
                if (io.sentry.hints.o.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB != null) {
                    a(this, r0VarB, (io.sentry.hints.o) objB);
                }
                this.f17907o.f17914q = null;
            }
        }
    }
}
