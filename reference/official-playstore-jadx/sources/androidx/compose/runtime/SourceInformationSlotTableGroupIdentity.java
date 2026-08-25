package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/SourceInformationSlotTableGroupIdentity;", "", "parentIdentity", "index", "", "(Ljava/lang/Object;I)V", "getIndex", "()I", "getParentIdentity", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class SourceInformationSlotTableGroupIdentity {
    private final int index;
    private final Object parentIdentity;

    public SourceInformationSlotTableGroupIdentity(Object obj, int i10) {
        this.parentIdentity = obj;
        this.index = i10;
    }

    public static /* synthetic */ SourceInformationSlotTableGroupIdentity copy$default(SourceInformationSlotTableGroupIdentity sourceInformationSlotTableGroupIdentity, Object obj, int i10, int i11, Object obj2) {
        if ((i11 & 1) != 0) {
            obj = sourceInformationSlotTableGroupIdentity.parentIdentity;
        }
        if ((i11 & 2) != 0) {
            i10 = sourceInformationSlotTableGroupIdentity.index;
        }
        return sourceInformationSlotTableGroupIdentity.copy(obj, i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Object getParentIdentity() {
        return this.parentIdentity;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final SourceInformationSlotTableGroupIdentity copy(Object parentIdentity, int index) {
        return new SourceInformationSlotTableGroupIdentity(parentIdentity, index);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceInformationSlotTableGroupIdentity)) {
            return false;
        }
        SourceInformationSlotTableGroupIdentity sourceInformationSlotTableGroupIdentity = (SourceInformationSlotTableGroupIdentity) other;
        return p.a(this.parentIdentity, sourceInformationSlotTableGroupIdentity.parentIdentity) && this.index == sourceInformationSlotTableGroupIdentity.index;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Object getParentIdentity() {
        return this.parentIdentity;
    }

    public int hashCode() {
        return (this.parentIdentity.hashCode() * 31) + this.index;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SourceInformationSlotTableGroupIdentity(parentIdentity=");
        sb2.append(this.parentIdentity);
        sb2.append(", index=");
        return a0.c.o(sb2, this.index, ')');
    }
}
