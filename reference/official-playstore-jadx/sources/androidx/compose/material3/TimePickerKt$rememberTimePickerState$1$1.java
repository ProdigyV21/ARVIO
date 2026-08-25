package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material3/TimePickerState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt$rememberTimePickerState$1$1 extends r implements r7.a<TimePickerState> {
    final /* synthetic */ int $initialHour;
    final /* synthetic */ int $initialMinute;
    final /* synthetic */ boolean $is24Hour;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$rememberTimePickerState$1$1(int i10, int i11, boolean z) {
        super(0);
        this.$initialHour = i10;
        this.$initialMinute = i11;
        this.$is24Hour = z;
    }

    @Override // r7.a
    public final TimePickerState invoke() {
        return new TimePickerState(this.$initialHour, this.$initialMinute, this.$is24Hour);
    }
}
