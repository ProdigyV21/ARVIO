package io.ktor.client.plugins;

import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpRequestRetry$intercept$1", f = "HttpRequestRetry.kt", l = {298, MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD}, m = "invokeSuspend")
public final class HttpRequestRetry$intercept$1 extends j implements q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpClient $client;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ HttpRequestRetry this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestRetry$intercept$1(HttpRequestRetry httpRequestRetry, HttpClient httpClient, d<? super HttpRequestRetry$intercept$1> dVar) {
        super(3, dVar);
        this.this$0 = httpRequestRetry;
        this.$client = httpClient;
    }

    @Override // r7.q
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
        HttpRequestRetry$intercept$1 httpRequestRetry$intercept$1 = new HttpRequestRetry$intercept$1(this.this$0, this.$client, dVar);
        httpRequestRetry$intercept$1.L$0 = sender;
        httpRequestRetry$intercept$1.L$1 = httpRequestBuilder;
        return httpRequestRetry$intercept$1.invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0111 A[Catch: all -> 0x0074, TRY_ENTER, TryCatch #0 {all -> 0x0074, blocks: (B:34:0x0111, B:35:0x0125, B:38:0x0141, B:41:0x014c, B:11:0x006d), top: B:53:0x006d }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x014c A[Catch: all -> 0x0074, TRY_LEAVE, TryCatch #0 {all -> 0x0074, blocks: (B:34:0x0111, B:35:0x0125, B:38:0x0141, B:41:0x014c, B:11:0x006d), top: B:53:0x006d }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01cc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x01cc -> B:7:0x0036). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 505
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpRequestRetry$intercept$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
