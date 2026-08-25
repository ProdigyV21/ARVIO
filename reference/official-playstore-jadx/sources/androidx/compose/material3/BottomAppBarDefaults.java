package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.tokens.BottomAppBarTokens;
import androidx.compose.material3.tokens.FabSecondaryTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JM\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0012\u001a\u00020\u00118\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001e\u001a\u00020\u001b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\"\u001a\u00020\u001f8G¢\u0006\u0006\u001a\u0004\b \u0010!R\u0017\u0010$\u001a\u00020\u001b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b#\u0010\u001d\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006%"}, d2 = {"Landroidx/compose/material3/BottomAppBarDefaults;", "", "<init>", "()V", "Landroidx/compose/material3/BottomAppBarState;", "state", "Lkotlin/Function0;", "", "canScroll", "Landroidx/compose/animation/core/AnimationSpec;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "flingAnimationSpec", "Landroidx/compose/material3/BottomAppBarScrollBehavior;", "exitAlwaysScrollBehavior", "(Landroidx/compose/material3/BottomAppBarState;Lr7/a;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomAppBarScrollBehavior;", "Landroidx/compose/ui/unit/Dp;", "ContainerElevation", "F", "getContainerElevation-D9Ej5fM", "()F", "Landroidx/compose/foundation/layout/PaddingValues;", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "containerColor", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "getBottomAppBarFabColor", "bottomAppBarFabColor", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BottomAppBarDefaults {
    public static final int $stable = 0;
    public static final BottomAppBarDefaults INSTANCE = new BottomAppBarDefaults();
    private static final float ContainerElevation = BottomAppBarTokens.INSTANCE.m2347getContainerElevationD9Ej5fM();
    private static final PaddingValues ContentPadding = PaddingKt.m526PaddingValuesa9UjIt4$default(AppBarKt.BottomAppBarHorizontalPadding, AppBarKt.getBottomAppBarVerticalPadding(), AppBarKt.BottomAppBarHorizontalPadding, 0.0f, 8, null);

    /* JADX INFO: renamed from: androidx.compose.material3.BottomAppBarDefaults$exitAlwaysScrollBehavior$1, reason: invalid class name */
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

    private BottomAppBarDefaults() {
    }

    public final BottomAppBarScrollBehavior exitAlwaysScrollBehavior(BottomAppBarState bottomAppBarState, r7.a<Boolean> aVar, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i10, int i11) {
        Composer composer2;
        composer.startReplaceableGroup(457144034);
        if ((i11 & 1) != 0) {
            composer2 = composer;
            bottomAppBarState = AppBarKt.rememberBottomAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
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
            ComposerKt.traceEventStart(457144034, i10, -1, "androidx.compose.material3.BottomAppBarDefaults.exitAlwaysScrollBehavior (AppBar.kt:1348)");
        }
        ExitAlwaysScrollBehavior exitAlwaysScrollBehavior = new ExitAlwaysScrollBehavior(bottomAppBarState, animationSpec, decayAnimationSpec, aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return exitAlwaysScrollBehavior;
    }

    public final long getBottomAppBarFabColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-1464561486);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464561486, i10, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-bottomAppBarFabColor> (AppBar.kt:1323)");
        }
        long value = ColorSchemeKt.getValue(FabSecondaryTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final long getContainerColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-368340078);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-368340078, i10, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-containerColor> (AppBar.kt:1295)");
        }
        long value = ColorSchemeKt.getValue(BottomAppBarTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1244getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i10) {
        composer.startReplaceableGroup(688896409);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(688896409, i10, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-windowInsets> (AppBar.kt:1315)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.INSTANCE;
        WindowInsets windowInsetsM604onlybOOhFvg = WindowInsetsKt.m604onlybOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.m614plusgK_yJZ4(companion.m624getHorizontalJoeWqyM(), companion.m622getBottomJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return windowInsetsM604onlybOOhFvg;
    }
}
