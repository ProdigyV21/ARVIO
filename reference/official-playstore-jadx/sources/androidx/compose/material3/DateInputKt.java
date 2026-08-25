package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import io.ktor.http.ContentDisposition;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001af\u0010\u0012\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0098\u0001\u0010&\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00002\u0014\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u00060\u00022\u0006\u0010\t\u001a\u00020\b2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0017¢\u0006\u0002\b\u00182\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0017¢\u0006\u0002\b\u00182\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\n\u0010#\u001a\u00060!j\u0002`\"2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%\"\u001a\u0010(\u001a\u00020'8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\u000e\u00100\u001a\u00020/8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "selectedDateMillis", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "dateInMillis", "Lx6/t0;", "onDateSelectionChange", "Landroidx/compose/material3/CalendarModel;", "calendarModel", "Lx7/i;", "yearRange", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "Landroidx/compose/material3/SelectableDates;", "selectableDates", "Landroidx/compose/material3/DatePickerColors;", "colors", "DateInputContent", "(Ljava/lang/Long;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/Modifier;", "modifier", "initialDateMillis", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "label", "placeholder", "Landroidx/compose/material3/InputIdentifier;", "inputIdentifier", "Landroidx/compose/material3/DateInputValidator;", "dateInputValidator", "Landroidx/compose/material3/DateInputFormat;", "dateInputFormat", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "DateInputTextField-tQNruF0", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lr7/l;Landroidx/compose/material3/CalendarModel;Lr7/p;Lr7/p;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "DateInputTextField", "Landroidx/compose/foundation/layout/PaddingValues;", "InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "Landroidx/compose/ui/unit/Dp;", "InputTextNonErroneousBottomPadding", "F", "Landroidx/compose/ui/text/input/TextFieldValue;", "text", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding;
    private static final float InputTextNonErroneousBottomPadding = Dp.m5678constructorimpl(16);

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ String $labelText;
        final /* synthetic */ String $pattern;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2) {
            super(2);
            this.$labelText = str;
            this.$pattern = str2;
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
                ComposerKt.traceEventStart(-1819015125, i10, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:84)");
            }
            String str = this.$labelText;
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(-694340528);
            boolean zChanged = composer.changed(this.$labelText) | composer.changed(this.$pattern);
            String str2 = this.$labelText;
            String str3 = this.$pattern;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DateInputKt$DateInputContent$2$1$1(str2, str3);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            TextKt.m2153Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ String $pattern;

        /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
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
            this.$pattern = str;
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
                ComposerKt.traceEventStart(-564233108, i10, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:88)");
            }
            TextKt.m2153Text4IGK_g(this.$pattern, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, AnonymousClass1.INSTANCE), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Long l10, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$selectedDateMillis = l10;
            this.$onDateSelectionChange = lVar;
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
            DateInputKt.DateInputContent(this.$selectedDateMillis, this.$onDateSelectionChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    static {
        float f10 = 24;
        InputTextFieldPadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(10), Dp.m5678constructorimpl(f10), 0.0f, 8, null);
    }

    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [boolean, int] */
    public static final void DateInputContent(Long l10, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Object dateInputValidator;
        DateInputFormat dateInputFormat;
        Locale locale;
        int i12;
        ?? r22;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(643325609);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(iVar) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= (i10 & 32768) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(selectableDates) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((599187 & i11) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(643325609, i11, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:55)");
            }
            Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-356766397);
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
            composerStartRestartGroup.startReplaceableGroup(-356766049);
            boolean zChanged2 = ((i11 & 57344) == 16384 || ((i11 & 32768) != 0 && composerStartRestartGroup.changed(datePickerFormatter))) | composerStartRestartGroup.changed(dateInputFormat2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer2 = composerStartRestartGroup;
                dateInputFormat = dateInputFormat2;
                locale = localeDefaultLocale;
                i12 = i11;
                r22 = 1;
                dateInputValidator = new DateInputValidator(iVar, selectableDates, dateInputFormat, datePickerFormatter, strM1994getStringNWtq28, strM1994getStringNWtq282, strM1994getStringNWtq283, "", null, null, 768, null);
                composer2.updateRememberedValue(dateInputValidator);
            } else {
                composer2 = composerStartRestartGroup;
                i12 = i11;
                dateInputFormat = dateInputFormat2;
                locale = localeDefaultLocale;
                dateInputValidator = objRememberedValue2;
                r22 = 1;
            }
            DateInputValidator dateInputValidator2 = (DateInputValidator) dateInputValidator;
            composer2.endReplaceableGroup();
            String upperCase = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            String strM1994getStringNWtq284 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_label), composer2, 0);
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r22, null), InputTextFieldPadding);
            int iM1632getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m1632getSingleDateInputJ2x2o4M();
            dateInputValidator2.setCurrentStartDateMillis$material3_release(l10);
            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda(composer2, -1819015125, r22, new AnonymousClass2(strM1994getStringNWtq284, upperCase));
            ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda(composer2, -564233108, r22, new AnonymousClass3(upperCase));
            int i13 = i12 << 3;
            Composer composer4 = composer2;
            m1447DateInputTextFieldtQNruF0(modifierPadding, l10, lVar, calendarModel, composableLambda, composableLambda2, iM1632getSingleDateInputJ2x2o4M, dateInputValidator2, dateInputFormat, locale, datePickerColors, composer4, (i13 & 112) | 1794054 | (i13 & 896) | (i13 & 7168), (i12 >> 18) & 14);
            composer3 = composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(l10, lVar, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }

    /* JADX INFO: renamed from: DateInputTextField-tQNruF0, reason: not valid java name */
    public static final void m1447DateInputTextFieldtQNruF0(Modifier modifier, Long l10, l<? super Long, t0> lVar, CalendarModel calendarModel, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, int i10, DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, Locale locale, DatePickerColors datePickerColors, Composer composer, int i11, int i12) {
        int i13;
        l<? super Long, t0> lVar2;
        int i14;
        MutableState mutableState;
        int i15;
        Composer composer2;
        DateInputFormat dateInputFormat2 = dateInputFormat;
        Composer composerStartRestartGroup = composer.startRestartGroup(-857008589);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(l10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            lVar2 = lVar;
            i13 |= composerStartRestartGroup.changedInstance(lVar2) ? 256 : 128;
        } else {
            lVar2 = lVar;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar2) ? 131072 : 65536;
        }
        if ((i11 & 1572864) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i13 |= composerStartRestartGroup.changed(dateInputValidator) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i13 |= composerStartRestartGroup.changed(dateInputFormat2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(locale) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
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
                ComposerKt.traceEventStart(-857008589, i13, i14, "androidx.compose.material3.DateInputTextField (DateInput.kt:116)");
            }
            MutableState mutableState2 = (MutableState) RememberSaveableKt.m3084rememberSaveable(new Object[0], (Saver) null, (String) null, (r7.a) DateInputKt$DateInputTextField$errorText$1.INSTANCE, composerStartRestartGroup, 3072, 6);
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            composerStartRestartGroup.startReplaceableGroup(1947288557);
            boolean zChangedInstance = ((i13 & 112) == 32) | composerStartRestartGroup.changedInstance(calendarModel);
            int i16 = 234881024 & i13;
            boolean zChangedInstance2 = zChangedInstance | (i16 == 67108864) | composerStartRestartGroup.changedInstance(locale);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DateInputKt$DateInputTextField$text$2$1(l10, calendarModel, dateInputFormat2, locale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableStateRememberSaveable = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composerStartRestartGroup, 0, 4);
            TextFieldValue textFieldValueDateInputTextField_tQNruF0$lambda$4 = DateInputTextField_tQNruF0$lambda$4(mutableStateRememberSaveable);
            composerStartRestartGroup.startReplaceableGroup(1947289016);
            boolean zChanged = (i16 == 67108864) | composerStartRestartGroup.changed(mutableStateRememberSaveable) | composerStartRestartGroup.changed(mutableState2) | ((i13 & 896) == 256) | composerStartRestartGroup.changedInstance(calendarModel) | ((29360128 & i13) == 8388608) | ((3670016 & i13) == 1048576) | composerStartRestartGroup.changedInstance(locale);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState2;
                i15 = i13;
                DateInputKt$DateInputTextField$1$1 dateInputKt$DateInputTextField$1$1 = new DateInputKt$DateInputTextField$1$1(dateInputFormat2, mutableState, lVar2, calendarModel, dateInputValidator, i10, locale, mutableStateRememberSaveable);
                dateInputFormat2 = dateInputFormat2;
                composerStartRestartGroup.updateRememberedValue(dateInputKt$DateInputTextField$1$1);
                objRememberedValue2 = dateInputKt$DateInputTextField$1$1;
            } else {
                mutableState = mutableState2;
                i15 = i13;
            }
            l lVar3 = (l) objRememberedValue2;
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, !o.h0((CharSequence) mutableState.getValue()) ? Dp.m5678constructorimpl(0) : InputTextNonErroneousBottomPadding, 7, null);
            composerStartRestartGroup.startReplaceableGroup(1947290848);
            boolean zChanged2 = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new DateInputKt$DateInputTextField$2$1(mutableState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            OutlinedTextFieldKt.OutlinedTextField(textFieldValueDateInputTextField_tQNruF0$lambda$4, (l<? super TextFieldValue, t0>) lVar3, SemanticsModifierKt.semantics$default(modifierM533paddingqDBjuR0$default, false, (l) objRememberedValue3, 1, null), false, false, (TextStyle) null, pVar, pVar2, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) ComposableLambdaKt.composableLambda(composerStartRestartGroup, -591991974, true, new DateInputKt$DateInputTextField$3(mutableState)), !o.h0((CharSequence) mutableState.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5418getNumberPjHm6EE(), ImeAction.INSTANCE.m5368getDoneeUduSuo(), null, 17, null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, datePickerColors.getDateTextFieldColors(), composerStartRestartGroup, (i15 << 6) & 33030144, 12779904, 0, 4001592);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new DateInputKt$DateInputTextField$4(modifier, l10, lVar, calendarModel, pVar, pVar2, i10, dateInputValidator, dateInputFormat2, locale, datePickerColors, i11, i12));
        }
    }

    private static final TextFieldValue DateInputTextField_tQNruF0$lambda$4(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }
}
