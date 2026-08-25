package androidx.compose.material3;

import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class NavigationRailKt$NavigationRailItem$2$2$1 extends r implements r7.a<Float> {
    final /* synthetic */ State<Float> $animationProgress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationRailKt$NavigationRailItem$2$2$1(State<Float> state) {
        super(0);
        this.$animationProgress = state;
    }

    @Override // r7.a
    public final Float invoke() {
        return this.$animationProgress.getValue();
    }
}
