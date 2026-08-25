package io.github.jan.supabase.plugins;

import d7.d;
import f7.c;
import f7.e;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007JB\u0010\r\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u0004\"\u0004\b\u0001\u0010\t\"\u0014\b\u0002\u0010\u000b*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\f\u001a\u00028\u0002H\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ@\u0010\u000f\u001a\u00028\u0000\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u0004\"\u0004\b\u0001\u0010\t\"\u0014\b\u0002\u0010\u000b*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\f\u001a\u00028\u0002H\u0086\b¢\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0010H\u0086H¢\u0006\u0004\b\u0011\u0010\u0012R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/plugins/PluginManager;", "", "", "", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "installedPlugins", "<init>", "(Ljava/util/Map;)V", "Plugin", "Config", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Provider", "provider", "getPluginOrNull", "(Lio/github/jan/supabase/plugins/SupabasePluginProvider;)Lio/github/jan/supabase/plugins/SupabasePlugin;", "getPlugin", "Lx6/t0;", "closeAllPlugins", "(Ld7/d;)Ljava/lang/Object;", "Ljava/util/Map;", "getInstalledPlugins", "()Ljava/util/Map;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PluginManager {
    private final Map<String, SupabasePlugin> installedPlugins;

    /* JADX INFO: renamed from: io.github.jan.supabase.plugins.PluginManager$closeAllPlugins$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 176)
    @e(c = "io.github.jan.supabase.plugins.PluginManager", f = "PluginManager.kt", l = {29}, m = "closeAllPlugins")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginManager.this.closeAllPlugins(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PluginManager(Map<String, ? extends SupabasePlugin> map) {
        this.installedPlugins = map;
    }

    private final Object closeAllPlugins$$forInline(d<? super t0> dVar) {
        Iterator<T> it = getInstalledPlugins().values().iterator();
        while (it.hasNext()) {
            ((SupabasePlugin) it.next()).close(null);
        }
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object closeAllPlugins(d7.d<? super x6.t0> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.github.jan.supabase.plugins.PluginManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.github.jan.supabase.plugins.PluginManager$closeAllPlugins$1 r0 = (io.github.jan.supabase.plugins.PluginManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.plugins.PluginManager$closeAllPlugins$1 r0 = new io.github.jan.supabase.plugins.PluginManager$closeAllPlugins$1
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
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            java.util.Map r5 = r4.getInstalledPlugins()
            java.util.Collection r5 = r5.values()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r1 = r5
        L43:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L5c
            java.lang.Object r5 = r1.next()
            io.github.jan.supabase.plugins.SupabasePlugin r5 = (io.github.jan.supabase.plugins.SupabasePlugin) r5
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r5 = r5.close(r0)
            e7.a r3 = e7.a.f15033i
            if (r5 != r3) goto L43
            return r3
        L5c:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.plugins.PluginManager.closeAllPlugins(d7.d):java.lang.Object");
    }

    public final Map<String, SupabasePlugin> getInstalledPlugins() {
        return this.installedPlugins;
    }

    public final <Plugin extends SupabasePlugin, Config, Provider extends SupabasePluginProvider<Config, Plugin>> Plugin getPlugin(Provider provider) {
        getInstalledPlugins().get(provider.getKey());
        p.h();
        throw null;
    }

    public final <Plugin extends SupabasePlugin, Config, Provider extends SupabasePluginProvider<Config, Plugin>> Plugin getPluginOrNull(Provider provider) {
        getInstalledPlugins().get(provider.getKey());
        p.h();
        throw null;
    }
}
