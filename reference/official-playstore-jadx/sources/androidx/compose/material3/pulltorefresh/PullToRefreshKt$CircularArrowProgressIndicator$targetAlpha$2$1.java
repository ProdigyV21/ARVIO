package androidx.compose.material3.pulltorefresh;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1 extends r implements a<Float> {
    final /* synthetic */ a<Float> $progress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1(a<Float> aVar) {
        super(0);
        this.$progress = aVar;
    }

    @Override // r7.a
    public final Float invoke() {
        return Float.valueOf(((Number) this.$progress.invoke()).floatValue() < 1.0f ? 0.3f : 1.0f);
    }
}
