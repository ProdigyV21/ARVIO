package androidx.media3.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class StuckPlayerException extends IllegalStateException {
    public static final int STUCK_BUFFERING_NOT_LOADING = 0;
    public static final int STUCK_BUFFERING_NO_PROGRESS = 1;
    public static final int STUCK_PLAYING_NOT_ENDING = 3;
    public static final int STUCK_PLAYING_NO_PROGRESS = 2;
    public static final int STUCK_SUPPRESSED = 4;
    public final int stuckType;
    public final int timeoutMs;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StuckType {
    }

    public StuckPlayerException(int i10, int i11) {
        super(getMessage(i10, i11));
        this.stuckType = i10;
        this.timeoutMs = i11;
    }

    private static String getMessage(int i10, int i11) {
        if (i10 == 0) {
            return androidx.compose.foundation.c.o(i11, "Player stuck buffering and not loading for ", " ms");
        }
        if (i10 == 1) {
            return androidx.compose.foundation.c.o(i11, "Player stuck buffering with no progress for ", " ms");
        }
        if (i10 == 2) {
            return androidx.compose.foundation.c.o(i11, "Player stuck playing with no progress for ", " ms");
        }
        if (i10 == 3) {
            return androidx.compose.foundation.c.o(i11, "Player stuck playing without ending for ", " ms");
        }
        if (i10 == 4) {
            return androidx.compose.foundation.c.o(i11, "Player stuck suppressed for ", " ms");
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && StuckPlayerException.class == obj.getClass()) {
            StuckPlayerException stuckPlayerException = (StuckPlayerException) obj;
            if (this.stuckType == stuckPlayerException.stuckType && this.timeoutMs == stuckPlayerException.timeoutMs) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((527 + this.stuckType) * 31) + this.timeoutMs;
    }
}
