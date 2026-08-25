package io.ktor.client.engine;

import a0.c;
import d7.j;
import ka.j0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Ld7/j;", "invoke", "()Ld7/j;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpClientJvmEngine$coroutineContext$2 extends r implements a<j> {
    final /* synthetic */ String $engineName;
    final /* synthetic */ HttpClientJvmEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientJvmEngine$coroutineContext$2(HttpClientJvmEngine httpClientJvmEngine, String str) {
        super(0);
        this.this$0 = httpClientJvmEngine;
        this.$engineName = str;
    }

    @Override // r7.a
    public final j invoke() {
        return this.this$0.getDispatcher().plus(this.this$0.clientContext).plus(new j0(c.p(new StringBuilder(), this.$engineName, "-context")));
    }
}
