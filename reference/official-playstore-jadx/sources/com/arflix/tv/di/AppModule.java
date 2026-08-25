package com.arflix.tv.di;

import android.content.Context;
import android.os.SystemClock;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.tv.foundation.lazy.list.k;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.data.api.AniSkipApi;
import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.IntroDbApi;
import com.arflix.tv.data.api.JikanApi;
import com.arflix.tv.data.api.MdbListApi;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.util.Constants;
import com.squareup.moshi.w;
import com.squareup.moshi.z;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.b0;
import gb.c0;
import gb.g0;
import gb.h0;
import gb.k0;
import gb.p0;
import javax.inject.Named;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import kotlin.text.u;
import q7.n;
import retrofit2.converter.gson.a;
import retrofit2.t0;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0001\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010!\u001a\u00020 2\b\b\u0001\u0010\u001f\u001a\u00020\u001cH\u0007¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b#\u0010\u001eJ\u0019\u0010%\u001a\u00020$2\b\b\u0001\u0010\u001f\u001a\u00020\u001cH\u0007¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b'\u0010\u001eJ\u0019\u0010)\u001a\u00020(2\b\b\u0001\u0010\u001f\u001a\u00020\u001cH\u0007¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b+\u0010\u001eJ\u0019\u0010-\u001a\u00020,2\b\b\u0001\u0010\u001f\u001a\u00020\u001cH\u0007¢\u0006\u0004\b-\u0010.J\u000f\u00100\u001a\u00020/H\u0007¢\u0006\u0004\b0\u00101¨\u00062"}, d2 = {"Lcom/arflix/tv/di/AppModule;", "", "<init>", "()V", "Lgb/h0;", "provideOkHttpClient", "()Lgb/h0;", "okHttpClient", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/api/TmdbApi;", "provideTmdbApi", "(Lgb/h0;Landroid/content/Context;)Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/arflix/tv/data/api/TraktApi;", "provideTraktApi", "(Lgb/h0;)Lcom/arflix/tv/data/api/TraktApi;", "Lcom/arflix/tv/data/api/MdbListApi;", "provideMdbListApi", "(Lgb/h0;)Lcom/arflix/tv/data/api/MdbListApi;", "Lcom/arflix/tv/data/api/SimklApi;", "provideSimklApi", "(Lgb/h0;)Lcom/arflix/tv/data/api/SimklApi;", "Lcom/arflix/tv/data/api/SupabaseApi;", "provideSupabaseApi", "(Lgb/h0;)Lcom/arflix/tv/data/api/SupabaseApi;", "Lcom/arflix/tv/data/api/StreamApi;", "provideStreamApi", "(Lgb/h0;)Lcom/arflix/tv/data/api/StreamApi;", "Lretrofit2/u0;", "provideIntroDbRetrofit", "(Lgb/h0;)Lretrofit2/u0;", "retrofit", "Lcom/arflix/tv/data/api/IntroDbApi;", "provideIntroDbApi", "(Lretrofit2/u0;)Lcom/arflix/tv/data/api/IntroDbApi;", "provideAniSkipRetrofit", "Lcom/arflix/tv/data/api/AniSkipApi;", "provideAniSkipApi", "(Lretrofit2/u0;)Lcom/arflix/tv/data/api/AniSkipApi;", "provideArmRetrofit", "Lcom/arflix/tv/data/api/ArmApi;", "provideArmApi", "(Lretrofit2/u0;)Lcom/arflix/tv/data/api/ArmApi;", "provideJikanRetrofit", "Lcom/arflix/tv/data/api/JikanApi;", "provideJikanApi", "(Lretrofit2/u0;)Lcom/arflix/tv/data/api/JikanApi;", "Lcom/squareup/moshi/z;", "provideMoshi", "()Lcom/squareup/moshi/z;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Module
public final class AppModule {
    public static final int $stable = 0;
    public static final AppModule INSTANCE = new AppModule();

    private AppModule() {
    }

    @Provides
    @Singleton
    @n
    public static final AniSkipApi provideAniSkipApi(@Named("aniSkip") u0 retrofit) {
        return (AniSkipApi) retrofit.b(AniSkipApi.class);
    }

    @Provides
    @n
    @Named("aniSkip")
    @Singleton
    public static final u0 provideAniSkipRetrofit(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b("https://api.aniskip.com/v2/");
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return t0Var.c();
    }

    @Provides
    @Singleton
    @n
    public static final ArmApi provideArmApi(@Named("arm") u0 retrofit) {
        return (ArmApi) retrofit.b(ArmApi.class);
    }

