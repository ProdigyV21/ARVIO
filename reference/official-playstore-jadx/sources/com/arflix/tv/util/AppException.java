package com.arflix.tv.util;

import a0.c;
import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0004\u0012\u0013\u0014\u0015B)\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0011R\u0015\u0010\u0003\u001a\u00020\u0004X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b\u0082\u0001\u0004\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/util/AppException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "errorCode", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "toSupportString", "isRetryable", "", "Network", "Auth", "Server", "Unknown", "Lcom/arflix/tv/util/AppException$Auth;", "Lcom/arflix/tv/util/AppException$Network;", "Lcom/arflix/tv/util/AppException$Server;", "Lcom/arflix/tv/util/AppException$Unknown;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AppException extends Exception {
    public static final int $stable = 8;
    private final Throwable cause;
    private final String errorCode;
    private final String message;

    public /* synthetic */ AppException(String str, Throwable th, String str2, h hVar) {
        this(str, th, str2);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public final boolean isRetryable() {
        if (this instanceof Network) {
            return true;
        }
        if (this instanceof Server) {
            int httpCode = ((Server) this).getHttpCode();
            return 500 <= httpCode && httpCode < 600;
        }
        if ((this instanceof Auth) || (this instanceof Unknown)) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String toSupportString() {
        if (getErrorCode() == null) {
            return getMessage();
        }
        return getMessage() + " [" + getErrorCode() + "]";
    }

    private AppException(String str, Throwable th, String str2) {
        super(str, th);
        this.message = str;
        this.cause = th;
        this.errorCode = str2;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/util/AppException$Auth;", "Lcom/arflix/tv/util/AppException;", "message", "", "cause", "", "errorCode", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Auth extends AppException {
        private final Throwable cause;
        private final String errorCode;
        private final String message;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;
        private static final Auth SESSION_EXPIRED = new Auth("Session expired. Please sign in again.", null, "ERR_SESSION_EXPIRED", 2, null);
        private static final Auth INVALID_CREDENTIALS = new Auth("Invalid email or password", null, "ERR_INVALID_CREDENTIALS", 2, null);
        private static final Auth ACCESS_DENIED = new Auth("Access denied", null, "ERR_ACCESS_DENIED", 2, null);
        private static final Auth TOKEN_INVALID = new Auth("Invalid authentication token", null, "ERR_TOKEN_INVALID", 2, null);

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/util/AppException$Auth$Companion;", "", "<init>", "()V", "SESSION_EXPIRED", "Lcom/arflix/tv/util/AppException$Auth;", "getSESSION_EXPIRED", "()Lcom/arflix/tv/util/AppException$Auth;", "INVALID_CREDENTIALS", "getINVALID_CREDENTIALS", "ACCESS_DENIED", "getACCESS_DENIED", "TOKEN_INVALID", "getTOKEN_INVALID", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final Auth getACCESS_DENIED() {
                return Auth.ACCESS_DENIED;
            }

            public final Auth getINVALID_CREDENTIALS() {
                return Auth.INVALID_CREDENTIALS;
            }

            public final Auth getSESSION_EXPIRED() {
                return Auth.SESSION_EXPIRED;
            }

            public final Auth getTOKEN_INVALID() {
                return Auth.TOKEN_INVALID;
            }

            private Companion() {
            }
        }

        public Auth(String str, Throwable th, String str2) {
            super(str, th, str2, null);
            this.message = str;
            this.cause = th;
            this.errorCode = str2;
        }

        public static /* synthetic */ Auth copy$default(Auth auth, String str, Throwable th, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = auth.message;
            }
            if ((i10 & 2) != 0) {
                th = auth.cause;
            }
            if ((i10 & 4) != 0) {
                str2 = auth.errorCode;
            }
            return auth.copy(str, th, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getErrorCode() {
            return this.errorCode;
        }

        public final Auth copy(String message, Throwable cause, String errorCode) {
            return new Auth(message, cause, errorCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Auth)) {
                return false;
            }
            Auth auth = (Auth) other;
            return p.a(this.message, auth.message) && p.a(this.cause, auth.cause) && p.a(this.errorCode, auth.errorCode);
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // com.arflix.tv.util.AppException
        public String getErrorCode() {
            return this.errorCode;
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            int iHashCode = this.message.hashCode() * 31;
            Throwable th = this.cause;
            int iHashCode2 = (iHashCode + (th == null ? 0 : th.hashCode())) * 31;
            String str = this.errorCode;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override // java.lang.Throwable
        public String toString() {
            String str = this.message;
            Throwable th = this.cause;
            String str2 = this.errorCode;
            StringBuilder sb2 = new StringBuilder("Auth(message=");
            sb2.append(str);
            sb2.append(", cause=");
            sb2.append(th);
            sb2.append(", errorCode=");
            return c.p(sb2, str2, ")");
        }

        public /* synthetic */ Auth(String str, Throwable th, String str2, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? null : th, (i10 & 4) != 0 ? "ERR_AUTH" : str2);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/util/AppException$Network;", "Lcom/arflix/tv/util/AppException;", "message", "", "cause", "", "errorCode", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Network extends AppException {
        private final Throwable cause;
        private final String errorCode;
        private final String message;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;
        private static final Network NO_CONNECTION = new Network("No internet connection", null, "ERR_NO_CONNECTION", 2, null);
        private static final Network TIMEOUT = new Network("Connection timed out", null, "ERR_TIMEOUT", 2, null);
        private static final Network SSL_ERROR = new Network("Secure connection failed", null, "ERR_SSL", 2, null);

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/arflix/tv/util/AppException$Network$Companion;", "", "<init>", "()V", "NO_CONNECTION", "Lcom/arflix/tv/util/AppException$Network;", "getNO_CONNECTION", "()Lcom/arflix/tv/util/AppException$Network;", "TIMEOUT", "getTIMEOUT", "SSL_ERROR", "getSSL_ERROR", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final Network getNO_CONNECTION() {
                return Network.NO_CONNECTION;
            }

            public final Network getSSL_ERROR() {
                return Network.SSL_ERROR;
            }

            public final Network getTIMEOUT() {
                return Network.TIMEOUT;
            }

            private Companion() {
            }
        }

        public Network(String str, Throwable th, String str2) {
            super(str, th, str2, null);
            this.message = str;
            this.cause = th;
            this.errorCode = str2;
        }

        public static /* synthetic */ Network copy$default(Network network, String str, Throwable th, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = network.message;
            }
            if ((i10 & 2) != 0) {
                th = network.cause;
            }
            if ((i10 & 4) != 0) {
                str2 = network.errorCode;
            }
            return network.copy(str, th, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getErrorCode() {
            return this.errorCode;
        }

        public final Network copy(String message, Throwable cause, String errorCode) {
            return new Network(message, cause, errorCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Network)) {
                return false;
            }
            Network network = (Network) other;
            return p.a(this.message, network.message) && p.a(this.cause, network.cause) && p.a(this.errorCode, network.errorCode);
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // com.arflix.tv.util.AppException
        public String getErrorCode() {
            return this.errorCode;
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            int iHashCode = this.message.hashCode() * 31;
            Throwable th = this.cause;
            int iHashCode2 = (iHashCode + (th == null ? 0 : th.hashCode())) * 31;
            String str = this.errorCode;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override // java.lang.Throwable
        public String toString() {
            String str = this.message;
            Throwable th = this.cause;
            String str2 = this.errorCode;
            StringBuilder sb2 = new StringBuilder("Network(message=");
            sb2.append(str);
            sb2.append(", cause=");
            sb2.append(th);
            sb2.append(", errorCode=");
            return c.p(sb2, str2, ")");
        }

        public /* synthetic */ Network(String str, Throwable th, String str2, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? null : th, (i10 & 4) != 0 ? "ERR_NETWORK" : str2);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/util/AppException$Unknown;", "Lcom/arflix/tv/util/AppException;", "message", "", "cause", "", "errorCode", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Unknown extends AppException {
        public static final int $stable = 8;
        private final Throwable cause;
        private final String errorCode;
        private final String message;

        public Unknown(String str, Throwable th, String str2) {
            super(str, th, str2, null);
            this.message = str;
            this.cause = th;
            this.errorCode = str2;
        }

        public static /* synthetic */ Unknown copy$default(Unknown unknown, String str, Throwable th, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = unknown.message;
            }
            if ((i10 & 2) != 0) {
                th = unknown.cause;
            }
            if ((i10 & 4) != 0) {
                str2 = unknown.errorCode;
            }
            return unknown.copy(str, th, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getErrorCode() {
            return this.errorCode;
        }

        public final Unknown copy(String message, Throwable cause, String errorCode) {
            return new Unknown(message, cause, errorCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Unknown)) {
                return false;
            }
            Unknown unknown = (Unknown) other;
            return p.a(this.message, unknown.message) && p.a(this.cause, unknown.cause) && p.a(this.errorCode, unknown.errorCode);
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // com.arflix.tv.util.AppException
        public String getErrorCode() {
            return this.errorCode;
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            int iHashCode = this.message.hashCode() * 31;
            Throwable th = this.cause;
            int iHashCode2 = (iHashCode + (th == null ? 0 : th.hashCode())) * 31;
            String str = this.errorCode;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override // java.lang.Throwable
        public String toString() {
            String str = this.message;
            Throwable th = this.cause;
            String str2 = this.errorCode;
            StringBuilder sb2 = new StringBuilder("Unknown(message=");
            sb2.append(str);
            sb2.append(", cause=");
            sb2.append(th);
            sb2.append(", errorCode=");
            return c.p(sb2, str2, ")");
        }

        public /* synthetic */ Unknown(String str, Throwable th, String str2, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? null : th, (i10 & 4) != 0 ? "ERR_UNKNOWN" : str2);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/util/AppException$Server;", "Lcom/arflix/tv/util/AppException;", "message", "", "httpCode", "", "cause", "", "errorCode", "<init>", "(Ljava/lang/String;ILjava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getHttpCode", "()I", "getCause", "()Ljava/lang/Throwable;", "getErrorCode", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Server extends AppException {
        private final Throwable cause;
        private final String errorCode;
        private final int httpCode;
        private final String message;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lcom/arflix/tv/util/AppException$Server$Companion;", "", "<init>", "()V", "notFound", "Lcom/arflix/tv/util/AppException$Server;", "resource", "", "internalError", "serviceUnavailable", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public static /* synthetic */ Server notFound$default(Companion companion, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = "Resource";
                }
                return companion.notFound(str);
            }

            public final Server internalError() {
                return new Server("Server error. Please try again later.", 500, null, "ERR_SERVER_INTERNAL", 4, null);
            }

            public final Server notFound(String resource) {
                return new Server(resource.concat(" not found"), 404, null, "ERR_NOT_FOUND", 4, null);
            }

            public final Server serviceUnavailable() {
                return new Server("Service temporarily unavailable", 503, null, "ERR_SERVICE_UNAVAILABLE", 4, null);
            }

            private Companion() {
            }
        }

        public Server(String str, int i10, Throwable th, String str2) {
            super(str, th, str2, null);
            this.message = str;
            this.httpCode = i10;
            this.cause = th;
            this.errorCode = str2;
        }

        public static /* synthetic */ Server copy$default(Server server, String str, int i10, Throwable th, String str2, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = server.message;
            }
            if ((i11 & 2) != 0) {
                i10 = server.httpCode;
            }
            if ((i11 & 4) != 0) {
                th = server.cause;
            }
            if ((i11 & 8) != 0) {
                str2 = server.errorCode;
            }
            return server.copy(str, i10, th, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getHttpCode() {
            return this.httpCode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getErrorCode() {
            return this.errorCode;
        }

        public final Server copy(String message, int httpCode, Throwable cause, String errorCode) {
            return new Server(message, httpCode, cause, errorCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Server)) {
                return false;
            }
            Server server = (Server) other;
            return p.a(this.message, server.message) && this.httpCode == server.httpCode && p.a(this.cause, server.cause) && p.a(this.errorCode, server.errorCode);
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // com.arflix.tv.util.AppException
        public String getErrorCode() {
            return this.errorCode;
        }

        public final int getHttpCode() {
            return this.httpCode;
        }

        @Override // com.arflix.tv.util.AppException, java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            int iHashCode = ((this.message.hashCode() * 31) + this.httpCode) * 31;
            Throwable th = this.cause;
            int iHashCode2 = (iHashCode + (th == null ? 0 : th.hashCode())) * 31;
            String str = this.errorCode;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override // java.lang.Throwable
        public String toString() {
            String str = this.message;
            int i10 = this.httpCode;
            Throwable th = this.cause;
            String str2 = this.errorCode;
            StringBuilder sbQ = a2.q("Server(message=", str, ", httpCode=", i10, ", cause=");
            sbQ.append(th);
            sbQ.append(", errorCode=");
            sbQ.append(str2);
            sbQ.append(")");
            return sbQ.toString();
        }

        public /* synthetic */ Server(String str, int i10, Throwable th, String str2, int i11, h hVar) {
            this(str, i10, (i11 & 4) != 0 ? null : th, (i11 & 8) != 0 ? "ERR_SERVER" : str2);
        }
    }

    public /* synthetic */ AppException(String str, Throwable th, String str2, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : th, (i10 & 4) != 0 ? null : str2, null);
    }
}
