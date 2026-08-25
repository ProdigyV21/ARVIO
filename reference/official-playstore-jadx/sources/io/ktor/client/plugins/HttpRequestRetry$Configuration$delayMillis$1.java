package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpRequestRetry;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "it", "", "invoke", "(Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;I)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestRetry$Configuration$delayMillis$1 extends r implements p<HttpRequestRetry.DelayContext, Integer, Long> {
    final /* synthetic */ p<HttpRequestRetry.DelayContext, Integer, Long> $block;
    final /* synthetic */ boolean $respectRetryAfterHeader;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HttpRequestRetry$Configuration$delayMillis$1(boolean z, p<? super HttpRequestRetry.DelayContext, ? super Integer, Long> pVar) {
        super(2);
        this.$respectRetryAfterHeader = z;
        this.$block = pVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((HttpRequestRetry.DelayContext) obj, ((Number) obj2).intValue());
    }

    public final Long invoke(HttpRequestRetry.DelayContext delayContext, int i10) {
        long jLongValue;
        Headers headers;
        String str;
        Long lS;
        if (this.$respectRetryAfterHeader) {
            HttpResponse response = delayContext.getResponse();
            Long lValueOf = (response == null || (headers = response.getHeaders()) == null || (str = headers.get(HttpHeaders.INSTANCE.getRetryAfter())) == null || (lS = u.S(str)) == null) ? null : Long.valueOf(lS.longValue() * ((long) 1000));
            jLongValue = Math.max(((Number) this.$block.invoke(delayContext, Integer.valueOf(i10))).longValue(), lValueOf != null ? lValueOf.longValue() : 0L);
        } else {
            jLongValue = ((Number) this.$block.invoke(delayContext, Integer.valueOf(i10))).longValue();
        }
        return Long.valueOf(jLongValue);
    }
}