    @Provides
    @n
    @Named("arm")
    @Singleton
    public static final u0 provideArmRetrofit(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b("https://arm.haglund.dev/api/v2/");
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return t0Var.c();
    }

    @Provides
    @Singleton
    @n
    public static final IntroDbApi provideIntroDbApi(@Named("introDb") u0 retrofit) {
        return (IntroDbApi) retrofit.b(IntroDbApi.class);
    }

    @Provides
    @n
    @Named("introDb")
    @Singleton
    public static final u0 provideIntroDbRetrofit(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b("https://api.introdb.app/");
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return t0Var.c();
    }

    @Provides
    @Singleton
    @n
    public static final JikanApi provideJikanApi(@Named("jikan") u0 retrofit) {
        return (JikanApi) retrofit.b(JikanApi.class);
    }

    @Provides
    @n
    @Named("jikan")
    @Singleton
    public static final u0 provideJikanRetrofit(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b("https://api.jikan.moe/v4/");
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return t0Var.c();
    }

    @Provides
    @Singleton
    @n
    public static final MdbListApi provideMdbListApi(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b(Constants.MDBLIST_API_URL);
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return (MdbListApi) t0Var.c().b(MdbListApi.class);
    }

    @Provides
    @Singleton
    @n
    public static final z provideMoshi() {
        w wVar = new w();
        wVar.f14489a.add(0, new com.squareup.moshi.a(6));
        return new z(wVar);
    }

    @Provides
    @Singleton
    @n
    public static final h0 provideOkHttpClient() {
        return OkHttpProvider.INSTANCE.getClient();
    }

    @Provides
    @Singleton
    @n
    public static final SimklApi provideSimklApi(h0 okHttpClient) {
        final j0 j0Var = new j0();
        final Object obj = new Object();
        g0 g0VarB = okHttpClient.b();
        g0VarB.f15665c.add(new c0() { // from class: com.arflix.tv.di.AppModule$provideSimklApi$$inlined$-addInterceptor$1
            @Override // gb.c0
            public final p0 intercept(c0.a aVar) {
                k0 k0VarRequest = aVar.request();
                if (u.L(k0VarRequest.f15731b, HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, true)) {
                    synchronized (obj) {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - j0Var.f19745i;
                        if (jElapsedRealtime < 1000) {
                            try {
                                Thread.sleep(1000 - jElapsedRealtime);
                            } catch (InterruptedException unused) {
                            }
                        }
                        j0Var.f19745i = SystemClock.elapsedRealtime();
                    }
                }
                b0 b0Var = k0VarRequest.f15730a;
                k kVarF = b0Var.f();
                if (b0Var.g("client_id") == null) {
                    kVarF.c("client_id", Constants.INSTANCE.getSIMKL_CLIENT_ID());
                }
                if (b0Var.g("app-name") == null) {
                    kVarF.c("app-name", BuildConfig.GITHUB_REPO);
                }
                if (b0Var.g("app-version") == null) {
                    kVarF.c("app-version", BuildConfig.VERSION_NAME);
                }
                gb.j0 j0VarB = k0VarRequest.b();
                j0VarB.f15722a = kVarF.d();
                j0VarB.f15724c.h("User-Agent", "ARVIO/1.9.995 (Android TV)");
                if (k0VarRequest.f15732c.a("simkl-api-key") == null) {
                    j0VarB.f15724c.h("simkl-api-key", Constants.INSTANCE.getSIMKL_CLIENT_ID());
                }
                return aVar.a(j0VarB.b());
            }
        });
        h0 h0Var = new h0(g0VarB);
        t0 t0Var = new t0();
        t0Var.b(Constants.SIMKL_BASE_URL);
        t0Var.f21589b = h0Var;
        t0Var.a(a.c());
        return (SimklApi) t0Var.c().b(SimklApi.class);
    }

    @Provides
    @Singleton
    @n
    public static final StreamApi provideStreamApi(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b("https://api.themoviedb.org/");
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return (StreamApi) t0Var.c().b(StreamApi.class);
    }

    @Provides
    @Singleton
    @n
    public static final SupabaseApi provideSupabaseApi(h0 okHttpClient) {
        g0 g0VarB = okHttpClient.b();
        g0VarB.k = null;
        h0 h0Var = new h0(g0VarB);
        t0 t0Var = new t0();
        t0Var.b(Constants.INSTANCE.getSUPABASE_URL() + DomExceptionUtils.SEPARATOR);
        t0Var.f21589b = h0Var;
        t0Var.a(a.c());
        return (SupabaseApi) t0Var.c().b(SupabaseApi.class);
    }

