package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class u7 extends l7 {
    public static final io.sentry.protocol.h0 C = io.sentry.protocol.h0.CUSTOM;
    public io.sentry.protocol.h0 A;
    public androidx.core.provider.e B;
    public String z;

    public u7(String str, io.sentry.protocol.h0 h0Var, String str2, androidx.core.provider.e eVar) {
        super(new io.sentry.protocol.v(), new o7(), str2, null);
        ac.b.V(str, "name is required");
        this.z = str;
        this.A = h0Var;
        a(eVar);
        this.f17447w = com.google.common.util.concurrent.p0.i(null, eVar == null ? null : (Boolean) eVar.f2140l, eVar == null ? null : (Double) eVar.f2141m, eVar == null ? null : (Double) eVar.f2142n);
    }

    public static u7 b(androidx.appcompat.app.i1 i1Var) {
        i1Var.getClass();
        d dVar = (d) i1Var.f1061n;
        Double d4 = dVar.f17206c;
        u7 u7Var = new u7((io.sentry.protocol.v) i1Var.f1059l, (o7) i1Var.f1060m, "default", (o7) null);
        u7Var.z = "<unlabeled transaction>";
        u7Var.B = null;
        u7Var.A = C;
        u7Var.f17447w = com.google.common.util.concurrent.p0.i(dVar, null, null, null);
        return u7Var;
    }
}
