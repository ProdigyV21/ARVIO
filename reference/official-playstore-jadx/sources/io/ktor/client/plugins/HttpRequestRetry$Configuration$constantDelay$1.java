package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpRequestRetry;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "it", "", "invoke", "(Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;I)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestRetry$Configuration$constantDelay$1 extends r implements p<HttpRequestRetry.DelayContext, Integer, Long> {
    final /* synthetic */ long $millis;
    final /* synthetic */ long $randomizationMs;
    final /* synthetic */ HttpRequestRetry.Configuration this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestRetry$Configuration$constantDelay$1(long j10, HttpRequestRetry.Configuration configuration, long j11) {
        super(2);
        this.$millis = j10;
        this.this$0 = configuration;
        this.$randomizationMs = j11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((HttpRequestRetry.DelayContext) obj, ((Number) obj2).intValue());
    }

    public final Long invoke(HttpRequestRetry.DelayContext delayContext, int i10) {
        return Long.valueOf(this.this$0.randomMs(this.$randomizationMs) + this.$millis);
    }
}
