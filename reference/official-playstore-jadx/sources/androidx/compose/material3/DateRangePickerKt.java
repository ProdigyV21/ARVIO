package androidx.compose.material3;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.material3.Strings;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.k;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.List;
import java.util.Locale;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001am\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aT\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a`\u0010#\u001a\u00020\u00002\n\u0010 \u001a\u00060\u001ej\u0002`\u001f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a½\u0001\u00105\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u00112\b\u0010%\u001a\u0004\u0018\u00010\u00112\u0006\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00172:\u0010-\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00070(2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00070.2\u0006\u00102\u001a\u0002012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a²\u0001\u00106\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u00112\b\u0010%\u001a\u0004\u0018\u00010\u00112\u0006\u0010&\u001a\u00020\u00112:\u0010-\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00070(2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00070.2\u0006\u00102\u001a\u0002012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b6\u00107\u001a²\u0001\u0010:\u001a\u00020\u00072\u0006\u00109\u001a\u0002082\b\u0010$\u001a\u0004\u0018\u00010\u00112\b\u0010%\u001a\u0004\u0018\u00010\u00112:\u0010-\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00070(2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00070.2\u0006\u00102\u001a\u0002012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b:\u0010;\u001ag\u0010?\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u00112\b\u0010=\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010\u00112:\u0010-\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00070(H\u0002¢\u0006\u0004\b?\u0010@\u001a&\u0010H\u001a\u00020\u0007*\u00020A2\u0006\u0010C\u001a\u00020B2\u0006\u0010E\u001a\u00020DH\u0000ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001a5\u0010P\u001a\b\u0012\u0004\u0012\u00020O0N2\u0006\u0010\u0001\u001a\u0002082\u0006\u0010J\u001a\u00020I2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020KH\u0002¢\u0006\u0004\bP\u0010Q\"\u001a\u0010S\u001a\u00020R8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0014\u0010W\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010T\"\u0014\u0010X\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010T\"\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\\"}, d2 = {"Landroidx/compose/material3/DateRangePickerState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", LinkHeader.Parameters.Title, "headline", "", "showModeToggle", "Landroidx/compose/material3/DatePickerColors;", "colors", "DateRangePicker", "(Landroidx/compose/material3/DateRangePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Lr7/p;Lr7/p;ZLandroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "", "initialSelectedStartDateMillis", "initialSelectedEndDateMillis", "initialDisplayedMonthMillis", "Lx7/i;", "yearRange", "Landroidx/compose/material3/DisplayMode;", "initialDisplayMode", "Landroidx/compose/material3/SelectableDates;", "selectableDates", "rememberDateRangePickerState-IlFM19s", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lx7/i;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DateRangePickerState;", "rememberDateRangePickerState", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "DateRangePickerState-HVP43zI", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lx7/i;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DateRangePickerState;", "DateRangePickerState", "selectedStartDateMillis", "selectedEndDateMillis", "displayedMonthMillis", "displayMode", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "startDateMillis", "endDateMillis", "onDatesSelectionChange", "Lkotlin/Function1;", "monthInMillis", "onDisplayedMonthChange", "Landroidx/compose/material3/CalendarModel;", "calendarModel", "SwitchableDateEntryContent-RN-2D1Q", "(Ljava/lang/Long;Ljava/lang/Long;JILr7/p;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "SwitchableDateEntryContent", "DateRangePickerContent", "(Ljava/lang/Long;Ljava/lang/Long;JLr7/p;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/lazy/LazyListState;", "lazyListState", "VerticalMonthsList", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Ljava/lang/Long;Lr7/p;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "dateInMillis", "currentStartDateMillis", "currentEndDateMillis", "updateDateSelection", "(JLjava/lang/Long;Ljava/lang/Long;Lr7/p;)V", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Landroidx/compose/material3/SelectedRangeInfo;", "selectedRangeInfo", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawRangeBackground-mxwnekA", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;Landroidx/compose/material3/SelectedRangeInfo;J)V", "drawRangeBackground", "Lka/k0;", "coroutineScope", "", "scrollUpLabel", "scrollDownLabel", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "customScrollActions", "(Landroidx/compose/foundation/lazy/LazyListState;Lka/k0;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "Landroidx/compose/foundation/layout/PaddingValues;", "CalendarMonthSubheadPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getCalendarMonthSubheadPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DateRangePickerTitlePadding", "DateRangePickerHeadlinePadding", "Landroidx/compose/ui/unit/Dp;", "HeaderHeightOffset", "F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerKt {
    private static final PaddingValues DateRangePickerHeadlinePadding;
    private static final PaddingValues DateRangePickerTitlePadding;
    private static final PaddingValues CalendarMonthSubheadPadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(20), 0.0f, Dp.m5678constructorimpl(8), 4, null);
    private static final float HeaderHeightOffset = Dp.m5678constructorimpl(60);

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DateRangePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DateRangePickerState dateRangePickerState) {
            super(2);
            this.$state = dateRangePickerState;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-162164694, i10, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:89)");
            }
            DateRangePickerDefaults.INSTANCE.m1503DateRangePickerTitlehOD91z4(this.$state.mo1513getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ DateRangePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(DateRangePickerState dateRangePickerState, DatePickerFormatter datePickerFormatter) {
            super(2);
            this.$state = dateRangePickerState;
            this.$dateFormatter = datePickerFormatter;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-185279404, i10, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:95)");
            }
            DateRangePickerDefaults.INSTANCE.m1502DateRangePickerHeadlinev84Udv0(this.$state.getSelectedStartDateMillis(), this.$state.getSelectedEndDateMillis(), this.$state.mo1513getDisplayModejFl4v0(), this.$dateFormatter, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer, 221184, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DateRangePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(DateRangePickerState dateRangePickerState) {
            super(2);
            this.$state = dateRangePickerState;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1490010652, i10, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:114)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
            int iMo1513getDisplayModejFl4v0 = this.$state.mo1513getDisplayModejFl4v0();
            composer.startReplaceableGroup(-1467496949);
            boolean zChanged = composer.changed(this.$state);
            DateRangePickerState dateRangePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DateRangePickerKt$DateRangePicker$4$1$1(dateRangePickerState);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            DatePickerKt.m1484DisplayModeToggleButtontER2X8s(modifierPadding, iMo1513getDisplayModejFl4v0, (l) objRememberedValue, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ DateRangePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(DateRangePickerState dateRangePickerState, CalendarModel calendarModel, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors) {
            super(2);
            this.$state = dateRangePickerState;
            this.$calendarModel = calendarModel;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-57534331, i10, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:130)");
            }
            Long selectedStartDateMillis = this.$state.getSelectedStartDateMillis();
            Long selectedEndDateMillis = this.$state.getSelectedEndDateMillis();
            long displayedMonthMillis = this.$state.getDisplayedMonthMillis();
            int iMo1513getDisplayModejFl4v0 = this.$state.mo1513getDisplayModejFl4v0();
            composer.startReplaceableGroup(-1467496213);
            boolean zChanged = composer.changed(this.$state);
            DateRangePickerState dateRangePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DateRangePickerKt$DateRangePicker$5$1$1(dateRangePickerState);
                composer.updateRememberedValue(objRememberedValue);
            }
            p pVar = (p) objRememberedValue;
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-1467495707);
            boolean zChanged2 = composer.changed(this.$state);
            DateRangePickerState dateRangePickerState2 = this.$state;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new DateRangePickerKt$DateRangePicker$5$2$1(dateRangePickerState2);
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            DateRangePickerKt.m1506SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1513getDisplayModejFl4v0, pVar, (l) objRememberedValue2, this.$calendarModel, this.$state.getYearRange(), this.$dateFormatter, this.$state.getSelectableDates(), this.$colors, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ p<Composer, Integer, t0> $headline;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $showModeToggle;
        final /* synthetic */ DateRangePickerState $state;
        final /* synthetic */ p<Composer, Integer, t0> $title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(DateRangePickerState dateRangePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, boolean z, DatePickerColors datePickerColors, int i10, int i11) {
            super(2);
            this.$state = dateRangePickerState;
            this.$modifier = modifier;
            this.$dateFormatter = datePickerFormatter;
            this.$title = pVar;
            this.$headline = pVar2;
            this.$showModeToggle = z;
            this.$colors = datePickerColors;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DateRangePickerKt.DateRangePicker(this.$state, this.$modifier, this.$dateFormatter, this.$title, this.$headline, this.$showModeToggle, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePickerContent$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05032 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
        final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05032(Long l10, Long l11, long j10, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$selectedStartDateMillis = l10;
            this.$selectedEndDateMillis = l11;
            this.$displayedMonthMillis = j10;
            this.$onDatesSelectionChange = pVar;
            this.$onDisplayedMonthChange = lVar;
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
            DateRangePickerKt.DateRangePickerContent(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$displayedMonthMillis, this.$onDatesSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ CalendarDate $today;
        final /* synthetic */ i $yearRange;

        /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C01001 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final C01001 INSTANCE = new C01001();

            /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class C01011 extends r implements r7.a<Float> {
                public static final C01011 INSTANCE = new C01011();

                public C01011() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass2 extends r implements r7.a<Float> {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                public AnonymousClass2() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            public C01001() {
                super(1);
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return t0.f22605a;
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(C01011.INSTANCE, AnonymousClass2.INSTANCE, false, 4, null));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Long l10, Long l11, p<? super Long, ? super Long, t0> pVar, LazyListState lazyListState, i iVar, CalendarModel calendarModel, CalendarMonth calendarMonth, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, CalendarDate calendarDate, SelectableDates selectableDates) {
            super(2);
            this.$selectedStartDateMillis = l10;
            this.$selectedEndDateMillis = l11;
            this.$onDatesSelectionChange = pVar;
            this.$lazyListState = lazyListState;
            this.$yearRange = iVar;
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
            this.$today = calendarDate;
            this.$selectableDates = selectableDates;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1090773432, i10, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous> (DateRangePicker.kt:780)");
            }
            Object objG = a0.c.g(773894976, composer, -492369756);
            Composer.Companion companion = Composer.INSTANCE;
            if (objG == companion.getEmpty()) {
                objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
            }
            composer.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composer.endReplaceableGroup();
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_scroll_to_previous_month), composer, 0);
            String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_scroll_to_next_month), composer, 0);
            composer.startReplaceableGroup(1645720805);
            boolean zChanged = composer.changed(this.$selectedStartDateMillis) | composer.changed(this.$selectedEndDateMillis) | composer.changed(this.$onDatesSelectionChange);
            Long l10 = this.$selectedStartDateMillis;
            Long l11 = this.$selectedEndDateMillis;
            p<Long, Long, t0> pVar = this.$onDatesSelectionChange;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new DateRangePickerKt$VerticalMonthsList$1$onDateSelectionChange$1$1(l10, l11, pVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            l lVar = (l) objRememberedValue;
            composer.endReplaceableGroup();
            List listCustomScrollActions = DateRangePickerKt.customScrollActions(this.$lazyListState, coroutineScope, strM1994getStringNWtq28, strM1994getStringNWtq282);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, C01001.INSTANCE, 1, null);
            LazyListState lazyListState = this.$lazyListState;
            composer.startReplaceableGroup(1645721776);
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changedInstance(this.$calendarModel) | composer.changed(this.$firstMonth) | composer.changedInstance(this.$dateFormatter) | composer.changedInstance(listCustomScrollActions) | composer.changed(this.$colors) | composer.changed(this.$selectedStartDateMillis) | composer.changed(this.$selectedEndDateMillis) | composer.changed(lVar) | composer.changed(this.$today) | composer.changed(this.$selectableDates);
            i iVar = this.$yearRange;
            CalendarModel calendarModel = this.$calendarModel;
            CalendarMonth calendarMonth = this.$firstMonth;
            Long l12 = this.$selectedStartDateMillis;
            Long l13 = this.$selectedEndDateMillis;
            CalendarDate calendarDate = this.$today;
            DatePickerFormatter datePickerFormatter = this.$dateFormatter;
            SelectableDates selectableDates = this.$selectableDates;
            DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                Object dateRangePickerKt$VerticalMonthsList$1$2$1 = new DateRangePickerKt$VerticalMonthsList$1$2$1(iVar, calendarModel, calendarMonth, l12, l13, lVar, calendarDate, datePickerFormatter, selectableDates, datePickerColors, listCustomScrollActions);
                composer.updateRememberedValue(dateRangePickerKt$VerticalMonthsList$1$2$1);
                objRememberedValue2 = dateRangePickerKt$VerticalMonthsList$1$2$1;
            }
            composer.endReplaceableGroup();
            LazyDslKt.LazyColumn(modifierSemantics$default, lazyListState, null, false, null, null, null, false, (l) objRememberedValue2, composer, 0, 252);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05043 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
        final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05043(LazyListState lazyListState, Long l10, Long l11, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$lazyListState = lazyListState;
            this.$selectedStartDateMillis = l10;
            this.$selectedEndDateMillis = l11;
            this.$onDatesSelectionChange = pVar;
            this.$onDisplayedMonthChange = lVar;
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
            DateRangePickerKt.VerticalMonthsList(this.$lazyListState, this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$onDatesSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    static {
        float f10 = 64;
        float f11 = 12;
        DateRangePickerTitlePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(f10), 0.0f, Dp.m5678constructorimpl(f11), 0.0f, 10, null);
        DateRangePickerHeadlinePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(f10), 0.0f, Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DateRangePicker(androidx.compose.material3.DateRangePickerState r24, androidx.compose.ui.Modifier r25, androidx.compose.material3.DatePickerFormatter r26, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, boolean r29, androidx.compose.material3.DatePickerColors r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerKt.DateRangePicker(androidx.compose.material3.DateRangePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.DatePickerFormatter, r7.p, r7.p, boolean, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DateRangePickerContent(Long l10, Long l11, long j10, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        Long l12;
        p<? super Long, ? super Long, t0> pVar2;
        l<? super Long, t0> lVar2;
        SelectableDates selectableDates2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-787063721);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            l12 = l11;
            i11 |= composerStartRestartGroup.changed(l12) ? 32 : 16;
        } else {
            l12 = l11;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            pVar2 = pVar;
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        } else {
            pVar2 = pVar;
        }
        if ((i10 & 24576) == 0) {
            lVar2 = lVar;
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 16384 : 8192;
        } else {
            lVar2 = lVar;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(iVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= (16777216 & i10) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i10) == 0) {
            selectableDates2 = selectableDates;
            i11 |= composerStartRestartGroup.changed(selectableDates2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i10 & 805306368) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 306783379) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-787063721, i11, -1, "androidx.compose.material3.DateRangePickerContent (DateRangePicker.kt:731)");
            }
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(calendarModel.getMonth(j10).indexIn(iVar), 0, composerStartRestartGroup, 0, 2);
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            DatePickerKt.WeekDays(datePickerColors, calendarModel, composerStartRestartGroup, ((i11 >> 27) & 14) | ((i11 >> 12) & 112));
            VerticalMonthsList(lazyListStateRememberLazyListState, l10, l12, pVar2, lVar2, calendarModel, iVar, datePickerFormatter, selectableDates2, datePickerColors, composerStartRestartGroup, ((i11 << 3) & AnalyticsListener.EVENT_AUDIO_DECODER_INITIALIZED) | (i11 & 7168) | (57344 & i11) | (458752 & i11) | (3670016 & i11) | (29360128 & i11) | (234881024 & i11) | (1879048192 & i11));
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05032(l10, l11, j10, pVar, lVar, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI, reason: not valid java name */
    public static final DateRangePickerState m1504DateRangePickerStateHVP43zI(Locale locale, Long l10, Long l11, Long l12, i iVar, int i10, SelectableDates selectableDates) {
        return new DateRangePickerStateImpl(l10, l11, l12, iVar, i10, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI$default, reason: not valid java name */
    public static /* synthetic */ DateRangePickerState m1505DateRangePickerStateHVP43zI$default(Locale locale, Long l10, Long l11, Long l12, i iVar, int i10, SelectableDates selectableDates, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            l10 = null;
        }
        if ((i11 & 4) != 0) {
            l11 = null;
        }
        if ((i11 & 8) != 0) {
            l12 = l10;
        }
        if ((i11 & 16) != 0) {
            iVar = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i11 & 32) != 0) {
            i10 = DisplayMode.INSTANCE.m1531getPickerjFl4v0();
        }
        if ((i11 & 64) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m1504DateRangePickerStateHVP43zI(locale, l10, l11, l12, iVar, i10, selectableDates);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-RN-2D1Q, reason: not valid java name */
    public static final void m1506SwitchableDateEntryContentRN2D1Q(Long l10, Long l11, long j10, int i10, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i11, int i12) {
        int i13;
        Long l12;
        long j11;
        p<? super Long, ? super Long, t0> pVar2;
        l<? super Long, t0> lVar2;
        CalendarModel calendarModel2;
        i iVar2;
        int i14;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-532789335);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            l12 = l11;
            i13 |= composerStartRestartGroup.changed(l12) ? 32 : 16;
        } else {
            l12 = l11;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j11 = j10;
            i13 |= composerStartRestartGroup.changed(j11) ? 256 : 128;
        } else {
            j11 = j10;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            pVar2 = pVar;
            i13 |= composerStartRestartGroup.changedInstance(pVar2) ? 16384 : 8192;
        } else {
            pVar2 = pVar;
        }
        if ((196608 & i11) == 0) {
            lVar2 = lVar;
            i13 |= composerStartRestartGroup.changedInstance(lVar2) ? 131072 : 65536;
        } else {
            lVar2 = lVar;
        }
        if ((1572864 & i11) == 0) {
            calendarModel2 = calendarModel;
            i13 |= composerStartRestartGroup.changedInstance(calendarModel2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((12582912 & i11) == 0) {
            iVar2 = iVar;
            i13 |= composerStartRestartGroup.changedInstance(iVar2) ? 8388608 : 4194304;
        } else {
            iVar2 = iVar;
        }
        if ((i11 & 100663296) == 0) {
            i13 |= (i11 & C.BUFFER_FLAG_FIRST_SAMPLE) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i13 |= composerStartRestartGroup.changed(selectableDates) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i12 & 6) == 0) {
            i14 = i12 | (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2);
        } else {
            i14 = i12;
        }
        if ((i13 & 306783379) == 306783378 && (i14 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-532789335, i13, i14, "androidx.compose.material3.SwitchableDateEntryContent (DateRangePicker.kt:680)");
            }
            composer2 = composerStartRestartGroup;
            CrossfadeKt.Crossfade(DisplayMode.m1523boximpl(i10), SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, DateRangePickerKt$SwitchableDateEntryContent$1.INSTANCE, 1, null), AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1026642619, true, new DateRangePickerKt$SwitchableDateEntryContent$2(l10, l12, j11, pVar2, lVar2, calendarModel2, iVar2, datePickerFormatter, selectableDates, datePickerColors)), composer2, ((i13 >> 9) & 14) | 24960, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new DateRangePickerKt$SwitchableDateEntryContent$3(l10, l11, j10, i10, pVar, lVar, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i11, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalMonthsList(LazyListState lazyListState, Long l10, Long l11, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        Long l12;
        Long l13;
        p<? super Long, ? super Long, t0> pVar2;
        Object dateRangePickerKt$VerticalMonthsList$2$1;
        LazyListState lazyListState2 = lazyListState;
        Composer composerStartRestartGroup = composer.startRestartGroup(1257365001);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(lazyListState2) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            l12 = l10;
            i11 |= composerStartRestartGroup.changed(l12) ? 32 : 16;
        } else {
            l12 = l10;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            l13 = l11;
            i11 |= composerStartRestartGroup.changed(l13) ? 256 : 128;
        } else {
            l13 = l11;
        }
        if ((i10 & 3072) == 0) {
            pVar2 = pVar;
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        } else {
            pVar2 = pVar;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(iVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= (16777216 & i10) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(selectableDates) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((805306368 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 306783379) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1257365001, i11, -1, "androidx.compose.material3.VerticalMonthsList (DateRangePicker.kt:769)");
            }
            CalendarDate today = calendarModel.getToday();
            composerStartRestartGroup.startReplaceableGroup(1454981403);
            boolean zChanged = composerStartRestartGroup.changed(iVar);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getMonth(iVar.f22619i, 1);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CalendarMonth calendarMonth = (CalendarMonth) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            int i12 = i11;
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), DatePickerModalTokens.INSTANCE.getDateLabelTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1090773432, true, new AnonymousClass1(l12, l13, pVar2, lazyListState2, iVar, calendarModel, calendarMonth, datePickerFormatter, datePickerColors, today, selectableDates)), composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(1454985957);
            int i13 = i12 & 14;
            boolean zChangedInstance = (i13 == 4) | ((57344 & i12) == 16384) | composerStartRestartGroup.changedInstance(calendarModel) | composerStartRestartGroup.changedInstance(iVar);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                dateRangePickerKt$VerticalMonthsList$2$1 = new DateRangePickerKt$VerticalMonthsList$2$1(lazyListState2, lVar, calendarModel, iVar, null);
                composerStartRestartGroup.updateRememberedValue(dateRangePickerKt$VerticalMonthsList$2$1);
            } else {
                dateRangePickerKt$VerticalMonthsList$2$1 = objRememberedValue2;
                lazyListState2 = lazyListState;
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(lazyListState2, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) dateRangePickerKt$VerticalMonthsList$2$1, composerStartRestartGroup, i13);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05043(lazyListState2, l10, l11, pVar, lVar, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(LazyListState lazyListState, k0 k0Var, String str, String str2) {
        return t7.a.E(new CustomAccessibilityAction(str, new DateRangePickerKt$customScrollActions$scrollUpAction$1(lazyListState, k0Var)), new CustomAccessibilityAction(str2, new DateRangePickerKt$customScrollActions$scrollDownAction$1(lazyListState, k0Var)));
    }

    /* JADX INFO: renamed from: drawRangeBackground-mxwnekA, reason: not valid java name */
    public static final void m1508drawRangeBackgroundmxwnekA(ContentDrawScope contentDrawScope, SelectedRangeInfo selectedRangeInfo, long j10) {
        float fMo285toPx0680j_4 = contentDrawScope.mo285toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float fMo285toPx0680j_42 = contentDrawScope.mo285toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float fMo285toPx0680j_43 = contentDrawScope.mo285toPx0680j_4(DatePickerModalTokens.INSTANCE.m2477getDateStateLayerHeightD9Ej5fM());
        float f10 = 2;
        float f11 = (fMo285toPx0680j_42 - fMo285toPx0680j_43) / f10;
        float f12 = 7;
        float fM3305getWidthimpl = (Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - (f12 * fMo285toPx0680j_4)) / f12;
        long gridStartCoordinates = selectedRangeInfo.getGridStartCoordinates();
        int iM5794component1impl = IntOffset.m5794component1impl(gridStartCoordinates);
        int iM5795component2impl = IntOffset.m5795component2impl(gridStartCoordinates);
        long gridEndCoordinates = selectedRangeInfo.getGridEndCoordinates();
        int iM5794component1impl2 = IntOffset.m5794component1impl(gridEndCoordinates);
        int iM5795component2impl2 = IntOffset.m5795component2impl(gridEndCoordinates);
        float f13 = iM5794component1impl;
        float f14 = fMo285toPx0680j_4 + fM3305getWidthimpl;
        float f15 = fM3305getWidthimpl / f10;
        float fM3305getWidthimpl2 = (f13 * f14) + (selectedRangeInfo.getFirstIsSelectionStart() ? fMo285toPx0680j_4 / f10 : 0.0f) + f15;
        float f16 = (iM5795component2impl * fMo285toPx0680j_42) + f11;
        float f17 = iM5794component1impl2 * f14;
        if (selectedRangeInfo.getLastIsSelectionEnd()) {
            fMo285toPx0680j_4 /= f10;
        }
        float fM3305getWidthimpl3 = f17 + fMo285toPx0680j_4 + f15;
        float f18 = (iM5795component2impl2 * fMo285toPx0680j_42) + f11;
        boolean z = contentDrawScope.getLayoutDirection() == LayoutDirection.Rtl;
        if (z) {
            fM3305getWidthimpl2 = Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - fM3305getWidthimpl2;
            fM3305getWidthimpl3 = Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - fM3305getWidthimpl3;
        }
        float fM3305getWidthimpl4 = fM3305getWidthimpl3;
        androidx.compose.ui.graphics.drawscope.c.K(contentDrawScope, j10, OffsetKt.Offset(fM3305getWidthimpl2, f16), SizeKt.Size(iM5795component2impl == iM5795component2impl2 ? fM3305getWidthimpl4 - fM3305getWidthimpl2 : z ? -fM3305getWidthimpl2 : Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - fM3305getWidthimpl2, fMo285toPx0680j_43), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        if (iM5795component2impl != iM5795component2impl2) {
            for (int i10 = (iM5795component2impl2 - iM5795component2impl) - 1; i10 > 0; i10--) {
                androidx.compose.ui.graphics.drawscope.c.K(contentDrawScope, j10, OffsetKt.Offset(0.0f, (i10 * fMo285toPx0680j_42) + f16), SizeKt.Size(Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()), fMo285toPx0680j_43), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
            }
            long jOffset = OffsetKt.Offset(contentDrawScope.getLayoutDirection() == LayoutDirection.Ltr ? 0.0f : Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()), f18);
            if (z) {
                fM3305getWidthimpl4 -= Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc());
            }
            androidx.compose.ui.graphics.drawscope.c.K(contentDrawScope, j10, jOffset, SizeKt.Size(fM3305getWidthimpl4, fMo285toPx0680j_43), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        }
    }

    public static final PaddingValues getCalendarMonthSubheadPadding() {
        return CalendarMonthSubheadPadding;
    }

    /* JADX INFO: renamed from: rememberDateRangePickerState-IlFM19s, reason: not valid java name */
    public static final DateRangePickerState m1509rememberDateRangePickerStateIlFM19s(Long l10, Long l11, Long l12, i iVar, int i10, SelectableDates selectableDates, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-2012087461);
        Long l13 = (i12 & 1) != 0 ? null : l10;
        Long l14 = (i12 & 2) != 0 ? null : l11;
        Long l15 = (i12 & 4) != 0 ? l13 : l12;
        i yearRange = (i12 & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : iVar;
        int iM1531getPickerjFl4v0 = (i12 & 16) != 0 ? DisplayMode.INSTANCE.m1531getPickerjFl4v0() : i10;
        SelectableDates allDates = (i12 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2012087461, i11, -1, "androidx.compose.material3.rememberDateRangePickerState (DateRangePicker.kt:261)");
        }
        Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composer, 0);
        Object[] objArr = new Object[0];
        Saver<DateRangePickerStateImpl, Object> Saver = DateRangePickerStateImpl.INSTANCE.Saver(allDates, localeDefaultLocale);
        composer.startReplaceableGroup(269010268);
        boolean zChangedInstance = ((((i11 & 112) ^ 48) > 32 && composer.changed(l14)) || (i11 & 48) == 32) | ((((i11 & 14) ^ 6) > 4 && composer.changed(l13)) || (i11 & 6) == 4) | ((((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(l15)) || (i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer.changedInstance(yearRange) | ((((57344 & i11) ^ 24576) > 16384 && composer.changed(iM1531getPickerjFl4v0)) || (i11 & 24576) == 16384) | ((((458752 & i11) ^ 196608) > 131072 && composer.changed(allDates)) || (i11 & 196608) == 131072) | composer.changedInstance(localeDefaultLocale);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object dateRangePickerKt$rememberDateRangePickerState$1$1 = new DateRangePickerKt$rememberDateRangePickerState$1$1(l13, l14, l15, yearRange, iM1531getPickerjFl4v0, allDates, localeDefaultLocale);
            composer.updateRememberedValue(dateRangePickerKt$rememberDateRangePickerState$1$1);
            objRememberedValue = dateRangePickerKt$rememberDateRangePickerState$1$1;
        }
        composer.endReplaceableGroup();
        DateRangePickerStateImpl dateRangePickerStateImpl = (DateRangePickerStateImpl) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return dateRangePickerStateImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDateSelection(long j10, Long l10, Long l11, p<? super Long, ? super Long, t0> pVar) {
        if ((l10 == null && l11 == null) || (l10 != null && l11 != null)) {
            pVar.invoke(Long.valueOf(j10), null);
        } else if (l10 == null || j10 < l10.longValue()) {
            pVar.invoke(Long.valueOf(j10), null);
        } else {
            pVar.invoke(l10, Long.valueOf(j10));
        }
    }
}
