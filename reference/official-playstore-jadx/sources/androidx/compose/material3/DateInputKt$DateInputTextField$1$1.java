package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.input.TextFieldValue;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/input/TextFieldValue;", "input", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateInputKt$DateInputTextField$1$1 extends r implements l<TextFieldValue, t0> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DateInputFormat $dateInputFormat;
    final /* synthetic */ DateInputValidator $dateInputValidator;
    final /* synthetic */ MutableState<String> $errorText;
    final /* synthetic */ int $inputIdentifier;
    final /* synthetic */ Locale $locale;
    final /* synthetic */ l<Long, t0> $onDateSelectionChange;
    final /* synthetic */ MutableState<TextFieldValue> $text$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateInputKt$DateInputTextField$1$1(DateInputFormat dateInputFormat, MutableState<String> mutableState, l<? super Long, t0> lVar, CalendarModel calendarModel, DateInputValidator dateInputValidator, int i10, Locale locale, MutableState<TextFieldValue> mutableState2) {
        super(1);
        this.$dateInputFormat = dateInputFormat;
        this.$errorText = mutableState;
        this.$onDateSelectionChange = lVar;
        this.$calendarModel = calendarModel;
        this.$dateInputValidator = dateInputValidator;
        this.$inputIdentifier = i10;
        this.$locale = locale;
        this.$text$delegate = mutableState2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextFieldValue) obj);
        return t0.f22605a;
    }

    public final void invoke(TextFieldValue textFieldValue) {
        if (textFieldValue.getText().length() <= this.$dateInputFormat.getPatternWithoutDelimiters().length()) {
            String text = textFieldValue.getText();
            for (int i10 = 0; i10 < text.length(); i10++) {
                if (!Character.isDigit(text.charAt(i10))) {
                    return;
                }
            }
            this.$text$delegate.setValue(textFieldValue);
            String string = o.L0(textFieldValue.getText()).toString();
            Long lValueOf = null;
            if (string.length() == 0 || string.length() < this.$dateInputFormat.getPatternWithoutDelimiters().length()) {
                this.$errorText.setValue("");
                this.$onDateSelectionChange.invoke(null);
                return;
            }
            CalendarDate calendarDate = this.$calendarModel.parse(string, this.$dateInputFormat.getPatternWithoutDelimiters());
            this.$errorText.setValue(this.$dateInputValidator.m1448validateXivgLIo(calendarDate, this.$inputIdentifier, this.$locale));
            l<Long, t0> lVar = this.$onDateSelectionChange;
            if (this.$errorText.getValue().length() == 0 && calendarDate != null) {
                lValueOf = Long.valueOf(calendarDate.getUtcTimeMillis());
            }
            lVar.invoke(lValueOf);
        }
    }
}
