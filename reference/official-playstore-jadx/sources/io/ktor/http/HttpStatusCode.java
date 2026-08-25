package io.ktor.http;

import androidx.compose.runtime.ComposerKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0002J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0005J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lio/ktor/http/HttpStatusCode;", "", "value", "", MediaTrack.ROLE_DESCRIPTION, "", "(ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getValue", "()I", "compareTo", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpStatusCode implements Comparable<HttpStatusCode> {
    private static final List<HttpStatusCode> allStatusCodes;
    private static final Map<Integer, HttpStatusCode> statusCodesMap;
    private final String description;
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HttpStatusCode Continue = new HttpStatusCode(100, "Continue");
    private static final HttpStatusCode SwitchingProtocols = new HttpStatusCode(101, "Switching Protocols");
    private static final HttpStatusCode Processing = new HttpStatusCode(MediaError.DetailedErrorCode.MEDIA_DECODE, "Processing");
    private static final HttpStatusCode OK = new HttpStatusCode(200, "OK");
    private static final HttpStatusCode Created = new HttpStatusCode(201, "Created");
    private static final HttpStatusCode Accepted = new HttpStatusCode(202, "Accepted");
    private static final HttpStatusCode NonAuthoritativeInformation = new HttpStatusCode(203, "Non-Authoritative Information");
    private static final HttpStatusCode NoContent = new HttpStatusCode(ComposerKt.providerMapsKey, "No Content");
    private static final HttpStatusCode ResetContent = new HttpStatusCode(205, "Reset Content");
    private static final HttpStatusCode PartialContent = new HttpStatusCode(ComposerKt.referenceKey, "Partial Content");
    private static final HttpStatusCode MultiStatus = new HttpStatusCode(ComposerKt.reuseKey, "Multi-Status");
    private static final HttpStatusCode MultipleChoices = new HttpStatusCode(300, "Multiple Choices");
    private static final HttpStatusCode MovedPermanently = new HttpStatusCode(MediaError.DetailedErrorCode.SEGMENT_NETWORK, "Moved Permanently");
    private static final HttpStatusCode Found = new HttpStatusCode(302, "Found");
    private static final HttpStatusCode SeeOther = new HttpStatusCode(303, "See Other");
    private static final HttpStatusCode NotModified = new HttpStatusCode(304, "Not Modified");
    private static final HttpStatusCode UseProxy = new HttpStatusCode(305, "Use Proxy");
    private static final HttpStatusCode SwitchProxy = new HttpStatusCode(306, "Switch Proxy");
    private static final HttpStatusCode TemporaryRedirect = new HttpStatusCode(307, "Temporary Redirect");
    private static final HttpStatusCode PermanentRedirect = new HttpStatusCode(308, "Permanent Redirect");
    private static final HttpStatusCode BadRequest = new HttpStatusCode(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, "Bad Request");
    private static final HttpStatusCode Unauthorized = new HttpStatusCode(401, "Unauthorized");
    private static final HttpStatusCode PaymentRequired = new HttpStatusCode(402, "Payment Required");
    private static final HttpStatusCode Forbidden = new HttpStatusCode(403, "Forbidden");
    private static final HttpStatusCode NotFound = new HttpStatusCode(404, "Not Found");
    private static final HttpStatusCode MethodNotAllowed = new HttpStatusCode(405, "Method Not Allowed");
    private static final HttpStatusCode NotAcceptable = new HttpStatusCode(406, "Not Acceptable");
    private static final HttpStatusCode ProxyAuthenticationRequired = new HttpStatusCode(407, "Proxy Authentication Required");
    private static final HttpStatusCode RequestTimeout = new HttpStatusCode(408, "Request Timeout");
    private static final HttpStatusCode Conflict = new HttpStatusCode(409, "Conflict");
    private static final HttpStatusCode Gone = new HttpStatusCode(410, "Gone");
    private static final HttpStatusCode LengthRequired = new HttpStatusCode(MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER, "Length Required");
    private static final HttpStatusCode PreconditionFailed = new HttpStatusCode(MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, "Precondition Failed");
    private static final HttpStatusCode PayloadTooLarge = new HttpStatusCode(413, "Payload Too Large");
    private static final HttpStatusCode RequestURITooLong = new HttpStatusCode(414, "Request-URI Too Long");
    private static final HttpStatusCode UnsupportedMediaType = new HttpStatusCode(415, "Unsupported Media Type");
    private static final HttpStatusCode RequestedRangeNotSatisfiable = new HttpStatusCode(416, "Requested Range Not Satisfiable");
    private static final HttpStatusCode ExpectationFailed = new HttpStatusCode(417, "Expectation Failed");
    private static final HttpStatusCode UnprocessableEntity = new HttpStatusCode(MediaError.DetailedErrorCode.DASH_MANIFEST_NO_MIMETYPE, "Unprocessable Entity");
    private static final HttpStatusCode Locked = new HttpStatusCode(MediaError.DetailedErrorCode.DASH_INVALID_SEGMENT_INFO, "Locked");
    private static final HttpStatusCode FailedDependency = new HttpStatusCode(424, "Failed Dependency");
    private static final HttpStatusCode TooEarly = new HttpStatusCode(425, "Too Early");
    private static final HttpStatusCode UpgradeRequired = new HttpStatusCode(426, "Upgrade Required");
    private static final HttpStatusCode TooManyRequests = new HttpStatusCode(429, "Too Many Requests");
    private static final HttpStatusCode RequestHeaderFieldTooLarge = new HttpStatusCode(MediaError.DetailedErrorCode.SMOOTH_MANIFEST, "Request Header Fields Too Large");
    private static final HttpStatusCode InternalServerError = new HttpStatusCode(500, "Internal Server Error");
    private static final HttpStatusCode NotImplemented = new HttpStatusCode(501, "Not Implemented");
    private static final HttpStatusCode BadGateway = new HttpStatusCode(502, "Bad Gateway");
    private static final HttpStatusCode ServiceUnavailable = new HttpStatusCode(503, "Service Unavailable");
    private static final HttpStatusCode GatewayTimeout = new HttpStatusCode(504, "Gateway Timeout");
    private static final HttpStatusCode VersionNotSupported = new HttpStatusCode(505, "HTTP Version Not Supported");
    private static final HttpStatusCode VariantAlsoNegotiates = new HttpStatusCode(506, "Variant Also Negotiates");
    private static final HttpStatusCode InsufficientStorage = new HttpStatusCode(507, "Insufficient Storage");

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bk\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020uR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0011\u0010/\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0011\u00101\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0011\u00105\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0011\u00107\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0011\u00109\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0011\u0010;\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0011\u0010=\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0011\u0010?\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0011\u0010A\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0011\u0010C\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0011\u0010E\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0011\u0010G\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0011\u0010I\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R\u0011\u0010K\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0011\u0010M\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0011\u0010O\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0011\u0010Q\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0011\u0010S\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0011\u0010U\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0011\u0010W\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0011\u0010Y\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0011\u0010[\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0011\u0010]\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0011\u0010_\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0011\u0010a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0011\u0010c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006R\u0011\u0010e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0006R\u0011\u0010g\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0006R\u0011\u0010i\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0006R\u0011\u0010k\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0006R\u0011\u0010m\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0006R\u0017\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00040p¢\u0006\b\n\u0000\u001a\u0004\bq\u0010rR\u001a\u0010s\u001a\u000e\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\u00040tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006x"}, d2 = {"Lio/ktor/http/HttpStatusCode$Companion;", "", "()V", "Accepted", "Lio/ktor/http/HttpStatusCode;", "getAccepted", "()Lio/ktor/http/HttpStatusCode;", "BadGateway", "getBadGateway", "BadRequest", "getBadRequest", "Conflict", "getConflict", "Continue", "getContinue", "Created", "getCreated", "ExpectationFailed", "getExpectationFailed", "FailedDependency", "getFailedDependency", "Forbidden", "getForbidden", "Found", "getFound", "GatewayTimeout", "getGatewayTimeout", "Gone", "getGone", "InsufficientStorage", "getInsufficientStorage", "InternalServerError", "getInternalServerError", "LengthRequired", "getLengthRequired", "Locked", "getLocked", "MethodNotAllowed", "getMethodNotAllowed", "MovedPermanently", "getMovedPermanently", "MultiStatus", "getMultiStatus", "MultipleChoices", "getMultipleChoices", "NoContent", "getNoContent", "NonAuthoritativeInformation", "getNonAuthoritativeInformation", "NotAcceptable", "getNotAcceptable", "NotFound", "getNotFound", "NotImplemented", "getNotImplemented", "NotModified", "getNotModified", "OK", "getOK", "PartialContent", "getPartialContent", "PayloadTooLarge", "getPayloadTooLarge", "PaymentRequired", "getPaymentRequired", "PermanentRedirect", "getPermanentRedirect", "PreconditionFailed", "getPreconditionFailed", "Processing", "getProcessing", "ProxyAuthenticationRequired", "getProxyAuthenticationRequired", "RequestHeaderFieldTooLarge", "getRequestHeaderFieldTooLarge", "RequestTimeout", "getRequestTimeout", "RequestURITooLong", "getRequestURITooLong", "RequestedRangeNotSatisfiable", "getRequestedRangeNotSatisfiable", "ResetContent", "getResetContent", "SeeOther", "getSeeOther", "ServiceUnavailable", "getServiceUnavailable", "SwitchProxy", "getSwitchProxy", "SwitchingProtocols", "getSwitchingProtocols", "TemporaryRedirect", "getTemporaryRedirect", "TooEarly", "getTooEarly", "TooManyRequests", "getTooManyRequests", "Unauthorized", "getUnauthorized", "UnprocessableEntity", "getUnprocessableEntity", "UnsupportedMediaType", "getUnsupportedMediaType", "UpgradeRequired", "getUpgradeRequired", "UseProxy", "getUseProxy", "VariantAlsoNegotiates", "getVariantAlsoNegotiates", "VersionNotSupported", "getVersionNotSupported", "allStatusCodes", "", "getAllStatusCodes", "()Ljava/util/List;", "statusCodesMap", "", "", "fromValue", "value", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final HttpStatusCode fromValue(int value) {
            HttpStatusCode httpStatusCode = (HttpStatusCode) HttpStatusCode.statusCodesMap.get(Integer.valueOf(value));
            return httpStatusCode == null ? new HttpStatusCode(value, "Unknown Status Code") : httpStatusCode;
        }

        public final HttpStatusCode getAccepted() {
            return HttpStatusCode.Accepted;
        }

        public final List<HttpStatusCode> getAllStatusCodes() {
            return HttpStatusCode.allStatusCodes;
        }

        public final HttpStatusCode getBadGateway() {
            return HttpStatusCode.BadGateway;
        }

        public final HttpStatusCode getBadRequest() {
            return HttpStatusCode.BadRequest;
        }

        public final HttpStatusCode getConflict() {
            return HttpStatusCode.Conflict;
        }

        public final HttpStatusCode getContinue() {
            return HttpStatusCode.Continue;
        }

        public final HttpStatusCode getCreated() {
            return HttpStatusCode.Created;
        }

        public final HttpStatusCode getExpectationFailed() {
            return HttpStatusCode.ExpectationFailed;
        }

        public final HttpStatusCode getFailedDependency() {
            return HttpStatusCode.FailedDependency;
        }

        public final HttpStatusCode getForbidden() {
            return HttpStatusCode.Forbidden;
        }

        public final HttpStatusCode getFound() {
            return HttpStatusCode.Found;
        }

        public final HttpStatusCode getGatewayTimeout() {
            return HttpStatusCode.GatewayTimeout;
        }

        public final HttpStatusCode getGone() {
            return HttpStatusCode.Gone;
        }

        public final HttpStatusCode getInsufficientStorage() {
            return HttpStatusCode.InsufficientStorage;
        }

        public final HttpStatusCode getInternalServerError() {
            return HttpStatusCode.InternalServerError;
        }

        public final HttpStatusCode getLengthRequired() {
            return HttpStatusCode.LengthRequired;
        }

        public final HttpStatusCode getLocked() {
            return HttpStatusCode.Locked;
        }

        public final HttpStatusCode getMethodNotAllowed() {
            return HttpStatusCode.MethodNotAllowed;
        }

        public final HttpStatusCode getMovedPermanently() {
            return HttpStatusCode.MovedPermanently;
        }

        public final HttpStatusCode getMultiStatus() {
            return HttpStatusCode.MultiStatus;
        }

        public final HttpStatusCode getMultipleChoices() {
            return HttpStatusCode.MultipleChoices;
        }

        public final HttpStatusCode getNoContent() {
            return HttpStatusCode.NoContent;
        }

        public final HttpStatusCode getNonAuthoritativeInformation() {
            return HttpStatusCode.NonAuthoritativeInformation;
        }

        public final HttpStatusCode getNotAcceptable() {
            return HttpStatusCode.NotAcceptable;
        }

        public final HttpStatusCode getNotFound() {
            return HttpStatusCode.NotFound;
        }

        public final HttpStatusCode getNotImplemented() {
            return HttpStatusCode.NotImplemented;
        }

        public final HttpStatusCode getNotModified() {
            return HttpStatusCode.NotModified;
        }

        public final HttpStatusCode getOK() {
            return HttpStatusCode.OK;
        }

        public final HttpStatusCode getPartialContent() {
            return HttpStatusCode.PartialContent;
        }

        public final HttpStatusCode getPayloadTooLarge() {
            return HttpStatusCode.PayloadTooLarge;
        }

        public final HttpStatusCode getPaymentRequired() {
            return HttpStatusCode.PaymentRequired;
        }

        public final HttpStatusCode getPermanentRedirect() {
            return HttpStatusCode.PermanentRedirect;
        }

        public final HttpStatusCode getPreconditionFailed() {
            return HttpStatusCode.PreconditionFailed;
        }

        public final HttpStatusCode getProcessing() {
            return HttpStatusCode.Processing;
        }

        public final HttpStatusCode getProxyAuthenticationRequired() {
            return HttpStatusCode.ProxyAuthenticationRequired;
        }

        public final HttpStatusCode getRequestHeaderFieldTooLarge() {
            return HttpStatusCode.RequestHeaderFieldTooLarge;
        }

        public final HttpStatusCode getRequestTimeout() {
            return HttpStatusCode.RequestTimeout;
        }

        public final HttpStatusCode getRequestURITooLong() {
            return HttpStatusCode.RequestURITooLong;
        }

        public final HttpStatusCode getRequestedRangeNotSatisfiable() {
            return HttpStatusCode.RequestedRangeNotSatisfiable;
        }

        public final HttpStatusCode getResetContent() {
            return HttpStatusCode.ResetContent;
        }

        public final HttpStatusCode getSeeOther() {
            return HttpStatusCode.SeeOther;
        }

        public final HttpStatusCode getServiceUnavailable() {
            return HttpStatusCode.ServiceUnavailable;
        }

        public final HttpStatusCode getSwitchProxy() {
            return HttpStatusCode.SwitchProxy;
        }

        public final HttpStatusCode getSwitchingProtocols() {
            return HttpStatusCode.SwitchingProtocols;
        }

        public final HttpStatusCode getTemporaryRedirect() {
            return HttpStatusCode.TemporaryRedirect;
        }

        public final HttpStatusCode getTooEarly() {
            return HttpStatusCode.TooEarly;
        }

        public final HttpStatusCode getTooManyRequests() {
            return HttpStatusCode.TooManyRequests;
        }

        public final HttpStatusCode getUnauthorized() {
            return HttpStatusCode.Unauthorized;
        }

        public final HttpStatusCode getUnprocessableEntity() {
            return HttpStatusCode.UnprocessableEntity;
        }

        public final HttpStatusCode getUnsupportedMediaType() {
            return HttpStatusCode.UnsupportedMediaType;
        }

        public final HttpStatusCode getUpgradeRequired() {
            return HttpStatusCode.UpgradeRequired;
        }

        public final HttpStatusCode getUseProxy() {
            return HttpStatusCode.UseProxy;
        }

        public final HttpStatusCode getVariantAlsoNegotiates() {
            return HttpStatusCode.VariantAlsoNegotiates;
        }

        public final HttpStatusCode getVersionNotSupported() {
            return HttpStatusCode.VersionNotSupported;
        }

        private Companion() {
        }
    }

    static {
        List<HttpStatusCode> listAllStatusCodes = HttpStatusCodeKt.allStatusCodes();
        allStatusCodes = listAllStatusCodes;
        int iQ0 = i0.q0(s.U(listAllStatusCodes, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : listAllStatusCodes) {
            linkedHashMap.put(Integer.valueOf(((HttpStatusCode) obj).value), obj);
        }
        statusCodesMap = linkedHashMap;
    }

    public HttpStatusCode(int i10, String str) {
        this.value = i10;
        this.description = str;
    }

    public static /* synthetic */ HttpStatusCode copy$default(HttpStatusCode httpStatusCode, int i10, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = httpStatusCode.value;
        }
        if ((i11 & 2) != 0) {
            str = httpStatusCode.description;
        }
        return httpStatusCode.copy(i10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final HttpStatusCode copy(int value, String description) {
        return new HttpStatusCode(value, description);
    }

    public final HttpStatusCode description(String value) {
        return copy$default(this, 0, value, 1, null);
    }

    public boolean equals(Object other) {
        return (other instanceof HttpStatusCode) && ((HttpStatusCode) other).value == this.value;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return this.value + ' ' + this.description;
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpStatusCode other) {
        return this.value - other.value;
    }
}
