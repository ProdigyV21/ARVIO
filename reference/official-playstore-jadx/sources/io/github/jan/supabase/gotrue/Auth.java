package io.github.jan.supabase.gotrue;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseClientBuilder;
import io.github.jan.supabase.annotations.SupabaseExperimental;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.gotrue.OtpType;
import io.github.jan.supabase.gotrue.SessionStatus;
import io.github.jan.supabase.gotrue.admin.AdminApi;
import io.github.jan.supabase.gotrue.mfa.MfaApi;
import io.github.jan.supabase.gotrue.providers.AuthProvider;
import io.github.jan.supabase.gotrue.providers.ExternalAuthConfigDefaults;
import io.github.jan.supabase.gotrue.providers.OAuthProvider;
import io.github.jan.supabase.gotrue.providers.builtin.SSO;
import io.github.jan.supabase.gotrue.user.Identity;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.github.jan.supabase.gotrue.user.UserUpdateBuilder;
import io.github.jan.supabase.plugins.CustomSerializationPlugin;
import io.github.jan.supabase.plugins.MainPlugin;
import io.github.jan.supabase.plugins.SupabasePluginProvider;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import na.h1;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001{Je\u0010\u000f\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0014\b\u0002\u0010\u0007*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00062\u0006\u0010\b\u001a\u00028\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u001b\b\u0002\u0010\u000e\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\rH¦@¢\u0006\u0004\b\u000f\u0010\u0010Jc\u0010\u0011\u001a\u00020\f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0014\b\u0002\u0010\u0007*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00062\u0006\u0010\b\u001a\u00028\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u001b\b\u0002\u0010\u000e\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\rH¦@¢\u0006\u0004\b\u0011\u0010\u0010J?\u0010\u0014\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00122\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH§@¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\u0017H§@¢\u0006\u0004\b\u0019\u0010\u001aJ5\u0010\u001d\u001a\u00020\u001c2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH¦@¢\u0006\u0004\b\u001d\u0010\u001eJ?\u0010\"\u001a\u00020!2\b\b\u0002\u0010\u001f\u001a\u00020\u00172\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH¦@¢\u0006\u0004\b\"\u0010#J,\u0010(\u001a\u00020\f2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b(\u0010)J,\u0010,\u001a\u00020\f2\u0006\u0010%\u001a\u00020*2\u0006\u0010+\u001a\u00020\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b,\u0010-J0\u0010.\u001a\u00020\f2\u0006\u0010&\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\fH¦@¢\u0006\u0004\b0\u00101J4\u00103\u001a\u00020\f2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\t2\u0006\u00102\u001a\u00020\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b3\u00104J4\u00106\u001a\u00020\f2\u0006\u0010%\u001a\u00020*2\u0006\u00105\u001a\u00020\t2\u0006\u00102\u001a\u00020\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b6\u00107J\u0018\u00109\u001a\u00020!2\u0006\u00108\u001a\u00020\tH¦@¢\u0006\u0004\b9\u0010:J\u001a\u0010<\u001a\u00020!2\b\b\u0002\u0010;\u001a\u00020\u0017H¦@¢\u0006\u0004\b<\u0010=J\u001a\u0010@\u001a\u00020\f2\b\b\u0002\u0010?\u001a\u00020>H¦@¢\u0006\u0004\b@\u0010AJ\"\u0010E\u001a\u00020\f2\u0006\u0010C\u001a\u00020B2\b\b\u0002\u0010D\u001a\u00020\u0017H¦@¢\u0006\u0004\bE\u0010FJ6\u0010I\u001a\u00020\f2\u0006\u0010G\u001a\u00020\t2\b\b\u0002\u0010H\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\u00172\b\b\u0002\u0010D\u001a\u00020\u0017H\u0096@¢\u0006\u0004\bI\u0010JJ\u001a\u0010K\u001a\u00020\u00172\b\b\u0002\u0010D\u001a\u00020\u0017H¦@¢\u0006\u0004\bK\u0010=J\u0018\u0010L\u001a\u00020B2\u0006\u0010H\u001a\u00020\tH¦@¢\u0006\u0004\bL\u0010:J\u0010\u0010M\u001a\u00020\fH¦@¢\u0006\u0004\bM\u00101J\u0010\u0010N\u001a\u00020\fH¦@¢\u0006\u0004\bN\u00101J\"\u0010Q\u001a\u00020B2\u0006\u0010O\u001a\u00020\t2\b\b\u0002\u0010P\u001a\u00020\u0017H¦@¢\u0006\u0004\bQ\u0010\u001aJ\u0010\u0010R\u001a\u00020\fH¦@¢\u0006\u0004\bR\u00101JH\u0010U\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00122\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010S\u001a\u00020\t2\u0019\b\u0002\u0010T\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH&¢\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020\fH&¢\u0006\u0004\bW\u0010XJ\u0011\u0010Y\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\bY\u0010ZJ\u0011\u0010[\u001a\u0004\u0018\u00010BH\u0016¢\u0006\u0004\b[\u0010\\J\u0011\u0010]\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b]\u0010^J\u0017\u0010a\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_H\u0016¢\u0006\u0004\ba\u0010bJ\u0010\u0010c\u001a\u00020\fH¦@¢\u0006\u0004\bc\u00101R\u001a\u0010h\u001a\b\u0012\u0004\u0012\u00020e0d8&X¦\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0014\u0010i\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u0014\u0010n\u001a\u00020k8&X¦\u0004¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0014\u0010r\u001a\u00020o8&X¦\u0004¢\u0006\u0006\u001a\u0004\bp\u0010qR\u0014\u0010v\u001a\u00020s8&X¦\u0004¢\u0006\u0006\u001a\u0004\bt\u0010uR\u0014\u0010z\u001a\u00020w8&X¦\u0004¢\u0006\u0006\u001a\u0004\bx\u0010y\u0082\u0001\u0001|¨\u0006}"}, d2 = {"Lio/github/jan/supabase/gotrue/Auth;", "Lio/github/jan/supabase/plugins/MainPlugin;", "Lio/github/jan/supabase/gotrue/AuthConfig;", "Lio/github/jan/supabase/plugins/CustomSerializationPlugin;", "C", "R", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Provider", "provider", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "signUpWith", "(Lio/github/jan/supabase/gotrue/providers/AuthProvider;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signInWith", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "linkIdentity", "(Lio/github/jan/supabase/gotrue/providers/OAuthProvider;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "identityId", "", "updateLocalUser", "unlinkIdentity", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;", "retrieveSSOUrl", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "updateCurrentUser", "Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "modifyUser", "(ZLjava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/OtpType$Email;", LinkHeader.Parameters.Type, "email", "captchaToken", "resendEmail", "(Lio/github/jan/supabase/gotrue/OtpType$Email;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/OtpType$Phone;", "phoneNumber", "resendPhone", "(Lio/github/jan/supabase/gotrue/OtpType$Phone;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "resetPasswordForEmail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "reauthenticate", "(Ld7/d;)Ljava/lang/Object;", "token", "verifyEmailOtp", "(Lio/github/jan/supabase/gotrue/OtpType$Email;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "phone", "verifyPhoneOtp", "(Lio/github/jan/supabase/gotrue/OtpType$Phone;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "jwt", "retrieveUser", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "updateSession", "retrieveUserForCurrentSession", "(ZLd7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/SignOutScope;", "scope", "signOut", "(Lio/github/jan/supabase/gotrue/SignOutScope;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/user/UserSession;", SettingsSessionManager.SETTINGS_KEY, "autoRefresh", "importSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;ZLd7/d;)Ljava/lang/Object;", "accessToken", "refreshToken", "importAuthToken", "(Ljava/lang/String;Ljava/lang/String;ZZLd7/d;)Ljava/lang/Object;", "loadFromStorage", "refreshSession", "refreshCurrentSession", "clearSession", "code", "saveSession", "exchangeCodeForSession", "startAutoRefreshForCurrentSession", "url", "additionalConfig", "oAuthUrl", "(Lio/github/jan/supabase/gotrue/providers/OAuthProvider;Ljava/lang/String;Ljava/lang/String;Lr7/l;)Ljava/lang/String;", "stopAutoRefreshForCurrentSession", "()V", "currentAccessTokenOrNull", "()Ljava/lang/String;", "currentSessionOrNull", "()Lio/github/jan/supabase/gotrue/user/UserSession;", "currentUserOrNull", "()Lio/github/jan/supabase/gotrue/user/UserInfo;", "", "Lio/github/jan/supabase/gotrue/user/Identity;", "currentIdentitiesOrNull", "()Ljava/util/List;", "awaitInitialization", "Lna/h1;", "Lio/github/jan/supabase/gotrue/SessionStatus;", "getSessionStatus", "()Lna/h1;", "sessionStatus", "isAutoRefreshRunning", "()Z", "Lio/github/jan/supabase/gotrue/SessionManager;", "getSessionManager", "()Lio/github/jan/supabase/gotrue/SessionManager;", "sessionManager", "Lio/github/jan/supabase/gotrue/admin/AdminApi;", "getAdmin", "()Lio/github/jan/supabase/gotrue/admin/AdminApi;", "admin", "Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "getMfa", "()Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "mfa", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "getCodeVerifierCache", "()Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "codeVerifierCache", "Companion", "Lio/github/jan/supabase/gotrue/AuthImpl;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Auth extends MainPlugin<AuthConfig>, CustomSerializationPlugin {
    public static final int API_VERSION = 1;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00022\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/Auth$Companion;", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Lio/github/jan/supabase/gotrue/AuthConfig;", "Lio/github/jan/supabase/gotrue/Auth;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "init", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/AuthConfig;", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "config", "create", "(Lio/github/jan/supabase/SupabaseClient;Lio/github/jan/supabase/gotrue/AuthConfig;)Lio/github/jan/supabase/gotrue/Auth;", "", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "", "API_VERSION", "I", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements SupabasePluginProvider<AuthConfig, Auth> {
        public static final int API_VERSION = 1;
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String key = "auth";

        private Companion() {
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public String getKey() {
            return key;
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public void setup(SupabaseClientBuilder supabaseClientBuilder, AuthConfig authConfig) {
            SupabasePluginProvider.DefaultImpls.setup(this, supabaseClientBuilder, authConfig);
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public Auth create(SupabaseClient supabaseClient, AuthConfig config) {
            return new AuthImpl(supabaseClient, config);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public AuthConfig createConfig(l<? super AuthConfig, t0> init) {
            AuthConfig authConfig = new AuthConfig();
            init.invoke(authConfig);
            return authConfig;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object close(Auth auth, d<? super t0> dVar) {
            Object objClose = MainPlugin.DefaultImpls.close(auth, dVar);
            return objClose == a.f15033i ? objClose : t0.f22605a;
        }

        public static String currentAccessTokenOrNull(Auth auth) {
            UserSession userSessionCurrentSessionOrNull = auth.currentSessionOrNull();
            if (userSessionCurrentSessionOrNull != null) {
                return userSessionCurrentSessionOrNull.getAccessToken();
            }
            return null;
        }

        public static List<Identity> currentIdentitiesOrNull(Auth auth) {
            UserInfo userInfoCurrentUserOrNull = auth.currentUserOrNull();
            if (userInfoCurrentUserOrNull != null) {
                return userInfoCurrentUserOrNull.getIdentities();
            }
            return null;
        }

        public static UserSession currentSessionOrNull(Auth auth) {
            SessionStatus sessionStatus = (SessionStatus) auth.getSessionStatus().getValue();
            if (sessionStatus instanceof SessionStatus.Authenticated) {
                return ((SessionStatus.Authenticated) sessionStatus).m6640unboximpl();
            }
            return null;
        }

        public static UserInfo currentUserOrNull(Auth auth) {
            UserSession userSessionCurrentSessionOrNull = auth.currentSessionOrNull();
            if (userSessionCurrentSessionOrNull != null) {
                return userSessionCurrentSessionOrNull.getUser();
            }
            return null;
        }

        public static /* synthetic */ Object exchangeCodeForSession$default(Auth auth, String str, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exchangeCodeForSession");
            }
            if ((i10 & 2) != 0) {
                z = true;
            }
            return auth.exchangeCodeForSession(str, z, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00cc, code lost:
        
            if (r13.importSession(r14, r11, r3) != r8) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static java.lang.Object importAuthToken(io.github.jan.supabase.gotrue.Auth r27, java.lang.String r28, java.lang.String r29, boolean r30, boolean r31, d7.d<? super x6.t0> r32) {
            /*
                Method dump skipped, instruction units count: 210
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.Auth.DefaultImpls.importAuthToken(io.github.jan.supabase.gotrue.Auth, java.lang.String, java.lang.String, boolean, boolean, d7.d):java.lang.Object");
        }

        public static /* synthetic */ Object importAuthToken$default(Auth auth, String str, String str2, boolean z, boolean z5, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: importAuthToken");
            }
            if ((i10 & 2) != 0) {
                str2 = "";
            }
            String str3 = str2;
            boolean z10 = (i10 & 4) != 0 ? false : z;
            if ((i10 & 8) != 0) {
                z5 = !o.h0(str3) ? auth.getConfig().getAlwaysAutoRefresh() : false;
            }
            return auth.importAuthToken(str, str3, z10, z5, dVar);
        }

        public static /* synthetic */ Object importSession$default(Auth auth, UserSession userSession, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: importSession");
            }
            if ((i10 & 2) != 0) {
                z = true;
            }
            return auth.importSession(userSession, z, dVar);
        }

        @SupabaseInternal
        public static void init(Auth auth) {
            MainPlugin.DefaultImpls.init(auth);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object linkIdentity$default(Auth auth, OAuthProvider oAuthProvider, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: linkIdentity");
            }
            if ((i10 & 2) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            if ((i10 & 4) != 0) {
                lVar = C16601.INSTANCE;
            }
            return auth.linkIdentity(oAuthProvider, str, lVar, dVar);
        }

        public static /* synthetic */ Object loadFromStorage$default(Auth auth, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadFromStorage");
            }
            if ((i10 & 1) != 0) {
                z = auth.getConfig().getAlwaysAutoRefresh();
            }
            return auth.loadFromStorage(z, dVar);
        }

        public static /* synthetic */ Object modifyUser$default(Auth auth, boolean z, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: modifyUser");
            }
            if ((i10 & 1) != 0) {
                z = true;
            }
            if ((i10 & 2) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            return auth.modifyUser(z, str, lVar, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ String oAuthUrl$default(Auth auth, OAuthProvider oAuthProvider, String str, String str2, l lVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: oAuthUrl");
            }
            if ((i10 & 2) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            if ((i10 & 4) != 0) {
                str2 = "authorize";
            }
            if ((i10 & 8) != 0) {
                lVar = C16611.INSTANCE;
            }
            return auth.oAuthUrl(oAuthProvider, str, str2, lVar);
        }

        public static /* synthetic */ Object resendEmail$default(Auth auth, OtpType.Email email, String str, String str2, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resendEmail");
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            return auth.resendEmail(email, str, str2, dVar);
        }

        public static /* synthetic */ Object resendPhone$default(Auth auth, OtpType.Phone phone, String str, String str2, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resendPhone");
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            return auth.resendPhone(phone, str, str2, dVar);
        }

        public static /* synthetic */ Object resetPasswordForEmail$default(Auth auth, String str, String str2, String str3, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resetPasswordForEmail");
            }
            if ((i10 & 2) != 0) {
                str2 = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            if ((i10 & 4) != 0) {
                str3 = null;
            }
            return auth.resetPasswordForEmail(str, str2, str3, dVar);
        }

        public static String resolveUrl(Auth auth, String str) {
            return MainPlugin.DefaultImpls.resolveUrl(auth, str);
        }

        public static /* synthetic */ Object retrieveSSOUrl$default(Auth auth, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retrieveSSOUrl");
            }
            if ((i10 & 1) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            return auth.retrieveSSOUrl(str, lVar, dVar);
        }

        public static /* synthetic */ Object retrieveUserForCurrentSession$default(Auth auth, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retrieveUserForCurrentSession");
            }
            if ((i10 & 1) != 0) {
                z = false;
            }
            return auth.retrieveUserForCurrentSession(z, dVar);
        }

        public static /* synthetic */ Object signInWith$default(Auth auth, AuthProvider authProvider, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signInWith");
            }
            if ((i10 & 2) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            if ((i10 & 4) != 0) {
                lVar = null;
            }
            return auth.signInWith(authProvider, str, lVar, dVar);
        }

        public static /* synthetic */ Object signOut$default(Auth auth, SignOutScope signOutScope, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signOut");
            }
            if ((i10 & 1) != 0) {
                signOutScope = SignOutScope.LOCAL;
            }
            return auth.signOut(signOutScope, dVar);
        }

        public static /* synthetic */ Object signUpWith$default(Auth auth, AuthProvider authProvider, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signUpWith");
            }
            if ((i10 & 2) != 0) {
                str = RedirectUrlKt.defaultRedirectUrl(auth);
            }
            if ((i10 & 4) != 0) {
                lVar = null;
            }
            return auth.signUpWith(authProvider, str, lVar, dVar);
        }

        public static /* synthetic */ Object unlinkIdentity$default(Auth auth, String str, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: unlinkIdentity");
            }
            if ((i10 & 2) != 0) {
                z = true;
            }
            return auth.unlinkIdentity(str, z, dVar);
        }

        public static /* synthetic */ Object verifyEmailOtp$default(Auth auth, OtpType.Email email, String str, String str2, String str3, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verifyEmailOtp");
            }
            if ((i10 & 8) != 0) {
                str3 = null;
            }
            return auth.verifyEmailOtp(email, str, str2, str3, dVar);
        }

        public static /* synthetic */ Object verifyPhoneOtp$default(Auth auth, OtpType.Phone phone, String str, String str2, String str3, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verifyPhoneOtp");
            }
            if ((i10 & 8) != 0) {
                str3 = null;
            }
            return auth.verifyPhoneOtp(phone, str, str2, str3, dVar);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.Auth$importAuthToken$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.Auth$DefaultImpls", f = "Auth.kt", l = {276, 276}, m = "importAuthToken")
    public static final class AnonymousClass1 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.importAuthToken(null, null, null, false, false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.Auth$linkIdentity$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16601 extends r implements l<ExternalAuthConfigDefaults, t0> {
        public static final C16601 INSTANCE = new C16601();

        public C16601() {
            super(1);
        }

        public final void invoke(ExternalAuthConfigDefaults externalAuthConfigDefaults) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ExternalAuthConfigDefaults) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.Auth$oAuthUrl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16611 extends r implements l<ExternalAuthConfigDefaults, t0> {
        public static final C16611 INSTANCE = new C16611();

        public C16611() {
            super(1);
        }

        public final void invoke(ExternalAuthConfigDefaults externalAuthConfigDefaults) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ExternalAuthConfigDefaults) obj);
            return t0.f22605a;
        }
    }

    Object awaitInitialization(d<? super t0> dVar);

    Object clearSession(d<? super t0> dVar);

    String currentAccessTokenOrNull();

    List<Identity> currentIdentitiesOrNull();

    UserSession currentSessionOrNull();

    UserInfo currentUserOrNull();

    Object exchangeCodeForSession(String str, boolean z, d<? super UserSession> dVar);

    AdminApi getAdmin();

    CodeVerifierCache getCodeVerifierCache();

    MfaApi getMfa();

    SessionManager getSessionManager();

    h1<SessionStatus> getSessionStatus();

    Object importAuthToken(String str, String str2, boolean z, boolean z5, d<? super t0> dVar);

    Object importSession(UserSession userSession, boolean z, d<? super t0> dVar);

    boolean isAutoRefreshRunning();

    @SupabaseExperimental
    Object linkIdentity(OAuthProvider oAuthProvider, String str, l<? super ExternalAuthConfigDefaults, t0> lVar, d<? super t0> dVar);

    Object loadFromStorage(boolean z, d<? super Boolean> dVar);

    Object modifyUser(boolean z, String str, l<? super UserUpdateBuilder, t0> lVar, d<? super UserInfo> dVar);

    String oAuthUrl(OAuthProvider provider, String redirectUrl, String url, l<? super ExternalAuthConfigDefaults, t0> additionalConfig);

    Object reauthenticate(d<? super t0> dVar);

    Object refreshCurrentSession(d<? super t0> dVar);

    Object refreshSession(String str, d<? super UserSession> dVar);

    Object resendEmail(OtpType.Email email, String str, String str2, d<? super t0> dVar);

    Object resendPhone(OtpType.Phone phone, String str, String str2, d<? super t0> dVar);

    Object resetPasswordForEmail(String str, String str2, String str3, d<? super t0> dVar);

    Object retrieveSSOUrl(String str, l<? super SSO.Config, t0> lVar, d<? super SSO.Result> dVar);

    Object retrieveUser(String str, d<? super UserInfo> dVar);

    Object retrieveUserForCurrentSession(boolean z, d<? super UserInfo> dVar);

    <C, R, Provider extends AuthProvider<C, R>> Object signInWith(Provider provider, String str, l<? super C, t0> lVar, d<? super t0> dVar);

    Object signOut(SignOutScope signOutScope, d<? super t0> dVar);

    <C, R, Provider extends AuthProvider<C, R>> Object signUpWith(Provider provider, String str, l<? super C, t0> lVar, d<? super R> dVar);

    Object startAutoRefreshForCurrentSession(d<? super t0> dVar);

    void stopAutoRefreshForCurrentSession();

    @SupabaseExperimental
    Object unlinkIdentity(String str, boolean z, d<? super t0> dVar);

    Object verifyEmailOtp(OtpType.Email email, String str, String str2, String str3, d<? super t0> dVar);

    Object verifyPhoneOtp(OtpType.Phone phone, String str, String str2, String str3, d<? super t0> dVar);
}
