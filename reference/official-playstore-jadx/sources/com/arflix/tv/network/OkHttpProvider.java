package com.arflix.tv.network;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.SvgDecoder;
import android.util.Log;
import androidx.tv.foundation.lazy.list.k;
import coil.ComponentRegistry;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import com.arflix.tv.network.OkHttpProvider;
import d7.h;
import f7.e;
import f7.j;
import gb.b0;
import gb.c0;
import gb.d0;
import gb.g;
import gb.g0;
import gb.h0;
import gb.j0;
import gb.o;
import gb.o0;
import gb.p0;
import gb.r0;
import gb.s0;
import gb.t;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.text.u;
import l7.i;
import mb.f;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.DocumentType;
import r7.p;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u001b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u009b\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0011¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010#\u001a\u00020\"*\u00020\u001f2\u0006\u0010!\u001a\u00020 H\u0002¢\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u0004\u0018\u00010\"2\u0006\u0010%\u001a\u00020\"H\u0002¢\u0006\u0004\b&\u0010'J\u0013\u0010(\u001a\u00020\u001c*\u00020\"H\u0002¢\u0006\u0004\b(\u0010)J\u001f\u0010+\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\tH\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020-2\u0006\u0010\u0014\u001a\u00020\u0011H\u0002¢\u0006\u0004\b.\u0010/J\u000f\u00101\u001a\u000200H\u0002¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u000200H\u0002¢\u0006\u0004\b3\u00102J\u0017\u00104\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u0002062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b7\u00108J)\u0010=\u001a\b\u0012\u0004\u0012\u00020<0;2\u0012\u0010:\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t09\"\u00020\tH\u0002¢\u0006\u0004\b=\u0010>J\u0017\u0010@\u001a\u00020-2\u0006\u0010?\u001a\u00020-H\u0002¢\u0006\u0004\b@\u0010AJ%\u0010D\u001a\u00020-2\u0006\u0010B\u001a\u00020\t2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020<0;H\u0002¢\u0006\u0004\bD\u0010EJ-\u0010G\u001a\u00020-2\u0006\u0010F\u001a\u00020\t2\u0006\u0010B\u001a\u00020\t2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020<0;H\u0002¢\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u000200H\u0002¢\u0006\u0004\bI\u00102R\u0014\u0010J\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020 8\u0002X\u0082T¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020 8\u0002X\u0082T¢\u0006\u0006\n\u0004\bN\u0010MR\u0014\u0010O\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bO\u0010KR\u0014\u0010P\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bP\u0010KR\u0014\u0010Q\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bQ\u0010KR\u0014\u0010R\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bR\u0010KR\u0014\u0010S\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bS\u0010KR\u0014\u0010T\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\bT\u0010KR\u0014\u0010U\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\bU\u0010KR\u0014\u0010V\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\bV\u0010KR\u0014\u0010W\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\bW\u0010KR\u0018\u0010X\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010KR\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010`\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010e\u001a\u00020b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010dR\u0014\u0010f\u001a\u00020 8\u0002X\u0082T¢\u0006\u0006\n\u0004\bf\u0010MR\u0014\u0010h\u001a\u00020g8\u0002X\u0082T¢\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010j\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010kR\u0018\u0010l\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010kR\u0018\u0010m\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0018\u0010o\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010kR\u001b\u0010t\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bp\u0010q\u001a\u0004\br\u0010sR\u001b\u0010w\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010q\u001a\u0004\bv\u0010sR\u001b\u0010z\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bx\u0010q\u001a\u0004\by\u0010sR\u001b\u0010}\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b{\u0010q\u001a\u0004\b|\u0010sR\u0018\u0010~\u001a\u00020-8\u0006¢\u0006\r\n\u0004\b~\u0010\u007f\u001a\u0005\b\u0080\u0001\u0010sR\u0018\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0084\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u0018\u0010\u0085\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0083\u0001R%\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020<0;8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0086\u0001\u0010q\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R%\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020<0;8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008a\u0001\u0010q\u001a\u0006\b\u008b\u0001\u0010\u0088\u0001R%\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020<0;8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008d\u0001\u0010q\u001a\u0006\b\u008e\u0001\u0010\u0088\u0001R\u0014\u0010\u0092\u0001\u001a\u00020\t8F¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0014\u0010\u0094\u0001\u001a\u00020\t8F¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0091\u0001R\u0013\u0010\u0096\u0001\u001a\u0002008F¢\u0006\u0007\u001a\u0005\b\u0095\u0001\u00102R\u0013\u0010\u0098\u0001\u001a\u0002008F¢\u0006\u0007\u001a\u0005\b\u0097\u0001\u00102R\u0013\u0010\u009a\u0001\u001a\u0002008F¢\u0006\u0007\u001a\u0005\b\u0099\u0001\u00102¨\u0006\u009c\u0001"}, d2 = {"Lcom/arflix/tv/network/OkHttpProvider;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lx6/t0;", "init", "(Landroid/content/Context;)V", "", "value", "setCustomUserAgent", "(Ljava/lang/String;)V", "defaultUserAgent", "userAgentOr", "(Ljava/lang/String;)Ljava/lang/String;", "raw", "Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;", "parseDnsProvider", "(Ljava/lang/String;)Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;", "provider", "setDnsProvider", "(Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;)V", "Lcoil/ImageLoader;", "createCoilImageLoader", "(Landroid/content/Context;)Lcoil/ImageLoader;", "Lgb/p0;", "response", "", "shouldDecodeLenientJsonGzip", "(Lgb/p0;)Z", "Lgb/s0;", "", "maxBytes", "", "readBytesWithLimit", "(Lgb/s0;J)[B", "rawBytes", "decodeGzipLayers", "([B)[B", "hasGzipMagic", "([B)Z", "message", "gzipErrorResponse", "(Lgb/p0;Ljava/lang/String;)Lgb/p0;", "Lgb/t;", "selectedDns", "(Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;)Lgb/t;", "Lgb/h0;", "buildAppClient", "()Lgb/h0;", "buildPlaybackClient", "isSafeHeaderValue", "(Ljava/lang/String;)Z", "Lgb/g;", "getOrCreateHttpCache", "(Landroid/content/Context;)Lgb/g;", "", "addresses", "", "Ljava/net/InetAddress;", "safeResolve", "([Ljava/lang/String;)Ljava/util/List;", "delegate", "preferIpv4ForTmdb", "(Lgb/t;)Lgb/t;", "dohHost", "bootstrapHosts", "buildBootstrapDns", "(Ljava/lang/String;Ljava/util/List;)Lgb/t;", "url", "buildDohDns", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lgb/t;", "buildCoilClient", "TAG", "Ljava/lang/String;", "HTTP_CACHE_SIZE", "J", "IMAGE_DISK_CACHE_SIZE", "CLOUDFLARE_DOH_HOST", "CLOUDFLARE_DOH_URL", "GOOGLE_DOH_HOST", "GOOGLE_DOH_URL", "ADGUARD_DOH_HOST", "ADGUARD_DOH_URL", "DNS_PROVIDER_PREF_KEY", "DEFAULT_USER_AGENT", "USER_AGENT_PREF_KEY", "appContext", "Landroid/content/Context;", "selectedDnsProvider", "Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;", "_customUserAgent", "Lka/k0;", "dnsScope", "Lka/k0;", "clientLock", "Ljava/lang/Object;", "Lgb/o;", "appConnectionPool", "Lgb/o;", "playbackConnectionPool", "MAX_LENIENT_GZIP_BYTES", "", "MAX_GZIP_LAYERS", "I", "appClient", "Lgb/h0;", "playbackSharedClient", "appHttpCache", "Lgb/g;", "coilSharedClient", "systemDns$delegate", "Lx6/s;", "getSystemDns", "()Lgb/t;", "systemDns", "cloudflareDns$delegate", "getCloudflareDns", "cloudflareDns", "googleDns$delegate", "getGoogleDns", "googleDns", "adguardDns$delegate", "getAdguardDns", "adguardDns", "dns", "Lgb/t;", "getDns", "Lgb/c0;", "apiDnsLoggingInterceptor", "Lgb/c0;", "lenientJsonGzipInterceptor", "customUserAgentInterceptor", "cloudflareBootstrapHosts$delegate", "getCloudflareBootstrapHosts", "()Ljava/util/List;", "cloudflareBootstrapHosts", "googleBootstrapHosts$delegate", "getGoogleBootstrapHosts", "googleBootstrapHosts", "adguardBootstrapHosts$delegate", "getAdguardBootstrapHosts", "adguardBootstrapHosts", "getCustomUserAgent", "()Ljava/lang/String;", "customUserAgent", "getUserAgent", "userAgent", "getClient", "client", "getPlaybackClient", "playbackClient", "getCoilClient", "coilClient", "AppDnsProvider", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OkHttpProvider {
    public static final int $stable;
    private static final String ADGUARD_DOH_HOST = "dns.adguard-dns.com";
    private static final String ADGUARD_DOH_URL = "https://dns.adguard-dns.com/dns-query";
    private static final String CLOUDFLARE_DOH_HOST = "cloudflare-dns.com";
    private static final String CLOUDFLARE_DOH_URL = "https://cloudflare-dns.com/dns-query";
    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    public static final String DNS_PROVIDER_PREF_KEY = "dns_provider_global";
    private static final String GOOGLE_DOH_HOST = "dns.google";
    private static final String GOOGLE_DOH_URL = "https://dns.google/dns-query";
    private static final long HTTP_CACHE_SIZE = 52428800;
    private static final long IMAGE_DISK_CACHE_SIZE = 50331648;
    private static final int MAX_GZIP_LAYERS = 3;
    private static final long MAX_LENIENT_GZIP_BYTES = 16777216;
    private static final String TAG = "AppDns";
    public static final String USER_AGENT_PREF_KEY = "custom_user_agent_global";

    /* JADX INFO: renamed from: adguardBootstrapHosts$delegate, reason: from kotlin metadata */
    private static final s adguardBootstrapHosts;

    /* JADX INFO: renamed from: adguardDns$delegate, reason: from kotlin metadata */
    private static final s adguardDns;
    private static final c0 apiDnsLoggingInterceptor;
    private static volatile h0 appClient;
    private static final o appConnectionPool;
    private static volatile Context appContext;
    private static volatile g appHttpCache;

    /* JADX INFO: renamed from: cloudflareBootstrapHosts$delegate, reason: from kotlin metadata */
    private static final s cloudflareBootstrapHosts;

    /* JADX INFO: renamed from: cloudflareDns$delegate, reason: from kotlin metadata */
    private static final s cloudflareDns;
    private static volatile h0 coilSharedClient;
    private static final c0 customUserAgentInterceptor;
    private static final t dns;

    /* JADX INFO: renamed from: googleBootstrapHosts$delegate, reason: from kotlin metadata */
    private static final s googleBootstrapHosts;

    /* JADX INFO: renamed from: googleDns$delegate, reason: from kotlin metadata */
    private static final s googleDns;
    private static final c0 lenientJsonGzipInterceptor;
    private static final o playbackConnectionPool;
    private static volatile h0 playbackSharedClient;

    /* JADX INFO: renamed from: systemDns$delegate, reason: from kotlin metadata */
    private static final s systemDns;
    public static final OkHttpProvider INSTANCE = new OkHttpProvider();
    private static volatile AppDnsProvider selectedDnsProvider = AppDnsProvider.SYSTEM;
    private static volatile String _customUserAgent = "";
    private static final k0 dnsScope = l0.a(h.a(m0.d(), x0.f19655d));
    private static final Object clientLock = new Object();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/network/OkHttpProvider$AppDnsProvider;", "", "<init>", "(Ljava/lang/String;I)V", DocumentType.SYSTEM_KEY, "CLOUDFLARE", "GOOGLE", "ADGUARD", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AppDnsProvider {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ AppDnsProvider[] $VALUES;
        public static final AppDnsProvider SYSTEM = new AppDnsProvider(DocumentType.SYSTEM_KEY, 0);
        public static final AppDnsProvider CLOUDFLARE = new AppDnsProvider("CLOUDFLARE", 1);
        public static final AppDnsProvider GOOGLE = new AppDnsProvider("GOOGLE", 2);
        public static final AppDnsProvider ADGUARD = new AppDnsProvider("ADGUARD", 3);

        private static final /* synthetic */ AppDnsProvider[] $values() {
            return new AppDnsProvider[]{SYSTEM, CLOUDFLARE, GOOGLE, ADGUARD};
        }

        static {
            AppDnsProvider[] appDnsProviderArr$values = $values();
            $VALUES = appDnsProviderArr$values;
            $ENTRIES = new g7.b(appDnsProviderArr$values);
        }

        private AppDnsProvider(String str, int i10) {
        }

        public static g7.a<AppDnsProvider> getEntries() {
            return $ENTRIES;
        }

        public static AppDnsProvider valueOf(String str) {
            return (AppDnsProvider) Enum.valueOf(AppDnsProvider.class, str);
        }

        public static AppDnsProvider[] values() {
            return (AppDnsProvider[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppDnsProvider.values().length];
            try {
                iArr[AppDnsProvider.SYSTEM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AppDnsProvider.CLOUDFLARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AppDnsProvider.GOOGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AppDnsProvider.ADGUARD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.network.OkHttpProvider$setDnsProvider$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.network.OkHttpProvider$setDnsProvider$1", f = "OkHttpProvider.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C13051 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C13051(d7.d<? super C13051> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C13051(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            OkHttpProvider.appConnectionPool.a();
            OkHttpProvider.playbackConnectionPool.a();
            Log.i(OkHttpProvider.TAG, "Evicted pooled app connections after DNS change");
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C13051) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        appConnectionPool = new o(32, 5L, timeUnit);
        playbackConnectionPool = new o(16, 5L, timeUnit);
        systemDns = new i0(new com.arflix.tv.data.repository.simkl.a(3));
        cloudflareDns = new i0(new com.arflix.tv.data.repository.simkl.a(4));
        googleDns = new i0(new com.arflix.tv.data.repository.simkl.a(5));
        adguardDns = new i0(new com.arflix.tv.data.repository.simkl.a(6));
        dns = new t() { // from class: com.arflix.tv.network.OkHttpProvider$dns$1
            @Override // gb.t
            public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                OkHttpProvider.AppDnsProvider appDnsProvider = OkHttpProvider.selectedDnsProvider;
                try {
                    return OkHttpProvider.INSTANCE.selectedDns(appDnsProvider).lookup(hostname);
                } catch (UnknownHostException e5) {
                    if (appDnsProvider == OkHttpProvider.AppDnsProvider.SYSTEM) {
                        throw e5;
                    }
                    try {
                        List<InetAddress> listLookup = OkHttpProvider.INSTANCE.getSystemDns().lookup(hostname);
                        Log.w("AppDns", "DNS provider=" + appDnsProvider + " failed for " + hostname + ", using system DNS fallback");
                        return listLookup;
                    } catch (UnknownHostException unused) {
                        throw e5;
                    }
                }
            }
        };
        final int i10 = 0;
        apiDnsLoggingInterceptor = new c0() { // from class: com.arflix.tv.network.d
            @Override // gb.c0
            public final p0 intercept(c0.a aVar) {
                f fVar = (f) aVar;
                switch (i10) {
                    case 0:
                        return OkHttpProvider.apiDnsLoggingInterceptor$lambda$0(fVar);
                    case 1:
                        return OkHttpProvider.lenientJsonGzipInterceptor$lambda$0(fVar);
                    default:
                        return OkHttpProvider.customUserAgentInterceptor$lambda$0(fVar);
                }
            }
        };
        final int i11 = 1;
        lenientJsonGzipInterceptor = new c0() { // from class: com.arflix.tv.network.d
            @Override // gb.c0
            public final p0 intercept(c0.a aVar) {
                f fVar = (f) aVar;
                switch (i11) {
                    case 0:
                        return OkHttpProvider.apiDnsLoggingInterceptor$lambda$0(fVar);
                    case 1:
                        return OkHttpProvider.lenientJsonGzipInterceptor$lambda$0(fVar);
                    default:
                        return OkHttpProvider.customUserAgentInterceptor$lambda$0(fVar);
                }
            }
        };
        final int i12 = 2;
        customUserAgentInterceptor = new c0() { // from class: com.arflix.tv.network.d
            @Override // gb.c0
            public final p0 intercept(c0.a aVar) {
                f fVar = (f) aVar;
                switch (i12) {
                    case 0:
                        return OkHttpProvider.apiDnsLoggingInterceptor$lambda$0(fVar);
                    case 1:
                        return OkHttpProvider.lenientJsonGzipInterceptor$lambda$0(fVar);
                    default:
                        return OkHttpProvider.customUserAgentInterceptor$lambda$0(fVar);
                }
            }
        };
        cloudflareBootstrapHosts = new i0(new com.arflix.tv.data.repository.simkl.a(7));
        googleBootstrapHosts = new i0(new com.arflix.tv.data.repository.simkl.a(1));
        adguardBootstrapHosts = new i0(new com.arflix.tv.data.repository.simkl.a(2));
        $stable = 8;
    }

    private OkHttpProvider() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List adguardBootstrapHosts_delegate$lambda$0() {
        return INSTANCE.safeResolve("94.140.14.14", "94.140.15.15");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t adguardDns_delegate$lambda$0() {
        OkHttpProvider okHttpProvider = INSTANCE;
        return okHttpProvider.preferIpv4ForTmdb(okHttpProvider.buildDohDns(ADGUARD_DOH_URL, ADGUARD_DOH_HOST, okHttpProvider.getAdguardBootstrapHosts()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p0 apiDnsLoggingInterceptor$lambda$0(c0.a aVar) {
        gb.k0 k0VarRequest = aVar.request();
        AppDnsProvider appDnsProvider = selectedDnsProvider;
        String str = k0VarRequest.f15731b;
        b0 b0Var = k0VarRequest.f15730a;
        Log.i(TAG, "API request dnsProvider=" + appDnsProvider + " method=" + str + " host=" + b0Var.f15620d + " url=" + b0Var);
        return aVar.a(k0VarRequest);
    }

    private final h0 buildAppClient() {
        wb.b bVar = new wb.b();
        bVar.f22540a = kotlin.collections.b0.f19686i;
        bVar.a("Authorization");
        bVar.a("apikey");
        bVar.a("x-user-token");
        bVar.a("X-Plex-Token");
        bVar.a("X-Emby-Token");
        bVar.a("Cookie");
        g0 g0Var = new g0();
        g0Var.f15665c.add(customUserAgentInterceptor);
        g0Var.f15665c.add(new ApiProxyInterceptor());
        g0Var.f15665c.add(bVar);
        g0Var.f15666d.add(lenientJsonGzipInterceptor);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(30L, timeUnit);
        g0Var.e(30L, timeUnit);
        g0Var.f(30L);
        g0Var.f15664b = appConnectionPool;
        g0Var.d(dns);
        g0Var.f15668f = true;
        Context context = appContext;
        if (context != null) {
            g0Var.k = INSTANCE.getOrCreateHttpCache(context);
        }
        return new h0(g0Var);
    }

    private final t buildBootstrapDns(final String dohHost, final List<? extends InetAddress> bootstrapHosts) {
        return new t() { // from class: com.arflix.tv.network.OkHttpProvider.buildBootstrapDns.1
            @Override // gb.t
            public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                if (hostname.equalsIgnoreCase(dohHost)) {
                    return bootstrapHosts;
                }
                throw new UnknownHostException(androidx.compose.material3.d.n("Bootstrap DNS is restricted to ", dohHost, ". Requested: ", hostname));
            }
        };
    }

    private final h0 buildCoilClient() {
        g0 g0Var = new g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(10L, timeUnit);
        g0Var.e(10L, timeUnit);
        g0Var.f(10L);
        g0Var.f15664b = new o(8, 30L, timeUnit);
        g0Var.d(dns);
        g0Var.f15668f = true;
        return new h0(g0Var);
    }

    private final t buildDohDns(String url, String dohHost, List<? extends InetAddress> bootstrapHosts) {
        t tVarBuildBootstrapDns = buildBootstrapDns(dohHost, bootstrapHosts);
        g0 g0Var = new g0();
        g0Var.d(tVarBuildBootstrapDns);
        g0Var.k = null;
        h0 h0Var = new h0(g0Var);
        k kVar = new k(1);
        kVar.i(null, url);
        b0 b0VarD = kVar.d();
        InetAddress[] inetAddressArr = (InetAddress[]) bootstrapHosts.toArray(new InetAddress[0]);
        List listO0 = r.o0((InetAddress[]) Arrays.copyOf(inetAddressArr, inetAddressArr.length));
        g0 g0VarB = h0Var.b();
        d0 d0Var = hb.b.f15967d;
        g0VarB.d(new hb.a(b0VarD.f15620d, listO0));
        return new hb.b(new h0(g0VarB), b0VarD);
    }

    private final h0 buildPlaybackClient() {
        g0 g0Var = new g0();
        g0Var.f15665c.add(customUserAgentInterceptor);
        g0Var.f15664b = playbackConnectionPool;
        g0Var.f15670h = true;
        g0Var.f15671i = true;
        g0Var.f15668f = true;
        g0Var.d(dns);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(10L, timeUnit);
        g0Var.e(180L, timeUnit);
        g0Var.f(20L);
        g0Var.k = null;
        return new h0(g0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List cloudflareBootstrapHosts_delegate$lambda$0() {
        return INSTANCE.safeResolve("1.1.1.1", "1.0.0.1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t cloudflareDns_delegate$lambda$0() {
        OkHttpProvider okHttpProvider = INSTANCE;
        return okHttpProvider.preferIpv4ForTmdb(okHttpProvider.buildDohDns(CLOUDFLARE_DOH_URL, CLOUDFLARE_DOH_HOST, okHttpProvider.getCloudflareBootstrapHosts()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MemoryCache createCoilImageLoader$lambda$0(Context context, int i10) {
        return new MemoryCache.Builder(context).maxSizeBytes(i10).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiskCache createCoilImageLoader$lambda$1(Context context) {
        return new DiskCache.Builder().directory(i.t0(context.getCacheDir(), new File("image_cache"))).maxSizeBytes(IMAGE_DISK_CACHE_SIZE).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p0 customUserAgentInterceptor$lambda$0(c0.a aVar) {
        gb.k0 k0VarRequest = aVar.request();
        if (k0VarRequest.f15732c.a("User-Agent") != null) {
            return aVar.a(k0VarRequest);
        }
        String strUserAgentOr = INSTANCE.userAgentOr(DEFAULT_USER_AGENT);
        j0 j0VarB = k0VarRequest.b();
        j0VarB.f15724c.h("User-Agent", strUserAgentOr);
        return aVar.a(j0VarB.b());
    }

    private final byte[] decodeGzipLayers(byte[] rawBytes) {
        Object c0Var;
        for (int i10 = 0; i10 < 3; i10++) {
            if (!INSTANCE.hasGzipMagic(rawBytes)) {
                return rawBytes;
            }
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(rawBytes));
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, gZIPInputStream.available()));
                    t7.a.k(gZIPInputStream, byteArrayOutputStream);
                    c0Var = byteArrayOutputStream.toByteArray();
                    gZIPInputStream.close();
                } finally {
                }
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = null;
            }
            rawBytes = (byte[]) c0Var;
            if (rawBytes == null) {
                return null;
            }
        }
        if (INSTANCE.hasGzipMagic(rawBytes)) {
            return null;
        }
        return rawBytes;
    }

    private final List<InetAddress> getAdguardBootstrapHosts() {
        return (List) adguardBootstrapHosts.getValue();
    }

    private final t getAdguardDns() {
        return (t) adguardDns.getValue();
    }

    private final List<InetAddress> getCloudflareBootstrapHosts() {
        return (List) cloudflareBootstrapHosts.getValue();
    }

    private final t getCloudflareDns() {
        return (t) cloudflareDns.getValue();
    }

    private final List<InetAddress> getGoogleBootstrapHosts() {
        return (List) googleBootstrapHosts.getValue();
    }

    private final t getGoogleDns() {
        return (t) googleDns.getValue();
    }

    private final g getOrCreateHttpCache(Context context) {
        g gVar;
        g gVar2 = appHttpCache;
        if (gVar2 != null) {
            return gVar2;
        }
        synchronized (clientLock) {
            gVar = appHttpCache;
            if (gVar == null) {
                gVar = new g(new File(context.getCacheDir(), "http_cache"));
                appHttpCache = gVar;
            }
        }
        return gVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final t getSystemDns() {
        return (t) systemDns.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List googleBootstrapHosts_delegate$lambda$0() {
        return INSTANCE.safeResolve("8.8.8.8", "8.8.4.4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t googleDns_delegate$lambda$0() {
        OkHttpProvider okHttpProvider = INSTANCE;
        return okHttpProvider.preferIpv4ForTmdb(okHttpProvider.buildDohDns(GOOGLE_DOH_URL, GOOGLE_DOH_HOST, okHttpProvider.getGoogleBootstrapHosts()));
    }

    private final p0 gzipErrorResponse(p0 response, String message) {
        String strL = a0.c.l("{\"success\":false,\"status_message\":\"", message, "\"}");
        Pattern pattern = d0.f15633d;
        d0 d0VarI0 = xc.d.i0("application/json; charset=utf-8");
        Charset charset = kotlin.text.a.f19924a;
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
        d0 d0Var = d0VarI0;
        xb.j jVar = new xb.j();
        int length = strL.length();
        if (length < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(length, 0, "endIndex < beginIndex: ", " < ").toString());
        }
        if (length > strL.length()) {
            StringBuilder sbS = a0.c.s(length, "endIndex > string.length: ", " > ");
            sbS.append(strL.length());
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        if (charset.equals(kotlin.text.a.f19924a)) {
            jVar.R0(0, length, strL);
        } else {
            byte[] bytes = strL.substring(0, length).getBytes(charset);
            jVar.write(bytes, 0, bytes.length);
        }
        r0 r0Var = new r0(d0Var, jVar.f22759l, jVar, 0);
        o0 o0VarL = response.l();
        o0VarL.f15764c = 502;
        o0VarL.f15765d = "Invalid compressed API response";
        o0VarL.f15767f.g(HttpConnection.CONTENT_ENCODING);
        o0VarL.f15767f.g("Content-Length");
        o0VarL.f15768g = r0Var;
        return o0VarL.a();
    }

    private final boolean hasGzipMagic(byte[] bArr) {
        return bArr.length >= 2 && (bArr[0] & 255) == 31 && (bArr[1] & 255) == 139;
    }

    private final boolean isSafeHeaderValue(String value) {
        for (int i10 = 0; i10 < value.length(); i10++) {
            char cCharAt = value.charAt(i10);
            if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p0 lenientJsonGzipInterceptor$lambda$0(c0.a aVar) {
        s0 s0Var;
        p0 p0VarA = aVar.a(aVar.request());
        OkHttpProvider okHttpProvider = INSTANCE;
        if (!okHttpProvider.shouldDecodeLenientJsonGzip(p0VarA) || (s0Var = p0VarA.f15786q) == null) {
            return p0VarA;
        }
        try {
            byte[] bArrDecodeGzipLayers = okHttpProvider.decodeGzipLayers(okHttpProvider.readBytesWithLimit(s0Var, MAX_LENIENT_GZIP_BYTES));
            if (bArrDecodeGzipLayers == null) {
                return okHttpProvider.gzipErrorResponse(p0VarA, "Compressed API response could not be decoded.");
            }
            o0 o0VarL = p0VarA.l();
            o0VarL.f15767f.g(HttpConnection.CONTENT_ENCODING);
            o0VarL.f15767f.g("Content-Length");
            d0 d0VarL = s0Var.l();
            xb.j jVar = new xb.j();
            jVar.write(bArrDecodeGzipLayers, 0, bArrDecodeGzipLayers.length);
            o0VarL.f15768g = new r0(d0VarL, bArrDecodeGzipLayers.length, jVar, 0);
            return o0VarL.a();
        } catch (IOException unused) {
            return INSTANCE.gzipErrorResponse(p0VarA, "Compressed API response could not be read.");
        }
    }

    private final t preferIpv4ForTmdb(final t delegate) {
        return new t() { // from class: com.arflix.tv.network.OkHttpProvider.preferIpv4ForTmdb.1
            @Override // gb.t
            public List<InetAddress> lookup(String hostname) {
                List<InetAddress> listLookup = delegate.lookup(hostname);
                if (kotlin.text.o.T(hostname, "tmdb", true) || kotlin.text.o.T(hostname, "themoviedb", true)) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : listLookup) {
                        if (obj instanceof Inet4Address) {
                            arrayList.add(obj);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        ArrayList arrayList2 = new ArrayList();
                        for (Object obj2 : listLookup) {
                            if (!(((InetAddress) obj2) instanceof Inet4Address)) {
                                arrayList2.add(obj2);
                            }
                        }
                        return x.I0(arrayList, arrayList2);
                    }
                }
                return listLookup;
            }
        };
    }

    private final byte[] readBytesWithLimit(s0 s0Var, long j10) throws IOException {
        InputStream inputStreamI = s0Var.i();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            long j11 = 0;
            while (true) {
                int i10 = inputStreamI.read(bArr);
                if (i10 == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    inputStreamI.close();
                    return byteArray;
                }
                j11 += (long) i10;
                if (j11 > j10) {
                    throw new IOException("Compressed API response exceeded " + j10 + " bytes");
                }
                byteArrayOutputStream.write(bArr, 0, i10);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                xc.d.L(inputStreamI, th);
                throw th2;
            }
        }
    }

    private final List<InetAddress> safeResolve(String... addresses) {
        InetAddress byName;
        ArrayList arrayList = new ArrayList();
        for (String str : addresses) {
            try {
                byName = InetAddress.getByName(str);
            } catch (Exception unused) {
                byName = null;
            }
            if (byName != null) {
                arrayList.add(byName);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final t selectedDns(AppDnsProvider provider) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[provider.ordinal()];
        if (i10 == 1) {
            return getSystemDns();
        }
        if (i10 == 2) {
            return getCloudflareDns();
        }
        if (i10 == 3) {
            return getGoogleDns();
        }
        if (i10 == 4) {
            return getAdguardDns();
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean shouldDecodeLenientJsonGzip(p0 response) {
        if (u.L(p0.j(response, HttpConnection.CONTENT_ENCODING), "gzip", true)) {
            s0 s0Var = response.f15786q;
            if ((s0Var != null ? s0Var.k() : -1L) <= MAX_LENIENT_GZIP_BYTES) {
                String strA = response.f15785p.a(HttpConnection.CONTENT_TYPE);
                if (strA == null) {
                    strA = null;
                }
                if (strA == null) {
                    strA = "";
                }
                return kotlin.text.o.T(strA.toLowerCase(Locale.ROOT), "json", false);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t systemDns_delegate$lambda$0() {
        return INSTANCE.preferIpv4ForTmdb(t.f15815a);
    }

    public final ImageLoader createCoilImageLoader(Context context) {
        Object systemService = context.getSystemService("activity");
        kotlin.jvm.internal.h hVar = null;
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        int i10 = 1;
        ImageLoader.Builder builderAllowRgb565 = new ImageLoader.Builder(context).okHttpClient(getCoilClient()).memoryCache(new b(context, (activityManager == null || !activityManager.isLowRamDevice()) ? 33554432 : 25165824, 0)).diskCache(new c(context, 0)).crossfade(false).respectCacheHeaders(false).allowRgb565(true);
        ComponentRegistry.Builder builder = new ComponentRegistry.Builder();
        builder.add(new SvgDecoder.Factory(false, i10, hVar));
        return builderAllowRgb565.components(builder.build()).build();
    }

    public final h0 getClient() {
        h0 h0VarBuildAppClient;
        h0 h0Var = appClient;
        if (h0Var != null) {
            return h0Var;
        }
        synchronized (clientLock) {
            h0VarBuildAppClient = appClient;
            if (h0VarBuildAppClient == null) {
                h0VarBuildAppClient = INSTANCE.buildAppClient();
                appClient = h0VarBuildAppClient;
            }
        }
        return h0VarBuildAppClient;
    }

    public final h0 getCoilClient() {
        h0 h0VarBuildCoilClient;
        h0 h0Var = coilSharedClient;
        if (h0Var != null) {
            return h0Var;
        }
        synchronized (clientLock) {
            h0VarBuildCoilClient = coilSharedClient;
            if (h0VarBuildCoilClient == null) {
                h0VarBuildCoilClient = INSTANCE.buildCoilClient();
                coilSharedClient = h0VarBuildCoilClient;
            }
        }
        return h0VarBuildCoilClient;
    }

    public final String getCustomUserAgent() {
        return _customUserAgent;
    }

    public final t getDns() {
        return dns;
    }

    public final h0 getPlaybackClient() {
        h0 h0VarBuildPlaybackClient;
        h0 h0Var = playbackSharedClient;
        if (h0Var != null) {
            return h0Var;
        }
        synchronized (clientLock) {
            h0VarBuildPlaybackClient = playbackSharedClient;
            if (h0VarBuildPlaybackClient == null) {
                h0VarBuildPlaybackClient = INSTANCE.buildPlaybackClient();
                playbackSharedClient = h0VarBuildPlaybackClient;
            }
        }
        return h0VarBuildPlaybackClient;
    }

    public final String getUserAgent() {
        return userAgentOr(DEFAULT_USER_AGENT);
    }

    public final void init(Context context) {
        appContext = context.getApplicationContext();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0024, code lost:
    
        if (r2.equals("ad guard") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        if (r2.equals("system_dns") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0036, code lost:
    
        if (r2.equals("system dns") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        if (r2.equals("cloudflare") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r2.equals("system") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
    
        return com.arflix.tv.network.OkHttpProvider.AppDnsProvider.SYSTEM;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
    
        if (r2.equals("adguard") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
    
        return com.arflix.tv.network.OkHttpProvider.AppDnsProvider.ADGUARD;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006c, code lost:
    
        if (r2.equals("cloudflare_dns") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0075, code lost:
    
        if (r2.equals("cloudflare dns") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007a, code lost:
    
        return com.arflix.tv.network.OkHttpProvider.AppDnsProvider.CLOUDFLARE;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.network.OkHttpProvider.AppDnsProvider parseDnsProvider(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L13
            java.lang.CharSequence r2 = kotlin.text.o.L0(r2)
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L13
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toLowerCase(r0)
            goto L14
        L13:
            r2 = 0
        L14:
            if (r2 == 0) goto L7b
            int r0 = r2.hashCode()
            switch(r0) {
                case -2131773630: goto L6f;
                case -2129896797: goto L66;
                case -1240244679: goto L5a;
                case -1144835998: goto L4e;
                case -887328209: goto L42;
                case -308958535: goto L39;
                case 641323960: goto L30;
                case 643200793: goto L27;
                case 1119770728: goto L1e;
                default: goto L1d;
            }
        L1d:
            goto L7b
        L1e:
            java.lang.String r0 = "ad guard"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L57
            goto L7b
        L27:
            java.lang.String r0 = "system_dns"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4b
            goto L7b
        L30:
            java.lang.String r0 = "system dns"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4b
            goto L7b
        L39:
            java.lang.String r0 = "cloudflare"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L78
            goto L7b
        L42:
            java.lang.String r0 = "system"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4b
            goto L7b
        L4b:
            com.arflix.tv.network.OkHttpProvider$AppDnsProvider r2 = com.arflix.tv.network.OkHttpProvider.AppDnsProvider.SYSTEM
            return r2
        L4e:
            java.lang.String r0 = "adguard"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L57
            goto L7b
        L57:
            com.arflix.tv.network.OkHttpProvider$AppDnsProvider r2 = com.arflix.tv.network.OkHttpProvider.AppDnsProvider.ADGUARD
            return r2
        L5a:
            java.lang.String r0 = "google"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L63
            goto L7b
        L63:
            com.arflix.tv.network.OkHttpProvider$AppDnsProvider r2 = com.arflix.tv.network.OkHttpProvider.AppDnsProvider.GOOGLE
            return r2
        L66:
            java.lang.String r0 = "cloudflare_dns"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L78
            goto L7b
        L6f:
            java.lang.String r0 = "cloudflare dns"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L78
            goto L7b
        L78:
            com.arflix.tv.network.OkHttpProvider$AppDnsProvider r2 = com.arflix.tv.network.OkHttpProvider.AppDnsProvider.CLOUDFLARE
            return r2
        L7b:
            com.arflix.tv.network.OkHttpProvider$AppDnsProvider r2 = com.arflix.tv.network.OkHttpProvider.AppDnsProvider.SYSTEM
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.network.OkHttpProvider.parseDnsProvider(java.lang.String):com.arflix.tv.network.OkHttpProvider$AppDnsProvider");
    }

    public final void setCustomUserAgent(String value) {
        String string = kotlin.text.o.L0(value).toString();
        if (!isSafeHeaderValue(string)) {
            string = null;
        }
        if (string == null) {
            string = "";
        }
        _customUserAgent = string;
    }

    public final void setDnsProvider(AppDnsProvider provider) {
        selectedDnsProvider = provider;
        Log.i(TAG, "Using DNS provider=" + provider);
        m0.p(dnsScope, null, 0, new C13051(null), 3);
    }

    public final String userAgentOr(String defaultUserAgent) {
        String str = _customUserAgent;
        if (kotlin.text.o.h0(str) || !INSTANCE.isSafeHeaderValue(str)) {
            str = null;
        }
        if (str != null) {
            return str;
        }
        if (!isSafeHeaderValue(defaultUserAgent)) {
            defaultUserAgent = null;
        }
        return defaultUserAgent == null ? DEFAULT_USER_AGENT : defaultUserAgent;
    }
}
