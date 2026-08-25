package com.arflix.tv;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.GifDecoder;
import android.graphics.ImageDecoderDecoder;
import android.graphics.SvgDecoder;
import android.os.Build;
import android.os.Process;
import androidx.media3.common.C;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.work.impl.r;
import coil.Coil;
import coil.ComponentRegistry;
import coil.ImageLoader;
import coil.ImageLoaderFactory;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import com.arflix.tv.data.repository.AppUsageAnalyticsRepository;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.CloudSyncCoordinator;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.RealtimeSyncManager;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.util.DataStoresKt;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.DeviceTypeKt;
import com.arflix.tv.util.DiagnosticsManager;
import com.arflix.tv.worker.TraktSyncWorker;
import dagger.hilt.android.HiltAndroidApp;
import java.io.File;
import java.lang.Thread;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import m2.b0;
import m2.t;
import m2.v;
import m2.w;
import na.h1;
import na.y0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 V2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001VB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\u0005J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0005J\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0005J\u000f\u0010\u0011\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u0005J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\"\u001a\u00020!8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010)\u001a\u00020(8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u00100\u001a\u00020/8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\"\u00107\u001a\u0002068\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010>\u001a\u00020=8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010E\u001a\u00020D8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\"\u0010L\u001a\u00020K8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0014\u0010U\u001a\u00020R8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010T¨\u0006W"}, d2 = {"Lcom/arflix/tv/ArflixApplication;", "Landroid/app/Application;", "Lm2/b;", "Lcoil/ImageLoaderFactory;", "<init>", "()V", "Lx6/t0;", "onCreate", "Lcoil/ImageLoader;", "newImageLoader", "()Lcoil/ImageLoader;", "", "level", "onTrimMemory", "(I)V", "onLowMemory", "scheduleTraktSyncIfNeeded", "clearImageMemoryCaches", "", "isLowRamDevice", "()Z", "Lka/k0;", "appScope", "Lka/k0;", "appImageLoader", "Lcoil/ImageLoader;", "Li1/b;", "workerFactory", "Li1/b;", "getWorkerFactory", "()Li1/b;", "setWorkerFactory", "(Li1/b;)V", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/ProfileManager;", "getProfileManager", "()Lcom/arflix/tv/data/repository/ProfileManager;", "setProfileManager", "(Lcom/arflix/tv/data/repository/ProfileManager;)V", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/AuthRepository;", "getAuthRepository", "()Lcom/arflix/tv/data/repository/AuthRepository;", "setAuthRepository", "(Lcom/arflix/tv/data/repository/AuthRepository;)V", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "getCloudSyncRepository", "()Lcom/arflix/tv/data/repository/CloudSyncRepository;", "setCloudSyncRepository", "(Lcom/arflix/tv/data/repository/CloudSyncRepository;)V", "Lcom/arflix/tv/data/repository/CloudSyncCoordinator;", "cloudSyncCoordinator", "Lcom/arflix/tv/data/repository/CloudSyncCoordinator;", "getCloudSyncCoordinator", "()Lcom/arflix/tv/data/repository/CloudSyncCoordinator;", "setCloudSyncCoordinator", "(Lcom/arflix/tv/data/repository/CloudSyncCoordinator;)V", "Lcom/arflix/tv/data/repository/RealtimeSyncManager;", "realtimeSyncManager", "Lcom/arflix/tv/data/repository/RealtimeSyncManager;", "getRealtimeSyncManager", "()Lcom/arflix/tv/data/repository/RealtimeSyncManager;", "setRealtimeSyncManager", "(Lcom/arflix/tv/data/repository/RealtimeSyncManager;)V", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "getWatchlistRepository", "()Lcom/arflix/tv/data/repository/WatchlistRepository;", "setWatchlistRepository", "(Lcom/arflix/tv/data/repository/WatchlistRepository;)V", "Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository;", "appUsageAnalyticsRepository", "Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository;", "getAppUsageAnalyticsRepository", "()Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository;", "setAppUsageAnalyticsRepository", "(Lcom/arflix/tv/data/repository/AppUsageAnalyticsRepository;)V", "Lm2/c;", "getWorkManagerConfiguration", "()Lm2/c;", "workManagerConfiguration", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@HiltAndroidApp
public class ArflixApplication extends Hilt_ArflixApplication implements m2.b, ImageLoaderFactory {
    private static ArflixApplication instance;
    private volatile ImageLoader appImageLoader;
    private final k0 appScope = l0.a(d7.h.a(m0.d(), x0.f19653b));

    @Inject
    public AppUsageAnalyticsRepository appUsageAnalyticsRepository;

    @Inject
    public AuthRepository authRepository;

    @Inject
    public CloudSyncCoordinator cloudSyncCoordinator;

    @Inject
    public CloudSyncRepository cloudSyncRepository;

    @Inject
    public ProfileManager profileManager;

    @Inject
    public RealtimeSyncManager realtimeSyncManager;

    @Inject
    public WatchlistRepository watchlistRepository;

    @Inject
    public i1.b workerFactory;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003R$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@BX\u0086.¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ArflixApplication$Companion;", "", "<init>", "()V", "Lx6/t0;", "trimImageMemory", "Lcom/arflix/tv/ArflixApplication;", "value", "instance", "Lcom/arflix/tv/ArflixApplication;", "getInstance", "()Lcom/arflix/tv/ArflixApplication;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ArflixApplication getInstance() {
            ArflixApplication arflixApplication = ArflixApplication.instance;
            if (arflixApplication != null) {
                return arflixApplication;
            }
            p.i("instance");
            throw null;
        }

        public final void trimImageMemory() {
            if (ArflixApplication.instance != null) {
                getInstance().clearImageMemoryCaches();
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ArflixApplication$onCreate$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ArflixApplication$onCreate$1", f = "ArflixApplication.kt", l = {88}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = ArflixApplication.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j data = DataStoresKt.getSettingsDataStore(ArflixApplication.this).getData();
                this.L$0 = k0Var;
                this.label = 1;
                obj = y0.l(data, this);
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
            c1.b bVar = (c1.b) ((c1.g) obj);
            String str = (String) bVar.f7368a.get(new c1.e(OkHttpProvider.DNS_PROVIDER_PREF_KEY));
            OkHttpProvider okHttpProvider = OkHttpProvider.INSTANCE;
            okHttpProvider.setDnsProvider(okHttpProvider.parseDnsProvider(str));
            String str2 = (String) bVar.f7368a.get(new c1.e(OkHttpProvider.USER_AGENT_PREF_KEY));
            if (str2 == null) {
                str2 = "";
            }
            okHttpProvider.setCustomUserAgent(str2);
            try {
                okHttpProvider.getDns().lookup("image.tmdb.org");
            } catch (Throwable unused) {
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ArflixApplication$onCreate$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ArflixApplication$onCreate$4", f = "ArflixApplication.kt", l = {125, 127, 128, TsExtractor.TS_STREAM_TYPE_DTS_HD, 137}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass4(d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass4 anonymousClass4 = ArflixApplication.this.new AnonymousClass4(dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
        
            if (r11.initialize(r10) == r9) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00d0, code lost:
        
            if (r11 != r9) goto L49;
         */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00be  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 216
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ArflixApplication.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ArflixApplication$onCreate$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ArflixApplication$onCreate$5", f = "ArflixApplication.kt", l = {144, 146}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass5 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass5(d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ArflixApplication.this.new AnonymousClass5(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        
            if (r7.recordAppOpen(r6) == r3) goto L20;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L1c
                if (r0 != r1) goto L14
                k2.c.G(r7)     // Catch: java.lang.Exception -> L10 java.util.concurrent.CancellationException -> L12
                goto L44
            L10:
                r7 = move-exception
                goto L3d
            L12:
                r7 = move-exception
                goto L47
            L14:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1c:
                k2.c.G(r7)
                goto L2e
            L20:
                k2.c.G(r7)
                r6.label = r2
                r4 = 3000(0xbb8, double:1.482E-320)
                java.lang.Object r7 = ka.s0.a(r4, r6)
                if (r7 != r3) goto L2e
                goto L3c
            L2e:
                com.arflix.tv.ArflixApplication r7 = com.arflix.tv.ArflixApplication.this     // Catch: java.lang.Exception -> L10 java.util.concurrent.CancellationException -> L12
                com.arflix.tv.data.repository.AppUsageAnalyticsRepository r7 = r7.getAppUsageAnalyticsRepository()     // Catch: java.lang.Exception -> L10 java.util.concurrent.CancellationException -> L12
                r6.label = r1     // Catch: java.lang.Exception -> L10 java.util.concurrent.CancellationException -> L12
                java.lang.Object r7 = r7.recordAppOpen(r6)     // Catch: java.lang.Exception -> L10 java.util.concurrent.CancellationException -> L12
                if (r7 != r3) goto L44
            L3c:
                return r3
            L3d:
                java.lang.String r0 = "ArflixApplication"
                java.lang.String r1 = "Failed to record app open analytics"
                android.util.Log.w(r0, r1, r7)
            L44:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            L47:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ArflixApplication.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ArflixApplication$onCreate$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ArflixApplication$onCreate$6", f = "ArflixApplication.kt", l = {156}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass6 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ArflixApplication$onCreate$6$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/AuthState;", "state", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/AuthState;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ArflixApplication$onCreate$6$1", f = "ArflixApplication.kt", l = {159, 165}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<AuthState, d7.d<? super t0>, Object> {
            final /* synthetic */ k0 $$this$launch;
            int I$0;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ ArflixApplication this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ArflixApplication arflixApplication, k0 k0Var, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = arflixApplication;
                this.$$this$launch = k0Var;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$$this$launch, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(AuthState authState, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(authState, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
            
                if (r7 == r5) goto L28;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = r6.L$0
                    com.arflix.tv.data.repository.AuthState r0 = (com.arflix.tv.data.repository.AuthState) r0
                    int r1 = r6.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    e7.a r5 = e7.a.f15033i
                    if (r1 == 0) goto L25
                    if (r1 == r3) goto L21
                    if (r1 != r2) goto L19
                    java.lang.Object r0 = r6.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r7)     // Catch: java.lang.Throwable -> Lb9
                    goto L7c
                L19:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L21:
                    k2.c.G(r7)
                    goto L39
                L25:
                    k2.c.G(r7)
                    boolean r7 = r0 instanceof com.arflix.tv.data.repository.AuthState.Authenticated
                    if (r7 == 0) goto L7f
                    r6.L$0 = r4
                    r6.label = r3
                    r0 = 2000(0x7d0, double:9.88E-321)
                    java.lang.Object r7 = ka.s0.a(r0, r6)
                    if (r7 != r5) goto L39
                    goto L7b
                L39:
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.AuthRepository r7 = r7.getAuthRepository()
                    java.lang.String r7 = r7.getCurrentUserId()
                    if (r7 == 0) goto Lb9
                    boolean r7 = kotlin.text.o.h0(r7)
                    if (r7 == 0) goto L4c
                    goto Lb9
                L4c:
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.CloudSyncCoordinator r7 = r7.getCloudSyncCoordinator()
                    r7.start()
                    java.lang.Boolean r7 = com.arflix.tv.BuildConfig.ENABLE_REALTIME_CLOUD_SYNC
                    boolean r7 = r7.booleanValue()
                    if (r7 == 0) goto L66
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.RealtimeSyncManager r7 = r7.getRealtimeSyncManager()
                    r7.start()
                L66:
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.CloudSyncRepository r7 = r7.getCloudSyncRepository()     // Catch: java.lang.Throwable -> Lb9
                    r6.L$0 = r4     // Catch: java.lang.Throwable -> Lb9
                    r6.L$1 = r4     // Catch: java.lang.Throwable -> Lb9
                    r0 = 0
                    r6.I$0 = r0     // Catch: java.lang.Throwable -> Lb9
                    r6.label = r2     // Catch: java.lang.Throwable -> Lb9
                    java.lang.Object r7 = com.arflix.tv.data.repository.CloudSyncRepository.pullFromCloud$default(r7, r0, r6, r3, r4)     // Catch: java.lang.Throwable -> Lb9
                    if (r7 != r5) goto L7c
                L7b:
                    return r5
                L7c:
                    com.arflix.tv.data.repository.CloudSyncRepository$RestoreResult r7 = (com.arflix.tv.data.repository.CloudSyncRepository.RestoreResult) r7     // Catch: java.lang.Throwable -> Lb9
                    goto Lb9
                L7f:
                    com.arflix.tv.data.repository.AuthState$NotAuthenticated r7 = com.arflix.tv.data.repository.AuthState.NotAuthenticated.INSTANCE
                    boolean r7 = kotlin.jvm.internal.p.a(r0, r7)
                    if (r7 == 0) goto L9a
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.RealtimeSyncManager r7 = r7.getRealtimeSyncManager()
                    r7.stop()
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.CloudSyncCoordinator r7 = r7.getCloudSyncCoordinator()
                    r7.stop()
                    goto Lb9
                L9a:
                    boolean r7 = r0 instanceof com.arflix.tv.data.repository.AuthState.Error
                    if (r7 == 0) goto Lb1
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.RealtimeSyncManager r7 = r7.getRealtimeSyncManager()
                    r7.stop()
                    com.arflix.tv.ArflixApplication r7 = r6.this$0
                    com.arflix.tv.data.repository.CloudSyncCoordinator r7 = r7.getCloudSyncCoordinator()
                    r7.stop()
                    goto Lb9
                Lb1:
                    com.arflix.tv.data.repository.AuthState$Loading r7 = com.arflix.tv.data.repository.AuthState.Loading.INSTANCE
                    boolean r7 = kotlin.jvm.internal.p.a(r0, r7)
                    if (r7 == 0) goto Lbc
                Lb9:
                    x6.t0 r7 = x6.t0.f22605a
                    return r7
                Lbc:
                    kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                    r7.<init>()
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ArflixApplication.AnonymousClass6.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public AnonymousClass6(d7.d<? super AnonymousClass6> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass6 anonymousClass6 = ArflixApplication.this.new AnonymousClass6(dVar);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                h1<AuthState> authState = ArflixApplication.this.getAuthRepository().getAuthState();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(ArflixApplication.this, k0Var, null);
                this.L$0 = null;
                this.label = 1;
                Object objG = y0.g(authState, anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objG == aVar) {
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
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass6) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearImageMemoryCaches() {
        MemoryCache memoryCache;
        ImageLoader imageLoader = this.appImageLoader;
        if (imageLoader != null && (memoryCache = imageLoader.getMemoryCache()) != null) {
            memoryCache.clear();
        }
        try {
            MemoryCache memoryCache2 = Coil.imageLoader(this).getMemoryCache();
            if (memoryCache2 != null) {
                memoryCache2.clear();
            }
        } catch (Throwable unused) {
        }
    }

    private final boolean isLowRamDevice() {
        Object systemService = getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        return activityManager != null && activityManager.isLowRamDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MemoryCache newImageLoader$lambda$0(ArflixApplication arflixApplication, boolean z, boolean z5) {
        return new MemoryCache.Builder(arflixApplication).maxSizeBytes((z && z5) ? 33554432 : z ? 50331648 : C.BUFFER_FLAG_NOT_DEPENDED_ON).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiskCache newImageLoader$lambda$1(ArflixApplication arflixApplication, boolean z) {
        return new DiskCache.Builder().directory(l7.i.t0(arflixApplication.getCacheDir(), new File("image_cache"))).maxSizeBytes(z ? 134217728L : 100663296L).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(ArflixApplication arflixApplication, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread thread, Throwable th) {
        try {
            SharedPreferences.Editor editorPutString = arflixApplication.getSharedPreferences("arvio_crash_store", 0).edit().putString("last_crash_id", UUID.randomUUID().toString());
            String simpleName = th.getClass().getSimpleName();
            String message = th.getMessage();
            editorPutString.putString("last_crash_msg", simpleName + ": " + (message != null ? kotlin.text.o.I0(200, message) : "")).putLong("last_crash_time", System.currentTimeMillis()).putString("last_crash_version", "1.9.995 (310)").putBoolean("has_pending_crash_report", true).commit();
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            } else {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable unused) {
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            } else {
                Process.killProcess(Process.myPid());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$1(ArflixApplication arflixApplication) {
        arflixApplication.getRealtimeSyncManager().markPush();
        return t0.f22605a;
    }

    public final AppUsageAnalyticsRepository getAppUsageAnalyticsRepository() {
        AppUsageAnalyticsRepository appUsageAnalyticsRepository = this.appUsageAnalyticsRepository;
        if (appUsageAnalyticsRepository != null) {
            return appUsageAnalyticsRepository;
        }
        p.i("appUsageAnalyticsRepository");
        throw null;
    }

    public final AuthRepository getAuthRepository() {
        AuthRepository authRepository = this.authRepository;
        if (authRepository != null) {
            return authRepository;
        }
        p.i("authRepository");
        throw null;
    }

    public final CloudSyncCoordinator getCloudSyncCoordinator() {
        CloudSyncCoordinator cloudSyncCoordinator = this.cloudSyncCoordinator;
        if (cloudSyncCoordinator != null) {
            return cloudSyncCoordinator;
        }
        p.i("cloudSyncCoordinator");
        throw null;
    }

    public final CloudSyncRepository getCloudSyncRepository() {
        CloudSyncRepository cloudSyncRepository = this.cloudSyncRepository;
        if (cloudSyncRepository != null) {
            return cloudSyncRepository;
        }
        p.i("cloudSyncRepository");
        throw null;
    }

    public final ProfileManager getProfileManager() {
        ProfileManager profileManager = this.profileManager;
        if (profileManager != null) {
            return profileManager;
        }
        p.i("profileManager");
        throw null;
    }

    public final RealtimeSyncManager getRealtimeSyncManager() {
        RealtimeSyncManager realtimeSyncManager = this.realtimeSyncManager;
        if (realtimeSyncManager != null) {
            return realtimeSyncManager;
        }
        p.i("realtimeSyncManager");
        throw null;
    }

    public final WatchlistRepository getWatchlistRepository() {
        WatchlistRepository watchlistRepository = this.watchlistRepository;
        if (watchlistRepository != null) {
            return watchlistRepository;
        }
        p.i("watchlistRepository");
        throw null;
    }

    @Override // m2.b
    public m2.c getWorkManagerConfiguration() {
        androidx.core.provider.j jVar = new androidx.core.provider.j(6, (byte) 0);
        jVar.f2160c = getWorkerFactory();
        jVar.f2159b = 7;
        return new m2.c(jVar);
    }

    public final i1.b getWorkerFactory() {
        i1.b bVar = this.workerFactory;
        if (bVar != null) {
            return bVar;
        }
        p.i("workerFactory");
        throw null;
    }

    @Override // coil.ImageLoaderFactory
    public ImageLoader newImageLoader() {
        int i10 = 1;
        boolean z = false;
        final boolean z5 = DeviceTypeKt.detectDeviceType(this) == DeviceType.TV;
        final boolean zIsLowRamDevice = isLowRamDevice();
        ImageLoader.Builder builderError = new ImageLoader.Builder(this).okHttpClient(OkHttpProvider.INSTANCE.getCoilClient()).memoryCache(new r7.a() { // from class: com.arflix.tv.a
            @Override // r7.a
            public final Object invoke() {
                return ArflixApplication.newImageLoader$lambda$0(this.f7601i, z5, zIsLowRamDevice);
            }
        }).diskCache(new b(0, this, z5)).crossfade(false).respectCacheHeaders(false).allowRgb565(true).bitmapConfig(Bitmap.Config.RGB_565).error(android.R.color.transparent);
        ComponentRegistry.Builder builder = new ComponentRegistry.Builder();
        kotlin.jvm.internal.h hVar = null;
        builder.add(new SvgDecoder.Factory(z, i10, hVar));
        if (Build.VERSION.SDK_INT >= 28) {
            builder.add(new ImageDecoderDecoder.Factory(z, i10, hVar));
        } else {
            builder.add(new GifDecoder.Factory(z, i10, hVar));
        }
        ImageLoader imageLoaderBuild = builderError.components(builder.build()).build();
        this.appImageLoader = imageLoaderBuild;
        return imageLoaderBuild;
    }

    @Override // com.arflix.tv.Hilt_ArflixApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkHttpProvider.INSTANCE.init(this);
        m0.p(this.appScope, x0.f19655d, 0, new AnonymousClass1(null), 2);
        DiagnosticsManager.INSTANCE.initialize(this);
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.arflix.tv.c
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                ArflixApplication.onCreate$lambda$0(this.f7607i, defaultUncaughtExceptionHandler, thread, th);
            }
        });
        getCloudSyncRepository().setOnPushCompleted(new d(this, 0));
        m0.p(this.appScope, null, 0, new AnonymousClass4(null), 3);
        m0.p(this.appScope, null, 0, new AnonymousClass5(null), 3);
        m0.p(this.appScope, null, 0, new AnonymousClass6(null), 3);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        clearImageMemoryCaches();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == 20 || level >= 5) {
            clearImageMemoryCaches();
        }
    }

    public final void scheduleTraktSyncIfNeeded() {
        v vVar = new v(TraktSyncWorker.class, 0);
        vVar.f20220c.f6928g = TimeUnit.MINUTES.toMillis(2L);
        if (Long.MAX_VALUE - System.currentTimeMillis() <= vVar.f20220c.f6928g) {
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }
        x[] xVarArr = {new x(TraktSyncWorker.INPUT_SYNC_MODE, TraktSyncWorker.SYNC_MODE_INCREMENTAL)};
        kb.d dVar = new kb.d(4);
        x xVar = xVarArr[0];
        dVar.n(xVar.f22609l, (String) xVar.f22608i);
        vVar.f20220c.f6926e = dVar.g();
        vVar.f20221d.add(TraktSyncWorker.TAG);
        w wVar = (w) vVar.a();
        TimeUnit timeUnit = TimeUnit.HOURS;
        v vVar2 = new v(TraktSyncWorker.class, 1);
        androidx.work.impl.model.p pVar = vVar2.f20220c;
        long millis = timeUnit.toMillis(6L);
        pVar.getClass();
        String str = androidx.work.impl.model.p.x;
        if (millis < 900000) {
            t.d().g(str, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        long j10 = millis < 900000 ? 900000L : millis;
        long j11 = millis < 900000 ? 900000L : millis;
        if (j10 < 900000) {
            t.d().g(str, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        pVar.f6929h = j10 >= 900000 ? j10 : 900000L;
        if (j11 < 300000) {
            t.d().g(str, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j11 > pVar.f6929h) {
            t.d().g(str, "Flex duration greater than interval duration; Changed to " + j10);
        }
        pVar.f6930i = qb.d.o(j11, 300000L, pVar.f6929h);
        vVar2.f20221d.add(TraktSyncWorker.TAG);
        b0 b0Var = (b0) vVar2.a();
        r rVarZ = r.Z(this);
        rVarZ.getClass();
        new androidx.work.impl.m(rVarZ, TraktSyncWorker.WORK_NAME_ON_OPEN, 2, Collections.singletonList(wVar), 0).J();
        r rVarZ2 = r.Z(this);
        rVarZ2.getClass();
        new androidx.work.impl.m(rVarZ2, TraktSyncWorker.WORK_NAME, 2, Collections.singletonList(b0Var), 0).J();
    }

    public final void setAppUsageAnalyticsRepository(AppUsageAnalyticsRepository appUsageAnalyticsRepository) {
        this.appUsageAnalyticsRepository = appUsageAnalyticsRepository;
    }

    public final void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public final void setCloudSyncCoordinator(CloudSyncCoordinator cloudSyncCoordinator) {
        this.cloudSyncCoordinator = cloudSyncCoordinator;
    }

    public final void setCloudSyncRepository(CloudSyncRepository cloudSyncRepository) {
        this.cloudSyncRepository = cloudSyncRepository;
    }

    public final void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public final void setRealtimeSyncManager(RealtimeSyncManager realtimeSyncManager) {
        this.realtimeSyncManager = realtimeSyncManager;
    }

    public final void setWatchlistRepository(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    public final void setWorkerFactory(i1.b bVar) {
        this.workerFactory = bVar;
    }
}
