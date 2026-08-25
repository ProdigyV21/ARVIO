package io.github.jan.supabase.postgrest;

import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.plugins.PluginManager;
import io.github.jan.supabase.plugins.SupabasePlugin;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u001a\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"postgrest", "Lio/github/jan/supabase/postgrest/Postgrest;", "Lio/github/jan/supabase/SupabaseClient;", "getPostgrest", "(Lio/github/jan/supabase/SupabaseClient;)Lio/github/jan/supabase/postgrest/Postgrest;", "from", "Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "table", "", "schema", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestKt {
    public static final PostgrestQueryBuilder from(SupabaseClient supabaseClient, String str) {
        PluginManager pluginManager = supabaseClient.getPluginManager();
        Postgrest.Companion companion = Postgrest.INSTANCE;
        SupabasePlugin supabasePlugin = pluginManager.getInstalledPlugins().get(companion.getKey());
        if (!(supabasePlugin instanceof Postgrest)) {
            supabasePlugin = null;
        }
        Postgrest postgrest = (Postgrest) supabasePlugin;
        if (postgrest != null) {
            return postgrest.from(str);
        }
        StringBuilder sb2 = new StringBuilder("Plugin ");
        sb2.append(companion.getKey());
        sb2.append(" not installed or not of type ");
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(". Consider installing ");
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(" within your SupabaseClientBuilder");
        throw new IllegalStateException(sb2.toString().toString());
    }

    public static final Postgrest getPostgrest(SupabaseClient supabaseClient) {
        PluginManager pluginManager = supabaseClient.getPluginManager();
        Postgrest.Companion companion = Postgrest.INSTANCE;
        SupabasePlugin supabasePlugin = pluginManager.getInstalledPlugins().get(companion.getKey());
        if (!(supabasePlugin instanceof Postgrest)) {
            supabasePlugin = null;
        }
        Postgrest postgrest = (Postgrest) supabasePlugin;
        if (postgrest != null) {
            return postgrest;
        }
        StringBuilder sb2 = new StringBuilder("Plugin ");
        sb2.append(companion.getKey());
        sb2.append(" not installed or not of type ");
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(". Consider installing ");
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(" within your SupabaseClientBuilder");
        throw new IllegalStateException(sb2.toString().toString());
    }

    public static final PostgrestQueryBuilder from(SupabaseClient supabaseClient, String str, String str2) {
        PluginManager pluginManager = supabaseClient.getPluginManager();
        Postgrest.Companion companion = Postgrest.INSTANCE;
        SupabasePlugin supabasePlugin = pluginManager.getInstalledPlugins().get(companion.getKey());
        if (!(supabasePlugin instanceof Postgrest)) {
            supabasePlugin = null;
        }
        Postgrest postgrest = (Postgrest) supabasePlugin;
        if (postgrest != null) {
            return postgrest.from(str, str2);
        }
        StringBuilder sb2 = new StringBuilder("Plugin ");
        sb2.append(companion.getKey());
        sb2.append(" not installed or not of type ");
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(". Consider installing ");
        sb2.append(m0Var.b(Postgrest.class).r());
        sb2.append(" within your SupabaseClientBuilder");
        throw new IllegalStateException(sb2.toString().toString());
    }
}
