package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilledIconButtonTokens;
import androidx.compose.material3.tokens.FilledTonalIconButtonTokens;
import androidx.compose.material3.tokens.IconButtonTokens;
import androidx.compose.material3.tokens.OutlinedIconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015JN\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ:\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0015JN\u0010\u001e\u001a\u00020\u00172\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001bJ\r\u0010 \u001a\u00020\nH\u0007¢\u0006\u0002\u0010!J:\u0010 \u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0015JN\u0010#\u001a\u00020\u00172\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010\u001bJ\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0007¢\u0006\u0002\u0010)J:\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010\u0015J\u001f\u0010,\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\u0006\u0010-\u001a\u00020(H\u0007¢\u0006\u0002\u0010.JN\u0010/\u001a\u00020\u00172\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010\u001bR\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults;", "", "()V", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape", "defaultIconButtonColors", "Landroidx/compose/material3/IconButtonColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultIconButtonColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconButtonColors;", "filledIconButtonColors", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "filledIconButtonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconButtonColors;", "filledIconToggleButtonColors", "Landroidx/compose/material3/IconToggleButtonColors;", "checkedContainerColor", "checkedContentColor", "filledIconToggleButtonColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconToggleButtonColors;", "filledTonalIconButtonColors", "filledTonalIconButtonColors-ro_MJ88", "filledTonalIconToggleButtonColors", "filledTonalIconToggleButtonColors-5tl4gsc", "iconButtonColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconButtonColors;", "iconButtonColors-ro_MJ88", "iconToggleButtonColors", "iconToggleButtonColors-5tl4gsc", "outlinedIconButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconButtonColors", "outlinedIconButtonColors-ro_MJ88", "outlinedIconToggleButtonBorder", "checked", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconToggleButtonColors", "outlinedIconToggleButtonColors-5tl4gsc", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IconButtonDefaults {
    public static final int $stable = 0;
    public static final IconButtonDefaults INSTANCE = new IconButtonDefaults();

    private IconButtonDefaults() {
    }

    /* JADX INFO: renamed from: filledIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1600filledIconButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-669858473);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getContainerColor(), composer, 6) : j10;
        long jM1394contentColorForek8zF_U = (i11 & 2) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(value, composer, i10 & 14) : j11;
        long jM3471copywmQWz5c$default = (i11 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getDisabledContainerColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long jM3471copywmQWz5c$default2 = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getDisabledColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-669858473, i10, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButton.kt:651)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(value, jM1394contentColorForek8zF_U, jM3471copywmQWz5c$default, jM3471copywmQWz5c$default2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: filledIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1601filledIconToggleButtonColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1887173701);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getUnselectedContainerColor(), composer, 6) : j10;
        long value2 = (i11 & 2) != 0 ? ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getToggleUnselectedColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i11 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getDisabledContainerColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long jM3471copywmQWz5c$default2 = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getDisabledColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long value3 = (i11 & 16) != 0 ? ColorSchemeKt.getValue(FilledIconButtonTokens.INSTANCE.getSelectedContainerColor(), composer, 6) : j14;
        long jM1394contentColorForek8zF_U = (i11 & 32) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(value3, composer, (i10 >> 12) & 14) : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1887173701, i10, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButton.kt:682)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(value, value2, jM3471copywmQWz5c$default, jM3471copywmQWz5c$default2, value3, jM1394contentColorForek8zF_U, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    /* JADX INFO: renamed from: filledTonalIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1602filledTonalIconButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-18532843);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getContainerColor(), composer, 6) : j10;
        long jM1394contentColorForek8zF_U = (i11 & 2) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(value, composer, i10 & 14) : j11;
        long jM3471copywmQWz5c$default = (i11 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long jM3471copywmQWz5c$default2 = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getDisabledColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-18532843, i10, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButton.kt:709)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(value, jM1394contentColorForek8zF_U, jM3471copywmQWz5c$default, jM3471copywmQWz5c$default2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: filledTonalIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1603filledTonalIconToggleButtonColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-19426557);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getUnselectedContainerColor(), composer, 6) : j10;
        long jM1394contentColorForek8zF_U = (i11 & 2) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(value, composer, i10 & 14) : j11;
        long jM3471copywmQWz5c$default = (i11 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long jM3471copywmQWz5c$default2 = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getDisabledColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long value2 = (i11 & 16) != 0 ? ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getSelectedContainerColor(), composer, 6) : j14;
        long value3 = (i11 & 32) != 0 ? ColorSchemeKt.getValue(FilledTonalIconButtonTokens.INSTANCE.getToggleSelectedColor(), composer, 6) : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-19426557, i10, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButton.kt:739)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(value, jM1394contentColorForek8zF_U, jM3471copywmQWz5c$default, jM3471copywmQWz5c$default2, value2, value3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    public final IconButtonColors getDefaultIconButtonColors(ColorScheme colorScheme, Composer composer, int i10) {
        composer.startReplaceableGroup(1437915677);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1437915677, i10, -1, "androidx.compose.material3.IconButtonDefaults.<get-defaultIconButtonColors> (IconButton.kt:589)");
        }
        IconButtonColors defaultIconButtonColorsCached = colorScheme.getDefaultIconButtonColorsCached();
        if (defaultIconButtonColorsCached == null) {
            long jM3482unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl();
            Color.Companion companion = Color.INSTANCE;
            IconButtonColors iconButtonColors = new IconButtonColors(companion.m3507getTransparent0d7_KjU(), jM3482unboximpl, companion.m3507getTransparent0d7_KjU(), Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
            colorScheme.setDefaultIconButtonColorsCached$material3_release(iconButtonColors);
            defaultIconButtonColorsCached = iconButtonColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultIconButtonColorsCached;
    }

    public final Shape getFilledShape(Composer composer, int i10) {
        composer.startReplaceableGroup(1265841879);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1265841879, i10, -1, "androidx.compose.material3.IconButtonDefaults.<get-filledShape> (IconButton.kt:540)");
        }
        Shape value = ShapesKt.getValue(FilledIconButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final Shape getOutlinedShape(Composer composer, int i10) {
        composer.startReplaceableGroup(1327125527);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1327125527, i10, -1, "androidx.compose.material3.IconButtonDefaults.<get-outlinedShape> (IconButton.kt:545)");
        }
        Shape value = ShapesKt.getValue(OutlinedIconButtonTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    public final IconButtonColors iconButtonColors(Composer composer, int i10) {
        composer.startReplaceableGroup(-1519621781);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1519621781, i10, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:551)");
        }
        IconButtonColors defaultIconButtonColors = getDefaultIconButtonColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i10 << 3) & 112);
        long jM3482unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl();
        if (Color.m3473equalsimpl0(defaultIconButtonColors.getContentColor(), jM3482unboximpl)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return defaultIconButtonColors;
        }
        IconButtonColors iconButtonColorsM1592copyjRlVdoo$default = IconButtonColors.m1592copyjRlVdoo$default(defaultIconButtonColors, 0L, jM3482unboximpl, 0L, Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null), 5, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconButtonColorsM1592copyjRlVdoo$default;
    }

    /* JADX INFO: renamed from: iconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1604iconButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(999008085);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3482unboximpl = (i11 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl() : j11;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3471copywmQWz5c$default = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(999008085, i10, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:580)");
        }
        IconButtonColors iconButtonColorsM1595copyjRlVdoo = getDefaultIconButtonColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i10 >> 9) & 112).m1595copyjRlVdoo(jM3508getUnspecified0d7_KjU, jM3482unboximpl, jM3508getUnspecified0d7_KjU2, jM3471copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconButtonColorsM1595copyjRlVdoo;
    }

    /* JADX INFO: renamed from: iconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1605iconToggleButtonColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-2020719549);
        long jM3507getTransparent0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        long jM3482unboximpl = (i11 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl() : j11;
        long jM3507getTransparent0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long jM3471copywmQWz5c$default = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long jM3507getTransparent0d7_KjU3 = (i11 & 16) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j14;
        long value = (i11 & 32) != 0 ? ColorSchemeKt.getValue(IconButtonTokens.INSTANCE.getSelectedIconColor(), composer, 6) : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2020719549, i10, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButton.kt:625)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3507getTransparent0d7_KjU, jM3482unboximpl, jM3507getTransparent0d7_KjU2, jM3471copywmQWz5c$default, jM3507getTransparent0d7_KjU3, value, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    public final BorderStroke outlinedIconButtonBorder(boolean z, Composer composer, int i10) {
        long jM3471copywmQWz5c$default;
        composer.startReplaceableGroup(-511461558);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511461558, i10, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonBorder (IconButton.kt:824)");
        }
        if (z) {
            composer.startReplaceableGroup(1252616568);
            jM3471copywmQWz5c$default = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl();
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(1252616623);
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null);
            composer.endReplaceableGroup();
        }
        composer.startReplaceableGroup(1252616777);
        boolean zChanged = composer.changed(jM3471copywmQWz5c$default);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = BorderStrokeKt.m207BorderStrokecXLIe8U(OutlinedIconButtonTokens.INSTANCE.m2695getUnselectedOutlineWidthD9Ej5fM(), jM3471copywmQWz5c$default);
            composer.updateRememberedValue(objRememberedValue);
        }
        BorderStroke borderStroke = (BorderStroke) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return borderStroke;
    }

    /* JADX INFO: renamed from: outlinedIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1606outlinedIconButtonColorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1030517545);
        long jM3507getTransparent0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        long jM3482unboximpl = (i11 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl() : j11;
        long jM3507getTransparent0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long jM3471copywmQWz5c$default = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1030517545, i10, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButton.kt:765)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(jM3507getTransparent0d7_KjU, jM3482unboximpl, jM3507getTransparent0d7_KjU2, jM3471copywmQWz5c$default, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconButtonColors;
    }

    public final BorderStroke outlinedIconToggleButtonBorder(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1244729690);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1244729690, i10, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonBorder (IconButton.kt:811)");
        }
        if (z5) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return null;
        }
        BorderStroke borderStrokeOutlinedIconButtonBorder = outlinedIconButtonBorder(z, composer, (i10 & 14) | ((i10 >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return borderStrokeOutlinedIconButtonBorder;
    }

    /* JADX INFO: renamed from: outlinedIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1607outlinedIconToggleButtonColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(2130592709);
        long jM3507getTransparent0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        long jM3482unboximpl = (i11 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3482unboximpl() : j11;
        long jM3507getTransparent0d7_KjU2 = (i11 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long jM3471copywmQWz5c$default = (i11 & 8) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long value = (i11 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedIconButtonTokens.INSTANCE.getSelectedContainerColor(), composer, 6) : j14;
        long jM1394contentColorForek8zF_U = (i11 & 32) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(value, composer, (i10 >> 12) & 14) : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2130592709, i10, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButton.kt:794)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3507getTransparent0d7_KjU, jM3482unboximpl, jM3507getTransparent0d7_KjU2, jM3471copywmQWz5c$default, value, jM1394contentColorForek8zF_U, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }
}
