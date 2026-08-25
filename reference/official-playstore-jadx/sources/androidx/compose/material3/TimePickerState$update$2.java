package androidx.compose.material3;

import f7.j;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.TimePickerState$update$2", f = "TimePicker.kt", l = {689, 691}, m = "invokeSuspend")
public final class TimePickerState$update$2 extends j implements l<d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $fromTap;
    final /* synthetic */ float $value;
    int label;
    final /* synthetic */ TimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerState$update$2(TimePickerState timePickerState, float f10, boolean z, d7.d<? super TimePickerState$update$2> dVar) {
        super(1, dVar);
        this.this$0 = timePickerState;
        this.$value = f10;
        this.$fromTap = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new TimePickerState$update$2(this.this$0, this.$value, this.$fromTap, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super t0> dVar) {
        return ((TimePickerState$update$2) create(dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008a, code lost:
    
        if (r7.snapTo(r3, r6) == r0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a6, code lost:
    
        if (r7.snapTo(r3, r6) == r0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a8, code lost:
    
        return r0;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L18
            if (r0 == r2) goto L13
            if (r0 != r1) goto Lb
            goto L13
        Lb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L13:
            k2.c.G(r7)
            goto La9
        L18:
            k2.c.G(r7)
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            int r7 = r7.m2204getSelectionJiIwxys$material3_release()
            androidx.compose.material3.Selection$Companion r0 = androidx.compose.material3.Selection.INSTANCE
            int r0 = r0.m1869getHourJiIwxys()
            boolean r7 = androidx.compose.material3.Selection.m1865equalsimpl0(r7, r0)
            if (r7 == 0) goto L40
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            float r0 = r6.$value
            int r0 = androidx.compose.material3.TimePickerState.access$toHour(r7, r0)
            int r0 = r0 % 12
            float r0 = (float) r0
            r3 = 1057360530(0x3f060a92, float:0.5235988)
            float r0 = r0 * r3
            r7.setHourAngle$material3_release(r0)
            goto L6d
        L40:
            boolean r7 = r6.$fromTap
            r0 = 1037465424(0x3dd67750, float:0.10471976)
            if (r7 == 0) goto L60
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            float r3 = r6.$value
            int r3 = androidx.compose.material3.TimePickerState.access$toMinute(r7, r3)
            androidx.compose.material3.TimePickerState r4 = r6.this$0
            float r5 = r6.$value
            int r4 = androidx.compose.material3.TimePickerState.access$toMinute(r4, r5)
            int r4 = r4 % 5
            int r3 = r3 - r4
            float r3 = (float) r3
            float r3 = r3 * r0
            r7.setMinuteAngle$material3_release(r3)
            goto L6d
        L60:
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            float r3 = r6.$value
            int r3 = androidx.compose.material3.TimePickerState.access$toMinute(r7, r3)
            float r3 = (float) r3
            float r3 = r3 * r0
            r7.setMinuteAngle$material3_release(r3)
        L6d:
            boolean r7 = r6.$fromTap
            e7.a r0 = e7.a.f15033i
            if (r7 == 0) goto L8d
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            androidx.compose.animation.core.Animatable r7 = r7.getCurrentAngle$material3_release()
            androidx.compose.material3.TimePickerState r1 = r6.this$0
            float r1 = r1.getMinuteAngle$material3_release()
            java.lang.Float r3 = new java.lang.Float
            r3.<init>(r1)
            r6.label = r2
            java.lang.Object r7 = r7.snapTo(r3, r6)
            if (r7 != r0) goto La9
            goto La8
        L8d:
            androidx.compose.material3.TimePickerState r7 = r6.this$0
            androidx.compose.animation.core.Animatable r7 = r7.getCurrentAngle$material3_release()
            androidx.compose.material3.TimePickerState r2 = r6.this$0
            float r3 = r6.$value
            float r2 = androidx.compose.material3.TimePickerState.access$offsetHour(r2, r3)
            java.lang.Float r3 = new java.lang.Float
            r3.<init>(r2)
            r6.label = r1
            java.lang.Object r7 = r7.snapTo(r3, r6)
            if (r7 != r0) goto La9
        La8:
            return r0
        La9:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerState$update$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
