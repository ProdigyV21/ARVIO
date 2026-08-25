package io.ktor.http.content;

import io.ktor.http.HttpStatusCode;
import kotlin.Metadata;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'OK' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lio/ktor/http/content/VersionCheckResult;", "", "statusCode", "Lio/ktor/http/HttpStatusCode;", "(Ljava/lang/String;ILio/ktor/http/HttpStatusCode;)V", "getStatusCode", "()Lio/ktor/http/HttpStatusCode;", "OK", "NOT_MODIFIED", "PRECONDITION_FAILED", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VersionCheckResult {
    private static final /* synthetic */ VersionCheckResult[] $VALUES;
    public static final VersionCheckResult NOT_MODIFIED;
    public static final VersionCheckResult OK;
    public static final VersionCheckResult PRECONDITION_FAILED;
    private final HttpStatusCode statusCode;

    private static final /* synthetic */ VersionCheckResult[] $values() {
        return new VersionCheckResult[]{OK, NOT_MODIFIED, PRECONDITION_FAILED};
    }

    static {
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        OK = new VersionCheckResult("OK", 0, companion.getOK());
        NOT_MODIFIED = new VersionCheckResult("NOT_MODIFIED", 1, companion.getNotModified());
        PRECONDITION_FAILED = new VersionCheckResult("PRECONDITION_FAILED", 2, companion.getPreconditionFailed());
        $VALUES = $values();
    }

    private VersionCheckResult(String str, int i10, HttpStatusCode httpStatusCode) {
        this.statusCode = httpStatusCode;
    }

    public static VersionCheckResult valueOf(String str) {
        return (VersionCheckResult) Enum.valueOf(VersionCheckResult.class, str);
    }

    public static VersionCheckResult[] values() {
        return (VersionCheckResult[]) $VALUES.clone();
    }

    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }
}
