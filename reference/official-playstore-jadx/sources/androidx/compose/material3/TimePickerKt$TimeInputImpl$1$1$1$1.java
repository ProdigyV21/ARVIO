package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "invoke-ZmokQxo", "(Landroid/view/KeyEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt$TimeInputImpl$1$1$1$1 extends r implements l<KeyEvent, Boolean> {
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ TimePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimeInputImpl$1$1$1$1(TimePickerState timePickerState, MutableState<TextFieldValue> mutableState) {
        super(1);
        this.$state = timePickerState;
        this.$hourValue$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m2191invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
    }

    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
    public final Boolean m2191invokeZmokQxo(android.view.KeyEvent keyEvent) {
        int iM4403getUtf16CodePointZmokQxo = KeyEvent_androidKt.m4403getUtf16CodePointZmokQxo(keyEvent);
        if (48 <= iM4403getUtf16CodePointZmokQxo && iM4403getUtf16CodePointZmokQxo < 58 && TextRange.m5192getStartimpl(TimePickerKt.TimeInputImpl$lambda$5(this.$hourValue$delegate).getSelection()) == 2 && TimePickerKt.TimeInputImpl$lambda$5(this.$hourValue$delegate).getText().length() == 2) {
            this.$state.m2207setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1870getMinuteJiIwxys());
        }
        return Boolean.FALSE;
    }
}
