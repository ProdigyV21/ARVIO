package com.arflix.tv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.view.r3;
import androidx.core.view.s3;
import androidx.core.view.t3;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRequest;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.ui.screens.crash.CrashReportActivity;
import com.arflix.tv.ui.screens.details.discord.DiscordRpcManager;
import com.arflix.tv.ui.startup.StartupState;
import com.arflix.tv.ui.startup.StartupViewModel;
import com.arflix.tv.ui.theme.ThemeKt;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.DeviceTypeKt;
import com.google.common.util.concurrent.r0;
import dagger.Lazy;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Locale;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.l0;
import r7.p;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0015\u0010\u0003R(\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u001f\u0010\u0019\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR(\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b#\u0010\u0019\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001dR(\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b'\u0010\u0019\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR(\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b+\u0010\u0019\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001dR(\u0010/\u001a\b\u0012\u0004\u0012\u00020.0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b/\u0010\u0019\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR(\u00103\u001a\b\u0012\u0004\u0012\u0002020\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b3\u0010\u0019\u001a\u0004\b4\u0010\u001b\"\u0004\b5\u0010\u001dR(\u00107\u001a\b\u0012\u0004\u0012\u0002060\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b7\u0010\u0019\u001a\u0004\b8\u0010\u001b\"\u0004\b9\u0010\u001dR(\u0010;\u001a\b\u0012\u0004\u0012\u00020:0\u00168\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b;\u0010\u0019\u001a\u0004\b<\u0010\u001b\"\u0004\b=\u0010\u001dR\u0018\u0010?\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R/\u0010I\u001a\u0004\u0018\u00010A2\b\u0010B\u001a\u0004\u0018\u00010A8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR/\u0010P\u001a\u0004\u0018\u00010J2\b\u0010B\u001a\u0004\u0018\u00010J8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010D\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001b\u0010V\u001a\u00020Q8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U¨\u0006_²\u0006\u000e\u0010W\u001a\u0004\u0018\u00010J8\nX\u008a\u0084\u0002²\u0006\u0010\u0010X\u001a\u0004\u0018\u00010\u00118\n@\nX\u008a\u008e\u0002²\u0006\f\u0010Y\u001a\u00020\u00118\nX\u008a\u0084\u0002²\u0006\u000e\u0010Z\u001a\u0004\u0018\u00010J8\nX\u008a\u0084\u0002²\u0006\u000e\u0010[\u001a\u0004\u0018\u00010J8\nX\u008a\u0084\u0002²\u0006\f\u0010\\\u001a\u00020J8\nX\u008a\u0084\u0002²\u0006\f\u0010^\u001a\u00020]8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/MainActivity;", "Landroidx/activity/t;", "<init>", "()V", "Landroid/content/Context;", "newBase", "Lx6/t0;", "attachBaseContext", "(Landroid/content/Context;)V", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "(Landroid/os/Bundle;)V", "Landroid/content/Intent;", "intent", "onNewIntent", "(Landroid/content/Intent;)V", "", "hasFocus", "onWindowFocusChanged", "(Z)V", "onDestroy", "Ldagger/Lazy;", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Ldagger/Lazy;", "getAuthRepository", "()Ldagger/Lazy;", "setAuthRepository", "(Ldagger/Lazy;)V", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileRepository", "getProfileRepository", "setProfileRepository", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "getTraktRepository", "setTraktRepository", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "getProfileManager", "setProfileManager", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "getWatchHistoryRepository", "setWatchHistoryRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "getWatchlistRepository", "setWatchlistRepository", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "launcherContinueWatchingRepository", "getLauncherContinueWatchingRepository", "setLauncherContinueWatchingRepository", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "getMediaRepository", "setMediaRepository", "Lcom/arflix/tv/data/repository/IptvRepository;", "iptvRepository", "getIptvRepository", "setIptvRepository", "Ls1/c;", "jankStats", "Ls1/c;", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "<set-?>", "pendingLauncherRequest$delegate", "Landroidx/compose/runtime/MutableState;", "getPendingLauncherRequest", "()Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "setPendingLauncherRequest", "(Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;)V", "pendingLauncherRequest", "", "pendingInstallPackUrl$delegate", "getPendingInstallPackUrl", "()Ljava/lang/String;", "setPendingInstallPackUrl", "(Ljava/lang/String;)V", "pendingInstallPackUrl", "Lcom/arflix/tv/ui/startup/StartupViewModel;", "startupViewModel$delegate", "Lx6/s;", "getStartupViewModel", "()Lcom/arflix/tv/ui/startup/StartupViewModel;", "startupViewModel", "deviceModeOverride", "skipProfileSelection", "oledBlackBackground", "accentColorName", "activeProfileId", "appLanguage", "Lcom/arflix/tv/ui/startup/StartupState;", "startupState", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@AndroidEntryPoint
public final class MainActivity extends Hilt_MainActivity {
    public static final int $stable = 8;

    @Inject
    public Lazy<AuthRepository> authRepository;

    @Inject
    public Lazy<IptvRepository> iptvRepository;
    private s1.c jankStats;

    @Inject
    public Lazy<LauncherContinueWatchingRepository> launcherContinueWatchingRepository;

    @Inject
    public Lazy<MediaRepository> mediaRepository;

    @Inject
    public Lazy<ProfileManager> profileManager;

    @Inject
    public Lazy<ProfileRepository> profileRepository;

    @Inject
    public Lazy<TraktRepository> traktRepository;

    @Inject
    public Lazy<WatchHistoryRepository> watchHistoryRepository;

    @Inject
    public Lazy<WatchlistRepository> watchlistRepository;

    /* JADX INFO: renamed from: pendingLauncherRequest$delegate, reason: from kotlin metadata */
    private final MutableState pendingLauncherRequest = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: pendingInstallPackUrl$delegate, reason: from kotlin metadata */
    private final MutableState pendingInstallPackUrl = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: startupViewModel$delegate, reason: from kotlin metadata */
    private final s startupViewModel = new androidx.core.provider.e(l0.f19747a.b(StartupViewModel.class), new MainActivity$special$$inlined$viewModels$default$2(this), new MainActivity$special$$inlined$viewModels$default$1(this), new MainActivity$special$$inlined$viewModels$default$3(null, this));

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceType.values().length];
            try {
                iArr[DeviceType.TV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceType.TABLET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceType.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.MainActivity$onCreate$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.MainActivity$onCreate$4", f = "MainActivity.kt", l = {269}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass4(d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass4 anonymousClass4 = MainActivity.this.new AnonymousClass4(dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    IptvRepository iptvRepository = MainActivity.this.getIptvRepository().get();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    Object objWarmupFromCacheOnly = iptvRepository.warmupFromCacheOnly(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objWarmupFromCacheOnly == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
            } catch (Throwable unused) {
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getPendingInstallPackUrl() {
        return (String) this.pendingInstallPackUrl.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LauncherContinueWatchingRequest getPendingLauncherRequest() {
        return (LauncherContinueWatchingRequest) this.pendingLauncherRequest.getValue();
    }

    private final StartupViewModel getStartupViewModel() {
        return (StartupViewModel) this.startupViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:63:0x019b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 onCreate$lambda$4(final com.arflix.tv.MainActivity r18, com.arflix.tv.util.DeviceType r19, androidx.compose.runtime.Composer r20, int r21) {
        /*
            Method dump skipped, instruction units count: 697
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivity.onCreate$lambda$4(com.arflix.tv.MainActivity, com.arflix.tv.util.DeviceType, androidx.compose.runtime.Composer, int):x6.t0");
    }

    private static final String onCreate$lambda$4$1(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$4$11(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$4$13(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$4$18(final State state, State state2, final MainActivity mainActivity, final MutableState mutableState, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-837828005, i10, -1, "com.arflix.tv.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:352)");
            }
            ThemeKt.ArflixTvTheme(onCreate$lambda$4$7(state), onCreate$lambda$4$9(state2), ComposableLambdaKt.rememberComposableLambda(1675866343, true, new p() { // from class: com.arflix.tv.g
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return MainActivity.onCreate$lambda$4$18$0(this.f7734i, state, mutableState, (Composer) obj, iIntValue);
                }
            }, composer, 54), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 onCreate$lambda$4$18$0(com.arflix.tv.MainActivity r23, androidx.compose.runtime.State r24, androidx.compose.runtime.MutableState r25, androidx.compose.runtime.Composer r26, int r27) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivity.onCreate$lambda$4$18$0(com.arflix.tv.MainActivity, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):x6.t0");
    }

    private static final StartupState onCreate$lambda$4$18$0$0(State<StartupState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$4$18$0$1$0(MainActivity mainActivity) {
        mainActivity.setPendingLauncherRequest(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$4$18$0$2$0(MainActivity mainActivity) {
        mainActivity.setPendingInstallPackUrl(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$4$18$0$3$0(MainActivity mainActivity) {
        mainActivity.finish();
        return t0.f22605a;
    }

    private static final Boolean onCreate$lambda$4$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean onCreate$lambda$4$7(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final String onCreate$lambda$4$9(State<String> state) {
        return state.getValue();
    }

    private static final void onCreate$lambda$5(s1.a aVar) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$6(MainActivity mainActivity) {
        m0.p(z0.g(mainActivity), null, 0, new MainActivity$onCreate$7$1(mainActivity, null), 3);
        ArflixApplication.INSTANCE.getInstance().scheduleTraktSyncIfNeeded();
        m0.p(z0.g(mainActivity), x0.f19655d, 0, new MainActivity$onCreate$7$2(mainActivity, null), 2);
        return t0.f22605a;
    }

    private final void setPendingInstallPackUrl(String str) {
        this.pendingInstallPackUrl.setValue(str);
    }

    private final void setPendingLauncherRequest(LauncherContinueWatchingRequest launcherContinueWatchingRequest) {
        this.pendingLauncherRequest.setValue(launcherContinueWatchingRequest);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context newBase) {
        String string = newBase.getSharedPreferences("app_locale", 0).getString("locale_tag", null);
        if (string == null || string.length() == 0) {
            super.attachBaseContext(newBase);
            return;
        }
        Locale localeForLanguageTag = Locale.forLanguageTag(string);
        Locale.setDefault(localeForLanguageTag);
        Configuration configuration = new Configuration(newBase.getResources().getConfiguration());
        configuration.setLocale(localeForLanguageTag);
        super.attachBaseContext(newBase.createConfigurationContext(configuration));
    }

    public final Lazy<AuthRepository> getAuthRepository() {
        Lazy<AuthRepository> lazy = this.authRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("authRepository");
        throw null;
    }

    public final Lazy<IptvRepository> getIptvRepository() {
        Lazy<IptvRepository> lazy = this.iptvRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("iptvRepository");
        throw null;
    }

    public final Lazy<LauncherContinueWatchingRepository> getLauncherContinueWatchingRepository() {
        Lazy<LauncherContinueWatchingRepository> lazy = this.launcherContinueWatchingRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("launcherContinueWatchingRepository");
        throw null;
    }

    public final Lazy<MediaRepository> getMediaRepository() {
        Lazy<MediaRepository> lazy = this.mediaRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("mediaRepository");
        throw null;
    }

    public final Lazy<ProfileManager> getProfileManager() {
        Lazy<ProfileManager> lazy = this.profileManager;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("profileManager");
        throw null;
    }

    public final Lazy<ProfileRepository> getProfileRepository() {
        Lazy<ProfileRepository> lazy = this.profileRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("profileRepository");
        throw null;
    }

    public final Lazy<TraktRepository> getTraktRepository() {
        Lazy<TraktRepository> lazy = this.traktRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("traktRepository");
        throw null;
    }

    public final Lazy<WatchHistoryRepository> getWatchHistoryRepository() {
        Lazy<WatchHistoryRepository> lazy = this.watchHistoryRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("watchHistoryRepository");
        throw null;
    }

    public final Lazy<WatchlistRepository> getWatchlistRepository() {
        Lazy<WatchlistRepository> lazy = this.watchlistRepository;
        if (lazy != null) {
            return lazy;
        }
        kotlin.jvm.internal.p.i("watchlistRepository");
        throw null;
    }

    @Override // com.arflix.tv.Hilt_MainActivity, androidx.activity.t, androidx.core.app.m, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        int i10;
        Uri data;
        (Build.VERSION.SDK_INT >= 31 ? new n0.b(this) : new kb.d(this, 11)).l();
        DeviceType deviceTypeDetectDeviceType = DeviceTypeKt.detectDeviceType(this);
        DeviceType deviceType = DeviceType.TV;
        if (deviceTypeDetectDeviceType == deviceType) {
            setTheme(R.style.Theme_ArflixTV);
        }
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
        getWindow().getDecorView().setBackgroundColor(-16777216);
        overridePendingTransition(0, 0);
        setPendingLauncherRequest(MainActivityKt.parseLauncherRequest(this, getIntent()));
        setPendingInstallPackUrl(MainActivityKt.parseInstallPackUrl(this, getIntent()));
        SharedPreferences sharedPreferences = getSharedPreferences("arvio_crash_store", 0);
        if (sharedPreferences.getBoolean("has_pending_crash_report", false)) {
            String string = sharedPreferences.getString("last_crash_id", "N/A");
            String string2 = sharedPreferences.getString("last_crash_msg", "Unexpected error");
            long j10 = sharedPreferences.getLong("last_crash_time", System.currentTimeMillis());
            sharedPreferences.edit().putBoolean("has_pending_crash_report", false).commit();
            Intent intent = new Intent(this, (Class<?>) CrashReportActivity.class);
            intent.putExtra(CrashReportActivity.EXTRA_CRASH_ID, string);
            intent.putExtra(CrashReportActivity.EXTRA_CRASH_MSG, string2);
            intent.putExtra(CrashReportActivity.EXTRA_CRASH_TIME, j10);
            startActivity(intent);
        }
        DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
        discordRpcManager.init(this);
        Intent intent2 = getIntent();
        if (intent2 != null && (data = intent2.getData()) != null) {
            Log.d("MainActivity", "Received intent data URI in onCreate: " + data);
            if (kotlin.jvm.internal.p.a(data.getScheme(), "arvio") && kotlin.jvm.internal.p.a(data.getHost(), "discord") && kotlin.jvm.internal.p.a(data.getPath(), "/auth")) {
                Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.");
                discordRpcManager.onLoginDeepLink(data);
            }
        }
        int i11 = WhenMappings.$EnumSwitchMapping$0[deviceTypeDetectDeviceType.ordinal()];
        if (i11 != 1) {
            i10 = 13;
            if (i11 != 2 && i11 != 3) {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            i10 = 0;
        }
        setRequestedOrientation(i10);
        r0.D(getWindow(), false);
        if (deviceTypeDetectDeviceType == deviceType) {
            Window window = getWindow();
            androidx.core.view.x0 x0Var = new androidx.core.view.x0(getWindow().getDecorView());
            int i12 = Build.VERSION.SDK_INT;
            k2.c t3Var = i12 >= 30 ? new t3(window, x0Var) : i12 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var);
            t3Var.D();
            t3Var.u();
        } else {
            getWindow().clearFlags(1024);
            getWindow().setStatusBarColor(0);
            getWindow().setNavigationBarColor(0);
            Window window2 = getWindow();
            androidx.core.view.x0 x0Var2 = new androidx.core.view.x0(getWindow().getDecorView());
            int i13 = Build.VERSION.SDK_INT;
            k2.c t3Var2 = i13 >= 30 ? new t3(window2, x0Var2) : i13 >= 26 ? new s3(window2, x0Var2) : new r3(window2, x0Var2);
            t3Var2.E();
            t3Var2.C();
            t3Var2.B();
        }
        m0.p(z0.g(this), x0.f19655d, 0, new AnonymousClass4(null), 2);
        e.f.a(this, ComposableLambdaKt.composableLambdaInstance(-2027569381, true, new x2.b(this, deviceTypeDetectDeviceType, 2)));
        MainActivityKt.runAfterFirstDraw(this, new f(this, 3));
    }

    @Override // com.arflix.tv.Hilt_MainActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.activity.t, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setPendingLauncherRequest(MainActivityKt.parseLauncherRequest(this, intent));
        setPendingInstallPackUrl(MainActivityKt.parseInstallPackUrl(this, intent));
        Uri data = intent.getData();
        if (data != null) {
            Log.d("MainActivity", "Received intent data URI in onNewIntent: " + data);
            if (kotlin.jvm.internal.p.a(data.getScheme(), "arvio") && kotlin.jvm.internal.p.a(data.getHost(), "discord") && kotlin.jvm.internal.p.a(data.getPath(), "/auth")) {
                Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.");
                DiscordRpcManager.INSTANCE.onLoginDeepLink(data);
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && DeviceTypeKt.detectDeviceType(this) == DeviceType.TV) {
            Window window = getWindow();
            androidx.core.view.x0 x0Var = new androidx.core.view.x0(getWindow().getDecorView());
            int i10 = Build.VERSION.SDK_INT;
            (i10 >= 30 ? new t3(window, x0Var) : i10 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var)).u();
        }
    }

    public final void setAuthRepository(Lazy<AuthRepository> lazy) {
        this.authRepository = lazy;
    }

    public final void setIptvRepository(Lazy<IptvRepository> lazy) {
        this.iptvRepository = lazy;
    }

    public final void setLauncherContinueWatchingRepository(Lazy<LauncherContinueWatchingRepository> lazy) {
        this.launcherContinueWatchingRepository = lazy;
    }

    public final void setMediaRepository(Lazy<MediaRepository> lazy) {
        this.mediaRepository = lazy;
    }

    public final void setProfileManager(Lazy<ProfileManager> lazy) {
        this.profileManager = lazy;
    }

    public final void setProfileRepository(Lazy<ProfileRepository> lazy) {
        this.profileRepository = lazy;
    }

    public final void setTraktRepository(Lazy<TraktRepository> lazy) {
        this.traktRepository = lazy;
    }

    public final void setWatchHistoryRepository(Lazy<WatchHistoryRepository> lazy) {
        this.watchHistoryRepository = lazy;
    }

    public final void setWatchlistRepository(Lazy<WatchlistRepository> lazy) {
        this.watchlistRepository = lazy;
    }
}
