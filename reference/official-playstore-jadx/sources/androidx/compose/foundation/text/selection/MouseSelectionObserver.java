package androidx.compose.foundation.text.selection;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0006J\"\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH&ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\"\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH&ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000eJ\u000f\u0010\u0013\u001a\u00020\u0012H&¢\u0006\u0004\b\u0013\u0010\u0014ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "", "Landroidx/compose/ui/geometry/Offset;", "downPosition", "", "onExtend-k-4lQ0M", "(J)Z", "onExtend", "dragPosition", "onExtendDrag-k-4lQ0M", "onExtendDrag", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustment", "onStart-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "onStart", "onDrag-3MmeM6k", "onDrag", "Lx6/t0;", "onDragDone", "()V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MouseSelectionObserver {
    /* JADX INFO: renamed from: onDrag-3MmeM6k */
    boolean mo948onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment);

    void onDragDone();

    /* JADX INFO: renamed from: onExtend-k-4lQ0M */
    boolean mo949onExtendk4lQ0M(long downPosition);

    /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
    boolean mo950onExtendDragk4lQ0M(long dragPosition);

    /* JADX INFO: renamed from: onStart-3MmeM6k */
    boolean mo951onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment);
}
