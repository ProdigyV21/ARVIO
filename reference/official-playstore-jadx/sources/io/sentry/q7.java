package io.sentry;

import com.google.android.gms.cast.MediaError;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public enum q7 implements i2 {
    OK(0, 399),
    CANCELLED(499),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    UNKNOWN_ERROR(500),
    INVALID_ARGUMENT(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN),
    DEADLINE_EXCEEDED(504),
    NOT_FOUND(404),
    ALREADY_EXISTS(409),
    PERMISSION_DENIED(403),
    RESOURCE_EXHAUSTED(429),
    FAILED_PRECONDITION(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN),
    ABORTED(409),
    OUT_OF_RANGE(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN),
    UNIMPLEMENTED(501),
    UNAVAILABLE(503),
    DATA_LOSS(500),
    UNAUTHENTICATED(401);

    private final int maxHttpStatusCode;
    private final int minHttpStatusCode;

    q7(int i10) {
        this.minHttpStatusCode = i10;
        this.maxHttpStatusCode = i10;
    }

    public static q7 fromApiNameSafely(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static q7 fromHttpStatusCode(int i10) {
        for (q7 q7Var : values()) {
            if (q7Var.matches(i10)) {
                return q7Var;
            }
        }
        return null;
    }

    private boolean matches(int i10) {
        return i10 >= this.minHttpStatusCode && i10 <= this.maxHttpStatusCode;
    }

    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.value(apiName());
    }

    public static q7 fromHttpStatusCode(Integer num, q7 q7Var) {
        q7 q7VarFromHttpStatusCode = num != null ? fromHttpStatusCode(num.intValue()) : q7Var;
        return q7VarFromHttpStatusCode != null ? q7VarFromHttpStatusCode : q7Var;
    }

    q7(int i10, int i11) {
        this.minHttpStatusCode = i10;
        this.maxHttpStatusCode = i11;
    }
}
