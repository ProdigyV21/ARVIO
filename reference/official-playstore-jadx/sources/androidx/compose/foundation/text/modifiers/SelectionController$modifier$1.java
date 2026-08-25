package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionController$modifier$1 extends r implements a<LayoutCoordinates> {
    final /* synthetic */ SelectionController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionController$modifier$1(SelectionController selectionController) {
        super(0);
        this.this$0 = selectionController;
    }

    @Override // r7.a
    public final LayoutCoordinates invoke() {
        return this.this$0.params.getLayoutCoordinates();
    }
}
