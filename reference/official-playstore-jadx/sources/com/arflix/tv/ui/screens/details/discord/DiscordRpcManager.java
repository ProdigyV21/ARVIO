package com.arflix.tv.ui.screens.details.discord;

import a0.c;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.ui.screens.details.discord.DiscordBridge;
import com.arflix.tv.ui.screens.details.discord.DiscordRpcManager;
import com.arflix.tv.util.Constants;
import com.discord.socialsdk.DiscordSocialSdkInit;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import d7.d;
import f7.e;
import f7.j;
import g7.b;
import io.ktor.http.LinkHeader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.s0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;
import pa.q;
import x6.c0;
import x6.d0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0099\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\b\r*\u0001y\bÇ\u0002\u0018\u00002\u00020\u0001:\n\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u0003J\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0003J\r\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u0010\u0003J?\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0006H\u0002¢\u0006\u0004\b!\u0010\u0003J\u001a\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010\"\u001a\u00020\u000bH\u0082@¢\u0006\u0004\b$\u0010%J\u001a\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010&\u001a\u00020\u000bH\u0082@¢\u0006\u0004\b(\u0010%J\u0017\u0010+\u001a\u00020*2\u0006\u0010)\u001a\u00020\u000bH\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u000bH\u0002¢\u0006\u0004\b.\u0010\u000eJ\u0018\u0010/\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u000bH\u0082@¢\u0006\u0004\b/\u0010%J\u001a\u00100\u001a\u0004\u0018\u00010\u000b2\u0006\u0010-\u001a\u00020\u000bH\u0082@¢\u0006\u0004\b0\u0010%J\u0017\u00101\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0014H\u0002¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u0006H\u0002¢\u0006\u0004\b3\u0010\u0003J\u000f\u00104\u001a\u00020\u0006H\u0002¢\u0006\u0004\b4\u0010\u0003J\u0012\u00105\u001a\u0004\u0018\u00010\u000bH\u0082@¢\u0006\u0004\b5\u00106J\u0012\u00107\u001a\u0004\u0018\u00010\u000bH\u0082@¢\u0006\u0004\b7\u00106J\"\u0010:\u001a\u0004\u0018\u0001092\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000bH\u0082@¢\u0006\u0004\b:\u0010;J&\u0010>\u001a\u0004\u0018\u0001092\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0<H\u0082@¢\u0006\u0004\b>\u0010?J\u0017\u0010A\u001a\u00020\u00062\u0006\u0010@\u001a\u000209H\u0002¢\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0006H\u0002¢\u0006\u0004\bC\u0010\u0003R\u0014\u0010D\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bF\u0010ER\u0014\u0010G\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bG\u0010ER\u0014\u0010H\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bH\u0010ER\u0014\u0010I\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bI\u0010ER\u0014\u0010J\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010ER\u0014\u0010K\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bK\u0010ER\u0014\u0010L\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bL\u0010ER\u0014\u0010M\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010V\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010X\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010WR\u0018\u0010Y\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010WR\u0018\u0010Z\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010WR\u0018\u0010[\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010WR\u0018\u0010\\\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010WR\u0016\u0010]\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010^R\u0016\u0010a\u001a\u00020`8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010c\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010ER\u0018\u0010d\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010ER\u0016\u0010e\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010NR\u0016\u0010f\u001a\u00020\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bf\u0010gR\u001a\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00140h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010jR\u001d\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00140k8\u0006¢\u0006\f\n\u0004\bl\u0010m\u001a\u0004\bl\u0010nR\u001c\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bo\u0010jR\u001f\u0010p\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0k8\u0006¢\u0006\f\n\u0004\bp\u0010m\u001a\u0004\bq\u0010nR\u001c\u0010r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\br\u0010jR\u001f\u0010s\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0k8\u0006¢\u0006\f\n\u0004\bs\u0010m\u001a\u0004\bt\u0010nR\u001a\u0010u\u001a\b\u0012\u0004\u0012\u00020\u00140h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010jR\u001d\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00140k8\u0006¢\u0006\f\n\u0004\bv\u0010m\u001a\u0004\bv\u0010nR\u001a\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00140h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010jR\u001d\u0010x\u001a\b\u0012\u0004\u0012\u00020\u00140k8\u0006¢\u0006\f\n\u0004\bx\u0010m\u001a\u0004\bx\u0010nR\u0014\u0010z\u001a\u00020y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bz\u0010{R\u0011\u0010|\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b|\u0010\u0016R\u0014\u0010\u007f\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b}\u0010~¨\u0006\u0085\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lx6/t0;", "init", "(Landroid/content/Context;)V", "login", "closeAuthDialog", "", "code", "completeAuthWithCode", "(Ljava/lang/String;)V", "Landroid/net/Uri;", "uri", "onLoginDeepLink", "(Landroid/net/Uri;)V", "disconnect", "", "isLoggedIn", "()Z", "logout", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "isPlaying", "", "progressMs", "durationMs", "largeImage", "updatePlayback", "(Ljava/lang/String;Ljava/lang/String;ZJJLjava/lang/String;)V", "openAuthDialog", "challenge", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingSession;", "startCloudSession", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "deviceCode", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingStatus;", "pollCloudStatus", "url", "Ljava/net/HttpURLConnection;", "appConnection", "(Ljava/lang/String;)Ljava/net/HttpURLConnection;", "token", "connectInternal", "updateUsername", "fetchUserProfile", "handlePauseTimeout", "(Z)V", "startTickLoop", "stopTickLoop", "ensureValidAccessToken", "(Ld7/d;)Ljava/lang/Object;", "refreshCurrentToken", "verifier", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$OAuthTokens;", "exchangeCodeForToken", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "parameters", "requestTokens", "(Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "tokens", "saveTokens", "(Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$OAuthTokens;)V", "scheduleTokenRefresh", "TAG", "Ljava/lang/String;", "PREFS_NAME", "KEY_ACCESS_TOKEN", "KEY_REFRESH_TOKEN", "KEY_ACCESS_TOKEN_EXPIRES_AT", "KEY_CODE_VERIFIER", "KEY_USERNAME", "REDIRECT_URI_WEB", "TOKEN_REFRESH_MARGIN_MS", "J", "Lka/k0;", "coroutineScope", "Lka/k0;", "Lua/a;", "tokenMutex", "Lua/a;", "Lka/v1;", "tickJob", "Lka/v1;", "lastUpdateJob", "reconnectJob", "tokenRefreshJob", "disconnectTimeoutJob", "authPollingJob", "initialized", "Z", "bridgeReady", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$ConnectionState;", "connectionState", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$ConnectionState;", "currentAccessToken", "currentRefreshToken", "accessTokenExpiresAt", "appContext", "Landroid/content/Context;", "Lna/q0;", "_isLoggedIn", "Lna/q0;", "Lna/h1;", "isLoggedInFlow", "Lna/h1;", "()Lna/h1;", "_username", "usernameFlow", "getUsernameFlow", "_authUrl", "authUrlFlow", "getAuthUrlFlow", "_isAuthDialogVisible", "isAuthDialogVisibleFlow", "_isAuthLoading", "isAuthLoadingFlow", "com/arflix/tv/ui/screens/details/discord/DiscordRpcManager$jniCallback$1", "jniCallback", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$jniCallback$1;", "isSupported", "getDiscordClientId", "()Ljava/lang/String;", "discordClientId", "ConnectionState", "PairingSession", "PairingStatus", "OAuthTokens", "PkceUtil", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DiscordRpcManager {
    public static final int $stable;
    public static final DiscordRpcManager INSTANCE = new DiscordRpcManager();
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_ACCESS_TOKEN_EXPIRES_AT = "access_token_expires_at";
    private static final String KEY_CODE_VERIFIER = "code_verifier";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_USERNAME = "username";
    private static final String PREFS_NAME = "discord_rpc_prefs";
    private static final String REDIRECT_URI_WEB = "https://auth.arvio.tv/discord/callback";
    private static final String TAG = "DiscordRpcManager";
    private static final long TOKEN_REFRESH_MARGIN_MS = 60000;
    private static final q0<String> _authUrl;
    private static final q0<Boolean> _isAuthDialogVisible;
    private static final q0<Boolean> _isAuthLoading;
    private static final q0<Boolean> _isLoggedIn;
    private static final q0<String> _username;
    private static volatile long accessTokenExpiresAt;
    private static Context appContext;
    private static v1 authPollingJob;
    private static final h1<String> authUrlFlow;
    private static volatile boolean bridgeReady;
    private static volatile ConnectionState connectionState;
    private static final k0 coroutineScope;
    private static volatile String currentAccessToken;
    private static volatile String currentRefreshToken;
    private static v1 disconnectTimeoutJob;
    private static boolean initialized;
    private static final h1<Boolean> isAuthDialogVisibleFlow;
    private static final h1<Boolean> isAuthLoadingFlow;
    private static final h1<Boolean> isLoggedInFlow;
    private static final DiscordRpcManager$jniCallback$1 jniCallback;
    private static v1 lastUpdateJob;
    private static v1 reconnectJob;
    private static v1 tickJob;
    private static final ua.a tokenMutex;
    private static v1 tokenRefreshJob;
    private static final h1<String> usernameFlow;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$ConnectionState;", "", "<init>", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTING", "CONNECTED", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ConnectionState {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ ConnectionState[] $VALUES;
        public static final ConnectionState DISCONNECTED = new ConnectionState("DISCONNECTED", 0);
        public static final ConnectionState CONNECTING = new ConnectionState("CONNECTING", 1);
        public static final ConnectionState CONNECTED = new ConnectionState("CONNECTED", 2);

        private static final /* synthetic */ ConnectionState[] $values() {
            return new ConnectionState[]{DISCONNECTED, CONNECTING, CONNECTED};
        }

        static {
            ConnectionState[] connectionStateArr$values = $values();
            $VALUES = connectionStateArr$values;
            $ENTRIES = new b(connectionStateArr$values);
        }

        private ConnectionState(String str, int i10) {
        }

        public static g7.a<ConnectionState> getEntries() {
            return $ENTRIES;
        }

        public static ConnectionState valueOf(String str) {
            return (ConnectionState) Enum.valueOf(ConnectionState.class, str);
        }

        public static ConnectionState[] values() {
            return (ConnectionState[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$OAuthTokens;", "", "accessToken", "", "refreshToken", "expiresAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "getExpiresAt", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class OAuthTokens {
        private final String accessToken;
        private final long expiresAt;
        private final String refreshToken;

        public OAuthTokens(String str, String str2, long j10) {
            this.accessToken = str;
            this.refreshToken = str2;
            this.expiresAt = j10;
        }

        public static /* synthetic */ OAuthTokens copy$default(OAuthTokens oAuthTokens, String str, String str2, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = oAuthTokens.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = oAuthTokens.refreshToken;
            }
            if ((i10 & 4) != 0) {
                j10 = oAuthTokens.expiresAt;
            }
            return oAuthTokens.copy(str, str2, j10);
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
        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final OAuthTokens copy(String accessToken, String refreshToken, long expiresAt) {
            return new OAuthTokens(accessToken, refreshToken, expiresAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OAuthTokens)) {
                return false;
            }
            OAuthTokens oAuthTokens = (OAuthTokens) other;
            return p.a(this.accessToken, oAuthTokens.accessToken) && p.a(this.refreshToken, oAuthTokens.refreshToken) && this.expiresAt == oAuthTokens.expiresAt;
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public int hashCode() {
            int iHashCode = this.accessToken.hashCode() * 31;
            String str = this.refreshToken;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            long j10 = this.expiresAt;
            return iHashCode2 + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return c.j(this.expiresAt, ")", a2.r("OAuthTokens(accessToken=", this.accessToken, ", refreshToken=", this.refreshToken, ", expiresAt="));
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingSession;", "", "deviceCode", "", "verificationUrl", "intervalSeconds", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getDeviceCode", "()Ljava/lang/String;", "getVerificationUrl", "getIntervalSeconds", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PairingSession {
        private final String deviceCode;
        private final long intervalSeconds;
        private final String verificationUrl;

        public PairingSession(String str, String str2, long j10) {
            this.deviceCode = str;
            this.verificationUrl = str2;
            this.intervalSeconds = j10;
        }

        public static /* synthetic */ PairingSession copy$default(PairingSession pairingSession, String str, String str2, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = pairingSession.deviceCode;
            }
            if ((i10 & 2) != 0) {
                str2 = pairingSession.verificationUrl;
            }
            if ((i10 & 4) != 0) {
                j10 = pairingSession.intervalSeconds;
            }
            return pairingSession.copy(str, str2, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDeviceCode() {
            return this.deviceCode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVerificationUrl() {
            return this.verificationUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getIntervalSeconds() {
            return this.intervalSeconds;
        }

        public final PairingSession copy(String deviceCode, String verificationUrl, long intervalSeconds) {
            return new PairingSession(deviceCode, verificationUrl, intervalSeconds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PairingSession)) {
                return false;
            }
            PairingSession pairingSession = (PairingSession) other;
            return p.a(this.deviceCode, pairingSession.deviceCode) && p.a(this.verificationUrl, pairingSession.verificationUrl) && this.intervalSeconds == pairingSession.intervalSeconds;
        }

        public final String getDeviceCode() {
            return this.deviceCode;
        }

        public final long getIntervalSeconds() {
            return this.intervalSeconds;
        }

        public final String getVerificationUrl() {
            return this.verificationUrl;
        }

        public int hashCode() {
            int iC = androidx.compose.foundation.c.c(this.deviceCode.hashCode() * 31, 31, this.verificationUrl);
            long j10 = this.intervalSeconds;
            return iC + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return c.j(this.intervalSeconds, ")", a2.r("PairingSession(deviceCode=", this.deviceCode, ", verificationUrl=", this.verificationUrl, ", intervalSeconds="));
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PkceUtil;", "", "<init>", "()V", "generateCodeVerifier", "", "generateCodeChallenge", "verifier", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class PkceUtil {
        public static final PkceUtil INSTANCE = new PkceUtil();

        private PkceUtil() {
        }

        public final String generateCodeChallenge(String verifier) {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(verifier.getBytes(kotlin.text.a.f19925b)), 11);
        }

        public final String generateCodeVerifier() {
            byte[] bArr = new byte[64];
            new SecureRandom().nextBytes(bArr);
            return Base64.encodeToString(bArr, 11);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$completeAuthWithCode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$completeAuthWithCode$1", f = "DiscordRpcManager.kt", l = {248, 258}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ String $code;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$code = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$code, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            SharedPreferences sharedPreferences;
            OAuthTokens oAuthTokens;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                Context context = DiscordRpcManager.appContext;
                if (context == null) {
                    p.i("appContext");
                    throw null;
                }
                sharedPreferences = context.getSharedPreferences(DiscordRpcManager.PREFS_NAME, 0);
                String string = sharedPreferences.getString(DiscordRpcManager.KEY_CODE_VERIFIER, null);
                if (string == null || o.h0(string)) {
                    Log.e(DiscordRpcManager.TAG, "No PKCE verifier found for Discord token exchange.");
                    return t0Var;
                }
                DiscordRpcManager._isAuthLoading.setValue(Boolean.TRUE);
                DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                String str = this.$code;
                this.L$0 = sharedPreferences;
                this.L$1 = null;
                this.label = 1;
                obj = discordRpcManager.exchangeCodeForToken(str, string, this);
                if (obj != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                oAuthTokens = (OAuthTokens) this.L$2;
                k2.c.G(obj);
                DiscordRpcManager discordRpcManager2 = DiscordRpcManager.INSTANCE;
                discordRpcManager2.connectInternal(oAuthTokens.getAccessToken());
                discordRpcManager2.closeAuthDialog();
                return t0Var;
            }
            sharedPreferences = (SharedPreferences) this.L$0;
            k2.c.G(obj);
            OAuthTokens oAuthTokens2 = (OAuthTokens) obj;
            if (oAuthTokens2 == null) {
                Log.e(DiscordRpcManager.TAG, "Discord token exchange failed.");
                DiscordRpcManager._isAuthLoading.setValue(Boolean.FALSE);
                return t0Var;
            }
            DiscordRpcManager discordRpcManager3 = DiscordRpcManager.INSTANCE;
            discordRpcManager3.saveTokens(oAuthTokens2);
            sharedPreferences.edit().remove(DiscordRpcManager.KEY_CODE_VERIFIER).apply();
            DiscordRpcManager._isLoggedIn.setValue(Boolean.TRUE);
            String accessToken = oAuthTokens2.getAccessToken();
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = oAuthTokens2;
            this.label = 2;
            if (discordRpcManager3.updateUsername(accessToken, this) != aVar) {
                oAuthTokens = oAuthTokens2;
                DiscordRpcManager discordRpcManager22 = DiscordRpcManager.INSTANCE;
                discordRpcManager22.connectInternal(oAuthTokens.getAccessToken());
                discordRpcManager22.closeAuthDialog();
                return t0Var;
            }
            return aVar;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$connectInternal$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$connectInternal$1", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C13351 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ String $token;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13351(String str, d<? super C13351> dVar) {
            super(2, dVar);
            this.$token = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C13351(this.$token, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (!DiscordBridge.INSTANCE.connect(this.$token)) {
                DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                DiscordRpcManager.connectionState = ConnectionState.DISCONNECTED;
                DiscordRpcManager.INSTANCE.stopTickLoop();
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13351) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$fetchUserProfile$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$fetchUserProfile$2", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements r7.p<k0, d<? super String>, Object> {
        final /* synthetic */ String $token;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$token = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$token, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
        /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.CharSequence, java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v15 */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v2, types: [x6.c0] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.String r0 = "Bearer "
                java.lang.Object r1 = r5.L$0
                ka.k0 r1 = (ka.k0) r1
                int r1 = r5.label
                if (r1 != 0) goto La4
                k2.c.G(r6)
                java.lang.String r6 = r5.$token
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L82
                java.lang.String r3 = "https://discord.com/api/v10/users/@me"
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L82
                java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L82
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L82
                java.lang.String r3 = "GET"
                r2.setRequestMethod(r3)     // Catch: java.lang.Throwable -> L82
                java.lang.String r3 = "Authorization"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82
                r4.<init>(r0)     // Catch: java.lang.Throwable -> L82
                r4.append(r6)     // Catch: java.lang.Throwable -> L82
                java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L82
                r2.setRequestProperty(r3, r6)     // Catch: java.lang.Throwable -> L82
                r6 = 5000(0x1388, float:7.006E-42)
                r2.setConnectTimeout(r6)     // Catch: java.lang.Throwable -> L82
                r2.setReadTimeout(r6)     // Catch: java.lang.Throwable -> L82
                int r6 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L82
                r0 = 200(0xc8, float:2.8E-43)
                if (r6 == r0) goto L44
                goto L80
            L44:
                java.io.InputStream r6 = r2.getInputStream()     // Catch: java.lang.Throwable -> L82
                java.nio.charset.Charset r0 = kotlin.text.a.f19924a     // Catch: java.lang.Throwable -> L82
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L82
                r2.<init>(r6, r0)     // Catch: java.lang.Throwable -> L82
                java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L82
                r0 = 8192(0x2000, float:1.148E-41)
                r6.<init>(r2, r0)     // Catch: java.lang.Throwable -> L82
                java.lang.String r0 = ac.b.U(r6)     // Catch: java.lang.Throwable -> L84
                r6.close()     // Catch: java.lang.Throwable -> L82
                org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L82
                r6.<init>(r0)     // Catch: java.lang.Throwable -> L82
                java.lang.String r0 = "global_name"
                java.lang.String r0 = r6.optString(r0)     // Catch: java.lang.Throwable -> L82
                boolean r2 = kotlin.text.o.h0(r0)     // Catch: java.lang.Throwable -> L82
                if (r2 != 0) goto L6f
                goto L70
            L6f:
                r0 = r1
            L70:
                if (r0 != 0) goto L90
                java.lang.String r0 = "username"
                java.lang.String r0 = r6.optString(r0)     // Catch: java.lang.Throwable -> L82
                boolean r6 = kotlin.text.o.h0(r0)     // Catch: java.lang.Throwable -> L82
                if (r6 != 0) goto L80
                goto L90
            L80:
                r0 = r1
                goto L90
            L82:
                r6 = move-exception
                goto L8b
            L84:
                r0 = move-exception
                throw r0     // Catch: java.lang.Throwable -> L86
            L86:
                r2 = move-exception
                xc.d.L(r6, r0)     // Catch: java.lang.Throwable -> L82
                throw r2     // Catch: java.lang.Throwable -> L82
            L8b:
                x6.c0 r0 = new x6.c0
                r0.<init>(r6)
            L90:
                java.lang.Throwable r6 = x6.d0.a(r0)
                if (r6 == 0) goto L9d
                java.lang.String r2 = "DiscordRpcManager"
                java.lang.String r3 = "Could not load Discord profile"
                android.util.Log.w(r2, r3, r6)
            L9d:
                boolean r6 = r0 instanceof x6.c0
                if (r6 == 0) goto La2
                goto La3
            La2:
                r1 = r0
            La3:
                return r1
            La4:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super String> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$handlePauseTimeout$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$handlePauseTimeout$1", f = "DiscordRpcManager.kt", l = {434}, m = "invokeSuspend", v = 2)
    public static final class C13361 extends j implements r7.p<k0, d<? super t0>, Object> {
        int label;

        public C13361(d<? super C13361> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C13361(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(60000L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            DiscordRpcManager.INSTANCE.disconnect();
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13361) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$init$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$init$3", f = "DiscordRpcManager.kt", l = {157, 164}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends j implements r7.p<k0, d<? super t0>, Object> {
        Object L$0;
        int label;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass3(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                this.label = 1;
                obj = discordRpcManager.ensureValidAccessToken(this);
                if (obj != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return t0Var;
            }
            k2.c.G(obj);
            String str = (String) obj;
            if (str == null) {
                DiscordRpcManager.INSTANCE.logout();
                return t0Var;
            }
            DiscordRpcManager._isLoggedIn.setValue(Boolean.TRUE);
            DiscordRpcManager discordRpcManager2 = DiscordRpcManager.INSTANCE;
            discordRpcManager2.connectInternal(str);
            if (DiscordRpcManager._username.getValue() == null) {
                this.L$0 = null;
                this.label = 2;
                if (discordRpcManager2.updateUsername(str, this) == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1", f = "DiscordRpcManager.kt", l = {187}, m = "invokeSuspend", v = 2)
    public static final class C13371 extends j implements r7.p<k0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1", f = "DiscordRpcManager.kt", l = {201, 202, 208, 213}, m = "invokeSuspend", v = 2)
        public static final class C01411 extends j implements r7.p<k0, d<? super t0>, Object> {
            final /* synthetic */ PairingSession $session;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
            @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1$1", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
            public static final class C01421 extends j implements r7.p<k0, d<? super t0>, Object> {
                final /* synthetic */ String $code;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01421(String str, d<? super C01421> dVar) {
                    super(2, dVar);
                    this.$code = str;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C01421(this.$code, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    DiscordRpcManager.INSTANCE.completeAuthWithCode(this.$code);
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                    return ((C01421) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
            @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$openAuthDialog$1$1$2", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
            public static final class AnonymousClass2 extends j implements r7.p<k0, d<? super t0>, Object> {
                int label;

                public AnonymousClass2(d<? super AnonymousClass2> dVar) {
                    super(2, dVar);
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new AnonymousClass2(dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    DiscordRpcManager.INSTANCE.closeAuthDialog();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                    return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01411(PairingSession pairingSession, d<? super C01411> dVar) {
                super(2, dVar);
                this.$session = pairingSession;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C01411 c01411 = new C01411(this.$session, dVar);
                c01411.L$0 = obj;
                return c01411;
            }

            /* JADX WARN: Path cross not found for [B:15:0x0041, B:27:0x0082], limit reached: 40 */
            /* JADX WARN: Path cross not found for [B:27:0x0082, B:15:0x0041], limit reached: 40 */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x007e A[PHI: r13
              0x007e: PHI (r13v10 java.lang.Object) = (r13v2 java.lang.Object), (r13v0 java.lang.Object) binds: [B:23:0x007b, B:12:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x007b -> B:25:0x007e). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 212
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13371.C01411.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C01411) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public C13371(d<? super C13371> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C13371 c13371 = new C13371(dVar);
            c13371.L$0 = obj;
            return c13371;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PkceUtil pkceUtil = PkceUtil.INSTANCE;
                String strGenerateCodeVerifier = pkceUtil.generateCodeVerifier();
                Context context = DiscordRpcManager.appContext;
                if (context == null) {
                    p.i("appContext");
                    throw null;
                }
                context.getSharedPreferences(DiscordRpcManager.PREFS_NAME, 0).edit().putString(DiscordRpcManager.KEY_CODE_VERIFIER, strGenerateCodeVerifier).apply();
                String strGenerateCodeChallenge = pkceUtil.generateCodeChallenge(strGenerateCodeVerifier);
                DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                this.L$0 = k0Var;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 1;
                obj = discordRpcManager.startCloudSession(strGenerateCodeChallenge, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            PairingSession pairingSession = (PairingSession) obj;
            t0 t0Var = t0.f22605a;
            if (pairingSession != null) {
                DiscordRpcManager._authUrl.setValue(pairingSession.getVerificationUrl());
                DiscordRpcManager._isAuthDialogVisible.setValue(Boolean.TRUE);
                DiscordRpcManager.authPollingJob = m0.p(k0Var, x0.f19655d, 0, new C01411(pairingSession, null), 2);
                return t0Var;
            }
            Log.e(DiscordRpcManager.TAG, "Could not start Discord pairing session.");
            Context context2 = DiscordRpcManager.appContext;
            if (context2 != null) {
                context2.getSharedPreferences(DiscordRpcManager.PREFS_NAME, 0).edit().remove(DiscordRpcManager.KEY_CODE_VERIFIER).apply();
                return t0Var;
            }
            p.i("appContext");
            throw null;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13371) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$pollCloudStatus$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingStatus;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingStatus;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$pollCloudStatus$2", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C13382 extends j implements r7.p<k0, d<? super PairingStatus>, Object> {
        final /* synthetic */ String $deviceCode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13382(String str, d<? super C13382> dVar) {
            super(2, dVar);
            this.$deviceCode = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C13382 c13382 = new C13382(this.$deviceCode, dVar);
            c13382.L$0 = obj;
            return c13382;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = this.$deviceCode;
            try {
                HttpURLConnection httpURLConnectionAppConnection = DiscordRpcManager.INSTANCE.appConnection(Constants.INSTANCE.getDISCORD_AUTH_STATUS_URL());
                String string = new JSONObject().put("device_code", str).toString();
                OutputStream outputStream = httpURLConnectionAppConnection.getOutputStream();
                try {
                    Charset charset = kotlin.text.a.f19924a;
                    outputStream.write(string.getBytes(charset));
                    outputStream.close();
                    int responseCode = httpURLConnectionAppConnection.getResponseCode();
                    if (200 > responseCode || responseCode >= 300) {
                        c0Var = null;
                    } else {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnectionAppConnection.getInputStream(), charset), 8192);
                        try {
                            String strU = ac.b.U(bufferedReader);
                            bufferedReader.close();
                            JSONObject jSONObject = new JSONObject(strU);
                            String strOptString = jSONObject.optString("status", "pending");
                            String strOptString2 = jSONObject.optString("code");
                            if (o.h0(strOptString2)) {
                                strOptString2 = null;
                            }
                            c0Var = new PairingStatus(strOptString, strOptString2);
                        } finally {
                        }
                    }
                } finally {
                }
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            Throwable thA = d0.a(c0Var);
            if (thA != null) {
                Log.w(DiscordRpcManager.TAG, "Could not poll Discord pairing", thA);
            }
            if (c0Var instanceof c0) {
                return null;
            }
            return c0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super PairingStatus> dVar) {
            return ((C13382) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$refreshCurrentToken$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager", f = "DiscordRpcManager.kt", l = {570, 469}, m = "refreshCurrentToken", v = 2)
    public static final class C13391 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C13391(d<? super C13391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DiscordRpcManager.this.refreshCurrentToken(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$requestTokens$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$OAuthTokens;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$OAuthTokens;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$requestTokens$2", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C13402 extends j implements r7.p<k0, d<? super OAuthTokens>, Object> {
        final /* synthetic */ Map<String, String> $parameters;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13402(Map<String, String> map, d<? super C13402> dVar) {
            super(2, dVar);
            this.$parameters = map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$0$0(Map.Entry entry) {
            return androidx.compose.foundation.c.t(URLEncoder.encode((String) entry.getKey(), "UTF-8"), "=", URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C13402 c13402 = new C13402(this.$parameters, dVar);
            c13402.L$0 = obj;
            return c13402;
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0122  */
        /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13402.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super OAuthTokens> dVar) {
            return ((C13402) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$scheduleTokenRefresh$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$scheduleTokenRefresh$1", f = "DiscordRpcManager.kt", l = {540, 541}, m = "invokeSuspend", v = 2)
    public static final class C13411 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ long $delayMs;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13411(long j10, d<? super C13411> dVar) {
            super(2, dVar);
            this.$delayMs = j10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C13411(this.$delayMs, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
        
            if (r7 == r3) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
            /*
                r6 = this;
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1c
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r7)
                goto L35
            L10:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L18:
                k2.c.G(r7)
                goto L2a
            L1c:
                k2.c.G(r7)
                long r4 = r6.$delayMs
                r6.label = r2
                java.lang.Object r7 = ka.s0.a(r4, r6)
                if (r7 != r3) goto L2a
                goto L34
            L2a:
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager r7 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.INSTANCE
                r6.label = r1
                java.lang.Object r7 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.access$refreshCurrentToken(r7, r6)
                if (r7 != r3) goto L35
            L34:
                return r3
            L35:
                java.lang.String r7 = (java.lang.String) r7
                if (r7 != 0) goto L3f
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager r7 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.INSTANCE
                r7.logout()
                goto L4c
            L3f:
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$ConnectionState r0 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.access$getConnectionState$p()
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$ConnectionState r1 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.ConnectionState.DISCONNECTED
                if (r0 == r1) goto L4c
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager r0 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.INSTANCE
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.access$connectInternal(r0, r7)
            L4c:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13411.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13411) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$startCloudSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingSession;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingSession;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$startCloudSession$2", f = "DiscordRpcManager.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C13422 extends j implements r7.p<k0, d<? super PairingSession>, Object> {
        final /* synthetic */ String $challenge;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13422(String str, d<? super C13422> dVar) {
            super(2, dVar);
            this.$challenge = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C13422 c13422 = new C13422(this.$challenge, dVar);
            c13422.L$0 = obj;
            return c13422;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x009e  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 209
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13422.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super PairingSession> dVar) {
            return ((C13422) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$startTickLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$startTickLoop$1", f = "DiscordRpcManager.kt", l = {445}, m = "invokeSuspend", v = 2)
    public static final class C13431 extends j implements r7.p<k0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C13431(d<? super C13431> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C13431 c13431 = new C13431(dVar);
            c13431.L$0 = obj;
            return c13431;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 != 0 && i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            while (l0.d(k0Var)) {
                DiscordBridge.INSTANCE.tick();
                this.L$0 = k0Var;
                this.label = 1;
                Object objA = s0.a(500L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13431) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updatePlayback$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updatePlayback$1", f = "DiscordRpcManager.kt", l = {405}, m = "invokeSuspend", v = 2)
    public static final class C13441 extends j implements r7.p<k0, d<? super t0>, Object> {
        int label;

        public C13441(d<? super C13441> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C13441(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                this.label = 1;
                obj = discordRpcManager.ensureValidAccessToken(this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            String str = (String) obj;
            if (str != null) {
                DiscordRpcManager.INSTANCE.connectInternal(str);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13441) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updatePlayback$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updatePlayback$2", f = "DiscordRpcManager.kt", l = {MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER}, m = "invokeSuspend", v = 2)
    public static final class C13452 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ boolean $isPlaying;
        final /* synthetic */ String $largeImage;
        final /* synthetic */ long $progressMs;
        final /* synthetic */ String $subtitle;
        final /* synthetic */ String $title;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13452(boolean z, long j10, String str, String str2, String str3, d<? super C13452> dVar) {
            super(2, dVar);
            this.$isPlaying = z;
            this.$progressMs = j10;
            this.$title = str;
            this.$subtitle = str2;
            this.$largeImage = str3;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C13452(this.$isPlaying, this.$progressMs, this.$title, this.$subtitle, this.$largeImage, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(350L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ConnectionState connectionState = DiscordRpcManager.connectionState;
            ConnectionState connectionState2 = ConnectionState.CONNECTED;
            t0 t0Var = t0.f22605a;
            if (connectionState != connectionState2) {
                return t0Var;
            }
            if (!this.$isPlaying) {
                DiscordBridge.INSTANCE.clearActivity();
                return t0Var;
            }
            long jCurrentTimeMillis = this.$progressMs >= 0 ? System.currentTimeMillis() - this.$progressMs : 0L;
            DiscordBridge discordBridge = DiscordBridge.INSTANCE;
            String str = this.$title;
            String str2 = this.$subtitle;
            if (o.h0(str2)) {
                str2 = null;
            }
            discordBridge.updateActivity(str, str2, jCurrentTimeMillis / ((long) 1000), 0L, this.$largeImage, BuildConfig.GITHUB_REPO);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C13452) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updateUsername$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager", f = "DiscordRpcManager.kt", l = {367}, m = "updateUsername", v = 2)
    public static final class C13461 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C13461(d<? super C13461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DiscordRpcManager.this.updateUsername(null, this);
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$jniCallback$1] */
    static {
        x0 x0Var = x0.f19652a;
        coroutineScope = l0.a(q.f21294a.plus(m0.d()));
        tokenMutex = ua.e.a();
        connectionState = ConnectionState.DISCONNECTED;
        Boolean bool = Boolean.FALSE;
        j1 j1VarB = y0.b(bool);
        _isLoggedIn = j1VarB;
        isLoggedInFlow = y0.e(j1VarB);
        j1 j1VarB2 = y0.b(null);
        _username = j1VarB2;
        usernameFlow = y0.e(j1VarB2);
        j1 j1VarB3 = y0.b(null);
        _authUrl = j1VarB3;
        authUrlFlow = y0.e(j1VarB3);
        j1 j1VarB4 = y0.b(bool);
        _isAuthDialogVisible = j1VarB4;
        isAuthDialogVisibleFlow = y0.e(j1VarB4);
        j1 j1VarB5 = y0.b(bool);
        _isAuthLoading = j1VarB5;
        isAuthLoadingFlow = y0.e(j1VarB5);
        jniCallback = new DiscordBridge.Callback() { // from class: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$jniCallback$1
            @Override // com.arflix.tv.ui.screens.details.discord.DiscordBridge.Callback
            public void onStatusChanged(int status, int error, int errorDetail) {
                StringBuilder sbV = androidx.compose.foundation.c.v("Discord status changed: status=", status, ", error=", error, ", detail=");
                sbV.append(errorDetail);
                Log.i("DiscordRpcManager", sbV.toString());
                if (status != 0) {
                    if (status != 1) {
                        return;
                    }
                    DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
                    DiscordRpcManager.connectionState = DiscordRpcManager.ConnectionState.CONNECTED;
                    DiscordRpcManager.INSTANCE.startTickLoop();
                    return;
                }
                DiscordRpcManager discordRpcManager2 = DiscordRpcManager.INSTANCE;
                DiscordRpcManager.connectionState = DiscordRpcManager.ConnectionState.DISCONNECTED;
                DiscordRpcManager.INSTANCE.stopTickLoop();
                if (errorDetail == 4004) {
                    m0.p(DiscordRpcManager.coroutineScope, null, 0, new DiscordRpcManager$jniCallback$1$onStatusChanged$1(null), 3);
                }
            }
        };
        $stable = 8;
    }

    private DiscordRpcManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HttpURLConnection appConnection(String url) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
        httpURLConnection.setRequestProperty(HttpConnection.CONTENT_TYPE, "application/json; charset=utf-8");
        Constants constants = Constants.INSTANCE;
        httpURLConnection.setRequestProperty("apikey", constants.getAPP_ANON_KEY());
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + constants.getAPP_ANON_KEY());
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        return httpURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectInternal(String token) {
        if (!isSupported() || o.h0(token)) {
            return;
        }
        connectionState = ConnectionState.CONNECTING;
        startTickLoop();
        m0.p(coroutineScope, x0.f19655d, 0, new C13351(token, null), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object ensureValidAccessToken(d<? super String> dVar) {
        String str = currentAccessToken;
        return (str == null || o.h0(str) || accessTokenExpiresAt <= System.currentTimeMillis() + 60000) ? refreshCurrentToken(dVar) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object exchangeCodeForToken(String str, String str2, d<? super OAuthTokens> dVar) {
        return requestTokens(h0.t0(new x("client_id", getDiscordClientId()), new x("grant_type", "authorization_code"), new x("code", str), new x("redirect_uri", REDIRECT_URI_WEB), new x(KEY_CODE_VERIFIER, str2)), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchUserProfile(String str, d<? super String> dVar) {
        return m0.y(x0.f19655d, new AnonymousClass2(str, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getDiscordClientId() {
        return o.L0("1501197333826637835").toString();
    }

    private final void handlePauseTimeout(boolean isPlaying) {
        v1 v1Var = disconnectTimeoutJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        if (isPlaying || connectionState != ConnectionState.CONNECTED) {
            return;
        }
        disconnectTimeoutJob = m0.p(coroutineScope, null, 0, new C13361(null), 3);
    }

    private final void openAuthDialog() {
        v1 v1Var = authPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        m0.p(coroutineScope, null, 0, new C13371(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object pollCloudStatus(String str, d<? super PairingStatus> dVar) {
        return m0.y(x0.f19655d, new C13382(str, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d4 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:13:0x003c, B:41:0x00cd, B:44:0x00d4, B:48:0x00df), top: B:54:0x003c }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshCurrentToken(d7.d<? super java.lang.String> r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.refreshCurrentToken(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object requestTokens(Map<String, String> map, d<? super OAuthTokens> dVar) {
        return m0.y(x0.f19655d, new C13402(map, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveTokens(OAuthTokens tokens) {
        currentAccessToken = tokens.getAccessToken();
        currentRefreshToken = tokens.getRefreshToken();
        accessTokenExpiresAt = tokens.getExpiresAt();
        Context context = appContext;
        if (context == null) {
            p.i("appContext");
            throw null;
        }
        context.getSharedPreferences(PREFS_NAME, 0).edit().putString(KEY_ACCESS_TOKEN, tokens.getAccessToken()).putString(KEY_REFRESH_TOKEN, tokens.getRefreshToken()).putLong(KEY_ACCESS_TOKEN_EXPIRES_AT, tokens.getExpiresAt()).apply();
        scheduleTokenRefresh();
    }

    private final void scheduleTokenRefresh() {
        v1 v1Var = tokenRefreshJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        long jCurrentTimeMillis = (accessTokenExpiresAt - System.currentTimeMillis()) - 60000;
        if (jCurrentTimeMillis < 1000) {
            jCurrentTimeMillis = 1000;
        }
        tokenRefreshJob = m0.p(coroutineScope, null, 0, new C13411(jCurrentTimeMillis, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object startCloudSession(String str, d<? super PairingSession> dVar) {
        return m0.y(x0.f19655d, new C13422(str, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTickLoop() {
        v1 v1Var = tickJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        tickJob = m0.p(coroutineScope, x0.f19653b, 0, new C13431(null), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopTickLoop() {
        v1 v1Var = tickJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        tickJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateUsername(java.lang.String r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13461
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updateUsername$1 r0 = (com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.C13461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updateUsername$1 r0 = new com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$updateUsername$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            k2.c.G(r6)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r4.fetchUserProfile(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L42
            return r5
        L42:
            java.lang.String r6 = (java.lang.String) r6
            x6.t0 r5 = x6.t0.f22605a
            if (r6 != 0) goto L49
            return r5
        L49:
            android.content.Context r0 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.appContext
            if (r0 == 0) goto L68
            java.lang.String r1 = "discord_rpc_prefs"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.lang.String r1 = "username"
            android.content.SharedPreferences$Editor r0 = r0.putString(r1, r6)
            r0.apply()
            na.q0<java.lang.String> r0 = com.arflix.tv.ui.screens.details.discord.DiscordRpcManager._username
            r0.setValue(r6)
            return r5
        L68:
            java.lang.String r5 = "appContext"
            kotlin.jvm.internal.p.i(r5)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.updateUsername(java.lang.String, d7.d):java.lang.Object");
    }

    public final void closeAuthDialog() {
        q0<Boolean> q0Var = _isAuthDialogVisible;
        Boolean bool = Boolean.FALSE;
        q0Var.setValue(bool);
        _isAuthLoading.setValue(bool);
        _authUrl.setValue(null);
        v1 v1Var = authPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        authPollingJob = null;
        Context context = appContext;
        if (context != null) {
            context.getSharedPreferences(PREFS_NAME, 0).edit().remove(KEY_CODE_VERIFIER).apply();
        }
    }

    public final void completeAuthWithCode(String code) {
        if (!isSupported() || o.h0(code) || code.length() > 2048) {
            return;
        }
        m0.p(coroutineScope, null, 0, new AnonymousClass1(code, null), 3);
    }

    public final void disconnect() {
        stopTickLoop();
        v1 v1Var = disconnectTimeoutJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = lastUpdateJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        v1 v1Var3 = reconnectJob;
        if (v1Var3 != null) {
            v1Var3.cancel((CancellationException) null);
        }
        connectionState = ConnectionState.DISCONNECTED;
        if (isSupported()) {
            DiscordBridge.INSTANCE.disconnect();
        }
    }

    public final h1<String> getAuthUrlFlow() {
        return authUrlFlow;
    }

    public final h1<String> getUsernameFlow() {
        return usernameFlow;
    }

    public final void init(Context context) {
        Object c0Var;
        if (initialized) {
            return;
        }
        appContext = context.getApplicationContext();
        initialized = true;
        if (!BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE.booleanValue() || !Pattern.compile("^\\d{17,20}$").matcher(getDiscordClientId()).matches()) {
            Log.i(TAG, "Discord Rich Presence is not included in this build.");
            return;
        }
        if (context instanceof Activity) {
            try {
                int i10 = DiscordSocialSdkInit.f12554a;
                c0Var = DiscordSocialSdkInit.class.getMethod("setEngineActivity", Activity.class).invoke(null, context);
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            Throwable thA = d0.a(c0Var);
            if (thA != null) {
                Log.e(TAG, "Failed to attach the Android activity to Discord Social SDK", thA);
            }
        }
        bridgeReady = DiscordBridge.INSTANCE.init(getDiscordClientId(), jniCallback);
        if (bridgeReady) {
            Context context2 = appContext;
            if (context2 == null) {
                p.i("appContext");
                throw null;
            }
            SharedPreferences sharedPreferences = context2.getSharedPreferences(PREFS_NAME, 0);
            currentAccessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
            currentRefreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
            accessTokenExpiresAt = sharedPreferences.getLong(KEY_ACCESS_TOKEN_EXPIRES_AT, 0L);
            _username.setValue(sharedPreferences.getString(KEY_USERNAME, null));
            if (currentAccessToken == null && currentRefreshToken == null) {
                return;
            }
            m0.p(coroutineScope, null, 0, new AnonymousClass3(null), 3);
        }
    }

    public final h1<Boolean> isAuthDialogVisibleFlow() {
        return isAuthDialogVisibleFlow;
    }

    public final h1<Boolean> isAuthLoadingFlow() {
        return isAuthLoadingFlow;
    }

    public final boolean isLoggedIn() {
        return (currentAccessToken == null && currentRefreshToken == null) ? false : true;
    }

    public final h1<Boolean> isLoggedInFlow() {
        return isLoggedInFlow;
    }

    public final boolean isSupported() {
        if (BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE.booleanValue()) {
            return Pattern.compile("^\\d{17,20}$").matcher(getDiscordClientId()).matches() && bridgeReady;
        }
        return false;
    }

    public final void login(Context context) {
        if (!initialized) {
            init(context);
        }
        if (isSupported()) {
            openAuthDialog();
        } else {
            Log.w(TAG, "Discord Rich Presence is unavailable in this build.");
        }
    }

    public final void logout() {
        disconnect();
        v1 v1Var = tokenRefreshJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        currentAccessToken = null;
        currentRefreshToken = null;
        accessTokenExpiresAt = 0L;
        _isLoggedIn.setValue(Boolean.FALSE);
        _username.setValue(null);
        Context context = appContext;
        if (context != null) {
            context.getSharedPreferences(PREFS_NAME, 0).edit().remove(KEY_ACCESS_TOKEN).remove(KEY_REFRESH_TOKEN).remove(KEY_ACCESS_TOKEN_EXPIRES_AT).remove(KEY_CODE_VERIFIER).remove(KEY_USERNAME).apply();
        }
    }

    public final void onLoginDeepLink(Uri uri) {
        String queryParameter = uri.getQueryParameter("error");
        if (queryParameter != null) {
            Log.e(TAG, "Discord authorization failed: ".concat(queryParameter));
            _isAuthLoading.setValue(Boolean.FALSE);
        } else {
            String queryParameter2 = uri.getQueryParameter("code");
            if (queryParameter2 != null) {
                completeAuthWithCode(queryParameter2);
            }
        }
    }

    public final void updatePlayback(String title, String subtitle, boolean isPlaying, long progressMs, long durationMs, String largeImage) {
        if (initialized && isSupported() && ((Boolean) _isLoggedIn.getValue()).booleanValue()) {
            handlePauseTimeout(isPlaying);
            if (isPlaying && connectionState == ConnectionState.DISCONNECTED) {
                v1 v1Var = reconnectJob;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
                reconnectJob = m0.p(coroutineScope, null, 0, new C13441(null), 3);
            }
            v1 v1Var2 = lastUpdateJob;
            if (v1Var2 != null) {
                v1Var2.cancel((CancellationException) null);
            }
            lastUpdateJob = m0.p(coroutineScope, null, 0, new C13452(isPlaying, progressMs, title, subtitle, largeImage, null), 3);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordRpcManager$PairingStatus;", "", "status", "", "code", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getCode", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PairingStatus {
        private final String code;
        private final String status;

        public PairingStatus(String str, String str2) {
            this.status = str;
            this.code = str2;
        }

        public static /* synthetic */ PairingStatus copy$default(PairingStatus pairingStatus, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = pairingStatus.status;
            }
            if ((i10 & 2) != 0) {
                str2 = pairingStatus.code;
            }
            return pairingStatus.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCode() {
            return this.code;
        }

        public final PairingStatus copy(String status, String code) {
            return new PairingStatus(status, code);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PairingStatus)) {
                return false;
            }
            PairingStatus pairingStatus = (PairingStatus) other;
            return p.a(this.status, pairingStatus.status) && p.a(this.code, pairingStatus.code);
        }

        public final String getCode() {
            return this.code;
        }

        public final String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int iHashCode = this.status.hashCode() * 31;
            String str = this.code;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return a2.m("PairingStatus(status=", this.status, ", code=", this.code, ")");
        }

        public /* synthetic */ PairingStatus(String str, String str2, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? null : str2);
        }
    }
}