    @Provides
    @Singleton
    @n
    public static final TmdbApi provideTmdbApi(h0 okHttpClient, @ApplicationContext final Context context) {
        g0 g0VarB = okHttpClient.b();
        g0VarB.f15665c.add(new c0() { // from class: com.arflix.tv.di.AppModule$provideTmdbApi$$inlined$-addInterceptor$1
            /* JADX WARN: Removed duplicated region for block: B:17:0x0085  */
            @Override // gb.c0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final gb.p0 intercept(gb.c0.a r12) {
                /*
                    r11 = this;
                    gb.k0 r0 = r12.request()
                    gb.b0 r1 = r0.f15730a
                    android.content.Context r2 = r1
                    java.lang.String r3 = "app_locale"
                    r4 = 0
                    android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)
                    java.lang.String r3 = "locale_tag"
                    java.lang.String r5 = "en-US"
                    java.lang.String r2 = r2.getString(r3, r5)
                    if (r2 != 0) goto L1a
                    r2 = r5
                L1a:
                    androidx.tv.foundation.lazy.list.k r1 = r1.f()
                    boolean r3 = r2.equals(r5)
                    if (r3 != 0) goto L8d
                    java.lang.String r3 = "iw"
                    java.lang.String r5 = "he"
                    java.lang.String r2 = kotlin.text.u.O(r2, r3, r5, r4)
                    r3 = 95
                    r5 = 45
                    java.lang.String r2 = r2.replace(r3, r5)
                    java.lang.Object r3 = r1.f5410d
                    java.util.ArrayList r3 = (java.util.ArrayList) r3
                    java.lang.String r8 = "language"
                    if (r3 != 0) goto L3d
                    goto L8a
                L3d:
                    r10 = 0
                    r7 = 219(0xdb, float:3.07E-43)
                    r5 = 0
                    r6 = 0
                    java.lang.String r9 = " !\"#$&'(),/:;<=>?@[]\\^`{|}~"
                    java.lang.String r3 = gb.u.c(r5, r6, r7, r8, r9, r10)
                    java.lang.Object r5 = r1.f5410d
                    java.util.ArrayList r5 = (java.util.ArrayList) r5
                    int r5 = r5.size()
                    int r5 = r5 + (-2)
                    r6 = -2
                    int r4 = i7.n.a(r5, r4, r6)
                    if (r4 > r5) goto L8a
                L59:
                    java.lang.Object r6 = r1.f5410d
                    java.util.ArrayList r6 = (java.util.ArrayList) r6
                    java.lang.Object r6 = r6.get(r5)
                    boolean r6 = r3.equals(r6)
                    if (r6 == 0) goto L85
                    java.lang.Object r6 = r1.f5410d
                    java.util.ArrayList r6 = (java.util.ArrayList) r6
                    int r7 = r5 + 1
                    r6.remove(r7)
                    java.lang.Object r6 = r1.f5410d
                    java.util.ArrayList r6 = (java.util.ArrayList) r6
                    r6.remove(r5)
                    java.lang.Object r6 = r1.f5410d
                    java.util.ArrayList r6 = (java.util.ArrayList) r6
                    boolean r6 = r6.isEmpty()
                    if (r6 == 0) goto L85
                    r3 = 0
                    r1.f5410d = r3
                    goto L8a
                L85:
                    if (r5 == r4) goto L8a
                    int r5 = r5 + (-2)
                    goto L59
                L8a:
                    r1.c(r8, r2)
                L8d:
                    gb.j0 r0 = r0.b()
                    gb.b0 r1 = r1.d()
                    r0.f15722a = r1
                    gb.k0 r0 = r0.b()
                    gb.p0 r12 = r12.a(r0)
                    return r12
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.di.AppModule$provideTmdbApi$$inlined$addInterceptor$1.intercept(gb.c0$a):gb.p0");
            }
        });
        h0 h0Var = new h0(g0VarB);
        t0 t0Var = new t0();
        t0Var.b(Constants.TMDB_BASE_URL);
        t0Var.f21589b = h0Var;
        t0Var.a(a.c());
        return (TmdbApi) t0Var.c().b(TmdbApi.class);
    }

    @Provides
    @Singleton
    @n
    public static final TraktApi provideTraktApi(h0 okHttpClient) {
        t0 t0Var = new t0();
        t0Var.b(Constants.TRAKT_API_URL);
        t0Var.f21589b = okHttpClient;
        t0Var.a(a.c());
        return (TraktApi) t0Var.c().b(TraktApi.class);
    }
}
