package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/AnchoredGroupPath;", "Landroidx/compose/runtime/SourceInformationGroupPath;", "group", "", "(I)V", "getGroup", "()I", "getIdentity", "", "table", "Landroidx/compose/runtime/SlotTable;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AnchoredGroupPath extends SourceInformationGroupPath {
    private final int group;

    public AnchoredGroupPath(int i10) {
        super(null);
        this.group = i10;
    }

    public final int getGroup() {
        return this.group;
    }

    @Override // androidx.compose.runtime.SourceInformationGroupPath
    public Object getIdentity(SlotTable table) {
        return table.anchor(this.group);
    }
}
