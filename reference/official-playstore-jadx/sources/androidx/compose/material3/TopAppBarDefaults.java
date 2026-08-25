package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallCenteredTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006JD\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJD\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0011\u0010\u0006JD\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000eJ\u000f\u0010\u0013\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0013\u0010\u0006JD\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000eJ\u000f\u0010\u0015\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0015\u0010\u0006JD\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u000eJ)\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJM\u0010$\u001a\u00020\u001c2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\"H\u0007¢\u0006\u0004\b$\u0010%JM\u0010&\u001a\u00020\u001c2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\"H\u0007¢\u0006\u0004\b&\u0010%R\u0018\u0010*\u001a\u00020\u0004*\u00020'8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010.\u001a\u00020+8G¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0018\u00100\u001a\u00020\u0004*\u00020'8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u0010)R\u0018\u00102\u001a\u00020\u0004*\u00020'8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u0010)R\u0018\u00104\u001a\u00020\u0004*\u00020'8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u0010)\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Landroidx/compose/material3/TopAppBarDefaults;", "", "<init>", "()V", "Landroidx/compose/material3/TopAppBarColors;", "topAppBarColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TopAppBarColors;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "scrolledContainerColor", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "topAppBarColors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "smallTopAppBarColors-zjMxDiM", "smallTopAppBarColors", "centerAlignedTopAppBarColors", "centerAlignedTopAppBarColors-zjMxDiM", "mediumTopAppBarColors", "mediumTopAppBarColors-zjMxDiM", "largeTopAppBarColors", "largeTopAppBarColors-zjMxDiM", "Landroidx/compose/material3/TopAppBarState;", "state", "Lkotlin/Function0;", "", "canScroll", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "pinnedScrollBehavior", "(Landroidx/compose/material3/TopAppBarState;Lr7/a;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "Landroidx/compose/animation/core/AnimationSpec;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "flingAnimationSpec", "enterAlwaysScrollBehavior", "(Landroidx/compose/material3/TopAppBarState;Lr7/a;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "exitUntilCollapsedScrollBehavior", "Landroidx/compose/material3/ColorScheme;", "getDefaultTopAppBarColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TopAppBarColors;", "defaultTopAppBarColors", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "getDefaultCenterAlignedTopAppBarColors$material3_release", "defaultCenterAlignedTopAppBarColors", "getDefaultMediumTopAppBarColors$material3_release", "defaultMediumTopAppBarColors", "getDefaultLargeTopAppBarColors$material3_release", "defaultLargeTopAppBarColors", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TopAppBarDefaults {
    public static final int $stable = 0;
    public static final TopAppBarDefaults INSTANCE = new TopAppBarDefaults();

    /* JADX INFO: renamed from: androidx.compose.material3.TopAppBarDefaults$enterAlwaysScrollBehavior$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TopAppBarDefaults$exitUntilCollapsedScrollBehavior$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05871 extends r implements r7.a<Boolean> {
        public static final C05871 INSTANCE = new C05871();

        public C05871() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TopAppBarDefaults$pinnedScrollBehavior$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05881 extends r implements r7.a<Boolean> {
        public static final C05881 INSTANCE = new C05881();

        public C05881() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.TRUE;
        }
    }

    private TopAppBarDefaults() {
    }

    public final TopAppBarColors centerAlignedTopAppBarColors(Composer composer, int i10) {
        composer.startReplaceableGroup(513940029);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(513940029, i10, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:798)");
        }
        TopAppBarColors defaultCenterAlignedTopAppBarColors$material3_release = getDefaultCenterAlignedTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultCenterAlignedTopAppBarColors$material3_release;
    }

    /* JADX INFO: renamed from: centerAlignedTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2311centerAlignedTopAppBarColorszjMxDiM(long j10, long j11, long j12, long j13, long j14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1896017784);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896017784, i10, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:818)");
        }
        TopAppBarColors topAppBarColorsM2305copyt635Npw = getDefaultCenterAlignedTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2305copyt635Npw(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarColorsM2305copyt635Npw;
    }

    public final TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState topAppBarState, r7.a<Boolean> aVar, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i10, int i11) {
        Composer composer2;
        composer.startReplaceableGroup(959086674);
        if ((i11 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i11 & 2) != 0) {
            aVar = AnonymousClass1.INSTANCE;
        }
        if ((i11 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i11 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(959086674, i10, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:994)");
        }
        EnterAlwaysScrollBehavior enterAlwaysScrollBehavior = new EnterAlwaysScrollBehavior(topAppBarState, animationSpec, decayAnimationSpec, aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return enterAlwaysScrollBehavior;
    }

    public final TopAppBarScrollBehavior exitUntilCollapsedScrollBehavior(TopAppBarState topAppBarState, r7.a<Boolean> aVar, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i10, int i11) {
        Composer composer2;
        composer.startReplaceableGroup(-1757023234);
        if ((i11 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i11 & 2) != 0) {
            aVar = C05871.INSTANCE;
        }
        if ((i11 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i11 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1757023234, i10, -1, "androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior (AppBar.kt:1027)");
        }
        ExitUntilCollapsedScrollBehavior exitUntilCollapsedScrollBehavior = new ExitUntilCollapsedScrollBehavior(topAppBarState, animationSpec, decayAnimationSpec, aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return exitUntilCollapsedScrollBehavior;
    }

    public final TopAppBarColors getDefaultCenterAlignedTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors defaultCenterAlignedTopAppBarColorsCached = colorScheme.getDefaultCenterAlignedTopAppBarColorsCached();
        if (defaultCenterAlignedTopAppBarColorsCached != null) {
            return defaultCenterAlignedTopAppBarColorsCached;
        }
        TopAppBarSmallCenteredTokens topAppBarSmallCenteredTokens = TopAppBarSmallCenteredTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getContainerColor()), Color.m3473equalsimpl0(ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getContainerColor()), colorScheme.getSurface()) ? ColorSchemeKt.m1403surfaceColorAtElevation3ABfNKs(colorScheme, TopAppBarSmallTokens.INSTANCE.m2898getOnScrollContainerElevationD9Ej5fM()) : ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallCenteredTokens.getTrailingIconColor()), null);
        colorScheme.setDefaultCenterAlignedTopAppBarColorsCached$material3_release(topAppBarColors);
        return topAppBarColors;
    }

    public final TopAppBarColors getDefaultLargeTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors defaultLargeTopAppBarColorsCached = colorScheme.getDefaultLargeTopAppBarColorsCached();
        if (defaultLargeTopAppBarColorsCached != null) {
            return defaultLargeTopAppBarColorsCached;
        }
        TopAppBarLargeTokens topAppBarLargeTokens = TopAppBarLargeTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getContainerColor()), Color.m3473equalsimpl0(ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getContainerColor()), colorScheme.getSurface()) ? ColorSchemeKt.m1403surfaceColorAtElevation3ABfNKs(colorScheme, TopAppBarSmallTokens.INSTANCE.m2898getOnScrollContainerElevationD9Ej5fM()) : ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarLargeTokens.getTrailingIconColor()), null);
        colorScheme.setDefaultLargeTopAppBarColorsCached$material3_release(topAppBarColors);
        return topAppBarColors;
    }

    public final TopAppBarColors getDefaultMediumTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors defaultMediumTopAppBarColorsCached = colorScheme.getDefaultMediumTopAppBarColorsCached();
        if (defaultMediumTopAppBarColorsCached != null) {
            return defaultMediumTopAppBarColorsCached;
        }
        TopAppBarMediumTokens topAppBarMediumTokens = TopAppBarMediumTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getContainerColor()), Color.m3473equalsimpl0(ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getContainerColor()), colorScheme.getSurface()) ? ColorSchemeKt.m1403surfaceColorAtElevation3ABfNKs(colorScheme, TopAppBarSmallTokens.INSTANCE.m2898getOnScrollContainerElevationD9Ej5fM()) : ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarMediumTokens.getTrailingIconColor()), null);
        colorScheme.setDefaultMediumTopAppBarColorsCached$material3_release(topAppBarColors);
        return topAppBarColors;
    }

    public final TopAppBarColors getDefaultTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors defaultTopAppBarColorsCached = colorScheme.getDefaultTopAppBarColorsCached();
        if (defaultTopAppBarColorsCached != null) {
            return defaultTopAppBarColorsCached;
        }
        TopAppBarSmallTokens topAppBarSmallTokens = TopAppBarSmallTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getContainerColor()), Color.m3473equalsimpl0(ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getContainerColor()), colorScheme.getSurface()) ? ColorSchemeKt.m1403surfaceColorAtElevation3ABfNKs(colorScheme, topAppBarSmallTokens.m2898getOnScrollContainerElevationD9Ej5fM()) : ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getTrailingIconColor()), null);
        colorScheme.setDefaultTopAppBarColorsCached$material3_release(topAppBarColors);
        return topAppBarColors;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i10) {
        composer.startReplaceableGroup(2143182847);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143182847, i10, -1, "androidx.compose.material3.TopAppBarDefaults.<get-windowInsets> (AppBar.kt:788)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.INSTANCE;
        WindowInsets windowInsetsM604onlybOOhFvg = WindowInsetsKt.m604onlybOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.m614plusgK_yJZ4(companion.m624getHorizontalJoeWqyM(), companion.m628getTopJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return windowInsetsM604onlybOOhFvg;
    }

    public final TopAppBarColors largeTopAppBarColors(Composer composer, int i10) {
        composer.startReplaceableGroup(1744932393);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1744932393, i10, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:906)");
        }
        TopAppBarColors defaultLargeTopAppBarColors$material3_release = getDefaultLargeTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultLargeTopAppBarColors$material3_release;
    }

    /* JADX INFO: renamed from: largeTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2312largeTopAppBarColorszjMxDiM(long j10, long j11, long j12, long j13, long j14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1471507700);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1471507700, i10, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:927)");
        }
        TopAppBarColors topAppBarColorsM2305copyt635Npw = getDefaultLargeTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2305copyt635Npw(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarColorsM2305copyt635Npw;
    }

    public final TopAppBarColors mediumTopAppBarColors(Composer composer, int i10) {
        composer.startReplaceableGroup(1268886463);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1268886463, i10, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:853)");
        }
        TopAppBarColors defaultMediumTopAppBarColors$material3_release = getDefaultMediumTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultMediumTopAppBarColors$material3_release;
    }

    /* JADX INFO: renamed from: mediumTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2313mediumTopAppBarColorszjMxDiM(long j10, long j11, long j12, long j13, long j14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-582474442);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-582474442, i10, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:874)");
        }
        TopAppBarColors topAppBarColorsM2305copyt635Npw = getDefaultMediumTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2305copyt635Npw(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarColorsM2305copyt635Npw;
    }

    public final TopAppBarScrollBehavior pinnedScrollBehavior(TopAppBarState topAppBarState, r7.a<Boolean> aVar, Composer composer, int i10, int i11) {
        Composer composer2;
        composer.startReplaceableGroup(286497075);
        if ((i11 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i11 & 2) != 0) {
            aVar = C05881.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(286497075, i10, -1, "androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior (AppBar.kt:969)");
        }
        PinnedScrollBehavior pinnedScrollBehavior = new PinnedScrollBehavior(topAppBarState, aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return pinnedScrollBehavior;
    }

    @x6.e
    /* JADX INFO: renamed from: smallTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2314smallTopAppBarColorszjMxDiM(long j10, long j11, long j12, long j13, long j14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1717201472);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(TopAppBarSmallTokens.INSTANCE.getContainerColor(), composer, 6) : j10;
        long jM1392applyTonalElevationRFCenO8 = (i11 & 2) != 0 ? ColorSchemeKt.m1392applyTonalElevationRFCenO8(MaterialTheme.INSTANCE.getColorScheme(composer, 6), value, TopAppBarSmallTokens.INSTANCE.m2898getOnScrollContainerElevationD9Ej5fM(), composer, ((i10 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK) : j11;
        long value2 = (i11 & 4) != 0 ? ColorSchemeKt.getValue(TopAppBarSmallTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j12;
        long value3 = (i11 & 8) != 0 ? ColorSchemeKt.getValue(TopAppBarSmallTokens.INSTANCE.getHeadlineColor(), composer, 6) : j13;
        long value4 = (i11 & 16) != 0 ? ColorSchemeKt.getValue(TopAppBarSmallTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1717201472, i10, -1, "androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors (AppBar.kt:775)");
        }
        long j15 = jM1392applyTonalElevationRFCenO8;
        TopAppBarColors topAppBarColorsM2315topAppBarColorszjMxDiM = m2315topAppBarColorszjMxDiM(value, j15, value2, value3, value4, composer, 524286 & i10, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarColorsM2315topAppBarColorszjMxDiM;
    }

    public final TopAppBarColors topAppBarColors(Composer composer, int i10) {
        composer.startReplaceableGroup(-1388520854);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1388520854, i10, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:697)");
        }
        TopAppBarColors defaultTopAppBarColors$material3_release = getDefaultTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultTopAppBarColors$material3_release;
    }

    /* JADX INFO: renamed from: topAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2315topAppBarColorszjMxDiM(long j10, long j11, long j12, long j13, long j14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(2142919275);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2142919275, i10, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:717)");
        }
        TopAppBarColors topAppBarColorsM2305copyt635Npw = getDefaultTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2305copyt635Npw(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarColorsM2305copyt635Npw;
    }
}
