package io.ktor.websocket;

import androidx.compose.foundation.c;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\n\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0019B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\b\u0010\u0018\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lio/ktor/websocket/CloseReason;", "", "code", "Lio/ktor/websocket/CloseReason$Codes;", "message", "", "(Lio/ktor/websocket/CloseReason$Codes;Ljava/lang/String;)V", "", "(SLjava/lang/String;)V", "getCode", "()S", "knownReason", "getKnownReason", "()Lio/ktor/websocket/CloseReason$Codes;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Codes", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class CloseReason {
    private final short code;
    private final String message;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\n\n\u0002\b\u0011\b\u0086\u0001\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lio/ktor/websocket/CloseReason$Codes;", "", "code", "", "(Ljava/lang/String;IS)V", "getCode", "()S", "NORMAL", "GOING_AWAY", "PROTOCOL_ERROR", "CANNOT_ACCEPT", "CLOSED_ABNORMALLY", "NOT_CONSISTENT", "VIOLATED_POLICY", "TOO_BIG", "NO_EXTENSION", "INTERNAL_ERROR", "SERVICE_RESTART", "TRY_AGAIN_LATER", "Companion", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Codes {
        NORMAL(1000),
        GOING_AWAY(1001),
        PROTOCOL_ERROR(1002),
        CANNOT_ACCEPT(1003),
        CLOSED_ABNORMALLY(1006),
        NOT_CONSISTENT(1007),
        VIOLATED_POLICY(1008),
        TOO_BIG(1009),
        NO_EXTENSION(1010),
        INTERNAL_ERROR(1011),
        SERVICE_RESTART(1012),
        TRY_AGAIN_LATER(1013);


        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Codes UNEXPECTED_CONDITION;
        private static final Map<Short, Codes> byCodeMap;
        private final short code;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\n\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\bR\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/websocket/CloseReason$Codes$Companion;", "", "()V", "UNEXPECTED_CONDITION", "Lio/ktor/websocket/CloseReason$Codes;", "getUNEXPECTED_CONDITION$annotations", "byCodeMap", "", "", "byCode", "code", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            @e
            public static /* synthetic */ void getUNEXPECTED_CONDITION$annotations() {
            }

            public final Codes byCode(short code) {
                return (Codes) Codes.byCodeMap.get(Short.valueOf(code));
            }

            private Companion() {
            }
        }

        static {
            Codes[] codesArrValues = values();
            int iQ0 = i0.q0(codesArrValues.length);
            LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
            for (Codes codes : codesArrValues) {
                linkedHashMap.put(Short.valueOf(codes.code), codes);
            }
            byCodeMap = linkedHashMap;
            UNEXPECTED_CONDITION = INTERNAL_ERROR;
        }

        Codes(short s10) {
            this.code = s10;
        }

        public final short getCode() {
            return this.code;
        }
    }

    public CloseReason(short s10, String str) {
        this.code = s10;
        this.message = str;
    }

    public static /* synthetic */ CloseReason copy$default(CloseReason closeReason, short s10, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            s10 = closeReason.code;
        }
        if ((i10 & 2) != 0) {
            str = closeReason.message;
        }
        return closeReason.copy(s10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final short getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final CloseReason copy(short code, String message) {
        return new CloseReason(code, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CloseReason)) {
            return false;
        }
        CloseReason closeReason = (CloseReason) other;
        return this.code == closeReason.code && p.a(this.message, closeReason.message);
    }

    public final short getCode() {
        return this.code;
    }

    public final Codes getKnownReason() {
        return Codes.INSTANCE.byCode(this.code);
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return this.message.hashCode() + (this.code * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("CloseReason(reason=");
        Object knownReason = getKnownReason();
        if (knownReason == null) {
            knownReason = Short.valueOf(this.code);
        }
        sb2.append(knownReason);
        sb2.append(", message=");
        return c.u(sb2, this.message, ')');
    }

    public CloseReason(Codes codes, String str) {
        this(codes.getCode(), str);
    }
}
