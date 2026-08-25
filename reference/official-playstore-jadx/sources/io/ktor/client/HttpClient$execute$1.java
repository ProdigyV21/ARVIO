package io.ktor.client;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.HttpClient", f = "HttpClient.kt", l = {191}, m = "execute$ktor_client_core")
public final class HttpClient$execute$1 extends c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClient$execute$1(HttpClient httpClient, d<? super HttpClient$execute$1> dVar) {
        super(dVar);
        this.this$0 = httpClient;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute$ktor_client_core(null, this);
    }
}
