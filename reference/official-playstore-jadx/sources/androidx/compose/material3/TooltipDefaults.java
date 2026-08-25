package androidx.compose.material3;

import androidx.compose.material3.tokens.PlainTooltipTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001c\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ\r\u0010 \u001a\u00020\u0014H\u0007¢\u0006\u0002\u0010!J:\u0010 \u001a\u00020\u00142\b\b\u0002\u0010\"\u001a\u00020\b2\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\b2\b\b\u0002\u0010%\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000eR\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/material3/TooltipDefaults;", "", "()V", "caretProperties", "Landroidx/compose/material3/CaretProperties;", "getCaretProperties", "()Landroidx/compose/material3/CaretProperties;", "plainTooltipContainerColor", "Landroidx/compose/ui/graphics/Color;", "getPlainTooltipContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "plainTooltipContainerShape", "Landroidx/compose/ui/graphics/Shape;", "getPlainTooltipContainerShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "plainTooltipContentColor", "getPlainTooltipContentColor", "richTooltipContainerShape", "getRichTooltipContainerShape", "defaultRichTooltipColors", "Landroidx/compose/material3/RichTooltipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultRichTooltipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/RichTooltipColors;", "rememberPlainTooltipPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "spacingBetweenTooltipAndAnchor", "Landroidx/compose/ui/unit/Dp;", "rememberPlainTooltipPositionProvider-kHDZbjc", "(FLandroidx/compose/runtime/Composer;II)Landroidx/compose/ui/window/PopupPositionProvider;", "rememberRichTooltipPositionProvider", "rememberRichTooltipPositionProvider-kHDZbjc", "richTooltipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/RichTooltipColors;", "containerColor", "contentColor", "titleContentColor", "actionContentColor", "richTooltipColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/RichTooltipColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TooltipDefaults {
    public static final int $stable = 0;
    public static final TooltipDefaults INSTANCE = new TooltipDefaults();
    private static final CaretProperties caretProperties = new CaretProperties(Dp.m5678constructorimpl(8), Dp.m5678constructorimpl(16), null);

    private TooltipDefaults() {
    }

    public final CaretProperties getCaretProperties() {
        return caretProperties;
    }

    public final RichTooltipColors getDefaultRichTooltipColors$material3_release(ColorScheme colorScheme) {
        RichTooltipColors defaultRichTooltipColorsCached = colorScheme.getDefaultRichTooltipColorsCached();
        if (defaultRichTooltipColorsCached != null) {
            return defaultRichTooltipColorsCached;
        }
        RichTooltipTokens richTooltipTokens = RichTooltipTokens.INSTANCE;
        RichTooltipColors richTooltipColors = new RichTooltipColors(ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getSupportingTextColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getSubheadColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getActionLabelTextColor()), null);
        colorScheme.setDefaultRichTooltipColorsCached$material3_release(richTooltipColors);
        return richTooltipColors;
    }

    public final long getPlainTooltipContainerColor(Composer composer, int i10) {
        composer.startReplaceableGroup(102696215);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(102696215, i10, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContainerColor> (Tooltip.kt:310)");
        }
        long value = ColorSchemeKt.getValue(PlainTooltipTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final Shape getPlainTooltipContainerShape(Composer composer, int i10) {
        composer.startReplaceableGroup(49570325);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(49570325, i10, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContainerShape> (Tooltip.kt:304)");
        }
        Shape value = ShapesKt.getValue(PlainTooltipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final long getPlainTooltipContentColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-1982928937);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1982928937, i10, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContentColor> (Tooltip.kt:316)");
        }
        long value = ColorSchemeKt.getValue(PlainTooltipTokens.INSTANCE.getSupportingTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final Shape getRichTooltipContainerShape(Composer composer, int i10) {
        composer.startReplaceableGroup(1138709783);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1138709783, i10, -1, "androidx.compose.material3.TooltipDefaults.<get-richTooltipContainerShape> (Tooltip.kt:322)");
        }
        Shape value = ShapesKt.getValue(RichTooltipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: rememberPlainTooltipPositionProvider-kHDZbjc, reason: not valid java name */
    public final PopupPositionProvider m2296rememberPlainTooltipPositionProviderkHDZbjc(float f10, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1047866909);
        if ((i11 & 1) != 0) {
            f10 = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1047866909, i10, -1, "androidx.compose.material3.TooltipDefaults.rememberPlainTooltipPositionProvider (Tooltip.kt:375)");
        }
        final int iMo279roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo279roundToPx0680j_4(f10);
        composer.startReplaceableGroup(-2013870024);
        boolean zChanged = composer.changed(iMo279roundToPx0680j_4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PopupPositionProvider() { // from class: androidx.compose.material3.TooltipDefaults$rememberPlainTooltipPositionProvider$1$1
                @Override // androidx.compose.ui.window.PopupPositionProvider
                /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                public long mo959calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                    int width = ((anchorBounds.getWidth() - IntSize.m5844getWidthimpl(popupContentSize)) / 2) + anchorBounds.getLeft();
                    int top = (anchorBounds.getTop() - IntSize.m5843getHeightimpl(popupContentSize)) - iMo279roundToPx0680j_4;
                    if (top < 0) {
                        top = iMo279roundToPx0680j_4 + anchorBounds.getBottom();
                    }
                    return IntOffsetKt.IntOffset(width, top);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipDefaults$rememberPlainTooltipPositionProvider$1$1 tooltipDefaults$rememberPlainTooltipPositionProvider$1$1 = (TooltipDefaults$rememberPlainTooltipPositionProvider$1$1) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return tooltipDefaults$rememberPlainTooltipPositionProvider$1$1;
    }

    /* JADX INFO: renamed from: rememberRichTooltipPositionProvider-kHDZbjc, reason: not valid java name */
    public final PopupPositionProvider m2297rememberRichTooltipPositionProviderkHDZbjc(float f10, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1538806795);
        if ((i11 & 1) != 0) {
            f10 = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1538806795, i10, -1, "androidx.compose.material3.TooltipDefaults.rememberRichTooltipPositionProvider (Tooltip.kt:410)");
        }
        final int iMo279roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo279roundToPx0680j_4(f10);
        composer.startReplaceableGroup(1605213924);
        boolean zChanged = composer.changed(iMo279roundToPx0680j_4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PopupPositionProvider() { // from class: androidx.compose.material3.TooltipDefaults$rememberRichTooltipPositionProvider$1$1
                @Override // androidx.compose.ui.window.PopupPositionProvider
                /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                public long mo959calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                    int right = anchorBounds.getRight();
                    if (IntSize.m5844getWidthimpl(popupContentSize) + right > IntSize.m5844getWidthimpl(windowSize) && (right = anchorBounds.getLeft() - IntSize.m5844getWidthimpl(popupContentSize)) < 0) {
                        right = ((anchorBounds.getWidth() - IntSize.m5844getWidthimpl(popupContentSize)) / 2) + anchorBounds.getLeft();
                    }
                    int top = (anchorBounds.getTop() - IntSize.m5843getHeightimpl(popupContentSize)) - iMo279roundToPx0680j_4;
                    if (top < 0) {
                        top = iMo279roundToPx0680j_4 + anchorBounds.getBottom();
                    }
                    return IntOffsetKt.IntOffset(right, top);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipDefaults$rememberRichTooltipPositionProvider$1$1 tooltipDefaults$rememberRichTooltipPositionProvider$1$1 = (TooltipDefaults$rememberRichTooltipPositionProvider$1$1) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return tooltipDefaults$rememberRichTooltipPositionProvider$1$1;
    }

    public final RichTooltipColors richTooltipColors(Composer composer, int i10) {
        composer.startReplaceableGroup(-1622312141);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1622312141, i10, -1, "androidx.compose.material3.TooltipDefaults.richTooltipColors (Tooltip.kt:335)");
        }
        RichTooltipColors defaultRichTooltipColors$material3_release = getDefaultRichTooltipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultRichTooltipColors$material3_release;
    }

    /* JADX INFO: renamed from: richTooltipColors-ro_MJ88, reason: not valid java name */
    public final RichTooltipColors m2298richTooltipColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1498555081);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1498555081, i10, -1, "androidx.compose.material3.TooltipDefaults.richTooltipColors (Tooltip.kt:347)");
        }
        RichTooltipColors richTooltipColorsM1792copyjRlVdoo = getDefaultRichTooltipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1792copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return richTooltipColorsM1792copyjRlVdoo;
    }
}
