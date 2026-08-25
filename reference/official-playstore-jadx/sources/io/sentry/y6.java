package io.sentry;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes4.dex */
public class y6 {
    static final w5 DEFAULT_DIAGNOSTIC_LEVEL = w5.DEBUG;
    private static final String DEFAULT_ENVIRONMENT = "production";
    public static final String DEFAULT_PROPAGATION_TARGETS = ".*";
    public static final long MAX_EVENT_SIZE_BYTES = 1048576;
    private boolean attachServerName;
    private boolean attachStacktrace;
    private boolean attachThreads;
    private io.sentry.backpressure.b backpressureMonitor;
    private h6 beforeBreadcrumb;
    private i6 beforeEnvelopeCallback;
    private j6 beforeSend;
    private j6 beforeSendFeedback;
    private k6 beforeSendReplay;
    private l6 beforeSendTransaction;
    private String cacheDirPath;
    private boolean captureOpenTelemetryEvents;
    io.sentry.clientreport.f clientReportRecorder;
    private l compositePerformanceCollector;
    private o0 connectionStatusProvider;
    private int connectionTimeoutMillis;
    private final List<String> contextTags;
    private p0 continuousProfiler;
    private m6 cron;
    private final io.sentry.util.k dateProvider;
    private long deadlineTimeout;
    private boolean debug;
    private io.sentry.internal.debugmeta.a debugMetaLoader;
    private g4 defaultScopeType;
    private final List<String> defaultTracePropagationTargets;
    private w5 diagnosticLevel;
    private String dist;
    private String distinctId;
    private n6 distribution;
    private q0 distributionController;
    private String dsn;
    private String dsnHash;
    private boolean enableAppStartProfiling;
    private boolean enableAutoSessionTracking;
    private boolean enableBackpressureHandling;
    private boolean enableCacheTracing;
    private boolean enableDatabaseTransactionTracing;
    private boolean enableDeduplication;
    private boolean enableEventSizeLimiting;
    private boolean enableExternalConfiguration;
    private boolean enablePrettySerializationOutput;
    private boolean enableScopePersistence;
    private boolean enableScreenTracking;
    private boolean enableShutdownHook;
    private boolean enableSpotlight;
    private boolean enableTimeToFullDisplayTracing;
    private boolean enableUncaughtExceptionHandler;
    private boolean enableUserInteractionBreadcrumbs;
    private boolean enableUserInteractionTracing;
    private boolean enabled;
    private io.sentry.cache.d envelopeDiskCache;
    private final io.sentry.util.k envelopeReader;
    private String environment;
    private g1 executorService;
    private final e0 experimental;
    private ILogger fatalLogger;
    private r5 feedbackOptions;
    private boolean forceInit;
    private i0 fullyDisplayedReporter;
    private final List<io.sentry.internal.gestures.a> gestureTargetLocators;
    private Boolean globalHubMode;
    private Long idleTimeout;
    private List<g0> ignoredCheckIns;
    private List<g0> ignoredSpanOrigins;
    private List<g0> ignoredTransactions;
    private final List<String> inAppExcludes;
    private final List<String> inAppIncludes;
    private q1 initPriority;
    private r1 instrumenter;
    private volatile t7 internalTracesSampler;
    protected final io.sentry.util.b lock;
    private ILogger logger;
    private p6 logs;
    private long maxAttachmentSize;
    private int maxBreadcrumbs;
    private int maxCacheItems;
    private int maxDepth;
    private int maxFeatureFlags;
    private int maxQueueSize;
    private w6 maxRequestBodySize;
    private int maxSpans;
    private long maxTraceFileSize;
    private r6 metrics;
    private io.sentry.internal.modules.a modulesLoader;
    private final List<b1> observers;
    private s6 onDiscard;
    private t6 onOversizedEvent;
    private f6 openTelemetryMode;
    private final List<u0> optionsObservers;
    private String orgId;
    private final io.sentry.util.k parsedDsn;
    private final List<v0> performanceCollectors;
    private boolean printUncaughtStackTrace;
    private s3 profileLifecycle;
    private Double profileSessionSampleRate;
    private y0 profilerConverter;
    private Double profilesSampleRate;
    private u6 profilesSampler;
    private String profilingTracesDirPath;
    private int profilingTracesHz;
    private String proguardUuid;
    private boolean propagateTraceparent;
    private v6 proxy;
    private int readTimeoutMillis;
    private String release;
    private w3 replayController;
    private Double sampleRate;
    private e1 scopesStorageFactory;
    private io.sentry.protocol.t sdkVersion;
    private boolean sendClientReports;
    private boolean sendDefaultPii;
    private boolean sendModules;
    private String sentryClientName;
    private final io.sentry.util.k serializer;
    private String serverName;
    private d7 sessionReplay;
    private long sessionTrackingIntervalMillis;
    private j1 socketTagger;
    private l1 spanFactory;
    private String spotlightConnectionUrl;
    private final AtomicBoolean spotlightIntegrationLoaded;
    private SSLSocketFactory sslSocketFactory;
    private boolean startProfilerOnAppStart;
    private boolean strictTraceContinuation;
    private final Map<String, String> tags;
    private io.sentry.util.thread.a threadChecker;
    private boolean traceOptionsRequests;
    private List<String> tracePropagationTargets;
    private boolean traceSampling;
    private Double tracesSampleRate;
    private x6 tracesSampler;
    private n1 transactionProfiler;
    private o1 transportFactory;
    private io.sentry.transport.h transportGate;
    private p1 versionDetector;
    private final List<io.sentry.internal.viewhierarchy.a> viewHierarchyExporters;
    private final List<d0> eventProcessors = new CopyOnWriteArrayList();
    private final Set<Class<? extends Throwable>> ignoredExceptionsForType = new CopyOnWriteArraySet();
    private List<g0> ignoredErrors = null;
    private final List<s1> integrations = new CopyOnWriteArrayList();
    private final Set<String> bundleIds = new CopyOnWriteArraySet();
    private long shutdownTimeoutMillis = 2000;
    private long flushTimeoutMillis = 15000;
    private long sessionFlushTimeoutMillis = 15000;

