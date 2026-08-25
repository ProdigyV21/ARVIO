package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyListScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$VerticalMonthsList$1$2$1 extends r implements l<LazyListScope, t0> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ List<CustomAccessibilityAction> $customAccessibilityAction;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ CalendarMonth $firstMonth;
    final /* synthetic */ l<Long, t0> $onDateSelectionChange;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;
    final /* synthetic */ CalendarDate $today;
    final /* synthetic */ i $yearRange;

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ List<CustomAccessibilityAction> $customAccessibilityAction;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ CalendarDate $today;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(CalendarModel calendarModel, CalendarMonth calendarMonth, Long l10, Long l11, l<? super Long, t0> lVar, CalendarDate calendarDate, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, List<CustomAccessibilityAction> list) {
            super(4);
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$selectedStartDateMillis = l10;
            this.$selectedEndDateMillis = l11;
            this.$onDateSelectionChange = lVar;
            this.$today = calendarDate;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
            this.$customAccessibilityAction = list;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            int i12;
            SelectedRangeInfo selectedRangeInfo;
            if ((i11 & 6) == 0) {
                i12 = i11 | (composer.changed(lazyItemScope) ? 4 : 2);
            } else {
                i12 = i11;
            }
            if ((i11 & 48) == 0) {
                i12 |= composer.changed(i10) ? 32 : 16;
            }
            if ((i12 & 147) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1413501381, i12, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:812)");
            }
            CalendarMonth calendarMonthPlusMonths = this.$calendarModel.plusMonths(this.$firstMonth, i10);
            Modifier modifierD = androidx.compose.foundation.lazy.a.d(lazyItemScope, Modifier.INSTANCE, 0.0f, 1, null);
            Long l10 = this.$selectedStartDateMillis;
            Long l11 = this.$selectedEndDateMillis;
            l<Long, t0> lVar = this.$onDateSelectionChange;
            CalendarDate calendarDate = this.$today;
            DatePickerFormatter datePickerFormatter = this.$dateFormatter;
            SelectableDates selectableDates = this.$selectableDates;
            DatePickerColors datePickerColors = this.$colors;
            List<CustomAccessibilityAction> list = this.$customAccessibilityAction;
            CalendarModel calendarModel = this.$calendarModel;
            composer.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierD);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composer, 6), DatePickerModalTokens.INSTANCE.getRangeSelectionMonthSubheadFont()), ComposableLambdaKt.composableLambda(composer, 1622100276, true, new DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1(datePickerFormatter, calendarMonthPlusMonths, list, datePickerColors)), composer, 48);
            composer.startReplaceableGroup(-1455463505);
            if (l10 == null || l11 == null) {
                selectedRangeInfo = null;
            } else {
                composer.startReplaceableGroup(-1455463352);
                boolean zChanged = composer.changed(l10) | composer.changed(l11);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SelectedRangeInfo.INSTANCE.calculateRangeInfo(calendarMonthPlusMonths, calendarModel.getCanonicalDate(l10.longValue()), calendarModel.getCanonicalDate(l11.longValue()));
                    composer.updateRememberedValue(objRememberedValue);
                }
                selectedRangeInfo = (SelectedRangeInfo) objRememberedValue;
                composer.endReplaceableGroup();
            }
            composer.endReplaceableGroup();
            DatePickerKt.Month(calendarMonthPlusMonths, lVar, calendarDate.getUtcTimeMillis(), l10, l11, selectedRangeInfo, datePickerFormatter, selectableDates, datePickerColors, composer, 0);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangePickerKt$VerticalMonthsList$1$2$1(i iVar, CalendarModel calendarModel, CalendarMonth calendarMonth, Long l10, Long l11, l<? super Long, t0> lVar, CalendarDate calendarDate, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, List<CustomAccessibilityAction> list) {
        super(1);
        this.$yearRange = iVar;
        this.$calendarModel = calendarModel;
        this.$firstMonth = calendarMonth;
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$onDateSelectionChange = lVar;
        this.$today = calendarDate;
        this.$dateFormatter = datePickerFormatter;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
        this.$customAccessibilityAction = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LazyListScope) obj);
        return t0.f22605a;
    }

    public final void invoke(LazyListScope lazyListScope) {
        LazyListScope.CC.k(lazyListScope, DatePickerKt.numberOfMonthsInRange(this.$yearRange), null, null, ComposableLambdaKt.composableLambdaInstance(-1413501381, true, new AnonymousClass1(this.$calendarModel, this.$firstMonth, this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$onDateSelectionChange, this.$today, this.$dateFormatter, this.$selectableDates, this.$colors, this.$customAccessibilityAction)), 6, null);
    }
}
