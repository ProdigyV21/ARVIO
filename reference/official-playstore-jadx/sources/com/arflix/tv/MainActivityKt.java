package com.arflix.tv;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.activity.t;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.navigation.c1;
import androidx.navigation.l0;
import androidx.navigation.o0;
import androidx.work.impl.r;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepositoryKt;
import com.arflix.tv.data.repository.LauncherContinueWatchingRequest;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.worker.TraktSyncWorker;
import com.google.android.gms.cast.MediaError;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.text.u;
import m2.v;
import m2.w;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a!\u0010\r\u001a\u00020\u000b*\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u000f\u0010\u000f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aë\u0001\u00101\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020!2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060.2\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007¢\u0006\u0004\b1\u00102\u001a\u0017\u00105\u001a\u00020\u000b2\u0006\u00104\u001a\u000203H\u0002¢\u0006\u0004\b5\u00106¨\u0006C²\u0006\f\u00108\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u00109\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u0010;\u001a\u00020:8\nX\u008a\u0084\u0002²\u0006\f\u0010=\u001a\u00020<8\nX\u008a\u0084\u0002²\u0006\u000e\u0010>\u001a\u00020!8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010?\u001a\u0004\u0018\u00010\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010A\u001a\u0004\u0018\u00010@8\nX\u008a\u0084\u0002²\u0006\u000e\u0010B\u001a\u00020!8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/MainActivity;", "Landroid/content/Intent;", "intent", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "parseLauncherRequest", "(Lcom/arflix/tv/MainActivity;Landroid/content/Intent;)Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "", "parseInstallPackUrl", "(Lcom/arflix/tv/MainActivity;Landroid/content/Intent;)Ljava/lang/String;", "Landroidx/activity/t;", "Lkotlin/Function0;", "Lx6/t0;", "block", "runAfterFirstDraw", "(Landroidx/activity/t;Lr7/a;)V", "ArvioLoadingScreen", "(Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileRepository", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/IptvRepository;", "iptvRepository", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "launcherContinueWatchingRepository", "", "oledBlackBackground", "skipProfileSelection", "pendingLauncherRequest", "onConsumeLauncherRequest", "pendingInstallPackUrl", "onConsumeInstallPackUrl", "", "Lcom/arflix/tv/data/model/Category;", "preloadedCategories", "Lcom/arflix/tv/data/model/MediaItem;", "preloadedHeroItem", "preloadedHeroLogoUrl", "", "preloadedLogoCache", "onExitApp", "ArflixApp", "(Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/ProfileRepository;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/WatchlistRepository;Lcom/arflix/tv/data/repository/IptvRepository;Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;ZLjava/lang/Boolean;Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;Lr7/a;Ljava/lang/String;Lr7/a;Ljava/util/List;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/util/Map;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Landroid/content/Context;", "context", "enqueueFullTraktSync", "(Landroid/content/Context;)V", "", "sweep", "logoAlpha", "Lcom/arflix/tv/data/repository/AuthState;", "authState", "Lcom/arflix/tv/ActiveProfileLoadState;", "activeProfileState", "startupIntroComplete", "lastAddonsSyncKey", "Landroidx/navigation/i;", "currentBackStackEntry", "iptvFullscreen", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MainActivityKt {

    /* JADX INFO: renamed from: com.arflix.tv.MainActivityKt$runAfterFirstDraw$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/arflix/tv/MainActivityKt$runAfterFirstDraw$1", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "onPreDraw", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ r7.a<t0> $block;
        final /* synthetic */ View $content;

        public AnonymousClass1(View view, r7.a<t0> aVar) {
            this.$content = view;
            this.$block = aVar;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.$content.getViewTreeObserver().removeOnPreDrawListener(this);
            this.$content.post(new androidx.activity.n(this.$block, 11));
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x076f  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x07aa  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x07bb  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x07f4  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0838  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0876  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0886  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0890  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0892  */
    /* JADX WARN: Removed duplicated region for block: B:471:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x08db  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x08e7  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x08e9  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x08f8  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x08fb  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x090f  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0937  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0980  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0989  */
    /* JADX WARN: Removed duplicated region for block: B:515:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ArflixApp(final com.arflix.tv.data.repository.AuthRepository r57, final com.arflix.tv.data.repository.ProfileRepository r58, final com.arflix.tv.data.repository.TraktRepository r59, final com.arflix.tv.data.repository.ProfileManager r60, final com.arflix.tv.data.repository.WatchHistoryRepository r61, final com.arflix.tv.data.repository.WatchlistRepository r62, final com.arflix.tv.data.repository.IptvRepository r63, final com.arflix.tv.data.repository.LauncherContinueWatchingRepository r64, boolean r65, java.lang.Boolean r66, com.arflix.tv.data.repository.LauncherContinueWatchingRequest r67, r7.a<x6.t0> r68, java.lang.String r69, r7.a<x6.t0> r70, java.util.List<com.arflix.tv.data.model.Category> r71, com.arflix.tv.data.model.MediaItem r72, java.lang.String r73, java.util.Map<java.lang.String, java.lang.String> r74, r7.a<x6.t0> r75, androidx.compose.runtime.Composer r76, final int r77, final int r78, final int r79) {
        /*
            Method dump skipped, instruction units count: 2483
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivityKt.ArflixApp(com.arflix.tv.data.repository.AuthRepository, com.arflix.tv.data.repository.ProfileRepository, com.arflix.tv.data.repository.TraktRepository, com.arflix.tv.data.repository.ProfileManager, com.arflix.tv.data.repository.WatchHistoryRepository, com.arflix.tv.data.repository.WatchlistRepository, com.arflix.tv.data.repository.IptvRepository, com.arflix.tv.data.repository.LauncherContinueWatchingRepository, boolean, java.lang.Boolean, com.arflix.tv.data.repository.LauncherContinueWatchingRequest, r7.a, java.lang.String, r7.a, java.util.List, com.arflix.tv.data.model.MediaItem, java.lang.String, java.util.Map, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$10(AuthRepository authRepository, ProfileRepository profileRepository, TraktRepository traktRepository, ProfileManager profileManager, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, boolean z, Boolean bool, LauncherContinueWatchingRequest launcherContinueWatchingRequest, r7.a aVar, String str, r7.a aVar2, List list, MediaItem mediaItem, String str2, Map map, r7.a aVar3, int i10, int i11, int i12, Composer composer, int i13) {
        ArflixApp(authRepository, profileRepository, traktRepository, profileManager, watchHistoryRepository, watchlistRepository, iptvRepository, launcherContinueWatchingRepository, z, bool, launcherContinueWatchingRequest, aVar, str, aVar2, list, mediaItem, str2, map, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    private static final androidx.navigation.i ArflixApp$lambda$15(State<androidx.navigation.i> state) {
        return state.getValue();
    }

    private static final boolean ArflixApp$lambda$17(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ArflixApp$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$20$0$0$0(k0 k0Var, TraktRepository traktRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, ProfileManager profileManager, ProfileRepository profileRepository) {
        m0.p(k0Var, null, 0, new MainActivityKt$ArflixApp$8$1$1$1$1(traktRepository, watchHistoryRepository, watchlistRepository, iptvRepository, profileManager, profileRepository, null), 3);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$20$0$1$0(MutableState mutableState, boolean z) {
        ArflixApp$lambda$18(mutableState, z);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$20$1$0(l0 l0Var, String str) {
        l0Var.i(str, new o(2));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$20$1$0$0(o0 o0Var) {
        o0Var.b("home");
        o0Var.a(-1);
        c1 c1Var = new c1();
        ArflixApp$lambda$20$1$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        o0Var.f4742b = true;
        return t0.f22605a;
    }

    private static final t0 ArflixApp$lambda$20$1$0$0$0(c1 c1Var) {
        c1Var.f4536a = false;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixApp$lambda$23(AuthRepository authRepository, ProfileRepository profileRepository, TraktRepository traktRepository, ProfileManager profileManager, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, boolean z, Boolean bool, LauncherContinueWatchingRequest launcherContinueWatchingRequest, r7.a aVar, String str, r7.a aVar2, List list, MediaItem mediaItem, String str2, Map map, r7.a aVar3, int i10, int i11, int i12, Composer composer, int i13) {
        ArflixApp(authRepository, profileRepository, traktRepository, profileManager, watchHistoryRepository, watchlistRepository, iptvRepository, launcherContinueWatchingRepository, z, bool, launcherContinueWatchingRequest, aVar, str, aVar2, list, mediaItem, str2, map, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AuthState ArflixApp$lambda$3(State<? extends AuthState> state) {
        return state.getValue();
    }

    private static final ActiveProfileLoadState ArflixApp$lambda$5(State<? extends ActiveProfileLoadState> state) {
        return state.getValue();
    }

    private static final boolean ArflixApp$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ArflixApp$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static final void ArvioLoadingScreen(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-838315600);
        final int i11 = 0;
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-838315600, i10, -1, "com.arflix.tv.ArvioLoadingScreen (MainActivity.kt:474)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("loading", composerStartRestartGroup, 6, 0);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Animatable animatable = (Animatable) objRememberedValue;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(animatable);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new MainActivityKt$ArvioLoadingScreen$1$1(animatable, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(t0.f22605a, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, 6);
            InfiniteRepeatableSpec infiniteRepeatableSpecM93infiniteRepeatable9IiC70o$default = AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1550, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Restart, 0L, 4, null);
            int i12 = InfiniteTransition.$stable;
            int i13 = InfiniteRepeatableSpec.$stable << 9;
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, -1.0f, 1.0f, infiniteRepeatableSpecM93infiniteRepeatable9IiC70o$default, "sweep", composerStartRestartGroup, i12 | 24960 | i13, 0);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.96f, 1.0f, AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2100, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "logoAlpha", composerStartRestartGroup, i12 | 25008 | i13, 0);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), Color.INSTANCE.m3498getBlack0d7_KjU(), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null);
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(animatable) | composerStartRestartGroup.changed(stateAnimateFloat);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new r7.l() { // from class: com.arflix.tv.h
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        switch (i11) {
                            case 0:
                                return MainActivityKt.ArvioLoadingScreen$lambda$4$0$0(animatable, stateAnimateFloat, (DrawScope) obj);
                            default:
                                return MainActivityKt.ArvioLoadingScreen$lambda$4$1$0(animatable, stateAnimateFloat, (GraphicsLayerScope) obj);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            CanvasKt.Canvas(modifierFillMaxSize$default, (r7.l) objRememberedValue3, composerStartRestartGroup, 6);
            Painter painterPainterResource = PainterResources_androidKt.painterResource(R.drawable.arvio_loading_logo, composerStartRestartGroup, 0);
            Modifier modifierM585widthInVpY3zN4$default = SizeKt.m585widthInVpY3zN4$default(SizeKt.fillMaxWidth(PaddingKt.m531paddingVpY3zN4$default(companion2, Dp.m5678constructorimpl(24), 0.0f, 2, null), 0.52f), 0.0f, Dp.m5678constructorimpl(320), 1, null);
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(animatable) | composerStartRestartGroup.changed(stateAnimateFloat2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue4 == companion.getEmpty()) {
                final int i14 = 1;
                objRememberedValue4 = new r7.l() { // from class: com.arflix.tv.h
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        switch (i14) {
                            case 0:
                                return MainActivityKt.ArvioLoadingScreen$lambda$4$0$0(animatable, stateAnimateFloat2, (DrawScope) obj);
                            default:
                                return MainActivityKt.ArvioLoadingScreen$lambda$4$1$0(animatable, stateAnimateFloat2, (GraphicsLayerScope) obj);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ImageKt.Image(painterPainterResource, BuildConfig.GITHUB_REPO, GraphicsLayerModifierKt.graphicsLayer(modifierM585widthInVpY3zN4$default, (r7.l) objRememberedValue4), (Alignment) null, ContentScale.INSTANCE.getFit(), 0.0f, (ColorFilter) null, composerStartRestartGroup, 24624, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new j(i10, i11));
        }
    }

    private static final float ArvioLoadingScreen$lambda$2(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ArvioLoadingScreen$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioLoadingScreen$lambda$4$0$0(Animatable animatable, State state, DrawScope drawScope) {
        Color.Companion companion = Color.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.K(drawScope, companion.m3498getBlack0d7_KjU(), 0L, 0L, 0.0f, null, null, 0, 126, null);
        float fFloatValue = ((Number) animatable.getValue()).floatValue();
        float fM3237getYimpl = (Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0()) - drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(8))) + drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(TsExtractor.TS_STREAM_TYPE_DTS));
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(180)) * fFloatValue;
        float fM3236getXimpl = Offset.m3236getXimpl(drawScope.mo3915getCenterF1C5BW0()) - fMo285toPx0680j_4;
        float fM3236getXimpl2 = Offset.m3236getXimpl(drawScope.mo3915getCenterF1C5BW0()) + fMo285toPx0680j_4;
        long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorKt.Color(4278251728L), fFloatValue * 0.32f, 0.0f, 0.0f, 0.0f, 14, null);
        long jOffset = OffsetKt.Offset(fM3236getXimpl, fM3237getYimpl);
        long jOffset2 = OffsetKt.Offset(fM3236getXimpl2, fM3237getYimpl);
        float fMo285toPx0680j_42 = drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl((float) 1.6d));
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.C(drawScope, jM3471copywmQWz5c$default, jOffset, jOffset2, fMo285toPx0680j_42, companion2.m3804getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
        float fMo285toPx0680j_43 = drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(34));
        float f10 = fMo285toPx0680j_4 - fMo285toPx0680j_43;
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        float fArvioLoadingScreen$lambda$2 = (ArvioLoadingScreen$lambda$2(state) * f10) + Offset.m3236getXimpl(drawScope.mo3915getCenterF1C5BW0());
        androidx.compose.ui.graphics.drawscope.c.C(drawScope, Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), fFloatValue * 0.54f, 0.0f, 0.0f, 0.0f, 14, null), OffsetKt.Offset(fArvioLoadingScreen$lambda$2 - fMo285toPx0680j_43, fM3237getYimpl), OffsetKt.Offset(fArvioLoadingScreen$lambda$2 + fMo285toPx0680j_43, fM3237getYimpl), drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl((float) 1.2d)), companion2.m3804getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioLoadingScreen$lambda$4$1$0(Animatable animatable, State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) animatable.getValue()).floatValue() * ArvioLoadingScreen$lambda$3(state));
        float fFloatValue = (((Number) animatable.getValue()).floatValue() * 0.12f) + 0.88f;
        graphicsLayerScope.setScaleX(fFloatValue);
        graphicsLayerScope.setScaleY(fFloatValue);
        graphicsLayerScope.setTranslationY(graphicsLayerScope.mo285toPx0680j_4(Dp.m5678constructorimpl(18)) * (1.0f - ((Number) animatable.getValue()).floatValue()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioLoadingScreen$lambda$5(int i10, Composer composer, int i11) {
        ArvioLoadingScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void enqueueFullTraktSync(Context context) {
        v vVar = new v(TraktSyncWorker.class, 0);
        androidx.work.impl.model.p pVar = vVar.f20220c;
        pVar.f6937q = true;
        pVar.f6938r = 1;
        x[] xVarArr = {new x(TraktSyncWorker.INPUT_SYNC_MODE, TraktSyncWorker.SYNC_MODE_FULL)};
        kb.d dVar = new kb.d(4);
        x xVar = xVarArr[0];
        dVar.n(xVar.f22609l, (String) xVar.f22608i);
        vVar.f20220c.f6926e = dVar.g();
        vVar.f20221d.add(TraktSyncWorker.TAG);
        w wVar = (w) vVar.a();
        r rVarZ = r.Z(context);
        rVarZ.getClass();
        new androidx.work.impl.m(rVarZ, "trakt_sync_after_auth", 1, Collections.singletonList(wVar), 0).J();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parseInstallPackUrl(MainActivity mainActivity, Intent intent) {
        Uri data;
        String scheme;
        String host;
        String path;
        if (intent == null || (data = intent.getData()) == null || (scheme = data.getScheme()) == null || (host = data.getHost()) == null) {
            return null;
        }
        if (scheme.equals("arvio") && host.equals("install-pack")) {
            return data.getQueryParameter("url");
        }
        if ((scheme.equals("http") || scheme.equals("https")) && host.equals("arvio.app") && (path = data.getPath()) != null && u.P(path, "/install-pack", false)) {
            return data.getQueryParameter("url");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LauncherContinueWatchingRequest parseLauncherRequest(MainActivity mainActivity, Intent intent) {
        Uri data;
        if (intent == null || (data = intent.getData()) == null) {
            return null;
        }
        return LauncherContinueWatchingRepositoryKt.toLauncherContinueWatchingRequest(data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runAfterFirstDraw(t tVar, r7.a<t0> aVar) {
        View decorView = tVar.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1(decorView, aVar));
    }
}
