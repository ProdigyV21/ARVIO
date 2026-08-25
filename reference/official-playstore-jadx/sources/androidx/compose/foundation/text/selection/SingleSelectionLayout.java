package androidx.compose.foundation.text.selection;

import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.Map;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u0000 82\u00020\u0001:\u00018B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0010\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u00162\u0006\u0010\u0015\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u0003\u0010\u001eR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010\u0006\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b\"\u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010%R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010&R\u0014\u0010(\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!R\u0014\u0010,\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010/\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0014\u00101\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010.R\u0014\u00103\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010.R\u0014\u00105\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010.R\u0014\u00107\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010.¨\u00069"}, d2 = {"Landroidx/compose/foundation/text/selection/SingleSelectionLayout;", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "", "isStartHandle", "", "startSlot", "endSlot", "Landroidx/compose/foundation/text/selection/Selection;", "previousSelection", "Landroidx/compose/foundation/text/selection/SelectableInfo;", "info", "<init>", "(ZIILandroidx/compose/foundation/text/selection/Selection;Landroidx/compose/foundation/text/selection/SelectableInfo;)V", "Lkotlin/Function1;", "Lx6/t0;", "block", "forEachMiddleInfo", "(Lr7/l;)V", "other", "shouldRecomputeSelection", "(Landroidx/compose/foundation/text/selection/SelectionLayout;)Z", "selection", "", "", "createSubSelections", "(Landroidx/compose/foundation/text/selection/Selection;)Ljava/util/Map;", "", "toString", "()Ljava/lang/String;", "Z", "()Z", "I", "getStartSlot", "()I", "getEndSlot", "Landroidx/compose/foundation/text/selection/Selection;", "getPreviousSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "Landroidx/compose/foundation/text/selection/SelectableInfo;", "getSize", ContentDisposition.Parameters.Size, "Landroidx/compose/foundation/text/selection/CrossStatus;", "getCrossStatus", "()Landroidx/compose/foundation/text/selection/CrossStatus;", "crossStatus", "getStartInfo", "()Landroidx/compose/foundation/text/selection/SelectableInfo;", "startInfo", "getEndInfo", "endInfo", "getCurrentInfo", "currentInfo", "getFirstInfo", "firstInfo", "getLastInfo", "lastInfo", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SingleSelectionLayout implements SelectionLayout {
    public static final long DEFAULT_SELECTABLE_ID = 1;
    public static final int DEFAULT_SLOT = 1;
    private final int endSlot;
    private final SelectableInfo info;
    private final boolean isStartHandle;
    private final Selection previousSelection;
    private final int startSlot;

    public SingleSelectionLayout(boolean z, int i10, int i11, Selection selection, SelectableInfo selectableInfo) {
        this.isStartHandle = z;
        this.startSlot = i10;
        this.endSlot = i11;
        this.previousSelection = selection;
        this.info = selectableInfo;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Map<Long, Selection> createSubSelections(Selection selection) {
        if ((selection.getHandlesCrossed() && selection.getStart().getOffset() >= selection.getEnd().getOffset()) || (!selection.getHandlesCrossed() && selection.getStart().getOffset() <= selection.getEnd().getOffset())) {
            return Collections.singletonMap(Long.valueOf(this.info.getSelectableId()), selection);
        }
        throw new IllegalStateException(("unexpectedly miss-crossed selection: " + selection).toString());
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public CrossStatus getCrossStatus() {
        return this.info.getRawCrossStatus();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    /* JADX INFO: renamed from: getCurrentInfo, reason: from getter */
    public SelectableInfo getInfo() {
        return this.info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getEndInfo() {
        return this.info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getEndSlot() {
        return this.endSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getFirstInfo() {
        return this.info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getLastInfo() {
        return this.info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Selection getPreviousSelection() {
        return this.previousSelection;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getSize() {
        return 1;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getStartInfo() {
        return this.info;
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
        if (getPreviousSelection() == null || other == null || !(other instanceof SingleSelectionLayout)) {
            return true;
        }
        SingleSelectionLayout singleSelectionLayout = (SingleSelectionLayout) other;
        return getIsStartHandle() != singleSelectionLayout.getIsStartHandle() || this.info.shouldRecomputeSelection(singleSelectionLayout.info);
    }

    public String toString() {
        return "SingleSelectionLayout(isStartHandle=" + getIsStartHandle() + ", crossed=" + getCrossStatus() + ", info=\n\t" + this.info + ')';
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public void forEachMiddleInfo(l<? super SelectableInfo, t0> block) {
    }
}
