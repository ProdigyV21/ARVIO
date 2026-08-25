package io.github.jan.supabase.gotrue;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import d7.d;
import db.c0;
import f7.e;
import f7.j;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.annotations.SupabaseExperimental;
import io.github.jan.supabase.exceptions.SupabaseEncodingException;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.OtpType;
import io.github.jan.supabase.gotrue.SessionStatus;
import io.github.jan.supabase.gotrue.admin.AdminApi;
import io.github.jan.supabase.gotrue.admin.AdminApiImpl;
import io.github.jan.supabase.gotrue.mfa.MfaApi;
import io.github.jan.supabase.gotrue.mfa.MfaApiImpl;
import io.github.jan.supabase.gotrue.providers.AuthProvider;
import io.github.jan.supabase.gotrue.providers.ExternalAuthConfigDefaults;
import io.github.jan.supabase.gotrue.providers.OAuthProvider;
import io.github.jan.supabase.gotrue.user.Identity;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.http.ContentType;
import io.ktor.http.LinkHeader;
import j$.time.Clock;
import j$.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.s0;
import ka.v1;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import kotlin.reflect.b0;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import q2.f;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ_\u0010\u0014\u001a\u00020\b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0014\b\u0002\u0010\u000e*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r2\u0006\u0010\u000f\u001a\u00028\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012¢\u0006\u0002\b\u0013H\u0096@¢\u0006\u0004\b\u0014\u0010\u0015Ja\u0010\u0016\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f\"\u0014\b\u0002\u0010\u000e*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r2\u0006\u0010\u000f\u001a\u00028\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012¢\u0006\u0002\b\u0013H\u0096@¢\u0006\u0004\b\u0016\u0010\u0015J;\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0097@¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001cH\u0097@¢\u0006\u0004\b\u001e\u0010\u001fJ3\u0010\"\u001a\u00020!2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0096@¢\u0006\u0004\b\"\u0010#J;\u0010'\u001a\u00020&2\u0006\u0010$\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0096@¢\u0006\u0004\b'\u0010(J*\u0010-\u001a\u00020\b2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b-\u0010.J*\u00101\u001a\u00020\b2\u0006\u0010*\u001a\u00020/2\u0006\u00100\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b1\u00102J,\u00103\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b3\u00104J\u0010\u00105\u001a\u00020\bH\u0096@¢\u0006\u0004\b5\u00106J\u0018\u00109\u001a\u00020\b2\u0006\u00108\u001a\u000207H\u0096@¢\u0006\u0004\b9\u0010:J2\u0010<\u001a\u00020\b2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b<\u0010=J2\u0010?\u001a\u00020\b2\u0006\u0010*\u001a\u00020/2\u0006\u0010>\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b?\u0010@J\u0018\u0010B\u001a\u00020&2\u0006\u0010A\u001a\u00020\u0010H\u0096@¢\u0006\u0004\bB\u0010CJ\u0018\u0010E\u001a\u00020&2\u0006\u0010D\u001a\u00020\u001cH\u0096@¢\u0006\u0004\bE\u0010FJ \u0010J\u001a\u00020I2\u0006\u0010G\u001a\u00020\u00102\u0006\u0010H\u001a\u00020\u001cH\u0096@¢\u0006\u0004\bJ\u0010\u001fJ\u0018\u0010L\u001a\u00020I2\u0006\u0010K\u001a\u00020\u0010H\u0096@¢\u0006\u0004\bL\u0010CJ\u0010\u0010M\u001a\u00020\bH\u0096@¢\u0006\u0004\bM\u00106J \u0010P\u001a\u00020\b2\u0006\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020\u001cH\u0096@¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020\bH\u0096@¢\u0006\u0004\bR\u00106J\u000f\u0010S\u001a\u00020\bH\u0016¢\u0006\u0004\bS\u0010\nJ\u0018\u0010T\u001a\u00020\u001c2\u0006\u0010O\u001a\u00020\u001cH\u0096@¢\u0006\u0004\bT\u0010FJ\u0010\u0010U\u001a\u00020\bH\u0096@¢\u0006\u0004\bU\u00106J\u0018\u0010Y\u001a\u00020X2\u0006\u0010W\u001a\u00020VH\u0096@¢\u0006\u0004\bY\u0010ZJB\u0010]\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010[\u001a\u00020\u00102\u0017\u0010\\\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0004\b]\u0010^J\u0010\u0010_\u001a\u00020\bH\u0096@¢\u0006\u0004\b_\u00106J\u0010\u0010`\u001a\u00020\bH\u0096@¢\u0006\u0004\b`\u00106J1\u0010c\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00102\u0017\u0010b\u001a\u0013\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0082@¢\u0006\u0004\bc\u0010#JC\u0010e\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u00102\u0017\u0010d\u001a\u0013\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0013H\u0082@¢\u0006\u0004\be\u0010fJL\u0010k\u001a\u00020\b2\u001c\u0010i\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0g\u0012\u0006\u0012\u0004\u0018\u00010h0\u00122\u001c\u0010j\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0g\u0012\u0006\u0012\u0004\u0018\u00010h0\u0012H\u0082@¢\u0006\u0004\bk\u0010lJ\u0018\u0010m\u001a\u00020\b2\u0006\u0010N\u001a\u00020IH\u0082@¢\u0006\u0004\bm\u0010nJ\"\u0010o\u001a\u00020\b2\u0006\u0010N\u001a\u00020I2\b\b\u0002\u0010O\u001a\u00020\u001cH\u0082@¢\u0006\u0004\bo\u0010QR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010p\u001a\u0004\bq\u0010rR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010s\u001a\u0004\bt\u0010uR\u001a\u0010x\u001a\b\u0012\u0004\u0012\u00020w0v8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR \u0010{\u001a\b\u0012\u0004\u0012\u00020w0z8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b{\u0010|\u001a\u0004\b}\u0010~R\u001f\u0010\u0080\u0001\u001a\u00020\u007f8\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R \u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R'\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0000X\u0080\u0004¢\u0006\u0017\n\u0006\b\u008f\u0001\u0010\u0090\u0001\u0012\u0005\b\u0093\u0001\u0010\n\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R \u0010\u0095\u0001\u001a\u00030\u0094\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0095\u0001\u0010\u0096\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R \u0010\u009a\u0001\u001a\u00030\u0099\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R,\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u009f\u0001\u0010 \u0001\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R \u0010¦\u0001\u001a\u00030¥\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¨\u0001\u0010©\u0001R\u0017\u0010ª\u0001\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u0018\u0010¯\u0001\u001a\u00030¬\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u0017\u0010²\u0001\u001a\u00020\u00108VX\u0096\u0004¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001¨\u0006³\u0001"}, d2 = {"Lio/github/jan/supabase/gotrue/AuthImpl;", "Lio/github/jan/supabase/gotrue/Auth;", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lio/github/jan/supabase/gotrue/AuthConfig;", "config", "<init>", "(Lio/github/jan/supabase/SupabaseClient;Lio/github/jan/supabase/gotrue/AuthConfig;)V", "Lx6/t0;", "init", "()V", "C", "R", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Provider", "provider", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "signInWith", "(Lio/github/jan/supabase/gotrue/providers/AuthProvider;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUpWith", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "linkIdentity", "(Lio/github/jan/supabase/gotrue/providers/OAuthProvider;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "identityId", "", "updateLocalUser", "unlinkIdentity", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;", "retrieveSSOUrl", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "updateCurrentUser", "Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "modifyUser", "(ZLjava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/OtpType$Email;", LinkHeader.Parameters.Type, "email", "captchaToken", "resendEmail", "(Lio/github/jan/supabase/gotrue/OtpType$Email;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/OtpType$Phone;", "phoneNumber", "resendPhone", "(Lio/github/jan/supabase/gotrue/OtpType$Phone;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "resetPasswordForEmail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "reauthenticate", "(Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/SignOutScope;", "scope", "signOut", "(Lio/github/jan/supabase/gotrue/SignOutScope;Ld7/d;)Ljava/lang/Object;", "token", "verifyEmailOtp", "(Lio/github/jan/supabase/gotrue/OtpType$Email;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "phone", "verifyPhoneOtp", "(Lio/github/jan/supabase/gotrue/OtpType$Phone;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "jwt", "retrieveUser", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "updateSession", "retrieveUserForCurrentSession", "(ZLd7/d;)Ljava/lang/Object;", "code", "saveSession", "Lio/github/jan/supabase/gotrue/user/UserSession;", "exchangeCodeForSession", "refreshToken", "refreshSession", "refreshCurrentSession", SettingsSessionManager.SETTINGS_KEY, "autoRefresh", "importSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;ZLd7/d;)Ljava/lang/Object;", "startAutoRefreshForCurrentSession", "stopAutoRefreshForCurrentSession", "loadFromStorage", "close", "Lio/ktor/client/statement/HttpResponse;", "response", "Lio/github/jan/supabase/exceptions/RestException;", "parseErrorResponse", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "url", "additionalConfig", "oAuthUrl", "(Lio/github/jan/supabase/gotrue/providers/OAuthProvider;Ljava/lang/String;Ljava/lang/String;Lr7/l;)Ljava/lang/String;", "clearSession", "awaitInitialization", "Ldb/c0;", TtmlNode.TAG_BODY, "resend", "additionalData", "verify", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/d;", "", "importRefreshedSession", "retry", "tryImportingSession", "(Lr7/l;Lr7/l;Ld7/d;)Ljava/lang/Object;", "delayBeforeExpiry", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "handleExpiredSession", "Lio/github/jan/supabase/SupabaseClient;", "getSupabaseClient", "()Lio/github/jan/supabase/SupabaseClient;", "Lio/github/jan/supabase/gotrue/AuthConfig;", "getConfig", "()Lio/github/jan/supabase/gotrue/AuthConfig;", "Lna/q0;", "Lio/github/jan/supabase/gotrue/SessionStatus;", "_sessionStatus", "Lna/q0;", "Lna/h1;", "sessionStatus", "Lna/h1;", "getSessionStatus", "()Lna/h1;", "Lka/k0;", "authScope", "Lka/k0;", "getAuthScope$gotrue_kt_release", "()Lka/k0;", "Lio/github/jan/supabase/gotrue/SessionManager;", "sessionManager", "Lio/github/jan/supabase/gotrue/SessionManager;", "getSessionManager", "()Lio/github/jan/supabase/gotrue/SessionManager;", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "codeVerifierCache", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "getCodeVerifierCache", "()Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "api", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi$gotrue_kt_release", "()Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi$gotrue_kt_release$annotations", "Lio/github/jan/supabase/gotrue/admin/AdminApi;", "admin", "Lio/github/jan/supabase/gotrue/admin/AdminApi;", "getAdmin", "()Lio/github/jan/supabase/gotrue/admin/AdminApi;", "Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "mfa", "Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "getMfa", "()Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "Lka/v1;", "sessionJob", "Lka/v1;", "getSessionJob", "()Lka/v1;", "setSessionJob", "(Lka/v1;)V", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "isAutoRefreshRunning", "()Z", "", "getApiVersion", "()I", "apiVersion", "getPluginKey", "()Ljava/lang/String;", "pluginKey", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AuthImpl implements Auth {
    private final q0<SessionStatus> _sessionStatus;
    private final AdminApi admin;
    private final AuthenticatedSupabaseApi api;
    private final k0 authScope;
    private final CodeVerifierCache codeVerifierCache;
    private final AuthConfig config;
    private final MfaApi mfa;
    private final SupabaseSerializer serializer;
    private v1 sessionJob;
    private final SessionManager sessionManager;
    private final h1<SessionStatus> sessionStatus;
    private final SupabaseClient supabaseClient;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$awaitInitialization$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/github/jan/supabase/gotrue/SessionStatus;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$awaitInitialization$2", f = "AuthImpl.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<SessionStatus, d<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(SessionStatus sessionStatus, d<? super Boolean> dVar) {
            return ((AnonymousClass2) create(sessionStatus, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return Boolean.valueOf(!(((SessionStatus) this.L$0) instanceof SessionStatus.LoadingFromStorage));
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$clearSession$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {533, 534}, m = "clearSession")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.clearSession(this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$exchangeCodeForSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {361, 559, 566, 368, 370}, m = "exchangeCodeForSession")
    public static final class C16621 extends f7.c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16621(d<? super C16621> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.exchangeCodeForSession(null, false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {455, 456}, m = "handleExpiredSession")
    public static final class C16631 extends f7.c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16631(d<? super C16631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.handleExpiredSession(null, false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C16642 extends r implements a<String> {
        public static final C16642 INSTANCE = new C16642();

        public C16642() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "Session expired. Refreshing session...";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 405, MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER}, m = "importSession")
    public static final class C16651 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16651(d<? super C16651> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.importSession(null, false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$2", f = "AuthImpl.kt", l = {406}, m = "invokeSuspend")
    public static final class C16662 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ UserSession $session;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16662(UserSession userSession, d<? super C16662> dVar) {
            super(1, dVar);
            this.$session = userSession;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return AuthImpl.this.new C16662(this.$session, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((C16662) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws SupabaseEncodingException {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                AuthImpl authImpl = AuthImpl.this;
                UserSession userSession = this.$session;
                boolean alwaysAutoRefresh = authImpl.getConfig().getAlwaysAutoRefresh();
                this.label = 1;
                Object objHandleExpiredSession = authImpl.handleExpiredSession(userSession, alwaysAutoRefresh, this);
                e7.a aVar = e7.a.f15033i;
                if (objHandleExpiredSession == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$3", f = "AuthImpl.kt", l = {407}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ UserSession $session;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(UserSession userSession, d<? super AnonymousClass3> dVar) {
            super(1, dVar);
            this.$session = userSession;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return AuthImpl.this.new AnonymousClass3(this.$session, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((AnonymousClass3) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                AuthImpl authImpl = AuthImpl.this;
                UserSession userSession = this.$session;
                this.label = 1;
                Object objImportSession$default = Auth.DefaultImpls.importSession$default(authImpl, userSession, false, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objImportSession$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$4", f = "AuthImpl.kt", l = {414}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ UserSession $session;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
        @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1", f = "AuthImpl.kt", l = {416}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ UserSession $session;
            int label;
            final /* synthetic */ AuthImpl this$0;

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 9, 0})
            @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1$1", f = "AuthImpl.kt", l = {417}, m = "invokeSuspend")
            public static final class C02261 extends j implements l<d<? super t0>, Object> {
                final /* synthetic */ UserSession $session;
                int label;
                final /* synthetic */ AuthImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02261(AuthImpl authImpl, UserSession userSession, d<? super C02261> dVar) {
                    super(1, dVar);
                    this.this$0 = authImpl;
                    this.$session = userSession;
                }

                @Override // f7.a
                public final d<t0> create(d<?> dVar) {
                    return new C02261(this.this$0, this.$session, dVar);
                }

                @Override // r7.l
                public final Object invoke(d<? super t0> dVar) {
                    return ((C02261) create(dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        AuthImpl authImpl = this.this$0;
                        UserSession userSession = this.$session;
                        this.label = 1;
                        Object objHandleExpiredSession$default = AuthImpl.handleExpiredSession$default(authImpl, userSession, false, this, 2, null);
                        e7.a aVar = e7.a.f15033i;
                        if (objHandleExpiredSession$default == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        c.G(obj);
                    }
                    return t0.f22605a;
                }
            }

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 9, 0})
            @e(c = "io.github.jan.supabase.gotrue.AuthImpl$importSession$4$1$2", f = "AuthImpl.kt", l = {418}, m = "invokeSuspend")
            public static final class AnonymousClass2 extends j implements l<d<? super t0>, Object> {
                final /* synthetic */ UserSession $session;
                int label;
                final /* synthetic */ AuthImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(AuthImpl authImpl, UserSession userSession, d<? super AnonymousClass2> dVar) {
                    super(1, dVar);
                    this.this$0 = authImpl;
                    this.$session = userSession;
                }

                @Override // f7.a
                public final d<t0> create(d<?> dVar) {
                    return new AnonymousClass2(this.this$0, this.$session, dVar);
                }

                @Override // r7.l
                public final Object invoke(d<? super t0> dVar) {
                    return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        AuthImpl authImpl = this.this$0;
                        UserSession userSession = this.$session;
                        this.label = 1;
                        Object objImportSession$default = Auth.DefaultImpls.importSession$default(authImpl, userSession, false, this, 2, null);
                        e7.a aVar = e7.a.f15033i;
                        if (objImportSession$default == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        c.G(obj);
                    }
                    return t0.f22605a;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AuthImpl authImpl, UserSession userSession, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = authImpl;
                this.$session = userSession;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$session, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    AuthImpl authImpl = this.this$0;
                    C02261 c02261 = new C02261(authImpl, this.$session, null);
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$session, null);
                    this.label = 1;
                    Object objTryImportingSession = authImpl.tryImportingSession(c02261, anonymousClass2, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objTryImportingSession == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(UserSession userSession, d<? super AnonymousClass4> dVar) {
            super(2, dVar);
            this.$session = userSession;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass4 anonymousClass4 = AuthImpl.this.new AnonymousClass4(this.$session, dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                k0Var = (k0) this.L$0;
                AuthImpl authImpl = AuthImpl.this;
                UserSession userSession = this.$session;
                this.L$0 = k0Var;
                this.label = 1;
                Object objDelayBeforeExpiry = authImpl.delayBeforeExpiry(userSession, this);
                e7.a aVar = e7.a.f15033i;
                if (objDelayBeforeExpiry == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k0Var = (k0) this.L$0;
                c.G(obj);
            }
            m0.p(k0Var, null, 0, new AnonymousClass1(AuthImpl.this, this.$session, null), 3);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$init$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$init$1", f = "AuthImpl.kt", l = {86}, m = "invokeSuspend")
    public static final class C16671 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$init$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class C02271 extends r implements a<String> {
            public static final C02271 INSTANCE = new C02271();

            public C02271() {
                super(0);
            }

            @Override // r7.a
            public final String invoke() {
                return "Trying to load latest session";
            }
        }

        /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$init$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class AnonymousClass2 extends r implements a<String> {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            public AnonymousClass2() {
                super(0);
            }

            @Override // r7.a
            public final String invoke() {
                return "Successfully loaded session from storage";
            }
        }

        public C16671(d<? super C16671> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AuthImpl.this.new C16671(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                f.A0(f.f21327l, "Auth", C02271.INSTANCE);
                AuthImpl authImpl = AuthImpl.this;
                this.label = 1;
                obj = Auth.DefaultImpls.loadFromStorage$default(authImpl, false, this, 1, null);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                f.A0(f.f21327l, "Auth", AnonymousClass2.INSTANCE);
            } else {
                AuthImpl.this._sessionStatus.setValue(SessionStatus.NotAuthenticated.INSTANCE);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C16671) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$linkIdentity$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$linkIdentity$2", f = "AuthImpl.kt", l = {126}, m = "invokeSuspend")
    public static final class C16682 extends j implements p<String, d<? super String>, Object> {
        final /* synthetic */ l<ExternalAuthConfigDefaults, t0> $config;
        final /* synthetic */ OAuthProvider $provider;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C16682(OAuthProvider oAuthProvider, l<? super ExternalAuthConfigDefaults, t0> lVar, d<? super C16682> dVar) {
            super(2, dVar);
            this.$provider = oAuthProvider;
            this.$config = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C16682 c16682 = AuthImpl.this.new C16682(this.$provider, this.$config, dVar);
            c16682.L$0 = obj;
            return c16682;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                String strOAuthUrl = AuthImpl.this.oAuthUrl(this.$provider, (String) this.L$0, "user/identities/authorize", this.$config);
                AuthenticatedSupabaseApi api = AuthImpl.this.getApi();
                AuthImpl$linkIdentity$2$response$1 authImpl$linkIdentity$2$response$1 = AuthImpl$linkIdentity$2$response$1.INSTANCE;
                this.label = 1;
                obj = api.rawRequest(strOAuthUrl, authImpl$linkIdentity$2$response$1, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return HttpResponseKt.getRequest((HttpResponse) obj).getUrl().getUrlString();
        }

        @Override // r7.p
        public final Object invoke(String str, d<? super String> dVar) {
            return ((C16682) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$linkIdentity$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserSession;", "it", "Lx6/t0;", "<anonymous>", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$linkIdentity$3", f = "AuthImpl.kt", l = {132}, m = "invokeSuspend")
    public static final class C16693 extends j implements p<UserSession, d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C16693(d<? super C16693> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C16693 c16693 = AuthImpl.this.new C16693(dVar);
            c16693.L$0 = obj;
            return c16693;
        }

        @Override // r7.p
        public final Object invoke(UserSession userSession, d<? super t0> dVar) {
            return ((C16693) create(userSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                UserSession userSession = (UserSession) this.L$0;
                AuthImpl authImpl = AuthImpl.this;
                this.label = 1;
                Object objImportSession$default = Auth.DefaultImpls.importSession$default(authImpl, userSession, false, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objImportSession$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$loadFromStorage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {468, 470}, m = "loadFromStorage")
    public static final class C16701 extends f7.c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16701(d<? super C16701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.loadFromStorage(false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$modifyUser$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {197, 560, 567, 215}, m = "modifyUser")
    public static final class C16711 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16711(d<? super C16711> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.modifyUser(false, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$oAuthUrl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$oAuthUrl$1", f = "AuthImpl.kt", l = {516}, m = "invokeSuspend")
    public static final class C16721 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ String $codeVerifier;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16721(String str, d<? super C16721> dVar) {
            super(2, dVar);
            this.$codeVerifier = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AuthImpl.this.new C16721(this.$codeVerifier, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                CodeVerifierCache codeVerifierCache = AuthKt.getAuth(AuthImpl.this.getSupabaseClient()).getCodeVerifierCache();
                String str = this.$codeVerifier;
                this.label = 1;
                Object objSaveCodeVerifier = codeVerifierCache.saveCodeVerifier(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveCodeVerifier == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C16721) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$parseErrorResponse$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {555}, m = "parseErrorResponse")
    public static final class C16731 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16731(d<? super C16731> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.parseErrorResponse(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$refreshCurrentSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {389, 393}, m = "refreshCurrentSession")
    public static final class C16741 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16741(d<? super C16741> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.refreshCurrentSession(this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$refreshSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {559, 565}, m = "refreshSession")
    public static final class C16751 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16751(d<? super C16751> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.refreshSession(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$refreshSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C16762 extends r implements a<String> {
        public static final C16762 INSTANCE = new C16762();

        public C16762() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "Refreshing session";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$resendEmail$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ldb/c0;", "Lx6/t0;", "invoke", "(Ldb/c0;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16772 extends r implements l<c0, t0> {
        final /* synthetic */ String $captchaToken;
        final /* synthetic */ String $email;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16772(String str, String str2) {
            super(1);
            this.$email = str;
            this.$captchaToken = str2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((c0) obj);
            return t0.f22605a;
        }

        public final void invoke(c0 c0Var) {
            f4.f.r(c0Var, "email", this.$email);
            String str = this.$captchaToken;
            if (str != null) {
                f4.f.s(c0Var, new AuthImpl$resendEmail$2$1$1(str));
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$resendPhone$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ldb/c0;", "Lx6/t0;", "invoke", "(Ldb/c0;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16782 extends r implements l<c0, t0> {
        final /* synthetic */ String $captchaToken;
        final /* synthetic */ String $phoneNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16782(String str, String str2) {
            super(1);
            this.$phoneNumber = str;
            this.$captchaToken = str2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((c0) obj);
            return t0.f22605a;
        }

        public final void invoke(c0 c0Var) {
            f4.f.r(c0Var, "phone", this.$phoneNumber);
            String str = this.$captchaToken;
            if (str != null) {
                f4.f.s(c0Var, new AuthImpl$resendPhone$2$1$1(str));
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$resetPasswordForEmail$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {263, 559}, m = "resetPasswordForEmail")
    public static final class C16791 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C16791(d<? super C16791> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.resetPasswordForEmail(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$retrieveSSOUrl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {165, 564, 570}, m = "retrieveSSOUrl")
    public static final class C16801 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C16801(d<? super C16801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.retrieveSSOUrl(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {554, 345}, m = "retrieveUser")
    public static final class C16811 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C16811(d<? super C16811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.retrieveUser(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$retrieveUserForCurrentSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {350, 355}, m = "retrieveUserForCurrentSession")
    public static final class C16821 extends f7.c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16821(d<? super C16821> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.retrieveUserForCurrentSession(false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signInWith$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0014\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"C", "R", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Provider", "Lio/github/jan/supabase/gotrue/user/UserSession;", "it", "Lx6/t0;", "<anonymous>", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$signInWith$2", f = "AuthImpl.kt", l = {105}, m = "invokeSuspend")
    public static final class C16832 extends j implements p<UserSession, d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C16832(d<? super C16832> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C16832 c16832 = AuthImpl.this.new C16832(dVar);
            c16832.L$0 = obj;
            return c16832;
        }

        @Override // r7.p
        public final Object invoke(UserSession userSession, d<? super t0> dVar) {
            return ((C16832) create(userSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                UserSession userSession = (UserSession) this.L$0;
                AuthImpl authImpl = AuthImpl.this;
                this.label = 1;
                Object objImportSession$default = Auth.DefaultImpls.importSession$default(authImpl, userSession, false, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objImportSession$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signOut$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {554, 297}, m = "signOut")
    public static final class C16841 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16841(d<? super C16841> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.signOut(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signOut$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C16853 extends r implements a<String> {
        public static final C16853 INSTANCE = new C16853();

        public C16853() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "Logged out session in Supabase";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signOut$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C16864 extends r implements a<String> {
        public static final C16864 INSTANCE = new C16864();

        public C16864() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "Skipping session logout as there is no session available. Proceeding to clean up local data...";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signOut$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements a<String> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "Successfully logged out";
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$signUpWith$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0014\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"C", "R", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Provider", "Lio/github/jan/supabase/gotrue/user/UserSession;", "it", "Lx6/t0;", "<anonymous>", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl$signUpWith$2", f = "AuthImpl.kt", l = {113}, m = "invokeSuspend")
    public static final class C16872 extends j implements p<UserSession, d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C16872(d<? super C16872> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C16872 c16872 = AuthImpl.this.new C16872(dVar);
            c16872.L$0 = obj;
            return c16872;
        }

        @Override // r7.p
        public final Object invoke(UserSession userSession, d<? super t0> dVar) {
            return ((C16872) create(userSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                UserSession userSession = (UserSession) this.L$0;
                AuthImpl authImpl = AuthImpl.this;
                this.label = 1;
                Object objImportSession$default = Auth.DefaultImpls.importSession$default(authImpl, userSession, false, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objImportSession$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$tryImportingSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {430, 432, 437, 438}, m = "tryImportingSession")
    public static final class C16881 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16881(d<? super C16881> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.tryImportingSession(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$unlinkIdentity$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {554}, m = "unlinkIdentity")
    public static final class C16891 extends f7.c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16891(d<? super C16891> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.unlinkIdentity(null, false, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$verify$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthImpl", f = "AuthImpl.kt", l = {559, 565, 320}, m = "verify")
    public static final class C16901 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16901(d<? super C16901> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthImpl.this.verify(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$verifyEmailOtp$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ldb/c0;", "Lx6/t0;", "invoke", "(Ldb/c0;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16912 extends r implements l<c0, t0> {
        final /* synthetic */ String $email;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16912(String str) {
            super(1);
            this.$email = str;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((c0) obj);
            return t0.f22605a;
        }

        public final void invoke(c0 c0Var) {
            f4.f.r(c0Var, "email", this.$email);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthImpl$verifyPhoneOtp$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ldb/c0;", "Lx6/t0;", "invoke", "(Ldb/c0;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16922 extends r implements l<c0, t0> {
        final /* synthetic */ String $phone;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16922(String str) {
            super(1);
            this.$phone = str;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((c0) obj);
            return t0.f22605a;
        }

        public final void invoke(c0 c0Var) {
            f4.f.r(c0Var, "phone", this.$phone);
        }
    }

    public AuthImpl(SupabaseClient supabaseClient, AuthConfig authConfig) {
        this.supabaseClient = supabaseClient;
        this.config = authConfig;
        j1 j1VarB = y0.b(SessionStatus.LoadingFromStorage.INSTANCE);
        this._sessionStatus = j1VarB;
        this.sessionStatus = y0.e(j1VarB);
        this.authScope = l0.a(getConfig().getCoroutineDispatcher());
        SessionManager sessionManager = getConfig().getSessionManager();
        this.sessionManager = sessionManager == null ? SettingsUtilKt.createDefaultSessionManager(this) : sessionManager;
        CodeVerifierCache codeVerifierCache = getConfig().getCodeVerifierCache();
        this.codeVerifierCache = codeVerifierCache == null ? SettingsUtilKt.createDefaultCodeVerifierCache(this) : codeVerifierCache;
        this.api = AuthenticatedSupabaseApiKt.authenticatedSupabaseApi$default(getSupabaseClient(), this, (l) null, 2, (Object) null);
        this.admin = new AdminApiImpl(this);
        this.mfa = new MfaApiImpl(this);
        SupabaseSerializer serializer = getConfig().getSerializer();
        this.serializer = serializer == null ? getSupabaseClient().getDefaultSerializer() : serializer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object delayBeforeExpiry(UserSession userSession, d<? super t0> dVar) {
        wa.c expiresAt = userSession.getExpiresAt();
        wa.c.Companion.getClass();
        wa.c cVar = new wa.c(Clock.systemUTC().instant());
        expiresAt.getClass();
        io.sentry.util.l lVar = kotlin.time.c.f19956l;
        long epochSecond = expiresAt.f22539i.getEpochSecond();
        Instant instant = cVar.f22539i;
        Object objA = s0.a((long) Math.floor((kotlin.time.c.i(kotlin.time.c.l(b0.I(epochSecond - instant.getEpochSecond(), kotlin.time.e.SECONDS), b0.H(r6.getNano() - instant.getNano(), kotlin.time.e.NANOSECONDS))) * 4.0f) / 5.0f), dVar);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    public static /* synthetic */ void getApi$gotrue_kt_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
    
        if (r7.importSession((io.github.jan.supabase.gotrue.user.UserSession) r9, r8, r0) == r4) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object handleExpiredSession(io.github.jan.supabase.gotrue.user.UserSession r7, boolean r8, d7.d<? super x6.t0> r9) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16631
            if (r0 == 0) goto L13
            r0 = r9
            io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16631) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3c
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r9)
            goto L68
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            boolean r8 = r0.Z$0
            java.lang.Object r7 = r0.L$0
            io.github.jan.supabase.gotrue.AuthImpl r7 = (io.github.jan.supabase.gotrue.AuthImpl) r7
            k2.c.G(r9)
            goto L5a
        L3c:
            k2.c.G(r9)
            q2.f r9 = q2.f.f21327l
            java.lang.String r1 = "Auth"
            io.github.jan.supabase.gotrue.AuthImpl$handleExpiredSession$2 r5 = io.github.jan.supabase.gotrue.AuthImpl.C16642.INSTANCE
            q2.f.A0(r9, r1, r5)
            java.lang.String r7 = r7.getRefreshToken()
            r0.L$0 = r6
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r9 = r6.refreshSession(r7, r0)
            if (r9 != r4) goto L59
            goto L67
        L59:
            r7 = r6
        L5a:
            io.github.jan.supabase.gotrue.user.UserSession r9 = (io.github.jan.supabase.gotrue.user.UserSession) r9
            r1 = 0
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r7 = r7.importSession(r9, r8, r0)
            if (r7 != r4) goto L68
        L67:
            return r4
        L68:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.handleExpiredSession(io.github.jan.supabase.gotrue.user.UserSession, boolean, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object handleExpiredSession$default(AuthImpl authImpl, UserSession userSession, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return authImpl.handleExpiredSession(userSession, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resend(String str, l<? super c0, t0> lVar, d<? super t0> dVar) {
        AuthenticatedSupabaseApi authenticatedSupabaseApi = this.api;
        c0 c0Var = new c0();
        f4.f.r(c0Var, LinkHeader.Parameters.Type, str);
        c0 c0Var2 = new c0();
        lVar.invoke(c0Var2);
        io.github.jan.supabase.UtilsKt.putJsonObject(c0Var, c0Var2.a());
        Object objRequest = authenticatedSupabaseApi.request("resend", new AuthImpl$resend$$inlined$postJson$default$1(ContentType.Application.INSTANCE.getJson(), c0Var.a()), dVar);
        return objRequest == e7.a.f15033i ? objRequest : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00df A[PHI: r3
      0x00df: PHI (r3v8 r7.l<? super d7.d<? super x6.t0>, ? extends java.lang.Object>) = 
      (r3v4 r7.l<? super d7.d<? super x6.t0>, ? extends java.lang.Object>)
      (r3v12 r7.l<? super d7.d<? super x6.t0>, ? extends java.lang.Object>)
     binds: [B:50:0x00dc, B:17:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0107 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryImportingSession(r7.l<? super d7.d<? super x6.t0>, ? extends java.lang.Object> r17, r7.l<? super d7.d<? super x6.t0>, ? extends java.lang.Object> r18, d7.d<? super x6.t0> r19) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.tryImportingSession(r7.l, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c0, code lost:
    
        if (io.github.jan.supabase.gotrue.Auth.DefaultImpls.importSession$default(r9, (io.github.jan.supabase.gotrue.user.UserSession) r13, false, r4, 2, null) == r7) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object verify(java.lang.String r9, java.lang.String r10, java.lang.String r11, r7.l<? super db.c0, x6.t0> r12, d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.verify(java.lang.String, java.lang.String, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object awaitInitialization(d<? super t0> dVar) {
        Object objM = y0.m(getSessionStatus(), new AnonymousClass2(null), dVar);
        return objM == e7.a.f15033i ? objM : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object clearSession(d7.d<? super x6.t0> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.github.jan.supabase.gotrue.AuthImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.github.jan.supabase.gotrue.AuthImpl$clearSession$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$clearSession$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$clearSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            io.github.jan.supabase.gotrue.AuthImpl r0 = (io.github.jan.supabase.gotrue.AuthImpl) r0
            k2.c.G(r6)
            goto L61
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L36:
            java.lang.Object r1 = r0.L$0
            io.github.jan.supabase.gotrue.AuthImpl r1 = (io.github.jan.supabase.gotrue.AuthImpl) r1
            k2.c.G(r6)
            goto L51
        L3e:
            k2.c.G(r6)
            io.github.jan.supabase.gotrue.CodeVerifierCache r6 = r5.getCodeVerifierCache()
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.deleteCodeVerifier(r0)
            if (r6 != r4) goto L50
            goto L5f
        L50:
            r1 = r5
        L51:
            io.github.jan.supabase.gotrue.SessionManager r6 = r1.getSessionManager()
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r6 = r6.deleteSession(r0)
            if (r6 != r4) goto L60
        L5f:
            return r4
        L60:
            r0 = r1
        L61:
            ka.v1 r6 = r0.sessionJob
            r1 = 0
            if (r6 == 0) goto L69
            r6.cancel(r1)
        L69:
            na.q0<io.github.jan.supabase.gotrue.SessionStatus> r6 = r0._sessionStatus
            io.github.jan.supabase.gotrue.SessionStatus$NotAuthenticated r2 = io.github.jan.supabase.gotrue.SessionStatus.NotAuthenticated.INSTANCE
            r6.setValue(r2)
            r0.sessionJob = r1
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.clearSession(d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.plugins.SupabasePlugin
    public Object close(d<? super t0> dVar) {
        l0.b(this.authScope, null);
        return t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public String currentAccessTokenOrNull() {
        return Auth.DefaultImpls.currentAccessTokenOrNull(this);
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public List<Identity> currentIdentitiesOrNull() {
        return Auth.DefaultImpls.currentIdentitiesOrNull(this);
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public UserSession currentSessionOrNull() {
        return Auth.DefaultImpls.currentSessionOrNull(this);
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public UserInfo currentUserOrNull() {
        return Auth.DefaultImpls.currentUserOrNull(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object exchangeCodeForSession(java.lang.String r18, boolean r19, d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r20) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            Method dump skipped, instruction units count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.exchangeCodeForSession(java.lang.String, boolean, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public AdminApi getAdmin() {
        return this.admin;
    }

    /* JADX INFO: renamed from: getApi$gotrue_kt_release, reason: from getter */
    public final AuthenticatedSupabaseApi getApi() {
        return this.api;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public int getApiVersion() {
        return 1;
    }

    /* JADX INFO: renamed from: getAuthScope$gotrue_kt_release, reason: from getter */
    public final k0 getAuthScope() {
        return this.authScope;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public CodeVerifierCache getCodeVerifierCache() {
        return this.codeVerifierCache;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public MfaApi getMfa() {
        return this.mfa;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public String getPluginKey() {
        return Auth.INSTANCE.getKey();
    }

    @Override // io.github.jan.supabase.plugins.CustomSerializationPlugin
    public SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    public final v1 getSessionJob() {
        return this.sessionJob;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public h1<SessionStatus> getSessionStatus() {
        return this.sessionStatus;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public SupabaseClient getSupabaseClient() {
        return this.supabaseClient;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object importAuthToken(String str, String str2, boolean z, boolean z5, d<? super t0> dVar) {
        return Auth.DefaultImpls.importAuthToken(this, str, str2, z, z5, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00da, code lost:
    
        if (r10.saveSession(r9, r0) == r11) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object importSession(io.github.jan.supabase.gotrue.user.UserSession r9, boolean r10, d7.d<? super x6.t0> r11) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.importSession(io.github.jan.supabase.gotrue.user.UserSession, boolean, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public void init() {
        SetupPlatformKt.setupPlatform(this);
        if (getConfig().getAutoLoadFromStorage()) {
            m0.p(this.authScope, null, 0, new C16671(null), 3);
        } else {
            this._sessionStatus.setValue(SessionStatus.NotAuthenticated.INSTANCE);
        }
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public boolean isAutoRefreshRunning() {
        v1 v1Var = this.sessionJob;
        return v1Var != null && v1Var.isActive();
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    @SupabaseExperimental
    public Object linkIdentity(OAuthProvider oAuthProvider, String str, l<? super ExternalAuthConfigDefaults, t0> lVar, d<? super t0> dVar) {
        Object objStartExternalAuth = Utils_androidKt.startExternalAuth(this, str, new C16682(oAuthProvider, lVar, null), new C16693(null), dVar);
        return objStartExternalAuth == e7.a.f15033i ? objStartExternalAuth : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadFromStorage(boolean r6, d7.d<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16701
            if (r0 == 0) goto L13
            r0 = r7
            io.github.jan.supabase.gotrue.AuthImpl$loadFromStorage$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$loadFromStorage$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$loadFromStorage$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L40
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$0
            io.github.jan.supabase.gotrue.user.UserSession r6 = (io.github.jan.supabase.gotrue.user.UserSession) r6
            k2.c.G(r7)
            goto L65
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            boolean r6 = r0.Z$0
            java.lang.Object r1 = r0.L$0
            io.github.jan.supabase.gotrue.AuthImpl r1 = (io.github.jan.supabase.gotrue.AuthImpl) r1
            k2.c.G(r7)
            goto L55
        L40:
            k2.c.G(r7)
            io.github.jan.supabase.gotrue.SessionManager r7 = r5.getSessionManager()
            r0.L$0 = r5
            r0.Z$0 = r6
            r0.label = r3
            java.lang.Object r7 = r7.loadSession(r0)
            if (r7 != r4) goto L54
            goto L63
        L54:
            r1 = r5
        L55:
            io.github.jan.supabase.gotrue.user.UserSession r7 = (io.github.jan.supabase.gotrue.user.UserSession) r7
            if (r7 == 0) goto L66
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r6 = r1.importSession(r7, r6, r0)
            if (r6 != r4) goto L64
        L63:
            return r4
        L64:
            r6 = r7
        L65:
            r7 = r6
        L66:
            if (r7 == 0) goto L69
            goto L6a
        L69:
            r3 = 0
        L6a:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.loadFromStorage(boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object modifyUser(boolean r25, java.lang.String r26, r7.l<? super io.github.jan.supabase.gotrue.user.UserUpdateBuilder, x6.t0> r27, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r28) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.modifyUser(boolean, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public String oAuthUrl(OAuthProvider provider, String redirectUrl, String url, l<? super ExternalAuthConfigDefaults, t0> additionalConfig) {
        ExternalAuthConfigDefaults externalAuthConfigDefaults = new ExternalAuthConfigDefaults();
        additionalConfig.invoke(externalAuthConfigDefaults);
        if (getConfig().getFlowType() == FlowType.PKCE) {
            String strGenerateCodeVerifier = PKCEKt.generateCodeVerifier();
            m0.p(this.authScope, null, 0, new C16721(strGenerateCodeVerifier, null), 3);
            externalAuthConfigDefaults.getQueryParams().put("code_challenge", PKCEKt.generateCodeChallenge(strGenerateCodeVerifier));
            externalAuthConfigDefaults.getQueryParams().put("code_challenge_method", "S256");
        }
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sbP = a2.p(url, "?provider=");
        sbP.append(provider.getName());
        sbP.append("&redirect_to=");
        sbP.append(redirectUrl);
        sb2.append(sbP.toString());
        if (!externalAuthConfigDefaults.getScopes().isEmpty()) {
            sb2.append("&scopes=" + x.u0(externalAuthConfigDefaults.getScopes(), "+", null, null, null, 62));
        }
        if (!externalAuthConfigDefaults.getQueryParams().isEmpty()) {
            for (Map.Entry<String, String> entry : externalAuthConfigDefaults.getQueryParams().entrySet()) {
                sb2.append("&" + entry.getKey() + '=' + entry.getValue());
            }
        }
        return resolveUrl(sb2.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.plugins.MainPlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object parseErrorResponse(io.ktor.client.statement.HttpResponse r10, d7.d<? super io.github.jan.supabase.exceptions.RestException> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16731
            if (r0 == 0) goto L13
            r0 = r11
            io.github.jan.supabase.gotrue.AuthImpl$parseErrorResponse$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16731) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$parseErrorResponse$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$parseErrorResponse$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r10 = r0.L$0
            io.ktor.client.statement.HttpResponse r10 = (io.ktor.client.statement.HttpResponse) r10
            k2.c.G(r11)     // Catch: java.lang.Exception -> L55
            goto L42
        L2a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L32:
            k2.c.G(r11)
            r0.L$0 = r10     // Catch: java.lang.Exception -> L55
            r0.label = r3     // Catch: java.lang.Exception -> L55
            java.lang.Object r11 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r10, r2, r0, r3, r2)     // Catch: java.lang.Exception -> L55
            e7.a r0 = e7.a.f15033i
            if (r11 != r0) goto L42
            return r0
        L42:
            java.lang.String r11 = (java.lang.String) r11     // Catch: java.lang.Exception -> L55
            db.b r0 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: java.lang.Exception -> L55
            r0.getClass()     // Catch: java.lang.Exception -> L55
            io.github.jan.supabase.gotrue.GoTrueErrorResponse$Companion r1 = io.github.jan.supabase.gotrue.GoTrueErrorResponse.INSTANCE     // Catch: java.lang.Exception -> L55
            ya.h r1 = r1.serializer()     // Catch: java.lang.Exception -> L55
            java.lang.Object r2 = r0.c(r11, r1)     // Catch: java.lang.Exception -> L55
        L55:
            r5 = r10
            io.github.jan.supabase.gotrue.GoTrueErrorResponse r2 = (io.github.jan.supabase.gotrue.GoTrueErrorResponse) r2
            if (r2 != 0) goto L63
            io.github.jan.supabase.gotrue.GoTrueErrorResponse r2 = new io.github.jan.supabase.gotrue.GoTrueErrorResponse
            java.lang.String r10 = "Unknown error"
            java.lang.String r11 = ""
            r2.<init>(r10, r11)
        L63:
            io.ktor.http.HttpStatusCode r10 = r5.getStatus()
            io.ktor.http.HttpStatusCode$Companion r11 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r0 = r11.getUnauthorized()
            boolean r0 = kotlin.jvm.internal.p.a(r10, r0)
            if (r0 == 0) goto L81
            io.github.jan.supabase.exceptions.UnauthorizedRestException r10 = new io.github.jan.supabase.exceptions.UnauthorizedRestException
            java.lang.String r11 = r2.getError()
            java.lang.String r0 = r2.getDescription()
            r10.<init>(r11, r5, r0)
            goto Lbe
        L81:
            io.ktor.http.HttpStatusCode r0 = r11.getBadRequest()
            boolean r0 = kotlin.jvm.internal.p.a(r10, r0)
            if (r0 == 0) goto L99
            io.github.jan.supabase.exceptions.BadRequestRestException r10 = new io.github.jan.supabase.exceptions.BadRequestRestException
            java.lang.String r11 = r2.getError()
            java.lang.String r0 = r2.getDescription()
            r10.<init>(r11, r5, r0)
            goto Lbe
        L99:
            io.ktor.http.HttpStatusCode r11 = r11.getUnprocessableEntity()
            boolean r10 = kotlin.jvm.internal.p.a(r10, r11)
            if (r10 == 0) goto Lb1
            io.github.jan.supabase.exceptions.BadRequestRestException r10 = new io.github.jan.supabase.exceptions.BadRequestRestException
            java.lang.String r11 = r2.getError()
            java.lang.String r0 = r2.getDescription()
            r10.<init>(r11, r5, r0)
            goto Lbe
        Lb1:
            io.github.jan.supabase.exceptions.UnknownRestException r3 = new io.github.jan.supabase.exceptions.UnknownRestException
            java.lang.String r4 = r2.getError()
            r7 = 4
            r8 = 0
            r6 = 0
            r3.<init>(r4, r5, r6, r7, r8)
            r10 = r3
        Lbe:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.parseErrorResponse(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object reauthenticate(d<? super t0> dVar) {
        Object objRequest = this.api.request("reauthenticate", new AuthImpl$reauthenticate$$inlined$get$default$1(), dVar);
        return objRequest == e7.a.f15033i ? objRequest : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0067, code lost:
    
        if (io.github.jan.supabase.gotrue.Auth.DefaultImpls.importSession$default(r0, (io.github.jan.supabase.gotrue.user.UserSession) r9, false, r4, 2, null) == r7) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object refreshCurrentSession(d7.d<? super x6.t0> r9) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16741
            if (r0 == 0) goto L14
            r0 = r9
            io.github.jan.supabase.gotrue.AuthImpl$refreshCurrentSession$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16741) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.github.jan.supabase.gotrue.AuthImpl$refreshCurrentSession$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$refreshCurrentSession$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            int r0 = r4.label
            r1 = 2
            r2 = 1
            e7.a r7 = e7.a.f15033i
            if (r0 == 0) goto L3c
            if (r0 == r2) goto L34
            if (r0 != r1) goto L2c
            k2.c.G(r9)
            goto L6a
        L2c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L34:
            java.lang.Object r0 = r4.L$0
            io.github.jan.supabase.gotrue.AuthImpl r0 = (io.github.jan.supabase.gotrue.AuthImpl) r0
            k2.c.G(r9)
            goto L57
        L3c:
            k2.c.G(r9)
            io.github.jan.supabase.gotrue.user.UserSession r9 = r8.currentSessionOrNull()
            if (r9 == 0) goto L6d
            java.lang.String r9 = r9.getRefreshToken()
            if (r9 == 0) goto L6d
            r4.L$0 = r8
            r4.label = r2
            java.lang.Object r9 = r8.refreshSession(r9, r4)
            if (r9 != r7) goto L56
            goto L69
        L56:
            r0 = r8
        L57:
            r2 = r9
            io.github.jan.supabase.gotrue.user.UserSession r2 = (io.github.jan.supabase.gotrue.user.UserSession) r2
            r9 = 0
            r4.L$0 = r9
            r4.label = r1
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r0
            java.lang.Object r9 = io.github.jan.supabase.gotrue.Auth.DefaultImpls.importSession$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r7) goto L6a
        L69:
            return r7
        L6a:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        L6d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "No refresh token found in current session"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.refreshCurrentSession(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
    
        if (r8 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object refreshSession(java.lang.String r7, d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r8) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.refreshSession(java.lang.String, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object resendEmail(OtpType.Email email, String str, String str2, d<? super t0> dVar) {
        Object objResend = resend(email.getType(), new C16772(str, str2), dVar);
        return objResend == e7.a.f15033i ? objResend : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object resendPhone(OtpType.Phone phone, String str, String str2, d<? super t0> dVar) {
        Object objResend = resend(phone.getType(), new C16782(str, str2), dVar);
        return objResend == e7.a.f15033i ? objResend : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e6, code lost:
    
        if (r9.request("recover", r1, r0) != r4) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object resetPasswordForEmail(java.lang.String r7, java.lang.String r8, java.lang.String r9, d7.d<? super x6.t0> r10) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.resetPasswordForEmail(java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public String resolveUrl(String str) {
        return Auth.DefaultImpls.resolveUrl(this, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x017b, code lost:
    
        if (r1 != r7) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x015b A[PHI: r1
      0x015b: PHI (r1v26 java.lang.Object) = (r1v22 java.lang.Object), (r1v1 java.lang.Object) binds: [B:57:0x0158, B:16:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveSSOUrl(java.lang.String r18, r7.l<? super io.github.jan.supabase.gotrue.providers.builtin.SSO.Config, x6.t0> r19, d7.d<? super io.github.jan.supabase.gotrue.providers.builtin.SSO.Result> r20) {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.retrieveSSOUrl(java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
    
        if (r7 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveUser(java.lang.String r6, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16811
            if (r0 == 0) goto L13
            r0 = r7
            io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            goto L58
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r7)
            goto L4c
        L36:
            k2.c.G(r7)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r7 = r5.api
            io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$$inlined$get$1 r1 = new io.github.jan.supabase.gotrue.AuthImpl$retrieveUser$$inlined$get$1
            r1.<init>(r6)
            r0.label = r3
            java.lang.String r6 = "user"
            java.lang.Object r7 = r7.request(r6, r1, r0)
            if (r7 != r4) goto L4c
            goto L57
        L4c:
            io.ktor.client.statement.HttpResponse r7 = (io.ktor.client.statement.HttpResponse) r7
            r0.label = r2
            r6 = 0
            java.lang.Object r7 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r7, r6, r0, r3, r6)
            if (r7 != r4) goto L58
        L57:
            return r4
        L58:
            java.lang.String r7 = (java.lang.String) r7
            db.b r6 = io.github.jan.supabase.UtilsKt.getSupabaseJson()
            r6.getClass()
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r0 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE
            ya.h r0 = r0.serializer()
            java.lang.Object r6 = r6.c(r7, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.retrieveUser(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveUserForCurrentSession(boolean r22, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r23
            boolean r2 = r1 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16821
            if (r2 == 0) goto L17
            r2 = r1
            io.github.jan.supabase.gotrue.AuthImpl$retrieveUserForCurrentSession$1 r2 = (io.github.jan.supabase.gotrue.AuthImpl.C16821) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            io.github.jan.supabase.gotrue.AuthImpl$retrieveUserForCurrentSession$1 r2 = new io.github.jan.supabase.gotrue.AuthImpl$retrieveUserForCurrentSession$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            int r3 = r2.label
            java.lang.String r4 = "No session found"
            r5 = 2
            r6 = 1
            e7.a r7 = e7.a.f15033i
            if (r3 == 0) goto L46
            if (r3 == r6) goto L3c
            if (r3 != r5) goto L34
            java.lang.Object r2 = r2.L$0
            io.github.jan.supabase.gotrue.user.UserInfo r2 = (io.github.jan.supabase.gotrue.user.UserInfo) r2
            k2.c.G(r1)
            return r2
        L34:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L3c:
            boolean r3 = r2.Z$0
            java.lang.Object r6 = r2.L$0
            io.github.jan.supabase.gotrue.AuthImpl r6 = (io.github.jan.supabase.gotrue.AuthImpl) r6
            k2.c.G(r1)
            goto L5f
        L46:
            k2.c.G(r1)
            java.lang.String r1 = r0.currentAccessTokenOrNull()
            if (r1 == 0) goto Laf
            r2.L$0 = r0
            r3 = r22
            r2.Z$0 = r3
            r2.label = r6
            java.lang.Object r1 = r0.retrieveUser(r1, r2)
            if (r1 != r7) goto L5e
            goto La5
        L5e:
            r6 = r0
        L5f:
            r16 = r1
            io.github.jan.supabase.gotrue.user.UserInfo r16 = (io.github.jan.supabase.gotrue.user.UserInfo) r16
            if (r3 == 0) goto Lac
            io.github.jan.supabase.gotrue.user.UserSession r8 = r6.currentSessionOrNull()
            if (r8 == 0) goto La6
            r19 = 447(0x1bf, float:6.26E-43)
            r20 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            r17 = 0
            r18 = 0
            io.github.jan.supabase.gotrue.user.UserSession r1 = io.github.jan.supabase.gotrue.user.UserSession.copy$default(r8, r9, r10, r11, r12, r13, r15, r16, r17, r18, r19, r20)
            r3 = r16
            io.github.jan.supabase.gotrue.user.UserSession r1 = io.github.jan.supabase.gotrue.SessionStatus.Authenticated.m6635constructorimpl(r1)
            na.q0<io.github.jan.supabase.gotrue.SessionStatus> r4 = r6._sessionStatus
            io.github.jan.supabase.gotrue.SessionStatus$Authenticated r8 = io.github.jan.supabase.gotrue.SessionStatus.Authenticated.m6634boximpl(r1)
            r4.setValue(r8)
            io.github.jan.supabase.gotrue.AuthConfig r4 = r6.getConfig()
            boolean r4 = r4.getAutoSaveToStorage()
            if (r4 == 0) goto Lae
            io.github.jan.supabase.gotrue.SessionManager r4 = r6.getSessionManager()
            r2.L$0 = r3
            r2.label = r5
            java.lang.Object r1 = r4.saveSession(r1, r2)
            if (r1 != r7) goto Lae
        La5:
            return r7
        La6:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r4)
            throw r1
        Lac:
            r3 = r16
        Lae:
            return r3
        Laf:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.retrieveUserForCurrentSession(boolean, d7.d):java.lang.Object");
    }

    public final void setSessionJob(v1 v1Var) {
        this.sessionJob = v1Var;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public <C, R, Provider extends AuthProvider<C, R>> Object signInWith(Provider provider, String str, l<? super C, t0> lVar, d<? super t0> dVar) {
        Object objLogin = provider.login(getSupabaseClient(), new C16832(null), str, lVar, dVar);
        return objLogin == e7.a.f15033i ? objLogin : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
    
        if (r1.clearSession(r0) == r5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.Auth
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object signOut(io.github.jan.supabase.gotrue.SignOutScope r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.github.jan.supabase.gotrue.AuthImpl.C16841
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.gotrue.AuthImpl$signOut$1 r0 = (io.github.jan.supabase.gotrue.AuthImpl.C16841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthImpl$signOut$1 r0 = new io.github.jan.supabase.gotrue.AuthImpl$signOut$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            java.lang.String r2 = "Auth"
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L40
            if (r1 == r4) goto L34
            if (r1 != r3) goto L2c
            k2.c.G(r8)
            goto L82
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            java.lang.Object r7 = r0.L$1
            io.github.jan.supabase.gotrue.SignOutScope r7 = (io.github.jan.supabase.gotrue.SignOutScope) r7
            java.lang.Object r1 = r0.L$0
            io.github.jan.supabase.gotrue.AuthImpl r1 = (io.github.jan.supabase.gotrue.AuthImpl) r1
            k2.c.G(r8)
            goto L60
        L40:
            k2.c.G(r8)
            io.github.jan.supabase.gotrue.user.UserSession r8 = r6.currentSessionOrNull()
            if (r8 == 0) goto L68
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r6.api
            io.github.jan.supabase.gotrue.AuthImpl$signOut$$inlined$post$1 r1 = new io.github.jan.supabase.gotrue.AuthImpl$signOut$$inlined$post$1
            r1.<init>(r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.String r4 = "logout"
            java.lang.Object r8 = r8.request(r4, r1, r0)
            if (r8 != r5) goto L5f
            goto L81
        L5f:
            r1 = r6
        L60:
            q2.f r8 = q2.f.f21327l
            io.github.jan.supabase.gotrue.AuthImpl$signOut$3 r4 = io.github.jan.supabase.gotrue.AuthImpl.C16853.INSTANCE
            q2.f.A0(r8, r2, r4)
            goto L70
        L68:
            q2.f r8 = q2.f.f21327l
            io.github.jan.supabase.gotrue.AuthImpl$signOut$4 r1 = io.github.jan.supabase.gotrue.AuthImpl.C16864.INSTANCE
            q2.f.D0(r8, r2, r1)
            r1 = r6
        L70:
            io.github.jan.supabase.gotrue.SignOutScope r8 = io.github.jan.supabase.gotrue.SignOutScope.OTHERS
            if (r7 == r8) goto L82
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r1.clearSession(r0)
            if (r7 != r5) goto L82
        L81:
            return r5
        L82:
            q2.f r7 = q2.f.f21327l
            io.github.jan.supabase.gotrue.AuthImpl$signOut$5 r8 = io.github.jan.supabase.gotrue.AuthImpl.AnonymousClass5.INSTANCE
            q2.f.A0(r7, r2, r8)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.signOut(io.github.jan.supabase.gotrue.SignOutScope, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public <C, R, Provider extends AuthProvider<C, R>> Object signUpWith(Provider provider, String str, l<? super C, t0> lVar, d<? super R> dVar) {
        return provider.signUp(getSupabaseClient(), new C16872(null), str, lVar, dVar);
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object startAutoRefreshForCurrentSession(d<? super t0> dVar) {
        UserSession userSessionCurrentSessionOrNull = currentSessionOrNull();
        if (userSessionCurrentSessionOrNull == null) {
            throw new IllegalStateException("No session found");
        }
        Object objImportSession = importSession(userSessionCurrentSessionOrNull, true, dVar);
        return objImportSession == e7.a.f15033i ? objImportSession : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public void stopAutoRefreshForCurrentSession() {
        v1 v1Var = this.sessionJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.sessionJob = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    @Override // io.github.jan.supabase.gotrue.Auth
    @io.github.jan.supabase.annotations.SupabaseExperimental
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object unlinkIdentity(java.lang.String r36, boolean r37, d7.d<? super x6.t0> r38) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthImpl.unlinkIdentity(java.lang.String, boolean, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object verifyEmailOtp(OtpType.Email email, String str, String str2, String str3, d<? super t0> dVar) {
        Object objVerify = verify(email.getType(), str2, str3, new C16912(str), dVar);
        return objVerify == e7.a.f15033i ? objVerify : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.Auth
    public Object verifyPhoneOtp(OtpType.Phone phone, String str, String str2, String str3, d<? super t0> dVar) {
        Object objVerify = verify(phone.getType(), str2, str3, new C16922(str), dVar);
        return objVerify == e7.a.f15033i ? objVerify : t0.f22605a;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public AuthConfig getConfig() {
        return this.config;
    }
}
