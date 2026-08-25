package androidx.compose.material3;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ List<CustomAccessibilityAction> $customAccessibilityAction;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ CalendarMonth $month;

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1512invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1512invoke();
            return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1(DatePickerFormatter datePickerFormatter, CalendarMonth calendarMonth, List<CustomAccessibilityAction> list, DatePickerColors datePickerColors) {
        super(2);
        this.$dateFormatter = datePickerFormatter;
        this.$month = calendarMonth;
        this.$customAccessibilityAction = list;
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
            ComposerKt.traceEventStart(1622100276, i10, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:825)");
        }
        String monthYear = this.$dateFormatter.formatMonthYear(Long.valueOf(this.$month.getStartUtcTimeMillis()), ActualAndroid_androidKt.defaultLocale(composer, 0));
        if (monthYear == null) {
            monthYear = "-";
        }
        Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.getCalendarMonthSubheadPadding()), false, null, null, AnonymousClass1.INSTANCE, 7, null);
        composer.startReplaceableGroup(413139989);
        boolean zChangedInstance = composer.changedInstance(this.$customAccessibilityAction);
        List<CustomAccessibilityAction> list = this.$customAccessibilityAction;
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1$2$1(list);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TextKt.m2153Text4IGK_g(monthYear, SemanticsModifierKt.semantics$default(modifierM214clickableXHw0xAI$default, false, (l) objRememberedValue, 1, null), this.$colors.getSubheadContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 131064);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
