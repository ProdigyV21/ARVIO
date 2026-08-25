package io.github.jan.supabase;

import h.f0;
import io.github.jan.supabase.annotations.SupabaseDsl;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.plugins.SupabasePlugin;
import io.github.jan.supabase.plugins.SupabasePluginProvider;
import io.github.jan.supabase.serializer.KotlinXSerializer;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.engine.HttpClientEngine;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import kotlin.reflect.b0;
import kotlin.text.o;
import kotlin.text.u;
import kotlin.time.c;
import kotlin.time.e;
import q2.f;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\b\u0010\tJ1\u0010\u0010\u001a\u00020\f2 \u0010\u000f\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J]\u0010\u0019\u001a\u00020\f\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013\"\u0014\b\u0002\u0010\u0016*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00152\u0006\u0010\u0017\u001a\u00028\u00022\u001e\b\u0002\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001bR\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010$\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010*\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010\u001e\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R(\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u00105\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R/\u0010<\u001a\u001d\u0012\u0019\u0012\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\u000e0;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R,\u0010?\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\n0>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006A"}, d2 = {"Lio/github/jan/supabase/SupabaseClientBuilder;", "", "", "supabaseUrl", "supabaseKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Lio/github/jan/supabase/SupabaseClient;", "build", "()Lio/github/jan/supabase/SupabaseClient;", "Lkotlin/Function1;", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "Lio/github/jan/supabase/annotations/SupabaseDsl;", "Lx6/n;", "block", "httpConfig", "(Lr7/l;)V", "Config", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "PluginInstance", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Provider", "plugin", "init", "install", "(Lio/github/jan/supabase/plugins/SupabasePluginProvider;Lr7/l;)V", "Ljava/lang/String;", "", "useHTTPS", "Z", "getUseHTTPS", "()Z", "setUseHTTPS", "(Z)V", "Lio/ktor/client/engine/HttpClientEngine;", "httpEngine", "Lio/ktor/client/engine/HttpClientEngine;", "getHttpEngine", "()Lio/ktor/client/engine/HttpClientEngine;", "setHttpEngine", "(Lio/ktor/client/engine/HttpClientEngine;)V", "ignoreModulesInUrl", "getIgnoreModulesInUrl", "setIgnoreModulesInUrl", "Lkotlin/time/c;", "requestTimeout", "J", "getRequestTimeout-UwyO8pc", "()J", "setRequestTimeout-LRDsOJo", "(J)V", "Lio/github/jan/supabase/SupabaseSerializer;", "defaultSerializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getDefaultSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "setDefaultSerializer", "(Lio/github/jan/supabase/SupabaseSerializer;)V", "", "httpConfigOverrides", "Ljava/util/List;", "", "plugins", "Ljava/util/Map;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SupabaseDsl
public final class SupabaseClientBuilder {
    private SupabaseSerializer defaultSerializer;
    private final List<l<HttpClientConfig<?>, t0>> httpConfigOverrides;
    private HttpClientEngine httpEngine;
    private boolean ignoreModulesInUrl;
    private final Map<String, l<SupabaseClient, SupabasePlugin>> plugins;
    private long requestTimeout;
    private final String supabaseKey;
    private final String supabaseUrl;
    private boolean useHTTPS;

    /* JADX INFO: renamed from: io.github.jan.supabase.SupabaseClientBuilder$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "You are using a non HTTPS supabase url (" + SupabaseClientBuilder.this.supabaseUrl + ").";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.SupabaseClientBuilder$install$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001\"\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003*\u00028\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Config", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "PluginInstance", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Provider", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16581 extends r implements l {
        public static final C16581 INSTANCE = new C16581();

        public C16581() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6631invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6631invoke(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [PluginInstance] */
    /* JADX INFO: renamed from: io.github.jan.supabase.SupabaseClientBuilder$install$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u00020\u0003\"\u0014\b\u0002\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "PluginInstance", "Config", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "Provider", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "it", "Lio/github/jan/supabase/SupabaseClient;", "invoke", "(Lio/github/jan/supabase/SupabaseClient;)Lio/github/jan/supabase/plugins/SupabasePlugin;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass2<PluginInstance> extends r implements l<SupabaseClient, PluginInstance> {
        final /* synthetic */ Config $config;

        /* JADX INFO: Incorrect field signature: TProvider; */
        final /* synthetic */ SupabasePluginProvider $plugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Incorrect types in method signature: (TProvider;TConfig;)V */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(SupabasePluginProvider supabasePluginProvider, Object obj) {
            super(1);
            this.$plugin = supabasePluginProvider;
            this.$config = obj;
        }

        /* JADX WARN: Incorrect return type in method signature: (Lio/github/jan/supabase/SupabaseClient;)TPluginInstance; */
        @Override // r7.l
        public final SupabasePlugin invoke(SupabaseClient supabaseClient) {
            return this.$plugin.create(supabaseClient, this.$config);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SupabaseClientBuilder(String str, String str2) {
        this.supabaseUrl = str;
        this.supabaseKey = str2;
        this.useHTTPS = true;
        io.sentry.util.l lVar = c.f19956l;
        this.requestTimeout = b0.H(10, e.SECONDS);
        this.defaultSerializer = new KotlinXSerializer(null, 1, 0 == true ? 1 : 0);
        this.httpConfigOverrides = new ArrayList();
        this.plugins = new LinkedHashMap();
        String str3 = "realtime/v1";
        if (!o.T(str, "realtime/v1", false)) {
            str3 = "auth/v1";
            if (!o.T(str, "auth/v1", false)) {
                str3 = "storage/v1";
                if (!o.T(str, "storage/v1", false)) {
                    str3 = "rest/v1";
                    if (!o.T(str, "rest/v1", false)) {
                        str3 = null;
                    }
                }
            }
        }
        if (!this.ignoreModulesInUrl && str3 != null) {
            throw new IllegalStateException(("The supabase url should not contain (" + str3 + "), supabase-kt handles the url endpoints. If you want to use a custom url for a module, specify it within their builder but that's not necessary for normal supabase projects").toString());
        }
        if (u.P(str, "http://", false)) {
            this.useHTTPS = false;
            f fVar = f.f21327l;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            Object obj = fVar.f6607i;
            if (f0.a(1, 4) <= 0) {
                String str4 = (String) anonymousClass1.invoke();
                if (f0.a(1, 4) <= 0) {
                    fVar.g0(4, "Core", null, str4);
                }
            }
        }
    }

    public static /* synthetic */ void install$default(SupabaseClientBuilder supabaseClientBuilder, SupabasePluginProvider supabasePluginProvider, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C16581.INSTANCE;
        }
        supabaseClientBuilder.install(supabasePluginProvider, lVar);
    }

    public final SupabaseClient build() {
        return new SupabaseClientImpl((String) x.w0(o.y0(this.supabaseUrl, new String[]{"//"}, 0, 6)), this.supabaseKey, this.plugins, this.httpConfigOverrides, this.useHTTPS, c.i(this.requestTimeout), this.httpEngine, this.defaultSerializer);
    }

    public final SupabaseSerializer getDefaultSerializer() {
        return this.defaultSerializer;
    }

    public final HttpClientEngine getHttpEngine() {
        return this.httpEngine;
    }

    public final boolean getIgnoreModulesInUrl() {
        return this.ignoreModulesInUrl;
    }

    /* JADX INFO: renamed from: getRequestTimeout-UwyO8pc, reason: not valid java name and from getter */
    public final long getRequestTimeout() {
        return this.requestTimeout;
    }

    public final boolean getUseHTTPS() {
        return this.useHTTPS;
    }

    @SupabaseDsl
    @SupabaseInternal
    public final void httpConfig(l<? super HttpClientConfig<?>, t0> block) {
        this.httpConfigOverrides.add(block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SupabaseDsl
    public final <Config, PluginInstance extends SupabasePlugin, Provider extends SupabasePluginProvider<Config, PluginInstance>> void install(Provider plugin, l<? super Config, t0> init) {
        Object objCreateConfig = plugin.createConfig(init);
        plugin.setup(this, objCreateConfig);
        this.plugins.put(plugin.getKey(), new AnonymousClass2(plugin, objCreateConfig));
    }

    public final void setDefaultSerializer(SupabaseSerializer supabaseSerializer) {
        this.defaultSerializer = supabaseSerializer;
    }

    public final void setHttpEngine(HttpClientEngine httpClientEngine) {
        this.httpEngine = httpClientEngine;
    }

    public final void setIgnoreModulesInUrl(boolean z) {
        this.ignoreModulesInUrl = z;
    }

    /* JADX INFO: renamed from: setRequestTimeout-LRDsOJo, reason: not valid java name */
    public final void m6630setRequestTimeoutLRDsOJo(long j10) {
        this.requestTimeout = j10;
    }

    public final void setUseHTTPS(boolean z) {
        this.useHTTPS = z;
    }
}
