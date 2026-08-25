package io.github.jan.supabase.plugins;

import d7.d;
import e7.a;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.exceptions.RestException;
import io.github.jan.supabase.plugins.MainConfig;
import io.github.jan.supabase.plugins.SupabasePlugin;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLUtilsKt;
import kotlin.Metadata;
import kotlin.text.o;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003J\u0019\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH¦@¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lio/github/jan/supabase/plugins/MainPlugin;", "Lio/github/jan/supabase/plugins/MainConfig;", "Config", "Lio/github/jan/supabase/plugins/SupabasePlugin;", "", "path", "resolveUrl", "(Ljava/lang/String;)Ljava/lang/String;", "Lio/ktor/client/statement/HttpResponse;", "response", "Lio/github/jan/supabase/exceptions/RestException;", "parseErrorResponse", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "init", "()V", "getConfig", "()Lio/github/jan/supabase/plugins/MainConfig;", "config", "Lio/github/jan/supabase/SupabaseClient;", "getSupabaseClient", "()Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "", "getApiVersion", "()I", "apiVersion", "getPluginKey", "()Ljava/lang/String;", "pluginKey", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface MainPlugin<Config extends MainConfig> extends SupabasePlugin {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <Config extends MainConfig> Object close(MainPlugin<Config> mainPlugin, d<? super t0> dVar) {
            Object objClose = SupabasePlugin.DefaultImpls.close(mainPlugin, dVar);
            return objClose == a.f15033i ? objClose : t0.f22605a;
        }

        @SupabaseInternal
        public static <Config extends MainConfig> void init(MainPlugin<Config> mainPlugin) {
        }

        public static <Config extends MainConfig> String resolveUrl(MainPlugin<Config> mainPlugin, String str) {
            boolean z = mainPlugin.getConfig().getCustomUrl() == null;
            String customUrl = mainPlugin.getConfig().getCustomUrl();
            if (customUrl == null) {
                customUrl = mainPlugin.getSupabaseClient().getSupabaseHttpUrl();
            }
            URLBuilder URLBuilder = URLUtilsKt.URLBuilder(customUrl);
            if (z) {
                URLBuilderKt.appendEncodedPathSegments(URLBuilder, mainPlugin.getPluginKey(), "v" + mainPlugin.getApiVersion());
            }
            if (!o.h0(str)) {
                URLBuilderKt.appendEncodedPathSegments(URLBuilder, str);
            }
            return URLBuilder.buildString();
        }

        public static /* synthetic */ String resolveUrl$default(MainPlugin mainPlugin, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resolveUrl");
            }
            if ((i10 & 1) != 0) {
                str = "";
            }
            return mainPlugin.resolveUrl(str);
        }
    }

    int getApiVersion();

    Config getConfig();

    String getPluginKey();

    SupabaseClient getSupabaseClient();

    @SupabaseInternal
    void init();

    Object parseErrorResponse(HttpResponse httpResponse, d<? super RestException> dVar);

    String resolveUrl(String path);
}
