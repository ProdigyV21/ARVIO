package io.ktor.client.plugins;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.events.EventDefinition;
import io.ktor.http.ContentDisposition;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import ka.t;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 -2\u00020\u0001:\u0006./0-12B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JL\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062#\u0010\u000f\u001a\u001f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\b\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u000f\u0010\u0012JT\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062#\u0010\u000f\u001a\u001f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\b\u000e2\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010!\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0000¢\u0006\u0004\b\u001f\u0010 R1\u0010\u000f\u001a\u001f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\b\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\"R1\u0010\u0017\u001a\u001f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\b\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\"R+\u0010&\u001a\u0019\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020%0#¢\u0006\u0002\b\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R3\u0010)\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020%\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0(\u0012\u0006\u0012\u0004\u0018\u00010\u00010#8\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b)\u0010'R\u0014\u0010\b\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010*R+\u0010,\u001a\u0019\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0#¢\u0006\u0002\b\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry;", "", "Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "configuration", "<init>", "(Lio/ktor/client/plugins/HttpRequestRetry$Configuration;)V", "", "retryCount", "maxRetries", "Lkotlin/Function3;", "Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "", "Lx6/n;", "shouldRetry", "Lio/ktor/client/call/HttpClientCall;", "call", "(IILr7/q;Lio/ktor/client/call/HttpClientCall;)Z", "Lio/ktor/client/request/HttpRequestBuilder;", "", "subRequest", "cause", "shouldRetryOnException", "(IILr7/q;Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/Throwable;)Z", "request", "prepareRequest", "(Lio/ktor/client/request/HttpRequestBuilder;)Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/HttpClient;", "client", "Lx6/t0;", "intercept$ktor_client_core", "(Lio/ktor/client/HttpClient;)V", "intercept", "Lr7/q;", "Lkotlin/Function2;", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "", "delayMillis", "Lr7/p;", "Ld7/d;", "delay", "I", "Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "modifyRequest", "Plugin", "Configuration", "DelayContext", "ModifyRequestContext", "RetryEventData", "ShouldRetryContext", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestRetry {
    private final p<Long, d<? super t0>, Object> delay;
    private final p<DelayContext, Integer, Long> delayMillis;
    private final int maxRetries;
    private final p<ModifyRequestContext, HttpRequestBuilder, t0> modifyRequest;
    private final q<ShouldRetryContext, HttpRequest, HttpResponse, Boolean> shouldRetry;
    private final q<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean> shouldRetryOnException;

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpRequestRetry> key = new AttributeKey<>("RetryFeature");
    private static final EventDefinition<RetryEventData> HttpRequestRetryEvent = new EventDefinition<>();

    @KtorDsl
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J,\u0010\u000b\u001a\u00020\u00042\u001d\u0010\n\u001a\u0019\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\u000b\u0010\fJ<\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r2#\u0010\n\u001a\u001f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u000f¢\u0006\u0002\b\t¢\u0006\u0004\b\u0014\u0010\u0015J<\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r2#\u0010\n\u001a\u001f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130\u000f¢\u0006\u0002\b\t¢\u0006\u0004\b\u0017\u0010\u0015J\u0019\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u001c\u0010\u0019J\u0017\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u001d\u0010\u0019JE\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00132,\u0010\n\u001a(\u0012\u0004\u0012\u00020\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b$\u0010%J+\u0010(\u001a\u00020\u00042\b\b\u0002\u0010&\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u0013¢\u0006\u0004\b(\u0010)J5\u0010-\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u0013¢\u0006\u0004\b-\u0010.J4\u00100\u001a\u00020\u00042\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040/\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006ø\u0001\u0000¢\u0006\u0004\b0\u0010\fJ\u0017\u00101\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0002¢\u0006\u0004\b1\u00102R?\u00103\u001a\u001f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u000f¢\u0006\u0002\b\t8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R?\u00109\u001a\u001f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130\u000f¢\u0006\u0002\b\t8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b9\u00104\u001a\u0004\b:\u00106\"\u0004\b;\u00108R9\u0010$\u001a\u0019\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020#0\u0006¢\u0006\u0002\b\t8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b$\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010\fR9\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\t8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010<\u001a\u0004\b@\u0010>\"\u0004\bA\u0010\fRA\u00100\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040/\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00068\u0000@\u0000X\u0080\u000eø\u0001\u0000¢\u0006\u0012\n\u0004\b0\u0010<\u001a\u0004\bB\u0010>\"\u0004\bC\u0010\fR\"\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "", "<init>", "()V", "Lx6/t0;", "noRetry", "Lkotlin/Function2;", "Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/n;", "block", "modifyRequest", "(Lr7/p;)V", "", "maxRetries", "Lkotlin/Function3;", "Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "", "retryIf", "(ILr7/q;)V", "", "retryOnExceptionIf", "retryOnException", "(I)V", "retryOnTimeout", "(IZ)V", "retryOnServerErrors", "retryOnExceptionOrServerErrors", "respectRetryAfterHeader", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "Lx6/y;", ContentDisposition.Parameters.Name, "retry", "", "delayMillis", "(ZLr7/p;)V", "millis", "randomizationMs", "constantDelay", "(JJZ)V", "", TtmlNode.RUBY_BASE, "maxDelayMs", "exponentialDelay", "(DJJZ)V", "Ld7/d;", "delay", "randomMs", "(J)J", "shouldRetry", "Lr7/q;", "getShouldRetry$ktor_client_core", "()Lr7/q;", "setShouldRetry$ktor_client_core", "(Lr7/q;)V", "shouldRetryOnException", "getShouldRetryOnException$ktor_client_core", "setShouldRetryOnException$ktor_client_core", "Lr7/p;", "getDelayMillis$ktor_client_core", "()Lr7/p;", "setDelayMillis$ktor_client_core", "getModifyRequest$ktor_client_core", "setModifyRequest$ktor_client_core", "getDelay$ktor_client_core", "setDelay$ktor_client_core", "I", "getMaxRetries", "()I", "setMaxRetries", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Configuration {
        public p<? super DelayContext, ? super Integer, Long> delayMillis;
        private int maxRetries;
        public q<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> shouldRetry;
        public q<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> shouldRetryOnException;
        private p<? super ModifyRequestContext, ? super HttpRequestBuilder, t0> modifyRequest = HttpRequestRetry$Configuration$modifyRequest$1.INSTANCE;
        private p<? super Long, ? super d<? super t0>, ? extends Object> delay = new HttpRequestRetry$Configuration$delay$1(null);

        public Configuration() {
            retryOnExceptionOrServerErrors(3);
            exponentialDelay$default(this, 0.0d, 0L, 0L, false, 15, null);
        }

        public static /* synthetic */ void constantDelay$default(Configuration configuration, long j10, long j11, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = 1000;
            }
            if ((i10 & 2) != 0) {
                j11 = 1000;
            }
            if ((i10 & 4) != 0) {
                z = true;
            }
            configuration.constantDelay(j10, j11, z);
        }

        public static /* synthetic */ void delayMillis$default(Configuration configuration, boolean z, p pVar, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = true;
            }
            configuration.delayMillis(z, pVar);
        }

        public static /* synthetic */ void exponentialDelay$default(Configuration configuration, double d4, long j10, long j11, boolean z, int i10, Object obj) {
            configuration.exponentialDelay((i10 & 1) != 0 ? 2.0d : d4, (i10 & 2) != 0 ? 60000L : j10, (i10 & 4) != 0 ? 1000L : j11, (i10 & 8) != 0 ? true : z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long randomMs(long randomizationMs) {
            if (randomizationMs == 0) {
                return 0L;
            }
            return v7.d.f22178l.g(randomizationMs);
        }

        public static /* synthetic */ void retryIf$default(Configuration configuration, int i10, q qVar, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            configuration.retryIf(i10, qVar);
        }

        public static /* synthetic */ void retryOnException$default(Configuration configuration, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            configuration.retryOnException(i10);
        }

        public static /* synthetic */ void retryOnExceptionIf$default(Configuration configuration, int i10, q qVar, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            configuration.retryOnExceptionIf(i10, qVar);
        }

        public static /* synthetic */ void retryOnExceptionOrServerErrors$default(Configuration configuration, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            configuration.retryOnExceptionOrServerErrors(i10);
        }

        public static /* synthetic */ void retryOnServerErrors$default(Configuration configuration, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            configuration.retryOnServerErrors(i10);
        }

        public final void constantDelay(long millis, long randomizationMs, boolean respectRetryAfterHeader) {
            if (millis <= 0) {
                throw new IllegalStateException("Check failed.");
            }
            if (randomizationMs < 0) {
                throw new IllegalStateException("Check failed.");
            }
            delayMillis(respectRetryAfterHeader, new HttpRequestRetry$Configuration$constantDelay$1(millis, this, randomizationMs));
        }

        public final void delay(p<? super Long, ? super d<? super t0>, ? extends Object> block) {
            this.delay = block;
        }

        public final void delayMillis(boolean respectRetryAfterHeader, p<? super DelayContext, ? super Integer, Long> block) {
            setDelayMillis$ktor_client_core(new HttpRequestRetry$Configuration$delayMillis$1(respectRetryAfterHeader, block));
        }

        public final void exponentialDelay(double base, long maxDelayMs, long randomizationMs, boolean respectRetryAfterHeader) {
            if (base <= 0.0d) {
                throw new IllegalStateException("Check failed.");
            }
            if (maxDelayMs <= 0) {
                throw new IllegalStateException("Check failed.");
            }
            if (randomizationMs < 0) {
                throw new IllegalStateException("Check failed.");
            }
            delayMillis(respectRetryAfterHeader, new HttpRequestRetry$Configuration$exponentialDelay$1(base, maxDelayMs, this, randomizationMs));
        }

        public final p<Long, d<? super t0>, Object> getDelay$ktor_client_core() {
            return this.delay;
        }

        public final p<DelayContext, Integer, Long> getDelayMillis$ktor_client_core() {
            p pVar = this.delayMillis;
            if (pVar != null) {
                return pVar;
            }
            kotlin.jvm.internal.p.i("delayMillis");
            throw null;
        }

        public final int getMaxRetries() {
            return this.maxRetries;
        }

        public final p<ModifyRequestContext, HttpRequestBuilder, t0> getModifyRequest$ktor_client_core() {
            return this.modifyRequest;
        }

        public final q<ShouldRetryContext, HttpRequest, HttpResponse, Boolean> getShouldRetry$ktor_client_core() {
            q qVar = this.shouldRetry;
            if (qVar != null) {
                return qVar;
            }
            kotlin.jvm.internal.p.i("shouldRetry");
            throw null;
        }

        public final q<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean> getShouldRetryOnException$ktor_client_core() {
            q qVar = this.shouldRetryOnException;
            if (qVar != null) {
                return qVar;
            }
            kotlin.jvm.internal.p.i("shouldRetryOnException");
            throw null;
        }

        public final void modifyRequest(p<? super ModifyRequestContext, ? super HttpRequestBuilder, t0> block) {
            this.modifyRequest = block;
        }

        public final void noRetry() {
            this.maxRetries = 0;
            setShouldRetry$ktor_client_core(HttpRequestRetry$Configuration$noRetry$1.INSTANCE);
            setShouldRetryOnException$ktor_client_core(HttpRequestRetry$Configuration$noRetry$2.INSTANCE);
        }

        public final void retryIf(int maxRetries, q<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> block) {
            if (maxRetries != -1) {
                this.maxRetries = maxRetries;
            }
            setShouldRetry$ktor_client_core(block);
        }

        @e
        public final /* synthetic */ void retryOnException(int maxRetries) {
            retryOnException(maxRetries, false);
        }

        public final void retryOnExceptionIf(int maxRetries, q<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> block) {
            if (maxRetries != -1) {
                this.maxRetries = maxRetries;
            }
            setShouldRetryOnException$ktor_client_core(block);
        }

        public final void retryOnExceptionOrServerErrors(int maxRetries) {
            retryOnServerErrors(maxRetries);
            retryOnException$default(this, maxRetries, false, 2, null);
        }

        public final void retryOnServerErrors(int maxRetries) {
            retryIf(maxRetries, HttpRequestRetry$Configuration$retryOnServerErrors$1.INSTANCE);
        }

        public final void setDelay$ktor_client_core(p<? super Long, ? super d<? super t0>, ? extends Object> pVar) {
            this.delay = pVar;
        }

        public final void setDelayMillis$ktor_client_core(p<? super DelayContext, ? super Integer, Long> pVar) {
            this.delayMillis = pVar;
        }

        public final void setMaxRetries(int i10) {
            this.maxRetries = i10;
        }

        public final void setModifyRequest$ktor_client_core(p<? super ModifyRequestContext, ? super HttpRequestBuilder, t0> pVar) {
            this.modifyRequest = pVar;
        }

        public final void setShouldRetry$ktor_client_core(q<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> qVar) {
            this.shouldRetry = qVar;
        }

        public final void setShouldRetryOnException$ktor_client_core(q<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> qVar) {
            this.shouldRetryOnException = qVar;
        }

        public static /* synthetic */ void retryOnException$default(Configuration configuration, int i10, boolean z, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = -1;
            }
            if ((i11 & 2) != 0) {
                z = false;
            }
            configuration.retryOnException(i10, z);
        }

        public final void retryOnException(int maxRetries, boolean retryOnTimeout) {
            retryOnExceptionIf(maxRetries, new HttpRequestRetry$Configuration$retryOnException$1(retryOnTimeout));
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DelayContext {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;

        public DelayContext(HttpRequestBuilder httpRequestBuilder, HttpResponse httpResponse, Throwable th) {
            this.request = httpRequestBuilder;
            this.response = httpResponse;
            this.cause = th;
        }

        public final Throwable getCause() {
            return this.cause;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "retryCount", "", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;I)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ModifyRequestContext {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;
        private final int retryCount;

        public ModifyRequestContext(HttpRequestBuilder httpRequestBuilder, HttpResponse httpResponse, Throwable th, int i10) {
            this.request = httpRequestBuilder;
            this.response = httpResponse;
            this.cause = th;
            this.retryCount = i10;
        }

        public final Throwable getCause() {
            return this.cause;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpRequestRetry$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "Lio/ktor/client/plugins/HttpRequestRetry;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpRequestRetry;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpRequestRetry;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/plugins/HttpRequestRetry$RetryEventData;", "HttpRequestRetryEvent", "Lio/ktor/events/EventDefinition;", "getHttpRequestRetryEvent", "()Lio/ktor/events/EventDefinition;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Configuration, HttpRequestRetry> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final EventDefinition<RetryEventData> getHttpRequestRetryEvent() {
            return HttpRequestRetry.HttpRequestRetryEvent;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpRequestRetry> getKey() {
            return HttpRequestRetry.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpRequestRetry plugin, HttpClient scope) {
            plugin.intercept$ktor_client_core(scope);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpRequestRetry prepare(l<? super Configuration, t0> block) {
            Configuration configuration = new Configuration();
            block.invoke(configuration);
            return new HttpRequestRetry(configuration);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\u0018\u00002\u00020\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$RetryEventData;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "retryCount", "", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "(Lio/ktor/client/request/HttpRequestBuilder;ILio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RetryEventData {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;
        private final int retryCount;

        public RetryEventData(HttpRequestBuilder httpRequestBuilder, int i10, HttpResponse httpResponse, Throwable th) {
            this.request = httpRequestBuilder;
            this.retryCount = i10;
            this.response = httpResponse;
            this.cause = th;
        }

        public final Throwable getCause() {
            return this.cause;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "", "retryCount", "", "(I)V", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ShouldRetryContext {
        private final int retryCount;

        public ShouldRetryContext(int i10) {
            this.retryCount = i10;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpRequestRetry$prepareRequest$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        final /* synthetic */ HttpRequestBuilder $subRequest;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpRequestBuilder httpRequestBuilder) {
            super(1);
            this.$subRequest = httpRequestBuilder;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            t tVar = (t) this.$subRequest.getExecutionContext();
            if (th == null) {
                tVar.complete();
            } else {
                tVar.i(th);
            }
        }
    }

    public HttpRequestRetry(Configuration configuration) {
        this.shouldRetry = configuration.getShouldRetry$ktor_client_core();
        this.shouldRetryOnException = configuration.getShouldRetryOnException$ktor_client_core();
        this.delayMillis = configuration.getDelayMillis$ktor_client_core();
        this.delay = configuration.getDelay$ktor_client_core();
        this.maxRetries = configuration.getMaxRetries();
        this.modifyRequest = configuration.getModifyRequest$ktor_client_core();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HttpRequestBuilder prepareRequest(HttpRequestBuilder request) {
        HttpRequestBuilder httpRequestBuilderTakeFrom = new HttpRequestBuilder().takeFrom(request);
        request.getExecutionContext().invokeOnCompletion(new AnonymousClass1(httpRequestBuilderTakeFrom));
        return httpRequestBuilderTakeFrom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetry(int retryCount, int maxRetries, q<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> shouldRetry, HttpClientCall call) {
        return retryCount < maxRetries && ((Boolean) shouldRetry.invoke(new ShouldRetryContext(retryCount + 1), call.getRequest(), call.getResponse())).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetryOnException(int retryCount, int maxRetries, q<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> shouldRetry, HttpRequestBuilder subRequest, Throwable cause) {
        return retryCount < maxRetries && ((Boolean) shouldRetry.invoke(new ShouldRetryContext(retryCount + 1), subRequest, cause)).booleanValue();
    }

    public final void intercept$ktor_client_core(HttpClient client) {
        ((HttpSend) HttpClientPluginKt.plugin(client, HttpSend.INSTANCE)).intercept(new HttpRequestRetry$intercept$1(this, client, null));
    }
}
