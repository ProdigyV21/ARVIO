package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/text/input/TextFieldValue;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt$TimeInputImpl$hourValue$2$1 extends r implements r7.a<MutableState<TextFieldValue>> {
    final /* synthetic */ TimePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimeInputImpl$hourValue$2$1(TimePickerState timePickerState) {
        super(0);
        this.$state = timePickerState;
    }

    @Override // r7.a
    public final MutableState<TextFieldValue> invoke() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(ActualJvm_jvmKt.toLocalString$default(this.$state.getHourForDisplay$material3_release(), 2, 0, false, 6, null), 0L, (TextRange) null, 6, (kotlin.jvm.internal.h) null), null, 2, null);
    }
}
