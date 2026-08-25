package io.sentry;

import io.ktor.http.LinkHeader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class a7 extends v4 implements k2, i2 {
    public int D;
    public Date F;
    public HashMap J;
    public File z;
    public io.sentry.protocol.v C = new io.sentry.protocol.v();
    public String A = "replay_event";
    public z6 B = z6.SESSION;
    public List H = new ArrayList();
    public List I = new ArrayList();
    public List G = new ArrayList();
    public Date E = k2.c.m();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && a7.class == obj.getClass()) {
            a7 a7Var = (a7) obj;
            if (this.D == a7Var.D && ac.b.C(this.A, a7Var.A) && this.B == a7Var.B && ac.b.C(this.C, a7Var.C) && ac.b.C(this.G, a7Var.G) && ac.b.C(this.H, a7Var.H) && ac.b.C(this.I, a7Var.I)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A, this.B, this.C, Integer.valueOf(this.D), this.G, this.H, this.I});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(LinkHeader.Parameters.Type);
        fVar.value(this.A);
        fVar.S("replay_type");
        fVar.b0(iLogger, this.B);
        fVar.S("segment_id");
        fVar.d(this.D);
        fVar.S("timestamp");
        fVar.b0(iLogger, this.E);
        if (this.C != null) {
            fVar.S("replay_id");
            fVar.b0(iLogger, this.C);
        }
        if (this.F != null) {
            fVar.S("replay_start_timestamp");
            fVar.b0(iLogger, this.F);
        }
        if (this.G != null) {
            fVar.S("urls");
            fVar.b0(iLogger, this.G);
        }
        if (this.H != null) {
            fVar.S("error_ids");
            fVar.b0(iLogger, this.H);
        }
        if (this.I != null) {
            fVar.S("trace_ids");
            fVar.b0(iLogger, this.I);
        }
        m2.f0.K(this, fVar, iLogger);
        HashMap map = this.J;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.J, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
