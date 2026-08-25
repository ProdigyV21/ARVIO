package com.arflix.tv.data.repository.simkl;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState;", "", "<init>", "()V", "Idle", "CodeRequested", "Success", "Error", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$CodeRequested;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Error;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Idle;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Success;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SimklPinAuthState {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$CodeRequested;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState;", "userCode", "", "verificationUrl", "expiresIn", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getUserCode", "()Ljava/lang/String;", "getVerificationUrl", "getExpiresIn", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CodeRequested extends SimklPinAuthState {
        public static final int $stable = 0;
        private final int expiresIn;
        private final String userCode;
        private final String verificationUrl;

        public CodeRequested(String str, String str2, int i10) {
            super(null);
            this.userCode = str;
            this.verificationUrl = str2;
            this.expiresIn = i10;
        }

        public static /* synthetic */ CodeRequested copy$default(CodeRequested codeRequested, String str, String str2, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = codeRequested.userCode;
            }
            if ((i11 & 2) != 0) {
                str2 = codeRequested.verificationUrl;
            }
            if ((i11 & 4) != 0) {
                i10 = codeRequested.expiresIn;
            }
            return codeRequested.copy(str, str2, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUserCode() {
            return this.userCode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVerificationUrl() {
            return this.verificationUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getExpiresIn() {
            return this.expiresIn;
        }

        public final CodeRequested copy(String userCode, String verificationUrl, int expiresIn) {
            return new CodeRequested(userCode, verificationUrl, expiresIn);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CodeRequested)) {
                return false;
            }
            CodeRequested codeRequested = (CodeRequested) other;
            return p.a(this.userCode, codeRequested.userCode) && p.a(this.verificationUrl, codeRequested.verificationUrl) && this.expiresIn == codeRequested.expiresIn;
        }

        public final int getExpiresIn() {
            return this.expiresIn;
        }

        public final String getUserCode() {
            return this.userCode;
        }

        public final String getVerificationUrl() {
            return this.verificationUrl;
        }

        public int hashCode() {
            return c.c(this.userCode.hashCode() * 31, 31, this.verificationUrl) + this.expiresIn;
        }

        public String toString() {
            return d.j(this.expiresIn, ")", a2.r("CodeRequested(userCode=", this.userCode, ", verificationUrl=", this.verificationUrl, ", expiresIn="));
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Error;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends SimklPinAuthState {
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

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Idle;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState;", "<init>", "()V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Idle extends SimklPinAuthState {
        public static final int $stable = 0;
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState$Success;", "Lcom/arflix/tv/data/repository/simkl/SimklPinAuthState;", "<init>", "()V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Success extends SimklPinAuthState {
        public static final int $stable = 0;
        public static final Success INSTANCE = new Success();

        private Success() {
            super(null);
        }
    }

    public /* synthetic */ SimklPinAuthState(h hVar) {
        this();
    }

    private SimklPinAuthState() {
    }
}
