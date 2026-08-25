package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "invoke-F1C5BW0", "()J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionContainerKt$SelectionContainer$3$1$1$1$1$positionProvider$1$2 extends r implements r7.a<Offset> {
    final /* synthetic */ SelectionManager $manager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionContainerKt$SelectionContainer$3$1$1$1$1$positionProvider$1$2(SelectionManager selectionManager) {
        super(0);
        this.$manager = selectionManager;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        return Offset.m3225boximpl(m968invokeF1C5BW0());
    }

    /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
    public final long m968invokeF1C5BW0() {
        Offset offsetM1002getEndHandlePosition_m7T9E = this.$manager.m1002getEndHandlePosition_m7T9E();
        return offsetM1002getEndHandlePosition_m7T9E != null ? offsetM1002getEndHandlePosition_m7T9E.getPackedValue() : Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
    }
}
