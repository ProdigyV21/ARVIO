package coil.network;

import gb.h;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.reflect.b0;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lgb/h;", "invoke", "()Lgb/h;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class CacheResponse$cacheControl$2 extends r implements a<h> {
    final /* synthetic */ CacheResponse this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CacheResponse$cacheControl$2(CacheResponse cacheResponse) {
        super(0);
        this.this$0 = cacheResponse;
    }

    @Override // r7.a
    public final h invoke() {
        h hVar = h.f15686n;
        return b0.A(this.this$0.getResponseHeaders());
    }
}
