package androidx.compose.foundation;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(F)Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScrollState$scrollableState$1 extends r implements l<Float, Float> {
    final /* synthetic */ ScrollState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollState$scrollableState$1(ScrollState scrollState) {
        super(1);
        this.this$0 = scrollState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).floatValue());
    }

    public final Float invoke(float f10) {
        float value = this.this$0.accumulator + this.this$0.getValue() + f10;
        float fM = d.m(value, 0.0f, this.this$0.getMaxValue());
        boolean z = value == fM;
        float value2 = fM - this.this$0.getValue();
        int iM = t7.a.M(value2);
        ScrollState scrollState = this.this$0;
        scrollState.setValue(scrollState.getValue() + iM);
        this.this$0.accumulator = value2 - iM;
        if (!z) {
            f10 = value2;
        }
        return Float.valueOf(f10);
    }
}
