package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ProgressIndicatorKt$LinearProgressIndicator$6$1 extends r implements r7.a<Float> {
    final /* synthetic */ float $progress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressIndicatorKt$LinearProgressIndicator$6$1(float f10) {
        super(0);
        this.$progress = f10;
    }

    @Override // r7.a
    public final Float invoke() {
        return Float.valueOf(this.$progress);
    }
}
