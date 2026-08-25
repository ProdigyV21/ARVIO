package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.SearchBarTokens;
import androidx.compose.material3.tokens.SearchViewTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u008a\u0001\u0010 \u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020\u001e2\b\b\u0002\u0010%\u001a\u00020\u001e2\b\b\u0002\u0010&\u001a\u00020\u001e2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020\u001e2\b\b\u0002\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\u001e2\b\b\u0002\u0010,\u001a\u00020\u001e2\b\b\u0002\u0010-\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u001e2\b\b\u0002\u0010/\u001a\u00020\u001e2\b\b\u0002\u00100\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u009e\u0001\u0010 \u001a\u00020!2\b\b\u0002\u00103\u001a\u00020\u001e2\b\b\u0002\u00104\u001a\u00020\u001e2\b\b\u0002\u0010%\u001a\u00020\u001e2\b\b\u0002\u0010&\u001a\u00020\u001e2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020\u001e2\b\b\u0002\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\u001e2\b\b\u0002\u0010,\u001a\u00020\u001e2\b\b\u0002\u0010-\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u001e2\b\b\u0002\u00105\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u00020\u001e2\b\b\u0002\u00100\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00108R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00069"}, d2 = {"Landroidx/compose/material3/SearchBarDefaults;", "", "()V", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM$annotations", "getElevation-D9Ej5fM", "()F", "F", "InputFieldHeight", "getInputFieldHeight-D9Ej5fM", "ShadowElevation", "getShadowElevation-D9Ej5fM", "TonalElevation", "getTonalElevation-D9Ej5fM", "dockedShape", "Landroidx/compose/ui/graphics/Shape;", "getDockedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "fullScreenShape", "getFullScreenShape", "inputFieldShape", "getInputFieldShape", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "colors", "Landroidx/compose/material3/SearchBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "dividerColor", "inputFieldColors", "Landroidx/compose/material3/TextFieldColors;", "colors-Klgx-Pg", "(JJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "textColor", "disabledTextColor", "cursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "placeholderColor", "disabledPlaceholderColor", "inputFieldColors--u-KgnY", "(JJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "unfocusedTextColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "inputFieldColors-ITpI4ow", "(JJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SearchBarDefaults {
    public static final int $stable = 0;
    private static final float Elevation;
    public static final SearchBarDefaults INSTANCE = new SearchBarDefaults();
    private static final float InputFieldHeight;
    private static final float ShadowElevation;
    private static final float TonalElevation;

    static {
        SearchBarTokens searchBarTokens = SearchBarTokens.INSTANCE;
        float fM2806getContainerElevationD9Ej5fM = searchBarTokens.m2806getContainerElevationD9Ej5fM();
        TonalElevation = fM2806getContainerElevationD9Ej5fM;
        ShadowElevation = ElevationTokens.INSTANCE.m2506getLevel0D9Ej5fM();
        Elevation = fM2806getContainerElevationD9Ej5fM;
        InputFieldHeight = searchBarTokens.m2807getContainerHeightD9Ej5fM();
    }

    private SearchBarDefaults() {
    }

    @x6.e
    /* JADX INFO: renamed from: getElevation-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m1808getElevationD9Ej5fM$annotations() {
    }

    /* JADX INFO: renamed from: colors-Klgx-Pg, reason: not valid java name */
    public final SearchBarColors m1809colorsKlgxPg(long j10, long j11, TextFieldColors textFieldColors, Composer composer, int i10, int i11) {
        int i12;
        TextFieldColors textFieldColorsM1815inputFieldColorsITpI4ow;
        composer.startReplaceableGroup(-1216168196);
        long value = (i11 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), composer, 6) : j10;
        long value2 = (i11 & 2) != 0 ? ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), composer, 6) : j11;
        if ((i11 & 4) != 0) {
            i12 = i10;
            textFieldColorsM1815inputFieldColorsITpI4ow = m1815inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, (i10 << 3) & 57344, 16383);
        } else {
            i12 = i10;
            textFieldColorsM1815inputFieldColorsITpI4ow = textFieldColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1216168196, i12, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.android.kt:548)");
        }
        SearchBarColors searchBarColors = new SearchBarColors(value, value2, textFieldColorsM1815inputFieldColorsITpI4ow, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return searchBarColors;
    }

    public final Shape getDockedShape(Composer composer, int i10) {
        composer.startReplaceableGroup(1006952150);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1006952150, i10, -1, "androidx.compose.material3.SearchBarDefaults.<get-dockedShape> (SearchBar.android.kt:530)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getDockedContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1810getElevationD9Ej5fM() {
        return Elevation;
    }

    public final Shape getFullScreenShape(Composer composer, int i10) {
        composer.startReplaceableGroup(1665502056);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1665502056, i10, -1, "androidx.compose.material3.SearchBarDefaults.<get-fullScreenShape> (SearchBar.android.kt:527)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getFullScreenContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getInputFieldHeight-D9Ej5fM, reason: not valid java name */
    public final float m1811getInputFieldHeightD9Ej5fM() {
        return InputFieldHeight;
    }

    public final Shape getInputFieldShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-971556142);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-971556142, i10, -1, "androidx.compose.material3.SearchBarDefaults.<get-inputFieldShape> (SearchBar.android.kt:523)");
        }
        Shape value = ShapesKt.getValue(SearchBarTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m1812getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m1813getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i10) {
        composer.startReplaceableGroup(2112270157);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2112270157, i10, -1, "androidx.compose.material3.SearchBarDefaults.<get-windowInsets> (SearchBar.android.kt:533)");
        }
        WindowInsets statusBars = WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return statusBars;
    }

    @x6.e
    /* JADX INFO: renamed from: inputFieldColors--u-KgnY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1814inputFieldColorsuKgnY(long j10, long j11, long j12, TextSelectionColors textSelectionColors, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, Composer composer, int i10, int i11, int i12) {
        composer.startReplaceableGroup(355927049);
        long value = (i12 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j10;
        long jM3471copywmQWz5c$default = (i12 & 2) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long value2 = (i12 & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j12;
        TextSelectionColors textSelectionColors2 = (i12 & 8) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value3 = (i12 & 16) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j13;
        long value4 = (i12 & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j14;
        long jM3471copywmQWz5c$default2 = (i12 & 64) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j15;
        long value5 = (i12 & 128) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j16;
        long value6 = (i12 & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j17;
        long jM3471copywmQWz5c$default3 = (i12 & 512) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j18;
        long value7 = (i12 & 1024) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j19;
        long jM3471copywmQWz5c$default4 = (i12 & 2048) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(355927049, i10, i11, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:635)");
        }
        int i13 = i10 << 3;
        int i14 = i11 << 6;
        long j21 = value7;
        long j22 = value;
        long j23 = value5;
        TextFieldColors textFieldColorsM1815inputFieldColorsITpI4ow = m1815inputFieldColorsITpI4ow(j22, j22, jM3471copywmQWz5c$default, value2, textSelectionColors2, value3, value4, jM3471copywmQWz5c$default2, j23, value6, jM3471copywmQWz5c$default3, j21, j21, jM3471copywmQWz5c$default4, composer, (i10 & 14) | (i13 & 112) | (i13 & 896) | (i13 & 7168) | (i13 & 57344) | (i13 & 458752) | (i13 & 3670016) | (i13 & 29360128) | (i13 & 234881024) | (i13 & 1879048192), ((i10 >> 27) & 14) | ((i11 << 3) & 112) | (i14 & 896) | (i14 & 7168) | (i14 & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1815inputFieldColorsITpI4ow;
    }

    /* JADX INFO: renamed from: inputFieldColors-ITpI4ow, reason: not valid java name */
    public final TextFieldColors m1815inputFieldColorsITpI4ow(long j10, long j11, long j12, long j13, TextSelectionColors textSelectionColors, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, Composer composer, int i10, int i11, int i12) {
        composer.startReplaceableGroup(-602148837);
        long value = (i12 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j10;
        long value2 = (i12 & 2) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i12 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i12 & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j13;
        TextSelectionColors textSelectionColors2 = (i12 & 16) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value4 = (i12 & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j14;
        long value5 = (i12 & 64) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j15;
        long jM3471copywmQWz5c$default2 = (i12 & 128) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long value6 = (i12 & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j17;
        long value7 = (i12 & 512) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j18;
        long jM3471copywmQWz5c$default3 = (i12 & 1024) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j19;
        long value8 = (i12 & 2048) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j20;
        long value9 = (i12 & 4096) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j21;
        long jM3471copywmQWz5c$default4 = (i12 & 8192) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j22;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-602148837, i10, i11, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:599)");
        }
        int i13 = i11 << 18;
        TextFieldColors textFieldColorsM2123colors0hiis_0 = TextFieldDefaults.INSTANCE.m2123colors0hiis_0(value, value2, jM3471copywmQWz5c$default, 0L, 0L, 0L, 0L, 0L, value3, 0L, textSelectionColors2, 0L, 0L, 0L, 0L, value4, value5, jM3471copywmQWz5c$default2, 0L, value6, value7, jM3471copywmQWz5c$default3, 0L, 0L, 0L, 0L, 0L, value8, value9, jM3471copywmQWz5c$default4, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, (i10 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED) | ((i10 << 15) & 234881024), ((i10 >> 12) & 14) | (458752 & i10) | (3670016 & i10) | (i10 & 29360128) | ((i10 << 3) & 1879048192), ((i10 >> 27) & 14) | ((i11 << 3) & 112) | (i13 & 29360128) | (i13 & 234881024) | (i13 & 1879048192), 0, 3072, 1204058872, 4095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2123colors0hiis_0;
    }
}
