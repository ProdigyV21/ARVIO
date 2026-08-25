package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/RelativeGroupPath;", "Landroidx/compose/runtime/SourceInformationGroupPath;", "parent", "index", "", "(Landroidx/compose/runtime/SourceInformationGroupPath;I)V", "getIndex", "()I", "getParent", "()Landroidx/compose/runtime/SourceInformationGroupPath;", "getIdentity", "", "table", "Landroidx/compose/runtime/SlotTable;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class RelativeGroupPath extends SourceInformationGroupPath {
    private final int index;
    private final SourceInformationGroupPath parent;

    public RelativeGroupPath(SourceInformationGroupPath sourceInformationGroupPath, int i10) {
        super(null);
        this.parent = sourceInformationGroupPath;
        this.index = i10;
    }

    @Override // androidx.compose.runtime.SourceInformationGroupPath
    public Object getIdentity(SlotTable table) {
        return new SourceInformationSlotTableGroupIdentity(this.parent.getIdentity(table), this.index);
    }

    public final int getIndex() {
        return this.index;
    }

    public final SourceInformationGroupPath getParent() {
        return this.parent;
    }
}
