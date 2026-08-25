package io.ktor.client.plugins.cookies;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.cookies.HttpCookies", f = "HttpCookies.kt", l = {59}, m = "sendCookiesWith$ktor_client_core")
public final class HttpCookies$sendCookiesWith$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpCookies this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$sendCookiesWith$1(HttpCookies httpCookies, d<? super HttpCookies$sendCookiesWith$1> dVar) {
        super(dVar);
        this.this$0 = httpCookies;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendCookiesWith$ktor_client_core(null, this);
    }
}
