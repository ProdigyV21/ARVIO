package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class t4 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f17886i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Double f17887l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f17888m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Double f17889n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17890o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f17891p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f17892q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17893r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f17894s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f17895t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f17896u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public s3 f17897v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ConcurrentHashMap f17898w;

    public t4(y6 y6Var, androidx.core.provider.e eVar) {
        this.f17888m = ((Boolean) eVar.f2140l).booleanValue();
        this.f17889n = (Double) eVar.f2141m;
        this.f17886i = ((Boolean) eVar.f2143o).booleanValue();
        this.f17887l = (Double) eVar.f2144p;
        t7 internalTracesSampler = y6Var.getInternalTracesSampler();
        double dC = io.sentry.util.q.a().c();
        Double profileSessionSampleRate = internalTracesSampler.f17900a.getProfileSessionSampleRate();
        this.f17894s = profileSessionSampleRate != null && profileSessionSampleRate.doubleValue() >= dC;
        this.f17890o = y6Var.getProfilingTracesDirPath();
        this.f17891p = y6Var.isProfilingEnabled();
        this.f17892q = y6Var.isContinuousProfilingEnabled();
        this.f17897v = y6Var.getProfileLifecycle();
        this.f17893r = y6Var.getProfilingTracesHz();
        this.f17895t = y6Var.isEnableAppStartProfiling();
        this.f17896u = y6Var.isStartProfilerOnAppStart();
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("profile_sampled");
        fVar.b0(iLogger, Boolean.valueOf(this.f17886i));
        fVar.S("profile_sample_rate");
        fVar.b0(iLogger, this.f17887l);
        fVar.S("continuous_profile_sampled");
        fVar.b0(iLogger, Boolean.valueOf(this.f17894s));
        fVar.S("trace_sampled");
        fVar.b0(iLogger, Boolean.valueOf(this.f17888m));
        fVar.S("trace_sample_rate");
        fVar.b0(iLogger, this.f17889n);
        fVar.S("profiling_traces_dir_path");
        fVar.b0(iLogger, this.f17890o);
        fVar.S("is_profiling_enabled");
        fVar.b0(iLogger, Boolean.valueOf(this.f17891p));
        fVar.S("is_continuous_profiling_enabled");
        fVar.b0(iLogger, Boolean.valueOf(this.f17892q));
        fVar.S("profile_lifecycle");
        fVar.b0(iLogger, this.f17897v.name());
        fVar.S("profiling_traces_hz");
        fVar.b0(iLogger, Integer.valueOf(this.f17893r));
        fVar.S("is_enable_app_start_profiling");
        fVar.b0(iLogger, Boolean.valueOf(this.f17895t));
        fVar.S("is_start_profiler_on_app_start");
        fVar.b0(iLogger, Boolean.valueOf(this.f17896u));
        ConcurrentHashMap concurrentHashMap = this.f17898w;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17898w, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
