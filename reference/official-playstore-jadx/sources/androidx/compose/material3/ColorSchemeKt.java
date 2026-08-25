package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorDarkTokens;
import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001a\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a´\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001aø\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a´\u0002\u0010<\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b=\u00102\u001aø\u0002\u0010<\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b>\u0010;\u001a&\u0010?\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020AH\u0001ø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u001e\u0010\u000f\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001a\u0019\u0010F\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\n\u001a\u00020\fH\u0001¢\u0006\u0002\u0010G\u001a\u001e\u0010H\u001a\u00020\u000b*\u00020\u00042\u0006\u0010@\u001a\u00020AH\u0007ø\u0001\u0000¢\u0006\u0004\bI\u0010J\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0018\u0010\n\u001a\u00020\u000b*\u00020\f8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006K"}, d2 = {"DisabledAlpha", "", "LocalColorScheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/ColorScheme;", "getLocalColorScheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalTonalElevationEnabled", "", "getLocalTonalElevationEnabled", "value", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getValue", "(Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;Landroidx/compose/runtime/Composer;I)J", "contentColorFor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "darkColorScheme", "primary", "onPrimary", "primaryContainer", "onPrimaryContainer", "inversePrimary", "secondary", "onSecondary", "secondaryContainer", "onSecondaryContainer", "tertiary", "onTertiary", "tertiaryContainer", "onTertiaryContainer", "background", "onBackground", "surface", "onSurface", "surfaceVariant", "onSurfaceVariant", "surfaceTint", "inverseSurface", "inverseOnSurface", "error", "onError", "errorContainer", "onErrorContainer", "outline", "outlineVariant", "scrim", "darkColorScheme-G1PFc-w", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "surfaceBright", "surfaceContainer", "surfaceContainerHigh", "surfaceContainerHighest", "surfaceContainerLow", "surfaceContainerLowest", "surfaceDim", "darkColorScheme-C-Xl9yA", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "lightColorScheme", "lightColorScheme-G1PFc-w", "lightColorScheme-C-Xl9yA", "applyTonalElevation", "elevation", "Landroidx/compose/ui/unit/Dp;", "applyTonalElevation-RFCenO8", "(Landroidx/compose/material3/ColorScheme;JFLandroidx/compose/runtime/Composer;I)J", "contentColorFor-4WTKRHQ", "(Landroidx/compose/material3/ColorScheme;J)J", "fromToken", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;)J", "surfaceColorAtElevation", "surfaceColorAtElevation-3ABfNKs", "(Landroidx/compose/material3/ColorScheme;F)J", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorSchemeKt {
    public static final float DisabledAlpha = 0.38f;
    private static final ProvidableCompositionLocal<ColorScheme> LocalColorScheme = CompositionLocalKt.staticCompositionLocalOf(ColorSchemeKt$LocalColorScheme$1.INSTANCE);
    private static final ProvidableCompositionLocal<Boolean> LocalTonalElevationEnabled = CompositionLocalKt.staticCompositionLocalOf(ColorSchemeKt$LocalTonalElevationEnabled$1.INSTANCE);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ColorSchemeKeyTokens.values().length];
            try {
                iArr[ColorSchemeKeyTokens.Background.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ColorSchemeKeyTokens.ErrorContainer.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseOnSurface.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InversePrimary.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseSurface.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnBackground.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnError.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnErrorContainer.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimary.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimaryContainer.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondary.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondaryContainer.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurface.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurfaceVariant.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceTint.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiary.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiaryContainer.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Outline.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OutlineVariant.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Primary.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[ColorSchemeKeyTokens.PrimaryContainer.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Scrim.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Secondary.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SecondaryContainer.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Surface.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceVariant.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceBright.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainer.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHigh.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHighest.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLow.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLowest.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceDim.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Tertiary.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[ColorSchemeKeyTokens.TertiaryContainer.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: applyTonalElevation-RFCenO8, reason: not valid java name */
    public static final long m1392applyTonalElevationRFCenO8(ColorScheme colorScheme, long j10, float f10, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1610977682, i10, -1, "androidx.compose.material3.applyTonalElevation (ColorScheme.kt:841)");
        }
        boolean zBooleanValue = ((Boolean) composer.consume(LocalTonalElevationEnabled)).booleanValue();
        if (Color.m3473equalsimpl0(j10, colorScheme.getSurface()) && zBooleanValue) {
            j10 = m1403surfaceColorAtElevation3ABfNKs(colorScheme, f10);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return j10;
    }

    /* JADX INFO: renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m1393contentColorFor4WTKRHQ(ColorScheme colorScheme, long j10) {
        return Color.m3473equalsimpl0(j10, colorScheme.getPrimary()) ? colorScheme.getOnPrimary() : Color.m3473equalsimpl0(j10, colorScheme.getSecondary()) ? colorScheme.getOnSecondary() : Color.m3473equalsimpl0(j10, colorScheme.getTertiary()) ? colorScheme.getOnTertiary() : Color.m3473equalsimpl0(j10, colorScheme.getBackground()) ? colorScheme.getOnBackground() : Color.m3473equalsimpl0(j10, colorScheme.getError()) ? colorScheme.getOnError() : Color.m3473equalsimpl0(j10, colorScheme.getPrimaryContainer()) ? colorScheme.getOnPrimaryContainer() : Color.m3473equalsimpl0(j10, colorScheme.getSecondaryContainer()) ? colorScheme.getOnSecondaryContainer() : Color.m3473equalsimpl0(j10, colorScheme.getTertiaryContainer()) ? colorScheme.getOnTertiaryContainer() : Color.m3473equalsimpl0(j10, colorScheme.getErrorContainer()) ? colorScheme.getOnErrorContainer() : Color.m3473equalsimpl0(j10, colorScheme.getInverseSurface()) ? colorScheme.getInverseOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurface()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceVariant()) ? colorScheme.getOnSurfaceVariant() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceBright()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceContainer()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceContainerHigh()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceContainerHighest()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceContainerLow()) ? colorScheme.getOnSurface() : Color.m3473equalsimpl0(j10, colorScheme.getSurfaceContainerLowest()) ? colorScheme.getOnSurface() : Color.INSTANCE.m3508getUnspecified0d7_KjU();
    }

    /* JADX INFO: renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m1394contentColorForek8zF_U(long j10, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(509589638, i10, -1, "androidx.compose.material3.contentColorFor (ColorScheme.kt:823)");
        }
        long jM1393contentColorFor4WTKRHQ = m1393contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composer, 6), j10);
        if (jM1393contentColorFor4WTKRHQ == Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            jM1393contentColorFor4WTKRHQ = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM1393contentColorFor4WTKRHQ;
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1395darkColorSchemeCXl9yA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45) {
        return new ColorScheme(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j45, j40, j41, j42, j43, j44, null);
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1396darkColorSchemeCXl9yA$default(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, int i10, int i11, Object obj) {
        long jM2396getPrimary0d7_KjU = (i10 & 1) != 0 ? ColorDarkTokens.INSTANCE.m2396getPrimary0d7_KjU() : j10;
        long jM2380getOnPrimary0d7_KjU = (i10 & 2) != 0 ? ColorDarkTokens.INSTANCE.m2380getOnPrimary0d7_KjU() : j11;
        long jM2397getPrimaryContainer0d7_KjU = (i10 & 4) != 0 ? ColorDarkTokens.INSTANCE.m2397getPrimaryContainer0d7_KjU() : j12;
        long jM2381getOnPrimaryContainer0d7_KjU = (i10 & 8) != 0 ? ColorDarkTokens.INSTANCE.m2381getOnPrimaryContainer0d7_KjU() : j13;
        long jM2375getInversePrimary0d7_KjU = (i10 & 16) != 0 ? ColorDarkTokens.INSTANCE.m2375getInversePrimary0d7_KjU() : j14;
        long jM2401getSecondary0d7_KjU = (i10 & 32) != 0 ? ColorDarkTokens.INSTANCE.m2401getSecondary0d7_KjU() : j15;
        long jM2384getOnSecondary0d7_KjU = (i10 & 64) != 0 ? ColorDarkTokens.INSTANCE.m2384getOnSecondary0d7_KjU() : j16;
        long jM2402getSecondaryContainer0d7_KjU = (i10 & 128) != 0 ? ColorDarkTokens.INSTANCE.m2402getSecondaryContainer0d7_KjU() : j17;
        long j46 = jM2396getPrimary0d7_KjU;
        long jM2385getOnSecondaryContainer0d7_KjU = (i10 & 256) != 0 ? ColorDarkTokens.INSTANCE.m2385getOnSecondaryContainer0d7_KjU() : j18;
        long jM2415getTertiary0d7_KjU = (i10 & 512) != 0 ? ColorDarkTokens.INSTANCE.m2415getTertiary0d7_KjU() : j19;
        long jM2390getOnTertiary0d7_KjU = (i10 & 1024) != 0 ? ColorDarkTokens.INSTANCE.m2390getOnTertiary0d7_KjU() : j20;
        long jM2416getTertiaryContainer0d7_KjU = (i10 & 2048) != 0 ? ColorDarkTokens.INSTANCE.m2416getTertiaryContainer0d7_KjU() : j21;
        long jM2391getOnTertiaryContainer0d7_KjU = (i10 & 4096) != 0 ? ColorDarkTokens.INSTANCE.m2391getOnTertiaryContainer0d7_KjU() : j22;
        long jM2371getBackground0d7_KjU = (i10 & 8192) != 0 ? ColorDarkTokens.INSTANCE.m2371getBackground0d7_KjU() : j23;
        long jM2377getOnBackground0d7_KjU = (i10 & 16384) != 0 ? ColorDarkTokens.INSTANCE.m2377getOnBackground0d7_KjU() : j24;
        long jM2405getSurface0d7_KjU = (i10 & 32768) != 0 ? ColorDarkTokens.INSTANCE.m2405getSurface0d7_KjU() : j25;
        long jM2388getOnSurface0d7_KjU = (i10 & 65536) != 0 ? ColorDarkTokens.INSTANCE.m2388getOnSurface0d7_KjU() : j26;
        long jM2414getSurfaceVariant0d7_KjU = (i10 & 131072) != 0 ? ColorDarkTokens.INSTANCE.m2414getSurfaceVariant0d7_KjU() : j27;
        long jM2389getOnSurfaceVariant0d7_KjU = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? ColorDarkTokens.INSTANCE.m2389getOnSurfaceVariant0d7_KjU() : j28;
        long j47 = (i10 & 524288) != 0 ? j46 : j29;
        long jM2376getInverseSurface0d7_KjU = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? ColorDarkTokens.INSTANCE.m2376getInverseSurface0d7_KjU() : j30;
        long jM2374getInverseOnSurface0d7_KjU = (i10 & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m2374getInverseOnSurface0d7_KjU() : j31;
        long jM2372getError0d7_KjU = (i10 & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m2372getError0d7_KjU() : j32;
        long jM2378getOnError0d7_KjU = (i10 & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m2378getOnError0d7_KjU() : j33;
        long jM2373getErrorContainer0d7_KjU = (i10 & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m2373getErrorContainer0d7_KjU() : j34;
        long jM2379getOnErrorContainer0d7_KjU = (i10 & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m2379getOnErrorContainer0d7_KjU() : j35;
        long jM2394getOutline0d7_KjU = (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? ColorDarkTokens.INSTANCE.m2394getOutline0d7_KjU() : j36;
        long jM2395getOutlineVariant0d7_KjU = (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorDarkTokens.INSTANCE.m2395getOutlineVariant0d7_KjU() : j37;
        long jM2400getScrim0d7_KjU = (i10 & 268435456) != 0 ? ColorDarkTokens.INSTANCE.m2400getScrim0d7_KjU() : j38;
        long jM2406getSurfaceBright0d7_KjU = (i10 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? ColorDarkTokens.INSTANCE.m2406getSurfaceBright0d7_KjU() : j39;
        long jM2407getSurfaceContainer0d7_KjU = (i10 & 1073741824) != 0 ? ColorDarkTokens.INSTANCE.m2407getSurfaceContainer0d7_KjU() : j40;
        long jM2408getSurfaceContainerHigh0d7_KjU = (i10 & Integer.MIN_VALUE) != 0 ? ColorDarkTokens.INSTANCE.m2408getSurfaceContainerHigh0d7_KjU() : j41;
        long jM2409getSurfaceContainerHighest0d7_KjU = (i11 & 1) != 0 ? ColorDarkTokens.INSTANCE.m2409getSurfaceContainerHighest0d7_KjU() : j42;
        long jM2410getSurfaceContainerLow0d7_KjU = (i11 & 2) != 0 ? ColorDarkTokens.INSTANCE.m2410getSurfaceContainerLow0d7_KjU() : j43;
        long jM2411getSurfaceContainerLowest0d7_KjU = (i11 & 4) != 0 ? ColorDarkTokens.INSTANCE.m2411getSurfaceContainerLowest0d7_KjU() : j44;
        if ((i11 & 8) != 0) {
            j45 = ColorDarkTokens.INSTANCE.m2412getSurfaceDim0d7_KjU();
        }
        return m1395darkColorSchemeCXl9yA(j46, jM2380getOnPrimary0d7_KjU, jM2397getPrimaryContainer0d7_KjU, jM2381getOnPrimaryContainer0d7_KjU, jM2375getInversePrimary0d7_KjU, jM2401getSecondary0d7_KjU, jM2384getOnSecondary0d7_KjU, jM2402getSecondaryContainer0d7_KjU, jM2385getOnSecondaryContainer0d7_KjU, jM2415getTertiary0d7_KjU, jM2390getOnTertiary0d7_KjU, jM2416getTertiaryContainer0d7_KjU, jM2391getOnTertiaryContainer0d7_KjU, jM2371getBackground0d7_KjU, jM2377getOnBackground0d7_KjU, jM2405getSurface0d7_KjU, jM2388getOnSurface0d7_KjU, jM2414getSurfaceVariant0d7_KjU, jM2389getOnSurfaceVariant0d7_KjU, j47, jM2376getInverseSurface0d7_KjU, jM2374getInverseOnSurface0d7_KjU, jM2372getError0d7_KjU, jM2378getOnError0d7_KjU, jM2373getErrorContainer0d7_KjU, jM2379getOnErrorContainer0d7_KjU, jM2394getOutline0d7_KjU, jM2395getOutlineVariant0d7_KjU, jM2400getScrim0d7_KjU, jM2406getSurfaceBright0d7_KjU, jM2407getSurfaceContainer0d7_KjU, jM2408getSurfaceContainerHigh0d7_KjU, jM2409getSurfaceContainerHighest0d7_KjU, jM2410getSurfaceContainerLow0d7_KjU, jM2411getSurfaceContainerLowest0d7_KjU, j45);
    }

    @x6.e
    /* JADX INFO: renamed from: darkColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1397darkColorSchemeG1PFcw(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38) {
        return m1396darkColorSchemeCXl9yA$default(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* JADX INFO: renamed from: darkColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1398darkColorSchemeG1PFcw$default(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, int i10, Object obj) {
        long jM2396getPrimary0d7_KjU = (i10 & 1) != 0 ? ColorDarkTokens.INSTANCE.m2396getPrimary0d7_KjU() : j10;
        long jM2380getOnPrimary0d7_KjU = (i10 & 2) != 0 ? ColorDarkTokens.INSTANCE.m2380getOnPrimary0d7_KjU() : j11;
        long jM2397getPrimaryContainer0d7_KjU = (i10 & 4) != 0 ? ColorDarkTokens.INSTANCE.m2397getPrimaryContainer0d7_KjU() : j12;
        long jM2381getOnPrimaryContainer0d7_KjU = (i10 & 8) != 0 ? ColorDarkTokens.INSTANCE.m2381getOnPrimaryContainer0d7_KjU() : j13;
        long jM2375getInversePrimary0d7_KjU = (i10 & 16) != 0 ? ColorDarkTokens.INSTANCE.m2375getInversePrimary0d7_KjU() : j14;
        long jM2401getSecondary0d7_KjU = (i10 & 32) != 0 ? ColorDarkTokens.INSTANCE.m2401getSecondary0d7_KjU() : j15;
        long jM2384getOnSecondary0d7_KjU = (i10 & 64) != 0 ? ColorDarkTokens.INSTANCE.m2384getOnSecondary0d7_KjU() : j16;
        long jM2402getSecondaryContainer0d7_KjU = (i10 & 128) != 0 ? ColorDarkTokens.INSTANCE.m2402getSecondaryContainer0d7_KjU() : j17;
        long j39 = jM2396getPrimary0d7_KjU;
        long jM2385getOnSecondaryContainer0d7_KjU = (i10 & 256) != 0 ? ColorDarkTokens.INSTANCE.m2385getOnSecondaryContainer0d7_KjU() : j18;
        long jM2415getTertiary0d7_KjU = (i10 & 512) != 0 ? ColorDarkTokens.INSTANCE.m2415getTertiary0d7_KjU() : j19;
        long jM2390getOnTertiary0d7_KjU = (i10 & 1024) != 0 ? ColorDarkTokens.INSTANCE.m2390getOnTertiary0d7_KjU() : j20;
        long jM2416getTertiaryContainer0d7_KjU = (i10 & 2048) != 0 ? ColorDarkTokens.INSTANCE.m2416getTertiaryContainer0d7_KjU() : j21;
        long jM2391getOnTertiaryContainer0d7_KjU = (i10 & 4096) != 0 ? ColorDarkTokens.INSTANCE.m2391getOnTertiaryContainer0d7_KjU() : j22;
        long jM2371getBackground0d7_KjU = (i10 & 8192) != 0 ? ColorDarkTokens.INSTANCE.m2371getBackground0d7_KjU() : j23;
        long jM2377getOnBackground0d7_KjU = (i10 & 16384) != 0 ? ColorDarkTokens.INSTANCE.m2377getOnBackground0d7_KjU() : j24;
        long jM2405getSurface0d7_KjU = (i10 & 32768) != 0 ? ColorDarkTokens.INSTANCE.m2405getSurface0d7_KjU() : j25;
        long jM2388getOnSurface0d7_KjU = (i10 & 65536) != 0 ? ColorDarkTokens.INSTANCE.m2388getOnSurface0d7_KjU() : j26;
        long jM2414getSurfaceVariant0d7_KjU = (i10 & 131072) != 0 ? ColorDarkTokens.INSTANCE.m2414getSurfaceVariant0d7_KjU() : j27;
        long jM2389getOnSurfaceVariant0d7_KjU = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? ColorDarkTokens.INSTANCE.m2389getOnSurfaceVariant0d7_KjU() : j28;
        long j40 = (i10 & 524288) != 0 ? j39 : j29;
        long jM2376getInverseSurface0d7_KjU = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? ColorDarkTokens.INSTANCE.m2376getInverseSurface0d7_KjU() : j30;
        long jM2374getInverseOnSurface0d7_KjU = (i10 & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m2374getInverseOnSurface0d7_KjU() : j31;
        long jM2372getError0d7_KjU = (i10 & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m2372getError0d7_KjU() : j32;
        long jM2378getOnError0d7_KjU = (i10 & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m2378getOnError0d7_KjU() : j33;
        long jM2373getErrorContainer0d7_KjU = (i10 & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m2373getErrorContainer0d7_KjU() : j34;
        long jM2379getOnErrorContainer0d7_KjU = (i10 & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m2379getOnErrorContainer0d7_KjU() : j35;
        long jM2394getOutline0d7_KjU = (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? ColorDarkTokens.INSTANCE.m2394getOutline0d7_KjU() : j36;
        long jM2395getOutlineVariant0d7_KjU = (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorDarkTokens.INSTANCE.m2395getOutlineVariant0d7_KjU() : j37;
        if ((i10 & 268435456) != 0) {
            j38 = ColorDarkTokens.INSTANCE.m2400getScrim0d7_KjU();
        }
        return m1397darkColorSchemeG1PFcw(j39, jM2380getOnPrimary0d7_KjU, jM2397getPrimaryContainer0d7_KjU, jM2381getOnPrimaryContainer0d7_KjU, jM2375getInversePrimary0d7_KjU, jM2401getSecondary0d7_KjU, jM2384getOnSecondary0d7_KjU, jM2402getSecondaryContainer0d7_KjU, jM2385getOnSecondaryContainer0d7_KjU, jM2415getTertiary0d7_KjU, jM2390getOnTertiary0d7_KjU, jM2416getTertiaryContainer0d7_KjU, jM2391getOnTertiaryContainer0d7_KjU, jM2371getBackground0d7_KjU, jM2377getOnBackground0d7_KjU, jM2405getSurface0d7_KjU, jM2388getOnSurface0d7_KjU, jM2414getSurfaceVariant0d7_KjU, jM2389getOnSurfaceVariant0d7_KjU, j40, jM2376getInverseSurface0d7_KjU, jM2374getInverseOnSurface0d7_KjU, jM2372getError0d7_KjU, jM2378getOnError0d7_KjU, jM2373getErrorContainer0d7_KjU, jM2379getOnErrorContainer0d7_KjU, jM2394getOutline0d7_KjU, jM2395getOutlineVariant0d7_KjU, j38);
    }

    public static final long fromToken(ColorScheme colorScheme, ColorSchemeKeyTokens colorSchemeKeyTokens) {
        switch (WhenMappings.$EnumSwitchMapping$0[colorSchemeKeyTokens.ordinal()]) {
            case 1:
                return colorScheme.getBackground();
            case 2:
                return colorScheme.getError();
            case 3:
                return colorScheme.getErrorContainer();
            case 4:
                return colorScheme.getInverseOnSurface();
            case 5:
                return colorScheme.getInversePrimary();
            case 6:
                return colorScheme.getInverseSurface();
            case 7:
                return colorScheme.getOnBackground();
            case 8:
                return colorScheme.getOnError();
            case 9:
                return colorScheme.getOnErrorContainer();
            case 10:
                return colorScheme.getOnPrimary();
            case 11:
                return colorScheme.getOnPrimaryContainer();
            case 12:
                return colorScheme.getOnSecondary();
            case 13:
                return colorScheme.getOnSecondaryContainer();
            case 14:
                return colorScheme.getOnSurface();
            case 15:
                return colorScheme.getOnSurfaceVariant();
            case 16:
                return colorScheme.getSurfaceTint();
            case 17:
                return colorScheme.getOnTertiary();
            case 18:
                return colorScheme.getOnTertiaryContainer();
            case 19:
                return colorScheme.getOutline();
            case 20:
                return colorScheme.getOutlineVariant();
            case 21:
                return colorScheme.getPrimary();
            case 22:
                return colorScheme.getPrimaryContainer();
            case 23:
                return colorScheme.getScrim();
            case 24:
                return colorScheme.getSecondary();
            case 25:
                return colorScheme.getSecondaryContainer();
            case 26:
                return colorScheme.getSurface();
            case 27:
                return colorScheme.getSurfaceVariant();
            case 28:
                return colorScheme.getSurfaceBright();
            case 29:
                return colorScheme.getSurfaceContainer();
            case 30:
                return colorScheme.getSurfaceContainerHigh();
            case 31:
                return colorScheme.getSurfaceContainerHighest();
            case 32:
                return colorScheme.getSurfaceContainerLow();
            case 33:
                return colorScheme.getSurfaceContainerLowest();
            case 34:
                return colorScheme.getSurfaceDim();
            case 35:
                return colorScheme.getTertiary();
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                return colorScheme.getTertiaryContainer();
            default:
                return Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
    }

    public static final ProvidableCompositionLocal<ColorScheme> getLocalColorScheme() {
        return LocalColorScheme;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalTonalElevationEnabled() {
        return LocalTonalElevationEnabled;
    }

    public static final long getValue(ColorSchemeKeyTokens colorSchemeKeyTokens, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-810780884, i10, -1, "androidx.compose.material3.<get-value> (ColorScheme.kt:945)");
        }
        long jFromToken = fromToken(MaterialTheme.INSTANCE.getColorScheme(composer, 6), colorSchemeKeyTokens);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jFromToken;
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1399lightColorSchemeCXl9yA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45) {
        return new ColorScheme(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j45, j40, j41, j42, j43, j44, null);
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1400lightColorSchemeCXl9yA$default(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, int i10, int i11, Object obj) {
        long jM2444getPrimary0d7_KjU = (i10 & 1) != 0 ? ColorLightTokens.INSTANCE.m2444getPrimary0d7_KjU() : j10;
        long jM2428getOnPrimary0d7_KjU = (i10 & 2) != 0 ? ColorLightTokens.INSTANCE.m2428getOnPrimary0d7_KjU() : j11;
        long jM2445getPrimaryContainer0d7_KjU = (i10 & 4) != 0 ? ColorLightTokens.INSTANCE.m2445getPrimaryContainer0d7_KjU() : j12;
        long jM2429getOnPrimaryContainer0d7_KjU = (i10 & 8) != 0 ? ColorLightTokens.INSTANCE.m2429getOnPrimaryContainer0d7_KjU() : j13;
        long jM2423getInversePrimary0d7_KjU = (i10 & 16) != 0 ? ColorLightTokens.INSTANCE.m2423getInversePrimary0d7_KjU() : j14;
        long jM2449getSecondary0d7_KjU = (i10 & 32) != 0 ? ColorLightTokens.INSTANCE.m2449getSecondary0d7_KjU() : j15;
        long jM2432getOnSecondary0d7_KjU = (i10 & 64) != 0 ? ColorLightTokens.INSTANCE.m2432getOnSecondary0d7_KjU() : j16;
        long jM2450getSecondaryContainer0d7_KjU = (i10 & 128) != 0 ? ColorLightTokens.INSTANCE.m2450getSecondaryContainer0d7_KjU() : j17;
        long j46 = jM2444getPrimary0d7_KjU;
        long jM2433getOnSecondaryContainer0d7_KjU = (i10 & 256) != 0 ? ColorLightTokens.INSTANCE.m2433getOnSecondaryContainer0d7_KjU() : j18;
        long jM2463getTertiary0d7_KjU = (i10 & 512) != 0 ? ColorLightTokens.INSTANCE.m2463getTertiary0d7_KjU() : j19;
        long jM2438getOnTertiary0d7_KjU = (i10 & 1024) != 0 ? ColorLightTokens.INSTANCE.m2438getOnTertiary0d7_KjU() : j20;
        long jM2464getTertiaryContainer0d7_KjU = (i10 & 2048) != 0 ? ColorLightTokens.INSTANCE.m2464getTertiaryContainer0d7_KjU() : j21;
        long jM2439getOnTertiaryContainer0d7_KjU = (i10 & 4096) != 0 ? ColorLightTokens.INSTANCE.m2439getOnTertiaryContainer0d7_KjU() : j22;
        long jM2419getBackground0d7_KjU = (i10 & 8192) != 0 ? ColorLightTokens.INSTANCE.m2419getBackground0d7_KjU() : j23;
        long jM2425getOnBackground0d7_KjU = (i10 & 16384) != 0 ? ColorLightTokens.INSTANCE.m2425getOnBackground0d7_KjU() : j24;
        long jM2453getSurface0d7_KjU = (i10 & 32768) != 0 ? ColorLightTokens.INSTANCE.m2453getSurface0d7_KjU() : j25;
        long jM2436getOnSurface0d7_KjU = (i10 & 65536) != 0 ? ColorLightTokens.INSTANCE.m2436getOnSurface0d7_KjU() : j26;
        long jM2462getSurfaceVariant0d7_KjU = (i10 & 131072) != 0 ? ColorLightTokens.INSTANCE.m2462getSurfaceVariant0d7_KjU() : j27;
        long jM2437getOnSurfaceVariant0d7_KjU = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? ColorLightTokens.INSTANCE.m2437getOnSurfaceVariant0d7_KjU() : j28;
        long j47 = (i10 & 524288) != 0 ? j46 : j29;
        long jM2424getInverseSurface0d7_KjU = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? ColorLightTokens.INSTANCE.m2424getInverseSurface0d7_KjU() : j30;
        long jM2422getInverseOnSurface0d7_KjU = (i10 & 2097152) != 0 ? ColorLightTokens.INSTANCE.m2422getInverseOnSurface0d7_KjU() : j31;
        long jM2420getError0d7_KjU = (i10 & 4194304) != 0 ? ColorLightTokens.INSTANCE.m2420getError0d7_KjU() : j32;
        long jM2426getOnError0d7_KjU = (i10 & 8388608) != 0 ? ColorLightTokens.INSTANCE.m2426getOnError0d7_KjU() : j33;
        long jM2421getErrorContainer0d7_KjU = (i10 & 16777216) != 0 ? ColorLightTokens.INSTANCE.m2421getErrorContainer0d7_KjU() : j34;
        long jM2427getOnErrorContainer0d7_KjU = (i10 & 33554432) != 0 ? ColorLightTokens.INSTANCE.m2427getOnErrorContainer0d7_KjU() : j35;
        long jM2442getOutline0d7_KjU = (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? ColorLightTokens.INSTANCE.m2442getOutline0d7_KjU() : j36;
        long jM2443getOutlineVariant0d7_KjU = (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorLightTokens.INSTANCE.m2443getOutlineVariant0d7_KjU() : j37;
        long jM2448getScrim0d7_KjU = (i10 & 268435456) != 0 ? ColorLightTokens.INSTANCE.m2448getScrim0d7_KjU() : j38;
        long jM2454getSurfaceBright0d7_KjU = (i10 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? ColorLightTokens.INSTANCE.m2454getSurfaceBright0d7_KjU() : j39;
        long jM2455getSurfaceContainer0d7_KjU = (i10 & 1073741824) != 0 ? ColorLightTokens.INSTANCE.m2455getSurfaceContainer0d7_KjU() : j40;
        long jM2456getSurfaceContainerHigh0d7_KjU = (i10 & Integer.MIN_VALUE) != 0 ? ColorLightTokens.INSTANCE.m2456getSurfaceContainerHigh0d7_KjU() : j41;
        long jM2457getSurfaceContainerHighest0d7_KjU = (i11 & 1) != 0 ? ColorLightTokens.INSTANCE.m2457getSurfaceContainerHighest0d7_KjU() : j42;
        long jM2458getSurfaceContainerLow0d7_KjU = (i11 & 2) != 0 ? ColorLightTokens.INSTANCE.m2458getSurfaceContainerLow0d7_KjU() : j43;
        long jM2459getSurfaceContainerLowest0d7_KjU = (i11 & 4) != 0 ? ColorLightTokens.INSTANCE.m2459getSurfaceContainerLowest0d7_KjU() : j44;
        if ((i11 & 8) != 0) {
            j45 = ColorLightTokens.INSTANCE.m2460getSurfaceDim0d7_KjU();
        }
        return m1399lightColorSchemeCXl9yA(j46, jM2428getOnPrimary0d7_KjU, jM2445getPrimaryContainer0d7_KjU, jM2429getOnPrimaryContainer0d7_KjU, jM2423getInversePrimary0d7_KjU, jM2449getSecondary0d7_KjU, jM2432getOnSecondary0d7_KjU, jM2450getSecondaryContainer0d7_KjU, jM2433getOnSecondaryContainer0d7_KjU, jM2463getTertiary0d7_KjU, jM2438getOnTertiary0d7_KjU, jM2464getTertiaryContainer0d7_KjU, jM2439getOnTertiaryContainer0d7_KjU, jM2419getBackground0d7_KjU, jM2425getOnBackground0d7_KjU, jM2453getSurface0d7_KjU, jM2436getOnSurface0d7_KjU, jM2462getSurfaceVariant0d7_KjU, jM2437getOnSurfaceVariant0d7_KjU, j47, jM2424getInverseSurface0d7_KjU, jM2422getInverseOnSurface0d7_KjU, jM2420getError0d7_KjU, jM2426getOnError0d7_KjU, jM2421getErrorContainer0d7_KjU, jM2427getOnErrorContainer0d7_KjU, jM2442getOutline0d7_KjU, jM2443getOutlineVariant0d7_KjU, jM2448getScrim0d7_KjU, jM2454getSurfaceBright0d7_KjU, jM2455getSurfaceContainer0d7_KjU, jM2456getSurfaceContainerHigh0d7_KjU, jM2457getSurfaceContainerHighest0d7_KjU, jM2458getSurfaceContainerLow0d7_KjU, jM2459getSurfaceContainerLowest0d7_KjU, j45);
    }

    @x6.e
    /* JADX INFO: renamed from: lightColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1401lightColorSchemeG1PFcw(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38) {
        return m1400lightColorSchemeCXl9yA$default(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* JADX INFO: renamed from: lightColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1402lightColorSchemeG1PFcw$default(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, int i10, Object obj) {
        long jM2444getPrimary0d7_KjU = (i10 & 1) != 0 ? ColorLightTokens.INSTANCE.m2444getPrimary0d7_KjU() : j10;
        long jM2428getOnPrimary0d7_KjU = (i10 & 2) != 0 ? ColorLightTokens.INSTANCE.m2428getOnPrimary0d7_KjU() : j11;
        long jM2445getPrimaryContainer0d7_KjU = (i10 & 4) != 0 ? ColorLightTokens.INSTANCE.m2445getPrimaryContainer0d7_KjU() : j12;
        long jM2429getOnPrimaryContainer0d7_KjU = (i10 & 8) != 0 ? ColorLightTokens.INSTANCE.m2429getOnPrimaryContainer0d7_KjU() : j13;
        long jM2423getInversePrimary0d7_KjU = (i10 & 16) != 0 ? ColorLightTokens.INSTANCE.m2423getInversePrimary0d7_KjU() : j14;
        long jM2449getSecondary0d7_KjU = (i10 & 32) != 0 ? ColorLightTokens.INSTANCE.m2449getSecondary0d7_KjU() : j15;
        long jM2432getOnSecondary0d7_KjU = (i10 & 64) != 0 ? ColorLightTokens.INSTANCE.m2432getOnSecondary0d7_KjU() : j16;
        long jM2450getSecondaryContainer0d7_KjU = (i10 & 128) != 0 ? ColorLightTokens.INSTANCE.m2450getSecondaryContainer0d7_KjU() : j17;
        long j39 = jM2444getPrimary0d7_KjU;
        long jM2433getOnSecondaryContainer0d7_KjU = (i10 & 256) != 0 ? ColorLightTokens.INSTANCE.m2433getOnSecondaryContainer0d7_KjU() : j18;
        long jM2463getTertiary0d7_KjU = (i10 & 512) != 0 ? ColorLightTokens.INSTANCE.m2463getTertiary0d7_KjU() : j19;
        long jM2438getOnTertiary0d7_KjU = (i10 & 1024) != 0 ? ColorLightTokens.INSTANCE.m2438getOnTertiary0d7_KjU() : j20;
        long jM2464getTertiaryContainer0d7_KjU = (i10 & 2048) != 0 ? ColorLightTokens.INSTANCE.m2464getTertiaryContainer0d7_KjU() : j21;
        long jM2439getOnTertiaryContainer0d7_KjU = (i10 & 4096) != 0 ? ColorLightTokens.INSTANCE.m2439getOnTertiaryContainer0d7_KjU() : j22;
        long jM2419getBackground0d7_KjU = (i10 & 8192) != 0 ? ColorLightTokens.INSTANCE.m2419getBackground0d7_KjU() : j23;
        long jM2425getOnBackground0d7_KjU = (i10 & 16384) != 0 ? ColorLightTokens.INSTANCE.m2425getOnBackground0d7_KjU() : j24;
        long jM2453getSurface0d7_KjU = (i10 & 32768) != 0 ? ColorLightTokens.INSTANCE.m2453getSurface0d7_KjU() : j25;
        long jM2436getOnSurface0d7_KjU = (i10 & 65536) != 0 ? ColorLightTokens.INSTANCE.m2436getOnSurface0d7_KjU() : j26;
        long jM2462getSurfaceVariant0d7_KjU = (i10 & 131072) != 0 ? ColorLightTokens.INSTANCE.m2462getSurfaceVariant0d7_KjU() : j27;
        long jM2437getOnSurfaceVariant0d7_KjU = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? ColorLightTokens.INSTANCE.m2437getOnSurfaceVariant0d7_KjU() : j28;
        long j40 = (i10 & 524288) != 0 ? j39 : j29;
        long jM2424getInverseSurface0d7_KjU = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? ColorLightTokens.INSTANCE.m2424getInverseSurface0d7_KjU() : j30;
        long jM2422getInverseOnSurface0d7_KjU = (i10 & 2097152) != 0 ? ColorLightTokens.INSTANCE.m2422getInverseOnSurface0d7_KjU() : j31;
        long jM2420getError0d7_KjU = (i10 & 4194304) != 0 ? ColorLightTokens.INSTANCE.m2420getError0d7_KjU() : j32;
        long jM2426getOnError0d7_KjU = (i10 & 8388608) != 0 ? ColorLightTokens.INSTANCE.m2426getOnError0d7_KjU() : j33;
        long jM2421getErrorContainer0d7_KjU = (i10 & 16777216) != 0 ? ColorLightTokens.INSTANCE.m2421getErrorContainer0d7_KjU() : j34;
        long jM2427getOnErrorContainer0d7_KjU = (i10 & 33554432) != 0 ? ColorLightTokens.INSTANCE.m2427getOnErrorContainer0d7_KjU() : j35;
        long jM2442getOutline0d7_KjU = (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? ColorLightTokens.INSTANCE.m2442getOutline0d7_KjU() : j36;
        long jM2443getOutlineVariant0d7_KjU = (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorLightTokens.INSTANCE.m2443getOutlineVariant0d7_KjU() : j37;
        if ((i10 & 268435456) != 0) {
            j38 = ColorLightTokens.INSTANCE.m2448getScrim0d7_KjU();
        }
        return m1401lightColorSchemeG1PFcw(j39, jM2428getOnPrimary0d7_KjU, jM2445getPrimaryContainer0d7_KjU, jM2429getOnPrimaryContainer0d7_KjU, jM2423getInversePrimary0d7_KjU, jM2449getSecondary0d7_KjU, jM2432getOnSecondary0d7_KjU, jM2450getSecondaryContainer0d7_KjU, jM2433getOnSecondaryContainer0d7_KjU, jM2463getTertiary0d7_KjU, jM2438getOnTertiary0d7_KjU, jM2464getTertiaryContainer0d7_KjU, jM2439getOnTertiaryContainer0d7_KjU, jM2419getBackground0d7_KjU, jM2425getOnBackground0d7_KjU, jM2453getSurface0d7_KjU, jM2436getOnSurface0d7_KjU, jM2462getSurfaceVariant0d7_KjU, jM2437getOnSurfaceVariant0d7_KjU, j40, jM2424getInverseSurface0d7_KjU, jM2422getInverseOnSurface0d7_KjU, jM2420getError0d7_KjU, jM2426getOnError0d7_KjU, jM2421getErrorContainer0d7_KjU, jM2427getOnErrorContainer0d7_KjU, jM2442getOutline0d7_KjU, jM2443getOutlineVariant0d7_KjU, j38);
    }

    /* JADX INFO: renamed from: surfaceColorAtElevation-3ABfNKs, reason: not valid java name */
    public static final long m1403surfaceColorAtElevation3ABfNKs(ColorScheme colorScheme, float f10) {
        if (Dp.m5683equalsimpl0(f10, Dp.m5678constructorimpl(0))) {
            return colorScheme.getSurface();
        }
        return ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(colorScheme.getSurfaceTint(), ((((float) Math.log(f10 + 1)) * 4.5f) + 2.0f) / 100.0f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface());
    }
}
