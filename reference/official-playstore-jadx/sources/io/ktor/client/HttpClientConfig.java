package io.ktor.client;

import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.KtorDsl;
import io.ktor.util.PlatformUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\n\u001a\u00020\u00072\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\n\u0010\u000bJP\u0010\u0011\u001a\u00020\u0007\"\b\b\u0001\u0010\f*\u00020\u0003\"\b\b\u0002\u0010\r*\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000e2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\u0011\u0010\u0012J.\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00132\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\u0011\u0010\u0016J\u0015\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0015¢\u0006\u0004\b\u0011\u0010\u0018J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001c\u001a\u00020\u00072\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000H\u0086\u0002¢\u0006\u0004\b\u001c\u0010\u001dR0\u0010 \u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u00060\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R5\u0010\"\u001a#\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b0\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010!R,\u0010#\u001a\u001a\u0012\u0004\u0012\u00020\u0013\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u00060\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010!R3\u0010$\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010\u000bR\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u0010+\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R\"\u00103\u001a\u00020)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b3\u0010+\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R\"\u00106\u001a\u00020)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b6\u0010+\u001a\u0004\b7\u0010-\"\u0004\b8\u0010/¨\u00069"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "engine", "(Lr7/l;)V", "TBuilder", "TPlugin", "Lio/ktor/client/plugins/HttpClientPlugin;", "plugin", "configure", "install", "(Lio/ktor/client/plugins/HttpClientPlugin;Lr7/l;)V", "", "key", "Lio/ktor/client/HttpClient;", "(Ljava/lang/String;Lr7/l;)V", "client", "(Lio/ktor/client/HttpClient;)V", "clone", "()Lio/ktor/client/HttpClientConfig;", "other", "plusAssign", "(Lio/ktor/client/HttpClientConfig;)V", "", "Lio/ktor/util/AttributeKey;", "plugins", "Ljava/util/Map;", "pluginConfigurations", "customInterceptors", "engineConfig", "Lr7/l;", "getEngineConfig$ktor_client_core", "()Lr7/l;", "setEngineConfig$ktor_client_core", "", "followRedirects", "Z", "getFollowRedirects", "()Z", "setFollowRedirects", "(Z)V", "useDefaultTransformers", "getUseDefaultTransformers", "setUseDefaultTransformers", "expectSuccess", "getExpectSuccess", "setExpectSuccess", "developmentMode", "getDevelopmentMode", "setDevelopmentMode", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpClientConfig<T extends HttpClientEngineConfig> {
    private boolean expectSuccess;
    private final Map<AttributeKey<?>, l<HttpClient, t0>> plugins = new LinkedHashMap();
    private final Map<AttributeKey<?>, l<Object, t0>> pluginConfigurations = new LinkedHashMap();
    private final Map<String, l<HttpClient, t0>> customInterceptors = new LinkedHashMap();
    private l<? super T, t0> engineConfig = HttpClientConfig$engineConfig$1.INSTANCE;
    private boolean followRedirects = true;
    private boolean useDefaultTransformers = true;
    private boolean developmentMode = PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE();

    /* JADX INFO: renamed from: io.ktor.client.HttpClientConfig$engine$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lx6/t0;", "invoke", "(Lio/ktor/client/engine/HttpClientEngineConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<T, t0> {
        final /* synthetic */ l<T, t0> $block;
        final /* synthetic */ l<T, t0> $oldConfig;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super T, t0> lVar, l<? super T, t0> lVar2) {
            super(1);
            this.$oldConfig = lVar;
            this.$block = lVar2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpClientEngineConfig) obj);
            return t0.f22605a;
        }

        public final void invoke(T t2) {
            this.$oldConfig.invoke(t2);
            this.$block.invoke(t2);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClientConfig$install$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u0003*\u00028\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "TBuilder", "TPlugin", "Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17271 extends r implements l {
        public static final C17271 INSTANCE = new C17271();

        public C17271() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6662invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6662invoke(obj);
            return t0.f22605a;
        }
    }

    /* JADX WARN: Unknown type variable: TBuilder in type: r7.l<TBuilder, x6.t0> */
    /* JADX INFO: renamed from: io.ktor.client.HttpClientConfig$install$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u0003*\u00020\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "TBuilder", "TPlugin", "Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Object, t0> {
        final /* synthetic */ l<TBuilder, t0> $configure;
        final /* synthetic */ l<Object, t0> $previousConfigBlock;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Unknown type variable: TBuilder in type: r7.l<? super TBuilder, x6.t0> */
        public AnonymousClass2(l<Object, t0> lVar, l<? super TBuilder, t0> lVar2) {
            super(1);
            this.$previousConfigBlock = lVar;
            this.$configure = lVar2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6663invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6663invoke(Object obj) {
            l<Object, t0> lVar = this.$previousConfigBlock;
            if (lVar != null) {
                lVar.invoke(obj);
            }
            this.$configure.invoke(obj);
        }
    }

    /* JADX WARN: Unknown type variable: TBuilder in type: io.ktor.client.plugins.HttpClientPlugin<TBuilder, TPlugin> */
    /* JADX WARN: Unknown type variable: TPlugin in type: io.ktor.client.plugins.HttpClientPlugin<TBuilder, TPlugin> */
    /* JADX INFO: renamed from: io.ktor.client.HttpClientConfig$install$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\n\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"", "TBuilder", "TPlugin", "Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lio/ktor/client/HttpClient;", "scope", "Lx6/t0;", "invoke", "(Lio/ktor/client/HttpClient;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<HttpClient, t0> {
        final /* synthetic */ HttpClientPlugin<TBuilder, TPlugin> $plugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Unknown type variable: TBuilder in type: io.ktor.client.plugins.HttpClientPlugin<? extends TBuilder, TPlugin> */
        /* JADX WARN: Unknown type variable: TPlugin in type: io.ktor.client.plugins.HttpClientPlugin<? extends TBuilder, TPlugin> */
        public AnonymousClass3(HttpClientPlugin<? extends TBuilder, TPlugin> httpClientPlugin) {
            super(1);
            this.$plugin = httpClientPlugin;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpClient) obj);
            return t0.f22605a;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void invoke(HttpClient httpClient) {
            Attributes attributes = (Attributes) httpClient.getAttributes().computeIfAbsent(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST(), HttpClientConfig$install$3$attributes$1.INSTANCE);
            Object objPrepare = this.$plugin.prepare((l<? super TBuilder, t0>) ((l) ((HttpClientConfig) httpClient.getConfig$ktor_client_core()).pluginConfigurations.get(this.$plugin.getKey())));
            this.$plugin.install((TPlugin) objPrepare, httpClient);
            attributes.put(this.$plugin.getKey(), objPrepare);
        }
    }

    public static /* synthetic */ void install$default(HttpClientConfig httpClientConfig, HttpClientPlugin httpClientPlugin, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17271.INSTANCE;
        }
        httpClientConfig.install(httpClientPlugin, lVar);
    }

    public final HttpClientConfig<T> clone() {
        HttpClientConfig<T> httpClientConfig = new HttpClientConfig<>();
        httpClientConfig.plusAssign(this);
        return httpClientConfig;
    }

    public final void engine(l<? super T, t0> block) {
        this.engineConfig = new AnonymousClass1(this.engineConfig, block);
    }

    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final l<T, t0> getEngineConfig$ktor_client_core() {
        return this.engineConfig;
    }

    public final boolean getExpectSuccess() {
        return this.expectSuccess;
    }

    public final boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public final boolean getUseDefaultTransformers() {
        return this.useDefaultTransformers;
    }

    public final <TBuilder, TPlugin> void install(HttpClientPlugin<? extends TBuilder, TPlugin> plugin, l<? super TBuilder, t0> configure) {
        this.pluginConfigurations.put(plugin.getKey(), new AnonymousClass2(this.pluginConfigurations.get(plugin.getKey()), configure));
        if (this.plugins.containsKey(plugin.getKey())) {
            return;
        }
        this.plugins.put(plugin.getKey(), new AnonymousClass3(plugin));
    }

    public final void plusAssign(HttpClientConfig<? extends T> other) {
        this.followRedirects = other.followRedirects;
        this.useDefaultTransformers = other.useDefaultTransformers;
        this.expectSuccess = other.expectSuccess;
        this.plugins.putAll(other.plugins);
        this.pluginConfigurations.putAll(other.pluginConfigurations);
        this.customInterceptors.putAll(other.customInterceptors);
    }

    public final void setDevelopmentMode(boolean z) {
        this.developmentMode = z;
    }

    public final void setEngineConfig$ktor_client_core(l<? super T, t0> lVar) {
        this.engineConfig = lVar;
    }

    public final void setExpectSuccess(boolean z) {
        this.expectSuccess = z;
    }

    public final void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public final void setUseDefaultTransformers(boolean z) {
        this.useDefaultTransformers = z;
    }

    public final void install(String key, l<? super HttpClient, t0> block) {
        this.customInterceptors.put(key, block);
    }

    public final void install(HttpClient client) {
        Iterator<T> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            ((l) it.next()).invoke(client);
        }
        Iterator<T> it2 = this.customInterceptors.values().iterator();
        while (it2.hasNext()) {
            ((l) it2.next()).invoke(client);
        }
    }
}
