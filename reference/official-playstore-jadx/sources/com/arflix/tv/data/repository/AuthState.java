package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState;", "", "<init>", "()V", "Loading", "NotAuthenticated", "Authenticated", "Error", "Lcom/arflix/tv/data/repository/AuthState$Authenticated;", "Lcom/arflix/tv/data/repository/AuthState$Error;", "Lcom/arflix/tv/data/repository/AuthState$Loading;", "Lcom/arflix/tv/data/repository/AuthState$NotAuthenticated;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AuthState {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState$Authenticated;", "Lcom/arflix/tv/data/repository/AuthState;", "userId", "", "email", "profile", "Lcom/arflix/tv/data/repository/UserProfile;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/UserProfile;)V", "getUserId", "()Ljava/lang/String;", "getEmail", "getProfile", "()Lcom/arflix/tv/data/repository/UserProfile;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Authenticated extends AuthState {
        public static final int $stable = 0;
        private final String email;
        private final UserProfile profile;
        private final String userId;

        public Authenticated(String str, String str2, UserProfile userProfile) {
            super(null);
            this.userId = str;
            this.email = str2;
            this.profile = userProfile;
        }

        public static /* synthetic */ Authenticated copy$default(Authenticated authenticated, String str, String str2, UserProfile userProfile, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = authenticated.userId;
            }
            if ((i10 & 2) != 0) {
                str2 = authenticated.email;
            }
            if ((i10 & 4) != 0) {
                userProfile = authenticated.profile;
            }
            return authenticated.copy(str, str2, userProfile);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final UserProfile getProfile() {
            return this.profile;
        }

        public final Authenticated copy(String userId, String email, UserProfile profile) {
            return new Authenticated(userId, email, profile);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Authenticated)) {
                return false;
            }
            Authenticated authenticated = (Authenticated) other;
            return kotlin.jvm.internal.p.a(this.userId, authenticated.userId) && kotlin.jvm.internal.p.a(this.email, authenticated.email) && kotlin.jvm.internal.p.a(this.profile, authenticated.profile);
        }

        public final String getEmail() {
            return this.email;
        }

        public final UserProfile getProfile() {
            return this.profile;
        }

        public final String getUserId() {
            return this.userId;
        }

        public int hashCode() {
            int iC = androidx.compose.foundation.c.c(this.userId.hashCode() * 31, 31, this.email);
            UserProfile userProfile = this.profile;
            return iC + (userProfile == null ? 0 : userProfile.hashCode());
        }

        public String toString() {
            String str = this.userId;
            String str2 = this.email;
            UserProfile userProfile = this.profile;
            StringBuilder sbR = a2.r("Authenticated(userId=", str, ", email=", str2, ", profile=");
            sbR.append(userProfile);
            sbR.append(")");
            return sbR.toString();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState$Error;", "Lcom/arflix/tv/data/repository/AuthState;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends AuthState {
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
            return (other instanceof Error) && kotlin.jvm.internal.p.a(this.message, ((Error) other).message);
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

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState$Loading;", "Lcom/arflix/tv/data/repository/AuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Loading extends AuthState {
        public static final int $stable = 0;
        public static final Loading INSTANCE = new Loading();

        private Loading() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Loading);
        }

        public int hashCode() {
            return -1987970714;
        }

        public String toString() {
            return "Loading";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState$NotAuthenticated;", "Lcom/arflix/tv/data/repository/AuthState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class NotAuthenticated extends AuthState {
        public static final int $stable = 0;
        public static final NotAuthenticated INSTANCE = new NotAuthenticated();

        private NotAuthenticated() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof NotAuthenticated);
        }

        public int hashCode() {
            return -411820238;
        }

        public String toString() {
            return "NotAuthenticated";
        }
    }

    public /* synthetic */ AuthState(kotlin.jvm.internal.h hVar) {
        this();
    }

    private AuthState() {
    }
}
