package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH¦\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/Measured;", "", "measuredHeight", "", "getMeasuredHeight", "()I", "measuredWidth", "getMeasuredWidth", "parentData", "getParentData", "()Ljava/lang/Object;", "get", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Measured {
    int get(AlignmentLine alignmentLine);

    int getMeasuredHeight();

    int getMeasuredWidth();

    Object getParentData();
}
