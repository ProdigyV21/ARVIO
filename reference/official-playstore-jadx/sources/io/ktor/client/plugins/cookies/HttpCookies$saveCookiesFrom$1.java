package io.ktor.client.plugins.cookies;

import androidx.media3.container.MdtaMetadataEntry;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.cookies.HttpCookies", f = "HttpCookies.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64}, m = "saveCookiesFrom$ktor_client_core")
public final class HttpCookies$saveCookiesFrom$1 extends c {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpCookies this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$saveCookiesFrom$1(HttpCookies httpCookies, d<? super HttpCookies$saveCookiesFrom$1> dVar) {
        super(dVar);
        this.this$0 = httpCookies;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.saveCookiesFrom$ktor_client_core(null, this);
    }
}
