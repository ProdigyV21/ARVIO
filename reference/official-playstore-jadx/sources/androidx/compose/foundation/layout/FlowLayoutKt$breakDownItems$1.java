package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/Placeable;", "placeable", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class FlowLayoutKt$breakDownItems$1 extends r implements l<Placeable, t0> {
    final /* synthetic */ int $index;
    final /* synthetic */ Placeable[] $placeables;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowLayoutKt$breakDownItems$1(Placeable[] placeableArr, int i10) {
        super(1);
        this.$placeables = placeableArr;
        this.$index = i10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable placeable) {
        this.$placeables[this.$index + 1] = placeable;
    }
}
