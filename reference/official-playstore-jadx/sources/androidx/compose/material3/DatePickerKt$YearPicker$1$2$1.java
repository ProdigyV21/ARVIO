package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.material3.Strings;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$YearPicker$1$2$1 extends r implements l<LazyGridScope, t0> {
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ int $currentYear;
    final /* synthetic */ int $displayedYear;
    final /* synthetic */ LazyGridState $lazyGridState;
    final /* synthetic */ l<Integer, t0> $onYearSelected;
    final /* synthetic */ String $scrollToEarlierYearsLabel;
    final /* synthetic */ String $scrollToLaterYearsLabel;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ i $yearRange;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.r<LazyGridItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ k0 $coroutineScope;
        final /* synthetic */ int $currentYear;
        final /* synthetic */ int $displayedYear;
        final /* synthetic */ LazyGridState $lazyGridState;
        final /* synthetic */ l<Integer, t0> $onYearSelected;
        final /* synthetic */ String $scrollToEarlierYearsLabel;
        final /* synthetic */ String $scrollToLaterYearsLabel;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ i $yearRange;

        /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ String $localizedYear;

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1$1$3$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00991 extends r implements l<SemanticsPropertyReceiver, t0> {
                public static final C00991 INSTANCE = new C00991();

                public C00991() {
                    super(1);
                }

                public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((SemanticsPropertyReceiver) obj);
                    return t0.f22605a;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(String str) {
                super(2);
                this.$localizedYear = str;
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
                    ComposerKt.traceEventStart(882189459, i10, -1, "androidx.compose.material3.YearPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2062)");
                }
                TextKt.m2153Text4IGK_g(this.$localizedYear, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, C00991.INSTANCE), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 130556);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(i iVar, LazyGridState lazyGridState, k0 k0Var, String str, String str2, int i10, int i11, l<? super Integer, t0> lVar, SelectableDates selectableDates, DatePickerColors datePickerColors) {
            super(4);
            this.$yearRange = iVar;
            this.$lazyGridState = lazyGridState;
            this.$coroutineScope = k0Var;
            this.$scrollToEarlierYearsLabel = str;
            this.$scrollToLaterYearsLabel = str2;
            this.$displayedYear = i10;
            this.$currentYear = i11;
            this.$onYearSelected = lVar;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyGridItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyGridItemScope lazyGridItemScope, int i10, Composer composer, int i11) {
            int i12;
            if ((i11 & 48) == 0) {
                i12 = i11 | (composer.changed(i10) ? 32 : 16);
            } else {
                i12 = i11;
            }
            if ((i12 & 145) == 144 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1040623618, i12, -1, "androidx.compose.material3.YearPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2029)");
            }
            int i13 = i10 + this.$yearRange.f22619i;
            String localString$default = ActualJvm_jvmKt.toLocalString$default(i13, 0, 0, false, 7, null);
            Modifier.Companion companion = Modifier.INSTANCE;
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            Modifier modifierM572requiredSizeVpY3zN4 = SizeKt.m572requiredSizeVpY3zN4(companion, datePickerModalTokens.m2486getSelectionYearContainerWidthD9Ej5fM(), datePickerModalTokens.m2485getSelectionYearContainerHeightD9Ej5fM());
            composer.startReplaceableGroup(-1669466775);
            boolean zChanged = ((i12 & 112) == 32) | composer.changed(this.$lazyGridState) | composer.changedInstance(this.$coroutineScope) | composer.changed(this.$scrollToEarlierYearsLabel) | composer.changed(this.$scrollToLaterYearsLabel);
            LazyGridState lazyGridState = this.$lazyGridState;
            k0 k0Var = this.$coroutineScope;
            String str = this.$scrollToEarlierYearsLabel;
            String str2 = this.$scrollToLaterYearsLabel;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                DatePickerKt$YearPicker$1$2$1$1$1$1 datePickerKt$YearPicker$1$2$1$1$1$1 = new DatePickerKt$YearPicker$1$2$1$1$1$1(lazyGridState, i10, k0Var, str, str2);
                composer.updateRememberedValue(datePickerKt$YearPicker$1$2$1$1$1$1);
                objRememberedValue = datePickerKt$YearPicker$1$2$1$1$1$1;
            }
            composer.endReplaceableGroup();
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM572requiredSizeVpY3zN4, false, (l) objRememberedValue, 1, null);
            boolean z = i13 == this.$displayedYear;
            boolean z5 = i13 == this.$currentYear;
            composer.startReplaceableGroup(-1669465643);
            boolean zChanged2 = composer.changed(this.$onYearSelected) | composer.changed(i13);
            l<Integer, t0> lVar = this.$onYearSelected;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new DatePickerKt$YearPicker$1$2$1$1$2$1(lVar, i13);
                composer.updateRememberedValue(objRememberedValue2);
            }
            r7.a aVar = (r7.a) objRememberedValue2;
            composer.endReplaceableGroup();
            boolean zIsSelectableYear = this.$selectableDates.isSelectableYear(i13);
            Strings.Companion companion2 = Strings.INSTANCE;
            DatePickerKt.Year(modifierSemantics$default, z, z5, aVar, zIsSelectableYear, String.format(Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_navigate_to_year_description), composer, 0), Arrays.copyOf(new Object[]{localString$default}, 1)), this.$colors, ComposableLambdaKt.composableLambda(composer, 882189459, true, new AnonymousClass3(localString$default)), composer, 12582912);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$YearPicker$1$2$1(i iVar, LazyGridState lazyGridState, k0 k0Var, String str, String str2, int i10, int i11, l<? super Integer, t0> lVar, SelectableDates selectableDates, DatePickerColors datePickerColors) {
        super(1);
        this.$yearRange = iVar;
        this.$lazyGridState = lazyGridState;
        this.$coroutineScope = k0Var;
        this.$scrollToEarlierYearsLabel = str;
        this.$scrollToLaterYearsLabel = str2;
        this.$displayedYear = i10;
        this.$currentYear = i11;
        this.$onYearSelected = lVar;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LazyGridScope) obj);
        return t0.f22605a;
    }

    public final void invoke(LazyGridScope lazyGridScope) {
        int size;
        Iterable iterable = this.$yearRange;
        if (iterable instanceof Collection) {
            size = ((Collection) iterable).size();
        } else {
            Iterator it = iterable.iterator();
            size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
                if (size < 0) {
                    t7.a.P();
                    throw null;
                }
            }
        }
        LazyGridScope.CC.b(lazyGridScope, size, null, null, null, ComposableLambdaKt.composableLambdaInstance(1040623618, true, new AnonymousClass1(this.$yearRange, this.$lazyGridState, this.$coroutineScope, this.$scrollToEarlierYearsLabel, this.$scrollToLaterYearsLabel, this.$displayedYear, this.$currentYear, this.$onYearSelected, this.$selectableDates, this.$colors)), 14, null);
    }
}
