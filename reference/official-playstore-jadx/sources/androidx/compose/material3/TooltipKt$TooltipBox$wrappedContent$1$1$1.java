package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TooltipKt$TooltipBox$wrappedContent$1$1$1 extends r implements l<LayoutCoordinates, t0> {
    final /* synthetic */ MutableState<LayoutCoordinates> $anchorBounds$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TooltipKt$TooltipBox$wrappedContent$1$1$1(MutableState<LayoutCoordinates> mutableState) {
        super(1);
        this.$anchorBounds$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutCoordinates) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutCoordinates layoutCoordinates) {
        this.$anchorBounds$delegate.setValue(layoutCoordinates);
    }
}
