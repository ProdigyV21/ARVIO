package androidx.compose.foundation.text2.input.internal;

import a0.c;
import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001&B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004J1\u0010\f\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0007H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0007H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0018J\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010%\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "initialChanges", "<init>", "(Landroidx/compose/foundation/text2/input/internal/ChangeTracker;)V", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker$Change;", "mergedOverlappingChange", "", "preMin", "preMax", "postDelta", "Lx6/t0;", "appendNewChange", "(Landroidx/compose/foundation/text2/input/internal/ChangeTracker$Change;III)V", "preStart", "preEnd", "postLength", "trackChange", "(III)V", "clearChanges", "()V", "changeIndex", "Landroidx/compose/ui/text/TextRange;", "getRange--jx7JFs", "(I)J", "getRange", "getOriginalRange--jx7JFs", "getOriginalRange", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/runtime/collection/MutableVector;", "_changes", "Landroidx/compose/runtime/collection/MutableVector;", "_changesTemp", "getChangeCount", "()I", "changeCount", "Change", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ChangeTracker implements TextFieldBuffer.ChangeList {
    public static final int $stable = 8;
    private MutableVector<Change> _changes;
    private MutableVector<Change> _changesTemp;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ChangeTracker$Change;", "", "preStart", "", "preEnd", "originalStart", "originalEnd", "(IIII)V", "getOriginalEnd", "()I", "setOriginalEnd", "(I)V", "getOriginalStart", "setOriginalStart", "getPreEnd", "setPreEnd", "getPreStart", "setPreStart", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Change {
        private int originalEnd;
        private int originalStart;
        private int preEnd;
        private int preStart;

        public Change(int i10, int i11, int i12, int i13) {
            this.preStart = i10;
            this.preEnd = i11;
            this.originalStart = i12;
            this.originalEnd = i13;
        }

        public static /* synthetic */ Change copy$default(Change change, int i10, int i11, int i12, int i13, int i14, Object obj) {
            if ((i14 & 1) != 0) {
                i10 = change.preStart;
            }
            if ((i14 & 2) != 0) {
                i11 = change.preEnd;
            }
            if ((i14 & 4) != 0) {
                i12 = change.originalStart;
            }
            if ((i14 & 8) != 0) {
                i13 = change.originalEnd;
            }
            return change.copy(i10, i11, i12, i13);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPreStart() {
            return this.preStart;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getPreEnd() {
            return this.preEnd;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getOriginalStart() {
            return this.originalStart;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final Change copy(int preStart, int preEnd, int originalStart, int originalEnd) {
            return new Change(preStart, preEnd, originalStart, originalEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Change)) {
                return false;
            }
            Change change = (Change) other;
            return this.preStart == change.preStart && this.preEnd == change.preEnd && this.originalStart == change.originalStart && this.originalEnd == change.originalEnd;
        }

        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final int getOriginalStart() {
            return this.originalStart;
        }

        public final int getPreEnd() {
            return this.preEnd;
        }

        public final int getPreStart() {
            return this.preStart;
        }

        public int hashCode() {
            return (((((this.preStart * 31) + this.preEnd) * 31) + this.originalStart) * 31) + this.originalEnd;
        }

        public final void setOriginalEnd(int i10) {
            this.originalEnd = i10;
        }

        public final void setOriginalStart(int i10) {
            this.originalStart = i10;
        }

        public final void setPreEnd(int i10) {
            this.preEnd = i10;
        }

        public final void setPreStart(int i10) {
            this.preStart = i10;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Change(preStart=");
            sb2.append(this.preStart);
            sb2.append(", preEnd=");
            sb2.append(this.preEnd);
            sb2.append(", originalStart=");
            sb2.append(this.originalStart);
            sb2.append(", originalEnd=");
            return c.o(sb2, this.originalEnd, ')');
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChangeTracker() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private final void appendNewChange(Change mergedOverlappingChange, int preMin, int preMax, int postDelta) {
        int preEnd;
        if (this._changesTemp.isEmpty()) {
            preEnd = 0;
        } else {
            Change changeLast = this._changesTemp.last();
            preEnd = changeLast.getPreEnd() - changeLast.getOriginalEnd();
        }
        if (mergedOverlappingChange == null) {
            int i10 = preMin - preEnd;
            mergedOverlappingChange = new Change(preMin, preMax + postDelta, i10, (preMax - preMin) + i10);
        } else {
            if (mergedOverlappingChange.getPreStart() > preMin) {
                mergedOverlappingChange.setPreStart(preMin);
                mergedOverlappingChange.setOriginalStart(preMin);
            }
            if (preMax > mergedOverlappingChange.getPreEnd()) {
                int preEnd2 = mergedOverlappingChange.getPreEnd() - mergedOverlappingChange.getOriginalEnd();
                mergedOverlappingChange.setPreEnd(preMax);
                mergedOverlappingChange.setOriginalEnd(preMax - preEnd2);
            }
            mergedOverlappingChange.setPreEnd(mergedOverlappingChange.getPreEnd() + postDelta);
        }
        this._changesTemp.add(mergedOverlappingChange);
    }

    public final void clearChanges() {
        this._changes.clear();
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    public int getChangeCount() {
        return this._changes.getSize();
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    /* JADX INFO: renamed from: getOriginalRange--jx7JFs */
    public long mo1053getOriginalRangejx7JFs(int changeIndex) {
        Change change = this._changes.getContent()[changeIndex];
        return TextRangeKt.TextRange(change.getOriginalStart(), change.getOriginalEnd());
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    /* JADX INFO: renamed from: getRange--jx7JFs */
    public long mo1054getRangejx7JFs(int changeIndex) {
        Change change = this._changes.getContent()[changeIndex];
        return TextRangeKt.TextRange(change.getPreStart(), change.getPreEnd());
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ChangeList(changes=[");
        MutableVector<Change> mutableVector = this._changes;
        int size = mutableVector.getSize();
        if (size > 0) {
            Change[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                Change change = content[i10];
                sb2.append("(" + change.getOriginalStart() + ',' + change.getOriginalEnd() + ")->(" + change.getPreStart() + ',' + change.getPreEnd() + ')');
                if (i10 < getChangeCount() - 1) {
                    sb2.append(", ");
                }
                i10++;
            } while (i10 < size);
        }
        sb2.append("])");
        return sb2.toString();
    }

    public final void trackChange(int preStart, int preEnd, int postLength) {
        int preEnd2;
        if (preStart == preEnd && postLength == 0) {
            return;
        }
        int iMin = Math.min(preStart, preEnd);
        int iMax = Math.max(preStart, preEnd);
        int i10 = postLength - (iMax - iMin);
        Change change = null;
        boolean z = false;
        for (int i11 = 0; i11 < this._changes.getSize(); i11++) {
            Change change2 = this._changes.getContent()[i11];
            int preStart2 = change2.getPreStart();
            if ((iMin > preStart2 || preStart2 > iMax) && (iMin > (preEnd2 = change2.getPreEnd()) || preEnd2 > iMax)) {
                if (change2.getPreStart() > iMax && !z) {
                    appendNewChange(change, iMin, iMax, i10);
                    z = true;
                }
                if (z) {
                    change2.setPreStart(change2.getPreStart() + i10);
                    change2.setPreEnd(change2.getPreEnd() + i10);
                }
                this._changesTemp.add(change2);
            } else if (change == null) {
                change = change2;
            } else {
                change.setPreEnd(change2.getPreEnd());
                change.setOriginalEnd(change2.getOriginalEnd());
            }
        }
        if (!z) {
            appendNewChange(change, iMin, iMax, i10);
        }
        MutableVector<Change> mutableVector = this._changes;
        this._changes = this._changesTemp;
        this._changesTemp = mutableVector;
        mutableVector.clear();
    }

    public ChangeTracker(ChangeTracker changeTracker) {
        MutableVector<Change> mutableVector;
        int size;
        int i10 = 0;
        this._changes = new MutableVector<>(new Change[16], 0);
        this._changesTemp = new MutableVector<>(new Change[16], 0);
        if (changeTracker == null || (mutableVector = changeTracker._changes) == null || (size = mutableVector.getSize()) <= 0) {
            return;
        }
        Change[] content = mutableVector.getContent();
        do {
            Change change = content[i10];
            this._changes.add(new Change(change.getPreStart(), change.getPreEnd(), change.getOriginalStart(), change.getOriginalEnd()));
            i10++;
        } while (i10 < size);
    }

    public /* synthetic */ ChangeTracker(ChangeTracker changeTracker, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : changeTracker);
    }
}
