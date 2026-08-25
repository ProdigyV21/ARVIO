package io.sentry;

import io.sentry.android.core.SentryAndroidOptions;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d1 f17849a = a3.f16299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile c1 f17850b = y2.f18061b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c4 f17851c = new c4(y6.empty());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f17852d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Charset f17853e = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f17854f = System.currentTimeMillis();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final io.sentry.util.b f17855g = new io.sentry.util.b();

    public static void a() {
        io.sentry.util.a aVarA = f17855g.a();
        try {
            c1 c1VarB = b();
            f17850b = y2.f18061b;
            f17849a.close();
            c1VarB.a(false);
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static c1 b() {
        if (f17852d) {
            return f17850b;
        }
        c1 c1Var = f17849a.get();
        if (c1Var != null && !c1Var.p()) {
            return c1Var;
        }
        c1 c1VarA = f17850b.A("getCurrentScopes");
        f17849a.a(c1VarA);
        return c1VarA;
    }

    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Object, java.util.Collection] */
    public static void c(r2 r2Var, io.sentry.android.core.g gVar) {
        y6 y6Var = (y6) SentryAndroidOptions.class.getDeclaredConstructor(null).newInstance(null);
        try {
            gVar.c(y6Var);
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.ERROR, "Error in the 'OptionsConfiguration.configure' callback.", th);
        }
        io.sentry.util.a aVarA = f17855g.a();
        try {
            if (!y6Var.getClass().getName().equals("io.sentry.android.core.SentryAndroidOptions") && io.sentry.util.o.f17967a) {
                throw new IllegalArgumentException("You are running Android. Please, use SentryAndroid.init. ".concat(y6Var.getClass().getName()));
            }
            if (g(y6Var)) {
                Boolean boolIsGlobalHubMode = y6Var.isGlobalHubMode();
                int i10 = 1;
                boolean zBooleanValue = boolIsGlobalHubMode != null ? boolIsGlobalHubMode.booleanValue() : true;
                int i11 = 0;
                y6Var.getLogger().q(w5.INFO, "GlobalHubMode: '%s'", String.valueOf(zBooleanValue));
                f17852d = zBooleanValue;
                if (y6Var.getFatalLogger() instanceof u2) {
                    y6Var.setFatalLogger(new r2());
                }
                c4 c4Var = f17851c;
                if (t7.a.O(c4Var.f17129v, y6Var, f())) {
                    if (f()) {
                        y6Var.getLogger().q(w5.WARNING, "Sentry has been already initialized. Previous configuration will be overwritten.", new Object[0]);
                    }
                    y6Var.activate();
                    b().a(true);
                    c4Var.f17129v = y6Var;
                    ?? r32 = c4Var.f17124q;
                    c4Var.f17124q = c4.a(y6Var.getMaxBreadcrumbs());
                    Iterator it = r32.iterator();
                    while (it.hasNext()) {
                        c4Var.h((f) it.next(), null);
                    }
                    f17850b = new i4(new c4(y6Var), new c4(y6Var), c4Var);
                    if (y6Var.isDebug() && (y6Var.getLogger() instanceof u2)) {
                        y6Var.setLogger(new r2());
                    }
                    e(y6Var);
                    f17849a.a(f17850b);
                    d(y6Var);
                    c4Var.E = new com.google.android.gms.common.api.internal.g0(y6Var);
                    if (y6Var.getExecutorService().isClosed()) {
                        y6Var.setExecutorService(new n5(y6Var));
                        y6Var.getExecutorService().c();
                    }
                    try {
                        y6Var.getExecutorService().submit(new p4(y6Var, i11));
                    } catch (RejectedExecutionException e5) {
                        y6Var.getLogger().l(w5.DEBUG, "Failed to call the executor. Lazy fields will not be loaded. Did you call Sentry.close()?", e5);
                    }
                    try {
                        y6Var.getExecutorService().submit(new n2(y6Var));
                    } catch (Throwable th2) {
                        y6Var.getLogger().l(w5.DEBUG, "Failed to move previous session.", th2);
                    }
                    for (s1 s1Var : y6Var.getIntegrations()) {
                        try {
                            s1Var.l(y6Var);
                        } catch (Throwable th3) {
                            y6Var.getLogger().l(w5.WARNING, "Failed to register the integration " + s1Var.getClass().getName(), th3);
                        }
                    }
                    try {
                        y6Var.getExecutorService().submit(new p4(y6Var, 2));
                    } catch (Throwable th4) {
                        y6Var.getLogger().l(w5.DEBUG, "Failed to notify options observers.", th4);
                    }
                    try {
                        y6Var.getExecutorService().submit(new o3(y6Var));
                    } catch (Throwable th5) {
                        y6Var.getLogger().l(w5.DEBUG, "Failed to finalize previous session.", th5);
                    }
                    try {
                        y6Var.getExecutorService().submit(new p4(y6Var, i10));
                    } catch (Throwable th6) {
                        y6Var.getLogger().l(w5.ERROR, "Failed to call the executor. App start profiling config will not be changed. Did you call Sentry.close()?", th6);
                    }
                    ILogger logger = y6Var.getLogger();
                    w5 w5Var = w5.DEBUG;
                    logger.q(w5Var, "Using openTelemetryMode %s", y6Var.getOpenTelemetryMode());
                    y6Var.getLogger().q(w5Var, "Using span factory %s", y6Var.getSpanFactory().getClass().getName());
                    y6Var.getLogger().q(w5Var, "Using scopes storage %s", f17849a.getClass().getName());
                } else {
                    y6Var.getLogger().q(w5.WARNING, "This init call has been ignored due to priority being too low.", new Object[0]);
                }
            }
            aVarA.close();
        } catch (Throwable th7) {
            try {
                aVarA.close();
            } catch (Throwable th8) {
                th7.addSuppressed(th8);
            }
            throw th7;
        }
    }

    public static void d(y6 y6Var) {
        io.sentry.cache.d cVar;
        ILogger logger = y6Var.getLogger();
        w5 w5Var = w5.INFO;
        int i10 = 0;
        logger.q(w5Var, "Initializing SDK with DSN: '%s'", y6Var.getDsn());
        String outboxPath = y6Var.getOutboxPath();
        if (outboxPath != null) {
            new File(outboxPath).mkdirs();
        } else {
            logger.q(w5Var, "No outbox dir path is defined in options.", new Object[0]);
        }
        String cacheDirPath = y6Var.getCacheDirPath();
        if (cacheDirPath != null) {
            new File(cacheDirPath).mkdirs();
            if (y6Var.getEnvelopeDiskCache() instanceof io.sentry.transport.i) {
                Charset charset = io.sentry.cache.c.f17147s;
                String cacheDirPath2 = y6Var.getCacheDirPath();
                int maxCacheItems = y6Var.getMaxCacheItems();
                if (cacheDirPath2 == null) {
                    y6Var.getLogger().q(w5.WARNING, "cacheDirPath is null, returning NoOpEnvelopeCache", new Object[0]);
                    cVar = io.sentry.transport.i.f17921i;
                } else {
                    cVar = new io.sentry.cache.c(y6Var, cacheDirPath2, maxCacheItems);
                }
                y6Var.setEnvelopeDiskCache(cVar);
            }
        }
        String profilingTracesDirPath = y6Var.getProfilingTracesDirPath();
        if ((y6Var.isProfilingEnabled() || y6Var.isContinuousProfilingEnabled()) && profilingTracesDirPath != null) {
            File file = new File(profilingTracesDirPath);
            file.mkdirs();
            try {
                y6Var.getExecutorService().submit(new q4(file, i10));
            } catch (RejectedExecutionException e5) {
                y6Var.getLogger().l(w5.ERROR, "Failed to call the executor. Old profiles will not be deleted. Did you call Sentry.close()?", e5);
            }
        }
        io.sentry.internal.modules.a modulesLoader = y6Var.getModulesLoader();
        if (!y6Var.isSendModules()) {
            y6Var.setModulesLoader(io.sentry.internal.modules.e.f17382a);
        } else if (modulesLoader instanceof io.sentry.internal.modules.e) {
            y6Var.setModulesLoader(new io.sentry.internal.modules.f(Arrays.asList(new io.sentry.internal.modules.c(y6Var.getLogger()), new io.sentry.internal.modules.f(y6Var.getLogger())), y6Var.getLogger()));
        }
        if (y6Var.getDebugMetaLoader() instanceof io.sentry.internal.debugmeta.b) {
            y6Var.setDebugMetaLoader(new io.sentry.internal.debugmeta.c(y6Var.getLogger()));
        }
        List<Properties> listP = y6Var.getDebugMetaLoader().p();
        if (listP != null) {
            if (y6Var.getBundleIds().isEmpty()) {
                Iterator it = listP.iterator();
                while (it.hasNext()) {
                    String property = ((Properties) it.next()).getProperty("io.sentry.bundle-ids");
                    y6Var.getLogger().q(w5.DEBUG, "Bundle IDs found: %s", property);
                    if (property != null) {
                        for (String str : property.split(",", -1)) {
                            y6Var.addBundleId(str);
                        }
                    }
                }
            }
            if (y6Var.getProguardUuid() == null) {
                Iterator it2 = listP.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    String property2 = ((Properties) it2.next()).getProperty("io.sentry.ProguardUuids");
                    if (property2 != null) {
                        y6Var.getLogger().q(w5.DEBUG, "Proguard UUID found: %s", property2);
                        y6Var.setProguardUuid(property2);
                        break;
                    }
                }
            }
            Iterator it3 = listP.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Properties properties = (Properties) it3.next();
                String property3 = properties.getProperty("io.sentry.build-tool");
                if (property3 != null) {
                    String property4 = properties.getProperty("io.sentry.build-tool-version");
                    if (property4 == null) {
                        property4 = "unknown";
                    }
                    y6Var.getLogger().q(w5.DEBUG, "Build tool found: %s, version %s", property3, property4);
                    u5.d().b(property3, property4);
                }
            }
            for (Properties properties2 : listP) {
                String property5 = properties2.getProperty("io.sentry.distribution.org-slug");
                String property6 = properties2.getProperty("io.sentry.distribution.project-slug");
                String property7 = properties2.getProperty("io.sentry.distribution.auth-token");
                String property8 = properties2.getProperty("io.sentry.distribution.build-configuration");
                String property9 = properties2.getProperty("io.sentry.distribution.install-groups-override");
                if (property5 != null || property6 != null || property7 != null || property8 != null || property9 != null) {
                    n6 distribution = y6Var.getDistribution();
                    if (property5 != null && !property5.isEmpty() && distribution.f17493b.isEmpty()) {
                        y6Var.getLogger().q(w5.DEBUG, "Distribution org slug found: %s", property5);
                        distribution.f17493b = property5;
                    }
                    if (property6 != null && !property6.isEmpty() && distribution.f17494c.isEmpty()) {
                        y6Var.getLogger().q(w5.DEBUG, "Distribution project slug found: %s", property6);
                        distribution.f17494c = property6;
                    }
                    if (property7 != null && !property7.isEmpty() && distribution.f17492a.isEmpty()) {
                        y6Var.getLogger().q(w5.DEBUG, "Distribution org auth token found", new Object[0]);
                        distribution.f17492a = property7;
                    }
                    if (property8 != null && !property8.isEmpty() && distribution.f17495d == null) {
                        y6Var.getLogger().q(w5.DEBUG, "Distribution build configuration found: %s", property8);
                        distribution.f17495d = property8;
                    }
                    if (property9 != null && !property9.isEmpty() && distribution.f17496e == null) {
                        String[] strArrSplit = property9.split(",", -1);
                        ArrayList arrayList = new ArrayList();
                        for (String str2 : strArrSplit) {
                            String strTrim = str2.trim();
                            if (!strTrim.isEmpty()) {
                                arrayList.add(strTrim);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            y6Var.getLogger().q(w5.DEBUG, "Distribution install groups override found: %s", arrayList);
                            distribution.f17496e = arrayList;
                        }
                    }
                }
            }
        }
        if (y6Var.getThreadChecker() instanceof io.sentry.util.thread.b) {
            y6Var.setThreadChecker(io.sentry.util.thread.c.f17979b);
        }
        if (y6Var.getPerformanceCollectors().isEmpty()) {
            y6Var.addPerformanceCollector(new t1());
        }
        if (y6Var.isEnableBackpressureHandling() && !io.sentry.util.o.f17967a) {
            if (y6Var.getBackpressureMonitor() instanceof io.sentry.backpressure.c) {
                y6Var.setBackpressureMonitor(new io.sentry.backpressure.a(y6Var));
            }
            y6Var.getBackpressureMonitor().start();
        }
        if (!io.sentry.util.o.f17967a && y6Var.isContinuousProfilingEnabled() && (y6Var.getContinuousProfiler() instanceof q2)) {
            try {
                p0 p0VarU = com.google.common.util.concurrent.p0.u(y6Var.getLogger(), t7.a.y(y6Var), y6Var.getProfilingTracesHz(), y6Var.getExecutorService());
                if (p0VarU instanceof q2) {
                    y6Var.getLogger().q(w5.WARNING, "Could not load profiler, profiling will be disabled. If you are using Spring or Spring Boot with the OTEL Agent profiler init will be retried.", new Object[0]);
                } else {
                    y6Var.setContinuousProfiler(p0VarU);
                    y6Var.getLogger().q(w5.INFO, "Successfully loaded profiler", new Object[0]);
                }
            } catch (Exception e6) {
                y6Var.getLogger().l(w5.ERROR, "Failed to create default profiling traces directory", e6);
            }
            y6Var.getContinuousProfiler();
        } else {
            y6Var.getContinuousProfiler();
        }
        if (!io.sentry.util.o.f17967a && y6Var.isContinuousProfilingEnabled() && (y6Var.getProfilerConverter() instanceof v2)) {
            y0 y0VarA = v2.f17983a;
            ILogger logger2 = f17851c.f17129v.getLogger();
            try {
                Iterator it4 = ServiceLoader.load(io.sentry.profiling.b.class).iterator();
                io.sentry.profiling.b bVar = (io.sentry.profiling.b) (it4.hasNext() ? it4.next() : null);
                if (bVar != null) {
                    logger2.q(w5.DEBUG, "Loaded profile converter from provider: %s", bVar.getClass().getName());
                    y0VarA = bVar.a();
                } else {
                    logger2.q(w5.DEBUG, "No profile converter provider found, using NoOpProfileConverter", new Object[0]);
                }
            } catch (Throwable th) {
                logger2.l(w5.ERROR, "Failed to load profile converter provider, using NoOpProfileConverter", th);
            }
            if (y0VarA instanceof v2) {
                y6Var.getLogger().q(w5.WARNING, "Could not load profile converter. If you are using Spring or Spring Boot with the OTEL Agent, profile converter init will be retried.", new Object[0]);
            } else {
                y6Var.setProfilerConverter(y0VarA);
                y6Var.getLogger().q(w5.INFO, "Successfully loaded profile converter", new Object[0]);
            }
            y6Var.getProfilerConverter();
        } else {
            y6Var.getProfilerConverter();
        }
        y6Var.getLogger().q(w5.INFO, "Continuous profiler is enabled %s mode: %s", Boolean.valueOf(y6Var.isContinuousProfilingEnabled()), y6Var.getProfileLifecycle());
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(io.sentry.y6 r6) {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.s4.e(io.sentry.y6):void");
    }

    public static boolean f() {
        return b().isEnabled();
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0262 A[LOOP:4: B:101:0x025c->B:103:0x0262, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0286 A[LOOP:5: B:105:0x0280->B:107:0x0286, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x013d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018d A[LOOP:0: B:65:0x0187->B:67:0x018d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e3 A[LOOP:1: B:75:0x01dd->B:77:0x01e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ff A[LOOP:2: B:79:0x01f9->B:81:0x01ff, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x022b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean g(io.sentry.y6 r12) {
        /*
            Method dump skipped, instruction units count: 1078
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.s4.g(io.sentry.y6):boolean");
    }

    public static void h(String str, String str2) {
        b().g(str, str2);
    }

    public static void i(String str, String str2) {
        b().e(str, str2);
    }

    public static void j(io.sentry.protocol.i0 i0Var) {
        b().d(i0Var);
    }
}
