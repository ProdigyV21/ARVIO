package androidx.compose.foundation.text.selection;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectableInfo;", "info", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/selection/SelectableInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class MultiSelectionLayout$createSubSelections$2$1 extends r implements l<SelectableInfo, t0> {
    final /* synthetic */ Selection $selection;
    final /* synthetic */ Map<Long, Selection> $this_buildMap;
    final /* synthetic */ MultiSelectionLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiSelectionLayout$createSubSelections$2$1(MultiSelectionLayout multiSelectionLayout, Map<Long, Selection> map, Selection selection) {
        super(1);
        this.this$0 = multiSelectionLayout;
        this.$this_buildMap = map;
        this.$selection = selection;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SelectableInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(SelectableInfo selectableInfo) {
        this.this$0.createAndPutSubSelection(this.$this_buildMap, this.$selection, selectableInfo, 0, selectableInfo.getTextLength());
    }
}
