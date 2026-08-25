package androidx.compose.material3;

import androidx.compose.animation.core.SpringSpec;
import androidx.compose.material3.tokens.CircularProgressIndicatorTokens;
import androidx.compose.material3.tokens.LinearProgressIndicatorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0010\u0010\u0006R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\u00020\u00178Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\u00020\u00178Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u001c\u001a\u00020\u00178Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u0017\u0010\u001e\u001a\u00020\u00178Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/material3/ProgressIndicatorDefaults;", "", "()V", "CircularDeterminateStrokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "getCircularDeterminateStrokeCap-KaPHkGw", "()I", "I", "CircularIndeterminateStrokeCap", "getCircularIndeterminateStrokeCap-KaPHkGw", "CircularStrokeWidth", "Landroidx/compose/ui/unit/Dp;", "getCircularStrokeWidth-D9Ej5fM", "()F", "F", "LinearStrokeCap", "getLinearStrokeCap-KaPHkGw", "ProgressAnimationSpec", "Landroidx/compose/animation/core/SpringSpec;", "", "getProgressAnimationSpec", "()Landroidx/compose/animation/core/SpringSpec;", "circularColor", "Landroidx/compose/ui/graphics/Color;", "getCircularColor", "(Landroidx/compose/runtime/Composer;I)J", "circularTrackColor", "getCircularTrackColor", "linearColor", "getLinearColor", "linearTrackColor", "getLinearTrackColor", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProgressIndicatorDefaults {
    public static final int $stable = 0;
    private static final int CircularDeterminateStrokeCap;
    private static final int CircularIndeterminateStrokeCap;
    private static final int LinearStrokeCap;
    private static final SpringSpec<Float> ProgressAnimationSpec;
    public static final ProgressIndicatorDefaults INSTANCE = new ProgressIndicatorDefaults();
    private static final float CircularStrokeWidth = CircularProgressIndicatorTokens.INSTANCE.m2369getActiveIndicatorWidthD9Ej5fM();

    static {
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        LinearStrokeCap = companion.m3803getButtKaPHkGw();
        CircularDeterminateStrokeCap = companion.m3803getButtKaPHkGw();
        CircularIndeterminateStrokeCap = companion.m3805getSquareKaPHkGw();
        ProgressAnimationSpec = new SpringSpec<>(1.0f, 50.0f, Float.valueOf(0.001f));
    }

    private ProgressIndicatorDefaults() {
    }

    public final long getCircularColor(Composer composer, int i10) {
        composer.startReplaceableGroup(1803349725);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1803349725, i10, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-circularColor> (ProgressIndicator.kt:601)");
        }
        long value = ColorSchemeKt.getValue(CircularProgressIndicatorTokens.INSTANCE.getActiveIndicatorColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getCircularDeterminateStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m1756getCircularDeterminateStrokeCapKaPHkGw() {
        return CircularDeterminateStrokeCap;
    }

    /* JADX INFO: renamed from: getCircularIndeterminateStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m1757getCircularIndeterminateStrokeCapKaPHkGw() {
        return CircularIndeterminateStrokeCap;
    }

    /* JADX INFO: renamed from: getCircularStrokeWidth-D9Ej5fM, reason: not valid java name */
    public final float m1758getCircularStrokeWidthD9Ej5fM() {
        return CircularStrokeWidth;
    }

    public final long getCircularTrackColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-404222247);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-404222247, i10, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-circularTrackColor> (ProgressIndicator.kt:608)");
        }
        long jM3507getTransparent0d7_KjU = Color.INSTANCE.m3507getTransparent0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return jM3507getTransparent0d7_KjU;
    }

    public final long getLinearColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-914312983);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-914312983, i10, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-linearColor> (ProgressIndicator.kt:597)");
        }
        long value = ColorSchemeKt.getValue(LinearProgressIndicatorTokens.INSTANCE.getActiveIndicatorColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getLinearStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m1759getLinearStrokeCapKaPHkGw() {
        return LinearStrokeCap;
    }

    public final long getLinearTrackColor(Composer composer, int i10) {
        composer.startReplaceableGroup(1677541593);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1677541593, i10, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-linearTrackColor> (ProgressIndicator.kt:605)");
        }
        long value = ColorSchemeKt.getValue(LinearProgressIndicatorTokens.INSTANCE.getTrackColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final SpringSpec<Float> getProgressAnimationSpec() {
        return ProgressAnimationSpec;
    }
}
