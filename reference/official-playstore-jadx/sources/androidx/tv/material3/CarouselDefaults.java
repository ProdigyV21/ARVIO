package androidx.tv.material3;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001J`\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072(\b\u0002\u0010\u0010\u001a\"\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00148G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/tv/material3/CarouselDefaults;", "", "", "itemCount", "activeItemIndex", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/unit/Dp;", "spacing", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "isActive", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "indicator", "IndicatorRow-hGBTI10", "(IILandroidx/compose/ui/Modifier;FLr7/q;Landroidx/compose/runtime/Composer;II)V", "IndicatorRow", "Landroidx/compose/animation/ContentTransform;", "getContentTransform", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ContentTransform;", "contentTransform", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CarouselDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CarouselDefaults f5565a = new CarouselDefaults();

    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x017f  */
    /* JADX INFO: renamed from: IndicatorRow-hGBTI10, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m5981IndicatorRowhGBTI10(int r17, int r18, androidx.compose.ui.Modifier r19, float r20, r7.q<? super java.lang.Boolean, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, androidx.compose.runtime.Composer r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CarouselDefaults.m5981IndicatorRowhGBTI10(int, int, androidx.compose.ui.Modifier, float, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public final ContentTransform getContentTransform(Composer composer, int i10) {
        composer.startReplaceableGroup(-1830651719);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1830651719, i10, -1, "androidx.tv.material3.CarouselDefaults.<get-contentTransform> (Carousel.kt:477)");
        }
        ContentTransform contentTransform = AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return contentTransform;
    }
}
