package io.github.jan.supabase.plugins;

import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseClientBuilder;
import io.github.jan.supabase.plugins.SupabasePlugin;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004J(\u0010\t\u001a\u00028\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00028\u0000H&¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Config", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "PluginInstance", "", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "init", "createConfig", "(Lr7/l;)Ljava/lang/Object;", "Lio/github/jan/supabase/SupabaseClientBuilder;", "builder", "config", "setup", "(Lio/github/jan/supabase/SupabaseClientBuilder;Ljava/lang/Object;)V", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "create", "(Lio/github/jan/supabase/SupabaseClient;Ljava/lang/Object;)Lio/github/jan/supabase/plugins/SupabasePlugin;", "", "getKey", "()Ljava/lang/String;", "key", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SupabasePluginProvider<Config, PluginInstance extends SupabasePlugin> {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <Config, PluginInstance extends SupabasePlugin> void setup(SupabasePluginProvider<Config, PluginInstance> supabasePluginProvider, SupabaseClientBuilder supabaseClientBuilder, Config config) {
        }
    }

    PluginInstance create(SupabaseClient supabaseClient, Config config);

    Config createConfig(l<? super Config, t0> init);

    String getKey();

    void setup(SupabaseClientBuilder builder, Config config);
}
