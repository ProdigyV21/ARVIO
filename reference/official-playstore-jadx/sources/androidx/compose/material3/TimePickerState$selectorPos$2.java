package androidx.compose.material3;

import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/DpOffset;", "invoke-RKDOV3M", "()J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TimePickerState$selectorPos$2 extends r implements r7.a<DpOffset> {
    final /* synthetic */ boolean $is24Hour;
    final /* synthetic */ TimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerState$selectorPos$2(TimePickerState timePickerState, boolean z) {
        super(0);
        this.this$0 = timePickerState;
        this.$is24Hour = z;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        return DpOffset.m5733boximpl(m2208invokeRKDOV3M());
    }

    /* JADX INFO: renamed from: invoke-RKDOV3M, reason: not valid java name */
    public final long m2208invokeRKDOV3M() {
        boolean zIsInnerCircle$material3_release = this.this$0.isInnerCircle$material3_release();
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        float f10 = 2;
        float fM5678constructorimpl = Dp.m5678constructorimpl(timePickerTokens.m2870getClockDialSelectorHandleContainerSizeD9Ej5fM() / f10);
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(Dp.m5678constructorimpl(((this.$is24Hour && zIsInnerCircle$material3_release && Selection.m1865equalsimpl0(this.this$0.m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1869getHourJiIwxys())) ? TimePickerKt.InnerCircleRadius : TimePickerKt.OuterCircleSizeRadius) - fM5678constructorimpl) + fM5678constructorimpl);
        return DpKt.m5699DpOffsetYgX7TsA(Dp.m5678constructorimpl(Dp.m5678constructorimpl(timePickerTokens.m2868getClockDialContainerSizeD9Ej5fM() / f10) + Dp.m5678constructorimpl(((float) Math.cos(this.this$0.getCurrentAngle$material3_release().getValue().floatValue())) * fM5678constructorimpl2)), Dp.m5678constructorimpl(Dp.m5678constructorimpl(timePickerTokens.m2868getClockDialContainerSizeD9Ej5fM() / f10) + Dp.m5678constructorimpl(fM5678constructorimpl2 * ((float) Math.sin(this.this$0.getCurrentAngle$material3_release().getValue().floatValue())))));
    }
}
