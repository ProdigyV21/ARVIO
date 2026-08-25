package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material3.tokens.FilledAutocompleteTokens;
import androidx.compose.material3.tokens.OutlinedAutocompleteTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0098\u0003\u00105\u001a\u0002022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010&\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020\t2\b\b\u0002\u0010-\u001a\u00020\t2\b\b\u0002\u0010.\u001a\u00020\t2\b\b\u0002\u0010/\u001a\u00020\t2\b\b\u0002\u00100\u001a\u00020\t2\b\b\u0002\u00101\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00104J\u0098\u0003\u0010;\u001a\u0002022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u00020\t2\b\b\u0002\u00107\u001a\u00020\t2\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010&\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020\t2\b\b\u0002\u0010-\u001a\u00020\t2\b\b\u0002\u0010.\u001a\u00020\t2\b\b\u0002\u0010/\u001a\u00020\t2\b\b\u0002\u00100\u001a\u00020\t2\b\b\u0002\u00101\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b:\u00104J\u0084\u0003\u00105\u001a\u0002022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010<\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010&\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020\t2\b\b\u0002\u0010-\u001a\u00020\t2\b\b\u0002\u0010.\u001a\u00020\t2\b\b\u0002\u0010/\u001a\u00020\t2\b\b\u0002\u00100\u001a\u00020\t2\b\b\u0002\u00101\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\u0084\u0003\u0010;\u001a\u0002022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010<\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u00020\t2\b\b\u0002\u00107\u001a\u00020\t2\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010&\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020\t2\b\b\u0002\u0010-\u001a\u00020\t2\b\b\u0002\u0010.\u001a\u00020\t2\b\b\u0002\u0010/\u001a\u00020\t2\b\b\u0002\u00100\u001a\u00020\t2\b\b\u0002\u00101\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b?\u0010>J\u0082\u0002\u00105\u001a\u0002022\b\b\u0002\u0010@\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010<\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010A\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\bB\u0010CJ\u0082\u0002\u0010;\u001a\u0002022\b\b\u0002\u0010@\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010<\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u00020\t2\b\b\u0002\u00107\u001a\u00020\t2\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010A\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010CR\u0017\u0010F\u001a\u00020E8\u0006¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006J"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuDefaults;", "", "<init>", "()V", "", "expanded", "Lx6/t0;", "TrailingIcon", "(ZLandroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/Color;", "focusedTextColor", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "selectionColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "Landroidx/compose/material3/TextFieldColors;", "textFieldColors-FD9MK7s", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "textFieldColors", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "outlinedTextFieldColors-FD9MK7s", "outlinedTextFieldColors", "containerColor", "textFieldColors-tN0la-I", "(JJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "outlinedTextFieldColors-tN0la-I", "textColor", "placeholderColor", "textFieldColors-St-qZLY", "(JJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material3/TextFieldColors;", "outlinedTextFieldColors-St-qZLY", "Landroidx/compose/foundation/layout/PaddingValues;", "ItemContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getItemContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenuDefaults {
    public static final int $stable = 0;
    public static final ExposedDropdownMenuDefaults INSTANCE = new ExposedDropdownMenuDefaults();
    private static final PaddingValues ItemContentPadding = PaddingKt.m523PaddingValuesYgX7TsA(ExposedDropdownMenu_androidKt.ExposedDropdownMenuItemHorizontalPadding, Dp.m5678constructorimpl(0));

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuDefaults$TrailingIcon$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $expanded;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, int i10) {
            super(2);
            this.$expanded = z;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ExposedDropdownMenuDefaults.this.TrailingIcon(this.$expanded, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    private ExposedDropdownMenuDefaults() {
    }

    public final void TrailingIcon(boolean z, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-473088613);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-473088613, i11, -1, "androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.android.kt:322)");
            }
            IconKt.m1610Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, RotateKt.rotate(Modifier.INSTANCE, z ? 180.0f : 0.0f), 0L, composerStartRestartGroup, 48, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(z, i10));
        }
    }

    public final PaddingValues getItemContentPadding() {
        return ItemContentPadding;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-FD9MK7s, reason: not valid java name */
    public final TextFieldColors m1551outlinedTextFieldColorsFD9MK7s(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        composer.startReplaceableGroup(-1567195085);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), composer, 6) : j10;
        long value2 = (i14 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i14 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), composer, 6) : j13;
        long jM3507getTransparent0d7_KjU = (i14 & 16) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j14;
        long jM3507getTransparent0d7_KjU2 = (i14 & 32) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j15;
        long jM3507getTransparent0d7_KjU3 = (i14 & 64) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j16;
        long jM3507getTransparent0d7_KjU4 = (i14 & 128) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j17;
        long value4 = (i14 & 256) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j18;
        long value5 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j19;
        TextSelectionColors textSelectionColors2 = (i14 & 1024) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value6 = (i14 & 2048) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), composer, 6) : j20;
        long value7 = (i14 & 4096) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), composer, 6) : j21;
        long jM3471copywmQWz5c$default2 = (i14 & 8192) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j22;
        long value8 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), composer, 6) : j23;
        long value9 = (32768 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j24;
        long value10 = (65536 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j25;
        long jM3471copywmQWz5c$default3 = (131072 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j26;
        long value11 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j27;
        long value12 = (524288 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j28;
        long value13 = (1048576 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j29;
        long jM3471copywmQWz5c$default4 = (2097152 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j30;
        long value14 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j31;
        long value15 = (8388608 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j32;
        long value16 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j33;
        long jM3471copywmQWz5c$default5 = (33554432 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j34;
        long value17 = (67108864 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j35;
        long value18 = (134217728 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j36;
        long value19 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j37;
        long jM3471copywmQWz5c$default6 = (536870912 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j38;
        long value20 = (i14 & 1073741824) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j39;
        long value21 = (i15 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j40;
        long value22 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j41;
        long jM3471copywmQWz5c$default7 = (i15 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j42;
        long value23 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j43;
        long value24 = (i15 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j44;
        long value25 = (i15 & 32) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j45;
        long jM3471copywmQWz5c$default8 = (i15 & 64) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j46;
        long value26 = (i15 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j47;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1567195085, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:595)");
        }
        int i16 = i13 << 12;
        int i17 = i13 >> 18;
        int i18 = (i17 & 14) | 3072 | (i17 & 112) | (i17 & 896);
        long j48 = value2;
        long j49 = jM3507getTransparent0d7_KjU;
        long j50 = jM3507getTransparent0d7_KjU4;
        TextSelectionColors textSelectionColors3 = textSelectionColors2;
        long j51 = jM3471copywmQWz5c$default;
        long j52 = jM3507getTransparent0d7_KjU2;
        long j53 = value4;
        long j54 = value6;
        long j55 = value8;
        long j56 = jM3471copywmQWz5c$default3;
        long j57 = value13;
        long j58 = value15;
        long j59 = value17;
        long j60 = value7;
        long j61 = value9;
        long j62 = value11;
        long j63 = jM3471copywmQWz5c$default4;
        long j64 = value16;
        long j65 = value18;
        TextFieldColors textFieldColorsM1745colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m1745colors0hiis_0(value, j48, j51, value3, j49, j52, jM3507getTransparent0d7_KjU3, j50, j53, value5, textSelectionColors3, j54, j60, jM3471copywmQWz5c$default2, j55, j61, value10, j56, j62, value12, j57, j63, value14, j58, j64, jM3471copywmQWz5c$default5, j59, j65, value19, jM3471copywmQWz5c$default6, value20, 0L, 0L, 0L, 0L, value21, value22, jM3471copywmQWz5c$default7, value23, value24, value25, jM3471copywmQWz5c$default8, value26, composer, i10 & 2147483646, i11 & 2147483646, i12 & 2147483646, (i13 & 14) | (458752 & i16) | (3670016 & i16) | (29360128 & i16) | (234881024 & i16) | (i16 & 1879048192), i18, 0, 15);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1745colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: outlinedTextFieldColors-St-qZLY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1552outlinedTextFieldColorsStqZLY(long j10, long j11, long j12, long j13, long j14, TextSelectionColors textSelectionColors, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, Composer composer, int i10, int i11, int i12, int i13) {
        composer.startReplaceableGroup(-836383316);
        long value = (i13 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j10;
        long jM3471copywmQWz5c$default = (i13 & 2) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long jM3507getTransparent0d7_KjU = (i13 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long value2 = (i13 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j13;
        long value3 = (i13 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j14;
        TextSelectionColors textSelectionColors2 = (i13 & 32) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value4 = (i13 & 64) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), composer, 6) : j15;
        long value5 = (i13 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), composer, 6) : j16;
        long jM3471copywmQWz5c$default2 = (i13 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long value6 = (i13 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), composer, 6) : j18;
        long value7 = (i13 & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j19;
        long value8 = (i13 & 2048) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j20;
        long jM3471copywmQWz5c$default3 = (i13 & 4096) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long value9 = (i13 & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j22;
        long value10 = (i13 & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j23;
        long value11 = (32768 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j24;
        long jM3471copywmQWz5c$default4 = (65536 & i13) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j25;
        long value12 = (131072 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j26;
        long value13 = (262144 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j27;
        long value14 = (524288 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j28;
        long jM3471copywmQWz5c$default5 = (1048576 & i13) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j29;
        long value15 = (2097152 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j30;
        long value16 = (4194304 & i13) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default6 = (i13 & 8388608) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-836383316, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:989)");
        }
        OutlinedAutocompleteTokens outlinedAutocompleteTokens = OutlinedAutocompleteTokens.INSTANCE;
        long value17 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value18 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long jM3471copywmQWz5c$default7 = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        long value19 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value20 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value21 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long jM3471copywmQWz5c$default8 = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        long value22 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        int i14 = i10 << 3;
        int i15 = (i10 & 14) | (i14 & 112) | (i14 & 896);
        int i16 = i10 << 9;
        int i17 = i15 | (i16 & 7168) | ((i10 << 6) & 57344) | (i16 & 458752) | ((i10 << 12) & 3670016);
        int i18 = i10 << 15;
        int i19 = i17 | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192);
        int i20 = i11 << 15;
        int i21 = ((i10 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192);
        int i22 = i12 << 15;
        int i23 = i12 << 18;
        int i24 = ((i11 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i23 & 234881024) | (i23 & 1879048192);
        int i25 = ((i12 >> 6) & 14) | (i22 & 1879048192);
        long j33 = value;
        long j34 = jM3507getTransparent0d7_KjU;
        TextFieldColors textFieldColorsM1551outlinedTextFieldColorsFD9MK7s = m1551outlinedTextFieldColorsFD9MK7s(j33, value, jM3471copywmQWz5c$default, j33, j34, j34, j34, j34, value2, value3, textSelectionColors2, value4, value5, jM3471copywmQWz5c$default2, value6, value7, value8, jM3471copywmQWz5c$default3, value9, value10, value11, jM3471copywmQWz5c$default4, value12, value13, value14, jM3471copywmQWz5c$default5, value15, value16, value16, jM3471copywmQWz5c$default6, value16, value17, value18, jM3471copywmQWz5c$default7, value19, value20, value21, jM3471copywmQWz5c$default8, value22, composer, i19, i21, i24, i25, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1551outlinedTextFieldColorsFD9MK7s;
    }

    @x6.e
    /* JADX INFO: renamed from: outlinedTextFieldColors-tN0la-I, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1553outlinedTextFieldColorstN0laI(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, TextSelectionColors textSelectionColors, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        long j46;
        long jM3471copywmQWz5c$default;
        composer.startReplaceableGroup(-388128543);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), composer, 6) : j10;
        long value2 = (i14 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default2 = (i14 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), composer, 6) : j13;
        long jM3507getTransparent0d7_KjU = (i14 & 16) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j14;
        long jM3507getTransparent0d7_KjU2 = (i14 & 32) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j15;
        long value4 = (i14 & 64) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j16;
        long value5 = (i14 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j17;
        TextSelectionColors textSelectionColors2 = (i14 & 256) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value6 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), composer, 6) : j18;
        long value7 = (i14 & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), composer, 6) : j19;
        long jM3471copywmQWz5c$default3 = (i14 & 2048) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        long value8 = (i14 & 4096) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), composer, 6) : j21;
        long value9 = (i14 & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j22;
        long value10 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j23;
        long jM3471copywmQWz5c$default4 = (32768 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j24;
        long value11 = (65536 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j25;
        long value12 = (131072 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j26;
        long value13 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j27;
        long jM3471copywmQWz5c$default5 = (524288 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j28;
        long value14 = (1048576 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j29;
        long value15 = (2097152 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j30;
        long value16 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default6 = (8388608 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        long value17 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j33;
        long value18 = (33554432 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j34;
        long value19 = (67108864 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j35;
        long jM3471copywmQWz5c$default7 = (134217728 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j36;
        long value20 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j37;
        long value21 = (536870912 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j38;
        long value22 = (i14 & 1073741824) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j39;
        if ((i15 & 1) != 0) {
            j46 = value22;
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            j46 = value22;
            jM3471copywmQWz5c$default = j40;
        }
        long value23 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j41;
        long value24 = (i15 & 4) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j42;
        long value25 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j43;
        long jM3471copywmQWz5c$default8 = (i15 & 16) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j44;
        long value26 = (i15 & 32) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j45;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-388128543, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:814)");
        }
        int i16 = i10 << 6;
        int i17 = (65534 & i10) | ((i10 << 3) & 458752) | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024) | (i16 & 1879048192);
        int i18 = i11 << 6;
        int i19 = ((i10 >> 24) & 126) | (i18 & 896) | (i18 & 7168) | (i18 & 57344) | (i18 & 458752) | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192);
        int i20 = i12 << 6;
        int i21 = i13 << 6;
        long j47 = jM3507getTransparent0d7_KjU;
        TextFieldColors textFieldColorsM1551outlinedTextFieldColorsFD9MK7s = m1551outlinedTextFieldColorsFD9MK7s(value, value2, jM3471copywmQWz5c$default2, value3, j47, j47, j47, jM3507getTransparent0d7_KjU2, value4, value5, textSelectionColors2, value6, value7, jM3471copywmQWz5c$default3, value8, value9, value10, jM3471copywmQWz5c$default4, value11, value12, value13, jM3471copywmQWz5c$default5, value14, value15, value16, jM3471copywmQWz5c$default6, value17, value18, value19, jM3471copywmQWz5c$default7, value20, value21, j46, jM3471copywmQWz5c$default, value23, value24, value25, jM3471copywmQWz5c$default8, value26, composer, i17, i19, ((i11 >> 24) & 126) | (i20 & 896) | (i20 & 7168) | (i20 & 57344) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192), (i21 & 896) | ((i12 >> 24) & 126) | (i21 & 7168) | (i21 & 57344) | (i21 & 458752) | (i21 & 3670016) | (i21 & 29360128) | (i21 & 234881024) | (i21 & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1551outlinedTextFieldColorsFD9MK7s;
    }

    /* JADX INFO: renamed from: textFieldColors-FD9MK7s, reason: not valid java name */
    public final TextFieldColors m1554textFieldColorsFD9MK7s(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        composer.startReplaceableGroup(768358577);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), composer, 6) : j10;
        long value2 = (i14 & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i14 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), composer, 6) : j13;
        long value4 = (i14 & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j14;
        long value5 = (i14 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j15;
        long value6 = (i14 & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j16;
        long value7 = (i14 & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j17;
        long value8 = (i14 & 256) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j18;
        long value9 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j19;
        TextSelectionColors textSelectionColors2 = (i14 & 1024) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value10 = (i14 & 2048) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), composer, 6) : j20;
        long value11 = (i14 & 4096) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), composer, 6) : j21;
        long jM3471copywmQWz5c$default2 = (i14 & 8192) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j22;
        long value12 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), composer, 6) : j23;
        long value13 = (32768 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j24;
        long value14 = (65536 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j25;
        long jM3471copywmQWz5c$default3 = (131072 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j26;
        long value15 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j27;
        long value16 = (524288 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j28;
        long value17 = (1048576 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j29;
        long jM3471copywmQWz5c$default4 = (2097152 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j30;
        long value18 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j31;
        long value19 = (8388608 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j32;
        long value20 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j33;
        long value21 = (33554432 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6) : j34;
        long value22 = (67108864 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j35;
        long value23 = (134217728 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j36;
        long value24 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j37;
        long jM3471copywmQWz5c$default5 = (536870912 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j38;
        long value25 = (i14 & 1073741824) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j39;
        long value26 = (i15 & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j40;
        long value27 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j41;
        long jM3471copywmQWz5c$default6 = (i15 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j42;
        long value28 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j43;
        long value29 = (i15 & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j44;
        long value30 = (i15 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j45;
        long jM3471copywmQWz5c$default7 = (i15 & 64) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j46;
        long value31 = (i15 & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j47;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(768358577, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:442)");
        }
        int i16 = i13 << 12;
        int i17 = i13 >> 18;
        int i18 = (i17 & 14) | 3072 | (i17 & 112) | (i17 & 896);
        long j48 = value2;
        long j49 = value4;
        long j50 = value7;
        TextSelectionColors textSelectionColors3 = textSelectionColors2;
        long j51 = jM3471copywmQWz5c$default;
        long j52 = value5;
        long j53 = value8;
        long j54 = value10;
        long j55 = value12;
        long j56 = jM3471copywmQWz5c$default3;
        long j57 = value17;
        long j58 = value19;
        long j59 = value22;
        long j60 = value11;
        long j61 = value13;
        long j62 = value15;
        long j63 = jM3471copywmQWz5c$default4;
        long j64 = value20;
        long j65 = value23;
        TextFieldColors textFieldColorsM2123colors0hiis_0 = TextFieldDefaults.INSTANCE.m2123colors0hiis_0(value, j48, j51, value3, j49, j52, value6, j50, j53, value9, textSelectionColors3, j54, j60, jM3471copywmQWz5c$default2, j55, j61, value14, j56, j62, value16, j57, j63, value18, j58, j64, value21, j59, j65, value24, jM3471copywmQWz5c$default5, value25, 0L, 0L, 0L, 0L, value26, value27, jM3471copywmQWz5c$default6, value28, value29, value30, jM3471copywmQWz5c$default7, value31, composer, i10 & 2147483646, i11 & 2147483646, i12 & 2147483646, (i13 & 14) | (458752 & i16) | (3670016 & i16) | (29360128 & i16) | (234881024 & i16) | (i16 & 1879048192), i18, 0, 15);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2123colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldColors-St-qZLY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1555textFieldColorsStqZLY(long j10, long j11, long j12, long j13, long j14, TextSelectionColors textSelectionColors, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, Composer composer, int i10, int i11, int i12, int i13) {
        composer.startReplaceableGroup(-1343678550);
        long value = (i13 & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j10;
        long jM3471copywmQWz5c$default = (i13 & 2) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long value2 = (i13 & 4) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j12;
        long value3 = (i13 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j13;
        long value4 = (i13 & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j14;
        TextSelectionColors textSelectionColors2 = (i13 & 32) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value5 = (i13 & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), composer, 6) : j15;
        long value6 = (i13 & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), composer, 6) : j16;
        long jM3471copywmQWz5c$default2 = (i13 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long value7 = (i13 & 512) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), composer, 6) : j18;
        long value8 = (i13 & 1024) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j19;
        long value9 = (i13 & 2048) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j20;
        long jM3471copywmQWz5c$default3 = (i13 & 4096) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long value10 = (i13 & 8192) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j22;
        long value11 = (i13 & 16384) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j23;
        long value12 = (32768 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j24;
        long jM3471copywmQWz5c$default4 = (65536 & i13) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j25;
        long value13 = (131072 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j26;
        long value14 = (262144 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j27;
        long value15 = (524288 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j28;
        long value16 = (1048576 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6) : j29;
        long value17 = (2097152 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j30;
        long value18 = (4194304 & i13) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default5 = (i13 & 8388608) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1343678550, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:901)");
        }
        OutlinedAutocompleteTokens outlinedAutocompleteTokens = OutlinedAutocompleteTokens.INSTANCE;
        long value19 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value20 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long jM3471copywmQWz5c$default6 = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        long value21 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value22 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long value23 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        long jM3471copywmQWz5c$default7 = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        long value24 = ColorSchemeKt.getValue(outlinedAutocompleteTokens.getFieldSupportingTextColor(), composer, 6);
        int i14 = i10 << 3;
        int i15 = (i10 & 14) | (i14 & 112) | (i14 & 896);
        int i16 = i10 << 9;
        int i17 = i15 | (i16 & 7168) | ((i10 << 6) & 57344) | (i16 & 458752) | ((i10 << 12) & 3670016);
        int i18 = i10 << 15;
        int i19 = i17 | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192);
        int i20 = i11 << 15;
        int i21 = ((i10 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192);
        int i22 = i12 << 15;
        int i23 = i12 << 18;
        int i24 = ((i11 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i23 & 234881024) | (i23 & 1879048192);
        int i25 = ((i12 >> 6) & 14) | (i22 & 1879048192);
        long j33 = value;
        long j34 = value2;
        TextFieldColors textFieldColorsM1554textFieldColorsFD9MK7s = m1554textFieldColorsFD9MK7s(j33, value, jM3471copywmQWz5c$default, j33, j34, j34, j34, j34, value3, value4, textSelectionColors2, value5, value6, jM3471copywmQWz5c$default2, value7, value8, value9, jM3471copywmQWz5c$default3, value10, value11, value12, jM3471copywmQWz5c$default4, value13, value14, value15, value16, value17, value18, value18, jM3471copywmQWz5c$default5, value18, value19, value20, jM3471copywmQWz5c$default6, value21, value22, value23, jM3471copywmQWz5c$default7, value24, composer, i19, i21, i24, i25, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1554textFieldColorsFD9MK7s;
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldColors-tN0la-I, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1556textFieldColorstN0laI(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, TextSelectionColors textSelectionColors, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        long j46;
        long jM3471copywmQWz5c$default;
        composer.startReplaceableGroup(611690079);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), composer, 6) : j10;
        long value2 = (i14 & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default2 = (i14 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), composer, 6) : j13;
        long value4 = (i14 & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j14;
        long value5 = (i14 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), composer, 6) : j15;
        long value6 = (i14 & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), composer, 6) : j16;
        long value7 = (i14 & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), composer, 6) : j17;
        TextSelectionColors textSelectionColors2 = (i14 & 256) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value8 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), composer, 6) : j18;
        long value9 = (i14 & 1024) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), composer, 6) : j19;
        long jM3471copywmQWz5c$default3 = (i14 & 2048) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        long value10 = (i14 & 4096) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), composer, 6) : j21;
        long value11 = (i14 & 8192) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), composer, 6) : j22;
        long value12 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), composer, 6) : j23;
        long jM3471copywmQWz5c$default4 = (32768 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j24;
        long value13 = (65536 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), composer, 6) : j25;
        long value14 = (131072 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), composer, 6) : j26;
        long value15 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), composer, 6) : j27;
        long jM3471copywmQWz5c$default5 = (524288 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j28;
        long value16 = (1048576 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), composer, 6) : j29;
        long value17 = (2097152 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), composer, 6) : j30;
        long value18 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), composer, 6) : j31;
        long value19 = (8388608 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), composer, 6) : j32;
        long value20 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), composer, 6) : j33;
        long value21 = (33554432 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j34;
        long value22 = (67108864 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j35;
        long jM3471copywmQWz5c$default6 = (134217728 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j36;
        long value23 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j37;
        long value24 = (536870912 & i14) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j38;
        long value25 = (i14 & 1073741824) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j39;
        if ((i15 & 1) != 0) {
            j46 = value25;
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            j46 = value25;
            jM3471copywmQWz5c$default = j40;
        }
        long value26 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j41;
        long value27 = (i15 & 4) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j42;
        long value28 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j43;
        long jM3471copywmQWz5c$default7 = (i15 & 16) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j44;
        long value29 = (i15 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), composer, 6) : j45;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611690079, i10, i11, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:709)");
        }
        int i16 = i10 << 6;
        int i17 = (65534 & i10) | ((i10 << 3) & 458752) | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024) | (i16 & 1879048192);
        int i18 = i11 << 6;
        int i19 = ((i10 >> 24) & 126) | (i18 & 896) | (i18 & 7168) | (i18 & 57344) | (i18 & 458752) | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192);
        int i20 = i12 << 6;
        int i21 = i13 << 6;
        long j47 = value4;
        TextFieldColors textFieldColorsM1554textFieldColorsFD9MK7s = m1554textFieldColorsFD9MK7s(value, value2, jM3471copywmQWz5c$default2, value3, j47, j47, j47, value5, value6, value7, textSelectionColors2, value8, value9, jM3471copywmQWz5c$default3, value10, value11, value12, jM3471copywmQWz5c$default4, value13, value14, value15, jM3471copywmQWz5c$default5, value16, value17, value18, value19, value20, value21, value22, jM3471copywmQWz5c$default6, value23, value24, j46, jM3471copywmQWz5c$default, value26, value27, value28, jM3471copywmQWz5c$default7, value29, composer, i17, i19, ((i11 >> 24) & 126) | (i20 & 896) | (i20 & 7168) | (i20 & 57344) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192), (i21 & 896) | ((i12 >> 24) & 126) | (i21 & 7168) | (i21 & 57344) | (i21 & 458752) | (i21 & 3670016) | (i21 & 29360128) | (i21 & 234881024) | (i21 & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1554textFieldColorsFD9MK7s;
    }
}
