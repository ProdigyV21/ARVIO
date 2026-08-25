package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.MenuTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\rJN\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00020\b*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/MenuDefaults;", "", "()V", "DropdownMenuItemContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDropdownMenuItemContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "defaultMenuItemColors", "Landroidx/compose/material3/MenuItemColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultMenuItemColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/MenuItemColors;", "itemColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/MenuItemColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "leadingIconColor", "trailingIconColor", "disabledTextColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "itemColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/MenuItemColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MenuDefaults {
    public static final int $stable = 0;
    public static final MenuDefaults INSTANCE = new MenuDefaults();
    private static final PaddingValues DropdownMenuItemContentPadding = PaddingKt.m523PaddingValuesYgX7TsA(MenuKt.DropdownMenuItemHorizontalPadding, Dp.m5678constructorimpl(0));

    private MenuDefaults() {
    }

    public final MenuItemColors getDefaultMenuItemColors$material3_release(ColorScheme colorScheme) {
        MenuItemColors defaultMenuItemColorsCached = colorScheme.getDefaultMenuItemColorsCached();
        if (defaultMenuItemColorsCached != null) {
            return defaultMenuItemColorsCached;
        }
        MenuTokens menuTokens = MenuTokens.INSTANCE;
        MenuItemColors menuItemColors = new MenuItemColors(ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemTrailingIconColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, menuTokens.getListItemDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultMenuItemColorsCached$material3_release(menuItemColors);
        return menuItemColors;
    }

    public final PaddingValues getDropdownMenuItemContentPadding() {
        return DropdownMenuItemContentPadding;
    }

    public final MenuItemColors itemColors(Composer composer, int i10) {
        composer.startReplaceableGroup(1326531516);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1326531516, i10, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:67)");
        }
        MenuItemColors defaultMenuItemColors$material3_release = getDefaultMenuItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultMenuItemColors$material3_release;
    }

    /* JADX INFO: renamed from: itemColors-5tl4gsc, reason: not valid java name */
    public final MenuItemColors m1672itemColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1278543580);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i11 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1278543580, i10, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:91)");
        }
        MenuItemColors menuItemColorsM1674copytNS2XkQ = getDefaultMenuItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1674copytNS2XkQ(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return menuItemColorsM1674copytNS2XkQ;
    }
}
