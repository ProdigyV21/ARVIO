package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.ElevatedButtonTokens;
import androidx.compose.material3.tokens.FilledButtonTokens;
import androidx.compose.material3.tokens.FilledTonalButtonTokens;
import androidx.compose.material3.tokens.OutlinedButtonTokens;
import androidx.compose.material3.tokens.TextButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010:\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010:\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bA\u0010BJD\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\r\u0010L\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010L\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010BJD\u0010N\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bO\u0010KJ\r\u0010P\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010P\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bQ\u0010BJD\u0010R\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bS\u0010KJ\r\u0010T\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010T\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bU\u0010BJ\r\u0010V\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010V\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bW\u0010BR\u0016\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\u0013\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0014\u0010\u0010R\u0019\u0010\u0015\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u0016\u0010\u0019\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\nR\u0016\u0010\u001c\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001d\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0011\u0010#\u001a\u00020$8G¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b(\u0010 R\u0011\u0010)\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b*\u0010 R\u0011\u0010+\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b,\u0010 R\u0018\u0010-\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0018\u00102\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u00101R\u0018\u00104\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00101R\u0018\u00106\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u00101R\u0018\u00108\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006X"}, d2 = {"Landroidx/compose/material3/ButtonDefaults;", "", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ButtonWithIconContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getButtonWithIconContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "ButtonWithIconHorizontalStartPadding", "ContentPadding", "getContentPadding", "IconSize", "getIconSize-D9Ej5fM", "()F", "IconSpacing", "getIconSpacing-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "TextButtonContentPadding", "getTextButtonContentPadding", "TextButtonHorizontalPadding", "TextButtonWithIconContentPadding", "getTextButtonWithIconContentPadding", "TextButtonWithIconHorizontalEndPadding", "elevatedShape", "Landroidx/compose/ui/graphics/Shape;", "getElevatedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "filledTonalShape", "getFilledTonalShape", "outlinedButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedButtonBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedShape", "getOutlinedShape", "shape", "getShape", "textShape", "getTextShape", "defaultButtonColors", "Landroidx/compose/material3/ButtonColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultButtonColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ButtonColors;", "defaultElevatedButtonColors", "getDefaultElevatedButtonColors$material3_release", "defaultFilledTonalButtonColors", "getDefaultFilledTonalButtonColors$material3_release", "defaultOutlinedButtonColors", "getDefaultOutlinedButtonColors$material3_release", "defaultTextButtonColors", "getDefaultTextButtonColors$material3_release", "buttonColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ButtonColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ButtonColors;", "buttonElevation", "Landroidx/compose/material3/ButtonElevation;", "defaultElevation", "pressedElevation", "focusedElevation", "hoveredElevation", "disabledElevation", "buttonElevation-R_JCAzs", "(FFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ButtonElevation;", "elevatedButtonColors", "elevatedButtonColors-ro_MJ88", "elevatedButtonElevation", "elevatedButtonElevation-R_JCAzs", "filledTonalButtonColors", "filledTonalButtonColors-ro_MJ88", "filledTonalButtonElevation", "filledTonalButtonElevation-R_JCAzs", "outlinedButtonColors", "outlinedButtonColors-ro_MJ88", "textButtonColors", "textButtonColors-ro_MJ88", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ButtonDefaults {
    public static final int $stable = 0;
    private static final float ButtonHorizontalPadding;
    private static final float ButtonVerticalPadding;
    private static final PaddingValues ButtonWithIconContentPadding;
    private static final float ButtonWithIconHorizontalStartPadding;
    private static final PaddingValues ContentPadding;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float IconSize;
    private static final float IconSpacing;
    private static final float MinHeight;
    private static final float MinWidth;
    private static final PaddingValues TextButtonContentPadding;
    private static final float TextButtonHorizontalPadding;
    private static final PaddingValues TextButtonWithIconContentPadding;
    private static final float TextButtonWithIconHorizontalEndPadding;

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(24);
        ButtonHorizontalPadding = fM5678constructorimpl;
        float f10 = 8;
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(f10);
        ButtonVerticalPadding = fM5678constructorimpl2;
        PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl, fM5678constructorimpl2, fM5678constructorimpl, fM5678constructorimpl2);
        ContentPadding = paddingValuesM525PaddingValuesa9UjIt4;
        float f11 = 16;
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(f11);
        ButtonWithIconHorizontalStartPadding = fM5678constructorimpl3;
        ButtonWithIconContentPadding = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl3, fM5678constructorimpl2, fM5678constructorimpl, fM5678constructorimpl2);
        float fM5678constructorimpl4 = Dp.m5678constructorimpl(12);
        TextButtonHorizontalPadding = fM5678constructorimpl4;
        TextButtonContentPadding = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl4, paddingValuesM525PaddingValuesa9UjIt4.getTop(), fM5678constructorimpl4, paddingValuesM525PaddingValuesa9UjIt4.getBottom());
        float fM5678constructorimpl5 = Dp.m5678constructorimpl(f11);
        TextButtonWithIconHorizontalEndPadding = fM5678constructorimpl5;
        TextButtonWithIconContentPadding = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl4, paddingValuesM525PaddingValuesa9UjIt4.getTop(), fM5678constructorimpl5, paddingValuesM525PaddingValuesa9UjIt4.getBottom());
        MinWidth = Dp.m5678constructorimpl(58);
        MinHeight = Dp.m5678constructorimpl(40);
        IconSize = FilledButtonTokens.INSTANCE.m2581getIconSizeD9Ej5fM();
        IconSpacing = Dp.m5678constructorimpl(f10);
    }

    private ButtonDefaults() {
    }

    public final ButtonColors buttonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(1449248637);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1449248637, i10, -1, "androidx.compose.material3.ButtonDefaults.buttonColors (Button.kt:546)");
        }
        ButtonColors defaultButtonColors$material3_release = getDefaultButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1266buttonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-339300779);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-339300779, i10, -1, "androidx.compose.material3.ButtonDefaults.buttonColors (Button.kt:563)");
        }
        ButtonColors buttonColorsM1261copyjRlVdoo = getDefaultButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1261copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonColorsM1261copyjRlVdoo;
    }

    /* JADX INFO: renamed from: buttonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1267buttonElevationR_JCAzs(float f10, float f11, float f12, float f13, float f14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1827791191);
        if ((i11 & 1) != 0) {
            f10 = FilledButtonTokens.INSTANCE.m2576getContainerElevationD9Ej5fM();
        }
        float f15 = f10;
        if ((i11 & 2) != 0) {
            f11 = FilledButtonTokens.INSTANCE.m2582getPressedContainerElevationD9Ej5fM();
        }
        float f16 = f11;
        if ((i11 & 4) != 0) {
            f12 = FilledButtonTokens.INSTANCE.m2579getFocusContainerElevationD9Ej5fM();
        }
        float f17 = f12;
        if ((i11 & 8) != 0) {
            f13 = FilledButtonTokens.INSTANCE.m2580getHoverContainerElevationD9Ej5fM();
        }
        float f18 = f13;
        if ((i11 & 16) != 0) {
            f14 = FilledButtonTokens.INSTANCE.m2578getDisabledContainerElevationD9Ej5fM();
        }
        float f19 = f14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1827791191, i10, -1, "androidx.compose.material3.ButtonDefaults.buttonElevation (Button.kt:772)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(f15, f16, f17, f18, f19, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonElevation;
    }

    public final ButtonColors elevatedButtonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(2025043443);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2025043443, i10, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonColors (Button.kt:589)");
        }
        ButtonColors defaultElevatedButtonColors$material3_release = getDefaultElevatedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultElevatedButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: elevatedButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1268elevatedButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1507908383);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1507908383, i10, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonColors (Button.kt:606)");
        }
        ButtonColors buttonColorsM1261copyjRlVdoo = getDefaultElevatedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1261copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonColorsM1261copyjRlVdoo;
    }

    /* JADX INFO: renamed from: elevatedButtonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1269elevatedButtonElevationR_JCAzs(float f10, float f11, float f12, float f13, float f14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1065482445);
        if ((i11 & 1) != 0) {
            f10 = ElevatedButtonTokens.INSTANCE.m2492getContainerElevationD9Ej5fM();
        }
        float f15 = f10;
        if ((i11 & 2) != 0) {
            f11 = ElevatedButtonTokens.INSTANCE.m2498getPressedContainerElevationD9Ej5fM();
        }
        float f16 = f11;
        if ((i11 & 4) != 0) {
            f12 = ElevatedButtonTokens.INSTANCE.m2495getFocusContainerElevationD9Ej5fM();
        }
        float f17 = f12;
        if ((i11 & 8) != 0) {
            f13 = ElevatedButtonTokens.INSTANCE.m2496getHoverContainerElevationD9Ej5fM();
        }
        float f18 = f13;
        if ((i11 & 16) != 0) {
            f14 = ElevatedButtonTokens.INSTANCE.m2494getDisabledContainerElevationD9Ej5fM();
        }
        float f19 = f14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1065482445, i10, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonElevation (Button.kt:798)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(f15, f16, f17, f18, f19, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonElevation;
    }

    public final ButtonColors filledTonalButtonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(824987837);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(824987837, i10, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonColors (Button.kt:632)");
        }
        ButtonColors defaultFilledTonalButtonColors$material3_release = getDefaultFilledTonalButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultFilledTonalButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: filledTonalButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1270filledTonalButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1670757653);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1670757653, i10, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonColors (Button.kt:649)");
        }
        ButtonColors buttonColorsM1261copyjRlVdoo = getDefaultFilledTonalButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1261copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonColorsM1261copyjRlVdoo;
    }

    /* JADX INFO: renamed from: filledTonalButtonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1271filledTonalButtonElevationR_JCAzs(float f10, float f11, float f12, float f13, float f14, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(5982871);
        if ((i11 & 1) != 0) {
            f10 = FilledTonalButtonTokens.INSTANCE.m2599getContainerElevationD9Ej5fM();
        }
        float f15 = f10;
        if ((i11 & 2) != 0) {
            f11 = FilledTonalButtonTokens.INSTANCE.m2605getPressedContainerElevationD9Ej5fM();
        }
        float f16 = f11;
        if ((i11 & 4) != 0) {
            f12 = FilledTonalButtonTokens.INSTANCE.m2602getFocusContainerElevationD9Ej5fM();
        }
        float f17 = f12;
        if ((i11 & 8) != 0) {
            f13 = FilledTonalButtonTokens.INSTANCE.m2603getHoverContainerElevationD9Ej5fM();
        }
        float f18 = f13;
        if ((i11 & 16) != 0) {
            f14 = Dp.m5678constructorimpl(0);
        }
        float f19 = f14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(5982871, i10, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonElevation (Button.kt:825)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(f15, f16, f17, f18, f19, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonElevation;
    }

    public final PaddingValues getButtonWithIconContentPadding() {
        return ButtonWithIconContentPadding;
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    public final ButtonColors getDefaultButtonColors$material3_release(ColorScheme colorScheme) {
        ButtonColors defaultButtonColorsCached = colorScheme.getDefaultButtonColorsCached();
        if (defaultButtonColorsCached != null) {
            return defaultButtonColorsCached;
        }
        FilledButtonTokens filledButtonTokens = FilledButtonTokens.INSTANCE;
        ButtonColors buttonColors = new ButtonColors(ColorSchemeKt.fromToken(colorScheme, filledButtonTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledButtonTokens.getLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledButtonTokens.getDisabledContainerColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledButtonTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultButtonColorsCached$material3_release(buttonColors);
        return buttonColors;
    }

    public final ButtonColors getDefaultElevatedButtonColors$material3_release(ColorScheme colorScheme) {
        ButtonColors defaultElevatedButtonColorsCached = colorScheme.getDefaultElevatedButtonColorsCached();
        if (defaultElevatedButtonColorsCached != null) {
            return defaultElevatedButtonColorsCached;
        }
        ElevatedButtonTokens elevatedButtonTokens = ElevatedButtonTokens.INSTANCE;
        ButtonColors buttonColors = new ButtonColors(ColorSchemeKt.fromToken(colorScheme, elevatedButtonTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, elevatedButtonTokens.getLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, elevatedButtonTokens.getDisabledContainerColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, elevatedButtonTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultElevatedButtonColorsCached$material3_release(buttonColors);
        return buttonColors;
    }

    public final ButtonColors getDefaultFilledTonalButtonColors$material3_release(ColorScheme colorScheme) {
        ButtonColors defaultFilledTonalButtonColorsCached = colorScheme.getDefaultFilledTonalButtonColorsCached();
        if (defaultFilledTonalButtonColorsCached != null) {
            return defaultFilledTonalButtonColorsCached;
        }
        FilledTonalButtonTokens filledTonalButtonTokens = FilledTonalButtonTokens.INSTANCE;
        ButtonColors buttonColors = new ButtonColors(ColorSchemeKt.fromToken(colorScheme, filledTonalButtonTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTonalButtonTokens.getLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalButtonTokens.getDisabledContainerColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalButtonTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultFilledTonalButtonColorsCached$material3_release(buttonColors);
        return buttonColors;
    }

    public final ButtonColors getDefaultOutlinedButtonColors$material3_release(ColorScheme colorScheme) {
        ButtonColors defaultOutlinedButtonColorsCached = colorScheme.getDefaultOutlinedButtonColorsCached();
        if (defaultOutlinedButtonColorsCached != null) {
            return defaultOutlinedButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        OutlinedButtonTokens outlinedButtonTokens = OutlinedButtonTokens.INSTANCE;
        ButtonColors buttonColors = new ButtonColors(jM3507getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, outlinedButtonTokens.getLabelTextColor()), companion.m3507getTransparent0d7_KjU(), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedButtonTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultOutlinedButtonColorsCached$material3_release(buttonColors);
        return buttonColors;
    }

    public final ButtonColors getDefaultTextButtonColors$material3_release(ColorScheme colorScheme) {
        ButtonColors defaultTextButtonColorsCached = colorScheme.getDefaultTextButtonColorsCached();
        if (defaultTextButtonColorsCached != null) {
            return defaultTextButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        TextButtonTokens textButtonTokens = TextButtonTokens.INSTANCE;
        ButtonColors buttonColors = new ButtonColors(jM3507getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, textButtonTokens.getLabelTextColor()), companion.m3507getTransparent0d7_KjU(), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, textButtonTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultTextButtonColorsCached$material3_release(buttonColors);
        return buttonColors;
    }

    public final Shape getElevatedShape(Composer composer, int i10) {
        composer.startReplaceableGroup(2143958791);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143958791, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-elevatedShape> (Button.kt:530)");
        }
        Shape value = ShapesKt.getValue(ElevatedButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final Shape getFilledTonalShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-886584987);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-886584987, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-filledTonalShape> (Button.kt:533)");
        }
        Shape value = ShapesKt.getValue(FilledTonalButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1272getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* JADX INFO: renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1273getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1274getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1275getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final BorderStroke getOutlinedButtonBorder(Composer composer, int i10) {
        composer.startReplaceableGroup(-563957672);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-563957672, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-outlinedButtonBorder> (Button.kt:836)");
        }
        OutlinedButtonTokens outlinedButtonTokens = OutlinedButtonTokens.INSTANCE;
        BorderStroke borderStrokeM207BorderStrokecXLIe8U = BorderStrokeKt.m207BorderStrokecXLIe8U(outlinedButtonTokens.m2684getOutlineWidthD9Ej5fM(), ColorSchemeKt.getValue(outlinedButtonTokens.getOutlineColor(), composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return borderStrokeM207BorderStrokecXLIe8U;
    }

    public final Shape getOutlinedShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-2045213065);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2045213065, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-outlinedShape> (Button.kt:536)");
        }
        Shape value = ShapesKt.getValue(OutlinedButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final Shape getShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-1234923021);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1234923021, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-shape> (Button.kt:527)");
        }
        Shape value = ShapesKt.getValue(FilledButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }

    public final PaddingValues getTextButtonWithIconContentPadding() {
        return TextButtonWithIconContentPadding;
    }

    public final Shape getTextShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-349121587);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-349121587, i10, -1, "androidx.compose.material3.ButtonDefaults.<get-textShape> (Button.kt:539)");
        }
        Shape value = ShapesKt.getValue(TextButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final ButtonColors outlinedButtonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(-1344886725);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1344886725, i10, -1, "androidx.compose.material3.ButtonDefaults.outlinedButtonColors (Button.kt:675)");
        }
        ButtonColors defaultOutlinedButtonColors$material3_release = getDefaultOutlinedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultOutlinedButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: outlinedButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1276outlinedButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1778526249);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1778526249, i10, -1, "androidx.compose.material3.ButtonDefaults.outlinedButtonColors (Button.kt:692)");
        }
        ButtonColors buttonColorsM1261copyjRlVdoo = getDefaultOutlinedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1261copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonColorsM1261copyjRlVdoo;
    }

    public final ButtonColors textButtonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(1880341584);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1880341584, i10, -1, "androidx.compose.material3.ButtonDefaults.textButtonColors (Button.kt:717)");
        }
        ButtonColors defaultTextButtonColors$material3_release = getDefaultTextButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultTextButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: textButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1277textButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1402274782);
        if ((i11 & 1) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        long j14 = j10;
        long jM3508getUnspecified0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1402274782, i10, -1, "androidx.compose.material3.ButtonDefaults.textButtonColors (Button.kt:734)");
        }
        ButtonColors buttonColorsM1261copyjRlVdoo = getDefaultTextButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1261copyjRlVdoo(j14, jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return buttonColorsM1261copyjRlVdoo;
    }
}
