package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material3/BottomAppBarState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AppBarKt$rememberBottomAppBarState$1$1 extends r implements r7.a<BottomAppBarState> {
    final /* synthetic */ float $initialContentOffset;
    final /* synthetic */ float $initialHeightOffset;
    final /* synthetic */ float $initialHeightOffsetLimit;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppBarKt$rememberBottomAppBarState$1$1(float f10, float f11, float f12) {
        super(0);
        this.$initialHeightOffsetLimit = f10;
        this.$initialHeightOffset = f11;
        this.$initialContentOffset = f12;
    }

    @Override // r7.a
    public final BottomAppBarState invoke() {
        return AppBarKt.BottomAppBarState(this.$initialHeightOffsetLimit, this.$initialHeightOffset, this.$initialContentOffset);
    }
}
