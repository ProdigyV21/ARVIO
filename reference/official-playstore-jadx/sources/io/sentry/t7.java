package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class t7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f17900a;

    public t7(y6 y6Var) {
        this.f17900a = y6Var;
    }

    public final androidx.core.provider.e a(androidx.appcompat.app.i1 i1Var) {
        Double dA;
        Double dA2;
        Double d4 = (Double) i1Var.f1060m;
        u7 u7Var = (u7) i1Var.f1059l;
        androidx.core.provider.e eVar = u7Var.f17438n;
        if (eVar != null) {
            return androidx.work.impl.t.b(eVar);
        }
        y6 y6Var = this.f17900a;
        if (y6Var.getProfilesSampler() != null) {
            try {
                dA = y6Var.getProfilesSampler().a();
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "Error in the 'ProfilesSamplerCallback' callback.", th);
                dA = null;
            }
        } else {
            dA = null;
        }
        if (dA == null) {
            dA = y6Var.getProfilesSampleRate();
        }
        Double d10 = dA;
        Boolean boolValueOf = Boolean.valueOf(d10 != null && d10.doubleValue() >= d4.doubleValue());
        if (y6Var.getTracesSampler() != null) {
            try {
                dA2 = y6Var.getTracesSampler().a();
            } catch (Throwable th2) {
                y6Var.getLogger().l(w5.ERROR, "Error in the 'TracesSamplerCallback' callback.", th2);
                dA2 = null;
            }
            if (dA2 != null) {
                return new androidx.core.provider.e(Boolean.valueOf(dA2.doubleValue() >= d4.doubleValue()), dA2, d4, boolValueOf, d10);
            }
        }
        androidx.core.provider.e eVar2 = u7Var.B;
        if (eVar2 != null) {
            return androidx.work.impl.t.b(eVar2);
        }
        Double tracesSampleRate = y6Var.getTracesSampleRate();
        Double dValueOf = tracesSampleRate != null ? Double.valueOf(tracesSampleRate.doubleValue() / Math.pow(2.0d, y6Var.getBackpressureMonitor().a())) : null;
        if (dValueOf != null) {
            return new androidx.core.provider.e(Boolean.valueOf(dValueOf.doubleValue() >= d4.doubleValue()), dValueOf, d4, boolValueOf, d10);
        }
        Boolean bool = Boolean.FALSE;
        return new androidx.core.provider.e(bool, null, d4, bool, null);
    }
}
