package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.Selection;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TimePickerKt$TimeInputImpl$1$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ TimePickerColors $colors;
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ MutableState<TextFieldValue> $minuteValue$delegate;
    final /* synthetic */ TimePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimeInputImpl$1$1(MutableState<TextFieldValue> mutableState, TimePickerState timePickerState, TimePickerColors timePickerColors, MutableState<TextFieldValue> mutableState2) {
        super(2);
        this.$hourValue$delegate = mutableState;
        this.$state = timePickerState;
        this.$colors = timePickerColors;
        this.$minuteValue$delegate = mutableState2;
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
            ComposerKt.traceEventStart(1306700887, i10, -1, "androidx.compose.material3.TimeInputImpl.<anonymous>.<anonymous> (TimePicker.kt:837)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        composer.startReplaceableGroup(-1645133303);
        boolean zChanged = composer.changed(this.$hourValue$delegate) | composer.changed(this.$state);
        TimePickerState timePickerState = this.$state;
        MutableState<TextFieldValue> mutableState = this.$hourValue$delegate;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TimePickerKt$TimeInputImpl$1$1$1$1(timePickerState, mutableState);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (l) objRememberedValue);
        TextFieldValue textFieldValueTimeInputImpl$lambda$5 = TimePickerKt.TimeInputImpl$lambda$5(this.$hourValue$delegate);
        composer.startReplaceableGroup(-1645132823);
        boolean zChanged2 = composer.changed(this.$state) | composer.changed(this.$hourValue$delegate);
        TimePickerState timePickerState2 = this.$state;
        MutableState<TextFieldValue> mutableState2 = this.$hourValue$delegate;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new TimePickerKt$TimeInputImpl$1$1$2$1(timePickerState2, mutableState2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        l lVar = (l) objRememberedValue2;
        composer.endReplaceableGroup();
        TimePickerState timePickerState3 = this.$state;
        Selection.Companion companion2 = Selection.INSTANCE;
        int iM1869getHourJiIwxys = companion2.m1869getHourJiIwxys();
        ImeAction.Companion companion3 = ImeAction.INSTANCE;
        int iM5370getNexteUduSuo = companion3.m5370getNexteUduSuo();
        KeyboardType.Companion companion4 = KeyboardType.INSTANCE;
        KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, companion4.m5418getNumberPjHm6EE(), iM5370getNexteUduSuo, null, 19, null);
        composer.startReplaceableGroup(-1645132161);
        boolean zChanged3 = composer.changed(this.$state);
        TimePickerState timePickerState4 = this.$state;
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new TimePickerKt$TimeInputImpl$1$1$3$1(timePickerState4);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        TimePickerKt.m2181TimePickerTextFieldlxUZ_iY(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$5, lVar, timePickerState3, iM1869getHourJiIwxys, keyboardOptions, new KeyboardActions(null, null, (l) objRememberedValue3, null, null, null, 59, null), this.$colors, composer, 24576, 0);
        TimePickerKt.DisplaySeparator(SizeKt.m580sizeVpY3zN4(companion, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2862getPeriodSelectorContainerHeightD9Ej5fM()), composer, 6);
        composer.startReplaceableGroup(-1645131867);
        boolean zChanged4 = composer.changed(this.$minuteValue$delegate) | composer.changed(this.$state);
        TimePickerState timePickerState5 = this.$state;
        MutableState<TextFieldValue> mutableState3 = this.$minuteValue$delegate;
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new TimePickerKt$TimeInputImpl$1$1$4$1(timePickerState5, mutableState3);
            composer.updateRememberedValue(objRememberedValue4);
        }
        composer.endReplaceableGroup();
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(companion, (l) objRememberedValue4);
        TextFieldValue textFieldValueTimeInputImpl$lambda$8 = TimePickerKt.TimeInputImpl$lambda$8(this.$minuteValue$delegate);
        composer.startReplaceableGroup(-1645131419);
        boolean zChanged5 = composer.changed(this.$state) | composer.changed(this.$minuteValue$delegate);
        TimePickerState timePickerState6 = this.$state;
        MutableState<TextFieldValue> mutableState4 = this.$minuteValue$delegate;
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new TimePickerKt$TimeInputImpl$1$1$5$1(timePickerState6, mutableState4);
            composer.updateRememberedValue(objRememberedValue5);
        }
        l lVar2 = (l) objRememberedValue5;
        composer.endReplaceableGroup();
        TimePickerState timePickerState7 = this.$state;
        int iM1870getMinuteJiIwxys = companion2.m1870getMinuteJiIwxys();
        KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, false, companion4.m5418getNumberPjHm6EE(), companion3.m5368getDoneeUduSuo(), null, 19, null);
        composer.startReplaceableGroup(-1645130777);
        boolean zChanged6 = composer.changed(this.$state);
        TimePickerState timePickerState8 = this.$state;
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new TimePickerKt$TimeInputImpl$1$1$6$1(timePickerState8);
            composer.updateRememberedValue(objRememberedValue6);
        }
        composer.endReplaceableGroup();
        TimePickerKt.m2181TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$8, lVar2, timePickerState7, iM1870getMinuteJiIwxys, keyboardOptions2, new KeyboardActions(null, null, (l) objRememberedValue6, null, null, null, 59, null), this.$colors, composer, 24576, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
