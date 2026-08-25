package com.arflix.tv.navigation;

import android.os.Bundle;
import androidx.appcompat.widget.g4;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.datasource.cache.CacheDataSink;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.navigation.c1;
import androidx.navigation.h;
import androidx.navigation.i0;
import androidx.navigation.l0;
import androidx.navigation.o0;
import androidx.navigation.w0;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.n;
import com.arflix.tv.navigation.AppNavigationKt;
import com.arflix.tv.navigation.Screen;
import com.arflix.tv.ui.components.f3;
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.home.z;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.player.d0;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.profile.f0;
import com.arflix.tv.ui.screens.profile.g;
import com.arflix.tv.ui.screens.profile.m;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.common.util.concurrent.r0;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.text.o;
import qb.d;
import r7.a;
import r7.l;
import r7.p;
import r7.r;
import r7.s;
import w2.b;
import w2.e;
import w2.f;
import w2.i;
import w2.k;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u00ad\u0001\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/navigation/l0;", "navController", "", "startDestination", "", "Lcom/arflix/tv/data/model/Category;", "preloadedCategories", "Lcom/arflix/tv/data/model/MediaItem;", "preloadedHeroItem", "preloadedHeroLogoUrl", "", "preloadedLogoCache", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "", "isCloudConnected", "Lkotlin/Function0;", "Lx6/t0;", "onSwitchProfile", "Lkotlin/Function1;", "onTvFullscreenChanged", "onExitApp", "AppNavigation", "(Landroidx/navigation/l0;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/util/Map;Lcom/arflix/tv/data/model/Profile;ZLr7/a;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppNavigationKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:254:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppNavigation(androidx.navigation.l0 r45, java.lang.String r46, java.util.List<com.arflix.tv.data.model.Category> r47, com.arflix.tv.data.model.MediaItem r48, java.lang.String r49, java.util.Map<java.lang.String, java.lang.String> r50, com.arflix.tv.data.model.Profile r51, boolean r52, r7.a<x6.t0> r53, r7.l<? super java.lang.Boolean, x6.t0> r54, r7.a<x6.t0> r55, androidx.compose.runtime.Composer r56, int r57, int r58, int r59) {
        /*
            Method dump skipped, instruction units count: 1003
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.navigation.AppNavigationKt.AppNavigation(androidx.navigation.l0, java.lang.String, java.util.List, com.arflix.tv.data.model.MediaItem, java.lang.String, java.util.Map, com.arflix.tv.data.model.Profile, boolean, r7.a, r7.l, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$1$0(boolean z) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$10(l0 l0Var, String str, List list, MediaItem mediaItem, String str2, Map map, Profile profile, boolean z, a aVar, l lVar, a aVar2, int i10, int i11, int i12, Composer composer, int i13) {
        AppNavigation(l0Var, str, list, mediaItem, str2, map, profile, z, aVar, lVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$3$0(l0 l0Var, String str) {
        l0Var.i(str, new i(13));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$3$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$3$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        o0Var.f4742b = true;
        o0Var.f4743c = true;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$3$0$0$0(c1 c1Var) {
        c1Var.f4537b = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$4$0(l0 l0Var) {
        l0Var.i(Screen.Home.INSTANCE.getRoute(), new com.arflix.tv.util.a(7));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$4$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$4$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        o0Var.f4742b = true;
        o0Var.f4743c = false;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$4$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        c1Var.f4537b = false;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnterTransition AppNavigation$lambda$5$0(AnimatedContentTransitionScope animatedContentTransitionScope) {
        return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(AnimationConstants.DURATION_IMAGE_CROSSFADE, 0, null, 6, null), 0.0f, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExitTransition AppNavigation$lambda$6$0(AnimatedContentTransitionScope animatedContentTransitionScope) {
        return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnterTransition AppNavigation$lambda$7$0(AnimatedContentTransitionScope animatedContentTransitionScope) {
        return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(AnimationConstants.DURATION_IMAGE_CROSSFADE, 0, null, 6, null), 0.0f, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExitTransition AppNavigation$lambda$8$0(AnimatedContentTransitionScope animatedContentTransitionScope) {
        return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0(final l0 l0Var, final List list, final MediaItem mediaItem, final String str, final Map map, final Profile profile, final l lVar, final a aVar, final a aVar2, final a aVar3, final l lVar2, final boolean z, i0 i0Var) {
        int i10 = 1;
        d.r(i0Var, Screen.Login.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(1726726075, true, new e(l0Var, 0)), 126);
        d.r(i0Var, Screen.Home.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(-718289422, true, new r() { // from class: w2.h
            @Override // r7.r
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$1(list, mediaItem, str, map, profile, l0Var, lVar, aVar, aVar2, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue);
            }
        }), 126);
        d.r(i0Var, Screen.Search.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(834797939, true, new f(profile, l0Var, aVar3, lVar, aVar, 2)), 126);
        d.r(i0Var, Screen.Watchlist.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(-1907081996, true, new f(profile, l0Var, aVar3, lVar, aVar, 3)), 126);
        int i11 = 2;
        d.r(i0Var, Screen.Tv.INSTANCE.getRoute(), t7.a.E(r0.z("channelId", new i(i10)), r0.z("streamUrl", new i(i11))), ComposableLambdaKt.composableLambdaInstance(-353994635, true, new r() { // from class: w2.j
            @Override // r7.r
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$6(profile, lVar2, aVar3, lVar, aVar, l0Var, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue);
            }
        }), 124);
        d.r(i0Var, "settings?autoCloudAuth={autoCloudAuth}&initialSection={initialSection}&installPackUrl={installPackUrl}", t7.a.E(r0.z("autoCloudAuth", new i(3)), r0.z("initialSection", new i(4)), r0.z("installPackUrl", new i(5))), ComposableLambdaKt.composableLambdaInstance(1199092726, true, new f(profile, aVar3, lVar, l0Var, aVar)), 124);
        d.r(i0Var, Screen.TelegramSettings.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(-1542787209, true, new e(l0Var, i10)), 126);
        d.r(i0Var, Screen.ProfileSelection.INSTANCE.getRoute(), null, ComposableLambdaKt.composableLambdaInstance(10300152, true, new r() { // from class: w2.g
            @Override // r7.r
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$12(l0Var, z, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue);
            }
        }), 126);
        d.r(i0Var, Screen.CollectionDetails.INSTANCE.getRoute(), Collections.singletonList(r0.z("catalogId", new com.arflix.tv.util.a(10))), ComposableLambdaKt.composableLambdaInstance(1563387513, true, new z(aVar3, profile, l0Var, lVar, 1)), 124);
        d.r(i0Var, Screen.Details.INSTANCE.getRoute(), t7.a.E(r0.z("mediaType", new com.arflix.tv.util.a(11)), r0.z("mediaId", new com.arflix.tv.util.a(12)), r0.z("initialSeason", new com.arflix.tv.util.a(13)), r0.z("initialEpisode", new com.arflix.tv.util.a(14))), ComposableLambdaKt.composableLambdaInstance(-1178492422, true, new f(aVar3, profile, l0Var, lVar, aVar)), 124);
        d.r(i0Var, Screen.Player.INSTANCE.getRoute(), t7.a.E(r0.z("mediaType", new com.arflix.tv.util.a(15)), r0.z("mediaId", new com.arflix.tv.util.a(16)), r0.z("seasonNumber", new com.arflix.tv.util.a(17)), r0.z("episodeNumber", new com.arflix.tv.util.a(18)), r0.z("tmdbSeasonNumber", new com.arflix.tv.util.a(19)), r0.z("tmdbEpisodeNumber", new com.arflix.tv.util.a(20)), r0.z("kitsuId", new com.arflix.tv.util.a(21)), r0.z("kitsuEpisodeNumber", new com.arflix.tv.util.a(22)), r0.z("imdbId", new com.arflix.tv.util.a(23)), r0.z("streamUrl", new com.arflix.tv.util.a(24)), r0.z("preferredAddonId", new com.arflix.tv.util.a(25)), r0.z("preferredSourceName", new com.arflix.tv.util.a(27)), r0.z("preferredBingeGroup", new com.arflix.tv.util.a(28)), r0.z("startPositionMs", new com.arflix.tv.util.a(29)), r0.z("isLiveStream", new i(0))), ComposableLambdaKt.composableLambdaInstance(-789746104, true, new e(l0Var, i11)), 124);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$0(l0 l0Var, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1726726075, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:172)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new w2.a(l0Var, 0);
            composer.updateRememberedValue(objRememberedValue);
        }
        LoginScreenKt.LoginScreen(null, (a) objRememberedValue, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$0$0$0(l0 l0Var) {
        l0Var.i(Screen.Home.INSTANCE.getRoute(), new i(12));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$0$0$0$0(o0 o0Var) {
        o0Var.b(Screen.Login.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$0$0$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$0$0$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1(List list, MediaItem mediaItem, String str, Map map, Profile profile, l0 l0Var, l lVar, a aVar, a aVar2, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-718289422, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:183)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new e(l0Var, 3);
            composer.updateRememberedValue(objRememberedValue);
        }
        r rVar = (r) objRememberedValue;
        boolean zChangedInstance2 = composer.changedInstance(l0Var);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new n(l0Var, 3);
            composer.updateRememberedValue(objRememberedValue2);
        }
        l lVar2 = (l) objRememberedValue2;
        boolean zChanged = composer.changed(lVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new g(lVar, 13);
            composer.updateRememberedValue(objRememberedValue3);
        }
        a aVar3 = (a) objRememberedValue3;
        boolean zChanged2 = composer.changed(lVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new g(lVar, 14);
            composer.updateRememberedValue(objRememberedValue4);
        }
        a aVar4 = (a) objRememberedValue4;
        boolean zChanged3 = composer.changed(lVar);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new f3(lVar, 4);
            composer.updateRememberedValue(objRememberedValue5);
        }
        p pVar = (p) objRememberedValue5;
        boolean zChangedInstance3 = composer.changedInstance(l0Var);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new k(l0Var, 0);
            composer.updateRememberedValue(objRememberedValue6);
        }
        s sVar = (s) objRememberedValue6;
        boolean zChanged4 = composer.changed(lVar);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new g(lVar, 15);
            composer.updateRememberedValue(objRememberedValue7);
        }
        a aVar5 = (a) objRememberedValue7;
        boolean zChanged5 = composer.changed(aVar) | composer.changedInstance(l0Var);
        Object objRememberedValue8 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue8 = new b(aVar, l0Var, 4);
            composer.updateRememberedValue(objRememberedValue8);
        }
        HomeScreenKt.HomeScreen(null, list, mediaItem, str, map, profile, rVar, lVar2, aVar3, aVar4, pVar, sVar, aVar5, (a) objRememberedValue8, aVar2, composer, 0, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$0$0(l0 l0Var, MediaType mediaType, int i10, Integer num, Integer num2) {
        androidx.navigation.s.j(l0Var, Screen.Details.INSTANCE.createRoute(mediaType, i10, num, num2), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$1$0(l0 l0Var, String str) {
        androidx.navigation.s.j(l0Var, Screen.CollectionDetails.INSTANCE.createRoute(str), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$2$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$3$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$4$0(l lVar, String str, String str2) {
        lVar.invoke(Screen.Tv.INSTANCE.createRoute(str, str2));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$5$0(l0 l0Var, MediaType mediaType, int i10, String str, String str2, String str3) {
        androidx.navigation.s.j(l0Var, Screen.Player.createRoute$default(Screen.Player.INSTANCE, mediaType, i10, null, null, null, null, null, null, null, str, str2, str3, null, null, true, 12796, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$6$0(l lVar) {
        lVar.invoke(Screen.Settings.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$7$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new i(15));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$1$7$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$1$7$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$1$7$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10(Profile profile, a aVar, l lVar, l0 l0Var, a aVar2, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1199092726, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:323)");
        }
        Bundle bundleA = iVar.a();
        boolean z = bundleA != null ? bundleA.getBoolean("autoCloudAuth") : false;
        Bundle bundleA2 = iVar.a();
        String string = bundleA2 != null ? bundleA2.getString("initialSection") : null;
        Bundle bundleA3 = iVar.a();
        String string2 = bundleA3 != null ? bundleA3.getString("installPackUrl") : null;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new f0(16, aVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        a aVar3 = (a) objRememberedValue;
        boolean zChanged2 = composer.changed(lVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new g(lVar, 16);
            composer.updateRememberedValue(objRememberedValue2);
        }
        a aVar4 = (a) objRememberedValue2;
        boolean zChanged3 = composer.changed(lVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new g(lVar, 17);
            composer.updateRememberedValue(objRememberedValue3);
        }
        a aVar5 = (a) objRememberedValue3;
        boolean zChanged4 = composer.changed(lVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new g(lVar, 18);
            composer.updateRememberedValue(objRememberedValue4);
        }
        a aVar6 = (a) objRememberedValue4;
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new w2.a(l0Var, 7);
            composer.updateRememberedValue(objRememberedValue5);
        }
        a aVar7 = (a) objRememberedValue5;
        boolean zChanged5 = composer.changed(aVar2) | composer.changedInstance(l0Var);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new b(aVar2, l0Var, 5);
            composer.updateRememberedValue(objRememberedValue6);
        }
        a aVar8 = (a) objRememberedValue6;
        boolean zChangedInstance2 = composer.changedInstance(l0Var);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new w2.a(l0Var, 8);
            composer.updateRememberedValue(objRememberedValue7);
        }
        SettingsScreenKt.SettingsScreen(null, profile, z, string, string2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, (a) objRememberedValue7, composer, 0, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$0$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$1$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$2$0(l lVar) {
        lVar.invoke(Screen.Tv.createRoute$default(Screen.Tv.INSTANCE, null, null, 3, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$3$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$4$0(l0 l0Var) {
        androidx.navigation.s.j(l0Var, Screen.TelegramSettings.INSTANCE.getRoute(), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$5$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new com.arflix.tv.util.a(9));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$5$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$10$5$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$10$5$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$10$6$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$11(l0 l0Var, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1542787209, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:348)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new w2.a(l0Var, 3);
            composer.updateRememberedValue(objRememberedValue);
        }
        TelegramSettingsScreenKt.TelegramSettingsScreen((a) objRememberedValue, null, composer, 0, 2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$11$0$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$12(l0 l0Var, boolean z, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(10300152, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:353)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new w2.a(l0Var, 4);
            composer.updateRememberedValue(objRememberedValue);
        }
        a aVar = (a) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new d0(24);
            composer.updateRememberedValue(objRememberedValue2);
        }
        a aVar2 = (a) objRememberedValue2;
        boolean zChangedInstance2 = composer.changedInstance(l0Var);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new w2.a(l0Var, 5);
            composer.updateRememberedValue(objRememberedValue3);
        }
        ProfileSelectionScreenKt.ProfileSelectionScreen(null, aVar, aVar2, (a) objRememberedValue3, z, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$12$0$0(l0 l0Var) {
        l0Var.i(Screen.Home.INSTANCE.getRoute(), new com.arflix.tv.util.a(8));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$12$0$0$0(o0 o0Var) {
        o0Var.b(Screen.ProfileSelection.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$12$0$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$12$0$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$12$2$0(l0 l0Var) {
        androidx.navigation.s.j(l0Var, "settings?autoCloudAuth=true", null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$13(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14(a aVar, Profile profile, l0 l0Var, l lVar, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1563387513, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:372)");
        }
        Bundle bundleA = iVar.a();
        String string = bundleA != null ? bundleA.getString("catalogId") : null;
        if (string == null) {
            string = "";
        }
        boolean zH0 = o.h0(string);
        t0 t0Var = t0.f22605a;
        if (zH0) {
            aVar.invoke();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return t0Var;
            }
        } else {
            boolean zChangedInstance = composer.changedInstance(l0Var);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new w2.d(l0Var, 2);
                composer.updateRememberedValue(objRememberedValue);
            }
            p pVar = (p) objRememberedValue;
            boolean zChangedInstance2 = composer.changedInstance(l0Var);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new k(l0Var, 1);
                composer.updateRememberedValue(objRememberedValue2);
            }
            s sVar = (s) objRememberedValue2;
            boolean zChanged = composer.changed(aVar);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new f0(17, aVar);
                composer.updateRememberedValue(objRememberedValue3);
            }
            a aVar2 = (a) objRememberedValue3;
            boolean zChanged2 = composer.changed(lVar);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new g(lVar, 19);
                composer.updateRememberedValue(objRememberedValue4);
            }
            a aVar3 = (a) objRememberedValue4;
            boolean zChanged3 = composer.changed(lVar);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new g(lVar, 20);
                composer.updateRememberedValue(objRememberedValue5);
            }
            a aVar4 = (a) objRememberedValue5;
            boolean zChanged4 = composer.changed(lVar);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new g(lVar, 21);
                composer.updateRememberedValue(objRememberedValue6);
            }
            a aVar5 = (a) objRememberedValue6;
            boolean zChanged5 = composer.changed(lVar);
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new g(lVar, 22);
                composer.updateRememberedValue(objRememberedValue7);
            }
            a aVar6 = (a) objRememberedValue7;
            boolean zChangedInstance3 = composer.changedInstance(l0Var);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChangedInstance3 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new w2.a(l0Var, 10);
                composer.updateRememberedValue(objRememberedValue8);
            }
            CollectionDetailsScreenKt.CollectionDetailsScreen(string, profile, null, pVar, sVar, aVar2, aVar3, aVar4, aVar5, aVar6, null, (a) objRememberedValue8, composer, 0, 0, AnalyticsListener.EVENT_PLAYER_RELEASED);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$0$0(l0 l0Var, MediaType mediaType, int i10) {
        androidx.navigation.s.j(l0Var, Screen.Details.createRoute$default(Screen.Details.INSTANCE, mediaType, i10, null, null, 12, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$1$0(l0 l0Var, MediaType mediaType, int i10, String str, String str2, String str3) {
        androidx.navigation.s.j(l0Var, Screen.Player.createRoute$default(Screen.Player.INSTANCE, mediaType, i10, null, null, null, null, null, null, null, str, str2, str3, null, null, true, 12796, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$2$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$3$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$4$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$5$0(l lVar) {
        lVar.invoke(Screen.Tv.createRoute$default(Screen.Tv.INSTANCE, null, null, 3, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$6$0(l lVar) {
        lVar.invoke(Screen.Settings.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$14$7$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$15(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$16(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$17(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$18(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 AppNavigation$lambda$9$0$19(r7.a r21, com.arflix.tv.data.model.Profile r22, final androidx.navigation.l0 r23, r7.l r24, r7.a r25, androidx.compose.animation.AnimatedContentScope r26, androidx.navigation.i r27, androidx.compose.runtime.Composer r28, int r29) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.navigation.AppNavigationKt.AppNavigation$lambda$9$0$19(r7.a, com.arflix.tv.data.model.Profile, androidx.navigation.l0, r7.l, r7.a, androidx.compose.animation.AnimatedContentScope, androidx.navigation.i, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$10$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new i(6));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$10$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$19$10$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$19$10$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$11$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$2$0(l0 l0Var, MediaType mediaType, int i10, EpisodeIdentity episodeIdentity, String str, String str2, String str3, String str4, Long l10) {
        androidx.navigation.s.j(l0Var, Screen.Player.createRoute$default(Screen.Player.INSTANCE, mediaType, i10, episodeIdentity != null ? Integer.valueOf(episodeIdentity.getDisplaySeason()) : null, episodeIdentity != null ? Integer.valueOf(episodeIdentity.getDisplayEpisode()) : null, episodeIdentity != null ? Integer.valueOf(episodeIdentity.getTmdbSeason()) : null, episodeIdentity != null ? Integer.valueOf(episodeIdentity.getTmdbEpisode()) : null, episodeIdentity != null ? episodeIdentity.getKitsuId() : null, episodeIdentity != null ? episodeIdentity.getKitsuEpisode() : null, str, str2, str3, str4, null, l10, false, CacheDataSink.DEFAULT_BUFFER_SIZE, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$3$0(l0 l0Var, MediaType mediaType, int i10) {
        androidx.navigation.s.j(l0Var, Screen.Details.createRoute$default(Screen.Details.INSTANCE, mediaType, i10, null, null, 12, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$4$0(l0 l0Var, String str) {
        androidx.navigation.s.j(l0Var, Screen.CollectionDetails.INSTANCE.createRoute(str), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$5$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$6$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$7$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$8$0(l lVar) {
        lVar.invoke(Screen.Tv.createRoute$default(Screen.Tv.INSTANCE, null, null, 3, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$19$9$0(l lVar) {
        lVar.invoke(Screen.Settings.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2(Profile profile, l0 l0Var, a aVar, l lVar, a aVar2, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(834797939, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:231)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new w2.d(l0Var, 1);
            composer.updateRememberedValue(objRememberedValue);
        }
        p pVar = (p) objRememberedValue;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new f0(14, aVar);
            composer.updateRememberedValue(objRememberedValue2);
        }
        a aVar3 = (a) objRememberedValue2;
        boolean zChanged2 = composer.changed(lVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new g(lVar, 10);
            composer.updateRememberedValue(objRememberedValue3);
        }
        a aVar4 = (a) objRememberedValue3;
        boolean zChanged3 = composer.changed(lVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new g(lVar, 11);
            composer.updateRememberedValue(objRememberedValue4);
        }
        a aVar5 = (a) objRememberedValue4;
        boolean zChanged4 = composer.changed(lVar);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new g(lVar, 12);
            composer.updateRememberedValue(objRememberedValue5);
        }
        a aVar6 = (a) objRememberedValue5;
        boolean zChanged5 = composer.changed(aVar2) | composer.changedInstance(l0Var);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new b(aVar2, l0Var, 3);
            composer.updateRememberedValue(objRememberedValue6);
        }
        a aVar7 = (a) objRememberedValue6;
        boolean zChanged6 = composer.changed(aVar);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new f0(15, aVar);
            composer.updateRememberedValue(objRememberedValue7);
        }
        SearchScreenKt.SearchScreen(null, profile, pVar, aVar3, aVar4, aVar5, aVar6, aVar7, (a) objRememberedValue7, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$0$0(l0 l0Var, MediaType mediaType, int i10) {
        androidx.navigation.s.j(l0Var, Screen.Details.createRoute$default(Screen.Details.INSTANCE, mediaType, i10, null, null, 12, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$1$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$2$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$3$0(l lVar) {
        lVar.invoke(Screen.Tv.createRoute$default(Screen.Tv.INSTANCE, null, null, 3, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$4$0(l lVar) {
        lVar.invoke(Screen.Settings.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$5$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new i(11));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$5$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$2$5$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$2$5$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$2$6$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$20(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$21(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$22(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$23(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$24(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$25(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$26(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$27(h hVar) {
        hVar.f4673a.f1591c = w0.f4791b;
        hVar.a(-1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$28(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        hVar.a("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$29(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        hVar.a("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3(Profile profile, l0 l0Var, a aVar, l lVar, a aVar2, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1907081996, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:252)");
        }
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new w2.d(l0Var, 3);
            composer.updateRememberedValue(objRememberedValue);
        }
        p pVar = (p) objRememberedValue;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new f0(18, aVar);
            composer.updateRememberedValue(objRememberedValue2);
        }
        a aVar3 = (a) objRememberedValue2;
        boolean zChanged2 = composer.changed(lVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new g(lVar, 23);
            composer.updateRememberedValue(objRememberedValue3);
        }
        a aVar4 = (a) objRememberedValue3;
        boolean zChanged3 = composer.changed(lVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new g(lVar, 24);
            composer.updateRememberedValue(objRememberedValue4);
        }
        a aVar5 = (a) objRememberedValue4;
        boolean zChanged4 = composer.changed(lVar);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new m(lVar, 1);
            composer.updateRememberedValue(objRememberedValue5);
        }
        l lVar2 = (l) objRememberedValue5;
        boolean zChanged5 = composer.changed(aVar2) | composer.changedInstance(l0Var);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new b(aVar2, l0Var, 0);
            composer.updateRememberedValue(objRememberedValue6);
        }
        a aVar6 = (a) objRememberedValue6;
        boolean zChanged6 = composer.changed(aVar);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new f0(11, aVar);
            composer.updateRememberedValue(objRememberedValue7);
        }
        WatchlistScreenKt.WatchlistScreen(null, profile, pVar, aVar3, aVar4, aVar5, lVar2, aVar6, (a) objRememberedValue7, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$0$0(l0 l0Var, MediaType mediaType, int i10) {
        androidx.navigation.s.j(l0Var, Screen.Details.createRoute$default(Screen.Details.INSTANCE, mediaType, i10, null, null, 12, null), null, 6);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$1$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$2$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$3$0(l lVar) {
        lVar.invoke(Screen.Tv.createRoute$default(Screen.Tv.INSTANCE, null, null, 3, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$4$0(l lVar, String str) {
        lVar.invoke(Screen.Settings.createRoute$default(Screen.Settings.INSTANCE, false, str, 1, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$5$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new i(8));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$5$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$3$5$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$3$5$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$3$6$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$30(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        hVar.a("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$31(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        hVar.a("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$32(h hVar) {
        hVar.f4673a.f1591c = w0.f4799j;
        hVar.a("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$33(h hVar) {
        hVar.f4673a.f1591c = w0.f4793d;
        hVar.a(-1L);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$34(h hVar) {
        hVar.f4673a.f1591c = w0.f4797h;
        hVar.a(Boolean.FALSE);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 AppNavigation$lambda$9$0$35(androidx.navigation.l0 r22, androidx.compose.animation.AnimatedContentScope r23, androidx.navigation.i r24, androidx.compose.runtime.Composer r25, int r26) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.InstantiationException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.navigation.AppNavigationKt.AppNavigation$lambda$9$0$35(androidx.navigation.l0, androidx.compose.animation.AnimatedContentScope, androidx.navigation.i, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$35$12$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$35$13$0(l0 l0Var, MediaType mediaType, int i10, EpisodeIdentity episodeIdentity, String str, String str2, String str3) {
        l0Var.i(Screen.Player.createRoute$default(Screen.Player.INSTANCE, mediaType, i10, Integer.valueOf(episodeIdentity.getDisplaySeason()), Integer.valueOf(episodeIdentity.getDisplayEpisode()), Integer.valueOf(episodeIdentity.getTmdbSeason()), Integer.valueOf(episodeIdentity.getTmdbEpisode()), episodeIdentity.getKitsuId(), episodeIdentity.getKitsuEpisode(), null, null, str, str2, str3, null, false, 25344, null), new i(10));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$35$13$0$0(o0 o0Var) {
        o0Var.b(Screen.Player.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$35$13$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$35$13$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$4(h hVar) {
        g4 g4Var = hVar.f4673a;
        g4Var.f1591c = w0.f4799j;
        g4Var.f1589a = true;
        hVar.a(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$5(h hVar) {
        g4 g4Var = hVar.f4673a;
        g4Var.f1591c = w0.f4799j;
        g4Var.f1589a = true;
        hVar.a(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6(Profile profile, l lVar, a aVar, l lVar2, a aVar2, l0 l0Var, AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-353994635, i10, -1, "com.arflix.tv.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:281)");
        }
        Bundle bundleA = iVar.a();
        String string = bundleA != null ? bundleA.getString("channelId") : null;
        Bundle bundleA2 = iVar.a();
        String string2 = bundleA2 != null ? bundleA2.getString("streamUrl") : null;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new f0(12, aVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        a aVar3 = (a) objRememberedValue;
        boolean zChanged2 = composer.changed(lVar2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new g(lVar2, 2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        a aVar4 = (a) objRememberedValue2;
        boolean zChanged3 = composer.changed(lVar2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new g(lVar2, 3);
            composer.updateRememberedValue(objRememberedValue3);
        }
        a aVar5 = (a) objRememberedValue3;
        boolean zChanged4 = composer.changed(lVar2);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new g(lVar2, 4);
            composer.updateRememberedValue(objRememberedValue4);
        }
        a aVar6 = (a) objRememberedValue4;
        boolean zChanged5 = composer.changed(lVar2);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new g(lVar2, 5);
            composer.updateRememberedValue(objRememberedValue5);
        }
        a aVar7 = (a) objRememberedValue5;
        boolean zChanged6 = composer.changed(aVar2) | composer.changedInstance(l0Var);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new b(aVar2, l0Var, 1);
            composer.updateRememberedValue(objRememberedValue6);
        }
        a aVar8 = (a) objRememberedValue6;
        boolean zChangedInstance = composer.changedInstance(l0Var);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new w2.a(l0Var, 1);
            composer.updateRememberedValue(objRememberedValue7);
        }
        LiveTvScreenKt.LiveTvScreen(null, profile, string, string2, lVar, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, (a) objRememberedValue7, composer, 0, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$0$0(a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$1$0(l lVar) {
        lVar.invoke(Screen.Search.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$2$0(l lVar) {
        lVar.invoke(Screen.Watchlist.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$3$0(l lVar) {
        lVar.invoke(Screen.Settings.INSTANCE.getRoute());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$4$0(l lVar) {
        lVar.invoke(Screen.Settings.createRoute$default(Screen.Settings.INSTANCE, false, "iptv", 1, null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$5$0(a aVar, l0 l0Var) {
        aVar.invoke();
        l0Var.i(Screen.ProfileSelection.INSTANCE.getRoute(), new i(7));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$5$0$0(o0 o0Var) {
        o0Var.b(Screen.Home.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        AppNavigation$lambda$9$0$6$5$0$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        return t0.f22605a;
    }

    private static final t0 AppNavigation$lambda$9$0$6$5$0$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$6$6$0(l0 l0Var) {
        l0Var.k();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$7(h hVar) {
        hVar.f4673a.f1591c = w0.f4797h;
        hVar.a(Boolean.FALSE);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$8(h hVar) {
        g4 g4Var = hVar.f4673a;
        g4Var.f1591c = w0.f4799j;
        g4Var.f1589a = true;
        hVar.a(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AppNavigation$lambda$9$0$9(h hVar) {
        g4 g4Var = hVar.f4673a;
        g4Var.f1591c = w0.f4799j;
        g4Var.f1589a = true;
        hVar.a(null);
        return t0.f22605a;
    }
}
