package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b \b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\tJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\tJ\u0015\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\tJ\u0015\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\tJ\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\fJ\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u000fJ\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0012\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\fJ\u0015\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001a\u0010\fJ\u0015\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\tJ\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\u000fJ\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001d\u0010\u000fJ\u001f\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001d\u0010\u001fJ\u000f\u0010 \u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b \u0010!J\r\u0010#\u001a\u00020\"¢\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\"¢\u0006\u0004\b%\u0010$J\r\u0010&\u001a\u00020\"¢\u0006\u0004\b&\u0010$J\r\u0010'\u001a\u00020\"¢\u0006\u0004\b'\u0010$J\r\u0010(\u001a\u00020\"¢\u0006\u0004\b(\u0010$J\r\u0010)\u001a\u00020\u0006¢\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\"¢\u0006\u0004\b+\u0010$J\u0015\u0010,\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b.\u0010-J\r\u0010/\u001a\u00020\"¢\u0006\u0004\b/\u0010$J\u0013\u00102\u001a\b\u0012\u0004\u0012\u00020100¢\u0006\u0004\b2\u00103J\u000f\u00105\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106J\u0017\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u00107J\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u0002082\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u00109J\u001d\u0010:\u001a\u0004\u0018\u00010\u0001*\u0002082\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b:\u00109J\u001d\u0010;\u001a\u0004\u0018\u00010\u0001*\u0002082\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b;\u00109R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010<\u001a\u0004\b=\u0010>R\u0014\u0010?\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u001c\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010BR6\u0010J\u001a\"\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020H\u0018\u00010Gj\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020H\u0018\u0001`I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR$\u0010M\u001a\u00020\n2\u0006\u0010L\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR$\u0010Q\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\bQ\u0010B\u001a\u0004\bR\u0010*R$\u0010S\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\bS\u0010B\u001a\u0004\bT\u0010*R$\u0010\b\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\b\u0010B\u001a\u0004\bU\u0010*R\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010BR\u0016\u0010Z\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010BR\u0016\u0010[\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010BR$\u0010\\\u001a\u00020\n2\u0006\u0010L\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\\\u0010N\u001a\u0004\b]\u0010PR\u0011\u0010_\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b^\u0010*R\u0011\u0010a\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b`\u0010*R\u0011\u0010\u000b\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010PR\u0011\u0010\r\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bb\u0010*R\u0011\u0010c\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bc\u0010PR\u0011\u0010e\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bd\u0010PR\u0011\u0010\u0010\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bf\u0010*R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bg\u0010*R\u0011\u0010\u0012\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bh\u0010*R\u0011\u0010j\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bi\u0010*R\u0011\u0010\u0013\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bk\u0010PR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\bl\u0010!R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\bm\u0010!R\u0013\u0010o\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\bn\u0010!R\u0011\u0010q\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bp\u0010*R\u0011\u0010s\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\br\u0010*R\u0011\u0010u\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bt\u0010*¨\u0006v"}, d2 = {"Landroidx/compose/runtime/SlotReader;", "", "Landroidx/compose/runtime/SlotTable;", "table", "<init>", "(Landroidx/compose/runtime/SlotTable;)V", "", "index", "parent", "(I)I", "", "isNode", "(I)Z", "nodeCount", "node", "(I)Ljava/lang/Object;", "groupSize", "groupEnd", "groupKey", "hasObjectKey", "groupObjectKey", "groupAux", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "(Landroidx/compose/runtime/Anchor;)I", "hasMark", "containsMark", "parentOf", "get", "groupGet", "group", "(II)Ljava/lang/Object;", LinkHeader.Rel.Next, "()Ljava/lang/Object;", "Lx6/t0;", "beginEmpty", "()V", "endEmpty", "close", "startGroup", "startNode", "skipGroup", "()I", "skipToGroupEnd", "reposition", "(I)V", "restoreParent", "endGroup", "", "Landroidx/compose/runtime/KeyInfo;", "extractKeys", "()Ljava/util/List;", "", "toString", "()Ljava/lang/String;", "(I)Landroidx/compose/runtime/Anchor;", "", "([II)Ljava/lang/Object;", "aux", "objectKey", "Landroidx/compose/runtime/SlotTable;", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "groups", "[I", "groupsSize", "I", "", "slots", "[Ljava/lang/Object;", "slotsSize", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "sourceInformationMap", "Ljava/util/HashMap;", "<set-?>", "closed", "Z", "getClosed", "()Z", "currentGroup", "getCurrentGroup", "currentEnd", "getCurrentEnd", "getParent", "Landroidx/compose/runtime/IntStack;", "currentSlotStack", "Landroidx/compose/runtime/IntStack;", "emptyCount", "currentSlot", "currentSlotEnd", "hadNext", "getHadNext", "getSize", ContentDisposition.Parameters.Size, "getSlot", "slot", "getNodeCount", "isGroupEnd", "getInEmpty", "inEmpty", "getGroupSize", "getGroupEnd", "getGroupKey", "getGroupSlotIndex", "groupSlotIndex", "getHasObjectKey", "getGroupObjectKey", "getGroupAux", "getGroupNode", "groupNode", "getParentNodes", "parentNodes", "getRemainingSlots", "remainingSlots", "getGroupSlotCount", "groupSlotCount", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SlotReader {
    public static final int $stable = 8;
    private boolean closed;
    private int currentEnd;
    private int currentGroup;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack currentSlotStack;
    private int emptyCount;
    private final int[] groups;
    private final int groupsSize;
    private boolean hadNext;
    private int parent;
    private final Object[] slots;
    private final int slotsSize;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private final SlotTable table;

    public SlotReader(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.getGroups();
        int groupsSize = slotTable.getGroupsSize();
        this.groupsSize = groupsSize;
        this.slots = slotTable.getSlots();
        this.slotsSize = slotTable.getSlotsSize();
        this.currentEnd = groupsSize;
        this.parent = -1;
        this.currentSlotStack = new IntStack();
    }

    public static /* synthetic */ Anchor anchor$default(SlotReader slotReader, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = slotReader.currentGroup;
        }
        return slotReader.anchor(i10);
    }

    private final Object aux(int[] iArr, int i10) {
        return SlotTableKt.hasAux(iArr, i10) ? this.slots[SlotTableKt.auxIndex(iArr, i10)] : Composer.INSTANCE.getEmpty();
    }

    private final Object objectKey(int[] iArr, int i10) {
        if (SlotTableKt.hasObjectKey(iArr, i10)) {
            return this.slots[SlotTableKt.objectKeyIndex(iArr, i10)];
        }
        return null;
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> anchors$runtime_release = this.table.getAnchors$runtime_release();
        int iSearch = SlotTableKt.search(anchors$runtime_release, index, this.groupsSize);
        if (iSearch >= 0) {
            return anchors$runtime_release.get(iSearch);
        }
        Anchor anchor = new Anchor(index);
        anchors$runtime_release.add(-(iSearch + 1), anchor);
        return anchor;
    }

    public final void beginEmpty() {
        this.emptyCount++;
    }

    public final void close() {
        this.closed = true;
        this.table.close$runtime_release(this, this.sourceInformationMap);
    }

    public final boolean containsMark(int index) {
        return SlotTableKt.containsMark(this.groups, index);
    }

    public final void endEmpty() {
        if (!(this.emptyCount > 0)) {
            PreconditionsKt.throwIllegalArgumentException("Unbalanced begin/end empty");
        }
        this.emptyCount--;
    }

    public final void endGroup() {
        if (this.emptyCount == 0) {
            if (!(this.currentGroup == this.currentEnd)) {
                ComposerKt.composeImmediateRuntimeError("endGroup() not called at the end of a group");
            }
            int iParentAnchor = SlotTableKt.parentAnchor(this.groups, this.parent);
            this.parent = iParentAnchor;
            this.currentEnd = iParentAnchor < 0 ? this.groupsSize : SlotTableKt.groupSize(this.groups, iParentAnchor) + iParentAnchor;
            int iPop = this.currentSlotStack.pop();
            if (iPop < 0) {
                this.currentSlot = 0;
                this.currentSlotEnd = 0;
            } else {
                this.currentSlot = iPop;
                this.currentSlotEnd = iParentAnchor >= this.groupsSize - 1 ? this.slotsSize : SlotTableKt.dataAnchor(this.groups, iParentAnchor + 1);
            }
        }
    }

    public final List<KeyInfo> extractKeys() {
        ArrayList arrayList = new ArrayList();
        if (this.emptyCount <= 0) {
            int i10 = 0;
            int iGroupSize = this.currentGroup;
            while (true) {
                int i11 = i10;
                if (iGroupSize >= this.currentEnd) {
                    break;
                }
                i10 = i11 + 1;
                arrayList.add(new KeyInfo(SlotTableKt.key(this.groups, iGroupSize), objectKey(this.groups, iGroupSize), iGroupSize, SlotTableKt.isNode(this.groups, iGroupSize) ? 1 : SlotTableKt.nodeCount(this.groups, iGroupSize), i11));
                iGroupSize += SlotTableKt.groupSize(this.groups, iGroupSize);
            }
        }
        return arrayList;
    }

    public final Object get(int index) {
        int i10 = this.currentSlot + index;
        return i10 < this.currentSlotEnd ? this.slots[i10] : Composer.INSTANCE.getEmpty();
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final int getCurrentEnd() {
        return this.currentEnd;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final Object getGroupAux() {
        int i10 = this.currentGroup;
        if (i10 < this.currentEnd) {
            return aux(this.groups, i10);
        }
        return 0;
    }

    public final int getGroupEnd() {
        return this.currentEnd;
    }

    public final int getGroupKey() {
        int i10 = this.currentGroup;
        if (i10 < this.currentEnd) {
            return SlotTableKt.key(this.groups, i10);
        }
        return 0;
    }

    public final Object getGroupNode() {
        int i10 = this.currentGroup;
        if (i10 < this.currentEnd) {
            return node(this.groups, i10);
        }
        return null;
    }

    public final Object getGroupObjectKey() {
        int i10 = this.currentGroup;
        if (i10 < this.currentEnd) {
            return objectKey(this.groups, i10);
        }
        return null;
    }

    public final int getGroupSize() {
        return SlotTableKt.groupSize(this.groups, this.currentGroup);
    }

    public final int getGroupSlotCount() {
        int i10 = this.currentGroup;
        int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i10);
        int i11 = i10 + 1;
        return (i11 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i11) : this.slotsSize) - iSlotAnchor;
    }

    public final int getGroupSlotIndex() {
        return this.currentSlot - SlotTableKt.slotAnchor(this.groups, this.parent);
    }

    public final boolean getHadNext() {
        return this.hadNext;
    }

    public final boolean getHasObjectKey() {
        int i10 = this.currentGroup;
        return i10 < this.currentEnd && SlotTableKt.hasObjectKey(this.groups, i10);
    }

    public final boolean getInEmpty() {
        return this.emptyCount > 0;
    }

    public final int getNodeCount() {
        return SlotTableKt.nodeCount(this.groups, this.currentGroup);
    }

    public final int getParent() {
        return this.parent;
    }

    public final int getParentNodes() {
        int i10 = this.parent;
        if (i10 >= 0) {
            return SlotTableKt.nodeCount(this.groups, i10);
        }
        return 0;
    }

    public final int getRemainingSlots() {
        return this.currentSlotEnd - this.currentSlot;
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final int getSlot() {
        return this.currentSlot - SlotTableKt.slotAnchor(this.groups, this.parent);
    }

    /* JADX INFO: renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final Object groupAux(int index) {
        return aux(this.groups, index);
    }

    public final int groupEnd(int index) {
        return SlotTableKt.groupSize(this.groups, index) + index;
    }

    public final Object groupGet(int index) {
        return groupGet(this.currentGroup, index);
    }

    public final int groupKey(int index) {
        return SlotTableKt.key(this.groups, index);
    }

    public final Object groupObjectKey(int index) {
        return objectKey(this.groups, index);
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, index);
    }

    public final boolean hasMark(int index) {
        return SlotTableKt.hasMark(this.groups, index);
    }

    public final boolean hasObjectKey(int index) {
        return SlotTableKt.hasObjectKey(this.groups, index);
    }

    public final boolean isGroupEnd() {
        return getInEmpty() || this.currentGroup == this.currentEnd;
    }

    public final boolean isNode() {
        return SlotTableKt.isNode(this.groups, this.currentGroup);
    }

    public final Object next() {
        int i10;
        if (this.emptyCount > 0 || (i10 = this.currentSlot) >= this.currentSlotEnd) {
            this.hadNext = false;
            return Composer.INSTANCE.getEmpty();
        }
        this.hadNext = true;
        Object[] objArr = this.slots;
        this.currentSlot = i10 + 1;
        return objArr[i10];
    }

    public final Object node(int index) {
        if (SlotTableKt.isNode(this.groups, index)) {
            return node(this.groups, index);
        }
        return null;
    }

    public final int nodeCount(int index) {
        return SlotTableKt.nodeCount(this.groups, index);
    }

    public final int parent(int index) {
        return SlotTableKt.parentAnchor(this.groups, index);
    }

    public final int parentOf(int index) {
        if (!(index >= 0 && index < this.groupsSize)) {
            PreconditionsKt.throwIllegalArgumentException("Invalid group index " + index);
        }
        return SlotTableKt.parentAnchor(this.groups, index);
    }

    public final void reposition(int index) {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reposition while in an empty region");
        }
        this.currentGroup = index;
        int iParentAnchor = index < this.groupsSize ? SlotTableKt.parentAnchor(this.groups, index) : -1;
        this.parent = iParentAnchor;
        if (iParentAnchor < 0) {
            this.currentEnd = this.groupsSize;
        } else {
            this.currentEnd = SlotTableKt.groupSize(this.groups, iParentAnchor) + iParentAnchor;
        }
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void restoreParent(int index) {
        int iGroupSize = SlotTableKt.groupSize(this.groups, index) + index;
        int i10 = this.currentGroup;
        if (!(i10 >= index && i10 <= iGroupSize)) {
            ComposerKt.composeImmediateRuntimeError("Index " + index + " is not a parent of " + i10);
        }
        this.parent = index;
        this.currentEnd = iGroupSize;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final int skipGroup() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip while in an empty region");
        }
        int iNodeCount = SlotTableKt.isNode(this.groups, this.currentGroup) ? 1 : SlotTableKt.nodeCount(this.groups, this.currentGroup);
        int i10 = this.currentGroup;
        this.currentGroup = SlotTableKt.groupSize(this.groups, i10) + i10;
        return iNodeCount;
    }

    public final void skipToGroupEnd() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip the enclosing group while in an empty region");
        }
        this.currentGroup = this.currentEnd;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void startGroup() {
        GroupSourceInformation groupSourceInformation;
        if (this.emptyCount <= 0) {
            int i10 = this.parent;
            int i11 = this.currentGroup;
            if (!(SlotTableKt.parentAnchor(this.groups, i11) == i10)) {
                PreconditionsKt.throwIllegalArgumentException("Invalid slot table detected");
            }
            HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
            if (map != null && (groupSourceInformation = map.get(anchor(i10))) != null) {
                groupSourceInformation.reportGroup(this.table, i11);
            }
            IntStack intStack = this.currentSlotStack;
            int i12 = this.currentSlot;
            int i13 = this.currentSlotEnd;
            if (i12 == 0 && i13 == 0) {
                intStack.push(-1);
            } else {
                intStack.push(i12);
            }
            this.parent = i11;
            this.currentEnd = SlotTableKt.groupSize(this.groups, i11) + i11;
            int i14 = i11 + 1;
            this.currentGroup = i14;
            this.currentSlot = SlotTableKt.slotAnchor(this.groups, i11);
            this.currentSlotEnd = i11 >= this.groupsSize - 1 ? this.slotsSize : SlotTableKt.dataAnchor(this.groups, i14);
        }
    }

    public final void startNode() {
        if (this.emptyCount <= 0) {
            if (!SlotTableKt.isNode(this.groups, this.currentGroup)) {
                PreconditionsKt.throwIllegalArgumentException("Expected a node group");
            }
            startGroup();
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SlotReader(current=");
        sb2.append(this.currentGroup);
        sb2.append(", key=");
        sb2.append(getGroupKey());
        sb2.append(", parent=");
        sb2.append(this.parent);
        sb2.append(", end=");
        return a0.c.o(sb2, this.currentEnd, ')');
    }

    private final Object node(int[] iArr, int i10) {
        return SlotTableKt.isNode(iArr, i10) ? this.slots[SlotTableKt.nodeIndex(iArr, i10)] : Composer.INSTANCE.getEmpty();
    }

    public final Object groupGet(int group, int index) {
        int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, group);
        int i10 = group + 1;
        int i11 = iSlotAnchor + index;
        return i11 < (i10 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i10) : this.slotsSize) ? this.slots[i11] : Composer.INSTANCE.getEmpty();
    }

    public final int groupKey(Anchor anchor) {
        if (anchor.getValid()) {
            return SlotTableKt.key(this.groups, this.table.anchorIndex(anchor));
        }
        return 0;
    }

    public final boolean isNode(int index) {
        return SlotTableKt.isNode(this.groups, index);
    }
}
