package io.ktor.server.plugins;

import io.ktor.http.RequestConnectionPoint;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class MutableOriginConnectionPoint$serverPort$2 extends r implements a<Integer> {
    final /* synthetic */ RequestConnectionPoint $delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableOriginConnectionPoint$serverPort$2(RequestConnectionPoint requestConnectionPoint) {
        super(0);
        this.$delegate = requestConnectionPoint;
    }

    @Override // r7.a
    public final Integer invoke() {
        return Integer.valueOf(this.$delegate.getServerPort());
    }
}
