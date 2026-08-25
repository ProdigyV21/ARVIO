package coil.network;

import gb.d0;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import org.jsoup.helper.HttpConnection;
import r7.a;
import xc.d;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lgb/d0;", "invoke", "()Lgb/d0;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class CacheResponse$contentType$2 extends r implements a<d0> {
    final /* synthetic */ CacheResponse this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CacheResponse$contentType$2(CacheResponse cacheResponse) {
        super(0);
        this.this$0 = cacheResponse;
    }

    @Override // r7.a
    public final d0 invoke() {
        String strA = this.this$0.getResponseHeaders().a(HttpConnection.CONTENT_TYPE);
        if (strA != null) {
            Pattern pattern = d0.f15633d;
            try {
                return d.i0(strA);
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }
}
