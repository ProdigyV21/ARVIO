package io.github.jan.supabase.network;

import io.ktor.client.HttpClientConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "invoke", "(Lio/ktor/client/HttpClientConfig;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class KtorSupabaseHttpClient$httpClient$1 extends r implements l<HttpClientConfig<?>, t0> {
    final /* synthetic */ List<l<HttpClientConfig<?>, t0>> $modifiers;
    final /* synthetic */ KtorSupabaseHttpClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KtorSupabaseHttpClient$httpClient$1(KtorSupabaseHttpClient ktorSupabaseHttpClient, List<? extends l<? super HttpClientConfig<?>, t0>> list) {
        super(1);
        this.this$0 = ktorSupabaseHttpClient;
        this.$modifiers = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpClientConfig<?>) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpClientConfig<?> httpClientConfig) {
        this.this$0.applyDefaultConfiguration(httpClientConfig, this.$modifiers);
    }
}
