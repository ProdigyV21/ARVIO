package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.TimePickerState", f = "TimePicker.kt", l = {703, 704}, m = "animateToCurrent$material3_release")
public final class TimePickerState$animateToCurrent$1 extends f7.c {
    float F$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerState$animateToCurrent$1(TimePickerState timePickerState, d7.d<? super TimePickerState$animateToCurrent$1> dVar) {
        super(dVar);
        this.this$0 = timePickerState;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.animateToCurrent$material3_release(this);
    }
}
