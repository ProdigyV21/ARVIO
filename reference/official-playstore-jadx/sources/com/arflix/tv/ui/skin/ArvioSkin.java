package com.arflix.tv.ui.skin;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioSkin;", "", "<init>", "()V", "tokens", "Lcom/arflix/tv/ui/skin/ArvioSkinTokens;", "getTokens", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioSkinTokens;", "colors", "Lcom/arflix/tv/ui/skin/ArvioColorTokens;", "getColors", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioColorTokens;", "spacing", "Lcom/arflix/tv/ui/skin/ArvioSpacingTokens;", "getSpacing", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioSpacingTokens;", "radius", "Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "getRadius", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "typography", "Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;", "getTypography", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;", "motion", "Lcom/arflix/tv/ui/skin/ArvioMotionTokens;", "getMotion", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioMotionTokens;", "focus", "Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "getFocus", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArvioSkin {
    public static final int $stable = 0;
    public static final ArvioSkin INSTANCE = new ArvioSkin();

    private ArvioSkin() {
    }

    public final ArvioColorTokens getColors(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(744159381, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-colors> (ArvioSkin.kt:65)");
        }
        ArvioColorTokens colors = getTokens(composer, i10 & 14).getColors();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return colors;
    }

    public final ArvioFocusTokens getFocus(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(673234768, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-focus> (ArvioSkin.kt:90)");
        }
        ArvioFocusTokens focus = getTokens(composer, i10 & 14).getFocus();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return focus;
    }

    public final ArvioMotionTokens getMotion(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1702399996, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-motion> (ArvioSkin.kt:85)");
        }
        ArvioMotionTokens motion = getTokens(composer, i10 & 14).getMotion();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return motion;
    }

    public final ArvioRadiusTokens getRadius(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1539295520, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-radius> (ArvioSkin.kt:75)");
        }
        ArvioRadiusTokens radius = getTokens(composer, i10 & 14).getRadius();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return radius;
    }

    public final ArvioSpacingTokens getSpacing(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1864928613, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-spacing> (ArvioSkin.kt:70)");
        }
        ArvioSpacingTokens spacing = getTokens(composer, i10 & 14).getSpacing();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return spacing;
    }

    public final ArvioSkinTokens getTokens(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-96725163, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-tokens> (ArvioSkin.kt:60)");
        }
        ArvioSkinTokens arvioSkinTokens = (ArvioSkinTokens) composer.consume(ArvioSkinKt.getLocalArvioSkinTokens());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return arvioSkinTokens;
    }

    public final ArvioTypographyTokens getTypography(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1219787779, i10, -1, "com.arflix.tv.ui.skin.ArvioSkin.<get-typography> (ArvioSkin.kt:80)");
        }
        ArvioTypographyTokens typography = getTokens(composer, i10 & 14).getTypography();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return typography;
    }
}
