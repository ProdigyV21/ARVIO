package io.ktor.client.plugins;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.plugins.HttpRedirect;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpRedirect$Plugin", f = "HttpRedirect.kt", l = {113}, m = "handleCall")
public final class HttpRedirect$Plugin$handleCall$1 extends c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpRedirect.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRedirect$Plugin$handleCall$1(HttpRedirect.Companion companion, d<? super HttpRedirect$Plugin$handleCall$1> dVar) {
        super(dVar);
        this.this$0 = companion;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleCall(null, null, null, false, null, this);
    }
}
