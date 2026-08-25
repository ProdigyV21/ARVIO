package com.arflix.tv.data.telegram;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u0004\u0005\u0006\u0007\b\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState;", "", "<init>", "()V", "Idle", "Initializing", "WaitPhone", "WaitQr", "WaitCode", "WaitPassword", "Ready", "Error", "Lcom/arflix/tv/data/telegram/TelegramAuthState$Error;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$Idle;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$Initializing;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$Ready;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitCode;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitPassword;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitPhone;", "Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitQr;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class TelegramAuthState {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$Error;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends TelegramAuthState {
        public static final int $stable = 0;
        private final String message;

        public Error(String str) {
            super(null);
            this.message = str;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = error.message;
            }
            return error.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Error copy(String message) {
            return new Error(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && p.a(this.message, ((Error) other).message);
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return a0.c.l("Error(message=", this.message, ")");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$Idle;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Idle extends TelegramAuthState {
        public static final int $stable = 0;
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Idle);
        }

        public int hashCode() {
            return 56017396;
        }

        public String toString() {
            return "Idle";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$Initializing;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Initializing extends TelegramAuthState {
        public static final int $stable = 0;
        public static final Initializing INSTANCE = new Initializing();

        private Initializing() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Initializing);
        }

        public int hashCode() {
            return 1060677229;
        }

        public String toString() {
            return "Initializing";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$Ready;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "firstName", "", "userId", "", "<init>", "(Ljava/lang/String;J)V", "getFirstName", "()Ljava/lang/String;", "getUserId", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Ready extends TelegramAuthState {
        public static final int $stable = 0;
        private final String firstName;
        private final long userId;

        public Ready(String str, long j10) {
            super(null);
            this.firstName = str;
            this.userId = j10;
        }

        public static /* synthetic */ Ready copy$default(Ready ready, String str, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = ready.firstName;
            }
            if ((i10 & 2) != 0) {
                j10 = ready.userId;
            }
            return ready.copy(str, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFirstName() {
            return this.firstName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getUserId() {
            return this.userId;
        }

        public final Ready copy(String firstName, long userId) {
            return new Ready(firstName, userId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Ready)) {
                return false;
            }
            Ready ready = (Ready) other;
            return p.a(this.firstName, ready.firstName) && this.userId == ready.userId;
        }

        public final String getFirstName() {
            return this.firstName;
        }

        public final long getUserId() {
            return this.userId;
        }

        public int hashCode() {
            int iHashCode = this.firstName.hashCode() * 31;
            long j10 = this.userId;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "Ready(firstName=" + this.firstName + ", userId=" + this.userId + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitCode;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "codeLength", "", "<init>", "(I)V", "getCodeLength", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WaitCode extends TelegramAuthState {
        public static final int $stable = 0;
        private final int codeLength;

        public WaitCode() {
            this(0, 1, null);
        }

        public static /* synthetic */ WaitCode copy$default(WaitCode waitCode, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = waitCode.codeLength;
            }
            return waitCode.copy(i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCodeLength() {
            return this.codeLength;
        }

        public final WaitCode copy(int codeLength) {
            return new WaitCode(codeLength);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WaitCode) && this.codeLength == ((WaitCode) other).codeLength;
        }

        public final int getCodeLength() {
            return this.codeLength;
        }

        public int hashCode() {
            return this.codeLength;
        }

        public String toString() {
            return androidx.compose.foundation.c.o(this.codeLength, "WaitCode(codeLength=", ")");
        }

        public WaitCode(int i10) {
            super(null);
            this.codeLength = i10;
        }

        public /* synthetic */ WaitCode(int i10, int i11, h hVar) {
            this((i11 & 1) != 0 ? 5 : i10);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitPassword;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WaitPassword extends TelegramAuthState {
        public static final int $stable = 0;
        public static final WaitPassword INSTANCE = new WaitPassword();

        private WaitPassword() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof WaitPassword);
        }

        public int hashCode() {
            return 47369552;
        }

        public String toString() {
            return "WaitPassword";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitPhone;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WaitPhone extends TelegramAuthState {
        public static final int $stable = 0;
        public static final WaitPhone INSTANCE = new WaitPhone();

        private WaitPhone() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof WaitPhone);
        }

        public int hashCode() {
            return -38719783;
        }

        public String toString() {
            return "WaitPhone";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramAuthState$WaitQr;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "link", "", "<init>", "(Ljava/lang/String;)V", "getLink", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WaitQr extends TelegramAuthState {
        public static final int $stable = 0;
        private final String link;

        public WaitQr(String str) {
            super(null);
            this.link = str;
        }

        public static /* synthetic */ WaitQr copy$default(WaitQr waitQr, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = waitQr.link;
            }
            return waitQr.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        public final WaitQr copy(String link) {
            return new WaitQr(link);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WaitQr) && p.a(this.link, ((WaitQr) other).link);
        }

        public final String getLink() {
            return this.link;
        }

        public int hashCode() {
            return this.link.hashCode();
        }

        public String toString() {
            return a0.c.l("WaitQr(link=", this.link, ")");
        }
    }

    public /* synthetic */ TelegramAuthState(h hVar) {
        this();
    }

    private TelegramAuthState() {
    }
}
