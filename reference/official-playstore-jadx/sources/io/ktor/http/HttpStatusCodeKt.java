package io.ktor.http;

import io.ktor.http.HttpStatusCode;
import java.util.List;
import kotlin.Metadata;
import t7.a;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0000\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0001\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"ExceptionFailed", "Lio/ktor/http/HttpStatusCode;", "Lio/ktor/http/HttpStatusCode$Companion;", "getExceptionFailed$annotations", "(Lio/ktor/http/HttpStatusCode$Companion;)V", "getExceptionFailed", "(Lio/ktor/http/HttpStatusCode$Companion;)Lio/ktor/http/HttpStatusCode;", "allStatusCodes", "", "isSuccess", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpStatusCodeKt {
    public static final List<HttpStatusCode> allStatusCodes() {
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        return a.E(companion.getContinue(), companion.getSwitchingProtocols(), companion.getProcessing(), companion.getOK(), companion.getCreated(), companion.getAccepted(), companion.getNonAuthoritativeInformation(), companion.getNoContent(), companion.getResetContent(), companion.getPartialContent(), companion.getMultiStatus(), companion.getMultipleChoices(), companion.getMovedPermanently(), companion.getFound(), companion.getSeeOther(), companion.getNotModified(), companion.getUseProxy(), companion.getSwitchProxy(), companion.getTemporaryRedirect(), companion.getPermanentRedirect(), companion.getBadRequest(), companion.getUnauthorized(), companion.getPaymentRequired(), companion.getForbidden(), companion.getNotFound(), companion.getMethodNotAllowed(), companion.getNotAcceptable(), companion.getProxyAuthenticationRequired(), companion.getRequestTimeout(), companion.getConflict(), companion.getGone(), companion.getLengthRequired(), companion.getPreconditionFailed(), companion.getPayloadTooLarge(), companion.getRequestURITooLong(), companion.getUnsupportedMediaType(), companion.getRequestedRangeNotSatisfiable(), companion.getExpectationFailed(), companion.getUnprocessableEntity(), companion.getLocked(), companion.getFailedDependency(), companion.getTooEarly(), companion.getUpgradeRequired(), companion.getTooManyRequests(), companion.getRequestHeaderFieldTooLarge(), companion.getInternalServerError(), companion.getNotImplemented(), companion.getBadGateway(), companion.getServiceUnavailable(), companion.getGatewayTimeout(), companion.getVersionNotSupported(), companion.getVariantAlsoNegotiates(), companion.getInsufficientStorage());
    }

    public static final HttpStatusCode getExceptionFailed(HttpStatusCode.Companion companion) {
        return companion.getExpectationFailed();
    }

    @e
    public static /* synthetic */ void getExceptionFailed$annotations(HttpStatusCode.Companion companion) {
    }

    public static final boolean isSuccess(HttpStatusCode httpStatusCode) {
        int value = httpStatusCode.getValue();
        return 200 <= value && value < 300;
    }
}
