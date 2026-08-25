package io.ktor.client.plugins;

import androidx.media3.extractor.AacUtil;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.AttributeKey;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000¢\u0006\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006\"\u0018\u0010\t\u001a\u00060\u0007j\u0002`\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\r\"\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\r\"\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "addDefaultResponseValidation", "(Lio/ktor/client/HttpClientConfig;)V", "Lio/ktor/util/AttributeKey;", "ValidateMark", "Lio/ktor/util/AttributeKey;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "", "NO_RESPONSE_TEXT", "Ljava/lang/String;", "BODY_FAILED_DECODING", "DEPRECATED_EXCEPTION_CTOR", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultResponseValidationKt {
    private static final String BODY_FAILED_DECODING = "<body failed decoding>";
    private static final String DEPRECATED_EXCEPTION_CTOR = "Please, provide response text in constructor";
    private static final String NO_RESPONSE_TEXT = "<no response text provided>";
    private static final AttributeKey<t0> ValidateMark = new AttributeKey<>("ValidateMark");
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.DefaultResponseValidation");

    /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/HttpCallValidator$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/HttpCallValidator$Config;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<HttpCallValidator.Config, t0> {
        final /* synthetic */ HttpClientConfig<?> $this_addDefaultResponseValidation;

        /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "response", "Lx6/t0;", "<anonymous>", "(Lio/ktor/client/statement/HttpResponse;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1$1", f = "DefaultResponseValidation.kt", l = {AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE, 48}, m = "invokeSuspend")
        public static final class C02301 extends j implements p<HttpResponse, d<? super t0>, Object> {
            int I$0;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;

            public C02301(d<? super C02301> dVar) {
                super(2, dVar);
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C02301 c02301 = new C02301(dVar);
                c02301.L$0 = obj;
                return c02301;
            }

            @Override // r7.p
            public final Object invoke(HttpResponse httpResponse, d<? super t0> dVar) {
                return ((C02301) create(httpResponse, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(1:(1:(8:6|53|7|30|34|(2:39|(1:(1:48)(1:47))(1:43))(1:38)|49|50)(2:9|10))(1:11))(2:12|(2:14|15)(2:16|(1:51)(3:21|(1:24)|28)))|25|55|26|(6:29|30|34|(2:39|(1:(2:45|48)(0))(0))(0)|49|50)|28) */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x00c8, code lost:
            
                r2 = r11;
                r3 = r6;
             */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x00e6  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x00f2  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 290
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.DefaultResponseValidationKt.AnonymousClass1.C02301.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpClientConfig<?> httpClientConfig) {
            super(1);
            this.$this_addDefaultResponseValidation = httpClientConfig;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpCallValidator.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpCallValidator.Config config) {
            config.setExpectSuccess(this.$this_addDefaultResponseValidation.getExpectSuccess());
            config.validateResponse(new C02301(null));
        }
    }

    public static final void addDefaultResponseValidation(HttpClientConfig<?> httpClientConfig) {
        HttpCallValidatorKt.HttpResponseValidator(httpClientConfig, new AnonymousClass1(httpClientConfig));
    }
}
