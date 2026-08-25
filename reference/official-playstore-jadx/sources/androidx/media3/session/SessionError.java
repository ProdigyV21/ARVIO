package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class SessionError {
    static final String DEFAULT_ERROR_MESSAGE = "no error message provided";
    public static final int ERROR_BAD_VALUE = -3;
    public static final int ERROR_INVALID_STATE = -2;
    public static final int ERROR_IO = -5;
    public static final int ERROR_NOT_SUPPORTED = -6;
    public static final int ERROR_PERMISSION_DENIED = -4;
    public static final int ERROR_SESSION_AUTHENTICATION_EXPIRED = -102;
    public static final int ERROR_SESSION_CONCURRENT_STREAM_LIMIT = -104;
    public static final int ERROR_SESSION_CONTENT_ALREADY_PLAYING = -110;
    public static final int ERROR_SESSION_DISCONNECTED = -100;
    public static final int ERROR_SESSION_END_OF_PLAYLIST = -109;
    public static final int ERROR_SESSION_NOT_AVAILABLE_IN_REGION = -106;
    public static final int ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED = -105;
    public static final int ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED = -103;
    public static final int ERROR_SESSION_SETUP_REQUIRED = -108;
    public static final int ERROR_SESSION_SKIP_LIMIT_REACHED = -107;
    public static final int ERROR_UNKNOWN = -1;
    public static final int INFO_CANCELLED = 1;
    public int code;
    public Bundle extras;
    public String message;
    private static final String FIELD_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_MESSAGE = Util.intToStringMaxRadix(1);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(2);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    public SessionError(int i10, String str) {
        this(i10, str, Bundle.EMPTY);
    }

    public static SessionError fromBundle(Bundle bundle) {
        int i10 = bundle.getInt(FIELD_CODE, 1000);
        String string = bundle.getString(FIELD_MESSAGE, "");
        Bundle bundle2 = bundle.getBundle(FIELD_EXTRAS);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new SessionError(i10, string, bundle2);
    }

    public static String getErrorCodeName(int i10) {
        if (i10 == -100) {
            return "ERROR_SESSION_DISCONNECTED";
        }
        if (i10 == 1) {
            return "INFO_CANCELLED";
        }
        switch (i10) {
            case -110:
                return "ERROR_SESSION_CONTENT_ALREADY_PLAYING";
            case -109:
                return "ERROR_SESSION_END_OF_PLAYLIST";
            case -108:
                return "ERROR_SESSION_SETUP_REQUIRED";
            case -107:
                return "ERROR_SESSION_SKIP_LIMIT_REACHED";
            case -106:
                return "ERROR_SESSION_NOT_AVAILABLE_IN_REGION";
            case -105:
                return "ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED";
            case -104:
                return "ERROR_SESSION_CONCURRENT_STREAM_LIMIT";
            case -103:
                return "ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED";
            case -102:
                return "ERROR_SESSION_AUTHENTICATION_EXPIRED";
            default:
                switch (i10) {
                    case -6:
                        return "ERROR_NOT_SUPPORTED";
                    case -5:
                        return "ERROR_IO";
                    case -4:
                        return "ERROR_PERMISSION_DENIED";
                    case -3:
                        return "ERROR_BAD_VALUE";
                    case -2:
                        return "ERROR_INVALID_STATE";
                    case -1:
                        return "ERROR_UNKNOWN";
                    default:
                        return "invalid error code";
                }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionError)) {
            return false;
        }
        SessionError sessionError = (SessionError) obj;
        return this.code == sessionError.code && Objects.equals(this.message, sessionError.message);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.code), this.message);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_CODE, this.code);
        bundle.putString(FIELD_MESSAGE, this.message);
        if (!this.extras.isEmpty()) {
            bundle.putBundle(FIELD_EXTRAS, this.extras);
        }
        return bundle;
    }

    public SessionError(int i10, String str, Bundle bundle) {
        boolean z = true;
        if (i10 >= 0 && i10 != 1) {
            z = false;
        }
        ac.b.j(z);
        this.code = i10;
        this.message = str;
        this.extras = bundle;
    }
}
