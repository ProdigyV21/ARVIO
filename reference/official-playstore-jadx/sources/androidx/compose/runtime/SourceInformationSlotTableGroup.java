package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010)H\u0096\u0002R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u0004\u0018\u00010!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006*"}, d2 = {"Landroidx/compose/runtime/SourceInformationSlotTableGroup;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "table", "Landroidx/compose/runtime/SlotTable;", "parent", "", "sourceInformation", "Landroidx/compose/runtime/GroupSourceInformation;", "identityPath", "Landroidx/compose/runtime/SourceInformationGroupPath;", "(Landroidx/compose/runtime/SlotTable;ILandroidx/compose/runtime/GroupSourceInformation;Landroidx/compose/runtime/SourceInformationGroupPath;)V", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "data", "", "getData", "identity", "getIdentity", "()Ljava/lang/Object;", "getIdentityPath", "()Landroidx/compose/runtime/SourceInformationGroupPath;", "isEmpty", "", "()Z", "key", "getKey", "node", "getNode", "getParent", "()I", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "getSourceInformation", "()Landroidx/compose/runtime/GroupSourceInformation;", "getTable", "()Landroidx/compose/runtime/SlotTable;", "iterator", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SourceInformationSlotTableGroup implements CompositionGroup, Iterable<CompositionGroup>, s7.a {
    private final Iterable<CompositionGroup> compositionGroups = this;
    private final SourceInformationGroupPath identityPath;
    private final Object key;
    private final int parent;
    private final GroupSourceInformation sourceInformation;
    private final SlotTable table;

    public SourceInformationSlotTableGroup(SlotTable slotTable, int i10, GroupSourceInformation groupSourceInformation, SourceInformationGroupPath sourceInformationGroupPath) {
        this.table = slotTable;
        this.parent = i10;
        this.sourceInformation = groupSourceInformation;
        this.identityPath = sourceInformationGroupPath;
        this.key = Integer.valueOf(groupSourceInformation.getKey());
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public final /* synthetic */ CompositionGroup find(Object obj) {
        return androidx.compose.runtime.tooling.a.a(this, obj);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this.compositionGroups;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Iterable<Object> getData() {
        return new SourceInformationGroupDataIterator(this.table, this.parent, this.sourceInformation);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public final /* synthetic */ int getGroupSize() {
        return androidx.compose.runtime.tooling.b.a(this);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getIdentity() {
        return this.identityPath.getIdentity(this.table);
    }

    public final SourceInformationGroupPath getIdentityPath() {
        return this.identityPath;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public Object getNode() {
        return null;
    }

    public final int getParent() {
        return this.parent;
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public final /* synthetic */ int getSlotsSize() {
        return androidx.compose.runtime.tooling.b.c(this);
    }

    @Override // androidx.compose.runtime.tooling.CompositionGroup
    public String getSourceInfo() {
        return this.sourceInformation.getSourceInformation();
    }

    public final GroupSourceInformation getSourceInformation() {
        return this.sourceInformation;
    }

    public final SlotTable getTable() {
        return this.table;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        ArrayList<Object> groups = this.sourceInformation.getGroups();
        boolean z = false;
        if (groups != null && !groups.isEmpty()) {
            z = true;
        }
        return !z;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new SourceInformationGroupIterator(this.table, this.parent, this.sourceInformation, this.identityPath);
    }
}
