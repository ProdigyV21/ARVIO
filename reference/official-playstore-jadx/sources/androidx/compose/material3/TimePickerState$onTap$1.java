package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.TimePickerState", f = "TimePicker.kt", l = {738, 746, 747, 750}, m = "onTap$material3_release")
public final class TimePickerState$onTap$1 extends f7.c {
    float F$0;
    float F$1;
    float F$2;
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerState$onTap$1(TimePickerState timePickerState, d7.d<? super TimePickerState$onTap$1> dVar) {
        super(dVar);
        this.this$0 = timePickerState;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onTap$material3_release(0.0f, 0.0f, 0.0f, false, this);
    }
}
