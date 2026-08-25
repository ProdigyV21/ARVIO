package io.ktor.client.engine;

import d7.j;
import io.ktor.util.CoroutinesUtilsKt;
import ka.j0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Ld7/j;", "invoke", "()Ld7/j;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpClientEngineBase$coroutineContext$2 extends r implements a<j> {
    final /* synthetic */ HttpClientEngineBase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientEngineBase$coroutineContext$2(HttpClientEngineBase httpClientEngineBase) {
        super(0);
        this.this$0 = httpClientEngineBase;
    }

    @Override // r7.a
    public final j invoke() {
        return CoroutinesUtilsKt.SilentSupervisor$default(null, 1, null).plus(this.this$0.getDispatcher()).plus(new j0(this.this$0.engineName + "-context"));
    }
}
