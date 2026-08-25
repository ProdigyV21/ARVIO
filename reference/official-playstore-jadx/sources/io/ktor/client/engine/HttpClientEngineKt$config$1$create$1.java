package io.ktor.client.engine;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lx6/t0;", "invoke", "(Lio/ktor/client/engine/HttpClientEngineConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpClientEngineKt$config$1$create$1<T> extends r implements l<T, t0> {
    final /* synthetic */ l<T, t0> $block;
    final /* synthetic */ l<T, t0> $nested;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HttpClientEngineKt$config$1$create$1(l<? super T, t0> lVar, l<? super T, t0> lVar2) {
        super(1);
        this.$nested = lVar;
        this.$block = lVar2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpClientEngineConfig) obj);
        return t0.f22605a;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    public final void invoke(HttpClientEngineConfig httpClientEngineConfig) {
        this.$nested.invoke(httpClientEngineConfig);
        this.$block.invoke(httpClientEngineConfig);
    }
}
