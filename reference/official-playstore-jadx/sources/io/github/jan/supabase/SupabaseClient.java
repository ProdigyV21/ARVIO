package io.github.jan.supabase;

import d7.d;
import io.github.jan.supabase.network.KtorSupabaseHttpClient;
import io.github.jan.supabase.plugins.PluginManager;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\b\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\f\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0001\u0001\u001d¨\u0006\u001e"}, d2 = {"Lio/github/jan/supabase/SupabaseClient;", "", "Lx6/t0;", "close", "(Ld7/d;)Ljava/lang/Object;", "", "getSupabaseHttpUrl", "()Ljava/lang/String;", "supabaseHttpUrl", "getSupabaseUrl", "supabaseUrl", "getSupabaseKey", "supabaseKey", "Lio/github/jan/supabase/plugins/PluginManager;", "getPluginManager", "()Lio/github/jan/supabase/plugins/PluginManager;", "pluginManager", "Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "getHttpClient", "()Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "httpClient", "", "getUseHTTPS", "()Z", "useHTTPS", "Lio/github/jan/supabase/SupabaseSerializer;", "getDefaultSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "defaultSerializer", "Lio/github/jan/supabase/SupabaseClientImpl;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SupabaseClient {
    Object close(d<? super t0> dVar);

    SupabaseSerializer getDefaultSerializer();

    KtorSupabaseHttpClient getHttpClient();

    PluginManager getPluginManager();

    String getSupabaseHttpUrl();

    String getSupabaseKey();

    String getSupabaseUrl();

    boolean getUseHTTPS();
}
