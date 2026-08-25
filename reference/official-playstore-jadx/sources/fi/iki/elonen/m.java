package fi.iki.elonen;

import androidx.compose.runtime.ComposerKt;
import com.google.android.gms.cast.MediaError;

/* JADX INFO: loaded from: classes4.dex */
public enum m implements l {
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(101, "Switching Protocols"),
    OK(200, "OK"),
    /* JADX INFO: Fake field, exist only in values array */
    PRECONDITION_FAILED(201, "Created"),
    /* JADX INFO: Fake field, exist only in values array */
    ACCEPTED(202, "Accepted"),
    /* JADX INFO: Fake field, exist only in values array */
    NO_CONTENT(ComposerKt.providerMapsKey, "No Content"),
    /* JADX INFO: Fake field, exist only in values array */
    PARTIAL_CONTENT(ComposerKt.referenceKey, "Partial Content"),
    /* JADX INFO: Fake field, exist only in values array */
    MULTI_STATUS(ComposerKt.reuseKey, "Multi-Status"),
    /* JADX INFO: Fake field, exist only in values array */
    REDIRECT(MediaError.DetailedErrorCode.SEGMENT_NETWORK, "Moved Permanently"),
    /* JADX INFO: Fake field, exist only in values array */
    FOUND(302, "Found"),
    /* JADX INFO: Fake field, exist only in values array */
    REDIRECT_SEE_OTHER(303, "See Other"),
    /* JADX INFO: Fake field, exist only in values array */
    NOT_MODIFIED(304, "Not Modified"),
    /* JADX INFO: Fake field, exist only in values array */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    BAD_REQUEST(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, "Bad Request"),
    /* JADX INFO: Fake field, exist only in values array */
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    /* JADX INFO: Fake field, exist only in values array */
    PRECONDITION_FAILED(405, "Method Not Allowed"),
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(406, "Not Acceptable"),
    /* JADX INFO: Fake field, exist only in values array */
    EXPECTATION_FAILED(408, "Request Timeout"),
    /* JADX INFO: Fake field, exist only in values array */
    PRECONDITION_FAILED(409, "Conflict"),
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(410, "Gone"),
    /* JADX INFO: Fake field, exist only in values array */
    EXPECTATION_FAILED(MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER, "Length Required"),
    /* JADX INFO: Fake field, exist only in values array */
    PRECONDITION_FAILED(MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, "Precondition Failed"),
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(413, "Payload Too Large"),
    /* JADX INFO: Fake field, exist only in values array */
    EXPECTATION_FAILED(415, "Unsupported Media Type"),
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(416, "Requested Range Not Satisfiable"),
    /* JADX INFO: Fake field, exist only in values array */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /* JADX INFO: Fake field, exist only in values array */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    /* JADX INFO: Fake field, exist only in values array */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /* JADX INFO: Fake field, exist only in values array */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /* JADX INFO: Fake field, exist only in values array */
    UNSUPPORTED_HTTP_VERSION(505, "HTTP Version Not Supported");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f15442i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f15443l;

    m(int i10, String str) {
        this.f15442i = i10;
        this.f15443l = str;
    }

    @Override // fi.iki.elonen.l
    public final String a() {
        return "" + this.f15442i + " " + this.f15443l;
    }
}