    /* JADX WARN: Removed duplicated region for block: B:19:0x02a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public y6(boolean r11) {
        /*
            Method dump skipped, instruction units count: 755
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.y6.<init>(boolean):void");
    }

    public static /* synthetic */ a0 a(y6 y6Var) {
        return new a0(y6Var.dsn);
    }

    public static /* synthetic */ b0 b(y6 y6Var) {
        return new b0((i1) y6Var.serializer.a());
    }

    public static y6 empty() {
        return new y6(true);
    }

    public void activate() {
        if (this.executorService instanceof c3) {
            n5 n5Var = new n5(this);
            this.executorService = n5Var;
            n5Var.c();
        }
        if (this.spotlightIntegrationLoaded.compareAndSet(false, true)) {
            try {
                this.integrations.add((s1) Class.forName("io.sentry.spotlight.SpotlightIntegration").getConstructor(null).newInstance(null));
            } catch (Throwable unused) {
            }
        }
    }

    public void addBundleId(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            this.bundleIds.add(strTrim);
        }
    }

    public void addContextTag(String str) {
        this.contextTags.add(str);
    }

    public void addEventProcessor(d0 d0Var) {
        this.eventProcessors.add(d0Var);
    }

    public void addIgnoredCheckIn(String str) {
        if (this.ignoredCheckIns == null) {
            this.ignoredCheckIns = new ArrayList();
        }
        this.ignoredCheckIns.add(new g0(str));
    }

    public void addIgnoredError(String str) {
        if (this.ignoredErrors == null) {
            this.ignoredErrors = new ArrayList();
        }
        this.ignoredErrors.add(new g0(str));
    }

    public void addIgnoredExceptionForType(Class<? extends Throwable> cls) {
        this.ignoredExceptionsForType.add(cls);
    }

    public void addIgnoredSpanOrigin(String str) {
        if (this.ignoredSpanOrigins == null) {
            this.ignoredSpanOrigins = new ArrayList();
        }
        this.ignoredSpanOrigins.add(new g0(str));
    }

    public void addIgnoredTransaction(String str) {
        if (this.ignoredTransactions == null) {
            this.ignoredTransactions = new ArrayList();
        }
        this.ignoredTransactions.add(new g0(str));
    }

    public void addInAppExclude(String str) {
        this.inAppExcludes.add(str);
    }

    public void addInAppInclude(String str) {
        this.inAppIncludes.add(str);
    }

    public void addIntegration(s1 s1Var) {
        this.integrations.add(s1Var);
    }

    public void addOptionsObserver(u0 u0Var) {
        this.optionsObservers.add(u0Var);
    }

    public void addPerformanceCollector(v0 v0Var) {
        this.performanceCollectors.add(v0Var);
    }

    public void addScopeObserver(b1 b1Var) {
        this.observers.add(b1Var);
    }

    public boolean containsIgnoredExceptionForType(Throwable th) {
        return this.ignoredExceptionsForType.contains(th.getClass());
    }

    public io.sentry.cache.g findPersistingScopeObserver() {
        for (b1 b1Var : this.observers) {
            if (b1Var instanceof io.sentry.cache.g) {
                return (io.sentry.cache.g) b1Var;
            }
        }
        return null;
    }

    public io.sentry.backpressure.b getBackpressureMonitor() {
        return this.backpressureMonitor;
    }

    public h6 getBeforeBreadcrumb() {
        return this.beforeBreadcrumb;
    }

    public i6 getBeforeEnvelopeCallback() {
        return this.beforeEnvelopeCallback;
    }

    public j6 getBeforeSend() {
        return this.beforeSend;
    }

    public j6 getBeforeSendFeedback() {
        return this.beforeSendFeedback;
    }

    public k6 getBeforeSendReplay() {
        return this.beforeSendReplay;
    }

    public l6 getBeforeSendTransaction() {
        return this.beforeSendTransaction;
    }

    public Set<String> getBundleIds() {
        return this.bundleIds;
    }

    public String getCacheDirPath() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.dsnHash != null ? new File(this.cacheDirPath, this.dsnHash).getAbsolutePath() : this.cacheDirPath;
    }

    public String getCacheDirPathWithoutDsn() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.cacheDirPath;
    }

    public io.sentry.clientreport.f getClientReportRecorder() {
        return this.clientReportRecorder;
    }

    public l getCompositePerformanceCollector() {
        return this.compositePerformanceCollector;
    }

    public o0 getConnectionStatusProvider() {
        return this.connectionStatusProvider;
    }

    public int getConnectionTimeoutMillis() {
        return this.connectionTimeoutMillis;
    }

    public List<String> getContextTags() {
        return this.contextTags;
    }

    public p0 getContinuousProfiler() {
        return this.continuousProfiler;
    }

    public m6 getCron() {
        return this.cron;
    }

    public a5 getDateProvider() {
        return (a5) this.dateProvider.a();
    }

    public long getDeadlineTimeout() {
        return this.deadlineTimeout;
    }

    public io.sentry.internal.debugmeta.a getDebugMetaLoader() {
        return this.debugMetaLoader;
    }

    public g4 getDefaultScopeType() {
        return this.defaultScopeType;
    }

    public w5 getDiagnosticLevel() {
        return this.diagnosticLevel;
    }

    public String getDist() {
        return this.dist;
    }

    public String getDistinctId() {
        return this.distinctId;
    }

    public n6 getDistribution() {
        return this.distribution;
    }

    public q0 getDistributionController() {
        return this.distributionController;
    }

    public String getDsn() {
        return this.dsn;
    }

    public String getEffectiveOrgId() {
        String str = this.orgId;
        if (str != null) {
            String strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        try {
            return retrieveParsedDsn().f16298d;
        } catch (Throwable unused) {
            return null;
        }
    }

    public io.sentry.cache.d getEnvelopeDiskCache() {
        return this.envelopeDiskCache;
    }

    public r0 getEnvelopeReader() {
        return (r0) this.envelopeReader.a();
    }

    public String getEnvironment() {
        String str = this.environment;
        return str != null ? str : DEFAULT_ENVIRONMENT;
    }

    public List<d0> getEventProcessors() {
        return this.eventProcessors;
    }

    public g1 getExecutorService() {
        return this.executorService;
    }

    public e0 getExperimental() {
        return this.experimental;
    }

    public ILogger getFatalLogger() {
        return this.fatalLogger;
    }

    public r5 getFeedbackOptions() {
        return this.feedbackOptions;
    }

    public long getFlushTimeoutMillis() {
        return this.flushTimeoutMillis;
    }

    public i0 getFullyDisplayedReporter() {
        return this.fullyDisplayedReporter;
    }

    public List<io.sentry.internal.gestures.a> getGestureTargetLocators() {
        return this.gestureTargetLocators;
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public List<g0> getIgnoredCheckIns() {
        return this.ignoredCheckIns;
    }

    public List<g0> getIgnoredErrors() {
        return this.ignoredErrors;
    }

    public Set<Class<? extends Throwable>> getIgnoredExceptionsForType() {
        return this.ignoredExceptionsForType;
    }

    public List<g0> getIgnoredSpanOrigins() {
        return this.ignoredSpanOrigins;
    }

    public List<g0> getIgnoredTransactions() {
        return this.ignoredTransactions;
    }

    public List<String> getInAppExcludes() {
        return this.inAppExcludes;
    }

    public List<String> getInAppIncludes() {
        return this.inAppIncludes;
    }

    public q1 getInitPriority() {
        return this.initPriority;
    }

    public r1 getInstrumenter() {
        return this.instrumenter;
    }

    public List<s1> getIntegrations() {
        return this.integrations;
    }

    public t7 getInternalTracesSampler() {
        if (this.internalTracesSampler == null) {
            io.sentry.util.a aVarA = this.lock.a();
            try {
                if (this.internalTracesSampler == null) {
                    this.internalTracesSampler = new t7(this);
                }
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
        return this.internalTracesSampler;
    }

    public ILogger getLogger() {
        return this.logger;
    }

    public p6 getLogs() {
        return this.logs;
    }

    public long getMaxAttachmentSize() {
        return this.maxAttachmentSize;
    }

    public int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    public int getMaxCacheItems() {
        return this.maxCacheItems;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public int getMaxFeatureFlags() {
        return this.maxFeatureFlags;
    }

    public int getMaxQueueSize() {
        return this.maxQueueSize;
    }

    public w6 getMaxRequestBodySize() {
        return this.maxRequestBodySize;
    }

    public int getMaxSpans() {
        return this.maxSpans;
    }

    public long getMaxTraceFileSize() {
        return this.maxTraceFileSize;
    }

    public r6 getMetrics() {
        return this.metrics;
    }

    public io.sentry.internal.modules.a getModulesLoader() {
        return this.modulesLoader;
    }

    public s6 getOnDiscard() {
        return this.onDiscard;
    }

    public t6 getOnOversizedEvent() {
        return this.onOversizedEvent;
    }

    public f6 getOpenTelemetryMode() {
        return this.openTelemetryMode;
    }

    public List<u0> getOptionsObservers() {
        return this.optionsObservers;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public String getOutboxPath() {
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "outbox").getAbsolutePath();
    }

    public List<v0> getPerformanceCollectors() {
        return this.performanceCollectors;
    }

    public s3 getProfileLifecycle() {
        return this.profileLifecycle;
    }

    public Double getProfileSessionSampleRate() {
        return this.profileSessionSampleRate;
    }

    public y0 getProfilerConverter() {
        return this.profilerConverter;
    }

    public Double getProfilesSampleRate() {
        return this.profilesSampleRate;
    }

    public u6 getProfilesSampler() {
        return this.profilesSampler;
    }

    public String getProfilingTracesDirPath() {
        String str = this.profilingTracesDirPath;
        if (str != null && !str.isEmpty()) {
            return this.dsnHash != null ? new File(this.profilingTracesDirPath, this.dsnHash).getAbsolutePath() : this.profilingTracesDirPath;
        }
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "profiling_traces").getAbsolutePath();
    }

    public int getProfilingTracesHz() {
        return this.profilingTracesHz;
    }

    public String getProguardUuid() {
        return this.proguardUuid;
    }

    public v6 getProxy() {
        return this.proxy;
    }

    public int getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public String getRelease() {
        return this.release;
    }

    public w3 getReplayController() {
        return this.replayController;
    }

    public Double getSampleRate() {
        return this.sampleRate;
    }

    public List<b1> getScopeObservers() {
        return this.observers;
    }

    public e1 getScopesStorageFactory() {
        return this.scopesStorageFactory;
    }

    public io.sentry.protocol.t getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSentryClientName() {
        return this.sentryClientName;
    }

    public i1 getSerializer() {
        return (i1) this.serializer.a();
    }

    public String getServerName() {
        return this.serverName;
    }

    public long getSessionFlushTimeoutMillis() {
        return this.sessionFlushTimeoutMillis;
    }

    public d7 getSessionReplay() {
        return this.sessionReplay;
    }

    public long getSessionTrackingIntervalMillis() {
        return this.sessionTrackingIntervalMillis;
    }

    public long getShutdownTimeoutMillis() {
        return this.shutdownTimeoutMillis;
    }

    public j1 getSocketTagger() {
        return this.socketTagger;
    }

    public l1 getSpanFactory() {
        return this.spanFactory;
    }

    public String getSpotlightConnectionUrl() {
        return this.spotlightConnectionUrl;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public io.sentry.util.thread.a getThreadChecker() {
        return this.threadChecker;
    }

    public List<String> getTracePropagationTargets() {
        List<String> list = this.tracePropagationTargets;
        return list == null ? this.defaultTracePropagationTargets : list;
    }

    public Double getTracesSampleRate() {
        return this.tracesSampleRate;
    }

    public x6 getTracesSampler() {
        return this.tracesSampler;
    }

    public n1 getTransactionProfiler() {
        return this.transactionProfiler;
    }

    public o1 getTransportFactory() {
        return this.transportFactory;
    }

    public io.sentry.transport.h getTransportGate() {
        return this.transportGate;
    }

    public p1 getVersionDetector() {
        return this.versionDetector;
    }

    public final List<io.sentry.internal.viewhierarchy.a> getViewHierarchyExporters() {
        return this.viewHierarchyExporters;
    }

    public boolean isAttachServerName() {
        return this.attachServerName;
    }

    public boolean isAttachStacktrace() {
        return this.attachStacktrace;
    }

    public boolean isAttachThreads() {
        return this.attachThreads;
    }

    public boolean isCaptureOpenTelemetryEvents() {
        return this.captureOpenTelemetryEvents;
    }

    public boolean isContinuousProfilingEnabled() {
        Double d4;
        return this.profilesSampleRate == null && this.profilesSampler == null && (d4 = this.profileSessionSampleRate) != null && d4.doubleValue() > 0.0d;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public boolean isEnableAppStartProfiling() {
        return (isProfilingEnabled() || isContinuousProfilingEnabled()) && this.enableAppStartProfiling;
    }

    public boolean isEnableAutoSessionTracking() {
        return this.enableAutoSessionTracking;
    }

    public boolean isEnableBackpressureHandling() {
        return this.enableBackpressureHandling;
    }

    public boolean isEnableCacheTracing() {
        return this.enableCacheTracing;
    }

    public boolean isEnableDatabaseTransactionTracing() {
        return this.enableDatabaseTransactionTracing;
    }

    public boolean isEnableDeduplication() {
        return this.enableDeduplication;
    }

    public boolean isEnableEventSizeLimiting() {
        return this.enableEventSizeLimiting;
    }

    public boolean isEnableExternalConfiguration() {
        return this.enableExternalConfiguration;
    }

    public boolean isEnablePrettySerializationOutput() {
        return this.enablePrettySerializationOutput;
    }

    public boolean isEnableScopePersistence() {
        return this.enableScopePersistence;
    }

    public boolean isEnableScreenTracking() {
        return this.enableScreenTracking;
    }

    public boolean isEnableShutdownHook() {
        return this.enableShutdownHook;
    }

    public boolean isEnableSpotlight() {
        return this.enableSpotlight;
    }

    public boolean isEnableTimeToFullDisplayTracing() {
        return this.enableTimeToFullDisplayTracing;
    }

    public boolean isEnableUncaughtExceptionHandler() {
        return this.enableUncaughtExceptionHandler;
    }

    public boolean isEnableUserInteractionBreadcrumbs() {
        return this.enableUserInteractionBreadcrumbs;
    }

    public boolean isEnableUserInteractionTracing() {
        return this.enableUserInteractionTracing;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isForceInit() {
        return this.forceInit;
    }

    public Boolean isGlobalHubMode() {
        return this.globalHubMode;
    }

    public boolean isPrintUncaughtStackTrace() {
        return this.printUncaughtStackTrace;
    }

    public boolean isProfilingEnabled() {
        Double d4 = this.profilesSampleRate;
        return (d4 != null && d4.doubleValue() > 0.0d) || this.profilesSampler != null;
    }

    public boolean isPropagateTraceparent() {
        return this.propagateTraceparent;
    }

    public boolean isSendClientReports() {
        return this.sendClientReports;
    }

    public boolean isSendDefaultPii() {
        return this.sendDefaultPii;
    }

    public boolean isSendModules() {
        return this.sendModules;
    }

    public boolean isStartProfilerOnAppStart() {
        return this.startProfilerOnAppStart;
    }

    public boolean isStrictTraceContinuation() {
        return this.strictTraceContinuation;
    }

    public boolean isTraceOptionsRequests() {
        return this.traceOptionsRequests;
    }

    public boolean isTraceSampling() {
        return this.traceSampling;
    }

    public boolean isTracingEnabled() {
        return (getTracesSampleRate() == null && getTracesSampler() == null) ? false : true;
    }

    public void loadLazyFields() {
        getSerializer();
        retrieveParsedDsn();
        getEnvelopeReader();
        getDateProvider();
    }

    public void merge(f0 f0Var) {
        String str = f0Var.f17253a;
        if (str != null) {
            setDsn(str);
        }
        String str2 = f0Var.f17254b;
        if (str2 != null) {
            setEnvironment(str2);
        }
        String str3 = f0Var.f17255c;
        if (str3 != null) {
            setRelease(str3);
        }
        String str4 = f0Var.f17256d;
        if (str4 != null) {
            setDist(str4);
        }
        String str5 = f0Var.f17257e;
        if (str5 != null) {
            setServerName(str5);
        }
        v6 v6Var = f0Var.f17265n;
        if (v6Var != null) {
            setProxy(v6Var);
        }
        Boolean bool = f0Var.f17258f;
        if (bool != null) {
            setEnableUncaughtExceptionHandler(bool.booleanValue());
        }
        Boolean bool2 = f0Var.f17275y;
        if (bool2 != null) {
            setPrintUncaughtStackTrace(bool2.booleanValue());
        }
        Double d4 = f0Var.f17261i;
        if (d4 != null) {
            setSampleRate(d4);
        }
        Double d10 = f0Var.f17262j;
        if (d10 != null) {
            setTracesSampleRate(d10);
        }
        Double d11 = f0Var.k;
        if (d11 != null) {
            setProfilesSampleRate(d11);
        }
        Boolean bool3 = f0Var.f17259g;
        if (bool3 != null) {
            setDebug(bool3.booleanValue());
        }
        Boolean bool4 = f0Var.f17260h;
        if (bool4 != null) {
            setEnableDeduplication(bool4.booleanValue());
        }
        Boolean bool5 = f0Var.z;
        if (bool5 != null) {
            setSendClientReports(bool5.booleanValue());
        }
        Boolean bool6 = f0Var.P;
        if (bool6 != null) {
            setForceInit(bool6.booleanValue());
        }
        for (Map.Entry entry : new HashMap(f0Var.f17264m).entrySet()) {
            this.tags.put((String) entry.getKey(), (String) entry.getValue());
        }
        Iterator it = new ArrayList(f0Var.f17267p).iterator();
        while (it.hasNext()) {
            addInAppInclude((String) it.next());
        }
        Iterator it2 = new ArrayList(f0Var.f17266o).iterator();
        while (it2.hasNext()) {
            addInAppExclude((String) it2.next());
        }
        Iterator it3 = new HashSet(f0Var.f17274w).iterator();
        while (it3.hasNext()) {
            addIgnoredExceptionForType((Class) it3.next());
        }
        if (f0Var.f17268q != null) {
            setTracePropagationTargets(new ArrayList(f0Var.f17268q));
        }
        Iterator it4 = new ArrayList(f0Var.f17269r).iterator();
        while (it4.hasNext()) {
            addContextTag((String) it4.next());
        }
        String str6 = f0Var.f17270s;
        if (str6 != null) {
            setProguardUuid(str6);
        }
        Long l10 = f0Var.f17271t;
        if (l10 != null) {
            setIdleTimeout(l10);
        }
        Long l11 = f0Var.f17272u;
        if (l11 != null) {
            setShutdownTimeoutMillis(l11.longValue());
        }
        Long l12 = f0Var.f17273v;
        if (l12 != null) {
            setSessionFlushTimeoutMillis(l12.longValue());
        }
        Iterator it5 = f0Var.A.iterator();
        while (it5.hasNext()) {
            addBundleId((String) it5.next());
        }
        Boolean bool7 = f0Var.B;
        if (bool7 != null) {
            setEnabled(bool7.booleanValue());
        }
        Boolean bool8 = f0Var.C;
        if (bool8 != null) {
            setEnablePrettySerializationOutput(bool8.booleanValue());
        }
        Boolean bool9 = f0Var.J;
        if (bool9 != null) {
            setSendModules(bool9.booleanValue());
        }
        if (f0Var.H != null) {
            setIgnoredCheckIns(new ArrayList(f0Var.H));
        }
        if (f0Var.I != null) {
            setIgnoredTransactions(new ArrayList(f0Var.I));
        }
        if (f0Var.x != null) {
            setIgnoredErrors(new ArrayList(f0Var.x));
        }
        Boolean bool10 = f0Var.L;
        if (bool10 != null) {
            setEnableBackpressureHandling(bool10.booleanValue());
        }
        Boolean bool11 = f0Var.M;
        if (bool11 != null) {
            setEnableDatabaseTransactionTracing(bool11.booleanValue());
        }
        Boolean bool12 = f0Var.N;
        if (bool12 != null) {
            setEnableCacheTracing(bool12.booleanValue());
        }
        w6 w6Var = f0Var.f17263l;
        if (w6Var != null) {
            setMaxRequestBodySize(w6Var);
        }
        Boolean bool13 = f0Var.K;
        if (bool13 != null) {
            setSendDefaultPii(bool13.booleanValue());
        }
        Boolean bool14 = f0Var.Q;
        if (bool14 != null) {
            setCaptureOpenTelemetryEvents(bool14.booleanValue());
        }
        Boolean bool15 = f0Var.D;
        if (bool15 != null) {
            setEnableSpotlight(bool15.booleanValue());
        }
        String str7 = f0Var.G;
        if (str7 != null) {
            setSpotlightConnectionUrl(str7);
        }
        Boolean bool16 = f0Var.O;
        if (bool16 != null) {
            setGlobalHubMode(bool16);
        }
        if (f0Var.W != null) {
            if (getCron() == null) {
                setCron(f0Var.W);
            } else {
                if (f0Var.W.f17473a != null) {
                    getCron().f17473a = f0Var.W.f17473a;
                }
                if (f0Var.W.f17474b != null) {
                    getCron().f17474b = f0Var.W.f17474b;
                }
                if (f0Var.W.f17475c != null) {
                    getCron().f17475c = f0Var.W.f17475c;
                }
                if (f0Var.W.f17476d != null) {
                    getCron().f17476d = f0Var.W.f17476d;
                }
                if (f0Var.W.f17477e != null) {
                    getCron().f17477e = f0Var.W.f17477e;
                }
            }
        }
        if (f0Var.E != null) {
            getLogs().f17516a = f0Var.E.booleanValue();
        }
        if (f0Var.F != null) {
            getMetrics().f17786a = f0Var.F.booleanValue();
        }
        Double d12 = f0Var.R;
        if (d12 != null) {
            setProfileSessionSampleRate(d12);
        }
        String str8 = f0Var.S;
        if (str8 != null) {
            setProfilingTracesDirPath(str8);
        }
        s3 s3Var = f0Var.T;
        if (s3Var != null) {
            setProfileLifecycle(s3Var);
        }
        Boolean bool17 = f0Var.U;
        if (bool17 != null) {
            setStrictTraceContinuation(bool17.booleanValue());
        }
        String str9 = f0Var.V;
        if (str9 != null) {
            setOrgId(str9);
        }
    }

    public a0 retrieveParsedDsn() throws IllegalArgumentException {
        return (a0) this.parsedDsn.a();
    }

    public void setAttachServerName(boolean z) {
        this.attachServerName = z;
    }

    public void setAttachStacktrace(boolean z) {
        this.attachStacktrace = z;
    }

    public void setAttachThreads(boolean z) {
        this.attachThreads = z;
    }

    public void setBackpressureMonitor(io.sentry.backpressure.b bVar) {
        this.backpressureMonitor = bVar;
    }

    public void setBeforeBreadcrumb(h6 h6Var) {
        this.beforeBreadcrumb = h6Var;
    }

    public void setBeforeEnvelopeCallback(i6 i6Var) {
        this.beforeEnvelopeCallback = i6Var;
    }

    public void setBeforeSend(j6 j6Var) {
        this.beforeSend = j6Var;
    }

    public void setBeforeSendFeedback(j6 j6Var) {
        this.beforeSendFeedback = j6Var;
    }

    public void setBeforeSendReplay(k6 k6Var) {
        this.beforeSendReplay = k6Var;
    }

    public void setBeforeSendTransaction(l6 l6Var) {
        this.beforeSendTransaction = l6Var;
    }

    public void setCacheDirPath(String str) {
        this.cacheDirPath = str;
    }

    public void setCaptureOpenTelemetryEvents(boolean z) {
        this.captureOpenTelemetryEvents = z;
    }

    public void setCompositePerformanceCollector(l lVar) {
        this.compositePerformanceCollector = lVar;
    }

    public void setConnectionStatusProvider(o0 o0Var) {
        this.connectionStatusProvider = o0Var;
    }

    public void setConnectionTimeoutMillis(int i10) {
        this.connectionTimeoutMillis = i10;
    }

    public void setContinuousProfiler(p0 p0Var) {
        if (this.continuousProfiler != q2.f17747i || p0Var == null) {
            return;
        }
        this.continuousProfiler = p0Var;
    }

    public void setCron(m6 m6Var) {
        this.cron = m6Var;
    }

    public void setDateProvider(a5 a5Var) {
        this.dateProvider.c(a5Var);
    }

    public void setDeadlineTimeout(long j10) {
        this.deadlineTimeout = j10;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void setDebugMetaLoader(io.sentry.internal.debugmeta.a aVar) {
        if (aVar == null) {
            aVar = io.sentry.internal.debugmeta.b.f17365i;
        }
        this.debugMetaLoader = aVar;
    }

    public void setDefaultScopeType(g4 g4Var) {
        this.defaultScopeType = g4Var;
    }

    public void setDiagnosticLevel(w5 w5Var) {
        if (w5Var == null) {
            w5Var = DEFAULT_DIAGNOSTIC_LEVEL;
        }
        this.diagnosticLevel = w5Var;
    }

    public void setDist(String str) {
        this.dist = str;
    }

    public void setDistinctId(String str) {
        this.distinctId = str;
    }

    public void setDistribution(n6 n6Var) {
        if (n6Var == null) {
            n6Var = new n6();
        }
        this.distribution = n6Var;
    }

    public void setDistributionController(q0 q0Var) {
        if (q0Var == null) {
            q0Var = r2.f17772i;
        }
        this.distributionController = q0Var;
    }

    public void setDsn(String str) {
        String string = null;
        this.dsn = str != null ? str.trim() : null;
        this.parsedDsn.b();
        String str2 = this.dsn;
        ILogger iLogger = this.logger;
        Charset charset = io.sentry.util.s.f17974a;
        if (str2 != null && !str2.isEmpty()) {
            try {
                string = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-1").digest(str2.getBytes(io.sentry.util.s.f17974a))).toString(16)).toString();
            } catch (NoSuchAlgorithmException e5) {
                iLogger.l(w5.INFO, "SHA-1 isn't available to calculate the hash.", e5);
            } catch (Throwable th) {
                iLogger.q(w5.INFO, "string: %s could not calculate its hash", th, str2);
            }
        }
        this.dsnHash = string;
    }

    public void setEnableAppStartProfiling(boolean z) {
        this.enableAppStartProfiling = z;
    }

    public void setEnableAutoSessionTracking(boolean z) {
        this.enableAutoSessionTracking = z;
    }

    public void setEnableBackpressureHandling(boolean z) {
        this.enableBackpressureHandling = z;
    }

    public void setEnableCacheTracing(boolean z) {
        this.enableCacheTracing = z;
    }

    public void setEnableDatabaseTransactionTracing(boolean z) {
        this.enableDatabaseTransactionTracing = z;
    }

    public void setEnableDeduplication(boolean z) {
        this.enableDeduplication = z;
    }

    public void setEnableEventSizeLimiting(boolean z) {
        this.enableEventSizeLimiting = z;
    }

    public void setEnableExternalConfiguration(boolean z) {
        this.enableExternalConfiguration = z;
    }

    public void setEnablePrettySerializationOutput(boolean z) {
        this.enablePrettySerializationOutput = z;
    }

    public void setEnableScopePersistence(boolean z) {
        this.enableScopePersistence = z;
    }

    public void setEnableScreenTracking(boolean z) {
        this.enableScreenTracking = z;
    }

    public void setEnableShutdownHook(boolean z) {
        this.enableShutdownHook = z;
    }

    public void setEnableSpotlight(boolean z) {
        this.enableSpotlight = z;
    }

    public void setEnableTimeToFullDisplayTracing(boolean z) {
        this.enableTimeToFullDisplayTracing = z;
    }

    public void setEnableUncaughtExceptionHandler(boolean z) {
        this.enableUncaughtExceptionHandler = z;
    }

    public void setEnableUserInteractionBreadcrumbs(boolean z) {
        this.enableUserInteractionBreadcrumbs = z;
    }

    public void setEnableUserInteractionTracing(boolean z) {
        this.enableUserInteractionTracing = z;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setEnvelopeDiskCache(io.sentry.cache.d dVar) {
        if (dVar == null) {
            dVar = io.sentry.transport.i.f17921i;
        }
        this.envelopeDiskCache = dVar;
    }

    public void setEnvelopeReader(r0 r0Var) {
        io.sentry.util.k kVar = this.envelopeReader;
        if (r0Var == null) {
            r0Var = s2.f17848a;
        }
        kVar.c(r0Var);
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setExecutorService(g1 g1Var) {
        if (g1Var != null) {
            this.executorService = g1Var;
        }
    }

    public void setFatalLogger(ILogger iLogger) {
        if (iLogger == null) {
            iLogger = u2.f17942i;
        }
        this.fatalLogger = iLogger;
    }

    public void setFeedbackOptions(r5 r5Var) {
        this.feedbackOptions = r5Var;
    }

    public void setFlushTimeoutMillis(long j10) {
        this.flushTimeoutMillis = j10;
    }

    public void setForceInit(boolean z) {
        this.forceInit = z;
    }

    public void setFullyDisplayedReporter(i0 i0Var) {
        this.fullyDisplayedReporter = i0Var;
    }

    public void setGestureTargetLocators(List<io.sentry.internal.gestures.a> list) {
        this.gestureTargetLocators.clear();
        this.gestureTargetLocators.addAll(list);
    }

    public void setGlobalHubMode(Boolean bool) {
        this.globalHubMode = bool;
    }

    public void setIdleTimeout(Long l10) {
        this.idleTimeout = l10;
    }

    public void setIgnoredCheckIns(List<String> list) {
        if (list == null) {
            this.ignoredCheckIns = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(new g0(str));
            }
        }
        this.ignoredCheckIns = arrayList;
    }

    public void setIgnoredErrors(List<String> list) {
        if (list == null) {
            this.ignoredErrors = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new g0(str));
            }
        }
        this.ignoredErrors = arrayList;
    }

    public void setIgnoredSpanOrigins(List<String> list) {
        if (list == null) {
            this.ignoredSpanOrigins = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new g0(str));
            }
        }
        this.ignoredSpanOrigins = arrayList;
    }

    public void setIgnoredTransactions(List<String> list) {
        if (list == null) {
            this.ignoredTransactions = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new g0(str));
            }
        }
        this.ignoredTransactions = arrayList;
    }

    public void setInitPriority(q1 q1Var) {
        this.initPriority = q1Var;
    }

    @Deprecated
    public void setInstrumenter(r1 r1Var) {
        this.instrumenter = r1Var;
    }

    public void setLogger(ILogger iLogger) {
        this.logger = iLogger == null ? u2.f17942i : new fi.iki.elonen.f(this, iLogger);
    }

    public void setLogs(p6 p6Var) {
        this.logs = p6Var;
    }

    public void setMaxAttachmentSize(long j10) {
        this.maxAttachmentSize = j10;
    }

    public void setMaxBreadcrumbs(int i10) {
        this.maxBreadcrumbs = i10;
    }

    public void setMaxCacheItems(int i10) {
        this.maxCacheItems = i10;
    }

    public void setMaxDepth(int i10) {
        this.maxDepth = i10;
    }

    public void setMaxFeatureFlags(int i10) {
        this.maxFeatureFlags = i10;
    }

    public void setMaxQueueSize(int i10) {
        if (i10 > 0) {
            this.maxQueueSize = i10;
        }
    }

    public void setMaxRequestBodySize(w6 w6Var) {
        this.maxRequestBodySize = w6Var;
    }

    public void setMaxSpans(int i10) {
        this.maxSpans = i10;
    }

    public void setMaxTraceFileSize(long j10) {
        this.maxTraceFileSize = j10;
    }

    public void setMetrics(r6 r6Var) {
        this.metrics = r6Var;
    }

    public void setModulesLoader(io.sentry.internal.modules.a aVar) {
        if (aVar == null) {
            aVar = io.sentry.internal.modules.e.f17382a;
        }
        this.modulesLoader = aVar;
    }

    public void setOnDiscard(s6 s6Var) {
        this.onDiscard = s6Var;
    }

    public void setOnOversizedEvent(t6 t6Var) {
        this.onOversizedEvent = t6Var;
    }

    public void setOpenTelemetryMode(f6 f6Var) {
        this.openTelemetryMode = f6Var;
    }

    public void setOrgId(String str) {
        this.orgId = str;
    }

    public void setPrintUncaughtStackTrace(boolean z) {
        this.printUncaughtStackTrace = z;
    }

    public void setProfileLifecycle(s3 s3Var) {
        this.profileLifecycle = s3Var;
        if (s3Var != s3.TRACE || isTracingEnabled()) {
            return;
        }
        this.logger.q(w5.WARNING, "Profiling lifecycle is set to TRACE but tracing is disabled. Profiling will not be started automatically.", new Object[0]);
    }

    public void setProfileSessionSampleRate(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.profileSessionSampleRate = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use values between 0.0 and 1.0.");
    }

    public void setProfilerConverter(y0 y0Var) {
        this.profilerConverter = y0Var;
    }

    public void setProfilesSampleRate(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.profilesSampleRate = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use null to disable or values between 0.0 and 1.0.");
    }

    public void setProfilesSampler(u6 u6Var) {
        this.profilesSampler = u6Var;
    }

    public void setProfilingTracesDirPath(String str) {
        this.profilingTracesDirPath = str;
    }

    public void setProfilingTracesHz(int i10) {
        this.profilingTracesHz = i10;
    }

    public void setProguardUuid(String str) {
        this.proguardUuid = str;
    }

    public void setPropagateTraceparent(boolean z) {
        this.propagateTraceparent = z;
    }

    public void setProxy(v6 v6Var) {
        this.proxy = v6Var;
    }

    public void setReadTimeoutMillis(int i10) {
        this.readTimeoutMillis = i10;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public void setReplayController(w3 w3Var) {
        if (w3Var == null) {
            w3Var = r2.f17773l;
        }
        this.replayController = w3Var;
    }

    public void setSampleRate(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.sampleRate = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
    }

    public void setScopesStorageFactory(e1 e1Var) {
        this.scopesStorageFactory = e1Var;
    }

    public void setSdkVersion(io.sentry.protocol.t tVar) {
        io.sentry.protocol.t tVar2 = getSessionReplay().f17225v;
        io.sentry.protocol.t tVar3 = this.sdkVersion;
        if (tVar3 != null && tVar2 != null && tVar3.equals(tVar2)) {
            getSessionReplay().f17225v = tVar;
        }
        this.sdkVersion = tVar;
    }

    public void setSendClientReports(boolean z) {
        this.sendClientReports = z;
        if (z) {
            this.clientReportRecorder = new fi.iki.elonen.f(this);
        } else {
            this.clientReportRecorder = new a1.a();
        }
    }

    public void setSendDefaultPii(boolean z) {
        this.sendDefaultPii = z;
    }

    public void setSendModules(boolean z) {
        this.sendModules = z;
    }

    public void setSentryClientName(String str) {
        this.sentryClientName = str;
    }

    public void setSerializer(i1 i1Var) {
        io.sentry.util.k kVar = this.serializer;
        if (i1Var == null) {
            i1Var = d3.f17211a;
        }
        kVar.c(i1Var);
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public void setSessionFlushTimeoutMillis(long j10) {
        this.sessionFlushTimeoutMillis = j10;
    }

    public void setSessionReplay(d7 d7Var) {
        this.sessionReplay = d7Var;
    }

    public void setSessionTrackingIntervalMillis(long j10) {
        this.sessionTrackingIntervalMillis = j10;
    }

    public void setShutdownTimeoutMillis(long j10) {
        this.shutdownTimeoutMillis = j10;
    }

    public void setSocketTagger(j1 j1Var) {
        if (j1Var == null) {
            j1Var = e3.f17230i;
        }
        this.socketTagger = j1Var;
    }

    public void setSpanFactory(l1 l1Var) {
        this.spanFactory = l1Var;
    }

    public void setSpotlightConnectionUrl(String str) {
        this.spotlightConnectionUrl = str;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
    }

    public void setStartProfilerOnAppStart(boolean z) {
        this.startProfilerOnAppStart = z;
    }

    public void setStrictTraceContinuation(boolean z) {
        this.strictTraceContinuation = z;
    }

    public void setTag(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            this.tags.remove(str);
        } else {
            this.tags.put(str, str2);
        }
    }

    public void setThreadChecker(io.sentry.util.thread.a aVar) {
        this.threadChecker = aVar;
    }

    public void setTraceOptionsRequests(boolean z) {
        this.traceOptionsRequests = z;
    }

    public void setTracePropagationTargets(List<String> list) {
        if (list == null) {
            this.tracePropagationTargets = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(str);
            }
        }
        this.tracePropagationTargets = arrayList;
    }

    @Deprecated
    public void setTraceSampling(boolean z) {
        this.traceSampling = z;
    }

    public void setTracesSampleRate(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.tracesSampleRate = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use null to disable or values between 0.0 and 1.0.");
    }

    public void setTracesSampler(x6 x6Var) {
        this.tracesSampler = x6Var;
    }

    public void setTransactionProfiler(n1 n1Var) {
        if (this.transactionProfiler != r2.f17774m || n1Var == null) {
            return;
        }
        this.transactionProfiler = n1Var;
    }

    public void setTransportFactory(o1 o1Var) {
        if (o1Var == null) {
            o1Var = i3.f17346i;
        }
        this.transportFactory = o1Var;
    }

    public void setTransportGate(io.sentry.transport.h hVar) {
        if (hVar == null) {
            hVar = io.sentry.transport.k.f17923a;
        }
        this.transportGate = hVar;
    }

    public void setVersionDetector(p1 p1Var) {
        this.versionDetector = p1Var;
    }

    public void setViewHierarchyExporters(List<io.sentry.internal.viewhierarchy.a> list) {
        this.viewHierarchyExporters.clear();
        this.viewHierarchyExporters.addAll(list);
    }
}
