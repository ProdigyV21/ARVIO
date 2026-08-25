package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import r7.l;
import x6.t0;
import z6.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001BK\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u001a\u001a\u00020\u0019*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000bH\u0002¢\u0006\u0004\b!\u0010\u001fJ\u0017\u0010#\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0003H\u0002¢\u0006\u0004\b#\u0010$J#\u0010'\u001a\u00020\u00192\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00190%H\u0016¢\u0006\u0004\b'\u0010(J\u0019\u0010)\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b)\u0010*J#\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u00022\u0006\u0010\u0015\u001a\u00020\rH\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u00100\u001a\u0004\b1\u00102R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u00103\u001a\u0004\b4\u00105R\u001a\u0010\t\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u00106\u001a\u0004\b7\u00108R\u001a\u0010\n\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u00106\u001a\u0004\b9\u00108R\u001a\u0010\f\u001a\u00020\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\b\f\u0010;R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010<\u001a\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u00108R\u0014\u0010D\u001a\u00020A8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0014\u0010G\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0014\u0010I\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010FR\u0014\u0010K\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010FR\u0014\u0010M\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010FR\u0014\u0010O\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010F¨\u0006P"}, d2 = {"Landroidx/compose/foundation/text/selection/MultiSelectionLayout;", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "", "", "", "selectableIdToInfoListIndex", "", "Landroidx/compose/foundation/text/selection/SelectableInfo;", "infoList", "startSlot", "endSlot", "", "isStartHandle", "Landroidx/compose/foundation/text/selection/Selection;", "previousSelection", "<init>", "(Ljava/util/Map;Ljava/util/List;IIZLandroidx/compose/foundation/text/selection/Selection;)V", "other", "shouldAnyInfoRecomputeSelection", "(Landroidx/compose/foundation/text/selection/MultiSelectionLayout;)Z", "", "selection", "info", "minOffset", "maxOffset", "Lx6/t0;", "createAndPutSubSelection", "(Ljava/util/Map;Landroidx/compose/foundation/text/selection/Selection;Landroidx/compose/foundation/text/selection/SelectableInfo;II)V", "slot", "isStartSlot", "startOrEndSlotToIndex", "(IZ)I", "isMinimumSlot", "slotToIndex", TtmlNode.ATTR_ID, "getInfoListIndexBySelectableId", "(J)I", "Lkotlin/Function1;", "block", "forEachMiddleInfo", "(Lr7/l;)V", "shouldRecomputeSelection", "(Landroidx/compose/foundation/text/selection/SelectionLayout;)Z", "createSubSelections", "(Landroidx/compose/foundation/text/selection/Selection;)Ljava/util/Map;", "", "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getSelectableIdToInfoListIndex", "()Ljava/util/Map;", "Ljava/util/List;", "getInfoList", "()Ljava/util/List;", "I", "getStartSlot", "()I", "getEndSlot", "Z", "()Z", "Landroidx/compose/foundation/text/selection/Selection;", "getPreviousSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "getSize", ContentDisposition.Parameters.Size, "Landroidx/compose/foundation/text/selection/CrossStatus;", "getCrossStatus", "()Landroidx/compose/foundation/text/selection/CrossStatus;", "crossStatus", "getStartInfo", "()Landroidx/compose/foundation/text/selection/SelectableInfo;", "startInfo", "getEndInfo", "endInfo", "getCurrentInfo", "currentInfo", "getFirstInfo", "firstInfo", "getLastInfo", "lastInfo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MultiSelectionLayout implements SelectionLayout {
    private final int endSlot;
    private final List<SelectableInfo> infoList;
    private final boolean isStartHandle;
    private final Selection previousSelection;
    private final Map<Long, Integer> selectableIdToInfoListIndex;
    private final int startSlot;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CrossStatus.values().length];
            try {
                iArr[CrossStatus.COLLAPSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CrossStatus.NOT_CROSSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CrossStatus.CROSSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MultiSelectionLayout(Map<Long, Integer> map, List<SelectableInfo> list, int i10, int i11, boolean z, Selection selection) {
        this.selectableIdToInfoListIndex = map;
        this.infoList = list;
        this.startSlot = i10;
        this.endSlot = i11;
        this.isStartHandle = z;
        this.previousSelection = selection;
        if (list.size() > 1) {
            return;
        }
        throw new IllegalStateException(("MultiSelectionLayout requires an infoList size greater than 1, was " + list.size() + '.').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createAndPutSubSelection(Map<Long, Selection> map, Selection selection, SelectableInfo selectableInfo, int i10, int i11) {
        Selection selectionMakeSingleLayoutSelection = selection.getHandlesCrossed() ? selectableInfo.makeSingleLayoutSelection(i11, i10) : selectableInfo.makeSingleLayoutSelection(i10, i11);
        if (i10 <= i11) {
            map.put(Long.valueOf(selectableInfo.getSelectableId()), selectionMakeSingleLayoutSelection);
        } else {
            throw new IllegalStateException(("minOffset should be less than or equal to maxOffset: " + selectionMakeSingleLayoutSelection).toString());
        }
    }

    private final int getInfoListIndexBySelectableId(long id) {
        Integer num = this.selectableIdToInfoListIndex.get(Long.valueOf(id));
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException(c.q(id, "Invalid selectableId: ").toString());
    }

    private final boolean shouldAnyInfoRecomputeSelection(MultiSelectionLayout other) {
        if (getSize() != other.getSize()) {
            return true;
        }
        int size = this.infoList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (this.infoList.get(i10).shouldRecomputeSelection(other.infoList.get(i10))) {
                return true;
            }
        }
        return false;
    }

    private final int slotToIndex(int slot, boolean isMinimumSlot) {
        return (slot - (!isMinimumSlot ? 1 : 0)) / 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int startOrEndSlotToIndex(int r4, boolean r5) {
        /*
            r3 = this;
            androidx.compose.foundation.text.selection.CrossStatus r0 = r3.getCrossStatus()
            int[] r1 = androidx.compose.foundation.text.selection.MultiSelectionLayout.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            if (r0 == r1) goto L17
            r2 = 2
            if (r0 == r2) goto L21
            r2 = 3
            if (r0 != r2) goto L1b
            if (r5 != 0) goto L19
        L17:
            r5 = r1
            goto L21
        L19:
            r5 = 0
            goto L21
        L1b:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L21:
            int r4 = r3.slotToIndex(r4, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.MultiSelectionLayout.startOrEndSlotToIndex(int, boolean):int");
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Map<Long, Selection> createSubSelections(Selection selection) {
        if (selection.getStart().getSelectableId() != selection.getEnd().getSelectableId()) {
            f fVar = new f();
            createAndPutSubSelection(fVar, selection, getFirstInfo(), (selection.getHandlesCrossed() ? selection.getEnd() : selection.getStart()).getOffset(), getFirstInfo().getTextLength());
            forEachMiddleInfo(new MultiSelectionLayout$createSubSelections$2$1(this, fVar, selection));
            createAndPutSubSelection(fVar, selection, getLastInfo(), 0, (selection.getHandlesCrossed() ? selection.getStart() : selection.getEnd()).getOffset());
            return fVar.c();
        }
        if ((selection.getHandlesCrossed() && selection.getStart().getOffset() >= selection.getEnd().getOffset()) || (!selection.getHandlesCrossed() && selection.getStart().getOffset() <= selection.getEnd().getOffset())) {
            return Collections.singletonMap(Long.valueOf(selection.getStart().getSelectableId()), selection);
        }
        throw new IllegalStateException(("unexpectedly miss-crossed selection: " + selection).toString());
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public void forEachMiddleInfo(l<? super SelectableInfo, t0> block) {
        int infoListIndexBySelectableId = getInfoListIndexBySelectableId(getFirstInfo().getSelectableId());
        int infoListIndexBySelectableId2 = getInfoListIndexBySelectableId(getLastInfo().getSelectableId());
        int i10 = infoListIndexBySelectableId + 1;
        if (i10 >= infoListIndexBySelectableId2) {
            return;
        }
        while (i10 < infoListIndexBySelectableId2) {
            block.invoke(this.infoList.get(i10));
            i10++;
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public CrossStatus getCrossStatus() {
        return getStartSlot() < getEndSlot() ? CrossStatus.NOT_CROSSED : getStartSlot() > getEndSlot() ? CrossStatus.CROSSED : this.infoList.get(getStartSlot() / 2).getRawCrossStatus();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    /* JADX INFO: renamed from: getCurrentInfo */
    public SelectableInfo getInfo() {
        return getIsStartHandle() ? getStartInfo() : getEndInfo();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getEndInfo() {
        return this.infoList.get(startOrEndSlotToIndex(getEndSlot(), false));
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getEndSlot() {
        return this.endSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getFirstInfo() {
        return getCrossStatus() == CrossStatus.CROSSED ? getEndInfo() : getStartInfo();
    }

    public final List<SelectableInfo> getInfoList() {
        return this.infoList;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getLastInfo() {
        return getCrossStatus() == CrossStatus.CROSSED ? getStartInfo() : getEndInfo();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Selection getPreviousSelection() {
        return this.previousSelection;
    }

    public final Map<Long, Integer> getSelectableIdToInfoListIndex() {
        return this.selectableIdToInfoListIndex;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getSize() {
        return this.infoList.size();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getStartInfo() {
        return this.infoList.get(startOrEndSlotToIndex(getStartSlot(), true));
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getStartSlot() {
        return this.startSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    /* JADX INFO: renamed from: isStartHandle, reason: from getter */
    public boolean getIsStartHandle() {
        return this.isStartHandle;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public boolean shouldRecomputeSelection(SelectionLayout other) {
        if (getPreviousSelection() == null || other == null || !(other instanceof MultiSelectionLayout)) {
            return true;
        }
        MultiSelectionLayout multiSelectionLayout = (MultiSelectionLayout) other;
        return (getIsStartHandle() == multiSelectionLayout.getIsStartHandle() && getStartSlot() == multiSelectionLayout.getStartSlot() && getEndSlot() == multiSelectionLayout.getEndSlot() && !shouldAnyInfoRecomputeSelection(multiSelectionLayout)) ? false : true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MultiSelectionLayout(isStartHandle=");
        sb2.append(getIsStartHandle());
        sb2.append(", startPosition=");
        boolean z = true;
        float f10 = 2;
        sb2.append((getStartSlot() + 1) / f10);
        sb2.append(", endPosition=");
        sb2.append((getEndSlot() + 1) / f10);
        sb2.append(", crossed=");
        sb2.append(getCrossStatus());
        sb2.append(", infos=");
        StringBuilder sb3 = new StringBuilder("[\n\t");
        List<SelectableInfo> list = this.infoList;
        int size = list.size();
        int i10 = 0;
        while (i10 < size) {
            SelectableInfo selectableInfo = list.get(i10);
            if (z) {
                z = false;
            } else {
                sb3.append(",\n\t");
            }
            StringBuilder sb4 = new StringBuilder();
            i10++;
            sb4.append(i10);
            sb4.append(" -> ");
            sb4.append(selectableInfo);
            sb3.append(sb4.toString());
        }
        sb3.append("\n]");
        sb2.append(sb3.toString());
        sb2.append(')');
        return sb2.toString();
    }
}
