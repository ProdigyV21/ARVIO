package io.github.jan.supabase.gotrue.user;

import ab.g;
import cb.b1;
import cb.l1;
import cb.r1;
import io.ktor.http.LinkHeader;
import io.sentry.util.l;
import j$.time.Clock;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import q7.n;
import wa.c;
import x6.e;
import xa.a;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0087\b\u0018\u0000 J2\u00020\u0001:\u0002KJB]\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010B\u0081\u0001\b\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0017J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0017J\u0010\u0010\u001b\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0017J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u0017J\u0010\u0010!\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b!\u0010\"Jp\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b%\u0010\u0017J\u0010\u0010&\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b&\u0010'J\u001a\u0010*\u001a\u00020)2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b*\u0010+J(\u00104\u001a\u0002012\u0006\u0010,\u001a\u00020\u00002\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/HÁ\u0001¢\u0006\u0004\b2\u00103R \u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0003\u00105\u0012\u0004\b7\u00108\u001a\u0004\b6\u0010\u0017R \u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0004\u00105\u0012\u0004\b:\u00108\u001a\u0004\b9\u0010\u0017R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u00105\u0012\u0004\b<\u00108\u001a\u0004\b;\u0010\u0017R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u00105\u0012\u0004\b>\u00108\u001a\u0004\b=\u0010\u0017R \u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\b\u0010?\u0012\u0004\bA\u00108\u001a\u0004\b@\u0010\u001cR \u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\t\u00105\u0012\u0004\bC\u00108\u001a\u0004\bB\u0010\u0017R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010D\u001a\u0004\bE\u0010\u001fR \u0010\f\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\f\u00105\u0012\u0004\bG\u00108\u001a\u0004\bF\u0010\u0017R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010H\u001a\u0004\bI\u0010\"¨\u0006L"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserSession;", "", "", "accessToken", "refreshToken", "providerRefreshToken", "providerToken", "", "expiresIn", "tokenType", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "user", LinkHeader.Parameters.Type, "Lwa/c;", "expiresAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lio/github/jan/supabase/gotrue/user/UserInfo;Ljava/lang/String;Lwa/c;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lio/github/jan/supabase/gotrue/user/UserInfo;Ljava/lang/String;Lwa/c;Lcb/l1;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()J", "component6", "component7", "()Lio/github/jan/supabase/gotrue/user/UserInfo;", "component8", "component9", "()Lwa/c;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lio/github/jan/supabase/gotrue/user/UserInfo;Ljava/lang/String;Lwa/c;)Lio/github/jan/supabase/gotrue/user/UserSession;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/user/UserSession;Lbb/c;Lab/g;)V", "write$Self", "Ljava/lang/String;", "getAccessToken", "getAccessToken$annotations", "()V", "getRefreshToken", "getRefreshToken$annotations", "getProviderRefreshToken", "getProviderRefreshToken$annotations", "getProviderToken", "getProviderToken$annotations", "J", "getExpiresIn", "getExpiresIn$annotations", "getTokenType", "getTokenType$annotations", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "getUser", "getType", "getType$annotations", "Lwa/c;", "getExpiresAt", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class UserSession {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String accessToken;
    private final c expiresAt;
    private final long expiresIn;
    private final String providerRefreshToken;
    private final String providerToken;
    private final String refreshToken;
    private final String tokenType;
    private final String type;
    private final UserInfo user;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserSession$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<UserSession> serializer() {
            return UserSession$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public UserSession(int i10, @q("access_token") String str, @q("refresh_token") String str2, @q("provider_refresh_token") String str3, @q("provider_token") String str4, @q("expires_in") long j10, @q("token_type") String str5, UserInfo userInfo, @q(LinkHeader.Parameters.Type) String str6, c cVar, l1 l1Var) {
        if (115 != (i10 & 115)) {
            b1.h(i10, 115, UserSession$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.accessToken = str;
        this.refreshToken = str2;
        if ((i10 & 4) == 0) {
            this.providerRefreshToken = null;
        } else {
            this.providerRefreshToken = str3;
        }
        if ((i10 & 8) == 0) {
            this.providerToken = null;
        } else {
            this.providerToken = str4;
        }
        this.expiresIn = j10;
        this.tokenType = str5;
        this.user = userInfo;
        if ((i10 & 128) == 0) {
            this.type = "";
        } else {
            this.type = str6;
        }
        if ((i10 & 256) != 0) {
            this.expiresAt = cVar;
            return;
        }
        c.Companion.getClass();
        c cVar2 = new c(Clock.systemUTC().instant());
        l lVar = kotlin.time.c.f19956l;
        this.expiresAt = cVar2.a(b0.I(j10, kotlin.time.e.SECONDS));
    }

    public static /* synthetic */ UserSession copy$default(UserSession userSession, String str, String str2, String str3, String str4, long j10, String str5, UserInfo userInfo, String str6, c cVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userSession.accessToken;
        }
        if ((i10 & 2) != 0) {
            str2 = userSession.refreshToken;
        }
        if ((i10 & 4) != 0) {
            str3 = userSession.providerRefreshToken;
        }
        if ((i10 & 8) != 0) {
            str4 = userSession.providerToken;
        }
        if ((i10 & 16) != 0) {
            j10 = userSession.expiresIn;
        }
        if ((i10 & 32) != 0) {
            str5 = userSession.tokenType;
        }
        if ((i10 & 64) != 0) {
            userInfo = userSession.user;
        }
        if ((i10 & 128) != 0) {
            str6 = userSession.type;
        }
        if ((i10 & 256) != 0) {
            cVar = userSession.expiresAt;
        }
        long j11 = j10;
        String str7 = str3;
        String str8 = str4;
        return userSession.copy(str, str2, str7, str8, j11, str5, userInfo, str6, cVar);
    }

    @q("access_token")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @q("expires_in")
    public static /* synthetic */ void getExpiresIn$annotations() {
    }

    @q("provider_refresh_token")
    public static /* synthetic */ void getProviderRefreshToken$annotations() {
    }

    @q("provider_token")
    public static /* synthetic */ void getProviderToken$annotations() {
    }

    @q("refresh_token")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    @q("token_type")
    public static /* synthetic */ void getTokenType$annotations() {
    }

    @q(LinkHeader.Parameters.Type)
    public static /* synthetic */ void getType$annotations() {
    }

    @n
    public static final void write$Self$gotrue_kt_release(UserSession self, bb.c output, g serialDesc) {
        output.x(serialDesc, 0, self.accessToken);
        output.x(serialDesc, 1, self.refreshToken);
        if (output.C() || self.providerRefreshToken != null) {
            output.m(serialDesc, 2, r1.f7553a, self.providerRefreshToken);
        }
        if (output.C() || self.providerToken != null) {
            output.m(serialDesc, 3, r1.f7553a, self.providerToken);
        }
        output.g(serialDesc, 4, self.expiresIn);
        output.x(serialDesc, 5, self.tokenType);
        output.m(serialDesc, 6, UserInfo$$serializer.INSTANCE, self.user);
        if (output.C() || !p.a(self.type, "")) {
            output.x(serialDesc, 7, self.type);
        }
        if (!output.C()) {
            c cVar = self.expiresAt;
            c.Companion.getClass();
            c cVar2 = new c(Clock.systemUTC().instant());
            l lVar = kotlin.time.c.f19956l;
            if (p.a(cVar, cVar2.a(b0.I(self.expiresIn, kotlin.time.e.SECONDS)))) {
                return;
            }
        }
        output.j(serialDesc, 8, a.f22712a, self.expiresAt);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProviderRefreshToken() {
        return this.providerRefreshToken;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProviderToken() {
        return this.providerToken;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getExpiresIn() {
        return this.expiresIn;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final UserInfo getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final c getExpiresAt() {
        return this.expiresAt;
    }

    public final UserSession copy(String accessToken, String refreshToken, String providerRefreshToken, String providerToken, long expiresIn, String tokenType, UserInfo user, String type, c expiresAt) {
        return new UserSession(accessToken, refreshToken, providerRefreshToken, providerToken, expiresIn, tokenType, user, type, expiresAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserSession)) {
            return false;
        }
        UserSession userSession = (UserSession) other;
        return p.a(this.accessToken, userSession.accessToken) && p.a(this.refreshToken, userSession.refreshToken) && p.a(this.providerRefreshToken, userSession.providerRefreshToken) && p.a(this.providerToken, userSession.providerToken) && this.expiresIn == userSession.expiresIn && p.a(this.tokenType, userSession.tokenType) && p.a(this.user, userSession.user) && p.a(this.type, userSession.type) && p.a(this.expiresAt, userSession.expiresAt);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final c getExpiresAt() {
        return this.expiresAt;
    }

    public final long getExpiresIn() {
        return this.expiresIn;
    }

    public final String getProviderRefreshToken() {
        return this.providerRefreshToken;
    }

    public final String getProviderToken() {
        return this.providerToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public final String getType() {
        return this.type;
    }

    public final UserInfo getUser() {
        return this.user;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.accessToken.hashCode() * 31, 31, this.refreshToken);
        String str = this.providerRefreshToken;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.providerToken;
        int iHashCode2 = str2 == null ? 0 : str2.hashCode();
        long j10 = this.expiresIn;
        int iC2 = androidx.compose.foundation.c.c((((iHashCode + iHashCode2) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.tokenType);
        UserInfo userInfo = this.user;
        return this.expiresAt.f22539i.hashCode() + androidx.compose.foundation.c.c((iC2 + (userInfo != null ? userInfo.hashCode() : 0)) * 31, 31, this.type);
    }

    public String toString() {
        return "UserSession(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", providerRefreshToken=" + this.providerRefreshToken + ", providerToken=" + this.providerToken + ", expiresIn=" + this.expiresIn + ", tokenType=" + this.tokenType + ", user=" + this.user + ", type=" + this.type + ", expiresAt=" + this.expiresAt + ')';
    }

    public UserSession(String str, String str2, String str3, String str4, long j10, String str5, UserInfo userInfo, String str6, c cVar) {
        this.accessToken = str;
        this.refreshToken = str2;
        this.providerRefreshToken = str3;
        this.providerToken = str4;
        this.expiresIn = j10;
        this.tokenType = str5;
        this.user = userInfo;
        this.type = str6;
        this.expiresAt = cVar;
    }

    public UserSession(String str, String str2, String str3, String str4, long j10, String str5, UserInfo userInfo, String str6, c cVar, int i10, h hVar) {
        str3 = (i10 & 4) != 0 ? null : str3;
        str4 = (i10 & 8) != 0 ? null : str4;
        str6 = (i10 & 128) != 0 ? "" : str6;
        if ((i10 & 256) != 0) {
            c.Companion.getClass();
            c cVar2 = new c(Clock.systemUTC().instant());
            l lVar = kotlin.time.c.f19956l;
            cVar = cVar2.a(b0.I(j10, kotlin.time.e.SECONDS));
        }
        this(str, str2, str3, str4, j10, str5, userInfo, str6, cVar);
    }
}
