package io.ktor.client.plugins.websocket;

import io.ktor.http.URLBuilder;
import io.ktor.http.URLProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/http/URLBuilder;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/http/URLBuilder;Lio/ktor/http/URLBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BuildersKt$webSocket$session$1$1 extends r implements p<URLBuilder, URLBuilder, t0> {
    public static final BuildersKt$webSocket$session$1$1 INSTANCE = new BuildersKt$webSocket$session$1$1();

    public BuildersKt$webSocket$session$1$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((URLBuilder) obj, (URLBuilder) obj2);
        return t0.f22605a;
    }

    public final void invoke(URLBuilder uRLBuilder, URLBuilder uRLBuilder2) {
        uRLBuilder.setProtocol(URLProtocol.INSTANCE.getWS());
    }
}
