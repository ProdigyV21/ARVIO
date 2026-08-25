package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;
import java.text.DecimalFormat;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.android.gms.common.api.internal.f1 f17203g = new com.google.android.gms.common.api.internal.f1(4);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap f17204a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.util.b f17205b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Double f17206c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Double f17207d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f17208e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ILogger f17209f;

    public d(ILogger iLogger) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.f17205b = new io.sentry.util.b();
        this.f17204a = concurrentHashMap;
        this.f17206c = null;
        this.f17207d = null;
        this.f17209f = iLogger;
        this.f17208e = true;
    }

    public final String a(String str) {
        return (String) this.f17204a.get(str);
    }

    public final void b(String str, String str2) {
        if (this.f17208e) {
            ConcurrentHashMap concurrentHashMap = this.f17204a;
            if (str2 == null) {
                concurrentHashMap.remove(str);
            } else {
                concurrentHashMap.put(str, str2);
            }
        }
    }

    public final void c(io.sentry.protocol.v vVar, io.sentry.protocol.v vVar2, y6 y6Var, androidx.core.provider.e eVar, String str, io.sentry.protocol.h0 h0Var) {
        b("sentry-trace_id", vVar.toString());
        b("sentry-public_key", y6Var.retrieveParsedDsn().f16296b);
        b("sentry-release", y6Var.getRelease());
        b("sentry-environment", y6Var.getEnvironment());
        if (h0Var == null || io.sentry.protocol.h0.URL.equals(h0Var)) {
            str = null;
        }
        b("sentry-transaction", str);
        if (vVar2 != null && !io.sentry.protocol.v.f17707l.equals(vVar2)) {
            b("sentry-replay_id", vVar2.toString());
        }
        b("sentry-org_id", y6Var.getEffectiveOrgId());
        Double d4 = eVar == null ? null : (Double) eVar.f2141m;
        if (this.f17208e) {
            this.f17206c = d4;
        }
        Boolean bool = eVar == null ? null : (Boolean) eVar.f2140l;
        b("sentry-sampled", bool == null ? null : bool.toString());
        Double d10 = eVar != null ? (Double) eVar.f2142n : null;
        if (this.f17208e) {
            this.f17207d = d10;
        }
    }

    public final s7 d() {
        String strA = a("sentry-trace_id");
        String strA2 = a("sentry-replay_id");
        String strA3 = a("sentry-public_key");
        if (strA == null || strA3 == null) {
            return null;
        }
        io.sentry.protocol.v vVar = new io.sentry.protocol.v(strA);
        String strA4 = a("sentry-release");
        String strA5 = a("sentry-environment");
        String strA6 = a("sentry-user_id");
        String strA7 = a("sentry-transaction");
        Double d4 = this.f17206c;
        boolean zX = androidx.work.impl.t.x(d4, false);
        com.google.android.gms.common.api.internal.f1 f1Var = f17203g;
        String str = !zX ? null : ((DecimalFormat) f1Var.get()).format(d4);
        String strA8 = a("sentry-sampled");
        io.sentry.protocol.v vVar2 = strA2 == null ? null : new io.sentry.protocol.v(strA2);
        Double d10 = this.f17207d;
        s7 s7Var = new s7(vVar, strA3, strA4, strA5, strA6, strA7, str, strA8, vVar2, androidx.work.impl.t.x(d10, false) ? ((DecimalFormat) f1Var.get()).format(d10) : null);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        io.sentry.util.a aVarA = this.f17205b.a();
        try {
            for (Map.Entry entry : this.f17204a.entrySet()) {
                String str2 = (String) entry.getKey();
                String str3 = (String) entry.getValue();
                if (!c.f17112a.contains(str2) && str3 != null) {
                    concurrentHashMap.put(str2.replaceFirst("sentry-", ""), str3);
                }
            }
            aVarA.close();
            s7Var.f17867u = concurrentHashMap;
            return s7Var;
        } finally {
        }
    }
}
