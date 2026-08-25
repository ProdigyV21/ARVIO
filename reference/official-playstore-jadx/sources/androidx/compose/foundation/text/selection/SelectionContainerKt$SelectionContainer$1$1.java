package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/text/selection/Selection;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/selection/Selection;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SelectionContainerKt$SelectionContainer$1$1 extends r implements l<Selection, t0> {
    final /* synthetic */ MutableState<Selection> $selection$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionContainerKt$SelectionContainer$1$1(MutableState<Selection> mutableState) {
        super(1);
        this.$selection$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Selection) obj);
        return t0.f22605a;
    }

    public final void invoke(Selection selection) {
        this.$selection$delegate.setValue(selection);
    }
}
