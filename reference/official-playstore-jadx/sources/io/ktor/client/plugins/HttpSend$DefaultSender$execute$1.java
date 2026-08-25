package io.ktor.client.plugins;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.plugins.HttpSend;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpSend$DefaultSender", f = "HttpSend.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS}, m = "execute")
public final class HttpSend$DefaultSender$execute$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpSend.DefaultSender this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpSend$DefaultSender$execute$1(HttpSend.DefaultSender defaultSender, d<? super HttpSend$DefaultSender$execute$1> dVar) {
        super(dVar);
        this.this$0 = defaultSender;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute(null, this);
    }
}
