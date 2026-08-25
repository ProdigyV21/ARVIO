package io.ktor.server.engine;

import a0.c;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.installations.d;
import d7.j;
import d7.k;
import io.ktor.events.EventDefinition;
import io.ktor.events.Events;
import io.ktor.events.EventsKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.internal.AutoReloadUtilsKt;
import io.ktor.server.engine.internal.CallableUtilsKt;
import io.ktor.server.engine.internal.ReloadingException;
import io.ktor.util.Attributes;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import kotlin.text.u;
import r7.a;
import r7.l;
import vc.b;
import x6.c0;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 p2\u00020\u0001:\u0001pB~\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u001d\u0010\u0010\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f0\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019Bv\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u001d\u0010\u0010\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f0\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0018\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u000e¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001d\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001e\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020!H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0002H\u0002¢\u0006\u0004\b$\u0010%J%\u0010)\u001a\u00020\u000e2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\r0&2\u0006\u0010(\u001a\u00020\rH\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u000eH\u0002¢\u0006\u0004\b+\u0010\u001cJ\u001d\u0010.\u001a\u00020\u000e2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\tH\u0002¢\u0006\u0004\b.\u0010/J\u0017\u00101\u001a\u00020\r2\u0006\u00100\u001a\u00020\u0002H\u0002¢\u0006\u0004\b1\u00102J'\u00105\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u00022\u0006\u00104\u001a\u00020\rH\u0002¢\u0006\u0004\b5\u00106J\u001d\u00109\u001a\u00020\u000e2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000e07H\u0002¢\u0006\u0004\b9\u0010:J%\u0010<\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u00112\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000e07H\u0002¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u000eH\u0002¢\u0006\u0004\b>\u0010\u001cR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010?\u001a\u0004\b@\u0010%R\u001e\u0010\u0006\u001a\u00060\u0004j\u0002`\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010A\u001a\u0004\bB\u0010CR\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010D\u001a\u0004\bE\u0010FR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010G\u001a\u0004\bH\u0010IR1\u0010\u0010\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f0\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010G\u001a\u0004\bJ\u0010IR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010G\u001a\u0004\bK\u0010IR\u001a\u0010\u0015\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010L\u001a\u0004\bM\u0010NR\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010O\u001a\u0004\bP\u0010QR\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00110\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010GR\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010S\u001a\u0004\bT\u0010UR\u0018\u0010V\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010OR\u0018\u0010Y\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010?R\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u001c\u0010^\u001a\b\u0012\u0004\u0012\u00020]0\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010GR\u001a\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00110\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b_\u0010GR \u0010`\u001a\b\u0012\u0004\u0012\u00020\u00110\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b`\u0010G\u001a\u0004\ba\u0010IR\u001d\u0010g\u001a\u0004\u0018\u00010b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bc\u0010d\u001a\u0004\be\u0010fR\u001a\u0010i\u001a\u00020h8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bi\u0010j\u001a\u0004\bk\u0010lR\u0014\u0010(\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bm\u0010 R\u001a\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00110\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bn\u0010I¨\u0006q"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentReloading;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Ljava/lang/ClassLoader;", "classLoader", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "log", "Lio/ktor/server/config/ApplicationConfig;", "config", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "connectors", "Lkotlin/Function1;", "Lio/ktor/server/application/Application;", "Lx6/t0;", "Lx6/n;", "modules", "", "watchPaths", "Ld7/j;", "parentCoroutineContext", "rootPath", "", "developmentMode", "<init>", "(Ljava/lang/ClassLoader;Lvc/b;Lio/ktor/server/config/ApplicationConfig;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ld7/j;Ljava/lang/String;Z)V", "(Ljava/lang/ClassLoader;Lvc/b;Lio/ktor/server/config/ApplicationConfig;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ld7/j;Ljava/lang/String;)V", "reload", "()V", TtmlNode.START, "stop", "currentApplication", "()Lio/ktor/server/application/Application;", "Lx6/x;", "createApplication", "()Lx6/x;", "createClassLoader", "()Ljava/lang/ClassLoader;", "Lio/ktor/events/EventDefinition;", "event", MimeTypes.BASE_TYPE_APPLICATION, "safeRiseEvent", "(Lio/ktor/events/EventDefinition;Lio/ktor/server/application/Application;)V", "destroyApplication", "Ljava/net/URL;", "urls", "watchUrls", "(Ljava/util/List;)V", "currentClassLoader", "instantiateAndConfigureApplication", "(Ljava/lang/ClassLoader;)Lio/ktor/server/application/Application;", ContentDisposition.Parameters.Name, "newInstance", "launchModuleByName", "(Ljava/lang/String;Ljava/lang/ClassLoader;Lio/ktor/server/application/Application;)V", "Lkotlin/Function0;", "block", "avoidingDoubleStartup", "(Lr7/a;)V", "fqName", "avoidingDoubleStartupFor", "(Ljava/lang/String;Lr7/a;)V", "cleanupWatcher", "Ljava/lang/ClassLoader;", "getClassLoader", "Lvc/b;", "getLog", "()Lvc/b;", "Lio/ktor/server/config/ApplicationConfig;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "Ljava/util/List;", "getConnectors", "()Ljava/util/List;", "getModules$ktor_server_host_common", "getWatchPaths$ktor_server_host_common", "Ljava/lang/String;", "getRootPath", "()Ljava/lang/String;", "Z", "getDevelopmentMode", "()Z", "watchPatterns", "Ld7/j;", "getParentCoroutineContext", "()Ld7/j;", "_applicationInstance", "Lio/ktor/server/application/Application;", "recreateInstance", "_applicationClassLoader", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "applicationInstanceLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/nio/file/WatchKey;", "packageWatchKeys", "configModulesNames", "modulesNames", "getModulesNames$ktor_server_host_common", "Ljava/nio/file/WatchService;", "watcher$delegate", "Lx6/s;", "getWatcher", "()Ljava/nio/file/WatchService;", "watcher", "Lio/ktor/events/Events;", "monitor", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "getApplication", "getConfiguredWatchPath", "configuredWatchPath", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ApplicationEngineEnvironmentReloading implements ApplicationEngineEnvironment {
    private ClassLoader _applicationClassLoader;
    private Application _applicationInstance;
    private final ReentrantReadWriteLock applicationInstanceLock;
    private final ClassLoader classLoader;
    private final ApplicationConfig config;
    private final List<String> configModulesNames;
    private final List<EngineConnectorConfig> connectors;
    private final boolean developmentMode;
    private final b log;
    private final List<l<Application, t0>> modules;
    private final List<String> modulesNames;
    private final Events monitor;
    private List<? extends WatchKey> packageWatchKeys;
    private final j parentCoroutineContext;
    private boolean recreateInstance;
    private final String rootPath;
    private final List<String> watchPaths;
    private final List<String> watchPatterns;

    /* JADX INFO: renamed from: watcher$delegate, reason: from kotlin metadata */
    private final s watcher;

    /* JADX INFO: renamed from: io.ktor.server.engine.ApplicationEngineEnvironmentReloading$instantiateAndConfigureApplication$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements a<t0> {
        final /* synthetic */ ClassLoader $currentClassLoader;
        final /* synthetic */ Application $newInstance;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ClassLoader classLoader, Application application) {
            super(0);
            this.$currentClassLoader = classLoader;
            this.$newInstance = application;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6711invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6711invoke() {
            List<String> modulesNames$ktor_server_host_common = ApplicationEngineEnvironmentReloading.this.getModulesNames$ktor_server_host_common();
            ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading = ApplicationEngineEnvironmentReloading.this;
            ClassLoader classLoader = this.$currentClassLoader;
            Application application = this.$newInstance;
            Iterator<T> it = modulesNames$ktor_server_host_common.iterator();
            while (it.hasNext()) {
                applicationEngineEnvironmentReloading.launchModuleByName((String) it.next(), classLoader, application);
            }
            List<l<Application, t0>> modules$ktor_server_host_common = ApplicationEngineEnvironmentReloading.this.getModules$ktor_server_host_common();
            ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading2 = ApplicationEngineEnvironmentReloading.this;
            ClassLoader classLoader2 = this.$currentClassLoader;
            Application application2 = this.$newInstance;
            Iterator<T> it2 = modules$ktor_server_host_common.iterator();
            while (it2.hasNext()) {
                l lVar = (l) it2.next();
                try {
                    applicationEngineEnvironmentReloading2.launchModuleByName(ServerHostUtilsKt.methodName(lVar), classLoader2, application2);
                } catch (ReloadingException unused) {
                    lVar.invoke(application2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.ApplicationEngineEnvironmentReloading$launchModuleByName$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18721 extends r implements a<t0> {
        final /* synthetic */ ClassLoader $currentClassLoader;
        final /* synthetic */ String $name;
        final /* synthetic */ Application $newInstance;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18721(ClassLoader classLoader, String str, Application application) {
            super(0);
            this.$currentClassLoader = classLoader;
            this.$name = str;
            this.$newInstance = application;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6712invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6712invoke() {
            CallableUtilsKt.executeModuleFunction(ApplicationEngineEnvironmentReloading.this, this.$currentClassLoader, this.$name, this.$newInstance);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ApplicationEngineEnvironmentReloading(ClassLoader classLoader, b bVar, ApplicationConfig applicationConfig, List<? extends EngineConnectorConfig> list, List<? extends l<? super Application, t0>> list2, List<String> list3, j jVar, String str, boolean z) {
        List<String> list4;
        this.classLoader = classLoader;
        this.log = bVar;
        this.config = applicationConfig;
        this.connectors = list;
        this.modules = list2;
        this.watchPaths = list3;
        this.rootPath = str;
        this.developmentMode = z;
        ArrayList arrayListI0 = x.I0(getConfiguredWatchPath(), list3);
        this.watchPatterns = arrayListI0;
        if (getDevelopmentMode() && !arrayListI0.isEmpty()) {
            jVar = jVar.plus(ClassLoaderAwareContinuationInterceptor.INSTANCE);
        }
        this.parentCoroutineContext = jVar;
        this._applicationInstance = new Application(this);
        this.applicationInstanceLock = new ReentrantReadWriteLock();
        List<String> list5 = z.f19728i;
        this.packageWatchKeys = list5;
        ApplicationConfigValue applicationConfigValuePropertyOrNull = getConfig().propertyOrNull("ktor.application.modules");
        if (applicationConfigValuePropertyOrNull != null && (list4 = applicationConfigValuePropertyOrNull.getList()) != null) {
            list5 = list4;
        }
        this.configModulesNames = list5;
        this.modulesNames = list5;
        this.watcher = new i0(ApplicationEngineEnvironmentReloading$watcher$2.INSTANCE);
        this.monitor = new Events();
    }

    private final void avoidingDoubleStartup(a<t0> block) {
        try {
            block.invoke();
        } finally {
            List<String> list = AutoReloadUtilsKt.getCurrentStartupModules().get();
            if (list != null && list.isEmpty()) {
                AutoReloadUtilsKt.getCurrentStartupModules().remove();
            }
        }
    }

    private final void avoidingDoubleStartupFor(String fqName, a<t0> block) {
        ThreadLocal<List<String>> currentStartupModules = AutoReloadUtilsKt.getCurrentStartupModules();
        List<String> arrayList = currentStartupModules.get();
        if (arrayList == null) {
            arrayList = new ArrayList<>(1);
            currentStartupModules.set(arrayList);
        }
        List<String> list = arrayList;
        if (list.contains(fqName)) {
            throw new IllegalStateException(c.l("Module startup is already in progress for function ", fqName, " (recursive module startup from module main?)").toString());
        }
        list.add(fqName);
        try {
            block.invoke();
        } finally {
            list.remove(fqName);
        }
    }

    private final void cleanupWatcher() throws IOException {
        try {
            WatchService watcher = getWatcher();
            if (watcher != null) {
                watcher.close();
            }
        } catch (NoClassDefFoundError unused) {
        }
    }

    private final x6.x createApplication() throws IOException {
        ClassLoader classLoaderCreateClassLoader = createClassLoader();
        Thread threadCurrentThread = Thread.currentThread();
        ClassLoader contextClassLoader = threadCurrentThread.getContextClassLoader();
        threadCurrentThread.setContextClassLoader(classLoaderCreateClassLoader);
        try {
            return new x6.x(instantiateAndConfigureApplication(classLoaderCreateClassLoader), classLoaderCreateClassLoader);
        } finally {
            threadCurrentThread.setContextClassLoader(contextClassLoader);
        }
    }

    private final ClassLoader createClassLoader() throws IOException {
        ClassLoader classLoader = getClassLoader();
        if (!getDevelopmentMode()) {
            getLog().f("Autoreload is disabled because the development mode is off.");
            return classLoader;
        }
        List<String> list = this.watchPatterns;
        if (list.isEmpty()) {
            getLog().f("No ktor.deployment.watch patterns specified, automatic reload is not active.");
            return classLoader;
        }
        Set<URL> setAllURLs = ClassLoadersKt.allURLs(classLoader);
        String parent = new File(System.getProperty("java.home")).getParent();
        Set<URL> set = setAllURLs;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(((URL) it.next()).getFile());
        }
        getLog().d("Java Home: " + parent);
        b log = getLog();
        StringBuilder sb2 = new StringBuilder("Class Loader: ");
        sb2.append(classLoader);
        sb2.append(": ");
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!u.P(((String) obj).toString(), parent, false)) {
                arrayList2.add(obj);
            }
        }
        sb2.append(arrayList2);
        log.d(sb2.toString());
        List listE = t7.a.E(ApplicationEnvironment.class, ApplicationEngineEnvironment.class, Pipeline.class, HttpStatusCode.class, l.class, b.class, ByteReadChannel.class, Input.class, Attributes.class);
        HashSet hashSet = new HashSet();
        Iterator it2 = listE.iterator();
        while (it2.hasNext()) {
            URL location = ((Class) it2.next()).getProtectionDomain().getCodeSource().getLocation();
            if (location != null) {
                hashSet.add(location);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : set) {
            URL url = (URL) obj2;
            if (!hashSet.contains(url) && !list.isEmpty()) {
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    if (o.T(url.toString(), (String) it3.next(), false)) {
                        String path = url.getPath();
                        if (path == null) {
                            path = "";
                        }
                        if (!u.P(path, parent, false)) {
                            arrayList3.add(obj2);
                        }
                    }
                }
            }
        }
        if (arrayList3.isEmpty()) {
            getLog().f("No ktor.deployment.watch patterns match classpath entries, automatic reload is not active");
            return classLoader;
        }
        watchUrls(arrayList3);
        return new OverridingClassLoader(arrayList3, classLoader);
    }

    /* JADX WARN: Finally extract failed */
    private final Application currentApplication() {
        ReentrantReadWriteLock.ReadLock lock = this.applicationInstanceLock.readLock();
        lock.lock();
        try {
            Application application = this._applicationInstance;
            if (application == null) {
                throw new IllegalStateException("ApplicationEngineEnvironment was not started");
            }
            if (getDevelopmentMode()) {
                List<? extends WatchKey> list = this.packageWatchKeys;
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    x.b0(arrayList, d.h(it.next()).pollEvents());
                }
                if (!arrayList.isEmpty()) {
                    getLog().f("Changes in application detected.");
                    int size = arrayList.size();
                    while (true) {
                        Thread.sleep(200L);
                        List<? extends WatchKey> list2 = this.packageWatchKeys;
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<T> it2 = list2.iterator();
                        while (it2.hasNext()) {
                            x.b0(arrayList2, d.h(it2.next()).pollEvents());
                        }
                        if (arrayList2.isEmpty()) {
                            break;
                        }
                        getLog().d("Waiting for more changes.");
                        size += arrayList2.size();
                    }
                    getLog().d("Changes to " + size + " files caused application restart.");
                    for (WatchEvent watchEvent : x.X0(arrayList, 5)) {
                        getLog().d("...  " + watchEvent.context());
                    }
                    ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
                    ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
                    int i10 = 0;
                    int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                    for (int i11 = 0; i11 < readHoldCount; i11++) {
                        lock2.unlock();
                    }
                    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                    writeLock.lock();
                    try {
                        destroyApplication();
                        x6.x xVarCreateApplication = createApplication();
                        Application application2 = (Application) xVarCreateApplication.f22608i;
                        ClassLoader classLoader = (ClassLoader) xVarCreateApplication.f22609l;
                        this._applicationInstance = application2;
                        this._applicationClassLoader = classLoader;
                        while (i10 < readHoldCount) {
                            lock2.lock();
                            i10++;
                        }
                        writeLock.unlock();
                        application = this._applicationInstance;
                        if (application == null) {
                            throw new IllegalStateException("ApplicationEngineEnvironment was not started");
                        }
                    } catch (Throwable th) {
                        while (i10 < readHoldCount) {
                            lock2.lock();
                            i10++;
                        }
                        writeLock.unlock();
                        throw th;
                    }
                }
            }
            lock.unlock();
            return application;
        } catch (Throwable th2) {
            lock.unlock();
            throw th2;
        }
    }

    private final void destroyApplication() {
        Application application = this._applicationInstance;
        ClassLoader classLoader = this._applicationClassLoader;
        this._applicationInstance = null;
        this._applicationClassLoader = null;
        if (application != null) {
            safeRiseEvent(DefaultApplicationEventsKt.getApplicationStopping(), application);
            try {
                application.dispose();
                OverridingClassLoader overridingClassLoader = classLoader instanceof OverridingClassLoader ? (OverridingClassLoader) classLoader : null;
                if (overridingClassLoader != null) {
                    overridingClassLoader.close();
                }
            } catch (Throwable th) {
                getLog().c("Failed to destroy application instance.", th);
            }
            safeRiseEvent(DefaultApplicationEventsKt.getApplicationStopped(), application);
        }
        Iterator<T> it = this.packageWatchKeys.iterator();
        while (it.hasNext()) {
            d.h(it.next()).cancel();
        }
        this.packageWatchKeys = new ArrayList();
    }

    private final List<String> getConfiguredWatchPath() {
        List<String> list;
        ApplicationConfigValue applicationConfigValuePropertyOrNull = getConfig().propertyOrNull(ConfigKeys.hostWatchPaths);
        return (applicationConfigValuePropertyOrNull == null || (list = applicationConfigValuePropertyOrNull.getList()) == null) ? z.f19728i : list;
    }

    private final WatchService getWatcher() {
        return (WatchService) this.watcher.getValue();
    }

    private final Application instantiateAndConfigureApplication(ClassLoader currentClassLoader) {
        Application application;
        if (this.recreateInstance || (application = this._applicationInstance) == null) {
            application = new Application(this);
        } else {
            this.recreateInstance = true;
        }
        safeRiseEvent(DefaultApplicationEventsKt.getApplicationStarting(), application);
        avoidingDoubleStartup(new AnonymousClass1(currentClassLoader, application));
        safeRiseEvent(DefaultApplicationEventsKt.getApplicationStarted(), application);
        return application;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchModuleByName(String name, ClassLoader currentClassLoader, Application newInstance) {
        avoidingDoubleStartupFor(name, new C18721(currentClassLoader, name, newInstance));
    }

    private final void safeRiseEvent(EventDefinition<Application> event, Application application) {
        EventsKt.raiseCatching$default(getMonitor(), event, application, null, 4, null);
    }

    private final void watchUrls(List<URL> urls) throws IOException {
        Object c0Var;
        final HashSet hashSet = new HashSet();
        Iterator<URL> it = urls.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String path = it.next().getPath();
            if (path != null) {
                try {
                    c0Var = new File(URLDecoder.decode(path, "utf-8")).toPath();
                } catch (Throwable th) {
                    c0Var = new c0(th);
                }
                Path pathE = d.e(c0Var instanceof c0 ? null : c0Var);
                if (pathE != null && Files.exists(pathE, new LinkOption[0])) {
                    SimpleFileVisitor<Path> simpleFileVisitor = new SimpleFileVisitor<Path>() { // from class: io.ktor.server.engine.ApplicationEngineEnvironmentReloading$watchUrls$visitor$1
                        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                        public /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object obj, BasicFileAttributes basicFileAttributes) {
                            return preVisitDirectory(d.e(obj), basicFileAttributes);
                        }

                        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                        public /* bridge */ /* synthetic */ FileVisitResult visitFile(Object obj, BasicFileAttributes basicFileAttributes) {
                            return visitFile(d.e(obj), basicFileAttributes);
                        }

                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                            hashSet.add(dir);
                            return FileVisitResult.CONTINUE;
                        }

                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            Path parent = file.getParent();
                            if (parent != null) {
                                hashSet.add(parent);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    };
                    if (Files.isDirectory(pathE, new LinkOption[0])) {
                        Files.walkFileTree(pathE, simpleFileVisitor);
                    }
                }
            }
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            Path pathE2 = d.e(it2.next());
            getLog().d("Watching " + pathE2 + " for changes.");
        }
        WatchEvent.Modifier modifier = AutoReloadUtilsKt.get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH();
        WatchEvent.Modifier[] modifierArr = modifier != null ? new WatchEvent.Modifier[]{modifier} : new WatchEvent.Modifier[0];
        ArrayList arrayList = new ArrayList();
        Iterator it3 = hashSet.iterator();
        while (it3.hasNext()) {
            Path pathE3 = d.e(it3.next());
            WatchService watcher = getWatcher();
            WatchKey watchKeyRegister = watcher != null ? pathE3.register(watcher, new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY}, (WatchEvent.Modifier[]) Arrays.copyOf(modifierArr, modifierArr.length)) : null;
            if (watchKeyRegister != null) {
                arrayList.add(watchKeyRegister);
            }
        }
        this.packageWatchKeys = arrayList;
    }

    @Override // io.ktor.server.engine.ApplicationEngineEnvironment
    public Application getApplication() {
        return currentApplication();
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public ApplicationConfig getConfig() {
        return this.config;
    }

    @Override // io.ktor.server.engine.ApplicationEngineEnvironment
    public List<EngineConnectorConfig> getConnectors() {
        return this.connectors;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public b getLog() {
        return this.log;
    }

    public final List<l<Application, t0>> getModules$ktor_server_host_common() {
        return this.modules;
    }

    public final List<String> getModulesNames$ktor_server_host_common() {
        return this.modulesNames;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public Events getMonitor() {
        return this.monitor;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public j getParentCoroutineContext() {
        return this.parentCoroutineContext;
    }

    @Override // io.ktor.server.application.ApplicationEnvironment
    public String getRootPath() {
        return this.rootPath;
    }

    public final List<String> getWatchPaths$ktor_server_host_common() {
        return this.watchPaths;
    }

    public final void reload() {
        ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i10 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i11 = 0; i11 < readHoldCount; i11++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            destroyApplication();
            x6.x xVarCreateApplication = createApplication();
            Application application = (Application) xVarCreateApplication.f22608i;
            ClassLoader classLoader = (ClassLoader) xVarCreateApplication.f22609l;
            this._applicationInstance = application;
            this._applicationClassLoader = classLoader;
        } finally {
            while (i10 < readHoldCount) {
                lock.lock();
                i10++;
            }
            writeLock.unlock();
        }
    }

    @Override // io.ktor.server.engine.ApplicationEngineEnvironment
    public void start() {
        ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i10 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i11 = 0; i11 < readHoldCount; i11++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            try {
                x6.x xVarCreateApplication = createApplication();
                Application application = (Application) xVarCreateApplication.f22608i;
                ClassLoader classLoader = (ClassLoader) xVarCreateApplication.f22609l;
                this._applicationInstance = application;
                this._applicationClassLoader = classLoader;
            } catch (Throwable th) {
                destroyApplication();
                if (!this.watchPatterns.isEmpty()) {
                    cleanupWatcher();
                }
                throw th;
            }
        } finally {
            while (i10 < readHoldCount) {
                lock.lock();
                i10++;
            }
            writeLock.unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // io.ktor.server.engine.ApplicationEngineEnvironment
    public void stop() throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i10 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i11 = 0; i11 < readHoldCount; i11++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            destroyApplication();
            while (i10 < readHoldCount) {
                lock.lock();
                i10++;
            }
            writeLock.unlock();
            if (this.watchPatterns.isEmpty()) {
                return;
            }
            cleanupWatcher();
        } catch (Throwable th) {
            while (i10 < readHoldCount) {
                lock.lock();
                i10++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    public /* synthetic */ ApplicationEngineEnvironmentReloading(ClassLoader classLoader, b bVar, ApplicationConfig applicationConfig, List list, List list2, List list3, j jVar, String str, boolean z, int i10, h hVar) {
        this(classLoader, bVar, applicationConfig, list, list2, (i10 & 32) != 0 ? z.f19728i : list3, (i10 & 64) != 0 ? k.f14688i : jVar, (i10 & 128) != 0 ? "" : str, (i10 & 256) != 0 ? true : z);
    }

    public /* synthetic */ ApplicationEngineEnvironmentReloading(ClassLoader classLoader, b bVar, ApplicationConfig applicationConfig, List list, List list2, List list3, j jVar, String str, int i10, h hVar) {
        this(classLoader, bVar, applicationConfig, list, list2, (i10 & 32) != 0 ? z.f19728i : list3, (i10 & 64) != 0 ? k.f14688i : jVar, (i10 & 128) != 0 ? "" : str);
    }

    public ApplicationEngineEnvironmentReloading(ClassLoader classLoader, b bVar, ApplicationConfig applicationConfig, List<? extends EngineConnectorConfig> list, List<? extends l<? super Application, t0>> list2, List<String> list3, j jVar, String str) {
        this(classLoader, bVar, applicationConfig, list, list2, list3, jVar, str, true);
    }
}
