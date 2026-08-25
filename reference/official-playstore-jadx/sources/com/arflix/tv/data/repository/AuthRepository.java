package com.arflix.tv.data.repository;

import android.content.Context;
import android.util.Base64;
import androidx.credentials.GetCredentialRequest;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.R;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.util.AppLogger;
import com.arflix.tv.util.Constants;
import com.arflix.tv.util.DataStoresKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseClientBuilder;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.AuthConfig;
import io.github.jan.supabase.gotrue.AuthKt;
import io.github.jan.supabase.gotrue.MemoryCodeVerifierCache;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.github.jan.supabase.gotrue.providers.Google;
import io.github.jan.supabase.gotrue.providers.builtin.Email;
import io.github.jan.supabase.gotrue.providers.builtin.IDToken;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.github.jan.supabase.postgrest.Postgrest;
import j$.time.Clock;
import j$.time.Instant;
import j$.time.format.DateTimeParseException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import ka.h2;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\u0004Ó\u0001Ô\u0001B7\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0017\u0010\u0015J&\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u001b\u0010\u0015J\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010!\u001a\u00020 H\u0086@¢\u0006\u0004\b\"\u0010#J\u0010\u0010%\u001a\u00020\rH\u0086@¢\u0006\u0004\b%\u0010\u000fJ\u001e\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010'\u001a\u00020&H\u0086@¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b+\u0010,J\r\u0010.\u001a\u00020-¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b0\u0010,J\u000f\u00101\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b1\u0010,J\u0012\u00102\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b2\u0010\u000fJ\u0010\u00103\u001a\u00020-H\u0086@¢\u0006\u0004\b3\u0010\u000fJ\u0012\u00104\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b4\u0010\u000fJ\u0012\u00105\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b5\u0010\u000fJ\u000f\u00106\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b6\u0010,J\u0018\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0013H\u0086@¢\u0006\u0004\b7\u0010\u000fJ\u001e\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u00109\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b=\u0010,J \u0010@\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\u0010>\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b?\u0010;J\u000f\u0010A\u001a\u0004\u0018\u00010-¢\u0006\u0004\bA\u0010BJ\u001e\u0010F\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010C\u001a\u00020-H\u0086@¢\u0006\u0004\bD\u0010EJ\u0018\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0013H\u0086@¢\u0006\u0004\bG\u0010\u000fJ\u001e\u0010K\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010I\u001a\u00020\u0010H\u0086@¢\u0006\u0004\bJ\u0010;J\u0016\u0010N\u001a\b\u0012\u0004\u0012\u00020L0\u0013H\u0086@¢\u0006\u0004\bM\u0010\u000fJ\u001e\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010O\u001a\u00020\u0010H\u0086@¢\u0006\u0004\bP\u0010;J(\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010R\u001a\u00020L2\b\b\u0002\u0010T\u001a\u00020SH\u0086@¢\u0006\u0004\bU\u0010VJ8\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010T\u001a\u00020SH\u0086@¢\u0006\u0004\bZ\u0010[J*\u0010b\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u00020^\u0012\u0004\u0012\u00020\r0]H\u0086@¢\u0006\u0004\b`\u0010aJ \u0010d\u001a\u00020c2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bd\u0010\u0015J \u0010e\u001a\u00020c2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0082@¢\u0006\u0004\be\u0010\u0015J0\u0010h\u001a\u00020c2\u0006\u0010f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010g\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bh\u0010iJ\u000f\u0010j\u001a\u00020\u0010H\u0002¢\u0006\u0004\bj\u0010,J\u001a\u0010l\u001a\u0004\u0018\u00010&2\u0006\u0010k\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bl\u0010;J \u0010m\u001a\u00020&2\u0006\u0010k\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bm\u0010\u0015J'\u0010r\u001a\u00020\u00102\u000e\u0010p\u001a\n\u0018\u00010nj\u0004\u0018\u0001`o2\u0006\u0010q\u001a\u00020\u0010H\u0002¢\u0006\u0004\br\u0010sJ\u001a\u0010t\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bt\u0010;J\u0012\u0010v\u001a\u0004\u0018\u00010uH\u0082@¢\u0006\u0004\bv\u0010\u000fJ!\u0010y\u001a\u00020-2\u0006\u0010w\u001a\u00020u2\b\b\u0002\u0010x\u001a\u00020LH\u0002¢\u0006\u0004\by\u0010zJ!\u0010|\u001a\u00020-2\u0006\u0010{\u001a\u00020\u00102\b\b\u0002\u0010x\u001a\u00020LH\u0002¢\u0006\u0004\b|\u0010}J\u0018\u0010~\u001a\u00020\r2\u0006\u0010w\u001a\u00020uH\u0082@¢\u0006\u0004\b~\u0010\u007fJ4\u0010\u0080\u0001\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010k\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0082@¢\u0006\u0005\b\u0080\u0001\u0010iJ\u001c\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001c\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0083\u0001\u0010\u0082\u0001J\u001c\u0010\u0084\u0001\u001a\u0004\u0018\u00010^2\u0006\u0010{\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001b\u0010\u0088\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0086\u00010\u0013H\u0082@¢\u0006\u0005\b\u0087\u0001\u0010\u000fJ#\u0010\u008a\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0086\u00010\u00132\u0006\u0010k\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u0089\u0001\u0010;J \u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010I\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u008b\u0001\u0010;J(\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010k\u001a\u00020\u00102\u0006\u0010I\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u008d\u0001\u0010\u0015J#\u0010\u0090\u0001\u001a\u00020\u00102\u0006\u0010f\u001a\u00020\u00102\u0007\u0010\u008f\u0001\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u0090\u0001\u0010\u0015J%\u0010\u0092\u0001\u001a\u00020\u00102\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010^2\u0006\u0010g\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J$\u0010\u0095\u0001\u001a\u00020\u00102\u0007\u0010\u0094\u0001\u001a\u00020\u00102\u0007\u0010\u008f\u0001\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u0095\u0001\u0010\u0015J(\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010k\u001a\u00020\u00102\u0006\u0010I\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u0096\u0001\u0010\u0015J\u001b\u0010\u0099\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0086\u00010\u0013H\u0082@¢\u0006\u0005\b\u0098\u0001\u0010\u000fJ(\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010k\u001a\u00020\u00102\u0006\u0010I\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u009a\u0001\u0010\u0015J\u001b\u0010\u009d\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0086\u00010\u0013H\u0082@¢\u0006\u0005\b\u009c\u0001\u0010\u000fJ(\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010k\u001a\u00020\u00102\u0006\u0010I\u001a\u00020\u0010H\u0082@¢\u0006\u0005\b\u009e\u0001\u0010\u0015J\u001f\u0010¡\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010 \u0001\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b¡\u0001\u0010\u0082\u0001J\u001d\u0010¢\u0001\u001a\u00020L2\t\u0010 \u0001\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J\u001c\u0010¤\u0001\u001a\u00020L2\b\u0010I\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b¤\u0001\u0010£\u0001J\u001d\u0010¦\u0001\u001a\u00020L2\t\u0010¥\u0001\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b¦\u0001\u0010£\u0001J%\u0010¨\u0001\u001a\u00020\u00102\t\u0010§\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010I\u001a\u00020\u0010H\u0002¢\u0006\u0006\b¨\u0001\u0010©\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010ª\u0001R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010«\u0001R\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\b\u0010¬\u0001R\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\n\u0010¬\u0001R\u0017\u0010\u00ad\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R\u0018\u0010°\u0001\u001a\u00030¯\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0001\u0010±\u0001R\u0018\u0010³\u0001\u001a\u00030²\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b³\u0001\u0010´\u0001R\u0017\u0010µ\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\bµ\u0001\u0010®\u0001R\u0017\u0010¶\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b¶\u0001\u0010®\u0001R\u0017\u0010·\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b·\u0001\u0010®\u0001R\u0017\u0010¸\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b¸\u0001\u0010®\u0001R\u0017\u0010¹\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b¹\u0001\u0010®\u0001R\u0017\u0010º\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\bº\u0001\u0010®\u0001R\u0017\u0010»\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b»\u0001\u0010®\u0001R\u0017\u0010¼\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b¼\u0001\u0010®\u0001R\u0017\u0010½\u0001\u001a\u00020\u00108\u0002X\u0082D¢\u0006\b\n\u0006\b½\u0001\u0010®\u0001R\u0018\u0010¿\u0001\u001a\u00030¾\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¿\u0001\u0010À\u0001R!\u0010Æ\u0001\u001a\u00030Á\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÂ\u0001\u0010Ã\u0001\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u001f\u0010É\u0001\u001a\n\u0012\u0005\u0012\u00030È\u00010Ç\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R$\u0010Ì\u0001\u001a\n\u0012\u0005\u0012\u00030È\u00010Ë\u00018\u0006¢\u0006\u0010\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001R \u0010Ð\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010&0Ç\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÐ\u0001\u0010Ê\u0001R%\u0010Ñ\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010&0Ë\u00018\u0006¢\u0006\u0010\n\u0006\bÑ\u0001\u0010Í\u0001\u001a\u0006\bÒ\u0001\u0010Ï\u0001¨\u0006Õ\u0001"}, d2 = {"Lcom/arflix/tv/data/repository/AuthRepository;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "Ljavax/inject/Provider;", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepositoryProvider", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepositoryProvider", "<init>", "(Landroid/content/Context;Lgb/h0;Ljavax/inject/Provider;Ljavax/inject/Provider;)V", "Lx6/t0;", "checkAuthState", "(Ld7/d;)Ljava/lang/Object;", "", "email", "password", "Lx6/d0;", "signIn-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "signIn", "signUp-0E7RQCE", "signUp", "accessToken", "refreshToken", "signInWithSessionTokens-0E7RQCE", "signInWithSessionTokens", "Landroidx/credentials/GetCredentialRequest;", "getGoogleSignInRequest", "()Landroidx/credentials/GetCredentialRequest;", "Landroidx/credentials/GetCredentialResponse;", "result", "handleGoogleSignInResult-gIAlu-s", "(Landroidx/credentials/GetCredentialResponse;Ld7/d;)Ljava/lang/Object;", "handleGoogleSignInResult", "signOut", "Lcom/arflix/tv/data/repository/UserProfile;", "profile", "updateProfile-gIAlu-s", "(Lcom/arflix/tv/data/repository/UserProfile;Ld7/d;)Ljava/lang/Object;", "updateProfile", "getTraktAccessToken", "()Ljava/lang/String;", "", "isTraktLinked", "()Z", "getCurrentUserId", "getCurrentUserEmail", "getCurrentUserIdForSync", "hasValidCloudSyncSession", "getAccessToken", "refreshAccessToken", "getAddonsFromProfile", "getAddonsFromProfileFresh-IoAF18A", "getAddonsFromProfileFresh", "addonsJson", "saveAddonsToProfile-gIAlu-s", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "saveAddonsToProfile", "getDefaultSubtitleFromProfile", MediaTrack.ROLE_SUBTITLE, "saveDefaultSubtitleToProfile-gIAlu-s", "saveDefaultSubtitleToProfile", "getAutoPlayNextFromProfile", "()Ljava/lang/Boolean;", "autoPlayNext", "saveAutoPlayNextToProfile-gIAlu-s", "(ZLd7/d;)Ljava/lang/Object;", "saveAutoPlayNextToProfile", "loadAccountSyncPayload-IoAF18A", "loadAccountSyncPayload", "payload", "saveAccountSyncPayload-gIAlu-s", "saveAccountSyncPayload", "", "getAccountSyncEventCursor-IoAF18A", "getAccountSyncEventCursor", "itemsJson", "pushAccountSyncItems-gIAlu-s", "pushAccountSyncItems", "sinceEventId", "", "limit", "pullAccountSyncDelta-0E7RQCE", "(JILd7/d;)Ljava/lang/Object;", "pullAccountSyncDelta", "scope", "profileId", "pullAccountSyncItems-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "pullAccountSyncItems", "Lkotlin/Function1;", "Lorg/json/JSONObject;", "mutator", "mutateAccountSyncPayload-gIAlu-s", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "mutateAccountSyncPayload", "Lcom/arflix/tv/data/repository/AuthRepository$CloudAccountSession;", "signInCloudAccountSession", "createCloudAccountSession", "url", "defaultError", "requestCloudAccountSession", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "generateNonce", "userId", "loadUserProfile", "createDefaultProfile", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "fallback", "safeErrorMessage", "(Ljava/lang/Exception;Ljava/lang/String;)Ljava/lang/String;", "refreshNetlifyAccessToken", "Lio/github/jan/supabase/gotrue/user/UserSession;", "ensureValidSession", SettingsSessionManager.SETTINGS_KEY, "bufferSeconds", "isSessionExpired", "(Lio/github/jan/supabase/gotrue/user/UserSession;J)Z", "token", "isJwtExpired", "(Ljava/lang/String;J)Z", "storeSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "storeRawSessionTokens", "extractUserIdFromAccessToken", "(Ljava/lang/String;)Ljava/lang/String;", "extractUserEmailFromAccessToken", "decodeJwtPayload", "(Ljava/lang/String;)Lorg/json/JSONObject;", "Lcom/arflix/tv/data/repository/AccountSyncPayloadCandidate;", "loadAccountSyncPayloadFromNetlify-IoAF18A", "loadAccountSyncPayloadFromNetlify", "loadAccountSyncPayloadFromAccountSyncState-gIAlu-s", "loadAccountSyncPayloadFromAccountSyncState", "saveAccountSyncPayloadToNetlify-gIAlu-s", "saveAccountSyncPayloadToNetlify", "saveAccountSyncPayloadViaRpc-0E7RQCE", "saveAccountSyncPayloadViaRpc", TtmlNode.TAG_BODY, "callNetlifyFunction", "json", "cloudAuthErrorMessage", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;", "functionName", "callSupabaseRpc", "saveAccountSyncPayloadToAccountSyncState-0E7RQCE", "saveAccountSyncPayloadToAccountSyncState", "loadAccountSyncPayloadFromUserSettings-IoAF18A", "loadAccountSyncPayloadFromUserSettings", "saveAccountSyncPayloadToUserSettings-0E7RQCE", "saveAccountSyncPayloadToUserSettings", "loadAccountSyncPayloadFromProfileAddons-IoAF18A", "loadAccountSyncPayloadFromProfileAddons", "saveAccountSyncPayloadToProfileAddons-0E7RQCE", "saveAccountSyncPayloadToProfileAddons", "raw", "decodeProfileAccountSyncPayload", "decodeProfileAccountSyncUpdatedAt", "(Ljava/lang/String;)J", "payloadUpdatedAtMillis", "value", "parseInstantMillis", "existingAddons", "encodeProfileAccountSyncPayload", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Landroid/content/Context;", "Lgb/h0;", "Ljavax/inject/Provider;", "TAG", "Ljava/lang/String;", "Lgb/d0;", "jsonMediaType", "Lgb/d0;", "Lua/a;", "accountSyncMutationMutex", "Lua/a;", "ACCOUNT_SYNC_PAYLOAD_KEY", "ACCOUNT_SYNC_UPDATED_AT_KEY", "PROFILE_SYNC_PAYLOAD_KEY", "PROFILE_SYNC_UPDATED_AT_KEY", "PROFILE_SYNC_LEGACY_ADDONS_KEY", "ACCOUNT_SYNC_SOURCE_NETLIFY", "ACCOUNT_SYNC_SOURCE_PRIMARY", "ACCOUNT_SYNC_SOURCE_USER_SETTINGS", "ACCOUNT_SYNC_SOURCE_PROFILE_ADDONS", "Lcom/arflix/tv/data/repository/DataStoreSessionManager;", "sessionManager", "Lcom/arflix/tv/data/repository/DataStoreSessionManager;", "Lio/github/jan/supabase/SupabaseClient;", "supabase$delegate", "Lx6/s;", "getSupabase", "()Lio/github/jan/supabase/SupabaseClient;", "supabase", "Lna/q0;", "Lcom/arflix/tv/data/repository/AuthState;", "_authState", "Lna/q0;", "Lna/h1;", "authState", "Lna/h1;", "getAuthState", "()Lna/h1;", "_userProfile", "userProfile", "getUserProfile", "PrefsKeys", "CloudAccountSession", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AuthRepository {
    public static final int $stable = 8;
    private final String ACCOUNT_SYNC_PAYLOAD_KEY;
    private final String ACCOUNT_SYNC_SOURCE_NETLIFY;
    private final String ACCOUNT_SYNC_SOURCE_PRIMARY;
    private final String ACCOUNT_SYNC_SOURCE_PROFILE_ADDONS;
    private final String ACCOUNT_SYNC_SOURCE_USER_SETTINGS;
    private final String ACCOUNT_SYNC_UPDATED_AT_KEY;
    private final String PROFILE_SYNC_LEGACY_ADDONS_KEY;
    private final String PROFILE_SYNC_PAYLOAD_KEY;
    private final String PROFILE_SYNC_UPDATED_AT_KEY;
    private final String TAG = "AuthRepository";
    private final q0<AuthState> _authState;
    private final q0<UserProfile> _userProfile;
    private final ua.a accountSyncMutationMutex;
    private final h1<AuthState> authState;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Context context;
    private final gb.d0 jsonMediaType;
    private final gb.h0 okHttpClient;
    private final DataStoreSessionManager sessionManager;

    /* JADX INFO: renamed from: supabase$delegate, reason: from kotlin metadata */
    private final x6.s supabase;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final h1<UserProfile> userProfile;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/AuthRepository$CloudAccountSession;", "", "accessToken", "", "refreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CloudAccountSession {
        private final String accessToken;
        private final String refreshToken;

        public CloudAccountSession(String str, String str2) {
            this.accessToken = str;
            this.refreshToken = str2;
        }

        public static /* synthetic */ CloudAccountSession copy$default(CloudAccountSession cloudAccountSession, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = cloudAccountSession.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = cloudAccountSession.refreshToken;
            }
            return cloudAccountSession.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public final CloudAccountSession copy(String accessToken, String refreshToken) {
            return new CloudAccountSession(accessToken, refreshToken);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloudAccountSession)) {
                return false;
            }
            CloudAccountSession cloudAccountSession = (CloudAccountSession) other;
            return kotlin.jvm.internal.p.a(this.accessToken, cloudAccountSession.accessToken) && kotlin.jvm.internal.p.a(this.refreshToken, cloudAccountSession.refreshToken);
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public int hashCode() {
            return this.refreshToken.hashCode() + (this.accessToken.hashCode() * 31);
        }

        public String toString() {
            return a2.m("CloudAccountSession(accessToken=", this.accessToken, ", refreshToken=", this.refreshToken, ")");
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\u0007\u001a\u0004\b\r\u0010\tR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0007\u001a\u0004\b\u000f\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/repository/AuthRepository$PrefsKeys;", "", "<init>", "()V", "Lc1/e;", "", "ACCESS_TOKEN", "Lc1/e;", "getACCESS_TOKEN", "()Lc1/e;", "REFRESH_TOKEN", "getREFRESH_TOKEN", "USER_ID", "getUSER_ID", "USER_EMAIL", "getUSER_EMAIL", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class PrefsKeys {
        public static final PrefsKeys INSTANCE = new PrefsKeys();
        private static final c1.e ACCESS_TOKEN = new c1.e("access_token");
        private static final c1.e REFRESH_TOKEN = new c1.e("refresh_token");
        private static final c1.e USER_ID = new c1.e("user_id");
        private static final c1.e USER_EMAIL = new c1.e("user_email");

        private PrefsKeys() {
        }

        public final c1.e getACCESS_TOKEN() {
            return ACCESS_TOKEN;
        }

        public final c1.e getREFRESH_TOKEN() {
            return REFRESH_TOKEN;
        }

        public final c1.e getUSER_EMAIL() {
            return USER_EMAIL;
        }

        public final c1.e getUSER_ID() {
            return USER_ID;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1848, 1850}, m = "callNetlifyFunction", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.callNetlifyFunction(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $accessToken;
        final /* synthetic */ String $body;
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ AuthRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2, String str3, AuthRepository authRepository, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = str;
            this.$accessToken = str2;
            this.$body = str3;
            this.this$0 = authRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$url, this.$accessToken, this.$body, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            gb.j0 j0Var = new gb.j0();
            j0Var.i(this.$url);
            j0Var.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", this.$accessToken));
            j0Var.f15724c.h("Cache-Control", "no-cache, no-store");
            String str = this.$body;
            gb.d0 d0VarI0 = this.this$0.jsonMediaType;
            Charset charset = kotlin.text.a.f19924a;
            if (d0VarI0 != null) {
                Pattern pattern = gb.d0.f15633d;
                Charset charsetA = d0VarI0.a(null);
                if (charsetA == null) {
                    try {
                        d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                    } catch (IllegalArgumentException unused) {
                        d0VarI0 = null;
                    }
                } else {
                    charset = charsetA;
                }
            }
            byte[] bytes = str.getBytes(charset);
            int length = bytes.length;
            ib.c.c(bytes.length, 0, length);
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            try {
                s0 s0Var = p0VarD.f15786q;
                String strX = s0Var != null ? s0Var.x() : null;
                if (strX == null) {
                    strX = "";
                }
                if (p0VarD.k()) {
                    p0VarD.close();
                    return strX;
                }
                throw new IllegalStateException("Netlify cloud sync failed (" + p0VarD.f15783n + "): " + AuthRepositoryKt.safePostgrestError(strX));
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1888, 1889}, m = "callSupabaseRpc", v = 2)
    public static final class C06731 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06731(d7.d<? super C06731> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.callSupabaseRpc(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06742 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $body;
        final /* synthetic */ String $functionName;
        final /* synthetic */ UserSession $session;
        int label;
        final /* synthetic */ AuthRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06742(String str, UserSession userSession, String str2, AuthRepository authRepository, d7.d<? super C06742> dVar) {
            super(2, dVar);
            this.$functionName = str;
            this.$session = userSession;
            this.$body = str2;
            this.this$0 = authRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C06742(this.$functionName, this.$session, this.$body, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            gb.j0 j0Var = new gb.j0();
            Constants constants = Constants.INSTANCE;
            j0Var.i(constants.getSUPABASE_URL() + "/rest/v1/rpc/" + this.$functionName);
            j0Var.f15724c.h("apikey", constants.getSUPABASE_ANON_KEY());
            j0Var.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", this.$session.getAccessToken()));
            j0Var.f15724c.h("Cache-Control", "no-cache, no-store");
            String str = this.$body;
            gb.d0 d0VarI0 = this.this$0.jsonMediaType;
            Charset charset = kotlin.text.a.f19924a;
            if (d0VarI0 != null) {
                Pattern pattern = gb.d0.f15633d;
                Charset charsetA = d0VarI0.a(null);
                if (charsetA == null) {
                    try {
                        d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                    } catch (IllegalArgumentException unused) {
                        d0VarI0 = null;
                    }
                } else {
                    charset = charsetA;
                }
            }
            byte[] bytes = str.getBytes(charset);
            int length = bytes.length;
            ib.c.c(bytes.length, 0, length);
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            String str2 = this.$functionName;
            try {
                s0 s0Var = p0VarD.f15786q;
                String strX = s0Var != null ? s0Var.x() : null;
                if (strX == null) {
                    strX = "";
                }
                if (p0VarD.k()) {
                    p0VarD.close();
                    return strX;
                }
                throw new IllegalStateException(str2 + " failed (" + p0VarD.f15783n + "): " + AuthRepositoryKt.safePostgrestError(strX));
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C06742) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {MediaError.DetailedErrorCode.DASH_MANIFEST_NO_PERIODS, 434, 445, 453, 467, 481, 489, 499, 510, 512, 518, 522, 523}, m = "checkAuthState", v = 2)
    public static final class C06751 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public C06751(d7.d<? super C06751> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.checkAuthState(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$2", f = "AuthRepository.kt", l = {468}, m = "invokeSuspend", v = 2)
    public static final class C06762 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$2$1", f = "AuthRepository.kt", l = {469}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
            int label;
            final /* synthetic */ AuthRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AuthRepository authRepository, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = authRepository;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    Auth auth = AuthKt.getAuth(this.this$0.getSupabase());
                    this.label = 1;
                    Object objLoadFromStorage = auth.loadFromStorage(true, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objLoadFromStorage == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return AuthKt.getAuth(this.this$0.getSupabase()).currentSessionOrNull();
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public C06762(d7.d<? super C06762> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return AuthRepository.this.new C06762(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            x0 x0Var = x0.f19652a;
            h2 h2Var = pa.q.f21294a;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(AuthRepository.this, null);
            this.label = 1;
            Object objY = ka.m0.y(h2Var, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            return objY == aVar ? aVar : objY;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
            return ((C06762) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$3", f = "AuthRepository.kt", l = {482}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
        final /* synthetic */ String $accessToken;
        final /* synthetic */ String $refreshToken;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$3$1", f = "AuthRepository.kt", l = {483}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
            final /* synthetic */ String $accessToken;
            final /* synthetic */ String $refreshToken;
            int label;
            final /* synthetic */ AuthRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AuthRepository authRepository, String str, String str2, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = authRepository;
                this.$accessToken = str;
                this.$refreshToken = str2;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$accessToken, this.$refreshToken, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                AnonymousClass1 anonymousClass1;
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    Auth auth = AuthKt.getAuth(this.this$0.getSupabase());
                    String str = this.$accessToken;
                    String str2 = str == null ? "" : str;
                    String str3 = this.$refreshToken;
                    String str4 = str3 == null ? "" : str3;
                    this.label = 1;
                    anonymousClass1 = this;
                    Object objImportAuthToken = auth.importAuthToken(str2, str4, false, true, anonymousClass1);
                    e7.a aVar = e7.a.f15033i;
                    if (objImportAuthToken == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    anonymousClass1 = this;
                }
                return AuthKt.getAuth(anonymousClass1.this$0.getSupabase()).currentSessionOrNull();
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(String str, String str2, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$accessToken = str;
            this.$refreshToken = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return AuthRepository.this.new AnonymousClass3(this.$accessToken, this.$refreshToken, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            x0 x0Var = x0.f19652a;
            h2 h2Var = pa.q.f21294a;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(AuthRepository.this, this.$accessToken, this.$refreshToken, null);
            this.label = 1;
            Object objY = ka.m0.y(h2Var, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            return objY == aVar ? aVar : objY;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$4", f = "AuthRepository.kt", l = {500}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$4$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "<anonymous>", "(Lka/k0;)Lio/github/jan/supabase/gotrue/user/UserSession;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$4$1", f = "AuthRepository.kt", l = {501}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super UserSession>, Object> {
            int label;
            final /* synthetic */ AuthRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AuthRepository authRepository, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = authRepository;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Exception {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                AuthRepository authRepository = this.this$0;
                this.label = 1;
                Object objEnsureValidSession = authRepository.ensureValidSession(this);
                e7.a aVar = e7.a.f15033i;
                return objEnsureValidSession == aVar ? aVar : objEnsureValidSession;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public AnonymousClass4(d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return AuthRepository.this.new AnonymousClass4(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            x0 x0Var = x0.f19652a;
            h2 h2Var = pa.q.f21294a;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(AuthRepository.this, null);
            this.label = 1;
            Object objY = ka.m0.y(h2Var, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            return objY == aVar ? aVar : objY;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super UserSession> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$checkAuthState$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$checkAuthState$5", f = "AuthRepository.kt", l = {524}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass5 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass5(d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return AuthRepository.this.new AnonymousClass5(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TraktRepository traktRepository = (TraktRepository) AuthRepository.this.traktRepositoryProvider.get();
                this.label = 1;
                Object objSyncLocalTokensToProfileIfNeeded = traktRepository.syncLocalTokensToProfileIfNeeded(this);
                e7.a aVar = e7.a.f15033i;
                if (objSyncLocalTokensToProfileIfNeeded == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$createDefaultProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {2187, 950, 951}, m = "createDefaultProfile", v = 2)
    public static final class C06771 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C06771(d7.d<? super C06771> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.createDefaultProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$ensureValidSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1171, 1179, 1183, 1190, 1191}, m = "ensureValidSession", v = 2)
    public static final class C06781 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C06781(d7.d<? super C06781> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.ensureValidSession(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$getAccessToken$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1083, 1085, 1088, 1093, 1095}, m = "getAccessToken", v = 2)
    public static final class C06791 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06791(d7.d<? super C06791> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.getAccessToken(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$getCurrentUserIdForSync$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1039, 1055, 1058, 1071}, m = "getCurrentUserIdForSync", v = 2)
    public static final class C06801 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06801(d7.d<? super C06801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.getCurrentUserIdForSync(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$hasValidCloudSyncSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1075}, m = "hasValidCloudSyncSession", v = 2)
    public static final class C06811 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C06811(d7.d<? super C06811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.hasValidCloudSyncSession(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$loadUserProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {2182, 917, 921, 923}, m = "loadUserProfile", v = 2)
    public static final class C06821 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C06821(d7.d<? super C06821> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.loadUserProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$refreshAccessToken$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1099, 1104, 1108, 1109}, m = "refreshAccessToken", v = 2)
    public static final class C06831 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06831(d7.d<? super C06831> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.refreshAccessToken(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$refreshNetlifyAccessToken$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$refreshNetlifyAccessToken$2", f = "AuthRepository.kt", l = {1145, 1147}, m = "invokeSuspend", v = 2)
    public static final class C06842 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $refreshToken;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ AuthRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06842(String str, AuthRepository authRepository, d7.d<? super C06842> dVar) {
            super(2, dVar);
            this.$refreshToken = str;
            this.this$0 = authRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C06842(this.$refreshToken, this.this$0, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0172 A[Catch: all -> 0x0040, TryCatch #0 {all -> 0x0040, blocks: (B:7:0x003b, B:72:0x01c5, B:30:0x011f, B:32:0x0123, B:36:0x012d, B:42:0x013b, B:44:0x0150, B:47:0x0157, B:49:0x0160, B:51:0x0168, B:58:0x0178, B:60:0x0180, B:67:0x0193, B:69:0x0199, B:56:0x0172), top: B:96:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x018b A[PHI: r0 r1 r2 r3 r4 r8 r10
          0x018b: PHI (r0v17 java.lang.String) = (r0v9 java.lang.String), (r0v16 java.lang.String), (r0v27 java.lang.String) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r1v13 ??) = (r1v12 ??), (r1v12 ??), (r1v16 ??) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r2v12 java.lang.String) = (r2v9 java.lang.String), (r2v9 java.lang.String), (r2v13 java.lang.String) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r3v11 java.lang.String) = (r3v6 java.lang.String), (r3v6 java.lang.String), (r3v12 java.lang.String) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r4v2 java.lang.String) = (r4v1 java.lang.String), (r4v1 java.lang.String), (r4v4 java.lang.String) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r8v15 com.arflix.tv.data.repository.AuthRepository) = 
          (r8v10 com.arflix.tv.data.repository.AuthRepository)
          (r8v10 com.arflix.tv.data.repository.AuthRepository)
          (r8v17 com.arflix.tv.data.repository.AuthRepository)
         binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]
          0x018b: PHI (r10v7 int) = (r10v3 int), (r10v3 int), (r10v8 int) binds: [B:68:0x0197, B:64:0x0188, B:72:0x01c5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0193 A[Catch: all -> 0x0040, TryCatch #0 {all -> 0x0040, blocks: (B:7:0x003b, B:72:0x01c5, B:30:0x011f, B:32:0x0123, B:36:0x012d, B:42:0x013b, B:44:0x0150, B:47:0x0157, B:49:0x0160, B:51:0x0168, B:58:0x0178, B:60:0x0180, B:67:0x0193, B:69:0x0199, B:56:0x0172), top: B:96:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0199 A[Catch: all -> 0x0040, TryCatch #0 {all -> 0x0040, blocks: (B:7:0x003b, B:72:0x01c5, B:30:0x011f, B:32:0x0123, B:36:0x012d, B:42:0x013b, B:44:0x0150, B:47:0x0157, B:49:0x0160, B:51:0x0168, B:58:0x0178, B:60:0x0180, B:67:0x0193, B:69:0x0199, B:56:0x0172), top: B:96:0x000d }] */
        /* JADX WARN: Type inference failed for: r1v12, types: [gb.p0, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v17, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r1v21 */
        /* JADX WARN: Type inference failed for: r1v31 */
        /* JADX WARN: Type inference failed for: r1v32 */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r8v16, types: [java.io.Closeable, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v26 */
        /* JADX WARN: Type inference failed for: r8v27 */
        /* JADX WARN: Type inference failed for: r8v28 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 538
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.C06842.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C06842) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$requestCloudAccountSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/AuthRepository$CloudAccountSession;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/AuthRepository$CloudAccountSession;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$requestCloudAccountSession$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06852 extends f7.j implements r7.p<ka.k0, d7.d<? super CloudAccountSession>, Object> {
        final /* synthetic */ String $defaultError;
        final /* synthetic */ String $email;
        final /* synthetic */ String $password;
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ AuthRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06852(String str, String str2, String str3, AuthRepository authRepository, String str4, d7.d<? super C06852> dVar) {
            super(2, dVar);
            this.$email = str;
            this.$password = str2;
            this.$url = str3;
            this.this$0 = authRepository;
            this.$defaultError = str4;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C06852(this.$email, this.$password, this.$url, this.this$0, this.$defaultError, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            JSONObject jSONObject;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String string = new JSONObject().put("email", this.$email).put("password", this.$password).toString();
            gb.j0 j0Var = new gb.j0();
            j0Var.i(this.$url);
            Constants constants = Constants.INSTANCE;
            j0Var.f15724c.h("apikey", constants.getAPP_ANON_KEY());
            j0Var.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", constants.getAPP_ANON_KEY()));
            gb.d0 d0VarI0 = this.this$0.jsonMediaType;
            Charset charset = kotlin.text.a.f19924a;
            if (d0VarI0 != null) {
                Pattern pattern = gb.d0.f15633d;
                Charset charsetA = d0VarI0.a(null);
                if (charsetA == null) {
                    try {
                        d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                    } catch (IllegalArgumentException unused) {
                        d0VarI0 = null;
                    }
                } else {
                    charset = charsetA;
                }
            }
            byte[] bytes = string.getBytes(charset);
            int length = bytes.length;
            ib.c.c(bytes.length, 0, length);
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            AuthRepository authRepository = this.this$0;
            String str = this.$defaultError;
            try {
                s0 s0Var = p0VarD.f15786q;
                String strX = s0Var != null ? s0Var.x() : null;
                String str2 = "";
                if (strX == null) {
                    strX = "";
                }
                try {
                    jSONObject = new JSONObject(strX);
                } catch (JSONException unused2) {
                    jSONObject = null;
                }
                if (!p0VarD.k()) {
                    throw new IllegalStateException(authRepository.cloudAuthErrorMessage(jSONObject, str));
                }
                String strOptString = jSONObject != null ? jSONObject.optString("access_token") : null;
                if (strOptString == null) {
                    strOptString = "";
                }
                String strOptString2 = jSONObject != null ? jSONObject.optString("refresh_token") : null;
                if (strOptString2 != null) {
                    str2 = strOptString2;
                }
                if (kotlin.text.o.h0(strOptString) || kotlin.text.o.h0(str2)) {
                    throw new IllegalStateException(authRepository.context.getString(R.string.auth_response_incomplete));
                }
                CloudAccountSession cloudAccountSession = new CloudAccountSession(strOptString, str2);
                p0VarD.close();
                return cloudAccountSession;
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super CloudAccountSession> dVar) {
            return ((C06852) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$signOut$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {872, 888, 893, 896, 897}, m = "signOut", v = 2)
    public static final class C06861 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06861(d7.d<? super C06861> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.signOut(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$signOut$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$signOut$2", f = "AuthRepository.kt", l = {873}, m = "invokeSuspend", v = 2)
    public static final class C06872 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C06872(d7.d<? super C06872> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C06872 c06872 = AuthRepository.this.new C06872(dVar);
            c06872.L$0 = obj;
            return c06872;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            Object objM6125pushToCloudgIAlus;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    CloudSyncRepository cloudSyncRepository = (CloudSyncRepository) AuthRepository.this.cloudSyncRepositoryProvider.get();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    objM6125pushToCloudgIAlus = cloudSyncRepository.m6125pushToCloudgIAlus(true, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objM6125pushToCloudgIAlus == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objM6125pushToCloudgIAlus = ((x6.d0) obj).f22580i;
                }
                c0Var = new x6.d0(objM6125pushToCloudgIAlus);
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            Throwable thA = x6.d0.a(c0Var);
            if (thA != null) {
                AppLogger.INSTANCE.breadcrumb("Auth", "sign_out_final_push_failed ".concat(thA.getClass().getSimpleName()), "warning");
            }
            return new x6.d0(c0Var);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
            return ((C06872) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$signOut$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$signOut$3", f = "AuthRepository.kt", l = {889}, m = "invokeSuspend", v = 2)
    public static final class C06883 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C06883(d7.d<? super C06883> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C06883 c06883 = AuthRepository.this.new C06883(dVar);
            c06883.L$0 = obj;
            return c06883;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    Auth auth = AuthKt.getAuth(AuthRepository.this.getSupabase());
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    Object objSignOut$default = Auth.DefaultImpls.signOut$default(auth, null, this, 1, null);
                    e7.a aVar = e7.a.f15033i;
                    if (objSignOut$default == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                c0Var = t0.f22605a;
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            return new x6.d0(c0Var);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
            return ((C06883) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$storeRawSessionTokens$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$storeRawSessionTokens$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06892 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $accessToken;
        final /* synthetic */ String $email;
        final /* synthetic */ String $refreshToken;
        final /* synthetic */ String $userId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06892(String str, String str2, String str3, String str4, d7.d<? super C06892> dVar) {
            super(2, dVar);
            this.$accessToken = str;
            this.$refreshToken = str2;
            this.$userId = str3;
            this.$email = str4;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C06892 c06892 = new C06892(this.$accessToken, this.$refreshToken, this.$userId, this.$email, dVar);
            c06892.L$0 = obj;
            return c06892;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C06892) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            PrefsKeys prefsKeys = PrefsKeys.INSTANCE;
            bVar.d(prefsKeys.getACCESS_TOKEN(), this.$accessToken);
            bVar.d(prefsKeys.getREFRESH_TOKEN(), this.$refreshToken);
            bVar.d(prefsKeys.getUSER_ID(), this.$userId);
            String str = this.$email;
            if (str != null && !kotlin.text.o.h0(str)) {
                bVar.d(prefsKeys.getUSER_EMAIL(), this.$email);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$storeSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1244, 1253}, m = "storeSession", v = 2)
    public static final class C06901 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06901(d7.d<? super C06901> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepository.this.storeSession(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AuthRepository$storeSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AuthRepository$storeSession$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06912 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ UserSession $session;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06912(UserSession userSession, d7.d<? super C06912> dVar) {
            super(2, dVar);
            this.$session = userSession;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C06912 c06912 = new C06912(this.$session, dVar);
            c06912.L$0 = obj;
            return c06912;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C06912) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            PrefsKeys prefsKeys = PrefsKeys.INSTANCE;
            bVar.d(prefsKeys.getACCESS_TOKEN(), this.$session.getAccessToken());
            bVar.d(prefsKeys.getREFRESH_TOKEN(), this.$session.getRefreshToken());
            UserInfo user = this.$session.getUser();
            if (user != null) {
                bVar.d(prefsKeys.getUSER_ID(), user.getId());
                String email = user.getEmail();
                if (email != null) {
                    bVar.d(prefsKeys.getUSER_EMAIL(), email);
                }
            }
            return t0.f22605a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Inject
    public AuthRepository(@ApplicationContext Context context, gb.h0 h0Var, Provider<TraktRepository> provider, Provider<CloudSyncRepository> provider2) {
        this.context = context;
        this.okHttpClient = h0Var;
        this.traktRepositoryProvider = provider;
        this.cloudSyncRepositoryProvider = provider2;
        Pattern pattern = gb.d0.f15633d;
        this.jsonMediaType = xc.d.i0("application/json; charset=utf-8");
        this.accountSyncMutationMutex = ua.e.a();
        this.ACCOUNT_SYNC_PAYLOAD_KEY = "accountSyncPayload";
        this.ACCOUNT_SYNC_UPDATED_AT_KEY = "accountSyncUpdatedAt";
        this.PROFILE_SYNC_PAYLOAD_KEY = "__arvioAccountSyncPayload";
        this.PROFILE_SYNC_UPDATED_AT_KEY = "__arvioAccountSyncUpdatedAt";
        this.PROFILE_SYNC_LEGACY_ADDONS_KEY = "__arvioLegacyAddons";
        this.ACCOUNT_SYNC_SOURCE_NETLIFY = "netlify_account_sync";
        this.ACCOUNT_SYNC_SOURCE_PRIMARY = "account_sync_state";
        this.ACCOUNT_SYNC_SOURCE_USER_SETTINGS = "user_settings";
        this.ACCOUNT_SYNC_SOURCE_PROFILE_ADDONS = "profile_addons";
        this.sessionManager = new DataStoreSessionManager(DataStoresKt.getAuthDataStore(context), null, 2, 0 == true ? 1 : 0);
        this.supabase = new x6.i0(new a0(this, 1));
        j1 j1VarB = y0.b(AuthState.Loading.INSTANCE);
        this._authState = j1VarB;
        this.authState = y0.e(j1VarB);
        j1 j1VarB2 = y0.b(null);
        this._userProfile = j1VarB2;
        this.userProfile = y0.e(j1VarB2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object callNetlifyFunction(java.lang.String r12, java.lang.String r13, d7.d<? super java.lang.String> r14) throws java.lang.Throwable {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.arflix.tv.data.repository.AuthRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r14
            com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$1 r0 = (com.arflix.tv.data.repository.AuthRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$1 r0 = new com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4d
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r12 = r0.L$2
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r12 = r0.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r12 = r0.L$0
            java.lang.String r12 = (java.lang.String) r12
            k2.c.G(r14)
            return r14
        L36:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L3e:
            java.lang.Object r12 = r0.L$1
            r13 = r12
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r12 = r0.L$0
            java.lang.String r12 = (java.lang.String) r12
            k2.c.G(r14)
        L4a:
            r6 = r12
            r8 = r13
            goto L5d
        L4d:
            k2.c.G(r14)
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r3
            java.lang.Object r14 = r11.getAccessToken(r0)
            if (r14 != r4) goto L4a
            goto L84
        L5d:
            java.lang.String r14 = (java.lang.String) r14
            if (r14 == 0) goto L86
            boolean r12 = kotlin.text.o.h0(r14)
            r13 = 0
            if (r12 != 0) goto L6a
            r7 = r14
            goto L6b
        L6a:
            r7 = r13
        L6b:
            if (r7 == 0) goto L86
            ra.c r12 = ka.x0.f19655d
            com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$2 r5 = new com.arflix.tv.data.repository.AuthRepository$callNetlifyFunction$2
            r10 = 0
            r9 = r11
            r5.<init>(r6, r7, r8, r9, r10)
            r0.L$0 = r13
            r0.L$1 = r13
            r0.L$2 = r13
            r0.label = r2
            java.lang.Object r12 = ka.m0.y(r12, r5, r0)
            if (r12 != r4) goto L85
        L84:
            return r4
        L85:
            return r12
        L86:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "Session expired"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.callNetlifyFunction(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object callSupabaseRpc(java.lang.String r12, java.lang.String r13, d7.d<? super java.lang.String> r14) throws java.lang.Exception {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.arflix.tv.data.repository.AuthRepository.C06731
            if (r0 == 0) goto L13
            r0 = r14
            com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$1 r0 = (com.arflix.tv.data.repository.AuthRepository.C06731) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$1 r0 = new com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4d
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r12 = r0.L$2
            io.github.jan.supabase.gotrue.user.UserSession r12 = (io.github.jan.supabase.gotrue.user.UserSession) r12
            java.lang.Object r12 = r0.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r12 = r0.L$0
            java.lang.String r12 = (java.lang.String) r12
            k2.c.G(r14)
            return r14
        L36:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L3e:
            java.lang.Object r12 = r0.L$1
            r13 = r12
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r12 = r0.L$0
            java.lang.String r12 = (java.lang.String) r12
            k2.c.G(r14)
        L4a:
            r6 = r12
            r8 = r13
            goto L5d
        L4d:
            k2.c.G(r14)
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r3
            java.lang.Object r14 = r11.ensureValidSession(r0)
            if (r14 != r4) goto L4a
            goto L7a
        L5d:
            r7 = r14
            io.github.jan.supabase.gotrue.user.UserSession r7 = (io.github.jan.supabase.gotrue.user.UserSession) r7
            if (r7 == 0) goto L7c
            ra.c r12 = ka.x0.f19655d
            com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$2 r5 = new com.arflix.tv.data.repository.AuthRepository$callSupabaseRpc$2
            r10 = 0
            r9 = r11
            r5.<init>(r6, r7, r8, r9, r10)
            r13 = 0
            r0.L$0 = r13
            r0.L$1 = r13
            r0.L$2 = r13
            r0.label = r2
            java.lang.Object r12 = ka.m0.y(r12, r5, r0)
            if (r12 != r4) goto L7b
        L7a:
            return r4
        L7b:
            return r12
        L7c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "Session expired"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.callSupabaseRpc(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String cloudAuthErrorMessage(JSONObject json, String defaultError) {
        if (json == null) {
            return defaultError;
        }
        String strOptString = json.optString("code");
        String strOptString2 = json.optString("error");
        String str = null;
        if (kotlin.text.o.h0(strOptString2)) {
            strOptString2 = null;
        }
        if (strOptString2 != null) {
            defaultError = strOptString2;
        }
        if (kotlin.jvm.internal.p.a(strOptString, "password_setup_required")) {
            String strOptString3 = json.optString("setup_error");
            if (!kotlin.text.o.h0(strOptString3) && !strOptString3.equals("null")) {
                str = strOptString3;
            }
            if (json.optBoolean("email_sent", false)) {
                return androidx.compose.material3.d.m(defaultError, " Check your email to create the new password.");
            }
            if (str != null && !kotlin.text.o.h0(str)) {
                return androidx.compose.foundation.c.t(defaultError, " Password setup email could not be sent: ", str);
            }
        }
        return defaultError;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object createCloudAccountSession(String str, String str2, d7.d<? super CloudAccountSession> dVar) {
        return requestCloudAccountSession(Constants.INSTANCE.getCLOUD_AUTH_EMAIL_URL(), str, str2, this.context.getString(R.string.auth_unable_create_account), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object createDefaultProfile(java.lang.String r33, java.lang.String r34, d7.d<? super com.arflix.tv.data.repository.UserProfile> r35) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 465
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.createDefaultProfile(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    private final JSONObject decodeJwtPayload(String token) throws Exception {
        try {
            List listY0 = kotlin.text.o.y0(token, new String[]{"."}, 0, 6);
            if (listY0.size() < 2) {
                return null;
            }
            return new JSONObject(new String(Base64.decode((String) listY0.get(1), 11), kotlin.text.a.f19924a));
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return null;
        }
    }

    private final String decodeProfileAccountSyncPayload(String raw) {
        Object c0Var;
        if (raw == null || kotlin.text.o.h0(raw)) {
            return null;
        }
        try {
            String strOptString = new JSONObject(raw).optString(this.PROFILE_SYNC_PAYLOAD_KEY);
            boolean zH0 = kotlin.text.o.h0(strOptString);
            c0Var = strOptString;
            if (zH0) {
                c0Var = null;
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return (String) (c0Var instanceof x6.c0 ? null : c0Var);
    }

    private final long decodeProfileAccountSyncUpdatedAt(String raw) {
        Object c0Var;
        if (raw == null || kotlin.text.o.h0(raw)) {
            return 0L;
        }
        try {
            c0Var = Long.valueOf(parseInstantMillis(new JSONObject(raw).optString(this.PROFILE_SYNC_UPDATED_AT_KEY)));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = 0L;
        }
        return ((Number) c0Var).longValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0011 A[PHI: r4
      0x0011: PHI (r4v12 'existingAddons' java.lang.String) = (r4v6 'existingAddons' java.lang.String), (r4v1 'existingAddons' java.lang.String) binds: [B:18:0x002e, B:8:0x000f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String encodeProfileAccountSyncPayload(java.lang.String r4, java.lang.String r5) throws org.json.JSONException {
        /*
            r3 = this;
            java.lang.String r0 = ""
            if (r4 != 0) goto L5
            r4 = r0
        L5:
            boolean r1 = kotlin.text.o.h0(r4)
            if (r1 != 0) goto L13
            java.lang.String r1 = r3.decodeProfileAccountSyncPayload(r4)
            if (r1 != 0) goto L13
        L11:
            r0 = r4
            goto L30
        L13:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L1f
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = r3.PROFILE_SYNC_LEGACY_ADDONS_KEY     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = r1.optString(r4)     // Catch: java.lang.Throwable -> L1f
            goto L26
        L1f:
            r4 = move-exception
            x6.c0 r1 = new x6.c0
            r1.<init>(r4)
            r4 = r1
        L26:
            boolean r1 = r4 instanceof x6.c0
            if (r1 == 0) goto L2c
            r4 = 0
        L2c:
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L11
        L30:
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            java.lang.String r1 = r3.PROFILE_SYNC_PAYLOAD_KEY
            r4.put(r1, r5)
            java.lang.String r5 = r3.PROFILE_SYNC_UPDATED_AT_KEY
            wa.b r1 = wa.c.Companion
            r1.getClass()
            wa.c r1 = new wa.c
            j$.time.Clock r2 = j$.time.Clock.systemUTC()
            j$.time.Instant r2 = r2.instant()
            r1.<init>(r2)
            j$.time.Instant r1 = r1.f22539i
            java.lang.String r1 = r1.toString()
            r4.put(r5, r1)
            boolean r5 = kotlin.text.o.h0(r0)
            if (r5 != 0) goto L62
            java.lang.String r5 = r3.PROFILE_SYNC_LEGACY_ADDONS_KEY
            r4.put(r5, r0)
        L62:
            java.lang.String r4 = r4.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.encodeProfileAccountSyncPayload(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f0 A[PHI: r0
      0x00f0: PHI (r0v9 java.lang.Object) = (r0v6 java.lang.Object), (r0v0 java.lang.Object) binds: [B:56:0x00ed, B:26:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0122 A[Catch: Exception -> 0x0048, PHI: r0
      0x0122: PHI (r0v16 java.lang.Object) = (r0v15 java.lang.Object), (r0v0 java.lang.Object) binds: [B:64:0x011f, B:24:0x005f] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #1 {Exception -> 0x0048, blocks: (B:17:0x0042, B:24:0x005f, B:66:0x0122, B:63:0x010b), top: B:75:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object ensureValidSession(d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.ensureValidSession(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractUserEmailFromAccessToken(String accessToken) throws Exception {
        String strOptString;
        JSONObject jSONObjectDecodeJwtPayload = decodeJwtPayload(accessToken);
        if (jSONObjectDecodeJwtPayload == null || (strOptString = jSONObjectDecodeJwtPayload.optString("email")) == null || kotlin.text.o.h0(strOptString)) {
            return null;
        }
        return strOptString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractUserIdFromAccessToken(String accessToken) throws Exception {
        String strOptString;
        JSONObject jSONObjectDecodeJwtPayload = decodeJwtPayload(accessToken);
        if (jSONObjectDecodeJwtPayload == null || (strOptString = jSONObjectDecodeJwtPayload.optString("sub")) == null || kotlin.text.o.h0(strOptString)) {
            return null;
        }
        return strOptString;
    }

    private final String generateNonce() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int i10 = 0;
        for (byte b10 : bArr) {
            i10++;
            if (i10 > 1) {
                sb2.append((CharSequence) "");
            }
            sb2.append(generateNonce$lambda$0(b10));
        }
        sb2.append((CharSequence) "");
        return sb2.toString();
    }

    private static final CharSequence generateNonce$lambda$0(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SupabaseClient getSupabase() {
        return (SupabaseClient) this.supabase.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 handleGoogleSignInResult_gIAlu_s$lambda$0(String str, IDToken.Config config) {
        config.setIdToken(str);
        config.setProvider(Google.INSTANCE);
        return t0.f22605a;
    }

    private final boolean isJwtExpired(String token, long bufferSeconds) throws Exception {
        try {
            List listY0 = kotlin.text.o.y0(token, new String[]{"."}, 0, 6);
            if (listY0.size() >= 2) {
                JSONObject jSONObject = new JSONObject(new String(Base64.decode((String) listY0.get(1), 11), kotlin.text.a.f19924a));
                if (jSONObject.has("exp")) {
                    long j10 = jSONObject.getLong("exp");
                    if (j10 > 0) {
                        wa.c.Companion.getClass();
                        if (j10 > Clock.systemUTC().instant().getEpochSecond() + bufferSeconds) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean isJwtExpired$default(AuthRepository authRepository, String str, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = 60;
        }
        return authRepository.isJwtExpired(str, j10);
    }

    private final boolean isSessionExpired(UserSession session, long bufferSeconds) {
        wa.c.Companion.getClass();
        return session.getExpiresAt().f22539i.getEpochSecond() <= new wa.c(Clock.systemUTC().instant()).f22539i.getEpochSecond() + bufferSeconds;
    }

    public static /* synthetic */ boolean isSessionExpired$default(AuthRepository authRepository, UserSession userSession, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = 60;
        }
        return authRepository.isSessionExpired(userSession, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010a, code lost:
    
        if (r0 == r8) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: loadAccountSyncPayloadFromAccountSyncState-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6085loadAccountSyncPayloadFromAccountSyncStategIAlus(java.lang.String r20, d7.d<? super x6.d0> r21) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6085loadAccountSyncPayloadFromAccountSyncStategIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: loadAccountSyncPayloadFromNetlify-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6086loadAccountSyncPayloadFromNetlifyIoAF18A(d7.d<? super x6.d0> r10) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6086loadAccountSyncPayloadFromNetlifyIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0109, code lost:
    
        if (r15 != r6) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: loadAccountSyncPayloadFromProfileAddons-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6087loadAccountSyncPayloadFromProfileAddonsIoAF18A(d7.d<? super x6.d0> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6087loadAccountSyncPayloadFromProfileAddonsIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x010b, code lost:
    
        if (r15 != r6) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: loadAccountSyncPayloadFromUserSettings-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6088loadAccountSyncPayloadFromUserSettingsIoAF18A(d7.d<? super x6.d0> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 421
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6088loadAccountSyncPayloadFromUserSettingsIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0148 A[Catch: Exception -> 0x003f, TryCatch #0 {Exception -> 0x003f, blocks: (B:15:0x003b, B:22:0x0052, B:37:0x0142, B:39:0x0148, B:42:0x0161, B:25:0x006b, B:31:0x00fa, B:33:0x0126, B:28:0x0073), top: B:51:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0161 A[Catch: Exception -> 0x003f, TRY_LEAVE, TryCatch #0 {Exception -> 0x003f, blocks: (B:15:0x003b, B:22:0x0052, B:37:0x0142, B:39:0x0148, B:42:0x0161, B:25:0x006b, B:31:0x00fa, B:33:0x0126, B:28:0x0073), top: B:51:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadUserProfile(java.lang.String r21, d7.d<? super com.arflix.tv.data.repository.UserProfile> r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.loadUserProfile(java.lang.String, d7.d):java.lang.Object");
    }

    private final long parseInstantMillis(String value) {
        long epochMilli = 0;
        if (value != null && !kotlin.text.o.h0(value)) {
            try {
                wa.c.Companion.getClass();
                Instant instant = wa.b.a(value).f22539i;
                try {
                    epochMilli = instant.toEpochMilli();
                } catch (ArithmeticException unused) {
                    epochMilli = instant.isAfter(Instant.EPOCH) ? Long.MAX_VALUE : Long.MIN_VALUE;
                }
            } catch (DateTimeParseException | IllegalArgumentException unused2) {
            }
        }
        return epochMilli;
    }

    private final long payloadUpdatedAtMillis(String payload) {
        Object c0Var;
        if (payload == null || kotlin.text.o.h0(payload)) {
            return 0L;
        }
        try {
            c0Var = Long.valueOf(new JSONObject(payload).optLong("updatedAt", 0L));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = 0L;
        }
        return ((Number) c0Var).longValue();
    }

    /* JADX INFO: renamed from: pullAccountSyncDelta-0E7RQCE$default, reason: not valid java name */
    public static /* synthetic */ Object m6089pullAccountSyncDelta0E7RQCE$default(AuthRepository authRepository, long j10, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 500;
        }
        return authRepository.m6101pullAccountSyncDelta0E7RQCE(j10, i10, dVar);
    }

    /* JADX INFO: renamed from: pullAccountSyncItems-BWLJW6A$default, reason: not valid java name */
    public static /* synthetic */ Object m6090pullAccountSyncItemsBWLJW6A$default(AuthRepository authRepository, String str, String str2, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = null;
        }
        if ((i11 & 2) != 0) {
            str2 = null;
        }
        if ((i11 & 4) != 0) {
            i10 = 1000;
        }
        return authRepository.m6102pullAccountSyncItemsBWLJW6A(str, str2, i10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object refreshNetlifyAccessToken(String str, d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new C06842(str, this, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object requestCloudAccountSession(String str, String str2, String str3, String str4, d7.d<? super CloudAccountSession> dVar) {
        return ka.m0.y(x0.f19655d, new C06852(str2, str3, str, this, str4, null), dVar);
    }

    private final String safeErrorMessage(Exception error, String fallback) {
        String message;
        if (error != null && (message = error.getMessage()) != null) {
            String lowerCase = message.toLowerCase(Locale.ROOT);
            if (kotlin.text.o.T(lowerCase, "arvio cloud moved", false) || kotlin.text.o.T(lowerCase, "password setup", false)) {
                return message;
            }
            if (Constants.INSTANCE.getUSE_NETLIFY_CLOUD_SYNC() && kotlin.text.o.T(lowerCase, "invalid email or password", false)) {
                return "Invalid email or password. If this is an existing ARVIO Cloud account, create a new password at auth.arvio.tv and then sign in again.";
            }
            if (kotlin.text.o.T(lowerCase, "database error saving new user", false)) {
                return this.context.getString(R.string.auth_account_exists);
            }
            if (kotlin.text.o.T(lowerCase, "settingssessionmanager", false)) {
                return this.context.getString(R.string.auth_signin_retry);
            }
            if (kotlin.text.o.T(lowerCase, "invalid login credentials", false)) {
                return this.context.getString(R.string.auth_invalid_credentials);
            }
            if (kotlin.text.o.T(lowerCase, "email not confirmed", false) || kotlin.text.o.T(lowerCase, "confirm", false)) {
                return this.context.getString(R.string.auth_verify_email);
            }
            if (kotlin.text.o.T(lowerCase, "user already", false) || kotlin.text.o.T(lowerCase, "already registered", false)) {
                return this.context.getString(R.string.auth_account_exists);
            }
        }
        return fallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x036c, code lost:
    
        if (r0 == r12) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01b1 A[Catch: Exception -> 0x0064, TRY_ENTER, TryCatch #0 {Exception -> 0x0064, blocks: (B:15:0x005f, B:49:0x036f, B:50:0x0371, B:22:0x008f, B:45:0x0283, B:25:0x00b0, B:39:0x017c, B:42:0x01b1, B:46:0x0287, B:28:0x00bf, B:35:0x00d9, B:31:0x00c8), top: B:57:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0287 A[Catch: Exception -> 0x0064, TryCatch #0 {Exception -> 0x0064, blocks: (B:15:0x005f, B:49:0x036f, B:50:0x0371, B:22:0x008f, B:45:0x0283, B:25:0x00b0, B:39:0x017c, B:42:0x01b1, B:46:0x0287, B:28:0x00bf, B:35:0x00d9, B:31:0x00c8), top: B:57:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveAccountSyncPayloadToAccountSyncState-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6091saveAccountSyncPayloadToAccountSyncState0E7RQCE(java.lang.String r30, java.lang.String r31, d7.d<? super x6.d0> r32) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6091saveAccountSyncPayloadToAccountSyncState0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX INFO: renamed from: saveAccountSyncPayloadToNetlify-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6092saveAccountSyncPayloadToNetlifygIAlus(java.lang.String r6, d7.d<? super x6.d0> r7) throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.String r0 = "Cloud sync upload rejected: "
            boolean r1 = r7 instanceof com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadToNetlify$1
            if (r1 == 0) goto L15
            r1 = r7
            com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadToNetlify$1 r1 = (com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadToNetlify$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadToNetlify$1 r1 = new com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadToNetlify$1
            r1.<init>(r5, r7)
        L1a:
            java.lang.Object r7 = r1.result
            int r2 = r1.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r6 = r1.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r1.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)     // Catch: java.lang.Exception -> L30
            goto L6f
        L30:
            r6 = move-exception
            goto L9e
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            k2.c.G(r7)
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Exception -> L30 org.json.JSONException -> L44
            r7.<init>(r6)     // Catch: java.lang.Exception -> L30 org.json.JSONException -> L44
            goto L45
        L44:
            r7 = r4
        L45:
            if (r7 != 0) goto L48
            goto L49
        L48:
            r6 = r7
        L49:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Exception -> L30
            r7.<init>()     // Catch: java.lang.Exception -> L30
            java.lang.String r2 = "payload"
            org.json.JSONObject r6 = r7.put(r2, r6)     // Catch: java.lang.Exception -> L30
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L30
            com.arflix.tv.util.Constants r7 = com.arflix.tv.util.Constants.INSTANCE     // Catch: java.lang.Exception -> L30
            java.lang.String r7 = r7.getNETLIFY_ACCOUNT_SYNC_PUSH_URL()     // Catch: java.lang.Exception -> L30
            r1.L$0 = r4     // Catch: java.lang.Exception -> L30
            r1.L$1 = r4     // Catch: java.lang.Exception -> L30
            r1.L$2 = r4     // Catch: java.lang.Exception -> L30
            r1.label = r3     // Catch: java.lang.Exception -> L30
            java.lang.Object r7 = r5.callNetlifyFunction(r7, r6, r1)     // Catch: java.lang.Exception -> L30
            e7.a r6 = e7.a.f15033i
            if (r7 != r6) goto L6f
            return r6
        L6f:
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L30
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Exception -> L30 org.json.JSONException -> L77
            r6.<init>(r7)     // Catch: java.lang.Exception -> L30 org.json.JSONException -> L77
            r4 = r6
        L77:
            if (r4 == 0) goto L86
            java.lang.String r6 = "accepted"
            r7 = 0
            boolean r6 = r4.optBoolean(r6, r7)     // Catch: java.lang.Exception -> L30
            if (r6 != 0) goto L83
            goto L86
        L83:
            x6.t0 r6 = x6.t0.f22605a     // Catch: java.lang.Exception -> L30
            goto La8
        L86:
            java.lang.String r6 = "invalid_response"
            if (r4 == 0) goto L94
            java.lang.String r7 = "reason"
            java.lang.String r7 = r4.optString(r7, r6)     // Catch: java.lang.Exception -> L30
            if (r7 != 0) goto L93
            goto L94
        L93:
            r6 = r7
        L94:
            com.arflix.tv.data.repository.AccountSyncPayloadRejectedException r7 = new com.arflix.tv.data.repository.AccountSyncPayloadRejectedException     // Catch: java.lang.Exception -> L30
            java.lang.String r6 = r0.concat(r6)     // Catch: java.lang.Exception -> L30
            r7.<init>(r6)     // Catch: java.lang.Exception -> L30
            throw r7     // Catch: java.lang.Exception -> L30
        L9e:
            boolean r7 = r6 instanceof java.util.concurrent.CancellationException
            if (r7 != 0) goto La9
            x6.c0 r7 = new x6.c0
            r7.<init>(r6)
            r6 = r7
        La8:
            return r6
        La9:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6092saveAccountSyncPayloadToNetlifygIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(2:4|(1:6)(1:7))(0)|8|75|(1:(1:(1:(10:13|14|15|52|(3:58|(1:60)|(1:62)(1:63))|57|64|(1:66)(1:67)|68|81)(2:18|19))(10:20|77|21|37|(1:39)(1:41)|45|(1:47)|48|(8:51|52|(5:54|56|58|(0)|(0)(0))(0)|57|64|(0)(0)|68|81)|50))(2:25|26))(4:28|29|(1:32)|50)|79|33|(7:36|37|(0)(0)|45|(0)|48|(0))|50) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x016d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016e, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0166 A[Catch: all -> 0x0089, TRY_LEAVE, TryCatch #1 {all -> 0x0089, blocks: (B:21:0x0084, B:37:0x013a, B:39:0x0166), top: B:77:0x0084 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0235 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0053, B:52:0x0222, B:54:0x022c, B:64:0x024c, B:66:0x0250, B:68:0x0266, B:58:0x0235, B:60:0x023f, B:62:0x0244, B:45:0x0175, B:48:0x017b, B:44:0x016f, B:26:0x0094, B:29:0x009d), top: B:75:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x023f A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0053, B:52:0x0222, B:54:0x022c, B:64:0x024c, B:66:0x0250, B:68:0x0266, B:58:0x0235, B:60:0x023f, B:62:0x0244, B:45:0x0175, B:48:0x017b, B:44:0x016f, B:26:0x0094, B:29:0x009d), top: B:75:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0244 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0053, B:52:0x0222, B:54:0x022c, B:64:0x024c, B:66:0x0250, B:68:0x0266, B:58:0x0235, B:60:0x023f, B:62:0x0244, B:45:0x0175, B:48:0x017b, B:44:0x016f, B:26:0x0094, B:29:0x009d), top: B:75:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0250 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0053, B:52:0x0222, B:54:0x022c, B:64:0x024c, B:66:0x0250, B:68:0x0266, B:58:0x0235, B:60:0x023f, B:62:0x0244, B:45:0x0175, B:48:0x017b, B:44:0x016f, B:26:0x0094, B:29:0x009d), top: B:75:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveAccountSyncPayloadToProfileAddons-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6093saveAccountSyncPayloadToProfileAddons0E7RQCE(java.lang.String r24, java.lang.String r25, d7.d<? super x6.d0> r26) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 651
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6093saveAccountSyncPayloadToProfileAddons0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:79|(1:(1:(1:(1:(5:14|15|67|68|82)(2:19|20))(5:21|22|63|68|82))(12:24|77|25|41|(1:43)(1:45)|49|(1:51)|52|(3:54|(2:57|55)|81)|58|(5:60|(1:62)|63|68|82)(1:64)|66))(2:29|30))(4:32|33|(1:36)|66)|75|37|(9:40|41|(0)(0)|49|(0)|52|(0)|58|(0)(0))|66) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01a0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01a1, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0365, code lost:
    
        if (r0 == r12) goto L66;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0199 A[Catch: all -> 0x00ba, TRY_LEAVE, TryCatch #1 {all -> 0x00ba, blocks: (B:25:0x00b5, B:41:0x016d, B:43:0x0199), top: B:77:0x00b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b7 A[Catch: Exception -> 0x0065, TryCatch #2 {Exception -> 0x0065, blocks: (B:15:0x0060, B:67:0x0368, B:68:0x036a, B:22:0x0090, B:63:0x02b5, B:49:0x01a8, B:52:0x01ae, B:54:0x01b7, B:55:0x01c1, B:57:0x01c7, B:58:0x01e0, B:60:0x0212, B:64:0x02b9, B:48:0x01a2, B:30:0x00c5, B:33:0x00ce), top: B:79:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0212 A[Catch: Exception -> 0x0065, TryCatch #2 {Exception -> 0x0065, blocks: (B:15:0x0060, B:67:0x0368, B:68:0x036a, B:22:0x0090, B:63:0x02b5, B:49:0x01a8, B:52:0x01ae, B:54:0x01b7, B:55:0x01c1, B:57:0x01c7, B:58:0x01e0, B:60:0x0212, B:64:0x02b9, B:48:0x01a2, B:30:0x00c5, B:33:0x00ce), top: B:79:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02b9 A[Catch: Exception -> 0x0065, TryCatch #2 {Exception -> 0x0065, blocks: (B:15:0x0060, B:67:0x0368, B:68:0x036a, B:22:0x0090, B:63:0x02b5, B:49:0x01a8, B:52:0x01ae, B:54:0x01b7, B:55:0x01c1, B:57:0x01c7, B:58:0x01e0, B:60:0x0212, B:64:0x02b9, B:48:0x01a2, B:30:0x00c5, B:33:0x00ce), top: B:79:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveAccountSyncPayloadToUserSettings-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6094saveAccountSyncPayloadToUserSettings0E7RQCE(java.lang.String r26, java.lang.String r27, d7.d<? super x6.d0> r28) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6094saveAccountSyncPayloadToUserSettings0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ad, code lost:
    
        if (r10 != r6) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: saveAccountSyncPayloadViaRpc-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6095saveAccountSyncPayloadViaRpc0E7RQCE(java.lang.String r9, java.lang.String r10, d7.d<? super x6.d0> r11) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6095saveAccountSyncPayloadViaRpc0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object signInCloudAccountSession(String str, String str2, d7.d<? super CloudAccountSession> dVar) {
        return requestCloudAccountSession(Constants.INSTANCE.getAUTH_LOGIN_URL(), str, str2, this.context.getString(R.string.auth_signin_failed), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 signIn_0E7RQCE$lambda$2(String str, String str2, Email.Config config) {
        config.setEmail(str);
        config.setPassword(str2);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object storeRawSessionTokens(String str, String str2, String str3, String str4, d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(DataStoresKt.getAuthDataStore(this.context), new C06892(str, str2, str3, str4, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
    
        if (r7.saveSession(r6, r0) == r4) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
    
        if (androidx.work.impl.t.o(r7, r1, r0) == r4) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
    
        return r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object storeSession(io.github.jan.supabase.gotrue.user.UserSession r6, d7.d<? super x6.t0> r7) throws java.lang.Exception {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.AuthRepository.C06901
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.AuthRepository$storeSession$1 r0 = (com.arflix.tv.data.repository.AuthRepository.C06901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$storeSession$1 r0 = new com.arflix.tv.data.repository.AuthRepository$storeSession$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$0
            io.github.jan.supabase.gotrue.user.UserSession r6 = (io.github.jan.supabase.gotrue.user.UserSession) r6
            k2.c.G(r7)
            goto L6d
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.L$0
            io.github.jan.supabase.gotrue.user.UserSession r6 = (io.github.jan.supabase.gotrue.user.UserSession) r6
            k2.c.G(r7)     // Catch: java.lang.Exception -> L3e java.util.concurrent.CancellationException -> L40
            goto L56
        L3e:
            r7 = move-exception
            goto L52
        L40:
            r6 = move-exception
            goto L71
        L42:
            k2.c.G(r7)
            com.arflix.tv.data.repository.DataStoreSessionManager r7 = r5.sessionManager     // Catch: java.lang.Exception -> L3e java.util.concurrent.CancellationException -> L40
            r0.L$0 = r6     // Catch: java.lang.Exception -> L3e java.util.concurrent.CancellationException -> L40
            r0.label = r3     // Catch: java.lang.Exception -> L3e java.util.concurrent.CancellationException -> L40
            java.lang.Object r7 = r7.saveSession(r6, r0)     // Catch: java.lang.Exception -> L3e java.util.concurrent.CancellationException -> L40
            if (r7 != r4) goto L56
            goto L6c
        L52:
            boolean r1 = r7 instanceof java.util.concurrent.CancellationException
            if (r1 != 0) goto L70
        L56:
            android.content.Context r7 = r5.context
            z0.g r7 = com.arflix.tv.util.DataStoresKt.getAuthDataStore(r7)
            com.arflix.tv.data.repository.AuthRepository$storeSession$2 r1 = new com.arflix.tv.data.repository.AuthRepository$storeSession$2
            r3 = 0
            r1.<init>(r6, r3)
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = androidx.work.impl.t.o(r7, r1, r0)
            if (r6 != r4) goto L6d
        L6c:
            return r4
        L6d:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L70:
            throw r7
        L71:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.storeSession(io.github.jan.supabase.gotrue.user.UserSession, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SupabaseClient supabase_delegate$lambda$0(AuthRepository authRepository) {
        Constants constants = Constants.INSTANCE;
        SupabaseClientBuilder supabaseClientBuilder = new SupabaseClientBuilder(constants.getSUPABASE_URL(), constants.getSUPABASE_ANON_KEY());
        supabaseClientBuilder.install(Auth.INSTANCE, new d0(authRepository, 1));
        SupabaseClientBuilder.install$default(supabaseClientBuilder, Postgrest.INSTANCE, null, 2, null);
        return supabaseClientBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 supabase_delegate$lambda$0$0$0(AuthRepository authRepository, AuthConfig authConfig) {
        authConfig.setSessionManager(authRepository.sessionManager);
        authConfig.setCodeVerifierCache(new MemoryCodeVerifierCache());
        authConfig.setAutoLoadFromStorage(true);
        authConfig.setAutoSaveToStorage(true);
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0315 A[Catch: Exception -> 0x0060, TryCatch #0 {Exception -> 0x0060, blocks: (B:14:0x005b, B:232:0x05b9, B:19:0x008b, B:228:0x0590, B:223:0x054c, B:224:0x0562, B:25:0x00de, B:210:0x04ff, B:28:0x0107, B:207:0x04d8, B:31:0x012c, B:179:0x0472, B:184:0x047d, B:186:0x0483, B:188:0x048e, B:190:0x0494, B:194:0x049d, B:196:0x04a3, B:203:0x04b1, B:233:0x05c9, B:176:0x044f, B:169:0x0442, B:172:0x0447, B:147:0x03e6, B:234:0x05d1, B:47:0x01ba, B:134:0x03a4, B:50:0x01e5, B:130:0x0361, B:53:0x0204, B:96:0x02e5, B:100:0x02fd, B:105:0x0308, B:111:0x0315, B:116:0x0321, B:120:0x032a, B:123:0x0331, B:136:0x03b4, B:54:0x0209, B:60:0x0224, B:62:0x026a, B:67:0x0274, B:69:0x0278, B:74:0x0284, B:81:0x0296, B:92:0x02bf, B:57:0x0210), top: B:241:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0447 A[Catch: Exception -> 0x0060, TryCatch #0 {Exception -> 0x0060, blocks: (B:14:0x005b, B:232:0x05b9, B:19:0x008b, B:228:0x0590, B:223:0x054c, B:224:0x0562, B:25:0x00de, B:210:0x04ff, B:28:0x0107, B:207:0x04d8, B:31:0x012c, B:179:0x0472, B:184:0x047d, B:186:0x0483, B:188:0x048e, B:190:0x0494, B:194:0x049d, B:196:0x04a3, B:203:0x04b1, B:233:0x05c9, B:176:0x044f, B:169:0x0442, B:172:0x0447, B:147:0x03e6, B:234:0x05d1, B:47:0x01ba, B:134:0x03a4, B:50:0x01e5, B:130:0x0361, B:53:0x0204, B:96:0x02e5, B:100:0x02fd, B:105:0x0308, B:111:0x0315, B:116:0x0321, B:120:0x032a, B:123:0x0331, B:136:0x03b4, B:54:0x0209, B:60:0x0224, B:62:0x026a, B:67:0x0274, B:69:0x0278, B:74:0x0284, B:81:0x0296, B:92:0x02bf, B:57:0x0210), top: B:241:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x044d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0475 A[PHI: r2 r3 r4 r5 r9
      0x0475: PHI (r2v10 int) = (r2v7 int), (r2v7 int), (r2v14 int) binds: [B:174:0x044b, B:175:0x044d, B:179:0x0472] A[DONT_GENERATE, DONT_INLINE]
      0x0475: PHI (r3v5 ??) = (r3v2 ??), (r3v2 ??), (r3v7 ??) binds: [B:174:0x044b, B:175:0x044d, B:179:0x0472] A[DONT_GENERATE, DONT_INLINE]
      0x0475: PHI (r4v6 java.lang.String) = (r4v3 java.lang.String), (r4v3 java.lang.String), (r4v10 java.lang.String) binds: [B:174:0x044b, B:175:0x044d, B:179:0x0472] A[DONT_GENERATE, DONT_INLINE]
      0x0475: PHI (r5v6 ??) = (r5v1 ??), (r5v1 ??), (r5v13 ??) binds: [B:174:0x044b, B:175:0x044d, B:179:0x0472] A[DONT_GENERATE, DONT_INLINE]
      0x0475: PHI (r9v6 ??) = (r9v2 ??), (r9v2 ??), (r9v8 ??) binds: [B:174:0x044b, B:175:0x044d, B:179:0x0472] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0477 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x049d A[Catch: Exception -> 0x0060, TryCatch #0 {Exception -> 0x0060, blocks: (B:14:0x005b, B:232:0x05b9, B:19:0x008b, B:228:0x0590, B:223:0x054c, B:224:0x0562, B:25:0x00de, B:210:0x04ff, B:28:0x0107, B:207:0x04d8, B:31:0x012c, B:179:0x0472, B:184:0x047d, B:186:0x0483, B:188:0x048e, B:190:0x0494, B:194:0x049d, B:196:0x04a3, B:203:0x04b1, B:233:0x05c9, B:176:0x044f, B:169:0x0442, B:172:0x0447, B:147:0x03e6, B:234:0x05d1, B:47:0x01ba, B:134:0x03a4, B:50:0x01e5, B:130:0x0361, B:53:0x0204, B:96:0x02e5, B:100:0x02fd, B:105:0x0308, B:111:0x0315, B:116:0x0321, B:120:0x032a, B:123:0x0331, B:136:0x03b4, B:54:0x0209, B:60:0x0224, B:62:0x026a, B:67:0x0274, B:69:0x0278, B:74:0x0284, B:81:0x0296, B:92:0x02bf, B:57:0x0210), top: B:241:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x04ad A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x054c A[Catch: Exception -> 0x0060, TRY_ENTER, TryCatch #0 {Exception -> 0x0060, blocks: (B:14:0x005b, B:232:0x05b9, B:19:0x008b, B:228:0x0590, B:223:0x054c, B:224:0x0562, B:25:0x00de, B:210:0x04ff, B:28:0x0107, B:207:0x04d8, B:31:0x012c, B:179:0x0472, B:184:0x047d, B:186:0x0483, B:188:0x048e, B:190:0x0494, B:194:0x049d, B:196:0x04a3, B:203:0x04b1, B:233:0x05c9, B:176:0x044f, B:169:0x0442, B:172:0x0447, B:147:0x03e6, B:234:0x05d1, B:47:0x01ba, B:134:0x03a4, B:50:0x01e5, B:130:0x0361, B:53:0x0204, B:96:0x02e5, B:100:0x02fd, B:105:0x0308, B:111:0x0315, B:116:0x0321, B:120:0x032a, B:123:0x0331, B:136:0x03b4, B:54:0x0209, B:60:0x0224, B:62:0x026a, B:67:0x0274, B:69:0x0278, B:74:0x0284, B:81:0x0296, B:92:0x02bf, B:57:0x0210), top: B:241:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x05d1 A[Catch: Exception -> 0x0060, TRY_LEAVE, TryCatch #0 {Exception -> 0x0060, blocks: (B:14:0x005b, B:232:0x05b9, B:19:0x008b, B:228:0x0590, B:223:0x054c, B:224:0x0562, B:25:0x00de, B:210:0x04ff, B:28:0x0107, B:207:0x04d8, B:31:0x012c, B:179:0x0472, B:184:0x047d, B:186:0x0483, B:188:0x048e, B:190:0x0494, B:194:0x049d, B:196:0x04a3, B:203:0x04b1, B:233:0x05c9, B:176:0x044f, B:169:0x0442, B:172:0x0447, B:147:0x03e6, B:234:0x05d1, B:47:0x01ba, B:134:0x03a4, B:50:0x01e5, B:130:0x0361, B:53:0x0204, B:96:0x02e5, B:100:0x02fd, B:105:0x0308, B:111:0x0315, B:116:0x0321, B:120:0x032a, B:123:0x0331, B:136:0x03b4, B:54:0x0209, B:60:0x0224, B:62:0x026a, B:67:0x0274, B:69:0x0278, B:74:0x0284, B:81:0x0296, B:92:0x02bf, B:57:0x0210), top: B:241:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x041a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02fb  */
    /* JADX WARN: Type inference failed for: r0v76 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v46 */
    /* JADX WARN: Type inference failed for: r10v47 */
    /* JADX WARN: Type inference failed for: r10v48 */
    /* JADX WARN: Type inference failed for: r10v49 */
    /* JADX WARN: Type inference failed for: r10v50 */
    /* JADX WARN: Type inference failed for: r10v51 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r25v0, types: [com.arflix.tv.data.repository.AuthRepository] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v46, types: [int] */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v61 */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r3v63 */
    /* JADX WARN: Type inference failed for: r3v64 */
    /* JADX WARN: Type inference failed for: r3v65 */
    /* JADX WARN: Type inference failed for: r3v66 */
    /* JADX WARN: Type inference failed for: r3v67 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v19, types: [com.arflix.tv.data.repository.TraktRepository] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v24, types: [int] */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r4v46 */
    /* JADX WARN: Type inference failed for: r4v47 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v75 */
    /* JADX WARN: Type inference failed for: r4v78 */
    /* JADX WARN: Type inference failed for: r4v79 */
    /* JADX WARN: Type inference failed for: r4v80 */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v13, types: [io.github.jan.supabase.gotrue.user.UserSession] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [io.github.jan.supabase.gotrue.user.UserSession] */
    /* JADX WARN: Type inference failed for: r5v69, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v85 */
    /* JADX WARN: Type inference failed for: r5v86 */
    /* JADX WARN: Type inference failed for: r5v87 */
    /* JADX WARN: Type inference failed for: r5v88 */
    /* JADX WARN: Type inference failed for: r5v89 */
    /* JADX WARN: Type inference failed for: r5v90 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v54 */
    /* JADX WARN: Type inference failed for: r9v55 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkAuthState(d7.d<? super x6.t0> r26) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 1536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.checkAuthState(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:
    
        if (r1 != r11) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f0 A[PHI: r1
      0x00f0: PHI (r1v24 java.lang.Object) = (r1v22 java.lang.Object), (r1v0 java.lang.Object) binds: [B:52:0x00ed, B:20:0x004a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0126 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAccessToken(d7.d<? super java.lang.String> r13) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.getAccessToken(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:
    
        if (r10 == r8) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: getAccountSyncEventCursor-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6096getAccountSyncEventCursorIoAF18A(d7.d<? super x6.d0> r10) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6096getAccountSyncEventCursorIoAF18A(d7.d):java.lang.Object");
    }

    public final String getAddonsFromProfile() {
        UserProfile userProfile = (UserProfile) this._userProfile.getValue();
        if (userProfile != null) {
            return userProfile.getAddons();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0171 A[Catch: Exception -> 0x0049, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017b A[Catch: Exception -> 0x0049, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0181 A[Catch: Exception -> 0x0049, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0197 A[Catch: Exception -> 0x0049, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01aa A[Catch: Exception -> 0x0049, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c3 A[Catch: Exception -> 0x0049, TRY_LEAVE, TryCatch #0 {Exception -> 0x0049, blocks: (B:13:0x0043, B:44:0x0134, B:46:0x0168, B:61:0x0193, B:63:0x0197, B:66:0x01aa, B:68:0x01b1, B:70:0x01c3, B:50:0x0171, B:52:0x017b, B:55:0x0181, B:57:0x0188, B:20:0x0058, B:36:0x009a, B:38:0x009e, B:40:0x00ab, B:32:0x0089), top: B:78:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: getAddonsFromProfileFresh-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6097getAddonsFromProfileFreshIoAF18A(d7.d<? super x6.d0> r20) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6097getAddonsFromProfileFreshIoAF18A(d7.d):java.lang.Object");
    }

    public final h1<AuthState> getAuthState() {
        return this.authState;
    }

    public final Boolean getAutoPlayNextFromProfile() {
        UserProfile userProfile = (UserProfile) this._userProfile.getValue();
        if (userProfile != null) {
            return userProfile.getAuto_play_next();
        }
        return null;
    }

    public final String getCurrentUserEmail() {
        String email;
        AuthState authState = (AuthState) this._authState.getValue();
        if (authState instanceof AuthState.Authenticated) {
            email = ((AuthState.Authenticated) authState).getEmail();
        } else {
            UserProfile userProfile = (UserProfile) this._userProfile.getValue();
            email = userProfile != null ? userProfile.getEmail() : null;
        }
        if (email == null || kotlin.text.o.h0(email)) {
            return null;
        }
        return email;
    }

    public final String getCurrentUserId() {
        AuthState authState = (AuthState) this._authState.getValue();
        if (authState instanceof AuthState.Authenticated) {
            return ((AuthState.Authenticated) authState).getUserId();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0086, code lost:
    
        if (r1 == r10) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x014b, code lost:
    
        if (r1 == r10) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01a4, code lost:
    
        if (r1 != r10) goto L96;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010a A[PHI: r1
      0x010a: PHI (r1v26 java.lang.Object) = (r1v11 java.lang.Object), (r1v1 java.lang.Object) binds: [B:61:0x0106, B:18:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0176  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentUserIdForSync(d7.d<? super java.lang.String> r24) {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.getCurrentUserIdForSync(d7.d):java.lang.Object");
    }

    public final String getDefaultSubtitleFromProfile() {
        UserProfile userProfile = (UserProfile) this._userProfile.getValue();
        if (userProfile != null) {
            return userProfile.getDefault_subtitle();
        }
        return null;
    }

    public final GetCredentialRequest getGoogleSignInRequest() {
        return new GetCredentialRequest.Builder().addCredentialOption(new v4.b(Constants.INSTANCE.getGOOGLE_WEB_CLIENT_ID(), generateNonce())).build();
    }

    public final String getTraktAccessToken() {
        db.b0 trakt_token;
        db.m mVar;
        UserProfile userProfile = (UserProfile) this._userProfile.getValue();
        if (userProfile == null || (trakt_token = userProfile.getTrakt_token()) == null || (mVar = (db.m) trakt_token.get("access_token")) == null) {
            return null;
        }
        return db.n.g(mVar).b();
    }

    public final h1<UserProfile> getUserProfile() {
        return this.userProfile;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0167, code lost:
    
        if (r0 == r15) goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0144 A[Catch: Exception -> 0x0056, GoogleIdTokenParsingException -> 0x0059, PHI: r0 r2
      0x0144: PHI (r0v25 java.lang.Object) = (r0v24 java.lang.Object), (r0v1 java.lang.Object) binds: [B:50:0x0141, B:25:0x007c] A[DONT_GENERATE, DONT_INLINE]
      0x0144: PHI (r2v29 io.github.jan.supabase.gotrue.user.UserInfo) = (r2v28 io.github.jan.supabase.gotrue.user.UserInfo), (r2v44 io.github.jan.supabase.gotrue.user.UserInfo) binds: [B:50:0x0141, B:25:0x007c] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {GoogleIdTokenParsingException -> 0x0059, Exception -> 0x0056, blocks: (B:16:0x0051, B:60:0x016a, B:61:0x016c, B:65:0x0181, B:25:0x007c, B:52:0x0144, B:54:0x0148, B:57:0x0153, B:28:0x0099, B:49:0x012b, B:31:0x00ae, B:41:0x0100, B:43:0x010e, B:46:0x0116, B:67:0x018a, B:34:0x00b5, B:36:0x00c4, B:38:0x00d0, B:69:0x01a7, B:71:0x01c4), top: B:82:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0148 A[Catch: Exception -> 0x0056, GoogleIdTokenParsingException -> 0x0059, TryCatch #2 {GoogleIdTokenParsingException -> 0x0059, Exception -> 0x0056, blocks: (B:16:0x0051, B:60:0x016a, B:61:0x016c, B:65:0x0181, B:25:0x007c, B:52:0x0144, B:54:0x0148, B:57:0x0153, B:28:0x0099, B:49:0x012b, B:31:0x00ae, B:41:0x0100, B:43:0x010e, B:46:0x0116, B:67:0x018a, B:34:0x00b5, B:36:0x00c4, B:38:0x00d0, B:69:0x01a7, B:71:0x01c4), top: B:82:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX INFO: renamed from: handleGoogleSignInResult-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6098handleGoogleSignInResultgIAlus(androidx.credentials.GetCredentialResponse r17, d7.d<? super x6.d0> r18) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6098handleGoogleSignInResultgIAlus(androidx.credentials.GetCredentialResponse, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hasValidCloudSyncSession(d7.d<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.AuthRepository.C06811
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.AuthRepository$hasValidCloudSyncSession$1 r0 = (com.arflix.tv.data.repository.AuthRepository.C06811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$hasValidCloudSyncSession$1 r0 = new com.arflix.tv.data.repository.AuthRepository$hasValidCloudSyncSession$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.getAccessToken(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            if (r5 == 0) goto L48
            boolean r5 = kotlin.text.o.h0(r5)
            if (r5 == 0) goto L46
            goto L48
        L46:
            r5 = 0
            goto L49
        L48:
            r5 = r2
        L49:
            r5 = r5 ^ r2
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.hasValidCloudSyncSession(d7.d):java.lang.Object");
    }

    public final boolean isTraktLinked() {
        UserProfile userProfile = (UserProfile) this._userProfile.getValue();
        return (userProfile != null ? userProfile.getTrakt_token() : null) != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00df, code lost:
    
        if (r0 == r10) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0144, code lost:
    
        if (r0 == r10) goto L97;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f3  */
    /* JADX INFO: renamed from: loadAccountSyncPayload-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6099loadAccountSyncPayloadIoAF18A(d7.d<? super x6.d0> r20) {
        /*
            Method dump skipped, instruction units count: 848
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6099loadAccountSyncPayloadIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(2:4|(1:6)(1:7))(0)|8|(1:78)|(1:(3:(1:(1:(5:14|15|63|64|65)(2:19|20))(11:21|74|22|23|47|(1:49)|50|(1:52)|53|(1:55)(3:72|57|58)|60))(6:26|76|27|28|40|(3:42|64|65)(2:43|(2:45|62)(8:46|47|(0)|50|(0)|53|(0)(0)|60)))|68|69)(1:31))(3:32|(1:35)|62)|70|36|(3:39|40|(0)(0))|62) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x014b, code lost:
    
        if (r14 != r9) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0153, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0154, code lost:
    
        r11 = r14;
        r14 = r13;
        r13 = r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d2 A[Catch: all -> 0x004e, TryCatch #4 {all -> 0x004e, blocks: (B:15:0x0045, B:47:0x00f6, B:50:0x00fb, B:53:0x0101, B:55:0x0107, B:60:0x0119, B:57:0x010d, B:59:0x0114, B:40:0x00ce, B:42:0x00d2, B:43:0x00e0), top: B:78:0x0027, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e0 A[Catch: all -> 0x004e, TryCatch #4 {all -> 0x004e, blocks: (B:15:0x0045, B:47:0x00f6, B:50:0x00fb, B:53:0x0101, B:55:0x0107, B:60:0x0119, B:57:0x010d, B:59:0x0114, B:40:0x00ce, B:42:0x00d2, B:43:0x00e0), top: B:78:0x0027, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0107 A[Catch: all -> 0x004e, TRY_LEAVE, TryCatch #4 {all -> 0x004e, blocks: (B:15:0x0045, B:47:0x00f6, B:50:0x00fb, B:53:0x0101, B:55:0x0107, B:60:0x0119, B:57:0x010d, B:59:0x0114, B:40:0x00ce, B:42:0x00d2, B:43:0x00e0), top: B:78:0x0027, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.arflix.tv.data.repository.AuthRepository] */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.Object, r7.l<? super org.json.JSONObject, x6.t0>] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v2, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v30 */
    /* JADX WARN: Type inference failed for: r13v31 */
    /* JADX WARN: Type inference failed for: r13v32 */
    /* JADX WARN: Type inference failed for: r13v33 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v9, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v4, types: [r7.l] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX INFO: renamed from: mutateAccountSyncPayload-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6100mutateAccountSyncPayloadgIAlus(r7.l<? super org.json.JSONObject, x6.t0> r13, d7.d<? super x6.d0> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6100mutateAccountSyncPayloadgIAlus(r7.l, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c5, code lost:
    
        if (r15 == r8) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pullAccountSyncDelta-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6101pullAccountSyncDelta0E7RQCE(long r12, int r14, d7.d<? super x6.d0> r15) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6101pullAccountSyncDelta0E7RQCE(long, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006a A[Catch: all -> 0x0036, TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:43:0x00ae, B:19:0x0044, B:21:0x004c, B:23:0x0056, B:25:0x0060, B:31:0x006c, B:33:0x0074, B:39:0x0080, B:38:0x007e, B:30:0x006a), top: B:47:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074 A[Catch: all -> 0x0036, TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:43:0x00ae, B:19:0x0044, B:21:0x004c, B:23:0x0056, B:25:0x0060, B:31:0x006c, B:33:0x0074, B:39:0x0080, B:38:0x007e, B:30:0x006a), top: B:47:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007e A[Catch: all -> 0x0036, TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:43:0x00ae, B:19:0x0044, B:21:0x004c, B:23:0x0056, B:25:0x0060, B:31:0x006c, B:33:0x0074, B:39:0x0080, B:38:0x007e, B:30:0x006a), top: B:47:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pullAccountSyncItems-BWLJW6A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6102pullAccountSyncItemsBWLJW6A(java.lang.String r6, java.lang.String r7, int r8, d7.d<? super x6.d0> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.AuthRepository$pullAccountSyncItems$1
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.AuthRepository$pullAccountSyncItems$1 r0 = (com.arflix.tv.data.repository.AuthRepository$pullAccountSyncItems$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$pullAccountSyncItems$1 r0 = new com.arflix.tv.data.repository.AuthRepository$pullAccountSyncItems$1
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L41
            if (r1 != r2) goto L39
            java.lang.Object r6 = r0.L$3
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$2
            com.arflix.tv.data.repository.AuthRepository r6 = (com.arflix.tv.data.repository.AuthRepository) r6
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L36
            goto Lae
        L36:
            r6 = move-exception
            goto Lb1
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            k2.c.G(r9)
            com.arflix.tv.util.Constants r9 = com.arflix.tv.util.Constants.INSTANCE     // Catch: java.lang.Throwable -> L36
            boolean r9 = r9.getUSE_NETLIFY_CLOUD_SYNC()     // Catch: java.lang.Throwable -> L36
            if (r9 == 0) goto L56
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L36
            r6.<init>()     // Catch: java.lang.Throwable -> L36
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L36
            return r6
        L56:
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L36
            r9.<init>()     // Catch: java.lang.Throwable -> L36
            java.lang.String r1 = "p_scope"
            r3 = 0
            if (r6 == 0) goto L6a
            boolean r4 = kotlin.text.o.h0(r6)     // Catch: java.lang.Throwable -> L36
            if (r4 != 0) goto L67
            goto L68
        L67:
            r6 = r3
        L68:
            if (r6 != 0) goto L6c
        L6a:
            java.lang.Object r6 = org.json.JSONObject.NULL     // Catch: java.lang.Throwable -> L36
        L6c:
            org.json.JSONObject r6 = r9.put(r1, r6)     // Catch: java.lang.Throwable -> L36
            java.lang.String r9 = "p_profile_id"
            if (r7 == 0) goto L7e
            boolean r1 = kotlin.text.o.h0(r7)     // Catch: java.lang.Throwable -> L36
            if (r1 != 0) goto L7b
            goto L7c
        L7b:
            r7 = r3
        L7c:
            if (r7 != 0) goto L80
        L7e:
            java.lang.Object r7 = org.json.JSONObject.NULL     // Catch: java.lang.Throwable -> L36
        L80:
            org.json.JSONObject r6 = r6.put(r9, r7)     // Catch: java.lang.Throwable -> L36
            java.lang.String r7 = "p_limit"
            r9 = 5000(0x1388, float:7.006E-42)
            int r9 = qb.d.n(r8, r2, r9)     // Catch: java.lang.Throwable -> L36
            org.json.JSONObject r6 = r6.put(r7, r9)     // Catch: java.lang.Throwable -> L36
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L36
            java.lang.String r7 = "pull_account_sync_items"
            r0.L$0 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$1 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$2 = r3     // Catch: java.lang.Throwable -> L36
            r0.L$3 = r3     // Catch: java.lang.Throwable -> L36
            r0.I$0 = r8     // Catch: java.lang.Throwable -> L36
            r8 = 0
            r0.I$1 = r8     // Catch: java.lang.Throwable -> L36
            r0.label = r2     // Catch: java.lang.Throwable -> L36
            java.lang.Object r9 = r5.callSupabaseRpc(r7, r6, r0)     // Catch: java.lang.Throwable -> L36
            e7.a r6 = e7.a.f15033i
            if (r9 != r6) goto Lae
            return r6
        Lae:
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Throwable -> L36
            return r9
        Lb1:
            x6.c0 r7 = new x6.c0
            r7.<init>(r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6102pullAccountSyncItemsBWLJW6A(java.lang.String, java.lang.String, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pushAccountSyncItems-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6103pushAccountSyncItemsgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.AuthRepository$pushAccountSyncItems$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.AuthRepository$pushAccountSyncItems$1 r0 = (com.arflix.tv.data.repository.AuthRepository$pushAccountSyncItems$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AuthRepository$pushAccountSyncItems$1 r0 = new com.arflix.tv.data.repository.AuthRepository$pushAccountSyncItems$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3f
            if (r1 != r2) goto L37
            java.lang.Object r5 = r0.L$3
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$2
            org.json.JSONArray r5 = (org.json.JSONArray) r5
            java.lang.Object r5 = r0.L$1
            com.arflix.tv.data.repository.AuthRepository r5 = (com.arflix.tv.data.repository.AuthRepository) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L35
            goto L92
        L35:
            r5 = move-exception
            goto L95
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3f:
            k2.c.G(r6)
            com.arflix.tv.util.Constants r6 = com.arflix.tv.util.Constants.INSTANCE     // Catch: java.lang.Throwable -> L35
            boolean r6 = r6.getUSE_NETLIFY_CLOUD_SYNC()     // Catch: java.lang.Throwable -> L35
            if (r6 == 0) goto L65
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L35
            r5.<init>()     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "accepted"
            org.json.JSONObject r5 = r5.put(r6, r2)     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "events"
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L35
            r0.<init>()     // Catch: java.lang.Throwable -> L35
            org.json.JSONObject r5 = r5.put(r6, r0)     // Catch: java.lang.Throwable -> L35
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L35
            return r5
        L65:
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L35
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L35
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L35
            r5.<init>()     // Catch: java.lang.Throwable -> L35
            java.lang.String r1 = "p_items"
            org.json.JSONObject r5 = r5.put(r1, r6)     // Catch: java.lang.Throwable -> L35
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "push_account_sync_items"
            r1 = 0
            r0.L$0 = r1     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r1     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r1     // Catch: java.lang.Throwable -> L35
            r0.L$3 = r1     // Catch: java.lang.Throwable -> L35
            r1 = 0
            r0.I$0 = r1     // Catch: java.lang.Throwable -> L35
            r0.label = r2     // Catch: java.lang.Throwable -> L35
            java.lang.Object r6 = r4.callSupabaseRpc(r6, r5, r0)     // Catch: java.lang.Throwable -> L35
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L92
            return r5
        L92:
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> L35
            return r6
        L95:
            x6.c0 r6 = new x6.c0
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6103pushAccountSyncItemsgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00aa, code lost:
    
        if (r9 == r7) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d6, code lost:
    
        if (storeSession(r1, r0) != r7) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c7 A[Catch: Exception -> 0x003e, PHI: r9
      0x00c7: PHI (r9v21 java.lang.Object) = (r9v18 java.lang.Object), (r9v1 java.lang.Object) binds: [B:43:0x00c4, B:22:0x0051] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x003e, blocks: (B:15:0x0039, B:48:0x00d9, B:22:0x0051, B:45:0x00c7, B:42:0x00b2), top: B:55:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshAccessToken(d7.d<? super java.lang.String> r9) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.refreshAccessToken(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0180, code lost:
    
        if (r13 == r8) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a4 A[PHI: r1 r13 r14
      0x00a4: PHI (r1v11 java.lang.String) = (r1v7 java.lang.String), (r1v13 java.lang.String) binds: [B:56:0x016b, B:16:0x0095] A[DONT_GENERATE, DONT_INLINE]
      0x00a4: PHI (r13v22 java.lang.String) = (r13v7 java.lang.String), (r13v29 java.lang.String) binds: [B:56:0x016b, B:16:0x0095] A[DONT_GENERATE, DONT_INLINE]
      0x00a4: PHI (r14v21 java.lang.Object) = (r14v9 java.lang.Object), (r14v26 java.lang.Object) binds: [B:56:0x016b, B:16:0x0095] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0216  */
    /* JADX INFO: renamed from: saveAccountSyncPayload-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6104saveAccountSyncPayloadgIAlus(java.lang.String r13, d7.d<? super x6.d0> r14) {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6104saveAccountSyncPayloadgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x01b9 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:13:0x004b, B:58:0x01a5, B:60:0x01af, B:70:0x01ce, B:72:0x01d2, B:74:0x01ea, B:64:0x01b9, B:66:0x01c3, B:68:0x01c8, B:20:0x0066, B:50:0x00ee, B:52:0x00f2, B:54:0x00ff, B:47:0x00e0), top: B:81:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c3 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:13:0x004b, B:58:0x01a5, B:60:0x01af, B:70:0x01ce, B:72:0x01d2, B:74:0x01ea, B:64:0x01b9, B:66:0x01c3, B:68:0x01c8, B:20:0x0066, B:50:0x00ee, B:52:0x00f2, B:54:0x00ff, B:47:0x00e0), top: B:81:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c8 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:13:0x004b, B:58:0x01a5, B:60:0x01af, B:70:0x01ce, B:72:0x01d2, B:74:0x01ea, B:64:0x01b9, B:66:0x01c3, B:68:0x01c8, B:20:0x0066, B:50:0x00ee, B:52:0x00f2, B:54:0x00ff, B:47:0x00e0), top: B:81:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01d2 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:13:0x004b, B:58:0x01a5, B:60:0x01af, B:70:0x01ce, B:72:0x01d2, B:74:0x01ea, B:64:0x01b9, B:66:0x01c3, B:68:0x01c8, B:20:0x0066, B:50:0x00ee, B:52:0x00f2, B:54:0x00ff, B:47:0x00e0), top: B:81:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveAddonsToProfile-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6105saveAddonsToProfilegIAlus(java.lang.String r28, d7.d<? super x6.d0> r29) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6105saveAddonsToProfilegIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveAutoPlayNextToProfile-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6106saveAutoPlayNextToProfilegIAlus(boolean r21, d7.d<? super x6.d0> r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 401
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6106saveAutoPlayNextToProfilegIAlus(boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: saveDefaultSubtitleToProfile-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6107saveDefaultSubtitleToProfilegIAlus(java.lang.String r26, d7.d<? super x6.d0> r27) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6107saveDefaultSubtitleToProfilegIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x01da, code lost:
    
        if (r2 == r14) goto L71;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0145 A[Catch: Exception -> 0x0054, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0183 A[Catch: Exception -> 0x0054, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x018b A[Catch: Exception -> 0x0054, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b8 A[Catch: Exception -> 0x0054, PHI: r0 r2 r3
      0x01b8: PHI (r0v43 io.github.jan.supabase.gotrue.user.UserInfo) = (r0v40 io.github.jan.supabase.gotrue.user.UserInfo), (r0v45 io.github.jan.supabase.gotrue.user.UserInfo) binds: [B:62:0x01b5, B:19:0x006b] A[DONT_GENERATE, DONT_INLINE]
      0x01b8: PHI (r2v33 java.lang.Object) = (r2v32 java.lang.Object), (r2v1 java.lang.Object) binds: [B:62:0x01b5, B:19:0x006b] A[DONT_GENERATE, DONT_INLINE]
      0x01b8: PHI (r3v25 java.lang.String) = (r3v20 java.lang.String), (r3v29 java.lang.String) binds: [B:62:0x01b5, B:19:0x006b] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01bc A[Catch: Exception -> 0x0054, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x020c A[Catch: Exception -> 0x0054, TRY_LEAVE, TryCatch #0 {Exception -> 0x0054, blocks: (B:14:0x004f, B:72:0x01dd, B:73:0x01df, B:77:0x01f4, B:19:0x006b, B:64:0x01b8, B:66:0x01bc, B:69:0x01c7, B:22:0x0084, B:61:0x01a0, B:25:0x0095, B:52:0x0175, B:54:0x0183, B:57:0x018b, B:79:0x020c, B:28:0x00ab, B:45:0x0141, B:47:0x0145, B:31:0x00c0, B:42:0x0124, B:37:0x00f5, B:39:0x0113, B:49:0x0152), top: B:86:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX INFO: renamed from: signIn-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6108signIn0E7RQCE(java.lang.String r22, java.lang.String r23, d7.d<? super x6.d0> r24) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6108signIn0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0211, code lost:
    
        if (r3 == r14) goto L107;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01f1 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0219 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x023f A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010e A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011a A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0120 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0134 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013a A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0179 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b3 A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:14:0x0054, B:108:0x0214, B:111:0x0219, B:115:0x0220, B:116:0x0234, B:118:0x023f, B:119:0x0243, B:95:0x01e7, B:98:0x01ec, B:100:0x01f1, B:105:0x01fe, B:94:0x01e2, B:24:0x0092, B:82:0x01b3, B:85:0x01bb, B:120:0x025b, B:28:0x00bc, B:66:0x015e, B:68:0x0179, B:74:0x0194, B:77:0x019b, B:31:0x00c9, B:45:0x010a, B:47:0x010e, B:49:0x0114, B:53:0x0120, B:55:0x0126, B:60:0x0134, B:62:0x013a, B:51:0x011a, B:34:0x00d5, B:36:0x00e4, B:38:0x00ea, B:42:0x00f3, B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c7 A[Catch: all -> 0x007b, TRY_ENTER, TryCatch #1 {all -> 0x007b, blocks: (B:19:0x0076, B:92:0x01df, B:89:0x01c7), top: B:127:0x0031, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01eb  */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r25v0, types: [com.arflix.tv.data.repository.AuthRepository] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v3, types: [int] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v41 */
    /* JADX WARN: Type inference failed for: r4v52 */
    /* JADX WARN: Type inference failed for: r4v53 */
    /* JADX WARN: Type inference failed for: r4v54 */
    /* JADX WARN: Type inference failed for: r4v55 */
    /* JADX WARN: Type inference failed for: r4v56 */
    /* JADX WARN: Type inference failed for: r4v57 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX INFO: renamed from: signInWithSessionTokens-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6109signInWithSessionTokens0E7RQCE(java.lang.String r26, java.lang.String r27, d7.d<? super x6.d0> r28) {
        /*
            Method dump skipped, instruction units count: 740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6109signInWithSessionTokens0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a8, code lost:
    
        if (r13.logout(r0) != r9) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00da, code lost:
    
        if (r13 == r9) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0096 A[Catch: all -> 0x00ab, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x00ab, blocks: (B:24:0x004f, B:39:0x0096), top: B:55:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object signOut(d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.signOut(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c9, code lost:
    
        if (r15 == r7) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX INFO: renamed from: signUp-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6110signUp0E7RQCE(java.lang.String r15, java.lang.String r16, d7.d<? super x6.d0> r17) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6110signUp0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX INFO: renamed from: updateProfile-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6111updateProfilegIAlus(com.arflix.tv.data.repository.UserProfile r18, d7.d<? super x6.d0> r19) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepository.m6111updateProfilegIAlus(com.arflix.tv.data.repository.UserProfile, d7.d):java.lang.Object");
    }
}
