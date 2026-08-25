package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.InputIdentifier;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import io.ktor.http.ContentDisposition;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0087\u0001\u0010\u0014\u001a\u00020\b2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00002:\u0010\t\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0001¢\u0006\u0004\b\u0014\u0010\u0015\"\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"", "selectedStartDateMillis", "selectedEndDateMillis", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "startDateMillis", "endDateMillis", "Lx6/t0;", "onDatesSelectionChange", "Landroidx/compose/material3/CalendarModel;", "calendarModel", "Lx7/i;", "yearRange", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "Landroidx/compose/material3/SelectableDates;", "selectableDates", "Landroidx/compose/material3/DatePickerColors;", "colors", "DateRangeInputContent", "(Ljava/lang/Long;Ljava/lang/Long;Lr7/p;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/unit/Dp;", "TextFieldSpacing", "F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateRangeInputKt {
    private static final float TextFieldSpacing = Dp.m5678constructorimpl(8);

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Long l10, Long l11, p<? super Long, ? super Long, t0> pVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$selectedStartDateMillis = l10;
            this.$selectedEndDateMillis = l11;
            this.$onDatesSelectionChange = pVar;
            this.$calendarModel = calendarModel;
            this.$yearRange = iVar;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DateRangeInputKt.DateRangeInputContent(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$onDatesSelectionChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    public static final void DateRangeInputContent(Long l10, Long l11, p<? super Long, ? super Long, t0> pVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        i iVar2;
        SelectableDates selectableDates2;
        DateInputFormat dateInputFormat;
        Composer composerStartRestartGroup = composer.startRestartGroup(-607499086);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(l11) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            iVar2 = iVar;
            i11 |= composerStartRestartGroup.changedInstance(iVar2) ? 16384 : 8192;
        } else {
            iVar2 = iVar;
        }
        if ((196608 & i10) == 0) {
            i11 |= (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            selectableDates2 = selectableDates;
            i11 |= composerStartRestartGroup.changed(selectableDates2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i10 & 12582912) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? 8388608 : 4194304;
        }
        if ((i11 & 4793491) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-607499086, i11, -1, "androidx.compose.material3.DateRangeInputContent (DateRangeInput.kt:40)");
            }
            Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1694771901);
            boolean zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getDateInputFormat(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            DateInputFormat dateInputFormat2 = (DateInputFormat) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            Strings.Companion companion = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_invalid_for_pattern), composerStartRestartGroup, 0);
            String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_invalid_year_range), composerStartRestartGroup, 0);
            String strM1994getStringNWtq283 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_invalid_not_allowed), composerStartRestartGroup, 0);
            String strM1994getStringNWtq284 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_input_invalid_range_input), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1694772328);
            boolean zChanged2 = ((i11 & 458752) == 131072 || ((i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 && composerStartRestartGroup.changed(datePickerFormatter))) | composerStartRestartGroup.changed(dateInputFormat2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                dateInputFormat = dateInputFormat2;
                objRememberedValue2 = new DateInputValidator(iVar2, selectableDates2, dateInputFormat, datePickerFormatter, strM1994getStringNWtq28, strM1994getStringNWtq282, strM1994getStringNWtq283, strM1994getStringNWtq284, null, null, 768, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                dateInputFormat = dateInputFormat2;
            }
            DateInputValidator dateInputValidator = (DateInputValidator) objRememberedValue2;
            composerStartRestartGroup.endReplaceableGroup();
            dateInputValidator.setCurrentStartDateMillis$material3_release(l10);
            dateInputValidator.setCurrentEndDateMillis$material3_release(l11);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierPadding = PaddingKt.padding(companion2, DateInputKt.getInputTextFieldPadding());
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(TextFieldSpacing);
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(Alignment.INSTANCE, horizontalOrVerticalM441spacedBy0680j_4, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
            if (!(composerStartRestartGroup.getApplier() != null)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyC, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String upperCase = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            String strM1994getStringNWtq285 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, 0.5f, false, 2, null);
            InputIdentifier.Companion companion4 = InputIdentifier.INSTANCE;
            int iM1633getStartDateInputJ2x2o4M = companion4.m1633getStartDateInputJ2x2o4M();
            composerStartRestartGroup.startReplaceableGroup(1849029901);
            int i12 = i11 & 896;
            int i13 = i11 & 112;
            boolean z = (i12 == 256) | (i13 == 32);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new DateRangeInputKt$DateRangeInputContent$2$1$1(pVar, l11);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i14 = i11 & 7168;
            int i15 = (i11 >> 21) & 14;
            int i16 = i11;
            DateInputFormat dateInputFormat3 = dateInputFormat;
            DateInputKt.m1447DateInputTextFieldtQNruF0(modifierA, l10, (l) objRememberedValue3, calendarModel, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 801434508, true, new DateRangeInputKt$DateRangeInputContent$2$2(strM1994getStringNWtq285, upperCase)), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 665407211, true, new DateRangeInputKt$DateRangeInputContent$2$3(upperCase)), iM1633getStartDateInputJ2x2o4M, dateInputValidator, dateInputFormat3, localeDefaultLocale, datePickerColors, composerStartRestartGroup, ((i11 << 3) & 112) | 1794048 | i14, i15);
            String strM1994getStringNWtq286 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
            Modifier modifierA2 = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, 0.5f, false, 2, null);
            int iM1631getEndDateInputJ2x2o4M = companion4.m1631getEndDateInputJ2x2o4M();
            composerStartRestartGroup.startReplaceableGroup(1849030941);
            boolean z5 = (i12 == 256) | ((i16 & 14) == 4);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new DateRangeInputKt$DateRangeInputContent$2$4$1(pVar, l10);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceableGroup();
            DateInputKt.m1447DateInputTextFieldtQNruF0(modifierA2, l11, (l) objRememberedValue4, calendarModel, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 911487285, true, new DateRangeInputKt$DateRangeInputContent$2$5(strM1994getStringNWtq286, upperCase)), ComposableLambdaKt.composableLambda(composerStartRestartGroup, -961726252, true, new DateRangeInputKt$DateRangeInputContent$2$6(upperCase)), iM1631getEndDateInputJ2x2o4M, dateInputValidator, dateInputFormat3, localeDefaultLocale, datePickerColors, composerStartRestartGroup, i13 | 1794048 | i14, i15);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass3(l10, l11, pVar, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }
}
