package io.github.jan.supabase;

import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.network.KtorSupabaseHttpClient;
import io.github.jan.supabase.plugins.MainPlugin;
import io.github.jan.supabase.plugins.PluginManager;
import io.github.jan.supabase.plugins.SupabasePlugin;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.engine.HttpClientEngine;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.jvm.internal.r;
import q2.f;
import r7.a;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B|\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012!\u0010\r\u001a\u001d\u0012\u0019\u0012\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\b\f0\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u000bH\u0096@¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010\u001a\u001a\u0004\b%\u0010\u001cR \u0010'\u001a\u00020&8\u0016X\u0096\u0004¢\u0006\u0012\n\u0004\b'\u0010(\u0012\u0004\b+\u0010,\u001a\u0004\b)\u0010*R\u001a\u0010.\u001a\u00020-8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101¨\u00062"}, d2 = {"Lio/github/jan/supabase/SupabaseClientImpl;", "Lio/github/jan/supabase/SupabaseClient;", "", "supabaseUrl", "supabaseKey", "", "Lkotlin/Function1;", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "plugins", "", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "Lx6/n;", "httpConfigOverrides", "", "useHTTPS", "", "requestTimeout", "Lio/ktor/client/engine/HttpClientEngine;", "httpEngine", "Lio/github/jan/supabase/SupabaseSerializer;", "defaultSerializer", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;ZJLio/ktor/client/engine/HttpClientEngine;Lio/github/jan/supabase/SupabaseSerializer;)V", "close", "(Ld7/d;)Ljava/lang/Object;", "Ljava/lang/String;", "getSupabaseUrl", "()Ljava/lang/String;", "getSupabaseKey", "Z", "getUseHTTPS", "()Z", "Lio/github/jan/supabase/SupabaseSerializer;", "getDefaultSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "supabaseHttpUrl", "getSupabaseHttpUrl", "Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "httpClient", "Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "getHttpClient", "()Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "getHttpClient$annotations", "()V", "Lio/github/jan/supabase/plugins/PluginManager;", "pluginManager", "Lio/github/jan/supabase/plugins/PluginManager;", "getPluginManager", "()Lio/github/jan/supabase/plugins/PluginManager;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SupabaseClientImpl implements SupabaseClient {
    private final SupabaseSerializer defaultSerializer;
    private final KtorSupabaseHttpClient httpClient;
    private final PluginManager pluginManager;
    private final String supabaseHttpUrl;
    private final String supabaseKey;
    private final String supabaseUrl;
    private final boolean useHTTPS;

    /* JADX INFO: renamed from: io.github.jan.supabase.SupabaseClientImpl$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "SupabaseClient created! Please report any bugs you find.";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.SupabaseClientImpl$close$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.SupabaseClientImpl", f = "SupabaseClient.kt", l = {109}, m = "close")
    public static final class C16591 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16591(d<? super C16591> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SupabaseClientImpl.this.close(this);
        }
    }

    public SupabaseClientImpl(String str, String str2, Map<String, ? extends l<? super SupabaseClient, ? extends SupabasePlugin>> map, List<l<HttpClientConfig<?>, t0>> list, boolean z, long j10, HttpClientEngine httpClientEngine, SupabaseSerializer supabaseSerializer) {
        this.supabaseUrl = str;
        this.supabaseKey = str2;
        this.useHTTPS = z;
        this.defaultSerializer = supabaseSerializer;
        f.D0(f.f21327l, "Core", AnonymousClass1.INSTANCE);
        this.supabaseHttpUrl = getUseHTTPS() ? "https://" + getSupabaseUrl() : "http://" + getSupabaseUrl();
        this.httpClient = new KtorSupabaseHttpClient(getSupabaseKey(), list, j10, httpClientEngine);
        List<x> listZ0 = h0.z0(map);
        int iQ0 = i0.q0(s.U(listZ0, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
        for (x xVar : listZ0) {
            linkedHashMap.put((String) xVar.f22608i, ((l) xVar.f22609l).invoke(this));
        }
        this.pluginManager = new PluginManager(linkedHashMap);
        for (SupabasePlugin supabasePlugin : getPluginManager().getInstalledPlugins().values()) {
            if (supabasePlugin instanceof MainPlugin) {
                ((MainPlugin) supabasePlugin).init();
            }
        }
    }

    public static /* synthetic */ void getHttpClient$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.SupabaseClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object close(d7.d<? super x6.t0> r5) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.github.jan.supabase.SupabaseClientImpl.C16591
            if (r0 == 0) goto L13
            r0 = r5
            io.github.jan.supabase.SupabaseClientImpl$close$1 r0 = (io.github.jan.supabase.SupabaseClientImpl.C16591) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.SupabaseClientImpl$close$1 r0 = new io.github.jan.supabase.SupabaseClientImpl$close$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r1 = r0.L$0
            java.util.Iterator r1 = (java.util.Iterator) r1
            k2.c.G(r5)
            goto L4e
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            io.github.jan.supabase.network.KtorSupabaseHttpClient r5 = r4.getHttpClient()
            r5.close()
            io.github.jan.supabase.plugins.PluginManager r5 = r4.getPluginManager()
            java.util.Map r5 = r5.getInstalledPlugins()
            java.util.Collection r5 = r5.values()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r1 = r5
        L4e:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L67
            java.lang.Object r5 = r1.next()
            io.github.jan.supabase.plugins.SupabasePlugin r5 = (io.github.jan.supabase.plugins.SupabasePlugin) r5
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r5 = r5.close(r0)
            e7.a r3 = e7.a.f15033i
            if (r5 != r3) goto L4e
            return r3
        L67:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.SupabaseClientImpl.close(d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public SupabaseSerializer getDefaultSerializer() {
        return this.defaultSerializer;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public KtorSupabaseHttpClient getHttpClient() {
        return this.httpClient;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public String getSupabaseHttpUrl() {
        return this.supabaseHttpUrl;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public String getSupabaseKey() {
        return this.supabaseKey;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public String getSupabaseUrl() {
        return this.supabaseUrl;
    }

    @Override // io.github.jan.supabase.SupabaseClient
    public boolean getUseHTTPS() {
        return this.useHTTPS;
    }
}
