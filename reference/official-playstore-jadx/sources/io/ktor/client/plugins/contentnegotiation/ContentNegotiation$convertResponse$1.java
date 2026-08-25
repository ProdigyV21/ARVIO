package io.ktor.client.plugins.contentnegotiation;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation", f = "ContentNegotiation.kt", l = {230}, m = "convertResponse$ktor_client_content_negotiation")
public final class ContentNegotiation$convertResponse$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ContentNegotiation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$convertResponse$1(ContentNegotiation contentNegotiation, d<? super ContentNegotiation$convertResponse$1> dVar) {
        super(dVar);
        this.this$0 = contentNegotiation;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.convertResponse$ktor_client_content_negotiation(null, null, null, null, null, this);
    }
}
