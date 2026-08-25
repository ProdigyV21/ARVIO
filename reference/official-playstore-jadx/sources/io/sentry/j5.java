package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class j5 extends v4 implements k2, i2 {
    public io.sentry.protocol.n A;
    public String B;
    public f2 C;
    public f2 D;
    public w5 E;
    public String F;
    public List G;
    public ConcurrentHashMap H;
    public AbstractMap I;
    public Date z;

    public j5(Throwable th) {
        this();
        this.f17993t = th;
    }

    public final ArrayList d() {
        f2 f2Var = this.D;
        if (f2Var == null) {
            return null;
        }
        return (ArrayList) f2Var.f17276a;
    }

    public final ArrayList e() {
        f2 f2Var = this.C;
        if (f2Var != null) {
            return (ArrayList) f2Var.f17276a;
        }
        return null;
    }

    public final io.sentry.protocol.u f() {
        Boolean bool;
        f2 f2Var = this.D;
        if (f2Var == null) {
            return null;
        }
        for (io.sentry.protocol.u uVar : (ArrayList) f2Var.f17276a) {
            io.sentry.protocol.m mVar = uVar.f17705p;
            if (mVar != null && (bool = mVar.f17641n) != null && !bool.booleanValue()) {
                return uVar;
            }
        }
        return null;
    }

    public final boolean g() {
        f2 f2Var = this.D;
        return (f2Var == null || ((ArrayList) f2Var.f17276a).isEmpty()) ? false : true;
    }

    public final void h(List list) {
        this.G = list != null ? new ArrayList(list) : null;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.b0(iLogger, this.z);
        if (this.A != null) {
            fVar.S("message");
            fVar.b0(iLogger, this.A);
        }
        if (this.B != null) {
            fVar.S("logger");
            fVar.value(this.B);
        }
        f2 f2Var = this.C;
        if (f2Var != null && !((ArrayList) f2Var.f17276a).isEmpty()) {
            fVar.S("threads");
            fVar.j();
            fVar.S("values");
            fVar.b0(iLogger, (ArrayList) this.C.f17276a);
            fVar.K();
        }
        f2 f2Var2 = this.D;
        if (f2Var2 != null && !((ArrayList) f2Var2.f17276a).isEmpty()) {
            fVar.S("exception");
            fVar.j();
            fVar.S("values");
            fVar.b0(iLogger, (ArrayList) this.D.f17276a);
            fVar.K();
        }
        if (this.E != null) {
            fVar.S("level");
            fVar.b0(iLogger, this.E);
        }
        if (this.F != null) {
            fVar.S("transaction");
            fVar.value(this.F);
        }
        if (this.G != null) {
            fVar.S("fingerprint");
            fVar.b0(iLogger, this.G);
        }
        if (this.I != null) {
            fVar.S("modules");
            fVar.b0(iLogger, this.I);
        }
        m2.f0.K(this, fVar, iLogger);
        ConcurrentHashMap concurrentHashMap = this.H;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.H, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }

    public j5() {
        io.sentry.protocol.v vVar = new io.sentry.protocol.v();
        Date dateM = k2.c.m();
        super(vVar);
        this.z = dateM;
    }
}
